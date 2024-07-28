package com.example.electricanalyse.util

import com.example.electricanalyse.logic.model.ApplianceSum

object DateChange {

    //用于修改时间的函数，实际使用时仍会修改，先以String类型举例
    fun timeChangeDate(time: String) : Float{
        val hours = time.substring(0, 2).toInt()
        val minutes = time.substring(2, 4).toInt()
        val number = (hours + (minutes / 60.0)).toFloat()
        return number
    }
    fun monthChangeDate(month: String): Float {
        return month.substring(month.length - 2).toFloat()
    }
    fun weekChangeDate(week: String):Float{
        return week.substring(week.length - 2).toFloat()
    }

}