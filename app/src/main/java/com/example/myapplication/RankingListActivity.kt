package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RankingListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking_list)

        val btnMainMenu: Button = findViewById(R.id.btnMenu)
        btnMainMenu.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnNewGame :Button = findViewById(R.id.btnNewGame)
        btnNewGame.setOnClickListener(){
            val intent = Intent(this, GroupInfoActivity::class.java)
            startActivity(intent)
        }
    }
}