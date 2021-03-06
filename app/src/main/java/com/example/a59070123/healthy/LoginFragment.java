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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Penporn Pettammarot 59070123 IT KMITL
 */

public class LoginFragment extends Fragment {
    FirebaseAuth userAuth;
    FirebaseAuth User;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        initLoginBTN();
        initGotoRegisterBTN();
        super.onActivityCreated(savedInstanceState);
    }

    void initLoginBTN() {
        userAuth = FirebaseAuth.getInstance();

        Button btn_login = (Button) getView().findViewById(R.id.login_login_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userId = (EditText) getView().findViewById(R.id.login_userId);
                EditText pass = (EditText) getView().findViewById(R.id.login_pass);

                String userID_str = userId.getText().toString();
                String pass_str = pass.getText().toString();

                Log.d("LOGIN", "USER ID = " + userID_str);
                Log.d("LOGIN", "PASSWORD = " + pass_str);

                if (userID_str.isEmpty() || pass_str.isEmpty()) {
                    Toast.makeText(getActivity(),
                            "กรุณาระบุ USER OR PASSWORD",
                            Toast.LENGTH_SHORT
                    ).show();

//                    /////////////////////BYPASS
//                    getActivity().getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.main_view, new MenuFragment()).addToBackStack(null)
//                            .commit();
//                    ///////////////////don't forget delete

                    Log.d("USER", "USER OR PASSWORD IS EMPTY");
                } else {
                    userAuth.signInWithEmailAndPassword(userID_str, pass_str).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if(userAuth.getCurrentUser().isEmailVerified()){
                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_view,new MenuFragment())
                                        .commit();
                            }
                            else{
                                Toast.makeText(getActivity(),
                                        "Please verify your E-mail",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),
                                    "กรุณาระบุ USER OR PASSWORD ให้ถูกต้อง",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    });
                    Log.d("USER", "INVALID USER NAME OR PASSWORD");
                }
            }
        });
    }

    void initGotoRegisterBTN() {
        TextView btn_regis = (TextView) getView().findViewById(R.id.login_regis_btn);
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d("USER", "GOTO REGISTER");
            }
        });

    }

}
