package com.pnp.galia_app_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private ImageView menuIcon;
    private ImageView homeIcon;
    private ImageView notificationsIcon;
    private ImageView searchIcon;

    private Button btnTasks;
    private Button btnBarriers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menuIcon = findViewById(R.id.menu_icon);
        homeIcon = findViewById(R.id.home_icon);
        notificationsIcon = findViewById(R.id.notification_icon);
        searchIcon = findViewById(R.id.search_icon);

        btnTasks = findViewById(R.id.btn_tasks);
        btnBarriers = findViewById(R.id.btn_barriers);

        FragmentManager fragmentManager = getSupportFragmentManager();

        btnTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_home_page, TasksListFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
                btnBarriers.setBackgroundResource(R.drawable.ic_btn_rounded_unselected);
                btnBarriers.setTextColor(getResources().getColor(R.color.gris_5));
                btnTasks.setBackgroundResource(R.drawable.ic_btn_rounded_selected);
                btnTasks.setTextColor(getResources().getColor(R.color.backgroundColor));
            }
        });

        btnBarriers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_home_page, BarriersAllFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
                btnBarriers.setBackgroundResource(R.drawable.ic_btn_rounded_selected);
                btnBarriers.setTextColor(getResources().getColor(R.color.backgroundColor));
                btnTasks.setBackgroundResource(R.drawable.ic_btn_rounded_unselected);
                btnTasks.setTextColor(getResources().getColor(R.color.gris_5));
            }
        });

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"MENU ICON CLICKED",Toast.LENGTH_SHORT).show();
            }
        });

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"HOME ICON CLICKED",Toast.LENGTH_SHORT).show();
            }
        });

        notificationsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"NOTIFICATIONS ICON CLICKED",Toast.LENGTH_SHORT).show();
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"SEARCH ICON CLICKED",Toast.LENGTH_SHORT).show();
            }
        });

    }
}