package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.db.DatabaseServiceProvider
import org.w3c.dom.Text
import com.example.myapplication.model.Game
import com.example.myapplication.model.Player

class WordsEnteringActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words_entering)
        val btnBack: Button = findViewById(R.id.btnBackWordEntering)
        btnBack.setOnClickListener(){
            val intent = Intent(this, ChooseGroupActivity::class.java)
            startActivity(intent)
        }

        //deklaracije objekata
        val tvCurPlayerEntering = findViewById<TextView>(R.id.tvCurPlayerEntering)
        val etAddWords = findViewById<EditText>(R.id.eTAddWords)
        val btnOK:Button = findViewById(R.id.btnOK2)
        val tvAddedWords = findViewById<TextView>(R.id.addedWords)
        val tvError = findViewById<TextView>(R.id.numberOfEnteredWordsError)
        val btnNextPlayer:Button = findViewById(R.id.btnNextPlayer)

        //todo napraviti geter za br igraca
        val numOfPl:Int = DatabaseServiceProvider.db.getGame().getPairs().size*2
        val numOfWordsPerPl = DatabaseServiceProvider.db.getGame().getNumOfWordsPerPlayer()

        var listOfPl = DatabaseServiceProvider.db.getGame().listForWordEntering()
        //listOfPl.add("Anja")
        //listOfPl.add("Tanja")
        //var listOfWords = mutableListOf<String>()
        var playerCounter =0
        var wordCounter = 0


        var text:String = listOfPl[playerCounter].getName().plus(" unosi reci")
        tvCurPlayerEntering.text=text
        btnOK.setOnClickListener(){
            val word = etAddWords.text.toString()
            if(wordCounter == numOfWordsPerPl) {
                etAddWords.setText("")
                tvError.text = "Vec je uneto dovoljno reci"
            }else{
                if(word.isNotBlank()){
                    DatabaseServiceProvider.db.getGame().addWord(word)
                    tvAddedWords.text = "${tvAddedWords.text} \n $word"
                    wordCounter+=1
                    etAddWords.setText("")
                }
            }
        }

        btnNextPlayer.setOnClickListener{
            if(wordCounter!=numOfWordsPerPl){
                tvError.text = "Nedovoljno unetih reci"
            }else{
                if (numOfPl - 1 == playerCounter) {
                        val intent = Intent(this, BetweenPhasesActivity::class.java)
                        startActivity(intent)
                }else {
                    if(numOfPl -2 == playerCounter ){
                        btnNextPlayer.text="start"
                    }
                    wordCounter = 0
                    playerCounter += 1
                    tvError.text = ""
                    tvAddedWords.text = ""
                    text = listOfPl[playerCounter].getName().plus(" unosi reci")
                    tvCurPlayerEntering.text = text
                }
            }
        }
    }
}