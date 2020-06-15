package com.makentoshe.androidgithubcitemplate

import java.sql.Date

data class Task(
    var title: String = "",
    val text: String = "",
    val date: Date = Date(0),
    val image: String = "",
    val pin: Boolean = false,
    val bookmark: Int = 0) : TaskNote{

}