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

import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Tool.TextStringTool;

import java.util.ArrayList;

public class NewsNewView extends View {
    int width,height;
    Paint paintOne;
    Paint paintOneP;
    Paint paintOneT;
    Paint paintNewsRect;
    Paint paintNewsRectP;
    Paint paintTitle;


    //ArrayList<Float> floatsViewNews;
    //ArrayList<NewsDataClass> DataClasses;
    ArrayList<NewsNewData> newsNewData;

    int intFloatArrayNum,intFloatArrayLast;
    float floatNewsLastH;
    boolean boolViewOK;
    boolean boolDataOK;
    NewsSystemCenter.NewsBreakingPool newsBreakingPool;
    public NewsNewView(Context context) {
        super(context);
    }

    public NewsNewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(width, height);//设置宽和高
        //newsNewData=new ArrayList<>();
        mScroller = new Scroller(getContext(), null, true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mMaxFlintVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMinFlintVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMinFlintVelocity = 600;

        paintOne=new Paint();
        paintOne.setAntiAlias(true);//消除锯齿
        paintOne.setColor(Color.parseColor("#3C3B3B"));//设置画笔颜色//#83DAD7D7
        paintOne.setStyle(Paint.Style.FILL);
        paintOne.setStrokeWidth(50);//设置宽度

        paintOneP=new Paint();
        paintOneP.setAntiAlias(true);//消除锯齿
        paintOneP.setColor(Color.parseColor("#FFE6DEDE"));//设置画笔颜色//#83DAD7D7
        paintOneP.setStyle(Paint.Style.FILL);
        paintOneP.setStrokeWidth(50);//设置宽度

        paintOneT=new Paint();
        paintOneT.setAntiAlias(true);//消除锯齿
        paintOneT.setColor(Color.parseColor("#FFDBDBDB"));//设置画笔颜色//#FFCCCBCB
        paintOneT.setStyle(Paint.Style.FILL);
        paintOneT.setStrokeWidth(50);//设置宽度

        paintNewsRect=new Paint();
        paintNewsRect.setAntiAlias(true);//消除锯齿
        paintNewsRect.setColor(Color.WHITE);//设置画笔颜色
        paintNewsRect.setStyle(Paint.Style.FILL);
        paintNewsRect.setStrokeWidth(10);//设置宽度

        paintNewsRectP=new Paint();
        paintNewsRectP.setAntiAlias(true);//消除锯齿
        paintNewsRectP.setColor(Color.parseColor("#FF929292"));//设置画笔颜色//#83DAD7D7
        paintNewsRectP.setStyle(Paint.Style.FILL);
        paintNewsRectP.setStrokeWidth(10);//设置宽度

        //绘制文字的画笔
        paintTitle = new Paint();
        paintTitle.setAntiAlias(true);//消除锯齿
        paintTitle.setColor(Color.BLACK);
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintTitle.setTextAlign(Paint.Align.LEFT);
        paintTitle.setTextSize(33);

        intFloatArrayNum=0;
        boolViewOK=true;
        ccTextSizeH();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(0+70,0,width-70,height,20,20,paintOneP);
        canvas.drawRoundRect(0+3+70,0,width-3-70,height-3,20,20,paintOne);
        canvas.drawRoundRect(0+10+70,3,width-10-70,height-3,30,30,paintOneT);
        if(boolDataOK)
            {
                canvas.save();
                canvas.clipRect(90,3,width-90,height-3);
                int int_A=intFloatArrayNum;
                float float_W=10;
                for(int i=int_A;i<newsNewData.size();i++)
                    {
                        float float_W2=float_W+newsNewData.get(i).viewH;//100+(30*strings_A.size());
                        String string_A=newsNewData.get(i).stringTitle;

                        canvas.drawRoundRect(90+20,float_W+0-intViewH,width-90+3-20,float_W+3+80-intViewH,8,8,paintNewsRectP);
                        canvas.drawRoundRect(90+20,float_W+0-intViewH,width-90-20,float_W+80-intViewH,8,8,paintNewsRect);
                        canvas.drawText(string_A,90+30,float_W+40-intViewH,paintTitle);
                        if(float_W>height)
                        {
                            break;
                        }
                        float_W=20+float_W2;
                    }

                canvas.restore();
            }
    }
    public void setNewsBreakingPool(NewsSystemCenter.NewsBreakingPool newsBreakingPool_A)
    {
        newsBreakingPool=newsBreakingPool_A;
    }
    void ccTextSizeH(){
        //]Log.w(this.toString(), ":KFMccTextSizeHD0: "+"="+boolDataOK +"="+boolViewOK );
        if(boolDataOK&&boolViewOK)
            {
                //]Log.w(this.toString(), ":KFMccTextSizeHD1: "+"="+newsNewData.size());
            for(int i=0;i<newsNewData.size();i++)
                {
                    newsNewData.get(i).viewH=80;
                }
                float float_M=0;
            for(int i=newsNewData.size()-1;i>=0;i--)
                {
                    float_M+=newsNewData.get(i).viewH;//+20;
                    if(float_M>height-3)
                    {
                        //]Log.w(this.toString(), " OPFccTextSizeHD0: "+"="+ (float_M-height) +"="+i +"="+float_M +"="+height);
                        floatNewsLastH=float_M-height;
                        intFloatArrayLast=i+1;
                        break;
                    }
                    float_M+=20;
                }
            }
//        for(int i=0;i<DataClasses.size();i++)
//        {
//            floatsViewNews.add(100F+20);
////            if(i%2==0)
////            {
////                floatsViewNews.add(200F);
////            }else {
////                floatsViewNews.add(150F);
////            }
//
//        }
//
//        for(int i=floatsViewNews.size()-1;i>=0;i--)
//        {
//
//        }
    }

