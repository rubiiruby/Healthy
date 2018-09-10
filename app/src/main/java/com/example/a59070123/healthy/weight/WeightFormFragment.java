package com.example.a59070123.healthy.weight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a59070123.healthy.MenuFragment;
import com.example.a59070123.healthy.R;

/**
 * Created by LAB203_02 on 10/9/2561.
 */

public class WeightFormFragment extends Fragment {

    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate( R.layout.fragment_weight_form, container, false);

    }



    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView btn_weight_add = (TextView) getView().findViewById(R.id.weight_form_btn_add);
        btn_weight_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weight", "Click ADD FORM");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFormFragment()).addToBackStack(null)
                        .commit();
            }
        });

        TextView btn_weight_form_back = (TextView) getView().findViewById(R.id.weight_form_btn_back);
        btn_weight_form_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weight", "Click BACK");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFragment()).addToBackStack(null)
                        .commit();
            }
        });



    }
}
