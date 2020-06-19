package com.makentoshe.androidgithubcitemplate.items

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.CustomFallbackBuilder
import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
import android.os.Build
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
        var piechart: PieChart = viewHolder.rating_chart
        var pievalues: ArrayList<PieEntry> = ArrayList(0)
        pievalues.add(0, PieEntry((100 - rating.Rating).toFloat(), ""))
        pievalues.add(1, PieEntry(rating.Rating.toFloat(), ""))
        var piedataset = PieDataSet(pievalues, "")
        piedataset.setDrawValues(false)
        var colorclassarray = listOf<Int>(0x55EEEEEE.toInt(), 0xff6bbaff.toInt())
        piedataset.colors = colorclassarray
        var piedata = PieData(piedataset)
        if (piechart != null) {
            piechart.setTouchEnabled(false)
            piechart.legend.isEnabled = false
            piechart.description.isEnabled = false
            piechart.setDrawCenterText(true)
            piechart.holeRadius = 70f
            piechart.setHoleColor(Color.TRANSPARENT)
            //piechart.transparentCircleRadius = 75f
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                piechart.setCenterTextTypeface(Typeface.Builder("font_poppins.ttf").build())
            }
            val editor = rating.pref.edit()

            if (rating.pref.getBoolean("switch", false)) {
                piechart.setCenterTextColor(0xFFAEAEAE.toInt())
            } else {
                piechart.setCenterTextColor(0xFF000000.toInt())
            }
            piechart.setTransparentCircleColor(0xff666666.toInt())
            piechart.setCenterTextSize(50F)
            piechart.centerText = "${rating.Rating}"
            //piechart.animateY( 1000)
            piechart.data = piedata
            piechart.invalidate()
        }
    }
}