package com.makentoshe.androidgithubcitemplate.items

import android.view.View
import com.makentoshe.androidgithubcitemplate.INSET
import com.makentoshe.androidgithubcitemplate.INSET_TYPE_KEY
import com.makentoshe.androidgithubcitemplate.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.note_layout.*

open class TaskItem(private val task: Task) : Item() {
    //open val swipeDirs: Int = 0
    init {
        extras[INSET_TYPE_KEY] = INSET
    }
    override fun getLayout() =
        R.layout.note_layout
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            note_title.text = task.title
            if (task.text != "") note_text.visibility = View.VISIBLE
            note_text.text = task.text
            if (task.date > 0) {
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