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
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
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
    public int getItemCount() { return taskList.length; }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
