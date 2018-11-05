package com.example.a59070123.healthy.sleep;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a59070123.healthy.R;
import com.example.a59070123.healthy.sleep.Sleep;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SleepAdapter extends ArrayAdapter <Sleep>  {

    List<Sleep> sleeps = new ArrayList<Sleep>();
    Context context;

    public SleepAdapter(Context context, int resource, List<Sleep> object){
        super(context, resource, object);
        this.sleeps = object;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View sleepItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_sleep_item,
                parent,
                false);

        TextView date_text = sleepItem.findViewById(R.id.sleep_item_date);
        TextView time = sleepItem.findViewById(R.id.sleep_item_timeslp);
        TextView total = sleepItem.findViewById(R.id.sleep_item_total);

        Sleep row = sleeps.get(position);

//        String total_str = "เวลาที่นอนทั้งหมด : "+Integer.parseInt(row.getTime_total()) +" ชั่วโมง";

        date_text.setText(row.getDate());
        time.setText("เวลาที่เข้านอน - ตื่น : "+row.getTime_slp() + " - " + row.getTime_awake());
        total.setText("เวลาที่นอนทั้งหมด : "+row.getTime_total() +" ชั่วโมง");


        return sleepItem;

    }

}
