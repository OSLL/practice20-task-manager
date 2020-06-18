import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import java.util.*


class MyAlarm : BroadcastReceiver {
    private val REMINDER_BUNDLE = "MyReminderBundle"

    // this constructor is called by the alarm manager.
    constructor() {}

    // you can use this constructor to create the alarm.
    //  Just pass in the main activity as the context,
    //  any extras you'd like to get later when triggered
    //  and the timeout
    constructor(context: Context, extras: Bundle?, timeoutInSeconds: Int) {
        val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MyAlarm::class.java)
        intent.putExtra(REMINDER_BUNDLE, extras)
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val time: Calendar = Calendar.getInstance()
        time.timeInMillis = System.currentTimeMillis()
        time.add(Calendar.SECOND, timeoutInSeconds)
        alarmMgr[AlarmManager.RTC_WAKEUP, time.timeInMillis] = pendingIntent
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        // here you can get the extras you passed in when creating the alarm
        //intent.getBundleExtra(REMINDER_BUNDLE));
        Toast.makeText(context, "Alarm went off", Toast.LENGTH_SHORT).show()
    }
}