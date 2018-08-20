package com.example.a59070123.healthy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by LAB203_03 on 20/8/2561.
 */

public class BMIFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CalBMI();
        return inflater.inflate( R.layout.fragment_bmi, container, false);
    }
    void CalBMI(){
        Button btn_bmi_cal = (Button) getView().findViewById(R.id.bmi_cal);
        btn_bmi_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText cm = (EditText) getView().findViewById(R.id.bmi_cm);
                EditText kg = (EditText) getView().findViewById(R.id.bmi_kg);

                String cm_str = cm.getText().toString();
                int cm_int = Integer.parseInt(cm_str);
                String kg_str = kg.getText().toString();
                int kg_int = Integer.parseInt(kg_str);

                if (cm_str.isEmpty() || kg_str.isEmpty()) {
                    Toast.makeText(getActivity(),
                            "กรุณาระบุข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "FIELD NAME IS EMPTY");
                }
                else {
                    float bmi = kg_int / ((cm_int/100)*(cm_int/100));

                }
            }
        });
    }

}
