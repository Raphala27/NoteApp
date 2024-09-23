package com.example.recyclerview2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview2.models.Note

class NoteAdapter(
    private var noteList: MutableList<Note>,
    private val editAction: (Note, Int) -> Unit
) : RecyclerView.Adapter<NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.bindNote(note,
            editAction = { editAction(note, position) },  // Action d'édition
            deleteAction = { deleteNote(position) }       // Action de suppression
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    // Méthode pour supprimer une note
    private fun deleteNote(position: Int) {
        noteList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, noteList.size)
    }

    // Méthode pour mettre à jour une note après modification
    fun updateNote(position: Int, newTitle: String, newDescription: String) {
        noteList[position].title = newTitle
        noteList[position].description = newDescription
        notifyItemChanged(position)
    }
}