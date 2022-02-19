package com.example.sumtwonumbers_mvc_mvp_mvvm.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.sumtwonumbers_mvc_mvp_mvvm.databinding.ActivityMvpBinding

class MvpActivity : AppCompatActivity(), MvpDivisionInterface {

    private lateinit var binding: ActivityMvpBinding
    private var divisionPresenter = DivisionPresenter(this)
    private var divisionModel = MvpDivisionModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupUi() {
        binding.numA.also {
            it.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    divisionModel.a(it.text.toString())
                    divisionPresenter.calculating(divisionModel)
                }
                false
            }

            it.setOnTouchListener { _, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    it.setText("")
                }
                false
            }
        }

        binding.numB.also {
            it.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    divisionModel.b(it.text.toString())
                    divisionPresenter.calculating(divisionModel)
                    hideSoftInput()
                }
                false
            }

            it.setOnTouchListener { _, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    it.setText("")
                }
                false
            }
        }
    }

    @SuppressLint("ServiceCast")
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_UP) {
            divisionModel.a(binding.numA.text.toString())
            divisionModel.b(binding.numB.text.toString())
            divisionPresenter.calculating(divisionModel)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun hideSoftInput() =
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
            currentFocus?.rootView?.windowToken,
            0
        )

    override fun error() {
        binding.message.isVisible = true
        binding.result.text = "_"
    }

    override fun calculatorEnable() {
        binding.message.isVisible = false
        binding.result.text = divisionModel.division().toString()
    }
}