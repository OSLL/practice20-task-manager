package com.makentoshe.androidgithubcitemplate.items

import android.content.Context
import android.content.res.ColorStateList
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.makentoshe.androidgithubcitemplate.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.note_layout.*
import java.util.*

class TaskItem(private val task: Task, private val context: Context) : Item() {

    override fun getLayout() =
        R.layout.note_layout

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            Log.v("task.title", "s:" + task.title)
            if (task.title == null) if ((task.bookmark == 0) and (task.date.equals(0)) and (!task.pin)) {
                iconcontainer.visibility = View.GONE
                Log.v("task.bookmark", task.bookmark.toString())
                Log.v("task.date",task.date.toString())
                Log.v("task.pin", task.pin.toString())
            }  else {
                note_title.visibility = View.VISIBLE

            }
            note_title.text = task.title
            if (task.text != null) note_text.visibility = View.VISIBLE
            note_text.text = task.text
            if (task.date > 0) {
                alarm_icon.visibility = View.VISIBLE
                note_date.visibility = View.VISIBLE
                var calendar = Calendar.getInstance()
                var calendarCurrent = Calendar.getInstance()
                var text = ""
                calendar.timeInMillis = task.date
                if ((calendar[Calendar.DAY_OF_MONTH] == calendarCurrent[Calendar.DAY_OF_MONTH]) and (calendar[Calendar.MONTH] == calendarCurrent[Calendar.MONTH]) and (calendar[Calendar.YEAR] == calendarCurrent[Calendar.YEAR])) {
                    text = text + calendar[Calendar.HOUR] + ":" + calendar[Calendar.MINUTE]
                } else {
                    text =
                        text + calendar[Calendar.DAY_OF_MONTH].toString() + "." + (calendar[Calendar.MONTH] + 1).toString() + " " + calendar[Calendar.HOUR].toString() + ":" + calendar[Calendar.MINUTE].toString()
                }
                note_date.text = text
                if (calendar.timeInMillis - calendarCurrent.timeInMillis <= 30 * 60 * 1000) {
                    ImageViewCompat.setImageTintList(
                        alarm_icon,
                        ColorStateList.valueOf(0xFFFF0000.toInt())
                    )
                    ImageViewCompat.setImageTintList(
                        pin_icon,
                        ColorStateList.valueOf(0xFFFF0000.toInt())
                    )
                    note_title.setTextColor(0xFFFF0000.toInt())
                    note_date.setTextColor(0xFFFF0000.toInt())
                }

            }
            if (task.pin) pin_icon.visibility = View.VISIBLE
            if (task.image != "") {
                note_image.visibility = View.VISIBLE
                Glide
                    .with(context)
                    .load(Uri.parse(task.image))
                    .into(note_image)
            }
            if (task.bookmark != 0) {
                bookmark_icon.visibility = View.VISIBLE
                Log.v("ColorStatusBind", task.bookmark.toString())
                ImageViewCompat.setImageTintList(
                    bookmark_icon,
                    ColorStateList.valueOf(task.bookmark)
                )
            }
        }

    }

}