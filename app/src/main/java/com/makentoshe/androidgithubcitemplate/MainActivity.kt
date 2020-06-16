package com.makentoshe.androidgithubcitemplate

import android.app.Application
import com.makentoshe.androidgithubcitemplate.items.SwipeCallback
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.makentoshe.androidgithubcitemplate.items.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottom_navigation


class MainActivity : AppCompatActivity() {
    var groupAdapter = GroupAdapter<ViewHolder>()
    fun updateAdapter(groupAdapter: GroupAdapter<ViewHolder>) {
        var db = TaskDatabase.getDatabase(application)
        var taskDao = db.taskDao()
        groupAdapter.clear()
        groupAdapter
            .add(
            RatingItem(
                Rating(
                    Rating = 34
                )
            )
        )
        groupAdapter.addAll(taskDao.getAll().map { TaskItem(it) })
        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = groupAdapter
            attachSwipeCallback(main_recycler_view, adapter as GroupAdapter<ViewHolder>)
        }
        //genTask(taskDao)
        //taskDao.deleteAll()
        //Log.v("Taskdao size", taskDao.getCount().toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateAdapter(groupAdapter)
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
        bottom_navigation.selectedItemId = R.id.HomeButton
        floating_action_button.setOnClickListener {
            intent = Intent(this, EditActivity::class.java)
            //intent.setFlags(FAB_ALIGNMENT_MODE_END)
            startActivity(intent)
            finish()
            //Toast.makeText(this, "AAAAAAAAA", Toast.LENGTH_SHORT ).show()
        }
        floating_action_button2.setOnClickListener {
            updateAdapter(groupAdapter)

        }
    }
    private fun attachSwipeCallback(
        recyclerView: RecyclerView,
        mAdapter: GroupAdapter<ViewHolder>
    ) {
        val itemTouchHelper = ItemTouchHelper(SwipeCallback(mAdapter, application))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    private fun newEntry(taskDao: TaskDao) {
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
        taskDao.update(task)
    }
    private fun genTask(taskDao: TaskDao){
        newEntry(taskDao)
    }

}



