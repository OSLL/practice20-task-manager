package com.makentoshe.androidgithubcitemplate.items

import androidx.room.*


@Dao
interface DayDao {
    @Query("SELECT * FROM Day")
    fun getAll(): List<Day>

    @Query("SELECT * FROM Day WHERE Date = :Date")
    fun getByDate(Date: Long): Day

    @Query("SELECT * FROM Day ORDER BY Date DESC LIMIT 7")
    fun getLast7():List<Day>

    @Query("SELECT * FROM Day ORDER BY Date DESC LIMIT 7 OFFSET :WeeksAgo-1")
    fun get7(WeeksAgo: Int):List<Day>

    @Insert
    fun insert(employee: Day)

    @Update
    fun update(employee: Day)


    @Query("DELETE FROM Day")
    fun deleteAll()

    @Update
    fun updateDay(vararg todos: Day)
}