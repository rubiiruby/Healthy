package com.example.a59070123.healthy.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.a59070123.healthy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAB203_02 on 27/8/2561.
 */

public class WeightAdapter extends ArrayAdapter <Weight> {

    List<Weight> weights = new ArrayList<Weight>();

    public WeightAdapter(Context context, int resource, List<Weight> object){
        super(context, resource, object);
        this.weights = object;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        return LayoutInflater.from(context).inflate(R.layout.fragment_weight_item,parent,false);


    }
}
