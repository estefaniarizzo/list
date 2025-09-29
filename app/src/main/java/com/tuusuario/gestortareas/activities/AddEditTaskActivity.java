package com.tuusuario.gestortareas.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.tuusuario.gestortareas.R;
import com.tuusuario.gestortareas.database.AppDatabase;
import com.tuusuario.gestortareas.model.Task;
import androidx.room.Room;
import android.widget.*;

public class AddEditTaskActivity extends AppCompatActivity {
    private EditText etTitle, etDescription, etCategory;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etCategory = findViewById(R.id.etCategory);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "tasks-db").allowMainThreadQueries().build();

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String desc = etDescription.getText().toString();
            String cat = etCategory.getText().toString();
            db.taskDao().insert(new Task(title, desc, cat));
            finish();
        });

        if (savedInstanceState != null) {
            etTitle.setText(savedInstanceState.getString("title"));
            etDescription.setText(savedInstanceState.getString("description"));
            etCategory.setText(savedInstanceState.getString("category"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", etTitle.getText().toString());
        outState.putString("description", etDescription.getText().toString());
        outState.putString("category", etCategory.getText().toString());
    }
}
