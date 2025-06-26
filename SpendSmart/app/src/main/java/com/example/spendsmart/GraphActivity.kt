package com.example.spendsmart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class GraphActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        barChart = findViewById(R.id.barChart)

        val sampleData = listOf(
            Pair("Groceries", 300f),
            Pair("Transport", 150f),
            Pair("Clothes", 250f),
            Pair("Entertainment", 100f)
        )

        val entries = sampleData.mapIndexed { index, data ->
            BarEntry(index.toFloat(), data.second)
        }

        val barDataSet = BarDataSet(entries, "Spending per Category")
        barDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        val barData = BarData(barDataSet)

        val xAxisLabels = sampleData.map { it.first }
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        barChart.data = barData

        barChart.setFitBars(true)
        val description = Description()
        description.text = "Amount Spent (in Rands)"
        barChart.description = description

        barChart.animateY(1500)
    }
}
