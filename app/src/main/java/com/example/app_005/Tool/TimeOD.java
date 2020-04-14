package com.example.app_005.Tool;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeOD {
    public int[] intsA;
    public TimeOD(){

    }
    public TimeOD(int[] intsAA ){
        intsA=intsAA;

    }
    public TimeOD(int int_Y,int int_M,int int_D){
        intsA=new int[]{int_Y,int_M,int_D,0,0};

    }
    public TimeOD(TimeOD timeODAA){
        intsA=timeODAA.intsA;
    }
    public TimeOD(long longAA){
        intsA=PrC_longToTimeOD(longAA);

    }
    public String Prc_getStringTime(){
        return intsA[0]+"-"+intsA[1]+"-"+intsA[2];
    }

    /**
     * 将long转换成TimeOD
     *
     * @return the int [ ]
     */
    int[] PrC_longToTimeOD(long longAA){
        int[] ints_A=new int[5];
        Calendar calendar_A=new GregorianCalendar();
        calendar_A.setTimeInMillis(longAA);
        ints_A[0]=calendar_A.get(Calendar.YEAR);
        ints_A[1]=calendar_A.get(Calendar.MONTH);
        ints_A[2]=calendar_A.get(Calendar.DATE);
        ints_A[3]=calendar_A.get(Calendar.HOUR_OF_DAY);
        ints_A[4]=calendar_A.get(Calendar.MINUTE);
        return ints_A;
    }

    /**
     * 将TimeOD转换成long
     *
     * @return the long
     */
    public long PuC_getTimeLong(){
        Calendar calendar_A=new GregorianCalendar();
        calendar_A.set(Calendar.YEAR,intsA[0]);
        calendar_A.set(Calendar.MONTH,intsA[1]);
        calendar_A.set(Calendar.DATE,intsA[2]);
        calendar_A.set(Calendar.HOUR_OF_DAY,intsA[3]);
        calendar_A.set(Calendar.MINUTE,intsA[4]);
        return calendar_A.getTimeInMillis();
    }

    /**
     * 调查Long是否一样日期
     *
     * @param longAA the long aa
     * @return the boolean
     */
    public boolean PuC_isLongSameMINUTE(long longAA){
        int[] ints_A=new int[5];
        Calendar calendar_A=new GregorianCalendar();
        calendar_A.setTimeInMillis(longAA);
        ints_A[4]=calendar_A.get(Calendar.MINUTE);if(ints_A[4]!=intsA[4]){return false;}
        ints_A[3]=calendar_A.get(Calendar.HOUR_OF_DAY);if(ints_A[3]!=intsA[3]){return false;}
        ints_A[2]=calendar_A.get(Calendar.DATE);if(ints_A[2]!=intsA[2]){return false;}
        ints_A[1]=calendar_A.get(Calendar.MONTH);if(ints_A[1]!=intsA[1]){return false;}
        ints_A[0]=calendar_A.get(Calendar.YEAR);if(ints_A[0]!=intsA[0]){return false;}
        return true;
    }
    /**
     * 调查Long是否一样日期
     *
     * @param longAA the long aa
     * @return the boolean
     */
    public boolean PuC_isLongSameDay(long longAA){
        int[] ints_A=new int[4];
        Calendar calendar_A=new GregorianCalendar();
        calendar_A.setTimeInMillis(longAA);
        //ints_A[3]=calendar_A.get(Calendar.HOUR_OF_DAY);if(ints_A[3]!=intsA[3]){return false;}
        ints_A[2]=calendar_A.get(Calendar.DATE);if(ints_A[2]!=intsA[2]){return false;}
        ints_A[1]=calendar_A.get(Calendar.MONTH);if(ints_A[1]!=intsA[1]){return false;}
        ints_A[0]=calendar_A.get(Calendar.YEAR);if(ints_A[0]!=intsA[0]){return false;}
        return true;
    }
    public boolean PuC_isLongSameDay(long longAA,int intAA){
        int[] ints_A=new int[4];
        Calendar calendar_A=new GregorianCalendar();
        calendar_A.setTimeInMillis(longAA);
        calendar_A.set(Calendar.DATE,intAA);
        //ints_A[3]=calendar_A.get(Calendar.HOUR_OF_DAY);if(ints_A[3]!=intsA[3]){return false;}
        ints_A[2]=calendar_A.get(Calendar.DATE);if(ints_A[2]!=intsA[2]){return false;}
        ints_A[1]=calendar_A.get(Calendar.MONTH);if(ints_A[1]!=intsA[1]){return false;}
        ints_A[0]=calendar_A.get(Calendar.YEAR);if(ints_A[0]!=intsA[0]){return false;}
        return true;
    }
    /**
     * 调查TimeOD是否一样日期
     *
     * @return the boolean
     */
    public boolean PuC_isTimeODSameMINUTE(TimeOD timeODAA){
        if(intsA[4]!=timeODAA.intsA[4])return false;
        if(intsA[3]!=timeODAA.intsA[3])return false;
        if(intsA[2]!=timeODAA.intsA[2])return false;
        if(intsA[1]!=timeODAA.intsA[1])return false;
        if(intsA[0]!=timeODAA.intsA[0])return false;
        return true;
    }
    /**
     * 调查TimeOD是否一样日期
     *
     * @return the boolean
     */
    public boolean PuC_isTimeODSameDay(TimeOD timeODAA){
        //if(intsA[3]!=timeODAA.intsA[3])return false;
        if(intsA[2]!=timeODAA.intsA[2])return false;
        if(intsA[1]!=timeODAA.intsA[1])return false;
        if(intsA[0]!=timeODAA.intsA[0])return false;
        return true;
    }

    /**
     * 计算TimeOD添加参数后得到的日期
     *
     * @param intAA the int aa
     * @return the time od
     */
    public TimeOD PuC_getCrTimeOD(int intAA){
        Calendar calendar=new GregorianCalendar();
        calendar.setTimeInMillis(this.PuC_getTimeLong());
        calendar.add(Calendar.DATE,intAA);
        return new TimeOD(calendar.getTimeInMillis());
    }
    public TimeOD PuC_getCrTimeODMinute(int intAA){
        Calendar calendar=new GregorianCalendar();
        calendar.setTimeInMillis(this.PuC_getTimeLong());
        calendar.add(Calendar.MINUTE,intAA);
        return new TimeOD(calendar.getTimeInMillis());
    }
    /**
     *对比的日期大小[0=一样,1=大过this,2=小过this]
     */
    public int PuC_calTimeContrast(TimeOD timeODAA){
        int int_A=0;
        for(int i=0;i<this.intsA.length;i++)
        {
            if((int_A=PrC_Contrast(this.intsA[i],timeODAA.intsA[i]))!=0)return int_A;
        }
        return 0;

    }
    /**
     *[0==,1=<,2=>]
     */
    public int PuC_calTimeContrastMinu(TimeOD timeODAA){
        int int_A=0;
        for(int i=0;i<5;i++)
        {
            if((int_A=PrC_Contrast(this.intsA[i],timeODAA.intsA[i]))!=0)return int_A;
        }
        return 0;

    }
    /**
     *增加分钟
     */
    public void PuC_addMinute(int intAA){
        Calendar calendar=new GregorianCalendar();
        calendar.setTimeInMillis(this.PuC_getTimeLong());
        calendar.add(Calendar.MINUTE,intAA);
        this.intsA=new TimeOD(calendar.getTimeInMillis()).intsA;


    }


    /**
     *对比大小[0==,1=<,2=>]
     */
    private int PrC_Contrast(int intAA,int intBB) {
        if (intAA == intBB) {
            return 0;
        } else if (intAA < intBB) {
            return 1;
        } else {
            return 2;
        }

    }
    public String getStringTime(){
        return (intsA[3]>9?intsA[3]:("0"+intsA[3]))+":"+(intsA[4]>9?intsA[4]:("0"+intsA[4]));
    }
    public String getStringDay(){
        return (intsA[1]>9?intsA[1]:("0"+intsA[1]))+"/"+(intsA[2]>9?intsA[2]:("0"+intsA[2]));
    }
    public String getStringDate(){
        return (intsA[1]>9?intsA[1]:("0"+intsA[1]))+"/"+(intsA[2]>9?intsA[2]:("0"+intsA[2]))+"   "+(intsA[3]>9?intsA[3]:("0"+intsA[3]))+":"+(intsA[4]>9?intsA[4]:("0"+intsA[4]));
    }
}
