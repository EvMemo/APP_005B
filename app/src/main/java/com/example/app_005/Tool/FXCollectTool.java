package com.example.app_005.Tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Class.SkillQuoName;
import com.example.app_005.Internet.FortradeCollectTool;
import com.example.app_005.Internet.Jin10CalendarCollectTool;
import com.example.app_005.Internet.XMCalendarCollertTool;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class FXCollectTool {
    //MyFXCollectService myFXCollectService;
    FXDataConnect.TimeConnect timeConnect;
    FXQuoCollect fxQuoCollect;
    FXNewsCollect fxNewsCollect;
    public FXCalendarCollect fxCalendarCollect;
    public FXCollectTool(Context context, FXDataConnect fxDataConnect){
       // myFXCollectService=myFXCollectServiceAA;
        timeConnect=fxDataConnect.getTimeConnect();
        fxQuoCollect=new FXQuoCollect(context,fxDataConnect.getQuoConnect());
        fxNewsCollect=new FXNewsCollect(context,fxDataConnect.getNewsConnect());
        fxCalendarCollect=new FXCalendarCollect(context,fxDataConnect.getCalendarConnect());
    }
    public void reqCollectQuoData(){
        fxQuoCollect.PuC_reqCollertFXQuoData();
    }
    public void reqCollectQuoLine(){
        fxQuoCollect.PuC_reqCollertFXQuoLine();
    }
    public void reqCollectNewsData(){
        fxNewsCollect.reqCollectNewsData();
    }
    public void reqCollectCalendarJin10TreeData(){
        TimeOD timeOD_A=timeConnect.getTimeNew();
        TimeOD[] timeODS_A=new TimeOD[]{timeOD_A,timeOD_A.PuC_getCrTimeOD(-1),timeOD_A.PuC_getCrTimeOD(1)};
        fxCalendarCollect.reqCollectFXCalendarJin10Data(timeODS_A);
        //]Log.w(TAG, "reqCollectCalendarJin10TreeData: E0" +"="+"OK");
    }
    public void reqCollectCalendarDHData(long long_Time){
        fxCalendarCollect.reqCollectFXCalendarFirstDHData(long_Time);
    }
    public void reqCollectCalendarCCData(){
        fxCalendarCollect.reqCollectFXCalendarCCData();
    }
    public void reqCollectCalendarJin10Data(){
        fxCalendarCollect.reqCollectFXCalendarJin10Data();
    }
//    public void reqCollectCalendarJin10Data(TimeOD timeOD){
//        fxCalendarCollect.reqCollectFXCalendarJin10Data();
//    }


    public class FXNewsCollect{
        public Context context;
        HandlerThread handlerThread;
        public Handler handler;
        String syStr_handler="syStr_handler";
        boolean syBool_handler;
        FXDataConnect.NewsConnect newsConnect;
        public FXNewsCollect(Context contextAA, FXDataConnect.NewsConnect newsConnectAA){
            context=contextAA;
            newsConnect=newsConnectAA;
            fortradeCollectTool=new FortradeCollectTool(contextAA);
            handlerThread=new HandlerThread("FXNewsCollect");
            handlerThread.start();
            handler =new Handler(handlerThread.getLooper() ) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch(msg.what){
                        case 0://请求收集/shōu jí/数据
                            synchronized (syStr_handler){
                                if(syBool_handler)
                                    {
                                        syBool_handler=false;
                                    }else {
                                    return;
                                }
                            }
                            startCollectNewsData();
                            break;
                        case 1:
                            break;
                        default:
                            break;
                    }
                }
            };
        }
        public void reqCollectNewsData(){
            synchronized (syStr_handler){
                if(!syBool_handler)
                {
                    syBool_handler=true;
                    reqCollectNewsDataMessage(0);
                }
            }
        }
        void reqCollectNewsDataMessage(int int_A){
            handler.sendEmptyMessage(int_A);
        }
        void startCollectNewsData(){
            try {
                collectNewsFortrade();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        FortradeCollectTool fortradeCollectTool;
        void collectNewsFortrade() throws IOException, JSONException {

            long long_A=System.currentTimeMillis();
            ////Log.w(TAG, "collectNewsFortradeF2: "+"="+true );
                ArrayList<NewsDataClass> newsDataClasses_A=fortradeCollectTool.collextFortradeData();
            ////Log.w(this.toString(), "collectNewsFortradeF0: "+"="+(newsDataClasses_A!=null)+"="+(System.currentTimeMillis()-long_A) );
            ////Log.w(this.toString(), "collectNewsFortradeF1: "+"="+(newsDataClasses_A.size()) );
                if(newsDataClasses_A!=null)
                    {
                        newsConnect.addNewsData(newsDataClasses_A,new TimeOD(System.currentTimeMillis()));
                    }


        }


//        /**
//         *请求收集DaiFX新闻数据
//         */
//        public void PuC_reqCollectDaiFXNewsData(){
//            synchronized (syStr_handler){
//                if(!syBool_handler)
//                {
//                    syBool_handler=true;
//                    handler.sendEmptyMessage(0);
//                }else {
//                    return;
//                }
//
//            }
//        }
//        void CollectDaiFXNewsData(){
//            String string_A="https://www.dailyfxasia.com/real-time-news";
//            String string_B=getHtml(string_A);
//            ArrayList<FXDataPool.FXNews.NewsDData> newsDDatas_A=null;
//        }
//        /**
//         *
//         */
//        void FirstcollectDaiFXNewsData(TimeOD timeOD){
//            String string_A="https://www.dailyfxasia.com/real-time-news";
//            String string_B=getHtml(string_A);
//            ArrayList<FXDataPool.FXNews.NewsDData> newsDDatas_A=null;
//        }
//        /**
//         *分析数据
//         */
//        ArrayList<FXDataPool.FXNews.NewsDData> analyzeDaiFXNewsData(String stringAA,String stringIDAA){
//            ArrayList<FXDataPool.FXNews.NewsDData> newsDDatas_A=null;
//
//            return  newsDDatas_A;
//        }

//        /**
//         *记录/jì lù/
//         */
//        public class NewsCollectRecord{
//            public String recordLastID;//最后/zuì hòu/记录
//            public long recordLastTime;
//            public String datafile;
//            public NewsCollectRecord(){
//
//
//            }
//
//            public boolean PuC_initRecordSPData(){
//                SharedPreferences diySP = context.getSharedPreferences("NewsCollectRecord",Context.MODE_PRIVATE) ;
//                recordLastID =diySP.getString("recordLastID",null);
//                if(recordLastID !=null)
//                    {
//                        recordLastTime =diySP.getLong("recordLastTime",-1);
//                        return true;
//                    }else {
//                    return false;
//                }
//            }
//            /**
//             *
//             */
//            public void PuC_setRecordSPData(){
//                SharedPreferences diySP = context.getSharedPreferences("NewsCollectRecord",Context.MODE_PRIVATE) ;
//                diySP.edit()
//                        .putString("recordLastID",recordLastID)
//                        .putLong("recordLastTime", recordLastTime)
//                        .apply();
//            }
//        }
    }
    public class FXQuoCollect{
        public Context context;
        HandlerThread handlerThread;
        public Handler handler;
        HandlerThread handlerThreadLine;
        public Handler handlerLine;
        String syStr_handler="FXQuoCollect";
        boolean syBool_handler;
        String syStr_handlerLine="FXQuoCollectLine";
        boolean syBool_handlerLine;
        FXDataConnect.QuoConnect quoConnect;
        TradingCollectTool tradingCollectTool;
        public FXQuoCollect(Context contextAA,FXDataConnect.QuoConnect quoConnectAA){
            context=contextAA;
            quoConnect=quoConnectAA;
            tradingCollectTool=new TradingCollectTool();
            handlerThread=new HandlerThread("FXQuoCollect");
            handlerThread.start();
            handler =new Handler(handlerThread.getLooper() ) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch(msg.what){
                        case 0://请求收集/shōu jí/数据
                            ////Log.w(TAG, "FXQuoCollect_handleMessage: "+"="+syBool_handler );
                            synchronized (syStr_handler){
                                if(syBool_handler)
                                {
                                    syBool_handler=false;
                                }else {

                                    return;
                                }

                            }
                            Calendar calendar_A=Calendar.getInstance();

                            CollectDaiFXQuoData();
                            Calendar calendar_B=Calendar.getInstance();
                            ////Log.w(TAG, "FXQuoCollect_handleMessageA0: "+"="+ calendar_A.get(Calendar.SECOND)+"="+ calendar_B.get(Calendar.SECOND) );

                            break;
                        default:
                            break;
                    }
                }
            };
            handlerThreadLine=new HandlerThread("FXQuoCollectLine");
            handlerThreadLine.start();
            handlerLine =new Handler(handlerThreadLine.getLooper() ) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch(msg.what){
                        case 0://请求收集/shōu jí/Line数据
                            synchronized (syStr_handlerLine){
                                if(syBool_handlerLine)
                                {
                                    syBool_handlerLine=false;
                                }else {
                                    return;
                                }


                            }
                            collectFXQuoLineData();
                            break;
                        default:
                            break;
                    }
                }
            };

        }
        /**
         *
         */
        public void PuC_reqCollertFXQuoData(){
            synchronized (syStr_handler){
                ////Log.w(TAG, "PuC_reqCollertFXQuoData: "+"="+syBool_handler );
                if(!syBool_handler)
                {
                    syBool_handler=true;

                    handler.sendEmptyMessage(0);
                }else {
                    return;
                }

            }

        }
        public void PuC_reqCollertFXQuoLine(){
            synchronized (syStr_handlerLine){
                ////Log.w(TAG, "PuC_reqCollertFXQuoData: "+"="+syBool_handler );
                if(!syBool_handlerLine)
                {
                    syBool_handlerLine=true;

                    handlerLine.sendEmptyMessage(0);
                }else {
                    return;
                }

            }

        }
        void CollectDaiFXQuoData(){

            float[][] floats_Data=tradingCollectTool.collectQuoTradingData();
            //"bid","ask","high","low","close","change","change_abs"
            if(floats_Data==null)return;
            QuoCollectData[] quoDDatas_B=new QuoCollectData[floats_Data.length];
            for(int i=0;i<quoDDatas_B.length;i++)
                {
                    quoDDatas_B[i]=new QuoCollectData(floats_Data[i]);
                }
            ////Log.w(TAG, "CollectDaiFXQuoDataT0 "+"="+ quoDDatas_B.length);
            quoConnect.assignQuoData(quoDDatas_B,timeConnect.getTimeNew());
            if(false)
                {
                    String string_A="https://webrates.truefx.com/rates/connect.html?f=html";
                    String string_B=getHtml(string_A);
                    // //Log.w(TAG, "CollectDaiFXQuoData0A: "+"="+string_B );
                    QuoCollectData[] quoDDatas_A=AnalyzeDaiFXQuoData(string_B);
                    ////Log.w(TAG, "CollectDaiFXQuoData01: "+"="+quoDDatas_A.length );
                    for(int i=0;i<1;i++)
                    {
                        float[] floats_A=quoDDatas_A[i].floats;
                        ////Log.w(TAG, "CollectDaiFXQuoData02: "+"="+floats_A[0]+"="+floats_A[1]+"="+floats_A[2]+"="+floats_A[3]+"="+floats_A[4] );

                    }
                    quoConnect.assignQuoData(quoDDatas_A,timeConnect.getTimeNew());

                }


        }
        void collectFXQuoLineData(){
            float[][] floats_Data=tradingCollectTool.collectQuoTradingLine();
            if(floats_Data==null)return;
            QuoLineCollectData[] quoDDatas_B=new QuoLineCollectData[floats_Data.length];
            for(int i=0;i<quoDDatas_B.length;i++)
            {
                quoDDatas_B[i]=new QuoLineCollectData(floats_Data[i]);
            }
            quoConnect.assignQuoLine(quoDDatas_B,timeConnect.getTimeNew());
        }

        /**
         *
         */
        public void PuC_firstcollectDaiFXQuoData(TimeOD timeOD){

        }
        /**
         *分析数据
         */
        QuoCollectData[] AnalyzeDaiFXQuoData(String stringAA){
            QuoCollectData[] quoDDatas_A=null;
            if(true)
                {
                    Calendar calendar_A=Calendar.getInstance();
                    calendar_A.set(Calendar.SECOND,0);
                    calendar_A.set(Calendar.MILLISECOND,0);
                    long long_A=calendar_A.getTimeInMillis();//-longA;

                    String string_A=stringAA;
                    //Log.w(TAG, "AnalyzeDaiFXQuoDataE01: "+"="+ stringAA);
                    StringBuilder stringBuilder_A =new StringBuilder (stringAA);

                    boolean boolean_A =true;
                    ArrayList<int[]> arrayList_A=new ArrayList<>();
                    int[] ints_A=new int[]{0,0};
                    while (boolean_A){
                        ints_A[0]=stringBuilder_A.indexOf("<tr>",ints_A[0]);
                        if(ints_A[0]==-1) {Log.d(TAG, "CrC_FXString_2");break;}

                        ints_A[1]=stringBuilder_A.indexOf("</tr>",ints_A[0]+4);
                        if(ints_A[1]==-1) {Log.d(TAG, "CrC_FXString_2");break;}
                        arrayList_A.add((new int[]{ints_A[0],ints_A[1]}));
                        ints_A[0]=ints_A[1]+5;
                    }

                    if(arrayList_A.size()!=0)
                    {
                        quoDDatas_A=new QuoCollectData[arrayList_A.size()];
                        ArrayList<String[]> arrayList_B=new ArrayList<>();
                        for(int i=0;i<arrayList_A.size();i++)
                        {
                            quoDDatas_A[i]=new QuoCollectData(CrC_FXString_1(stringBuilder_A,arrayList_A.get(i)));
                        }
                        //PrO_FXQuotedDataForDataService(fxQuotedData_A01,long_A);

                    }else {
                        Log.e(TAG, "CrC_FXString: " );
                    }
                }

            return  quoDDatas_A;
        }
        /**
         * 转换成报价数据
         *
         * @param stringBuilderAA the string builder aa
         * @param intsAA          the ints aa
         * @return the string [ ]
         */
        float[] CrC_FXString_1(StringBuilder stringBuilderAA,int[] intsAA ){
            String[] strings_A=new String[4];//名,时,卖,买,小,大,开
            float[] floats_A=new float[5];//卖,买,小,大,开
            String string_A=stringBuilderAA.substring(intsAA[0]+4,intsAA[1]);
            Log.d(TAG, "CrC_FXString_4: "+string_A);
            int[] ints_B=new int[]{0,0};
            int int_C2=0;

            for(int i=0;i<9;i++)
            {
                ints_B[0]=string_A.indexOf("<td>",ints_B[0]);
                ints_B[1]=string_A.indexOf("</td>",ints_B[0]+4);
                Log.d(TAG, "CrC_FXString_5:"+string_A.substring(ints_B[0]+4,ints_B[1]) +"="+i);
                switch(i){
                    case 0:
                        strings_A[i]=string_A.substring(ints_B[0]+4,ints_B[1]);
                        break;
                    case 1:
                        //int iny=1559940300020;
                        strings_A[i]=string_A.substring(ints_B[0]+4,ints_B[1]);
                        break;
                    case 2:
                    case 4:
                        String string_B2=string_A.substring(ints_B[0]+4,ints_B[1]);
                        ints_B[0]=ints_B[1]+5;
                        ints_B[0]=string_A.indexOf("<td>",ints_B[0]);
                        ints_B[1]=string_A.indexOf("</td>",ints_B[0]+4);
                        string_B2+=string_A.substring(ints_B[0]+4,ints_B[1]);
                        floats_A[int_C2]=Float.valueOf(string_B2);
                        int_C2++;
                        i++;
                        break;
                    case 6:
                    case 7:
                    case 8:
                        String string_B=string_A.substring(ints_B[0]+4,ints_B[1]);
                        //ints_B[0]=ints_B[1]+5;
                        //ints_B[0]=string_A.indexOf("<td>",ints_B[0]);
                       // ints_B[1]=string_A.indexOf("</td>",ints_B[0]+4);
                       // string_B+=string_A.substring(ints_B[0]+4,ints_B[1]);
                        //]Log.w(TAG, "CrC_FXString_1: ="+string_B);
                        floats_A[int_C2]=Float.valueOf(string_B);
                        int_C2++;
                        break;
                    default:

                        break;
                }
                ints_B[0]=ints_B[1]+5;

            }

           // Log.d(TAG, "CrC_FXString_6: "+strings_A[0] +"="+strings_A[1]+"="+strings_A[2]+"="+strings_A[3]+"="+strings_A[4]+"="+strings_A[5]+"="+strings_A[6]);


            return floats_A;

        }
        public class QuoCollectData{
            float[] floats;
            public QuoCollectData(float[] floatsAA){
                floats=floatsAA;
            }
        }
        public class QuoLineCollectData{
            SkillQuoName skillQuoName;
            float[][] floatsLineData;
            public QuoLineCollectData(float[] floats_Data){
                skillQuoName=SkillQuoName.getSkill((int)floats_Data[0]);
                ccfloatLineData(floats_Data);
                //Log.w(TAG, "QuoLineCollectData: "+"="+ floatsLineData.length);
            }
            void ccfloatLineData(float[] floats_Data){
                floatsLineData=new float[26][];
                int int_A=1;
                int int_Name=0;
                //Log.w(TAG, "ccfloatLineData: F0"+"="+floats_Data.length );
                //["Recommend.All|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++]};
                //"Recommend.Other|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++]};
                //"Recommend.MA|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++]};
                // "RSI|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++]};
                // "Stoch.K|1","Stoch.D|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++]};
                // "Stoch.RSI.K|1","Stoch.RSI.D|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++]};
                // "MACD.macd|1","MACD.signal|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++]};
                // "ADX|1","ADX+DI|1","ADX-DI|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++]};
                // "W.R|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++]};
                // "CCI20|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++]};
                // "ROC|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++]};
                // "BBPower|1","Rec.BBPower|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++]};
                // "AO|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++]};
                // "Mom|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++]};
                //"ATR"
                int_Name++;
                //"UO"
                int_Name++;
                // "SMA5|1","SMA10|1","SMA20|1","SMA30|1","SMA50|1","SMA100|1","SMA200|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++]};
                // "EMA5|1","EMA10|1","EMA20|1","EMA30|1","EMA50|1","EMA100|1","EMA200|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++]};
                // "Rec.Ichimoku|1","Ichimoku.BLine|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++]};
                // "Rec.VWMA|1","VWMA|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++]};
                // "Rec.HullMA9|1","HullMA9|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++]};
                // "Pivot.M.Classic.S3|1","Pivot.M.Classic.S2|1","Pivot.M.Classic.S1|1","Pivot.M.Classic.Middle|1","Pivot.M.Classic.R1|1","Pivot.M.Classic.R2|1","Pivot.M.Classic.R3|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++]};
                // "Pivot.M.Fibonacci.S3|1","Pivot.M.Fibonacci.S2|1","Pivot.M.Fibonacci.S1|1","Pivot.M.Fibonacci.Middle|1","Pivot.M.Fibonacci.R1|1","Pivot.M.Fibonacci.R2|1","Pivot.M.Fibonacci.R3|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++]};
                // "Pivot.M.Camarilla.S3|1","Pivot.M.Camarilla.S2|1","Pivot.M.Camarilla.S1|1","Pivot.M.Camarilla.Middle|1","Pivot.M.Camarilla.R1|1","Pivot.M.Camarilla.R2|1","Pivot.M.Camarilla.R3|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++]};
                // "Pivot.M.Woodie.S3|1","Pivot.M.Woodie.S2|1","Pivot.M.Woodie.S1|1","Pivot.M.Woodie.Middle|1","Pivot.M.Woodie.R1|1","Pivot.M.Woodie.R2|1","Pivot.M.Woodie.R3|1",
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++]};
                // "Pivot.M.Demark.S1|1","Pivot.M.Demark.Middle|1","Pivot.M.Demark.R1|1"]
                floatsLineData[int_Name]=new float[]{int_Name++,floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++],floats_Data[int_A++]};

            }

        }
        /**
         *记录/jì lù/
         */
        public class QuoCollectRecord{
            public long recordLastTime;//最后/zuì hòu/记录
            public String datafile;
            public QuoCollectRecord(){


            }

            public boolean PuC_initRecordSPData(){
                SharedPreferences diySP = context.getSharedPreferences("QuoCollectRecord",Context.MODE_PRIVATE) ;
                recordLastTime =diySP.getLong("recordLastTime",-1);
                if(recordLastTime!=-1)
                {

                    return true;
                }else {
                    return false;
                }
            }
            /**
             *
             */
            public void PuC_setRecordSPData(){
                SharedPreferences diySP = context.getSharedPreferences("QuoCollectRecord",Context.MODE_PRIVATE) ;
                diySP.edit()
                        .putLong("recordLastTime", recordLastTime)
                        .apply();
            }
        }
    }
    public class FXCalendarCollect{
        public Context context;
        HandlerThread handlerThread;
        public Handler handler;
        String syStr_handler="FXCalendarCollect";
        boolean syBool_handler;
        FXDataConnect.CalendarConnect calendarConnect;
        XMCalendarCollertTool xmCalendarCollertTool;
        Jin10CalendarCollectTool jin10CalendarCollectTool;
        public FXCalendarCollect(Context contextAA, FXDataConnect.CalendarConnect connertConnect_A){
            context=contextAA;
            calendarConnect =connertConnect_A;
            xmCalendarCollertTool=new XMCalendarCollertTool();
            jin10CalendarCollectTool=new Jin10CalendarCollectTool();
            handlerThread=new HandlerThread("FXCalendarCollect");
            handlerThread.start();
            handler =new Handler(handlerThread.getLooper() ) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    synchronized (syStr_handler){
                        if(syBool_handler)
                        {
                            syBool_handler=false;
                        }else {

                            return;
                        }

                    }
                    switch(msg.what){
                        case 0://请求收集/shōu jí/数据
                          //  collectFXCalendarCCData();
                            break;
                        case 1://请求收集/shōu jí/数据

                           // collectFXCalendarFirstDHData((long)msg.obj);
                            break;
                        case 2://请求收集/shōu jí/数据

                            collectFXCalendarJin10();
                            break;
                            case 3://请求收集/shōu jí/数据

                            collectFXCalendarJin10((TimeOD[])msg.obj);
                            break;
                        default:
                            break;
                    }
                }
            };


        }
        /**
         *初次
         */
        public void reqCollectFXCalendarFirstDHData(long long_Time){
            synchronized (syStr_handler){
                //Log.w(TAG, "PuC_reqCollertFXQuoData: "+"="+syBool_handler );
                if(!syBool_handler)
                {
                    syBool_handler=true;
                    long long_A=0;
                    Message message_A=new Message();
                    message_A.what=1;
                    message_A.obj=long_Time;

                    handler.sendMessage(message_A);
                }else {
                    return;
                }

            }

        }
        public void reqCollectFXCalendarCCData(){
            synchronized (syStr_handler){
                //Log.w(TAG, "PuC_reqCollertFXQuoData: "+"="+syBool_handler );
                if(!syBool_handler)
                {
                    syBool_handler=true;

                    handler.sendEmptyMessage(0);
                }else {
                    return;
                }

            }

        }
        public void reqCollectFXCalendarJin10Data(){
            synchronized (syStr_handler){
                //Log.w(TAG, "PuC_reqCollertFXQuoData: "+"="+syBool_handler );
                if(!syBool_handler)
                {
                    syBool_handler=true;

                    handler.sendEmptyMessage(2);
                }else {
                    return;
                }

            }

        }
        public void reqCollectFXCalendarJin10Data(TimeOD[] timeOD_A){
            synchronized (syStr_handler){
                //Log.w(TAG, "PuC_reqCollertFXQuoData: "+"="+syBool_handler );
                if(!syBool_handler)
                {
                    syBool_handler=true;
                    Message message_A=new Message();
                    message_A.what=3;
                    message_A.obj=timeOD_A;
                    handler.sendMessage(message_A);
                }else {
                    return;
                }

            }

        }
