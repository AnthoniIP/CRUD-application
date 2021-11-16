package com.ipsoft.crudapplication.repository

import com.ipsoft.crudapplication.datasource.model.Task

interface TaskRepository {

    suspend fun getAll(): List<Task>
    suspend fun insertAll(task: Task)
    suspend fun loadById(id: Int): Task
    suspend fun update(task: Task)
    suspend fun delete(task: Task)
}