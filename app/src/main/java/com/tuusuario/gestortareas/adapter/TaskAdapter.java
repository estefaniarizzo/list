package com.tuusuario.gestortareas.adapter;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import com.tuusuario.gestortareas.R;
import com.tuusuario.gestortareas.model.Task;
import com.tuusuario.gestortareas.database.AppDatabase;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;
    private Context context;
    private AppDatabase db;

    public TaskAdapter(List<Task> tasks, Context context, AppDatabase db) {
        this.tasks = tasks;
        this.context = context;
        this.db = db;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task t = tasks.get(position);
        holder.tvTitle.setText(t.title);
        holder.tvCategory.setText(t.category);
        holder.btnDelete.setOnClickListener(v -> {
            db.taskDao().delete(t);
            setTasks(db.taskDao().getAllTasks());
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCategory;
        Button btnDelete;

        TaskViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
