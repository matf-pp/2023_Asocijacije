package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.db.DatabaseServiceProvider
import com.example.myapplication.model.PlayerGroup
import com.example.myapplication.model.Player
import com.example.myapplication.db.PlayerGroupViewModel

class AddPlayersActivity : AppCompatActivity() {
    private lateinit var mPlayerGroupViewModel : PlayerGroupViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_players)

        val btnBack: Button = findViewById(R.id.btnBackAddPlayers)
        btnBack.setOnClickListener {
            val intent = Intent(this, NewGroupActivity::class.java)
            startActivity(intent)
        }
        //--------------------------------------------------------------------------------------------
        //DODAVANJE IGRACA U PREDJASNJU GRUPU


        //inicijalizacija objekata
        val editText = findViewById<EditText>(R.id.eTAddPlayers)
        val textView = findViewById<TextView>(R.id.addedPlayers)
        val textViewError = findViewById<TextView>(R.id.numberOfPlayersError)

        //preuzimamo ime grupe i broj igraca iz prethodnog activity-ja
        val bundle = intent.extras
        var groupName = ""
        var groupNum = 0
        if (bundle != null){
            groupName = bundle.getString("id").toString()
            groupNum = bundle.getString("num").toString().toInt()
        }
        //testiram da li se ispravno preuzelo:
        //textView.text="$groupName \n $groupNum"

        val currentGroup = PlayerGroup(groupName)

        //petlja sa brojacem groupNum
        //dodamo igrace u currentGroup

        var clickCount = 0
        val numOfPl = groupNum

        val btnOK: Button = findViewById(R.id.btnOK)
        btnOK.setOnClickListener {
            val name = editText.text.toString()
            if(clickCount == numOfPl) {
                textViewError.text = "Vec je uneto dovoljno igraca"
            }else{
                if(name.isNotBlank()){
                    val newPlayer = Player(name)
                    textView.text = "${textView.text} \n $name"
                    currentGroup.add(newPlayer)
                    clickCount+=1
                    editText.setText("")
                }
            }
        }

        val btnNext: Button = findViewById(R.id.btnNextAddPlayers)
        btnNext.setOnClickListener {
            if(clickCount!=numOfPl) {
                textViewError.text = "Nema dovoljno igraca!"
            }else{
                val intent = Intent(this, ChooseGroupActivity::class.java)
                startActivity(intent)
                //dodamo currentGroup u bazu:
                DatabaseServiceProvider.db.addPlayerGroup(currentGroup)
                mPlayerGroupViewModel = ViewModelProvider(this)[PlayerGroupViewModel::class.java]
                insertDataToDatabase(currentGroup)
            }
        }
    }
    private fun insertDataToDatabase(pg : PlayerGroup) {
        mPlayerGroupViewModel.addPlayerGroup(pg)
    }
}