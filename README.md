# CRUD-application

This apps uses room and sqlite3 for database if you wish change beetween then, just uncomment the //single<TaskRepository> { TaskRepositoryRoom(androidApplication()) }
and comment the  single<TaskRepository> { TaskRepositorySQLite(get()) } at di packet >> modules.kt and run the project.
