package com.example.asdzx.weatherand2;

public class weather {
    //미세먼지
    String pm10 = null;
    String pm25 = null;

    //초단기 실황 (지금 현재 날씨)
    String T1H = null; //현재 기온
    String REH = null; //현재 습도
    String PTY = null; //현재 강수형태
    String WDF = null; //현재 풍속
    String VEC = null; //현재 풍향

    //일주일 날씨 (해,구름 등) - 중기예보, 동네예보- 1200
    String SKY1 = null;
    String SKY2 = null;
    String SKY3 = null;
    String SKY4 = null;
    String SKY5 = null;
    String SKY6 = null;
    String SKY7 = null;
    String SKY8 = null;
    String SKY9 = null;
    String SKY10 = null;

    //일주일 최고온도 (중기예보, 동네예보- 0500)
    String TMX1 = null;
    String TMX2 = null;
    String TMX3 = null;
    String TMX4 = null;
    String TMX5 = null;
    String TMX6 = null;
    String TMX7 = null;
    String TMX8 = null;
    String TMX9 = null;
    String TMX10 = null;

    //일주일 최저온도 (중기예보, 동네예보-0200은 오늘내일 최저 0500은 모레최저)
    String TMN1 = null;
    String TMN2 = null;
    String TMN3 = null;
    String TMN4 = null;
    String TMN5 = null;
    String TMN6 = null;
    String TMN7 = null;
    String TMN8 = null;
    String TMN9 = null;
    String TMN10 = null;

    //오늘 날씨 (새벽, 아침, 점심, 저녁, 밤) - 동네예보
    String SKY0_1 = null;
    String SKY0_2 = null;
    String SKY0_3 = null;
    String SKY0_4 = null;
    String SKY0_5 = null;

    //오늘 온도 (새벽, 아침, 점심, 저녁, 밤) - 동네예보
    String T3H_1 = null;
    String T3H_2 = null;
    String T3H_3 = null;
    String T3H_4 = null;
    String T3H_5 = null;

    //오늘 습도 (새벽, 아침, 점심, 저녁, 밤) - 동네예보
    String REH_1 = null;
    String REH_2 = null;
    String REH_3 = null;
    String REH_4 = null;
    String REH_5 = null;

    //오늘 풍속 (새벽, 아침, 점심, 저녁, 밤) - 동네예보
    String WSD_1 = null;
    String WSD_2 = null;
    String WSD_3 = null;
    String WSD_4 = null;
    String WSD_5 = null;

    //내일 날씨 (새벽, 아침, 점심, 저녁, 밤) - 동네예보
    String SKY1_1 = null;
    String SKY1_2 = null;
    String SKY1_3 = null;
    String SKY1_4 = null;
    String SKY1_5 = null;

    //내일 온도 (새벽, 아침, 점심, 저녁, 밤) - 동네예보
    String T_T3H_1 = null;
    String T_T3H_2 = null;
    String T_T3H_3 = null;
    String T_T3H_4 = null;
    String T_T3H_5 = null;

    //내일 습도 (새벽, 아침, 점심, 저녁, 밤) - 동네예보
    String T_REH_1 = null;
    String T_REH_2 = null;
    String T_REH_3 = null;
    String T_REH_4 = null;
    String T_REH_5 = null;

    //내일 풍속 (새벽, 아침, 점심, 저녁, 밤) - 동네예보
    String T_WSD_1 = null;
    String T_WSD_2 = null;
    String T_WSD_3 = null;
    String T_WSD_4 = null;
    String T_WSD_5 = null;

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPTY(String PTY) {
        this.PTY = PTY;
    }

    public void setREH(String REH) {
        this.REH = REH;
    }

    public void setT1H(String t1H) {
        T1H = t1H;
    }

    public void setVEC(String VEC) {
        this.VEC = VEC;
    }

    public void setWDF(String WDF) {
        this.WDF = WDF;
    }

    public String getPTY() {
        return PTY;
    }

    public String getREH() {
        return REH;
    }

    public String getT1H() {
        return T1H;
    }

    public String getVEC() {
        return VEC;
    }

    public String getWDF() {
        return WDF;
    }
}