package com.tuusuario.gestortareas.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuusuario.gestortareas.R;
import com.tuusuario.gestortareas.adapter.TaskAdapter;
import com.tuusuario.gestortareas.database.AppDatabase;
import com.tuusuario.gestortareas.model.Task;

import java.util.List;

import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Base de datos Room
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "tasks-db").allowMainThreadQueries().build();

        // Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Task> tasks = db.taskDao().getAllTasks();
        adapter = new TaskAdapter(tasks, this, db);
        recyclerView.setAdapter(adapter);

        // Configuración del canal de notificaciones (solo Android 8+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "canalTareas",
                    "Canal de Tareas",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        // Botón para agregar tareas
        Button btnAdd = findViewById(R.id.btnAddTask);
        btnAdd.setOnClickListener(v -> {
            // Solo abre la pantalla de agregar tarea. La notificación se lanza al guardar
            // la tarea, no aquí.
            Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
            startActivity(intent);
        });
    }

    // Si quieres mostrar una notificación desde MainActivity, llama a este método
    // pasando el texto deseado.
    private void mostrarNotificacion(String tarea) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "canalTareas")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Gestor de Tareas")
                .setContentText(tarea)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    // Ciclo de vida de Activity
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CICLO", "onStart MainActivity");
        Toast.makeText(this, "onStart MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setTasks(db.taskDao().getAllTasks());
        Log.d("CICLO", "onResume MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CICLO", "onPause MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CICLO", "onStop MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CICLO", "onDestroy MainActivity");
    }
}
