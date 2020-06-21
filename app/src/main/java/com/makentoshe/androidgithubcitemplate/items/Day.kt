package com.makentoshe.androidgithubcitemplate.items

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Day(
    @PrimaryKey
    var Date: Long,
    var SolvedToday: Int = 0,
    var RejectedToday: Int = 0)
{
}