package com.example.fitbit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalorieAdapter(
    private var items: List<CalorieEntry>
) : RecyclerView.Adapter<CalorieAdapter.CalorieViewHolder>() {

    inner class CalorieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val food: TextView = view.findViewById(R.id.tvFood)
        val calories: TextView = view.findViewById(R.id.tvCalories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalorieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calorie, parent, false)
        return CalorieViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalorieViewHolder, position: Int) {
        val entry = items[position]
        holder.food.text = entry.foodName
        holder.calories.text = "${entry.calories} cal"
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<CalorieEntry>) {
        items = newItems
        notifyDataSetChanged()
    }
}
