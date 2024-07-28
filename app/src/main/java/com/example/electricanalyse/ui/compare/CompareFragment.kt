package com.example.electricanalyse.ui.compare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.electricanalyse.databinding.FragmentCompareBinding
import com.example.electricanalyse.ui.components.UtilBarChart

class CompareFragment : Fragment() {

    val compareViewModel by lazy { ViewModelProvider(this).get(CompareViewModel::class.java) }

    private var _binding: FragmentCompareBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCompareBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compareViewModel.refresh()

        //图表监测
        compareViewModel.list.observe(viewLifecycleOwner){
        UtilBarChart.setChartForm(binding.chartCompare, "月", "W",1F , 12F)
        UtilBarChart.loadData(binding.chartCompare, it , UtilBarChart.DATE_MONTH )
        }

        //变化量监测后赋值
        compareViewModel.changeNum.observe(viewLifecycleOwner){changeNumValue->
            val formattedChangeNum = String.format("%.3f%%", changeNumValue * 100)
            binding.tvResult.text = formattedChangeNum
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}