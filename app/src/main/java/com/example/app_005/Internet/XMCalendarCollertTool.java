package com.example.app_005.Internet;

import android.util.Log;

import com.example.app_005.Tool.TimeOD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import static android.support.constraint.Constraints.TAG;

public class XMCalendarCollertTool {
    public XMCalendarCollertTool(){
        if(false)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<20;i++)
                            {
                                String string_Data=getHttpPostTradingDataCC(new TimeOD(System.currentTimeMillis()));
                                //]Log.w(this.toString(), "XMCalenderCollertToolW0: "+"="+string_Data );
                                try {
                                    Thread.sleep(10000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }



//                        //JsonObject jsonObject =new JsonObject(string_Data);
//                        try {
//                            getXMCalendarHistoryJson("76");
//                            getXMCalendarTextData("398671",null);
//                            JSONObject jsonObject=new JSONObject(string_Data);
//                            //]Log.w(this.toString(), "XMCalenderCollertToolD2: "+"="+jsonObject.toString() );
//                            //JSONObject jsonObject_A=jsonObject.getJSONObject("renderedFilteredEvents");
//                            //]Log.w(this.toString(), "XMCalenderCollertToolW3: "+"="+jsonObject.toString() );
//                            //]Log.w(this.toString(), "XMCalenderCollertToolW4: "+"="+jsonObject.getString("renderedFilteredEvents").toString() );
//                            //ccGMLData(jsonObject.getString("renderedFilteredEvents"));
////                    InputSource is = new InputSource(new StringReader(jsonObject.getString("renderedFilteredEvents")));
////                    DocumentBuilderFactory documentBuilderFactory=  DocumentBuilderFactory.newInstance();
////                    DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
////                   // Document document= new SAXResult().
////                    Document document = documentBuilder.parse(is);
////
////                   // inputSource.setCharacterStream(new StringReader(rawXML));
////                   // Document document_A= Jsoup.parse("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+jsonObject.getString("renderedFilteredEvents"));
////                    //]Log.w(this.toString(), "XMCalenderCollertToolD0: "+"="+document.toString() );
////                   // //]Log.w(this.toString(), "XMCalenderCollertToolD1: "+"="+document_A.child(0).html() );
//////                    //]Log.w(this.toString(), "XMCalenderCollertToolD-1: "+"="+document_A.lastElementSibling() );
//////                    //]Log.w(this.toString(), "XMCalenderCollertToolD0: "+"="+document_A.getAllElements().size() );
//                        } catch (JSONException e) {
//                            Log.e(this.toString(), "XMCalenderCollertToolE1: "+"="+e.toString() );
//                            e.printStackTrace();
//
//                        }
                    }
                }).start();
            }

    }
    public CalendarDH[] startCollertXMCalenderDH(TimeOD timeOD_A){
       // TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
        String string_Data=getHttpPostTradingData(timeOD_A);
        if(string_Data==null) {
            for (int i = 0; i < 20; i++) {
                string_Data = getHttpPostTradingData(timeOD_A);
                if (string_Data != null) {
                    break;
                }
            }
        }
        CalendarDH[] calendarDHS_A=null;
        long long_A=System.currentTimeMillis();
        if(string_Data==null)
            {
                //]Log.w(this.toString(), "startCollertXMCalenderNullDH" );
            return null;
            }
        try {
            calendarDHS_A=ccGMLData(new JSONObject(string_Data).getString("renderedFilteredEvents"),timeOD_A);
        } catch (JSONException e) {
            Log.e(this.toString(), "XMCalenderCollertToolE1: "+"="+e.toString() );
            e.printStackTrace();

        }
        ////]Log.w(this.toString(), "startCollertXMCalender: C0DH"+"="+(calendarDHS_A!=null)+"="+ (System.currentTimeMillis()-long_A));
        return calendarDHS_A;
    }
    public CalendarCC[] startCollertXMCalenderCC(){
        TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
        String string_Data=getHttpPostTradingDataCC(timeOD_A);
        if(string_Data==null) {
            for (int i = 0; i < 20; i++) {
                string_Data = getHttpPostTradingDataCC(timeOD_A);
                if (string_Data != null) {
                    break;
                }
            }
        }
        if(string_Data==null)
            {
                //]Log.w(this.toString(), "startCollertXMCalenderNullCC" );
            return null;
            }
        CalendarCC[] calendarCCS_A=null;
        long long_A=System.currentTimeMillis();
        //]Log.w(this.toString(), "startCollertXMCalenderCC: R0"+"="+(string_Data!=null) );
        try {
            calendarCCS_A=ccGMLDataCalenderCC(new JSONObject(string_Data).getString("renderedFilteredEvents"));
        } catch (JSONException e) {
            Log.e(this.toString(), "XMCalenderCollertToolE1: "+"="+e.toString() );
            e.printStackTrace();

        }
        //]Log.w(this.toString(), "startCollertXMCalender: C0:CC"+"="+(calendarCCS_A!=null)+"="+ (System.currentTimeMillis()-long_A));
        return calendarCCS_A;
    }
    public
    String getHttpPostTradingData(){
        String string_rt=null;
        try {
            URL url = new URL("https://sslecal2.forexprostools.com/ajax.php");
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(2000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(5000);//设置指定时间内服务器没有返回数据的超时，5000ms
            httpURLConnection.setDoOutput(true);//设置允许输出
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
            httpURLConnection.setRequestProperty("X-Requested-With"," XMLHttpRequest");
            httpURLConnection.setRequestMethod("POST");//设置请求的方式

            PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
            String string_Time="2019-12-23";
            String string_Post="dateFrom="+string_Time+"&dateTo="+string_Time+"&timeframe=&columns%5B%5D=exc_flags&columns%5B%5D=exc_currency&columns%5B%5D=exc_importance&columns%5B%5D=exc_actual&columns%5B%5D=exc_forecast&columns%5B%5D=exc_previous&timeZone=28&quotes_search_text=&country%5B%5D=37&country%5B%5D=24&country%5B%5D=61&country%5B%5D=123&country%5B%5D=23&country%5B%5D=56&country%5B%5D=70&country%5B%5D=113&country%5B%5D=106&country%5B%5D=6&country%5B%5D=93&country%5B%5D=110&country%5B%5D=163&country%5B%5D=170&country%5B%5D=80&country%5B%5D=103&country%5B%5D=14&country%5B%5D=48&country%5B%5D=121&country%5B%5D=46&country%5B%5D=122&country%5B%5D=15&country%5B%5D=63&country%5B%5D=85&country%5B%5D=59&country%5B%5D=107&country%5B%5D=7&country%5B%5D=54&country%5B%5D=138&country%5B%5D=20&country%5B%5D=193&country%5B%5D=44&country%5B%5D=145&country%5B%5D=32&country%5B%5D=51&country%5B%5D=17&country%5B%5D=10&country%5B%5D=97&country%5B%5D=60&country%5B%5D=55&country%5B%5D=105&country%5B%5D=90&country%5B%5D=112&country%5B%5D=162&country%5B%5D=36&country%5B%5D=43&country%5B%5D=35&country%5B%5D=27&country%5B%5D=72&country%5B%5D=34&country%5B%5D=188&country%5B%5D=52&country%5B%5D=22&country%5B%5D=53&country%5B%5D=41&country%5B%5D=75&country%5B%5D=25&country%5B%5D=33&country%5B%5D=89&country%5B%5D=9&country%5B%5D=12&country%5B%5D=94&country%5B%5D=125&country%5B%5D=202&country%5B%5D=96&country%5B%5D=92&country%5B%5D=172&country%5B%5D=100&country%5B%5D=5&country%5B%5D=57&country%5B%5D=71&country%5B%5D=4&country%5B%5D=21&country%5B%5D=45&country%5B%5D=38&country%5B%5D=26&country%5B%5D=84&country%5B%5D=178&country%5B%5D=143&country%5B%5D=87&country%5B%5D=29&country%5B%5D=11&country%5B%5D=39&country%5B%5D=109&country%5B%5D=111&country%5B%5D=42&country%5B%5D=68&timeFilter=timeRemain&action=filter&lang=6";
            if(false)
                {
                String string_ID="397180";
                    string_Post="action=event&eventID="+string_ID+"&lang=6";
                }
            if(false)
                {
                //event_attr_ID=1059&event_timestamp=2019-12-10&eventID=397182&lang=6&action=history
                    String string_Attr="1059";
                    String string_ID="397182";
                    String string_Date="2019-12-10";

                    string_Post="event_attr_ID="+string_Attr+"&event_timestamp="+string_Date+"&eventID="+string_ID+"&lang=6&action=history";
                }
            out.print(string_Post);//写入输出流
            out.flush();//立即刷新
            out.close();

            int code = httpURLConnection.getResponseCode();
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "GBK");
//                    BufferedReader in = new BufferedReader(new InputStreamReader(input,"GBK"));
//                    String result = "";
//                    String line = null;
//                    while ((line = in.readLine()) != null) {
//                        result += "|"+line;
//                    }
//                    result = new String(result.toString().getBytes("GBK"),"UTF-8");
//                    //]Log.w(this.toString(), "XMCalenderCollertToolW1: "+"="+result );
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
            //]Log.w(TAG, "getHttpPostTradingDataF0: "+"="+e.toString() );
            e.printStackTrace();
        }

        return string_rt;
    }
    String getHttpPostTradingData(TimeOD timeOD_A){
        String string_rt=null;
        try {
            URL url = new URL("https://sslecal2.forexprostools.com/ajax.php");
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(20000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(50000);//设置指定时间内服务器没有返回数据的超时，5000ms
            httpURLConnection.setDoOutput(true);//设置允许输出
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
            httpURLConnection.setRequestProperty("X-Requested-With"," XMLHttpRequest");
            httpURLConnection.setRequestMethod("POST");//设置请求的方式

            PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
            String string_Time=null;
            if(true)
                {
                    string_Time=(timeOD_A.intsA[0]+"-"+(timeOD_A.intsA[1]+1)+"-"+timeOD_A.intsA[2]);
                }else {
                timeOD_A.intsA[0]=2019;
                timeOD_A.intsA[1]=12;
                timeOD_A.intsA[2]=23;
                string_Time="2019-12-23";
            }
            //String string_Time="2019-12-23";
            String string_Post="dateFrom="+string_Time+"&dateTo="+string_Time+"&timeframe=&columns%5B%5D=exc_flags&columns%5B%5D=exc_currency&columns%5B%5D=exc_importance&columns%5B%5D=exc_actual&columns%5B%5D=exc_forecast&columns%5B%5D=exc_previous&timeZone=28&quotes_search_text=&country%5B%5D=37&country%5B%5D=24&country%5B%5D=61&country%5B%5D=123&country%5B%5D=23&country%5B%5D=56&country%5B%5D=70&country%5B%5D=113&country%5B%5D=106&country%5B%5D=6&country%5B%5D=93&country%5B%5D=110&country%5B%5D=163&country%5B%5D=170&country%5B%5D=80&country%5B%5D=103&country%5B%5D=14&country%5B%5D=48&country%5B%5D=121&country%5B%5D=46&country%5B%5D=122&country%5B%5D=15&country%5B%5D=63&country%5B%5D=85&country%5B%5D=59&country%5B%5D=107&country%5B%5D=7&country%5B%5D=54&country%5B%5D=138&country%5B%5D=20&country%5B%5D=193&country%5B%5D=44&country%5B%5D=145&country%5B%5D=32&country%5B%5D=51&country%5B%5D=17&country%5B%5D=10&country%5B%5D=97&country%5B%5D=60&country%5B%5D=55&country%5B%5D=105&country%5B%5D=90&country%5B%5D=112&country%5B%5D=162&country%5B%5D=36&country%5B%5D=43&country%5B%5D=35&country%5B%5D=27&country%5B%5D=72&country%5B%5D=34&country%5B%5D=188&country%5B%5D=52&country%5B%5D=22&country%5B%5D=53&country%5B%5D=41&country%5B%5D=75&country%5B%5D=25&country%5B%5D=33&country%5B%5D=89&country%5B%5D=9&country%5B%5D=12&country%5B%5D=94&country%5B%5D=125&country%5B%5D=202&country%5B%5D=96&country%5B%5D=92&country%5B%5D=172&country%5B%5D=100&country%5B%5D=5&country%5B%5D=57&country%5B%5D=71&country%5B%5D=4&country%5B%5D=21&country%5B%5D=45&country%5B%5D=38&country%5B%5D=26&country%5B%5D=84&country%5B%5D=178&country%5B%5D=143&country%5B%5D=87&country%5B%5D=29&country%5B%5D=11&country%5B%5D=39&country%5B%5D=109&country%5B%5D=111&country%5B%5D=42&country%5B%5D=68&timeFilter=timeRemain&action=filter&lang=6";
            if(false)
            {
                String string_ID="397180";
                string_Post="action=event&eventID="+string_ID+"&lang=6";
            }
            if(false)
            {
                //event_attr_ID=1059&event_timestamp=2019-12-10&eventID=397182&lang=6&action=history
                String string_Attr="1059";
                String string_ID="397182";
                String string_Date="2019-12-10";

                string_Post="event_attr_ID="+string_Attr+"&event_timestamp="+string_Date+"&eventID="+string_ID+"&lang=6&action=history";
            }
            out.print(string_Post);//写入输出流
            out.flush();//立即刷新
            out.close();

            int code = httpURLConnection.getResponseCode();
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "GBK");
//                    BufferedReader in = new BufferedReader(new InputStreamReader(input,"GBK"));
//                    String result = "";
//                    String line = null;
//                    while ((line = in.readLine()) != null) {
//                        result += "|"+line;
//                    }
//                    result = new String(result.toString().getBytes("GBK"),"UTF-8");
//                    //]Log.w(this.toString(), "XMCalenderCollertToolW1: "+"="+result );
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
            //]Log.w(TAG, "getHttpPostTradingDataF0: "+"="+e.toString() );
            e.printStackTrace();
        }

        return string_rt;
    }
    String getHttpPostTradingDataCC(TimeOD timeOD_A){
        String string_rt=null;
        long long_A=System.currentTimeMillis();
        try {
            Proxy proxy_A= new Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress("116.196.85.166", 3128));
            URL url = new URL("https://sslecal2.forexprostools.com/ajax.php");
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(proxy_A);
            httpURLConnection.setConnectTimeout(20000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(50000);//设置指定时间内服务器没有返回数据的超时，5000ms
            httpURLConnection.setDoOutput(true);//设置允许输出
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
            httpURLConnection.setRequestProperty("X-Requested-With"," XMLHttpRequest");
            httpURLConnection.setRequestMethod("POST");//设置请求的方式

            PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
            String string_Time=null;
            if(true)
            {
                string_Time=(timeOD_A.intsA[0]+"-"+(timeOD_A.intsA[1]+1)+"-"+timeOD_A.intsA[2]);
            }else {
                timeOD_A.intsA[0]=2019;
                timeOD_A.intsA[1]=12;
                timeOD_A.intsA[2]=23;
                string_Time="2019-12-23";
            }
            //String string_Time="2019-12-23";
            String string_Post="dateFrom="+string_Time+"&dateTo="+string_Time+"&timeframe=&columns%5B%5D=exc_flags&columns%5B%5D=exc_currency&columns%5B%5D=exc_importance&columns%5B%5D=exc_actual&columns%5B%5D=exc_forecast&columns%5B%5D=exc_previous&timeZone=28&quotes_search_text=&country%5B%5D=37&country%5B%5D=24&country%5B%5D=61&country%5B%5D=123&country%5B%5D=23&country%5B%5D=56&country%5B%5D=70&country%5B%5D=113&country%5B%5D=106&country%5B%5D=6&country%5B%5D=93&country%5B%5D=110&country%5B%5D=163&country%5B%5D=170&country%5B%5D=80&country%5B%5D=103&country%5B%5D=14&country%5B%5D=48&country%5B%5D=121&country%5B%5D=46&country%5B%5D=122&country%5B%5D=15&country%5B%5D=63&country%5B%5D=85&country%5B%5D=59&country%5B%5D=107&country%5B%5D=7&country%5B%5D=54&country%5B%5D=138&country%5B%5D=20&country%5B%5D=193&country%5B%5D=44&country%5B%5D=145&country%5B%5D=32&country%5B%5D=51&country%5B%5D=17&country%5B%5D=10&country%5B%5D=97&country%5B%5D=60&country%5B%5D=55&country%5B%5D=105&country%5B%5D=90&country%5B%5D=112&country%5B%5D=162&country%5B%5D=36&country%5B%5D=43&country%5B%5D=35&country%5B%5D=27&country%5B%5D=72&country%5B%5D=34&country%5B%5D=188&country%5B%5D=52&country%5B%5D=22&country%5B%5D=53&country%5B%5D=41&country%5B%5D=75&country%5B%5D=25&country%5B%5D=33&country%5B%5D=89&country%5B%5D=9&country%5B%5D=12&country%5B%5D=94&country%5B%5D=125&country%5B%5D=202&country%5B%5D=96&country%5B%5D=92&country%5B%5D=172&country%5B%5D=100&country%5B%5D=5&country%5B%5D=57&country%5B%5D=71&country%5B%5D=4&country%5B%5D=21&country%5B%5D=45&country%5B%5D=38&country%5B%5D=26&country%5B%5D=84&country%5B%5D=178&country%5B%5D=143&country%5B%5D=87&country%5B%5D=29&country%5B%5D=11&country%5B%5D=39&country%5B%5D=109&country%5B%5D=111&country%5B%5D=42&country%5B%5D=68&timeFilter=timeRemain&action=filter&lang=6";
            if(false)
            {
                String string_ID="397180";
                string_Post="action=event&eventID="+string_ID+"&lang=6";
            }
            if(false)
            {
                //event_attr_ID=1059&event_timestamp=2019-12-10&eventID=397182&lang=6&action=history
                String string_Attr="1059";
                String string_ID="397182";
                String string_Date="2019-12-10";

                string_Post="event_attr_ID="+string_Attr+"&event_timestamp="+string_Date+"&eventID="+string_ID+"&lang=6&action=history";
            }
            out.print(string_Post);//写入输出流
            out.flush();//立即刷新
            out.close();

            int code = httpURLConnection.getResponseCode();
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "GBK");
//                    BufferedReader in = new BufferedReader(new InputStreamReader(input,"GBK"));
//                    String result = "";
//                    String line = null;
//                    while ((line = in.readLine()) != null) {
//                        result += "|"+line;
//                    }
//                    result = new String(result.toString().getBytes("GBK"),"UTF-8");
//                    //]Log.w(this.toString(), "XMCalenderCollertToolW1: "+"="+result );
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
            //]Log.w(TAG, "getHttpPostTradingDataCC: E0"+"="+e.toString() );
            e.printStackTrace();
            return null;
        }
        //]Log.w(TAG, "getHttpPostTradingDataCC: V0" +"="+(System.currentTimeMillis()-long_A) +"="+string_rt);
        return string_rt;
    }
    String getHttpPostTradingData(String string_Post){
        String string_rt=null;
        try {
            URL url = new URL("https://sslecal2.forexprostools.com/ajax.php");
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(2000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(5000);//设置指定时间内服务器没有返回数据的超时，5000ms
            httpURLConnection.setDoOutput(true);//设置允许输出
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
            httpURLConnection.setRequestProperty("X-Requested-With"," XMLHttpRequest");
            httpURLConnection.setRequestMethod("POST");//设置请求的方式

            PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
//            String string_Time="2019-12-11";
//            String string_Post="dateFrom="+string_Time+"&dateTo="+string_Time+"&timeframe=&columns%5B%5D=exc_flags&columns%5B%5D=exc_currency&columns%5B%5D=exc_importance&columns%5B%5D=exc_actual&columns%5B%5D=exc_forecast&columns%5B%5D=exc_previous&timeZone=28&quotes_search_text=&country%5B%5D=43&timeFilter=timeRemain&action=filter&lang=6";
//            if(false)
//            {
//                String string_ID="397180";
//                string_Post="action=event&eventID="+string_ID+"&lang=6";
//            }
//            if(true)
//            {
//                //event_attr_ID=1059&event_timestamp=2019-12-10&eventID=397182&lang=6&action=history
//                String string_Attr="1059";
//                String string_ID="397182";
//                String string_Date="2019-12-10";
//
//                string_Post="event_attr_ID="+string_Attr+"&event_timestamp="+string_Date+"&eventID="+string_ID+"&lang=6&action=history";
//            }
            out.print(string_Post);//写入输出流
            out.flush();//立即刷新
            out.close();

            int code = httpURLConnection.getResponseCode();
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
//                    BufferedReader in = new BufferedReader(new InputStreamReader(input,"GBK"));
//                    String result = "";
//                    String line = null;
//                    while ((line = in.readLine()) != null) {
//                        result += "|"+line;
//                    }
//                    result = new String(result.toString().getBytes("GBK"),"UTF-8");
//                    //]Log.w(this.toString(), "XMCalenderCollertToolW1: "+"="+result );
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
            //]Log.w(TAG, "getHttpPostTradingDataF0: "+"="+e.toString() );
            e.printStackTrace();
        }

        return string_rt;
    }
    String getHttpGetTradingData(String string_Url){
        String string_rt=null;
        try {
            URL url = new URL(string_Url);
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(2000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(5000);//设置指定时间内服务器没有返回数据的超时，5000ms
            httpURLConnection.setDoOutput(true);//设置允许输出
            httpURLConnection.setRequestMethod("GET");//设置请求的方式

//            PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
////            String string_Time="2019-12-11";
////            String string_Post="dateFrom="+string_Time+"&dateTo="+string_Time+"&timeframe=&columns%5B%5D=exc_flags&columns%5B%5D=exc_currency&columns%5B%5D=exc_importance&columns%5B%5D=exc_actual&columns%5B%5D=exc_forecast&columns%5B%5D=exc_previous&timeZone=28&quotes_search_text=&country%5B%5D=43&timeFilter=timeRemain&action=filter&lang=6";
////            if(false)
////            {
////                String string_ID="397180";
////                string_Post="action=event&eventID="+string_ID+"&lang=6";
////            }
////            if(true)
////            {
////                //event_attr_ID=1059&event_timestamp=2019-12-10&eventID=397182&lang=6&action=history
////                String string_Attr="1059";
////                String string_ID="397182";
////                String string_Date="2019-12-10";
////
////                string_Post="event_attr_ID="+string_Attr+"&event_timestamp="+string_Date+"&eventID="+string_ID+"&lang=6&action=history";
////            }
//            out.print(string_Post);//写入输出流
//            out.flush();//立即刷新
//            out.close();

            int code = httpURLConnection.getResponseCode();
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
//                    BufferedReader in = new BufferedReader(new InputStreamReader(input,"GBK"));
//                    String result = "";
//                    String line = null;
//                    while ((line = in.readLine()) != null) {
//                        result += "|"+line;
//                    }
//                    result = new String(result.toString().getBytes("GBK"),"UTF-8");
//                    //]Log.w(this.toString(), "XMCalenderCollertToolW1: "+"="+result );
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
            //]Log.w(TAG, "getHttpPostTradingDataF0: "+"="+e.toString() );
            e.printStackTrace();
        }

        return string_rt;
    }
    public CalendarDetailH getXMCalendarTextData(String string_ID,String string_Attr){
//        String string_ID="398668";
        //string_ID="398685";
        CalendarDetailH calendarDetailH =null;
        String string_Post="action=event&eventID="+string_ID+"&lang=6";
        String string_Data=getHttpPostTradingData(string_Post);
        if(string_Data==null)
        {
            for(int i=0;i<20;i++)
                {
                    string_Data=getHttpPostTradingData(string_Post);
                    if(string_Data!=null)
                        {
                        break;
                        }
                }
        }
        if(string_Data==null)
            {
            return null;
            }
        int int_A=string_Data.indexOf("eventOverview block\">");
        int int_B=0;
        if(int_A!=-1)
            {
                calendarDetailH =new CalendarDetailH(string_ID,string_Attr);
                int_B=string_Data.indexOf("</div>",int_A+22);
                //int_B=string_Data.indexOf("<",int_A+1);
                String string_Actual=string_Data.substring(int_A+21,int_B);
                StringBuilder stringBuilder_A=new StringBuilder(string_Actual);
                int int_A3=stringBuilder_A.indexOf("<");
                int int_B3=0;//stringBuilder_A.indexOf(">",int_A3+1);
                while (int_A3!=-1){
                    int_B3=stringBuilder_A.indexOf(">",int_A3+1);
                    //]Log.w(this.toString(), "getXMCalendarTextData: T0"+"="+int_A3 +"="+int_B3 +"="+ stringBuilder_A.length());
                    stringBuilder_A.delete(int_A3,int_B3+1);
                    int_A3=stringBuilder_A.indexOf("<");
                }
                //]Log.w(this.toString(), "getXMCalendarTextData: T1"+"="+stringBuilder_A.toString() );
                calendarDetailH.setText(stringBuilder_A.toString());
                boolean bool_Actual=(string_Data.indexOf("eventInfoActual")!=-1);
                //]Log.w(this.toString(), "getXMCalendarTextData: E0"+"="+bool_Actual+"="+string_Actual);

                if(bool_Actual)
                    {
                        calendarDetailH.setHistoryJsonData(getXMCalendarHistoryJson(string_Attr));
                    }else {

                }

            }
        return calendarDetailH;

    }
    JSONArray getXMCalendarHistoryJson(String string_Attr){
            String string_Post="https://sslecal2.forexprostools.com/events_charts/us/\"+string_Attr+\".json";
            String string_Data=getHttpGetTradingData(string_Post);
            if(string_Data==null)
                {
                    for(int i=0;i<20;i++)
                        {
                            string_Data=getHttpGetTradingData(string_Post);
                        }

                }
            if(string_Data==null)
                {
                return null;
                }
        //]Log.w(this.toString(), "getXMCalendarHistoryJson: E0"+"="+ string_Data);
        //JSONObject jsonObject_A=null;
        JSONArray jsonArray_A=null;
        try {
            JSONObject jsonObject_A=new JSONObject(string_Data);
            jsonArray_A=jsonObject_A.getJSONArray("attr");
            //JSONArray jsonArray_B=jsonObject_A.getJSONArray("data");
            //]Log.w(this.toString(), "getXMCalendarHistoryJson: R0"+"="+jsonArray_A.length());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray_A;
    }
    public CalendarDH[] ccGMLData(String string_XML,TimeOD timeOD_A){
        ArrayList<CalendarDH> calendarDHS_A =new ArrayList<>();
        Calendar calendar_A=Calendar.getInstance();
        calendar_A.setTimeInMillis(timeOD_A.PuC_getTimeLong());
        calendar_A.set(Calendar.SECOND,0);
        calendar_A.set(Calendar.MINUTE,0);
        //]Log.w(this.toString(), "ccGMLData: Y0"+"="+ string_XML);
        int int_2A=string_XML.indexOf("eventRowId_");
        int int_2B=0;
        int int_2C=0;
        if(int_2A!=-1)
        {
            while (int_2A!=-1){
                int_2B=string_XML.indexOf("\"",int_2A+12);
                String string_ID=string_XML.substring(int_2A+11,int_2B);
                char char_A=(string_XML.charAt(int_2B+1));
                //]Log.w(this.toString(), "ccGMLDataB: R1"+"="+ (char_A=='>') +"="+char_A);
                if((char_A=='>'))
                {
                    int_2A = string_XML.indexOf("flag", int_2B + 1);
                    int_2A = string_XML.indexOf("title=\"", int_2A + 5);
                    int_2B = string_XML.indexOf("\"", int_2A + 8);
                    String string_Flag = string_XML.substring(int_2A + 7, int_2B);
                    int_2A = string_XML.indexOf("left event\">", int_2B + 1);
                    int_2B = string_XML.indexOf("<", int_2A + 13);
                    String string_Name = string_XML.substring(int_2A + 12, int_2B).trim();
//
//                    int_2A = string_XML.indexOf("first left time\" >", int_2B +1);
//                    int_2B = string_XML.indexOf("<", int_2A + 19);
                    String string_Time="全天";
                   // long long_Time=getCalendarDataLong(calendar_A,string_Time);
                    //]Log.w(this.toString(), "ccGMLDataB: R0"+"="+ string_ID +"="+string_Flag  +"="+string_Name);
                    calendarDHS_A.add(new CalendarDH(string_Time,string_ID, string_Flag, string_Name));
                }
                else {


                    int_2A = string_XML.indexOf("event_attr_ID=\"", int_2B + 1);
                    int_2B = string_XML.indexOf("\"", int_2A + 16);
                    String string_Attr = string_XML.substring(int_2A + 15, int_2B);
                    //]Log.w(this.toString(), "LKINDccGMLData: E1"+"="+string_Attr );

                    int_2A = string_XML.indexOf("first left time", int_2B +1);
                    int_2A = string_XML.indexOf(">", int_2A+16);
                    int_2B = string_XML.indexOf("<", int_2A + 1);
                    String string_Time=string_XML.substring(int_2A + 1, int_2B);
                    //]Log.w(this.toString(), "LKINDccGMLData: E0"+"="+ string_Time);
                    long long_Time=getCalendarDataLong(calendar_A,string_Time);

                    int_2A = string_XML.indexOf("flag", int_2B + 1);
                    int_2A = string_XML.indexOf("title=\"", int_2A + 5);
                    int_2B = string_XML.indexOf("\"", int_2A + 8);
                    String string_Flag = string_XML.substring(int_2A + 7, int_2B);

                    int_2A = string_XML.indexOf("预计变数", int_2B + 1);
                    int_2B = string_XML.indexOf("\"", int_2A + 5);
                    String string_Importance = string_XML.substring(int_2A + 4, int_2B);
                    int int_Importance = (string_Importance.equals("中等") ? 2 : (string_Importance.equals("较低") ? 1 : 3));

                    int_2A = string_XML.indexOf("left event\">", int_2B + 1);
                    int_2B = string_XML.indexOf("<", int_2A + 14);
                    String string_Name = string_XML.substring(int_2A + 13, int_2B).trim();


                    //]Log.w(this.toString(), "ccGMLData: E1" + "=" + (int_2C) + "=" + string_ID + "=" + string_Attr + "=" + int_Importance + "=" + string_Flag + "=" + string_Name.trim());


                    int_2A = string_XML.indexOf("bold act ", int_2B + 1);
                    int_2B = string_XML.indexOf("Font", int_2A + 10);
                    String string_ActualFont = string_XML.substring(int_2A + 9, int_2B);
                    boolean bool_Actual = string_ActualFont.equals("green");
                    int int_ActualColor=ccCalendarDataColor(string_ActualFont);

                    int_2A = string_XML.indexOf(">", int_2B + 1);
                    int_2B = string_XML.indexOf("<", int_2A + 1);
                    String string_Actual = string_XML.substring(int_2A + 1, int_2B);

                    int_2A = string_XML.indexOf("<td", int_2B + 1);
                    int_2A = string_XML.indexOf(">", int_2A + 1);
                    int_2B = string_XML.indexOf("<", int_2A + 1);
                    String string_Forecast = string_XML.substring(int_2A + 1, int_2B);

                    int_2A = string_XML.indexOf("prev", int_2B + 1);
                    int_2B = string_XML.indexOf("Font", int_2A + 6);
                    String string_PreviousFont = string_XML.substring(int_2A + 5, int_2B);
                    boolean bool_Previous = string_PreviousFont.equals("green");
                    int int_PreviousColor=ccCalendarDataColor(string_PreviousFont);

                    int_2A = string_XML.indexOf(">", int_2B + 1);
                    int_2B = string_XML.indexOf("<", int_2A + 1);
                    String string_Previous = string_XML.substring(int_2A + 1, int_2B);

                    int_2A = string_XML.indexOf("diamond", int_2B + 1);
                    int_2A = string_XML.indexOf(">", int_2A + 8);
                    int_2B = string_XML.indexOf("<", int_2A + 1);
                    String string_Revised = string_XML.substring(int_2A + 1, int_2B);
                    boolean bool_Revised = (string_Revised.equals("&nbsp;"));
                    //getXMCalendarTextData(string_ID);
                    calendarDHS_A.add(new CalendarDH(long_Time,string_Time,string_ID, string_Attr, string_Flag, string_Name, int_Importance, string_Actual, string_Previous, string_Forecast, int_ActualColor, int_PreviousColor, bool_Revised));
                    //]Log.w(this.toString(), "ccGMLData: E2" + "=" + (int_2C) + "=" + bool_Actual + "=" + string_Actual + "=" + string_Forecast + "=" + bool_Previous + "=" + string_Previous + "=" + bool_Revised);
                    //]Log.w(this.toString(), "ccGMLData: E3" + "=" + (int_2C) + "=" + string_Name + "=" + string_Actual + "=" + string_Previous + "=" + string_Forecast);
                    int_2C++;
                    if (true) {
                        //break;
                    }
                }
                int_2A=string_XML.indexOf("eventRowId_",int_2B+1);
            }

        }
        if(calendarDHS_A.size()!=0)
        {
            return  calendarDHS_A.toArray(new CalendarDH[calendarDHS_A.size()]);
        }else {
            return null;
        }

    }
    public CalendarCC[] ccGMLDataCalenderCC(String string_XML){
        ArrayList<CalendarCC> calendarCCS_A =new ArrayList<>();
        int int_2A=string_XML.indexOf("eventRowId_");
        int int_2B=0;
        int int_2C=0;
        if(int_2A!=-1)
        {
            while (int_2A!=-1){
                int_2B=string_XML.indexOf("\"",int_2A+12);
                String string_ID=string_XML.substring(int_2A+11,int_2B);
                char char_A=(string_XML.charAt(int_2B+1));
                //]Log.w(this.toString(), "ccGMLDataB: R1"+"="+ (char_A=='>') +"="+char_A);
                if((char_A=='>'))
                {
                    calendarCCS_A.add(null);
                }else {


//                    int_2A = string_XML.indexOf("event_attr_ID=\"", int_2B + 1);
//                    int_2B = string_XML.indexOf("\"", int_2A + 16);
//                    String string_Attr = string_XML.substring(int_2A + 15, int_2B);
//
//                    int_2A = string_XML.indexOf("flag", int_2B + 1);
//                    int_2A = string_XML.indexOf("title=\"", int_2A + 5);
//                    int_2B = string_XML.indexOf("\"", int_2A + 8);
//                    String string_Flag = string_XML.substring(int_2A + 7, int_2B);
//
//                    int_2A = string_XML.indexOf("预计变数", int_2B + 1);
//                    int_2B = string_XML.indexOf("\"", int_2A + 5);
//                    String string_Importance = string_XML.substring(int_2A + 4, int_2B);
//                    int int_Importance = (string_Importance.equals("中等") ? 2 : (string_Importance.equals("较低") ? 3 : 1));
//
//                    int_2A = string_XML.indexOf("left event\">", int_2B + 1);
//                    int_2B = string_XML.indexOf("<", int_2A + 14);
//                    String string_Name = string_XML.substring(int_2A + 13, int_2B);
//
//
//                    //]Log.w(this.toString(), "ccGMLData: E1" + "=" + (int_2C) + "=" + string_ID + "=" + string_Attr + "=" + int_Importance + "=" + string_Flag + "=" + string_Name.trim());
//

                    int_2A = string_XML.indexOf("bold act ", int_2B + 1);
                    int_2B = string_XML.indexOf("Font", int_2A + 10);
                    String string_ActualFont = string_XML.substring(int_2A + 9, int_2B);
                    boolean bool_Actual = string_ActualFont.equals("green");
                    int int_ActualColor=ccCalendarDataColor(string_ActualFont);

                    int_2A = string_XML.indexOf(">", int_2B + 1);
                    int_2B = string_XML.indexOf("<", int_2A + 1);
                    String string_Actual = string_XML.substring(int_2A + 1, int_2B);

                    int_2A = string_XML.indexOf("<td", int_2B + 1);
                    int_2A = string_XML.indexOf(">", int_2A + 1);
                    int_2B = string_XML.indexOf("<", int_2A + 1);
                    String string_Forecast = string_XML.substring(int_2A + 1, int_2B);

                    int_2A = string_XML.indexOf("prev", int_2B + 1);
                    int_2B = string_XML.indexOf("Font", int_2A + 6);
                    String string_PreviousFont = string_XML.substring(int_2A + 5, int_2B);
                    boolean bool_Previous = string_PreviousFont.equals("green");
                    int int_PreviousColor=ccCalendarDataColor(string_PreviousFont);

                    int_2A = string_XML.indexOf(">", int_2B + 1);
                    int_2B = string_XML.indexOf("<", int_2A + 1);
                    String string_Previous = string_XML.substring(int_2A + 1, int_2B);

                    int_2A = string_XML.indexOf("diamond", int_2B + 1);
                    int_2A = string_XML.indexOf(">", int_2A + 8);
                    int_2B = string_XML.indexOf("<", int_2A + 1);
                    String string_Revised = string_XML.substring(int_2A + 1, int_2B);
                    boolean bool_Revised = (string_Revised.equals("&nbsp;"));
                    //getXMCalendarTextData(string_ID);
                    calendarCCS_A.add(new CalendarCC(string_ID, string_Actual, string_Previous, string_Forecast, int_ActualColor, int_PreviousColor, bool_Revised));
                    //]Log.w(this.toString(), "ccGMLData: E2" + "=" + (int_2C) + "=" + bool_Actual + "=" + string_Actual + "=" + string_Forecast + "=" + bool_Previous + "=" + string_Previous + "=" + bool_Revised);
                    int_2C++;
                    if (true) {
                        //break;
                    }
                }
                int_2A=string_XML.indexOf("eventRowId_",int_2B+1);
            }

        }
        if(calendarCCS_A.size()!=0)
        {
            return  calendarCCS_A.toArray(new CalendarCC[calendarCCS_A.size()]);
        }else {
            return null;
        }

    }
    long getCalendarDataLong(Calendar calendar_A,String string_A){
        if(Character.isDigit(string_A.charAt(0)))
            {
                int int_A=Integer.valueOf(string_A.substring(0,2));
                int int_B=Integer.valueOf(string_A.substring(3,5));
                calendar_A.set(Calendar.HOUR_OF_DAY,int_A);
                calendar_A.set(Calendar.MINUTE,int_B);
                return calendar_A.getTimeInMillis();
            }else {
            return 0;
        }

    }
    int ccCalendarDataColor(String string_Data){
        switch(string_Data){
            case "green":
                return 3;
            case "red":
                return 1;
            case "black":
            default:
                return 2;
        }

    }
    /**
     *日历情报
     */
    public class CalendarDH {
        public boolean boolMode;
        public long longTime;
        public String stringTime;
        public String stringID;
        public String stringAttr;
        public String stringFlag;
        public String stringName;
        public int intImportance;
        public String stringData0;
        public String stringData1;
        public String stringData2;
        public int intColorFont0;
        public int intColorFont1;
        public boolean boolRevised;
        public CalendarDH(long longTime,String stringTime,String stringID, String stringAttr, String stringFlag, String stringName, int intImportance, String stringData0, String stringData1, String stringData2, int intColorFont0, int intColorFont1, boolean boolRevised){
            boolMode=true;
            this.longTime=longTime;
            this.stringTime=stringTime;
            this.stringID=stringID;
            this.stringAttr=stringAttr;
            this.stringFlag=stringFlag;
            this. stringName=stringName;
            this. intImportance=intImportance;
            this. stringData0=ccStringNull(stringData0);
            this. stringData1=ccStringNull(stringData1);
            this. stringData2=ccStringNull(stringData2);
            this. intColorFont0=intColorFont0;
            this. intColorFont1=intColorFont1;
            this. boolRevised=boolRevised;
        }
        public CalendarDH(String stringTime,String stringID, String stringFlag, String stringName){
            boolMode=false;
            this.stringTime=stringTime;
            this.stringID=stringID;
            this.stringFlag=stringFlag;
            this. stringName=stringName;
        }
        String ccStringNull(String string_A){
            return (string_A.equals("&nbsp;")?null:ccStringNum(string_A));
        }
        String ccStringNum(String string_A){
            StringBuilder stringBuilder_A=new StringBuilder(string_A);
            for(int i=stringBuilder_A.length()-1;i>=0;i--)
                {
                    //]Log.w(this.toString(), "ccStringNum: Y1"+"="+i +"="+stringBuilder_A.length() );

                    if(!Character.isDigit(stringBuilder_A.charAt(i)))
                        {
                            stringBuilder_A=stringBuilder_A.deleteCharAt(i);
                        }else {
                        break;
                    }

                }
            for(int i=0;i<stringBuilder_A.length();i++)
                {
                    if((stringBuilder_A.charAt(i)==','))
                        {
                            stringBuilder_A=stringBuilder_A.deleteCharAt(i);
                        }

                }
            //]Log.w(this.toString(), "ccStringNum: Y0"+"="+string_A +"="+stringBuilder_A );
            return stringBuilder_A.toString();
        }
        public boolean setRevisedData(String stringAttr){
            if(!this.stringAttr.equals(stringAttr))
                {
                    this.stringAttr=stringAttr;
                return true;
                }else {
                return false;
            }
        }

    }
    /**
     *日历数据
     */
    public class CalendarCC {
        public String stringID;
        public String stringData0;
        public String stringData1;
        public String stringData2;
        public int intColorFont0;
        public int intColorFont1;
        public boolean boolRevised;
        public CalendarCC(String stringID, String stringData0, String stringData1, String stringData2, int intColorFont0, int intColorFont1, boolean boolRevised){
            this.stringID=stringID;
            this. stringData0=ccStringNull(stringData0);
            this. stringData1=ccStringNull(stringData1);
            this. stringData2=ccStringNull(stringData2);
            this. intColorFont0=intColorFont0;
            this. intColorFont1=intColorFont1;
            this. boolRevised=boolRevised;
        }
        String ccStringNull(String string_A){
            return (string_A.equals("&nbsp;")?"":string_A);
        }
    }
    /**
     *日历详细历史
     */
    public class CalendarDetailH{
        public String stringID;
        public String stringAttr;
        public String stringText;
        public JSONArray jsonArray;
        public CalendarDetailH(String string_ID,String string_Attr){
            this.stringID=string_ID;
            this.stringAttr=string_Attr;
        }
        public void setText(String string_Text){
            stringText=string_Text;
        }
        public void setHistoryJsonData(JSONArray json_Data){
            jsonArray=json_Data;
        }
    }


//    class GMLObject{
//        public String name;
//        public ArrayList<GMLObject> objects;
//        public ArrayList<GMLAttr> attrs;
//        public GMLText text;
//
//        public GMLObject(String string_Name,String string_XML,int int_P){
//            name=string_Name;
//            int int_A=string_XML.indexOf(int_P);
//        }
//
//
//    }

    /**
     *{"renderedFilteredEvents":"\t\t\t\t\r\n\t \t\t\t2019\u5e7412\u670811\u65e5  \u661f\u671f\u4e09<\/td>\r\n\t \t\t<\/tr>\r\n\t\t\t\t\t \t\t\r\n\t\t\t\t 05:45<\/td>\r\n\t\t\t\t \t\t\t\t \t <\/span> NZD<\/td>\t\t\t\t \t\t\t\t \t\t\t\t \t<\/i><\/i><\/i><\/td>\r\n\t\t\t\t \t\t\t\t \t\t\t\t \u65b0\u897f\u5170\u7535\u5b50\u5361\u96f6\u552e\u9500\u552e\u5e74\u7387(%) (\u5e74\u5ea6) (\u5341\u4e00\u6708)\t\t\t\t \t\t\t\t <\/td>\r\n\t\t\t\t 5.1%<\/td>\t\t\t\t 1.5%<\/td>\t\t\t\t 1.6%<\/td>\t\t\t\t  <\/td>\r\n\t\t\t\t <\/tr>\r\n\t\t\t \t\t\t \t\t\r\n \r\n\t\t \t\t
     * <\/div>\r\n <\/td>\r\n <\/tr>\r\n\t\t \t\t\t\t\t\t\t\t \t\t\r\n\t\t\t\t 05:45<\/td>\r\n\t\t\t\t \t\t\t\t \t <\/span> NZD<\/td>\t\t\t\t \t\t\t\t \t\t\t\t \t<\/i><\/i><\/i><\/td>\r\n\t\t\t\t \t\t\t\t \t\t\t\t \u65b0\u897f\u5170\u7535\u5b50\u5361\u96f6\u552e\u9500\u552e\u6708\u7387(%) (\u6708\u5ea6) (\u5341\u4e00\u6708)\t\t\t\t \t\t\t\t <\/td>\r\n\t\t\t\t 2.6%<\/td>\t\t\t\t 0.5%<\/td>\t\t\t\t -0.5%<\/td>\t\t\t\t <\/span><\/td>\r\n\t\t\t\t <\/tr>\r\n\t\t\t \t\t\t \t\t\r\n \r\n\t\t \t\t
     * <\/div>\r\n <\/td>\r\n <\/tr>\r\n\t\t \t\t\t\t\t\t\t\t \t\t\r\n\t\t\t\t 08:00<\/td>\r\n\t\t\t\t \t\t\t\t \t <\/span> NZD<\/td>\t\t\t\t \t\t\t\t \t\t\t\t \t<\/i><\/i><\/i><\/td>\r\n\t\t\t\t \t\t\t\t \t\t\t\t \u65b0\u897f\u5170\u9884\u7b97\u5e73\u8861(\u65b0\u897f\u5170\u5143) (\u4e03\u6708)\t\t\t\t \t\t\t\t <\/td>\r\n\t\t\t\t -5.154B<\/td>\t\t\t\t  <\/td>\t\t\t\t -2.785B<\/td>\t\t\t\t  <\/td>\r\n\t\t\t\t <\/tr>\r\n\t\t\t \t\t\t \t\t\r\n \r\n\t\t \t\t
     * <\/div>\r\n <\/td>\r\n <\/tr>\r\n\t\t \t\t\t\t\t\t\t\t \t\t\r\n\t\t\t\t 08:00<\/td>\r\n\t\t\t\t \t\t\t\t \t <\/span> NZD<\/td>\t\t\t\t \t\t\t\t \t\t\t\t \t<\/i><\/i><\/i><\/td>\r\n\t\t\t\t \t\t\t\t \t\t\t\t \u65b0\u897f\u5170\u51c0\u503a\u52a1\u5360GDP\u6bd4\u91cd\u9884\u6d4b(%) (\u4e03\u6708)\t\t\t\t \t\t\t\t <\/td>\r\n\t\t\t\t 19.60%<\/td>\t\t\t\t  <\/td>\t\t\t\t 20.10%<\/td>\t\t\t\t  <\/td>\r\n\t\t\t\t <\/tr>\r\n\t\t\t \t\t\t \t\t\r\n \r\n\t\t \t\t
     * <\/div>\r\n <\/td>\r\n <\/tr>\r\n\t\t \t\t\t\t\t\t\t\t \t\t\r\n\t\t\t\t 08:00<\/td>\r\n\t\t\t\t \t\t\t\t \t <\/span> NZD<\/td>\t\t\t\t \t\t\t\t \t\t\t\t \t<\/i><\/i><\/i><\/td>\r\n\t\t\t\t \t\t\t\t \t\t\t\t \u65b0\u897f\u5170\u653f\u5e9c\u8d22\u653f\u6536\u5165\u9884\u671f(\u65b0\u897f\u5170\u5143) (\u4e03\u6708)\t\t\t\t \t\t\t\t <\/td>\r\n\t\t\t\t -0.943B<\/td>\t\t\t\t  <\/td>\t\t\t\t 3.465B<\/td>\t\t\t\t  <\/td>\r\n\t\t\t\t <\/tr>\r\n\t\t\t \t\t\t \t\t\r\n \r\n\t\t \t\t
     * <\/div>\r\n <\/td>\r\n <\/tr>\r\n\t\t \t\t\t\t\t\t","params":{"country":["43"],"timeZone":28,"dateFrom":"2019\/12\/11","dateTo":"2019\/12\/11","columns":["exc_flags","exc_currency","exc_importance","exc_actual","exc_forecast","exc_previous"],"timeFilter":"timeRemain","dst":false,"label_dst":"CST","offsetSec":28800,"offsetSecWithoutDst":28800,"offsetFormatted":"+8:00","tz":"Asia\/Hong_Kong","timezoneId":"28","gmtTimeFormat":"(GMT +8:00)","currentTimeHrMinFormatted":"23:07","requestTimeFormatted":"2019-12-18 15:07:46","requestDateTimeFormatted":"2019-12-18 23:07:46","isFiltered":true,"filterButtonState":"On"}}
     *
     */

    /**
     *<tr>
     *     <td colspan="8" class="theDay" id="theDay1576022400">2019年12月11日&nbsp;&nbsp;星期三</td>
     *</tr>
     * <tr id="eventRowId_351">
     *     <td class="center time">全天</td>
     *     <td class="flagCur"><span title="斯里兰卡" class="ceFlags Sri_Lanka float_lang_base_1">&nbsp;</span></td>
     *     <td class="left textNum sentiment"><span class="bold" >假日</span></td>
     *     <td colspan="5" class="left event">斯里兰卡 - Unduvap月圆节</td>
     * </tr>
     * <tr id="eventRowId_376712" event_attr_ID="1764" event_timestamp="2019-12-10 17:00:00" onclick="javascript:changeEventDisplay(376712, this, 'overview');">
     *     <td class="first left time" >01:00</td>
     *     <td class="left flagCur noWrap"><span title="美国" class="ceFlags United_States">&nbsp;</span> USD</td>
     *     <td class="left textNum sentiment noWrap" title="预计变数中等">
     *         <i class="newSiteIconsSprite grayFullBullishIcon middle"></i>
     *         <i class="newSiteIconsSprite grayFullBullishIcon middle"></i>
     *         <i class="newSiteIconsSprite grayEmptyBullishIcon middle"></i>
     *     </td>
     *     <td class="left event"> 美国农业部世界农产品供需预测报告 </td>
     *     <td class="bold act blackFont" title="" id="eventActual_376712">&nbsp;</td>
     *     <td class="fore" id="eventForecast_376712">&nbsp;</td>
     *     <td class="prev blackFont" id="eventPrevious_376712">&nbsp;</td>
     *     <td class="diamond" id="eventRevisedFrom_376712">&nbsp;</td>
     * </tr>
     * <tr id="eventInfo376712" class="noHover displayNone" >
     *     <td id="eventInfoTd376712" colspan="8" class="eventInfo">
     *         <div class="displayNone" id="eventDivWrapper376712"></div>
     *     </td>
     * </tr>
     * <tr id="eventRowId_398016" event_attr_ID="571" event_timestamp="2019-12-10 18:01:00" onclick="javascript:changeEventDisplay(398016, this, 'overview');">
     *     <td class="first left time" >02:01</td>
     *     <td class="left flagCur noWrap"><span title="美国" class="ceFlags United_States">&nbsp;</span> USD</td>
     *     <td class="left textNum sentiment noWrap" title="预计变数中等">
     *         <i class="newSiteIconsSprite grayFullBullishIcon middle"></i>
     *         <i class="newSiteIconsSprite grayFullBullishIcon middle"></i>
     *         <i class="newSiteIconsSprite grayEmptyBullishIcon middle"></i>
     *     </td>
     *     <td class="left event"> 美国10年期国债拍卖 </td>
     *     <td class="bold act blackFont" title="" id="eventActual_398016">1.842%</td>
     *     <td class="fore" id="eventForecast_398016">&nbsp;</td>
     *     <td class="prev blackFont" id="eventPrevious_398016">1.809%</td>
     *     <td class="diamond" id="eventRevisedFrom_398016">&nbsp;</td>
     *     </tr>
     * <tr id="eventInfo398016" class="noHover displayNone" >
     *     <td id="eventInfoTd398016" colspan="8" class="eventInfo">
     *         <div class="displayNone" id="eventDivWrapper398016"></div>
     *     </td>
     * </tr>
     * <tr id="eventRowId_398086" event_attr_ID="656" event_timestamp="2019-12-10 21:30:00" onclick="javascript:changeEventDisplay(398086, this, 'overview');">
     *     <td class="first left time" >05:30</td>
     *     <td class="left flagCur noWrap"><span title="美国" class="ceFlags United_States">&nbsp;</span> USD</td>
     *     <td class="left textNum sentiment noWrap" title="预计变数中等">
     *         <i class="newSiteIconsSprite grayFullBullishIcon middle"></i><i class="newSiteIconsSprite grayFullBullishIcon middle"></i><i class="newSiteIconsSprite grayEmptyBullishIcon middle"></i></td> <td class="left event"> 美国当周API原油库存变动(桶) </td> <td class="bold act blackFont" title="" id="eventActual_398086">1.410M</td> <td class="fore" id="eventForecast_398086">&nbsp;</td> <td class="p
     */

    /**
     *<tr>
     *     <td colspan="8" class="theDay" id="theDay1576195200">2019年12月13日&nbsp;&nbsp;星期五</td>
     *</tr>
     * <tr id="eventRowId_397367" event_attr_ID="1915" event_timestamp="2019-12-13 02:00:00" onclick="javascript:changeEventDisplay(397367, this, 'overview');">
     *     <td class="first left time" >10:00</td>
     *     <td class="left flagCur noWrap">
     *         <span title="中国" class="ceFlags China">&nbsp;</span>
     *         CNY</td>
     *         <td class="left textNum sentiment noWrap" title="预计变数较低">
     *             <i class="newSiteIconsSprite grayFullBullishIcon middle"></i>
     *             <i class="newSiteIconsSprite grayEmptyBullishIcon middle"></i>
     *             <i class="newSiteIconsSprite grayEmptyBullishIcon middle"></i>
     *             </td>
     *             <td class="left event"> 中国汤森路透/益普索主要消费者信心指数 (十二月) </td>
     *             <td class="bold act blackFont" title="" id="eventActual_397367">68.99</td>
     *             <td class="fore" id="eventForecast_397367">&nbsp;</td>
     *             <td class="prev blackFont" id="eventPrevious_397367">69.40</td>
     *             <td class="diamond" id="eventRevisedFrom_397367">&nbsp;</td>
     *             </tr> <tr id="eventInfo397367" class="noHover displayNone" >
     *                 <td id="eventInfoTd397367" colspan="8" class="eventInfo">
     *                     <div class="displayNone" id="eventDivWrapper397367"></div>
     *                     </td>
     *                     </tr>
     *                     <tr id="eventRowId_398137" event_attr_ID="588" event_timestamp="2019-12-13 07:30:00" onclick="javascript:changeEventDisplay(398137, this, 'overview');"> <td class="first left time" >15:30</td> <td class="left flagCur noWrap"><span title="中国" class="ceFlags China">&nbsp;</span> CNY</td> <td class="left textNum sentiment noWrap" title="预计变数较低"><i class="newSiteIconsSprite grayFullBullishIcon middle"></i><i class="newSiteIconsSprite grayEmptyBullishIcon middle"></i><i class="newSiteIconsSprite grayEmptyBullishIcon middle"></i></td> <td class="left event"> 中国实际外商直接投资年率(%) </td> <td class="bold act blackFont" title="" id="eventActual_398137">6.00%</td> <td class="fore" id="eventForecast_398137">&nbsp;</td> <td class="prev blackFont" id="eventPrevious_398137">6.60%</td> <td class="diamond" id="eventRevisedFrom_398137">&nbsp;</td> </tr> <tr id="eventInfo398137" class="noHover displayNone" > <td id="eventInfoTd398137" colspan="8" class="eventInfo"> <div class="displayNone" id="eventDivWrapper398137"></div> </td> </tr>
     */
}
