package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlay: Button = findViewById(R.id.btnPlay)
        btnPlay.setOnClickListener(){
            val intent = Intent(this, ChooseGroupActivity::class.java)
            startActivity(intent)
        }
        val btnInstruction: Button = findViewById(R.id.btnInstructions)
        btnInstruction.setOnClickListener(){
            val intent = Intent(this, InstructionsActivity::class.java)
            startActivity(intent)
        }
    }
}