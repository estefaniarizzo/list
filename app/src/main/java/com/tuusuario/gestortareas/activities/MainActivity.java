package com.tuusuario.gestortareas.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.tuusuario.gestortareas.R;
import com.tuusuario.gestortareas.database.AppDatabase;
import com.tuusuario.gestortareas.model.Task;
import com.tuusuario.gestortareas.adapter.TaskAdapter;
import java.util.List;
import androidx.room.Room;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "tasks-db").allowMainThreadQueries().build();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Task> tasks = db.taskDao().getAllTasks();
        adapter = new TaskAdapter(tasks, this, db);
        recyclerView.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.btnAddTask);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setTasks(db.taskDao().getAllTasks());
    }
}
