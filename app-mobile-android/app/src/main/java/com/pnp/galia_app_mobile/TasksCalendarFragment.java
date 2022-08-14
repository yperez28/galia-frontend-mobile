package com.pnp.galia_app_mobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.Date;
import java.util.Locale;

public class TasksCalendarFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEEE d", new Locale("es", "ES"));
    private RecyclerView recyclerView;
    private RelativeLayout eventText;

    private MaterialCalendarView calendar;

    public TasksCalendarFragment() {
        // Required empty public constructor
    }

    public static TasksCalendarFragment newInstance(Bundle arguments) {
        TasksCalendarFragment fragment = new TasksCalendarFragment();
        if (arguments != null) {
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_tasks_calendar, container, false);
        FragmentManager myFragmentManager = getFragmentManager();

        LinearLayout header = myView.findViewById(com.prolificinteractive.materialcalendarview.R.id.header);
        header.setBackgroundResource(R.drawable.ic_top_calendar);
        header.setPadding(120,0,120,0);

        calendar = myView.findViewById(R.id.calendarView);
        TextView selectedDay = myView.findViewById(R.id.selected_day);
        RelativeLayout addTaskHolder = myView.findViewById(R.id.add_task);
        Button addTaskButton = myView.findViewById(R.id.add_task_button);
        recyclerView = myView.findViewById(R.id.task_recycler);
        eventText = myView.findViewById(R.id.no_events);

        final LocalDate instance = LocalDate.now();
        calendar.setSelectedDate(instance);
        calendar.setOnDateChangedListener((widget, date, selected) -> {
            String day = FORMATTER.format(date.getDate());
            String finalDay = day.substring(0, 1).toUpperCase() + day.substring(1);

            selectedDay.setText(selected ? finalDay : "Seleccione una fecha");

            addTaskHolder.setVisibility(View.VISIBLE);

            Task[] taskList = new Task[] {new Task("1", "Tarea numero uno", "María Gutierrez L", "Preguntar sobre la situación de transporte", new Date(), "Alta", "Presencial", "Susana Cornejo"), new Task("2", "Tarea numero dos", "Sandra Castro J", "Conseguir teléfono de persona de contacto", new Date(), "Media", "Telefónico", "Andres Cornejo"), new Task("3", "Tarea numero tres", "Lucía López Ruíz", "Acompañar a quimioterapia", new Date(), "Baja", "Presencial", "Susana Cornejo")};
            getVisibleView(taskList);
        });

        addTaskButton.setOnClickListener(view -> {
            String message = "Adding new task...";
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        });

        return myView;
    }

    private void getVisibleView(Task[] dayTasks) {
        if (dayTasks.length > 0) {
            final float scale = getContext().getResources().getDisplayMetrics().density;
            int multiTask = (int) (75 * scale + 0.5f);
            int oneTask = (int) (115 * scale + 0.5f);
            if (dayTasks.length == 1) {
                recyclerView.getLayoutParams().height = multiTask;
            }
            else {
                recyclerView.getLayoutParams().height = oneTask;
            }
            eventText.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            TaskAdapter adapter = new TaskAdapter(dayTasks);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setVisibility(View.INVISIBLE);
            eventText.setVisibility(View.VISIBLE);
        }
    }
}