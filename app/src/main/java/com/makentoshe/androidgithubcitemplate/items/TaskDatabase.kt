package com.makentoshe.androidgithubcitemplate.items

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(
            context: Context
        ): TaskDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "wordDatabase"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .allowMainThreadQueries() //TODO(УБРАТЬ ПОТОМ)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        /*private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.*/

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.taskDao())
                    }
                }
            }
        }*/

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        /*fun populateDatabase(taskDao: TaskDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            taskDao.deleteAll()

            var word = Task("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
        }*/
    }
}