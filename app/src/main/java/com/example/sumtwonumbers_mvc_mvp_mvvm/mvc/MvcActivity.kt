package com.example.sumtwonumbers_mvc_mvp_mvvm.mvc

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.sumtwonumbers_mvc_mvp_mvvm.databinding.ActivityMvcBinding

class MvcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvcBinding
    private val model = MvcDivisionModel()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.numA.also {
            it.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    model.a = getValue(it.text.toString())
                    showResult()
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
                    model.b = getValue(it.text.toString(), differenceZero = true)
                    showResult()
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
            model.a = getValue(binding.numA.text.toString())
            model.b = getValue(binding.numB.text.toString(), differenceZero = true)
            showResult()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun showResult() {
        if (!model.canDivision()) {
            binding.message.isVisible = true
            binding.result.text = "_"
            return
        }
        binding.message.isVisible = false
        binding.result.text = model.division().toString()
    }

    private fun hideSoftInput() =
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
            currentFocus?.rootView?.windowToken,
            0
        )

    private fun getValue(string: String, differenceZero: Boolean = false) = try {
        string.toFloat()
    } catch (ex: NumberFormatException) {
        if (differenceZero) 1F else 0F
    }
}