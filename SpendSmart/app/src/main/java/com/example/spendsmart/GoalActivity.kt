package com.example.spendsmart

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class GoalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        val categoryName = findViewById<EditText>(R.id.etGoalCategory)
        val seekBar = findViewById<SeekBar>(R.id.seekBarGoal)
        val tvSelectedGoal = findViewById<TextView>(R.id.tvGoalValue)
        val btnSetGoal = findViewById<Button>(R.id.btnSetGoal)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvSelectedGoal.text = "Goal: $progress items"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btnSetGoal.setOnClickListener {
            val cat = categoryName.text.toString().trim()
            val goal = seekBar.progress

            if (cat.isNotEmpty()) {
                Toast.makeText(this, "Goal of $goal items set for '$cat'", Toast.LENGTH_SHORT).show()
                categoryName.text.clear()
                seekBar.progress = 0
                tvSelectedGoal.text = "Goal: 0 items"
                // TODO: Save to DB later
            } else {
                Toast.makeText(this, "Please enter a category", Toast.LENGTH_SHORT).show()
            }
        }
    }
}