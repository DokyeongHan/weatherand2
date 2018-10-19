package com.example.asdzx.weatherand2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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

        Drawable alpha = ((ImageView)root.findViewById(R.id.gif_image)).getDrawable();
        alpha.setAlpha(30);

        ImageView rain = root.findViewById(R.id.gif_image);
        Glide.with(this).load(R.drawable.a61940b94f8cf642d4762890e057a88d).into(rain);

        return root;
    }
}