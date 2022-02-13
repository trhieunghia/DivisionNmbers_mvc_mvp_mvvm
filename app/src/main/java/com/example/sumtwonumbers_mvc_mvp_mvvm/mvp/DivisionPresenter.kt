package com.example.sumtwonumbers_mvc_mvp_mvvm.mvp

class DivisionPresenter(private val divisionInterface: MvpDivisionInterface) {

    fun calculating(values: MvpDivisionModel) {
        if (values.canDivision()) {
            divisionInterface.calculatorEnable()
            return
        }

        divisionInterface.error()
    }
}