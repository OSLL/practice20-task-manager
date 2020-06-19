package com.makentoshe.androidgithubcitemplate.items

import android.content.SharedPreferences

class Rating(
    var Rating: Int = 100,
    var SolvedToday: Int = 0,
    var RejectedToday: Int = 0,
    var pref: SharedPreferences
) : TaskNote