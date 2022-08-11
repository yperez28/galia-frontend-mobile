package com.pnp.galia_app_mobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BarriersAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BarriersAllFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;

    public BarriersAllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BarriersAllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BarriersAllFragment newInstance(String param1, String param2) {
        BarriersAllFragment fragment = new BarriersAllFragment();
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
        View myView = inflater.inflate(R.layout.fragment_barriers_all, container, false);
        recyclerView = myView.findViewById(R.id.barrier_all_recycler);

        Barrier[] barrierList = new Barrier[] {new Barrier("1", "Ausencia de redes de apoyo", "B02", "Maria Gutierrez L", "Barrera numero uno", "Sociocultural", "Media", "Interna"), new Barrier("2", "Religión", "B07", "Lucía Brenes J", "Barrera numero dos", "Personal", "Alta", "Interna"), new Barrier("3", "Desestimación del caso", "B23", "Yolanda Rojas", "Barrera numero tres", "Serv.Salud", "Alta", "Externa"), new Barrier("4", "Variaciones del personal médico", "B27", "Miriam Ureña P", "Barrera numero cuatro", "Serv.Salud", "Baja", "Externa")};
        getVisibleView(barrierList);
        return myView;
    }

    private void getVisibleView(Barrier[] listBarriers) {
        if (listBarriers.length > 0) {
            /*final float scale = getContext().getResources().getDisplayMetrics().density;
            int multiTask = (int) (75 * scale + 0.5f);
            int oneTask = (int) (156 * scale + 0.5f);
            if (listBarriers.length == 1) {
                recyclerView.getLayoutParams().height = multiTask;
            }
            else {
                recyclerView.getLayoutParams().height = oneTask;
            }*/
            BarrierAdapter adapter = new BarrierAdapter(listBarriers);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }
}