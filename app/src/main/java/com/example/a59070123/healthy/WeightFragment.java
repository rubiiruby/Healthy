package com.example.a59070123.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LAB203_02 on 27/8/2561.
 */

public class WeightFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_weight, container, false);
    }

    ArrayList<String> weight;

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //////////////Array
        weight = new ArrayList<>();
        final ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_2,
                weight
        );

        final ListView weightList = (ListView) getView().findViewById(R.id.weight_List);
        weightList.setAdapter(menuAdapter);
        weightList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("MENU", "Click on menu = "+weight.get(i));

                weight.add("");
                menuAdapter.notifyDataSetChanged();

            }
        });

        /////////////BTN Back
        TextView btn_bmi_back = (TextView) getView().findViewById(R.id.weight_btn_back);
        btn_bmi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment()).addToBackStack(null)
                        .commit();
            }
        });

    }

}
