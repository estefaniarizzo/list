package com.tuusuario.gestortareas.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.tuusuario.gestortareas.model.Task;

@Database(entities = { Task.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
