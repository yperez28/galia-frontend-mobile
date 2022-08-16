package com.pnp.galia_app_mobile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class ContactDataFragment extends Fragment {
    private Button next_button;
    private Button addSecondContact;
    private Button previous_button;
    private RelativeLayout secondContact;

    private MaterialAutoCompleteTextView provinceLayout;
    private MaterialAutoCompleteTextView cantonLayout;
    private MaterialAutoCompleteTextView contactOneName;
    private MaterialAutoCompleteTextView contactTwoName;

    private RecyclerView provinceList;
    private RecyclerView cantonList;
    private RecyclerView contactOneList;
    private RecyclerView contactTwoList;

    private Boolean provinceClicked = false;
    private Boolean cantonClicked = false;
    private Boolean contactOneClicked = false;
    private Boolean contactTwoClicked = false;

    private final String[] province = {"Alajuela", "Cartago", "Guanacaste", "Heredia", "Limón", "Puntarenas", "San José"};
    private final String[] canton = {"Alajuela Central", "Atenas", "Grecia", "Guatuso", "Los Chiles", "Naranjo", "Oreamuno"};
    private final String[] contactRelationship = {"Esposo / Esposa", "Papá / Mamá", "Hijo / Hija", "Hermano / Hermana", "Nieto / Nieta", "Sobrino / Sobrina", "Cuñado / Cuñada", "Yerno / Nuera", "Vecino (a) / Amigo (a)", "Otro"};

    private final DropDownItemAdapter ProvinceAdapter = new DropDownItemAdapter(province);
    private final DropDownItemAdapter CantonAdapter = new DropDownItemAdapter(canton);
    private final DropDownItemAdapter ContactOneAdapter = new DropDownItemAdapter(contactRelationship);
    private final DropDownItemAdapter ContactTwoAdapter = new DropDownItemAdapter(contactRelationship);

    public ContactDataFragment() {
    }

    public static ContactDataFragment newInstance(Bundle arguments) {
        ContactDataFragment frag = new ContactDataFragment();
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
        View contactData = inflater.inflate(R.layout.fragment_contact_data, container, false);

        next_button = contactData.findViewById(R.id.contact_next);
        addSecondContact = contactData.findViewById(R.id.add_contact_button);
        secondContact = contactData.findViewById(R.id.second_contact_container);
        previous_button = contactData.findViewById(R.id.contact_previous);
        provinceLayout = contactData.findViewById(R.id.province_edittext);
        cantonLayout = contactData.findViewById(R.id.canton_edittext);
        contactOneName = contactData.findViewById(R.id.relationship_edittext);
        contactTwoName = contactData.findViewById(R.id.second_relationship_edittext);
        provinceList = contactData.findViewById(R.id.province_recycler);
        cantonList= contactData.findViewById(R.id.canton_recycler);
        contactOneList = contactData.findViewById(R.id.relationship_recycler);
        contactTwoList = contactData.findViewById(R.id.second_relationship_recycler);

        return contactData;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = getParentFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.patient_data_fragment);
        TextView contactSubtitle = fragment.getView().getRootView().findViewById(R.id.fragment_subtitle);
        TextView stepCounter = fragment.getView().getRootView().findViewById(R.id.step_counter);

        next_button.setOnClickListener(view12 -> { getParentFragmentManager().beginTransaction()
                .replace(R.id.patient_data_fragment, SuspicionDataFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();

            contactSubtitle.setText("Sospecha y citas");
            ImageView fourthIcon = fragment.getView().getRootView().findViewById(R.id.fourth_step);
            fourthIcon.setActivated(true);
            ImageView thirdIcon = fragment.getView().getRootView().findViewById(R.id.third_step_complete);
            thirdIcon.setVisibility(View.VISIBLE);
            View thirdDivider = fragment.getView().getRootView().findViewById(R.id.third_divider);
            thirdDivider.setBackgroundColor(Color.parseColor("#5D5FEF"));
        });

        previous_button.setOnClickListener(view1 -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.patient_data_fragment, DemographicDataFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();

            contactSubtitle.setText("Datos demográficos");
            stepCounter.setText("Paso 2 de 4");
            ImageView secondIcon = fragment.getView().getRootView().findViewById(R.id.second_step);
            ImageView secondIconC = fragment.getView().getRootView().findViewById(R.id.second_step_complete);
            View secondDivider = fragment.getView().getRootView().findViewById(R.id.second_divider);
            ImageView thirdIcon = fragment.getView().getRootView().findViewById(R.id.third_step);
            secondIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_stepbar_incomplete_step));
            thirdIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_stepbar_inactive_step));
            secondIconC.setVisibility(View.INVISIBLE);
            secondDivider.setBackgroundColor(Color.parseColor("#A5A6F6"));
        });

        addSecondContact.setOnClickListener(view1 -> secondContact.setVisibility(View.VISIBLE));

        provinceLayout.setOnClickListener(view13 -> {
            provinceClicked = !provinceClicked;
            if (!provinceClicked) {
                provinceList.setVisibility(View.VISIBLE);
                provinceList.setSelected(true);
                provinceLayout.setSelected(true);
                provinceList.setHasFixedSize(true);
                provinceList.setAdapter(ProvinceAdapter);
                provinceList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                ProvinceAdapter.setCurrentLayout(provinceLayout);
                ProvinceAdapter.setCurrentOptionsList(provinceList);
            } else {
                provinceList.setVisibility(View.GONE);
                provinceLayout.setSelected(false);
            }
        });

        cantonLayout.setOnClickListener(view13 -> {
            cantonClicked = !cantonClicked;
            if (!cantonClicked) {
                cantonList.setVisibility(View.VISIBLE);
                cantonList.setSelected(true);
                cantonLayout.setSelected(true);
                cantonList.setHasFixedSize(true);
                cantonList.setAdapter(CantonAdapter);
                cantonList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                CantonAdapter.setCurrentLayout(cantonLayout);
                CantonAdapter.setCurrentOptionsList(cantonList);
            } else {
                cantonList.setVisibility(View.GONE);
                cantonLayout.setSelected(false);
            }
        });

        contactOneName.setOnClickListener(view13 -> {
            contactOneClicked = !contactOneClicked;
            if (!contactOneClicked) {
                contactOneList.setVisibility(View.VISIBLE);
                contactOneList.setSelected(true);
                contactOneName.setSelected(true);
                contactOneList.setHasFixedSize(true);
                contactOneList.setAdapter(ContactOneAdapter);
                contactOneList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                ContactOneAdapter.setCurrentLayout(contactOneName);
                ContactOneAdapter.setCurrentOptionsList(contactOneList);
            } else {
                contactOneList.setVisibility(View.GONE);
                contactOneName.setSelected(false);
            }
        });

        contactTwoName.setOnClickListener(view13 -> {
            contactTwoClicked = !contactTwoClicked;
            if (!contactTwoClicked) {
                contactTwoList.setVisibility(View.VISIBLE);
                contactTwoList.setSelected(true);
                contactTwoName.setSelected(true);
                contactTwoList.setHasFixedSize(true);
                contactTwoList.setAdapter(ContactTwoAdapter);
                contactTwoList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                ContactTwoAdapter.setCurrentLayout(contactTwoName);
                ContactTwoAdapter.setCurrentOptionsList(contactTwoList);
            } else {
                contactTwoList.setVisibility(View.GONE);
                contactTwoName.setSelected(false);
            }
        });
    }
}
