package com.example.app_005.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.example.app_005.Class.CalendarDataClass;
import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Interface.OnReadNews;
import com.example.app_005.Interface.OnUpdateCalendarData;
import com.example.app_005.Interface.OnUpdateNewsData;
import com.example.app_005.MainActivity;
import com.example.app_005.MainApplication;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.ReadModeTool;
import com.example.app_005.Tool.TextStringTool;
import com.example.app_005.Tool.TimeOD;

import java.util.ArrayList;
import java.util.Arrays;

public class IntegratedNewsView extends View implements OnUpdateNewsData, OnUpdateCalendarData {

    private int width;//设置高
    private int height;//设置高

    Paint paintUIMore;
    Paint paintUIMoreB;
    Paint paintTitle;
    Paint paintText;
    Paint paintTime;
    Paint paintTimeB;
    Paint paintCalenDat;
//    ArrayList<NewsDataClass> DataClasses;
//    ArrayList<IntegratedDrawNews> integratedDrawData;
//    ArrayList<NewsDataClass> DataClassesUpdate;
    Handler handlerUI;
//    boolean boolDrawOK;
//    boolean boolViewOK;
    MainActivity mainActivity;
    IntegratedNewsVD integratedNewsVD;
    IntegratedCalendarVD integratedCalendarVD;
    int intDrawM;


    public IntegratedNewsView(Context context) {
        super(context);
    }

    public IntegratedNewsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintUIMore=new Paint();
        paintUIMore.setColor(Color.WHITE);//FF5E5E5E


        paintUIMoreB=new Paint();
        paintUIMoreB.setColor(Color.BLACK);//FF5E5E5E
        paintUIMoreB.setAlpha(10);


        //绘制文字的画笔
        paintTitle = new Paint();
        paintTitle.setAntiAlias(true);//消除锯齿
        paintTitle.setColor(Color.BLACK);
        paintTitle.setTextAlign(Paint.Align.LEFT);
        paintTitle.setTextSize(40);

        paintText = new Paint();
        paintText.setAntiAlias(true);//消除锯齿
        paintText.setColor(Color.BLACK);
        paintText.setTextAlign(Paint.Align.LEFT);
        paintText.setTextSize(20);

        paintTime = new Paint();
        paintTime.setAntiAlias(true);//消除锯齿
        paintTime.setColor(Color.GRAY);
        paintTime.setTextAlign(Paint.Align.RIGHT);
        paintTime.setTextSize(22);


        paintTimeB = new Paint();
        paintTimeB.setAntiAlias(true);//消除锯齿
        paintTimeB.setColor(Color.GRAY);
        paintTimeB.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        paintTimeB.setTextAlign(Paint.Align.RIGHT);
        paintTimeB.setTextSize(36);

        paintCalenDat = new Paint();
        paintCalenDat.setAntiAlias(true);//消除锯齿
        paintCalenDat.setColor(Color.BLACK);
        paintCalenDat.setTextAlign(Paint.Align.CENTER);
        paintCalenDat.setTextSize(30);

        integratedNewsVD=new IntegratedNewsVD();
        integratedCalendarVD=new IntegratedCalendarVD();

        //DataClasses=new ArrayList<>();
        //DataClassesUpdate=new ArrayList<>();
        handlerUI = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0://更换临时数据
                        //]Log.w(this.toString(), "IntegratedNewsView: T1");
                        getUpdateNewsData();
                        break;
                        case 1://更换临时数据
                        //]Log.w(this.toString(), "IntegratedNewsView: T2");
                        getUpdateCalendarData();
                        break;
                    default:
                        break;
                }
            }
        };
        initScroller();
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch(intDrawM){
            case 1:
                integratedNewsVD.Draw(canvas);
                break;
            case 2:
                integratedCalendarVD.Draw(canvas);
                break;
            default:
                break;
        }

    }
    public void setIntDrawM(int int_A){
        intDrawM=int_A;
        //]Log.w(this.toString(), "setIntDrawM: E0"+"="+int_A );
        invalidate();
    }
    public void startViewOK(){
        //]Log.w(this.toString(), "startViewOK: R0" );
        integratedNewsVD.boolViewOK=true;
        integratedCalendarVD.boolViewOK=true;
        intDataNum=0;
        handlerUI.sendEmptyMessage(0);
//        if(integratedDrawData!=null&&integratedDrawData.size()!=0)
//            {
//                ccViewHLast();
//            }
    }
    public void closeIntegrated(){
        integratedNewsVD.updateDataNewMode();
        switch(intDrawM){
            case 0:
                break;
            default:
                break;
        }

    }
    void updateIntegratedNewNum(int intMode_A,int intNum_A){
        //]Log.w(this.toString(), "updateIntegratedNewNum: R0"+"="+ (mainActivity!=null));
        if(mainActivity!=null)
            {
                mainActivity.updateIntegratedNewNum(intMode_A,intNum_A);
            }

    }
    public void setMainActivity(MainActivity mainActivity_A){
        mainActivity=mainActivity_A;
    }
    float floatAnimDayUI;
    public void setfloatAnimDayUI(float float_A){
        integratedNewsVD.setFloatAnimDayUI(float_A);
        integratedCalendarVD.setFloatAnimDayUI(float_A);
        //floatAnimDayUI=float_A;
        invalidate();
    }
