package com.pnp.galia_app_mobile;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private Task[] taskList;

    public TaskListAdapter(Task[] myTaskList) {
        this.taskList = myTaskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.tasklist_content, parent, false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.taskDescription.setText(taskList[position].getDescription());
        holder.patientName.setText(taskList[position].getPatient());
        holder.priorityDescription.setText(taskList[position].getPriority());
        holder.deadlineDescription.setText("Hoy");
        holder.modeDescription.setText(taskList[position].getMode());

        String priority = taskList[position].getPriority();
        switch (priority) {
            case "Baja":
                holder.priorityDescription.setTextColor(Color.parseColor("#808E3B"));
                holder.priorityDescription.setActivated(true);
                break;
            case "Media":
                holder.priorityDescription.setTextColor(Color.parseColor("#F5CB5E"));
                holder.priorityDescription.setSelected(true);
                break;
            case "Alta":
                holder.priorityDescription.setTextColor(Color.parseColor("#9D2234"));
                holder.priorityDescription.setPressed(true);
                break;
            default:
                holder.priorityDescription.setTextColor(Color.parseColor("#828282"));
        }

    }

    @Override
    public int getItemCount() { return taskList.length; }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox taskDescription;
        public TextView patientName;
        public TextView priorityDescription;
        public TextView deadlineDescription;
        public TextView modeDescription;
        public View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.taskDescription = itemView.findViewById(R.id.tasklist_description);
            this.patientName = itemView.findViewById(R.id.patientList_name);
            this.priorityDescription = itemView.findViewById(R.id.priorityList_description);
            this.deadlineDescription = itemView.findViewById(R.id.deadline_description);
            this.modeDescription = itemView.findViewById(R.id.modeList_description);
            this.divider = itemView.findViewById(R.id.dividerList);
        }
    }
}
