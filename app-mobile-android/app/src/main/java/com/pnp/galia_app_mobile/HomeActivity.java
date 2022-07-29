package com.pnp.galia_app_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menuIcon = findViewById(R.id.menu_icon);
        homeIcon = findViewById(R.id.home_icon);
        notificationsIcon = findViewById(R.id.notification_icon);
        searchIcon = findViewById(R.id.search_icon);

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