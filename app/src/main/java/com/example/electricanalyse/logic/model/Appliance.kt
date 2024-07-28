package com.example.electricanalyse.logic.model


//先做简单测试，后期会修改
data class Appliance(val name:String , val time:String , val power: Double)

data class ApplianceSum(val time:String , val power: Double)