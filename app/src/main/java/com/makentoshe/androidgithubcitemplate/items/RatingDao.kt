package com.makentoshe.androidgithubcitemplate.items

import androidx.room.*


@Dao
interface RatingDao {
    @Query("SELECT * FROM Rating")
    fun getAll(): List<Rating>

    @Query("SELECT * FROM Rating WHERE id = :id")
    fun getById(id: Long): Rating

    @Insert
    fun insert(employee: Rating)

    @Update
    fun update(employee: Rating)

    @Query("DELETE FROM Rating WHERE id = :id")
    fun deleteById(id: Long)

    @Delete
    fun delete(employee: Rating?)

    @Query("DELETE FROM Rating")
    fun deleteAll()

    @Update
    fun updateRating(vararg todos: Rating)
}