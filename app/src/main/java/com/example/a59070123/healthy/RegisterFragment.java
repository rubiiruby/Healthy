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
import android.widget.TextView;
import android.widget.Toast;

import com.example.a59070123.healthy.weight.WeightFormFragment;

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
        initBackBTN();
        super.onActivityCreated(savedInstanceState);
    }

    void initRegisterBTN(){
        Button btn_regis = (Button) getView().findViewById(R.id.regis_btn_regisnew);
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = (EditText) getView().findViewById(R.id.regis_userid);
                EditText pass = (EditText) getView().findViewById(R.id.regis_pass);
                EditText re_pass = (EditText) getView().findViewById(R.id.regis_repass);

                String user_str = user.getText().toString();
                String pass_str = pass.getText().toString();
                String re_pass_str = re_pass.getText().toString();

                if (user_str.isEmpty() || pass_str.isEmpty() || re_pass_str.isEmpty()){
                    Toast.makeText(getActivity(),
                            "กรุณาระบุกรอกข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "FIELD NAME IS EMPTY");
                }

                else if(user_str.length() < 6){
                    Toast.makeText(getActivity(),
                            "กรุณาระบุกรอกPasswordให้มากว่า6ตัวอักษร",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "pass < 6");
                }

                else if(pass_str != re_pass_str){
                    Toast.makeText(getActivity(),
                            "กรุณาระบุกรอกPasswordให้ตรงกัน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "pass != repass");
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
    void initBackBTN(){
        TextView btn_regis_back = (TextView) getView().findViewById(R.id.regis_btn_back);
        btn_regis_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weight", "Click ADD FORM");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFormFragment()).addToBackStack(null)
                        .commit();
            }
        });
    }
}
