package com.example.app_005.View;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.app_005.Class.CalendarDataClass;
import com.example.app_005.Interface.OnUpdateCalendarData;
import com.example.app_005.Interface.OnUpdateTime;
import com.example.app_005.MainApplication;
import com.example.app_005.R;
import com.example.app_005.Tool.FXDataConnect;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.TimeOD;

public class EconomicCalendarSystemCenter implements OnUpdateCalendarData , OnUpdateTime {
    TimeOD timeODNow;
    FXDataConnect.TimeConnect timeConnect;
    FXDataConnect.CalendarConnect calendarConnect;
    FXDataPool.FXCalendar.CalenderDData calenderDDataMain,calenderDDataA,calenderDDataB,calenderDDataC;
    EconomicCalendarView economicCalendarView;
    CalendarDaySelView calendarDaySelView;
    OpenCalendarDataView openCalendarDataView;
    ImageButton imageButton;
    HandlerThread handlerThreadData;
    Handler handlerData;
    Handler handlerUI;
    boolean boolHandleData;
    boolean boolHandleUI;
    boolean calendarDataBool;
    CalendarJinDataTool calendarJinDataTool;
    public EconomicCalendarSystemCenter(Context context_A, View view_A){
        MainApplication mainApplication_A=((MainApplication)context_A.getApplicationContext());
        calendarJinDataTool=new CalendarJinDataTool(mainApplication_A.fxDataConnect);
        calendarJinDataTool.setEconomicCalendarSystemCenter(this);
        timeConnect=mainApplication_A.fxDataConnect.getTimeConnect(this);
        timeODNow=timeConnect.getTimeNew();
        calendarConnect=mainApplication_A.fxDataConnect.getCalendarConnect(this);
        economicCalendarView=view_A.findViewById(R.id.fra003_EconomicCalendarView_001);
        calendarDaySelView=view_A.findViewById(R.id.fra003_CalendarDaySelView_001);
        openCalendarDataView=view_A.findViewById(R.id.fra003_OpenCalendarDataView_001);
        imageButton=view_A.findViewById(R.id.fra003_OpenOutBut_001);
        initCalendarTextBut();
        openCalendarDataView.setVisibility(View.INVISIBLE);
        imageButton.setVisibility(View.INVISIBLE);
        economicCalendarView.setEconCalendarSystemCenter(this);
        calendarDaySelView.setEconomicCalendarSystemCenter(this);
        handlerThreadData = new HandlerThread("NewsODData");
        handlerThreadData.start();
        handlerData = new Handler(handlerThreadData.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0://请求更新临时数据
                        synchronized ("CalendarODBoolData") {
                            if (boolHandleData) {
                                boolHandleData = false;
                            } else {
                                return;
                            }
                            updateCalendarData();
                           // updateCalendarData();
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
                        synchronized ("CalendarODBoolUI") {
                            if (boolHandleUI) {
                                boolHandleUI = false;
                            } else {
                                return;
                            }
                            changeCalendarData();
                           // changeCalendarData();
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
        updateCalendarData();
        changeCalendarData();
        if(calenderDDataA!=null)
            {
                economicCalendarView.isInitDataOK();
            }

    }
//    public FXDataPool.FXCalendar.CalenderDData getDData(){
//       // timeODNow=timeOD_A;
//        if (calenderDDataA == null) {
//            if (calenderDDataA.timeODNow.PuC_isTimeODSameDay(timeODNow)) {
//                return calenderDDataA;
//
//            }
//        } else if (calenderDDataB == null) {
//            if (calenderDDataB.timeODNow.PuC_isTimeODSameDay(timeODNow)) {
//                return calenderDDataB;
//
//            }
//        }
//        reqUpdateCalendarData();
//        return null;
//
//    }
    public void closeCalendarViewData(){
        if(economicCalendarView!=null)
            {
                economicCalendarView.closeViewData();
            }
    }
    public void setCalendarDayTime(TimeOD timeOD_A){
        //]Log.w(this.toString(), "setCalendarDayTime: E0"+"="+timeOD_A.intsA[1]+"="+ timeOD_A.intsA[2]);
        if(timeODNow==null||(!timeODNow.PuC_isTimeODSameDay(timeOD_A)))
            {
                synchronized ("timeODNow"){

                    timeODNow=timeOD_A;
                    //]Log.w(this.toString(), "getTimeNow: G0"+"="+ (timeODNow!=null));
                }

            }

        reqUpdateCalendarData();
    }
    public TimeOD getTimeNow(){
        synchronized ("timeODNow"){
            //]Log.w(this.toString(), "getTimeNow: E0"+"="+ (timeODNow!=null));
            return timeODNow;
        }
    }
    public void nextCalendarDay(int int_A){
        if(calendarDaySelView!=null)
            {
                calendarDaySelView.nextSalDayData(int_A);
            }
    }
    public FXDataPool.FXCalendar.CalenderDData getDData(){
        //]Log.w(this.toString(), "SIKDJNgetDData: E1: "+"="+(calenderDDataA != null) +"="+(calenderDDataB != null) );
        if (calenderDDataA != null) {
            //]Log.w(this.toString(), "SIKDJNgetDData: E2A: "+"="+(calenderDDataA.timeOD.intsA[2]) );
            if (calenderDDataA.timeOD.PuC_isTimeODSameDay(timeODNow)) {
                //]Log.w(this.toString(), "SIKDJNgetDData: E0"+"="+ (calenderDDataA!=null));
                return calenderDDataA;

            }
        }
        if (calenderDDataB != null) {
            //]Log.w(this.toString(), "SIKDJNgetDData: E2B: "+"="+(calenderDDataB.timeOD.intsA[2]) );
            if (calenderDDataB.timeOD.PuC_isTimeODSameDay(timeODNow)) {
                return calenderDDataB;

            }
        }
        reqUpdateCalendarData();
        return null;

    }

//    public void reqHttpGetDData(TimeOD timeOD_A){
//
//    }
//    void getHttpDData(){
//        calendarConnect.getDData()
//    }
    /**
     *修正QuoDData数据确定
     */
    public void setCalendarDDataBool(Boolean bool_A){
        synchronized ("calendarDataBool"){
            calendarDataBool =bool_A;
        }
    }
    /**
     *QuoDData数据是否确定
     */
    public Boolean getCalendarDDataBool(){
        synchronized ("calendarDataBool"){
            return calendarDataBool;
        }
    }
    public void reqUpdateCalendarData(){
        if (!boolHandleData) {
            boolHandleData = true;
            handlerData.sendEmptyMessage(0);
        }
    }
    public void reqChangeCalendarData(){
        if (!boolHandleUI) {
            boolHandleUI = true;
            handlerUI.sendEmptyMessage(0);
        }
    }
    void updateCalendarData(){
        TimeOD timeOD_A=getTimeNow();


        Boolean bool_A = true;
        if (calenderDDataA != null&&calenderDDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
            if(calenderDDataA.calenderDayData.calenderDHData.size()==0)
                {
                    calenderDDataA=calendarConnect.getDData(timeOD_A);
                }
            bool_A = false;
        }
        if (bool_A && calenderDDataB != null &&  calenderDDataB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
            if(calenderDDataB.calenderDayData.calenderDHData.size()==0)
            {
                calenderDDataB=calendarConnect.getDData(timeOD_A);
            }
            bool_A = false;

        }
        if(bool_A)
        {
            FXDataPool.FXCalendar.CalenderDData calenderDData_A=calendarConnect.getDData(timeOD_A);

            //]Log.w(this.toString(), "CalendarMainDataAgetDData: E0"+"="+(calenderDDataC!=null) );
            if(calenderDData_A!=null)
            {
                synchronized ("calenderDDataC"){
                    calenderDDataC=calenderDData_A;
                }
                //]Log.w(this.toString(), "CalendarMainDataAgetDData: E0"+"="+calenderDDataC.calenderDayData.calenderDHData.size() );
                reqChangeCalendarData();
            }else {
                reqUpdateCalendarData();
            }

        }else {
            if(economicCalendarView!=null)
            {
                economicCalendarView.isInitDataOK();
            }
        }

    }
    void changeCalendarData(){
        if(calenderDDataC!=null)
        {
            synchronized ("calenderDDataC"){
                FXDataPool.FXCalendar.CalenderDData calenderDData_D=null;
                if(calenderDDataA!=null)
                {
                    calenderDData_D=calenderDDataA;
                }
                calenderDDataA=calenderDDataC;
                if(calenderDData_D!=null)
                {
                    calenderDDataB=calenderDData_D;
                }
            }

            setCalendarDDataBool(true);
            if(economicCalendarView!=null)
                {
                    economicCalendarView.isInitDataOK();
                }
        }

    }
   /**
    *发布
    */
    void announceCalendarData(CalendarDataClass[] calenderData_A){
        //]Log.w(this.toString(), "updateCalendarData: V0"+"="+ calenderData_A.length);
        for(int i=0;i<calenderData_A.length;i++)
            {
                //]Log.w(this.toString(), "updateCalendarData:V1 "+"="+i +"="+calenderData_A[i].stringName +"="+new TimeOD(System.currentTimeMillis()).intsA[4]);
            }
        if(economicCalendarView!=null)
            {
                economicCalendarView.announceClanedarData();
            }



    }
    public void initCalendarTextBut(){
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeOpenCalendarView();
            }
        });
    }
    public void openCalendarView(FXDataPool.FXCalendar.CalenderData calenderData_A){
        imageButton.setVisibility(View.VISIBLE);
        openCalendarDataView.setVisibility(View.VISIBLE);
        openCalendarDataView.setCalendarData(calenderData_A);

    }
    public void closeOpenCalendarView(){
        imageButton.setVisibility(View.INVISIBLE);
        openCalendarDataView.setVisibility(View.INVISIBLE);
        openCalendarDataView.boolDataOk=false;
    }



    @Override
    public void onSetCalendarData(int int_Num) {
        //]Log.w(this.toString(), "onSetCalendarData: E0"+"="+ int_Num +"="+(economicCalendarView!=null));
        //]Log.w(this.toString(), "OnCalendarData: O0"+"="+int_Num);
        if(economicCalendarView!=null)
            {
            economicCalendarView.isInitDataOK();
            }

    }

    @Override
    public void onUpdateCalendarData(CalendarDataClass[] calenderData_A) {
        //]Log.w(this.toString(), "OnCalendarData: O1"+"="+calenderData_A.length );
        announceCalendarData(calenderData_A);
    }

    @Override
    public void onUpdateTime(TimeOD timeODAA) {

    }

    @Override
    public void onUpdateNewDay(TimeOD timeODAA) {

    }
}
