package com.codiansoft.baxcetproject.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codiansoft.baxcetproject.Adapters.SchuduleOrdersAdapter;
import com.codiansoft.baxcetproject.Models.SchuduleOrderModel;
import com.codiansoft.baxcetproject.R;

import java.util.ArrayList;
import java.util.List;


public class ScheduleOrdersFragment extends Fragment {


    RecyclerView recyclerView;
    SchuduleOrdersAdapter adapter;
    List<SchuduleOrderModel> list=new ArrayList<>();
    Activity activity;

    public ScheduleOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_schedule_orders, container, false);

        activity=getActivity();
        Log.e("orders" , "schudule orders");
        recyclerView=(RecyclerView)view.findViewById(R.id.recycleView);

        adapter = new SchuduleOrdersAdapter(list,activity);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);
        loadData();

        return view;
    }
    public void loadData()
    {
        for(int i=0 ; i < 10 ; i++)
        {
            SchuduleOrderModel model=new SchuduleOrderModel();
            list.add(model);
        }
        adapter.notifyDataSetChanged();
    }

}
