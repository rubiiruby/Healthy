package com.example.a59070123.healthy.weight;

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

import com.example.a59070123.healthy.BMIFragment;
import com.example.a59070123.healthy.MenuFragment;
import com.example.a59070123.healthy.R;

import java.util.ArrayList;

/**
 * Created by LAB203_02 on 27/8/2561.
 */

public class WeightFragment extends Fragment {

    ArrayList<Weight> weight = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_weight, container, false);

    }



    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        weight.add(new Weight("01 Jan 2018", 63, "UP"));
        weight.add(new Weight("02 Jan 2018", 64, "DOWN"));
        weight.add(new Weight("03 Jan 2018", 63, "UP"));


        ListView weightList =  (ListView) getView().findViewById(R.id.weight_list);
        WeightAdapter weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_weight_item,
                weight
        );
        weightList.setAdapter(weightAdapter);

        /////////////BTN Add
        TextView btn_weight_add = (TextView) getView().findViewById(R.id.weight_btn_add);
        btn_weight_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weight", "Click ADD FORM");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFormFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        /////////////BTN Back
        TextView btn_weight_back = (TextView) getView().findViewById(R.id.weight_btn_back);
        btn_weight_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weight", "Click ADD BACK");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });



    }

}
