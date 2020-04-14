package com.example.app_005.Tool;

import android.util.Log;

import com.example.app_005.Internet.InternetTool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import static android.support.constraint.Constraints.TAG;

public class TradingCollectTool {
    String stringUSDName="{\"filter\":[{\"left\":\"name\",\"operation\":\"nempty\"},{\"left\":\"name,description\",\"operation\":\"match\",\"right\":\"EURUSD|USDJPY|GBPUSD|EURGBP|EURJPY|GBPJPY|AUDUSD|USDCAD|USDCHF|NZDUSD|EURCHF\"}],\"options\":{\"lang\":\"en\"},\"symbols\":{\"query\":{\"types\":[\"forex\"]},\"tickers\":[]},\"columns\":[\"bid\",\"ask\",\"high\",\"low\",\"close\",\"change\",\"change_abs\"],\"sort\":{\"sortBy\":\"name\",\"sortOrder\":\"asc\"},\"range\":[0,150]}";//"{\"filter\":[{\"left\":\"forex_priority\",\"operation\":\"nempty\"},{\"left\":\"sector\",\"operation\":\"equal\",\"right\":\"Major\"}],\"options\":{\"lang\":\"en\"},\"symbols\":{\"query\":{\"types\":[\"forex\"]},\"tickers\":[]},\"columns\":[\"name\",\"close\",\"change\",\"change_abs\",\"bid\",\"ask\",\"high\",\"low\",\"Recommend.All\",\"description\",\"name\",\"subtype\",\"update_mode\",\"pricescale\",\"minmov\",\"fractional\",\"minmove2\"],\"sort\":{\"sortBy\":\"forex_priority\",\"sortOrder\":\"asc\"},\"range\":[0,150]}";
    String stringMinorName="EURGBP|EURJPY|GBPJPY|EURCHF";
    //"FX_IDC:EURUSD","FX_IDC:USDJPY","FX_IDC:GBPUSD","FX_IDC:AUDUSD","FX_IDC:USDCAD","FX_IDC:USDCHF","FX_IDC:NZDUSD"
    //"FX_IDC:EURGBP","FX_IDC:EURJPY","FX_IDC:GBPJPY","FX_IDC:EURCHF"
    String stringRecommend="\"Recommend.Other\",\"Recommend.All|1\",\"Recommend.MA|1\"";
    //EURUSD|USDJPY|GBPUSD|EURGBP|EURJPY|GBPJPY|AUDUSD|USDCAD|USDCHF|NZDUSD|EURCHF";
    String stringLineFXName="\"FX_IDC:EURUSD\",\"FX_IDC:USDJPY\",\"FX_IDC:GBPUSD\",\"FX_IDC:EURGBP\",\"FX_IDC:EURJPY\",\"FX_IDC:GBPJPY\",\"FX_IDC:AUDUSD\",\"FX_IDC:USDCAD\",\"FX_IDC:USDCHF\",\"FX_IDC:NZDUSD\",\"FX_IDC:EURCHF\"";
//    "Recommend.All","Recommend.Other","Recommend.MA","RSI","Stoch.K","Stoch.D","Stoch.RSI.K","Stoch.RSI.D","MACD.macd","MACD.signal","ADX","ADX+DI","ADX-DI","W.R","CCI20","ROC","BBPower","Rec.BBPower","AO","Mom"
    String stringLine="\"Recommend.All|1\",\"Recommend.Other|1\",\"Recommend.MA|1\",\"RSI|1\",\"Stoch.K|1\",\"Stoch.D|1\",\"Stoch.RSI.K|1\",\"Stoch.RSI.D|1\",\"MACD.macd|1\",\"MACD.signal|1\",\"ADX|1\",\"ADX+DI|1\",\"ADX-DI|1\",\"W.R|1\"" +
            ",\"CCI20|1\",\"ROC|1\",\"BBPower|1\",\"Rec.BBPower|1\",\"AO|1\",\"Mom|1\"";
    String stringCCLine="\"ATR|1\",\"UO|1\"";
    String stringMAName="\"SMA5|1\",\"SMA10|1\",\"SMA20|1\",\"SMA30|1\",\"SMA50|1\",\"SMA100|1\",\"SMA200|1\",\"bid\",\"EMA5|1\",\"EMA10|1\",\"EMA20|1\",\"EMA30|1\",\"EMA50|1\",\"EMA100|1\",\"EMA200|1\",\"bid\"";
    String stringPivotName=" \"Rec.Ichimoku|1\",\"Ichimoku.BLine|1\",\"bid\",\"Rec.VWMA|1\",\"VWMA|1\",\"bid\",\"Rec.HullMA9|1\",\"HullMA9|1\",\"bid\",\"Pivot.M.Classic.S3|1\",\"Pivot.M.Classic.S2|1\",\"Pivot.M.Classic.S1|1\",\"Pivot.M.Classic.Middle|1\",\"Pivot.M.Classic.R1|1\",\"Pivot.M.Classic.R2|1\",\"Pivot.M.Classic.R3|1\",\"bid\",\"Pivot.M.Fibonacci.S3|1\",\"Pivot.M.Fibonacci.S2|1\",\"Pivot.M.Fibonacci.S1|1\",\"Pivot.M.Fibonacci.Middle|1\",\"Pivot.M.Fibonacci.R1|1\",\"Pivot.M.Fibonacci.R2|1\",\"Pivot.M.Fibonacci.R3|1\",\"bid\",\"Pivot.M.Camarilla.S3|1\",\"Pivot.M.Camarilla.S2|1\",\"Pivot.M.Camarilla.S1|1\",\"Pivot.M.Camarilla.Middle|1\",\"Pivot.M.Camarilla.R1|1\",\"Pivot.M.Camarilla.R2|1\",\"Pivot.M.Camarilla.R3|1\",\"bid\",\"Pivot.M.Woodie.S3|1\",\"Pivot.M.Woodie.S2|1\",\"Pivot.M.Woodie.S1|1\",\"Pivot.M.Woodie.Middle|1\",\"Pivot.M.Woodie.R1|1\",\"Pivot.M.Woodie.R2|1\",\"Pivot.M.Woodie.R3|1\",\"bid\",\"Pivot.M.Demark.S1|1\",\"Pivot.M.Demark.Middle|1\",\"Pivot.M.Demark.R1|1\",\"bid\"";
    public TradingCollectTool(){

        if(false)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                try {
//                for(int i=0;i<9999;i++)
//                    {
//                        String string_A=colllectQuoLineData(SkillQuoName.EURUSD);
//                        ////]Log.w(this.toString(), "TradingCollectToolA0: "+"="+i +"="+string_A);
//
//                            Thread.sleep(5000);
//
//                    }
//                    } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                        try {
                            while (true) {

                                float[][] floats_A = collectQuoUSDData();
                                float[][] floats_B = colllectQuoLineData(stringLineFXName);
                                String string_A = "";
                                for (int i = 0; i < floats_A[0].length; i++) {
                                    string_A += "=" + floats_A[0][i];

                                }
                                string_A += "|||";
                                for (int i = 0; i < floats_B[0].length; i++) {
                                    string_A += "=" + floats_B[0][i];
                                }
                                //////]Log.w(TAG, "TradingCollectToolP1: " + "=" + string_A);
                                Thread.sleep(5000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //QuoHtGson quoHtGson_A=new Gson().fromJson(string_A,QuoHtGson.class);
                        //////]Log.w(TAG, "TradingCollectToolP2: "+"="+ (quoHtGson_A!=null));
                    }
                }).start();
            }


    }
    public float[][] collectQuoTradingData(){

        //["bid","ask","high","low","close","change","change_abs"]
        //[出价，买价，高,低,关闭，百分巴仙,百分数]
        return collectQuoUSDData();

    }
    public float[][] collectQuoTradingLine(){
        return colllectQuoLineData(stringLineFXName);

    }
    float[][] colllectQuoLineData(String string_Name){
        String string_Data=null;
        String string_LineName="{\"symbols\":{\"tickers\":["+string_Name+"],\"query\":{\"types\":[\"forex\"]}},\"columns\":["+stringLine+","+stringMAName+","+stringPivotName+"]}";
        ////]Log.w(TAG, "TradingCollectToolP1: "+"="+ string_LineName);
        string_Data= getHttpPostTradingData(string_LineName);
        String str_M="https://translate.google.com.my/translate_a/single?client=webapp&sl=en&tl=zh-CN&hl=zh-CN&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&source=sel&tk=312208.157949&q=today%27s%20game";
        ////]Log.w(TAG, "colllectQuoLineDataGG0: "+"="+getHttpPostTradingData(str_M) );
        for(int i=0;i<90;i++)
        {
            if(string_Data!=null)
            {
                break;
            }else {
                string_Data= getHttpPostTradingData(string_LineName);
            }
        }
        //]Log.w(TAG, "colllectQuoLineDataH1: "+"="+ string_Data);
        if(string_Data==null)
            {
                return null;

            }
        return ccQuoLineData(string_Data);
    }
    float[][] ccQuoLineData(String string_Data){
        float[][] floats_rt=new float[11][];
        int int_A=0;
        int int_B=0;
        ArrayList<int[]> arrayList_A=new ArrayList<>();
        StringBuilder stringBuilder_A=new StringBuilder(string_Data);
        int_A=stringBuilder_A.indexOf("[");
        for(int i=0;i<11;i++)
            {
                int_A=stringBuilder_A.indexOf("{",int_A+1);
                int_B=stringBuilder_A.indexOf("}",int_A+1);
                if(int_A==-1) {
                    break;
                }
                //////]Log.w(this.toString(), "ccQuoLineDataV0: "+"="+i +"="+int_A +"="+int_B );
                floats_rt[getQuoHtLintInt(i)]= ccQuoLineString(stringBuilder_A,int_A,int_B);
            }
        return floats_rt;
    }
    float[] ccQuoLineString(StringBuilder stringBuilder, int int_A, int int_B){
        float[] floats_rt=null;
        String string_A=stringBuilder.substring(int_A,int_B);
        //////]Log.w(TAG, "getQuoJsStringC2 "+"="+ string_A);

        int int_FloatB=string_A.indexOf("d\":[");
        int int_FloatB2=string_A.indexOf("]",int_FloatB+4);
        String string_B=string_A.substring(int_FloatB+3,int_FloatB2);
        boolean bool_A=true;
        int_FloatB=1;
        int_FloatB2=0;
        floats_rt=new float[82];
        if(true)
        {
            int int_NameA=string_A.indexOf("DC:");
            //////]Log.w(TAG, "getQuoJsStringC3: "+"="+ int_NameA);
            String string_Name=string_A.substring(int_NameA+3,int_NameA+9);
            //////]Log.w(TAG, "getQuoJsStringC3A: "+"="+ string_Name);
            floats_rt[0]=ccQuoLineName(string_Name);
        }
        int intCC=0;

        for(int i=1;i<82;i++)
            {
                intCC=i;
                int_FloatB2=string_B.indexOf(",",int_FloatB);

                if(int_FloatB2==-1){
                   // ////]Log.w(this.toString(), "ccQuoLineString:C1 "+"="+ string_B.substring(int_FloatB));
                    if(true)
                        {
                          //  //]Log.w(this.toString(), "ccQuoLineString: T0"+"="+string_B );
                            ////]Log.w(this.toString(), "ccQuoLineString: T1"+"="+int_FloatB +"="+ int_FloatB2 +"="+string_B.length());
                        }
                    String string_Y=string_B.substring(int_FloatB,string_B.length());
                    if(Character.isDigit(string_Y.charAt(0)))
                    {
                        floats_rt[i]=Float.valueOf(string_Y);

                    }else {
                        floats_rt[i]=0;
                    }
                   // floats_rt[i]=Float.valueOf(string_B.substring(int_FloatB));
                    break;
                }
               // ////]Log.w(this.toString(), "ccQuoLineString:C0 "+"="+ string_B.substring(int_FloatB,int_FloatB2));
                String string_C=string_B.substring(int_FloatB,int_FloatB2);
                if(Character.isDigit(string_C.charAt(0))||string_C.charAt(0)=='-')
                    {
                        ////]Log.w(this.toString(), "ccQuoLineString: E4"+"="+ string_B.substring(int_FloatB,int_FloatB2));
                        floats_rt[i]=Float.valueOf(string_B.substring(int_FloatB,int_FloatB2));
                        ////]Log.w(TAG, "ccQuoLineStringG0: "+"="+floats_rt[i] +"="+string_B.substring(int_FloatB,int_FloatB2));

                    }else {
                    ////]Log.w(TAG, "ccQuoLineStringG1: "+"="+string_C );
                    floats_rt[i]=0;
                }
                int_FloatB=int_FloatB2+1;

            }
        if(floats_rt[0]==0)
            {
                ////]Log.w(TAG, "ccQuoLineStringJ0: "+"="+ floats_rt[1]+"="+ floats_rt[2]+"="+ floats_rt[3]);

            }
       // ////]Log.w(TAG, "ccQuoLineStringG0: "+"="+ intCC);
        return floats_rt;
    }
    int ccQuoLineName(String string_Name){
        int int_rt=0;
        //EURUSD|USDJPY|GBPUSD|EURGBP|EURJPY|GBPJPY|AUDUSD|USDCAD|USDCHF|NZDUSD|EURCHF
        switch(string_Name){
            case "EURUSD":
                return 0;
            case "USDJPY":
                return 1;
            case "GBPUSD":
                return 2;
            case "EURGBP":
                return 3;
            case "EURJPY":
                return 4;
            case "GBPJPY":
                return 5;
            case "AUDUSD":
                return 6;
            case "USDCAD":
                return 7;
            case "USDCHF":
                return 8;
            case "NZDUSD":
                return 9;
            case "EURCHF":
                return 10;
            default:
                break;
        }
        return int_rt;
    }
    class QuoHtDa{
        TimeOD timeOD;
        public float[][] floatsHtDa;
        public QuoHtDa(TimeOD timeOD){
            floatsHtDa=new float[11][];
        }
        public void getHtDataQuo(float[][] floats_Data){
            for(int i=0;i<floats_Data.length;i++)
                {
                    for(int e=0;e<0;e++)
                        {
                            floatsHtDa[i][e]=floats_Data[i][e];
                        }

                }
        }

    }
    //"\"FX_IDC:EURUSD\",\"FX_IDC:USDJPY\",\"FX_IDC:GBPUSD\",\"FX_IDC:EURGBP\",\"FX_IDC:EURJPY\"," +
     // "\"FX_IDC:GBPJPY\",\"FX_IDC:AUDUSD\",\"FX_IDC:USDCAD\",\"FX_IDC:USDCHF\",\"FX_IDC:NZDUSD\",\"FX_IDC:EURCHF\"";
    int getQuoHtDataInt(int intAA){
        switch(intAA){
            case 0://EURGBP
                return 6;
            case 1://USDCHF
                return 10;
            case 2://EURJPY
                return 3;
            case 3://AUDUSD
                return 4;
            case 4://GBPJPY
                return 0;
            case 5://GBPUSD
                return 5;
            case 6://EURUSD
                return 2;
            case 7://NZDUSD
                return 9;
            case 8://EURCHF
                return 7;
            case 9://USDJPY
                return 8;
            case 10://USDCAD
                return 1;
            default:
                return 0;
        }
    }
    int getQuoHtLintInt(int intAA){
        switch(intAA){
            case 0://EURGBP
                return 3;
            case 1://USDCHF
                return 8;
            case 2://EURJPY
                return 4;
            case 3://AUDUSD
                return 6;
            case 4://GBPJPY
                return 5;
            case 5://GBPUSD
                return 2;
            case 6://EURUSD
                return 0;
            case 7://NZDUSD
                return 9;
            case 8://EURCHF
                return 10;
            case 9://USDJPY
                return 1;
            case 10://USDCAD
                return 7;
            default:
                return 0;
        }
    }
    class QuoHtGson{
        data[] data;

        class data{
            String s;
            float[] d;
        }

    }
    float[][] collectQuoUSDData(){
        String string_Url=stringUSDName;
        ////]Log.w(TAG, "collectQuoUSDDataD0: "+"="+stringUSDName );
        String string_Data=getHttpPostTradingData(string_Url);
        for(int i=0;i<90;i++)
            {
            if(string_Data!=null)
                {
                break;
                }else {
                string_Data=getHttpPostTradingData(string_Url);
            }
            }
        if(string_Data==null)
            {
                //]Log.w(TAG, "collectQuoUSDDataE1: "+"="+false );
                return null;

            }else {
            //]Log.w(TAG, "collectQuoUSDDataE1: "+"="+true  +"="+string_Data);

        }
        float[][] floats_rt=ccQuoHtData(string_Data);
        return floats_rt;
    }
    float[][] ccQuoHtData(String stringCCLine_Data){
        float[][] floats_rt=new float[11][];
        int int_A=0;
        int int_B=1;
        ArrayList<int[]> arrayList_A=new ArrayList<>();
        if(stringCCLine_Data==null)
            {
                ////]Log.w(TAG, "ccQuoHtDataG0: "+"="+stringCCLine_Data );
                return null;

            }
        ////]Log.w(TAG, "ccQuoHtDataE0: "+"="+stringCCLine_Data );
        int_A=stringCCLine_Data.indexOf("[");
        for(int i=0;i<11;i++)
        {
            int_A=stringCCLine_Data.indexOf("{",int_A+1);
            int_B=stringCCLine_Data.indexOf("}",int_A+1);
            if(int_A==-1||int_B==-1) {
                break;
            }
            //////]Log.w(this.toString(), "ccQuoLineDataV0: "+"="+i +"="+int_A +"="+int_B );
            floats_rt[getQuoHtDataInt(i)]= ccQuoDataString(stringCCLine_Data,int_A,int_B);

        }
        //////]Log.w(TAG, "ccQuoHtDataC0: "+"="+floats_rt[0][0]+"="+ floats_rt[0][1]+"="+floats_rt[0][2]+"="+floats_rt[0][3]+"="+floats_rt[0][4]+"="+floats_rt[0][5]+"="+floats_rt[0][6]);
        return  floats_rt;
    }
    float[] ccQuoDataString(String string_Data, int int_A, int int_B){
        float[] floats_rt=null;
        String string_A=string_Data.substring(int_A,int_B);
        //////]Log.w(TAG, "getQuoJsStringC2 "+"="+ string_A);
        if(true)
        {
            int int_NameA=string_A.indexOf("DC:");
           // ////]Log.w(TAG, "getQuoJsStringC3: "+"="+ int_NameA);
            String string_Name=string_A.substring(int_NameA+3,int_NameA+10);
           // ////]Log.w(TAG, "getQuoJsStringC3A: "+"="+ string_Name);
        }
        int int_FloatB=string_A.indexOf("d\":[");
        int int_FloatB2=string_A.indexOf("]",int_FloatB+4);
        String string_B=string_A.substring(int_FloatB+3,int_FloatB2);
        boolean bool_A=true;
        int_FloatB=1;
        int_FloatB2=0;
        floats_rt=new float[7];
        for(int i=0;i<20;i++)
        {
            int_FloatB2=string_B.indexOf(",",int_FloatB);

            if(int_FloatB2==-1){
               // ////]Log.w(this.toString(), "ccQuoLineString:C1 "+"="+ i +"="+ string_B.substring(int_FloatB));
                floats_rt[i]=Float.valueOf(string_B.substring(int_FloatB));
               // ////]Log.w(TAG, "ccQuoLineString:C2 "+"="+floats_rt[i] );
                break;
            }
            //////]Log.w(this.toString(), "ccQuoLineString:C0 "+"="+ string_B.substring(int_FloatB,int_FloatB2));
            String string_C=string_B.substring(int_FloatB,int_FloatB2);
            //]Log.w(this.toString(), "ccQuoDataString: G0"+"="+string_C );
            if(Character.isDigit(string_C.charAt(0)))
            {
                floats_rt[i]=Float.valueOf(string_B.substring(int_FloatB,int_FloatB2));

            }else {
                floats_rt[i]=0;
            }

            int_FloatB=int_FloatB2+1;

        }
        return floats_rt;
    }
    String collectQuoMinorData(String string_Url){
        //{"filter":[{"left":"name","operation":"nempty"},{"left":"name,description","operation":"match","right":"EURUSD|USDJPY|GBPUSD|EURGBP|EURJPY|GBPJPY|AUDUSD|USDCAD|USDCHF|NZDUSD|EURCHF"}],"options":{"lang":"en"},"symbols":{"query":{"types":["forex"]},"tickers":[]},"columns":["close","change","change_abs","bid","ask","high","low"],"sort":{"sortBy":"name","sortOrder":"asc"},"range":[0,150]}
//        String string_Url="{\"filter\":[{\"left\":\"forex_minor_priority\",\"operation\":\"nempty\"},{\"left\":\"sector\",\"operation\":\"equal\",\"right\":\"Minor\"},{\"left\":\"name,description\",\"operation\":\"match\",\"right\":\""+
//                string_MinorName+"\"}],\"options\":{\"lang\":\"en\"},\"symbols\":{\"query\":{\"types\":[\"forex\"]},\"tickers\":[]},\"columns\":[\"name\",\"close\",\"change\",\"change_abs\",\"bid\",\"ask\",\"high\",\"low\",\"Recommend.All\",\"description\",\"name\",\"subtype\",\"update_mode\",\"pricescale\",\"minmov\",\"fractional\",\"minmove2\"],\"sort\":{\"sortBy\":\"forex_minor_priority\",\"sortOrder\":\"asc\"},\"range\":[0,150]}";
        return getHttpPostTradingData(string_Url);
    }
    String getHttpPostTradingData(String string_Data){
        String string_rt=null;
        try {
            URL url = new URL("https://scanner.tradingview.com/forex/scan");
            final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(2000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(5000);//设置指定时间内服务器没有返回数据的超时，5000ms
            httpURLConnection.setDoOutput(true);//设置允许输出
            httpURLConnection.setRequestMethod("POST");//设置请求的方式

            PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
            out.print(string_Data);//写入输出流
            out.flush();//立即刷新
            out.close();

            int code = httpURLConnection.getResponseCode();
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                    StringBuffer sb1 = new StringBuffer();
                    int ss;
                    while ((ss = reader.read()) != -1) {
                        sb1.append((char) ss);
                    }
                    string_rt=sb1.toString();
                    input.close();
                }

            }
        }catch (Exception e) {
            ////]Log.w(TAG, "getHttpPostTradingDataF0: "+"="+e.toString() );
            e.printStackTrace();
        }

        return string_rt;
    }
    void ccc_003(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<10;i++)
                    {
                        URL url = new URL("https://scanner.tradingview.com/forex/scan");
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        //////]Log.w(this.toString(), "ccc_003: " );
                        //设置连接超时,2000ms
                        httpURLConnection.setConnectTimeout(2000);
//设置指定时间内服务器没有返回数据的超时，5000ms
                        httpURLConnection.setReadTimeout(5000);
//设置允许输出
                        httpURLConnection.setDoOutput(true);
//设置请求的方式
                        httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setRequestProperty("Host","scanner.tradingview.com");
//                    httpURLConnection.setRequestProperty("User-Agent","User-Agent");
//                    httpURLConnection.setRequestProperty("Accept","*/*");
//                    httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//                    httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
//                    httpURLConnection.setRequestProperty("Referer","https://www.tradingview.com/");
//                    httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//                    httpURLConnection.setRequestProperty("Origin","https://www.tradingview.com");
//                    httpURLConnection.setRequestProperty("Content-Length","1444");
//                    httpURLConnection.setRequestProperty("Connection","keep-alive");
//                    httpURLConnection.setRequestProperty("TE","Trailers");


                        PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
                        //out.print("{\"symbols\":{\"tickers\":[\"FX_IDC:EURUSD\"],\"query\":{\"types\":[\"forex\"]}},\"columns\":[\"Recommend.Other|1\",\"Recommend.All|1\",\"Recommend.MA|1\",\"RSI|1\",\"RSI[1]|1\",\"Stoch.K|1\",\"Stoch.D|1\",\"Stoch.K[1]|1\",\"Stoch.D[1]|1\",\"CCI20|1\",\"CCI20[1]|1\",\"ADX|1\",\"ADX+DI|1\",\"ADX-DI|1\",\"ADX+DI[1]|1\",\"ADX-DI[1]|1\",\"AO|1\",\"AO[1]|1\",\"Mom|1\",\"Mom[1]|1\",\"MACD.macd|1\",\"MACD.signal|1\",\"Rec.Stoch.RSI|1\",\"Stoch.RSI.K|1\",\"Rec.WR|1\",\"W.R|1\",\"Rec.BBPower|1\",\"BBPower|1\",\"Rec.UO|1\",\"UO|1\",\"EMA5|1\",\"close|1\",\"SMA5|1\",\"EMA10|1\",\"SMA10|1\",\"EMA20|1\",\"SMA20|1\",\"EMA30|1\",\"SMA30|1\",\"EMA50|1\",\"SMA50|1\",\"EMA100|1\",\"SMA100|1\",\"EMA200|1\",\"SMA200|1\",\"Rec.Ichimoku|1\",\"Ichimoku.BLine|1\",\"Rec.VWMA|1\",\"VWMA|1\",\"Rec.HullMA9|1\",\"HullMA9|1\",\"Pivot.M.Classic.S3|1\",\"Pivot.M.Classic.S2|1\",\"Pivot.M.Classic.S1|1\",\"Pivot.M.Classic.Middle|1\",\"Pivot.M.Classic.R1|1\",\"Pivot.M.Classic.R2|1\",\"Pivot.M.Classic.R3|1\",\"Pivot.M.Fibonacci.S3|1\",\"Pivot.M.Fibonacci.S2|1\",\"Pivot.M.Fibonacci.S1|1\",\"Pivot.M.Fibonacci.Middle|1\",\"Pivot.M.Fibonacci.R1|1\",\"Pivot.M.Fibonacci.R2|1\",\"Pivot.M.Fibonacci.R3|1\",\"Pivot.M.Camarilla.S3|1\",\"Pivot.M.Camarilla.S2|1\",\"Pivot.M.Camarilla.S1|1\",\"Pivot.M.Camarilla.Middle|1\",\"Pivot.M.Camarilla.R1|1\",\"Pivot.M.Camarilla.R2|1\",\"Pivot.M.Camarilla.R3|1\",\"Pivot.M.Woodie.S3|1\",\"Pivot.M.Woodie.S2|1\",\"Pivot.M.Woodie.S1|1\",\"Pivot.M.Woodie.Middle|1\",\"Pivot.M.Woodie.R1|1\",\"Pivot.M.Woodie.R2|1\",\"Pivot.M.Woodie.R3|1\",\"Pivot.M.Demark.S1|1\",\"Pivot.M.Demark.Middle|1\",\"Pivot.M.Demark.R1|1\"]}");//写入输出流
                        out.print("{\"symbols\":{\"tickers\":[\"FX_IDC:EURUSD\"],\"query\":{\"types\":[\"forex\"]}},\"columns\":[\"EMA5|1\",\"ATR|1\",\"ADX|1\",\"ADX+DI|1\",\"ADX-DI|1\"]}");//写入输出流
                        out.flush();//立即刷新

                        out.close();
                        //////]Log.w(this.toString(), "ccc_001B5: " );

                        int code = httpURLConnection.getResponseCode();
                       // ////]Log.w(this.toString(), "ccc_001A0: " +"="+i+"="+code );
                        if(true||code == 200){
                            InputStream is = httpURLConnection.getInputStream();
                            if(true) {
                                InputStream input = httpURLConnection.getInputStream();
                                InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                                StringBuffer sb1 = new StringBuffer();
                                int ss;
                                while ((ss = reader.read()) != -1) {
                                    sb1.append((char) ss);
                                }
                                ////]Log.w(TAG, "ccc_001A3: "+"="+sb1.toString());
                            }
                            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                            StringBuilder result = new StringBuilder();
                            String line;
                            while((line = reader.readLine()) != null) {
                                result.append(line);
                            }
                           // ////]Log.w(this.toString(), "ccc_001A1: "+"="+(is!=null)+"="+line +"="+result );
                            //连接服务器后，服务器做出响应返回的数据
                            String serverResponse = URLDecoder.decode((result.toString()), "utf-8");
                           // ////]Log.w(this.toString(), "ccc_001A2: "+"="+serverResponse );
                            is.close();

                            //对返回的数据serverResponse进行操作

                        }
                        Thread.sleep(3000);
                    }


                } catch (Exception e) {
                    ////Log.e(this.toString(), e.toString());
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
