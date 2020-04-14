package com.example.app_005.View;

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
import com.example.app_005.Class.SkillNewsName;
import com.example.app_005.Internet.Jin10CalendarCollectTool;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.TextStringTool;
import com.example.app_005.Tool.TimeOD;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IntegratedTextView extends View {

    Paint paintUI;
    Paint paintP;
    Paint paintP2;
    Paint paintTitle;
    Paint paintTitleB;
    Paint paintTime;
    Paint paintText;
    Paint paintImgText;

    int width,height;
    boolean boolDrawOK;
    InteNewsClassDraw inteNewsClassDraw;
    CalendarDrawData calendarDrawData;
    int intDrawM;
    public IntegratedTextView(Context context) {
        super(context);
    }

    public IntegratedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intDrawM=-1;
        paintUI=new Paint();
        paintUI.setAntiAlias(true);//消除锯齿
        paintUI.setColor(Color.BLACK);//设置画笔颜色//#83DAD7D7
        paintUI.setAlpha(100);
        paintUI.setStyle(Paint.Style.FILL);
        paintUI.setStrokeWidth(10);//设置宽度

        paintP=new Paint();
        paintP.setColor(Color.WHITE);//设置画笔颜色//#83DAD7D7
        paintP.setStyle(Paint.Style.FILL);
        paintP.setStrokeWidth(10);//设置宽度

        paintP2=new Paint();
        paintP2.setColor(Color.BLACK);//设置画笔颜色//#83DAD7D7
        paintP2.setStyle(Paint.Style.FILL);
        paintP2.setAlpha(60);


        paintTitle = new Paint();
        paintTitle.setAntiAlias(true);//消除锯齿
        paintTitle.setColor(Color.BLACK);
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintTitle.setTextAlign(Paint.Align.LEFT);
        paintTitle.setTextSize(37);


        paintTitleB = new Paint();
        paintTitleB.setAntiAlias(true);//消除锯齿
        paintTitleB.setColor(Color.BLACK);
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintTitleB.setTextAlign(Paint.Align.CENTER);
        paintTitleB.setTextSize(37);

        paintText = new Paint();
        paintText.setAntiAlias(true);//消除锯齿
        paintText.setColor(Color.BLACK);
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintText.setTextAlign(Paint.Align.LEFT);
        paintText.setTextSize(30);

        paintImgText = new Paint();
        paintImgText.setAntiAlias(true);//消除锯齿
        paintImgText.setColor(Color.BLACK);
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintImgText.setTextAlign(Paint.Align.CENTER);
        paintImgText.setTextSize(30);

        paintTime = new Paint();
        paintTime.setAntiAlias(true);//消除锯齿
        paintTime.setColor(Color.parseColor("#FF7C7B7B"));
        paintTime.setTextAlign(Paint.Align.LEFT);
        //paintNewsTextTime.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paintTime.setTextSize(20);
        paintTime.setSubpixelText(true);

        jin10CalendarCollectTool=new Jin10CalendarCollectTool();
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
        drawUI(canvas);
        if(boolDrawOK)
            {
                switch(intDrawM){
                    case 0:

                        inteNewsClassDraw.StartDraw(canvas);
                        break;
                    case 1:
                        calendarDrawData.onDraw(canvas);
                        break;
                    default:
                        break;
                }
            }
    }
    public void drawUI(Canvas canvas){
        canvas.drawRect(0,0,width,height,paintUI);
        canvas.drawRoundRect(70-1,200-2,width-70+3,height-100+3,20,20,paintP2);
        canvas.drawRoundRect(70,200,width-70,height-100,20,20,paintP);

    }
    public void setIntegratedNewsData(NewsDataClass newsData_A){
        //]Log.w(this.toString(), "setIntegratedNewsData: T0"+"="+newsData_A.stringTitle );

        if(newsData_A!=null)
            {
                intDrawM=0;
                boolDrawOK=true;
                inteNewsClassDraw=new InteNewsClassDraw(newsData_A,width,height);
                ccViewH();
            }else {
            boolDrawOK=false;
        }
    }
    public void setIntegratedCalendarData(CalendarDataClass calenderData_A){
       // //]Log.w(this.toString(), "setIntegratedNewsData: T0"+"="+newsData_A.stringTitle );

        if(calenderData_A!=null)
        {
            intDrawM=1;
            boolDrawOK=true;
            calendarDrawData=new CalendarDrawData(calenderData_A);
           // inteNewsClassDraw=new InteNewsClassDraw(newsData_A,width,height);
            ccCalendarViewH();
        }else {
            boolDrawOK=false;
        }
    }
    float floatTouchLashH;
    float floatTouchNow;
    void ccViewH(){
        if(inteNewsClassDraw!=null)
            {
                floatTouchLashH=0;
                floatTouchNow=0;
                for(int i=0;i<inteNewsClassDraw.newsDraws.length;i++)
                    {
                        floatTouchLashH+=inteNewsClassDraw.newsDraws[i].viewH;
                    }
                //]Log.w(this.toString(), "inteNewsClassDraw.ccViewH: "+"="+floatTouchLashH +"="+height );
                floatTouchLashH-=(height-320);
                if(floatTouchLashH<0)
                    {
                        floatTouchLashH=-1;
                    }
                invalidate();

            }
    }

    void ccCalendarViewH(){
        if(calendarDrawData!=null)
        {
            floatTouchLashH=0;
            float float_W=110;
            float float_R=50+(width-150)/2F;
            for(int i=0;i<calendarDrawData.stringNameX.length;i++)
            {
                float_W+=(60);
            }
            float_W+=30;
            float_W+=80;
            float_W+=100;
            float_W+=50;

            if(calendarDrawData.boolHisOK)
            {
                float float_T=(width-150)/(calendarDrawData.calendarJinText.calendarJinHistories.length+1F);
                float float_E=350;



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

            floatTouchLashH=float_W-(height-320);

            if(floatTouchLashH<0)
            {
                floatTouchLashH=-1;
            }
            invalidate();
        }else {
            floatTouchLashH=-1;
        }
    }

    void moveView(float float_A){
        switch(intDrawM){
            case 0:

                if(boolDrawOK)
                {
                    if(floatTouchLashH!=-1)
                    {
                        floatTouchNow+=float_A;
                        if(floatTouchNow<0)
                        {
                            floatTouchNow=0;
                        }else if(floatTouchNow>floatTouchLashH){
                            floatTouchNow=floatTouchLashH;
                        }
                        inteNewsClassDraw.setFloatViewH(floatTouchNow);
                        invalidate();

                    }
                }
                break;
            case 1:
                if(floatTouchLashH==-1)
                {
                    floatTouchNow=0;
                    invalidate();
                }else {
                    floatTouchNow-=float_A;
                    //]Log.w(this.toString(), "DFGmoveView: R0"+"="+floatTouchNow );
                    if(floatTouchNow<0)
                    {
                        floatTouchNow=0;
                    }else
                    if(floatTouchNow>floatTouchLashH)
                    {
                        floatTouchNow=floatTouchLashH;
                    }
                    invalidate();
                }
                break;
            default:
                break;
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
                startScroller(0,floatTouchDownY);
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

    public class InteNewsClassDraw {
        NewsDataClass newsDataClass;
        String[] stringTitle;
        String[] stringsText;
        String stringTime;
        SkillNewsName skillNewsName;
        int width,height;
        int intMode;
        float floatViewH;
        float floatLastH;

        NewsDraw[] newsDraws;

        public InteNewsClassDraw(NewsDataClass newsDataClass_A, int width_A, int height_A){
            newsDataClass=newsDataClass_A;
            width=width_A;
            height=height_A;
            initData();

        }
        void initData(){
            newsDraws=new NewsDraw[newsDataClass.naks.size()];
           // intFloatArrayNum=0;
            float float_H=0;
            for(int i=0;i<newsDataClass.naks.size();i++)
            {
                NewsDraw newsDraw_A=null;
                //]Log.w(this.toString(), "NewsClassDrawD1: "+"="+newsDataClass.naks.get(i).intMode );
                switch(newsDataClass.naks.get(i).intMode){
                    case 0:
                        newsDraw_A=new NewsDrawTitle(newsDataClass.naks.get(i).stringText);
                        break;
                    case 1:
                        newsDraw_A=new NewsDrawText(newsDataClass.naks.get(i).stringText);
                        break;
                    case 2:
                        newsDraw_A=new NewsDrawTextTit(newsDataClass.naks.get(i).stringText);
                        break;
                    case 3:
                        newsDraw_A=new NewsDrawTextTit(newsDataClass.naks.get(i).stringText);
                        break;
                    case 4:
                        newsDraw_A=new NewsDrawImg(newsDataClass.naks.get(i).stringText);
                        break;
                    case 5:
                        newsDraw_A=new NewsDrawImgText(newsDataClass.naks.get(i).stringText);
                        break;
                    case 6:
                        newsDraw_A=new NewsDrawTitle(newsDataClass.naks.get(i).stringText);
                        break;
                    case 7:
                        newsDraw_A=new NewsDrawTime(newsDataClass.naks.get(i).stringText);
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
        public void setFloatViewH(float float_A){
            //]Log.w(this.toString(), "setFloatViewH: R0"+"="+ floatViewH);
            floatViewH=float_A;
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
                //intFloatArrayLast=i-1;
                //floatNewsLastH=(height-133)-float_H;
                ////]Log.w(this.toString(), "ccNewsDrawViewH: "+"="+i +"="+float_H +"="+(height-133) );
                floatLastH=float_H-(height-3);
            }
        }
        public void StartDraw(Canvas canvas){
            if(true)
            {
               // drawUI(canvas);
                canvas.save();
               // canvas.drawRoundRect(70,200,width-70,height-100,20,20,paintP);
                canvas.clipRect(70,200,width-70,height-100);
                //]Log.w(this.toString(), "KJUStartDrawS0: "+"="+newsDraws.length );
                float float_H=220;
                //int int_A=intFloatArrayNum;
                //float_H-=intViewH;
                for(int i=0;i<newsDraws.length;i++)
                {
                    newsDraws[i].draw(canvas,0,float_H- floatViewH);
                    //]Log.w(this.toString(), "IntegratedTextView.StartDraw: R0"+"="+(float_H- floatViewH)+"="+ floatViewH);

//                    if((float_H-intViewH)>(height-133))
//                    {
//                        //]Log.w(this.toString()   , "KJUStartDrawS1: "+"="+ i +"="+float_H +"="+ (height-133)+"="+intFloatArrayLast   );
//                        break;
//                    }
                    float_H+=newsDraws[i].viewH;
                }
                canvas.restore();
                // drawOurUI(canvas);
            }
            ////drawUI(canvas);
            //drawTitle(canvas,stringTitle,stringsText,stringTime);
        }

        public void onTouch(MotionEvent event){

        }
        public void drawUI(Canvas canvas){
            canvas.drawRect(0,0,width,height,paintUI);
            canvas.drawRoundRect(50,30,width-50,height-100,20,20,paintP);

        }
        public void drawOurUI(Canvas canvas){
            canvas.drawRoundRect(width-90,70,width-20,180,25,25,paintTime);
        }
        public void drawTitle(Canvas canvas,String[] stringTitle,String[] stringText,String stringTime){
            float float_H=30;
            for(int i=0;i<stringTitle.length;i++)
            {
                float_H+=40;
                canvas.drawText(stringTitle[i],90,float_H,paintTitle);
            }
            float_H+=30;
            canvas.drawText(stringTime,90,float_H,paintTime);
            float_H+=10;
            for(int i=0;i<stringText.length;i++)
            {
                float_H+=30;
                canvas.drawText(stringText[i],90,float_H,paintText);
            }


        }
        class NewsDraw{
            public float viewH;
            public int intMode;
            public NewsDraw(String string_Data){

            }
            public void draw(Canvas canvas,float width_A,float height_A){

            }
        }
        class NewsDrawTitle extends InteNewsClassDraw.NewsDraw {
            String[] stringsTitle;
            public NewsDrawTitle(String string_Data) {
                super(string_Data);
                stringsTitle= TextStringTool.getTextAdvancesC(string_Data,paintTitle,width-200);
                viewH=30+(40*stringsTitle.length);
            }
            @Override
            public void draw(Canvas canvas,float width_A,float height_A){
                for(int i=0;i<stringsTitle.length;i++)
                {
                    canvas.drawText(stringsTitle[i],90,70+height_A+(i*40),paintTitle);
                }


            }

        }
        class NewsDrawText extends InteNewsClassDraw.NewsDraw {
            String[] stringsText;
            public NewsDrawText(String string_Data) {
                super(string_Data);
                stringsText=TextStringTool.getTextAdvancesC(string_Data,paintText,width-200);
                viewH=(37*stringsText.length)+30;
            }
            @Override
            public void draw(Canvas canvas,float width_A,float height_A){
                for(int i=0;i<stringsText.length;i++)
                {
                    canvas.drawText(stringsText[i],90,37+height_A+(i*37),paintText);
                }

            }
        }
        class NewsDrawTextTit extends NewsDraw {
            String[] stringsTextTit;
            public NewsDrawTextTit(String string_Data) {
                super(string_Data);
                stringsTextTit=TextStringTool.getTextAdvancesC(string_Data,paintTitle,width-200);
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
                canvas.drawText(stringTime,90,height_A+40,paintTime);

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
                    ccViewH();
                }else {
                    viewH=30;
                    getHttpImg();
                }
            }
            @Override
            public void draw(Canvas canvas, float width_A, float height_A){
                if(boolBitmapOK)
                {
                    canvas.drawBitmap(bitmap,90,height_A,paintTitle);
                }else {
                    canvas.drawRect(90,height_A+5,90+20,height_A+25,paintTitle);

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
                        float float_A=((float)bitmap.getWidth())/(width-200);
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
                stringImgText=TextStringTool.getTextAdvancesC(string_Data,paintImgText,width-200);
                viewH=(40*stringImgText.length)+30;
            }
            @Override
            public void draw(Canvas canvas,float width_A,float height_A){
                //]Log.w(this.toString(), "NewsDrawImgText: G0"+"="+width_A +"="+height_A );
                for(int i=0;i<stringImgText.length;i++)
                {
                    canvas.drawText(stringImgText[i],570,40+height_A+(i*40),paintImgText);
                }

            }
        }

    }

    Jin10CalendarCollectTool jin10CalendarCollectTool;
    public class CalendarDrawData{
        public CalendarDataClass calenderData;
       // EconomicCalendarView.EconCalendarData econCalendarData;
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
        Paint paintTextMore;
        Paint paintLine;
        Paint paintLineY;
        Paint paintLineBP;
        Paint paintTextHis;

        public CalendarDrawHistory[] calendarDrawHistories;
        Jin10CalendarCollectTool.CalendarJinText calendarJinText;
        boolean boolHisOK;
        boolean boolDrawOK;

        public CalendarDrawData(CalendarDataClass calenderData_A){
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

            paintTextHis = new Paint();
            paintTextHis.setAntiAlias(true);//消除锯齿
            paintTextHis.setColor(Color.BLACK);
            paintTextHis.setTextAlign(Paint.Align.CENTER);
            paintTextHis.setTextSize(18);

            calenderData=calenderData_A;
            stringName=calenderData.stringName;
            stringNameX= TextStringTool.getTextAdvancesB(stringName,paintTitle,width-180);

            stringData0=calenderData.stringActual;//.stringActual;
            stringData0=(stringData0==null?"":stringData0);
            stringData1=calenderData.stringForecast;
            stringData1=(stringData1==null?"":stringData1);
            stringData2=calenderData.stringPrevious;
            stringData2=(stringData2==null?"":stringData2);

            calenderData=calenderData_A;
            boolDrawOK=true;
            ccCalendarViewH();
            getCalendarDrawHistory();
        }
//        public CalendarDrawData(EconomicCalendarView.EconCalendarData econCalendarData_A){
//            synchronized ("EconCalendarData"){
//                econCalendarData=econCalendarData_A;
//            }
//
//            stringName=econCalendarData.calendarJinDH.stringFlag+econCalendarData.calendarJinDH.stringTimePeriod+econCalendarData.calendarJinDH.stringName;
//            stringNameX= TextStringTool.getTextAdvancesB(stringName,paintTitle,width-180);
//            stringActual=econCalendarData.stringActual;//.stringActual;
//            stringForecast=econCalendarData.stringForecast;
//            stringPrevious=econCalendarData.stringPrevious;
//            this.boolAc=econCalendarData.boolAc;
//            calendarDrawHistories=new CalendarDrawHistory[0];
//            getCalendarDrawHistory();
//        }
        protected void onDraw(Canvas canvas)
        {
            if(true)
            {
//                canvas.save();
//                // canvas.drawRoundRect(70,200,width-70,height-100,20,20,paintP);
//                canvas.clipRect(70,200,width-70,height-100);
//                //]Log.w(this.toString(), "KJUStartDrawS0: "+"="+newsDraws.length );
//                float float_H=220;
//                //int int_A=intFloatArrayNum;
//                //float_H-=intViewH;
//                for(int i=0;i<newsDraws.length;i++)
//                {
//                    newsDraws[i].draw(canvas,0,float_H- floatViewH);
//                    //]Log.w(this.toString(), "IntegratedTextView.StartDraw: R0"+"="+(float_H- floatViewH)+"="+ floatViewH);
//
////                    if((float_H-intViewH)>(height-133))
////                    {
////                        //]Log.w(this.toString()   , "KJUStartDrawS1: "+"="+ i +"="+float_H +"="+ (height-133)+"="+intFloatArrayLast   );
////                        break;
////                    }
//                    float_H+=newsDraws[i].viewH;
//                }
//                canvas.restore();
//
//                canvas.drawRect(0,0,width,height,paintUI);
//                // canvas.drawRoundRect(30,100,width-27,height-27,20,20,paintLineBP);
//                canvas.drawRoundRect(30,100,width-30,height-30,20,20,paintP);
                if(boolDrawOK)
                {
                    canvas.save();
                    canvas.clipRect(70,200,width-70,height-100);
                    float float_W=220-floatViewH;
                    float float_R=((width)/2F)-20;
                    for(int i=0;i<stringNameX.length;i++)
                    {
                        float_W+=(60);
                        canvas.drawText(stringNameX[i],float_R,float_W,paintTitleB);
                    }
                    float_W+=30;
                    canvas.drawLine(100,float_W,width-100-20,float_W,paintLine);
                    float_W+=80;
                    canvas.drawRoundRect(float_R-120,float_W-60,float_R+120,float_W+25,20,20,paintLine);
                    canvas.drawRoundRect((float_R-300)-120,float_W-60,(float_R-300)+120,float_W+25,20,20,paintLine);
                    canvas.drawRoundRect((float_R+300)-120,float_W-60,(float_R+300)+120,float_W+25,20,20,paintLine);
                    canvas.drawText(stringData0,float_R-300,float_W,paintTitleB);
                    canvas.drawText(stringData1,float_R,float_W,paintTitleB);
                    canvas.drawText(stringData2,float_R+300,float_W,paintTitleB);

                    float_W+=100;
                    canvas.drawText(calenderData.stringTime,300,float_W,paintTitleB);
                    canvas.drawText(calenderData.intStar +"-Star",width-300-20,float_W,paintTitleB);
                    float_W+=50;

                    if(boolHisOK)
                    {
                        //]Log.w(this.toString(), "CalendarJin10FFe: R0 "+"="+ calendarJinText.boolForecast +"="+ calendarJinText.floatMax +"="+calendarJinText.floatMin);
                        canvas.drawRoundRect(100,float_W,width-100-20,float_W+550,10,10,paintLine);
                        float float_T=(width-150)/(calendarJinText.calendarJinHistories.length+1F);
                        float float_E=350;
                        if(true)
                        {
                            if(calendarJinText.boolForecast)
                            {
                                for(int i=0;i<calendarJinText.calendarJinHistories.length;i++)
                                {
                                    //canvas.drawCircle(50+float_T*(i+1),float_W+20+(float_E*calendarDrawData.calendarDrawHistories[i].floatHisP),20,paintLineY);
                                    canvas.drawRect(65+float_T*(i+1)-30,float_W+450,65+float_T*(i+1)+30,float_W+70+(float_E*calendarDrawHistories[i].floatHisP),paintLineY);
                                    canvas.drawText(calendarDrawHistories[i].stringHisP,65+float_T*(i+1),float_W+70+(float_E*calendarDrawHistories[i].floatHisP)-18,paintLineY);

                                }

                            }
                        }

                        for(int i=0;i<calendarJinText.calendarJinHistories.length;i++)
                        {
                            if(i!=0)
                            {
                                canvas.drawLine(65+float_T*(i),float_W+70+(float_E*calendarDrawHistories[i-1].floatHisA),65+float_T*(i+1),float_W+70+(float_E*calendarDrawHistories[i].floatHisA),paintLine);

                            }
                            // canvas.drawLine(50+float_T*(i+1),float_W+520,50+float_T*(i+1),float_W+480,paintLine);
                            canvas.drawRoundRect(65+float_T*(i+1)-5,float_W+70+(float_E*calendarDrawHistories[i].floatHisA)-5,65+float_T*(i+1)+5,float_W+70+(float_E*calendarDrawHistories[i].floatHisA)+5,5,5,paintLineBP);
                            canvas.drawText(calendarDrawHistories[i].stringHisA,65+float_T*(i+1),float_W+70+(float_E*calendarDrawHistories[i].floatHisA)-18,paintLine);
                            //canvas.drawText(calendarDrawData.calendarDrawHistories[i].stringTime,50+float_T*(i+1),float_W+500,paintText);

                        }

                        canvas.save();
                        for(int i=0;i<calendarJinText.calendarJinHistories.length;i++)
                        {
                            canvas.rotate(30, 65+float_T*(i+1), float_W+500);
                            canvas.drawText(calendarDrawHistories[i].stringTime,65+float_T*(i+1),float_W+500,paintTextHis);
                            canvas.rotate(-30, 65+float_T*(i+1), float_W+500);
                        }

                        canvas.restore();

                        if(stringsTextMore!=null)
                        {
                            float_W+=600;
                            for(int i=0;i<stringsTextMore.length;i++)
                            {
                                float float_Q=float_W;
                                float_W+=30;
                                for(int e=0;e<stringsTextMore[i].length;e++)
                                {
                                    canvas.drawText(stringsTextMore[i][e],100,float_W,paintTextMore);
                                    float_W+=35;
                                }
                                if(stringsTextMore[i].length>0)
                                {
                                    canvas.drawRoundRect(90,float_Q-5,width-120,float_W-15,15,15,paintLine);
                                }
                                float_W+=20;
                            }

                        }
                    }

                    canvas.restore();
                }
            }

        }
        float floatViewH;
        public void setFloatViewH(float float_A){
            //]Log.w(this.toString(), "setFloatViewH: R0"+"="+ floatViewH);
            floatViewH=float_A;
        }
        void ccStringsTextMore(){
            if(calendarJinText!=null)
            {
                ArrayList<String[]> strings_A=new ArrayList<>();
                if(calendarJinText.stringImpact!=null) strings_A.add(TextStringTool.getTextAdvancesB(calendarJinText.stringImpact,paintTextMore,width-240));
                if(calendarJinText.stringMethod!=null) strings_A.add(TextStringTool.getTextAdvancesB(calendarJinText.stringMethod,paintTextMore,width-240));
                if(calendarJinText.stringParaphrase!=null) strings_A.add(TextStringTool.getTextAdvancesB(calendarJinText.stringParaphrase,paintTextMore,width-240));
                if(calendarJinText.stringConcern!=null) strings_A.add(TextStringTool.getTextAdvancesB(calendarJinText.stringConcern,paintTextMore,width-240));
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
                        //EconomicCalendarView.EconCalendarData econCalendarData_A=null;
                        CalendarDataClass calenderData_A=null;
                        synchronized ("EconCalendarData"){
                           // econCalendarData_A=econCalendarData;
                            calenderData_A=calenderData;
                        }
                        if(calenderData_A!=null)
                        {
                            try {
                                //]Log.w(this.toString(), "getCalendarDrawHistory: RA"  +"="+calenderData_A.intID);
                                Jin10CalendarCollectTool.CalendarJinText calendarJinText_A =jin10CalendarCollectTool.getTextCalendar(String.valueOf(calenderData_A.intID));
                                //]Log.w(this.toString(), "getCalendarDrawHistory: R0"+"="+(calendarJinText_A!=null) );

                                if(calendarJinText_A!=null)
                                {

                                    //]Log.w(this.toString(), "getCalendarDrawHistory: R1"+"="+(calendarJinText_A.calendarJinHistories.length) );
                                    CalendarDataClass calenderData_B=null;
                                    synchronized ("EconCalendarData"){
                                        calenderData_B=calenderData;
                                    }
                                    if(calenderData_B!=null&&!boolHisOK)
                                    {
                                        //]Log.w(this.toString(), "getCalendarDrawHistory: R2 "+"="+(calenderData_A.intID==calenderData_A.intID) );
                                        if(calenderData_A.intID==calenderData_B.intID)
                                        {
                                            calendarJinText=calendarJinText_A;
                                            ccCalendarJinHistoryDraw();
                                            ccStringsTextMore();

                                            boolHisOK=true;
                                            ccCalendarViewH();
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
