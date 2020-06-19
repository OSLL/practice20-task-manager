package com.makentoshe.androidgithubcitemplate.items

import android.content.SharedPreferences
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Entity
class Rating constructor(
    var Rating: Int = 50,
    var SolvedToday: Int = 0,
    var RejectedToday: Int = 0,
    var pref: SharedPreferences,
    @PrimaryKey
    var id: Long = 0
) : TaskNote {}