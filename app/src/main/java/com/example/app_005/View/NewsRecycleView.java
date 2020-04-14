package com.example.app_005.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.Scroller;

import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Class.SkillNewsName;
import com.example.app_005.Tool.TimeOD;

import java.util.ArrayList;

//新闻时间点
public class NewsRecycleView extends View {
    Context context;
    public ArrayList<NewsRecycData> newsRecycDataNow;
    public ArrayList<NewsDataClass> newsDataClasses;
    boolean boolDataOK;
    boolean boolUIOK;
    Paint paintNewsRect;
    Paint paintNewsRectP;
    Paint paintNewsSkill;
    Paint paintNewsText;
    Paint paintNewsTextNote;
    Paint paintNewsTextTime;
    int width,height;
    public float intViewNowH;
    float floatRecycDataLastH;
    int intRecycDataNow=-1;
    int intRecycDataLastNum;
    public float intViewHRest;
    public float intViewW;
    private int mMaxFlintVelocity, mMinFlintVelocity;
    NewsSystemCenter.NewsBreakingPool newsBreakingPool;
    boolean boolReqNextNewsData;

   // ArrayList<Float> floatsViewNews;
//    int intFloatArrayLast;


    public NewsRecycleView(Context context) {
        super(context);

    }

    public NewsRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intRecycDataNow=-1;


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(width, height);//设置宽和高


        paintNewsRect=new Paint();
        paintNewsRect.setAntiAlias(true);//消除锯齿
        paintNewsRect.setColor(Color.WHITE);//设置画笔颜色
        paintNewsRect.setStyle(Paint.Style.FILL);
        paintNewsRect.setStrokeWidth(10);//设置宽度

        paintNewsRectP=new Paint();
        paintNewsRectP.setAntiAlias(true);//消除锯齿
        paintNewsRectP.setColor(Color.parseColor("#83DAD7D7"));//设置画笔颜色//#83DAD7D7
        paintNewsRectP.setStyle(Paint.Style.FILL);
        paintNewsRectP.setStrokeWidth(10);//设置宽度

        paintNewsSkill=new Paint();
        paintNewsSkill.setAntiAlias(true);//消除锯齿
        paintNewsSkill.setColor(Color.BLUE);//设置画笔颜色
        paintNewsSkill.setStyle(Paint.Style.STROKE);
        paintNewsSkill.setStrokeWidth(2);//设置宽度

        //绘制文字的画笔
        paintNewsText = new Paint();
        paintNewsText.setAntiAlias(true);//消除锯齿
        paintNewsText.setColor(Color.BLACK);
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintNewsText.setTextAlign(Paint.Align.LEFT);
        paintNewsText.setTextSize(37);

        paintNewsTextNote = new Paint();
        paintNewsTextNote.setAntiAlias(true);//消除锯齿
        paintNewsTextNote.setColor(Color.parseColor("#FF818181"));
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintNewsTextNote.setTextAlign(Paint.Align.LEFT);
        paintNewsTextNote.setTextSize(25);

        paintNewsTextTime = new Paint();
        paintNewsTextTime.setAntiAlias(true);//消除锯齿
        paintNewsTextTime.setColor(Color.parseColor("#FF7C7B7B"));
        paintNewsTextTime.setTextAlign(Paint.Align.LEFT);
        //paintNewsTextTime.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paintNewsTextTime.setTextSize(20);
        paintNewsTextTime.setSubpixelText(true);


