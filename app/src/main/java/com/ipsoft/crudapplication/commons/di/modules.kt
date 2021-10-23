package com.ipsoft.crudapplication.commons.di

import com.ipsoft.crudapplication.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel() }

}