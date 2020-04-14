package com.example.app_005.Class;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.example.app_005.Internet.FortradeCollectTool;
import com.example.app_005.Internet.FortradeCollectTool.NewsDataFortradeDayAnalysis;
import com.example.app_005.Tool.TextStringTool;

public class NewsClassDW {
    Paint paintUI;
    Paint paintP;
    Paint paintTitle;
    Paint paintTime;
    Paint paintText;
    public NewsClassDW(){

        paintUI=new Paint();
        paintUI.setAntiAlias(true);//消除锯齿
        paintUI.setColor(Color.parseColor("#83DAD7D7"));//设置画笔颜色//#83DAD7D7
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
        paintTitle.setTextAlign(Paint.Align.LEFT);
        paintTitle.setTextSize(37);

        paintTitle = new Paint();
        paintTitle.setAntiAlias(true);//消除锯齿
        paintTitle.setColor(Color.BLACK);
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintTitle.setTextAlign(Paint.Align.LEFT);
        paintTitle.setTextSize(37);

        paintText = new Paint();
        paintText.setAntiAlias(true);//消除锯齿
        paintText.setColor(Color.BLACK);
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintText.setTextAlign(Paint.Align.LEFT);
        paintText.setTextSize(25);

        paintTime = new Paint();
        paintTime.setAntiAlias(true);//消除锯齿
        paintTime.setColor(Color.parseColor("#FF7C7B7B"));
        paintTime.setTextAlign(Paint.Align.LEFT);
        //paintNewsTextTime.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paintTime.setTextSize(20);
        paintTime.setSubpixelText(true);
    }
    public NewsClassDraw getNewsClassDraw(NewsDataClass newsDataClass_A,int width_A,int height_A){
        NewsClassDraw newsClassDraw_rt=null;
      //  //]Log.w(this.toString(), "getNewsClassDrawD0: "+"="+newsDataClass_A.skillNewsName.toString() +"="+newsDataClass_A.intMode );
        switch(newsDataClass_A.skillNewsName){
            case DailyFX:
                break;
            case FortradeAnalysis:
                switch(newsDataClass_A.intMode){
                    case 0:
                        newsClassDraw_rt=new NewsClassDraw(newsDataClass_A,width_A,height_A);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return newsClassDraw_rt;
    }

    public class NewsClassDraw{
        NewsDataClass newsDataClass;
        String[] stringTitle;
        String[] stringsText;
        String stringTime;
        SkillNewsName skillNewsName;
        int width,height;
        int intMode;
        float viewH;
        public NewsClassDraw(NewsDataClass newsDataClass_A,int width_A,int height_A){
            newsDataClass=newsDataClass_A;
            width=width_A;
            height=height_A;
            initData();

        }
        void initData(){
            stringTitle= TextStringTool.getTextAdvancesB(newsDataClass.stringTitle,paintTitle,width-150);
            stringsText=TextStringTool.getTextAdvancesB(newsDataClass.stringText,paintText,width-150);
            stringTime=newsDataClass.timeOD.intsA[0]+"-"+newsDataClass.timeOD.intsA[2]+"-"+newsDataClass.timeOD.intsA[3];
        }
        public void StartDraw(Canvas canvas){
            drawUI(canvas);
            drawTitle(canvas,stringTitle,stringsText,stringTime);
        }
        public void onTouch(MotionEvent event){

        }
        public void drawUI(Canvas canvas){
            canvas.drawRect(0,0,width,height,paintUI);
            canvas.drawRoundRect(50,30,width-50,height-100,20,20,paintP);

        }
        public void drawTitle(Canvas canvas,String[] stringTitle,String[] stringText,String stringTime){
            float float_H=30;
            for(int i=0;i<stringTitle.length;i++)
                {
                    float_H+=40;
                    canvas.drawText(stringTitle[i],70,float_H,paintTitle);
                }
            float_H+=30;
            canvas.drawText(stringTime,70,float_H,paintTime);
            float_H+=10;
            for(int i=0;i<stringText.length;i++)
                {
                    float_H+=30;
                    canvas.drawText(stringText[i],70,float_H,paintText);
                }


        }
    }
    public class NewsFortradeDrawDay extends NewsClassDraw{
        FortradeCollectTool.NewsDataFortradeDayAnalysis newsData;

        public NewsFortradeDrawDay(NewsDataClass newsDataClass_A,int width_A,int height_A) {
            super(newsDataClass_A,width_A,height_A);
            newsData=(FortradeCollectTool.NewsDataFortradeDayAnalysis)newsDataClass_A;
        }

        @Override
        public void StartDraw(Canvas canvas){
            //]Log.w(this.toString(), "KJBFStartDrawS0: "+"="+"OK" );
            drawUI(canvas);
        }
        @Override
        public void onTouch(MotionEvent event){
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    break;
                default:
                    break;
            }

        }
    }
    public class NewsFortradeDrawWeekly extends NewsClassDraw{

        public NewsFortradeDrawWeekly(NewsDataClass newsDataClass_A,int width_A,int height_A) {
            super(newsDataClass_A,width_A,height_A);
        }

        @Override
        public void StartDraw(Canvas canvas){

        }
    }
    public class NewsFortradeDrawOppoMic extends NewsClassDraw{

        public NewsFortradeDrawOppoMic(NewsDataClass newsDataClass_A,int width_A,int height_A) {
            super(newsDataClass_A,width_A,height_A);
        }

        @Override
        public void StartDraw(Canvas canvas){

        }
    }
}
