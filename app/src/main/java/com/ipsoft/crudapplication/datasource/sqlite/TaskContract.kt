package com.ipsoft.crudapplication.datasource.sqlite

import android.provider.BaseColumns

object TaskContract {

    object TaskEntry : BaseColumns {
        const val TABLE_NAME = "tasks"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_DATE = "date"
        const val COLUMN_NAME_TIME = "time"
    }

}