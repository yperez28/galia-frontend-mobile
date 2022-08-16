package com.pnp.galia_app_mobile;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BasicDataFragment extends Fragment {
    private Button next_button;

    private EditText joinDate;
    private EditText name;
    private EditText firstLastname;
    private EditText secondLastname;
    private EditText id;
    private EditText cellphone;
    private EditText phone;
    private EditText birthDate;

    private Boolean nameFilled = false;
    private Boolean cellphoneFilled = false;

    public BasicDataFragment() {
    }

    public static BasicDataFragment newInstance(Bundle arguments) {
        BasicDataFragment frag = new BasicDataFragment();
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
        View basicData = inflater.inflate(R.layout.fragment_basic_data, container, false);
        Date currentDate = Calendar.getInstance().getTime();
        String currentDateString = (new SimpleDateFormat("MMM dd / yyyy")).format(currentDate);

        next_button = basicData.findViewById(R.id.next);
        joinDate = basicData.findViewById(R.id.join_date_edittext);
        name = basicData.findViewById(R.id.patient_name_edittext);
        firstLastname = basicData.findViewById(R.id.first_lastname_edittext);
        secondLastname = basicData.findViewById(R.id.second_lastname_edittext);
        id = basicData.findViewById(R.id.patient_id_edittext);
        cellphone = basicData.findViewById(R.id.patient_cellphone_edittext);
        phone = basicData.findViewById(R.id.phone_number_edittext);
        birthDate = basicData.findViewById(R.id.birth_date_edittext);

        birthDate.setText(currentDateString);
        joinDate.setText(currentDateString);

        return basicData;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        joinDate.setOnClickListener(view1 -> {
            showDatePickerDialog(joinDate);
            joinDate.setSelected(true);
        });
        birthDate.setOnClickListener(view1 -> {
            showDatePickerDialog(birthDate);
            birthDate.setSelected(true);
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("beforeTextChanged", "not override");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (name.length() > 0) {
                    nameFilled = true;
                    if (cellphoneFilled) {
                        next_button.setEnabled(true);
                    }
                } else {
                    nameFilled = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("afterTextChanged", "not override");
            }
        });
        cellphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("beforeTextChanged", "not override");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (cellphone.length() > 0) {
                    cellphoneFilled = true;
                    if (nameFilled) {
                        next_button.setEnabled(true);
                    }
                } else {
                    cellphoneFilled = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("afterTextChanged", "not override");
            }
        });
        next_button.setOnClickListener(view12 -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.patient_data_fragment, DemographicDataFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
            FragmentManager fragmentManager = getParentFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.patient_data_fragment);
            TextView basicDataSubtitle = fragment.getView().getRootView().findViewById(R.id.fragment_subtitle);
            basicDataSubtitle.setText("Datos demográficos");
            TextView stepCounter = fragment.getView().getRootView().findViewById(R.id.step_counter);
            stepCounter.setText("Paso 2 de 4");
            ImageView firstIcon = fragment.getView().getRootView().findViewById(R.id.first_step_complete);
            firstIcon.setVisibility(View.VISIBLE);
            ImageView secondIcon = fragment.getView().getRootView().findViewById(R.id.second_step);
            secondIcon.setActivated(true);
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
