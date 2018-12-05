package com.example.asdzx.weatherand2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
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

    //미세먼지 - 완료
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

    //초단기 실황 - 완료
    public void chodanparsing() {
        String nowtime = AsyncTask1.doTime();
        String today = AsyncTask1.doYearMonthDay();

        weather_url = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?" +
                "serviceKey=GRaMjomvOF4JA093y1NSWvnbZBDrOYMVpmeCfn7qS0STtnV2Es%2Bz1SiJkMs4ql1hUGy%2BwsbYptj2JPrb2VGh%2BQ%3D%3D&" +
                "base_date=" +
                today +
                "&" +
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

    // 0200 동네 예보 - 오늘 내일 예보
    public void dongnae2parsing() {
        String nowtime = AsyncTask1.doTime();
        String today;
        String today_1;
        String today_2;
        int a = Integer.parseInt(nowtime);

        if (a <= 0200) {
            today = AsyncTask1.doyes();
            today_1 = AsyncTask1.doYearMonthDay();
            today_2 = AsyncTask1.today_1();
            nowtime = "0200";
        } else {
            today = AsyncTask1.doYearMonthDay();
            today_1 = AsyncTask1.today_1();
            today_2 = AsyncTask1.today_2();
            nowtime = "0200";
        }

        weather_url = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?" +
                "serviceKey=GRaMjomvOF4JA093y1NSWvnbZBDrOYMVpmeCfn7qS0STtnV2Es%2Bz1SiJkMs4ql1hUGy%2BwsbYptj2JPrb2VGh%2BQ%3D%3D&" +
                "base_date=" +
                today +
                "&base_time=" +
                nowtime +
                "&nx=73&" +
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
                } else if (sss.equals(today_2)) {
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
                        weather.SKY2_1 = ss;
                    }
                    if (s.equals("0900")) {
                        weather.SKY2_2 = ss;
                    }
                    if (s.equals("1200")) {
                        weather.SKY2_3 = ss;
                    }
                    if (s.equals("1800")) {
                        weather.SKY2_4 = ss;
                    }
                    if (s.equals("2100")) {
                        weather.SKY2_5 = ss;
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
                } else if (s.equals(today_1)) {
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
                s = "" + gugun1.item(0).getChildNodes().item(0).getNodeValue();

                if (s.equals(today)) {
                    NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                    ss = "" + gugun2.item(0).getChildNodes().item(0).getNodeValue();
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    sss = "" + gugun.item(0).getChildNodes().item(0).getNodeValue();
                    if (ss.equals("0600")) {
                        weather.WSD_1 = sss;
                    }
                    if (ss.equals("0900")) {
                        weather.WSD_2 = sss;
                    }
                    if (ss.equals("1200")) {
                        weather.WSD_3 = sss;
                    }
                    if (ss.equals("1800")) {
                        weather.WSD_4 = sss;
                    }
                    if (ss.equals("2100")) {
                        weather.WSD_5 = sss;
                    }
                } else if (s.equals(today_1)) {
                    NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                    ss = "" + gugun2.item(0).getChildNodes().item(0).getNodeValue();
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    sss = "" + gugun.item(0).getChildNodes().item(0).getNodeValue();
                    if (ss.equals("0600")) {
                        weather.T_WSD_1 = sss;
                    }
                    if (ss.equals("0900")) {
                        weather.T_WSD_2 = sss;
                    }
                    if (ss.equals("1200")) {
                        weather.T_WSD_3 = sss;
                    }
                    if (ss.equals("1800")) {
                        weather.T_WSD_4 = sss;
                    }
                    if (ss.equals("2100")) {
                        weather.T_WSD_5 = sss;
                    }
                }
            }

            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("PTY")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s = "" + gugun1.item(0).getChildNodes().item(0).getNodeValue();

                if (s.equals(today)) {
                    NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                    ss = "" + gugun2.item(0).getChildNodes().item(0).getNodeValue();
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    sss = "" + gugun.item(0).getChildNodes().item(0).getNodeValue();
                    if (ss.equals("0600")) {
                        weather.PTY_1 = sss;
                    }
                    if (ss.equals("0900")) {
                        weather.PTY_2 = sss;
                    }
                    if (ss.equals("1200")) {
                        weather.PTY_3 = sss;
                    }
                    if (ss.equals("1800")) {
                        weather.PTY_4 = sss;
                    }
                    if (ss.equals("2100")) {
                        weather.PTY_5 = sss;
                    }
                } else if (s.equals(today_1)) {
                    NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                    ss = "" + gugun2.item(0).getChildNodes().item(0).getNodeValue();
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    sss = "" + gugun.item(0).getChildNodes().item(0).getNodeValue();
                    if (ss.equals("0600")) {
                        weather.T_PTY_1 = sss;
                    }
                    if (ss.equals("0900")) {
                        weather.T_PTY_2 = sss;
                    }
                    if (ss.equals("1200")) {
                        weather.T_PTY_3 = sss;
                    }
                    if (ss.equals("1800")) {
                        weather.T_PTY_4 = sss;
                    }
                    if (ss.equals("2100")) {
                        weather.T_PTY_5 = sss;
                    }
                }
            }

            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("T3H")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s = "" + gugun1.item(0).getChildNodes().item(0).getNodeValue();

                if (s.equals(today)) {
                    NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                    ss = "" + gugun2.item(0).getChildNodes().item(0).getNodeValue();
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    sss = "" + gugun.item(0).getChildNodes().item(0).getNodeValue();
                    if (ss.equals("0600")) {
                        weather.T3H_1 = sss;
                    }
                    if (ss.equals("0900")) {
                        weather.T3H_2 = sss;
                    }
                    if (ss.equals("1500")) {
                        weather.T3H_3 = sss;
                    }
                    if (ss.equals("2100")) {
                        weather.T3H_4 = sss;
                    }
                } else if (s.equals(today_1)) {
                    NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                    ss = "" + gugun2.item(0).getChildNodes().item(0).getNodeValue();
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    sss = "" + gugun.item(0).getChildNodes().item(0).getNodeValue();
                    if (ss.equals("0000")) {
                        weather.T3H_5 = sss;
                    }
                    if (ss.equals("0600")) {
                        weather.T_T3H_1 = sss;
                    }
                    if (ss.equals("0900")) {
                        weather.T_T3H_2 = sss;
                    }
                    if (ss.equals("1500")) {
                        weather.T_T3H_3 = sss;
                    }
                    if (ss.equals("2100")) {
                        weather.T_T3H_4 = sss;
                    }
                } else if (s.equals(today_2)) {
                    NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                    ss = "" + gugun2.item(0).getChildNodes().item(0).getNodeValue();
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    sss = "" + gugun.item(0).getChildNodes().item(0).getNodeValue();
                    if (ss.equals("0000")) {
                        weather.T_T3H_5 = sss;
                    }
                }
            }
        }
    }

    // 0500 동네 예보 - 내일, 모레 최저 최고온도
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
        String sss = "";
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
                    weather.TMN1 = gugun3.item(0).getChildNodes().item(0).getNodeValue();
                }
                if (ss.equals(today_2)) {
                    NodeList gugun3 = fstElmnt.getElementsByTagName("fcstValue");
                    weather.TMN2 = gugun3.item(0).getChildNodes().item(0).getNodeValue();
                }
            }
            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("TMX")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                ss = "" + gugun1.item(0).getChildNodes().item(0).getNodeValue() + "";
                if (ss.equals(today_1)) {
                    NodeList gugun3 = fstElmnt.getElementsByTagName("fcstValue");
                    weather.TMX1 = gugun3.item(0).getChildNodes().item(0).getNodeValue();
                }
                if (ss.equals(today_2)) {
                    NodeList gugun3 = fstElmnt.getElementsByTagName("fcstValue");
                    weather.TMX2 = gugun3.item(0).getChildNodes().item(0).getNodeValue();
                }
            }

            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("SKY")) {
                NodeList gugun2 = fstElmnt.getElementsByTagName("fcstDate");
                sss = gugun2.item(0).getChildNodes().item(0).getNodeValue() + "";

                if (sss.equals(today_2)) {
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
                        weather.SKY2_1 = ss;
                    }
                    if (s.equals("0900")) {
                        weather.SKY2_2 = ss;
                    }
                    if (s.equals("1200")) {
                        weather.SKY2_3 = ss;
                    }
                    if (s.equals("1800")) {
                        weather.SKY2_4 = ss;
                    }
                    if (s.equals("2100")) {
                        weather.SKY2_5 = ss;
                    }
                }

            }

            if (idx.item(0).getChildNodes().item(0).getNodeValue().equals("PTY")) {
                NodeList gugun1 = fstElmnt.getElementsByTagName("fcstDate");
                s = "" + gugun1.item(0).getChildNodes().item(0).getNodeValue();

                if (s.equals(today_2)) {
                    NodeList gugun2 = fstElmnt.getElementsByTagName("fcstTime");
                    ss = "" + gugun2.item(0).getChildNodes().item(0).getNodeValue();
                    NodeList gugun = fstElmnt.getElementsByTagName("fcstValue");
                    sss = "" + gugun.item(0).getChildNodes().item(0).getNodeValue();
                    if (ss.equals("0600")) {
                        weather.F_PTY_1 = sss;
                    }
                    if (ss.equals("0900")) {
                        weather.F_PTY_2 = sss;
                    }
                    if (ss.equals("1200")) {
                        weather.F_PTY_3 = sss;
                    }
                    if (ss.equals("1800")) {
                        weather.F_PTY_4 = sss;
                    }
                    if (ss.equals("2100")) {
                        weather.F_PTY_5 = sss;
                    }
                }
            }
        }
    }

    //지역별 해달 출몰시각 조회
    public void haedal() {
        String today = AsyncTask1.doYearMonthDay();

        boolean initem = false, inlocdate = false, inlocation = false, insunrise = false, insuntransit = false, insunset = false, inmoonrise = false, inmoontransit = false, inmoonset = false;
        boolean incivilm = false, incivile = false, innautm = false, innaute = false, inastm = false, inaste = false, inlongitude = false, inlatitude = false;

        String locdate = null, location = null, sunrise = null, suntransit = null, sunset = null, moonrise = null, moontransit = null, moonset = null;
        String civilm = null, civile = null, nautm = null, naute = null, astm = null, aste = null, longitude = null, latitude = null;

        try {
            URL url = new URL("http://apis.data.go.kr/B090041/openapi/service/RiseSetInfoService/getAreaRiseSetInfo?location=%EC%B6%98%EC%B2%9C&locdate=" +
                    today
                    + "&ServiceKey=GRaMjomvOF4JA093y1NSWvnbZBDrOYMVpmeCfn7qS0STtnV2Es%2Bz1SiJkMs4ql1hUGy%2BwsbYptj2JPrb2VGh%2BQ%3D%3D");

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("locdate")) {
                            inlocdate = true;
                        }
                        if (parser.getName().equals("location")) {
                            inlocation = true;
                        }
                        if (parser.getName().equals("longitude")) {
                            inlongitude = true;
                        }
                        if (parser.getName().equals("latitude")) {
                            inlatitude = true;
                        }
                        if (parser.getName().equals("sunrise")) {
                            insunrise = true;
                        }
                        if (parser.getName().equals("suntransit")) {
                            insuntransit = true;
                        }
                        if (parser.getName().equals("sunset")) {
                            insunset = true;
                        }
                        if (parser.getName().equals("moonrise")) {
                            inmoonrise = true;
                        }
                        if (parser.getName().equals("moontransit")) {
                            inmoontransit = true;
                        }
                        if (parser.getName().equals("moonset")) {
                            inmoonset = true;
                        }
                        if (parser.getName().equals("civilm")) {
                            incivilm = true;
                        }
                        if (parser.getName().equals("civile")) {
                            incivile = true;
                        }
                        if (parser.getName().equals("nautm")) {
                            innautm = true;
                        }
                        if (parser.getName().equals("naute")) {
                            innaute = true;
                        }
                        if (parser.getName().equals("astm")) {
                            inastm = true;
                        }
                        if (parser.getName().equals("aste")) {
                            inaste = true;
                        }

                        if (parser.getName().equals("message")) { //message 만나면 에러 출력
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (inlocdate) {
                            locdate = parser.getText();
                            inlocdate = false;
                        }
                        if (inlocation) {
                            location = parser.getText();
                            inlocation = false;
                        }
                        if (inlongitude) {
                            longitude = parser.getText();
                            inlongitude = false;
                        }
                        if (inlatitude) {
                            latitude = parser.getText();
                            inlatitude = false;
                        }
                        if (insunrise) {
                            sunrise = parser.getText();
                            weather.sunrise = sunrise;
                            insunrise = false;
                        }
                        if (insuntransit) {
                            suntransit = parser.getText();
                            weather.suntransit = suntransit;
                            insuntransit = false;
                        }
                        if (insunset) {
                            sunset = parser.getText();
                            weather.sunset = sunset;
                            insunset = false;
                        }
                        if (inmoonrise) {
                            moonrise = parser.getText();
                            weather.moonrise = moonrise;
                            inmoonrise = false;
                        }
                        if (inmoontransit) {
                            moontransit = parser.getText();
                            weather.moontransit = moontransit;
                            inmoontransit = false;
                        }
                        if (inmoonset) {
                            moonset = parser.getText();
                            weather.moonset = moonset;
                            inmoonset = false;
                        }
                        if (incivilm) {
                            civilm = parser.getText();
                            weather.civilm = civilm;
                            incivilm = false;
                        }

                        if (incivile) {
                            civile = parser.getText();
                            weather.civile = civile;
                            incivile = false;
                        }

                        if (innautm) {
                            nautm = parser.getText();
                            weather.nautm = nautm;
                            innautm = false;
                        }
                        if (innaute) {
                            naute = parser.getText();
                            weather.naute = naute;
                            innaute = false;
                        }
                        if (inastm) {
                            astm = parser.getText();
                            weather.astm = astm;
                            inastm = false;
                        }
                        if (inaste) {
                            aste = parser.getText();
                            weather.aste = aste;
                            inaste = false;
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
        }
    }

    //중기예보
    public void midterm() {
        String nowtime = AsyncTask1.doTime();
        int a = Integer.parseInt(nowtime);
        String today = null;

        if ((a >= 600) && (a <= 1800)) {
            today = AsyncTask1.doYearMonthDay();
            nowtime = "0600";
        } else if (a < 600) {
            today = AsyncTask1.doyes();
            nowtime = "1800";
        } else if (a > 1800) {
            today = AsyncTask1.doYearMonthDay();
            nowtime = "1800";
        }

        boolean intaMax3 = false, intaMin3 = false, intaMax4 = false, intaMin4 = false, intaMax5 = false, intaMin5 = false, intaMax6 = false, intaMin6 = false;
        boolean intaMax7 = false, intaMin7 = false, intaMax8 = false, intaMin8 = false, intaMax9 = false, intaMin9 = false, intaMax10 = false, intaMin10 = false;

        String taMax3 = null, taMin3 = null, taMax4 = null, taMin4 = null, taMax5 = null, taMin5 = null, taMax6 = null, taMin6 = null;
        String taMax7 = null, taMin7 = null, taMax8 = null, taMin8 = null, taMax9 = null, taMin9 = null, taMax10 = null, taMin10 = null;

        try {
            URL url = new URL("http://newsky2.kma.go.kr/service/MiddleFrcstInfoService/getMiddleTemperature"
                    + "?ServiceKey=GRaMjomvOF4JA093y1NSWvnbZBDrOYMVpmeCfn7qS0STtnV2Es%2Bz1SiJkMs4ql1hUGy%2BwsbYptj2JPrb2VGh%2BQ%3D%3D&regId=11D10301&tmFc=" +
                    today +
                    nowtime +
                    "&numOfRows=1&pageNo=1");

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("taMax3")) {
                            intaMax3 = true;
                        }
                        if (parser.getName().equals("taMin3")) {
                            intaMin3 = true;
                        }
                        if (parser.getName().equals("taMax4")) {
                            intaMax4 = true;
                        }
                        if (parser.getName().equals("taMin4")) {
                            intaMin4 = true;
                        }
                        if (parser.getName().equals("taMax5")) {
                            intaMax5 = true;
                        }
                        if (parser.getName().equals("taMin5")) {
                            intaMin5 = true;
                        }
                        if (parser.getName().equals("taMax6")) {
                            intaMax6 = true;
                        }
                        if (parser.getName().equals("taMin6")) {
                            intaMin6 = true;
                        }
                        if (parser.getName().equals("taMax7")) {
                            intaMax7 = true;
                        }
                        if (parser.getName().equals("taMin7")) {
                            intaMin7 = true;
                        }
                        if (parser.getName().equals("taMax8")) {
                            intaMax8 = true;
                        }
                        if (parser.getName().equals("taMin8")) {
                            intaMin8 = true;
                        }
                        if (parser.getName().equals("taMax9")) {
                            intaMax9 = true;
                        }
                        if (parser.getName().equals("taMin9")) {
                            intaMin9 = true;
                        }
                        if (parser.getName().equals("taMax10")) {
                            intaMax10 = true;
                        }
                        if (parser.getName().equals("taMin10")) {
                            intaMin10 = true;
                        }

                        if (parser.getName().equals("message")) { //message 만나면 에러 출력
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (intaMax3) {
                            taMax3 = parser.getText();
                            weather.TMX3 = taMax3;
                            intaMax3 = false;
                        }
                        if (intaMin3) {
                            taMin3 = parser.getText();
                            weather.TMN3 = taMin3;
                            intaMin3 = false;
                        }
                        if (intaMax4) {
                            taMax4 = parser.getText();
                            weather.TMX4 = taMax4;
                            intaMax4 = false;
                        }
                        if (intaMin4) {
                            taMin4 = parser.getText();
                            weather.TMN4 = taMin4;
                            intaMin4 = false;
                        }
                        if (intaMax5) {
                            taMax5 = parser.getText();
                            weather.TMX5 = taMax5;
                            intaMax5 = false;
                        }
                        if (intaMin5) {
                            taMin5 = parser.getText();
                            weather.TMN5 = taMin5;
                            intaMin5 = false;
                        }
                        if (intaMax6) {
                            taMax6 = parser.getText();
                            weather.TMX6 = taMax6;
                            intaMax6 = false;
                        }
                        if (intaMin6) {
                            taMin6 = parser.getText();
                            weather.TMN6 = taMin6;
                            intaMin6 = false;
                        }
                        if (intaMax7) {
                            taMax7 = parser.getText();
                            weather.TMX7 = taMax7;
                            intaMax7 = false;
                        }
                        if (intaMin7) {
                            taMin7 = parser.getText();
                            weather.TMN7 = taMin7;
                            intaMin7 = false;
                        }
                        if (intaMax8) {
                            taMax8 = parser.getText();
                            weather.TMX8 = taMax8;
                            intaMax8 = false;
                        }
                        if (intaMin8) {
                            taMin8 = parser.getText();
                            weather.TMN8 = taMin8;
                            intaMin8 = false;
                        }
                        if (intaMax9) {
                            taMax9 = parser.getText();
                            weather.TMX9 = taMax9;
                            intaMax9 = false;
                        }
                        if (intaMin9) {
                            taMin9 = parser.getText();
                            weather.TMN9 = taMin9;
                            intaMin9 = false;
                        }
                        if (intaMax10) {
                            taMax10 = parser.getText();
                            weather.TMX10 = taMax10;
                            intaMax10 = false;
                        }
                        if (intaMin10) {
                            taMin10 = parser.getText();
                            weather.TMN10 = taMin10;
                            intaMin10 = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
        }

        boolean inwf3Am = false, inwf3Pm = false, inwf4Am = false, inwf4Pm = false, inwf5Am = false, inwf5Pm = false, inwf6Am = false, inwf6Pm = false;
        boolean inwf7Am = false, inwf7Pm = false, inwf8 = false, inwf9 = false, inwf10 = false;

        String wf3Am = null, wf3Pm = null, wf4Am = null, wf4Pm = null, wf5Am = null, wf5Pm = null, wf6Am = null, wf6Pm = null;
        String wf7Am = null, wf7Pm = null, wf8 = null, wf9 = null, wf10 = null;

        try {
            URL url = new URL("http://newsky2.kma.go.kr/service/MiddleFrcstInfoService/getMiddleLandWeather"
                    + "?ServiceKey=GRaMjomvOF4JA093y1NSWvnbZBDrOYMVpmeCfn7qS0STtnV2Es%2Bz1SiJkMs4ql1hUGy%2BwsbYptj2JPrb2VGh%2BQ%3D%3D&regId=11B00000&tmFc=" +
                    today +
                    nowtime +
                    "&numOfRows=1&pageNo=1");

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("wf3Am")) {
                            inwf3Am = true;
                        }
                        if (parser.getName().equals("wf3Pm")) {
                            inwf3Pm = true;
                        }
                        if (parser.getName().equals("wf4Am")) {
                            inwf4Am = true;
                        }
                        if (parser.getName().equals("wf4Pm")) {
                            inwf4Pm = true;
                        }
                        if (parser.getName().equals("wf5Am")) {
                            inwf5Am = true;
                        }
                        if (parser.getName().equals("wf5Pm")) {
                            inwf5Pm = true;
                        }
                        if (parser.getName().equals("wf6Am")) {
                            inwf6Am = true;
                        }
                        if (parser.getName().equals("wf6Pm")) {
                            inwf6Pm = true;
                        }
                        if (parser.getName().equals("wf7Am")) {
                            inwf7Am = true;
                        }
                        if (parser.getName().equals("wf7Pm")) {
                            inwf7Pm = true;
                        }
                        if (parser.getName().equals("wf8")) {
                            inwf8 = true;
                        }
                        if (parser.getName().equals("wf9")) {
                            inwf9 = true;
                        }
                        if (parser.getName().equals("wf10")) {
                            inwf10 = true;
                        }

                        if (parser.getName().equals("message")) { //message 만나면 에러 출력
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (inwf3Am) {
                            wf3Am = parser.getText();
                            weather.SKY3 = wf3Am;
                            inwf3Am = false;
                        }
                        if (inwf3Pm) {
                            wf3Pm = parser.getText();
                            inwf3Pm = false;
                        }
                        if (inwf4Am) {
                            wf4Am = parser.getText();
                            weather.SKY4 = wf4Am;
                            inwf4Am = false;
                        }
                        if (inwf4Pm) {
                            wf4Pm = parser.getText();
                            inwf4Pm = false;
                        }
                        if (inwf5Am) {
                            wf5Am = parser.getText();
                            weather.SKY5 = wf5Am;
                            inwf5Am = false;
                        }
                        if (inwf5Pm) {
                            wf5Pm = parser.getText();
                            inwf5Pm = false;
                        }
                        if (inwf6Am) {
                            wf6Am = parser.getText();
                            weather.SKY6 = wf6Am;
                            inwf6Am = false;
                        }
                        if (inwf6Pm) {
                            wf6Pm = parser.getText();
                            inwf6Pm = false;
                        }
                        if (inwf7Am) {
                            wf7Am = parser.getText();
                            weather.SKY7 = wf7Am;
                            inwf7Am = false;
                        }
                        if (inwf7Pm) {
                            wf7Pm = parser.getText();
                            inwf7Pm = false;
                        }
                        if (inwf8) {
                            wf8 = parser.getText();
                            weather.SKY8 = wf8;
                            inwf8 = false;
                        }
                        if (inwf9) {
                            wf9 = parser.getText();
                            weather.SKY9 = wf9;
                            inwf9 = false;
                        }
                        if (inwf10) {
                            wf10 = parser.getText();
                            weather.SKY10 = wf10;
                            inwf10 = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
        }
    }
}