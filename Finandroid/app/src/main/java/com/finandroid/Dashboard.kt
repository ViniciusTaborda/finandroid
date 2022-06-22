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

    lateinit var barIncomeArrayList: ArrayList<BarEntry>
    lateinit var barExpenseArrayList: ArrayList<BarEntry>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setIncomeBarData()
        setExpenseBarData()
        setExpensesChart()
        setIncomesChart()
    }

    private fun setIncomeBarData(){
        barIncomeArrayList = ArrayList<BarEntry>()
        for (i in 1..10){
            barIncomeArrayList.add(BarEntry(i.toFloat(), 10f))
        }

    }

    private fun setExpenseBarData(){
        barExpenseArrayList = ArrayList<BarEntry>()
        for (i in 1..10){
            barExpenseArrayList.add(BarEntry(i.toFloat(), 10f))
        }
    }

    private fun setIncomesChart(){
        var barChartIncomes: BarChart = findViewById(R.id.barchart_incomes)
        var barDataSetObj = BarDataSet(barIncomeArrayList, "Incomes chart")
        var barDataObj = BarData(barDataSetObj)
        barChartIncomes.data = barDataObj
        barDataSetObj.colors = mutableListOf<Int>(Color.GREEN)
        barDataSetObj.valueTextColor = Color.BLACK
        barDataSetObj.valueTextSize = 12f
        barChartIncomes.description.isEnabled = false
        barChartIncomes.axisRight.setDrawAxisLine(false)
        barChartIncomes.axisLeft.setDrawAxisLine(false)
        barChartIncomes.xAxis.setDrawAxisLine(false)
        barChartIncomes.axisRight.setDrawGridLines(false)
        barChartIncomes.axisLeft.setDrawGridLines(false)
        barChartIncomes.xAxis.setDrawGridLines(false)
        barChartIncomes.xAxis.isEnabled = false
        barChartIncomes.legend.isEnabled = false
        barChartIncomes.axisLeft.isEnabled = false
        barChartIncomes.axisRight.isEnabled = false

    }
    private fun setExpensesChart(){
        var barChartExpenses: BarChart = findViewById(R.id.barchart_expenses)
        var barDataSetObj = BarDataSet(barExpenseArrayList, "Expenses chart")
        var barDataObj = BarData(barDataSetObj)
        barChartExpenses.data = barDataObj
        barDataSetObj.colors = mutableListOf<Int>(Color.RED)
        barDataSetObj.valueTextColor = Color.BLACK
        barDataSetObj.valueTextSize = 12f
        barChartExpenses.description.isEnabled = false
        barChartExpenses.axisRight.setDrawAxisLine(false)
        barChartExpenses.axisLeft.setDrawAxisLine(false)
        barChartExpenses.xAxis.setDrawAxisLine(false)
        barChartExpenses.axisRight.setDrawGridLines(false)
        barChartExpenses.axisLeft.setDrawGridLines(false)
        barChartExpenses.xAxis.setDrawGridLines(false)
        barChartExpenses.xAxis.isEnabled = false
        barChartExpenses.legend.isEnabled = false
        barChartExpenses.axisLeft.isEnabled = false
        barChartExpenses.axisRight.isEnabled = false
    }
}