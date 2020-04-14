package com.example.app_005.Tool.WindowView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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
import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Interface.OnUpdateSnapCalendarData;
import com.example.app_005.Interface.OnUpdateSnapNewsData;
import com.example.app_005.MainApplication;
import com.example.app_005.Tool.FXDataConnect;
import com.example.app_005.Tool.LogM;
import com.example.app_005.Tool.TextStringTool;
import com.example.app_005.Tool.TimeOD;
import com.example.app_005.View.IntegratedTextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WindowRecycView extends View {
    private int width;//设置高
    private int height;//设置高
    Paint paintUI;
    Paint paintUIB;
    WindowRecycDrawQuo windowRecycDrawQuo;
    WindowRecycDrawNews windowRecycDrawNews;
    WindowRecycDrawCalendar windowRecycDrawCalendar;
    WindowRecycDrawClass[] windowRecycDrawClasses;
    public int intNo;


    public WindowRecycView(Context context) {
        super(context);
    }

    public WindowRecycView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintUI=new Paint();
        paintUI.setColor(Color.WHITE);
        paintUIB=new Paint();
        paintUIB.setColor(Color.BLACK);
        windowRecycDrawQuo=new WindowRecycDrawQuo(((MainApplication)context.getApplicationContext()).fxDataConnect);
        windowRecycDrawNews=new WindowRecycDrawNews(((MainApplication)context.getApplicationContext()).fxDataConnect,790);
        windowRecycDrawCalendar=new WindowRecycDrawCalendar(((MainApplication)context.getApplicationContext()).fxDataConnect,790);
        windowRecycDrawClasses=new WindowRecycDrawClass[]{windowRecycDrawQuo,windowRecycDrawNews,windowRecycDrawCalendar};
        intNo=0;
        initScroller();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(width, height);//设置宽和高
        windowRecycDrawQuo.initData();
        //]Log.w(this.toString(), "WinRecV.onMeasure: E0"+"="+height +"="+windowRecycDrawNews.boolDrawOK );
        if(windowRecycDrawNews.boolDrawOK)
            {

                windowRecycDrawNews.ccViewH();
                invalidate();
            }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(0,0,width,height,20,20,paintUIB);
        canvas.drawRoundRect(1,1,width-2,height-3,20,20,paintUI);
        canvas.save();
        canvas.clipRect(1,1,width-2,height-3);
        windowRecycDrawClasses[intNo].Draw(canvas,0,0);
        canvas.restore();
    }

    public void cutTabView(int int_A){
        intNo=int_A;
        stopScroller();
        if(intNo==1)
            {
                windowRecycDrawNews.intDrawMode=0;
            }
        //]Log.w(this.toString(), "cutTabView: E0"+"="+intNo );
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        stopScroller();
        windowRecycDrawClasses[intNo].onTouchEvent(event);
        //windowRecycDrawQuo.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

                return true;
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return super.onTouchEvent(event);
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
            mScroller.fling((int)float_W,(int) float_H, xVelocity, yVelocity, -99999, 99999, -99999, 99999);
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
            if(intNo==0)
                {
                    //]Log.w(this.toString(), "WinRecQuo.computeScroll: E0"+"="+mScroller.getCurrX() +"="+mScroller.getCurrY() );
                    windowRecycDrawClasses[intNo].computeScroll(mScroller.getCurrX(),mScroller.getCurrY());

                }else {

                windowRecycDrawClasses[intNo].computeScroll(mScroller.getCurrY());
            }

//            //]Log.w(this.toString(), "IntegratedNewsView.computeScroll: "+"="+float_A );
//            moveView(float_A);
//            floatTouchDownY=mScroller.getCurrY();
//            // invalidate();

            postInvalidate();
        }
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

    public class WindowRecycDrawClass{

        float floatTouchDownX;
        float floatTouchDownY;
        long longTouchTime;
//        float floatTouchX,floatTouchY;

        float floatTouchLashH;

        boolean boolDrawOK;
        boolean boolViewOK;

        public void Draw(Canvas canvas,float float_W,float float_H){

        }
        void ccViewH(){

        }
        public void setAnimFloat(float float_A){

        }
        void moveView(float float_A,int int_A){

        }
        float repsMoveView(float float_Move) {
            return 0;
        }
        public void touchView(MotionEvent event){

        }

        public boolean onTouchEvent(MotionEvent event) {
            return false;
        }
        private float spacing(float float_X1,float float_Y1,float float_X2,float float_Y2) {
            float x = float_X1 - float_X2;
            float y = float_Y1 - float_Y2;
            return (float) Math.sqrt(x * x + y * y);
        }
        public void computeScroll(float float_A) {

        }
        public void computeScroll(float float_X,float float_Y) {

        }


    }
    public class WindowRecycDrawQuo extends WindowRecycDrawClass {
        Paint paintUI;
        Paint paintUI2;
        Paint paintUI2B;
        Paint paintUI3;
        Paint paintText;
        Paint paintTextMT;
        Paint paintTextMUD;
        Paint paintMU;
        Paint paintMD;
        // int intDrawDaNum;
        //float floatDrawDaL;
        // float floatTouchNow=1;
        WindowQuoColTool windowQuoColTool;

        public WindowRecycDrawQuo(FXDataConnect fxDataConnect_A) {
            paintUI = new Paint();
            paintUI.setColor(Color.GRAY);
            paintUI.setStrokeWidth(3);//设置宽度


            paintUI3 = new Paint();
            paintUI3.setColor(Color.BLACK);
            paintUI3.setStrokeWidth(3);//设置宽度
            paintUI3.setStyle(Paint.Style.STROKE);

            paintUI2 = new Paint();
            paintUI2.setColor(Color.BLUE);
            paintUI2.setStyle(Paint.Style.FILL);
            paintUI2.setAntiAlias(true);//消除锯齿
            paintUI2.setStrokeWidth(1);//设置宽度

            paintUI2B = new Paint();
            paintUI2B.setColor(Color.RED);
            paintUI2B.setStyle(Paint.Style.FILL);
            paintUI2B.setAntiAlias(true);//消除锯齿
            paintUI2B.setStrokeWidth(1);//设置宽度

            paintText = new Paint();
            paintText.setAntiAlias(true);//消除锯齿
            paintText.setColor(Color.BLACK);//设置画笔颜色
            paintText.setTextAlign(Paint.Align.CENTER);
            paintText.setTextSize(15);

            paintTextMT = new Paint();
            paintTextMT.setAntiAlias(true);//消除锯齿
            paintTextMT.setColor(Color.BLACK);//设置画笔颜色
            paintTextMT.setTextAlign(Paint.Align.LEFT);
            paintTextMT.setTextSize(15);

            paintTextMUD = new Paint();
            paintTextMUD.setAntiAlias(true);//消除锯齿
            paintTextMUD.setColor(Color.WHITE);//设置画笔颜色
            paintTextMUD.setTextAlign(Paint.Align.LEFT);
            paintTextMUD.setTextSize(15);

            paintMU = new Paint();
            paintMU.setColor(Color.BLUE);
            paintMU.setStyle(Paint.Style.FILL);
            paintMU.setAntiAlias(true);//消除锯齿
            paintMU.setStrokeWidth(2);//设置宽度

            paintMD = new Paint();
            paintMD.setColor(Color.RED);
            paintMD.setStyle(Paint.Style.FILL);
            paintMD.setAntiAlias(true);//消除锯齿
            paintMD.setStrokeWidth(2);//设置宽度

            // floatDrawDaL=50;
            //intDrawDaNum=(int)((width-180)/floatDrawDaL)+3;
            windowQuoColTool = new WindowQuoColTool(fxDataConnect_A, this);
            post(new Runnable() {
                @Override
                public void run() {

                    ccViewH();
                }
            });
        }

        public void initData() {

            //floatDrawDaL=15;
            // ccDrawDaNum(floatDrawDaL);
        }

        public void reqDraw() {
            invalidate();
        }

        @Override
        public void Draw(Canvas canvas, float float_W, float float_H) {
            if (windowQuoColTool.boolDraw) {
                float float_L = 0 - floatViewP;
                for (int i = 0; i < windowQuoColTool.windowQuoDrDraws.length; i++) {
                    //]Log.w(this.toString(), "WinRecQuo.Draw: E0"+"="+ (float_L < 0 && (float_L + 480) < 0) +"="+(float_L > height) +"="+float_L);
                    if (float_L < 0 && (float_L + 480) < 0) {
                        //]Log.w(this.toString(), "WinRecQuo.Draw: E0a"+"="+float_L);
                        float_L += 480;
                        continue;



                    } else if (float_L > height) {

                        break;
                    } else {
                        DrawB(canvas, float_W, float_H + float_L);
                        DrawC(canvas, float_W, float_H + float_L, windowQuoColTool.windowQuoDrDraws[i]);
                    }

                    float_L += 480;
                    //break;
                }
            }


        }

        void DrawB(Canvas canvas, float float_W, float float_H) {
            canvas.drawRect(30, float_H + 20, width - 100, float_H + 420, paintUI);
            // canvas.drawRect(20,20,width-20,500,paintUI);
            canvas.drawLine(50, float_H + 350 - 50, 50, float_H + 350 + 30, paintUI3);
            canvas.drawLine(width - 130, float_H + 350 - 50, width - 130, float_H + 350 + 30, paintUI3);
            canvas.drawRect(50, float_H + 380, width - 130, float_H + 410, paintUI3);

            if (false) {
//                    canvas.save();
//                    canvas.clipRect(50,float_H+40,width-130,float_H+380);
//                    //]Log.w(this.toString(), this.toString()+".DrawB: E0"+"="+ intDrawDaNum +"="+floatTouchNow);
//                    for(int i=0;i<intDrawDaNum;i++)
//                    {
//
//                        //canvas.drawRect(width-130+floatDrawDaL-(floatDrawDaL*(i+1))-(floatDrawDaL*floatTouchNow),350-30,width-130+floatDrawDaL-(floatDrawDaL*(i))-(floatDrawDaL*floatTouchNow),350+30,paintUI2);
//                        canvas.drawRect(width-130+floatDrawDaL-(floatDrawDaL*(i))-(floatDrawDaL*floatTouchNow)-(floatDrawDaL*0.3F),float_H+350-100,width-130+floatDrawDaL-(floatDrawDaL*(i))-(floatDrawDaL*floatTouchNow)+(floatDrawDaL*0.3F),float_H+350+30,paintUI2);
//                    }
//
//                    canvas.restore();
            }

        }

        void DrawC(Canvas canvas, float float_W, float float_H, WindowQuoColTool.WindowQuoDrDraw windowQuoDrDraw_A) {
            // //]Log.w(this.toString(), "DrawC: W0"+"="+(windowQuoDrDraw_A!=null) +"="+ windowQuoDrDraw_A.boolDraw +"="+windowQuoDrDraw_A.skillQuoName.getName());
            if (windowQuoDrDraw_A != null && windowQuoDrDraw_A.boolDraw) {
                int int_N = windowQuoDrDraw_A.windowQuoDrawDS.size();
                // //]Log.w(this.toString(), "DrawC: W1"+"="+int_N +"="+ windowQuoDrDraw_A.floatDrawDaL);
                float float_R = 0;
                float float_Q = 0;
                canvas.save();
                canvas.clipRect(50, float_H + 40, width - 130, float_H + 420);
                // //]Log.w(this.toString(), this.toString()+".DrawB: E0"+"="+ windowQuoDrDraw_A.intNum +"="+ windowQuoDrDraw_A.intNum +"="+windowQuoDrDraw_A.windowQuoDrawDS.size() ) ;

                ////]Log.w(this.toString(), "DrawC: T0"+"="+windowQuoDrDraw_A.windowQuoDrawDS.size() +"="+windowQuoDrDraw_A.timeODStart.getStringTime()+"="+windowQuoDrDraw_A.timeODLast.getStringTime()   );
                int int_TiW = windowQuoDrDraw_A.windowQuoDrawTime.intTiA;
                int[] int_TiT = new int[]{windowQuoDrDraw_A.windowQuoDrawTime.timeODN.intsA[3], windowQuoDrDraw_A.windowQuoDrawTime.timeODN.intsA[4], -1};
                String string_TiS = "";
                for (int i = windowQuoDrDraw_A.windowQuoDrawDS.size() - 1; i >= 0; i--) {

                    float_R = width - 130 - (windowQuoDrDraw_A.floatDrawDaL * (windowQuoDrDraw_A.windowQuoDrawDS.size() - i)) - (windowQuoDrDraw_A.floatDrawDaL * (windowQuoDrDraw_A.floatTouchNow));//-(floatDrawDaL*0.3F);
                    float_Q = windowQuoDrDraw_A.windowQuoDrawDS.get(i).floatUI * 300F;
                    if (true || windowQuoDrDraw_A.windowQuoDrawDS.get(i).quoData != null) {

                        int int_T = windowQuoDrDraw_A.windowQuoDrawDS.get(i).intM;
                        //  //]Log.w(this.toString(), "DrawC: T3"+"="+i +"="+ int_T);
                        // canvas.drawText(int_T+"",float_R,float_H+400,paintText);

                    }
                    if (i == ((windowQuoDrDraw_A.windowQuoDrawDS.size() - 1) - int_TiW)) {
                        //canvas.drawRect(float_R + (windowQuoDrDraw_A.windowQuoDrawTime.floatW / 2F), float_H + 400 - 10, float_R - (windowQuoDrDraw_A.windowQuoDrawTime.floatW / 2F), float_H + 400 + 10, paintUI2);

                        for (int w = 0; w < 99; w++) {
                            if (int_TiT[1] < 0) {
                                int_TiT[1] = 60 - int_TiT[1];

                                int_TiT[0] -= 1;
                                if (int_TiT[0] < 0) {
                                    int_TiT[0] = 23;
                                    TimeOD timeOD_Nf = windowQuoDrDraw_A.windowQuoDrawTime.timeODN.PuC_getCrTimeOD(-1);
                                    int_TiT[2] = timeOD_Nf.intsA[2];
                                }

                            } else {
                                break;
                            }
                        }
                        if (int_TiT[2] != -1) {
                            string_TiS = int_TiT[2] + "/" + int_TiT[0] + ":" + int_TiT[1];
                        } else {

                            string_TiS = int_TiT[0] + ":" + int_TiT[1];
                        }

                        canvas.drawText(string_TiS, float_R, float_H + 400, paintText);
                        int_TiW += windowQuoDrDraw_A.windowQuoDrawTime.intTiC;
                        int_TiT[1] -= windowQuoDrDraw_A.windowQuoDrawTime.intTiC;
                    }

                    ////]Log.w(this.toString(), "DrawC: Ty0"+"="+i +"="+((windowQuoDrDraw_A.windowQuoDrawDS.size()-1)-windowQuoDrDraw_A.windowQuoDrawTime.intTiB) +"="+windowQuoDrDraw_A.windowQuoDrawTime.intTiB );
//                            if(i==((windowQuoDrDraw_A.windowQuoDrawDS.size()-1)-windowQuoDrDraw_A.windowQuoDrawTime.intTiB)) {
//                                canvas.drawRect(float_R + (windowQuoDrDraw_A.windowQuoDrawTime.floatW / 2F), float_H + 400 - 10, float_R - (windowQuoDrDraw_A.windowQuoDrawTime.floatW / 2F), float_H + 400 + 10, paintUI2);
//
//                            }
                    ////]Log.w(this.toString(), "DrawC: W3"+"="+float_R +"="+float_Q );

                    // //]Log.w(this.toString(), "DrawC: E1"+"="+windowQuoDrDraw_A.windowQuoDrawDS.get(i).boolData +"="+ windowQuoDrDraw_A.windowQuoDrawDS.get(i).floatUI +"="+float_Q);
                    canvas.drawRect(float_R - (windowQuoDrDraw_A.floatDrawDaL * 0.3F), float_H + 380, float_R + (windowQuoDrDraw_A.floatDrawDaL * 0.3F), float_H + 380 - float_Q, paintUI2);
                    if (i == 0) {
                        canvas.drawRect(float_R - (windowQuoDrDraw_A.floatDrawDaL * 0.3F), float_H + 380, float_R + (windowQuoDrDraw_A.floatDrawDaL * 0.3F), float_H + 380 - float_Q, paintUI2B);

                    }
                    if (i == windowQuoDrDraw_A.windowQuoDrawDS.size() - 1) {
                        canvas.drawRect(float_R - (windowQuoDrDraw_A.floatDrawDaL * 0.3F), float_H + 380, float_R + (windowQuoDrDraw_A.floatDrawDaL * 0.3F), float_H + 380 - float_Q, paintUI2B);

                    }

                }


                canvas.restore();
                if (windowQuoDrDraw_A.windowQuoDrawMT != null && windowQuoDrDraw_A.windowQuoDrawMT.boolData) {
                    canvas.save();
                    canvas.clipRect(50, float_H + 40, width - 130, float_H + 420);
                    float float_Q2 = windowQuoDrDraw_A.windowQuoDrawMT.floatUIu * 300F;
                    float flaot_R2 = width - 150;
                    // canvas.drawText(windowQuoDrDraw_A.windowQuoDrawMT.stringQU,flaot_R2,float_H+380-float_Q2,paintTextMUD);
                    // canvas.drawRoundRect(flaot_R2,float_H+380-float_Q2+5,flaot_R2+50,float_H+380-float_Q2-5,5,5,paintMU);
                    canvas.drawLine(50, float_H + 380 - float_Q2, width - 150, float_H + 380 - float_Q2, paintMU);
                    float_Q2 = windowQuoDrDraw_A.windowQuoDrawMT.floatUId * 300F;
                    //canvas.drawText(windowQuoDrDraw_A.windowQuoDrawMT.stringQD,flaot_R2,float_H+380-float_Q2,paintTextMUD);
                    canvas.drawLine(50, float_H + 380 - float_Q2, width - 150, float_H + 380 - float_Q2, paintMD);
                    canvas.restore();
                    for (int i = 0; i < windowQuoDrDraw_A.windowQuoDrawMT.floatsUI.size(); i++) {
                        float_Q2 = windowQuoDrDraw_A.windowQuoDrawMT.floatsUI.get(i) * 300F;
                        //canvas.drawRect(flaot_R2-100,float_H+380-float_Q2-20,flaot_R2+100,float_H+380-float_Q2+20,paintUI2);
                        canvas.drawText(windowQuoDrDraw_A.windowQuoDrawMT.stringsQ.get(i), flaot_R2, float_H + 380 - float_Q2, paintTextMT);

                    }

                }
            }

        }

        //        public void ccDrawDaNum(float float_A){
//            intDrawDaNum=(int)((width-180)/floatDrawDaL)+3;
//            //]Log.w(this.toString(), "WRecycV.zoomView: E0"+"="+intDrawDaNum +"="+ floatDrawDaL);
//            invalidate();
//        }
        @Override
        void ccViewH() {
            if(windowQuoColTool.windowQuoDrDraws!=null&&windowQuoColTool.windowQuoDrDraws.length!=0)
                {
                    float float_H=(480*windowQuoColTool.windowQuoDrDraws.length)-(height*1F);
                    floatTouchLashH=float_H;



                }

        }

        @Override
        public void setAnimFloat(float float_A) {

        }

        @Override
        void moveView(float float_A, int int_A) {
            //]Log.w(this.toString(), "WRecycV.moveView: E0" + "=" + float_A + "=" + int_A);
            windowQuoColTool.windowQuoDrDraws[int_A].moveQuoDrDrawTime(float_A);
            invalidate();
//            floatTouchNow+=float_A/floatDrawDaL;
//
//            //]Log.w(this.toString(), "WRecycV.moveView: E0" +"="+floatTouchNow+"="+float_A +"="+ floatDrawDaL);
//            if(floatTouchNow>1)
//                {
//                    floatTouchNow-=((int)floatTouchNow);
//                }else if(floatTouchNow<-1)
//                    {
//                        floatTouchNow-=((int)floatTouchNow);
//                    }
//            invalidate();


        }
        void zoomView(float float_A, int int_A) {
            windowQuoColTool.windowQuoDrDraws[int_A].zoomView(float_A);
            invalidate();
//            floatDrawDaL+=float_A;
//            if(floatDrawDaL<9)
//            {
//                floatDrawDaL=9;
//            }
//            if(floatDrawDaL>40)
//            {
//                floatDrawDaL=40;
//            }
//            ccDrawDaNum(floatDrawDaL);
        }

        float floatViewP;
        float floatQuoDrawLastY = -1;

        void moveViewP(float float_A) {

            floatViewP += float_A;
            //]Log.w(this.toString(), "WinRecQuo.moveViewP: E0"+"="+ float_A +"="+floatViewP +"="+floatTouchLashH);
            if (floatViewP < 0) {
                floatViewP = 0;
            }else if(floatViewP>floatTouchLashH)
                {
                    floatViewP=floatTouchLashH;
                }
            invalidate();
        }

        @Override
        float repsMoveView(float float_Move) {
            return 0;
        }

        @Override
        public void touchView(MotionEvent event) {

        }

        @Override
        public void computeScroll(float float_X,float float_Y) {
            super.computeScroll(float_X,float_Y);
            if (intTouchMode == 1) {
                moveView(floatTouchDownX-(float_X), intTouchN);
                floatTouchDownX=float_X;
            } else if (intTouchMode == 0) {
                moveViewP(floatTouchDownY-float_Y);
                floatTouchDownY=float_Y;
            }

        }


        int intTouchMode;
        int intTouchN;

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            ccScroller(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    intTouchMode = -1;
                    floatTouchDownX = event.getX();
                    floatTouchDownY = event.getY();
                    float float_Y = event.getY();
                    if (windowQuoColTool.boolDraw) {

                        if (floatTouchDownX > 50 && floatTouchDownX < width - 130) {
                            float float_L = 0 - floatViewP;
                            for (int i = 0; i < windowQuoColTool.windowQuoDrDraws.length; i++) {
                                //]Log.w(this.toString(), this.toString() + ".onTouchEvent: E0" + "=" + float_L + "=" + float_Y);
                                if (float_L < float_Y) {
                                    if ((float_L + 20) < float_Y && (float_L + 420) > float_Y) {
                                        if (float_Y > (float_L + 380) && float_Y < (float_L + 410)) {
                                            intTouchMode = 2;
                                            intTouchN = i;
                                            break;
                                        } else if (float_Y > (float_L + 40) && float_Y < (float_L + 380)) {
                                            intTouchMode = 1;
                                            intTouchN = i;
                                            break;
                                        }
                                    }
                                } else {
                                    intTouchMode = 0;
                                    break;
                                }

                                float_L += 480;
                            }


                        } else {
                            intTouchMode = 0;
                        }
                    }

                    break;
                case MotionEvent.ACTION_MOVE:
                    float float_A = floatTouchDownX - event.getX();
                    float float_B = floatTouchDownY - event.getY();
                    floatTouchDownX = event.getX();
                    floatTouchDownY = event.getY();
                    if (intTouchMode == 1) {
                        moveView(float_A, intTouchN);
                    } else if (intTouchMode == 2) {

                        zoomView(float_A, intTouchN);
                    } else if (intTouchMode == 0) {
                        moveViewP(float_B);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                  //  float float_Y = event.getY();
                    startScroller(floatTouchDownX,floatTouchDownY);

                    break;
                default:
                    break;
            }
            return false;
        }
    }
    public class WindowRecycDrawNews extends WindowRecycDrawClass implements OnUpdateSnapNewsData {
        Paint paintUI;
        Paint paintNewsUIP;
        Paint paintTitle;
        Paint paintText;
        Paint paintTime;
        Paint paintImgText;
        SnapNewsDataTool snapNewsDataTool;
        float floatViewH;
        float floatDataLastH;
        //int intViewLast;
        float floatTextW;
        int intDataNum;
        int intDataLastNum;
        int intNSize;
        int intDrawMode;
        boolean boolDataOK;
        boolean boolTextOK;
        WindowRecycDrawNewsDr windowRecycDrawNewsDr;
        WinRecNewsTextDraw winRecNewsTextDraw;
        public WindowRecycDrawNews(FXDataConnect fxDataConnect_A,float floatTextW_A){
            paintUI=new Paint();
            paintUI.setColor(Color.GRAY);

            paintNewsUIP=new Paint();
            paintNewsUIP.setColor(Color.GRAY);

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
            paintText.setTextSize(25);

            paintTime = new Paint();
            paintTime.setAntiAlias(true);//消除锯齿
            paintTime.setColor(Color.BLACK);
            paintTime.setTextAlign(Paint.Align.RIGHT);
            paintTime.setTextSize(22);
            
            paintImgText = new Paint();
            paintImgText.setAntiAlias(true);//消除锯齿
            paintImgText.setColor(Color.BLACK);
            //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
            paintImgText.setTextAlign(Paint.Align.CENTER);
            paintImgText.setTextSize(30);

            floatTextW=floatTextW_A;
            snapNewsDataTool=fxDataConnect_A.getNewsConnect().getSnapNewsDataTool(this);
            floatDataLastH =-1;
            windowRecycDrawNewsDr=new WindowRecycDrawNewsDr(snapNewsDataTool.snapNewsDataDTA.timeOD,snapNewsDataTool.snapNewsDataDTB.timeOD);
            intDataNum =-1;
            intDataLastNum=-1;
            intDrawMode=0;
        }
        @Override
        public void Draw(Canvas canvas,float float_W,float float_H){
//            //canvas.drawRect(0,0,width,height,paintUI);
//            canvas.drawRoundRect(30,30,width-30,200,10,10,paintNewsUIP);
//            canvas.drawText("Samsung Galaxy S10 马来西亚价格,功能与规格参数",40,80,paintTitle);
//            canvas.drawText("Samsung Galaxy S10 马来西亚价格,功能与规格参数",45,110,paintText);
//            canvas.drawText("单SIM (Nano-SIM) 或 Hybrid 双SIM (Nano-SIM, 双卡双待)",45,140,paintText);
//            canvas.drawText("2020/3/10",width-40,170,paintTime);
            if(intDrawMode==0)
                {
                    if(boolDrawOK&& intDataNum !=-1)
                    {
                        float float_G=10-floatDataNowH;
                        for(int i = intDataNum; i<intNSize; i++)
                        {
                            float_G-=0;
                            WindowRecycDrawNewsDr.WinRecycNewsP.WinRecycNewsD winRecycNewsD_A=getSnapIntNum(i);
                            if(winRecycNewsD_A!=null)
                            {
                                // //]Log.w(this.toString(), "WinRecV.Draw: R0"+"="+i +"="+ winRecycNewsD_A.stringTitle );

                                drawA(canvas,0,float_G,winRecycNewsD_A);
                                float_G+=winRecycNewsD_A.floatH+0;
                            }
                        }
                    }
                }else {
                winRecNewsTextDraw.StartDraw(canvas,0,0);
            }



        }
        void drawA(Canvas canvas, float float_W, float float_H, WindowRecycDrawNewsDr.WinRecycNewsP.WinRecycNewsD winRecycNewsD_A){
            canvas.drawRoundRect(30,float_H+30,width-30,float_H+winRecycNewsD_A.floatH,10,10,paintNewsUIP);
            canvas.drawText(winRecycNewsD_A.stringTitle,40,float_H+75,paintTitle);
            for(int i=0;i<winRecycNewsD_A.stringsText.length;i++)
                {

                    canvas.drawText(winRecycNewsD_A.stringsText[i],45,float_H+80+(30*(i+1)),paintText);
                }
           // canvas.drawText("Samsung Galaxy S10 马来西亚价格,功能与规格参数",45,float_H+30,paintText);
           // canvas.drawText("单SIM (Nano-SIM) 或 Hybrid 双SIM (Nano-SIM, 双卡双待)",45,float_H+140,paintText);
            canvas.drawText("2020/3/10",width-40,float_H+winRecycNewsD_A.floatH-13,paintTime);

        }
        WindowRecycDrawNewsDr.WinRecycNewsP.WinRecycNewsD getSnapIntNum(int int_A){
            if(windowRecycDrawNewsDr.winRecycNewsPA!=null)
                {
                    if(windowRecycDrawNewsDr.winRecycNewsPA.winRecycNewsDS.size()>int_A)
                        {
                        return windowRecycDrawNewsDr.winRecycNewsPA.winRecycNewsDS.get(int_A);
                        }else if(windowRecycDrawNewsDr.winRecycNewsPB!=null)
                            {
                                if(windowRecycDrawNewsDr.winRecycNewsPA.winRecycNewsDS.size()+windowRecycDrawNewsDr.winRecycNewsPB.winRecycNewsDS.size()>int_A)
                                    {

                                        return windowRecycDrawNewsDr.winRecycNewsPB.winRecycNewsDS.get(int_A-windowRecycDrawNewsDr.winRecycNewsPA.winRecycNewsDS.size());
                                    }else {
                                    return null;
                                }

                            }

                }else {
                if(windowRecycDrawNewsDr.winRecycNewsPB!=null)
                {
                    if(windowRecycDrawNewsDr.winRecycNewsPB.winRecycNewsDS.size()>int_A)
                    {

                        return windowRecycDrawNewsDr.winRecycNewsPB.winRecycNewsDS.get(int_A);
                    }else {
                        return null;
                    }

                }
            }

            return null;

        }
        void openWinRecNewsTextView(WindowRecycDrawNewsDr.WinRecycNewsP.WinRecycNewsD winRecycNewsD_A){
            if(winRecycNewsD_A!=null)
                {
                    winRecNewsTextDraw=new WinRecNewsTextDraw(winRecycNewsD_A);
                    boolTextOK=true;
                    intDrawMode=1;
                    invalidate();

                }
        }
        @Override
        void ccViewH(){
            float float_V=height;
            float floatLastH=20;
            intDataLastNum=-1;
            for(int i=intNSize-1;i>=0;i--)
                {
                    floatLastH+=getSnapIntNum(i).floatH;
                    if(floatLastH>height-3)
                        {
                            //]Log.w(this.toString(), "WinRecV.ccViewH: E0a"+"="+ floatLastH +"="+(height-3) +"="+getSnapIntNum(i).floatH);
                            floatDataLastH=getSnapIntNum(i).floatH-(getSnapIntNum(i).floatH-(floatLastH-(height-3)));
                            intDataLastNum=i;
                            break;

                        }

                }
            //]Log.w(this.toString(), "WinRecV.ccViewH: E0"+"="+ intDataLastNum +"="+floatDataLastH +"="+getSnapIntNum(intDataLastNum).floatH +"="+height);

        }
        @Override
        public void setAnimFloat(float float_A){

        }
        float floatDataNowH;
        @Override
        void moveView(float float_Move,int int_A) {
            if (boolDrawOK) {
                if(intDrawMode==0)
                    {
                        float float_A = floatDataNowH + float_Move;
                        float float_B = getSnapIntNum(intDataNum).floatH;
                        //]Log.w(this.toString(), "WinRecV.moveView: W0"+"="+ intDataNum +"="+intDataLastNum +"="+float_A);
                        if (intDataNum == intDataLastNum) {

                            if (float_A > (floatDataLastH)) {
                                //intViewHRest+=(float_A-(float_B - 144));
                                float_A = floatDataLastH;
                                //float_A -= floatDataNowH;

                            }
                            if (float_A < 0) {
                                if (intDataNum != 0) {
                                    intDataNum--;
                                    float_B = getSnapIntNum(intDataNum).floatH;
                                    float_A = float_B + float_A;
                                    float_A = repsMoveView(float_A);
                                } else {
                                    //intViewHRest+=float_A;
                                    float_A = 0;
                                }
                            }

                        }else if (float_A < 0) {
                            if (intDataNum != 0) {
                                intDataNum--;
                                float_B =  getSnapIntNum(intDataNum).floatH;
                                float_A = float_B + float_A;
                                float_A = repsMoveView(float_A);
                            } else {
                                //intViewHRest+=float_A;
                                float_A = 0;
                            }

                        }else if (float_A > float_B) {
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
                        LogM.w("WinRecV.moveView: E0"+"="+ intDataNum +"="+intDataLastNum +"="+floatDataNowH +"="+float_Move +"="+((Object)this).toString());
                        LogM.w("WinRecV.moveView: E1" +"="+floatDataNowH +"="+floatDataLastH +"="+intDataNum +"="+intDataLastNum);
                        floatDataNowH = float_A;
                        invalidate();
                    }else {
                    winRecNewsTextDraw.moveView(float_Move);
                }

            }

        }
        @Override
        float repsMoveView(float float_Move) {
            float float_A=float_Move;
            float float_B=getSnapIntNum(intDataNum).floatH;
            if(float_A<0)
            {
                if(intDataNum!=0)
                {
                    intDataNum--;
                    float_B=getSnapIntNum(intDataNum).floatH;
                    float_A=float_B+float_A;
                    return repsMoveView(float_A);
                }else {
                    float_A=0;
                }

            }else
            {
                if(intDataNum==intDataLastNum)
                {
                    if (float_A > (floatDataLastH)) {
                        //intViewHRest+=(float_A-(float_B - 144));
                        float_A = floatDataLastH;
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
        @Override
        public void touchView(MotionEvent event){
            if(boolDrawOK)
                {

                    if(intDrawMode==0)
                    {
                        //]Log.w(this.toString(), "WinRecV.touchView: E0"+"="+(event.getX()>30&&event.getX()<width-30) +"="+event.getX() +"="+event.getY() );
                        if(event.getX()>30&&event.getX()<width-30)
                        {
                            float float_G=10-floatDataNowH;
                            for(int i = intDataNum; i<intNSize; i++)
                            {
                                WindowRecycDrawNewsDr.WinRecycNewsP.WinRecycNewsD winRecycNewsD_A=getSnapIntNum(i);

                                if(winRecycNewsD_A!=null)
                                {

                                    float float_G2=float_G+winRecycNewsD_A.floatH+0;
                                    if(event.getX()>float_G+30)
                                    {
                                        if(event.getX()<float_G2+30)
                                        {
                                            openWinRecNewsTextView(winRecycNewsD_A);
                                        }


                                    }else {
                                        break;
                                    }
                                    float_G=float_G2;
                                }

                                if(float_G>height)
                                {
                                    break;
                                }
                            }
                        }
                    }else {

                    }
                }



        }
        float floatDownX,floatDownY;

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            ccScroller(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    stopScroller();
                    floatDownX=event.getX();
                    floatDownY=event.getY();
                    floatTouchDownX =event.getX();
                    floatTouchDownY =event.getY();
                    longTouchTime=System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float float_A= floatTouchDownX -event.getX();
                    float float_B=floatTouchDownY-event.getY();
                    floatTouchDownX =event.getX();
                    floatTouchDownY=event.getY();
                    moveView(float_B,0);

                    break;
                case MotionEvent.ACTION_UP:
                    //]Log.w(this.toString(), "WinRecV.onTouchEvent: E0"+"="+(System.currentTimeMillis()<longTouchTime+500)+"="+((spacing(floatTouchDownX,floatTouchDownY,event.getX(),event.getY())<10)) +"="+spacing(floatTouchDownX,floatTouchDownY,event.getX(),event.getY()) );
                    //]Log.w(this.toString(), "WinRecV.onTouchEvent: E0"+"="+floatTouchDownX +"="+floatTouchDownY +"="+event.getX() +"="+event.getY());
                    //]Log.w(this.toString(), "WinRecV.onTouchEvent: E2"+"="+spacing(floatTouchDownX,floatTouchDownY,event.getX(),event.getY()) );
                    if(System.currentTimeMillis()<longTouchTime+200&&(spacing(floatDownX,floatDownY,event.getX(),event.getY())<10))
                    {
                        touchView(event);

                    }else {

                        startScroller(floatTouchDownX,floatTouchDownY);
                    }
                    break;
                default:
                    break;
            }
            return false;
        }

        @Override
        public void computeScroll(float float_A) {
            super.computeScroll(float_A);
            //]Log.w(this.toString(), "IntegratedNewsView.computeScroll: "+"="+float_A );
            moveView(floatTouchDownY-float_A,0);
            floatTouchDownY=mScroller.getCurrY();
            // invalidate();

        }

        @Override
        public void onUpdateSnapNewsData(SnapNewsDataTool.SnapNewsDataJ[] snapNewsDataJS,TimeOD timeOD_A) {
            windowRecycDrawNewsDr.addSnapNewsData(snapNewsDataJS,timeOD_A);

            intNSize+=snapNewsDataJS.length;
            boolDrawOK=true;
            if(intDataLastNum ==-1)
                {
                    ccViewH();
                    if(intDataLastNum!=-1)
                        {

                    intDataNum=intDataLastNum;
                    floatDataNowH=floatDataLastH;
                        }
                }else {

                intDataLastNum+=snapNewsDataJS.length;
                intDataNum +=snapNewsDataJS.length;
            }
            //]Log.w(this.toString(), "onUpdateSnapCalendarData: E0"+"="+ intDataNum +"="+intNSize +"="+intDataLastNum );
            invalidate();

        }

        @Override
        public void onPlanningSnapNewsTime(TimeOD[] timeODS_A) {
            if(timeODS_A.length==2)
                {
                    windowRecycDrawNewsDr.updateNewSnapNewsDay(timeODS_A[0],timeODS_A[1]);
                    int int_A=windowRecycDrawNewsDr.winRecycNewsPA.winRecycNewsDS.size()+windowRecycDrawNewsDr.winRecycNewsPB.winRecycNewsDS.size();
                    intNSize=int_A;
                    if(intDataNum >(int_A-1))
                        {
                            intDataNum =int_A-1;

                        }
                }

        }

        public class WindowRecycDrawNewsDr{
            WinRecycNewsP winRecycNewsPA,winRecycNewsPB;
            public WindowRecycDrawNewsDr(TimeOD timeOD_A,TimeOD timeOD_B){
                winRecycNewsPA=new WinRecycNewsP(timeOD_A);
                winRecycNewsPB=new WinRecycNewsP(timeOD_B);
            }
            public void updateNewSnapNewsDay(TimeOD timeOD_A,TimeOD timeOD_B){
                WinRecycNewsP winRecycNewsP_C=null;
                int int_A=0;
                if(!winRecycNewsPA.timeOD.PuC_isTimeODSameDay(timeOD_A))
                    {
                        if(winRecycNewsPA.timeOD.PuC_isTimeODSameDay(timeOD_B))
                            {
                                winRecycNewsP_C=winRecycNewsPA;
                            }
                        winRecycNewsPA=new WinRecycNewsP(timeOD_A);

                    }

                    if(!winRecycNewsPB.timeOD.PuC_isTimeODSameDay(timeOD_B))
                    {
                        if(winRecycNewsP_C!=null)
                        {
                            winRecycNewsPB=winRecycNewsP_C;
                        }else {

                            winRecycNewsPB=new WinRecycNewsP(timeOD_B);
                        }

                    }

            }
            public void addSnapNewsData(SnapNewsDataTool.SnapNewsDataJ[] snapNewsDataJS_A,TimeOD timeOD_A){
                if(winRecycNewsPA.timeOD.PuC_isTimeODSameDay(timeOD_A))
                    {
                        winRecycNewsPA.addSnapNewsData(snapNewsDataJS_A);

                    } else if(winRecycNewsPB.timeOD.PuC_isTimeODSameDay(timeOD_A))
                        {

                            winRecycNewsPB.addSnapNewsData(snapNewsDataJS_A);
                        }

            }
            public class WinRecycNewsP{
                public TimeOD timeOD;
                public ArrayList<WinRecycNewsD> winRecycNewsDS;
                public WinRecycNewsP(TimeOD timeOD_A){
                    timeOD=timeOD_A;
                    winRecycNewsDS=new ArrayList<>();
                }
                void addSnapNewsData(SnapNewsDataTool.SnapNewsDataJ[] snapNewsDataJS_A){

                    for(int i=snapNewsDataJS_A.length-1;i>=0;i--)
                        {
                            winRecycNewsDS.add(0,new WinRecycNewsD(snapNewsDataJS_A[i],winRecycNewsDS.size()));
                        }
                }
                public class WinRecycNewsD{
                    public SnapNewsDataTool.SnapNewsDataJ snapNewsDataJ;
                    public float floatH;
                    public String stringTitle;
                    public String[] stringsText;
                    public String stringTime;

                    public WinRecycNewsD(SnapNewsDataTool.SnapNewsDataJ snapNewsDataJ_A){
                        snapNewsDataJ=snapNewsDataJ_A;
                        //]Log.w(this.toString(), "WinRecycNewsD: E0"+"="+snapNewsDataJ.newsDataClass.stringTitle );
                        stringTitle= TextStringTool.getTitleAdvances(snapNewsDataJ.newsDataClass.stringTitle,paintTitle,floatTextW);
                        floatH+=125;
                        stringsText=TextStringTool.getTextAdvancesC(snapNewsDataJ.newsDataClass.stringText,paintText,floatTextW);
                        floatH+=(30*stringsText.length);
                    }
                    public WinRecycNewsD(SnapNewsDataTool.SnapNewsDataJ snapNewsDataJ_A,int int_A){
                        snapNewsDataJ=snapNewsDataJ_A;
                        //]Log.w(this.toString(), "WinRecycNewsD: E0"+"="+snapNewsDataJ.newsDataClass.stringTitle );
                        stringTitle= "["+int_A+"]"+TextStringTool.getTitleAdvances(snapNewsDataJ.newsDataClass.stringTitle,paintTitle,floatTextW);
                        floatH+=125;
                        stringsText=TextStringTool.getTextAdvancesC(snapNewsDataJ.newsDataClass.stringText,paintText,floatTextW);
                        floatH+=(30*stringsText.length);
                    }

                }
            }

        }
        class WinRecNewsTextDraw{

            float floatViewH;
            float floatLastH;
            NewsDraw[] newsDraws;
            public WinRecNewsTextDraw(WindowRecycDrawNewsDr.WinRecycNewsP.WinRecycNewsD winRecycNewsD_A){
                initData(winRecycNewsD_A.snapNewsDataJ.newsDataClass);
                intTDra=30;
                floatViewH=0;
                floatLastH=-1;
            }
            void StartDraw(Canvas canvas_A,float float_W,float float_H){
                for(int i=0;i<newsDraws.length;i++)
                {
                    newsDraws[i].draw(canvas_A,0,float_H-floatViewH);

//                    if((float_H-intViewH)>(height-133))
//                    {
//                        //]Log.w(this.toString()   , "KJUStartDrawS1: "+"="+ i +"="+float_H +"="+ (height-133)+"="+intFloatArrayLast   );
//                        break;
//                    }
                    float_H+=newsDraws[i].viewH;
                }
            }
            void moveView(float float_A){
                if(floatLastH!=-1)
                    {
                        floatViewH+=float_A;
                        if(floatViewH<0)
                            {
                                floatViewH=0;
                            }
                        if(floatViewH>floatLastH)
                            {
                                floatViewH=floatLastH;
                            }

                    }
                invalidate();

            }
            void ccNewsDrawViewH(){
                float float_H=0;
                //intFloatArrayLast=-1;
                floatLastH=-1;
                for(int i=newsDraws.length-1;i>=0;i--)
                {

                    float_H+=newsDraws[i].viewH;



                }
                if(float_H>height-3)
                {
                    floatLastH=float_H-(height-3);
                    //]Log.w(this.toString(), "ccNewsDrawViewH: E0"+"="+ floatLastH);
                    //intFloatArrayLast=i-1;
                    //floatNewsLastH=(height-133)-float_H;
                }
            }
            int intTDra;

            void initData(NewsDataClass newsDataClass_A){
                newsDraws=new NewsDraw[newsDataClass_A.naks.size()];
                // intFloatArrayNum=0;
                float float_H=0;
                for(int i=0;i<newsDataClass_A.naks.size();i++)
                {
                    NewsDraw newsDraw_A=null;
                    //]Log.w(this.toString(), "NewsClassDrawD1: "+"="+newsDataClass_A.naks.get(i).intMode );
                    switch(newsDataClass_A.naks.get(i).intMode){
                        case 0:
                            newsDraw_A=new NewsDrawTitle(newsDataClass_A.naks.get(i).stringText);
                            break;
                        case 1:
                            newsDraw_A=new NewsDrawText(newsDataClass_A.naks.get(i).stringText);
                            break;
                        case 2:
                            newsDraw_A=new NewsDrawTextTit(newsDataClass_A.naks.get(i).stringText);
                            break;
                        case 3:
                            newsDraw_A=new NewsDrawTextTit(newsDataClass_A.naks.get(i).stringText);
                            break;
                        case 4:
                            newsDraw_A=new NewsDrawImg(newsDataClass_A.naks.get(i).stringText);
                            break;
                        case 5:
                            newsDraw_A=new NewsDrawImgText(newsDataClass_A.naks.get(i).stringText);
                            break;
                        case 6:
                            newsDraw_A=new NewsDrawTitle(newsDataClass_A.naks.get(i).stringText);
                            break;
                        case 7:
                            newsDraw_A=new NewsDrawTime(newsDataClass_A.naks.get(i).stringText);
                            break;
                        default:
                            break;
                    }
                    newsDraws[i]=newsDraw_A;
                    float_H+=newsDraw_A.viewH;


                }
                ccNewsDrawViewH();
                //]Log.w(this.toString(), "NewsClassDrawD0: "+"="+float_H  +"="+height);
//            stringTitle= TextStringTool.getTextAdvancesB(dataClass.stringTitle,paintTitle,width-150);
//            stringsText=TextStringTool.getTextAdvancesB(dataClass.stringText,paintText,width-150);
//            stringTime=dataClass.timeODNow.intsA[0]+"-"+dataClass.timeODNow.intsA[2]+"-"+dataClass.timeODNow.intsA[3];
            }
            class NewsDraw{
                public float viewH;
                public int intMode;
                public NewsDraw(String string_Data){

                }
                public void draw(Canvas canvas,float width_A,float height_A){

                }
            }
            class NewsDrawTitle extends NewsDraw {
                String[] stringsTitle;
                public NewsDrawTitle(String string_Data) {
                    super(string_Data);
                    stringsTitle= TextStringTool.getTextAdvancesC(string_Data,paintTitle,floatTextW);
                    viewH=30+(40*stringsTitle.length);
                }
                @Override
                public void draw(Canvas canvas,float width_A,float height_A){
                    for(int i=0;i<stringsTitle.length;i++)
                    {
                        canvas.drawText(stringsTitle[i],intTDra,70+height_A+(i*40),paintTitle);
                    }


                }

            }
            class NewsDrawText extends NewsDraw {
                String[] stringsText;
                public NewsDrawText(String string_Data) {
                    super(string_Data);
                    stringsText=TextStringTool.getTextAdvancesC(string_Data,paintText,floatTextW);
                    viewH=(37*stringsText.length)+30;
                }
                @Override
                public void draw(Canvas canvas,float width_A,float height_A){
                    for(int i=0;i<stringsText.length;i++)
                    {
                        canvas.drawText(stringsText[i],intTDra,37+height_A+(i*37),paintText);
                    }

                }
            }
            class NewsDrawTextTit extends NewsDraw {
                String[] stringsTextTit;
                public NewsDrawTextTit(String string_Data) {
                    super(string_Data);
                    stringsTextTit=TextStringTool.getTextAdvancesC(string_Data,paintTitle,floatTextW);
                    viewH=(30*stringsTextTit.length);
                }
                @Override
                public void draw(Canvas canvas,float width_A,float height_A){

                }
            }
            class NewsDrawTime extends NewsDraw {
                String stringTime;
                boolean boolLive;
                public NewsDrawTime(String string_Data) {
                    super(string_Data);
                    stringTime=string_Data;
                    viewH=40;
                }
                @Override
                public void draw(Canvas canvas,float width_A,float height_A){
                    canvas.drawText(stringTime,intTDra+100,height_A+40,paintTime);

                }
            }
            class NewsDrawImg extends NewsDraw {
                String stringUrl;
                Bitmap bitmap;
                boolean boolBitmapOK;
                public NewsDrawImg(String string_Data) {
                    super(string_Data);
                    stringUrl=string_Data;
                    initDataView();
                }
                void initDataView(){
                    if(boolBitmapOK)
                    {
                        viewH=bitmap.getHeight()+10;
                        ccNewsDrawViewH();
                        //ccViewH();
                        invalidate();
                    }else {
                        viewH=30;
                        getHttpImg();
                    }
                }
                @Override
                public void draw(Canvas canvas, float width_A, float height_A){
                    if(boolBitmapOK)
                    {
                        canvas.drawBitmap(bitmap,intTDra,height_A,paintTitle);
                    }else {
                        canvas.drawRect(intTDra,height_A+5,intTDra+20,height_A+25,paintTitle);

                    }


                }
                void getHttpImg(){
                    OkHttpClient okHttpClient = new OkHttpClient();
                    //2.创建Request.Builder对象，设置参数，请求方式如果是Get，就不用设置，默认就是Get
                    Request request = new Request.Builder()
                            .url(stringUrl)
                            .build();
                    //3.创建一个Call对象，参数是request对象，发送请求
                    Call call = okHttpClient.newCall(request);
                    //4.异步请求，请求加入调度
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            //]Log.w(this.toString(), "onFailure:NewsDrawImg "+"="+false );

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //得到从网上获取资源，转换成我们想要的类型
                            //]Log.w(this.toString(), "onFailure:NewsDrawImg "+"="+true );
                            byte[] Picture_bt = response.body().bytes();
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            bitmap= BitmapFactory.decodeByteArray(Picture_bt, 0, Picture_bt.length);
                            float float_A=((float)bitmap.getWidth())/(floatTextW);
                            //]Log.w(this.toString(), "onFailure:NewsDrawImgS0 "+"="+float_A +"="+ bitmap.getWidth()+"="+bitmap.getHeight());
                            if(true||float_A>1)
                            {
                                float_A=2-float_A;
                                Matrix matrix = new Matrix();
                                matrix.postScale(float_A, float_A);
                                bitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
                            }




                            post(new Runnable() {
                                @Override
                                public void run() {
                                    boolBitmapOK=true;
                                    initDataView();

                                    invalidate();
                                }
                            });
                        }
                    });

                }
            }
            class NewsDrawImgText extends NewsDraw {
                String[] stringImgText;
                boolean boolLive;
                public NewsDrawImgText(String string_Data) {
                    super(string_Data);
                    stringImgText=TextStringTool.getTextAdvancesC(string_Data,paintImgText,floatTextW);
                    viewH=(40*stringImgText.length)+30;
                }
                @Override
                public void draw(Canvas canvas,float width_A,float height_A){
                    //]Log.w(this.toString(), "NewsDrawImgText: G0"+"="+width_A +"="+height_A );
                    for(int i=0;i<stringImgText.length;i++)
                    {
                        canvas.drawText(stringImgText[i],floatTextW/2F,40+height_A+(i*40),paintImgText);
                    }

                }
            }
        }
    }
    public class WindowRecycDrawCalendar extends WindowRecycDrawClass implements OnUpdateSnapCalendarData {
        Paint paintUI;
        SnapCalendarDataTool snapCalendarDataTool;
        WinRecycDrawCalendarPool[] winRecycDrawCalendarPools;
        int intTabNum;
        public WindowRecycDrawCalendar(FXDataConnect fxDataConnect_A,float floatTextW_A){
            paintUI=new Paint();
            paintUI.setColor(Color.WHITE);

            snapCalendarDataTool=fxDataConnect_A.getCalendarConnect().getSnapCalendarDataTool(this);

        }
        @Override
        public void Draw(Canvas canvas,float float_W,float float_H){
          //  canvas.drawRect(0,0,width,height,paintUI);


        }
        @Override
        void ccViewH(){

        }
        @Override
        public void setAnimFloat(float float_A){

        }
        @Override
        void moveView(float float_A,int int_A){

        }
        @Override
        float repsMoveView(float float_Move) {
            return 0;
        }
        @Override
        public void touchView(MotionEvent event){

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return false;
        }

        @Override
        public void onUpdateSnapCalendarData(SnapCalendarDataTool.SnapCalendarDataJ[] snapCalendarDataJS_A, TimeOD timeOD_A) {

        }

        @Override
        public void onPlanningSnapNewsTime(TimeOD[] timeODS_A) {

        }

        public class  WinRecycDrawCalendarPool{
            public TimeOD timeOD;
            public ArrayList<WinRecycCalendarData> winRecycCalendarData;
            public WinRecycDrawCalendarPool(){

            }
            public void addData(CalendarDataClass[] calendarDataClasses_A){

            }
            public void updateData(){

            }
            public class WinRecycCalendarData{
                public WinRecycCalendarData(CalendarDataClass calendarDataClass_A){

                }

            }

        }
    }
}
