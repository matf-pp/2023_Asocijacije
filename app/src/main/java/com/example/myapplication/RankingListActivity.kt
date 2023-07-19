package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.db.DatabaseServiceProvider
import com.example.myapplication.model.Game

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

        val tvRangList = findViewById<TextView>(R.id.tvRangList)
        val tvListOfPoints = findViewById<TextView>(R.id.tvListOfPoints)
        var text:String =""
        var num :Int
        var listOfPairs = DatabaseServiceProvider.db.getGame().sortedByAnswers()
        for(pair in listOfPairs){
            text = "$text \n ${pair.first} & ${pair.second}"
        }
        tvRangList.text = text

        text=""
        for(p in listOfPairs){
            num = DatabaseServiceProvider.db.getGame().pointsOfAPair(p)
            text = "$text \n $num"
        }
        tvListOfPoints.text = text

    }
}