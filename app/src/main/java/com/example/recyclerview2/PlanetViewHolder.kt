package com.example.recyclerview2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview2.models.Planete

class PlanetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val planeteImage: ImageView = itemView.findViewById(R.id.planeteImage)
    val planeteNom: TextView = itemView.findViewById(R.id.planeteNom)
    val planeteDistance: TextView = itemView.findViewById(R.id.planeteDistance)

    fun bindPlanete(planete: Planete){
        planeteNom.text = planete.nom
        planeteDistance.text = "${planete.distance}"
        Glide
            .with(itemView.context)
            .load(planete.image)
            .into(planeteImage)

    }
}