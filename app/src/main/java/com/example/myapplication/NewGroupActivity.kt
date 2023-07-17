package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.model.PlayerGroup


class NewGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_group)

        val btnBack: Button = findViewById(R.id.btnBackNewGroup)
        btnBack.setOnClickListener(){
            val intent = Intent(this, ChooseGroupActivity::class.java)
            startActivity(intent)
        }

        val editText = findViewById<EditText>(R.id.newTeamName)
        val textViewError = findViewById<TextView>(R.id.emptyError)

        val numbers = resources.getStringArray(R.array.Numbers)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, numbers)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView2)
        autocompleteTV.setAdapter(arrayAdapter)

        val btnNext: Button = findViewById(R.id.btnNextNewGroup)
        btnNext.setOnClickListener(){
            if(editText.text.isEmpty()){
                textViewError.text = "Unesite ime tima!"
            }else {
                if (autocompleteTV.text.isEmpty()) {
                    textViewError.text = "Unesite broj igraca!"
                }else{
                    val groupName = editText.text.toString()
                    val newGroup = PlayerGroup(groupName)
                    var numberOfPlayers :Int
                    autocompleteTV.setOnItemClickListener { parent, view, position, id ->
                        val selectedGroup = parent.getItemAtPosition(position)
                        numberOfPlayers = selectedGroup.toString().toInt()
                        //newGroup.setPGNumOfPl(numberOfPlayers
                    }

                    val bundle = Bundle()
                    bundle.putString("id", groupName)
                    val intent = Intent(this, AddPlayersActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            }
        }
    }
}