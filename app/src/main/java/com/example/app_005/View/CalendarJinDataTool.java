package com.example.app_005.View;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.example.app_005.Class.CalendarDataClass;
import com.example.app_005.Interface.OnUpdateCalendarData;
import com.example.app_005.Internet.Jin10CalendarCollectTool;
import com.example.app_005.Tool.FXDataConnect;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.TimeOD;

public class CalendarJinDataTool implements OnUpdateCalendarData {

    FXDataConnect.CalendarConnect calendarConnect;
    HandlerThread handlerThreadData;
    Handler handlerData;
    Handler handlerUI;
    boolean boolHandleData;
    boolean boolHandleUI;
    TimeOD timeODNow;
    public CalendarJinD calendarJinDMain;
    EconomicCalendarSystemCenter economicCalendarSystemCenter;
    Jin10CalendarCollectTool jin10CalendarCollectTool;
    Jin10CalendarSystemCenter jin10CalendarSystemCenter;
    public CalendarJinDataTool(FXDataConnect fxDataConnect_A){
        calendarConnect=fxDataConnect_A.getCalendarConnect(this);
        jin10CalendarCollectTool=new Jin10CalendarCollectTool();
        handlerThreadData = new HandlerThread("CalendarJinDataToolData");
        handlerThreadData.start();
        handlerData = new Handler(handlerThreadData.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0://请求更新临时数据
                        TimeOD timeOD_A=null;
                        synchronized ("timeODNow"){
                            timeOD_A= timeODNow;
                            timeODNow=null;
                        }

                        if(timeOD_A!=null)
                            {
                                //collectCalendarJinData(timeOD_A);
                                getCalendarJinData(timeOD_A);
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
                        updateViewCalendarData();
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
    }
    public void setEconomicCalendarSystemCenter(EconomicCalendarSystemCenter economicCalendarSystemCenter_A){
        economicCalendarSystemCenter=economicCalendarSystemCenter_A;
    }
    public void setJin10CalendarSystemCenter(Jin10CalendarSystemCenter jin10CalendarSystemCenter_A){
        jin10CalendarSystemCenter=jin10CalendarSystemCenter_A;
    }
    void collectCalendarJinData(TimeOD timeOD_A){
        CalendarJinD calendarJinD_A=null;
        try {

            Jin10CalendarCollectTool.CalendarJinDH[] calendarJinDHS_A=jin10CalendarCollectTool.getEconomicCalendar(timeOD_A);
            Jin10CalendarCollectTool.CalendarJinEV[] calendarJinEV_A=jin10CalendarCollectTool.getEconomicCalendarEV(timeOD_A);
            calendarJinD_A=new CalendarJinD(timeOD_A,calendarJinDHS_A,calendarJinEV_A);
            setCalendarJinDMain(calendarJinD_A);
            handlerUI.sendEmptyMessage(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void getCalendarJinData(TimeOD timeOD_A){
        if(true)
            {
                FXDataPool.FXCalendar.CalenderDData calenderDData_A=calendarConnect.getDData(timeOD_A);
                CalendarJinD calendarJinD_A=new CalendarJinD(timeOD_A,calenderDData_A);
                setCalendarJinDMain(calendarJinD_A);
                handlerUI.sendEmptyMessage(0);
            }

    }
    public void reqGetJinData(TimeOD timeOD_A){
        synchronized ("timeODNow"){
            if(timeODNow==null)
                {
                    timeODNow=timeOD_A;
                    handlerData.sendEmptyMessage(0);
                }else {
                timeODNow=timeOD_A;
                handlerData.sendEmptyMessage(0);
            }

        }

    }
    public TimeOD getTimeODNow(){
        synchronized ("timeODNow"){
            return timeODNow;

        }
    }
    public void setCalendarJinDMain(CalendarJinD calendarJinD_A){
        synchronized ("CalendarJinDMain"){
            calendarJinDMain=calendarJinD_A;
        }
    }
    public void updateViewCalendarData(){
        if(jin10CalendarSystemCenter!=null)
            {
                CalendarJinD calendarJinD_A=null;
                synchronized ("CalendarJinDMain"){
                    if(calendarJinDMain!=null)
                        {
                            calendarJinD_A=calendarJinDMain;

                        }

                }
                if(calendarJinD_A!=null)
                    {
                        jin10CalendarSystemCenter.updateJin10CalendarData(calendarJinD_A);
                    }
            }

    }

    @Override
    public void onSetCalendarData(int int_Num) {

    }

    @Override
    public void onUpdateCalendarData(CalendarDataClass[] calenderData_A) {

    }

    public class CalendarJinD{
        public TimeOD timeOD;
        Jin10CalendarCollectTool.CalendarJinDH[] calendarJinDHS;
        Jin10CalendarCollectTool.CalendarJinEV[] calendarJinEVS;
        FXDataPool.FXCalendar.CalenderDData calenderDData;
        public CalendarJinD(TimeOD timeOD_A,Jin10CalendarCollectTool.CalendarJinDH[] calendarJinDHS_A,Jin10CalendarCollectTool.CalendarJinEV[] calendarJinEVS_A){
            timeOD=timeOD_A;
            calendarJinDHS=calendarJinDHS_A;
            calendarJinEVS=calendarJinEVS_A;
        }
        public CalendarJinD(TimeOD timeOD_A,Jin10CalendarCollectTool.CalendarJinDH[] calendarJinDHS_A){
            timeOD=timeOD_A;
            calendarJinDHS=calendarJinDHS_A;
        }
        public CalendarJinD(TimeOD timeOD_A,FXDataPool.FXCalendar.CalenderDData calenderDData_A){
            timeOD=timeOD_A;
            calenderDData=calenderDData_A;
        }
    }



}
