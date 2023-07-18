package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.db.DatabaseServiceProvider

class BetweenPlayersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_between_players)

        //dugme
        val btnBegin: Button = findViewById(R.id.btnBegin)
        btnBegin.setOnClickListener(){
            val intent = Intent(this, TimerActivity::class.java)
            startActivity(intent)
        }
    }
}