package com.example.app_005.Internet;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.app_005.Class.Nak;
import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Class.SkillNewsName;
import com.example.app_005.Tool.TimeOD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class FortradeCollectTool {
    Context context;
    TimeOD timeODDayMon;
    TimeOD timeODDayEve;
    TimeOD timeODWeekly;

    String stringDayMonLast;
    String stringDayEveLast;
    String stringWeeklyLast;
    int intMicroLast;
    int intOpportunitiesLast;
    void setStringDayMonLast(String string_A){
        stringDayMonLast=string_A;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("stringDayMonLast",stringDayMonLast);
        editor.commit();

    }
    void setStringDayEveLast(String string_A){
        stringDayEveLast=string_A;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("stringDayEveLast",stringDayEveLast);
        editor.commit();
    }
    void setStringWeeklyLast(String string_A){
        stringWeeklyLast=string_A;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("stringWeeklyLast",stringWeeklyLast);
        editor.commit();
    }
    void setIntMicroLast(int int_A){
        intMicroLast=int_A;
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("intMicroLast",intMicroLast);
        editor.commit();
    }
    void setIntOpportunitiesLast(int int_A){
        intOpportunitiesLast=int_A;
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("intOpportunitiesLast",intOpportunitiesLast);
        editor.commit();
    }
    public FortradeCollectTool(Context context_A){
        context=context_A;
        initData();
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();
    }
    SharedPreferences sp;
    void initData(){
        sp = context.getSharedPreferences("FortradeCollectTool", 0);
        SharedPreferences.Editor editor = sp.edit();
        if(false )
            {
                stringDayMonLast=sp.getString("stringDayMonLast",null);
                stringDayEveLast=sp.getString("stringDayEveLast",null);
                stringWeeklyLast=sp.getString("stringWeeklyLast",null);
                intMicroLast=sp.getInt("intMicroLast",-1);
                intOpportunitiesLast=sp.getInt("intOpportunitiesLast",-1);
            }else {
            stringDayMonLast=null;
            stringDayEveLast=null;
            stringWeeklyLast=null;
            intMicroLast=-1;
            intOpportunitiesLast=-1;
        }


        ArrayList<NewsDataClass> newsDataClasses_A=new ArrayList<>();


//
//        TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
//        Calendar calendar_A=Calendar.getInstance();
//        calendar_A.add(Calendar.DAY_OF_YEAR,-8);
//        long long_A=calendar_A.getTimeInMillis();
//        if(longDayMon==-1||long_A>longDayMon)
//            {
//                longDayMon=long_A;
//
//            }
//        if(longDayEve==-1||long_A>longDayEve)
//        {
//            longDayEve=long_A;
//
//        }
//        calendar_A.add(Calendar.DAY_OF_YEAR,-7);
//        long_A=calendar_A.getTimeInMillis();
//        if(longWeekly==-1||long_A>longWeekly)
//        {
//            longWeekly=long_A;
//
//        }
    }
    public ArrayList<NewsDataClass> collextFortradeData() throws IOException, JSONException {
        //ccFortradeMicroData(null);
        ArrayList<NewsDataClass> newsDataClasses_A=new ArrayList<>();
        TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
        ArrayList<NewsDataClass> newsDataClass_A=collextFortradeDayData(true,stringDayMonLast);
        if(newsDataClass_A!=null)newsDataClasses_A.addAll(newsDataClass_A);
        if(newsDataClasses_A.size()!=0)return newsDataClasses_A;
        ArrayList<NewsDataClass> newsDataClass_B=collextFortradeDayData(false,stringDayEveLast);
        if(newsDataClass_B!=null)newsDataClasses_A.addAll(newsDataClass_B);
        if(newsDataClasses_A.size()!=0)return newsDataClasses_A;
        NewsDataClass newsDataClass_C=collextFortradeWeeklyData(stringWeeklyLast);
        if(newsDataClass_C!=null)newsDataClasses_A.add(newsDataClass_C);
        if(newsDataClasses_A.size()!=0)return newsDataClasses_A;
        ArrayList<NewsDataClass> newsDataClass_D=collextFortradeMicroData(intMicroLast);
        if(newsDataClass_D!=null)newsDataClasses_A.addAll(newsDataClass_D);
        if(newsDataClasses_A.size()!=0)return newsDataClasses_A;
        ArrayList<NewsDataClass> newsDataClass_E=collextFortradeOpportunitiesData(intOpportunitiesLast);
        if(newsDataClass_E!=null)newsDataClasses_A.addAll(newsDataClass_E);
        if(newsDataClasses_A.size()!=0)return newsDataClasses_A;
        if(newsDataClasses_A!=null)
            {
                //]Log.w(this.toString(), "collextFortradeDataR0: "+"="+ newsDataClasses_A.size());
                if(newsDataClasses_A.size()==0)
                    {
                        newsDataClasses_A=null;
                    }
            }


        return newsDataClasses_A;
        //collextFortradeMicroData(0);
        //collextFortradeDayData(timeOD_A);
    }
    NewsDataClass collextFortradeWeeklyData(String string_LastTime) throws IOException, JSONException {
        String string_Url="https://www.fortrade.com/analysis/weekly-analysis";
        Document document_A= Jsoup.connect(string_Url).get();

        Elements elements_A=document_A.select("span.insideTitleDate.dateText");
        if(elements_A.size()==0)
            {
            return null;
            }
        if(elements_A.size()==0)
            {
            return null;
            }
        //]Log.w(this.toString(), "collextFortradeWeeklyDataF0: "+"="+elements_A.size()+"="+elements_A.get(0).child(0).attr("content") );
        String string_Time=elements_A.get(0).child(0).attr("content");
        TimeOD timeOD_A=null;
        if(true)
        {
            int int_T1=string_Time.indexOf("-");
            int int_S1=Integer.valueOf(string_Time.substring(0,int_T1));
            int int_T2=string_Time.indexOf("-",int_T1+1);
            int int_S2=Integer.valueOf(string_Time.substring(int_T1+1,int_T2));
            int int_S3=Integer.valueOf(string_Time.substring(int_T2+1));
            //]Log.w(this.toString(), "collextFortradeDayDataST0: "+"="+int_S1 +"="+int_S2 +"="+int_S3 );
            timeOD_A=new TimeOD(System.currentTimeMillis());//(int_S3,int_S2,int_S1);

        }
        //]Log.w(this.toString(), "collextFortradeWeeklyDataJ0: "+"="+string_Time);
        if(string_LastTime==null||(!(string_LastTime.equals(string_Time))))
            {
//                Elements elements_B=document_A.select("div.weeklyTextSide.dynamicText");
//                //]Log.w(this.toString(), "collextFortradeWeeklyDataF1: "+"="+elements_B.get(0).childNodeSize() +"="+elements_B.get(0).text() );
//                String string_Text=elements_B.get(0).text();
//                Elements elements_B=document_A.select("div.weeklyGraphSide");
//                //]Log.w(this.toString(), "collextFortradeWeeklyDataF2: "+"="+elements_B.get(0).child(1).attr("src") );
//                String string_img=elements_B.get(0).child(1).attr("src");


                String string_Title="Fortrade"+"每周分析"+"="+(timeOD_A.intsA[2] +"-"+timeOD_A.intsA[1] +"-"+timeOD_A.intsA[0]);;
                String string_Text=document_A.select("div.weeklyTextSide.dynamicText").get(0).text();

                //]Log.w(this.toString(), "collextFortradeWeeklyData: GO2b"+"="+document_A.select("div.weeklyGraphSide").get(0).select("img").size());
                //]Log.w(this.toString(), "collextFortradeWeeklyData: GO1b"+"="+document_A.select("div.weeklyGraphSide").html());
                //]Log.w(this.toString(), "collextFortradeWeeklyData: GO1b"+"="+document_A.select("div.weeklyGraphSide").get(0).html());
                //]Log.w(this.toString(), "collextFortradeWeeklyData: GO1"+"="+document_A.select("div.weeklyGraphSide").get(0).children());
                String string_Img=null;
                if(document_A.select("div.weeklyGraphSide").size()!=0&&document_A.select("div.weeklyGraphSide").get(0).select("img").size()!=0)
                    {
                        string_Img=document_A.select("div.weeklyGraphSide").get(0).select("img").get(0).attr("src");

                    }

                //]Log.w(this.toString(), "collextFortradeWeeklyData: GO1a"+"="+document_A.select("div.weeklyGraphSide").get(0).select("img").size());

                ArrayList<Nak> naks_A=new ArrayList<>();
                naks_A.add(new Nak(0,string_Title));
                naks_A.add(new Nak(7,timeOD_A.Prc_getStringTime()));
                naks_A.add(new Nak(1,string_Text));
                if(string_Img!=null)
                    {

                        naks_A.add(new Nak(4,string_Img));
                    }
                ////]Log.w(this.toString(), "collextFortradeWeeklyDataE0: "+"="+ document_A.select("div.weeklyGraphSide").get(0).select("img").size());
               // string_Img=

                //TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
               // NewsDataFortradeWeeklyAnalysis newsDataFortradeWeeklyAnalysis=new NewsDataFortradeWeeklyAnalysis(string_Title,string_Text,true, SkillNewsName.FortradeAnalysis,1,timeOD_A,string_Img);
                setStringWeeklyLast(string_Time);

                return new NewsDataClass(string_Title,string_Text,naks_A,SkillNewsName.FortradeAnalysis,1,timeOD_A);//.retNewsDataClassJSON();
            }else {
            return null;
        }

    }




    ArrayList<NewsDataClass> collextFortradeDayData(boolean bool_A, String string_LastTime) throws IOException, JSONException {
        String string_Url="https://www.fortrade.com/analysis/daily-analysis/morning-analysis";
        if(bool_A)
            {
                string_Url="https://www.fortrade.com/analysis/daily-analysis/morning-analysis";
            }else {
            string_Url="https://www.fortrade.com/analysis/daily-analysis/evening-analysis";
        }

        Document document_A= Jsoup.connect(string_Url).get();
        Elements elements_A=document_A.select("div.row.insideIntroduction.noBorderBottom");
        if(elements_A.size()==0)
            {
            return null;
            }
        //]Log.w(this.toString(), "collextFortradeDayMorningDataF0: "+"="+elements_A.size() +"="+elements_A.get(0).child(1).text() );
        String string_Time=elements_A.get(0).child(1).text();
        TimeOD timeOD_A=null;
        if(true)
            {
                int int_T1=string_Time.indexOf("/");
                int int_S1=Integer.valueOf(string_Time.substring(0,int_T1));
                int int_T2=string_Time.indexOf("/",int_T1+1);
                int int_S2=Integer.valueOf(string_Time.substring(int_T1+1,int_T2));
                int int_S3=Integer.valueOf(string_Time.substring(int_T2+1));
                //]Log.w(this.toString(), "collextFortradeDayDataST0: "+"="+int_S1 +"="+int_S2 +"="+int_S3 );
                timeOD_A=new TimeOD(System.currentTimeMillis());//(int_S3,int_S2,int_S1);

            }
        ////]Log.w(this.toString(), "collextFortradeDayDataJ0: "+(string_LastTime!=string_Time &&string_LastTime==null)+"="+(string_LastTime.equals(string_Time))+"="+string_LastTime +"="+string_Time);
        if(string_LastTime==null||(!(string_LastTime.equals(string_Time))))
            {
                Elements elements_B=document_A.select("div.sectionWrap.catAnalysedInstruments");
//                //]Log.w(this.toString(), "collextFortradeDayMorningDataF1: "+"="+elements_B.size() +"="+elements_B.get(0).getElementsByClass("caItem").size() );
                Elements elements_C=elements_B.get(0).getElementsByClass("caItem");
//                //]Log.w(this.toString(), "collextFortradeDayMorningDataF2: "+"="+elements_C.get(0).childNodeSize() +"="+elements_C.get(0).child(0).attr("href"));
//                //]Log.w(this.toString(), "collextFortradeDayMorningDataF2: "+"="+elements_C.get(0).child(0).childNodeSize() +"="+elements_C.get(0).child(0).child(1).text());
                //String string_Title="Fortrade"+(bool_A?"早上":"晚上")+"每日分析"+"-"+string_Time;
               // String string_Text=null;
                //TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
               ArrayList<NewsDataClass> newsDataClasses_A=new ArrayList<>();

                //NewsDataFortradeDayAnalysis newsDataFortradeDayAnalysis_A =new NewsDataFortradeDayAnalysis(string_Title,string_Text,true,SkillNewsName.FortradeAnalysis,0,timeOD_A);
                //newsDataFortradeDayAnalysis_A.newsDayAnalyses =new NewsDayAnalysis[elements_C.size()];
                for(int i=0;i<elements_C.size();i++)
                {
                    NewsDataClass newsDataClass_V=collextDayAnalysisData(bool_A,elements_C.get(i).child(0).attr("href"),timeOD_A);
                    if(newsDataClass_V!=null)
                        {

                            newsDataClasses_A.add(collextDayAnalysisData(bool_A,elements_C.get(i).child(0).attr("href"),timeOD_A));
                        }
                }
                if(newsDataClasses_A.size()==0)
                    {
                    return null;
                    }
                if(bool_A)
                {
                    setStringDayMonLast(string_Time);
                }else {
                    setStringDayEveLast(string_Time);
                }
                return newsDataClasses_A;//.retNewsDataClassJSON();
            }else {
            return null;
        }


    }

//    NewsDayAnalysis collextDayAnalysisData(String string_Url) throws IOException {
//
//        Document document_A= Jsoup.connect(string_Url).get();
//        Elements elements_A=document_A.select("div.row.fundamentalAnalysis.textJustify.textContainer");
//        String string_Text=elements_A.get(0).text();
//        //]Log.w(this.toString(), "collextDayAnalysisDataF0: "+"="+elements_A.get(0).text() );
//        Elements elements_B=document_A.select("table.buySellSituationTable");
//        String string_Fod=elements_B.get(0).child(0).child(0).child(0).text();
//        String string_SMA_Buy=elements_B.get(0).getElementsByClass("technicalTable").get(0).child(0).child(0).child(1).text();
//        String string_SMA_Sell=elements_B.get(0).getElementsByClass("technicalTable").get(0).child(0).child(0).child(2).text();
//        String string_Indicator_Buy=elements_B.get(0).getElementsByClass("technicalTable").get(0).child(0).child(1).child(1).text();
//        String string_Indicator_Sell=elements_B.get(0).getElementsByClass("technicalTable").get(0).child(0).child(1).child(2).text();
//        //]Log.w(this.toString(), "collextDayAnalysisDataF1: "+"="+elements_B.size() +"="+ elements_B.get(0).child(0).child(0).child(0).text());
//        //]Log.w(this.toString(), "collextDayAnalysisDataF1: "+"="+elements_B.size() +"="+ elements_B.get(0).getElementsByClass("technicalTable").get(0).child(0).child(1).child(1).text());
//        Elements elements_C=document_A.select("div.technicalGraphic");
//        //]Log.w(this.toString(), "collextDayAnalysisDataF2: "+"="+elements_C.get(0).child(0).attr("src"));
//        String string_Img=elements_C.get(0).child(0).attr("src");
//        String[] strings_A=new String[]{string_Fod,string_SMA_Buy,string_SMA_Sell,string_Indicator_Buy,string_Indicator_Sell};
//        return new NewsDayAnalysis(string_Fod,strings_A,string_Img);
//
//    }
    NewsDataClass collextDayAnalysisData(boolean bool_A,String string_Url,TimeOD timeOD_A) throws IOException {

        Document document_A= Jsoup.connect(string_Url).get();
        if(document_A.select("div.RatioChart").size()==0)
            {
            return null;
            }
        String string_FX=document_A.select("div.RatioChart").get(0).attr("data-ratiochartsymbol");
        String string_Title="Fortrade"+(bool_A?"早上":"晚上")+"每日分析"+"="+string_FX+"-"+(timeOD_A.intsA[2]+"-"+timeOD_A.intsA[1]+"-"+timeOD_A.intsA[0]);
        Elements elements_A=document_A.select("div.row.fundamentalAnalysis.textJustify.textContainer");
        String string_Text=elements_A.get(0).text();
        Elements elements_B=document_A.select("table.buySellSituationTable");
        String string_Fod=elements_B.get(0).child(0).child(0).child(0).text();
        String string_SMA_Buy="SMA[买]="+elements_B.get(0).getElementsByClass("technicalTable").get(0).child(0).child(0).child(1).text();
        String string_SMA_Sell="SMA[卖]="+elements_B.get(0).getElementsByClass("technicalTable").get(0).child(0).child(0).child(2).text();
        String string_Indicator_Buy="技术指标[买]"+elements_B.get(0).getElementsByClass("technicalTable").get(0).child(0).child(1).child(1).text();
        String string_Indicator_Sell="技术指标[卖]="+elements_B.get(0).getElementsByClass("technicalTable").get(0).child(0).child(1).child(2).text();
        Elements elements_C=document_A.select("div.technicalGraphic");
        String string_Img=elements_C.get(0).child(0).attr("src");
        String string_ImgText=elements_C.get(0).child(1).text();

        ArrayList<Nak> naks_A=new ArrayList<>();
        naks_A.add(new Nak(0,string_Title));
        naks_A.add(new Nak(7,timeOD_A.Prc_getStringTime()));
        naks_A.add(new Nak(1,string_Text));
        naks_A.add(new Nak(2,string_Fod));
        naks_A.add(new Nak(3,string_SMA_Buy));
        naks_A.add(new Nak(3,string_SMA_Sell));
        naks_A.add(new Nak(3,string_Indicator_Buy));
        naks_A.add(new Nak(3,string_Indicator_Sell));
        naks_A.add(new Nak(4,string_Img));
        naks_A.add(new Nak(5,string_ImgText));


//        //]Log.w(this.toString(), "collextDayAnalysisDataF0: "+"="+elements_A.get(0).text() );
//
//        //]Log.w(this.toString(), "collextDayAnalysisDataF1: "+"="+elements_B.size() +"="+ elements_B.get(0).child(0).child(0).child(0).text());
//        //]Log.w(this.toString(), "collextDayAnalysisDataF1: "+"="+elements_B.size() +"="+ elements_B.get(0).getElementsByClass("technicalTable").get(0).child(0).child(1).child(1).text());

//        //]Log.w(this.toString(), "collextDayAnalysisDataF2: "+"="+elements_C.get(0).child(0).attr("src"));

        //String[] strings_A=new String[]{string_Fod,string_SMA_Buy,string_SMA_Sell,string_Indicator_Buy,string_Indicator_Sell};
        return new NewsDataClass(string_Title,string_Text,naks_A,SkillNewsName.FortradeAnalysis,0,timeOD_A);

    }
    ArrayList<NewsDataClass> collextFortradeMicroData(int int_LastTime) throws IOException, JSONException {
        String string_Url = "https://www.fortrade.com/analysis/loadMoreMicro/";
        int int_A=0;
        ArrayList<NewsDataClass> newsDataClasses=new ArrayList<>();
            if(true)
                {
                    int int_F=0;
                    int int_D=-1;
                    for(int i=0;i<8;i++)
                        {
                            string_Url = "https://www.fortrade.com/analysis/loadMoreMicro/"+int_F;
                            Connection.Response res  = Jsoup.connect(string_Url+int_A).ignoreContentType(true).execute();
                            String body = res.body();
                            JSONObject json = new JSONObject(body);
                            //]Log.w(this.toString(), "collextFortradeMicroData: "+"="+(json!=null));
                            JSONArray jsonArray=json.getJSONArray("contents");
                            //]Log.w(this.toString(), "collextFortradeMicroDataF2: "+"="+ jsonArray.length());

                            for(int e=0;e< jsonArray.length();e++)
                                {
                                    JSONObject jsonObject_A=(JSONObject)jsonArray.get(e);
                                    String string_ID=jsonObject_A.getString("id");
                                    int int_ID=Integer.valueOf(string_ID);
                                    //]Log.w(this.toString(), "collextFortradeMicroDataH0: "+"="+int_ID +"="+ int_LastTime +"="+(int_LastTime==-1||int_ID>int_LastTime)+"="+int_D);
                                    if(int_LastTime==-1||int_ID>int_LastTime)
                                    {
                                        if(int_D==-1)
                                            {
                                                int_D=int_ID;
                                            }
                                        /**
                                         *{"id":"75024",
                                         * "title":"Crude Oil",
                                         * "content":"TREND: UP\r\n\r\n\r\n\tU.S.-China made progress on trade. Will the optimism last for longer?\r\n\r\n\r\nU.S. President Donald Trump on Friday (October 11) outlined the first phase of a deal to end a trade war with China and suspended a threatened tariff hike,...",
                                         * "date":"18 October, 2019",
                                         * "link":"https:\/\/www.fortrade.com\/analysis\/micro-analysis\/crude-oil-75024"}
                                         */
                                        String string_Title=jsonObject_A.getString("title");
                                        String string_Content=jsonObject_A.getString("content");
                                        String string_Date=jsonObject_A.getString("date");
                                        String string_Link=jsonObject_A.getString("link");

                                        TimeOD timeOD_A=null;
                                        if(true)
                                        {
                                            int int_T1=string_Date.indexOf(" ");
                                            int int_S1=Integer.valueOf(string_Date.substring(0,int_T1));
                                            int int_T2=string_Date.indexOf(",",int_T1+1);
                                            String stringS2=string_Date.substring(int_T1+1,int_T2);
                                            int int_S2=0;
                                            switch(stringS2){
                                                case "January":
                                                    int_S2=1;
                                                    break;
                                                case "february":
                                                    int_S2=2;
                                                    break;
                                                case "March":
                                                    int_S2=3;
                                                    break;
                                                case "April":
                                                    int_S2=4;
                                                    break;
                                                case "May":
                                                    int_S2=5;
                                                    break;
                                                case "June":
                                                    int_S2=6;
                                                    break;
                                                case "July":
                                                    int_S2=7;
                                                    break;
                                                case "August":
                                                    int_S2=8;
                                                    break;
                                                case "September":
                                                    int_S2=9;
                                                    break;
                                                case "October":
                                                    int_S2=10;
                                                    break;
                                                case "November":
                                                    int_S2=11;
                                                    break;
                                                case "December":
                                                    int_S2=12;
                                                    break;
                                                default:
                                                    break;
                                            }
                                            int int_S3=Integer.valueOf(string_Date.substring(int_T2+2));
                                            //]Log.w(this.toString(), "collextFortradeDayDataST2: "+"="+int_S1 +"="+int_S2 +"="+stringS2 +"="+int_S3 );
                                            timeOD_A=new TimeOD(System.currentTimeMillis());//(int_S3,int_S2,int_S1);

                                        }
                                        String string_Title2="微观分析-"+string_Title+"-"+(timeOD_A.intsA[2]+"-"+timeOD_A.intsA[1]+"-"+timeOD_A.intsA[0]);
//                                        ArrayList<Nak> naks_A=new ArrayList<>();
//                                        naks_A.add(new Nak(6,string_Link));
//                                        naks_A.add(new Nak(7,string_Link));
//                                        naks_A.add(new Nak(0,string_Title2));

//                                        NewsDataFortradeMicro newsDataFortradeMicro=new NewsDataFortradeMicro(string_Title2,null,true,SkillNewsName.FortradeAnalysis,2,new TimeOD(System.currentTimeMillis()));
//                                        newsDataFortradeMicro.setNewsData(string_ID,string_Title,string_Content,string_Date,string_Link);
                                        NewsDataClass newsDataClass_B=ccFortradeMicroData(string_Link,string_Content,timeOD_A);
                                        if(newsDataClass_B!=null)
                                            {
                                                newsDataClasses.add(newsDataClass_B);//new NewsDataClass(string_Title2,string_Content,naks_A,SkillNewsName.FortradeAnalysis,2,timeOD_A));

                                            }

                                    } else {
                                        i=999;
                                        break;
                                       // return calendarDataClasses;

                                    }
                                }



                            int_F+=3;
                        }
                    if(int_D!=-1)
                    {
                        setIntMicroLast(int_D);
                    }


                }
            if(newsDataClasses.size()!=0)
                {
                   return newsDataClasses;
                }else {
                return null;

            }


    }
    NewsDataClass ccFortradeMicroData(String string_Url,String string_text,TimeOD timeOD_A) throws IOException {

        Document document_A= Jsoup.connect(string_Url).get();
        if(document_A.select("div.microAnalysisText.dynamicText").size()==0)
            {
            return null;
            }
        Element element_A=document_A.select("div.microAnalysisText.dynamicText").get(0);
        //]Log.w(this.toString(), "ccFortradeMicroDataS1: "+element_A.select("img").size());
        String string_Tendency=element_A.child(0).text();
        //]Log.w(this.toString(), "ccFortradeMicroDataS0: "+"="+ string_Tendency);
        String string_Title="微观分析"+document_A.select("h1.introFirstRow").get(0).text();
        ArrayList<Nak> naks_A=new ArrayList<>();
        naks_A.add(new Nak(0,string_Title));
        naks_A.add(new Nak(7,timeOD_A.Prc_getStringTime()));
        for(int i=0;i<element_A.children().size();i++)
            {
                if(element_A.child(i).select("img").size()>0)
                    {
                        naks_A.add(new Nak(4,element_A.child(i).select("img").get(0).attr("src")));
                        if(element_A.child(i).hasText())
                            {
                                naks_A.add(new Nak(5,element_A.child(i).text()));
                            }

                    }else {
                    if(element_A.child(i).hasText())
                    {
                        naks_A.add(new Nak(1,element_A.child(i).text()));
                    }
                }

               // //]Log.w(this.toString(), "ccFortradeMicroDataS1: "+"="+ i+"="+element_A.child(i).select("img").size()+"="+element_A.child(i).text());

            }

        return new NewsDataClass(string_Title,string_text,naks_A,SkillNewsName.FortradeAnalysis,2,timeOD_A);
    }


//    void ccFortradeMicroJson(String string_Json) throws JSONException {
//        JSONObject json = new JSONObject(string_Json);
//        //]Log.w(this.toString(), "collextFortradeMicroData: "+"="+(json!=null));
//        JSONArray jsonArray=json.getJSONArray("contents");
//        //]Log.w(this.toString(), "collextFortradeMicroDataF2: "+"="+ jsonArray.length());
//        JSONObject jsonObject_A=(JSONObject)jsonArray.get(0);
//        String string_ID=jsonObject_A.getString("id");
//        int int_ID=Integer.valueOf(string_ID);
//        String string_id=jsonObject_A.getString("id");
//        String string_content=jsonObject_A.getString("content");
//        String string_date=jsonObject_A.getString("date");
//        String string_link=jsonObject_A.getString("link");
//        /**
//         *{"id":"77163","title":"USD\/JPY",
//         * "content":"TREND: UP\r\n\r\n\r\n\tU.S.-China made progress on trade. Will the optimism last for longer?\r\n\r\n\r\nU.S. President Donald Trump on October 11 outlined the first phase of a deal to end a trade war with China and suspended a threatened tariff hike, but...",
//         * "date":"15 November, 2019",
//         * "link":"https:\/\/www.fortrade.com\/analysis\/micro-analysis\/usd-jpy-77163"}
//         */
//
//    }
    ArrayList<NewsDataClass> collextFortradeOpportunitiesData(int int_LastTime) throws IOException, JSONException {
        String string_Url = null;
        int int_A=0;
        ArrayList<NewsDataClass> newsDataClasses=new ArrayList<>();

        if(true)
        {
            int int_F=0;
            int int_D=-1;
            for(int i=0;i<8;i++)
            {
                string_Url = "https://www.fortrade.com/analysis/loadMoreOpportunity/"+int_F;
                Connection.Response res  = Jsoup.connect(string_Url+int_A).ignoreContentType(true).execute();
                String body = res.body();
                JSONObject json = new JSONObject(body);
                //]Log.w(this.toString(), "collextFortradeMicroData: "+"="+(json!=null));
                JSONArray jsonArray=json.getJSONArray("contents");
                //]Log.w(this.toString(), "collextFortradeMicroDataF2: "+"="+ jsonArray.length());
                for(int e=0;e< jsonArray.length();e++)
                {
                    JSONObject jsonObject_A=(JSONObject)jsonArray.get(e);
                    String string_ID=jsonObject_A.getString("id");
                    int int_ID=Integer.valueOf(string_ID);
                    //]Log.w(this.toString(), "collextFortradeOpportunitiesDataH0: "+"="+string_ID +"="+int_ID +"="+ int_LastTime);
                    if(int_LastTime==-1||int_ID>int_LastTime)
                    {
                        if(int_D==-1)
                            {
                                int_D=int_ID;
                            }
                        /**
                         *{"id":"75810",
                         * "title":"Gold",
                         * "content":"\r\nGold an uptrend opportunity based On 1.00 Lot Calculation:\r\n\r\nIt is highly likely that on Wednesday the US Federal Reserve will cut rates for the third time this year. So, the focus of investors will be on the forward guidance and overview of the...",
                         * "date":"30 October, 2019",
                         * "link":"https:\/\/www.fortrade.com\/analysis\/market-opportunities\/gold-75810",
                         * "image":"\"Waiting"}
                         */

                        String string_Title=jsonObject_A.getString("title");
                        String string_Content=jsonObject_A.getString("content");
                        String string_Date=jsonObject_A.getString("date");
                        String string_Link=jsonObject_A.getString("link");


                        TimeOD timeOD_A=null;
                        if(true)
                        {
                            int int_T1=string_Date.indexOf(" ");
                            int int_S1=Integer.valueOf(string_Date.substring(0,int_T1));
                            int int_T2=string_Date.indexOf(",",int_T1+1);
                            String stringS2=string_Date.substring(int_T1+1,int_T2);
                            int int_S2=0;
                            switch(stringS2){
                                case "January":
                                    int_S2=1;
                                    break;
                                case "february":
                                    int_S2=2;
                                    break;
                                case "March":
                                    int_S2=3;
                                    break;
                                case "April":
                                    int_S2=4;
                                    break;
                                case "May":
                                    int_S2=5;
                                    break;
                                case "June":
                                    int_S2=6;
                                    break;
                                case "July":
                                    int_S2=7;
                                    break;
                                case "August":
                                    int_S2=8;
                                    break;
                                case "September":
                                    int_S2=9;
                                    break;
                                case "October":
                                    int_S2=10;
                                    break;
                                case "November":
                                    int_S2=11;
                                    break;
                                case "December":
                                    int_S2=12;
                                    break;
                                default:
                                    break;
                            }
                            int int_S3=Integer.valueOf(string_Date.substring(int_T2+2));
                            //]Log.w(this.toString(), "collextFortradeDayDataST2: "+"="+int_S1 +"="+int_S2 +"="+stringS2 +"="+int_S3 );
                            timeOD_A=new TimeOD(System.currentTimeMillis());//(int_S3,int_S2,int_S1);

                        }
                        String string_Title2="市场机会-"+string_Title+"-"+(timeOD_A.intsA[2]+"-"+timeOD_A.intsA[1]+"-"+timeOD_A.intsA[0]);
//                        ArrayList<Nak> naks_A=new ArrayList<>();
//                        naks_A.add(new Nak(6,string_Link));
//                        naks_A.add(new Nak(0,string_Title2));
//                        NewsDataFortradeOpportunities newsDataFortradeMicro=new NewsDataFortradeOpportunities(string_Title2,null,true,SkillNewsName.FortradeAnalysis,2,new TimeOD(System.currentTimeMillis()));
//                        newsDataFortradeMicro.setNewsData(string_ID,string_Title,string_Content,string_Date,string_Link);
                        NewsDataClass newsDataClass_A=ccFortradeOpportunitiesData(string_Link,string_Content,timeOD_A);
                        if(newsDataClass_A!=null)
                            {
                                newsDataClasses.add(newsDataClass_A);//new NewsDataClass(string_Title2,string_Content,naks_A,SkillNewsName.FortradeAnalysis,2,timeOD_A));
                            }

                    } else {
                        i=999;
                        break;
                       // return calendarDataClasses;

                    }
                }



                int_F+=2;
            }
            if(int_D!=-1)
                {
                setIntOpportunitiesLast(int_D);
                }


        }
        if(newsDataClasses.size()!=0)
        {
            return newsDataClasses;
        }else {
            return null;

        }
    }
        NewsDataClass ccFortradeOpportunitiesData(String string_Url, String string_text, TimeOD timeOD_A) throws IOException {
            Document document_A= Jsoup.connect(string_Url).get();
            //]Log.w(this.toString(), "ccFortradeOpportunitiesData: Y0"+"="+ string_Url +"="+timeOD_A.getStringDay() +"="+(document_A!=null));
            if(document_A.select("div.row.textJustify.textContainer.dynamicText").size()==0)
                {
                    return null;

                }
            Element element_A=document_A.select("div.row.textJustify.textContainer.dynamicText").get(0);
            //]Log.w(this.toString(), "ccOpportunitiesMicroDataS1: "+element_A.select("img").size());
            String string_Tendency=element_A.child(0).text();
            //]Log.w(this.toString(), "ccFortradeMicroDataS0: "+"="+ string_Tendency);
            String string_Title="市场机会"+document_A.select("h1.introFirstRow").get(0).text()+"-"+(timeOD_A.intsA[0]+"/"+timeOD_A.intsA[1]+"/"+timeOD_A.intsA[2]);

            ArrayList<Nak> naks_A=new ArrayList<>();
            naks_A.add(new Nak(0,string_Title));
            naks_A.add(new Nak(7,timeOD_A.Prc_getStringTime()));
            for(int i=0;i<element_A.children().size();i++)
            {
                //]Log.w(this.toString(), "ccFortradeOpportunitiesDataE0: "+"="+element_A.child(i).select("img").size() );
                if(element_A.child(i).select("img").size()>0)
                {
                    naks_A.add(new Nak(4,element_A.child(i).select("img").get(0).attr("src")));
                    if(element_A.child(i).hasText())
                    {
                        naks_A.add(new Nak(5,element_A.child(i).text()));
                    }

                }else {
                    if(element_A.child(i).hasText())
                    {
                        naks_A.add(new Nak(1,element_A.child(i).text()));
                    }
                }

                // //]Log.w(this.toString(), "ccFortradeMicroDataS1: "+"="+ i+"="+element_A.child(i).select("img").size()+"="+element_A.child(i).text());

            }

            return new NewsDataClass(string_Title,string_text,naks_A,SkillNewsName.FortradeAnalysis,2,timeOD_A);
        }
//        String string_Url = "https://www.fortrade.com/analysis/market-opportunities";//https://www.fortrade.com/analysis/loadMoreOpportunity/0
//        Document document_A = Jsoup.connect(string_Url).get();
//        Elements elements_A = document_A.select("div.insideWrap.pageOpportunitiesInsideWrap");
//        //]Log.w(this.toString(), "collextFortradeOpportunitiesDataF0: " + "=" + elements_A.get(0).child(0).child(1).attr("href") + "=" + elements_A.get(0).child(0).child(1).text());
//        String string_Href = elements_A.get(0).child(0).child(1).attr("href");
//        String string_Name= elements_A.get(0).child(0).child(1).text();
//        //]Log.w(this.toString(), "collextFortradeOpportunitiesDataF1: " + "=" + elements_A.get(0).child(0).child(2).text());
//        String string_Time=elements_A.get(0).child(0).child(2).text();
//        //]Log.w(this.toString(), "collextFortradeOpportunitiesDataF1: " + "=" + elements_A.get(0).child(0).child(3).text());
//        String string_Text=elements_A.get(0).child(0).child(3).text();


        //
//        String string_Time=elements_A.get(0).child(0).attr("content");
//        Elements elements_B=document_A.select("div.weeklyTextSide.dynamicText");
//        //]Log.w(this.toString(), "collextFortradeOpportunitiesDataF1: "+"="+elements_B.get(0).childNodeSize() +"="+elements_B.get(0).text() );

    public class NewsDataFortradeDayAnalysis extends NewsDataClass{
        public int intMonEve;
        public NewsDayAnalysis[] newsDayAnalyses;
        //NewsDataClass dataClass;


        public NewsDataFortradeDayAnalysis(String stringTitle_A, String stringText_A, boolean boolMore_A, SkillNewsName skillNewsName_A, int intMode_A, TimeOD timeOD_A) throws JSONException {
            super(stringTitle_A, stringText_A, boolMore_A, skillNewsName_A, intMode_A, timeOD_A);
            //jsonObject=convertToJSON();
           // dataClass=new NewsDataClass(stringTitle_A, stringText_A, boolMore_A, skillNewsName_A, intMode_A, timeOD_A);
        }
        public JSONObject convertToJSON() throws JSONException {
            JSONObject jsonObject_A=new JSONObject();
            jsonObject_A.put("intMonEve",intMonEve);
            JSONArray jsonArray_A=new JSONArray();
            for(int i=0;i<newsDayAnalyses.length;i++)
            {
                JSONObject jsonObject_B=new JSONObject();
                jsonObject_B.put("stringText",newsDayAnalyses[i].stringText);
                jsonObject_B.put("stringtReference",newsDayAnalyses[i].stringtReference);
                jsonObject_B.put("stringSmaBuy",newsDayAnalyses[i].stringSmaBuy);
                jsonObject_B.put("stringSmaSell",newsDayAnalyses[i].stringSmaSell);
                jsonObject_B.put("stringIndicatorBuy",newsDayAnalyses[i].stringIndicatorBuy);
                jsonObject_B.put("stringIndicatorSell",newsDayAnalyses[i].stringIndicatorSell);
                jsonObject_B.put("stringImg",newsDayAnalyses[i].stringImg);
                jsonArray_A.put(jsonObject_B);
            }
            jsonObject_A.put("newsDayAnalyses",jsonArray_A);
            return jsonObject_A;
        }
//        public NewsDataClass retNewsDataClassJSON() throws JSONException {
//            dataClass.setJsonData(convertToJSON());
//            return dataClass;
//        }
    }
    public class NewsDayAnalysis{
        public String stringText;
        public String stringtReference;
        public String stringSmaBuy,stringSmaSell;
        public String stringIndicatorBuy,stringIndicatorSell;
        public String stringImg;
        public NewsDayAnalysis(String string_Text, String[] strings_A, String string_Img ){

            stringText=string_Text;
            stringtReference=strings_A[0];
            stringSmaBuy=strings_A[1];
            stringSmaSell=strings_A[2];
            stringIndicatorBuy=strings_A[3];
            stringIndicatorSell=strings_A[4];
            stringImg=string_Img;
        }
    }
    public class NewsDataFortradeWeeklyAnalysis extends NewsDataClass{
        public String stringImg;
       // NewsDataClass dataClass;

        public NewsDataFortradeWeeklyAnalysis(String stringTitle_A, String stringText_A, boolean boolMore_A, SkillNewsName skillNewsName_A, int intMode_A, TimeOD timeOD_A,String string_Img) {
            super(stringTitle_A, stringText_A, boolMore_A, skillNewsName_A, intMode_A, timeOD_A);
           // dataClass=new NewsDataClass(stringTitle_A, stringText_A, boolMore_A, skillNewsName_A, intMode_A, timeOD_A);
            stringImg=string_Img;
        }
//        public NewsDataClass retNewsDataClassJSON() throws JSONException {
//            dataClass.setJsonData(convertToJSON());
//            return dataClass;
//        }
        public JSONObject convertToJSON() throws JSONException {
            JSONObject jsonObject_A=new JSONObject();
            jsonObject_A.put("stringImg",stringImg);
            return jsonObject_A;
        }
    }
    public class NewsDataFortradeOpportunities extends NewsDataClass{
        public String stringID;
        public String stringTitle;
        public String stringContent;
        public String stringDate;
        public String stringLink;

        public NewsDataFortradeOpportunities(String stringTitle_A, String stringText_A, boolean boolMore_A, SkillNewsName skillNewsName_A, int intMode_A, TimeOD timeOD_A) {
            super(stringTitle_A, stringText_A, boolMore_A, skillNewsName_A, intMode_A, timeOD_A);
            //dataClass=new NewsDataClass(stringTitle_A, stringText_A, boolMore_A, skillNewsName_A, intMode_A, timeOD_A);
        }
        public void setData(String stringID,String stringTitle,String stringContent,String stringDate,String stringLink){
            this.stringID=stringID;
            this.stringTitle=stringTitle;
            this.stringContent=stringContent;
            this.stringDate=stringDate;
            this.stringLink=stringLink;
        }
        public JSONObject convertToJSON() throws JSONException {
            JSONObject jsonObject_A=new JSONObject();
            jsonObject_A.put("stringID",stringID);
            jsonObject_A.put("stringTitle",stringTitle);
            jsonObject_A.put("stringContent",stringContent);
            jsonObject_A.put("stringDate",stringDate);
            jsonObject_A.put("stringLink",stringLink);
            return jsonObject_A;
        }
       /* NewsDataClass dataClass;
        public NewsDataClass retNewsDataClassJSON() throws JSONException {
            dataClass.setJsonData(convertToJSON());
            return dataClass;
        }*/
    }
    public class NewsDataFortradeMicro extends NewsDataClass{
        public String stringID;
        public String stringTitle;
        public String stringContent;
        public String stringDate;
        public String stringLink;
        //NewsDataClass dataClass;

        public NewsDataFortradeMicro(String stringTitle_A, String stringText_A, boolean boolMore_A, SkillNewsName skillNewsName_A, int intMode_A, TimeOD timeOD_A) {
            super(stringTitle_A, stringText_A, boolMore_A, skillNewsName_A, intMode_A, timeOD_A);
            //dataClass=new NewsDataClass(stringTitle_A, stringText_A, boolMore_A, skillNewsName_A, intMode_A, timeOD_A);
        }
        public void setData(String stringID,String stringTitle,String stringContent,String stringDate,String stringLink){
            this.stringID=stringID;
            this.stringTitle=stringTitle;
            this.stringContent=stringContent;
            this.stringDate=stringDate;
            this.stringLink=stringLink;
        }
        public JSONObject convertToJSON() throws JSONException {
            JSONObject jsonObject_A=new JSONObject();
            jsonObject_A.put("stringID",stringID);
            jsonObject_A.put("stringTitle",stringTitle);
            jsonObject_A.put("stringContent",stringContent);
            jsonObject_A.put("stringDate",stringDate);
            jsonObject_A.put("stringLink",stringLink);
            return jsonObject_A;
        }
//        public NewsDataClass retNewsDataClassJSON() throws JSONException {
//            dataClass.setJsonData(convertToJSON());
//            return dataClass;
//        }
    }
//    void ccFortradeOpportunitiesJson(String string_json) throws JSONException {
//        JSONObject json = new JSONObject(string_json);
//        //]Log.w(this.toString(), "collextFortradeMicroData: "+"="+(json!=null));
//        JSONArray jsonArray=json.getJSONArray("contents");
//        //]Log.w(this.toString(), "collextFortradeMicroDataF2: "+"="+ jsonArray.length());
//        JSONObject jsonObject_A=(JSONObject)jsonArray.get(0);
//        String string_ID=jsonObject_A.getString("id");
//        int int_ID=Integer.valueOf(string_ID);
//        String string_id=jsonObject_A.getString("id");
//        String string_content=jsonObject_A.getString("content");
//        String string_date=jsonObject_A.getString("date");
//        String string_link=jsonObject_A.getString("link");
//        /**
//         *{"id":"76686","title":"GBP\/USD",
//         * "content":"GBP\/USD uptrend opportunity based On 1.00 Lot Calculation:\r\n\r\nThe British parliament yesterday again surprised with its twofold decision: on the one hand, it supported the general principles of the proposed deal, but on the other hand, refused to...",
//         * "date":"23 October, 2019",
//         * "link":"https:\/\/www.fortrade.com\/analysis\/market-opportunities\/gbp-usd-76686",
//         * "image":"\"Waiting"}
//         */
//    }

}
