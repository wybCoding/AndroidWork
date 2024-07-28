package com.example.electricanalyse.ui.compare


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.electricanalyse.logic.Repository.CompareRepository
import com.example.electricanalyse.logic.model.ApplianceSum


class CompareViewModel : ViewModel() {

    companion object{
        private const val TAG = "CompareViewModel"
    }

    //维护list变量存储图表数据
    private val refreshLiveData = MutableLiveData<Any?>()
    val list: LiveData<List<ApplianceSum>> = refreshLiveData.switchMap {
        CompareRepository.getMonthList()
    }

    val changeNum: LiveData<Double> = list.map {
        Log.d(TAG, ": data get and size is ${it.size}  , first is ${it[it.size - 1].power}  last is ${it[it.size - 2].power}")
        if(it.size >= 2){
            (it[it.size - 1].power - it[it.size - 2].power) / it[it.size - 2].power
        }
        else 0.0
    }

    fun refresh(){            //刷新指令，同时也是获取数据指令
        refreshLiveData.value = refreshLiveData.value
    }






}