package com.pnp.galia_app_mobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class TasksListFragment extends Fragment {

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
        FragmentManager myFragmentManager = getFragmentManager();

        Button btnTasksToDo = myView.findViewById(R.id.btn_tasks_to_do_close);
        Button btnTasksComplete = myView.findViewById(R.id.btn_tasks_complete_close);
        Button btnAddTask = myView.findViewById(R.id.btn_add_new_task);

        btnTasksToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"TASKS TO DO BUTTON CLICKED",Toast.LENGTH_SHORT).show();

            }
        });

        btnTasksComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"TASKS COMPLETE BUTTON CLICKED",Toast.LENGTH_SHORT).show();

            }
        });

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"ADD TASK BUTTON CLICKED",Toast.LENGTH_SHORT).show();

            }
        });
        return myView;
    }
}