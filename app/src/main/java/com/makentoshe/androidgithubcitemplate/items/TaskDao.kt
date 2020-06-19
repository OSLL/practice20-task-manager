package com.makentoshe.androidgithubcitemplate.items

import androidx.room.*


@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM Task WHERE id = :id")
    fun getById(id: Long): Task

    @Insert
    fun insert(employee: Task)

    @Update
    fun update(employee: Task)

    @Query("SELECT * FROM Task ORDER BY pin DESC")
    fun sortedPinned(): List<Task>

    @Query("DELETE FROM Task WHERE id = :id")
    fun deleteById(id: Long)

    @Delete
    fun delete(employee: Task?)

    @Query("DELETE FROM Task")
    fun deleteAll()

    @Update
    fun updateTask(vararg todos: Task)
}