package com.example.spendsmart

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class DashboardActivity : AppCompatActivity() {

    private val tips = listOf(
        "Track every expense to understand your spending habits.",
        "Set savings goals and automate your savings.",
        "Avoid impulse purchases by waiting 24 hours before buying.",
        "Use cash instead of cards to limit spending.",
        "Review your budget weekly and adjust where needed."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val tvTip = findViewById<TextView>(R.id.tvTip)
        val tvBadge = findViewById<TextView>(R.id.tvBadge)

        val randomTip = tips.random()
        tvTip.text = "\uD83D\uDCA1 Tip of the Day:\n$randomTip"

        val sharedPref = getSharedPreferences("SpendSmartPrefs", MODE_PRIVATE)
        val totalItemsSaved = sharedPref.getInt("items_saved", 0)
        tvBadge.text = if (totalItemsSaved >= 3) "\uD83C\uDFC5 Budget Master Badge Unlocked!" else ""

        findViewById<Button>(R.id.btnGoToCategory).setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }
        findViewById<Button>(R.id.btnSetItemGoals).setOnClickListener {
            startActivity(Intent(this, GoalActivity::class.java))
        }
        findViewById<Button>(R.id.btnAddItem).setOnClickListener {
            startActivity(Intent(this, AddItemActivity::class.java))
        }
        findViewById<Button>(R.id.btnViewGraph).setOnClickListener {
            startActivity(Intent(this, GraphActivity::class.java))
        }

        scheduleDailyReminder()
    }

    private fun scheduleDailyReminder() {
        val intent = Intent(this, ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 20)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}
