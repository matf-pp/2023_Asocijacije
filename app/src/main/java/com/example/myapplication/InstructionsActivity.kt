package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView

class InstructionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructions)


        val btnBack: Button = findViewById(R.id.btnBackInstructions)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val textView = findViewById<TextView>(R.id.description)
        textView.movementMethod = ScrollingMovementMethod()
        textView.text = "Postavka igre\n" +
                "Na početku unesite ime koje želite Vaša grupa da nosi. Zatim unesite broj igrača koji Vaša grupa sadrži (mora da bude paran broj). Unesite imena članova grupe i zatim na početnom meniju izaberite ime Vaše grupe. Parovi su nasumično izabrani, ali imate slobodu da to promenite. Kada izaberete broj reči po igraču i broj sekundi za pogađanje, spremni ste za igru!\n" +
                "\nUnos reči\n" +
                "Na ekranu piše koji igrač unosi reči. Kada završi, daje telefon igraču čije ime piše na ekranu. Tek kada su svi igrači uneli reči, na ekranu će pisati koji igrač započinje igru.\n" +
                "\nFaze\n" +
                "Faze se menjaju kada se objasne svi pojmovi.\n" +
                "Prva faza (Slobodno objašnjavanje) - Igrač koji objašnjava može da priča partneru koliko god hoće o pojmu na ekranu.\n" +
                "Druga faza (Jedna reč) - Igrač koji objašnjava pantomimom pokušava da objasni partneru dati pojam.\n" +
                "Treća faza (Pantomima) - Igrač koji objašnjava sme da iskoristi samo jednu reč prilikom objašnjavanja nakon čega ne izgovara ništa dok partner ne pogodi pojam.\n" +
                "\nKraj igre\n" +
                "Nakon treće faze, pogođene reči se sabiraju po parovima i pobedio je onaj par koji ima najviše pogođenih pojmova."
    }

}