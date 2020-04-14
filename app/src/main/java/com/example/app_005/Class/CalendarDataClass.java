package com.example.app_005.Class;

import android.util.Log;

import com.example.app_005.Internet.Jin10CalendarCollectTool;
import com.example.app_005.Tool.TimeOD;

public class CalendarDataClass {
    public int intMode;
    public String stringName;
    public int intID;
    public TimeOD timeOD;
    public String stringTime;
    public String stringText;
    public boolean hasResult;
    public String stringActual;
    public String stringForecast;
    public String stringPrevious;
    public int intStar;
    public String stringFlag;
    public boolean isA;
    public boolean isSaat;
    public CalendarDataClass(Jin10CalendarCollectTool.CalendarJinDH calendarJinDH_A){
        intMode=1;
        this.hasResult=calendarJinDH_A.boolMode;
        this.timeOD=new TimeOD(calendarJinDH_A.longTime);
        this.stringName=calendarJinDH_A.stringFlag+calendarJinDH_A.stringTimePeriod+calendarJinDH_A.stringName;
        this.stringTime=calendarJinDH_A.stringTime;
        this.intID=calendarJinDH_A.intID;
        //this.stringAttr=calendarDH.stringAttr;
        this.stringFlag=calendarJinDH_A.stringFlag;
        this.intStar =calendarJinDH_A.intStar;
        //]Log.w(this.toString(), "CalendarDataClass: T0"+"="+calendarJinDH_A.stringActual +"="+ calendarJinDH_A.stringForecast +"="+calendarJinDH_A.stringPrevious);
        this.stringActual =(calendarJinDH_A.stringActual);
        this.stringForecast =(calendarJinDH_A.stringForecast);
        this.stringPrevious =(calendarJinDH_A.stringPrevious);
    }
    public CalendarDataClass(Jin10CalendarCollectTool.CalendarJinEV calendarJinEV_A){
        intMode=2;

        this.hasResult=calendarJinEV_A.boolPending;
        //this.timeOD=new TimeOD(calendarJinEV_A.longTime);
        this.intID=calendarJinEV_A.intID;
        this.stringFlag=calendarJinEV_A.stringFlag;
        this.stringText =calendarJinEV_A.stringCity;
        this.stringTime=calendarJinEV_A.stringTime;
        this. stringName=calendarJinEV_A.stringEvent;
        this.intStar =calendarJinEV_A.intStar;

    }
}
