package com.example.app_005.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.app_005.Tool.TimeOD;

import java.util.Calendar;

public class CalendarDaySelView extends View {
    private int width;//设置高
    private int height;//设置高

    float widthSal;
    float widthSalB;
    float heightSal;
    Paint paintDayText;
    Paint paintDayTextB;
    Paint paintDayUI;
    Paint paintRectP;
    TimeOD timeODNow;
    String[] stringsWeekDay;
    int intDayNum;
    float floatAnimDayUI;
    EconomicCalendarSystemCenter economicCalendarSystemCenter;
    boolean boolCalendarOK;
    public CalendarDaySelView(Context context) {
        super(context);
    }

    public CalendarDaySelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }
    void initData(){

        paintDayText=new Paint();
        paintDayText.setAntiAlias(true);//消除锯齿
        paintDayText.setColor(Color.WHITE);
        paintDayText.setTextAlign(Paint.Align.CENTER);
        paintDayText.setTextSize(30);

        paintDayTextB=new Paint();
        paintDayTextB.setAntiAlias(true);//消除锯齿
        paintDayTextB.setColor(Color.parseColor("#FF5E5E5E"));//FF5E5E5E
        paintDayTextB.setTextAlign(Paint.Align.CENTER);
        paintDayTextB.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paintDayTextB.setTextSize(40);

        paintDayUI=new Paint();
        paintDayUI.setAntiAlias(true);//消除锯齿
        paintDayUI.setColor(Color.WHITE);

        paintRectP=new Paint();
        paintRectP.setAntiAlias(true);//消除锯齿
        paintRectP.setColor(Color.parseColor("#FF5E5E5E"));//FF5E5E5E

        valueAnimator = ValueAnimator.ofFloat(0.5F, 1);
        valueAnimator.setDuration(150);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);//设置宽和高
        //initData();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDaySel(canvas);
    }
    public void closeCalendarViewData(){
        if(economicCalendarSystemCenter!=null)
            {
                economicCalendarSystemCenter.closeCalendarViewData();
            }
    }
    public void setEconomicCalendarSystemCenter(EconomicCalendarSystemCenter economicCalendarSystemCenter_A){
        economicCalendarSystemCenter=economicCalendarSystemCenter_A;
    }
    Jin10CalendarSystemCenter jin10CalendarSystemCenter;
    public void setJin10CalendarSystemCenter(Jin10CalendarSystemCenter jin10CalendarSystemCenter_A){
        jin10CalendarSystemCenter=jin10CalendarSystemCenter_A;
    }
    public void initJin10Data(){
        widthSal=width/7F;
        widthSalB=widthSal/2F;
        heightSal=height/2F;
       //]Log.w(this.toString(), "initJin10Data: R0"+"="+ width +"="+height+"="+ widthSal +"="+widthSalB +"="+heightSal);

        timeODNow =new TimeOD(System.currentTimeMillis());
        initDay(timeODNow);
        //setSalDayData();

        floatAnimDayUI=1;
       //]Log.w(this.toString(), "initJin10Data: R1"+"="+intDayNum +"="+timeODNow.intsA[2]);
    }
    void drawDaySel(Canvas canvas){
        canvas.drawRect(0,0,width,25,paintRectP);
        canvas.drawRoundRect(0,0,width,height,25,25,paintRectP);
        if(stringsWeekDay!=null)
            {
                for(int i=0;i<7;i++)
                {
                    if(intDayNum==i)
                    {
                        canvas.drawCircle((widthSal*i)+widthSalB,heightSal,(28*floatAnimDayUI),paintDayUI);
                        canvas.drawText(stringsWeekDay[i],(widthSal*i)+widthSalB,heightSal+13,paintDayTextB);

                    }else {
                        canvas.drawText(stringsWeekDay[i],(widthSal*i)+widthSalB,heightSal+10,paintDayText);
                    }

                }
            }


    }
    void animDaySal(){

    }
    ValueAnimator valueAnimator;
    private void startValueAnimator() {


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 动画更新过程中的动画值，可以根据动画值的变化来关联对象的属性，实现属性动画
                floatAnimDayUI = (float) animation.getAnimatedValue();
                //Log.d("ValueAnimator", "动画值：" + floatAnimDayUI);
                invalidate();
            }
        });
        valueAnimator.start();
    }
    void initDay(TimeOD timeOD_A){
        timeODNow =timeOD_A;
        Calendar calendar_A=Calendar.getInstance();
        calendar_A.setTimeInMillis(timeOD_A.PuC_getTimeLong());
        switch (calendar_A.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                intDayNum=0;
                break;
            case Calendar.MONDAY:
                intDayNum=1;
                break;
            case Calendar.TUESDAY:
                intDayNum=2;
                break;
            case Calendar.WEDNESDAY:
                intDayNum=3;
                break;
            case Calendar.THURSDAY:
                intDayNum=4;
                break;
            case Calendar.FRIDAY:
                intDayNum=5;
                break;
            case Calendar.SATURDAY:
                intDayNum=6;
                break;
            default:
                intDayNum=0;
                break;
        }
        setDalDayWeek(0);

    }
    void setDaySal(int int_A){
        if(int_A!=intDayNum)
            {
                timeODNow=timeODNow.PuC_getCrTimeOD(int_A-intDayNum);
                intDayNum=int_A;
                closeCalendarViewData();
                setSalDayData();
                //floatAnimDayUI=0;
                startValueAnimator();
                invalidate();
            }else {
            valueAnimator.cancel();
        }

    }
