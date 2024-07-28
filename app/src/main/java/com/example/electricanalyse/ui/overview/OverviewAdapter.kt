package com.example.electricanalyse.ui.overview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.electricanalyse.R
import com.example.electricanalyse.logic.model.ApplianceSum

class OverviewAdapter (var list: MutableList<ApplianceSum>): RecyclerView.Adapter<OverviewAdapter.OverviewViewHolder>(){

    private var totalPower = list.sumOf { it.power } // 添加总功率值变量

    class OverviewViewHolder private constructor( itemView: View) : RecyclerView.ViewHolder(itemView){
        val tv_overview_item = itemView.findViewById<TextView>(R.id.tv_overview_item)
        val tv_result_item = itemView.findViewById<TextView>(R.id.tv_result_item)
        val progressbar_overview_item = itemView.findViewById<ProgressBar>(R.id.progressbar_overview_item)

        companion object {
            private const val TAG = "OverviewAdapter"
            fun from(parent: ViewGroup): OverviewViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.overview_item, parent, false)
                return OverviewViewHolder(view)
            }
        }
            fun bind(item:ApplianceSum , totalpower:Double){
                val resource = itemView.context.resources
                tv_overview_item.text = item.time
                tv_result_item.text = item.power.toString()
                progressbar_overview_item.setProgress((item.power / totalpower * 100).toInt(),false)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewViewHolder {
        return OverviewViewHolder.from(parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OverviewViewHolder, position: Int) {
       val item = list[position]
       holder.bind(item,totalPower)
    }

    //更新列表的新方法
    fun updateList(){
        totalPower = list.sumOf { it.power }
        notifyDataSetChanged()
    }

}