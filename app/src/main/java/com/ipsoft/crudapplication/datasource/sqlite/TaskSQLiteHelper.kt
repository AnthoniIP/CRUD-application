package com.ipsoft.crudapplication.datasource.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.ipsoft.crudapplication.datasource.model.Task

class TaskSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @SuppressLint("Range")
    fun getTasks(): List<Task> {

        val tasks: MutableList<Task> = mutableListOf()

        // Select All Query
        val selectQuery = "SELECT  * FROM " + TaskContract.TaskEntry.TABLE_NAME + " ORDER BY " +
                TaskContract.TaskEntry.COLUMN_NAME_DATE + " DESC"

        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        // looping through all rows and adding to list

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val task = Task(
                    id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)),
                    title = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_NAME_TITLE)),
                    description = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_NAME_DESCRIPTION)),
                    date = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_NAME_DATE)),
                    time = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_NAME_TIME)),
                )
                tasks.add(task)
            } while (cursor.moveToNext())
        }

        // close db connection

        // close db connection
        db.close()

        // return notes list

        // return notes list
        return tasks


    }

    fun insertTask(task: Task) {
// get writable database as we want to write data

        // get writable database as we want to write data
        val db = this.writableDatabase

        val values = ContentValues()
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(TaskContract.TaskEntry.COLUMN_NAME_TITLE, task.title)
        values.put(TaskContract.TaskEntry.COLUMN_NAME_DESCRIPTION, task.description)
        values.put(TaskContract.TaskEntry.COLUMN_NAME_DATE, task.date)
        values.put(TaskContract.TaskEntry.COLUMN_NAME_TIME, task.time)


        // insert row

        // insert row
        db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values)

        // close db connection

        // close db connection
        db.close()

        // return newly inserted row id

        // return newly inserted row id

    }

    @SuppressLint("Range")
    fun getTaskById(id: Int): Task {
// get readable database as we are not inserting anything

        // get readable database as we are not inserting anything
        val db = this.readableDatabase

        val cursor = db.query(
            TaskContract.TaskEntry.TABLE_NAME,
            arrayOf(
                BaseColumns._ID,
                TaskContract.TaskEntry.COLUMN_NAME_TITLE,
                TaskContract.TaskEntry.COLUMN_NAME_DESCRIPTION,
                TaskContract.TaskEntry.COLUMN_NAME_DATE,
                TaskContract.TaskEntry.COLUMN_NAME_TIME
            ),
            BaseColumns._ID + "=?",
            arrayOf(id.toString()),
            null,
            null,
            null,
            null
        )

        cursor?.moveToFirst()

        // prepare note object

        // prepare note object
        val task = Task(
            id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)),
            title = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_NAME_TITLE)),
            description = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_NAME_DESCRIPTION)),
            date = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_NAME_DATE)),
            time = cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_NAME_TIME)),
        )

        // close the db connection

        // close the db connection
        cursor.close()

        return task

    }

    fun updateTask(task: Task) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(TaskContract.TaskEntry.COLUMN_NAME_TITLE, task.title)
        values.put(TaskContract.TaskEntry.COLUMN_NAME_DESCRIPTION, task.description)
        values.put(TaskContract.TaskEntry.COLUMN_NAME_DATE, task.date)
        values.put(TaskContract.TaskEntry.COLUMN_NAME_TIME, task.time)

        // updating row

        // updating row
        db.update(
            TaskContract.TaskEntry.TABLE_NAME,
            values,
            BaseColumns._ID + " = ?",
            arrayOf(task.id.toString())
        )
    }

    fun deleteTask(task: Task) {
        val db = this.writableDatabase
        db.delete(
            TaskContract.TaskEntry.TABLE_NAME,
            BaseColumns._ID + " = ?",
            arrayOf(task.id.toString())
        )
        db.close()
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "tasks.db"
    }
}