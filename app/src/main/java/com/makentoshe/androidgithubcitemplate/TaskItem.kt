package com.makentoshe.androidgithubcitemplate

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.note_layout.*

class TaskItem(private val task: Task) : Item() {
    override fun getLayout() = R.layout.note_layout

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.note_title.text = task.title
    }
}