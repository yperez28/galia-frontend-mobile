package com.pnp.galia_app_mobile;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class PatientsActivity extends AppCompatActivity
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
    private TextView notificationCounter;
    private TextView readNotifications;

    private ImageView homeIcon;
    private ImageView notificationsIcon;
    private ImageView filterIcon;
    private ImageView provinceClose;
    private ImageView cancerClose;
    private ImageView treatmentClose;
    private ImageView genderClose;

    private boolean clicked = false;
    private boolean notificationOpen = false;
    private boolean isFilterClicked = false;
    private boolean isProvinceSelected = false;
    private boolean isCancerSelected = false;
    private boolean isTreatmentSelected = false;
    private boolean isGenderSelected = false;

    private int notificationNumberCounter = 0;

    private DrawerLayout drawerLayout;

    private RelativeLayout toolBarIcons;
    private RelativeLayout filterSelector;

    private LinearLayout provinceFilterSelected;
    private LinearLayout cancerTypeFilterSelected;
    private LinearLayout treatmentFilterSelected;
    private LinearLayout genderFilterSelected;
    private LinearLayout container;

    private CheckBox provinceFilter;
    private CheckBox cancerTypeFilter;
    private CheckBox treatmentFilter;
    private CheckBox genderFilter;

    private NavigationView navigationView;

    private RecyclerView notificationRecycler;

    private CardView filterButton;

    private Toolbar toolbar;

    private Notification[] notificationList;
    private Patient[] patientList = new Patient[] {new Patient("Marina Gómez Muñoz", "Activa", "102340578", "F2020-0201", "14/08/2022", "14/08/2022"), new Patient("Maria Gutierrez Lopez", "Activa", "103330578", "F2020-0202", "17/08/2022", "18/08/2022"),
            new Patient("Sandra Gómez Muñoz", "Activa", "502580586", "F2020-0203", "14/07/2022", "14/08/2022"), new Patient("Karla Gómez Muñoz", "Activa", "102340555", "F2020-0205", "14/08/2021", "14/09/2022"),
            new Patient("Alondra Gómez Muñoz", "Activa", "303340378", "F2020-0206", "04/02/2022", "06/05/2022"), new Patient("Jimena Gomez Gomez", "Activa", "102470222", "F2020-0901", "30/10/2017", "28/02/2019"),
            new Patient("Luciana Torres Torres", "Activa", "305590471", "F2021-0201", "23/01/2015", "26/02/2016"),new Patient("Ivannia Alfaro Alfaro", "Activa", "701590852", "F2021-0303", "17/02/2018", "31/12/2021"),
            new Patient("Sofía Gómez Gómez", "Inactiva", "305590647", "F2020-0209", "26/08/2020", "30/08/2020"), new Patient("Genesis López Muñoz", "Inactiva", "509890475", "F2020-0301", "24/01/2019", "14/08/2022"),
            new Patient("Felicia Ulloa Muñoz", "Inactiva", "122340578", "F2020-0222", "22/03/2022", "22/03/2022"),new Patient("Karina Madriz Madriz", "Inactiva", "603350669", "F2020-0211", "25/06/2022", "25/06/2022"),
            new Patient("Guadalupe Jimenez Mendez", "Inactiva", "405260147", "F2020-0701", "07/02/2018", "10/09/2020")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);

        notificationList = new Notification[] {new Notification("Tienes una tarea pendiente para hoy! Recuerda completarla", NotificationType.REMINDER, false, "Hace 2min"), new Notification("La Dra. Mariela Boñaños te ha asignado una nueva paciente.", NotificationType.NEW_PATIENT, false, "Hace 5min"), new Notification("La Dra. Mariela Boñaños te ha asignado una nueva tarea.", NotificationType.NEW_TASK, false, "Hace 14min"), new Notification("Tienes una tarea pendiente para hoy! Recuerda completarla", NotificationType.NEW_TASK, true, "Hace 2min"), new Notification("Tienes una tarea pendiente para hoy! Recuerda completarla", NotificationType.REMINDER, true, "Hace 2min"), new Notification("Tienes una tarea pendiente para hoy! Recuerda completarla", NotificationType.REMINDER, true, "Hace 2min"), new Notification("Tienes una tarea pendiente para hoy! Recuerda completarla", NotificationType.NEW_PATIENT, true, "Hace 2min")};

        toolBarIcons = findViewById(R.id.option_icons_container);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        drawerLayout.setScrimColor(Color.parseColor("#B5BA2C75"));
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        MenuItem menuItem = navigationView.getMenu().getItem(1);
        onNavigationItemSelected(menuItem);
        menuItem.setChecked(true);

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

        getNotificationsCounter();
        notificationCounter = findViewById(R.id.notification_counter);
        notificationCounter.setText(setNotificationIcons());
        notificationRecycler = findViewById(R.id.notifications_recycler);
        readNotifications = findViewById(R.id.read_notifications);
        filterIcon = findViewById(R.id.filter_patients_icon);

        filterSelector = findViewById(R.id.filter_selector);
        container = findViewById(R.id.container);

        provinceFilterSelected = findViewById(R.id.province_filter_selected);
        cancerTypeFilterSelected = findViewById(R.id.cancer_type_filter_selected);
        treatmentFilterSelected = findViewById(R.id.treatment_filter_selected);
        genderFilterSelected = findViewById(R.id.gender_filter_selected);

        provinceFilter = findViewById(R.id.province_selector);
        cancerTypeFilter = findViewById(R.id.cancer_selector);
        treatmentFilter = findViewById(R.id.treatment_selector);
        genderFilter = findViewById(R.id.gender_selector);

        provinceClose = findViewById(R.id.province_close_button);
        cancerClose = findViewById(R.id.cancer_close_button);
        treatmentClose = findViewById(R.id.treatment_close_button);
        genderClose = findViewById(R.id.gender_close_button);

        filterButton = findViewById(R.id.filter_patients);
        filterButton.setOnClickListener(view -> {
            if (isFilterClicked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    filterButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FDFDFD")));
                    filterIcon.setImageTintList(ColorStateList.valueOf(Color.parseColor("#828282")));
                }
                filterSelector.setVisibility(View.INVISIBLE);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    filterButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5D5FEF")));
                    filterIcon.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FDFDFD")));
                }
                filterSelector.setVisibility(View.VISIBLE);
            }
            isFilterClicked = !isFilterClicked;
        });

        RecyclerView recyclerView = findViewById(R.id.patient_recycler);
        PatientAdapter patientAdapter = new PatientAdapter(patientList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(patientAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        floating_button_main.setOnClickListener(view -> {
            setVisibility(clicked);
            setClickable(clicked);
            setAnimation(clicked);
            clicked = !clicked;
        });

        floating_button_task.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewTaskActivity.class);
            startActivity(intent);
        });

        floating_button_patient.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewPatientActivity.class);
            startActivity(intent);
        });

        floating_button_tracing.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewTracingActivity.class);
            startActivity(intent);
        });

        floating_button_barrier.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewBarrierActivity.class);
            startActivity(intent);
        });

        floating_button_appointment.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewAppointmentActivity.class);
            startActivity(intent);
        });

        homeIcon.setOnClickListener(view -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        notificationsIcon.setOnClickListener(view -> {
            LinearLayout notificationsContainer = findViewById(R.id.notification_layout);
            if (!notificationOpen) {
                notificationsContainer.setVisibility(View.VISIBLE);
                notificationOpen = true;
                getNotifications(notificationList);
            } else {
                notificationsContainer.setVisibility(View.INVISIBLE);
                notificationOpen = false;
            }
            getNotificationsCounter();
            notificationCounter.setText(setNotificationIcons());
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

        provinceFilter.setOnClickListener(view -> {
            if (isProvinceSelected) {
                provinceFilterSelected.setVisibility(View.GONE);
            } else {
                provinceFilterSelected.setVisibility(View.VISIBLE);
            }
            isProvinceSelected = !isProvinceSelected;
        });

        cancerTypeFilter.setOnClickListener(view -> {
            if (isCancerSelected) {
                cancerTypeFilterSelected.setVisibility(View.GONE);
            } else {
                cancerTypeFilterSelected.setVisibility(View.VISIBLE);
            }
            isCancerSelected = !isCancerSelected;
        });

        treatmentFilter.setOnClickListener(view -> {
            if (isTreatmentSelected) {
                treatmentFilterSelected.setVisibility(View.GONE);
                if (!isGenderSelected) {
                    container.setVisibility(View.GONE);
                }
            } else {
                treatmentFilterSelected.setVisibility(View.VISIBLE);
                container.setVisibility(View.VISIBLE);
            }
            isTreatmentSelected = !isTreatmentSelected;
        });

        genderFilter.setOnClickListener(view -> {
            if (isGenderSelected) {
                genderFilterSelected.setVisibility(View.GONE);
                if (!isTreatmentSelected) {
                    container.setVisibility(View.GONE);
                }
            } else {
                genderFilterSelected.setVisibility(View.VISIBLE);
                container.setVisibility(View.VISIBLE);
            }
            isGenderSelected = !isGenderSelected;
        });

        provinceClose.setOnClickListener(view -> {
            isProvinceSelected = false;
            provinceFilter.setChecked(false);
            provinceFilterSelected.setVisibility(View.GONE);
        });

        cancerClose.setOnClickListener(view -> {
            isCancerSelected = false;
            cancerTypeFilter.setChecked(false);
            cancerTypeFilterSelected.setVisibility(View.GONE);
        });

        treatmentClose.setOnClickListener(view -> {
            isTreatmentSelected = false;
            treatmentFilter.setChecked(false);
            treatmentFilterSelected.setVisibility(View.GONE);
        });

        genderClose.setOnClickListener(view -> {
            isGenderSelected = false;
            genderFilter.setChecked(false);
            genderFilterSelected.setVisibility(View.GONE);
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
            overlay.setBackgroundResource(R.color.overlayColor);
            overlay.setVisibility(View.VISIBLE);
        } else {
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
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.patient_item:
                break;
            case R.id.settings_item:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.log_out_item:
                startActivity(new Intent(this, LoginActivity.class));
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