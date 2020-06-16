package com.makentoshe.androidgithubcitemplate.items

import android.view.View
import com.makentoshe.androidgithubcitemplate.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.note_layout.*

class TaskItem(private val task: Task) : Item() {

    override fun getLayout() =
        R.layout.note_layout
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            note_title.text = task.title
            if (task.text != "") note_text.visibility = View.VISIBLE
            note_text.text = task.text
            if (task.date.time > 0) {
                alarm_icon.visibility = View.VISIBLE
                note_date.visibility = View.VISIBLE
                note_date.text = "High Noon"
            }
            if (task.pin) pin_icon.visibility = View.VISIBLE
            if (task.image != "") note_image.visibility = View.VISIBLE
            if (task.bookmark != 0) {
                bookmark_icon.visibility = View.VISIBLE
            }
        }

    }

}