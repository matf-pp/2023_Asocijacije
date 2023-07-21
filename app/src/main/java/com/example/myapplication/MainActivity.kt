package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.db.DatabaseServiceProvider
import com.example.myapplication.db.PlayerGroupViewModel
import com.example.myapplication.model.Player
import com.example.myapplication.model.PlayerGroup
//todo provjeri ovo
var firstTimeIn: Boolean = true

class MainActivity : AppCompatActivity() {
    private lateinit var mPlayerGroupViewModel: PlayerGroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlay: Button = findViewById(R.id.btnPlay)
        btnPlay.setOnClickListener(){
            val intent = Intent(this, ChooseGroupActivity::class.java)
            startActivity(intent)
        }
        val btnInstruction: Button = findViewById(R.id.btnInstructions)
        btnInstruction.setOnClickListener(){
            val intent = Intent(this, InstructionsActivity::class.java)
            startActivity(intent)
        }

        //pocetni podaci za testiranje
        mPlayerGroupViewModel = ViewModelProvider(this)[PlayerGroupViewModel::class.java]
        mPlayerGroupViewModel.readAllData.observe(this) { playergroups ->
            DatabaseServiceProvider.db.setPlayerGroups(
                playergroups as MutableList<PlayerGroup>
            )
        }
        //todo neki problem je ovdje, vjerovatno vracanje na ovaj prozor i firstTimeIn
        //dataInit()
    }
    private fun dataInit() {
        if(firstTimeIn) {
            val playerGroupAsocijacije = PlayerGroup("Asocijacije")
            val tanja = Player("tanja")
            val anja = Player("anja")
            val vaske = Player("vaske")
            val zile = Player("zile")
            playerGroupAsocijacije.add(tanja)
            playerGroupAsocijacije.add(anja)
            playerGroupAsocijacije.add(vaske)
            playerGroupAsocijacije.add(zile)

            val playerGroupMatf = PlayerGroup("Matf")
            val mila = Player("mila")
            val irina = Player("irina")
            val milos = Player("milos")
            val tina = Player("tina")
            playerGroupMatf.add(mila)
            playerGroupMatf.add(irina)
            playerGroupMatf.add(milos)
            playerGroupMatf.add(tina)
            playerGroupMatf.add(tanja)
            playerGroupMatf.add(anja)
            playerGroupMatf.add(vaske)
            playerGroupMatf.add(zile)

            mPlayerGroupViewModel.addPlayerGroup(playerGroupMatf)
            mPlayerGroupViewModel.addPlayerGroup(playerGroupAsocijacije)

            firstTimeIn = false
        }
    }
}