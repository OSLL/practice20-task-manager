package com.makentoshe.androidgithubcitemplate

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.makentoshe.androidgithubcitemplate.items.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), AppCallback {
    var groupAdapter = GroupAdapter<ViewHolder>()
    override fun updateAdapter(
        groupAdapter: GroupAdapter<ViewHolder>,
        db: TaskDatabase,
        rdb: RatingDatabase,
        deleteId: Long,
        pref: SharedPreferences
    ) {
        sendNot()
        var taskDao = db.taskDao()
        groupAdapter.clear()
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val date = cal[Calendar.DATE]
        cal.clear()
        cal[year, month] = date
        val todayMillis2 = cal.timeInMillis
        groupAdapter
            .add(
                RatingItem(
                    Rating(
                        Rating = 34,
                    pref = pref, id = todayMillis2)
                )
            )
        if (deleteId > -1) taskDao.deleteById(deleteId)
        groupAdapter.addAll(taskDao.sortedPinned().map { TaskItem(it, this@MainActivity) })
        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = groupAdapter
            attachSwipeCallback(
                this@MainActivity,
                main_recycler_view,
                adapter as GroupAdapter<ViewHolder>,
                db,
                pref
            )
        }


        val alarmMgr =
            getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        val resultPendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val time: Calendar = Calendar.getInstance()
        time.setTimeInMillis(System.currentTimeMillis())
        time.add(Calendar.SECOND, 30)
        alarmMgr[AlarmManager.RTC_WAKEUP, time.timeInMillis] = pendingIntent

        //genTask(taskDao)
        //taskDao.deleteAll()
        //Log.v("Taskdao size", taskDao.getCount().toString())
        /*val builder =
            NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.motiontask_icon_white)
                .setContentTitle("Placeholder")
                .setContentText("Note expires in 30 minutes")
                .setContentIntent(resultPendingIntent)
                .setWhen(System.currentTimeMillis()+30000)
                .setUsesChronometer(true)
        val notification = builder.build()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var db = TaskDatabase.getDatabase(application)
        val pref =
            applicationContext.getSharedPreferences("MyPref", 0) // 0 - for private mode

        val editor = pref.edit()

        if (pref.getBoolean("switch", false)) {

            val theme: Resources.Theme = super.getTheme()
            theme.applyStyle(com.makentoshe.androidgithubcitemplate.R.style.AppTheme_Dark, true)

        } else {

            val theme: Resources.Theme = super.getTheme()
            theme.applyStyle(com.makentoshe.androidgithubcitemplate.R.style.AppTheme, true)

        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateAdapter(groupAdapter, db,rdb ,-1, pref)
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
        val resultIntent = Intent(this, MainActivity::class.java)
        val resultPendingIntent = PendingIntent.getActivity(
            this, 0, resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )


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

    private fun sendNot() {
        val resultIntent = Intent(this, MainActivity::class.java)
        val resultPendingIntent = PendingIntent.getActivity(
            this, 0, resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder =
            NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.motiontask_icon_white)
                .setContentTitle("Placeholder")
                .setContentText("Note expires in 30 minutes")
                .setContentIntent(resultPendingIntent)
                .setWhen(System.currentTimeMillis() + 30000)
                .setUsesChronometer(true)

        val notification: Notification = builder.build()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

    private fun attachSwipeCallback(
        appCallback: AppCallback,
        recyclerView: RecyclerView,
        mAdapter: GroupAdapter<ViewHolder> = groupAdapter,
        db: TaskDatabase,
        pref: SharedPreferences
    ) {
        val itemTouchHelper =
            ItemTouchHelper(AppCallback.SwipeCallback(appCallback, groupAdapter, application, db, pref))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}



