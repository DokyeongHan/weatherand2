package com.example.asdzx.weatherand2;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.example.asdzx.weatherand2.MainActivity.weather;

public class parstring {
    boolean inpm10Value = false;
    boolean inpm25Value = false;

    String pm10Value = null;
    String pm25Value = null;

    XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
    XmlPullParser parser = parserCreator.newPullParser();

    public parstring() throws XmlPullParserException {

    }

    public void miseparsing() throws XmlPullParserException, IOException {
        URL url = new URL("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?" +
                "stationName=석사동&" +
                "dataTerm=month&" +
                "pageNo=1&" +
                "numOfRows=1&" +
                "ServiceKey=PExw%2FhWxDuRH5PLh2T7Z0gRK0KPerENji1Yskvtp4ApbzepFsKLRH%2FZnI17I6dRfs0Obd6DBevxgbonv%2BUdq9g%3D%3D&" +
                "ver=1.3");

        parser.setInput(url.openStream(), null);
        int parserEvent = parser.getEventType();
        while (parserEvent != XmlPullParser.END_DOCUMENT) {
            switch (parserEvent) {
                case XmlPullParser.START_TAG://시작 태그를 만나면 실행
                    if (parser.getName().equals("pm10Value")) {
                        inpm10Value = true;
                    }
                    if (parser.getName().equals("pm25Value")) {
                        inpm25Value = true;
                    }
                    if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                        //에러 메세지
                    }
                    break;

                case XmlPullParser.TEXT://parser가 내용에 접근했을때
                    if (inpm10Value) {
                        pm10Value = parser.getText();
                        weather.setPm10(pm10Value);
                        inpm10Value = false;
                    }
                    if (inpm25Value) {
                        pm25Value = parser.getText();
                        weather.setPm25(pm25Value);
                        inpm25Value = false;
                    }

                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("item")) {
                    }
                    break;
            }
            parserEvent = parser.next();
        }
    }
}
