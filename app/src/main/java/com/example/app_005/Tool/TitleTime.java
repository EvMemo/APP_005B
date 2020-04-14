package com.example.app_005.Tool;


import java.sql.Time;
import java.util.Calendar;

public class TitleTime {
    TimeOD timeODNew;
    TimeOD timeODNow;
    long longNew;
    long longNow;
    public TitleTime(){
        synchronized (this){
            longNew= Calendar.getInstance().getTimeInMillis();
            longNow=longNew;
            timeODNew=new TimeOD(longNew);
            timeODNow=new TimeOD(longNow);
        }

    }
    public void updateTimeNew(){
        synchronized (this){
            longNew=Calendar.getInstance().getTimeInMillis();
            timeODNew=new TimeOD(longNew);
//            if(!timeOD_A.PuC_isTimeODSameDay(timeODNew))
//                {
//                    timeODNew=timeOD_A;
//                    return true;
//                }else {
//                timeODNew=timeOD_A;
//                return false;
//            }

        }
    }
    public void setTimeNow(TimeOD timeODAA){
        synchronized (this){
            longNow=timeODAA.PuC_getTimeLong();
            timeODNow=new TimeOD(longNow);
        }
    }
    public void setTimeNow(long longTimeAA){
        synchronized (this){
            longNow=longTimeAA;
            timeODNow=new TimeOD(longNow);
        }
    }
    public TimeOD getTimeNew() {
        synchronized (this) {
            return timeODNew;
        }
    }
    public TimeOD getTimeNow() {
        synchronized (this) {
            return timeODNow;
        }
    }
    public long getlongNew() {
        synchronized (this) {
            return longNew;
        }
    }
    public long getlongNow() {
        synchronized (this) {
            return longNow;
        }
    }
}
