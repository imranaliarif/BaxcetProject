package com.codiansoft.baxcetproject.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.codiansoft.baxcetproject.CustomClasses.GlobalClass;
import com.codiansoft.baxcetproject.R;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;


public class DashBoardFragment extends Fragment {


    LinearLayout grocery_and_bakery;
    LinearLayout fruits_and_vegetables;
    LinearLayout medicine;
    LinearLayout contacts;
    LinearLayout shopping;
    LinearLayout recent;
    Activity activity;
    private SliderLayout mDemoSlider;

    public DashBoardFragment() {
        // Required empty public constructor
    }


    public static DashBoardFragment newInstance(String param1, String param2) {
        DashBoardFragment fragment = new DashBoardFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        activity = getActivity();
        grocery_and_bakery = view.findViewById(R.id.grocery_and_bakery);
        fruits_and_vegetables = view.findViewById(R.id.fruits_and_vegetables);
        medicine = view.findViewById(R.id.medicine);
        contacts = view.findViewById(R.id.contacts);
        shopping = view.findViewById(R.id.shopping);
        recent = view.findViewById(R.id.recent);

        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);


        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("why to waste time in Marts ?", R.drawable.dashboard_slide_image1);
        file_maps.put("when you can have items at your doorstep", R.drawable.dashboard_slide_image2);


        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(activity);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }


            grocery_and_bakery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GlobalClass.selected_option_dashboard = "bakery";

                    LocationFragment nextFrag = new LocationFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, nextFrag, "location_fragment")
                            .addToBackStack(null)
                            .commit();
//
                }
            });

            fruits_and_vegetables.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GlobalClass.selected_option_dashboard = "fruits_and_vegetables";
                    LocationFragment nextFrag = new LocationFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, nextFrag, "location_fragment")
                            .addToBackStack(null)
                            .commit();
                }
            });

            medicine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GlobalClass.selected_option_dashboard = "medical_store";
                    LocationFragment nextFrag = new LocationFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, nextFrag, "location_fragment")
                            .addToBackStack(null)
                            .commit();

                }
            });

            shopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GlobalClass.selected_option_dashboard = "shopping_mall";
                    LocationFragment nextFrag = new LocationFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, nextFrag, "location_fragment")
                            .addToBackStack(null)
                            .commit();
                }
            });

            return view;
        }


}