//    void setNextDaySal(int int_A){
//        if(int_A!=intDayNum)
//        {
//            timeODNow=timeODNow.PuC_getCrTimeOD()
//            intDayNum=int_A;
//            setSalDayData(intDayNum);
//            //floatAnimDayUI=0;
//            startValueAnimator();
//            invalidate();
//        }else {
//            valueAnimator.cancel();
//        }
//
//    }
    void ccDaySal(){
        //intDayNum=int_A;
        //setSalDayData(intDayNum);
        //floatAnimDayUI=0;
        closeCalendarViewData();
        setSalDayData(intDayNum);
        startValueAnimator();
        invalidate();

    }
    void setSalDayData(int int_A){
        int int_B=int_A;
        //timeODNow = timeODNow.PuC_getCrTimeOD(int_B);
        if(jin10CalendarSystemCenter!=null)
            {
                jin10CalendarSystemCenter.setCalendarDayTime(timeODNow);
            }
    }
    void setSalDayData(){
        if(jin10CalendarSystemCenter!=null)
        {
            boolCalendarOK=false;
            jin10CalendarSystemCenter.setCalendarDayTime(timeODNow);
            jin10CalendarSystemCenter.closeCalendarView();
        }
    }
    public void setBoolCalendarOK(boolean bool_A){
        boolCalendarOK=bool_A;
    }
    void nextSalDayData(int int_A) {
        if (int_A == 1) {
            if (intDayNum != 6) {
                setDaySal(intDayNum + 1);
            } else {
                intDayNum = 0;
                setDalDayWeekB(1);
                ccDaySal();
            }

        } else {
            if (intDayNum != 0) {
                setDaySal(intDayNum - 1);
            } else {
                intDayNum = 6;
                setDalDayWeekB(-1);
                ccDaySal();
            }
        }
    }
    void setDalDayWeek(int int_A){

        if(int_A>0)
            {
                timeODNow = timeODNow.PuC_getCrTimeOD(7);
            }else if(int_A<0){
            timeODNow = timeODNow.PuC_getCrTimeOD(-7);
        }
        stringsWeekDay=new String[7];
        Calendar calendar_A =Calendar.getInstance();
        calendar_A.setTimeInMillis(timeODNow.PuC_getTimeLong());
        calendar_A.add(Calendar.DATE,-intDayNum);
        for(int i=0;i<7;i++)
            {
                stringsWeekDay[i]=String.valueOf(calendar_A.get(Calendar.DATE));
                calendar_A.add(Calendar.DATE,1);
            }
        setSalDayData();


    }
    void setDalDayWeekB(int int_A){

        if(int_A>0)
        {
            timeODNow = timeODNow.PuC_getCrTimeOD(1);
        }else if(int_A<0){
            timeODNow = timeODNow.PuC_getCrTimeOD(-1);
        }
        stringsWeekDay=new String[7];
        Calendar calendar_A =Calendar.getInstance();
        calendar_A.setTimeInMillis(timeODNow.PuC_getTimeLong());
        calendar_A.add(Calendar.DATE,-intDayNum);
        ////Log.w(this.toString(), "setDalDayWeekB: E0"+"="+calendar_A.get(Calendar.DATE) +"="+intDayNum );
        for(int i=0;i<7;i++)
        {
            stringsWeekDay[i]=String.valueOf(calendar_A.get(Calendar.DATE));
            calendar_A.add(Calendar.DATE,1);
        }

    }

    void ccTouchSalEvent(MotionEvent event){
        float float_X=event.getX();
        float float_Y=event.getY();
        //Log.w(this.toString(), "ccTouchSalEvent: W2"+"="+widthSal +"="+widthSalB );
        for(int i=0;i<7;i++)
            {
                if((((widthSal*i)+widthSalB)-30)<float_X&&(((widthSal*i)+widthSalB)+50)>float_X)
                    {
                        //Log.w(this.toString(), "ccTouchSalEvent: W0"+"="+ i +"="+float_X +"="+((widthSal*i)+widthSalB));
                        setDaySal(i);
                    }


            }

    }


    public boolean dispatchTouchEvent(MotionEvent event) {


        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                ////Log.i(this.toString(), "("+event.getX()+"="+width+")_A_("+event.getY()+"="+height+")");
                break;
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }

        return super.dispatchTouchEvent(event);
    }
    float floatTouchMove;
    boolean boolTouchDown;
    public boolean onTouchEvent(MotionEvent event) {
        if(!jin10CalendarSystemCenter.openCalendarDataView.boolDataOk)
            {
                switch (event.getActionMasked()){
                    case MotionEvent.ACTION_MOVE:
                        if(boolTouchDown)
                        {
                            float float_M=floatTouchMove-event.getX();
                            //Log.w(this.toString(), "floatTouchMove: V0"+"="+ float_M +"="+floatTouchMove +"="+event.getX());
                            if(float_M>200)
                            {
                                setDalDayWeek(1);
                                boolTouchDown=false;
                                invalidate();
                            }else if(float_M<-200)
                            {
                                setDalDayWeek(-1);
                                boolTouchDown=false;
                                invalidate();
                            }
                        }

                        return true;
                    case MotionEvent.ACTION_DOWN:
                        boolTouchDown=true;
                        floatTouchMove=event.getX();
                        return true;
                    case MotionEvent.ACTION_UP:
                        if(boolTouchDown)
                        {
                            ccTouchSalEvent(event);
                        }

                        return true;

                }
            }

        return super.onTouchEvent(event);
    }
}
