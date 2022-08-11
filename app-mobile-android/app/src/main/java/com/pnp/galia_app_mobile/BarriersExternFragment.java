package com.pnp.galia_app_mobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BarriersExternFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BarriersExternFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;

    public BarriersExternFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BarriersExternFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BarriersExternFragment newInstance(String param1, String param2) {
        BarriersExternFragment fragment = new BarriersExternFragment();
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
        View myView = inflater.inflate(R.layout.fragment_barriers_extern, container, false);
        recyclerView = myView.findViewById(R.id.barrier_extern_recycler);

        Barrier[] barrierList = new Barrier[] {new Barrier("1", "Ausencia de redes de apoyo", "B02", "Maria Gutierrez L", "Barrera numero uno", "Sociocultural", "Media", "Interna"), new Barrier("2", "Religión", "B07", "Lucía Brenes J", "Barrera numero dos", "Personal", "Alta", "Interna"), new Barrier("3", "Desestimación del caso", "B23", "Yolanda Rojas", "Barrera numero tres", "Serv.Salud", "Alta", "Externa"), new Barrier("4", "Variaciones del personal médico", "B27", "Miriam Ureña P", "Barrera numero cuatro", "Serv.Salud", "Baja", "Externa")};
        getVisibleView(barrierList);

        return myView;
    }

    private void getVisibleView(Barrier[] listBarriers) {
        Barrier[] myFilterlistBarriers = filterBarriers(listBarriers);
        if (myFilterlistBarriers.length > 0) {
            BarrierAdapter adapter = new BarrierAdapter(myFilterlistBarriers);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }

    private Barrier[] filterBarriers(Barrier[] listBarriers){
        int numElements = listBarriers.length;
        int counter=0;
        int counter2=0;
        String typeBarrier;
        int numBarriers=0;
        Barrier myBarrier;

        //Calcular numero de elementos de barreras externas
        while (counter < numElements) {
            typeBarrier = listBarriers[counter].getType();
            if (Objects.equals(typeBarrier, "Externa")) {
                numBarriers = numBarriers + 1;
            }
            counter = counter + 1;
        }

        Barrier[] myFilterList = new Barrier[numBarriers];
        counter = 0;
        while (counter < numElements) {
            myBarrier = listBarriers[counter];
            typeBarrier = listBarriers[counter].getType();

            if (Objects.equals(typeBarrier, "Externa")) {
                myFilterList[counter2] = new Barrier(myBarrier.getId(),
                        myBarrier.getName(), myBarrier.getCode(),
                        myBarrier.getPatient(), myBarrier.getDescription(),
                        myBarrier.getClasification(), myBarrier.getPriority(),
                        myBarrier.getType());
                counter2=counter2+1;
            }
            counter = counter+1;
        }

        return myFilterList;
    }
}