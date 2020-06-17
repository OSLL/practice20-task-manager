package com.makentoshe.androidgithubcitemplate.items

import android.graphics.Color
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.makentoshe.androidgithubcitemplate.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.rating_layout.*

class RatingItem(private val rating: Rating) : Item() {
    override fun getLayout() =
        R.layout.rating_layout

    override fun bind(viewHolder: ViewHolder, position: Int) {
        var solved_today = rating.SolvedToday
        var solved_today_pivo: String = "Solved today: " + solved_today.toString()
        viewHolder.text_solved.text = solved_today_pivo
        var rejected_today = rating.RejectedToday
        var rejected_today_pivo: String = "Rejected today: " + rejected_today.toString()
        viewHolder.text_rejected.text = rejected_today_pivo
        var piechart: PieChart = viewHolder.rating_chart
        var pievalues: ArrayList<PieEntry> = ArrayList(0)
        pievalues.add(0, PieEntry((100 - rating.Rating).toFloat(), ""))
        pievalues.add(1, PieEntry(rating.Rating.toFloat(), ""))
        var piedataset = PieDataSet(pievalues, "")
        piedataset.setDrawValues(false)
        var colorclassarray = listOf<Int>(Color.TRANSPARENT, 0xff6bbaff.toInt())
        piedataset.colors = colorclassarray
        var piedata = PieData(piedataset)
        if (piechart != null) {
            piechart.setTouchEnabled(false)
            piechart.legend.isEnabled = false
            piechart.description.isEnabled = false
            piechart.setDrawCenterText(true)
            piechart.holeRadius = 70f
            //piechart.transparentCircleRadius = 75f
            piechart.setTransparentCircleColor(0xff666666.toInt())
            piechart.setCenterTextSize(50F)
            piechart.centerText = "${rating.Rating}"
            //piechart.animateY( 1000)
            piechart.data = piedata
            piechart.invalidate()
        }
    }
}