package com.example.electricanalyse.logic.Repository

import com.example.electricanalyse.logic.model.Appliance
import com.example.electricanalyse.ui.realtime.RealtimeViewModel
import kotlin.random.Random


interface ApplianceRepository {
    suspend fun getApplianceList(): MutableList<Appliance>
}

object RealtimeRepository : ApplianceRepository{

    override suspend fun getApplianceList(): MutableList<Appliance> {
        // 生成随机的家电列表
        val list = mutableListOf<Appliance>()
        for (i in 1..4) { // 生成10个随机的家电对象
            val name = "Appliance ${Random.nextInt(1, 101)}" // 随机生成家电名称
            val date = "2001-${Random.nextInt(1, 13)}-${Random.nextInt(1, 32)}" // 随机生成日期
            val power = Random.nextDouble(1.0, 3000.0) // 随机生成功率
            list.add(Appliance(name, date, power))
        }
        return list
    }


}