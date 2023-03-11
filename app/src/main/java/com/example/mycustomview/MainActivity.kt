package com.example.mycustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mycustomview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private  val binding =ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("myTag", "start")
        setContentView(binding.root)
    }
}