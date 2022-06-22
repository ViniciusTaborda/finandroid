package com.finandroid

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.finandroid.database.TransactionDbHelper
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class Dashboard : AppCompatActivity() {
    private val transactionDbHelper = TransactionDbHelper(this)
    private val incomesList = transactionDbHelper.readIncomes()
    private val expensesList = transactionDbHelper.readExpenses()

    lateinit var barIncomeArrayList: ArrayList<BarEntry>
    lateinit var barExpenseArrayList: ArrayList<BarEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if (this.incomesList.isEmpty() || this.expensesList.isEmpty()){
            Toast.makeText(this, "Dados insuficientes para visualizar o dashboard. Por favor, insira mais transações.", Toast.LENGTH_SHORT)
                .show()
        }

        setIncomeBarData(this.incomesList)
        setExpenseBarData(this.expensesList)
        setExpensesChart()
        setIncomesChart()
    }

    private fun setIncomeBarData(incomesList: MutableList<Double>) {
        barIncomeArrayList = ArrayList<BarEntry>()
        for ((i, income_value) in incomesList.withIndex()) {
            barIncomeArrayList.add(BarEntry(i.toFloat(), income_value.toFloat()))
        }

    }

    private fun setExpenseBarData(expensesList: MutableList<Double>) {
        barExpenseArrayList = ArrayList<BarEntry>()
        for ((i, income_value) in expensesList.withIndex()) {
            barExpenseArrayList.add(BarEntry(i.toFloat(), income_value.toFloat()))
        }
    }

    private fun setIncomesChart() {
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

    private fun setExpensesChart() {
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