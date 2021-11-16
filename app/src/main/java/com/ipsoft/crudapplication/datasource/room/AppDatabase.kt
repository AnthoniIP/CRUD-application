package ipsoft.lembretesetarefas.datasource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ipsoft.crudapplication.datasource.model.Task
import com.ipsoft.crudapplication.datasource.room.DB_NAME
import com.ipsoft.crudapplication.datasource.room.TaskDao

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        fun createDataBase(context: Context): TaskDao {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
                .taskDao()
        }
    }

}