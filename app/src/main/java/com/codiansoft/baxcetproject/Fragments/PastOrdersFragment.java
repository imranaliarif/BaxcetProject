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

import com.codiansoft.baxcetproject.Adapters.PastOrderAdapter;
import com.codiansoft.baxcetproject.Models.PastOrderModel;
import com.codiansoft.baxcetproject.R;

import java.util.ArrayList;
import java.util.List;


public class PastOrdersFragment extends Fragment {


    RecyclerView recyclerView;
    PastOrderAdapter adapter;
    List<PastOrderModel> list=new ArrayList<>();
    Activity activity;

    public PastOrdersFragment() {
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
        View view= inflater.inflate(R.layout.fragment_past_orders, container, false);
        Log.e("orders" , "past orders");
        activity=getActivity();

        recyclerView=(RecyclerView)view.findViewById(R.id.recycleView);

        adapter = new PastOrderAdapter(list,activity);
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
            PastOrderModel model=new PastOrderModel();
            list.add(model);
        }
        adapter.notifyDataSetChanged();
    }


}
