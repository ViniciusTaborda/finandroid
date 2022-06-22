package com.finandroid

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.finandroid.database.TransactionDbHelper
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class Dashboard : AppCompatActivity() {
    private val transactionDbHelper = TransactionDbHelper(this)


    private lateinit var barIncomeArrayList: ArrayList<BarEntry>
    private lateinit var barExpenseArrayList: ArrayList<BarEntry>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        val incomesList = transactionDbHelper.readIncomes()
        val expensesList = transactionDbHelper.readExpenses()

        val incomesSum = incomesList.sum()
        val expensesSum = expensesList.sum()
        val balance = incomesSum - expensesSum

        val incomesSumText = findViewById<TextView>(R.id.totalIncomes)
        val expensesSumText = findViewById<TextView>(R.id.totalExpenses)
        val balanceText = findViewById<TextView>(R.id.balance)

    expensesSumText.text = "R$ $expensesSum"
    incomesSumText.text = "R$ $incomesSum"
    balanceText.text = "R$ $balance"

    setIncomeBarData(incomesList)
    setExpenseBarData(expensesList)
    setExpensesChart()
    setIncomesChart()
}

    private fun setIncomeBarData(transactionsList: MutableList<Float>) {

        if (transactionsList.isEmpty()){
            Toast.makeText(this, "Dados insuficientes por favor insira mais entradas.", Toast.LENGTH_SHORT)
                .show()
        }

        barIncomeArrayList = ArrayList<BarEntry>()
        for ((i, income_value) in transactionsList.withIndex()) {
            barIncomeArrayList.add(BarEntry(i.toFloat(), income_value.toFloat()))
        }

    }

    private fun setExpenseBarData(transactionsList: MutableList<Float>) {
        if (transactionsList.isEmpty()){
            Toast.makeText(this, "Dados insuficientes por favor insira mais saídas.", Toast.LENGTH_SHORT)
                .show()
        }
        barExpenseArrayList = ArrayList<BarEntry>()
        for ((i, income_value) in transactionsList.withIndex()) {
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