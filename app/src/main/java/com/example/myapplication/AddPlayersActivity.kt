package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.model.PlayerGroup

class AddPlayersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_players)

        val btnBack: Button = findViewById(R.id.btnBackAddPlayers)
        btnBack.setOnClickListener(){
            val intent = Intent(this, NewGroupActivity::class.java)
            startActivity(intent)
        }


    }
}