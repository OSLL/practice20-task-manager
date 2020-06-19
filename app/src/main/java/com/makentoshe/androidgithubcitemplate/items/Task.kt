package com.makentoshe.androidgithubcitemplate.items

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String? = null,
    var text: String? = null,
    var date: Long = 0,
    var image: String = "",
    var pin: Boolean = false,
    var bookmark: Int = 0
) : TaskNote