package com.example.a59070123.healthy.sleep;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a59070123.healthy.DBHelper;
import com.example.a59070123.healthy.MenuFragment;
import com.example.a59070123.healthy.R;

import java.util.ArrayList;


public class SleepFragment extends Fragment {
    ArrayList<Sleep> sleeps = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            Log.wtf("sleep", "toast");
            String isNull = bundle.getString("complete");
            if (isNull != null) {
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }
            bundle.clear();
        }

        btnAdd();
        getData();
        btnBack();

    }

    void btnAdd(){
        Button addBtn = getActivity().findViewById(R.id.sleep_btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFormFragment())
                        .addToBackStack(null).commit();
            }
        });
    }

    void getData() {
        DBHelper db = new DBHelper(getActivity());
        db.getReadableDatabase();
        sleeps = db.getSleepList();
        ListView sleepList = getView().findViewById(R.id.sleep_list);
        SleepAdapter sleepAdapter = new SleepAdapter(getActivity(), R.layout.fragment_sleep, sleeps);
        sleepList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SleepFormFragment sleepFormFragment = new SleepFormFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("sleep", sleeps.get(position));
                bundle.putString("sleepId", sleeps.get(position).getId());
                Log.wtf("sleep","size + " + sleeps.size());
                sleepFormFragment.setArguments(bundle);
                setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, sleepFormFragment).addToBackStack(null).commit();
            }
        });
        sleepList.setAdapter(sleepAdapter);

    }

    void btnBack(){
        /////////////BTN Back
        TextView btn_weight_back = (TextView) getView().findViewById(R.id.sleep_btn_back);
        btn_weight_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sleep", "Click ADD BACK");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}
