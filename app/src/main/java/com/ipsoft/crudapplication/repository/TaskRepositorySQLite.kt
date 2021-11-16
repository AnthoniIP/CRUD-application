package com.ipsoft.crudapplication.repository

import com.ipsoft.crudapplication.datasource.model.Task

class TaskRepositorySQLite: TaskRepository {
    override suspend fun getAll(): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun loadById(id: Int): Task {
        TODO("Not yet implemented")
    }

    override suspend fun update(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(task: Task) {
        TODO("Not yet implemented")
    }
}