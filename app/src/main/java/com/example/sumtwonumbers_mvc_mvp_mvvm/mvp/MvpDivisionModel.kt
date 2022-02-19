package com.example.sumtwonumbers_mvc_mvp_mvvm.mvp

class MvpDivisionModel(var a: Float = 0F, var b: Float = 1F) {

    fun a(stringValue: String) {
        a = getValue(stringValue) ?: 0F
    }

    fun b(stringValue: String) {
        b = getValue(stringValue) ?: 1F
    }

    private fun getValue(stringValue: String) = stringValue.toFloatOrNull()

    fun division() = a / b

    fun canDivision() = !b.equals(0F)
}