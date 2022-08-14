package com.pnp.galia_app_mobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BarriersAllFragment extends Fragment {

    private RecyclerView recyclerView;

    public BarriersAllFragment() {
        // Required empty public constructor
    }

    public static BarriersAllFragment newInstance(Bundle arguments) {
        BarriersAllFragment fragment = new BarriersAllFragment();
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
        View myView = inflater.inflate(R.layout.fragment_barriers_all, container, false);
        recyclerView = myView.findViewById(R.id.barrier_recycler);

        Barrier[] barrierList = new Barrier[] {new Barrier("1", "Ausencia de redes de apoyo", "B02", "Maria Gutierrez L", "Barrera numero uno", "Sociocultural", "Media", "Interna"), new Barrier("2", "Religión", "B07", "Lucía Brenes J", "Barrera numero dos", "Personal", "Alta", "Interna"), new Barrier("3", "Desestimación del caso", "B23", "Yolanda Rojas", "Barrera numero tres", "Serv.Salud", "Alta", "Externa"), new Barrier("4", "Variaciones del personal médico", "B27", "Miriam Ureña P", "Barrera numero cuatro", "Serv.Salud", "Baja", "Externa")};
        getVisibleView(barrierList);
        return myView;
    }

    private void getVisibleView(Barrier[] listBarriers) {
        if (listBarriers.length > 0) {
            final float scale = getContext().getResources().getDisplayMetrics().density;
            int multiTask = (int) (75 * scale + 0.5f);
            int oneTask = (int) (156 * scale + 0.5f);
            if (listBarriers.length == 1) {
                recyclerView.getLayoutParams().height = multiTask;
            }
            else {
                recyclerView.getLayoutParams().height = oneTask;
            }
            BarrierAdapter adapter = new BarrierAdapter(listBarriers);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }
}