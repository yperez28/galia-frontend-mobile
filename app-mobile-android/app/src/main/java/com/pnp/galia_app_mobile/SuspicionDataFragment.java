package com.pnp.galia_app_mobile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SuspicionDataFragment extends Fragment {
    private EditText susDate;
    private Button previous;
    private Button save;
    private MaterialAutoCompleteTextView suspicionTypeLayout;
    private MaterialAutoCompleteTextView suspicionPlaceLayout;
    private RecyclerView suspicionTypeList;
    private RecyclerView suspicionPlaceList;
    private RelativeLayout popUpMessage;
    private Boolean suspicionTypeClicked = false;
    private Boolean suspicionPlaceClicked = false;
    private Boolean successfullySaved = true;

    private final String[] suspicionType = {"Autoexamen", "Control de rutina (incluye mmg y us)", "Sintomatología", "Otro"};
    private final String[] suspicionPlace = {"Charla", "Consulta externa", "En consultorio", "Oficina de navegación", "Sesión de mama", "Visita al salón", "Otro"};

    private final DropDownItemAdapter TypeAdapter = new DropDownItemAdapter(suspicionType);
    private final DropDownItemAdapter PlaceAdapter = new DropDownItemAdapter(suspicionPlace);

    public SuspicionDataFragment() {
    }

    public static SuspicionDataFragment newInstance(Bundle arguments) {
        SuspicionDataFragment frag = new SuspicionDataFragment();
        if (arguments != null) {
            frag.setArguments(arguments);
        }
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View suspicionData = inflater.inflate(R.layout.fragment_suspicion_data, container, false);
        Date currentDate = Calendar.getInstance().getTime();
        String currentDateString = (new SimpleDateFormat("MMM dd / yyyy")).format(currentDate);

        susDate = suspicionData.findViewById(R.id.susp_date_edittext);
        susDate.setText(currentDateString);
        previous = suspicionData.findViewById(R.id.suspicion_previous);
        save = suspicionData.findViewById(R.id.save);
        suspicionTypeLayout = suspicionData.findViewById(R.id.susp_reason_textview);
        suspicionPlaceLayout = suspicionData.findViewById(R.id.susp_place_textview);
        suspicionTypeList = suspicionData.findViewById(R.id.susp_reason_recycler);
        suspicionPlaceList = suspicionData.findViewById(R.id.susp_place_recycler);

        return suspicionData;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        susDate.setOnClickListener(view1 -> {
            showDatePickerDialog(susDate);
            susDate.setSelected(true);
        });

        previous.setOnClickListener(view1 -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.patient_data_fragment, ContactDataFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
            FragmentManager fragmentManager = getParentFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.patient_data_fragment);
            TextView subtitle = fragment.getView().getRootView().findViewById(R.id.fragment_subtitle);
            TextView stepCounter = fragment.getView().getRootView().findViewById(R.id.step_counter);
            ImageView thirdIcon = fragment.getView().getRootView().findViewById(R.id.third_step);
            ImageView fourthIcon = fragment.getView().getRootView().findViewById(R.id.fourth_step);
            View thirdDivider = fragment.getView().getRootView().findViewById(R.id.third_divider);
            ImageView thirdIconC = fragment.getView().getRootView().findViewById(R.id.third_step_complete);
            subtitle.setText("Contacto y dirección");
            stepCounter.setText("Paso 3 de 4");
            thirdIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_stepbar_incomplete_step));
            fourthIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_stepbar_inactive_step));
            thirdDivider.setBackgroundColor(Color.parseColor("#A5A6F6"));
            thirdIconC.setVisibility(View.INVISIBLE);
        });

        suspicionTypeLayout.setOnClickListener(view13 -> {
            suspicionTypeClicked = !suspicionTypeClicked;
            if (!suspicionTypeClicked) {
                suspicionTypeList.setVisibility(View.VISIBLE);
                suspicionTypeList.setSelected(true);
                suspicionTypeLayout.setSelected(true);
                suspicionTypeList.setHasFixedSize(true);
                suspicionTypeList.setAdapter(TypeAdapter);
                suspicionTypeList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                TypeAdapter.setCurrentLayout(suspicionTypeLayout);
                TypeAdapter.setCurrentOptionsList(suspicionTypeList);
            } else {
                suspicionTypeList.setVisibility(View.GONE);
                suspicionTypeLayout.setSelected(false);
            }
        });

        suspicionPlaceLayout.setOnClickListener(view14 -> {
            suspicionPlaceClicked = !suspicionPlaceClicked;
            if (!suspicionPlaceClicked) {
                suspicionPlaceList.setVisibility(View.VISIBLE);
                suspicionPlaceList.setSelected(true);
                suspicionPlaceLayout.setSelected(true);
                suspicionPlaceList.setHasFixedSize(true);
                suspicionPlaceList.setAdapter(PlaceAdapter);
                suspicionPlaceList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                PlaceAdapter.setCurrentLayout(suspicionPlaceLayout);
                PlaceAdapter.setCurrentOptionsList(suspicionPlaceList);
            } else {
                suspicionPlaceList.setVisibility(View.GONE);
                suspicionPlaceLayout.setSelected(false);
            }
        });

        save.setOnClickListener(view12 -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.patient_data_fragment);
            successfullySaved = !successfullySaved;
            if (successfullySaved) {
                popUpMessage = fragment.getView().getRootView().findViewById(R.id.popup_success);
            } else {
                popUpMessage = fragment.getView().getRootView().findViewById(R.id.popup_failure);
            }
            popUpMessage.setVisibility(View.VISIBLE);
        });
    }

    private void showDatePickerDialog(EditText editText) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = (month + 1) + "/" + day + "/" + year;
            Date date = new Date();
            try {
                date = new SimpleDateFormat("MM/dd/yyyy").parse(selectedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String formattedSelectedDate = (new SimpleDateFormat("MMM dd / yyyy")).format(date);
            editText.setText(formattedSelectedDate);
            editText.setSelected(false);
            editText.setTextColor(Color.parseColor("#4F4F4F"));
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
}
