package com.example.a59070123.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a59070123.healthy.MenuFragment;
import com.example.a59070123.healthy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by Penporn Pettammarot 59070123 IT KMITL
 */

public class WeightFormFragment extends Fragment {
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_form, container, false);

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        initSaveButton();
        btnBack();

    }

    void initSaveButton() {
        Button btn = getView().findViewById(R.id.weight_form_btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText date = getView().findViewById(R.id.weight_form_date);
                EditText weight = getView().findViewById(R.id.weight_form_weight);

                String dateStr = date.getText().toString();
                int weightStr = Integer.parseInt(weight.getText().toString());

                dateStr = dateStr.replace("/","-"); //แปลงข้อมูล

                    Weight data = new Weight(
                            dateStr,
                            Integer.valueOf(weightStr)
                    );


                String uid = auth.getUid();
                firestore.collection("myfitness")
                        .document(uid)
                        .collection("weight")
                        .document(dateStr)
                        .set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Success Add", Toast.LENGTH_SHORT).show();

                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new WeightFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Add Fail !!!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    void btnBack(){
        /////////////BTN Back
        TextView btn_weight_back = (TextView) getView().findViewById(R.id.weight_form_btn_back);
        btn_weight_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weight Form", "Click ADD BACK");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
