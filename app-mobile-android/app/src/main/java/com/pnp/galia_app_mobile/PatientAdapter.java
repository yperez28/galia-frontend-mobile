package com.pnp.galia_app_mobile;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {

    private Patient[] patientList;
    private Boolean isClicked = false;

    public PatientAdapter(Patient[] patientList) {
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.patient_content, parent, false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.patientName.setText(patientList[position].getName());
        holder.status.setText(patientList[position].getStatus());
        holder.id.setText(patientList[position].getId());
        holder.token.setText(patientList[position].getToken());
        holder.captureDate.setText(patientList[position].getCaptureDate());
        holder.modificationDate.setText(patientList[position].getModificationDate());

        if (patientList[position].getStatus().equals("Activa")) {
            holder.status.setTextColor(Color.parseColor("#BA2C75"));
        } else {
            holder.status.setTextColor(Color.parseColor("#5D5FEF"));
        }

        holder.patientNameInformation.setOnClickListener(view -> {
            if (isClicked) {
                holder.patientFullInformation.setVisibility(View.GONE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.icon.setImageTintList(ColorStateList.valueOf(Color.parseColor("#828282")));
                    holder.icon.setRotation(0);
                }
            } else {
                holder.patientFullInformation.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.icon.setImageTintList(ColorStateList.valueOf(Color.parseColor("#5D5FEF")));
                    holder.icon.setRotation(180);
                }
            }
            isClicked = !isClicked;
        });
    }

    @Override
    public int getItemCount() {
        return patientList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView patientName;
        public TextView status;
        public TextView id;
        public TextView token;
        public TextView captureDate;
        public TextView modificationDate;
        private ImageView icon;
        public RelativeLayout patientNameInformation;
        public RelativeLayout patientFullInformation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.patientName = itemView.findViewById(R.id.patient_name_textview);
            this.status = itemView.findViewById(R.id.patient_status);
            this.id = itemView.findViewById(R.id.patient_id_content);
            this.token = itemView.findViewById(R.id.patient_token_content);
            this.captureDate = itemView.findViewById(R.id.patient_capture_date_content);
            this.modificationDate = itemView.findViewById(R.id.patient_modification_content);
            this.icon = itemView.findViewById(R.id.expandable_icon);
            this.patientNameInformation = itemView.findViewById(R.id.patient_main_information);
            this.patientFullInformation = itemView.findViewById(R.id.patient_all_information);
        }
    }
}
