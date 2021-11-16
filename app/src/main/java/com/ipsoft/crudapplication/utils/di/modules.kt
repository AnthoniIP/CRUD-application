package com.ipsoft.crudapplication.utils.di

import com.ipsoft.crudapplication.datasource.sqlite.TaskSQLiteHelper
import com.ipsoft.crudapplication.repository.TaskRepository
import com.ipsoft.crudapplication.repository.TaskRepositoryRoom
import com.ipsoft.crudapplication.repository.TaskRepositorySQLite
import com.ipsoft.crudapplication.ui.main.MainViewModel
import com.ipsoft.crudapplication.ui.newtask.NewTaskViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //This apps uses room and SQLiteHelper for database.

    //If you wish change beetween then,
    // just uncomment a TaskRepository above and comment another

    single<TaskRepository> { TaskRepositoryRoom(androidApplication()) }
    //single<TaskRepository> { TaskRepositorySQLite(get()) }
    single { TaskSQLiteHelper(androidContext()) }
    single { TaskRepositorySQLite(get()) }
    viewModel { MainViewModel(repository = get()) }
    viewModel { NewTaskViewModel(repository = get()) }

}