//    void ccViewHLast(){
//        integratedNewsVD.ccViewHLast();
////        if(boolViewOK)
////            {
////                boolDrawOK=true;
////                float float_H=10;
////                floatDataLastH=-1;
////                intDataLastNum=-1;
////                for(int i=integratedDrawData.size()-1;i>=0;i--)
////                {
////                    float_H+=integratedDrawData.get(i).floatViewH;
////                    if(float_H>(height-10))
////                    {
////
////                        intDataLastNum=i;
////                        floatDataLastH=float_H-(height-10);
////                        //]Log.w(this.toString(), "ccViewHLast: Y0"+"="+i +"="+float_H +"="+ height +"="+floatDataLastH);
////                        break;
////                    }
////                    //float_H+=20;
////                }
////            }
//
//    }
    float floatDataLastH;
    float floatDataNowH;
    int intDataLastNum=-1;
    int intDataNum=0;
    public void moveView(float float_Move) {
        switch(intDrawM){
            case 1:
                integratedNewsVD.moveView(float_Move);
                break;
            case 2:
                integratedCalendarVD.moveView(float_Move);
                break;
            default:
                break;
        }
//        if(boolDrawOK&&intDataLastNum!=-1)
//        {
//
//           // //]Log.w("NewsRecycleView", "LKDMLDmoveViewD0: "+"="+float_Move +"="+ intViewHRest +"="+intRecycDataNow +"="+(newsRecycDataNow!=null));
//            float float_A = floatDataNowH - float_Move;
//            float float_B = integratedDrawData.get(intDataNum).floatViewH;
//
//            if (intDataNum == intDataLastNum) {
//
//                if (float_A > (floatDataLastH)) {
//                    //intViewHRest+=(float_A-(float_B - 144));
//                    float_A = floatDataLastH;
//                    //float_A -= floatDataNowH;
//
//                }
//                if (float_A < 0) {
//                    if (intDataNum != 0) {
//                        intDataNum--;
//                        float_B = integratedDrawData.get(intDataNum).floatViewH;
//                        float_A = float_B + float_A;
//                        float_A = repsMoveView(float_A);
//                    } else {
//                        //intViewHRest+=float_A;
//                        float_A = 0;
//                    }
//
//                }
//
//            } else if (float_A < 0) {
//                if (intDataNum != 0) {
//                    intDataNum--;
//                    float_B = integratedDrawData.get(intDataNum).floatViewH;
//                    float_A = float_B + float_A;
//                    float_A = repsMoveView(float_A);
//                } else {
//                    //intViewHRest+=float_A;
//                    float_A = 0;
//                }
//
//            } else if (float_A > float_B) {
//                if (intDataNum == intDataLastNum) {
//                    if (float_A > (floatDataLastH)) {
//                        //intViewHRest+=(float_A-(float_B - 144));
//                        float_A = floatDataLastH;
//                        //float_A -=floatDataLastH;
//                    }
//                } else {
//                    intDataNum++;
//                    float_A = float_A - float_B;
//                    float_A = repsMoveView(float_A);
//                }
//
//            }
//
//
//            floatDataNowH = float_A;
//            invalidate();
//
//        }
    }