    void setNewsData(ArrayList<NewsDataClass> newsData_A){

        if(newsData_A!=null&&newsData_A.size()!=0)
            {
                //DataClasses=newsData_A;
                newsNewData=new ArrayList<>();
                for(int i=newsData_A.size()-1;i>=0;i--)
                    {
                        newsNewData.add(new NewsNewData(newsData_A.get(i)));
                    }
                boolDataOK=true;
                intFloatArrayNum=0;
                ccTextSizeH();
                invalidate();
            }

    }
    void addNewNewsData(ArrayList<NewsDataClass> newsData_A){
        if(newsData_A!=null&&newsData_A.size()!=0)
            {
                if(newsNewData==null||newsNewData.size()==0)
                    {
                        newsNewData=new ArrayList<>();
                        for(int i=0;i<newsData_A.size();i++)
                            {
                                newsNewData.add(new NewsNewData(newsData_A.get(i)));
                            }
                        intFloatArrayNum=0;

                    }else {
                    ArrayList<NewsNewData> newsNewData_A=new ArrayList<>();
                    for(int i=newsData_A.size()-1;i>=0;i--)
                        {
                            newsNewData_A.add(new NewsNewData(newsData_A.get(i)));
                        }

                    newsNewData.addAll(0,newsNewData_A);
                    intFloatArrayNum+=newsNewData_A.size();
                }
                //]Log.w(this.toString(), "LJKBFaddNewNewsDataS0: "+"="+newsData_A.size() +"="+ newsNewData.size());
                boolDataOK=true;
                ccTextSizeH();
                invalidate();
            }

    }
    void removeNewsData(int int_Num){
        if(newsNewData!=null&&newsNewData.size()>int_Num)
            {
                //]Log.w(this.toString(), "removeNewsDataD0: "+"="+int_Num +"="+newsNewData.size() );
                newsNewData.remove(int_Num);
               // DataClasses.remove(int_Num);
                if(intFloatArrayNum>=int_Num)
                    {
                        intFloatArrayNum--;
                    }
                ccTextSizeH();
                if(newsNewData.size()==0)
                    {
                    boolDataOK=false;
                    }
                invalidate();


            }

    }
    ArrayList<String> getTextAdvances(String string_A, Paint paint_A, float float_W) {
        ArrayList<String> strings_A = new ArrayList<>();
        int int_A = string_A.length();

        float float_A = paint_A.measureText(string_A);
        if (float_A > float_W) {
            int int_F2 = (int)(float_A / float_W)+1;
            int int_F = (int_A / int_F2);
            int int_B2 = 0;
            int int_B =  int_F;
            String string_B = null;
            while (true) {
                string_B = string_A.substring(int_B2, int_B);
                float_A = paint_A.measureText(string_B);

                if (float_A < float_W) {
                    int int_C = int_B;
                    while (true) {
                        if (float_A > (float_W - 10)) {
                            int_B = int_C;
                            break;
                        } else if (int_C >= int_A) {

                            if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                            {
                                for(int e=int_C-2;e>int_B2;e--)
                                {
                                    if(ccUnicodeBlock(string_A.charAt(e)))
                                    {
                                        int_C=e+1;
                                        break;
                                    }

                                }

                            }
                            string_B = string_A.substring(int_B2, int_C);
                            // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"1" +"="+ string_B);
                            strings_A.add(string_B);
                            return strings_A;

                        } else {
                            int_C++;
                            string_B = string_A.substring(int_B2, int_C);
                            float_A = paint_A.measureText(string_B);
                        }
                    }
                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
                    {
                        for(int e=int_B-2;e>int_B2;e--)
                        {
                            if(ccUnicodeBlock(string_A.charAt(e)))
                            {
                                int_B=e+1;
                                break;
                            }

                        }

                    }
                    string_B = string_A.substring(int_B2, int_B);
                    // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"0" +"="+ string_B);
                    strings_A.add(string_B);
                    //int_B2 = int_B;
                } else {
                    int int_C = int_B;
                    while (true) {
                        if (float_A < float_W) {
                            int_B = int_C;

                            break;
                        } else if (int_C >= int_B2) {
                            if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                            {
                                for(int e=int_C-2;e>int_B2;e--)
                                {
                                    if(ccUnicodeBlock(string_A.charAt(e)))
                                    {
                                        int_C=e+1;
                                        break;
                                    }

                                }

                            }
                            string_B = string_A.substring(int_B2, int_C);
                            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"0" +"="+ string_B);
                            strings_A.add(string_B);
                            return strings_A;

                        } else {
                            if (int_C <= int_B2+1)
                            {
                                if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                                {
                                    for(int e=int_C-2;e>int_B2;e--)
                                    {
                                        if(ccUnicodeBlock(string_A.charAt(e)))
                                        {
                                            int_C=e+1;
                                            break;
                                        }

                                    }

                                }
                                string_B = string_A.substring(int_B2, int_C);
                                // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"1" +"="+ string_B);
                                strings_A.add(string_B);
                                return strings_A;

                            }else {
                                int_C--;
                                string_B = string_A.substring(int_B2, int_C);
                                float_A = paint_A.measureText(string_B);
                            }

                        }
                    }
                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
                    {
                        for(int e=int_B-2;e>int_B2;e--)
                        {
                            if(ccUnicodeBlock(string_A.charAt(e)))
                            {
                                int_B=e+1;
                                break;
                            }

                        }

                    }
                    string_B = string_A.substring(int_B2, int_B);
                    // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"2" +"="+ string_B);
                    strings_A.add(string_B);
                    //int_B2 = int_B;
                }
                if(int_B>=int_A)
                {
                    break;
                }else {
                    int_B2=int_B;
                    int_B+=int_F;
                    if(int_B>=int_A)
                    {
                        int_B=int_A;

                        string_B = string_A.substring(int_B2, int_B);
                        // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"3" +"="+ string_B);
                        strings_A.add(string_B);
                        return strings_A;

                    }


                }
            }
            return strings_A;
        }else {
            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"9" +"="+ string_A);
            strings_A.add(string_A);
            return strings_A;
        }



    }
    boolean ccUnicodeBlock(char char_A){

        Character.UnicodeScript unicodeScript=Character.UnicodeScript.of(char_A);
        // //]Log.w(this.toString(), "ccUnicodeBlockG1: "+"="+char_A +"="+ (unicodeScript==Character.UnicodeScript.HAN) +"="+(Character.isLetter(char_A))+"="+(Character.isDigit(char_A))+"="+(Character.isWhitespace(char_A)));
        if(unicodeScript==Character.UnicodeScript.HAN)
        {
            return true;
        }
        if(Character.isLetter(char_A))
        {
            return false;
        }
        if(Character.isDigit(char_A))
        {
            return true;
        }
        if(Character.isWhitespace(char_A))
        {
            return true;

        }

        //]Log.w(this.toString(), "ccUnicodeBlockF0: "+"="+ char_A +"="+unicodeScript.toString());
        return true;

    }
    public void moveView(float float_Move) {
        if(boolDataOK&&intFloatArrayNum!=-1)
            {

        float float_A = intViewH - float_Move;
        float float_B = newsNewData.get(intFloatArrayNum).viewH;
        int int_F=intFloatArrayNum;

        if (intFloatArrayNum == intFloatArrayLast) {

            if (float_A > (float_B - floatNewsLastH)) {
                //intViewHRest+=(float_A-(float_B - 144));
                float_A = float_B;
                float_A -= floatNewsLastH;

            }
            if (float_A < 0) {
                if (intFloatArrayNum != 0) {
                    intFloatArrayNum--;
                    float_B = newsNewData.get(intFloatArrayNum).viewH;
                    float_A = float_B + float_A;
                    float_A = repsMoveView(float_A);
                } else {
                    //intViewHRest+=float_A;
                    float_A = 0;
                }

            }

        } else if (float_A < 0) {
            if (intFloatArrayNum != 0) {
                intFloatArrayNum--;
                float_B = newsNewData.get(intFloatArrayNum).viewH;
                float_A = float_B + float_A;
                float_A = repsMoveView(float_A);
            } else {
                //intViewHRest+=float_A;
                float_A = 0;
            }

        } else if (float_A > float_B) {
            if (intFloatArrayNum == intFloatArrayLast) {
                if (float_A > (float_B - floatNewsLastH)) {
                    //intViewHRest+=(float_A-(float_B - 144));
                    float_A = float_B;
                    float_A -= floatNewsLastH;
                }
            } else {
                intFloatArrayNum++;
                float_A = float_A - float_B;
                float_A = repsMoveView(float_A);
            }

        }
        //]Log.w(this.toString(), " DKODmoveViewS0: "+"="+((newsNewData.get(int_F)).viewH-(intViewH - float_Move)) +"="+float_A +"="+(intViewH - float_Move));

        intViewH = float_A;
        invalidate();
            }

    }
    public float repsMoveView(float float_Move) {
        float float_A=float_Move;
        float float_B=newsNewData.get(intFloatArrayNum).viewH;
        if(float_A<0)
        {
            if(intFloatArrayNum!=0)
            {
                intFloatArrayNum--;
                float_B=newsNewData.get(intFloatArrayNum).viewH;
                float_A=float_B+float_A;
                return repsMoveView(float_A);
            }else {
                float_A=0;
            }

        }else if(float_A>float_B)
        {
            if(intFloatArrayNum!=intFloatArrayLast)
            {
                intFloatArrayNum++;
                float_A=float_A-float_B;
                return repsMoveView(float_A);
            }else {
                float_A=float_B;
                float_A-=floatNewsLastH;
            }

        }
        return float_A;
    }
    long longTouchTime;
    float floatTouchX,floatTouchY;
    boolean touchView(MotionEvent event){
        if(newsNewData!=null&&newsNewData.size()>0)
            {

        float float_X=event.getX();
        float float_Y=event.getY();
        if(float_X>110&&float_X<width-110)
        {
            int int_A=intFloatArrayNum;
            float float_W=10;
            for(int i=int_A;i<newsNewData.size();i++)
            {
                //float float_W2=float_W+newsNewData.get(i).floatViewH;
                float float_H2=float_W- intViewH;
                float float_H3=float_W+80- intViewH;
                if(float_Y>float_H2&&float_Y<float_H3)
                {
                    //]Log.w(this.toString(), "touchViewS0: "+"="+float_Y +"="+ float_H2 +"="+float_H3);
                    openNewsRecycView(newsNewData.get(i).newsDataClass);
                    return true;
                }


                if(float_W>height)
                {
                    break;
                }
                float_W+=newsNewData.get(i).viewH+20;

            }
        }


            }
        return false;
    }
    void openNewsRecycView(NewsDataClass newsDataClass_A){
        //]Log.w(this.toString(), "openNewsRecycViewS0: "+"="+newsDataClass_A.stringTitle );
        if(newsBreakingPool!=null)
        {
            newsBreakingPool.openNewsNewTextView(newsDataClass_A);
            //newsBreakingPool.openNewsTextView(newsDataClass_A);
        }
    }

    public float intViewH;
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
                floatTouchX=event.getX();
                floatTouchY=event.getY();
                longTouchTime=System.currentTimeMillis();

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
    class NewsNewData{
        public NewsDataClass newsDataClass;
        public String stringTitle;
        public float viewH;
        public NewsNewData(NewsDataClass newsDataClass_A){
            newsDataClass=newsDataClass_A;
            stringTitle=newsDataClass.stringTitle;
            stringTitle= TextStringTool.getTitleAdvances(newsDataClass.stringTitle,paintTitle,width-240);
        }


    }

}
