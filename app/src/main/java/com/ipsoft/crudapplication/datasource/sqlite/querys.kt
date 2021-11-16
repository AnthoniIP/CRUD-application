package com.ipsoft.crudapplication.datasource.sqlite

import android.provider.BaseColumns

const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${TaskContract.TaskEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${TaskContract.TaskEntry.COLUMN_NAME_TITLE} TEXT," +
            "${TaskContract.TaskEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
            "${TaskContract.TaskEntry.COLUMN_NAME_DATE} TEXT," +
            "${TaskContract.TaskEntry.COLUMN_NAME_TIME})"

const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TaskContract.TaskEntry.TABLE_NAME}"