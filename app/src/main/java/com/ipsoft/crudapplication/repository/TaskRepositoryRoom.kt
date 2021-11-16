package com.ipsoft.crudapplication.repository

import android.content.Context
import com.ipsoft.crudapplication.datasource.model.Task
import ipsoft.lembretesetarefas.datasource.room.AppDatabase

class TaskRepositoryRoom(context: Context) : TaskRepository {

    private val db = AppDatabase.createDataBase(context = context)

    override suspend fun getAll(): List<Task> = db.getAll()

    override suspend fun insertAll(task: Task) = db.insertAll(task)

    override suspend fun loadById(id: Int) = db.loadById(id)
    override suspend fun update(task: Task) = db.update(task)
    override suspend fun delete(task: Task) = db.delete(task)
}