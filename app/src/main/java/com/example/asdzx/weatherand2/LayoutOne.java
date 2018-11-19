package com.example.asdzx.weatherand2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.asdzx.weatherand2.MainActivity.parstring;
import static com.example.asdzx.weatherand2.MainActivity.weather;

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
                text = root.findViewById(R.id.result);

                APItask api = new APItask();
                api.execute();

                return root;
            }

            public class APItask extends AsyncTask<Integer, Integer, Void> {
                @Override
                protected Void doInBackground(Integer... integers) {
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    text.setText(weather.REH_2);
                    //text.setText(AsyncTask1.doyes());
                    super.onPostExecute(aVoid);
                }
    }
}