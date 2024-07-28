package com.example.electricanalyse.logic.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.electricanalyse.logic.model.ApplianceSum

interface CompareRepositoryInter{

    fun getMonthList():LiveData<List<ApplianceSum>>

}
object CompareRepository : CompareRepositoryInter{

    // 测试
    override fun getMonthList(): LiveData<List<ApplianceSum>> {
        val livedata = MutableLiveData<List<ApplianceSum>>()
        val powerValues = mutableMapOf<String, Double>()
        powerValues["202201"] = 30.1
        powerValues["202202"] = 90.1
        // 将功率值列表转换为 ApplianceSum 对象列表
        val applianceSumList = powerValues.map { ApplianceSum(it.key, it.value) }
        livedata.postValue(applianceSumList)
        return livedata
    }



}