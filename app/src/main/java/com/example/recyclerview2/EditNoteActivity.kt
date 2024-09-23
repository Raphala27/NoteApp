package com.example.recyclerview2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditNoteActivity : AppCompatActivity() {

    private lateinit var editNoteTitle: EditText
    private lateinit var editNoteDescription: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        editNoteTitle = findViewById(R.id.editNoteTitle)
        editNoteDescription = findViewById(R.id.editNoteDescription)
        saveButton = findViewById(R.id.saveButton)

        // Récupérer les données de la note à partir de l'Intent
        val noteTitle = intent.getStringExtra("note_title") ?: ""
        val noteDescription = intent.getStringExtra("note_description") ?: ""

        if (noteTitle.isEmpty() && noteDescription.isEmpty()) {
            // Cela signifie que nous sommes en mode création
        }


        // Initialiser les champs avec les données existantes
        editNoteTitle.setText(noteTitle)
        editNoteDescription.setText(noteDescription)

        // Sauvegarder les modifications
        saveButton.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("new_note_title", editNoteTitle.text.toString())
            resultIntent.putExtra("new_note_description", editNoteDescription.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

    }
}