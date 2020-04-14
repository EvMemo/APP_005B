package com.example.app_005.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.example.app_005.Class.CalendarDataClass;
import com.example.app_005.Internet.Jin10CalendarCollectTool;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.TextStringTool;
import com.example.app_005.Tool.TimeOD;

import java.util.ArrayList;

public class EconomicCalendarView extends View {
    private int width;//设置高
    private int height;//设置高
    //说明explain
    Paint paintTextExplain;
    Paint paintTextName;
    Paint paintTextNameR;
    Paint paintTextNameG;
    Paint paintStar;
    Paint paintLine;
    ArrayList<EconCalendarData> econCalendarDataAr;
    FXDataPool.FXCalendar.CalenderDData calenderDData;
    boolean boolDataOK;
    boolean boolDataZero;
    boolean boolInitOK1,boolInitOK2;
    public TimeOD timeODNow;
    CalendarJinDataTool.CalendarJinD calendarJinD;
    public EconomicCalendarView(Context context) {
        super(context);
        initData();
    }

    public EconomicCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }
    void initData(){
        mScroller = new Scroller(getContext(), null, true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mMaxFlintVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMinFlintVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMinFlintVelocity = 600;
        econCalendarDataAr=new ArrayList<>();

        paintTextExplain=new Paint();
        paintTextExplain.setAntiAlias(true);//消除锯齿
        paintTextExplain.setColor(Color.BLACK);
        paintTextExplain.setTextAlign(Paint.Align.LEFT);
        paintTextExplain.setTextSize(30);

        paintTextName=new Paint();
        paintTextName.setAntiAlias(true);//消除锯齿
        paintTextName.setColor(Color.BLACK);
        paintTextName.setTextAlign(Paint.Align.LEFT);
        paintTextName.setTextSize(25);

        paintStar=new Paint();
        paintStar.setAntiAlias(true);//消除锯齿
        paintStar.setColor(Color.BLACK);
        paintStar.setTextAlign(Paint.Align.CENTER);
        paintStar.setTextSize(25);

        paintTextNameR=new Paint();
        paintTextNameR.setAntiAlias(true);//消除锯齿
        paintTextNameR.setColor(Color.RED);
        paintTextNameR.setTextAlign(Paint.Align.LEFT);
        paintTextNameR.setTextSize(25);

        paintTextNameG=new Paint();
        paintTextNameG.setAntiAlias(true);//消除锯齿
        paintTextNameG.setColor(Color.parseColor("#308D24"));
        paintTextNameG.setTextAlign(Paint.Align.LEFT);
        paintTextNameG.setTextSize(25);

        paintLine=new Paint();
        paintLine.setAntiAlias(true);//消除锯齿
        paintLine.setColor(Color.GRAY);
        paintLine.setTextAlign(Paint.Align.CENTER);
        paintLine.setStrokeWidth(3);//设置宽度
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);//设置宽和高
        boolInitOK1=true;
        isInitDataOK();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(boolDataOK)
            {
                int int_A = intRecycDataNow;
                float float_W = 0;
                float float_C=30-intViewNowH;
                //]Log.w(this.toString(), "econCalendarDataAr:E0 "+"="+econCalendarDataAr.size());
                for (int i = int_A; i < econCalendarDataAr.size(); i++) {


                    if(econCalendarDataAr.get(i).intMode==0)
                        {
                            canvas.drawLine(0,float_W-intViewNowH+20,width,float_W-intViewNowH+20,paintLine);
                            canvas.drawLine(0,float_W+econCalendarDataAr.get(i).viewH-intViewNowH,width,float_W+econCalendarDataAr.get(i).viewH-intViewNowH,paintLine);
                            canvas.drawText(econCalendarDataAr.get(i).strings[0],20,float_W+float_C+25,paintTextExplain);
                            canvas.drawText(econCalendarDataAr.get(i).strings[1],100,float_W+float_C+25,paintTextExplain);
                            canvas.drawText(econCalendarDataAr.get(i).strings[2],180,float_W+float_C+25,paintTextExplain);
                            canvas.drawText(econCalendarDataAr.get(i).strings[3],400,float_W+float_C+25,paintTextExplain);
                            canvas.drawText(econCalendarDataAr.get(i).strings[4],width-280,float_W+float_C+25,paintTextExplain);
                            canvas.drawText(econCalendarDataAr.get(i).strings[5],width-190,float_W+float_C+25,paintTextExplain);
                            canvas.drawText(econCalendarDataAr.get(i).strings[6],width-80,float_W+float_C+25,paintTextExplain);
                        }else if(econCalendarDataAr.get(i).intMode==3)
                            {
                                canvas.drawLine(0,float_W-intViewNowH+20,width,float_W-intViewNowH+20,paintLine);
                                canvas.drawLine(0,float_W+econCalendarDataAr.get(i).viewH-intViewNowH-3,width,float_W+econCalendarDataAr.get(i).viewH-intViewNowH-3,paintLine);
                                canvas.drawText(econCalendarDataAr.get(i).strings[0],20,float_W+float_C+25,paintTextExplain);
                                canvas.drawText(econCalendarDataAr.get(i).strings[1],100,float_W+float_C+25,paintTextExplain);
                                canvas.drawText(econCalendarDataAr.get(i).strings[2],180,float_W+float_C+25,paintTextExplain);
                                canvas.drawText(econCalendarDataAr.get(i).strings[3],600,float_W+float_C+25,paintTextExplain);
                            }else if(econCalendarDataAr.get(i).intMode==2)
                                {
                                    canvas.drawLine(0,float_W+econCalendarDataAr.get(i).viewH-intViewNowH-3,width,float_W+econCalendarDataAr.get(i).viewH-intViewNowH-3,paintLine);
                                    canvas.drawText(econCalendarDataAr.get(i).stringTime,20,float_W+float_C,paintTextName);
                                    canvas.drawText(econCalendarDataAr.get(i).stringActual,100,float_W+float_C,paintTextName);
                                    //canvas.drawText(econCalendarDataAr.get(i).stringPrevious,170,float_W+float_C+25,paintTextName);

                                    drawStar(canvas,225,float_W+float_C,econCalendarDataAr.get(i).intStar);
                                    String[] strings_A = econCalendarDataAr.get(i).strings;
                                    if (strings_A != null) {
                                        for (int e = 0; e < strings_A.length; e++) {
                                            canvas.drawText(strings_A[e],280,float_W+float_C+(e*30),paintTextName);
                                        }
                                    }


                                }
                    else {
                        canvas.drawLine(0,float_W+econCalendarDataAr.get(i).viewH-intViewNowH-3,width,float_W+econCalendarDataAr.get(i).viewH-intViewNowH-3,paintLine);
                        //String string_Flag = econCalendarDataAr.get(i).calendarJinDH.stringFlag;
                       // String string_Name=econCalendarDataAr.get(i).calendarJinDH.stringName;
                        //canvas.drawText(string_Name,290,float_W+15,paintTextName);
                        String[] strings_A = econCalendarDataAr.get(i).strings;
                        //float float_W2 = float_W + econCalendarDataAr.get(i).floatViewH;
                        drawStar(canvas,225,float_W+float_C,econCalendarDataAr.get(i).intStar);
//                    canvas.drawRoundRect(20 - 1, float_W - intViewNowH - 1, width - 20 + 3, float_W2 - intViewNowH + 4, 8, 8, paintNewsRectP);
//                    canvas.drawRoundRect(20, float_W - intViewNowH, width - 20, float_W2 - intViewNowH, 8, 8, paintNewsRect);
//
//                    canvas.drawText(string_A, 20 + 20, float_W - intViewNowH + 45, paintNewsText);
//
                        if (strings_A != null) {
                            for (int e = 0; e < strings_A.length; e++) {
                                canvas.drawText(strings_A[e],280,float_W+float_C+(e*30),paintTextName);
                            }
                        }
                        canvas.drawText(econCalendarDataAr.get(i).stringTime,20,float_W+float_C,paintTextName);
                       // canvas.drawText(econCalendarDataAr.get(i).calenderDHData.stringFlag,100,float_W+float_C,paintTextName);

                        if(true)
                            {

//                                canvas.drawText(getCalendarImportance(econCalendarDataAr.get(i).calenderDHData.intStar),170,float_W+float_C,paintTextName);
                                canvas.drawText(econCalendarDataAr.get(i).stringActual,width-290,float_W+float_C,econCalendarDataAr.get(i).boolAc?paintTextNameR:paintTextNameG);
                                canvas.drawText(econCalendarDataAr.get(i).stringForecast,width-190,float_W+float_C,paintTextName);
                                canvas.drawText(econCalendarDataAr.get(i).stringPrevious,width-90,float_W+float_C,paintTextName);
                            }else {
                           // canvas.drawText("假日",180,float_W+float_C,paintTextName);
                        }

                    }


                    //canvas.drawText("11/27 22:59", 20 + 20, float_W2 - intViewNowH - 13, paintNewsTextTime);
                    //canvas.drawRoundRect(170,float_W2- intViewNowH -33,250,float_W2- intViewNowH -5,3,3,paintNewsSkill);
                    //StaticLayout staticLayout_A=new StaticLayout(string_A,paintNewsTextNote,()(width-40), Layout.Alignment.ALIGN_LEFT,);
                    ////]Log.w(this.toString(), "KOILPKFMonDrawD0: "+"="+height +"="+float_W +"="+intViewNowH);
                    if (float_W > height) {
                        //]Log.w(this.toString(), "ODIKDonDrawS0: " + "=" + int_A + "=" + i);
                        break;
                    }
                    float_W += econCalendarDataAr.get(i).viewH;

                }

            }else {
            canvas.drawText("等待",width/2F,height/2F,paintTextExplain);
        }
    }
    public void closeViewData(){
        boolDataOK=false;
        invalidate();
    }
    String[] stringStars=new String[]{"✩","✩✩","✩✩✩"};
    void drawStar(Canvas canvas,float float_W,float float_H,int intStar_A){
        //]Log.w(this.toString(), "drawStar: R0"+"="+ intStar_A);
        //intStar_A=5;
        switch(intStar_A){
            case 1:
                canvas.drawText(stringStars[0],float_W,float_H,paintStar);
                break;
            case 2:
                canvas.drawText(stringStars[1],float_W,float_H,paintStar);
                break;
            case 3:
                canvas.drawText(stringStars[2],float_W,float_H,paintStar);
                break;
            case 4:
                canvas.drawText(stringStars[1],float_W,float_H-10,paintStar);
                canvas.drawText(stringStars[1],float_W,float_H+10,paintStar);
                break;
            case 5:
                canvas.drawText(stringStars[2],float_W,float_H-10,paintStar);
                canvas.drawText(stringStars[1],float_W,float_H+10,paintStar);
                break;
            default:
                break;
        }
    }
    String stringImportance1="★✩✩";
    String stringImportance2="★★✩";
    String stringImportance3="★★★";
    String getCalendarImportance(int int_A){
        switch(int_A){
            case 3:
                return stringImportance3;
            case 2:
                return stringImportance2;
            case 1:
            default:
                return stringImportance1;
        }
    }
    Paint getFontColor(int int_A){
        switch(int_A){
            case 1:
                return paintTextNameR;
            case 2:
                return paintTextName;
            case 3:
            default:
                return paintTextNameG;
        }

    }
    public void announceClanedarData(){
        invalidate();
    }
    EconomicCalendarSystemCenter economicCalendarSystemCenter;
    public void setEconCalendarSystemCenter(EconomicCalendarSystemCenter econCalendarSystemCenter_A ){
        economicCalendarSystemCenter=econCalendarSystemCenter_A;
        //]Log.w(this.toString(), "setEconCalendarSystemCenter: T0"+"="+(economicCalendarSystemCenter.getTimeNow()!=null) );
        boolInitOK2=true;
        isInitDataOK();
    }
    Jin10CalendarSystemCenter jin10CalendarSystemCenter;
    public void setJin10CalendarSystemCenter(Jin10CalendarSystemCenter jin10CalendarSystemCenter_A ){
        jin10CalendarSystemCenter=jin10CalendarSystemCenter_A;
       // //]Log.w(this.toString(), "setEconCalendarSystemCenter: T0"+"="+(economicCalendarSystemCenter.getTimeNow()!=null) );
       // boolInitOK2=true;
        //isInitDataOK();
    }
    public void isInitDataOK(){
        if((boolInitOK1&&boolInitOK2)&&economicCalendarSystemCenter!=null)
            {
                TimeOD timeOD_A=economicCalendarSystemCenter.getTimeNow();
                //]Log.w(this.toString(), "isInitDataOK: T3"+"="+ (economicCalendarSystemCenter.getTimeNow()!=null));
                //]Log.w(this.toString(), "isInitDataOK: T0"+"="+ (timeODNow==null));
                //]Log.w(this.toString(), "isInitDataOK: T1"+"="+ (timeOD_A==null));
                //]Log.w(this.toString(), "isInitDataOK: E2"+"="+(!boolDataZero||timeODNow==null||(!timeODNow.PuC_isTimeODSameDay(timeOD_A))) );
                if(!boolDataZero||timeODNow==null||(!timeODNow.PuC_isTimeODSameDay(timeOD_A)))
                    {
                        timeODNow=timeOD_A;
                        FXDataPool.FXCalendar.CalenderDData calendarDData_A=economicCalendarSystemCenter.getDData();
                        //]Log.w(this.toString(), "isInitDataOK: E0"+"="+(calendarDData_A!=null) );
                        if(calendarDData_A!=null)
                        {
                            //]Log.w(this.toString(), "isInitDataOK: E1"+"="+calendarDData_A.calenderDayData.calenderDHData.size() );
                            setCalendarData(calendarDData_A);
                        }
                    }



            }
    }

    public void setCalendarData(FXDataPool.FXCalendar.CalenderDData calendarDData_A){

        calenderDData=calendarDData_A;
        if(calenderDData!=null)
        {
            ccCalendarDrewData();

            boolDataOK=true;
            invalidate();
        }else {
            boolDataOK=false;
        }
    }
    public void setCalendarJinD(CalendarJinDataTool.CalendarJinD calendarJinD_A){
        calendarJinD=calendarJinD_A;
        ccCalendarDrewJinData();
        ////]Log.w(this.toString(), "setCalendarJinD: R0"+"="+(calendarJinD!=null));
        ////]Log.w(this.toString(), "setCalendarJinD: R0"+"="+(calendarJinD.calendarJinDHS!=null));
       // //]Log.w(this.toString(), "setCalendarJinD: R2"+"="+calendarJinD.calendarJinDHS.length +"="+calendarJinD.timeOD.intsA[2] );
        invalidate();
    }
    public void closeCalendarView(){
        econCalendarDataAr=new ArrayList<>();
       // econCalendarDataAr.add(new EconCalendarData());
        econCalendarDataAr.add(new EconCalendarData());
        intRecycDataNow=0;
        intViewNowH=0;
        floatRecycDataLastH=-1;
        intRecycDataLastNum=-1;
        boolDataOK=false;
        invalidate();
    }
    void reqCalendarJiData() {
        if (economicCalendarSystemCenter != null) {
            calenderDData = economicCalendarSystemCenter.getDData();
            if (calenderDData != null) {
                ccCalendarDrewData();
                boolDataOK = true;
            } else {
                boolDataOK = false;
            }
        }
    }
    void ccCalendarDrewData(){
        econCalendarDataAr=new ArrayList<>();
        econCalendarDataAr.add(new EconCalendarData());
        intRecycDataNow=0;
        intViewNowH=0;
        floatRecycDataLastH=-1;
        intRecycDataLastNum=-1;
        //]Log.w(this.toString(), "ccCalendarDrewData: V0"+"="+ calenderDData.calenderDayData.calenderDHData.size());
        if(calenderDData.calenderDayData.calenderDHData.size()==0)
            {
                boolDataZero=false;
            }else {
            boolDataZero=true;
            for(int i = 0; i<calenderDData.calenderDayData.calenderDHData.size(); i++)
            {
                econCalendarDataAr.add(new EconCalendarData(calenderDData.calenderDayData.calenderDHData.get(i)));
            }
            float floatLastH=0;
            for(int i=econCalendarDataAr.size()-1;i>=0;i--)
            {
                floatLastH+=econCalendarDataAr.get(i).viewH;
                if(floatLastH>height-3)
                {
                    floatRecycDataLastH=(height-3)-floatLastH;
                    intRecycDataLastNum=i-1;
                    break;
                }
                //floatLastH+=20;
            }
        }


    }
    void ccCalendarDrewJinData(){
        econCalendarDataAr=new ArrayList<>();
        econCalendarDataAr.add(new EconCalendarData());
        //econCalendarDataAr.add(new EconCalendarData());
        intRecycDataNow=0;
        intViewNowH=0;
        floatRecycDataLastH=-1;
        intRecycDataLastNum=-1;
     //]Log.w(this.toString(), "ccCalendarDrewData: V0"+"="+ calendarJinD.calenderDData.calenderDayData.calenderDHData.size() +"="+calendarJinD.calenderDData.calenderDayData.calenderEVData.size());
        boolDataOK=true;
        if(calendarJinD.calenderDData!=null) {

            if (calendarJinD.calenderDData.calenderDayData.calenderDHData.size() == 0) {
                boolDataZero = false;
            } else {
                boolDataZero = true;
                for (int i = 0; i < calendarJinD.calenderDData.calenderDayData.calenderDHData.size(); i++) {
                    econCalendarDataAr.add(new EconCalendarData(calendarJinD.calenderDData.calenderDayData.calenderDHData.get(i)));//.calendarJinDHS[i]));
                }
//                float floatLastH=0;
//                for(int i=econCalendarDataAr.size()-1;i>=0;i--)
//                {
//                    floatLastH+=econCalendarDataAr.get(i).viewH;
//                    if(floatLastH>height-3)
//                    {
//                        floatRecycDataLastH=(height-3)-floatLastH;
//                        intRecycDataLastNum=i-1;
//                        break;
//                    }
//                    //floatLastH+=20;
//                }
            }

            //]Log.w(this.toString(), "ccCalendarDrewJinData: GH0a" + "=" + econCalendarDataAr.size());

            if (calendarJinD.calenderDData.calenderDayData.calenderEVData.size() == 0) {

            } else {
                econCalendarDataAr.add(new EconCalendarData(3));
                boolDataZero = true;
                for (int i = 0; i < calendarJinD.calenderDData.calenderDayData.calenderEVData.size(); i++) {
                    //econCalendarDataAr.add(new EconCalendarData(2,calendarJinD.calenderDData.calenderDayData.calenderEVData.get(i)));//.calendarJinEVS[i]));
                    econCalendarDataAr.add(new EconCalendarData(calendarJinD.calenderDData.calenderDayData.calenderEVData.get(i)));
                }

            }

        }
        if(boolDataZero)
            {
                float floatLastH=0;
                for(int i=econCalendarDataAr.size()-1;i>=0;i--)
                {
                    floatLastH+=econCalendarDataAr.get(i).viewH;
                    if(floatLastH>height-3)
                    {
                        floatRecycDataLastH=(height-3)-floatLastH;
                        intRecycDataLastNum=i-1;
                        break;
                    }
                    //floatLastH+=20;
                }
            }

        //]Log.w(this.toString(), "ccCalendarDrewJinData: GH0b"+"="+econCalendarDataAr.size() );


    }
    public float intViewNowH;
    float floatRecycDataLastH;
    int intRecycDataNow=-1;
    int intRecycDataLastNum;
    public void moveView(float float_Move) {
        if(boolDataOK&&intRecycDataNow!=-1&&boolDataZero&&intRecycDataLastNum!=-1)
        {

           // //]Log.w("NewsRecycleView", "LKDMLDmoveViewD0: "+"="+float_Move +"="+ intViewHRest +"="+intRecycDataNow +"="+(newsRecycDataNow!=null));
            float float_A = intViewNowH - float_Move;
            float float_B = econCalendarDataAr.get(intRecycDataNow).viewH;

            if (intRecycDataNow == intRecycDataLastNum) {

                if (float_A > (float_B - floatRecycDataLastH)) {
                    //intViewHRest+=(float_A-(float_B - 144));
                    float_A = float_B;
                    float_A -= floatRecycDataLastH;

                }
                if (float_A < 0) {
                    if (intRecycDataNow != 0) {
                        intRecycDataNow--;
                        float_B = econCalendarDataAr.get(intRecycDataNow).viewH;
                        float_A = float_B + float_A;
                        float_A = repsMoveView(float_A);
                    } else {
                        //intViewHRest+=float_A;
                        float_A = 0;
                    }

                }

            } else if (float_A < 0) {
                if (intRecycDataNow != 0) {
                    intRecycDataNow--;
                    float_B = econCalendarDataAr.get(intRecycDataNow).viewH;
                    float_A = float_B + float_A;
                    float_A = repsMoveView(float_A);
                } else {
                    //intViewHRest+=float_A;
                    float_A = 0;
                }

            } else if (float_A > float_B) {
                if (intRecycDataNow == intRecycDataLastNum) {
                    if (float_A > (float_B - floatRecycDataLastH)) {
                        //intViewHRest+=(float_A-(float_B - 144));
                        float_A = float_B;
                        float_A -= floatRecycDataLastH;
                    }
                } else {
                    intRecycDataNow++;
                    float_A = float_A - float_B;
                    float_A = repsMoveView(float_A);
                }

            }


            //]Log.w(this.toString(), "DL:FmoveViewD1: " + "=" + intViewNowH + "=" + float_A);

            intViewNowH = float_A;


//        if(float_A<0)
//            {
//                intViewNowH+=float_Move;
//            }else {
//            intViewNowH=0;
//        }


            //]Log.w(this.toString(), "moveViewD0: " + "=" + intViewNowH + "=" + intRecycDataNow);
            invalidate();

        }
    }
    public float repsMoveView(float float_Move) {
        float float_A=float_Move;
        float float_B=econCalendarDataAr.get(intRecycDataNow).viewH;
        if(float_A<0)
        {
            if(intRecycDataNow!=0)
            {
                intRecycDataNow--;
                float_B=econCalendarDataAr.get(intRecycDataNow).viewH;
                float_A=float_B+float_A;
                return repsMoveView(float_A);
            }else {
                float_A=0;
            }

        }else if(float_A>float_B)
        {
            if(intRecycDataNow!=intRecycDataLastNum)
            {
                intRecycDataNow++;
                //]Log.w(this.toString(), "repsMoveViewS9: "+"="+intRecycDataNow );
                float_A=float_A-float_B;
                return repsMoveView(float_A);
            }else {
                float_A=float_B;
                float_A-=floatRecycDataLastH;
            }

        }
        return float_A;
    }
    long longTouchTime;
    float floatTouchX,floatTouchY;
    boolean touchView(MotionEvent event){
        if(econCalendarDataAr!=null&&econCalendarDataAr.size()>0)
        {

            float float_X=event.getX();
            float float_Y=event.getY();
            if(float_X>20&&float_X<width-20)
            {
                int int_A=intRecycDataNow;
                float float_W=10;
                for(int i=int_A;i<econCalendarDataAr.size();i++)
                {
                    float float_W2=float_W+econCalendarDataAr.get(i).viewH-20;
                    float float_H2=float_W- intViewNowH;
                    float float_H3=float_W2- intViewNowH;
                    if(float_Y>float_H2&&float_Y<float_H3)
                    {
                        openCalendarView(econCalendarDataAr.get(i));
                        return true;
                    }


                    if(float_W>height)
                    {
                        break;
                    }
                    float_W+=econCalendarDataAr.get(i).viewH;

                }
            }
        }
        return false;
    }
    void openCalendarView(EconomicCalendarView.EconCalendarData econCalendarData_A){
        if(econCalendarData_A!=null)
            {
                jin10CalendarSystemCenter.openCalendarView(econCalendarData_A);
            }

    }
    void nextCalendarDay(int int_A){
        //]Log.w(this.toString(), "nextCalendarDay: E0"+"="+ int_A +"="+(economicCalendarSystemCenter!=null));
        if(jin10CalendarSystemCenter!=null)
            {
                jin10CalendarSystemCenter.nextCalendarDay(int_A);
            }
    }
    float floatTouchDownY;
    private VelocityTracker velocityTracker;
    private int mMaxFlintVelocity, mMinFlintVelocity;
    private Scroller mScroller;
    float floatModeNextW;
    boolean boolNext;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //]Log.w(this.toString(), "DOKDonTouchEventD0: "+"="+event.getAction() );
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if(boolNext)
                    {
                        float float_B=event.getX()-floatModeNextW;
                        if(float_B>400)
                        {
                            nextCalendarDay(-1);
                            boolDataOK=false;
                            boolNext=false;

                        }else if(float_B<-400)
                        {
                            nextCalendarDay(1);
                            boolDataOK=false;
                            boolNext=false;
                        }
                        float float_A=event.getY()-floatTouchDownY;
                        floatTouchDownY=event.getY();
                        moveView(float_A);
                    }





                return true;
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                floatTouchDownY=event.getY();
                floatTouchX=event.getX();
                floatTouchY=event.getY();
                longTouchTime=System.currentTimeMillis();
                floatModeNextW=floatTouchX;
                boolNext=true;
                return true;
            case MotionEvent.ACTION_UP:
                if(System.currentTimeMillis()<longTouchTime+500&&(spacing(floatTouchX,floatTouchY,event.getX(),event.getY())<10))
                {
                    touchView(event);

                }else {

                    velocityTracker.computeCurrentVelocity(1000, mMaxFlintVelocity);
                    int xVelocity = (int) velocityTracker.getXVelocity();
                    int yVelocity = (int) velocityTracker.getYVelocity();
                    boolean bool_A=(Math.abs(xVelocity) > mMinFlintVelocity|| Math.abs(yVelocity) > mMinFlintVelocity);
                    if (bool_A)
                    {
                        mScroller.fling(0,(int) floatTouchDownY, xVelocity, yVelocity, 0, 0, -99999, 99999);
                        invalidate();
                    }
                }

                return true;
        }
        return super.onTouchEvent(event);
    }
    private float spacing(float float_X1,float float_Y1,float float_X2,float float_Y2) {
        float x = float_X1 - float_X2;
        float y = float_Y1 - float_Y2;
        return (float) Math.sqrt(x * x + y * y);
    }
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            //]Log.w(this.toString(), "KNDLOcomputeScrollS0: "+"="+ mScroller.getCurrY());
            float float_A=mScroller.getCurrY()-floatTouchDownY;

            moveView(mScroller.getCurrY()-floatTouchDownY);
            floatTouchDownY=mScroller.getCurrY();
            //floatTouchDownY=mScroller.getCurrY();
            ////]Log.w("OKL1", "computeScroll: "+mScroller.getStartX()+"="+ mScroller.getStartY());
            ////]Log.w("OKL0", "computeScroll: "+mScroller.getCurrX()+"="+ mScroller.getCurrY());
            ////]Log.w("OKL1", "computeScroll: "+mScroller.getFinalX()+"="+ mScroller.getFinalY());
            //scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        ////]Log.w(this.toString(), "DOKDonTouchEventD1: "+"="+event.getAction() );
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }

        return super.dispatchTouchEvent(event);
    }
    public class EconCalendarData{
        public int intMode;
        //FXDataPool.FXCalendar.CalenderData calenderData;
        CalendarDataClass calendarDataClass;
        //Jin10CalendarCollectTool.CalendarJinDH calendarJinDH;
        public String[] strings;
        //public String[] stringsB;
        public String stringName;
        public String stringTime;
        public String stringActual;
        public String stringPrevious;
        public String stringForecast;
        public int intStar;
        public boolean boolAc;
        public float viewH;
        public EconCalendarData(CalendarDataClass calendarDataClass_A){
            if(calendarDataClass_A.intMode==1)
            {
                intMode=1;
                //calendarJinDH=calendarJinDH_A;
                calendarDataClass=calendarDataClass_A;
                stringName=calendarDataClass_A.stringName;
                strings= TextStringTool.getTextAdvancesB(stringName,paintTextName,width-600);
                intStar=calendarDataClass_A.intStar;
                ////]Log.w(this.toString(), "EconCalendarData: R0"+"="+(calendarJinDH.stringActual!=null)+"="+(calendarJinDH.stringPrevious!=null) );
                if(calendarDataClass_A.stringActual!=null&&calendarDataClass_A.stringPrevious!=null)
                {
                    //]Log.w(this.toString(), "EconCalendarData: HA0"+"="+ calendarDataClass_A.stringActual +"="+calendarDataClass_A.stringPrevious);
                    if(Float.valueOf(calendarDataClass_A.stringActual)>Float.valueOf(calendarDataClass_A.stringPrevious))
                    {
                        boolAc=true;
                    }else {
                        boolAc=false;
                    }
                }


                stringActual=calendarDataClass_A.stringActual;
                if(stringActual==null)stringActual="";
                stringPrevious=calendarDataClass_A.stringPrevious;
                if(stringPrevious==null)stringPrevious="";
                stringForecast=calendarDataClass_A.stringForecast;
                if(stringForecast==null)stringForecast="";

                //Calendar calendar_A=Calendar.getInstance();
               // calendar_A.setTimeInMillis(calendarDataClass_A.longTime);

                stringTime=(calendarDataClass_A.timeOD.intsA[3]>9?calendarDataClass_A.timeOD.intsA[3]:"0"+calendarDataClass_A.timeOD.intsA[3])+":"+(calendarDataClass_A.timeOD.intsA[4]>9?calendarDataClass_A.timeOD.intsA[4]:"0"+calendarDataClass_A.timeOD.intsA[4]);

            }else {
                intMode=2;
                intStar=calendarDataClass_A.intStar;
                strings= TextStringTool.getTextAdvancesB(calendarDataClass_A.stringName,paintTextName,width-300);
                //strings=new String[]{strings[0]};
                stringTime=calendarDataClass_A.stringTime;//(calendarDataClass_A.timeOD.intsA[3]>9?calendarDataClass_A.timeOD.intsA[3]:"0"+calendarDataClass_A.timeOD.intsA[3])+":"+(calendarDataClass_A.timeOD.intsA[4]>9?calendarDataClass_A.timeOD.intsA[4]:"0"+calendarDataClass_A.timeOD.intsA[4]);
                stringActual=calendarDataClass_A.stringFlag;
                stringPrevious=calendarDataClass_A.stringActual;
            }
            ccNewsViewH();
        }
//        public EconCalendarData(FXDataPool.FXCalendar.CalenderData calenderData_A){
//            intMode=1;
//            //calenderData=calenderData_A;
//            //]Log.w(this.toString(), "EconCalendarData: E0"+"="+calenderData.stringName +"="+width);
//            strings= TextStringTool.getTextAdvancesB(calenderData.stringName,paintTextName,width-600);
//            ccNewsViewH();
//        }
//        public EconCalendarData(FXDataPool.FXCalendar.CalenderData calenderData_A,int int_A){
//            //]Log.w(this.toString(), "HJI.EconCalendarData: R0"+"="+ int_A);
//            if(int_A==1)
//                {
//                    intMode=1;
//                    //calendarJinDH=calendarJinDH_A;
//                    calenderData=calenderData_A;
//                    stringName=calenderData_A.stringFlag+calenderData_A.stringTimePeriod+calenderData_A.stringName;
//                    strings= TextStringTool.getTextAdvancesB(stringName,paintTextName,width-600);
//                    intStar=calenderData_A.intStar;
//                    ////]Log.w(this.toString(), "EconCalendarData: R0"+"="+(calendarJinDH.stringActual!=null)+"="+(calendarJinDH.stringPrevious!=null) );
//                    if(calenderData_A.stringActual!=null&&calenderData_A.stringPrevious!=null)
//                    {
//                        //]Log.w(this.toString(), "EconCalendarData: HA0"+"="+ calenderData_A.stringActual +"="+calenderData_A.stringPrevious);
//                        if(Float.valueOf(calenderData_A.stringActual)>Float.valueOf(calenderData_A.stringPrevious))
//                        {
//                            boolAc=true;
//                        }else {
//                            boolAc=false;
//                        }
//                    }
//
//
//                    stringActual=calenderData_A.stringActual;
//                    if(stringActual==null)stringActual="";
//                    stringPrevious=calenderData_A.stringPrevious;
//                    if(stringPrevious==null)stringPrevious="";
//                    stringForecast=calenderData_A.stringForecast;
//                    if(stringForecast==null)stringForecast="";
//
//                    Calendar calendar_A=Calendar.getInstance();
//                    calendar_A.setTimeInMillis(calenderData_A.longTime);
//
//                    stringTime=(calendar_A.get(Calendar.HOUR_OF_DAY)>9?calendar_A.get(Calendar.HOUR_OF_DAY):"0"+calendar_A.get(Calendar.HOUR_OF_DAY))+":"+(calendar_A.get(Calendar.MINUTE)>9?calendar_A.get(Calendar.MINUTE):"0"+calendar_A.get(Calendar.MINUTE));
//
//                }else {
//                intMode=2;
//                intStar=calenderData_A.intStar;
//                strings= TextStringTool.getTextAdvancesB(calenderData_A.stringName,paintTextName,width-300);
//                //strings=new String[]{strings[0]};
//                stringTime=calenderData_A.stringTime;
//                stringActual=calenderData_A.stringFlag;
//                stringPrevious=calenderData_A.stringActual;
//            }
//            ccNewsViewH();
//        }

        public EconCalendarData(Jin10CalendarCollectTool.CalendarJinDH calendarJinDH_A){
//            intMode=1;
//            calendarJinDH=calendarJinDH_A;
//            //]Log.w(this.toString(), "EconCalendarData: E0"+"="+calendarJinDH.stringName +"="+width);
//            String string_N=calendarJinDH.stringFlag+calendarJinDH.stringTimePeriod+calendarJinDH.stringName;
//            strings= TextStringTool.getTextAdvancesB(string_N,paintTextName,width-600);
//            intStar=calendarJinDH.intStar;
//            //]Log.w(this.toString(), "EconCalendarData: R0"+"="+(calendarJinDH.stringActual!=null)+"="+(calendarJinDH.stringPrevious!=null) );
//            if(calendarJinDH.stringActual!=null&&calendarJinDH.stringPrevious!=null)
//                {
//                    if(Float.valueOf(calendarJinDH.stringActual)>Float.valueOf(calendarJinDH.stringPrevious))
//                    {
//                        boolAc=true;
//                    }else {
//                        boolAc=false;
//                    }
//                }
//
//
//            stringActual=calendarJinDH.stringActual;
//            if(stringActual==null)stringActual="";
//            stringPrevious=calendarJinDH.stringPrevious;
//            if(stringPrevious==null)stringPrevious="";
//            stringForecast=calendarJinDH.stringForecast;
//            if(stringForecast==null)stringForecast="";
//
//            Calendar calendar_A=Calendar.getInstance();
//            calendar_A.setTimeInMillis(calendarJinDH.longTime);
//
//            stringTime=(calendar_A.get(Calendar.HOUR_OF_DAY)>9?calendar_A.get(Calendar.HOUR_OF_DAY):"0"+calendar_A.get(Calendar.HOUR_OF_DAY))+":"+(calendar_A.get(Calendar.MINUTE)>9?calendar_A.get(Calendar.MINUTE):"0"+calendar_A.get(Calendar.MINUTE));
//            ccNewsViewH();
        }
        public EconCalendarData(int int_A,Jin10CalendarCollectTool.CalendarJinEV calendarJinEV_A){
            intMode=int_A;
            intStar=calendarJinEV_A.intStar;
            strings= TextStringTool.getTextAdvancesB(calendarJinEV_A.stringEvent,paintTextName,width-300);
            //strings=new String[]{strings[0]};
            stringTime=calendarJinEV_A.stringTime;
            stringActual=calendarJinEV_A.stringFlag;
            stringPrevious=calendarJinEV_A.stringCity;
            ccNewsViewH();
        }
        public EconCalendarData(){
            intMode=0;
            strings=new String[]{"时间","货币","重要性","活动","今值","预测值","前值"};
            viewH=70;
        }
        public EconCalendarData(int int_A){
            intMode=3;
            strings=new String[]{"时间","国家","重要性","事件"};
            viewH=70;
        }
        public void ccNewsViewH(){
            if(strings!=null)
            {
                viewH+=25+(30*strings.length);
            }
        }


    }
}
