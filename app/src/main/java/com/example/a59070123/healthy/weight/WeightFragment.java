package com.example.a59070123.healthy.weight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.example.a59070123.healthy.MenuFragment;
import com.example.a59070123.healthy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Penporn Pettammarot 59070123 IT KMITL
 */

public class WeightFragment extends Fragment {
    FirebaseFirestore db;
    FirebaseAuth auth;
    ArrayList<Weight> arr_weight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_weight, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        arr_weight = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance(); ///////////don't forget !!!!!!!!!!

//        weight.add(new Weight("1-1-2018", 63, "UP"));
//        weight.add(new Weight("02 Jan 2018", 64, "DOWN"));
//        weight.add(new Weight("03 Jan 2018", 63, "UP"));

        showData();
        btnAdd();
        btnBack();
    }

    void showData(){
        final ListView weightList = getView().findViewById(R.id.weight_list);
        final WeightAdapter weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_weight_item,
                arr_weight
        );
        weightList.setAdapter(weightAdapter);
        weightAdapter.clear();
        db.collection("myfitness").document(auth.getUid()).collection("weight").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot DocumentSnapshots,
                                @javax.annotation.Nullable FirebaseFirestoreException e) {
                weightAdapter.clear();


                for (QueryDocumentSnapshot doc : DocumentSnapshots){
                    Weight weights = doc.toObject(Weight.class);
                    arr_weight.add(weights);
                    weightAdapter.notifyDataSetChanged();

                }

                Collections.reverse(arr_weight);
                for (int i = 0; i < arr_weight.size()-1; i++){
                    if (arr_weight.get(i).getWeight() < arr_weight.get(i+1).getWeight()){
                        arr_weight.get(i).setStatus("DOWN");
                    }
                    else if(arr_weight.get(i).getWeight() > arr_weight.get(i+1).getWeight()){
                        arr_weight.get(i).setStatus("UP");
                    }

                }
            }



        });
    }

    void btnAdd(){
        /////////////BTN Add
        TextView btn_weight_add = (TextView) getView().findViewById(R.id.weight_btn_add);
        btn_weight_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weight", "Click ADD FORM");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFormFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    void btnBack(){
        /////////////BTN Back
        TextView btn_weight_back = (TextView) getView().findViewById(R.id.weight_btn_back);
        btn_weight_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Weight", "Click ADD BACK");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}
