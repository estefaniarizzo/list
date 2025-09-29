package com.tuusuario.gestortareas.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String title;

    public String description;

    public String category;

    public Task(@NonNull String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }
}
