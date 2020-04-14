package com.example.app_005.Service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;


import com.example.app_005.Class.SkillNewsName;
import com.example.app_005.Class.SkillQuoName;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.MyFileIOTool;
import com.example.app_005.Tool.TimeOD;
import com.google.gson.Gson;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 外汇数据服务
 */
public class MyFXDataService extends Service {
    private Context contextA;
    private static final String stringSyA="QuotedData";
    private MyBinder binder = new MyBinder();
    //private boolean sy_booleanA;
    //private MyFXCollectService.ClC_FXQuotedData sy_clC_fxQuotedDataA;
    public static FXDataPool ST_fxDataPool;
    MyFileIOTool fileIOTool;


    public MyFXDataService() {
    }
    public class MyBinder extends Binder {
        public MyFXDataService getService(){
            return MyFXDataService.this;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        contextA=getApplicationContext();

        fileIOTool=new MyFileIOTool(contextA);
        //sy_booleanA=false;
        //sy_clC_fxQuotedDataA=new MyFXCollectService.ClC_FXQuotedData();
        //TimeOD timeOD_A=MyFXTimingService.ST_titleTime.timeODNew;
        //init_initCachaQuoData(timeOD_A);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    void init(){

    }

//
//    /**
//     * 初始化缓存数据
//     */
//    void init_initCachaQuoData(TimeOD timeODAA){
//        TimeOD timeOD_B=timeODAA.PuC_getCrTimeOD(-1);
////        ST_fxDataPool =new FXDataPool(this);
////        ST_fxDataPool.fxQuo=ST_fxDataPool.new FXQuo(fileIOTool);
////        PrC_initQuoPoolMainData(ST_fxDataPool.fxQuo.quoPool.mainDataA,timeODAA);
////        PrC_initQuoPoolMainData(ST_fxDataPool.fxQuo.quoPool.mainDataB,timeOD_B);
////        ST_fxDataPool.fxNews=ST_fxDataPool.new FXNews(fileIOTool);
////        PrC_initNewsPoolMainData(ST_fxDataPool.fxNews.fxNewsPool.newsMainDataA,timeODAA);
////        PrC_initNewsPoolMainData(ST_fxDataPool.fxNews.fxNewsPool.newsMainDataA,timeOD_B);
//    }
//    //操作数据线程
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0:
//                    HuC_UpdateFXDayCacheData();
//                    break;
//                case 1:
//                    HuC_SaveCollectData();
//                    break;
//                case 2:
//                    HuC_UpdateNewDate();
//                    break;
//            }
//
//        }
//    };
//    /**
//     *handleMessage更新外汇天数缓存数据连接
//     */
//    void HuC_UpdateFXDayCacheData(){
//
//    }
//    /**
//     *handleMessage存储收集到的外汇数据
//     */
//    void HuC_SaveCollectData(){
//       // Sy_SaveQuoData();
//
//
//    }
//    /**
//     *handleMessage更新最新的日期
//     */
//    void HuC_UpdateNewDate(){
//        synchronized (stringSyA) {
//
//
//        }
//    }
////--------------------------------------------------------------------------------------------------
//    /**
//     * 将外汇收集服务传来的收集报价数据存储赋值//OK_20190712
//     *
//     * @param longAA           the long aa
//     */
//    public void PuC_asiCollectFXQuotedData(FXDataPool.FXQuo.QuoData[] quoDatasAA, long longAA){
//        synchronized (stringSyA) {
////            TimeOD timeOD_A=new TimeOD(longAA);
////            PrC_checkUpdateQuoData();//调查缓存是否更新最新日期
////            if (ST_fxDataPool.fxQuo.quoPool.mainDataA.timeOD.PuC_isLongSameDay(longAA))//收集日期是否和缓存最新日期一样
////            {
////
////            } else if (ST_fxDataPool.fxQuo.quoPool.mainDataB.timeOD.PuC_isLongSameDay(longAA)) {
////                ST_fxDataPool.fxQuo.quoPool.mainDataB.PuC_addCollectData(quoDatasAA,timeOD_A);
////
////            } else {
////
////                MyFXDataService.ST_fxDataPool.fxQuo.fxQuoCacha.PuC_addCollectQuoData(quoDatasAA,timeOD_A);
////
////                for(int i=0;i<quoDatasAA.length;i++)
////                {
////                    ST_fxDataPool.fxQuo.fxQuoFileTool.PuC_saveOneQuoDataToFile(quoDatasAA[i],SkillQuoName.getSkill(i), timeOD_A);
////                }
////
////
////            }
//
//        }
//    }
//    /**
//     * 将外汇收集服务传来的收集报价数据存储赋值//OK_20190712
//     *
//     * @param longAA           the long aa
//     */
//    public void PuC_asiCollectFXQuoLine(FXDataPool.FXQuo.QuoData[] quoDatasAA, long longAA){
//        synchronized (stringSyA) {
//            PrC_checkUpdateQuoData();//调查缓存是否更新最新日期
//            if (ST_fxDataPool.fxQuo.quoPool.mainDataA.timeOD.PuC_isLongSameDay(longAA))//收集日期是否和缓存最新日期一样
//            {
//              //  PrC_saveQuoDataToCacheQuo(0,quoDatasAA, longAA);
//            } else if (ST_fxDataPool.fxQuo.quoPool.mainDataB.timeOD.PuC_isLongSameDay(longAA)) {
//                //PrC_saveQuoDataToCacheQuo(1,quoDatasAA, longAA);
//
//            } else {
//
//
//
//            }
//
//        }
//    }
//    /**
//     * 将外汇收集服务传来的收集报价数据存储赋值//OK_20190712
//     *
//     * @param longAA           the long aa
//     */
//    public void PuC_asiCollectFXNewsData(FXDataPool.FXQuo.QuoData[] quoDatasAA, long longAA){
//        synchronized (stringSyA) {
//            PrC_checkUpdateQuoData();//调查缓存是否更新最新日期
//            if (ST_fxDataPool.fxQuo.quoPool.mainDataA.timeOD.PuC_isLongSameDay(longAA))//收集日期是否和缓存最新日期一样
//            {
//
//            } else if (ST_fxDataPool.fxQuo.quoPool.mainDataB.timeOD.PuC_isLongSameDay(longAA)) {
//
//            } else {
//
//
//
//            }
//
//        }
//    }
////    /**
////     *获取对应日期的报价数据//OK_20190711
////     */
////    public FXDataPool.FXQuo.QuoDayData PuC_getQuoDayData(RequestDataTool.QuoRequest quoRequestAA){
////        if(quoRequestAA!=null)
////            {
////                for(int i=0;i<quoRequestAA.fxDataListA.size();i++)
////                    {
////                        PrC_crQuoRequest(quoRequestAA.fxDataListA.get(i));
////                    }
////
////            }
////
////
////    }
//
//    /**
//     * Pu c get quo d day data boolean.
//     *获取报价数据
//     */
//    public boolean PuC_getQuoDDayData(FXDataPool.FXQuo.QuoDayData quoDayDataAA){
//        FXDataPool.FXQuo.QuoDayData quoDayData_A=null;
//        quoDayData_A=null;//quoDataTool.PuC_getQuoDDayData(quoDayDataAA.skillName,quoDayDataAA.timeOD);
//        if(quoDayData_A!=null)
//            {
//                quoDayDataAA=quoDayData_A;
//                return true;
//            }else {
//            return false;
//        }
//
//
//    }
//    public  boolean PuC_getQuoDDayLine( FXDataPool.FXQuo.QuoDayLine quoDayLineAA){
//        FXDataPool.FXQuo.QuoDayLine quoDayLine_A=null;
//        quoDayLine_A=null;//quoDataTool.PuC_getQuoDDayLine(quoDayLineAA.skillName,quoDayLineAA.timeOD);
//        if(quoDayLine_A!=null)
//        {
//            quoDayLineAA=quoDayLine_A;
//            return true;
//        }else {
//            return false;
//        }
//    }
////--------------------------------------------------------------------------------------------------
//    /**
//     *
//     */
//    private void PrC_getQuoChaPoolData(SkillQuoName skillNameAA, TimeOD timeODAA){
//    if(timeODAA.PuC_isTimeODSameDay(ST_fxDataPool.fxQuo.quoPool.mainDataA.timeOD))
//        {
//
//        }else if(timeODAA.PuC_isTimeODSameDay(ST_fxDataPool.fxQuo.quoPool.mainDataB.timeOD))
//            {
//
//            }else {
//
//
//    }
//    }
//    /**
//     *初始化赋值缓存报价文件数据//OK_20190909
//     */
//    private void PrC_initQuoPoolMainData(FXDataPool.FXQuo.QuoMainData mainDataAA,TimeOD timeODAA){
//        if(mainDataAA!=null)
//            {
////                mainDataAA= ST_fxDataPool.fxQuo.new QuoMainData(timeODAA);
////                String string_A;
////                File file_A;
////                String string_B;
////                Gson gson_A=new Gson();
////                for(int i = 0; i<mainDataAA.quoDayData.length; i++)
////                    {
////                        string_A=fileIOTool.getJSONQuoFileName(mainDataAA.quoDayData[i].skillName,mainDataAA.quoDayData[i].timeOD);
////                        file_A=fileIOTool.PuC_isJSONFile(string_A);
////                        if(file_A!=null) {
////                            string_B = fileIOTool.getJSONFileData(file_A);
////                            mainDataAA.quoDayData[i] = gson_A.fromJson(string_B, FXDataPool.FXQuo.QuoDayData.class);
////                            mainDataAA.quoDayData[i].intState=1;
////                        }else {
////                            mainDataAA.arQuoDataData();
////                        }
////
////                    }
////                mainDataAA.intLoad=3;
//
//            }
//    }
//    /**
//     *初始化/chū shǐ huā/赋值缓存新闻/xīn wén/文件数据//OK_20190909
//     */
//    private void PrC_initNewsPoolMainData(FXDataPool.FXNews.NewsMainData newsMainDataAA,TimeOD timeODAA){
//        if(newsMainDataAA!=null)
//        {
//            newsMainDataAA= ST_fxDataPool.fxNews.new NewsMainData(timeODAA);
//            String string_A;
//            File file_A;
//            String string_B;
//            Gson gson_A=new Gson();
//            if(true)
//                {
//                    string_A=fileIOTool.getJSONNewsDDataFileName(SkillNewsName.DaiFX即时新闻,newsMainDataAA.timeOD);
//                    file_A=fileIOTool.PuC_isJSONFile(string_A);
//                    if(file_A!=null) {
//                        string_B = fileIOTool.getJSONFileData(file_A);
//                        FXDataPool.FXNews.NewsDData newsDData_A=gson_A.fromJson(string_B, FXDataPool.FXNews.NewsDData.class);
//                        newsMainDataAA.newsDData.PuC_setData(newsDData_A);
//                    }else {
//                        newsMainDataAA.newsDData.intState=1;
//                    }
//                    newsMainDataAA.intState=3;
//                }
//
//
//        }
//    }
//
////    /**
////     *根据请求数据举行处理方法//OK_20190712
////     */
////    private void PrC_crQuoRequest(FXDataPool.FXData fxDataAA){
////        switch(fxDataAA.intsA[0]){
////            case 0:
////                fxDataAA.dataT= ST_fxDataPool.new FXData<FXDataPool.FXQuo.QuoDData>();
////                fxDataAA.dataT=PrC_crQuoCurrencyRequest(fxDataAA.intsA[1],fxDataAA.timeODA);
////                break;
////            case 1:
////                break;
////            default:
////                break;
////        }
////
////    }
////    /**
////     *处理报价货币请求//OK_20190712
////     */
////    private FXDataPool.FXQuo.QuoDData PrC_crQuoCurrencyRequest(int intAA, TimeOD timeODAA){
////        FXDataPool.FXQuo.QuoDData dayData_A=null;
////        String string_A=fileIOTool.PuC_getJSONQuoFileName(intAA,timeODAA);
////        File file_A=fileIOTool.PuC_isJSONFile(string_A);
////        if(file_A!=null)
////            {
////                String string_B=fileIOTool.getJSONFileData(file_A);
////                Gson gson_A=new Gson();
////                dayData_A=gson_A.fromJson(string_B, FXDataPool.FXQuo.QuoDData.class);
////
////            }else {
////            dayData_A= ST_fxDataPool.fxQuo.new QuoDData(SkillQuoName.getSkill(intAA),timeODAA);
////        }
////
////        return dayData_A;
////    }
//    /**
//     *调查是否更新缓存报价数据最新日期checkUpdateQuoData//OK_20190711
//     */
//    private void PrC_checkUpdateQuoData(){
//        synchronized (stringSyA){
//            if(true)
//                {
////                    TimeOD timeOD_A0=new TimeOD(MyFXTimingService.ST_calendar.getTimeInMillis());
////                    if((!ST_fxDataPool.fxQuo.quoPool.mainDataA.timeOD.PuC_isTimeODSameDay(timeOD_A0)))//调查第一缓存日期是不是最新日期)
////                    {
////
////                        }
//
//                }
//            if(true)
//                {
////                    TimeOD timeOD_A0=new TimeOD(MyFXTimingService.ST_calendar.getTimeInMillis());
////                    if((!ST_fxDataPool.fxQuo.quoPool.mainDataA.timeODMain.PuC_isTimeODSameDay(timeOD_A0))//调查第一缓存日期是不是最新日期)
////                        {
////                            if(ST_fxDataPool.fxQuo.quoPool.mainDataB.intLoad !=3&& ST_fxDataPool.fxQuo.quoPool.mainDataB.intLoad !=2)//调查第二缓存是否存储
////                            {
////                                PrC_saveCacheQuoToFile(ST_fxDataPool.fxQuo.quoPool.mainDataB);//存储第二缓存报价数据
////                            }
////                            Calendar calendar_A=new GregorianCalendar();
////                            calendar_A.setTimeInMillis((MyFXTimingService.ST_calendar.getTimeInMillis()));
////                            calendar_A.set(Calendar.DATE,-1);
////                            TimeOD timeOD_A=new TimeOD(timeOD_A0.PuC_getCrTimeOD(-1));
////                            if(ST_fxDataPool.fxQuo.quoPool.mainDataA.timeODMain.PuC_isTimeODSameDay(timeOD_A))//调查第一缓存日期是不是最新日期前天
////                            {
////                                ST_fxDataPool.fxQuo.quoPool.mainDataB= ST_fxDataPool.fxQuo.quoPool.mainDataA;//将第一缓存赋值给第二缓存
////                                ST_fxDataPool.fxQuo.quoPool.mainDataB.intLoad =1;//等待存储
////                            } else {
////                                if(ST_fxDataPool.fxQuo.quoPool.mainDataA.intLoad !=3&& ST_fxDataPool.fxQuo.quoPool.mainDataA.intLoad !=2)//调查第一缓存是否存储
////                                {
////
////                                    PrC_saveCacheQuoToFile(ST_fxDataPool.fxQuo.quoPool.mainDataA);
////                                    //ST_fxDataPool.quoPool.mainDataA.intLoad =2;
////                                }
////                                ST_fxDataPool.fxQuo.quoPool.mainDataB = ST_fxDataPool.fxQuo.new QuoMainData(timeOD_A);//初始化第二缓存
////                                PrC_crFileDataCachaQuoData(ST_fxDataPool.fxQuo.quoPool.mainDataB);//赋值第二缓存报价文件数据
////                            }
////                            ST_fxDataPool.fxQuo.quoPool.mainDataA= ST_fxDataPool.fxQuo.new QuoMainData(new TimeOD(MyFXTimingService.ST_calendar.getTimeInMillis()));
////                            PrC_crFileDataCachaQuoData(ST_fxDataPool.fxQuo.quoPool.mainDataA);//赋值第一缓存报价文件数据
////                        }
//
//                }
//            if(false)
//                {
////                    if (!ST_fxDataPool.fxQuo.quoPool.mainDataA.timeODMain.PuC_isLongSameDay(MyFXTimingService.ST_calendar.getTimeInMillis()))//调查第一缓存日期是不是最新日期
////                    {
////                        //int int_A=PrC_caIsWantSaveCacheQuoData();//调查需要存储的缓存报价数据
////                        if(ST_fxDataPool.fxQuo.quoPool.mainDataB.intLoad !=3&& ST_fxDataPool.fxQuo.quoPool.mainDataB.intLoad !=2)//调查第二缓存是否存储
////                        {
////                            PrC_saveCacheQuoToFile(ST_fxDataPool.fxQuo.quoPool.mainDataB);//存储第二缓存报价数据
////                        }
////
////
////                        if(ST_fxDataPool.fxQuo.quoPool.mainDataA.timeODMain.PuC_isTimeODSameDay(timeOD_A))//调查第一缓存日期是不是最新日期前天
////                        {
////                            ST_fxDataPool.fxQuo.quoPool.mainDataB= ST_fxDataPool.fxQuo.quoPool.mainDataA;//将第一缓存赋值给第二缓存
////                            ST_fxDataPool.fxQuo.quoPool.mainDataB.intLoad =1;//等待存储
////                        }
////                        else {
////                            if(ST_fxDataPool.fxQuo.quoPool.mainDataA.intLoad !=3&& ST_fxDataPool.fxQuo.quoPool.mainDataA.intLoad !=2)//调查第一缓存是否存储
////                            {
////
////                                PrC_saveCacheQuoToFile(ST_fxDataPool.fxQuo.quoPool.mainDataA);
////                                //ST_fxDataPool.quoPool.mainDataA.intLoad =2;
////                            }
////                            ST_fxDataPool.fxQuo.quoPool.mainDataB = ST_fxDataPool.fxQuo.new QuoMainData(timeOD_A);//初始化第二缓存
////                            PrC_crFileDataCachaQuoData(ST_fxDataPool.fxQuo.quoPool.mainDataB);//赋值第二缓存报价文件数据
////                        }
////
////
////                        ST_fxDataPool.fxQuo.quoPool.mainDataA= ST_fxDataPool.fxQuo.new QuoMainData(new TimeOD(MyFXTimingService.ST_calendar.getTimeInMillis()));
////                        PrC_crFileDataCachaQuoData(ST_fxDataPool.fxQuo.quoPool.mainDataA);//赋值第一缓存报价文件数据
////
////
////                    }
//                }
//
//        }
//
//    }
//    /**
//     * 时间表
//     */
//    public static class TimeOD{
////        public int[] intsA;
////        public TimeOD(){
////
////        }
////        public TimeOD(int[] intsAA ){
////            intsA=intsAA;
////
////        }
////        public TimeOD(TimeOD timeODAA){
////            intsA=timeODAA.intsA;
////        }
////        public TimeOD(long longAA){
////            intsA=PrC_longToTimeOD(longAA);
////
////        }
////
////        /**
////         * 将long转换成TimeOD
////         *
////         * @return the int [ ]
////         */
////        int[] PrC_longToTimeOD(long longAA){
////            int[] ints_A=new int[5];
////            Calendar calendar_A=new GregorianCalendar();
////            calendar_A.setTimeInMillis(longAA);
////            ints_A[0]=calendar_A.get(Calendar.YEAR);
////            ints_A[1]=calendar_A.get(Calendar.MONTH);
////            ints_A[2]=calendar_A.get(Calendar.DATE);
////            ints_A[3]=calendar_A.get(Calendar.HOUR_OF_DAY);
////            ints_A[4]=calendar_A.get(Calendar.MINUTE);
////            return ints_A;
////        }
////
////        /**
////         * 将TimeOD转换成long
////         *
////         * @return the long
////         */
////        public long PuC_getTimeLong(){
////            Calendar calendar_A=new GregorianCalendar();
////            calendar_A.set(Calendar.YEAR,intsA[0]);
////            calendar_A.set(Calendar.MONTH,intsA[1]);
////            calendar_A.set(Calendar.DATE,intsA[2]);
////            calendar_A.set(Calendar.HOUR_OF_DAY,intsA[3]);
////            calendar_A.set(Calendar.MINUTE,intsA[4]);
////            return calendar_A.getTimeInMillis();
////        }
////
////        /**
////         * 调查Long是否一样日期
////         *
////         * @param longAA the long aa
////         * @return the boolean
////         */
////        public boolean PuC_isLongSameMINUTE(long longAA){
////            int[] ints_A=new int[5];
////            Calendar calendar_A=new GregorianCalendar();
////            calendar_A.setTimeInMillis(longAA);
////            ints_A[4]=calendar_A.get(Calendar.MINUTE);if(ints_A[4]!=intsA[4]){return false;}
////            ints_A[3]=calendar_A.get(Calendar.HOUR_OF_DAY);if(ints_A[3]!=intsA[3]){return false;}
////            ints_A[2]=calendar_A.get(Calendar.DATE);if(ints_A[2]!=intsA[2]){return false;}
////            ints_A[1]=calendar_A.get(Calendar.MONTH);if(ints_A[1]!=intsA[1]){return false;}
////            ints_A[0]=calendar_A.get(Calendar.YEAR);if(ints_A[0]!=intsA[0]){return false;}
////            return true;
////        }
////        /**
////         * 调查Long是否一样日期
////         *
////         * @param longAA the long aa
////         * @return the boolean
////         */
////        public boolean PuC_isLongSameDay(long longAA){
////            int[] ints_A=new int[4];
////            Calendar calendar_A=new GregorianCalendar();
////            calendar_A.setTimeInMillis(longAA);
////            //ints_A[3]=calendar_A.get(Calendar.HOUR_OF_DAY);if(ints_A[3]!=intsA[3]){return false;}
////            ints_A[2]=calendar_A.get(Calendar.DATE);if(ints_A[2]!=intsA[2]){return false;}
////            ints_A[1]=calendar_A.get(Calendar.MONTH);if(ints_A[1]!=intsA[1]){return false;}
////            ints_A[0]=calendar_A.get(Calendar.YEAR);if(ints_A[0]!=intsA[0]){return false;}
////            return true;
////        }
////        public boolean PuC_isLongSameDay(long longAA,int intAA){
////            int[] ints_A=new int[4];
////            Calendar calendar_A=new GregorianCalendar();
////            calendar_A.setTimeInMillis(longAA);
////            calendar_A.set(Calendar.DATE,intAA);
////            //ints_A[3]=calendar_A.get(Calendar.HOUR_OF_DAY);if(ints_A[3]!=intsA[3]){return false;}
////            ints_A[2]=calendar_A.get(Calendar.DATE);if(ints_A[2]!=intsA[2]){return false;}
////            ints_A[1]=calendar_A.get(Calendar.MONTH);if(ints_A[1]!=intsA[1]){return false;}
////            ints_A[0]=calendar_A.get(Calendar.YEAR);if(ints_A[0]!=intsA[0]){return false;}
////            return true;
////        }
////        /**
////         * 调查TimeOD是否一样日期
////         *
////         * @return the boolean
////         */
////        public boolean PuC_isTimeODSameMINUTE(TimeOD timeODAA){
////            if(intsA[4]!=timeODAA.intsA[4])return false;
////            if(intsA[3]!=timeODAA.intsA[3])return false;
////            if(intsA[2]!=timeODAA.intsA[2])return false;
////            if(intsA[1]!=timeODAA.intsA[1])return false;
////            if(intsA[0]!=timeODAA.intsA[0])return false;
////            return true;
////        }
////        /**
////         * 调查TimeOD是否一样日期
////         *
////         * @return the boolean
////         */
////        public boolean PuC_isTimeODSameDay(TimeOD timeODAA){
////            //if(intsA[3]!=timeODAA.intsA[3])return false;
////            if(intsA[2]!=timeODAA.intsA[2])return false;
////            if(intsA[1]!=timeODAA.intsA[1])return false;
////            if(intsA[0]!=timeODAA.intsA[0])return false;
////            return true;
////        }
////
////        /**
////         * 计算TimeOD添加参数后得到的日期
////         *
////         * @param intAA the int aa
////         * @return the time od
////         */
////        public TimeOD PuC_getCrTimeOD(int intAA){
////            Calendar calendar=new GregorianCalendar();
////            calendar.setTimeInMillis(this.PuC_getTimeLong());
////            calendar.set(Calendar.DATE,intsA[2]+intAA);
////            return new TimeOD(calendar.getTimeInMillis());
////        }
////        public TimeOD PuC_getCrTimeODMinute(int intAA){
////            Calendar calendar=new GregorianCalendar();
////            calendar.setTimeInMillis(this.PuC_getTimeLong());
////            calendar.add(Calendar.MINUTE,intAA);
////            return new TimeOD(calendar.getTimeInMillis());
////        }
////        /**
////         *对比的日期大小[0=一样,1=大过this,2=小过this]
////         */
////        public int PuC_calTimeContrast(TimeOD timeODAA){
////            int int_A=0;
////            for(int i=0;i<this.intsA.length;i++)
////            {
////                if((int_A=PrC_Contrast(this.intsA[i],timeODAA.intsA[i]))!=0)return int_A;
////            }
////            return 0;
////
////        }
////        /**
////         *增加分钟
////         */
////        public void PuC_addMinute(int intAA){
////            Calendar calendar=new GregorianCalendar();
////            calendar.setTimeInMillis(this.PuC_getTimeLong());
////            calendar.add(Calendar.MINUTE,intAA);
////            this.intsA=new TimeOD(calendar.getTimeInMillis()).intsA;
////
////
////        }
////
////        /**
////         *对比大小
////         */
////        private int PrC_Contrast(int intAA,int intBB) {
////            if (intAA == intBB) {
////                return 0;
////            } else if (intAA < intBB) {
////                return 1;
////            } else {
////                return 2;
////            }
////
////        }
//
//    }
//    //**********************************************************************************************
//
//
//    /**
//     *
//     */
////    public void ClC_000(){
////        TimeOD timeOD_A=new TimeOD(Calendar.getInstance().getTimeInMillis());
////        Log.d(TAG, "ClC_000_0: ");
////        File file_A=fileIOTool.PuC_isXMLFile(1,timeOD_A);
////        Log.d(TAG, "ClC_000_0B: "+"="+file_A.getPath());
////        fileIOTool.PuC_createFXQuoXMLFile(timeOD_A);
////        if(true)
////            {
////                fileIOTool.PuC_createFXQuoXMLFile(timeOD_A,new Boolean(true));
////            }
////        if(file_A!=null)
////        {
////            Log.d(TAG, "ClC_000_0C: ");
////            Document document_A=fileIOTool.PuC_getDOMModeFileXML(file_A);
////            Log.d(TAG, "ClC_000_0C.1: "+"="+(document_A!=null));
////            Element element_A=document_A.getDocumentElement();
////            Node node_A=element_A.getLastChild().getChildNodes().item(0);
////            Log.d(TAG, "ClC_000_1A: "+"="+element_A.getTagName());
////            Log.d(TAG, "ClC_000_1B: "+"="+node_A.getNodeName()+"="+node_A.getChildNodes().getLength() );
////            Element element_B=(Element) node_A;
////            Element element_C=document_A.createElement("M_0");//.appendChild(document_A.createTextNode("a"));
////            Element element_D=document_A.createElement("a");
////            element_D.appendChild(document_A.createTextNode("1"));//USB/EUR
////            element_C.appendChild(element_D);
////            element_D=document_A.createElement("b");
////            element_D.appendChild(document_A.createTextNode("1.23144"));
////            element_C.appendChild(element_D);
////           // element_C.appendChild(document_A.createElement("c").appendChild(document_A.createTextNode("1.3232343")));
////            element_B.appendChild(element_C);
////            //element_B.appendChild(document_A.createElement("M1").appendChild(document_A.createElement("a").appendChild(document_A.createTextNode("1.3232425"))));
////            //element_B.appendChild(document_A.createElement("M2").appendChild(document_A.createElement("a").appendChild(document_A.createTextNode("1.3232425"))));
////           // element_B.appendChild(document_A.createElement("M3").appendChild(document_A.createElement("a").appendChild(document_A.createTextNode("1.3232425"))));
////            if(fileIOTool.PuC_sevaFXQuoXMLToFile(document_A,file_A,timeOD_A))
////                {
////                    Log.d(TAG, "ClC_000_0C:");
////                }
////            FXDataPool.FXQuo fxQuo=new FXDataPool().new FXQuo();
////            FXDataPool.FXQuo.QuoDData dayData_A=fxQuo.new QuoDData(SkillQuoName.getSkill(1),timeOD_A);
////            document_A=fileIOTool.PuC_getDOMModeFileXML(file_A);
////            NodeList nodeList_A=document_A.getDocumentElement().getLastChild().getChildNodes();
////            for(int i=0;i<nodeList_A.getLength();i++)
////                {
////                NodeList nodeList_B=nodeList_A.item(i).getChildNodes();
////                    Log.d(TAG, "ClC_000_0F_0:"+"="+i +"="+nodeList_B.getLength());
////                    for(int e=0;e<nodeList_B.getLength();e++)
////                        {
////                            String string_A=nodeList_B.item(e).getNodeName();
////                            NodeList nodeList_C=nodeList_B.item(e).getChildNodes();
////                            Log.d(TAG, "ClC_000_0F_1:"+"="+e +"="+string_A);
////                            int int_A=Integer.valueOf(string_A.split("_")[1]);
////                            Log.d(TAG, "ClC_000_0F_2:"+"="+(string_A.split("_")[1]) +"="+int_A);
////                            if(nodeList_C.getLength()!=0)
////                                {
////                                    for(int r=1;r<nodeList_C.getLength();r++)
////                                    {
////
////                                        dayData_A.hourData[i].dataMin[int_A].floatsA[r-1]=Float.valueOf(nodeList_B.item(e).getNodeValue());
////                                    }
////                                }
////
////
////
////                        }
////                }
////            for(int i=0;i<dayData_A.hourData.length;i++)
////                {
////                for(int e=0;e<0;e++)
////                    {
////
////                    }
////                }
////        }else {
////            if(fileIOTool.PuC_createFXQuoXMLFile(timeOD_A)){
////                Log.d(TAG, "ClC_000_0A:");
////
////
////
////
////            }
////        }
////
////
////    }
//
//    /**
//     * 测试使存储报价数据
//     */
////    public void ClC_001(){
////        TimeOD timeOD_A=new TimeOD(Calendar.getInstance().getTimeInMillis());
////        Gson gson_A=new Gson();//new GsonBuilder() .serializeNulls() .create(); //生成配置好的Gson
////        FXDataPool.FXQuo.QuoDData dayData_A=new FXDataPool().new FXQuo().new QuoDData();
////        dayData_A.hourData[0]=new FXDataPool().new FXQuo().new QuoDData().new QuoHourData();
////        String string_A=gson_A.toJson(dayData_A);
////        Log.d(TAG, "ClC_001_A0: "+string_A);
////        String string_B=fileIOTool.PuC_getJSONQuoFileName(1,timeOD_A);
////        Log.d(TAG, "ClC_001_A1: "+string_B);
////        Boolean boolea_A=fileIOTool.PuC_sevaFXQuoJSONToFile(string_A,new File(string_B));
////        Log.d(TAG, "ClC_001_A2: "+boolea_A);
////        string_B=fileIOTool.PuC_getJSONQuoFileName(1,timeOD_A);
////        String string_C=fileIOTool.getJSONFileData(new File(string_B));
////        Log.d(TAG, "ClC_001_B0: "+string_C);
////        Gson gson_B=new GsonBuilder() .serializeNulls() .create(); //生成配置好的Gson
////        FXDataPool.FXQuo.QuoDData dayData_B =gson_B.fromJson(string_C,FXDataPool.FXQuo.QuoDData.class);
////        Gson gson_C=new Gson();
////        String string_D=gson_C.toJson(dayData_B);
////        Log.d(TAG, "ClC_001_B1: "+string_D);
////        Gson gson_D=new Gson();
////        String string_E=gson_C.toJson(dayData_B);
////        Log.d(TAG, "ClC_001_B2: "+string_D);
////    }
//
//    /**
//     * 将当前缓存报价数据存储到文件存档//OK_20190711
//     */
//    private void PrC_saveCacheQuoToFile(FXDataPool.FXQuo.QuoMainData mainDataAA){
////        synchronized (stringSyA){
////            for(int i=0;i<mainDataAA.quoDayData.length;i++)
////                {
////                    String string_A=fileIOTool.PuC_getJSONQuoFileName(mainDataAA.quoDayData[i].skillName,mainDataAA.quoDayData[i].timeOD);
////                    File file_A=fileIOTool.PuC_isJSONFile(string_A);
////                    if(file_A!=null)
////                    {
////                        String string_B=fileIOTool.getJSONFileData(file_A);
////                        Gson gson_A=new Gson();
////                        FXDataPool.FXQuo.QuoDayData dayData_A=gson_A.fromJson(string_B, FXDataPool.FXQuo.QuoDayData.class);
////                        PrC_setTheQuoDayData(dayData_A,mainDataAA.quoDayData[i]);
////                        string_B=gson_A.toJson(dayData_A);
////                        fileIOTool.PuC_sevaFXQuoJSONToFile(string_B,file_A);
////                    }else {
////                        Gson gson_A=new Gson();
////                        String string_B=gson_A.toJson(mainDataAA.quoDayData[i]);
////                        fileIOTool.PuC_sevaFXQuoJSONToFile(string_B,file_A);
////                    }
////                }
////        }
//
//    }
//    /**
//     *赋值货币报价数据//OK_20190712
//     */
//    private void PrC_setTheQuoDayData(FXDataPool.FXQuo.QuoDayData quoDayDataAA, FXDataPool.FXQuo.QuoDayData quoDayDataBB){
//        if(quoDayDataAA.flSta!=0)quoDayDataAA.flSta=quoDayDataBB.flSta;
//        if(quoDayDataAA.flMax!=0)quoDayDataAA.flMax=quoDayDataBB.flMax;
//        if(quoDayDataAA.flMin!=0)quoDayDataAA.flMin=quoDayDataBB.flMin;
//        for(int i=0;i<quoDayDataBB.hourData.length;i++)
//            {
//                if(quoDayDataBB.hourData[i]!=null)
//                    {
//                    for(int e=0;e<quoDayDataBB.hourData[i].dataMin.length;e++)
//                        {
//                            if(quoDayDataBB.hourData[i].dataMin[i]!=null)
//                            {
//                                quoDayDataAA.hourData[i].dataMin[i]=quoDayDataBB.hourData[i].dataMin[i];
//
//                            }
//                        }
//                    }
//
//            }
//    }
//
//
//    /**
//     *将报价数据存储到缓存报价数据//OK_20190711
//     */
//    private void PrC_saveQuoDataToCacheQuo(int intAA, FXDataPool.FXQuo.QuoData[] quoDatasAA, TimeOD timeODAA){
////        synchronized (stringSyA){
////            FXDataPool.FXQuo.QuoMainData mainData_A;
////            if(intAA==0)//判断是存储那个缓存报价数据
////                {
////                    mainData_A= ST_fxDataPool.fxQuo.quoPool.mainDataA;
////                }else {
////                mainData_A= ST_fxDataPool.fxQuo.quoPool.mainDataB;
////            }
////            TimeOD timeOD=new TimeOD(longAA);
////            for(int i=0;i<quoDatasAA.length;i++)
////                {
////                    mainData_A.quoDayData[i].PuC_setQuoData(quoDatasAA[i].floatsA,,timeOD.intsA[3],timeOD.intsA[4]);
////                    //PuC_giveQuaCollectData(mainData_A.quoDayData[i],quoDatasAA[i],timeOD.intsA[3],timeOD.intsA[4]);
////                }
////
////        }
//
//    }
//    /**
//     *返回货币数据号//OK_20190711
//     */
//    private int PrC_getQuoNumber(String stringAA){
//
//        switch(stringAA){
//            case "EUR/USD":
//               return 0;
//            case "USD/JPY":
//                return 1;
//            case "GBP/USD":
//                return 2;
//            case "EUR/GBP":
//                return 3;
//            case "USD/CHF":
//                return 4;
//            case "EUR/JPY":
//                return 5;
//            case "EUR/CHF":
//                return 6;
//            case "USD/CAD":
//                return 7;
//            case "AUD/USD":
//                return 8;
//            case "GBP/JPY":
//                return 9;
//            default:
//                return -1;
//        }
//
//    }
//    /**
//     *将收集贷币分钟数据赋值给对应的缓存数据//OK_20190711
//     */
//    public void PuC_giveQuaCollectData(FXDataPool.FXQuo.QuoDayData dayDataAA,float[] floatsAA, int intAA, int intBB){
//
//        float[] floats_A=new float[3];
//        floats_A[1]=Float.valueOf(floatsAA[2]);//卖
//        floats_A[2]=Float.valueOf(floatsAA[3]);//买
//        floats_A[0]=(( floats_A[2]-floats_A[1])/2F);//中
//        floats_A[0]+=floats_A[1];//
//        if(dayDataAA.hourData[intAA]==null)dayDataAA.hourData[intAA]=ST_fxDataPool.fxQuo.new QuoHourData();
//        dayDataAA.hourData[intAA].dataMin[intBB]=ST_fxDataPool.fxQuo.new QuoData(floats_A);
//        dayDataAA.flMin=Float.valueOf(floatsAA[4]);//最小
//        dayDataAA.flMax=Float.valueOf(floatsAA[5]);//最大
//        dayDataAA.flSta=Float.valueOf(floatsAA[6]);//开始
//    }
////    /**
////     *将报价数据存储到文件存档
////     */
////    private void PrC_saveQuoDataToFile(FXDataPool.FXQuo.QuoData[] quoDataAA, long longAA){
////        TimeOD timeOD_A=new TimeOD(longAA);
////        for(int i=0;i<quoDataAA.length;i++)
////            {
////            int intAA=PrC_getQuoNumber(fxQuotedData01AA.stringssA[i][0]);//返回货币数据号
////                String string_A=fileIOTool.PuC_getJSONQuoFileName(intAA,timeOD_A);
////                File file_A=fileIOTool.PuC_isJSONFile(string_A);
////                if(file_A!=null)
////                    {
////                        String string_B=fileIOTool.getJSONFileData(file_A);
////                        Gson gson_A=new Gson();
////                        FXDataPool.FXQuo.QuoDayData dayData_A=gson_A.fromJson(string_B, FXDataPool.FXQuo.QuoDayData.class);
////
////                        PuC_giveQuaCollectData(dayData_A, ST_fxDataPool.fxQuo,fxQuotedData01AA.stringssA[i],timeOD_A.intsA[3],timeOD_A.intsA[4]);
////                        string_B=gson_A.toJson(dayData_A);
////                        fileIOTool.PuC_sevaFXQuoJSONToFile(string_B,file_A);
////                    }else {
////                    FXDataPool.FXQuo.QuoDayData dayData_A= ST_fxDataPool.fxQuo.new QuoDayData(SkillQuoName.getSkill(intAA),timeOD_A);
////                    PuC_giveQuaCollectData(dayData_A, ST_fxDataPool.fxQuo,fxQuotedData01AA.stringssA[i],timeOD_A.intsA[3],timeOD_A.intsA[4]);
////                    Gson gson_A=new Gson();
////                   String string_B=gson_A.toJson(dayData_A);
////                    file_A=new File(string_A);
////                    fileIOTool.PuC_sevaFXQuoJSONToFile(string_B,file_A);
////                }
////            }
////
////
////    }
////    /**
////     *将数据保存到DOM//??_20190712
////     */
////    private void PrC_sevaDataToDOM(Document document,MyFXCollectService.ClC_FXQuotedData01 fxQuotedData01AA){
////        TimeOD timeOD=new TimeOD(fxQuotedData01AA.longA);
////        Element element=document.getDocumentElement();
////        Log.d(TAG, "Clc_001_2: "+element.getTagName());
////        Log.d(TAG, "Clc_001_3: "+element.getFirstChild().getNodeName());
////        Log.d(TAG, "Clc_001_4: "+element.getLastChild().getNodeName());
////        NodeList nodeList_A=element.getLastChild().getChildNodes();
////        Log.d(TAG, "Clc_001_5: "+nodeList_A.getLength());
////        Node node=nodeList_A.item(timeOD.intsA[3]);
////        nodeList_A=node.getChildNodes();
////        boolean boolean_A=true;
////        if(nodeList_A.getLength()==0)
////            {
////
////            }
////        Element element1_B=document.createElement("M30");
////        Element element1_C=document.createElement("M30");
////        element1_B.appendChild(element1_C);
////        node.appendChild(element1_B);
////
////    }
//
//    /**
//     *获取本地XML文件报价数据??_20190712
//     */
//    public FXDataPool.FXQuo.QuoDData PuC_getQuoXMLFileData(TimeOD timeODAA, FXDataPool.FXQuo fxQuo){
////        FXDataPool.FXQuo.QuoDData quoData_A=fxQuo.new QuoDData();
////        File file_A=fileIOTool.PuC_isXMLFile(1,timeODAA);
////        if(file_A.exists())
////        {
////
////            Document document_A=fileIOTool.PuC_getDOMModeFileXML(file_A);
////
////        }
////        return  quoData_A;
//        return null;
//    }
//
//
////
////
////
////    /**
////     * 赋值报价数据存储
////     * @param fxQuotedData01AA
////     * @param longAA
////     */
////    private void Sy_setQuoData(MyFXCollectService.ClC_FXQuotedData01 fxQuotedData01AA,long longAA){
////        synchronized (stringSyA){
////
////            //sy_clC_fxQuotedDataA.fxQuotedData01sA.add(fxQuotedData01AA);
////            //sy_booleanA=true;
////        }
////    }
//public class RAMMemoryTool{
//    ActivityManager activityManager;
//        public RAMMemoryTool(Context context){
//            activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//            //最大分配内存
//            int memory = activityManager.getMemoryClass();
//            System.out.println("memory: "+memory);
//            //最大分配内存获取方法2
//            float maxMemory = (float) (Runtime.getRuntime().maxMemory() * 1.0/ (1024 * 1024));
//            //当前分配的总内存
//            float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0/ (1024 * 1024));
//            //剩余内存
//            float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0/ (1024 * 1024));
//            System.out.println("maxMemory: "+maxMemory);
//            System.out.println("totalMemory: "+totalMemory);
//            System.out.println("freeMemory: "+freeMemory);
//        }
//}
//
//
//

}
