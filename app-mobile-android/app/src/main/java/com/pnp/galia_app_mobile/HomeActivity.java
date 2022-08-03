package com.pnp.galia_app_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener {

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

    private ImageView homeIcon;
    private ImageView notificationsIcon;
    private ImageView searchIcon;

    private boolean clicked = false;

    private DrawerLayout drawerLayout;
    private RelativeLayout toolBarIcons;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolBarIcons = findViewById(R.id.option_icons_container);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        drawerLayout.setScrimColor(Color.parseColor("#BA2C75"));
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        MenuItem menuItem = navigationView.getMenu().getItem(0);
        onNavigationItemSelected(menuItem);
        menuItem.setChecked(true);

        drawerLayout.addDrawerListener(this);

        toolBarIcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"Icon clicked",Toast.LENGTH_SHORT).show();
            }
        });

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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.planification_item:
                Toast.makeText(HomeActivity.this, "Opening planification",Toast.LENGTH_SHORT).show();
                break;
            case R.id.patient_item:
                Toast.makeText(HomeActivity.this, "Opening Patient",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings_item:
                Toast.makeText(HomeActivity.this, "Opening Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.log_out_item:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                throw new IllegalArgumentException("menu option not implemented!!");
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerOpened(@NonNull View view) {
    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
    }

    @Override
    public void onDrawerStateChanged(int i) {
    }
}