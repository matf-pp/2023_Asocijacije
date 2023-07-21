package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.db.DatabaseServiceProvider
import com.example.myapplication.model.Player

class BetweenPhasesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_between_phases)


        //deklaracija objekata
        val tvEndOfPhase = findViewById<TextView>(R.id.tvEndOfPhase)
        val tvRangListPhase = findViewById<TextView>(R.id.tvRangListPhase)
        val  tvListOfPoints= findViewById<TextView>(R.id.tvListOfPointsPhase)
        val tvFirstPlayer = findViewById<TextView>(R.id.tvFirstPlayer)
        val btnBegin: Button = findViewById(R.id.btnBeginPhase)


        //postavljanje naslova
        val numberOfPhase:Int = DatabaseServiceProvider.db.getGame().getCurrentGamePhase()
        var text:String
        text = if(numberOfPhase==1){
            "Pripremite se za igru"
        }else{
            "Kraj faze ${numberOfPhase-1}"
        }
        tvEndOfPhase.text = text

        //postavljanje narednog igraca
        val currentPlayer:Player
        if(numberOfPhase == 1){
            currentPlayer = DatabaseServiceProvider.db.getGame().listForWordEntering().first()
            DatabaseServiceProvider.db.getGame().setCurrentPlayer(currentPlayer)
        }else{
            DatabaseServiceProvider.db.getGame().nextPlayer()
            currentPlayer = DatabaseServiceProvider.db.getGame().getCurrentPlayer()
        }
        text = "Naredni igrac je ${currentPlayer.getName()}"
        tvFirstPlayer.text = text

        //postavljanje redosleda
        text=""
        if(numberOfPhase!=1){
            val listOfPairs = DatabaseServiceProvider.db.getGame().sortedByAnswers()
            for(pair in listOfPairs){
                text = "$text \n ${pair.first} & ${pair.second}"
            }
            tvRangListPhase.text = text
            text=""
            var num:Int
            for(p in listOfPairs){
                num = DatabaseServiceProvider.db.getGame().pointsOfAPair(p)
                text = "$text \n $num"
            }
            tvListOfPoints.text = text
        }


        //dugme
        btnBegin.setOnClickListener {
            DatabaseServiceProvider.db.getGame().shuffleWords()
            val firstWord = DatabaseServiceProvider.db.getGame().getWords().first()
            DatabaseServiceProvider.db.getGame().setCurrentWord(firstWord)
            val intent = Intent(this, TimerActivity::class.java)
            startActivity(intent)
        }
    }
}