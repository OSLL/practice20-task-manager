package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var piechart: PieChart = findViewById<PieChart>(R.id.rating_chart)
        var pievalues: ArrayList<PieEntry> = ArrayList(0)
            pievalues.add(0, PieEntry(34f,""))
            pievalues.add(1,PieEntry(66f,""))
        var piedataset = PieDataSet(pievalues,"")
        piedataset.setDrawValues(false)
        var colorclassarray = listOf<Int>(Color.TRANSPARENT, 0xff6bbaff.toInt())
        piedataset.colors = colorclassarray
        var piedata = PieData(piedataset)
        if (piechart != null) {
            piechart.legend.isEnabled = false
            piechart.description.isEnabled = false
            piechart.setDrawCenterText(true)
            piechart.holeRadius = 70f
            //piechart.transparentCircleRadius = 75f
            piechart.setTransparentCircleColor(0xff666666.toInt())
            piechart.setCenterTextSize(50F)
            piechart.centerText = "66";
            piechart.animateY( 1500)
            piechart.data = piedata
            piechart.invalidate()
        }
        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.HomeButton -> {
                        //goto up
                        true
                    }
                    R.id.StatsButton -> {
                        intent = Intent(this, StatsActivity::class.java)
                        startActivity(intent)
                        finish()
                        overridePendingTransition(0, 0)
                        true
                    }
                    R.id.ProfileButton -> {
                        intent = Intent(this, SettingsActivity::class.java)
                        startActivity(intent)
                        finish()
                        overridePendingTransition(0, 0)
                        true
                    }
                }
                false
            }
         fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent) {
            super.onActivityResult(requestCode, resultCode, data)
            finish()
        }

        supportActionBar?.hide()
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        floating_action_button.setOnClickListener {
            intent = Intent(this, EditActivity::class.java)
            //intent.setFlags(FAB_ALIGNMENT_MODE_END)
            startActivity(intent)
            //Toast.makeText(this, "AAAAAAAAA", Toast.LENGTH_SHORT ).show()
            bottom_navigation.selectedItemId = R.id.HomeButton
            bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }

        acceptedNote()
    }

    fun EditNote(view: View){
        val editIntent = Intent(this, EditActivity::class.java)
        startActivity(editIntent)

    }


    companion object {
        const val acceptedTitle = "acceptedTitle"
        const val acceptedNote = "acceptedNote"
    }

    fun acceptedNote(){
        val acceptedTitle = intent.getStringExtra(acceptedTitle)
        val acceptedNote = intent.getStringExtra(acceptedNote)
        textView2.text = acceptedTitle
        textView3.text = acceptedNote
    }

}

private operator fun PieData.invoke(piedata: PieData) {

}

