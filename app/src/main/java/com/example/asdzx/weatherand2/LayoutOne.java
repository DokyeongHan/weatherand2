package com.example.asdzx.weatherand2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.asdzx.weatherand2.MainActivity.a;
import static com.example.asdzx.weatherand2.MainActivity.b;

public class LayoutOne extends Fragment {
    TextView text;

    public static LayoutOne newInstance() {
        LayoutOne fragment = new LayoutOne();
        return fragment;
    }

    public LayoutOne() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_one, null);
        text = (TextView) root.findViewById(R.id.result);

        APItask api = new APItask();
        api.execute();

        return root;
    }

    public class APItask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            text.setText(b.mise);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}