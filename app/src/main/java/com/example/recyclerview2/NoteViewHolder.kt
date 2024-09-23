package com.example.recyclerview2

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview2.models.Note

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val noteTitle: TextView = itemView.findViewById(R.id.noteTitle)
    val noteDescription: TextView = itemView.findViewById(R.id.noteDescription)
    val editButton: Button = itemView.findViewById(R.id.editButton)
    val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

    fun bindNote(note: Note, editAction: () -> Unit, deleteAction: () -> Unit) {
        noteTitle.text = note.title
        noteDescription.text = note.description

        editButton.setOnClickListener {
            editAction()  // Appelle l'action d'édition
        }

        deleteButton.setOnClickListener {
            deleteAction()  // Appelle l'action de suppression
        }
    }
}