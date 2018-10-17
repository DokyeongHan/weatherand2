package com.example.asdzx.weatherand2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

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

        LineChart chart = root.findViewById(R.id.chart);

        ArrayList<Entry> valsComp1 = new ArrayList<>();

        valsComp1.add(new Entry(100.0f, 0));
        valsComp1.add(new Entry(50.0f, 1));
        valsComp1.add(new Entry(75.0f, 2));
        valsComp1.add(new Entry(30.0f, 3));

        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setComp1);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1.0");
        xVals.add("2.0");
        xVals.add("3.0");
        xVals.add("4.0");

        LineData data = new LineData(xVals, dataSets);

        chart.setData(data);
        chart.invalidate();

        return root;
    }
}