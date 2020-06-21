package com.makentoshe.androidgithubcitemplate.items

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Day::class], version = 1)
abstract class DayDatabase : RoomDatabase() {
    abstract fun dayDao(): DayDao

    companion object {
        @Volatile
        private var INSTANCE: DayDatabase? = null

        fun getDatabase(
            context: Context
        ): DayDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DayDatabase::class.java,
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
                        populateDatabase(database.dayDao())
                    }
                }
            }
        }*/

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        /*fun populateDatabase(dayDao: DayDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            dayDao.deleteAll()

            var word = Day("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
        }*/
    }
}