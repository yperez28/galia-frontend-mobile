package com.pnp.galia_app_mobile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class DemographicDataFragment extends Fragment {

    private Button next_button;
    private Button previous_button;

    private MaterialAutoCompleteTextView civilStatusLayout;
    private MaterialAutoCompleteTextView healthCareLayout;
    private MaterialAutoCompleteTextView scholarshipLayout;
    private MaterialAutoCompleteTextView occupationLayout;
    private MaterialAutoCompleteTextView insuranceTypeLayout;

    private RecyclerView civilStatusList;
    private RecyclerView healthCareList;
    private RecyclerView scholarshipList;
    private RecyclerView occupationList;
    private RecyclerView insuranceTypeList;

    private Boolean civilStatusClicked = false;
    private Boolean healthCareClicked = false;
    private Boolean scholarshipClicked = false;
    private Boolean occupationClicked = false;
    private Boolean insuranceTypeClicked = false;

    private final String[] civilStatus = {"Casada", "Divorciada", "Separada", "Soltera", "Unión Libre", "Viuda"};
    private final String[] healthCare = {"Alajuela Central", "Atenas", "Grecia", "Guatuso", "Los Chiles", "Naranjo", "Oreamuno"};
    private final String[] scholarship = {"Primaria completa", "Primaria incompleta", "Secundaria completa", "Secundaria incompleta", "Sin estudios", "Técnico", "Universidad completa", "Universidad incompleta"};
    private final String[] occupation = {"Ama de casa", "Estudiante", "Pensionada", "Trabajadora formal", "Trabajadora informal"};
    private final String[] insuranceType = {"Directo", "Estado", "Familiar", "Pensionada", "Voluntario"};

    private final DropDownItemAdapter CivilStatusAdapter = new DropDownItemAdapter(civilStatus);
    private final DropDownItemAdapter HealthCareAdapter = new DropDownItemAdapter(healthCare);
    private final DropDownItemAdapter ScholarshipAdapter = new DropDownItemAdapter(scholarship);
    private final DropDownItemAdapter OccupationAdapter = new DropDownItemAdapter(occupation);
    private final DropDownItemAdapter InsuranceTypeAdapter = new DropDownItemAdapter(insuranceType);

    public DemographicDataFragment() {
    }

    public static DemographicDataFragment newInstance(Bundle arguments) {
        DemographicDataFragment frag = new DemographicDataFragment();
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
        View demographicsData = inflater.inflate(R.layout.fragment_demographic_data, container, false);

        next_button = demographicsData.findViewById(R.id.demographics_next);
        previous_button = demographicsData.findViewById(R.id.demographics_previous);
        civilStatusLayout = demographicsData.findViewById(R.id.civil_status_edittext);
        healthCareLayout = demographicsData.findViewById(R.id.health_center_edittext);
        scholarshipLayout = demographicsData.findViewById(R.id.schoolarship_edittext);
        occupationLayout = demographicsData.findViewById(R.id.occupation_edittext);
        insuranceTypeLayout = demographicsData.findViewById(R.id.insurance_type_edittext);
        civilStatusList = demographicsData.findViewById(R.id.civil_status_recycler);
        healthCareList = demographicsData.findViewById(R.id.health_center_recycler);
        scholarshipList = demographicsData.findViewById(R.id.schoolarship_recycler);
        occupationList = demographicsData.findViewById(R.id.occupation_recycler);
        insuranceTypeList = demographicsData.findViewById(R.id.insurance_type_recycler);

        return demographicsData;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = getParentFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.patient_data_fragment);
        TextView demographicsSubtitle = fragment.getView().getRootView().findViewById(R.id.fragment_subtitle);
        TextView stepCounter = fragment.getView().getRootView().findViewById(R.id.step_counter);

        next_button.setOnClickListener(view1 -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.patient_data_fragment, ContactDataFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();

            demographicsSubtitle.setText("Contacto y dirección");
            stepCounter.setText("Paso 3 de 4");
            ImageView thirdIcon = fragment.getView().getRootView().findViewById(R.id.third_step);
            View secondDivider = fragment.getView().getRootView().findViewById(R.id.second_divider);
            ImageView secondIcon = fragment.getView().getRootView().findViewById(R.id.second_step_complete);
            thirdIcon.setActivated(true);
            secondDivider.setBackgroundColor(Color.parseColor("#5D5FEF"));
            secondIcon.setVisibility(View.VISIBLE);
        });

        previous_button.setOnClickListener(view1 -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.patient_data_fragment, BasicDataFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();

            demographicsSubtitle.setText("Datos básicos");
            stepCounter.setText("Paso 1 de 4");
            ImageView firstIcon = fragment.getView().getRootView().findViewById(R.id.first_step);
            ImageView secondIcon = fragment.getView().getRootView().findViewById(R.id.second_step);
            ImageView firstIconC = fragment.getView().getRootView().findViewById(R.id.first_step_complete);
            firstIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_stepbar_incomplete_step));
            secondIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_stepbar_inactive_step));
            firstIconC.setVisibility(View.INVISIBLE);
        });

        civilStatusLayout.setOnClickListener(view13 -> {
            civilStatusClicked = !civilStatusClicked;
            if (!civilStatusClicked) {
                civilStatusList.setVisibility(View.VISIBLE);
                civilStatusList.setSelected(true);
                civilStatusLayout.setSelected(true);
                civilStatusList.setHasFixedSize(true);
                civilStatusList.setAdapter(CivilStatusAdapter);
                civilStatusList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                CivilStatusAdapter.setCurrentLayout(civilStatusLayout);
                CivilStatusAdapter.setCurrentOptionsList(civilStatusList);
            } else {
                civilStatusList.setVisibility(View.GONE);
                civilStatusLayout.setSelected(false);
            }
        });

        healthCareLayout.setOnClickListener(view13 -> {
            healthCareClicked = !healthCareClicked;
            if (!healthCareClicked) {
                healthCareList.setVisibility(View.VISIBLE);
                healthCareList.setSelected(true);
                healthCareLayout.setSelected(true);
                healthCareList.setHasFixedSize(true);
                healthCareList.setAdapter(HealthCareAdapter);
                healthCareList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                HealthCareAdapter.setCurrentLayout(healthCareLayout);
                HealthCareAdapter.setCurrentOptionsList(healthCareList);
            } else {
                healthCareList.setVisibility(View.GONE);
                healthCareLayout.setSelected(false);
            }
        });

        scholarshipLayout.setOnClickListener(view13 -> {
            scholarshipClicked = !scholarshipClicked;
            if (!scholarshipClicked) {
                scholarshipList.setVisibility(View.VISIBLE);
                scholarshipList.setSelected(true);
                scholarshipLayout.setSelected(true);
                scholarshipList.setHasFixedSize(true);
                scholarshipList.setAdapter(ScholarshipAdapter);
                scholarshipList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                ScholarshipAdapter.setCurrentLayout(scholarshipLayout);
                ScholarshipAdapter.setCurrentOptionsList(scholarshipList);
            } else {
                scholarshipList.setVisibility(View.GONE);
                scholarshipLayout.setSelected(false);
            }
        });

        occupationLayout.setOnClickListener(view13 -> {
            occupationClicked = !occupationClicked;
            if (!occupationClicked) {
                occupationList.setVisibility(View.VISIBLE);
                occupationList.setSelected(true);
                occupationLayout.setSelected(true);
                occupationList.setHasFixedSize(true);
                occupationList.setAdapter(CivilStatusAdapter);
                occupationList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                CivilStatusAdapter.setCurrentLayout(occupationLayout);
                CivilStatusAdapter.setCurrentOptionsList(occupationList);
            } else {
                occupationList.setVisibility(View.GONE);
                occupationLayout.setSelected(false);
            }
        });

        insuranceTypeLayout.setOnClickListener(view13 -> {
            insuranceTypeClicked = !insuranceTypeClicked;
            if (!insuranceTypeClicked) {
                insuranceTypeList.setVisibility(View.VISIBLE);
                insuranceTypeList.setSelected(true);
                insuranceTypeLayout.setSelected(true);
                insuranceTypeList.setHasFixedSize(true);
                insuranceTypeList.setAdapter(InsuranceTypeAdapter);
                insuranceTypeList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                InsuranceTypeAdapter.setCurrentLayout(insuranceTypeLayout);
                InsuranceTypeAdapter.setCurrentOptionsList(insuranceTypeList);
            } else {
                insuranceTypeList.setVisibility(View.GONE);
                insuranceTypeLayout.setSelected(false);
            }
        });
    }
}
