package com.example.a59070123.healthy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Penporn Pettammarot 59070123 IT KMITL
 */

public class BMIFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate( R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        initCalBMI();
        initButtonBack();

        super.onActivityCreated(savedInstanceState);
    }

    void initCalBMI(){
        Button btn_bmi_cal = (Button) getView().findViewById(R.id.bmi_cal);
        btn_bmi_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText cm = (EditText) getView().findViewById(R.id.bmi_cm);
                EditText kg = (EditText) getView().findViewById(R.id.bmi_kg);

                String cm_str = cm.getText().toString();
                Float cm_flo = Float.parseFloat(cm_str);
                String kg_str = kg.getText().toString();
                Float kg_flo = Float.parseFloat(kg_str);

                if (cm_str.isEmpty() || kg_str.isEmpty()) {
                    Toast.makeText(getActivity(),
                            "กรุณาระบุข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("BMI", "FIELD NAME IS EMPTY");
                }
                else {
                    Log.d("BMI", "BMI IS VALUE");
                    cm_flo = (cm_flo/100)*(cm_flo/100);
                    Float bmi = kg_flo / cm_flo;
                    //bmi = bmi.shortValue();

                    TextView you_bmi = (TextView) getView().findViewById(R.id.you_bmi);
                    you_bmi.setVisibility(View.VISIBLE);


                    TextView you_bmi_cal = (TextView) getView().findViewById(R.id.you_bmi_cal);
                    you_bmi_cal.setVisibility(View.VISIBLE);
                    you_bmi_cal.append(bmi.toString());

                }
            }
        });
    }
    void initButtonBack(){
        TextView btn_bmi_back = (TextView) getView().findViewById(R.id.bmi_btn_back);
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
