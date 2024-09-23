package com.example.recyclerview2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview2.models.Note

class NoteAdapter(
    private var noteList: MutableList<Note>,
    private val editAction: (Note, Int) -> Unit,
    private val deleteAction: (Note, Int) -> Unit
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
            deleteAction = { deleteAction(note, position) } // Action de suppression
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    // Méthode pour mettre à jour les notes
    fun updateNotes(newNotes: MutableList<Note>) {
        noteList = newNotes
        notifyDataSetChanged()
    }
}
