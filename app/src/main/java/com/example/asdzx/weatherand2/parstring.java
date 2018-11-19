package com.example.asdzx.weatherand2;

import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.example.asdzx.weatherand2.MainActivity.weather;

public class parstring {
    boolean inpm10Value = false;
    boolean inpm25Value = false;

    String pm10Value = null; //미세먼지
    String pm25Value = null; //초미세먼지

    String weather_url;

    XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
    XmlPullParser parser = parserCreator.newPullParser();

    public parstring() throws XmlPullParserException {

    }

    //미세먼지
    public void miseparsing() throws XmlPullParserException, IOException {
        URL weather_url = new URL("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?" +
                "stationName=석사동&" +
                "dataTerm=month&" +
                "pageNo=1&" +
                "numOfRows=1&" +
                "ServiceKey=PExw%2FhWxDuRH5PLh2T7Z0gRK0KPerENji1Yskvtp4ApbzepFsKLRH%2FZnI17I6dRfs0Obd6DBevxgbonv%2BUdq9g%3D%3D&" +
                "ver=1.3");

        parser.setInput(weather_url.openStream(), null);
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

    //초단기 실황
    public void chodanparsing() {
        String nowtime = AsyncTask1.doTime();

        weather_url = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?" +
                "serviceKey=GRaMjomvOF4JA093y1NSWvnbZBDrOYMVpmeCfn7qS0STtnV2Es%2Bz1SiJkMs4ql1hUGy%2BwsbYptj2JPrb2VGh%2BQ%3D%3D&" +
                "base_date=" +
                "20181119&" +
                "base_time=" +
                nowtime +
                "&" +
                "nx=73&" +
                "ny=134&" +
                "numOfRows=10&" +
                "_type=xml";

        URL url;
        Document doc = null;

        try {
            url = new URL(weather_url);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();


        } catch (Exception e) {
        }

        String s = "";
        NodeList nodeList = doc.getElementsByTagName("item");


        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);
            Element fstElmnt = (Element) node;
            NodeList idx = fstElmnt.getElementsByTagName("category");

            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("T1H")) {
                NodeList gugun = fstElmnt.getElementsByTagName("obsrValue");
                s = gugun.item(0).getChildNodes().item(0).getNodeValue() + "";
                weather.setT1H(s);
            }

            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("REH")) {
                NodeList gugun = fstElmnt.getElementsByTagName("obsrValue");
                s = gugun.item(0).getChildNodes().item(0).getNodeValue() + "";
                weather.setREH(s);
            }

            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("PTY")) {
                NodeList gugun = fstElmnt.getElementsByTagName("obsrValue");
                s = gugun.item(0).getChildNodes().item(0).getNodeValue() + "";
                weather.setPTY(s);
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("VEC")) {
                NodeList gugun = fstElmnt.getElementsByTagName("obsrValue");
                s = gugun.item(0).getChildNodes().item(0).getNodeValue() + "";
                weather.setVEC(s);
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("WSD")) {
                NodeList gugun = fstElmnt.getElementsByTagName("obsrValue");
                s = gugun.item(0).getChildNodes().item(0).getNodeValue() + "";
                weather.setWDF(s);
            }

        }
    }

    // 0200 동네 예보
    public void dongnae2parsing() {
        String nowtime = AsyncTask1.doTime();
        String today;
        String today_1;

        int a = Integer.parseInt(nowtime);

        if (a <= 0200) {
            today = AsyncTask1.doyes();
            today_1 = AsyncTask1.doYearMonthDay();
            nowtime = "0200";
        } else {
            today = AsyncTask1.doYearMonthDay();
            today_1 = AsyncTask1.today_1();
            nowtime = "0200";
        }

        weather_url = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?" +
                "serviceKey=GRaMjomvOF4JA093y1NSWvnbZBDrOYMVpmeCfn7qS0STtnV2Es%2Bz1SiJkMs4ql1hUGy%2BwsbYptj2JPrb2VGh%2BQ%3D%3D&" +
                "base_date=" +
                today +
                "&" +
                "base_time=" +
                nowtime +
                "&" +
                "nx=73&" +
                "ny=134&" +
                "numOfRows=250&" +
                "_type=xml";

        URL url;
        Document doc = null;
        try {
            url = new URL(weather_url);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
        }

        String s = "";
        String ss = "";
        String sss = "";
        NodeList nodeList = doc.getElementsByTagName("item");


        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);
            Element fstElmnt = (Element) node;
            NodeList idx = fstElmnt.getElementsByTagName("category");

            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("SKY")) {
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstDate");
                sss = gugun2.item(0).getChildNodes().item(0).getNodeValue() + "";

                if (sss.equals(today)) {
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    int cloud_num = Integer.parseInt(gugun.item(0).getChildNodes().item(0).getNodeValue());
                    NodeList gugun1 = fstElmnt.getElementsByTagName("fcstTime");
                    s = "" + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "";

                    if (cloud_num == 0 || cloud_num == 1 || cloud_num == 2) {
                        ss = "맑음";
                    } else if (cloud_num == 3 || cloud_num == 4 || cloud_num == 5) {
                        ss = "구름 조금";
                    } else if (cloud_num == 6 || cloud_num == 7 || cloud_num == 8) {
                        ss = "구름 많음";
                    } else if (cloud_num == 9 || cloud_num == 10) {
                        ss = "흐림";
                    }
                    if (s.equals("0600")) {
                        weather.SKY0_1 = ss;
                    }
                    if (s.equals("0900")) {
                        weather.SKY0_2 = ss;
                    }
                    if (s.equals("1200")) {
                        weather.SKY0_3 = ss;
                    }
                    if (s.equals("1800")) {
                        weather.SKY0_4 = ss;
                    }
                    if (s.equals("2100")) {
                        weather.SKY0_5 = ss;
                    }
                } else if (sss.equals(today_1)) {
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    int cloud_num = Integer.parseInt(gugun.item(0).getChildNodes().item(0).getNodeValue());
                    NodeList gugun1 = fstElmnt.getElementsByTagName("fcstTime");
                    s = "" + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "";

                    if (cloud_num == 0 || cloud_num == 1 || cloud_num == 2) {
                        ss = "맑음";
                    } else if (cloud_num == 3 || cloud_num == 4 || cloud_num == 5) {
                        ss = "구름 조금";
                    } else if (cloud_num == 6 || cloud_num == 7 || cloud_num == 8) {
                        ss = "구름 많음";
                    } else if (cloud_num == 9 || cloud_num == 10) {
                        ss = "흐림";
                    }
                    if (s.equals("0600")) {
                        weather.SKY1_1 = ss;
                    }
                    if (s.equals("0900")) {
                        weather.SKY1_2 = ss;
                    }
                    if (s.equals("1200")) {
                        weather.SKY1_3 = ss;
                    }
                    if (s.equals("1800")) {
                        weather.SKY1_4 = ss;
                    }
                    if (s.equals("2100")) {
                        weather.SKY1_5 = ss;
                    }
                }

            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("REH")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s = "" + gugun1.item(0).getChildNodes().item(0).getNodeValue();

                if (s.equals(today)) {
                    NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                    ss = "" + gugun2.item(0).getChildNodes().item(0).getNodeValue();
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    sss = "" + gugun.item(0).getChildNodes().item(0).getNodeValue();
                    if (ss.equals("0600")) {
                        weather.REH_1 = sss;
                    }
                    if (ss.equals("0900")) {
                        weather.REH_2 = sss;
                    }
                    if (ss.equals("1200")) {
                        weather.REH_3 = sss;
                    }
                    if (ss.equals("1800")) {
                        weather.REH_4 = sss;
                    }
                    if (ss.equals("2100")) {
                        weather.REH_5 = sss;
                    }
                }else if(s.equals(today_1)) {
                    NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                    ss = "" + gugun2.item(0).getChildNodes().item(0).getNodeValue();
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    sss = "" + gugun.item(0).getChildNodes().item(0).getNodeValue();
                    if (ss.equals("0600")) {
                        weather.T_REH_1 = sss;
                    }
                    if (ss.equals("0900")) {
                        weather.T_REH_2 = sss;
                    }
                    if (ss.equals("1200")) {
                        weather.T_REH_3 = sss;
                    }
                    if (ss.equals("1800")) {
                        weather.T_REH_4 = sss;
                    }
                    if (ss.equals("2100")) {
                        weather.T_REH_5 = sss;
                    }
                }
            }
            /*if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("S06")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "6시간 신적설 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "cm \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("R06")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "6시간 강수량 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "mm \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("UUU")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "풍속(동서성분) = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "m/s \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("VVV")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "풍속(남북성분) = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "m/s \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("WAV")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "파고 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "M \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("VEC")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "풍향 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "m/s \n";
            }*/
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("WSD")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "풍속 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + " \n";
            }
        }
    }

    // 0500 동네 예보
    public void dongnae5parsing() {
        String nowtime = AsyncTask1.doTime();
        String today;
        String today_1;
        String today_2;

        int a = Integer.parseInt(nowtime);

        if (a <= 500) {
            today = AsyncTask1.doyes();
            today_1 = AsyncTask1.doYearMonthDay();
            today_2 = AsyncTask1.today_1();
            nowtime = "0500";
        } else {
            today = AsyncTask1.doYearMonthDay();
            today_1 = AsyncTask1.today_1();
            today_2 = AsyncTask1.today_2();
            nowtime = "0500";
        }

        weather_url = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?" +
                "serviceKey=GRaMjomvOF4JA093y1NSWvnbZBDrOYMVpmeCfn7qS0STtnV2Es%2Bz1SiJkMs4ql1hUGy%2BwsbYptj2JPrb2VGh%2BQ%3D%3D&" +
                "base_date=" +
                today +
                "&" +
                "base_time=" +
                nowtime +
                "&" +
                "nx=73&" +
                "ny=134&" +
                "numOfRows=250&" +
                "_type=xml";

        URL url;
        Document doc = null;
        try {
            url = new URL(weather_url);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
        }

        String s = "";
        String ss = "";
        NodeList nodeList = doc.getElementsByTagName("item");


        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);
            Element fstElmnt = (Element) node;
            NodeList idx = fstElmnt.getElementsByTagName("category");


            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("TMN")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                ss = "" + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "";
                if (ss.equals(today_1)) {
                    NodeList gugun3 = fstElmnt.getElementsByTagName("fcstValue");
                    weather.TMN2 = gugun3.item(0).getChildNodes().item(0).getNodeValue();
                }
                if (ss.equals(today_2)) {
                    NodeList gugun3 = fstElmnt.getElementsByTagName("fcstValue");
                    weather.TMN3 = gugun3.item(0).getChildNodes().item(0).getNodeValue();
                }
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("TMX")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                ss = "" + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "";
                if (ss.equals(today)) {
                    NodeList gugun3 = fstElmnt.getElementsByTagName("fcstValue");
                    weather.TMX1 = gugun3.item(0).getChildNodes().item(0).getNodeValue();
                }
                if (ss.equals(today_1)) {
                    NodeList gugun3 = fstElmnt.getElementsByTagName("fcstValue");
                    weather.TMX2 = gugun3.item(0).getChildNodes().item(0).getNodeValue();
                }
                if (ss.equals(today_2)) {
                    NodeList gugun3 = fstElmnt.getElementsByTagName("fcstValue");
                    weather.TMX3 = gugun3.item(0).getChildNodes().item(0).getNodeValue();
                }
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("SKY")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                int cloud_num = Integer.parseInt(gugun.item(0).getChildNodes().item(0).getNodeValue());

                if (cloud_num == 0 || cloud_num == 1 || cloud_num == 2) {
                    s += "fcstValue 하늘상태 = 맑음\n";
                } else if (cloud_num == 3 || cloud_num == 4 || cloud_num == 5) {
                    s += "fcstValue 하늘상태 = 구름 조금\n";
                } else if (cloud_num == 6 || cloud_num == 7 || cloud_num == 8) {
                    s += "fcstValue 하늘상태 = 구름 많음\n";
                } else if (cloud_num == 9 || cloud_num == 10) {
                    s += "fcstValue 하늘상태 = 흐림\n";
                }
                s += "하늘상태 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + " \n";


            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("REH")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "습도 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "% \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("S06")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "6시간 신적설 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "cm \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("R06")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "6시간 강수량 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "mm \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("UUU")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "풍속(동서성분) = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "m/s \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("VVV")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "풍속(남북성분) = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "m/s \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("WAV")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "파고 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "M \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("VEC")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "풍향 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "m/s \n";
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("WSD")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜 = " + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간 = " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + " \n";
                NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                s += "풍속 = " + gugun.item(0).getChildNodes().item(0).getNodeValue() + " \n";
            }
        }
    }

    //초단기 예보
    public void chodanyeparsing() {
        weather_url = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastTimeData?" +
                "serviceKey=GRaMjomvOF4JA093y1NSWvnbZBDrOYMVpmeCfn7qS0STtnV2Es%2Bz1SiJkMs4ql1hUGy%2BwsbYptj2JPrb2VGh%2BQ%3D%3D&" +
                "base_date=20181115&" +
                "base_time=1130&" +
                "nx=73&ny=134&" +
                "numOfRows=40&" +
                "_type=xml";

        URL url;
        Document doc = null;
        try {
            url = new URL(weather_url);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
        }

        String s = "";
        NodeList nodeList = doc.getElementsByTagName("item");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);
            Element fstElmnt = (Element) node;

            NodeList idx = fstElmnt.getElementsByTagName("category");

            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("T1H")) {
                NodeList gugun = fstElmnt.getElementsByTagName("fcstDate");
                s += "날짜= " + gugun.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                s += "시간= " + gugun2.item(0).getChildNodes().item(0).getNodeValue() + "℃ \n";
                NodeList gugun3 = fstElmnt.getElementsByTagName("fcstValue");
                s += "온도= " + gugun3.item(0).getChildNodes().item(0).getNodeValue() + "℃ \n";
            }

            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("T1H")) {

            }
        }
    }
}