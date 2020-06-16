package com.makentoshe.androidgithubcitemplate

import java.sql.Date

data class Task(
    var id: Int = 0,
    var title: String = "",
    var text: String = "",
    var date: Date = Date(0),
    var image: String = "",
    var pin: Boolean = false,
    var bookmark: Int = 0
) : TaskNote