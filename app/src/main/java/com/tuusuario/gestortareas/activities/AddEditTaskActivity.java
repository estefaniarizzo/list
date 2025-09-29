package com.tuusuario.gestortareas.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.tuusuario.gestortareas.R;
import com.tuusuario.gestortareas.database.AppDatabase;
import com.tuusuario.gestortareas.model.Task;
import androidx.room.Room;
import android.widget.*;

import android.util.Log;
import android.widget.Toast;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.content.Context;

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
            // Notificación local al guardar tarea
            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE);
            String channelId = "tareas_channel";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, "Tareas",
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Tarea guardada")
                    .setContentText("¡Tu tarea fue creada exitosamente!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            notificationManager.notify(1, builder.build());
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CICLO", "onStart AddEditTaskActivity");
        Toast.makeText(this, "onStart AddEditTaskActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CICLO", "onResume AddEditTaskActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CICLO", "onPause AddEditTaskActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CICLO", "onStop AddEditTaskActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CICLO", "onDestroy AddEditTaskActivity");
    }
}
