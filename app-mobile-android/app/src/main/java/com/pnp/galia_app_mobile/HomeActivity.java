package com.pnp.galia_app_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {


    private FloatingActionButton floating_button_main;
    private FloatingActionButton floating_button_appointment;
    private FloatingActionButton floating_button_barrier;
    private FloatingActionButton floating_button_tracing;
    private FloatingActionButton floating_button_patient;
    private FloatingActionButton floating_button_task;

    private TextView text_view_appointment;
    private TextView text_view_barrier;
    private TextView text_view_tracing;
    private TextView text_view_patient;
    private TextView text_view_task;
    
    private ImageView menuIcon;
    private ImageView homeIcon;
    private ImageView notificationsIcon;
    private ImageView searchIcon;

    private boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floating_button_main = findViewById(R.id.floating_button_main);
        floating_button_appointment = findViewById(R.id.floating_button_appointment);
        floating_button_barrier = findViewById(R.id.floating_button_barrier);
        floating_button_tracing = findViewById(R.id.floating_button_tracing);
        floating_button_patient = findViewById(R.id.floating_button_patient);
        floating_button_task = findViewById(R.id.floating_button_task);

        text_view_appointment = findViewById(R.id.text_view_appointment);
        text_view_barrier = findViewById(R.id.text_view_barrier);
        text_view_tracing = findViewById(R.id.text_view_tracing);
        text_view_patient = findViewById(R.id.text_view_patient);
        text_view_task = findViewById(R.id.text_view_task);
        
        menuIcon = findViewById(R.id.menu_icon);
        homeIcon = findViewById(R.id.home_icon);
        notificationsIcon = findViewById(R.id.notification_icon);
        searchIcon = findViewById(R.id.search_icon);

        floating_button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibility(clicked);
                setClickable(clicked);
                setAnimation(clicked);
                clicked = !clicked;
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
    
    private void setVisibility(boolean clicked) {
        if (!clicked) {
            floating_button_appointment.setVisibility(View.VISIBLE);
            floating_button_barrier.setVisibility(View.VISIBLE);
            floating_button_tracing.setVisibility(View.VISIBLE);
            floating_button_patient.setVisibility(View.VISIBLE);
            floating_button_task.setVisibility(View.VISIBLE);
            text_view_appointment.setVisibility(View.VISIBLE);
            text_view_barrier.setVisibility(View.VISIBLE);
            text_view_tracing.setVisibility(View.VISIBLE);
            text_view_patient.setVisibility(View.VISIBLE);
            text_view_task.setVisibility(View.VISIBLE);
        } else {
            floating_button_appointment.setVisibility(View.INVISIBLE);
            floating_button_barrier.setVisibility(View.INVISIBLE);
            floating_button_tracing.setVisibility(View.INVISIBLE);
            floating_button_patient.setVisibility(View.INVISIBLE);
            floating_button_task.setVisibility(View.INVISIBLE);
            text_view_appointment.setVisibility(View.INVISIBLE);
            text_view_barrier.setVisibility(View.INVISIBLE);
            text_view_tracing.setVisibility(View.INVISIBLE);
            text_view_patient.setVisibility(View.INVISIBLE);
            text_view_task.setVisibility(View.INVISIBLE);
        }
    }

    private void setClickable(boolean clicked) {
        if (!clicked) {
            floating_button_appointment.setClickable(true);
            floating_button_barrier.setClickable(true);
            floating_button_tracing.setClickable(true);
            floating_button_patient.setClickable(true);
            floating_button_task.setClickable(true);
        } else {
            floating_button_appointment.setClickable(false);
            floating_button_barrier.setClickable(false);
            floating_button_tracing.setClickable(false);
            floating_button_patient.setClickable(false);
            floating_button_task.setClickable(false);
        }
    }

    private void setAnimation(boolean clicked) {
        Animation rotateOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_open_anim);
        Animation rotateClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_close_anim);
        Animation fromBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_bottom_anim);
        Animation fromBottomTitle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_bottom_title_anim);
        Animation toBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_bottom_anim);
        Animation toBottomTitle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_bottom_title_anim);

        if (!clicked) {
            floating_button_appointment.setAnimation(fromBottom);
            floating_button_barrier.setAnimation(fromBottom);
            floating_button_tracing.setAnimation(fromBottom);
            floating_button_patient.setAnimation(fromBottom);
            floating_button_task.setAnimation(fromBottom);
            text_view_appointment.setAnimation(fromBottomTitle);
            text_view_barrier.setAnimation(fromBottomTitle);
            text_view_tracing.setAnimation(fromBottomTitle);
            text_view_patient.setAnimation(fromBottomTitle);
            text_view_task.setAnimation(fromBottomTitle);
            floating_button_main.setAnimation(rotateOpen);
        } else {
            floating_button_appointment.setAnimation(toBottom);
            floating_button_barrier.setAnimation(toBottom);
            floating_button_tracing.setAnimation(toBottom);
            floating_button_patient.setAnimation(toBottom);
            floating_button_task.setAnimation(toBottom);
            text_view_appointment.setAnimation(toBottomTitle);
            text_view_barrier.setAnimation(toBottomTitle);
            text_view_tracing.setAnimation(toBottomTitle);
            text_view_patient.setAnimation(toBottomTitle);
            text_view_task.setAnimation(toBottomTitle);
            floating_button_main.setAnimation(rotateClose);
        }
    }
}