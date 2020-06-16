package com.makentoshe.androidgithubcitemplate

import SwipeCallback
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.makentoshe.androidgithubcitemplate.items.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Date


class MainActivity : AppCompatActivity() {

    var db: TaskDatabase = App().getDatabase()
    var taskDao = db.taskDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var groupAdapter = GroupAdapter<ViewHolder>().apply {
            add(
                RatingItem(
                    Rating(
                        Rating = 34
                    )
                )
            )
        }
        var noteList: RecyclerView = findViewById(R.id.main_recycler_view)
        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                adapter = groupAdapter
            }
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
            var groupAdapter = GroupAdapter<ViewHolder>()
            groupAdapter.add(
                RatingItem(
                    Rating(
                        Rating = 34
                    )
                )
            )
            genTask().map { TaskItem(it) }

            main_recycler_view.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = groupAdapter
                attachSwipeCallback(main_recycler_view, adapter as GroupAdapter<ViewHolder>)
            }
            bottom_navigation.selectedItemId = R.id.HomeButton
        }
    }
    private fun attachSwipeCallback(
        recyclerView: RecyclerView,
        mAdapter: GroupAdapter<ViewHolder>
    ) {
        val itemTouchHelper = ItemTouchHelper(SwipeCallback(mAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    private fun newEntry() {
        val titlestr = (1..100000).random().toString()
        val textstr = (1..100000000000000).random().toString()
        val task = Task()
        task.title = titlestr
        task.text = textstr
        task.pin = true
        task.date = System.currentTimeMillis() + 100000
        task.bookmark = 1
        task.image = "a"
        taskDao.insert(task)
    }
    private fun genTask(): List<Task>{
        newEntry()
        val items = taskDao.getAll()
        return items
    }

}



