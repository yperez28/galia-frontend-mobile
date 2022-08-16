package com.pnp.galia_app_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private Button btnTasks;
    private Button btnBarriers;
    private boolean clicked = false;
    private boolean notificationOpen = false;

    private DrawerLayout drawerLayout;
    private RelativeLayout toolBarIcons;
    private NavigationView navigationView;
    private CardView modeCardView;
    private CardView barriersCardView;
    private CardView selectedCardView;
    private TextView notificationCounter;
    private int notificationNumberCounter = 0;
    private RecyclerView notificationRecycler;
    private TextView readNotifications;

    private Toolbar toolbar;

    private Notification[] notificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        notificationList = new Notification[] {new Notification("Tienes una tarea pendiente para hoy! Recuerda completarla", NotificationType.REMINDER, false, "Hace 2min"), new Notification("La Dra. Mariela Boñaños te ha asignado una nueva paciente.", NotificationType.NEW_PATIENT, false, "Hace 5min"), new Notification("La Dra. Mariela Boñaños te ha asignado una nueva tarea.", NotificationType.NEW_TASK, false, "Hace 14min"), new Notification("Tienes una tarea pendiente para hoy! Recuerda completarla", NotificationType.NEW_TASK, true, "Hace 2min"), new Notification("Tienes una tarea pendiente para hoy! Recuerda completarla", NotificationType.REMINDER, true, "Hace 2min"), new Notification("Tienes una tarea pendiente para hoy! Recuerda completarla", NotificationType.REMINDER, true, "Hace 2min"), new Notification("Tienes una tarea pendiente para hoy! Recuerda completarla", NotificationType.NEW_PATIENT, true, "Hace 2min")};

        toolBarIcons = findViewById(R.id.option_icons_container);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        drawerLayout.setScrimColor(Color.parseColor("#BABA2C75"));
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        MenuItem menuItem = navigationView.getMenu().getItem(0);
        onNavigationItemSelected(menuItem);
        menuItem.setChecked(true);

        Button listViewButton = findViewById(R.id.list_view);
        Button calendarViewButton = findViewById(R.id.calendar_view);
        listViewButton.setSelected(true);
        listViewButton.setBackgroundResource(R.drawable.ic_button_background);
        Button allViewButton = findViewById(R.id.barriers_all_view);
        Button internViewButton = findViewById(R.id.barriers_interns_view);
        Button externViewButton = findViewById(R.id.barriers_externs_view);
        allViewButton.setSelected(true);
        allViewButton.setBackgroundResource(R.drawable.ic_button_background);
        modeCardView = findViewById(R.id.mode_cardview);
        barriersCardView = findViewById(R.id.cardview_barriers);
        selectedCardView = findViewById(R.id.cardview_tasks);

        drawerLayout.addDrawerListener(this);

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

        btnTasks = findViewById(R.id.btn_tasks);
        btnBarriers = findViewById(R.id.btn_barriers);

        getNotificationsCounter();
        notificationCounter = findViewById(R.id.notification_counter);
        notificationCounter.setText(setNotificationIcons());
        notificationRecycler = findViewById(R.id.notifications_recycler);
        readNotifications = findViewById(R.id.read_notifications);

        FragmentManager fragmentManager = getSupportFragmentManager();

        listViewButton.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_home_page, TasksListFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
            calendarViewButton.setSelected(false);
            calendarViewButton.setBackgroundResource(R.color.transparentColor);
            listViewButton.setSelected(true);
            listViewButton.setBackgroundResource(R.drawable.ic_button_background);
        });

        calendarViewButton.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_home_page, TasksCalendarFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();

            calendarViewButton.setSelected(true);
            calendarViewButton.setBackgroundResource(R.drawable.ic_button_background);
            listViewButton.setSelected(false);
            listViewButton.setBackgroundResource(R.color.transparentColor);
        });

        allViewButton.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_home_page, BarriersAllFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
            internViewButton.setSelected(false);
            internViewButton.setBackgroundResource(R.color.transparentColor);
            externViewButton.setSelected(false);
            externViewButton.setBackgroundResource(R.color.transparentColor);
            allViewButton.setSelected(true);
            allViewButton.setBackgroundResource(R.drawable.ic_button_background);
        });

        internViewButton.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_home_page, BarriersInternFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
            internViewButton.setSelected(true);
            internViewButton.setBackgroundResource(R.drawable.ic_button_background);
            externViewButton.setSelected(false);
            externViewButton.setBackgroundResource(R.color.transparentColor);
            allViewButton.setSelected(false);
            allViewButton.setBackgroundResource(R.color.transparentColor);
        });

        externViewButton.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_home_page, BarriersExternFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
            internViewButton.setSelected(false);
            internViewButton.setBackgroundResource(R.color.transparentColor);
            externViewButton.setSelected(true);
            externViewButton.setBackgroundResource(R.drawable.ic_button_background);
            allViewButton.setSelected(false);
            allViewButton.setBackgroundResource(R.color.transparentColor);
        });

        btnTasks.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_home_page, TasksListFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
            btnBarriers.setBackgroundResource(R.drawable.ic_btn_rounded_unselected);
            btnBarriers.setTextColor(getResources().getColor(R.color.gris_5));
            btnTasks.setBackgroundResource(R.drawable.ic_btn_rounded_selected);
            btnTasks.setTextColor(getResources().getColor(R.color.backgroundColor));
            modeCardView.setVisibility(View.VISIBLE);
            barriersCardView.setVisibility(View.INVISIBLE);
        });

        btnBarriers.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_home_page, BarriersAllFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
            btnBarriers.setBackgroundResource(R.drawable.ic_btn_rounded_selected);
            btnBarriers.setTextColor(getResources().getColor(R.color.backgroundColor));
            btnTasks.setBackgroundResource(R.drawable.ic_btn_rounded_unselected);
            btnTasks.setTextColor(getResources().getColor(R.color.gris_5));
            modeCardView.setVisibility(View.INVISIBLE);
            barriersCardView.setVisibility(View.VISIBLE);
        });

        floating_button_main.setOnClickListener(view -> {
            setVisibility(clicked);
            setClickable(clicked);
            setAnimation(clicked);
            clicked = !clicked;
        });

        homeIcon.setOnClickListener(view -> Toast.makeText(HomeActivity.this,"HOME ICON CLICKED",Toast.LENGTH_SHORT).show());

        notificationsIcon.setOnClickListener(view -> {
            LinearLayout notificationsContainer = findViewById(R.id.notification_layout);
            if (!notificationOpen) {
                notificationsContainer.setVisibility(View.VISIBLE);
                notificationOpen = true;
                getNotifications(notificationList);
                getNotificationsCounter();
                notificationCounter.setText(setNotificationIcons());
            } else {
                notificationsContainer.setVisibility(View.INVISIBLE);
                notificationOpen = false;
                getNotificationsCounter();
                notificationCounter.setText(setNotificationIcons());
            }
        });

        readNotifications.setOnClickListener(view -> {
            for (int i = 0; i < notificationList.length; i++) {
                notificationList[i].setRead(true);
                readNotifications.setSelected(true);
                getNotifications(notificationList);
            }
            notificationCounter.setVisibility(View.INVISIBLE);
            notificationsIcon.setSelected(false);

        });
    }

    private void getNotificationsCounter() {
        int counter = 0;
        if (notificationList != null) {
            for (int i = 0; i < notificationList.length; i++) {
                if (!notificationList[i].getRead()) {
                    counter = counter + 1;
                }
            }
        }
        notificationNumberCounter = counter;
    }

    private String setNotificationIcons() {
        if (notificationNumberCounter > 0) {
            notificationCounter.setVisibility(View.VISIBLE);
            notificationsIcon.setSelected(true);
        } else {
            notificationCounter.setVisibility(View.INVISIBLE);
            notificationsIcon.setSelected(false);
        }
        return String.valueOf(notificationNumberCounter);
    }

    private void getNotifications(Notification[] notificationList) {
        NotificationAdapter adapter = new NotificationAdapter(notificationList);
        notificationRecycler.setHasFixedSize(true);
        notificationRecycler.setAdapter(adapter);
        notificationRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void setVisibility(boolean clicked) {
        LinearLayout overlay = findViewById(R.id.floating_button_overlay);
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                modeCardView.setElevation(0);
                selectedCardView.setElevation(0);
            }
            overlay.setBackgroundResource(R.color.overlayColor);
            overlay.setVisibility(View.VISIBLE);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                modeCardView.setElevation(4);
                selectedCardView.setElevation(4);
            }
            overlay.setBackgroundResource(R.color.transparentColor);
            overlay.setVisibility(View.INVISIBLE);
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