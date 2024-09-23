package com.example.recyclerview2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview2.models.Note
import com.example.recyclerview2.models.Planete
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteDao: NoteDao
    private lateinit var noteDatabase: NoteDatabase

    private var notes = mutableListOf<Note>()

    // Code pour identifier une modification
    private val EDIT_NOTE_REQUEST = 1
    private val ADD_NOTE_REQUEST = 2 // Pour l'ajout de notes
    private var currentNotePosition: Int = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteDatabase = NoteDatabase.getDatabase(this)
        noteDao = noteDatabase.noteDao()

        lifecycleScope.launch {
            val notes = noteDao.getAllNotes()
            noteAdapter = NoteAdapter(notes.toMutableList(), editAction = { note, position ->
                currentNotePosition = position
                val intent = Intent(this@MainActivity, EditNoteActivity::class.java)
                intent.putExtra("note_title", note.title)
                intent.putExtra("note_description", note.description)
                startActivityForResult(intent, EDIT_NOTE_REQUEST)
            })
            recyclerView.adapter = noteAdapter
        }
        // Ajouter un bouton pour ajouter une note
        val addNoteButton: Button = findViewById(R.id.addNoteButton)
        addNoteButton.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADD_NOTE_REQUEST) {
                val newTitle = data?.getStringExtra("new_note_title")
                val newDescription = data?.getStringExtra("new_note_description")

                if (newTitle != null && newDescription != null) {
                    val newNote = Note(title = newTitle, description = newDescription)

                    // Insérer la note dans la base de données
                    lifecycleScope.launch {
                        noteDao.insert(newNote) // Insérer la note
                        val notes = noteDao.getAllNotes() // Récupérer toutes les notes
                        noteAdapter.updateNotes(notes.toMutableList()) // Mettre à jour l'adaptateur
                    }
                }
            } else if (requestCode == EDIT_NOTE_REQUEST) {
                val newTitle = data?.getStringExtra("new_note_title")
                val newDescription = data?.getStringExtra("new_note_description")

                if (newTitle != null && newDescription != null && currentNotePosition != -1) {
                    val updatedNote = notes[currentNotePosition].copy(title = newTitle, description = newDescription)

                    // Mettre à jour la note dans la base de données
                    lifecycleScope.launch {
                        noteDao.update(updatedNote) // Mettre à jour la note
                        val notes = noteDao.getAllNotes() // Récupérer toutes les notes
                        noteAdapter.updateNotes(notes.toMutableList()) // Mettre à jour l'adaptateur
                    }
                }
            }
        }
    }





}