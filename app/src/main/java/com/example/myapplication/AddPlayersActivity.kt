package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.model.PlayerGroup
import com.example.myapplication.model.Player
import com.example.myapplication.NewGroupActivity
class AddPlayersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_players)

        val btnBack: Button = findViewById(R.id.btnBackAddPlayers)
        btnBack.setOnClickListener(){
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
        if (bundle != null){
           groupName = "id = ${bundle.getString("id")}"
        }

        //currentGroup = FIND PlayerGroup with name groupName
        var clickCount : Int = 0
        var numOfPl = 4 //numOfPl = currentGroup.numberOfPlayers



        val btnOK : Button = findViewById(R.id.btnOK)
        btnOK.setOnClickListener(){
            val name = editText.text.toString()
            if(clickCount == numOfPl) {
                textViewError.text = "Vec je uneto dovoljno igraca"
            }else{
                if(name.isNotBlank()){
                    val newPlayer = Player(name)
                    textView.text = "${textView.text.toString()} \n $name"
                    //currentGroup.add(newPlayer)
                    clickCount+=1
                    editText.setText("")
                }
            }
        }


        val btnNext: Button = findViewById(R.id.btnNextAddPlayers)
        btnNext.setOnClickListener(){
            if(clickCount!=numOfPl) {
                textViewError.text = "Nema dovoljno igraca!"
            }else{
                val intent = Intent(this, GroupInfoActivity::class.java)
                startActivity(intent)
            }
        }


    }
}