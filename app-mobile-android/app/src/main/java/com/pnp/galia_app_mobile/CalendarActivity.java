package com.pnp.galia_app_mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CalendarActivity extends AppCompatActivity implements OnDateSelectedListener {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEEE d", new Locale("es", "ES"));

    @BindView(R.id.calendarView)
    MaterialCalendarView calendar;
    @BindView(R.id.selected_day)
    TextView selectedDay;
    @BindView(R.id.add_task)
    RelativeLayout addTaskHolder;
    @BindView(R.id.add_task_button)
    Button addTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);

        LinearLayout header = findViewById(com.prolificinteractive.materialcalendarview.R.id.header);
        header.setBackgroundResource(R.drawable.ic_top_calendar);
        header.setPadding(120,0,120,0);

        final LocalDate instance = LocalDate.now();
        calendar.setSelectedDate(instance);
        calendar.setOnDateChangedListener(this);

        addTaskButton.setOnClickListener(view -> {
            String message = "Adding new task...";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onDateSelected(
            @NonNull MaterialCalendarView widget,
            @NonNull CalendarDay date,
            boolean selected) {
        String day = FORMATTER.format(date.getDate());
        String finalDay = day.substring(0, 1).toUpperCase() + day.substring(1);

        selectedDay.setText(selected ? finalDay : "Seleccione una fecha");

        addTaskHolder.setVisibility(View.VISIBLE);

        Task[] taskList = new Task[] {new Task("1", "Tarea numero uno", "María Gutierrez L", "Preguntar sobre la situación de transporte", new Date(), "Alta", "Presencial", "Susana Cornejo"), new Task("2", "Tarea numero dos", "Sandra Castro J", "Conseguir teléfono de persona de contacto", new Date(), "Media", "Telefónico", "Andres Cornejo"), new Task("3", "Tarea numero tres", "Lucía López Ruíz", "Acompañar a quimioterapia", new Date(), "Baja", "Presencial", "Susana Cornejo")};
        getVisibleView(taskList);

    }

    private void getVisibleView(Task[] dayTasks) {
        if (dayTasks.length > 0) {
            RecyclerView recyclerView = findViewById(R.id.task_recycler);
            recyclerView.setVisibility(View.VISIBLE);
            TaskAdapter adapter = new TaskAdapter(dayTasks);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            RelativeLayout eventText = findViewById(R.id.no_events);
            eventText.setVisibility(View.VISIBLE);
        }
    }
}








