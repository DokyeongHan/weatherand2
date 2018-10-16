package com.example.asdzx.weatherand2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import im.dacer.androidcharts.LineView;

public class LayoutTwo extends Fragment {
    public static LayoutTwo newInstance() {
        LayoutTwo fragment = new LayoutTwo();
        return fragment;
    }

    public LayoutTwo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_two, null);

        final Button bt = root.findViewById(R.id.bt2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt.setText("123");
            }
        });
        return root;
    }
}