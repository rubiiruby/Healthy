package com.example.a59070123.healthy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by Penporn Pettammarot 59070123 IT KMITL
 */

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore mdb;
    FirebaseAuth firebase;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebase = FirebaseAuth.getInstance();
        user = firebase.getCurrentUser();
        mdb = FirebaseFirestore.getInstance();

        if(savedInstanceState == null) {
            if(user == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment()).commit();
            }
            else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment()).commit();
            }
        }

    }
}