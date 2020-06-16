package com.makentoshe.androidgithubcitemplate.items

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var text: String = "",
    var date: Date = Date(0),
    var image: String = "",
    var pin: Boolean = false,
    var bookmark: Int = 0
) : TaskNote