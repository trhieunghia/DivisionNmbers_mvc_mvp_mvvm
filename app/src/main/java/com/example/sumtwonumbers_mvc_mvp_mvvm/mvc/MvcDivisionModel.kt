package com.example.sumtwonumbers_mvc_mvp_mvvm.mvc

class MvcDivisionModel(var a: Float = 0F, var b: Float = 1F) {

    fun division() = a / b

    fun canDivision() = !b.equals(0F)
}