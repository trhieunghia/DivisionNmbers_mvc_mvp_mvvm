package com.example.sumtwonumbers_mvc_mvp_mvvm.mvc

class MvcSumModel(var a: Int = 0, var b: Int = 0) {

    fun sum() = a + b

    fun getNumber(num: String) = try {
        num.toFloat().toInt()
    } catch (ex: NumberFormatException) {
        0
    }
}