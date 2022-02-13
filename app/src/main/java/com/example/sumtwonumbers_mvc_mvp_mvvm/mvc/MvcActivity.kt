package com.example.sumtwonumbers_mvc_mvp_mvvm.mvc

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.sumtwonumbers_mvc_mvp_mvvm.databinding.ActivityMvcBinding

class MvcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvcBinding
    private val model = MvcSumModel()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.soHangA.also {
            it.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    model.a = model.getNumber(it.text.toString())
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

        binding.soHangB.also {
            it.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    model.b = model.getNumber(it.text.toString())
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
            model.a = model.getNumber(binding.soHangA.text.toString())
            model.b = model.getNumber(binding.soHangB.text.toString())
            showResult()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun showResult() {
        binding.result.text = model.sum().toString()
    }

    private fun hideSoftInput() =
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
            currentFocus?.rootView?.windowToken,
            0
        )
}