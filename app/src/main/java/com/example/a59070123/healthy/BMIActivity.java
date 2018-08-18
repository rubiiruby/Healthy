package com.example.a59070123.healthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bmi);

//        TextView cal = (TextView) findViewById(R.id.cal_bmi);
//        cal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(  MainActivity.this, BMIActivity.class);
//                startActivity(intent);
//            }
//        });
    }

}
