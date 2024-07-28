package com.example.electricanalyse.ui.components

import androidx.core.content.ContextCompat
import com.example.electricanalyse.R
import com.example.electricanalyse.logic.model.ApplianceSum
import com.example.electricanalyse.util.DateChange
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate



object UtilBarChart {


    const val DATE_DAY = 0
    const val DATE_MONTH = 1
    const val DATE_WEEK = 2
    //为了看到更明显的效果，我这里设置了图形在上下左右四个方向上的偏移量
    fun loadData(bc: BarChart, data: List<ApplianceSum>? , type : Int ) {

    //注意，在loadData方法中涉及到时间戳的转换，type决定了转换类型（年月单位还是日时等单位）

        val barEntries =ArrayList<BarEntry>().apply {

            //根据数据类型添加数据
            if(data != null) {
                for (item in data) {
                    if(type == DATE_DAY)
                    add(BarEntry(DateChange.timeChangeDate(item.time), item.power.toFloat()))
                    else if(type == DATE_MONTH){
                        add(BarEntry(DateChange.monthChangeDate(item.time), item.power.toFloat()))
                    }
                    else if(type == DATE_WEEK){
                        add(BarEntry(DateChange.weekChangeDate(item.time), item.power.toFloat()))
                    }
                }
            }
        }


        val barDataSet = BarDataSet(barEntries,"用电量")
        barDataSet.setColor(R.color.purple_200)
        val ba = BarData(barDataSet)

        bc.setData(ba)
        bc.notifyDataSetChanged()
        bc.invalidate()
    }
    //存储的备用方法
    fun setChartForm2(bc: BarChart , yUnit : String , xUnit : String ) {


        // 设置偏移
        bc.extraTopOffset = 25F
        bc.extraLeftOffset = 30F
        bc.extraRightOffset = 30F
        bc.extraBottomOffset = 30F
        // 显示横坐标
        bc.xAxis.isEnabled = true

        // 放大字体大小
        bc.description.textSize = 14f
        bc.legend.textSize = 14f
        bc.xAxis.textSize = 14f
        bc.axisLeft.textSize = 14f
        bc.axisRight.textSize = 14f

        // 禁止竖直移动图表
        bc.setDragEnabled(false)
        bc.setScaleEnabled(false)
        bc.setDragYEnabled(false)

        //隐藏右侧坐标
        val rightAxis = bc.axisRight
        rightAxis.setDrawLabels(false) // 隐藏右侧坐标轴的标签
        rightAxis.setDrawGridLines(false) // 隐藏右侧坐标轴的网格线

        // 设置柱子变得细一些
        bc.data?.barWidth = 0.1f // 将柱子宽度改为0.3f以使其变窄

        // 设置图表标题
        bc.description.text = "example"

        // 设置纵坐标单位
        //bc.axisLeft.axisMaximum = 100f
        bc.axisLeft.axisMinimum = 0.5f
        //bc.axisLeft.granularity = 10f
        bc.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "$value ${xUnit}"
            }
        }

        // 设置横坐标单位 , 加入了重复判断
        var lastDisplayedValue = -1

        bc.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val intValue = value.toInt()
                return if (intValue != lastDisplayedValue && intValue != 0) {
                    lastDisplayedValue = intValue
                    "${intValue} ${yUnit}"
                } else {
                    ""
                }
            }
        }



        // 设置动画
        bc.animateY(500)
    }

    fun setChartForm(bc: BarChart , yUnit : String , xUnit : String , start : Float ,end: Float){
        // 设置偏移
        bc.extraTopOffset = 35F
        bc.extraLeftOffset = 5F
        bc.extraRightOffset = 5F
        bc.extraBottomOffset = 30F
        // 显示横坐标
        bc.xAxis.isEnabled = true

        // 放大字体大小
        bc.description.textSize = 14f
        bc.legend.textSize = 14f
        bc.xAxis.textSize = 6f
        bc.axisLeft.textSize = 14f
        bc.axisRight.textSize = 14f

        // 禁止竖直移动图表
        bc.setDragEnabled(false)
        bc.setScaleEnabled(false)
        bc.setDragYEnabled(false)

        //隐藏右侧坐标
        val rightAxis = bc.axisRight
        rightAxis.setDrawLabels(false) // 隐藏右侧坐标轴的标签
        rightAxis.setDrawGridLines(false) // 隐藏右侧坐标轴的网格线

        // 设置柱子变得细一些
        bc.data?.barWidth = 0.1f // 将柱子宽度改为0.1f以使其变窄

        // 设置图表标题
        bc.description.text = "example"


        // 设置横纵坐标距离
        bc.axisLeft.axisMinimum = start
        //bc.xAxis.granularity = 1f; // 设置刻度间隔为1
        //bc.xAxis.labelCount = range.toInt(); // 设置标签数量为对应值，以确保所有刻度都显示出来,但实际效果并不好
        bc.xAxis.axisMaximum = end
        //bc.axisLeft.granularity = 10f

        //设置单位
        bc.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "$value ${xUnit}"
            }
        }

        // 设置横坐标单位，使其均匀分布
        bc.xAxis.granularity = 1f // 设置横坐标的刻度间隔为1
        bc.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()} ${yUnit}"
            }
        }

        // 设置动画
        bc.animateY(500)
    }
}