//        public void collectFXCalendarFirstDHData(){
//            XMCalendarCollertTool.CalendarDH[] calendarDHS_A = xmCalendarCollertTool.startCollertXMCalenderDH();
//            //Log.w(this.toString(), "collectFXCalendarFirstDHData: E0"+"="+(calendarDHS_A!=null) );
//            if(calendarDHS_A!=null)
//                {
//                    //Log.w(this.toString(), "collectFXCalendarFirstDHData: E1"+"="+(calendarDHS_A.length) );
//                    calendarConnect.setNewsData(calendarDHS_A,new TimeOD(System.currentTimeMillis()));
//                }
//
//
//        }
//        public void collectFXCalendarFirstDHData(long long_A){
//            TimeOD timeOD_A=new TimeOD(long_A);
//            XMCalendarCollertTool.CalendarDH[] calendarDHS_A = xmCalendarCollertTool.startCollertXMCalenderDH(timeOD_A);
//            //Log.w(this.toString(), "collectFXCalendarFirstDHData: E0"+"="+(calendarDHS_A!=null) );
//            if(calendarDHS_A!=null)
//            {
//                //Log.w(this.toString(), "collectFXCalendarFirstDHData: E1"+"="+(calendarDHS_A.length) );
//                calendarConnect.setData(calendarDHS_A,timeOD_A);
//            }
//
//
//        }
        public void collectFXCalendarJin10(){
            try {
                TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
                Jin10CalendarCollectTool.CalendarJinDH[] calendarJinDHS_A=jin10CalendarCollectTool.getEconomicCalendar(timeOD_A);
                Jin10CalendarCollectTool.CalendarJinEV[] calendarJinEVS_A=jin10CalendarCollectTool.getEconomicCalendarEV(timeOD_A);
                if(calendarJinDHS_A!=null)
                    {
                        calendarConnect.setData(calendarJinDHS_A,calendarJinEVS_A,timeOD_A);
                    }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void collectFXCalendarJin10(TimeOD[] timeODS_A){
            try {
                for(int i=0;i<timeODS_A.length;i++)
                    {
                    TimeOD timeOD_A=timeODS_A[i];
                        Jin10CalendarCollectTool.CalendarJinDH[] calendarJinDHS_A=jin10CalendarCollectTool.getEconomicCalendar(timeOD_A);
                        Jin10CalendarCollectTool.CalendarJinEV[] calendarJinEVS_A=jin10CalendarCollectTool.getEconomicCalendarEV(timeOD_A);
                        if(calendarJinDHS_A!=null)
                        {
                            calendarConnect.setData(calendarJinDHS_A,calendarJinEVS_A,timeOD_A);
                        }
                    }
                //TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        public XMCalendarCollertTool.CalendarDH[] getCollectFXCalendarFirstDHData(long long_A){
//            TimeOD timeOD_A=new TimeOD(long_A);
//            XMCalendarCollertTool.CalendarDH[] calendarDHS_A = xmCalendarCollertTool.startCollertXMCalenderDH(timeOD_A);
//            return calendarDHS_A;
//
//
//        }
        /**
         *
         */
//        public void collectFXCalendarCCData(){
//            XMCalendarCollertTool.CalendarCC[] calendarCCS_A = xmCalendarCollertTool.startCollertXMCalenderCC();
//            if(calendarCCS_A!=null)
//                {
//                    calendarConnect.updateData(calendarCCS_A,new TimeOD(System.currentTimeMillis()));
//                }
//
//        }

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
