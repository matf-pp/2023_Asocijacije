package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GroupInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_info)

        val btnBack: Button = findViewById(R.id.btnBackGroupInfo)
        btnBack.setOnClickListener{
            val intent = Intent(this, ChooseGroupActivity::class.java)
            startActivity(intent)
        }
    }
}