package com.ipsoft.crudapplication.datasource.room

import androidx.room.*
import com.ipsoft.crudapplication.datasource.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE id IN (:taskId)")
    fun loadById(taskId: Int): Task


    @Insert
    fun insertAll(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}
