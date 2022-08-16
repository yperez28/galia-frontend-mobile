package com.pnp.galia_app_mobile;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public class TasksListFragment extends Fragment {
    private RecyclerView recyclerViewTaskToDo;
    private RecyclerView recyclerViewTaskComplete;

    public TasksListFragment() {
        // Required empty public constructor
    }

    public static TasksListFragment newInstance(Bundle arguments) {
        TasksListFragment fragment = new TasksListFragment();
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
        View myView = inflater.inflate(R.layout.fragment_tasks_list, container, false);

        Button btnAddTask = myView.findViewById(R.id.add_task_button);

        recyclerViewTaskToDo = myView.findViewById(R.id.task_todo_recycler);
        ImageView arrowTaskToDo = myView.findViewById(R.id.arrow_task_to_do);
        LinearLayout hiddenViewTaskToDo = myView.findViewById(R.id.hidden_task_to_do_layout);
        CardView cardViewTaskToDo = myView.findViewById(R.id.cardview_task_to_do);

        recyclerViewTaskComplete = myView.findViewById(R.id.task_complete_recycler);
        ImageView arrowTaskComplete = myView.findViewById(R.id.arrow_task_complete);
        LinearLayout hiddenViewTaskComplete = myView.findViewById(R.id.hidden_task_complete_layout);
        CardView cardViewTaskComplete = myView.findViewById(R.id.cardview_task_complete);

        arrowTaskToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hiddenViewTaskToDo.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardViewTaskToDo, new AutoTransition());
                    hiddenViewTaskToDo.setVisibility(View.GONE);
                    arrowTaskToDo.setImageResource(R.drawable.ic_vector__stroke_close);
                }
                else {
                    TransitionManager.beginDelayedTransition(cardViewTaskToDo, new AutoTransition());
                    hiddenViewTaskToDo.setVisibility(View.VISIBLE);
                    arrowTaskToDo.setImageResource(R.drawable.ic_vector__stroke_open);
                    Task[] taskList = new Task[] {new Task("1", "Tarea numero uno", "María Gutierrez L", "Preguntar sobre la situación de transporte", new Date(), "Alta", "Presencial", "Susana Cornejo"), new Task("2", "Tarea numero dos", "Sandra Castro J", "Conseguir teléfono de persona de contacto", new Date(), "Media", "Telefónico", "Andres Cornejo"), new Task("3", "Tarea numero tres", "Lucía López Ruíz", "Acompañar a quimioterapia", new Date(), "Baja", "Presencial", "Susana Cornejo")};
                    getVisibleTaskToDoView(taskList);
                }
            }
        });

        arrowTaskComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hiddenViewTaskComplete.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardViewTaskComplete, new AutoTransition());
                    hiddenViewTaskComplete.setVisibility(View.GONE);
                    arrowTaskComplete.setImageResource(R.drawable.ic_vector__stroke_close);
                }
                else {
                    TransitionManager.beginDelayedTransition(cardViewTaskComplete, new AutoTransition());
                    hiddenViewTaskComplete.setVisibility(View.VISIBLE);
                    arrowTaskComplete.setImageResource(R.drawable.ic_vector__stroke_open);
                    Task[] taskList = new Task[] {new Task("1", "Tarea numero uno", "María Gutierrez L", "Preguntar sobre la situación de transporte", new Date(), "Alta", "Presencial", "Susana Cornejo"), new Task("2", "Tarea numero dos", "Sandra Castro J", "Conseguir teléfono de persona de contacto", new Date(), "Media", "Telefónico", "Andres Cornejo"), new Task("3", "Tarea numero tres", "Lucía López Ruíz", "Acompañar a quimioterapia", new Date(), "Baja", "Presencial", "Susana Cornejo")};
                    getVisibleTaskCompleteView(taskList);
                }
            }
        });

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return myView;
    }

   private void getVisibleTaskToDoView(Task[] dayTasks) {
        if (dayTasks.length > 0) {
            recyclerViewTaskToDo.setVisibility(View.VISIBLE);
            TaskListAdapter adapter = new TaskListAdapter(dayTasks);
            recyclerViewTaskToDo.setHasFixedSize(true);
            recyclerViewTaskToDo.setAdapter(adapter);
            recyclerViewTaskToDo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerViewTaskToDo.setVisibility(View.INVISIBLE);
        }
    }

    private void getVisibleTaskCompleteView(Task[] dayTasks) {
        if (dayTasks.length > 0) {
            recyclerViewTaskComplete.setVisibility(View.VISIBLE);
            TaskListAdapter adapter = new TaskListAdapter(dayTasks);
            recyclerViewTaskComplete.setHasFixedSize(true);
            recyclerViewTaskComplete.setAdapter(adapter);
            recyclerViewTaskComplete.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerViewTaskComplete.setVisibility(View.INVISIBLE);
        }
    }
}