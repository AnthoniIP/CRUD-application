package com.ipsoft.crudapplication.repository

import com.ipsoft.crudapplication.datasource.model.Task
import com.ipsoft.crudapplication.datasource.sqlite.TaskSQLiteHelper

class TaskRepositorySQLite(private val dbHelper: TaskSQLiteHelper) : TaskRepository {
    override suspend fun getAll(): List<Task> = dbHelper.getTasks()

    override suspend fun insertAll(task: Task) = dbHelper.insertTask(task)

    override suspend fun loadById(id: Int): Task = dbHelper.getTaskById(id)

    override suspend fun update(task: Task) = dbHelper.updateTask(task)

    override suspend fun delete(task: Task) = dbHelper.deleteTask(task)
}