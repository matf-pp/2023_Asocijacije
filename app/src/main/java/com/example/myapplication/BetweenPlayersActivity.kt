package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.db.DatabaseServiceProvider

class BetweenPlayersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_between_players)

        //deklaracije
        val tvPairNames = findViewById<TextView>(R.id.tvPairNames)
        val tvNumberOfPoints = findViewById<TextView>(R.id.tvNumberOfPoints)
        val tvNextPlayer = findViewById<TextView>(R.id.tvNextPlayer)
        val btnBegin: Button = findViewById(R.id.btnBegin)

        //par koji je zavrsio
        var currentPlayer = DatabaseServiceProvider.db.getGame().getCurrentPlayer()
        var text:String = DatabaseServiceProvider.db.getGame().pairedWith(currentPlayer).getName()
        text = "$text & ${currentPlayer.getName()} su osvojili: "
        tvPairNames.text = text

        //broj poena
        text = DatabaseServiceProvider.db.getGame().numOfCorrectAnswersInPair(currentPlayer).toString()
        tvNumberOfPoints.text = text


        //sledeci igrac
        DatabaseServiceProvider.db.getGame().nextPlayer()
        currentPlayer = DatabaseServiceProvider.db.getGame().getCurrentPlayer()
        text = currentPlayer.getName()
        tvNextPlayer.text="Naredni igrac je $text"

        //dugme
        btnBegin.setOnClickListener {
            val intent = Intent(this, TimerActivity::class.java)
            startActivity(intent)
        }
    }
}