package com.example.sonuroy.attendancesheet;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SONU ROY on 30-06-2017.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    static ArrayList<String> list = new  ArrayList<String>();
    static RadioGroup radioGroup;

     public CustomAdapter(@NonNull Context context, ArrayList<String> data) {
        super(context,R.layout.customadapter,data);
    }
    public class ViewHolder {
        TextView text_name,text_roll;
        ViewHolder(View view) {
            //text_name = (TextView) view.findViewById(R.id.textView);
            text_roll = (TextView) view.findViewById(R.id.textView);
            radioGroup = (RadioGroup) view.findViewById(R.id.radiogrp);
        }
    }
    @NonNull
    @Override
    public View getView( final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        try {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View customView = inflater.inflate(R.layout.customadapter, parent, false);
            ViewHolder holder;
            holder = new ViewHolder(customView);
            customView.setTag(holder);
            String s2 = getItem(position);
             //holder.text_name.setText(s1);
            holder.text_roll.setText(s2);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {

                    String s3 = getItem(position);
                    switch (checkedId) {
                        case R.id.rAbsent:
                            //do any activity

                            break;
                        case R.id.rPresent:
                            list.add(s3);
                            break;
                    }
                }
            });

            return customView;
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return convertView;
    }
}
