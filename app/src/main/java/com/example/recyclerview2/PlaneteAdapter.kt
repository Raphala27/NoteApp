package com.example.recyclerview2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.recyclerview2.models.Planete

class PlaneteAdapter : RecyclerView.Adapter<PlanetViewHolder>() {

    private var planetes: MutableList<Planete> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val planeteView: View = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.planete_item,
                parent,
                false
            )

        return PlanetViewHolder(planeteView)
    }

    override fun getItemCount() = planetes.size


    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bindPlanete(planetes.get(position))
    }

    fun loadPlanetes(newPlanete: MutableList<Planete>) {
        planetes = newPlanete
        notifyDataSetChanged()
    }


}