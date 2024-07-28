package com.example.electricanalyse.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.electricanalyse.logic.Repository.OverviewRepository
import com.example.electricanalyse.logic.model.Appliance
import com.example.electricanalyse.logic.model.ApplianceSum

class OverviewViewModel : ViewModel() {
    //维护list变量存储图表数据 , 受开发时间影响，暂时使用list表示下方具体项目
    private val _list = MutableLiveData<List<ApplianceSum>>()
    val list: LiveData<List<ApplianceSum>> get() = _list



    var topSelectedStats = MutableLiveData<String>()
    fun getOverviewList(type: String) = OverviewRepository.getOverviewList(type)

    init {
        // 使用switchMap方法转换和合并topSelectedStats和getOverviewList的LiveData源
        topSelectedStats.switchMap { type ->
            getOverviewList(type)
        }.observeForever { result ->
            if (result != null) {
                _list.value = result
            }
        }

    }

}