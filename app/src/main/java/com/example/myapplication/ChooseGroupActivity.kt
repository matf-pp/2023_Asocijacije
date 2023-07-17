package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView

class ChooseGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_group)
        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        //vadicemo iz baze listu grupi
        val existingGroups = resources.getStringArray(R.array.Groups)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, existingGroups)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autocompleteTV.setAdapter(arrayAdapter)
        autocompleteTV.setOnItemClickListener { parent, view, position, id ->
            val selectedGroup = parent.getItemAtPosition(position)
            val tekst = selectedGroup.toString()

            //ispisivacemo clanove ove grupe (vadicemo iz jsona) a ne ime ali za sad
            val textView = findViewById<TextView>(R.id.teamMembers)
            textView.text = tekst
        }

        val tVerror = findViewById<TextView>(R.id.chooseGroupError)

        val btnChoose: Button = findViewById(R.id.btnChooseThisGroup)
        btnChoose.setOnClickListener(){

            if(autocompleteTV.text.toString().isEmpty()){
                tVerror.text = "Izaberite tim!"
            }else{
                val intent = Intent(this, GroupInfoActivity::class.java)
                startActivity(intent)
            }

        }


        val btnNewGroup: Button = findViewById(R.id.btnNewGroup)
        btnNewGroup.setOnClickListener(){
            val intent = Intent(this, NewGroupActivity::class.java)
            startActivity(intent)
        }




    }
}