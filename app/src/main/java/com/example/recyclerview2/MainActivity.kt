package com.example.recyclerview2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview2.models.Note
import com.example.recyclerview2.models.Planete
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter

    // Code pour identifier une modification
    private val EDIT_NOTE_REQUEST = 1
    private var currentNotePosition: Int = -1
    private val ADD_NOTE_REQUEST = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Exemple de données de notes
        val notes = mutableListOf(
            Note("Meeting with Bob", "Discuss project status"),
            Note("Shopping List", "Buy milk, bread, and eggs"),
            Note("Workout", "Gym at 7 PM")
        )

        noteAdapter = NoteAdapter(notes, editAction = { note, position ->
            currentNotePosition = position
            val intent = Intent(this, EditNoteActivity::class.java)
            intent.putExtra("note_title", note.title)
            intent.putExtra("note_description", note.description)
            startActivityForResult(intent, EDIT_NOTE_REQUEST)
        })

        recyclerView.adapter = noteAdapter



        val addNoteButton: Button = findViewById(R.id.addNoteButton)
        addNoteButton.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }

    }

    // Recevoir les données modifiées après l'édition
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADD_NOTE_REQUEST) {
                val newTitle = data?.getStringExtra("new_note_title")
                val newDescription = data?.getStringExtra("new_note_description")

                if (newTitle != null && newDescription != null) {
                    noteAdapter.addNote(Note(newTitle, newDescription))
                }
            } else if (requestCode == EDIT_NOTE_REQUEST) {
                val newTitle = data?.getStringExtra("new_note_title")
                val newDescription = data?.getStringExtra("new_note_description")

                if (newTitle != null && newDescription != null && currentNotePosition != -1) {
                    noteAdapter.updateNote(currentNotePosition, newTitle, newDescription)
                }
            }
        }
    }



}