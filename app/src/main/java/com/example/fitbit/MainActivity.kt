package com.example.fitbit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var db: CalorieDatabase
    private lateinit var adapter: CalorieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Database
        db = CalorieDatabase.getDatabase(this)

        // RecyclerView setup
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CalorieAdapter(emptyList())
        recyclerView.adapter = adapter

        // Inputs
        val foodInput = findViewById<EditText>(R.id.tvFood)
        val calorieInput = findViewById<EditText>(R.id.tvCalories)
        val addButton = findViewById<Button>(R.id.btnAdd)

        // Load existing entries
        loadEntries()

        // Button logic
        addButton.setOnClickListener {
            val food = foodInput.text.toString()
            val calories = calorieInput.text.toString().toIntOrNull() ?: 0

            if (food.isNotBlank()) {
                addEntry(food, calories)
                foodInput.text.clear()
                calorieInput.text.clear()
            }
        }
    }

    private fun loadEntries() {
        lifecycleScope.launch {
            val entries = db.calorieDao().getAll()
            adapter.updateData(entries)
        }
    }

    private fun addEntry(food: String, calories: Int) {
        lifecycleScope.launch {
            db.calorieDao().insert(
                CalorieEntry(foodName = food, calories = calories)
            )
            loadEntries()
        }
    }
}
