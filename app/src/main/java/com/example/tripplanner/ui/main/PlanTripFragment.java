package com.example.tripplanner.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.R;

import java.util.List;

public class PlanTripFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private Button createPlan;
    private Adaptor adaptor;
    private RecyclerView recyclerView;

    public static PlanTripFragment newInstance(int index) {
        PlanTripFragment fragment = new PlanTripFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.plans_list, container, false);
        createPlan = root.findViewById(R.id.newPlan);
        createPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CreatePlan.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) root
                .findViewById(R.id.view_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        checkhere();

        return root;
    }


    public void checkhere(){
        PlanDatabase idb = PlanDatabase.get(getActivity());


        List<Plan> items = idb.getItems();

        if (adaptor == null){
            adaptor = new Adaptor(items);
            recyclerView.setAdapter(adaptor);
        }
        else{
            adaptor.setItems(items);
            adaptor.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        PlanDatabase idb = PlanDatabase.get(getActivity());
        List<Plan> items = idb.getItems();

        if (adaptor == null){
            adaptor = new Adaptor(items);
            recyclerView.setAdapter(adaptor);
        }
        else{
            adaptor.setItems(items);
            adaptor.notifyDataSetChanged();
        };
    }

}
