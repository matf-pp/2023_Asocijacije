package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BetweenPhasesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_between_phases)

        val btnBegin: Button = findViewById(R.id.btnBeginPhase)
        btnBegin.setOnClickListener(){
            val intent = Intent(this, TimerActivity::class.java)
            startActivity(intent)
        }
    }
}