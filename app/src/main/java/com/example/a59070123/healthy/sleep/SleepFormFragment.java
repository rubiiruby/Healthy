package com.example.a59070123.healthy.sleep;

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

import com.example.a59070123.healthy.DBHelper;
import com.example.a59070123.healthy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import static java.lang.Math.abs;

public class SleepFormFragment extends Fragment {

    EditText date;
    EditText sleepHour;
    EditText wakeHour;
    EditText sleepMinute;
    EditText wakeMinute;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Bundle bundle = getArguments();


        date = getActivity().findViewById(R.id.sleep_form_date);
        sleepHour = getActivity().findViewById(R.id.sleep_form_sleep_hour);
        wakeHour = getActivity().findViewById(R.id.sleep_form_wake_hour);
        sleepMinute = getActivity().findViewById(R.id.sleep_form_sleep_minute);
        wakeMinute = getActivity().findViewById(R.id.sleep_form_wake_minute);

        if (bundle != null) {
            Sleep sleep = getArguments().getParcelable("sleep");

            date.setText(sleep.getDate());
            String[] sleepTime = sleep.getTime_slp().split(":");
            sleepHour.setText(sleepTime[0]);
            sleepMinute.setText(sleepTime[1]);

            String[] wakeTime = sleep.getTime_awake().split(":");
            wakeHour.setText(wakeTime[0]);
            wakeMinute.setText(wakeTime[1]);
        }

        Button saveBtn = getActivity().findViewById(R.id.sleep_form_btn_add);
        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String dateStr = date.getText().toString();
                String sleepStr = sleepHour.getText().toString() + ":" + sleepMinute.getText().toString();
                String wakeStr = wakeHour.getText().toString() + ":" + wakeMinute.getText().toString();
                Integer sleepHourInt = Integer.parseInt(sleepHour.getText().toString());
                Integer wakeHourInt = Integer.parseInt(wakeHour.getText().toString());

                if (wakeHourInt <= sleepHourInt) {
                    wakeHourInt += 24;
                }

                Integer totalSleepHour = abs(sleepHourInt - wakeHourInt);
                Integer totalSleepMinute = abs(Integer.parseInt(sleepMinute.getText().toString()) - Integer.parseInt(wakeMinute.getText().toString()));

                String totalSleep = totalSleepHour.toString() + " : " + totalSleepMinute.toString();
                Sleep sleep = new Sleep(dateStr, sleepStr, wakeStr, totalSleep);

                DBHelper dbHelper = new DBHelper(getActivity());

//                dbHelper.addSleep(sleep);


                if (bundle == null) {
                    dbHelper.addSleep(sleep);
                }
                else {
                    dbHelper.updateSleep(sleep, bundle.getString("sleepId"));
                }

                SleepFragment sleepFragment = new SleepFragment();
                Bundle bundle = new Bundle();
                bundle.putString("complete", "complete");
                sleepFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFragment())
                        .addToBackStack(null).commit();

                Toast.makeText(getActivity(), "Success" +
                        "", Toast.LENGTH_SHORT).show();
            }
        });


        btnBack();
    }

    void btnBack(){
        /////////////BTN Back
        TextView btn_sleep_back = (TextView) getView().findViewById(R.id.sleep_form_btn_back);
        btn_sleep_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Sleep Form", "Click ADD BACK");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