//    public float repsMoveView(float float_Move) {
////        //integratedNewsVD.repsMoveView(float_Move)
////        float float_A=float_Move;
////        float float_B=integratedDrawData.get(intDataNum).floatViewH;
////        if(float_A<0)
////        {
////            if(intDataNum!=0)
////            {
////                intDataNum--;
////                float_B=integratedDrawData.get(intDataNum).floatViewH;
////                float_A=float_B+float_A;
////                return repsMoveView(float_A);
////            }else {
////                float_A=0;
////            }
////
////        }else
////        {
////            if(intDataNum==intDataLastNum)
////                {
////                    if(float_A>floatDataLastH)
////                    {
////                        float_A=floatDataLastH;
////                    }
////                }else {
////                if(float_A>float_B)
////                {
////                    intDataNum++;
////                    float_A=float_A-float_B;
////                    return repsMoveView(float_A);
////                }
////            }
////
////
////
////        }
////        return float_A;
//    }



    @Override
    public void onUpdateNewsData(int int_Num) {

    }
    void setUpdateNewsData(NewsDataClass[] newsDataClasses_A){
       // integratedNewsVD.setNewsData(newsDataClasses_A);
       // handlerUI.sendEmptyMessage(0);
    }
    void getUpdateNewsData(){
        //integratedNewsVD.getData();
       // integratedNewsVD.ccViewHLast();
       // ccViewHLast();
       // integratedNewsVD.invalidate();
        invalidate();

    }
    void setUpdateCalendarData(CalendarDataClass[] calendarData_A){
        integratedCalendarVD.setData(calendarData_A);
        handlerUI.sendEmptyMessage(1);
    }
    void getUpdateCalendarData(){
        integratedCalendarVD.getData();
        integratedCalendarVD.ccViewHLast();
        // ccViewHLast();
        invalidate();

    }
    void touchView(MotionEvent event) {
        switch(intDrawM){
            case 1:
                integratedNewsVD.touchView(event);
                break;
            case 2:
                integratedCalendarVD.touchView(event);
                break;
            default:
                break;
        }
//        if (boolDrawOK) {
//            if(event.getX()>80&&event.getX()<width-80)
//            {
//                float float_W = 10 - floatDataNowH - (height * (1 - floatAnimDayUI));
//                float float_W2;
//                int int_A = intDataNum;
//                for (int i = int_A; i < integratedDrawData.size(); i++) {
//                    float_W2 = float_W;
//                    float_W += integratedDrawData.get(i).floatViewH - 20;
//                    if(event.getY()<(height-float_W2)&&event.getY()>height-float_W)
//                        {
//                            //]Log.w(this.toString(), "IntegratedNewsView.touchView: T0"+"="+i +"="+event.getY() +"="+(height-float_W2) +"="+(height-float_W));
//                            openIntegratedText(integratedDrawData.get(i).dataClass);
//
//                        }
//                    float_W+=20;
//
//                }
//            }
//
//        }

    }
    void openIntegratedText(NewsDataClass newsDataClass_A){
        if(mainActivity!=null)
            {
                mainActivity.openIntegratedNewsTextData(newsDataClass_A);
            }
    }
    void openIntegratedText(CalendarDataClass calenderData_A){
        if(mainActivity!=null)
        {
            mainActivity.openIntegratedCalendarTextData(calenderData_A);
        }
    }

    @Override
    public void onUpdateNewsData(NewsDataClass[] newsDataClasses_A) {
        //]Log.w(this.toString(), "IntegratedNewsView.onUpdateNewsData: R0"+"="+ newsDataClasses_A.length +"="+integratedNewsVD.boolViewOK);
        setUpdateNewsData(newsDataClasses_A);
        if(integratedNewsVD.boolViewOK)
            {
                handlerUI.sendEmptyMessage(0);
            }

    }


    float floatTouchDownY;
    long longTouchTime;
    float floatTouchX,floatTouchY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //]Log.w(this.toString(), "DOKDonTouchEventD0: "+"="+event.getAction() );
        ccScroller(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float float_A=floatTouchDownY-event.getY();
                floatTouchDownY=event.getY();
                moveView(float_A);
                return true;
            case MotionEvent.ACTION_DOWN:
                stopScroller();
                floatTouchDownY=event.getY();
                floatTouchX=event.getX();
                floatTouchY=event.getY();
                longTouchTime=System.currentTimeMillis();
                return true;
            case MotionEvent.ACTION_UP:
                if(System.currentTimeMillis()<longTouchTime+500&&(spacing(floatTouchX,floatTouchY,event.getX(),event.getY())<10))
                {
                    touchView(event);

                }else {

                    startScroller(0,floatTouchDownY);
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

    private int mMaxFlintVelocity, mMinFlintVelocity;
    private VelocityTracker velocityTracker;
    private Scroller mScroller;
    void initScroller(){

        mScroller = new Scroller(getContext(), null, true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mMaxFlintVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMinFlintVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMinFlintVelocity = 600;
    }
    void ccScroller(MotionEvent event){
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
    }
    void startScroller(float float_W,float float_H){
        velocityTracker.computeCurrentVelocity(1000, mMaxFlintVelocity);
        int xVelocity = (int) velocityTracker.getXVelocity();
        int yVelocity = (int) velocityTracker.getYVelocity();
        boolean bool_A=(Math.abs(xVelocity) > mMinFlintVelocity|| Math.abs(yVelocity) > mMinFlintVelocity);
        if (bool_A)
        {
            mScroller.fling((int)float_W,(int) float_H, xVelocity, yVelocity, 0, 0, -99999, 99999);
        }
    }
    void stopScroller(){
        if(!mScroller.isFinished()){
            mScroller.abortAnimation();
        }
    }
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {

            float float_A=floatTouchDownY-mScroller.getCurrY();
            //]Log.w(this.toString(), "IntegratedNewsView.computeScroll: "+"="+float_A );
            moveView(float_A);
            floatTouchDownY=mScroller.getCurrY();
           // invalidate();

            postInvalidate();
        }
    }

    @Override
    public void onSetCalendarData(int int_Num) {

    }

    @Override
    public void onUpdateCalendarData(CalendarDataClass[] calenderData_A) {
        //]Log.w(this.toString(), "onUpdateCalendarData: R0"+"="+calenderData_A.length );
        setUpdateCalendarData(calenderData_A);
        if(integratedCalendarVD.boolViewOK)
        {
            handlerUI.sendEmptyMessage(1);
        }

    }


    public class IntegratedNewsVD implements OnReadNews {
        float floatDataLastH;
        float floatDataNowH;
        int intDataLastNum=-1;
        int intDataNum=0;

        //ArrayList<NewsDataClass> calendarDataClasses;
        ArrayList<IntegratedDrawNews> integratedDrawData;
        //ArrayList<NewsDataClass> newsDataClassesUpdate;
        Handler handlerUI;
        boolean boolDrawOK;
        boolean boolViewOK;
        ReadModeTool readModeTool;
        ReadModeTool.ReadModeNews readModeNews;
        public IntegratedNewsVD(){

            MainApplication mainApplication_A=((MainApplication)getContext().getApplicationContext());
            readModeTool=mainApplication_A.fxDataConnect.getReadConnect().getReadModeTool();
            readModeNews=readModeTool.readModeNews;
            readModeNews.addOnReadNews(this);
           // calendarDataClasses=new ArrayList<>();
            integratedDrawData=new ArrayList<>();
           // newsDataClassesUpdate=new ArrayList<>();
        }

        @Override
        public void updateData(ReadModeTool.ReadModeNews.ReadNewsData[] readNewsData_A) {
            for(int i=0;i<readNewsData_A.length;i++)
                    {
                        integratedDrawData.add(new IntegratedDrawNews(readNewsData_A[i]));
                    }

            updateIntegratedNewNum(1,readModeNews.intNewNum);
            ccViewHLast();
        }
        //已读
        void ccViewHLast(){
            if(boolViewOK)
            {
                boolDrawOK=true;
                float float_H=10;
                floatDataLastH=-1;
                intDataLastNum=-1;
                for(int i=integratedDrawData.size()-1;i>=0;i--)
                {
                    float_H+=integratedDrawData.get(i).floatViewH;
                    if(float_H>(height-10))
                    {

                        intDataLastNum=i;
                        floatDataLastH=float_H-(height-10);
                        //]Log.w(this.toString(), "ccViewHLast: Y0"+"="+i +"="+float_H +"="+ height +"="+floatDataLastH);
                        break;
                    }
                    //float_H+=20;
                }
            }

        }
        public void updateDataNewMode(){
            readModeNews.ccReadNewData();
            //updateIntegratedNewNum(1,readModeNews.intNewNum);
        }

        public void invalidateStart(){
            invalidate();
        }
//        public void setNewsData(NewsDataClass[] newsDataClasses_A){
//            synchronized ("UpdateNewsData"){
//                if(newsDataClassesUpdate==null)
//                {
//                    newsDataClassesUpdate=new ArrayList<>();
//                }
//                newsDataClassesUpdate.addAll(Arrays.asList(newsDataClasses_A));
//            }
//        }
//
//        public void getData(){
//            ArrayList<IntegratedDrawNews> integratedDrawNews_A =new ArrayList<>();
//            synchronized ("UpdateNewsData"){
//                ////]Log.w(this.toString(), "getUpdateNewsData: R0"+"="+ DataClassesUpdate.size());
//                if(newsDataClassesUpdate.size()!=0)
//                {
//                    // DataClasses.addAll(DataClassesUpdate);
//                    for(int i=0;i<newsDataClassesUpdate.size();i++)
//                    {
//                        integratedDrawNews_A.add(new IntegratedDrawNews(newsDataClassesUpdate.get(i)));
//                    }
//                    newsDataClassesUpdate=new ArrayList<>();
//                }else {
//                    return;
//                }
//
//            }
//            if(integratedDrawData==null)
//            {
//                integratedDrawData=new ArrayList<>();
//            }
//            integratedDrawData.addAll(integratedDrawNews_A);
//        }
        public void Draw(Canvas canvas) {

            // canvas.drawRoundRect(60 - 1, 0, width - 60 + 3, height, 15, 15, paintUIMoreB);
            if (boolDrawOK) {
//            //]Log.w(this.toString(), "IntegratedNewsView: T0"+"="+intDataNum +"="+integratedDrawData.size() );
                int int_A = intDataNum;
                float float_W = 10-floatDataNowH-(height*(1-floatAnimDayUI));
                float float_W2;
                canvas.save();
                //]Log.w(this.toString(), "IntegratedNewsView: F0"+"="+(height*1) +"="+floatAnimDayUI );

                //]Log.w(this.toString(), "IntegratedNewsView: R0"+"="+int_A +"="+float_W +"="+height );
                canvas.clipRect(0,(height*(1-floatAnimDayUI)),width,height);
                for (int i = int_A; i < integratedDrawData.size(); i++) {
                    float_W2=float_W;
                    float_W+=integratedDrawData.get(i).floatViewH-20;
                    canvas.drawRoundRect(73 - 1, height - (float_W ) - 2, width - 73 + 3, height - (float_W2 ) + 3, 15, 15, paintUIMoreB);
                    canvas.drawRoundRect(73, height - (float_W ), width - 73, height - (float_W2 ), 10, 10, paintUIMore);
                    if(integratedDrawData.get(i).stringTitle!=null)
                    {

                    }
                    for(int e=0;e<integratedDrawData.get(i).stringTitle.length;e++)
                    {

                        canvas.drawText(integratedDrawData.get(i).stringTitle[e],83,height-float_W+(50*(e+1)),paintTitle);
                    }
                    canvas.drawText(integratedDrawData.get(i).stringTime,width-93,height-float_W2-(10),paintTime);
                    float_W+=20;
                    if(integratedDrawData.get(i).readNewsData.boolNew)
                        {

                            updateIntegratedNewNum(1,integratedDrawData.get(i).readNewsData.setBoolNewFalse());
                        }
                    if(integratedDrawData.get(i).readNewsData.boolDrewNew)
                    {

                        canvas.drawText("[New]",83,height-float_W2-(10),paintTitle);

                    }
                    if((height-(float_W+(height*(1-floatAnimDayUI))))<0)
                    {

                        //]Log.w(this.toString(), "IntegratedNewsView: R1"+"="+int_A +"="+i);
                        break;
                    }
                }

                canvas.restore();
            }
        }
        public void setFloatAnimDayUI(float float_A){
            floatAnimDayUI=float_A;
        }
        public void moveView(float float_Move){
            if(boolDrawOK&&intDataLastNum!=-1)
            {

                // //]Log.w("NewsRecycleView", "LKDMLDmoveViewD0: "+"="+float_Move +"="+ intViewHRest +"="+intRecycDataNow +"="+(newsRecycDataNow!=null));
                float float_A = floatDataNowH - float_Move;
                float float_B = integratedDrawData.get(intDataNum).floatViewH;

                if (intDataNum == intDataLastNum) {

                    if (float_A > (floatDataLastH)) {
                        //intViewHRest+=(float_A-(float_B - 144));
                        float_A = floatDataLastH;
                        //float_A -= floatDataNowH;

                    }
                    if (float_A < 0) {
                        if (intDataNum != 0) {
                            intDataNum--;
                            float_B = integratedDrawData.get(intDataNum).floatViewH;
                            float_A = float_B + float_A;
                            float_A = repsMoveView(float_A);
                        } else {
                            //intViewHRest+=float_A;
                            float_A = 0;
                        }

                    }

                } else if (float_A < 0) {
                    if (intDataNum != 0) {
                        intDataNum--;
                        float_B = integratedDrawData.get(intDataNum).floatViewH;
                        float_A = float_B + float_A;
                        float_A = repsMoveView(float_A);
                    } else {
                        //intViewHRest+=float_A;
                        float_A = 0;
                    }

                } else if (float_A > float_B) {
                    if (intDataNum == intDataLastNum) {
                        if (float_A > (floatDataLastH)) {
                            //intViewHRest+=(float_A-(float_B - 144));
                            float_A = floatDataLastH;
                            //float_A -=floatDataLastH;
                        }
                    } else {
                        intDataNum++;
                        float_A = float_A - float_B;
                        float_A = repsMoveView(float_A);
                    }

                }


                floatDataNowH = float_A;
                invalidate();

            }
        }
        public float repsMoveView(float float_Move) {
            float float_A=float_Move;
            float float_B=integratedDrawData.get(intDataNum).floatViewH;
            if(float_A<0)
            {
                if(intDataNum!=0)
                {
                    intDataNum--;
                    float_B=integratedDrawData.get(intDataNum).floatViewH;
                    float_A=float_B+float_A;
                    return repsMoveView(float_A);
                }else {
                    float_A=0;
                }

            }else
            {
                if(intDataNum==intDataLastNum)
                {
                    if(float_A>floatDataLastH)
                    {
                        float_A=floatDataLastH;
                    }
                }else {
                    if(float_A>float_B)
                    {
                        intDataNum++;
                        float_A=float_A-float_B;
                        return repsMoveView(float_A);
                    }
                }



            }
            return float_A;
        }
        public void touchView(MotionEvent event){
            if (boolDrawOK) {
                if(event.getX()>80&&event.getX()<width-80)
                {
                    float float_W = 10 - floatDataNowH - (height * (1 - floatAnimDayUI));
                    float float_W2;
                    int int_A = intDataNum;
                    for (int i = int_A; i < integratedDrawData.size(); i++) {
                        float_W2 = float_W;
                        float_W += integratedDrawData.get(i).floatViewH - 20;
                        if(event.getY()<(height-float_W2)&&event.getY()>height-float_W)
                        {
                            //]Log.w(this.toString(), "IntegratedNewsView.touchView: T0"+"="+i +"="+event.getY() +"="+(height-float_W2) +"="+(height-float_W));
                            openIntegratedText(integratedDrawData.get(i).newsDataClass);

                        }
                        float_W+=20;

                    }
                }

            }
        }


    }

    public class IntegratedCalendarVD{
        float floatDataLastH;
        float floatDataNowH;
        int intDataLastNum=-1;
        int intDataNum=0;

        ArrayList<CalendarDataClass> DataClasses;
        ArrayList<CalendarDataClass> DataClassesUpdate;
        ArrayList<IntegratedDrawCalendar> integratedDrawData;
        Handler handlerUI;
        boolean boolDrawOK;
        boolean boolViewOK;
        public IntegratedCalendarVD(){

            DataClasses =new ArrayList<>();
            integratedDrawData=new ArrayList<>();
            DataClassesUpdate =new ArrayList<>();
        }

        public void invalidateStart(){
            invalidate();
        }
        public void setData(CalendarDataClass[] newsDataClasses_A){
            synchronized ("UpdateNewsData"){
                if(DataClassesUpdate ==null)
                {
                    DataClassesUpdate =new ArrayList<>();
                }
                DataClassesUpdate.addAll(Arrays.asList(newsDataClasses_A));
            }
        }
        public void updateData(){

        }
        public void getData(){
            ArrayList<IntegratedDrawCalendar> integratedDrawNews_A =new ArrayList<>();
            synchronized ("UpdateNewsData"){
                ////]Log.w(this.toString(), "getUpdateNewsData: R0"+"="+ DataClassesUpdate.size());
                if(DataClassesUpdate.size()!=0)
                {
                    // DataClasses.addAll(DataClassesUpdate);
                    for(int i = 0; i< DataClassesUpdate.size(); i++)
                    {
                        integratedDrawNews_A.add(new IntegratedDrawCalendar(DataClassesUpdate.get(i)));
                    }
                    DataClassesUpdate =new ArrayList<>();
                }else {
                    return;
                }

            }
            if(integratedDrawData==null)
            {
                integratedDrawData=new ArrayList<>();
            }
            integratedDrawData.addAll(integratedDrawNews_A);
        }
        public void Draw(Canvas canvas) {


            // canvas.drawRoundRect(60 - 1, 0, width - 60 + 3, height, 15, 15, paintUIMoreB);
            if (boolDrawOK) {
//            //]Log.w(this.toString(), "IntegratedNewsView: T0"+"="+intDataNum +"="+integratedDrawData.size() );
                int int_A = intDataNum;
                float float_W = 10-floatDataNowH-(height*(1-floatAnimDayUI));
                float float_W2;
                canvas.save();
                //]Log.w(this.toString(), "IntegratedNewsView: W0"+"="+(height*1) +"="+floatAnimDayUI );
                //]Log.w(this.toString(), "IntegratedNewsView: Q1"+"="+ int_A +"="+float_W +"="+height);
                //canvas.clipRect(0,(height*(1-floatAnimDayUI)),width,height);
                for (int i = int_A; i < integratedDrawData.size(); i++) {
                    float_W2=float_W;
                    float_W+=integratedDrawData.get(i).floatViewH-20;
                    canvas.drawRoundRect(73 - 1, height - (float_W ) - 2, width - 73 + 3, height - (float_W2 ) + 3, 15, 15, paintUIMoreB);
                    canvas.drawRoundRect(73, height - (float_W ), width - 73, height - (float_W2 ), 10, 10, paintUIMore);
                    if(integratedDrawData.get(i).stringTitle!=null)
                    {

                    for(int e=0;e<integratedDrawData.get(i).stringTitle.length;e++)
                    {

                        canvas.drawText(integratedDrawData.get(i).stringTitle[e],83,height-float_W+(50*(e+1)),paintTitle);
                    }
                    }
                    float floatCD_A=(width-(83+93))/4F;
                    canvas.drawText(integratedDrawData.get(i).stringData[0],floatCD_A*1,height-30-float_W2,paintCalenDat);
                    canvas.drawText(integratedDrawData.get(i).stringData[1],floatCD_A*2,height-30-float_W2,paintCalenDat);
                    canvas.drawText(integratedDrawData.get(i).stringData[2],floatCD_A*3,height-30-float_W2,paintCalenDat);
                    canvas.drawText(integratedDrawData.get(i).stringTime,width-93,height-float_W+(50),paintTimeB);
                    drawStar(canvas,width-93,height-float_W+(80),integratedDrawData.get(i).intStar);
                    float_W+=20;

                    if((height-float_W)<0)
                    {
                        //]Log.w(this.toString(), "IntegratedNewsView: Q2"+"="+i +"="+ int_A);
                        break;
                    }
                }

                canvas.restore();
            }
        }
        String[] stringStars=new String[]{"✩","✩✩","✩✩✩"};
        void drawStar(Canvas canvas,float float_W,float float_H,int intStar_A){
            //]Log.w(this.toString(), "drawStar: R0"+"="+ intStar_A);
           switch(intStar_A){
               case 1:
                   canvas.drawText(stringStars[0],float_W-40,float_H+10,paintCalenDat);
                   break;
               case 2:
                   canvas.drawText(stringStars[1],float_W-40,float_H+10,paintCalenDat);
                   break;
               case 3:
                   canvas.drawText(stringStars[2],float_W-40,float_H+10,paintCalenDat);
                   break;
               case 4:
                   canvas.drawText(stringStars[1],float_W-37,float_H+10,paintCalenDat);
                   canvas.drawText(stringStars[1],float_W-47,float_H+35,paintCalenDat);
                   break;
               case 5:
                   canvas.drawText(stringStars[2],float_W-40,float_H+10,paintCalenDat);
                   canvas.drawText(stringStars[1],float_W-40,float_H+35,paintCalenDat);
                   break;
               default:
                   break;
           }
        }
        public void setFloatAnimDayUI(float float_A){
            floatAnimDayUI=float_A;
        }
        public void moveView(float float_Move){
            if(boolDrawOK&&intDataLastNum!=-1)
            {

                // //]Log.w("NewsRecycleView", "LKDMLDmoveViewD0: "+"="+float_Move +"="+ intViewHRest +"="+intRecycDataNow +"="+(newsRecycDataNow!=null));
                float float_A = floatDataNowH - float_Move;
                float float_B = integratedDrawData.get(intDataNum).floatViewH;

                if (intDataNum == intDataLastNum) {

                    if (float_A > (floatDataLastH)) {
                        //intViewHRest+=(float_A-(float_B - 144));
                        float_A = floatDataLastH;
                        //float_A -= floatDataNowH;

                    }
                    if (float_A < 0) {
                        if (intDataNum != 0) {
                            intDataNum--;
                            float_B = integratedDrawData.get(intDataNum).floatViewH;
                            float_A = float_B + float_A;
                            float_A = repsMoveView(float_A);
                        } else {
                            //intViewHRest+=float_A;
                            float_A = 0;
                        }

                    }

                } else if (float_A < 0) {
                    if (intDataNum != 0) {
                        intDataNum--;
                        float_B = integratedDrawData.get(intDataNum).floatViewH;
                        float_A = float_B + float_A;
                        float_A = repsMoveView(float_A);
                    } else {
                        //intViewHRest+=float_A;
                        float_A = 0;
                    }

                } else if (float_A > float_B) {
                    if (intDataNum == intDataLastNum) {
                        if (float_A > (floatDataLastH)) {
                            //intViewHRest+=(float_A-(float_B - 144));
                            float_A = floatDataLastH;
                            //float_A -=floatDataLastH;
                        }
                    } else {
                        intDataNum++;
                        float_A = float_A - float_B;
                        float_A = repsMoveView(float_A);
                    }

                }


                floatDataNowH = float_A;
                invalidate();

            }
        }
        public float repsMoveView(float float_Move) {
            float float_A=float_Move;
            float float_B=integratedDrawData.get(intDataNum).floatViewH;
            if(float_A<0)
            {
                if(intDataNum!=0)
                {
                    intDataNum--;
                    float_B=integratedDrawData.get(intDataNum).floatViewH;
                    float_A=float_B+float_A;
                    return repsMoveView(float_A);
                }else {
                    float_A=0;
                }

            }else
            {
                if(intDataNum==intDataLastNum)
                {
                    if(float_A>floatDataLastH)
                    {
                        float_A=floatDataLastH;
                    }
                }else {
                    if(float_A>float_B)
                    {
                        intDataNum++;
                        float_A=float_A-float_B;
                        return repsMoveView(float_A);
                    }
                }



            }
            return float_A;
        }
        public void touchView(MotionEvent event){
            if (boolDrawOK) {
                if(event.getX()>80&&event.getX()<width-80)
                {
                    float float_W = 10 - floatDataNowH - (height * (1 - floatAnimDayUI));
                    float float_W2;
                    int int_A = intDataNum;
                    for (int i = int_A; i < integratedDrawData.size(); i++) {
                        float_W2 = float_W;
                        float_W += integratedDrawData.get(i).floatViewH - 20;
                        if(event.getY()<(height-float_W2)&&event.getY()>height-float_W)
                        {
                            //]Log.w(this.toString(), "IntegratedNewsView.touchView: T0"+"="+i +"="+event.getY() +"="+(height-float_W2) +"="+(height-float_W));
                            //]Log.w(this.toString(), "IntegratedNewsView.touchView: T1"+"="+i +"="+(integratedDrawData.get(i).dataClass.stringName));
                            openIntegratedText(integratedDrawData.get(i).dataClass);

                        }
                        float_W+=20;

                    }
                }

            }
        }
        void ccViewHLast(){
            if(boolViewOK)
            {
                boolDrawOK=true;
                float float_H=10;
                floatDataLastH=-1;
                intDataLastNum=-1;
                for(int i=integratedDrawData.size()-1;i>=0;i--)
                {
                    float_H+=integratedDrawData.get(i).floatViewH;
                    if(float_H>(height-10))
                    {

                        intDataLastNum=i;
                        floatDataLastH=float_H-(height-10);
                        //]Log.w(this.toString(), "ccViewHLast: Y0"+"="+i +"="+float_H +"="+ height +"="+floatDataLastH);
                        break;
                    }
                    //float_H+=20;
                }
            }

        }
    }

    public class IntegratedDrawNews {
        public float floatViewH;
        public NewsDataClass newsDataClass;
        public ReadModeTool.ReadModeNews.ReadNewsData readNewsData;
        public String[] stringTitle;
        public String[] stringsText;
        public String stringTime;
        public IntegratedDrawNews(NewsDataClass newsDataClass_A){
            newsDataClass=newsDataClass_A;
            //stringTitle=;
            stringTime=newsDataClass.timeOD.getStringDate();//.intsA[1]+"/"+dataClass.timeOD.intsA[2]+"  "+dataClass.timeOD.intsA[3]+":"+dataClass.timeOD.intsA[4];
            //stringTitle+=stringTitle+stringTitle+stringTitle;
            stringTitle= TextStringTool.getTextAdvancesB(newsDataClass.stringTitle,paintTitle,width-186);//"欧元(EUR)是仅次于美元的第二大储备货币，同时也是欧元区大部分国家的官方货币。1969年欧洲货币联盟的构想已被提",paintTitle,width-186);
            floatViewH=60+(50*stringTitle.length);
        }
        public IntegratedDrawNews(ReadModeTool.ReadModeNews.ReadNewsData readNewsData_A){
            readNewsData=readNewsData_A;
            newsDataClass=readNewsData.newsDataClass;
            //stringTitle=;
            stringTime=newsDataClass.timeOD.getStringDate();//.intsA[1]+"/"+dataClass.timeOD.intsA[2]+"  "+dataClass.timeOD.intsA[3]+":"+dataClass.timeOD.intsA[4];
            //stringTitle+=stringTitle+stringTitle+stringTitle;
            stringTitle= TextStringTool.getTextAdvancesB(newsDataClass.stringTitle,paintTitle,width-186);//"欧元(EUR)是仅次于美元的第二大储备货币，同时也是欧元区大部分国家的官方货币。1969年欧洲货币联盟的构想已被提",paintTitle,width-186);
            floatViewH=60+(50*stringTitle.length);
        }

    }
    public class IntegratedDrawCalendar {
        public float floatViewH;
        public CalendarDataClass dataClass;
        public String[] stringTitle;
       // public String[] stringsText;
        public String[] stringData;
        public String stringTime;
        public String stringTimeNow;
        public int intStar;
        public IntegratedDrawCalendar(CalendarDataClass dataClass_A){
            dataClass =dataClass_A;
            //stringTitle=;
            //stringTime= dataClass.timeOD.getStringDate();//.intsA[1]+"/"+dataClass.timeOD.intsA[2]+"  "+dataClass.timeOD.intsA[3]+":"+dataClass.timeOD.intsA[4];
            //stringTitle+=stringTitle+stringTitle+stringTitle;
            stringTimeNow=new TimeOD(System.currentTimeMillis()).getStringTime();
            String stringName=dataClass_A.stringName;
            //stringName+=stringName+stringName+stringName;
            stringTitle= TextStringTool.getTextAdvancesB(stringName,paintTitle,width-290);//"欧元(EUR)是仅次于美元的第二大储备货币，同时也是欧元区大部分国家的官方货币。1969年欧洲货币联盟的构想已被提",paintTitle,width-186);
            stringTime=dataClass.stringTime;
            floatViewH=100+(50*stringTitle.length);
            stringData=new String[3];
            intStar= (dataClass_A.intStar);
            stringData[0]=(dataClass.stringActual ==null?"-":dataClass.stringActual);
            stringData[1]=(dataClass.stringForecast ==null?"-":dataClass.stringForecast);
            stringData[2]=(dataClass.stringPrevious ==null?"-":dataClass.stringPrevious);
        }

    }
}
