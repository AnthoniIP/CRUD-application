package com.ipsoft.crudapplication.utils.di

import com.ipsoft.crudapplication.repository.TaskRepository
import com.ipsoft.crudapplication.repository.TaskRepositoryRoom
import com.ipsoft.crudapplication.ui.main.MainViewModel
import com.ipsoft.crudapplication.ui.newtask.NewTaskViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<TaskRepository> { TaskRepositoryRoom(androidApplication()) }
    viewModel { MainViewModel(repository = get()) }
    viewModel { NewTaskViewModel(repository = get()) }

}