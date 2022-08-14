package com.pnp.galia_app_mobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class BarriersExternFragment extends Fragment {

    public BarriersExternFragment() {
        // Required empty public constructor
    }

    public static BarriersExternFragment newInstance(Bundle arguments) {
        BarriersExternFragment fragment = new BarriersExternFragment();
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

        return inflater.inflate(R.layout.fragment_barriers_extern, container, false);
    }
}