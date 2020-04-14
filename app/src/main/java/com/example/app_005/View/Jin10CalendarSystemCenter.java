package com.example.app_005.View;

import android.content.Context;
import android.service.autofill.FillEventHistory;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.app_005.Internet.Jin10CalendarCollectTool;
import com.example.app_005.MainApplication;
import com.example.app_005.R;
import com.example.app_005.Tool.TimeOD;

import java.sql.Time;

public class Jin10CalendarSystemCenter {
    CalendarJinDataTool calendarJinDataTool;
    CalendarDaySelView calendarDaySelView;
    OpenCalendarDataView openCalendarDataView;
    ImageButton imageButton;
    EconomicCalendarView economicCalendarView;
    public Jin10CalendarSystemCenter(Context context_A,View view_A){
        MainApplication mainApplication_A=((MainApplication)context_A.getApplicationContext());
        calendarDaySelView=view_A.findViewById(R.id.fra003_CalendarDaySelView_001);
        openCalendarDataView=view_A.findViewById(R.id.fra003_OpenCalendarDataView_001);
        economicCalendarView=view_A.findViewById(R.id.fra003_EconomicCalendarView_001);
        imageButton=view_A.findViewById(R.id.fra003_OpenOutBut_001);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeOpenCalendarView();
            }
        });
        calendarJinDataTool=new CalendarJinDataTool(mainApplication_A.fxDataConnect);
        calendarJinDataTool.setJin10CalendarSystemCenter(this);
        calendarDaySelView.setJin10CalendarSystemCenter(this);
        economicCalendarView.setJin10CalendarSystemCenter(this);
        openCalendarDataView.setJin10CalendarCollectTool(new Jin10CalendarCollectTool());

        calendarDaySelView.post(new Runnable() {
            @Override
            public void run() {
                calendarDaySelView.initJin10Data();
            }
        });

        openCalendarDataView.setVisibility(View.INVISIBLE);
        imageButton.setVisibility(View.INVISIBLE);
    }
    public void setCalendarDayTime(TimeOD time_A){
        //]Log.w(this.toString(), "setCalendarDayTime: R0"+"="+time_A.intsA[2] );
        calendarJinDataTool.reqGetJinData(time_A);

    }
    public boolean updateJin10CalendarData(CalendarJinDataTool.CalendarJinD calendarJinD_A){
        if(!calendarDaySelView.boolCalendarOK)
            {
                if(calendarDaySelView.timeODNow.PuC_isTimeODSameDay(calendarJinD_A.timeOD))
                    {
                        economicCalendarView.setCalendarJinD(calendarJinD_A);
                        calendarDaySelView.setBoolCalendarOK(true);

                    }

            }
        return true;

    }
    public void closeCalendarView(){
        economicCalendarView.closeCalendarView();
    }
    public void nextCalendarDay(int int_A){
        if(calendarDaySelView!=null)
        {
            calendarDaySelView.nextSalDayData(int_A);
        }
    }
    public void openCalendarView(EconomicCalendarView.EconCalendarData econCalendarData_A){
        imageButton.setVisibility(View.VISIBLE);
        openCalendarDataView.setVisibility(View.VISIBLE);
        openCalendarDataView.setCalendarData(econCalendarData_A);
    }
    public void closeOpenCalendarView(){
        imageButton.setVisibility(View.INVISIBLE);
        openCalendarDataView.setVisibility(View.INVISIBLE);
        openCalendarDataView.boolDataOk=false;
    }

}
