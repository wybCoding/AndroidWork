package com.example.electricanalyse.ui.overview

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.electricanalyse.databinding.FragmentOverviewBinding
import com.example.electricanalyse.logic.model.ApplianceSum
import com.example.electricanalyse.ui.components.UtilBarChart
import com.example.electricanalyse.ui.realtime.RealtimeAdapter
import com.example.electricanalyse.ui.realtime.RealtimeFragment
import com.example.electricanalyse.util.DateChange
import com.example.electricanalyse.util.DateChange.timeChangeDate
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.hurryyu.bestchooser.ChooserView
import com.hurryyu.bestchooser.ChooserViewGroupManager
import com.hurryyu.bestchooser.OnChooseChangeListener


class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val overviewViewModel by lazy { ViewModelProvider(this).get(OverviewViewModel::class.java) }

    companion object{
        private const val TAG = "OverviewFragment"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //初始化，绘制一个空表，选中状态为空
        overviewViewModel.topSelectedStats.postValue("")
        //顶部导航栏配置
        val topChoose = ChooserViewGroupManager.Builder().addChooserView(
            chooserView = *arrayOf(
                binding.navigationTop.selectorDay,
                binding.navigationTop.selectorWeek
            )
        )
            .build()

        //顶部导航设置监听，每当选项变化时通知viewModel
        topChoose.setOnChooseChangeListener(object : OnChooseChangeListener(){
            override fun onChanged(
                chooserView: ChooserView,
                viewTag: String,
                groupTag: String,
                isSelected: Boolean
            ) {
                overviewViewModel.topSelectedStats.postValue(viewTag)
                Log.d(TAG, "onChanged: topStatsChanged viewTag = ${viewTag}")
            }

        })


        //图表配置
        //每当顶部导航发生变化时就更新图表等数据
        overviewViewModel.list.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: list change and list is ${it} and ${overviewViewModel.topSelectedStats.value}")
            if(overviewViewModel.topSelectedStats.value == "本日") {
                UtilBarChart.setChartForm(binding.chartOverview, "时", "W",1F , 24F)
                UtilBarChart.loadData(
                    binding.chartOverview,
                    it,
                    UtilBarChart.DATE_DAY,
                )
            }
            else {
                UtilBarChart.setChartForm(binding.chartOverview, "日", "W",1F , 7F)
                UtilBarChart.loadData(
                    binding.chartOverview,
                    it,
                    UtilBarChart.DATE_WEEK,
                )
            }
        }

        //配置RecyclerList
        binding.recyclerviewOverview.layoutManager = LinearLayoutManager(view.context)
        val adapter = OverviewAdapter(mutableListOf())
        binding.recyclerviewOverview.adapter = adapter

        overviewViewModel.list.observe(viewLifecycleOwner){
                newList ->
            run {
                adapter.list = newList.toMutableList()
                adapter.updateList()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //表明又没有选中的了
        overviewViewModel.topSelectedStats.postValue("")
        _binding = null
    }



}

