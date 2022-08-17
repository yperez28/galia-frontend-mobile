package com.pnp.galia_app_mobile;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BarrierAdapter extends RecyclerView.Adapter<BarrierAdapter.ViewHolder> {

    private Barrier[] barrierList;

    public BarrierAdapter(Barrier[] listBarriers) { this.barrierList = listBarriers; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.barrier_content, parent, false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.barrierName.setText(barrierList[position].getName());
        holder.patientName.setText(barrierList[position].getPatient());
        holder.priorityDescription.setText(barrierList[position].getPriority());
        holder.classificationName.setText(barrierList[position].getClassification());
        holder.typeDescription.setText(barrierList[position].getType());
        holder.codeDescription.setText(barrierList[position].getCode());

        String priority = barrierList[position].getPriority();
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
    public int getItemCount() { return barrierList.length; }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView barrierName;
        public TextView patientName;
        public TextView priorityDescription;
        public TextView classificationName;
        public TextView typeDescription;
        public TextView codeDescription;
        public View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.barrierName = itemView.findViewById(R.id.barrier_name);
            this.patientName = itemView.findViewById(R.id.patient_name);
            this.priorityDescription = itemView.findViewById(R.id.priority_description);
            this.classificationName = itemView.findViewById(R.id.clasification_description);
            this.typeDescription = itemView.findViewById(R.id.type_description);
            this.codeDescription = itemView.findViewById(R.id.code_description);
            this.divider = itemView.findViewById(R.id.divider);
        }
    }
}
