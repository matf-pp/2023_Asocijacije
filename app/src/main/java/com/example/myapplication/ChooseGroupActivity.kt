package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.db.DatabaseServiceProvider
import com.example.myapplication.model.Game
import com.example.myapplication.model.PlayerGroup

class ChooseGroupActivity : AppCompatActivity() {
//    private lateinit var mPlayerGroupViewModel: PlayerGroupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_group)
        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //vadicemo iz baze listu grupa

        //nece odmah da mi prikaze listu iz lokalne baze, ne razumijem zasto??
        //observe bi trebao da vrati vec postojece podatke, ali iz nekog razloga to radi tek kad se predje u novi prozor
        //zato ovo pozivam odmah u main activity-u, a ne ovdje
        var currentPGroup = PlayerGroup("")
//        mPlayerGroupViewModel = ViewModelProvider(this)[PlayerGroupViewModel::class.java]
//        mPlayerGroupViewModel.readAllData.observe(this) { playergroups ->
//            DatabaseServiceProvider.db.setPlayerGroups(
//                playergroups as MutableList<PlayerGroup>
//            )
//        }
        val existingGroups = DatabaseServiceProvider.db.getPlayerGroupNames()
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, existingGroups)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autocompleteTV.setAdapter(arrayAdapter)
        autocompleteTV.setOnItemClickListener { parent, view, position, id ->
            val selectedGroup = parent.getItemAtPosition(position)
            val tekst = selectedGroup.toString()

            //clanovi tima, samo sredi kako ce ih ispisati
            val textView = findViewById<TextView>(R.id.teamMembers)
            textView.movementMethod = ScrollingMovementMethod()

            currentPGroup = DatabaseServiceProvider.db.getPlayerGroup(tekst)
            var text = ""
            for (player in currentPGroup.getPlayers()){
                //todo promijeni ovo kasnije da bude samo ime! sad samo testiram
                text = "$text \n $player"
            }

            textView.text = text
        }

        val tVerror = findViewById<TextView>(R.id.chooseGroupError)

        val btnChoose: Button = findViewById(R.id.btnChooseThisGroup)
        btnChoose.setOnClickListener {

            if (autocompleteTV.text.toString().isEmpty()) {
                tVerror.text = "Izaberite tim!"
            } else {
                val intent = Intent(this, GroupInfoActivity::class.java)
                val currentGame = Game()
                currentGame.setPlayerGroup(currentPGroup)
                DatabaseServiceProvider.db.setGame(currentGame)
                startActivity(intent)
            }
        }

        val btnNewGroup: Button = findViewById(R.id.btnNewGroup)
        btnNewGroup.setOnClickListener {
            val intent = Intent(this, NewGroupActivity::class.java)
            startActivity(intent)
        }

    }
}