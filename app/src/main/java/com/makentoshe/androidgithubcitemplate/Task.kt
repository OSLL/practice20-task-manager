package com.makentoshe.androidgithubcitemplate

import java.sql.Date


data class Task(
    val title: String? = null,
    val text: String? = null,
    val date: Date? = null,
    val image: String? = null,
    val pin: Boolean = false,
    val bookmark: Int? = null) : TaskNote{
}