package com.makentoshe.androidgithubcitemplate.items

import android.app.Application
import androidx.room.Room


class App : Application() {
    private var database: TaskDatabase? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        database  = Room.databaseBuilder<TaskDatabase>(this, TaskDatabase::class.java, "database")
            .build()
    }

    fun getDatabase(): TaskDatabase? {
        return database
    }

    companion object {
        var instance: App? = null
    }
}