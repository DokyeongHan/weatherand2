package com.example.asdzx.weatherand2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutThree extends Fragment {

    public static LayoutThree newInstance() {
        LayoutThree fragment = new LayoutThree();
        return fragment;
    }

    public LayoutThree() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_three, null);
        return root;
    }
}