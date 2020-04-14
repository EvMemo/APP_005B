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

import com.example.app_005.Internet.Jin10CalendarCollectTool;
import com.example.app_005.Internet.XMCalendarCollertTool;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.TextStringTool;
import com.example.app_005.Tool.TimeOD;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class OpenCalendarDataView extends View {
    private int width;//设置高
    private int height;//设置高
    CalendarDrawData calendarDrawData;
    boolean boolDataOk;
    Paint paintUI;
    Paint paintP;
    Paint paintTitle;
    Paint paintText;
    Paint paintTextHis;
    Paint paintTextMore;
    Paint paintLine;

    Paint paintLineY;
    Paint paintLineBP;
    XMCalendarCollertTool xmCalendarCollertTool;
    Jin10CalendarCollectTool jin10CalendarCollectTool;
    public OpenCalendarDataView(Context context) {
        super(context);
    }

    public OpenCalendarDataView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(getContext(), null, true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mMaxFlintVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMinFlintVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMinFlintVelocity = 600;

        xmCalendarCollertTool=new XMCalendarCollertTool();
        paintUI=new Paint();
        paintUI.setAntiAlias(true);//消除锯齿
        paintUI.setColor(Color.parseColor("#FFDAD7D7"));//设置画笔颜色//#83DAD7D7
        paintUI.setStyle(Paint.Style.FILL);
        paintUI.setStrokeWidth(10);//设置宽度

        paintP=new Paint();
        paintP.setColor(Color.WHITE);//设置画笔颜色//#83DAD7D7
        paintP.setStyle(Paint.Style.FILL);
        paintP.setStrokeWidth(10);//设置宽度

        paintTitle = new Paint();
        paintTitle.setAntiAlias(true);//消除锯齿
        paintTitle.setColor(Color.BLACK);
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintTitle.setTextAlign(Paint.Align.CENTER);
        paintTitle.setTextSize(45);

        paintText = new Paint();
        paintText.setAntiAlias(true);//消除锯齿
        paintText.setColor(Color.BLACK);
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintText.setTextAlign(Paint.Align.LEFT);
        paintText.setTextSize(30);


        paintTextHis = new Paint();
        paintTextHis.setAntiAlias(true);//消除锯齿
        paintTextHis.setColor(Color.BLACK);
        paintTextHis.setTextAlign(Paint.Align.CENTER);
        paintTextHis.setTextSize(18);

        paintTextMore = new Paint();
        paintTextMore.setAntiAlias(true);//消除锯齿
        paintTextMore.setColor(Color.BLACK);
        paintTextMore.setTextAlign(Paint.Align.LEFT);
        paintTextMore.setTextSize(25);

        paintLine=new Paint();
        paintLine.setAntiAlias(true);//消除锯齿
        paintLine.setColor(Color.BLACK);//设置画笔颜色//#83DAD7D7
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setTextAlign(Paint.Align.CENTER);
        paintLine.setStrokeWidth(2);//设置宽度
        paintLine.setTextSize(15);

        paintLineY=new Paint();
       // paintLineY.setColor(Color.BLACK);//设置画笔颜色//#83DAD7D7//#008080
        paintLineY.setColor(Color.parseColor("#008080"));//设置画笔颜色//#83DAD7D7
        paintLineY.setTextAlign(Paint.Align.CENTER);
        paintLine.setTextSize(15);

        paintLineY.setStyle(Paint.Style.FILL);
        paintLineY.setStrokeWidth(2);//设置宽度
        paintLineY.setTextSize(15);


        paintLineBP=new Paint();
        paintLineBP.setColor(Color.BLACK);//设置画笔颜色//#83DAD7D7
        paintLineBP.setStyle(Paint.Style.FILL);
        paintLineBP.setStrokeWidth(2);//设置宽度

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);//设置宽和高
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(true)
            {
                canvas.drawRect(0,0,width,height,paintUI);
               // canvas.drawRoundRect(30,100,width-27,height-27,20,20,paintLineBP);
                canvas.drawRoundRect(30,100,width-30,height-30,20,20,paintP);
            if(boolDataOk)
                {
                    canvas.save();
                    canvas.clipRect(40,100,width-40,height-30);
                    float float_W=110-intViewH;
                    float float_R=50+(width-150)/2F;
                    for(int i=0;i<calendarDrawData.stringNameX.length;i++)
                        {
                            float_W+=(60);
                        canvas.drawText(calendarDrawData.stringNameX[i],float_R,float_W,paintTitle);
                        }
                    float_W+=30;
                    canvas.drawLine(50,float_W,width-100,float_W,paintLine);
                    float_W+=80;
                    canvas.drawRoundRect(float_R-140,float_W-60,float_R+140,float_W+25,20,20,paintLine);
                    canvas.drawRoundRect((float_R-320)-140,float_W-60,(float_R-320)+140,float_W+25,20,20,paintLine);
                    canvas.drawRoundRect((float_R+320)-140,float_W-60,(float_R+320)+140,float_W+25,20,20,paintLine);
                    canvas.drawText(calendarDrawData.stringData0,float_R-320,float_W,paintTitle);
                    canvas.drawText(calendarDrawData.stringData1,float_R,float_W,paintTitle);
                    canvas.drawText(calendarDrawData.stringData2,float_R+320,float_W,paintTitle);

                    float_W+=100;
                    canvas.drawText(calendarDrawData.econCalendarData.stringTime,300,float_W,paintTitle);
                    canvas.drawText(calendarDrawData.econCalendarData.intStar+"-Star",width-300,float_W,paintTitle);
                    float_W+=50;

                    if(calendarDrawData.boolHisOK)
                        {
                            //]Log.w(this.toString(), "CalendarJin10FFe: R0 "+"="+ calendarDrawData.calendarJinText.boolForecast +"="+ calendarDrawData.calendarJinText.floatMax +"="+calendarDrawData.calendarJinText.floatMin);
                            canvas.drawRoundRect(50,float_W,width-100,float_W+550,10,10,paintLine);
                            float float_T=(width-150)/(calendarDrawData.calendarJinText.calendarJinHistories.length+1F);
                            float float_E=350;
                            if(true)
                            {
                                if(calendarDrawData.calendarJinText.boolForecast)
                                {
                                    for(int i=0;i<calendarDrawData.calendarJinText.calendarJinHistories.length;i++)
                                    {
                                        //canvas.drawCircle(50+float_T*(i+1),float_W+20+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisP),20,paintLineY);
                                        canvas.drawRect(50+float_T*(i+1)-30,float_W+450,50+float_T*(i+1)+30,float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisP),paintLineY);
                                        canvas.drawText(calendarDrawData.calendarDrawHistories[i].stringHisP,50+float_T*(i+1),float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisP)-18,paintLineY);

                                    }

                                }
                            }

                            for(int i=0;i<calendarDrawData.calendarJinText.calendarJinHistories.length;i++)
                                {
                                    if(i!=0)
                                        {
                                            canvas.drawLine(50+float_T*(i),float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i-1].floatHisA),50+float_T*(i+1),float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisA),paintLine);

                                        }
                                   // canvas.drawLine(50+float_T*(i+1),float_W+520,50+float_T*(i+1),float_W+480,paintLine);
                                    canvas.drawRoundRect(50+float_T*(i+1)-5,float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisA)-5,50+float_T*(i+1)+5,float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisA)+5,5,5,paintLineBP);
                                    canvas.drawText(calendarDrawData.calendarDrawHistories[i].stringHisA,50+float_T*(i+1),float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisA)-18,paintLine);
                                    //canvas.drawText(calendarDrawData.calendarDrawHistories[i].stringTime,50+float_T*(i+1),float_W+500,paintText);

                                }

                            canvas.save();
                            for(int i=0;i<calendarDrawData.calendarJinText.calendarJinHistories.length;i++)
                            {
                                canvas.rotate(30, 50+float_T*(i+1), float_W+500);
                                canvas.drawText(calendarDrawData.calendarDrawHistories[i].stringTime,50+float_T*(i+1),float_W+500,paintTextHis);
                                canvas.rotate(-30, 50+float_T*(i+1), float_W+500);
                            }

                            canvas.restore();

                            if(calendarDrawData.stringsTextMore!=null)
                                {
                                    float_W+=600;
                                    for(int i=0;i<calendarDrawData.stringsTextMore.length;i++)
                                        {
                                            float float_Q=float_W;
                                            float_W+=30;
                                        for(int e=0;e<calendarDrawData.stringsTextMore[i].length;e++)
                                            {
                                                canvas.drawText(calendarDrawData.stringsTextMore[i][e],80,float_W,paintTextMore);
                                                float_W+=35;
                                            }
                                        if(calendarDrawData.stringsTextMore[i].length>0)
                                            {
                                                canvas.drawRoundRect(70,float_Q-5,width-110,float_W-15,15,15,paintLine);
                                            }
                                            float_W+=20;
                                        }

                                }
                        }

                    canvas.restore();
                }
            }else {
            if(boolDataOk)
            {


            }
        }

    }
    void ccViewH(){
        if(boolDataOk)
        {
            float float_W=110;
            float float_R=50+(width-150)/2F;
            for(int i=0;i<calendarDrawData.stringNameX.length;i++)
            {
                float_W+=(60);
               // canvas.drawText(calendarDrawData.stringNameX[i],float_R,float_W,paintTitle);
            }
            float_W+=30;
            //canvas.drawLine(50,float_W,width-100,float_W,paintLine);
            float_W+=80;
            //canvas.drawRoundRect(float_R-140,float_W-60,float_R+140,float_W+25,20,20,paintLine);
            //canvas.drawRoundRect((float_R-320)-140,float_W-60,(float_R-320)+140,float_W+25,20,20,paintLine);
           // canvas.drawRoundRect((float_R+320)-140,float_W-60,(float_R+320)+140,float_W+25,20,20,paintLine);
           // canvas.drawText(calendarDrawData.stringActual,float_R-320,float_W,paintTitle);
           // canvas.drawText(calendarDrawData.stringForecast,float_R,float_W,paintTitle);
            //canvas.drawText(calendarDrawData.stringPrevious,float_R+320,float_W,paintTitle);
//
            float_W+=100;
           // canvas.drawText(calendarDrawData.econCalendarData.stringTime,300,float_W,paintTitle);
           // canvas.drawText(calendarDrawData.econCalendarData.calendarJinDH.intStar+"-Star",width-300,float_W,paintTitle);
            float_W+=50;

            if(calendarDrawData.boolHisOK)
            {
               // //]Log.w(this.toString(), "CalendarJin10FFe: R0 "+"="+ calendarDrawData.calendarJinText.boolForecast +"="+ calendarDrawData.calendarJinText.floatMax +"="+calendarDrawData.calendarJinText.floatMin);
                //canvas.drawRoundRect(50,float_W,width-100,float_W+550,10,10,paintLine);
                float float_T=(width-150)/(calendarDrawData.calendarJinText.calendarJinHistories.length+1F);
                float float_E=350;
                if(true)
                {
                    if(calendarDrawData.calendarJinText.boolForecast)
                    {
                        for(int i=0;i<calendarDrawData.calendarJinText.calendarJinHistories.length;i++)
                        {
                            //canvas.drawCircle(50+float_T*(i+1),float_W+20+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisP),20,paintLineY);
                          //  canvas.drawRect(50+float_T*(i+1)-30,float_W+450,50+float_T*(i+1)+30,float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisP),paintLineY);
                         //   canvas.drawText(calendarDrawData.calendarDrawHistories[i].stringHisP,50+float_T*(i+1),float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisP)-18,paintLineY);

                        }

                    }
                }

                for(int i=0;i<calendarDrawData.calendarJinText.calendarJinHistories.length;i++)
                {
                    if(i!=0)
                    {
                       // canvas.drawLine(50+float_T*(i),float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i-1].floatHisA),50+float_T*(i+1),float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisA),paintLine);

                    }
                    // canvas.drawLine(50+float_T*(i+1),float_W+520,50+float_T*(i+1),float_W+480,paintLine);
                    //canvas.drawRoundRect(50+float_T*(i+1)-5,float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisA)-5,50+float_T*(i+1)+5,float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisA)+5,5,5,paintLineBP);
                   // canvas.drawText(calendarDrawData.calendarDrawHistories[i].stringHisA,50+float_T*(i+1),float_W+70+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisA)-18,paintLine);
                    //canvas.drawText(calendarDrawData.calendarDrawHistories[i].stringTime,50+float_T*(i+1),float_W+500,paintText);

                }

                //canvas.save();
                for(int i=0;i<calendarDrawData.calendarJinText.calendarJinHistories.length;i++)
                {
                  //  canvas.rotate(30, 50+float_T*(i+1), float_W+500);
                  //  canvas.drawText(calendarDrawData.calendarDrawHistories[i].stringTime,50+float_T*(i+1),float_W+500,paintTextHis);
                  //  canvas.rotate(-30, 50+float_T*(i+1), float_W+500);
                }

               // c//anvas.restore();

                if(calendarDrawData.stringsTextMore!=null)
                {
                    float_W+=600;
                    for(int i=0;i<calendarDrawData.stringsTextMore.length;i++)
                    {
                        float float_Q=float_W;
                        float_W+=30;
                        for(int e=0;e<calendarDrawData.stringsTextMore[i].length;e++)
                        {
                           // canvas.drawText(calendarDrawData.stringsTextMore[i][e],80,float_W,paintTextMore);
                            float_W+=35;
                        }
                        if(calendarDrawData.stringsTextMore[i].length>0)
                        {
                            //canvas.drawRoundRect(70,float_Q-5,width-110,float_W-15,15,15,paintLine);
                        }
                        float_W+=20;
                    }

                }
            }

            intViewHLast=float_W-(height-60);

            //]Log.w(this.toString(), "ccViewH: R1"+"="+intViewHLast +"="+(height-60) );
            if(intViewHLast<0)
                {
                    intViewHLast=-1;
                }
        }else {
            intViewHLast=-1;
        }
    }
    public void setXMCalendarCollertTool(XMCalendarCollertTool xmCalendarCollertTool_A){
        xmCalendarCollertTool=xmCalendarCollertTool_A;
    }
    public void setJin10CalendarCollectTool(Jin10CalendarCollectTool jin10CalendarCollectTool_A){
        jin10CalendarCollectTool=jin10CalendarCollectTool_A;
    }

    public void setCalendarData(FXDataPool.FXCalendar.CalenderData calenderData_A){
        calendarDrawData=new CalendarDrawData(calenderData_A);
        boolDataOk=true;
        invalidate();
    }
    public void setCalendarData(EconomicCalendarView.EconCalendarData econCalendarData_A){
        calendarDrawData=new CalendarDrawData(econCalendarData_A);
        boolDataOk=true;
        invalidate();
    }
    void moveView(float float_A){
        if(intViewHLast==-1)
            {
                intViewH=0;
                invalidate();
            }else {
            intViewH-=float_A;
            //]Log.w(this.toString(), "DFGmoveView: R0"+"="+intViewH );
            if(intViewH<0)
                {
                    intViewH=0;
                }else
            if(intViewH>intViewHLast)
                {
                    intViewH=intViewHLast;
                }
            invalidate();
        }

    }
    public float intViewH,intViewHLast;
    float floatTouchDownY;
    private VelocityTracker velocityTracker;
    private Scroller mScroller;
    private int mMaxFlintVelocity, mMinFlintVelocity;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //]Log.w(this.toString(), "DOKDonTouchEventD0: "+"="+event.getAction() );
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float float_A=event.getY()-floatTouchDownY;
                floatTouchDownY=event.getY();
                moveView(float_A);


                return true;
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                floatTouchDownY=event.getY();

                return true;
            case MotionEvent.ACTION_UP:

                    velocityTracker.computeCurrentVelocity(1000, mMaxFlintVelocity);
                    int xVelocity = (int) velocityTracker.getXVelocity();
                    int yVelocity = (int) velocityTracker.getYVelocity();
                    boolean bool_A=(Math.abs(xVelocity) > mMinFlintVelocity|| Math.abs(yVelocity) > mMinFlintVelocity);
                    if (bool_A)
                    {
                        mScroller.fling(0,(int) floatTouchDownY, xVelocity, yVelocity, 0, 0, -99999, 99999);
                        invalidate();
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
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            //]Log.w(this.toString(), "KNDLOcomputeScrollS0: "+"="+ mScroller.getCurrY());

            moveView(mScroller.getCurrY()-floatTouchDownY);
            floatTouchDownY=mScroller.getCurrY();
            //Log.i("OKL1", "computeScroll: "+mScroller.getStartX()+"="+ mScroller.getStartY());
            //Log.i("OKL0", "computeScroll: "+mScroller.getCurrX()+"="+ mScroller.getCurrY());
            //Log.i("OKL1", "computeScroll: "+mScroller.getFinalX()+"="+ mScroller.getFinalY());
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
    public class CalendarDrawData{
        public FXDataPool.FXCalendar.CalenderData calenderData;
        EconomicCalendarView.EconCalendarData econCalendarData;
        public String stringName;
        public String[] stringNameX;
        public String stringText;
        public TimeOD timeOD;
        public String stringData0;
        public String stringData1;
        public String stringData2;
        public String[][] stringsTextMore;
        public int intImportance;
        public boolean boolAc;
        public CalendarDrawHistory[] calendarDrawHistories;
        Jin10CalendarCollectTool.CalendarJinText calendarJinText;
        boolean boolHisOK;

        public CalendarDrawData(FXDataPool.FXCalendar.CalenderData calenderData_A){
            calenderData=calenderData_A;
            stringName=calenderData.stringName;
            stringData0=calenderData.stringActual;
            stringData1=calenderData.stringForecast;
            stringData2=calenderData.stringPrevious;
            intImportance=calenderData.intStar;
            calendarDrawHistories=new CalendarDrawHistory[0];
            ccViewH();
            getCalendarDrawHistory();
        }
        public CalendarDrawData(EconomicCalendarView.EconCalendarData econCalendarData_A){
            synchronized ("EconCalendarData"){
                econCalendarData=econCalendarData_A;
            }

            stringName=econCalendarData.stringName;//.calendarJinDH.stringFlag+econCalendarData.calendarJinDH.stringTimePeriod+econCalendarData.calendarJinDH.stringName;
            stringNameX= TextStringTool.getTextAdvancesB(stringName,paintTitle,width-180);
            stringData0=econCalendarData.stringActual;//.stringActual;
            stringData1=econCalendarData.stringForecast;
            stringData2=econCalendarData.stringPrevious;
            this.boolAc=econCalendarData.boolAc;
            calendarDrawHistories=new CalendarDrawHistory[0];
            getCalendarDrawHistory();
        }
        void ccStringsTextMore(){
            if(calendarJinText!=null)
                {
                    ArrayList<String[]> strings_A=new ArrayList<>();
                    if(calendarJinText.stringImpact!=null) strings_A.add(TextStringTool.getTextAdvancesB(calendarJinText.stringImpact,paintTextMore,width-200));
                    if(calendarJinText.stringMethod!=null) strings_A.add(TextStringTool.getTextAdvancesB(calendarJinText.stringMethod,paintTextMore,width-200));
                    if(calendarJinText.stringParaphrase!=null) strings_A.add(TextStringTool.getTextAdvancesB(calendarJinText.stringParaphrase,paintTextMore,width-200));
                    if(calendarJinText.stringConcern!=null) strings_A.add(TextStringTool.getTextAdvancesB(calendarJinText.stringConcern,paintTextMore,width-200));
                    if(strings_A.size()>0)
                        {
                            stringsTextMore=strings_A.toArray(new String[strings_A.size()][]);
                        }

                }

        }
        void getCalendarDrawHistory(){
            boolHisOK=false;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(true)
                        {
                            EconomicCalendarView.EconCalendarData econCalendarData_A=null;
                            synchronized ("EconCalendarData"){
                                econCalendarData_A=econCalendarData;
                            }
                            if(econCalendarData_A!=null)
                                {
                                    try {
                                        //]Log.w(this.toString(), "getCalendarDrawHistory: RA" );
                                        Jin10CalendarCollectTool.CalendarJinText calendarJinText_A =jin10CalendarCollectTool.getTextCalendar(String.valueOf(econCalendarData_A.calendarDataClass.intID));
                                        //]Log.w(this.toString(), "getCalendarDrawHistory: R0"+"="+(calendarJinText_A!=null) );

                                        if(calendarJinText_A!=null)
                                        {

                                            //]Log.w(this.toString(), "getCalendarDrawHistory: R1"+"="+(calendarJinText_A.calendarJinHistories.length) );
                                            EconomicCalendarView.EconCalendarData econCalendarData_B=null;
                                            synchronized ("EconCalendarData"){
                                                econCalendarData_B=econCalendarData;
                                            }
                                            if(econCalendarData_B!=null&&!boolHisOK)
                                                {
                                                    //]Log.w(this.toString(), "getCalendarDrawHistory: R2 "+"="+(econCalendarData_A.calendarDataClass.intID==econCalendarData_B.calendarDataClass.intID) );
                                                    if(econCalendarData_A.calendarDataClass.intID==econCalendarData_B.calendarDataClass.intID)
                                                        {
                                                            calendarJinText=calendarJinText_A;
                                                            ccCalendarJinHistoryDraw();
                                                            ccStringsTextMore();

                                                            boolHisOK=true;
                                                            ccViewH();
                                                            invalidate();

                                                        }

                                                }


                                        }
                                    } catch (JSONException e) {
                                        //]Log.w(this.toString(), "getCalendarDrawHistory: E0"+"="+e.toString() );
                                        e.printStackTrace();
                                    } catch (InterruptedException e) {
                                        //]Log.w(this.toString(), "getCalendarDrawHistory: E1"+"="+e.toString() );
                                        e.printStackTrace();
                                    }
                                }
                        }else{

                    FXDataPool.FXCalendar.CalenderData calenderData_A=calenderData;
                    if(calenderData_A!=null)
                        {
                            XMCalendarCollertTool.CalendarDetailH calendarDetailH_A =xmCalendarCollertTool.getXMCalendarTextData(calenderData.stringID,calenderData.stringAttr);
                            //]Log.w(this.toString(), "getCalendarDrawHistory: E0"+"="+(calendarDetailH_A!=null) );
                            if(calendarDetailH_A==null)
                                {
                                for(int i=0;i<20;i++)
                                    {
                                        calendarDetailH_A =xmCalendarCollertTool.getXMCalendarTextData(calenderData.stringID,calenderData.stringAttr);
                                        if(calendarDetailH_A!=null)
                                        {
                                            break;

                                        }
                                    }
                                }
                            if(calendarDetailH_A!=null)
                                {
                                    stringText=calendarDetailH_A.stringText;
                                    JSONArray jsonArray_A=calendarDetailH_A.jsonArray;
                                    //]Log.w(this.toString(), "getCalendarDrawHistory: E1"+"="+ (jsonArray_A!=null));
                                    if(jsonArray_A!=null)
                                        {
                                            //jsonArray_A=jsonArray_A.
                                            calendarDrawHistories=new CalendarDrawHistory[10];
                                            //]Log.w(this.toString(), "getCalendarDrawHistory: E1"+"="+ jsonArray_A.length());
                                            for(int i=0;i<10;i++)
                                            {

                                               // calendarDrawHistories[i]=new CalendarDrawHistory(jsonArray_A.getJSONArray(jsonArray_A.length()-i-1));

                                            }

                                        }

                                }
                        }

                }}
            }).start();

        }
        public void ccCalendarJinHistoryDraw(){
            if(calendarJinText!=null)
                {
                    calendarDrawHistories=new CalendarDrawHistory[calendarJinText.calendarJinHistories.length];
                    float float_A=calendarJinText.floatMax-calendarJinText.floatMin;
                    //]Log.w(this.toString(), "ccCalendarJinHistoryDraw: R0"+"="+float_A );
                    float float_B=0;
                    float float_C=0;
                    float float_C2=0;
                    for(int i=0;i<calendarJinText.calendarJinHistories.length;i++)
                        {
                            float_B=calendarJinText.floatMax-calendarJinText.calendarJinHistories[i].floatActual;
                            float_C=float_B/float_A;
                            //]Log.w(this.toString(), "ccCalendarJinHistoryDraw: R1"+"="+i +"="+ float_B +"="+ float_C);
                            String string_O=null;
                            if(calendarJinText.boolForecast)
                                {
                                    float_B=calendarJinText.floatMax-calendarJinText.calendarJinHistories[i].floatForecast;
                                    float_C2=float_B/float_A;

                                    string_O=String.valueOf(calendarJinText.calendarJinHistories[i].floatForecast);
                                }
                            calendarDrawHistories[i]=new CalendarDrawHistory(calendarJinText.calendarJinHistories[i].stringTime,float_C,float_C2,String.valueOf(calendarJinText.calendarJinHistories[i].floatActual),string_O);
                        }

                }
        }


        public class CalendarDrawHistory{
            public float floatHisA;
            public float floatHisP;
            public String stringTime;
            public String stringHisA;
            public String stringHisP;
            public CalendarDrawHistory(String stringTime_A,float floatHisA_A,float floatHisP_A,String stringHisA_A,String stringHisP_A){
                stringTime=stringTime_A;
                floatHisA=floatHisA_A;
                floatHisP=floatHisP_A;
                stringHisA=stringHisA_A;
                stringHisP=stringHisP_A;

            }

        }

    }
}
