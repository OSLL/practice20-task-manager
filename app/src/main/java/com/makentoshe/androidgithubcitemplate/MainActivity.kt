package com.makentoshe.androidgithubcitemplate

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


class MainActivity : AppCompatActivity(), AppCallback {
    var groupAdapter = GroupAdapter<ViewHolder>()
    override fun updateAdapter(
        groupAdapter: GroupAdapter<ViewHolder>,
        db: TaskDatabase,
        deleteId: Long
    ) {
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
        if (deleteId > -1) taskDao.deleteById(deleteId)
        groupAdapter.addAll(taskDao.getAll().map { TaskItem(it) })
        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = groupAdapter
            attachSwipeCallback(
                this@MainActivity,
                main_recycler_view,
                adapter as GroupAdapter<ViewHolder>,
                db
            )
        }
        //genTask(taskDao)
        //taskDao.deleteAll()
        //Log.v("Taskdao size", taskDao.getCount().toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var db = TaskDatabase.getDatabase(application)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateAdapter(groupAdapter, db)
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

    }


    private fun attachSwipeCallback(
        appCallback: AppCallback,
        recyclerView: RecyclerView,
        mAdapter: GroupAdapter<ViewHolder> = groupAdapter,
        db: TaskDatabase
    ) {
        val itemTouchHelper =
            ItemTouchHelper(AppCallback.SwipeCallback(appCallback, groupAdapter, application, db))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}



