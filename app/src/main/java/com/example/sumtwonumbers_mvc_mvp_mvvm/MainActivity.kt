package com.example.sumtwonumbers_mvc_mvp_mvvm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sumtwonumbers_mvc_mvp_mvvm.databinding.ActivityMainBinding
import com.example.sumtwonumbers_mvc_mvp_mvvm.mvc.MvcActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickView()
    }

    private fun setOnClickView() {
        binding.mvcPattern.setOnClickListener {
            startActivity(Intent(this, MvcActivity::class.java))
        }

        binding.mvpPattern.setOnClickListener {
            startActivity(Intent(this, MvpActivity::class.java))
        }

        binding.mvvmPattern.setOnClickListener {
            startActivity(Intent(this, MvvmActivity::class.java))
        }
    }
}