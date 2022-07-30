package com.pnp.galia_app_mobile;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Task[] taskList;

    public TaskAdapter(Task[] taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.task_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.taskDescription.setText(taskList[position].getDescription());
        holder.patientName.setText(taskList[position].getPatient());
        holder.modeDescription.setText(taskList[position].getMode());

        String priority = taskList[position].getPriority();

        switch (priority) {
            case "Baja":
                holder.divider.setBackgroundColor(Color.parseColor("#808E3B"));
                break;
            case "Media":
                holder.divider.setBackgroundColor(Color.parseColor("#F5CB5E"));
                break;
            case "Alta":
                holder.divider.setBackgroundColor(Color.parseColor("#9D2234"));
                break;
            default:
                holder.divider.setBackgroundColor(Color.parseColor("#828282"));
        }
    }

    @Override
    public int getItemCount() {
        return taskList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox taskDescription;
        public TextView patientName;
        public TextView modeDescription;
        public View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.taskDescription = itemView.findViewById(R.id.task_description);
            this.patientName = itemView.findViewById(R.id.patient_name);
            this.modeDescription = itemView.findViewById(R.id.mode_description);
            this.divider = itemView.findViewById(R.id.divider);
        }
    }
}
