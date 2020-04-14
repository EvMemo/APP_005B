package com.example.app_005.Internet;

import android.util.Log;

import com.example.app_005.Tool.TextStringTool;
import com.example.app_005.Tool.TimeOD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;

import static android.support.constraint.Constraints.TAG;

public class Jin10CalendarCollectTool {
    public Jin10CalendarCollectTool(){
        if(false)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       // getHttpTradingData(new TimeOD(System.currentTimeMillis()));
                        try {
                            for(int i=0;i<999;i++)
                                {
                                    long long_A=System.currentTimeMillis();
                                    //getEconomicCalendar(new TimeOD(System.currentTimeMillis()));
                                    getTextCalendar("157784");
                                    //]Log.w(this.toString(), "getEconomicCalendar: TT: "+"="+(System.currentTimeMillis()-long_A ));
                                    Thread.sleep(30000);

                                }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

    }
    public CalendarJinDH[] getEconomicCalendar(TimeOD timeOD_A) throws InterruptedException {
        //]Log.w(this.toString(), "getEconomicCalendar: R0U"+"="+(timeOD_A.intsA[2]));
        CalendarJinDH[] calendarJinDHS_A=null;
        String string_Data=getHttpTradingData(timeOD_A);
        Calendar calendar_A=Calendar.getInstance();
        calendar_A.set(Calendar.HOUR,0);
        calendar_A.set(Calendar.MINUTE,0);
        calendar_A.set(Calendar.SECOND,0);
        calendar_A.set(Calendar.MILLISECOND,0);
        if(string_Data==null)
            {
            for(int i=0;i<10;i++)
                {
                    Thread.sleep(10000);
                    string_Data=getHttpTradingData(timeOD_A);
                    if(string_Data!=null)
                        {
                        break;
                        }
                }
            }
        if(string_Data==null)
            {
                //]Log.w(this.toString(), "getEconomicCalendar: EE0"+"="+null );
            }
        JSONArray jsonArray_A=null;
        try {
            jsonArray_A=new JSONArray(string_Data);
            calendarJinDHS_A=new CalendarJinDH[jsonArray_A.length()];
            for(int i=0;i<jsonArray_A.length();i++)
            {
                calendarJinDHS_A[i]=new CalendarJinDH(jsonArray_A.getJSONObject(i),calendar_A);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //]Log.w(this.toString(), "getEconomicCalendar: R0"+"="+(jsonArray_A!=null) +"="+new TimeOD(System.currentTimeMillis()).intsA[4] );
        //]Log.w(this.toString(), "getEconomicCalendar: R0"+"="+(jsonArray_A.length()) );
        return calendarJinDHS_A;


    }
    public CalendarJinEV[] getEconomicCalendarEV(TimeOD timeOD_A) throws InterruptedException {
        //]Log.w(this.toString(), "getEconomicCalendar: R0U"+"="+(timeOD_A.intsA[2]));
        CalendarJinEV[] calendarJinDHS_A=null;
        String string_Data=getHttpTradingEVData(timeOD_A);
        Calendar calendar_A=Calendar.getInstance();
        calendar_A.set(Calendar.HOUR,0);
        calendar_A.set(Calendar.MINUTE,0);
        calendar_A.set(Calendar.SECOND,0);
        calendar_A.set(Calendar.MILLISECOND,0);
        if(string_Data==null)
        {
            for(int i=0;i<10;i++)
            {
                Thread.sleep(10000);
                string_Data=getHttpTradingEVData(timeOD_A);
                if(string_Data!=null)
                {
                    break;
                }
            }
        }
        if(string_Data==null)
        {
            //]Log.w(this.toString(), "getEconomicCalendar: EE0"+"="+null );
        }
        JSONArray jsonArray_A=null;
        try {
            jsonArray_A=new JSONArray(string_Data);
            calendarJinDHS_A=new CalendarJinEV[jsonArray_A.length()];
            for(int i=0;i<jsonArray_A.length();i++)
            {
                calendarJinDHS_A[i]=new CalendarJinEV(jsonArray_A.getJSONObject(i),calendar_A);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //]Log.w(this.toString(), "getEconomicCalendar: RA0"+"="+(jsonArray_A!=null) +"="+new TimeOD(System.currentTimeMillis()).intsA[4] );
        //]Log.w(this.toString(), "getEconomicCalendar: RA0"+"="+(jsonArray_A.length()) );
        return calendarJinDHS_A;


    }
    public CalendarJinText getTextCalendar(String string_ID) throws JSONException, InterruptedException {
        String string_Data=getHttpTradingDataInID(string_ID);
        if(string_Data==null)
        {
            for(int i=0;i<10;i++)
            {
                Thread.sleep(10000);
                string_Data=getHttpTradingDataInID(string_ID);
                if(string_Data!=null)
                {
                    break;
                }
            }
        }
        if(string_Data==null)
        {
            //]Log.w(this.toString(), "getTextCalendar: EE0"+"="+null );
            return null;
        }
        //]Log.w(this.toString(), "getEconomicCalendar: I0"+"="+string_Data );
        CalendarJinText calendarJinText_A=new CalendarJinText(string_Data);
        String string_Data2=getHttpTradingHistoryData(string_ID);
        JSONObject jsonObject_A=new JSONObject(string_Data2);
        JSONArray jsonArray_A=jsonObject_A.getJSONArray("data");
        if(jsonArray_A!=null)
            {
                CalendarJinHistory[] calendarJinHistories_A=new CalendarJinHistory[jsonArray_A.length()];
                String string_H="";
                for(int i=0;i<jsonArray_A.length();i++)
                    {
                        string_H+=jsonArray_A.getJSONObject(i).toString() +"=";
                        calendarJinHistories_A[i]=new CalendarJinHistory(jsonArray_A.getJSONObject(i));


                    }
                calendarJinText_A.setCalendarJinHistory(calendarJinHistories_A);
                //]Log.w(this.toString(), "getEconomicCalendar: I1"+"="+string_H );
            }
        return calendarJinText_A;

    }
    public void getHistoryCalendar(){

    }

    String getHttpTradingData(TimeOD timeOD_A){
        String string_rt=null;
        long long_A=System.currentTimeMillis();
        try {
            String string_Day=((timeOD_A.intsA[1]+1)>9?String.valueOf(timeOD_A.intsA[1]+1):"0"+(timeOD_A.intsA[1]+1));
            string_Day+=(timeOD_A.intsA[2]>9?String.valueOf(timeOD_A.intsA[2]):"0"+timeOD_A.intsA[2]);

            String string_Url="https://cdn-rili.jin10.com/data/"+timeOD_A.intsA[0]+"/"+string_Day+"/economics.json?_="+System.currentTimeMillis();
            //]Log.w(this.toString(), "Jin10CalendarCollectTool: R0"+"="+string_Url );

            URL url = new URL(string_Url);
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(20000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(50000);//设置指定时间内服务器没有返回数据的超时，5000ms
            if(false)
                {
//                    Host: cdn-rili.jin10.com
//User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0
//Accept: */*
//Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
//Accept-Encoding: gzip, deflate, br
//Access-Control-Request-Method: GET
//Access-Control-Request-Headers: x-csrf-token
//Referer: https://rili.jin10.com/?date=20200110
//Origin: https://rili.jin10.com
//Connection: keep-alive
//Cache-Control: max-age=0, no-cache
//Pragma: no-cache
//TE: Trailers
                    //Host: cdn-rili.jin10.com
                    //User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0
                    //Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
                    //Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
                    //Accept-Encoding: gzip, deflate, br
                    //Connection: keep-alive
                    //Cookie: UM_distinctid=16df449e55f9-0059f79ba68f228-4c312373-1aeaa0-16df449e56043e; x-token=; statsId2=sPgeYAqTXSeiuWu1DGEQ; statsToken=E2f4ltMKK7sZQvh0FHak
                    //Upgrade-Insecure-Requests: 1
                    String string_IJ=timeOD_A.intsA[0]+string_Day;
                    httpURLConnection.setRequestProperty("Host","cdn-rili.jin10.com");
                    httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0");
                    httpURLConnection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                    httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
                    httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
                    httpURLConnection.setRequestProperty("Connection","keep-alive");
                    httpURLConnection.setRequestProperty("Cookie","UM_distinctid=16df449e55f9-0059f79ba68f228-4c312373-1aeaa0-16df449e56043e; x-token=; statsId2=sPgeYAqTXSeiuWu1DGEQ; statsToken=E2f4ltMKK7sZQvh0FHak");
                    httpURLConnection.setRequestProperty("Upgrade-Insecure-Requests","1");
                }else {


//            httpURLConnection.setRequestProperty("Host","cdn-rili.jin10.com");
//            httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0");
//            httpURLConnection.setRequestProperty("Accept","application/json, text/javascript, */*; q=0.01");
//            httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//            httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
//            httpURLConnection.setRequestProperty("X-CSRF-TOKEN","");
//            httpURLConnection.setRequestProperty("Origin","https://rili.jin10.com");
//            httpURLConnection.setRequestProperty("Connection","keep-alive");
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
            httpURLConnection.setRequestProperty("X-Requested-With"," XMLHttpRequest");
            httpURLConnection.setRequestMethod("GET");//设置请求的方式
            }


            int code = httpURLConnection.getResponseCode();
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                  //  JSONObject jsonObject=new JSONObject(reader);
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
            //]Log.w(TAG, "getEconomicCalendar: EEE"+"="+e.toString() );
            e.printStackTrace();
            return null;
        }
        //]Log.w(TAG, "Jin10CalendarCollectTool: V0" +"="+(System.currentTimeMillis()-long_A) +"="+string_rt);
        return string_rt;
    }
    String getHttpTradingEVData(TimeOD timeOD_A){
        String string_rt=null;
        long long_A=System.currentTimeMillis();
        try {
            String string_Day=((timeOD_A.intsA[1]+1)>9?String.valueOf(timeOD_A.intsA[1]+1):"0"+(timeOD_A.intsA[1]+1));
            string_Day+=(timeOD_A.intsA[2]>9?String.valueOf(timeOD_A.intsA[2]):"0"+timeOD_A.intsA[2]);

            String string_Url="https://cdn-rili.jin10.com/data/"+timeOD_A.intsA[0]+"/"+string_Day+"/event.json?_="+System.currentTimeMillis();
            //]Log.w(this.toString(), "Jin10CalendarCollectTool: R0"+"="+string_Url );

            URL url = new URL(string_Url);
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(20000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(50000);//设置指定时间内服务器没有返回数据的超时，5000ms
            if(false)
            {
//                    Host: cdn-rili.jin10.com
//User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0
//Accept: */*
//Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
//Accept-Encoding: gzip, deflate, br
//Access-Control-Request-Method: GET
//Access-Control-Request-Headers: x-csrf-token
//Referer: https://rili.jin10.com/?date=20200110
//Origin: https://rili.jin10.com
//Connection: keep-alive
//Cache-Control: max-age=0, no-cache
//Pragma: no-cache
//TE: Trailers
                //Host: cdn-rili.jin10.com
                //User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0
                //Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
                //Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
                //Accept-Encoding: gzip, deflate, br
                //Connection: keep-alive
                //Cookie: UM_distinctid=16df449e55f9-0059f79ba68f228-4c312373-1aeaa0-16df449e56043e; x-token=; statsId2=sPgeYAqTXSeiuWu1DGEQ; statsToken=E2f4ltMKK7sZQvh0FHak
                //Upgrade-Insecure-Requests: 1
                String string_IJ=timeOD_A.intsA[0]+string_Day;
                httpURLConnection.setRequestProperty("Host","cdn-rili.jin10.com");
                httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0");
                httpURLConnection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
                httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
                httpURLConnection.setRequestProperty("Connection","keep-alive");
                httpURLConnection.setRequestProperty("Cookie","UM_distinctid=16df449e55f9-0059f79ba68f228-4c312373-1aeaa0-16df449e56043e; x-token=; statsId2=sPgeYAqTXSeiuWu1DGEQ; statsToken=E2f4ltMKK7sZQvh0FHak");
                httpURLConnection.setRequestProperty("Upgrade-Insecure-Requests","1");
            }else {


//            httpURLConnection.setRequestProperty("Host","cdn-rili.jin10.com");
//            httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0");
//            httpURLConnection.setRequestProperty("Accept","application/json, text/javascript, */*; q=0.01");
//            httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//            httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
//            httpURLConnection.setRequestProperty("X-CSRF-TOKEN","");
//            httpURLConnection.setRequestProperty("Origin","https://rili.jin10.com");
//            httpURLConnection.setRequestProperty("Connection","keep-alive");
                httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
                httpURLConnection.setRequestProperty("X-Requested-With"," XMLHttpRequest");
                httpURLConnection.setRequestMethod("GET");//设置请求的方式
            }


            int code = httpURLConnection.getResponseCode();
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                    //  JSONObject jsonObject=new JSONObject(reader);
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
            //]Log.w(TAG, "getEconomicCalendar: EEE"+"="+e.toString() );
            e.printStackTrace();
            return null;
        }
        //]Log.w(TAG, "Jin10CalendarCollectTool: V0" +"="+(System.currentTimeMillis()-long_A) +"="+string_rt);
        return string_rt;
    }
    String getHttpTradingDataInID(String string_ID){
        String string_rt=null;
        long long_A=System.currentTimeMillis();
        try {
            String string_Url="https://cdn-rili.jin10.com/data/jiedu/"+string_ID+".json";

            URL url = new URL(string_Url);
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(20000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(50000);//设置指定时间内服务器没有返回数据的超时，5000ms
            if(false)
                {
                //Host: cdn-rili.jin10.com
                    //User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0
                    //Accept: application/json, text/javascript, */*; q=0.01
                    //Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
                    //Accept-Encoding: gzip, deflate, br
                    //Origin: https://rili.jin10.com
                    //Connection: keep-alive
                    //Referer: https://rili.jin10.com/jiedu.html?id=161983
                    //If-Modified-Since: Wed, 08 Jan 2020 04:20:13 GMT
                    //If-None-Match: "33C12479ADD8C3785E846BAB5B6BEBAB"
                    //Cache-Control: max-age=0
                    httpURLConnection.setRequestProperty("Host","cdn-rili.jin10.com");
                    httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0");
                    httpURLConnection.setRequestProperty("Accept","application/json, text/javascript, */*; q=0.01");
                    httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
                    httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
                    httpURLConnection.setRequestProperty("Origin","https://rili.jin10.com");
                    httpURLConnection.setRequestProperty("Connection","keep-alive");
                    httpURLConnection.setRequestProperty("Referer","https://rili.jin10.com/jiedu.html?id="+string_ID);
                    httpURLConnection.setRequestProperty("If-Modified-Since","Wed, 08 Jan 2020 04:20:13 GMT");
                    httpURLConnection.setRequestProperty("If-None-Match","\"33C12479ADD8C3785E846BAB5B6BEBAB\"");
                    httpURLConnection.setRequestProperty("Cache-Control","max-age=0");

                }else {
//                httpURLConnection.setRequestProperty("Host","cdn-rili.jin10.com");
//                httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0");
//                httpURLConnection.setRequestProperty("Accept","application/json, text/javascript, */*; q=0.01");
//                httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//                httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
//                httpURLConnection.setRequestProperty("X-CSRF-TOKEN","");
//                httpURLConnection.setRequestProperty("Origin","https://rili.jin10.com");
//                httpURLConnection.setRequestProperty("Connection","keep-alive");
                httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
                httpURLConnection.setRequestProperty("X-Requested-With"," XMLHttpRequest");
                httpURLConnection.setRequestMethod("GET");//设置请求的方式
            }




            int code = httpURLConnection.getResponseCode();
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                    //  JSONObject jsonObject=new JSONObject(reader);
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
            //]Log.w(TAG, "getEconomicCalendar: EEE"+"="+e.toString() );
            e.printStackTrace();
            return null;
        }
        //]Log.w(TAG, "Jin10CalendarCollectTool: V0" +"="+(System.currentTimeMillis()-long_A) +"="+string_rt);
        return string_rt;
    }
    String getHttpTradingHistoryData(String string_ID){
        String string_rt=null;
        long long_A=System.currentTimeMillis();
        try {
            String string_Url="https://rili-open-api.jin10.com/getSiteChartByDateRange?dataId="+string_ID;

            URL url = new URL(string_Url);
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(20000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(50000);//设置指定时间内服务器没有返回数据的超时，5000ms
            if(false)
                {
                //Host: rili-open-api.jin10.com
                    //User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0
                    //Accept: */*
                    //Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
                    //Accept-Encoding: gzip, deflate, br
                    //x-version: 1.0.0
                    //x-app-id: 1coXNOi34tU5TDTl
                    //Origin: https://rili.jin10.com
                    //Connection: keep-alive
                    //Referer: https://rili.jin10.com/jiedu.html?id=161983
                    //Cookie: UM_distinctid=16df449e55f9-0059f79ba68f228-4c312373-1aeaa0-16df449e56043e; x-token=; statsId2=sPgeYAqTXSeiuWu1DGEQ; statsToken=kYRSXXfSOanpMBteEhnL
                    //TE: Trailers
                    httpURLConnection.setRequestProperty("Host","rili-open-api.jin10.com");
                    httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0");
                    httpURLConnection.setRequestProperty("Accept","*/*");
                    httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
                    httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
                    httpURLConnection.setRequestProperty("x-version","1.0.0");
                    httpURLConnection.setRequestProperty("x-app-id","1coXNOi34tU5TDTl");
                    httpURLConnection.setRequestProperty("Origin","https://rili.jin10.com");
                    httpURLConnection.setRequestProperty("Connection","keep-alive");
                    httpURLConnection.setRequestProperty("Referer","https://rili.jin10.com/jiedu.html?id=161983");
                    httpURLConnection.setRequestProperty("Cookie","UM_distinctid=16df449e55f9-0059f79ba68f228-4c312373-1aeaa0-16df449e56043e; x-token=; statsId2=sPgeYAqTXSeiuWu1DGEQ; statsToken=kYRSXXfSOanpMBteEhnL");
                    httpURLConnection.setRequestProperty("TE","Trailers");

                }else {

            httpURLConnection.setRequestProperty("Host","rili-open-api.jin10.com");
            httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:71.0) Gecko/20100101 Firefox/71.0");
            httpURLConnection.setRequestProperty("Accept","*/*");
            httpURLConnection.setRequestProperty("Accept-Language"," zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
            httpURLConnection.setRequestProperty("x-version","1.0.0");
            httpURLConnection.setRequestProperty("x-app-id","1coXNOi34tU5TDTl");
            httpURLConnection.setRequestProperty("Origin","https://rili.jin10.com");
            httpURLConnection.setRequestProperty("Connection","keep-alive");
            httpURLConnection.setRequestProperty("Referer","https://rili.jin10.com/jiedu.html?id="+string_ID);
            httpURLConnection.setRequestMethod("GET");//设置请求的方式

            }

            int code = httpURLConnection.getResponseCode();
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                    //  JSONObject jsonObject=new JSONObject(reader);
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
            //]Log.w(TAG, "getEconomicCalendar: EEE"+"="+e.toString() );
            e.printStackTrace();
            return null;
        }
        //]Log.w(TAG, "Jin10CalendarCollectTool: V0" +"="+(System.currentTimeMillis()-long_A) +"="+string_rt);
        return string_rt;
    }
    public class CalendarJinDH{
        public boolean boolMode;
        public int intID;
        public int intIndicatorID;
        public long longTime;
        public String stringFlag;
        public String stringTimePeriod;
        public String stringTime;
        public String stringName;
        public String stringActual;
        public String stringPrevious;
        public String stringForecast;
        public String stringUnit;
        public int intStar;
        public CalendarJinDH(JSONObject jsonObject_A,Calendar calendar_A) throws JSONException {
            intID=jsonObject_A.getInt("id");
            intIndicatorID=jsonObject_A.getInt("indicator_id");
            stringFlag=jsonObject_A.getString("country");
            stringTimePeriod=jsonObject_A.getString("time_period");
            stringName=jsonObject_A.getString("name");
            if(jsonObject_A.isNull("actual"))
                {

                    stringActual=null;
                }else {

                stringActual=jsonObject_A.getString("actual");
                //]Log.w(this.toString(), "CalendarJinDH: GR0"+"="+stringActual );
            }
            //]Log.w(this.toString(), "CalendarJinDH: E0"+"="+ (jsonObject_A.isNull("previous")));
            if(jsonObject_A.isNull("previous"))
            {

                stringPrevious=null;
            }else {

                stringPrevious=jsonObject_A.getString("previous");
            }
            if(jsonObject_A.isNull("consensus"))
            {

                stringForecast=null;
            }else {

                stringForecast=jsonObject_A.getString("consensus");
            }
//            stringPrevious=jsonObject_A.optString("previous","");
//            stringForecast=jsonObject_A.optString("consensus","");
            stringUnit=jsonObject_A.optString("unit","");
            intStar=jsonObject_A.getInt("star");
            String string_Time=jsonObject_A.getString("pub_time");
            int int_A=Integer.valueOf(string_Time.substring(11,13))+8;
            int int_B=Integer.valueOf(string_Time.substring(14,16));
            stringTime=((int_A>9)?int_A:"0"+int_A)+":"+((int_B>9)?int_B:"0"+int_B);
            calendar_A.set(Calendar.HOUR_OF_DAY,int_A);
            calendar_A.set(Calendar.MINUTE,int_B);
            longTime=calendar_A.getTimeInMillis();
            //]Log.w(this.toString(), "CalendarJinDH: V0"+"="+ intID +"="+stringFlag +stringTimePeriod+stringName +"="+stringActual+stringUnit +"="+stringPrevious+stringUnit +"="+stringForecast+stringUnit
              //      +"="+intStar +"="+stringTime);

        }
    }
    public class CalendarJinEV{
        public boolean boolPending;
        public int intID;
        public String stringFlag;
        public String stringCity;
        public String stringTime;
        public String stringEvent;
        public int intStar;
        public CalendarJinEV(JSONObject jsonObject_A,Calendar calendar_A) throws JSONException {
            if(jsonObject_A.isNull("determine"))
                {
                    boolPending=false;
                }else {
                boolPending=true;
            }
            intID=jsonObject_A.getInt("id");
            stringFlag=jsonObject_A.getString("country");
            stringCity=jsonObject_A.getString("region");
            stringEvent=jsonObject_A.getString("event_content");
            intStar=jsonObject_A.getInt("star");
            stringTime=jsonObject_A.getString("event_time");
            int int_A=Integer.valueOf(stringTime.substring(11,13))+8;
            int int_B=Integer.valueOf(stringTime.substring(14,16));
            stringTime=((int_A>9)?int_A:"0"+int_A)+":"+((int_B>9)?int_B:"0"+int_B);

        }
    }
    public class CalendarJinText{
        public String stringMethod;//统计方法
        public String stringImpact;//数据影响
        public String stringParaphrase;//数据释意
        public String stringConcern;//趣味解读
        public float floatMin;
        public float floatMax;
        public boolean boolForecast;
        public CalendarJinHistory[] calendarJinHistories;
        public CalendarJinText(String string_Data) throws JSONException {
            JSONObject jsonObject_A=new JSONObject(string_Data);
            if(!jsonObject_A.isNull("method")) stringMethod="[统计方法]= "+jsonObject_A.getString("method");
            if(!jsonObject_A.isNull("impact")) stringImpact="[数据影响]= "+jsonObject_A.getString("impact");
            if(!jsonObject_A.isNull("paraphrase")) stringParaphrase="[数据释意]= "+jsonObject_A.getString("paraphrase");
            if(!jsonObject_A.isNull("concern")) stringConcern="[趣味解读]=  "+jsonObject_A.getString("concern");
//            stringImpact=""+jsonObject_A.getString("impact");
//            stringParaphrase=""+jsonObject_A.getString("paraphrase");
//            stringConcern=""+jsonObject_A.getString("concern");
        }

        public void setCalendarJinHistory(CalendarJinHistory[] calendarJinHistory_A){
            calendarJinHistories=calendarJinHistory_A;
            ccCalendarHistoryMM();
        }
        public void ccCalendarHistoryMM(){
            if(calendarJinHistories!=null)
                {
                    if(calendarJinHistories.length>0)
                        {
                            isForecast();
                            floatMin=calendarJinHistories[0].floatPrevious;
                            floatMax=calendarJinHistories[0].floatPrevious;
                            for(int i=0;i<calendarJinHistories.length;i++)
                                {
                                    ccFloatM(calendarJinHistories[i]);
                                }

                        }

                }
        }
        public void ccFloatM(CalendarJinHistory calendarJinHistory_A){
            if(floatMin>calendarJinHistory_A.floatPrevious)floatMin=calendarJinHistory_A.floatPrevious;
            if(floatMin>calendarJinHistory_A.floatPrevious)floatMin=calendarJinHistory_A.floatPrevious;

            if(floatMax<calendarJinHistory_A.floatPrevious)floatMax=calendarJinHistory_A.floatPrevious;
            if(floatMax<calendarJinHistory_A.floatPrevious)floatMax=calendarJinHistory_A.floatPrevious;
            if(boolForecast)
            {
                if(floatMax<calendarJinHistory_A.floatForecast)floatMax=calendarJinHistory_A.floatForecast;
                if(floatMin>calendarJinHistory_A.floatForecast)floatMin=calendarJinHistory_A.floatForecast;
            }

        }
        void isForecast(){
            for(int i=0;i<calendarJinHistories.length;i++)
                {
                    if(calendarJinHistories[i].floatForecast!=0)
                    {
                        boolForecast=true;
                        return;
                    }
                }
        }
    }
    public class CalendarJinHistory{
        public int intID;
        public long longTime;
        public String stringTime;
        public float floatActual;
        public float floatPrevious;
        public float floatForecast;
        public CalendarJinHistory(JSONObject jsonObject_A) throws JSONException {
            intID=jsonObject_A.getInt("id");
            longTime=jsonObject_A.getInt("d");
            stringTime=jsonObject_A.getString("xx");
            floatActual=Float.valueOf(jsonObject_A.getString("actual"));
            floatPrevious=Float.valueOf(jsonObject_A.getString("previous"));
            floatForecast=Float.valueOf(jsonObject_A.getString("consensus"));

        }

    }
}
