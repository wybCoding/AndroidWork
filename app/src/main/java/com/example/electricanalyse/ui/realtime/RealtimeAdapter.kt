package com.example.electricanalyse.ui.realtime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.electricanalyse.R
import com.example.electricanalyse.logic.model.Appliance

class RealtimeAdapter(var list : MutableList<Appliance>) : RecyclerView.Adapter<RealtimeAdapter.RealtimeViewHolder>() {

    //该写法条理性更好！

    class RealtimeViewHolder private constructor( itemView: View) : RecyclerView.ViewHolder(itemView){
        val tv_name : TextView = itemView.findViewById(R.id.tv_name)
        val tv_power : TextView = itemView.findViewById(R.id.tv_power)
        val tv_update : TextView = itemView.findViewById(R.id.tv_update)

        companion object {

            //onCreate
            fun from(parent: ViewGroup): RealtimeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.realtime_item, parent, false)
                return RealtimeViewHolder(view)
            }
        }
        //onbind
        fun bind(
            item: Appliance
        ) {
            val res =itemView.context.resources
            tv_name.text = item.name
            tv_power.text = item.power.toString()
            tv_update.text = item.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RealtimeViewHolder {
        return RealtimeViewHolder.from(parent)


    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: RealtimeViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

    }




}