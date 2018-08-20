package com.example.a59070123.healthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new LoginFragment())
                    .commit();
        }






//        TextView btn_login = (TextView) findViewById(R.id.btn_login);
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(  MainActivity.this, BMIActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        TextView btn_regis = (TextView) findViewById(R.id.btn_register);
//        btn_regis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(  MainActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });


    }
}
