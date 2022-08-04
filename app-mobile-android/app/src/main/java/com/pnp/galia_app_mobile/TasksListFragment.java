package com.pnp.galia_app_mobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
        FragmentManager myFragmentManager = getFragmentManager();

        Button btnCalendar = myView.findViewById(R.id.btn_calendar_unselected);
        Button btnTasksToDo = myView.findViewById(R.id.btn_tasks_to_do_close);
        Button btnTasksComplete = myView.findViewById(R.id.btn_tasks_complete_close);
        Button btnAddTask = myView.findViewById(R.id.btn_add_new_task);
        Button listButton = myView.findViewById(R.id.btn_list_selected);
        listButton.setSelected(true);
        listButton.setBackgroundResource(R.drawable.ic_button_background);

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myFragmentManager.beginTransaction()
                        .replace(R.id.fragment_home_page, TasksCalendarFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();

            }
        });

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