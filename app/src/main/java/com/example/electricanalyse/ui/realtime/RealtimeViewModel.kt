package com.example.electricanalyse.ui.realtime

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electricanalyse.logic.Repository.ApplianceRepository
import com.example.electricanalyse.logic.Repository.RealtimeRepository
import com.example.electricanalyse.logic.model.Appliance
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RealtimeViewModel : ViewModel() {

    companion object{
        private const val TAG = "RealtimeViewModel"
    }

    private val repository: ApplianceRepository by lazy {
        RealtimeRepository
    }

    private val _applianceList = MutableLiveData<MutableList<Appliance>>()
    val applianceList:MutableLiveData<MutableList<Appliance>> get() = _applianceList

    fun updateApplianceList(newList: MutableList<Appliance>){
        _applianceList.value = newList
    }


    init {
        loadApplianceList()
    }

    //实际开发应该使用sse等通信技术，这里做为演示使用轮询式的技术访问仓库层
    private fun loadApplianceList() {
        viewModelScope.launch {
            while(true) {
                try {
                    val applianceList = repository.getApplianceList()
                    _applianceList.postValue(applianceList)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(3000)
                Log.d(TAG, "loadApplianceList: cycle time ")
            }

        }
    }
}