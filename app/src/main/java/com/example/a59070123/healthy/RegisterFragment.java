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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by LAB203_03 on 20/8/2561.
 */

public class RegisterFragment extends Fragment {

    private FirebaseAuth fbAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate( R.layout.fragment_register, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fbAuth = FirebaseAuth.getInstance();

        initBackBTN();
        registerNewUser();

    }


    void initBackBTN(){
        TextView btn_regis_back = (TextView) getView().findViewById(R.id.regis_btn_back);
        btn_regis_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weight", "Click ADD FORM");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment()).addToBackStack(null)
                        .commit();
            }
        });
    }
    void registerNewUser(){
        Button btn_regis = (Button) getView().findViewById(R.id.regis_btn_regisnew);
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = (EditText) getView().findViewById(R.id.regis_email);
                EditText pass = (EditText) getView().findViewById(R.id.regis_pass);
                EditText re_pass = (EditText) getView().findViewById(R.id.regis_repass);

                String email_str = email.getText().toString();
                String pass_str = pass.getText().toString();
                String re_pass_str = re_pass.getText().toString();

                if (email_str.isEmpty() || pass_str.isEmpty() || re_pass_str.isEmpty()) {
                    Toast.makeText(getActivity(),
                            "กรุณากรอกข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "FIELD NAME IS EMPTY");
                } else if (pass_str.length() < 6) {
                    Toast.makeText(getActivity(),
                            "กรุณากรอกPasswordให้มากกว่า6ตัวอักษร",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "pass < 6");
                } else if (!pass_str.equals(re_pass_str)) {
                    Toast.makeText(getActivity(),
                            "กรุณากรอกPasswordให้ตรงกัน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "pass != repass");
                }else if(pass_str.equals(re_pass_str)){
                    fbAuth.createUserWithEmailAndPassword(email_str, pass_str).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            sendVerifiedEmail(authResult.getUser());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "ERROR = "+e.getMessage(),Toast.LENGTH_SHORT);
                        }
                    });

                }
            }
        });

    }

    private void sendVerifiedEmail(FirebaseUser user){
        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment())
                        .commit();
                Log.d("USER", "GOTO LOGIN");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }




}
