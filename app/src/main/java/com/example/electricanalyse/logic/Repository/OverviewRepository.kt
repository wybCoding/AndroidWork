package com.example.electricanalyse.logic.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.electricanalyse.logic.model.Appliance
import com.example.electricanalyse.logic.model.ApplianceSum

interface OverviewRepositoryInter{
    fun getOverviewList(type: String): LiveData<List<ApplianceSum>>   //type分为“本日”“本周”两种，将来也可以扩展
}
object OverviewRepository: OverviewRepositoryInter {

    //待实现,是一个测试，举例一个返回
    override fun getOverviewList(type: String): LiveData<List<ApplianceSum>> {
        val livedata = MutableLiveData<List<ApplianceSum>>()
        val powerValues = mutableMapOf<String, Double>()
        // 根据类型参数生成功率值列表
        when (type) {
            "本日" -> {
                powerValues["0800"] = 56.1
                powerValues["0900"] = 53.1
                powerValues["1000"] = 25.1
                powerValues["1100"] = 46.9
            }

            "本周" -> {
                powerValues["0901"] = 30.1
                powerValues["0902"] = 90.1
            }
        }
        // 将功率值列表转换为 ApplianceSum 对象列表
        val applianceSumList = powerValues.map { ApplianceSum(it.key, it.value) }
        livedata.postValue(applianceSumList)
        return livedata
    }
}