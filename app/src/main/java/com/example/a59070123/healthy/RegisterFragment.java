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

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate( R.layout.fragment_register, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initRegisterBTN();
        super.onActivityCreated(savedInstanceState);
    }

    void initRegisterBTN(){
        Button btn_regis = (Button) getView().findViewById(R.id.regis_btn_regisnew);
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = (EditText) getView().findViewById(R.id.regis_userid);
                EditText name = (EditText) getView().findViewById(R.id.regis_name);
                EditText age = (EditText) getView().findViewById(R.id.regis_age);
                EditText pass = (EditText) getView().findViewById(R.id.regis_pass);

                String user_str = user.getText().toString();
                String name_str = name.getText().toString();
                String age_str = age.getText().toString();
                String pass_str = pass.getText().toString();

                if (user_str.isEmpty() || pass_str.isEmpty() || name_str.isEmpty() || age_str.isEmpty()){
                    Toast.makeText(getActivity(),
                            "กรุณาระบุกรอกข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "FIELD NAME IS EMPTY");
                }

                else if (user_str.equals("admin")) {
                    Toast.makeText(getActivity(),
                            "USER นี้มีอยู่ในระบบแล้ว",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "ALREADY EXIST");
                }

                else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BMIFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("USER", "GOTO BMI");
                }

            }
        });
    }
}
