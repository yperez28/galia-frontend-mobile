package com.pnp.galia_app_mobile;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    private Notification[] notificationList;

    public NotificationAdapter(Notification[] notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.notification_content, parent, false);
        NotificationAdapter.ViewHolder viewHolder = new NotificationAdapter.ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {

        holder.notificationDescription.setText(notificationList[position].getDescription());
        holder.time.setText(notificationList[position].getTime());

        setIcon(holder, position);

        if (notificationList[position].getRead()) {
            holder.container.setBackgroundColor(Color.parseColor("#FDFDFD"));
        }

        holder.container.setOnClickListener(view -> {
            holder.container.setBackgroundColor(Color.parseColor("#FDFDFD"));
            notificationList[position].setRead(true);
            setIcon(holder, position);
        });
    }

    @Override
    public int getItemCount() {
        return notificationList.length;
    }

    public void setIcon(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        switch (notificationList[position].getType()) {
            case REMINDER:
                if (notificationList[position].getRead()) {
                    holder.notificationIcon.setImageResource(R.drawable.ic_task_check_pink_read);
                    holder.circle.setImageResource(R.drawable.ic_circle_pink_read);
                } else {
                    holder.notificationIcon.setImageResource(R.drawable.ic_task_check_pink);
                    holder.circle.setImageResource(R.drawable.ic_small_circle);
                }
                break;
            case NEW_PATIENT:
                if (notificationList[position].getRead()) {
                    holder.notificationIcon.setImageResource(R.drawable.ic_patient_purple_read);
                    holder.circle.setImageResource(R.drawable.ic_small_circle_purple_read);
                } else {
                    holder.notificationIcon.setImageResource(R.drawable.ic_patient_purple);
                    holder.circle.setImageResource(R.drawable.ic_small_circle_purple);
                }
                break;
            default:
                if (notificationList[position].getRead()) {
                    holder.notificationIcon.setImageResource(R.drawable.ic_task_check_purple_read);
                    holder.circle.setImageResource(R.drawable.ic_small_circle_purple_read);
                } else {
                    holder.notificationIcon.setImageResource(R.drawable.ic_task_check_purple);
                    holder.circle.setImageResource(R.drawable.ic_small_circle_purple);
                }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout container;
        public ImageView circle;
        public TextView notificationDescription;
        public ImageView notificationIcon;
        public TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.container = itemView.findViewById(R.id.notification_container);
            this.circle = itemView.findViewById(R.id.notification_circle);
            this.notificationDescription = itemView.findViewById(R.id.notification_description);
            this.notificationIcon = itemView.findViewById(R.id.notification_icon);
            this.time = itemView.findViewById(R.id.notification_time);
        }
    }
}
