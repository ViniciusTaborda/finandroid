package com.finandroid

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class Dashboard : AppCompatActivity() {

    lateinit var barArrayList: ArrayList<BarEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setBarData()
        var barChart: BarChart = findViewById(R.id.barchart)
        var barDataSet: BarDataSet = BarDataSet(barArrayList, "Test chart")
        var barData: BarData = BarData(barDataSet)
        barChart.data = barData
        barDataSet.colors = ColorTemplate.COLORFUL_COLORS.toMutableList()
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f
        barChart.description.isEnabled = true


    }

    private fun setBarData(){
        barArrayList = ArrayList<BarEntry>()
        barArrayList.add(BarEntry(1f, 10f))
        barArrayList.add(BarEntry(2f, 19f))
        barArrayList.add(BarEntry(6f, 10f))
        barArrayList.add(BarEntry(4f, 15f))
        barArrayList.add(BarEntry(7f, 1f))
        barArrayList.add(BarEntry(6f, 10f))
        barArrayList.add(BarEntry(18f, 10f))

    }
}