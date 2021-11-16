# CRUD-application

This apps uses room and SQLiteHelper for database. 

If you wish change beetween then, just uncomment the //single<TaskRepository> { TaskRepositoryRoom(androidApplication()) }
and comment the  single<TaskRepository> { TaskRepositorySQLite(get()) } at di packet >> modules.kt and run the project.
