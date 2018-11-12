package com.example.asdzx.weatherand2;
import static com.example.asdzx.weatherand2.MainActivity.weather;

public class parstring {
    String miseString = "";
    String shorttermString = "";
    String longtermString = "";

    public void setMiseString(String mise) {
        this.miseString = mise;
    }

    public void setLongtermString(String longterm) {
        this.longtermString = longterm;
    }

    public void setShorttermString(String shortterm) {
        this.shorttermString = shortterm;
    }

    public void setweather() {
        weather.setPm10(10);
    }
}
