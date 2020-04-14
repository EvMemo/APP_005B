package com.example.app_005.Internet;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;

import com.example.app_005.Data.DailyFXData;
import com.example.app_005.Data.DailyFXInnerData;
import com.example.app_005.Tool.TimeOD;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DailyFXCollectTool {
    Context context;
    public DailyFXCollectTool(Context context_A){
        context=context_A;
        init_Data(context_A);
    }

    public void getDailyFXInnerData(String string_Url){

    }



    long longLastTime;
    String stringLastTime;
    SharedPreferences.Editor editor;
    void init_Data(Context context_A){
        SharedPreferences sp = context_A.getSharedPreferences("DailyFXCollectTool", 0);
        editor = sp.edit();
        longLastTime=sp.getLong("longLastTime",-1);
        stringLastTime=sp.getString("stringLastTime",null);
        if(longLastTime==-1)
            {
                initLongLastTime();
            }else {
            TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
            Calendar calendar_A=Calendar.getInstance();
            calendar_A.add(Calendar.DAY_OF_YEAR,-10);
            long long_A=calendar_A.getTimeInMillis();
            if(longLastTime<long_A)
                {
                    longLastTime=long_A;
                }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                DailyFXData[] dailyFXData_A=ccDailyFXHtml(longLastTime,stringLastTime);
            }
        }).start();

    }
    void initLongLastTime(){
        TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
        Calendar calendar_A=Calendar.getInstance();
        calendar_A.add(Calendar.DATE,-3);
        calendar_A.set(Calendar.HOUR_OF_DAY,0);
        calendar_A.set(Calendar.MINUTE,0);
        longLastTime=(calendar_A.getTimeInMillis()/1000);
        //Log.i(this.toString(), "initLongLastTimeG0: "+"="+longLastTime );


    }


    void saveLongLastTime(long long_A) {
        longLastTime=long_A;
        editor.putLong("longLastTime", longLastTime); //执行方法：public abstract SharedPreferences.Editor putString (String key, String value)
        editor.commit();
    }

    void collectDailyFXData(){
        String string_Url="";
        String string_Data=getHtml(string_Url);
        ccDailyFXHtml(longLastTime, stringLastTime);

    }
    DailyFXData[] ccDailyFXHtml(long long_A,String string_ID){
        String string_A="https://www.dailyfxasia.com/real-time-news";
        ArrayList<DailyFXData> dailyFXData=new ArrayList<>();
        boolean bool_B=true;
        try {
            Document document_A=Jsoup.connect(string_A).get();
            Elements elements =document_A.select("div.dfx-real-time-news-list.dfx-real-time-news-list-main");
            Elements elements_B=elements.select("li.jsdfx-feed-item.dfx-real-time-news.jsdfx-feed-item-type-livenews");
            //Log.i(this.toString(), "ccDail-" +
//                    "+" +
//                    "yFXHtmlF0: "+"="+ elements_B.size());

            for(int i=0;i<elements_B.size();i++) {
                DailyFXData dailyFXData_A = cc2DailyFXHtml(elements_B.get(i));
               // Log.i(this.toString(), "ccDailyFXHtmlS0: "+"="+dailyFXData_A.longTime +"="+ long_A +"="+(dailyFXData_A.longTime <= long_A));
                if (dailyFXData_A.longTime <= long_A) {

                    if (dailyFXData_A.longTime == long_A) {
                        if (string_ID == null || dailyFXData_A.string_ID == string_ID) {
                          //  Log.i(this.toString(), "ccDailyFXHtmlX0: "+"="+i +"="+dailyFXData_A.longTime +"="+long_A );
                            bool_B = false;
                            break;
                        }

                    } else {
                       // Log.i(this.toString(), "ccDailyFXHtmlX1: "+"="+i +"="+dailyFXData_A.longTime +"="+long_A );
                        bool_B = false;
                        break;
                    }


                }
                dailyFXData.add(dailyFXData_A);

            }
            //Log.i(this.toString(), "ccDailyFXHtmlC0: "+"="+ bool_B +"="+dailyFXData.size() +"="+long_A +"="+string_ID);
            if(bool_B&&true)
                {
                    long long_B=dailyFXData.get(dailyFXData.size()-1).longTime;
                    String string_ID_B=dailyFXData.get(dailyFXData.size()-1).string_ID;
                   // Log.i(this.toString(), "ccDailyFXHtmlD1: "+"="+long_B +"="+ string_ID_B);
                    boolean bool_C2=true;
                    int int_N=1;
                    while (bool_B){
                       // Log.i(this.toString(), "ccDailyFXHtmlD1: "+"="+ dailyFXData.size() +"="+int_N +"="+long_B +"="+string_ID_B +"="+dailyFXData.get(dailyFXData.size()-1).longTime);
                        String string_Url_B="https://www.dailyfxasia.com/real-time-news-archive/"+int_N;
                       // Log.i(this.toString(), "ccDailyFXHtmlG0: "+"="+string_Url_B );
                        Document document_A2=Jsoup.connect(string_Url_B).get();
                        Elements elements2 =document_A2.select("div.dfx-authors-articles-list.dfx-real-time-news-list");
                       // Log.i(this.toString(), "ccDailyFXHtmlG1: "+"="+elements2.size() );
                       // Log.i(this.toString(), "ccDailyFXHtmlG2: "+"="+ elements2 .html());
                        Elements elements_B2=elements2.select("li.jsdfx-feed-item.dfx-real-time-news.jsdfx-feed-item-type-livenews.dfx-tweet-old");
                       // Log.i(this.toString(), "ccDailyFXHtmlF5: "+"="+ elements_B2.size() +"="+int_N);


                        for(int i=0;i<elements_B2.size();i++)
                        {
                            DailyFXData dailyFXData_A=cc2DailyFXHtml(elements_B2.get(i));
                            if(bool_C2) {

                                if (dailyFXData_A.longTime >= long_B) {

                                    if (dailyFXData_A.longTime == long_B) {
                                        if (dailyFXData_A.string_ID == string_ID_B) {
                                            //Log.i(this.toString(), "ccDailyFXHtmlD6: "+"="+ i+"="+ dailyFXData_A.string_ID +"="+ string_ID_B);
                                            bool_C2 = false;
                                            continue;

                                        }

                                    } else {
                                       // Log.i(this.toString(), "ccDailyFXHtmlD2: "+"="+ i+"="+ dailyFXData_A.longTime +"="+long_B);
                                        bool_C2 = false;
                                        continue;
                                    }


                                } else {
                                   // continue;
                                }
                            }
                            //Log.i(this.toString(), "ccDailyFXHtmlD8: "+"="+dailyFXData_A.longTime +"="+ long_A +"="+(dailyFXData_A.longTime <= long_A));

                            if (dailyFXData_A.longTime <= long_A) {
                                if (dailyFXData_A.longTime == long_A) {
                                   // Log.i(this.toString(), "ccDailyFXHtmlD7: "+"="+ i+"="+ dailyFXData_A.string_ID +"="+ string_ID);

                                    if (string_ID == null || dailyFXData_A.string_ID == string_ID) {

                                        bool_B = false;
                                        break;
                                    }

                                } else {
                                    bool_B = false;
                                    break;
                                }


                            }
                            dailyFXData.add(dailyFXData_A);
                        }
                        int_N++;
                    }
                    //Log.i(this.toString(), "ccDailyFXHtmlD0: "+"="+ bool_B +"="+dailyFXData.size());

                }
        } catch (IOException e) {
            //Log.i(this.toString(), "ccDailyFXHtmlE0: "+"="+e.toString() );
            e.printStackTrace();
        }
        //Log.i(this.toString(), "ccDailyFXHtmlO0: "+"="+dailyFXData.size()+"="+bool_B +"="+dailyFXData.get(dailyFXData.size()-1).longTime+"="+dailyFXData.get(dailyFXData.size()-1).string_ID );
        return dailyFXData.toArray(new DailyFXData[dailyFXData.size()]);

    }
    DailyFXData cc2DailyFXHtml(Element element_A) throws IOException {
        DailyFXData dailyFXData_A = new DailyFXData();
        //Log.i(this.toString(), "cc2DailyFXHtmlD0: "+"="+ element_A.attr("data-timestamp"));
        dailyFXData_A.longTime = Long.valueOf(element_A.attr("data-timestamp"));
        dailyFXData_A.string_ID = element_A.attr("data-id");
        dailyFXData_A.string_Data = element_A.child(0).child(0).text();

//        Log.i(this.toString(), "cc2DailyFXHtmlF0: " + "=" + element_A.attr("data-id"));
//        Log.i(this.toString(), "cc2DailyFXHtmlF47: " +"="+element_A.child(0).child(0).getElementsByClass("jsdfx-feed-item-link").get(0).attr("href")+"="+element_A.child(0).child(0).children().size() +"="+element_A.child(0).child(0).children().get(0).html());
//
//        Log.i(this.toString(), "cc2DailyFXHtmlF1: " + "=" + element_A.child(0).child(0).child(0).html());
//        Log.i(this.toString(), "cc2DailyFXHtmlF12: " + "=" + element_A.child(0).child(0).html());
//        //Log.i(this.toString(), "cc2DailyFXHtmlF2: " +"="+element_A.html()+"="+element_A.child(0).html());
//        //Log.i(this.toString(), "cc2DailyFXHtmlF3: " +"="+element_A.childNodeSize());
//        Log.i(this.toString(), "cc2DailyFXHtmlF46: " + "=" + element_A.child(0).child(0).children().size());
//        Log.i(this.toString(), "cc2DailyFXHtmlF42: " + "=" + element_A.child(0).child(0).children().get(0).html());
//        Log.i(this.toString(), "cc2DailyFXHtmlF42: " + "=" + element_A.child(0).child(0).children().get(1).html());
//        Log.i(this.toString(), "cc2DailyFXHtmlF42: " + "=" + element_A.child(0).child(0).children().get(2).html());
//        Log.i(this.toString(), "cc2DailyFXHtmlF45: " + "=" + element_A.child(0).child(0).html());
//        Log.i(this.toString(), "cc2DailyFXHtmlF45: " + "=" + element_A.child(0).child(0).text());
        Elements elements_B=element_A.child(0).child(0).getElementsByClass("jsdfx-feed-item-link");
        if (elements_B.size()!=0) {
            dailyFXData_A.string_Url=elements_B.attr("href");
            if(false)
                {

                    DailyFXInnerData dailyFXInnerData_A =new DailyFXInnerData(dailyFXData_A);
                }
        }else {
            dailyFXData_A.string_Url=null;
        }
//        if (element_A.child(0).child(0).childNodeSize() >= 3) {
//            Log.i(this.toString(), "cc2DailyFXHtmlF43: " + "=" + element_A.child(0).child(0).child(0).attr("href") + "=" + (element_A.child(0).child(0).child(0).attr("href") == null) + "=" + (element_A.child(0).child(0).child(0).attr("href") == ""));
//        }

        return dailyFXData_A;
    }
    /**
     * 获取静态网页源码
     *
     * @param path the path
     * @return the html
     * @throws Exception the exception
     */
    public String getHtml(String path){
        String html=null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取html数据
            byte[] data = readInputStream(inStream);//得到html的二进制数据
            html = new String(data, "UTF-8");
        } catch (Exception e) {
            //Log.e(this.toString(), e.toString());
            e.printStackTrace();
        }
        return html;
    }
    /**
     * 转换InputStream数据
     *
     * @param inStream the in stream
     * @return the byte [ ]
     * @throws Exception the exception
     */
    private byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }




}

