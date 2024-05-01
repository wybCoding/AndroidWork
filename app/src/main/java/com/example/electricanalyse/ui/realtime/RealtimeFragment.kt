package com.example.electricanalyse.ui.realtime

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.electricanalyse.databinding.FragmentRealtimeBinding
import com.example.electricanalyse.logic.model.Appliance

class RealtimeFragment : Fragment() {

    companion object{
        private const val TAG = "RealtimeFragment"
    }

    private var _binding: FragmentRealtimeBinding? = null
    private val binding get() = _binding!!

    private lateinit var realtimeViewModel:RealtimeViewModel

    private lateinit var adapter: RealtimeAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        realtimeViewModel =
            ViewModelProvider(this).get(RealtimeViewModel::class.java)

        _binding = FragmentRealtimeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: get in")
        //配置RecyclerList
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        adapter = RealtimeAdapter(mutableListOf())
        binding.recyclerView.adapter = adapter

        realtimeViewModel.applianceList.observe(viewLifecycleOwner){
            newList ->
            run {
                Log.d(TAG, "onViewCreated: data changed")
                adapter.list = newList
                adapter.notifyDataSetChanged()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}