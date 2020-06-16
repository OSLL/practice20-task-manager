package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Date


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val items = MutableList<Task>(0) { a -> Task() }
        setContentView(R.layout.activity_main)
        var groupAdapter = GroupAdapter<ViewHolder>().apply {
            add(RatingItem(Rating(Rating = 34)))
        }
        var noteList: RecyclerView = findViewById(R.id.main_recycler_view)
        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                adapter = groupAdapter
            }
        }

        /*var piechart: PieChart = findViewById<PieChart>(R.id.rating_chart)
        var pievalues: ArrayList<PieEntry> = ArrayList(0)
        pievalues.add(0, PieEntry(34f, ""))
        pievalues.add(1, PieEntry(66f, ""))
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
            piechart.centerText = "66"
            //piechart.animateY( 1000)
            piechart.data = piedata
            piechart.invalidate()
        }*/
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
                        overridePendingTransition(R.anim.slidein, R.anim.slideout)
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

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
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
        }
        floating_action_button2.setOnClickListener {
            val titlestr = (1..100000).random().toString()
            val textstr = (1..100000000000000).random().toString()
            val task = Task()
            task.title = titlestr
            task.text = textstr
            task.pin = true
            task.date = Date(System.currentTimeMillis() + 100000)
            task.bookmark = 1
            task.image = "a"
            items.add(items.size, task)
            var group: Group
            var groupAdapter = GroupAdapter<ViewHolder>()
            groupAdapter.add(RatingItem(Rating(Rating = 34)))
            for (i in items) groupAdapter.add(TaskItem(i))

            main_recycler_view.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = groupAdapter
            }
            bottom_navigation.selectedItemId = R.id.HomeButton


            /*var solved_today = 0
        var solved_today_pivo : String = "Solved today: " + solved_today.toString()
        text_solved.text = solved_today_pivo
        var rejected_today = 0
        var rejected_today_pivo : String = "Rejected today: " + rejected_today.toString()
        text_rejected.text = rejected_today_pivo
        var piechart: PieChart = findViewById<PieChart>(R.id.rating_chart)
        var pievalues: ArrayList<PieEntry> = ArrayList(0)
        pievalues.add(0, PieEntry(34f, ""))
        pievalues.add(1, PieEntry(66f, ""))
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
            piechart.centerText = "66"
            //piechart.animateY( 1000)
            piechart.data = piedata
            piechart.invalidate()
        }*/
            //acceptedNote()
        }

        /* fun EditNote(view: View){
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
         textView2.text = acceptedTitle //когда у нас будет много заметок, так обращаться уже не выйдет, нужно будет что-то еще придумать
         textView3.text = acceptedNote
     }*/ //Я (Ильдар) пока закомментировал, чтоби прикинуть еще что-нибудь по разметке


    }
}



