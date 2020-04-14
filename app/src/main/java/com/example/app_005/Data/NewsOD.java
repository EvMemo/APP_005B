package com.example.app_005.Data;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.app_005.Class.NewsData;
import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Class.SkillNewsName;
import com.example.app_005.Interface.OnUpdateNewsData;
import com.example.app_005.Tool.FXDataConnect;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.TimeOD;
import com.example.app_005.View.NewsNewView;
import com.example.app_005.View.NewsRecycleView;
import com.example.app_005.View.NewsSystemCenter;

import java.util.ArrayList;

public class NewsOD {
    public FXDataPool.FXNews.NewsDData newsDataClassA,newsDataClassB;
    FXDataPool.FXNews.NewsDData newsDataClassC;
    FXDataConnect.NewsConnect newsConnect;
    HandlerThread handlerThreadData;
    Handler handlerData;
    Handler handlerUI;
    boolean boolHandleData;
    boolean boolHandleUI;
    boolean newsDataBool;
    public TimeOD timeODNow;
    public SkillNewsName skillNewsName;
    public NewsOD(FXDataConnect fxDataConnectAA, TimeOD timeOD_Now, OnUpdateNewsData onUpdateNewsData_A){
        newsConnect=fxDataConnectAA.getNewsConnect(onUpdateNewsData_A);
        timeODNow=timeOD_Now;
        handlerThreadData = new HandlerThread("NewsODData");
        handlerThreadData.start();
        handlerData = new Handler(handlerThreadData.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0://请求更新临时数据
                        synchronized ("NewsODBoolData") {
                            if (boolHandleData) {
                                boolHandleData = false;
                            } else {
                                return;
                            }
                            updateNewsData();
                        }
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        };
        handlerUI = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0://更换临时数据
                        synchronized ("NewsODBoolUI") {
                            if (boolHandleUI) {
                                boolHandleUI = false;
                            } else {
                                return;
                            }
                            changeNewsData();
                        }
                        break;
                    case 1://更新UI
                        break;
                    case 2://更改日期
                        break;
                    default:
                        break;
                }
            }
        };
        updateNewsData();
        changeNewsData();
    }
    void initData(){

    }
//    public NewsBreakingPool getNewsBreakingPool(){
//        return null;
//    }

    /**
     *修正QuoDData数据确定
     */
    public void setQuoDDataBool(Boolean bool_A){
        synchronized ("newsDataBool"){
            newsDataBool=bool_A;
        }
    }
    /**
     *QuoDData数据是否确定
     */
    public Boolean getQuoDDataBool(){
        synchronized ("newsDataBool"){
            return newsDataBool;
        }
    }
    public void reqUpdateNewsData(){
        if (!boolHandleData) {
            boolHandleData = true;
            handlerData.sendEmptyMessage(0);
        }
    }
    public void reqChangeNewsData(){
        if (!boolHandleUI) {
            boolHandleUI = true;
            handlerUI.sendEmptyMessage(0);
        }
    }

    public FXDataPool.FXNews.NewsDData getNewsData(SkillNewsName skillNewsName_A, TimeOD timeOD_A){
        if (newsDataClassA == null) {
            if (newsDataClassA.skillNewsName == skillNewsName_A && newsDataClassA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                return newsDataClassA;

            }
        } else if (newsDataClassB == null) {
            if (newsDataClassB.skillNewsName == skillNewsName_A && newsDataClassB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                return newsDataClassB;

            }
        }
        return null;

    }
    public void updateNewsData(){


            Boolean bool_A = true;
            if (newsDataClassA != null&&newsDataClassA.timeOD.PuC_isTimeODSameDay(timeODNow)) {
                bool_A = false;
            }
            if (bool_A && newsDataClassB != null &&  newsDataClassB.timeOD.PuC_isTimeODSameDay(timeODNow)) {
                bool_A = false;

            }
            if(bool_A)
            {
               newsDataClassC=newsConnect.getNewsData(timeODNow);
               if(newsDataClassC!=null)
                   {
                       reqChangeNewsData();
                   }

            }

    }
    public void changeNewsData(){
        if(newsDataClassC!=null)
            {
                FXDataPool.FXNews.NewsDData newsDataClass_D=null;
                if(newsDataClassA!=null)
                    {
                        newsDataClass_D=newsDataClassA;
                    }
                newsDataClassA=newsDataClassC;
                if(newsDataClass_D!=null)
                    {
                        newsDataClassB=newsDataClass_D;
                    }
                setQuoDDataBool(true);
            }

    }
//    public NewsBreakingPool ccNewsData(TimeOD timeOD_A) {
//        FXDataPool.FXNews.NewsDData newsDData_A=getNewsDData(timeOD_A);
//        return new NewsBreakingPool(newsDData_A,timeOD_A);
//
//
//    }

    public FXDataPool.FXNews.NewsDData getNewsDData(TimeOD timeOD_A) {
        if (newsDataClassA != null && newsDataClassA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {

            return newsDataClassA;

        }else if (newsDataClassB != null && newsDataClassB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
            return newsDataClassB;
        }else {
            setQuoDDataBool(false);
            reqUpdateNewsData();
        }
        return null;
    }
    public class NewsBreakingData{
        public NewsDataClass newsDataClass;
        public boolean boolMoreText;
        public String content;//内容
        public boolean isSeen;//看过
        public boolean isCollect;//收藏
        public NewsBreakingData(NewsDataClass newsDataClass_A){
            newsDataClass=newsDataClass_A;


        }
//        public NewsBreakingData( FXDataPool.FXNews.NewsData newsDataAA){
//            newsData=newsDataAA;
//        }

    }




}
