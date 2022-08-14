package com.pnp.galia_app_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class DropDownItemAdapter extends RecyclerView.Adapter<DropDownItemAdapter.ViewHolder> {
    private String[] itemList;
    private MaterialAutoCompleteTextView currentLayout;
    private RecyclerView currentOptionsList;

    public DropDownItemAdapter(String[] itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public DropDownItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.dropdown_content, parent, false);
        DropDownItemAdapter.ViewHolder viewHolder = new DropDownItemAdapter.ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DropDownItemAdapter.ViewHolder holder, int position) {
        
        holder.itemName.setText(itemList[position]);

        holder.itemName.setOnClickListener(view -> {
            currentLayout.setText(holder.itemName.getText());
            currentOptionsList.setVisibility(View.GONE);
            currentLayout.setSelected(false);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemName = itemView.findViewById(R.id.item_name);
        }
    }

    public void setCurrentLayout(MaterialAutoCompleteTextView currentLayout) {
        this.currentLayout = currentLayout;
    }

    public void setCurrentOptionsList(RecyclerView currentOptionsList) {
        this.currentOptionsList = currentOptionsList;
    }
}
