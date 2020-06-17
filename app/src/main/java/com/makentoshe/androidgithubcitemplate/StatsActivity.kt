package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class StatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.title = "Statistics"
        actionBar?.setBackgroundDrawable(ColorDrawable(0xff6bbaff.toInt()))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_stats)
        var Barchart: BarChart = findViewById<BarChart>(R.id.month_rating_chart)
        var Barvalues: ArrayList<BarEntry> = ArrayList(0)
        Barvalues.add(0, BarEntry(0f, 40f))
        Barvalues.add(1, BarEntry(2f, 50f))
        Barvalues.add(2, BarEntry(4f, 30f))
        Barvalues.add(3, BarEntry(6f, 80f))
        val labels_month: ArrayList<String> = ArrayList(0)
        labels_month.add(0, "Week 1")
        labels_month.add(1, "")
        labels_month.add(2, "Week 2")
        labels_month.add(3, "")
        labels_month.add(4, "Week 3")
        labels_month.add(5, "")
        labels_month.add(6, "Week 4")
        var Bardataset = BarDataSet(Barvalues, "")
        Bardataset.setDrawValues(false)
        var colorclassarray = listOf<Int>(0xff6bbaff.toInt())
        Bardataset.colors = colorclassarray
        var Bardata = BarData(Bardataset)
        if (Barchart != null) {
            Barchart.xAxis.valueFormatter = IndexAxisValueFormatter(labels_month)
            Barchart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            Barchart.setTouchEnabled(false)
            Barchart.legend.isEnabled = false
            Barchart.description.isEnabled = false
            Barchart.animateY(1000)
            Barchart.data = Bardata
            Barchart.setFitBars(true)
            Barchart.setDrawBorders(false)
            Barchart.xAxis.setDrawGridLines(false)
            Barchart.axisRight.isEnabled = false
            Barchart.invalidate()
        }
        var Linechart: LineChart = findViewById<LineChart>(R.id.task_rating_chart)
        var Linevalues: ArrayList<Entry> = ArrayList(0)
        Linevalues.add(0, Entry(0f, 5f))
        Linevalues.add(1, Entry(1f, 3f))
        Linevalues.add(2, Entry(2f, 4f))
        Linevalues.add(3, Entry(3f, 1f))
        Linevalues.add(4, Entry(4f, 8f))
        Linevalues.add(5, Entry(5f, 6f))
        Linevalues.add(6, Entry(6f, 2f))
        val labels_task: ArrayList<String> = ArrayList(0)
        labels_task.add(0, "Mon")
        labels_task.add(1, "Tue")
        labels_task.add(2, "Wed")
        labels_task.add(3, "Thu")
        labels_task.add(4, "Fri")
        labels_task.add(5, "Sat")
        labels_task.add(6, "Sun")
        var Linedataset = LineDataSet(Linevalues, "")
        Linedataset.setDrawValues(false)
        Linedataset.colors = colorclassarray
        var Linedata = LineData(Linedataset)
        if (Linechart != null) {
            Linedataset.lineWidth = 5f
            Linedataset.circleRadius = 5f
            Linechart.axisLeft.setDrawLabels(false)
            Linechart.xAxis.valueFormatter = IndexAxisValueFormatter(labels_task)
            Linechart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            Linechart.setTouchEnabled(false)
            Linechart.legend.isEnabled = false
            Linechart.description.isEnabled = false
            Linechart.data = Linedata
            Linechart.setDrawBorders(false)
            Linechart.axisLeft.setDrawGridLines(true)
            Linechart.xAxis.setDrawGridLines(false)
            Linechart.axisRight.isEnabled = false
            Linechart.invalidate()
        }
        title = null
        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.HomeButton -> {
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slidein2, R.anim.slideout2)
                        finish()
                        true
                    }
                    R.id.StatsButton -> {
                        ///goto
                        true
                    }
                    R.id.ProfileButton -> {
                        intent = Intent(this, SettingsActivity::class.java)
                        startActivity(intent)
                        finish()
                        overridePendingTransition(R.anim.slidein, R.anim.slideout)
                        true
                    }
                }
                false
            }
        bottom_navigation.selectedItemId = R.id.StatsButton
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
            super.onActivityResult(requestCode, resultCode, data)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slidein2, R.anim.slideout2)
        finish()
        return true
    }

    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slidein2, R.anim.slideout2)
        finish()

    }

}