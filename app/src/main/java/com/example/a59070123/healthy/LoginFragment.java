package com.example.a59070123.healthy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LAB203_03 on 20/8/2561.
 */

public class LoginFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LoginBTN();
        GotoRegisterBTN();
        return inflater.inflate( R.layout.fragment_login, container, false);
    }

    void LoginBTN(){
        Button btn_login = (Button) getView().findViewById(R.id.login_login_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userId = (EditText) getView().findViewById(R.id.login_userId);
                EditText pass = (EditText) getView().findViewById(R.id.login_pass);

                String userID_str = userId.getText().toString();
                String pass_str = pass.getText().toString();

                Log.d("LOGIN","USER ID = "+userID_str);
                Log.d("LOGIN","PASSWORD = "+pass_str);

                if (userID_str.isEmpty() || pass_str.isEmpty()){
                    Toast.makeText(getActivity(),
                            "กรุณาระบุ USER OR PASSWORD",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "USER OR PASSWORD IS EMPTY");
                }

                else if (userID_str.equals("admin") && pass_str.equals("admin")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BMIFragment())

                            .commit();
                    Log.d("USER", "GOTO BMI");
                }

                else{
                    Log.d("USER", "INVALID USER NAME OR PASSWORD");
                }
            }
        });
    }

    void GotoRegisterBTN(){
        TextView btn_regis = (TextView) getView().findViewById(R.id.login_regis_btn);
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())

                        .commit();
                Log.d("USER", "GOTO REGISTER");
            }
        });

    }

}
