package com.pnp.galia_app_mobile;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TasksListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TasksListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerViewTaskToDo;
    private RecyclerView recyclerViewTaskComplete;

    public TasksListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TasksListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TasksListFragment newInstance(String param1, String param2) {
        TasksListFragment fragment = new TasksListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_tasks_list, container, false);

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