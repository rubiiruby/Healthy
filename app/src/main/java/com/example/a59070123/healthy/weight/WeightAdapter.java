package com.example.a59070123.healthy.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a59070123.healthy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Penporn Pettammarot 59070123 IT KMITL
 */

public class WeightAdapter extends ArrayAdapter <Weight> {

    List<Weight> weights = new ArrayList<Weight>();
    Context context;

    public WeightAdapter(Context context, int resource, List<Weight> object){
        super(context, resource, object);
        this.weights = object;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View weightItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_weight_item,
                parent,
                false);

        TextView date_text = (TextView) weightItem.findViewById(R.id.weight_item_date);
        TextView weight_text = (TextView) weightItem.findViewById(R.id.weight_item_weight);
        TextView status_text = (TextView) weightItem.findViewById(R.id.weight_item_status);


        Weight row = weights.get(position);

        date_text.setText(row.getDate());
        weight_text.setText(Integer.toString(row.getWeight()));
        status_text.setText(row.getStatus());

        return weightItem;

    }
}
