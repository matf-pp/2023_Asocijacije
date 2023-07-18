package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.model.Game
import java.io.Serializable

class GroupInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_info)

        val btnBack: Button = findViewById(R.id.btnBackGroupInfo)
        btnBack.setOnClickListener{
            val intent = Intent(this, ChooseGroupActivity::class.java)
            startActivity(intent)
        }

        //inicijalizacija objekata
        val tVListOfPairs = findViewById<TextView>(R.id.listOfPairs)
        val numbers = resources.getStringArray(R.array.NumOfWords)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, numbers)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView3)
        autocompleteTV.setAdapter(arrayAdapter)

        //preuzimanje iz proslog activity-ja:
        //ne radi ovo :(((((

        /*
        val game = intent.getSerializableExtra("game") as Game
        game.makeRandomPairs()
        tVListOfPairs.text = game.listOfPairs.toString()
        */

        //dugmici
        val tvError = findViewById<TextView>(R.id.numOfWordsError)
        val btnStart: Button = findViewById(R.id.btnStart)
        btnStart.setOnClickListener{
            if(autocompleteTV.text.toString().isEmpty()){
                tvError.text = "Izaberite broj reci!"
            }else{
                val intent = Intent(this, WordsEnteringActivity::class.java)
                startActivity(intent)
            }
        }

        val btnPreviousGames: Button = findViewById(R.id.btnPreviousGames)
        btnPreviousGames.setOnClickListener{
            val intent = Intent(this, PreviousGamesActivity::class.java)
            startActivity(intent)
        }

        //treba nam i random dugme
    }
}