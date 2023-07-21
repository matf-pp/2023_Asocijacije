package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.db.DatabaseServiceProvider

class TimerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        //deklaracije objekata
        val tvTimer = findViewById<TextView>(R.id.tvTimer)
        val tvNumberOfPhase = findViewById<TextView>(R.id.tvNumberOfPhase)
        val tvNameOfPhase = findViewById<TextView>(R.id.tvNameOfPhase)
        val tvWord = findViewById<TextView>(R.id.tvWord)
        val btnCorrectAnswer: Button = findViewById(R.id.btnCorrectAnswer)

        //naslov
        var numOfPhase = DatabaseServiceProvider.db.getGame().getCurrentGamePhase()
        var text = "FAZA $numOfPhase"
        tvNumberOfPhase.text = text
        text = if(numOfPhase == 1){
            "verbalno objasnjavanje"
        }else{
            if(numOfPhase == 2){
                "pantomima"
            }else{
                "crtanje"
            }
        }
        tvNameOfPhase.text = text

        var word = DatabaseServiceProvider.db.getGame().getCurrentWord()
        val currentPlayer = DatabaseServiceProvider.db.getGame().getCurrentPlayer()
        object : CountDownTimer(5000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = "" + millisUntilFinished / 1000
                tvWord.text = word

                btnCorrectAnswer.setOnClickListener {
                    currentPlayer.correctAnswersIncrease()
                    val temp = DatabaseServiceProvider.db.getGame().getNextWord(word)
                    if(temp.isNullOrEmpty()){
                        if(numOfPhase==3){
                            this.cancel()
                            val intent = Intent(this@TimerActivity,RankingActivity::class.java)
                            startActivity(intent)
                        }else{
                            this.cancel()
                            numOfPhase+=1
                            DatabaseServiceProvider.db.getGame().setCurrentGamePhase(numOfPhase)
                            val intent = Intent(this@TimerActivity,BetweenPhasesActivity::class.java)
                            startActivity(intent)
                        }
                    }else{
                        word = temp
                        tvWord.text = word
                    }
                }

            }

            override fun onFinish() {
                val intent = Intent(this@TimerActivity, BetweenPlayersActivity::class.java)
                startActivity(intent)
            }
        }.start()
    }
}