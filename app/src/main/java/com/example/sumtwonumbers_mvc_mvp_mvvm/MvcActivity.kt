package com.example.sumtwonumbers_mvc_mvp_mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sumtwonumbers_mvc_mvp_mvvm.databinding.ActivityMvcBinding

class MvcActivity : AppCompatActivity() {

    lateinit var binding: ActivityMvcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvcBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}