        mScroller = new Scroller(getContext(), null, true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mMaxFlintVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMinFlintVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMinFlintVelocity = 600;

        boolUIOK=true;
        //]Log.w(this.toString(), "LJKNGFonMeasureD0: " );
         updateRecycViewUI();
//


       // setNewsData(newsDataClasses_A);
//        floatsViewNews=new ArrayList<>();
//        for(int i=0;i<30;i++)
//            {
//                if(i%2==0)
//                    {
//                        floatsViewNews.add(200F);
//                    }else {
//                    floatsViewNews.add(150F);
//                }
//
//            }
//        float float_M=0;
//        for(int i=floatsViewNews.size()-1;i>=0;i--)
//            {
//                float_M+=floatsViewNews.get(i);//+20;
//                if(float_M>height)
//                    {
//
//                        intFloatArrayLast=i;
//                        break;
//                    }
//                float_M+=20;
//            }
//        //]Log.w(this.toString(), "LKDMLDonMeasureF0-: "+"="+intFloatArrayLast +"="+float_M +"="+height  +"="+(float_M-height));
//        intFloatArrayNum=0;
//        String string_F="烟雨江湖》是一款武侠类独立游戏，为《江湖风云录》的续作；给玩家们带来原汁原味的武侠体验；在游戏中玩家将踏入刀光剑影的江湖世界，在这个真实开始的江湖中探索，走出属于自己的道路；2D卡通式的画风，搭配上简洁有趣的玩法，吸引了很多的玩家，赶紧下载试试吧！";
//        ArrayList<String> strings_A=getTextAdvances(string_F,paintNewsTextNote,width-180);
//        //]Log.w("KJNFKJN", "DLKMDDonMeasureF0: "+"="+(strings_A!=null) );
//        //]Log.w("KJNFKJN", "DLKMDDonMeasureF1: "+"="+(strings_A.size()) );
//        for(int i=0;i<strings_A.size();i++)
//            {
//                //]Log.w("KJNFKJN", "DLKMDDonMeasureF2: "+"="+i +"="+(strings_A.get(i)) );
//            }
//        //]Log.w("KJNFKJN", "DLKMDDonMeasureG1: "+"="+Character.isSpaceChar(' ') +"="+Character.isSpaceChar('A') +"="+Character.isSpaceChar('平') );
//        //]Log.w("KJNFKJN", "DLKMDDonMeasureG2: "+"="+(Character.UnicodeScript.of(' ')==Character.UnicodeScript.HAN) +"="+(Character.UnicodeScript.of('A')==Character.UnicodeScript.HAN)+"="+(Character.UnicodeScript.of('平')==Character.UnicodeScript.HAN)+"="+(Character.UnicodeScript.of(',')==Character.UnicodeScript.HAN)+"="+(Character.UnicodeScript.of('，')==Character.UnicodeScript.HAN));
//        //]Log.w("KJNFKJN", "DLKMDDonMeasureG3: "+"="+(Character.UnicodeBlock.of(' ')==Character.UnicodeBlock.GENERAL_PUNCTUATION) +"="+(Character.UnicodeBlock.of('A')==Character.UnicodeBlock.GENERAL_PUNCTUATION)+"="+(Character.UnicodeBlock.of('平')==Character.UnicodeBlock.GENERAL_PUNCTUATION)+"="+(Character.UnicodeBlock.of(',')==Character.UnicodeBlock.GENERAL_PUNCTUATION)+"="+(Character.UnicodeBlock.of('，')==Character.UnicodeBlock.GENERAL_PUNCTUATION));
//        //]Log.w("KJNFKJN", "DLKMDDonMeasureG4: "+"="+(Character.UnicodeBlock.of('.')==Character.UnicodeBlock.SUPPLEMENTAL_PUNCTUATION)+"="+(Character.UnicodeBlock.of(',')==Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION)+"="+(Character.UnicodeBlock.of(',')==Character.UnicodeBlock.CUNEIFORM_NUMBERS_AND_PUNCTUATION));
//        //]Log.w("KJNFKJN", "DLKMDDonMeasureG4: "+"="+(Character.UnicodeScript.of('.'))+"="+(Character.UnicodeScript.of('1'))+"="+(Character.UnicodeScript.of('A'))+"="+(Character.UnicodeScript.of('平'))+"="+(Character.UnicodeScript.of(' '))+"="+(Character.UnicodeScript.of('/')));
//        //]Log.w("KJNFKJN", "DLKMDDonMeasureG4: "+"="+(Character.UnicodeBlock.of('.'))+"="+(Character.UnicodeBlock.of('1'))+"="+(Character.UnicodeBlock.of('A'))+"="+(Character.UnicodeBlock.of('平'))+"="+(Character.UnicodeBlock.of(' '))+"="+(Character.UnicodeBlock.of('/')));
//        if(Character.isSpaceChar(' '))
//        {
//
//        }
//        if(Character.UnicodeScript.of(' ')==Character.UnicodeScript.HAN)
//        {
//
//        }
//        if(Character.UnicodeBlock.of(' ')==Character.UnicodeBlock.GENERAL_PUNCTUATION)
//        {
//
//        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(boolDataOK)
            {
                if(intRecycDataNow!=-1) {

                    int int_A = intRecycDataNow;
                    float float_W = 10;
                    for (int i = int_A; i < newsRecycDataNow.size(); i++) {
                        String string_A = newsRecycDataNow.get(i).stringTitle;
                        String[] strings_A = newsRecycDataNow.get(i).stringsText;
                        float float_W2 = float_W + newsRecycDataNow.get(i).viewH - 20;

                        canvas.drawRoundRect(20 - 1, float_W - intViewNowH - 1, width - 20 + 3, float_W2 - intViewNowH + 4, 8, 8, paintNewsRectP);
                        canvas.drawRoundRect(20, float_W - intViewNowH, width - 20, float_W2 - intViewNowH, 8, 8, paintNewsRect);

                        canvas.drawText(string_A, 20 + 20, float_W - intViewNowH + 45, paintNewsText);


                        if (strings_A != null) {
                            for (int e = 0; e < strings_A.length; e++) {
                                canvas.drawText(strings_A[e], 20 + 21, float_W - intViewNowH + 82 + (30 * e), paintNewsTextNote);
                            }
                        }

                        canvas.drawText("11/27 22:59", 20 + 20, float_W2 - intViewNowH - 13, paintNewsTextTime);
                        //canvas.drawRoundRect(170,float_W2- intViewNowH -33,250,float_W2- intViewNowH -5,3,3,paintNewsSkill);
                        //StaticLayout staticLayout_A=new StaticLayout(string_A,paintNewsTextNote,()(width-40), Layout.Alignment.ALIGN_LEFT,);
                        ////]Log.w(this.toString(), "KOILPKFMonDrawD0: "+"="+height +"="+float_W +"="+intViewNowH);
                        if (float_W > height) {
                            //]Log.w(this.toString(), "ODIKDonDrawS0: " + "=" + int_A + "=" + i);
                            break;
                        }
                        float_W += newsRecycDataNow.get(i).viewH;

                    }
                    if (boolReqNextNewsData) {
                        canvas.drawCircle(width / 2F, height - 300, 100, paintNewsRectP);
                    } else {
                        if (boolRest) {
                            if (intViewHRest > 100) {

                                canvas.drawCircle(width / 2F, intViewHRest, 100, paintNewsRectP);
                            } else if (intViewHRest < -100) {
                                canvas.drawCircle(width / 2F, height + intViewHRest, 100, paintNewsRectP);
                            }


                        }
                    }
                }else {
                    canvas.drawText("没有数据", width/2, height/2, paintNewsTextNote);
                }
            }


    }
    void setNewsBreakingPool(NewsSystemCenter.NewsBreakingPool newsBreakingPool_A){newsBreakingPool=newsBreakingPool_A;}
    void setNewsData(ArrayList<NewsDataClass> newsData_A){

        newsDataClasses=newsData_A;
        if(newsDataClasses!=null)
            {
                boolDataOK=true;
                updateRecycViewUI();
            }

    }
//    void addNewsData(ArrayList<NewsDataClass> newsData_A){
//        if(newsData_A!=null)
//            {
//                if(DataClasses==null)
//                    {
//                        DataClasses=new ArrayList<>();
//                    }
//                boolDataOK=true;
//                DataClasses.addAll(newsData_A);
//            }
//
////        DataClasses=newsData_A;
////        if(DataClasses!=null)
////        {
////
////            updateRecycViewUI();
////        }
//
//    }
    void addNewsData(ArrayList<NewsDataClass> newsData_A){
        ArrayList<NewsRecycData> newsRecycData_A=new ArrayList<>();
        for(int i=0;i<newsData_A.size();i++)
            {
                newsRecycData_A.add(new NewsRecycData(newsData_A.get(i)));
                newsRecycData_A.get(i).ccNewsViewH(110,30);
            }
//        NewsRecycData newsRecycData_A=new NewsRecycData(newsDataClass_A);
//        newsRecycData_A.ccNewsViewH(110,30);
        //]Log.w(this.toString(), "addNewsData: R1"+"="+ (newsRecycDataNow!=null)+"="+(newsRecycDataNow!=null&&newsRecycDataNow.size()>0) +"="+newsData_A.size());
        if(newsRecycDataNow!=null&&newsRecycDataNow.size()>0)
        {
            //]Log.w(this.toString(), "addNewsData: R2"+"="+ newsRecycDataNow.size());
            newsRecycDataNow.addAll(0,newsRecycData_A);
            intRecycDataNow+=newsRecycData_A.size();
        }else {
            newsRecycDataNow=new ArrayList<>();
            newsRecycDataNow.addAll(newsRecycData_A);
            intRecycDataNow=0;
        }
        float floatLastH=0;
        for(int i=newsRecycDataNow.size()-1;i>=0;i--)
        {
            floatLastH+=newsRecycDataNow.get(i).viewH;
            if(floatLastH>height-3)
            {
                floatRecycDataLastH=(height-3)-floatLastH;
                intRecycDataLastNum=i-1;
                break;
            }
            //floatLastH+=20;
        }
        //]Log.w(this.toString(), "addNewsData: R0"+"="+intRecycDataNow +"="+ ((newsRecycDataNow!=null)?newsRecycDataNow.size():false));
        invalidate();

    }
    public void addNextDayData(ArrayList<NewsDataClass> newsData_A){

        if(boolDataOK&&boolUIOK)
        {
            if(newsData_A!=null&&newsRecycDataNow!=null)
            {

                ArrayList<NewsRecycData> newsRecycData_A=new ArrayList<>();
                for(int i=0;i<newsData_A.size();i++)
                {
                    NewsRecycData newsRecycData_B=new NewsRecycData(newsData_A.get(i));
                    newsRecycData_B.ccNewsViewH(110,30);
                    newsRecycData_A.add(newsRecycData_B);

                }
                int int_A=newsRecycData_A.size();
                //intRecycDataNow+=int_A;
                newsRecycDataNow.addAll(newsRecycData_A);

                float floatLastH=0;
                for(int i=newsRecycDataNow.size()-1;i>=0;i--)
                    {
                        floatLastH+=newsRecycDataNow.get(i).viewH;
                        if(floatLastH>height-3)
                        {
                            floatRecycDataLastH=(height-3)-floatLastH;
                            intRecycDataLastNum=i-1;
                            break;
                        }
                        //floatLastH+=20;
                    }
                boolReqNextNewsData=false;
                invalidate();
            }
            }

    }
    void updateRecycViewUI(){
        if(boolDataOK&&boolUIOK)
            {  //]Log.w(this.toString(), "updateRecycViewUIE0:"+"="+newsDataClasses.size() );
                if(newsDataClasses.size()>0)
                    {
                        intViewNowH=0;

                        ArrayList<NewsRecycData> newsRecycData_A=new ArrayList<>();
                        for(int i=0;i<newsDataClasses.size();i++)
                        {
                            NewsRecycData newsRecycData_B=new NewsRecycData(newsDataClasses.get(i));
                            newsRecycData_B.ccNewsViewH(110,30);
                            newsRecycData_A.add(newsRecycData_B);

                        }
                        float floatLastH=0;
                        floatRecycDataLastH=-1;
                        intRecycDataLastNum=-1;
                        for(int i=newsRecycData_A.size()-1;i>=0;i--)
                            {
                                floatLastH+=newsRecycData_A.get(i).viewH;
                                if(floatLastH>height-3)
                                    {
                                        floatRecycDataLastH=(height-3)-floatLastH;
                                        intRecycDataLastNum=i-1;
                                        break;
                                    }
                                //floatLastH+=20;

                            }
                        newsRecycDataNow=newsRecycData_A;
                        intRecycDataNow=0;
                        //]Log.w(this.toString(), "updateRecycViewUIE0: "+"="+intRecycDataNow +"="+(newsRecycDataNow!=null) );
                        invalidate();
                    }else{
                    intRecycDataNow=-1;
                }




            }
    }
    void addToNewsData(NewsDataClass newsDataClass_A){
        NewsRecycData newsRecycData_A=new NewsRecycData(newsDataClass_A);
        newsRecycData_A.ccNewsViewH(110,30);
        if(newsRecycDataNow!=null&&newsRecycDataNow.size()>0)
            {
                newsRecycDataNow.add(0,newsRecycData_A);
                intRecycDataNow+=1;
            }else {
            newsRecycDataNow=new ArrayList<>();
            newsRecycDataNow.add(newsRecycData_A);
            intRecycDataNow=0;
        }
        float floatLastH=0;
        for(int i=newsRecycDataNow.size()-1;i>=0;i--)
        {
            floatLastH+=newsRecycDataNow.get(i).viewH;
            if(floatLastH>height-3)
            {
                floatRecycDataLastH=(height-3)-floatLastH;
                intRecycDataLastNum=i-1;
                break;
            }
            //floatLastH+=20;
        }
        //]Log.w(this.toString(), "updateRecycViewUIE0: "+"="+intRecycDataNow +"="+ (newsRecycDataNow!=null));
        invalidate();

    }
//    void ccNewsViewH(){
//        for(int i = 0; i< newsRecycDataNow.size(); i++)
//            {
//
//            }
//    }
    ArrayList<String> getTextAdvances(String string_A,Paint paint_A,float float_W) {
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
    String getTitleAdvances(String string_A,Paint paint_A,float float_W) {
        //ArrayList<String> strings_A = new ArrayList<>();
        String string_rt=null;
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
                            //strings_A.add(string_B);
                            return string_B+"...";

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
                    //strings_A.add(string_B);
                    return string_B+"...";
                    //int_B2 = int_B;
                }
                else {
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
//                            strings_A.add(string_B);
                            return string_B+"...";

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
//                                strings_A.add(string_B);
                                return string_B+"...";

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
//                    strings_A.add(string_B);
                    return string_B+"...";
                    //int_B2 = int_B;
                }
//                if(int_B>=int_A)
//                {
//                    return null;
//                }else {
//                    int_B2=int_B;
//                    int_B+=int_F;
//                    if(int_B>=int_A)
//                    {
//                        int_B=int_A;
//
//                        string_B = string_A.substring(int_B2, int_B);
//                        // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"3" +"="+ string_B);
////                        strings_A.add(string_B);
//                        return string_B;
//
//                    }
//
//
//                }

            }
           //
        }else {
            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"9" +"="+ string_A);
           // strings_A.add(string_A);
            return string_A;
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
    public void ccViewPos(){

    }
    boolean boolRest;

    public void moveView(float float_Move) {
        if(boolDataOK&&intRecycDataNow!=-1)
            {

        //]Log.w("NewsRecycleView", "LKDMLDmoveViewD0: "+"="+float_Move +"="+ intViewHRest +"="+intRecycDataNow +"="+(newsRecycDataNow!=null));
        float float_A = intViewNowH - float_Move;
        float float_B = newsRecycDataNow.get(intRecycDataNow).viewH;

        if (intRecycDataNow == intRecycDataLastNum) {

            if (float_A > (float_B - floatRecycDataLastH)) {
                //intViewHRest+=(float_A-(float_B - 144));
                float_A = float_B;
                float_A -= floatRecycDataLastH;
                boolRest=true;

            }
            if (float_A < 0) {
                if (intRecycDataNow != 0) {
                    boolRest=false;
                    intRecycDataNow--;
                    float_B = newsRecycDataNow.get(intRecycDataNow).viewH;
                    float_A = float_B + float_A;
                    float_A = repsMoveView(float_A);
                } else {
                    //intViewHRest+=float_A;
                    float_A = 0;
                    boolRest=true;
                }

            }

        } else if (float_A < 0) {
            if (intRecycDataNow != 0) {
                intRecycDataNow--;
                boolRest=false;
                float_B = newsRecycDataNow.get(intRecycDataNow).viewH;
                float_A = float_B + float_A;
                float_A = repsMoveView(float_A);
            } else {
                //intViewHRest+=float_A;
                float_A = 0;
                boolRest=true;
            }

        } else if (float_A > float_B) {
            if (intRecycDataNow == intRecycDataLastNum) {
                if (float_A > (float_B - floatRecycDataLastH)) {
                    //intViewHRest+=(float_A-(float_B - 144));
                    float_A = float_B;
                    float_A -= floatRecycDataLastH;
                    boolRest=true;
                }
            } else {
                intRecycDataNow++;
                boolRest=false;
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
        float float_B=newsRecycDataNow.get(intRecycDataNow).viewH;
        if(float_A<0)
        {
            if(intRecycDataNow!=0)
            {
                intRecycDataNow--;
                boolRest=false;
                float_B=newsRecycDataNow.get(intRecycDataNow).viewH;
                float_A=float_B+float_A;
                return repsMoveView(float_A);
            }else {
                float_A=0;
                boolRest=true;
            }

        }else if(float_A>float_B)
        {
            if(intRecycDataNow!=intRecycDataLastNum)
            {
                intRecycDataNow++;
                //]Log.w(this.toString(), "repsMoveViewS9: "+"="+intRecycDataNow );
                boolRest=false;
                float_A=float_A-float_B;
                return repsMoveView(float_A);
            }else {
                float_A=float_B;
                float_A-=floatRecycDataLastH;
                boolRest=true;
            }

        }
        return float_A;
    }
    long longTouchTime;
    float floatTouchX,floatTouchY;
    boolean touchView(MotionEvent event){
        if(newsRecycDataNow!=null&&newsRecycDataNow.size()>0)
            {

        float float_X=event.getX();
        float float_Y=event.getY();
        if(float_X>20&&float_X<width-20)
            {
                int int_A=intRecycDataNow;
                float float_W=10;
                for(int i=int_A;i<newsRecycDataNow.size();i++)
                {
                    float float_W2=float_W+newsRecycDataNow.get(i).viewH-20;
                    float float_H2=float_W- intViewNowH;
                    float float_H3=float_W2- intViewNowH;
                    if(float_Y>float_H2&&float_Y<float_H3)
                        {
                            openNewsRecycView(newsRecycDataNow.get(i).newsDataClass);
                            return true;
                        }


                    if(float_W>height)
                    {
                        break;
                    }
                    float_W+=newsRecycDataNow.get(i).viewH;

                }
            }
            }
        return false;
    }
    void openNewsRecycView(NewsDataClass newsDataClass_A){
        //]Log.w(this.toString(), "openNewsRecycViewS0: "+"="+newsDataClass_A.stringTitle );
        if(newsBreakingPool!=null)
            {
                newsBreakingPool.openNewsTextView(newsDataClass_A);
            }
    }
    float floatTouchDownY;
    private VelocityTracker velocityTracker;
    private Scroller mScroller;
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
                if(false&&boolRest)
                    {
                        if(float_A>0)
                        {
                            //newsBreakingPool.reqAddToNewsData();

                        }else {
                            if(boolRest&&(!boolReqNextNewsData))
                            {
                                intViewHRest+=(float_A/2F);
                                //]Log.w(this.toString(), " LMDLKMLonTouchEventD1: "+"="+intViewHRest +"="+float_A );
//                    if(intViewHRest>300)
//                        {
//                            //]Log.w(this.toString(), " LMDLKMLonTouchEventD0: "+"="+intViewHRest +"="+true );
//                            intViewHRest=300;
//                        }else
                                if(intViewHRest<-300)
                                {
                                    //]Log.w(this.toString(), " LMDLKMLonTouchEventD0: "+"="+intViewHRest +"="+false );
                                    if(newsBreakingPool!=null)
                                    {
                                        boolReqNextNewsData=true;
                                        intViewHRest=0;
                                        newsBreakingPool.reqUpdateNextDayNowNewsData();

                                    }

                                }
                            }
                        }
                    }




                return true;
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                boolRest=false;
                intViewHRest=0;
                floatTouchDownY=event.getY();
                floatTouchX=event.getX();
                floatTouchY=event.getY();
                longTouchTime=System.currentTimeMillis();
                return true;
            case MotionEvent.ACTION_UP:
                boolRest=false;
                intViewHRest=0;
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
                        mScroller.fling((int)intViewW,(int) floatTouchDownY, xVelocity, yVelocity, 0, 0, -99999, 99999);
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
            if(false&&boolRest)
            {
                if(float_A>0)
                {
                   // newsBreakingPool.reqAddToNewsData();

                }
            }
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
    class NewsRecycData{
        public NewsDataClass newsDataClass;
        public String stringTitle;
        public String[] stringsText;
        public float viewH;
        public NewsRecycData(NewsDataClass newsDataClass_A){
            newsDataClass=newsDataClass_A;
            stringTitle=getTitleAdvances(newsDataClass.stringTitle,paintNewsText,width-100);
            if(newsDataClass.stringText!=null)
                {
                    ArrayList<String> arrayList=getTextAdvances(newsDataClass.stringText,paintNewsTextNote,width-90);
                    stringsText=arrayList.toArray(new String[arrayList.size()]);
                }
        }
        public void ccNewsViewH(float float_A,float float_B){
            float_A=115;
            float_B=30;
            viewH=float_A;
            if(stringsText!=null)
                {
                    viewH+=(float_B*stringsText.length);
                }
        }
    }


}
