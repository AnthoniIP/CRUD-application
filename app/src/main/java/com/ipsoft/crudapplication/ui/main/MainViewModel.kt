package com.ipsoft.crudapplication.ui.main

import androidx.lifecycle.ViewModel
import com.ipsoft.crudapplication.repository.TaskRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class MainViewModel(private val repository: TaskRepository) : ViewModel() {

    fun getTasks() = runBlocking {
        val asyncResult = async {
            repository.getAll()
        }

        return@runBlocking asyncResult.await()
    }



}