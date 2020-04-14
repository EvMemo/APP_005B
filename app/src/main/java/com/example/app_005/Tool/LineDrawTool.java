package com.example.app_005.Tool;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import com.example.app_005.Class.SkillLineNema;

import java.math.BigDecimal;

public class LineDrawTool {
    Paint paintOneColorBlue;
    Paint paintOneColorRed;
    Paint paintOneColorBlack;
    Paint paintOneColorPao;
    Paint paintOneLineBlack;
    Paint paintOneLineBlue;
    Paint paintOneLineBlue2;
    Paint paintOneLineRed;
    Paint paintOneLineOreg;
    Paint paintOneLineGreen;
    Paint paintOneLinePurple;//FF8E61DD
    Paint paintOneLineChocolate;//FFB4340A
    Paint paintOneTextName;
    Paint paintOneTextData;
    Paint paintMOText;
    Paint paintMOTextName;
    public LineDrawTool(){
        paintOneColorPao= new Paint();
        paintOneColorPao.setAntiAlias(true);//消除锯齿
        paintOneColorPao.setColor(Color.parseColor("#FFE4A0D4"));//设置画笔颜色
        paintOneColorPao.setStyle(Paint.Style.FILL);//设置为空心
        paintOneColorPao.setAlpha(100);
        paintOneColorPao.setStrokeWidth(3);//

        paintOneColorBlue= new Paint();
        paintOneColorBlue.setAntiAlias(true);//消除锯齿
        paintOneColorBlue.setColor(Color.parseColor("#6DDDF6"));//设置画笔颜色
        paintOneColorBlue.setStyle(Paint.Style.STROKE);//设置为空心
        paintOneColorBlue.setStrokeWidth(3);//

        paintOneColorRed= new Paint();
        paintOneColorRed.setAntiAlias(true);//消除锯齿
        paintOneColorRed.setColor(Color.parseColor("#FD476C"));//设置画笔颜色
        paintOneColorRed.setStyle(Paint.Style.STROKE);//设置为空心
        paintOneColorRed.setStrokeWidth(3);//设置宽度



        paintOneColorBlack = new Paint();
        paintOneColorBlack.setAntiAlias(true);//消除锯齿
        paintOneColorBlack.setColor(Color.BLACK);//设置画笔颜色
        paintOneColorBlack.setStyle(Paint.Style.STROKE);//设置为空心
        paintOneColorBlack.setStrokeWidth(3);//设置宽度

        paintOneLineBlack = new Paint();
        paintOneLineBlack.setAntiAlias(true);//消除锯齿
        paintOneLineBlack.setColor(Color.BLACK);//设置画笔颜色
        paintOneLineBlack.setStyle(Paint.Style.FILL);//设置为空心
        paintOneLineBlack.setStrokeWidth(2);//设置宽度

        paintOneLineOreg = new Paint();
        paintOneLineOreg.setAntiAlias(true);//消除锯齿
        paintOneLineOreg.setColor(Color.parseColor("#FFFB8C00"));//设置画笔颜色
        paintOneLineOreg.setStyle(Paint.Style.FILL);//设置为空心
        paintOneLineOreg.setStrokeWidth(2);//设置宽度

        paintOneLineBlue = new Paint();
        paintOneLineBlue.setAntiAlias(true);//消除锯齿
        paintOneLineBlue.setColor(Color.parseColor("#FF1E88E5"));//设置画笔颜色
        paintOneLineBlue.setStyle(Paint.Style.FILL);//设置为空心
        paintOneLineBlue.setStrokeWidth(2);//设置宽度

        paintOneLineBlue2 = new Paint();
        paintOneLineBlue2.setAntiAlias(true);//消除锯齿
        paintOneLineBlue2.setColor(Color.parseColor("#FF001D75"));//设置画笔颜色
        paintOneLineBlue2.setStyle(Paint.Style.FILL);//设置为空心
        paintOneLineBlue2.setStrokeWidth(2);//设置宽度

        paintOneLineRed = new Paint();
        paintOneLineRed.setAntiAlias(true);//消除锯齿
        paintOneLineRed.setColor(Color.parseColor("#FFF74F76"));//设置画笔颜色
        paintOneLineRed.setStyle(Paint.Style.FILL);//设置为空心
        paintOneLineRed.setStrokeWidth(2);//设置宽度

        paintOneLineGreen = new Paint();
        paintOneLineGreen.setAntiAlias(true);//消除锯齿
        paintOneLineGreen.setColor(Color.parseColor("#FF0DD1BD"));//设置画笔颜色
        paintOneLineGreen.setStyle(Paint.Style.FILL);//设置为空心
        paintOneLineGreen.setStrokeWidth(2);//设置宽度

        paintOneLinePurple = new Paint();
        paintOneLinePurple.setAntiAlias(true);//消除锯齿
        paintOneLinePurple.setColor(Color.parseColor("#FF8E61DD"));//设置画笔颜色
        paintOneLinePurple.setStyle(Paint.Style.FILL);//设置为空心
        paintOneLinePurple.setStrokeWidth(2);//设置宽度

        paintOneLineChocolate = new Paint();
        paintOneLineChocolate.setAntiAlias(true);//消除锯齿
        paintOneLineChocolate.setColor(Color.parseColor("#FFB4340A"));//设置画笔颜色
        paintOneLineChocolate.setStyle(Paint.Style.FILL);//设置为空心
        paintOneLineChocolate.setStrokeWidth(2);//设置宽度

        paintOneTextName = new Paint();
        paintOneTextName.setAntiAlias(true);//消除锯齿
        paintOneTextName.setColor(Color.BLACK);
        paintOneTextName.setTextAlign(Paint.Align.CENTER);
        paintOneTextName.setTextSize(25);
        paintOneTextName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));

        paintOneTextData = new Paint();
        paintOneTextData.setAntiAlias(true);//消除锯齿
        paintOneTextData.setColor(Color.BLACK);
        //paintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintOneTextData.setTextAlign(Paint.Align.CENTER);
        paintOneTextData.setTextSize(30);

        paintMOText = new Paint();
        paintMOText.setAntiAlias(true);//消除锯齿
        paintMOText.setColor(Color.BLACK);
        paintMOText.setTextAlign(Paint.Align.LEFT);
        paintMOText.setTextSize(20);

        paintMOTextName = new Paint();
        paintMOTextName.setAntiAlias(true);//消除锯齿
        paintMOTextName.setColor(Color.BLACK);
        paintMOTextName.setTextAlign(Paint.Align.LEFT);
        paintMOTextName.setTextSize(25);
        paintMOTextName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
    }
    public LineDraw getLineDraw(SkillLineNema skillLineNema_A){
        switch(skillLineNema_A){
            case Recommend_All:
            case Recommend_MA:
            case Recommend_Other:
                return new LineDrawRecommend();
            case RSI:
                return new LineDrawRSI();
            case Stoch:
            case Stoch_RSI:
                return new LineDrawStock();
            case MACD:
                return new LineDrawMACD();
            case ADX:
                return new LineDrawADX();
            case W_R:
                return new LineDrawW_R();
            case CCI20:
                return new LineDrawCCI();
            case ROC:
                return new LineDrawROC();
            case BBPower:
                return new LineDrawBBPower();
            case AO:
                return new LineDrawAO();
            case Mom:
                return new LineDrawMom();
            case Ichimoku:
            case VWMA:
            case HullMA9:
                return new LineDrawIchVo();
            case EMA:
            case SMA:
                return new LineDrawMA();
            case PivotM_Classic:
            case PivotM_Camarilla:
            case PivotM_Fibonacci:
            case PivotM_Woodie:
                return new LineDrawPivotM();
            case PivotM_Demark:
                return new LineDrawPivotM3();
            default:
                return new LineDraw();
        }
    }
    public class LineDraw{
        public Paint[] paints;
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos,String string_Name){
            float float_A=5F;
            //floatLine=1F;
            float float_B=(floats_WH[2]-floats_WH[0])-(float_A*4);
            int int_A=(((int)floats_WH[0])%2);
            //Log.w(this.toString(), "KJFdrawOneD1: "+"="+int_A +"="+((int)float_B) +"="+float_B );
            if(int_A==0)
            {
                canvas_A.drawRoundRect(floats_WH[0],floats_WH[1],floats_WH[2],floats_WH[3],float_A,float_A,paintOneColorBlue);
            }else {
                canvas_A.drawRoundRect(floats_WH[0],floats_WH[1],floats_WH[2],floats_WH[3],float_A,float_A,paintOneColorRed);
            }
//            canvas_A.drawRoundRect(floats_WH[0],floats_WH[1],floats_WH[0]+float_A*2+10,floats_WH[3],float_A,float_A,paintOneColorBlue);
//            canvas_A.drawRoundRect(floats_WH[2]-(float_A*2+10),floats_WH[1],floats_WH[2],floats_WH[3],float_A,float_A,paintOneColorRed);
            canvas_A.drawText(string_Name,floats_WH[0]+((floats_WH[2]-floats_WH[0])/2),floats_WH[1]+((floats_WH[3]-floats_WH[1])*0.85F),paintOneTextName);
            if(floats_Data!=null&&floats_Data.length>0)
            {
                if(floats_Data[0]!=null)
                {
                    float flaot_A=(((int)(floats_Data[0][1]*100000))/100000F);
                    //Log.w(this.toString(), "OJIFdrawOneF0: "+"="+flaot_A +"="+ (String.valueOf(flaot_A)+"="+floats_Data[0][1]));
                    canvas_A.drawText(String.valueOf(flaot_A),floats_WH[0]+((floats_WH[2]-floats_WH[0])/2),floats_WH[1]+((floats_WH[3]-floats_WH[1])*0.46F),paintOneTextData);
                }
            }
            canvas_A.drawLine(floats_WH[2]-50,floats_WH[1]+10,floats_WH[2]-50,floats_WH[3]-10, paintOneColorBlack);

            float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
            float float_H=floats_WH[1]+((floats_WH[3]-floats_WH[1])/2F);
            //Log.w(this.toString(), "IKNJHUdrawF0: "+"="+floats_Pos.length +"="+float_W );
            canvas_A.drawLine(floats_WH[0]+20,float_H,floats_WH[0]+float_W+20,float_H,paintOneColorBlue);
            for(int i=0;i<floats_Pos.length;i++)
                {
                    //if(floats_Pos[i]!=0||floats_Pos[i]!=floats_Pos.length-1)continue;
                    if(floats_Pos[i]<0)
                        {
                        continue;
                        }
                    float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                    //Log.w(this.toString(), "IKNJHUdrawF0: "+"="+i +"="+float_G);
                    canvas_A.drawRect(float_G-5,float_H-5,float_G+5,float_H+5,paintOneColorBlue);


                }
        }
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data,String string_Name){
            float float_A=5F;
            //floatLine=1F;
            float float_B=(floats_WH[2]-floats_WH[0])-(float_A*4);
            int int_A=(((int)floats_WH[0])%2);
            //Log.w(this.toString(), "KJFdrawOneD0: "+"="+int_A +"="+((int)float_B) +"="+float_B );
            if(int_A==0)
            {
                canvas_A.drawRoundRect(floats_WH[0],floats_WH[1],floats_WH[2],floats_WH[3],float_A,float_A,paintOneColorBlue);
            }else {
                canvas_A.drawRoundRect(floats_WH[0],floats_WH[1],floats_WH[2],floats_WH[3],float_A,float_A,paintOneColorRed);
            }
            //canvas_A.drawRoundRect(floats_WH[0],floats_WH[1],floats_WH[0]+float_A*2+10,floats_WH[3],float_A,float_A,paintOneColorBlue);
           // canvas_A.drawRoundRect(floats_WH[2]-(float_A*2+10),floats_WH[1],floats_WH[2],floats_WH[3],float_A,float_A,paintOneColorRed);
            canvas_A.drawText(string_Name,floats_WH[0]+((floats_WH[2]-floats_WH[0])/2),floats_WH[1]+((floats_WH[3]-floats_WH[1])*0.85F),paintOneTextName);
            if(floats_Data!=null&&floats_Data.length>0)
                {
                if(floats_Data[0]!=null)
                    {
                        float flaot_A=(((int)(floats_Data[0][1]*100000))/100000F);
                        //Log.w(this.toString(), "OJIFdrawOneF0: "+"="+flaot_A +"="+ (String.valueOf(flaot_A)+"="+floats_Data[0][1]));
                        canvas_A.drawText(String.valueOf(flaot_A),floats_WH[0]+((floats_WH[2]-floats_WH[0])/2),floats_WH[1]+((floats_WH[3]-floats_WH[1])*0.46F),paintOneTextData);
                    }
                }


        }
        void drawViewRect(Canvas canvas_A,int int_A,float[] floats_WH,String string_Name){
            float float_A=5F;
            Paint paint_A;
            if(int_A==0)
                {
                    paint_A= paintOneColorBlack;
                }else if(int_A==1){
                paint_A= paintOneColorBlue;
            }else {
                paint_A= paintOneColorRed;
            }

            canvas_A.drawRoundRect(floats_WH[0],floats_WH[1],floats_WH[2],floats_WH[3],float_A,float_A,paint_A);

            canvas_A.drawText(string_Name,floats_WH[0]+10,floats_WH[3]-10,paintMOText);
            canvas_A.drawLine(floats_WH[2]-50,floats_WH[1]+10,floats_WH[2]-50,floats_WH[3]-10, paintOneColorBlack);



        }

    }
    class LineDrawPivotM3 extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=1;
                float float_KU2=0;
                boolean bool_KU=true;
                if(true)
                {
                    for(int i=0;i<floats_Data.length;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(bool_KU)
                            {
                                bool_KU=false;
                                float_KU=floats_Data[i][2];
                                float_KU2=floats_Data[i][2];
                            }
                            for(int e=1;e<floats_Data[i].length;e++)
                            {
                                if(floats_Data[i][e]>float_KU)
                                {
                                    float_KU=floats_Data[i][e];
                                }else if(floats_Data[i][e]<float_KU2) {
                                    float_KU2=floats_Data[i][e];
                                }

                            }

//                            if(floats_Data[i][3]>float_KU)
//                            {
//                                float_KU=floats_Data[i][3];
//                            }else if(floats_Data[i][3]<float_KU2) {
//                                float_KU2=floats_Data[i][3];
//                            }

//                            if(Math.abs(floats_Data[i][2])>float_KU)
//                            {
//                                float_KU=Math.abs(floats_Data[i][2]);
//
//                            }
                        }
                    }
                }
                float float_KU3=float_KU-float_KU2;


                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][2];
                    float flaot_A2=floats_Data[0][1];
                    //Log.w(this.toString(), "LFIKINHOIdrawFF0: "+"="+ floats_Data[0].length);
                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(5,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                    if(flaot_A2==1)
                    {
                        int_UI=1;
                    }else if(flaot_A2==-1) {
                        int_UI=-1;
                    }else {
                        int_UI=0;
                    }

                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    //float flaot_JH=0;
                    float[] floats_JH=new float[4];
                    floats_JH[0]=0;
                    //floats_JH[1]=0;
                    float[] floats_GH=new float[4];
                    //float float_JT=((100+float_KU)/(float_KU*2F));
                    //float_JT=(floats_WH[3]-(float_H*float_JT))-20;
                    // float float_JT2=((-100+float_KU)/(float_KU*2F));
                    //float_JT2=(floats_WH[3]-(float_H*float_JT2))-20;
                    //canvas_A.drawLine(floats_WH[0],float_JT,floats_WH[0]+float_W,float_JT,paintOneLineBlack);
                    // canvas_A.drawLine(floats_WH[0],float_JT2,floats_WH[0]+float_W,float_JT2,paintOneLineBlack);

                    float float_JT3=(floats_WH[3]-(float_H*0.5F))-20;
                    if(true)
                    {
                        int int_CV=12;
//                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*0)),5, paintOneLinePurple);
//                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*1)),5,  paintOneLineBlue2);
                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*2)),5,  paintOneLineGreen);
                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*3)),5, paintOneLineBlue);
                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*4)),5, paintOneLineOreg);
//                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*5)),5, paintOneLineRed);
//                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*6)),5, paintOneLineChocolate);
                        if(floats_Data[0]!=null)
                        {
                            boolean bool_N=true;
                            if(floats_Data[0][4]<floats_Data[0][1])
                                {
                                    canvas_A.drawLine(floats_WH[0] + 20, floats_WH[1] + (107 - (int_CV * (2))), floats_WH[0] + 28, floats_WH[1] + (107 - (int_CV * (2))), paintOneLineBlack);
                                    float float_N=(((floats_Data[0][1]-float_KU2))/float_KU3);
                                    float_N=(floats_WH[3]-(float_H*float_N))-20;
                                    float float_N2=1;
                                    float_N2=(floats_WH[3]-(float_H*float_N2))-20;
                                    canvas_A.drawRect(floats_WH[0]+40,float_N,floats_WH[2]-70,float_N2,paintOneLineOreg);
                                }else  if(floats_Data[0][4]>floats_Data[0][3]){
                                canvas_A.drawLine(floats_WH[0] + 20, floats_WH[1] + (107 - (int_CV * (4))), floats_WH[0] + 28, floats_WH[1] + (107 - (int_CV * (4))), paintOneLineBlack);
                                float float_N=(((floats_Data[0][3]-float_KU2))/float_KU3);
                                float_N=(floats_WH[3]-(float_H*float_N))-20;
                                float float_N2=0;
                                float_N2=(floats_WH[3]-(float_H*float_N2))-20;
                                canvas_A.drawRect(floats_WH[0]+40,float_N,floats_WH[2]-70,float_N2,paintOneLineGreen);
                            }else {
                                canvas_A.drawLine(floats_WH[0] + 20, floats_WH[1] + (107 - (int_CV * (3))), floats_WH[0] + 28, floats_WH[1] + (107 - (int_CV * (3))), paintOneLineBlack);
                                float float_N=(((floats_Data[0][1]-float_KU2))/float_KU3);
                                float_N=(floats_WH[3]-(float_H*float_N))-20;
                                float float_N2=(((floats_Data[0][3]-float_KU2))/float_KU3);
                                float_N2=(floats_WH[3]-(float_H*float_N2))-20;
                                canvas_A.drawRect(floats_WH[0]+40,float_N,floats_WH[2]-70,float_N2,paintOneLineBlue);
                            }


                        }
                    }
                    // canvas_A.drawLine(floats_WH[0],float_JT3,floats_WH[0]+float_W,float_JT3,paintOneLineBlack);
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            // float float_GH=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            //float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            for(int w=0;w<floats_GH.length;w++)
                            {
                                floats_GH[w]=(((floats_Data[i][w+1]-float_KU2))/float_KU3);
                                floats_GH[w]=(floats_WH[3]-(float_H*floats_GH[w]))-20;
                            }

                            //floats_GH[1]=(((floats_Data[i][3]-float_KU2))/float_KU3);
                            //floats_GH[1]=(floats_WH[3]-(float_H*floats_GH[1]))-20;
                            //Log.w(this.toString(), "JIUBNdrawFF0: "+"="+i +"="+ floats_Data[i][2] +"="+floats_GH[0] +"="+(floats_Data[i][2]-float_KU2) +"="+float_KU2 +"="+float_KU3);
                            //floats_GH[1]=((floats_Data[i][2]+float_KU)/(float_KU*2F));
                            //floats_GH[1]=(floats_WH[3]-(float_H*floats_GH[1]))-20;
                            if(bool_JB)
                            {

                                // canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineRed);
//                                canvas_A.drawLine(flaot_JW,floats_JH[0],float_G,floats_GH[0], paintOneLineChocolate);
//                                canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineRed);
                                canvas_A.drawLine(flaot_JW,floats_JH[0],float_G,floats_GH[0], paintOneLineOreg);
                                canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineBlue);
                                canvas_A.drawLine(flaot_JW,floats_JH[2],float_G,floats_GH[2], paintOneLineGreen);
//                                canvas_A.drawLine(flaot_JW,floats_JH[5],float_G,floats_GH[5], paintOneLineBlue2);
//                                canvas_A.drawLine(flaot_JW,floats_JH[6],float_G,floats_GH[6], paintOneLinePurple);
                                canvas_A.drawLine(flaot_JW,floats_JH[3],float_G,floats_GH[3], paintOneLineBlack);

                            }else {
                                // canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineRed);
                                // canvas_A.drawRect(float_G-2,floats_GH[0]-2,float_G+2,floats_GH[0]+2, paintOneLineOreg);
                                //canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineBlack);
//                                canvas_A.drawRect(float_G-2,floats_GH[0]-2,float_G+2,floats_GH[0]+2, paintOneLineBlue);
//                                canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineRed);
                                canvas_A.drawRect(float_G-2,floats_GH[0]-2,float_G+2,floats_GH[0]+2, paintOneLineOreg);
                                canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineBlue);
                                canvas_A.drawRect(float_G-2,floats_GH[2]-2,float_G+2,floats_GH[2]+2, paintOneLineGreen);
//                                canvas_A.drawRect(float_G-2,floats_GH[5]-2,float_G+2,floats_GH[5]+2, paintOneLineChocolate);
//                                canvas_A.drawRect(float_G-2,floats_GH[6]-2,float_G+2,floats_GH[6]+2, paintOneLineBlue2);
                                canvas_A.drawRect(float_G-2,floats_GH[3]-2,float_G+2,floats_GH[3]+2, paintOneLineBlack);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            for(int r=0;r<floats_GH.length;r++)
                            {
                                floats_JH[r]= floats_GH[r];
                            }

                            // floats_JH[1]= floats_GH[1];


//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        ////Log.w(this.toString(), "JIUBNdrawFF1: "+"="+float_KU +"="+(new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString());
                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);



                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawPivotM extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=1;
                float float_KU2=0;
                boolean bool_KU=true;
                if(true)
                {
                    for(int i=0;i<floats_Data.length;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(bool_KU)
                            {
                                bool_KU=false;
                                float_KU=floats_Data[i][2];
                                float_KU2=floats_Data[i][2];
                            }
                            for(int e=1;e<floats_Data[i].length;e++)
                            {
                                if(floats_Data[i][e]>float_KU)
                                {
                                    float_KU=floats_Data[i][e];
                                }else if(floats_Data[i][e]<float_KU2) {
                                    float_KU2=floats_Data[i][e];
                                }

                            }

//                            if(floats_Data[i][3]>float_KU)
//                            {
//                                float_KU=floats_Data[i][3];
//                            }else if(floats_Data[i][3]<float_KU2) {
//                                float_KU2=floats_Data[i][3];
//                            }

//                            if(Math.abs(floats_Data[i][2])>float_KU)
//                            {
//                                float_KU=Math.abs(floats_Data[i][2]);
//
//                            }
                        }
                    }
                }
                float float_KU3=float_KU-float_KU2;


                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][2];
                    float flaot_A2=floats_Data[0][1];
                    //Log.w(this.toString(), "LFIKINHOIdrawFF0: "+"="+ floats_Data[0].length);
                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(5,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                    if(flaot_A2==1)
                    {
                        int_UI=1;
                    }else if(flaot_A2==-1) {
                        int_UI=-1;
                    }else {
                        int_UI=0;
                    }

                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    //float flaot_JH=0;
                    float[] floats_JH=new float[8];
                    floats_JH[0]=0;
                    //floats_JH[1]=0;
                    float[] floats_GH=new float[8];
                    //float float_JT=((100+float_KU)/(float_KU*2F));
                    //float_JT=(floats_WH[3]-(float_H*float_JT))-20;
                    // float float_JT2=((-100+float_KU)/(float_KU*2F));
                    //float_JT2=(floats_WH[3]-(float_H*float_JT2))-20;
                    //canvas_A.drawLine(floats_WH[0],float_JT,floats_WH[0]+float_W,float_JT,paintOneLineBlack);
                    // canvas_A.drawLine(floats_WH[0],float_JT2,floats_WH[0]+float_W,float_JT2,paintOneLineBlack);

                    float float_JT3=(floats_WH[3]-(float_H*0.5F))-20;
                    if(true)
                    {
                        int int_CV=12;
                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*0)),5, paintOneLinePurple);
                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*1)),5,  paintOneLineBlue2);
                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*2)),5,  paintOneLineGreen);
                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*3)),5, paintOneLineBlue);
                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*4)),5, paintOneLineOreg);
                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*5)),5, paintOneLineRed);
                        canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*6)),5, paintOneLineChocolate);
                        if(floats_Data[0]!=null)
                        {
                            boolean bool_N=true;
                            for(int i=1;i<4;i++)
                            {
                                if(floats_Data[0][8]<floats_Data[0][i]) {
                                    Paint paint_N=null;
                                    switch(i){
                                        case 1:
                                            paint_N=paintOneLineChocolate;
                                            break;
                                        case 2:
                                            paint_N=paintOneLineRed;
                                            break;
                                        case 3:
                                            paint_N=paintOneLineOreg;
                                            break;
                                        default:
                                            paint_N=paintOneLineBlue;
                                            break;
                                    }
                                    canvas_A.drawLine(floats_WH[0] + 20, floats_WH[1] + (107 - (int_CV * (i - 1))), floats_WH[0] + 28, floats_WH[1] + (107 - (int_CV * (i - 1))), paintOneLineBlack);
                                    float float_N=(((floats_Data[0][i]-float_KU2))/float_KU3);
                                    float_N=(floats_WH[3]-(float_H*float_N))-20;
                                    float float_N2=0;
                                    if(i!=4)
                                        {
                                            float_N2=(((floats_Data[0][i-1]-float_KU2))/float_KU3);
                                        }else {
                                        float_N2=0;
                                    }
                                    float_N2=(floats_WH[3]-(float_H*float_N2))-20;
                                    canvas_A.drawRect(floats_WH[0]+40,float_N,floats_WH[2]-70,float_N2,paint_N);

                                    bool_N=false;
                                    break;
                                }
                            }
                            if(bool_N)
                                {
                                    for(int i=7;i>=5;i--) {
                                        if (floats_Data[0][8] > floats_Data[0][i]) {
                                            Paint paint_N=null;
                                            switch(i){
                                                case 7:
                                                    paint_N=paintOneLinePurple;
                                                    break;
                                                case 6:
                                                    paint_N=paintOneLineBlue2;
                                                    break;
                                                case 5:
                                                    paint_N=paintOneLineGreen;
                                                    break;
                                                default:
                                                    paint_N=paintOneLineBlue;
                                                    break;
                                            }
                                            canvas_A.drawLine(floats_WH[0] + 20, floats_WH[1] + (107 - (int_CV * (i - 1))), floats_WH[0] + 28, floats_WH[1] + (107 - (int_CV * (i - 1))), paintOneLineBlack);
                                            float float_N=(((floats_Data[0][i]-float_KU2))/float_KU3);
                                            float_N=(floats_WH[3]-(float_H*float_N))-20;
                                            float float_N2=0;
                                            if(i!=7)
                                                {
                                                    float_N2=(((floats_Data[0][i+1]-float_KU2))/float_KU3);
                                                }else {
                                                float_N2=1;
                                            }
                                            float_N2=(floats_WH[3]-(float_H*float_N2))-20;
                                            canvas_A.drawRect(floats_WH[0]+40,float_N,floats_WH[2]-70,float_N2,paint_N);
                                            bool_N=false;
                                            break;
                                        }
                                    }
                                }
                            if(bool_N)
                                {

                                    canvas_A.drawLine(floats_WH[0] + 20, floats_WH[1] + (107 - (int_CV * (4 - 1))), floats_WH[0] + 28, floats_WH[1] + (107 - (int_CV * (4 - 1))), paintOneLineBlack);
                                    float float_N=(((floats_Data[0][3]-float_KU2))/float_KU3);
                                    float_N=(floats_WH[3]-(float_H*float_N))-20;
                                    float float_N2=0;
                                    float_N2=(((floats_Data[0][5]-float_KU2))/float_KU3);
                                    float_N2=(floats_WH[3]-(float_H*float_N2))-20;
                                    canvas_A.drawRect(floats_WH[0]+40,float_N,floats_WH[2]-70,float_N2,paintOneLineBlue);
                                }

                        }
                    }
                    // canvas_A.drawLine(floats_WH[0],float_JT3,floats_WH[0]+float_W,float_JT3,paintOneLineBlack);
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            // float float_GH=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            //float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            for(int w=0;w<floats_GH.length;w++)
                            {
                                floats_GH[w]=(((floats_Data[i][w+1]-float_KU2))/float_KU3);
                                floats_GH[w]=(floats_WH[3]-(float_H*floats_GH[w]))-20;
                            }

                            //floats_GH[1]=(((floats_Data[i][3]-float_KU2))/float_KU3);
                            //floats_GH[1]=(floats_WH[3]-(float_H*floats_GH[1]))-20;
                            //Log.w(this.toString(), "JIUBNdrawFF0: "+"="+i +"="+ floats_Data[i][2] +"="+floats_GH[0] +"="+(floats_Data[i][2]-float_KU2) +"="+float_KU2 +"="+float_KU3);
                            //floats_GH[1]=((floats_Data[i][2]+float_KU)/(float_KU*2F));
                            //floats_GH[1]=(floats_WH[3]-(float_H*floats_GH[1]))-20;
                            if(bool_JB)
                            {

                                // canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineRed);
                                canvas_A.drawLine(flaot_JW,floats_JH[0],float_G,floats_GH[0], paintOneLineChocolate);
                                canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineRed);
                                canvas_A.drawLine(flaot_JW,floats_JH[2],float_G,floats_GH[2], paintOneLineOreg);
                                canvas_A.drawLine(flaot_JW,floats_JH[3],float_G,floats_GH[3], paintOneLineBlue);
                                canvas_A.drawLine(flaot_JW,floats_JH[4],float_G,floats_GH[4], paintOneLineGreen);
                                canvas_A.drawLine(flaot_JW,floats_JH[5],float_G,floats_GH[5], paintOneLineBlue2);
                                canvas_A.drawLine(flaot_JW,floats_JH[6],float_G,floats_GH[6], paintOneLinePurple);
                                canvas_A.drawLine(flaot_JW,floats_JH[7],float_G,floats_GH[7], paintOneLineBlack);

                            }else {
                                // canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineRed);
                                // canvas_A.drawRect(float_G-2,floats_GH[0]-2,float_G+2,floats_GH[0]+2, paintOneLineOreg);
                                //canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineBlack);
                                canvas_A.drawRect(float_G-2,floats_GH[0]-2,float_G+2,floats_GH[0]+2, paintOneLineBlue);
                                canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineRed);
                                canvas_A.drawRect(float_G-2,floats_GH[2]-2,float_G+2,floats_GH[2]+2, paintOneLineGreen);
                                canvas_A.drawRect(float_G-2,floats_GH[3]-2,float_G+2,floats_GH[3]+2, paintOneLineOreg);
                                canvas_A.drawRect(float_G-2,floats_GH[4]-2,float_G+2,floats_GH[4]+2, paintOneLinePurple);
                                canvas_A.drawRect(float_G-2,floats_GH[5]-2,float_G+2,floats_GH[5]+2, paintOneLineChocolate);
                                canvas_A.drawRect(float_G-2,floats_GH[6]-2,float_G+2,floats_GH[6]+2, paintOneLineBlue2);
                                canvas_A.drawRect(float_G-2,floats_GH[7]-2,float_G+2,floats_GH[7]+2, paintOneLineBlack);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            for(int r=0;r<floats_GH.length;r++)
                            {
                                floats_JH[r]= floats_GH[r];
                            }

                            // floats_JH[1]= floats_GH[1];


//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        ////Log.w(this.toString(), "JIUBNdrawFF1: "+"="+float_KU +"="+(new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString());
                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);



                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawMA extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=1;
                float float_KU2=0;
                boolean bool_KU=true;
                if(true)
                {
                    for(int i=0;i<floats_Data.length;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(bool_KU)
                            {
                                bool_KU=false;
                                float_KU=floats_Data[i][2];
                                float_KU2=floats_Data[i][2];
                            }
                            for(int e=1;e<floats_Data[i].length;e++)
                                {
                                    if(floats_Data[i][e]>float_KU)
                                    {
                                        float_KU=floats_Data[i][e];
                                    }else if(floats_Data[i][e]<float_KU2) {
                                        float_KU2=floats_Data[i][e];
                                    }

                                }

//                            if(floats_Data[i][3]>float_KU)
//                            {
//                                float_KU=floats_Data[i][3];
//                            }else if(floats_Data[i][3]<float_KU2) {
//                                float_KU2=floats_Data[i][3];
//                            }

//                            if(Math.abs(floats_Data[i][2])>float_KU)
//                            {
//                                float_KU=Math.abs(floats_Data[i][2]);
//
//                            }
                        }
                    }
                }
                float float_KU3=float_KU-float_KU2;


                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][2];
                    float flaot_A2=floats_Data[0][1];
                    //Log.w(this.toString(), "LFIKINHOIdrawFF0: "+"="+ floats_Data[0].length);
                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(5,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                    if(flaot_A2==1)
                    {
                        int_UI=1;
                    }else if(flaot_A2==-1) {
                        int_UI=-1;
                    }else {
                        int_UI=0;
                    }

                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    //float flaot_JH=0;
                    float[] floats_JH=new float[8];
                    floats_JH[0]=0;
                    //floats_JH[1]=0;
                    float[] floats_GH=new float[8];
                    //float float_JT=((100+float_KU)/(float_KU*2F));
                    //float_JT=(floats_WH[3]-(float_H*float_JT))-20;
                    // float float_JT2=((-100+float_KU)/(float_KU*2F));
                    //float_JT2=(floats_WH[3]-(float_H*float_JT2))-20;
                    //canvas_A.drawLine(floats_WH[0],float_JT,floats_WH[0]+float_W,float_JT,paintOneLineBlack);
                    // canvas_A.drawLine(floats_WH[0],float_JT2,floats_WH[0]+float_W,float_JT2,paintOneLineBlack);

                    float float_JT3=(floats_WH[3]-(float_H*0.5F))-20;
                    if(true)
                        {
                            int int_CV=12;
                            canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*0)),5, paintOneLineBlue);
                            canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*1)),5,  paintOneLineRed);
                            canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*2)),5,  paintOneLineGreen);
                            canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*3)),5, paintOneLineOreg);
                            canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*4)),5, paintOneLinePurple);
                            canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*5)),5, paintOneLineChocolate);
                            canvas_A.drawCircle(floats_WH[0]+10,floats_WH[1]+(35+(int_CV*6)),5, paintOneLineBlue2);
                            if(floats_Data[0]!=null)
                                {
                                for(int i=1;i<floats_Data.length-1;i++)
                                    {
                                        if(floats_Data[0][8]>floats_Data[0][i])canvas_A.drawLine(floats_WH[0]+20,floats_WH[1]+(35+(int_CV*(i-1))),floats_WH[0]+28,floats_WH[1]+(35+(int_CV*(i-1))),paintOneColorBlack);
                                    }
                                }
                        }
                    // canvas_A.drawLine(floats_WH[0],float_JT3,floats_WH[0]+float_W,float_JT3,paintOneLineBlack);
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            // float float_GH=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            //float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            for(int w=0;w<floats_GH.length;w++)
                                {
                                    floats_GH[w]=(((floats_Data[i][w+1]-float_KU2))/float_KU3);
                                    floats_GH[w]=(floats_WH[3]-(float_H*floats_GH[w]))-20;
                                }

                            //floats_GH[1]=(((floats_Data[i][3]-float_KU2))/float_KU3);
                            //floats_GH[1]=(floats_WH[3]-(float_H*floats_GH[1]))-20;
                            //Log.w(this.toString(), "JIUBNdrawFF0: "+"="+i +"="+ floats_Data[i][2] +"="+floats_GH[0] +"="+(floats_Data[i][2]-float_KU2) +"="+float_KU2 +"="+float_KU3);
                            //floats_GH[1]=((floats_Data[i][2]+float_KU)/(float_KU*2F));
                            //floats_GH[1]=(floats_WH[3]-(float_H*floats_GH[1]))-20;
                            if(bool_JB)
                            {

                                // canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineRed);
                                canvas_A.drawLine(flaot_JW,floats_JH[0],float_G,floats_GH[0], paintOneLineBlue);
                                canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineRed);
                                canvas_A.drawLine(flaot_JW,floats_JH[2],float_G,floats_GH[2], paintOneLineGreen);
                                canvas_A.drawLine(flaot_JW,floats_JH[3],float_G,floats_GH[3], paintOneLineOreg);
                                canvas_A.drawLine(flaot_JW,floats_JH[4],float_G,floats_GH[4], paintOneLinePurple);
                                canvas_A.drawLine(flaot_JW,floats_JH[5],float_G,floats_GH[5], paintOneLineChocolate);
                                canvas_A.drawLine(flaot_JW,floats_JH[6],float_G,floats_GH[6], paintOneLineBlue2);
                                canvas_A.drawLine(flaot_JW,floats_JH[7],float_G,floats_GH[7], paintOneLineBlack);

                            }else {
                                // canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineRed);
                               // canvas_A.drawRect(float_G-2,floats_GH[0]-2,float_G+2,floats_GH[0]+2, paintOneLineOreg);
                                //canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineBlack);
                                canvas_A.drawRect(float_G-2,floats_GH[0]-2,float_G+2,floats_GH[0]+2, paintOneLineBlue);
                                canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineRed);
                                canvas_A.drawRect(float_G-2,floats_GH[2]-2,float_G+2,floats_GH[2]+2, paintOneLineGreen);
                                canvas_A.drawRect(float_G-2,floats_GH[3]-2,float_G+2,floats_GH[3]+2, paintOneLineOreg);
                                canvas_A.drawRect(float_G-2,floats_GH[4]-2,float_G+2,floats_GH[4]+2, paintOneLinePurple);
                                canvas_A.drawRect(float_G-2,floats_GH[5]-2,float_G+2,floats_GH[5]+2, paintOneLineChocolate);
                                canvas_A.drawRect(float_G-2,floats_GH[6]-2,float_G+2,floats_GH[6]+2, paintOneLineBlue2);
                                canvas_A.drawRect(float_G-2,floats_GH[7]-2,float_G+2,floats_GH[7]+2, paintOneLineBlack);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            for(int r=0;r<floats_GH.length;r++)
                                {
                                    floats_JH[r]= floats_GH[r];
                                }

                            // floats_JH[1]= floats_GH[1];


//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        ////Log.w(this.toString(), "JIUBNdrawFF1: "+"="+float_KU +"="+(new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString());
                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);



                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawIchVo extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=1;
                float float_KU2=0;
                boolean bool_KU=true;
                if(true)
                {
                    for(int i=0;i<floats_Data.length;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(bool_KU)
                                {
                                    bool_KU=false;
                                    float_KU=floats_Data[i][2];
                                    float_KU2=floats_Data[i][2];
                                }
                            if(floats_Data[i][2]>float_KU)
                            {
                                float_KU=floats_Data[i][2];
                            }else if(floats_Data[i][2]<float_KU2) {
                                float_KU2=floats_Data[i][2];
                            }
                            if(floats_Data[i][3]>float_KU)
                            {
                                float_KU=floats_Data[i][3];
                            }else if(floats_Data[i][3]<float_KU2) {
                                float_KU2=floats_Data[i][3];
                            }

//                            if(Math.abs(floats_Data[i][2])>float_KU)
//                            {
//                                float_KU=Math.abs(floats_Data[i][2]);
//
//                            }
                        }
                    }
                }
                float float_KU3=float_KU-float_KU2;


                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][2];
                    float flaot_A2=floats_Data[0][1];
                    ////Log.w(this.toString(), "LMNINHOIdrawFF0: "+"="+ flaot_A +"="+flaot_A2);
                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(5,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                    if(flaot_A2==1)
                    {
                        int_UI=1;
                    }else if(flaot_A2==-1) {
                        int_UI=-1;
                    }else {
                        int_UI=0;
                    }

                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    //float flaot_JH=0;
                    float[] floats_JH=new float[2];
                    floats_JH[0]=0;
                    //floats_JH[1]=0;
                    float[] floats_GH=new float[2];
                    //float float_JT=((100+float_KU)/(float_KU*2F));
                    //float_JT=(floats_WH[3]-(float_H*float_JT))-20;
                    // float float_JT2=((-100+float_KU)/(float_KU*2F));
                    //float_JT2=(floats_WH[3]-(float_H*float_JT2))-20;
                    //canvas_A.drawLine(floats_WH[0],float_JT,floats_WH[0]+float_W,float_JT,paintOneLineBlack);
                    // canvas_A.drawLine(floats_WH[0],float_JT2,floats_WH[0]+float_W,float_JT2,paintOneLineBlack);

                    float float_JT3=(floats_WH[3]-(float_H*0.5F))-20;
                   // canvas_A.drawLine(floats_WH[0],float_JT3,floats_WH[0]+float_W,float_JT3,paintOneLineBlack);
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            // float float_GH=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            //float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            floats_GH[0]=(((floats_Data[i][2]-float_KU2))/float_KU3);
                            floats_GH[0]=(floats_WH[3]-(float_H*floats_GH[0]))-20;
                            floats_GH[1]=(((floats_Data[i][3]-float_KU2))/float_KU3);
                            floats_GH[1]=(floats_WH[3]-(float_H*floats_GH[1]))-20;
                            //Log.w(this.toString(), "JIUBNdrawFF0: "+"="+i +"="+ floats_Data[i][2] +"="+floats_GH[0] +"="+(floats_Data[i][2]-float_KU2) +"="+float_KU2 +"="+float_KU3);
                            //floats_GH[1]=((floats_Data[i][2]+float_KU)/(float_KU*2F));
                            //floats_GH[1]=(floats_WH[3]-(float_H*floats_GH[1]))-20;
                            if(bool_JB)
                            {

                                // canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineRed);
                                canvas_A.drawLine(flaot_JW,floats_JH[0],float_G,floats_GH[0], paintOneLineOreg);
                                canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineBlack);

                            }else {
                                // canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineRed);
                                canvas_A.drawRect(float_G-2,floats_GH[0]-2,float_G+2,floats_GH[0]+2, paintOneLineOreg);
                                canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineBlack);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            floats_JH[0]= floats_GH[0];
                            floats_JH[1]= floats_GH[1];
                            // floats_JH[1]= floats_GH[1];


//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        ////Log.w(this.toString(), "JIUBNdrawFF1: "+"="+float_KU +"="+(new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString());
                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);



                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawMom extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=0.0005F;
                if(true)
                {
                    for(int i=0;i<floats_Data.length;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(Math.abs(floats_Data[i][1])>float_KU)
                            {
                                float_KU=Math.abs(floats_Data[i][1]);

                            }
//                            if(Math.abs(floats_Data[i][2])>float_KU)
//                            {
//                                float_KU=Math.abs(floats_Data[i][2]);
//
//                            }
                        }
                    }
                }


                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][1];
                    ////Log.w(this.toString(), "LMNINHOIdrawFF0: "+"="+ flaot_A +"="+flaot_A2);
                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(5,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                   if(floats_Data[1]!=null)
                    {
                        float flaot_A2=floats_Data[1][1];
                        if(flaot_A2<flaot_A)
                        {
                            int_UI=1;
                        }else {
                            int_UI=-1;
                        }
                    }

                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    //float flaot_JH=0;
                    float[] floats_JH=new float[1];
                    floats_JH[0]=0;
                    //floats_JH[1]=0;
                    float[] floats_GH=new float[1];
                    //float float_JT=((100+float_KU)/(float_KU*2F));
                    //float_JT=(floats_WH[3]-(float_H*float_JT))-20;
                    // float float_JT2=((-100+float_KU)/(float_KU*2F));
                    //float_JT2=(floats_WH[3]-(float_H*float_JT2))-20;
                    //canvas_A.drawLine(floats_WH[0],float_JT,floats_WH[0]+float_W,float_JT,paintOneLineBlack);
                    // canvas_A.drawLine(floats_WH[0],float_JT2,floats_WH[0]+float_W,float_JT2,paintOneLineBlack);

                    float float_JT3=(floats_WH[3]-(float_H*0.5F))-20;
                    canvas_A.drawLine(floats_WH[0],float_JT3,floats_WH[0]+float_W,float_JT3,paintOneLineBlack);
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            // float float_GH=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            //float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            floats_GH[0]=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            floats_GH[0]=(floats_WH[3]-(float_H*floats_GH[0]))-20;
                            //floats_GH[1]=((floats_Data[i][2]+float_KU)/(float_KU*2F));
                            //floats_GH[1]=(floats_WH[3]-(float_H*floats_GH[1]))-20;
                            if(bool_JB)
                            {

                                // canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineRed);
                                canvas_A.drawLine(flaot_JW,floats_JH[0],float_G,floats_GH[0], paintOneLineBlue);

                            }else {
                                // canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineRed);
                                canvas_A.drawRect(float_G-2,floats_GH[0]-2,float_G+2,floats_GH[0]+2, paintOneLineBlue);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            floats_JH[0]= floats_GH[0];
                            // floats_JH[1]= floats_GH[1];


//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText((new BigDecimal(-float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);



                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawAO extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=0.0002F;
                if(true)
                {
                    for(int i=0;i<floats_Data.length;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(Math.abs(floats_Data[i][1])>float_KU)
                            {
                                float_KU=Math.abs(floats_Data[i][1]);

                            }
//                            if(Math.abs(floats_Data[i][2])>float_KU)
//                            {
//                                float_KU=Math.abs(floats_Data[i][2]);
//
//                            }
                        }
                    }
                }


                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][1];
                    ////Log.w(this.toString(), "LMNINHOIdrawFF0: "+"="+ flaot_A +"="+flaot_A2);

                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(5,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                    if(floats_Data[1]!=null)
                        {
                            float flaot_A2=floats_Data[1][1];
                            if(flaot_A2<flaot_A)
                            {
                                int_UI=1;
                            }else {
                                int_UI=-1;
                            }
                        }

                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    float flaot_JW2=(floats_WH[3]-(float_H*0.5F))-20;
                    //float flaot_JH=0;
//                    float[] floats_JH=new float[1];
//                    floats_JH[0]=0;
                    //floats_JH[1]=0;
                    float[] floats_GH=new float[1];
                    //float float_JT=((100+float_KU)/(float_KU*2F));
                    //float_JT=(floats_WH[3]-(float_H*float_JT))-20;
                    // float float_JT2=((-100+float_KU)/(float_KU*2F));
                    //float_JT2=(floats_WH[3]-(float_H*float_JT2))-20;
                    //canvas_A.drawLine(floats_WH[0],float_JT,floats_WH[0]+float_W,float_JT,paintOneLineBlack);
                    // canvas_A.drawLine(floats_WH[0],float_JT2,floats_WH[0]+float_W,float_JT2,paintOneLineBlack);

                    float float_JT3=(floats_WH[3]-(float_H*0.5F))-20;
                    canvas_A.drawLine(floats_WH[0],float_JT3,floats_WH[0]+float_W,float_JT3,paintOneLineBlack);
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;

                            // float float_GH=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            //float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            floats_GH[0]=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            floats_GH[0]=(floats_WH[3]-(float_H*floats_GH[0]))-20;
                            Paint paint_G=paintOneLineBlack;
                            int int_I=0;
                            if(floats_Data[i+1]!=null)
                                {
                                    if(floats_Data[i][1]>floats_Data[i+1][1])
                                        {
                                            paint_G=paintOneLineGreen;
                                        }else {
                                        paint_G=paintOneLineRed;
                                    }

                                }
                            canvas_A.drawLine(float_G,flaot_JW2,float_G,floats_GH[0], paint_G);
                            //floats_GH[1]=((floats_Data[i][2]+float_KU)/(float_KU*2F));
                            //floats_GH[1]=(floats_WH[3]-(float_H*floats_GH[1]))-20;
//                            if(bool_JB)
//                            {
//
//                                // canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineRed);
//
//
//                            }else {
//                                // canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineRed);
//                                canvas_A.drawRect(float_G-2,floats_GH[0]-2,float_G+2,floats_GH[0]+2, paint_G);
//                            }
//                            bool_JB=true;
                            //flaot_JW=float_G;
                            //floats_JH[0]= floats_GH[0];
                            // floats_JH[1]= floats_GH[1];


//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText((new BigDecimal(-float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);



                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawBBPower extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=0.0005F;
                if(true)
                {
                    for(int i=0;i<floats_Data.length;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(Math.abs(floats_Data[i][1])>float_KU)
                            {
                                float_KU=Math.abs(floats_Data[i][1]);

                            }
//                            if(Math.abs(floats_Data[i][2])>float_KU)
//                            {
//                                float_KU=Math.abs(floats_Data[i][2]);
//
//                            }
                        }
                    }
                }


                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][1];
                    float flaot_A2=floats_Data[0][2];
                    ////Log.w(this.toString(), "LMNINHOIdrawFF0: "+"="+ flaot_A +"="+flaot_A2);
                    if(flaot_A2>=1)
                    {
                        int_UI=1;
                    }else if(flaot_A2<=-1) {
                        int_UI=-1;
                    }else {
                        int_UI=0;
                    }
                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(5,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                    canvas_A.drawText((new BigDecimal(flaot_A2).setScale(5,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+110,floats_WH[1]+24,paintMOText);


                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    //float flaot_JH=0;
                    float[] floats_JH=new float[1];
                    floats_JH[0]=0;
                    //floats_JH[1]=0;
                    float[] floats_GH=new float[1];
                    //float float_JT=((100+float_KU)/(float_KU*2F));
                    //float_JT=(floats_WH[3]-(float_H*float_JT))-20;
                    // float float_JT2=((-100+float_KU)/(float_KU*2F));
                    //float_JT2=(floats_WH[3]-(float_H*float_JT2))-20;
                    //canvas_A.drawLine(floats_WH[0],float_JT,floats_WH[0]+float_W,float_JT,paintOneLineBlack);
                    // canvas_A.drawLine(floats_WH[0],float_JT2,floats_WH[0]+float_W,float_JT2,paintOneLineBlack);

                    float float_JT3=(floats_WH[3]-(float_H*0.5F))-20;
                    canvas_A.drawLine(floats_WH[0],float_JT3,floats_WH[0]+float_W,float_JT3,paintOneLineBlack);
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                           // float float_GH=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            //float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            floats_GH[0]=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            floats_GH[0]=(floats_WH[3]-(float_H*floats_GH[0]))-20;
                            //floats_GH[1]=((floats_Data[i][2]+float_KU)/(float_KU*2F));
                            //floats_GH[1]=(floats_WH[3]-(float_H*floats_GH[1]))-20;
                            if(bool_JB)
                            {

                               // canvas_A.drawLine(flaot_JW,floats_JH[1],float_G,floats_GH[1], paintOneLineRed);
                                canvas_A.drawLine(flaot_JW,floats_JH[0],float_G,floats_GH[0], paintOneLineBlue);

                            }else {
                               // canvas_A.drawRect(float_G-2,floats_GH[1]-2,float_G+2,floats_GH[1]+2, paintOneLineRed);
                                canvas_A.drawRect(float_G-2,floats_GH[0]-2,float_G+2,floats_GH[0]+2, paintOneLineBlue);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            floats_JH[0]= floats_GH[0];
                           // floats_JH[1]= floats_GH[1];


//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText((new BigDecimal(-float_KU).setScale(5,BigDecimal.ROUND_DOWN)).toString(),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);



                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawROC extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=0.1F;
                if(true)
                {
                    for(int i=0;i<floats_Data.length;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(Math.abs(floats_Data[i][1])>float_KU)
                            {
                                float_KU=Math.abs(floats_Data[i][1]);

                            }
                        }
                    }
                }


                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][1];

                    if(flaot_A>0)
                    {
                        int_UI=1;
                    }else {
                        int_UI=-1;
                    }
                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(2,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);



                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    float flaot_JH=0;
                    //float float_JT=((100+float_KU)/(float_KU*2F));
                    //float_JT=(floats_WH[3]-(float_H*float_JT))-20;
                   // float float_JT2=((-100+float_KU)/(float_KU*2F));
                    //float_JT2=(floats_WH[3]-(float_H*float_JT2))-20;
                    //canvas_A.drawLine(floats_WH[0],float_JT,floats_WH[0]+float_W,float_JT,paintOneLineBlack);
                   // canvas_A.drawLine(floats_WH[0],float_JT2,floats_WH[0]+float_W,float_JT2,paintOneLineBlack);

                    float float_JT3=(floats_WH[3]-(float_H*0.5F))-20;
                    canvas_A.drawLine(floats_WH[0],float_JT3,floats_WH[0]+float_W,float_JT3,paintOneLineBlack);
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            float float_GH=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            if(bool_JB)
                            {

                                canvas_A.drawLine(flaot_JW,flaot_JH,float_G,float_GH, paintOneLineBlue);

                            }else {
                                canvas_A.drawRect(float_G-2,float_GH-2,float_G+2,float_GH+2, paintOneLineBlue);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            flaot_JH=float_GH;


//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(2,BigDecimal.ROUND_DOWN)).toString(),float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText((new BigDecimal(-float_KU).setScale(2,BigDecimal.ROUND_DOWN)).toString(),float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);



                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawCCI extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=150;
                if(true)
                    {
                    for(int i=0;i<floats_Data.length;i++)
                        {
                        if(floats_Data[i]!=null)
                            {
                            if(Math.abs(floats_Data[i][1])>float_KU)
                                {
                                    //Log.w(this.toString(), "FKIOdrawDO0: "+"="+i +"="+float_KU +"="+floats_Data[i][1] );
                                    float_KU=Math.abs(floats_Data[i][1]);

                                }
                            }
                        }
                    }


                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][1];

                    if(flaot_A>100)
                    {
                        int_UI=1;
                    }else if(flaot_A<-100){
                        int_UI=-1;
                    }else {
                        int_UI=0;
                    }
                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(2,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);



                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    float flaot_JH=0;
                    float float_JT=((100+float_KU)/(float_KU*2F));
                    float_JT=(floats_WH[3]-(float_H*float_JT))-20;
                    float float_JT2=((-100+float_KU)/(float_KU*2F));
                    float_JT2=(floats_WH[3]-(float_H*float_JT2))-20;
                    canvas_A.drawLine(floats_WH[0],float_JT,floats_WH[0]+float_W,float_JT,paintOneLineBlack);
                    canvas_A.drawLine(floats_WH[0],float_JT2,floats_WH[0]+float_W,float_JT2,paintOneLineBlack);

                    float float_JT3=(floats_WH[3]-(float_H*0.5F))-20;
                    canvas_A.drawLine(floats_WH[0],float_JT3,floats_WH[0]+float_W,float_JT3,paintOneLineBlack);
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            float float_GH=((floats_Data[i][1]+float_KU)/(float_KU*2F));
                            float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            if(bool_JB)
                            {

                                canvas_A.drawLine(flaot_JW,flaot_JH,float_G,float_GH, paintOneLineBlue);

                            }else {
                                canvas_A.drawRect(float_G-2,float_GH-2,float_G+2,float_GH+2, paintOneLineBlue);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            flaot_JH=float_GH;


//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(2,BigDecimal.ROUND_DOWN)).toString(),float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=((100+float_KU)/(float_KU*2F));
                        float_P=(floats_WH[3]-(float_H*float_JT))-20;
                        canvas_A.drawText("100",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.2F))-20;
                        canvas_A.drawText("-100",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText((new BigDecimal(-float_KU).setScale(2,BigDecimal.ROUND_DOWN)).toString(),float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);



                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawW_R extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=-100F;


                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][1];

                    if(flaot_A<=-80)
                        {
                            int_UI=1;
                        }else if(flaot_A>=-20){
                        int_UI=-1;
                    }else {
                        int_UI=0;
                    }
                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(2,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);



                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    float flaot_JH=0;
                    float float_JT=(floats_WH[3]-(float_H*0.8F))-20;
                    float float_JT2=(floats_WH[3]-(float_H*0.2F))-20;
                    canvas_A.drawLine(floats_WH[0],float_JT,floats_WH[0]+float_W,float_JT,paintOneLineBlack);
                    canvas_A.drawLine(floats_WH[0],float_JT2,floats_WH[0]+float_W,float_JT2,paintOneLineBlack);
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            float float_GH=(floats_Data[i][1]/float_KU);
                            float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            if(bool_JB)
                            {

                                canvas_A.drawLine(flaot_JW,flaot_JH,float_G,float_GH, paintOneLineBlue);

                            }else {
                                canvas_A.drawRect(float_G-2,float_GH-2,float_G+2,float_GH+2, paintOneLineBlue);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            flaot_JH=float_GH;


//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.8F))-20;
                        canvas_A.drawText("-20",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.2F))-20;
                        canvas_A.drawText("-80",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText("-100",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);



                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawADX extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=50F;
                for(int i=0;i<floats_Data.length;i++) {
                    if (floats_Data[i] != null) {

                        if (Math.abs(floats_Data[i][1]) > float_KU) {
                            float_KU = Math.abs(floats_Data[i][1]);

                        }
                        if (Math.abs(floats_Data[i][2]) > float_KU) {
                            float_KU = Math.abs(floats_Data[i][2]);

                        }
                        if (Math.abs(floats_Data[i][3]) > float_KU) {
                            float_KU = Math.abs(floats_Data[i][3]);

                        }
                    }
                }

                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][1];
                    float flaot_A2=floats_Data[0][2];
                    float flaot_A3=floats_Data[0][3];
                    //Log.w(this.toString(), "MACDdrawFS0: "+"="+flaot_A +"="+ flaot_A2 +"="+flaot_A3 +"="+BigDecimal.valueOf(((int)(flaot_A*100000)/100000F)) +"="+(new BigDecimal(flaot_A).setScale(5,BigDecimal.ROUND_DOWN)));

                    if(flaot_A>=25)
                    {
                       if(flaot_A2>flaot_A3)
                           {
                               int_UI=1;
                           }else {
                           int_UI=-1;
                       }
                    }else {
                        int_UI=0;
                    }
                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(2,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                    canvas_A.drawText((new BigDecimal(flaot_A2).setScale(2,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+110,floats_WH[1]+24,paintMOText);
                    canvas_A.drawText((new BigDecimal(flaot_A3).setScale(2,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+210,floats_WH[1]+24,paintMOText);
//                    if(floats_Data[0][1]>=80)
//                    {
//                        int_UI=1;
//                    }else if(floats_Data[0][1]<=20) {
//                        int_UI=-1;
//                    }else {
//                        int_UI=0;
//                    }


                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    //Log.w(this.toString(), "IKNJHUdrawF0: "+"="+floats_Pos.length +"="+float_W );
                    //canvas_A.drawLine(floats_WH[0]+20,float_H,floats_WH[0]+float_W+20,float_H,paintOneColorBlue);
                    //Log.w(this.toString(), "IKNJHUdrawF4: "+"="+floats_Data.length );
                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    float flaot_JH=0;
                    float flaot_JH2=0;
                    float flaot_JH3=0;
                    //float float_TP=float_W*((floats_Pos[0]-floats_Pos[1])*0.4F);
                    if(true)
                    {

                    }
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            float float_GH=(floats_Data[i][1]/float_KU);
                            float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            float float_GH2=(floats_Data[i][2]/float_KU);
                            float_GH2=(floats_WH[3]-(float_H*float_GH2))-20;
                            float float_GH3=(floats_Data[i][3]/float_KU);
                            float_GH3=((floats_WH[3])-(float_H*float_GH3))-20;
                            if(bool_JB)
                            {

                                canvas_A.drawLine(flaot_JW,flaot_JH3,float_G,float_GH3, paintOneLineOreg);
                                canvas_A.drawLine(flaot_JW,flaot_JH2,float_G,float_GH2, paintOneLineGreen);
                                canvas_A.drawLine(flaot_JW,flaot_JH,float_G,float_GH, paintOneLineBlue);

                            }else {
                                canvas_A.drawRect(float_G-2,float_GH3-2,float_G+2,float_GH3+2, paintOneLineOreg);
                                canvas_A.drawRect(float_G-2,float_GH2-2,float_G+2,float_GH2+2, paintOneLineGreen);
                                canvas_A.drawRect(float_G-2,float_GH-2,float_G+2,float_GH+2, paintOneLineBlue);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            flaot_JH=float_GH;
                            flaot_JH2=float_GH2;
                            flaot_JH3=float_GH3;


//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText(String.valueOf((int)float_KU),float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);



                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawMACD extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {

                float float_KU=0.00003F;
                for(int i=0;i<floats_Data.length;i++) {
                    if (floats_Data[i] != null) {

                        if (Math.abs(floats_Data[i][1]) > float_KU) {
                            float_KU += 0.00002F;
                            if (Math.abs(floats_Data[i][1]) > float_KU) {
                                float_KU = Math.abs(floats_Data[i][1]);
                            }

                        }
                        if (Math.abs(floats_Data[i][2]) > float_KU) {
                            float_KU += 0.00002F;
                            if (Math.abs(floats_Data[i][2]) > float_KU) {
                                float_KU = Math.abs(floats_Data[i][2]);
                            }

                        }
                    }
                }

                if(floats_Data[0]!=null)
                {
                    float flaot_A=floats_Data[0][1];
                    float flaot_A2=floats_Data[0][2];
                    float flaot_A3=floats_Data[0][1]-floats_Data[0][2];
                    //Log.w(this.toString(), "MACDdrawFS0: "+"="+flaot_A +"="+ flaot_A2 +"="+flaot_A3 +"="+BigDecimal.valueOf(((int)(flaot_A*100000)/100000F)) +"="+(new BigDecimal(flaot_A).setScale(5,BigDecimal.ROUND_DOWN)));

                    if(flaot_A3>=0)
                        {
                            int_UI=1;
                        }else {
                        int_UI=-1;
                    }
                    canvas_A.drawText((new BigDecimal(flaot_A3).setScale(6,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                    canvas_A.drawText((new BigDecimal(flaot_A).setScale(6,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+110,floats_WH[1]+24,paintMOText);
                    canvas_A.drawText((new BigDecimal(flaot_A2).setScale(6,BigDecimal.ROUND_DOWN)).toString(),floats_WH[0]+210,floats_WH[1]+24,paintMOText);
//                    if(floats_Data[0][1]>=80)
//                    {
//                        int_UI=1;
//                    }else if(floats_Data[0][1]<=20) {
//                        int_UI=-1;
//                    }else {
//                        int_UI=0;
//                    }


                }

                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;

                    //Log.w(this.toString(), "IKNJHUdrawF0: "+"="+floats_Pos.length +"="+float_W );
                    //canvas_A.drawLine(floats_WH[0]+20,float_H,floats_WH[0]+float_W+20,float_H,paintOneColorBlue);
                    //Log.w(this.toString(), "IKNJHUdrawF4: "+"="+floats_Data.length );
                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    float flaot_JH=0;
                    float flaot_JH2=0;

                    float float_TP=float_W*((floats_Pos[0]-floats_Pos[1])*0.4F);
                    if(true)
                        {

                        }
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_GF=(0+float_KU)/(float_KU*2);
                            float_GF=(floats_WH[3]-(float_H*float_GF))-20;
                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            float float_GH=(floats_Data[i][1]+float_KU)/(float_KU*2);
                            float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            float float_GH2=(floats_Data[i][2]+float_KU)/(float_KU*2);
                            float_GH2=(floats_WH[3]-(float_H*float_GH2))-20;
                            float float_GH3=((floats_Data[i][1]-floats_Data[i][2])+float_KU)/(float_KU*2);
                            float_GH3=((floats_WH[3])-(float_H*float_GH3))-20;
                            Paint paint_G=null;
                            if(floats_Data[i][1]-floats_Data[i][2]>=0)
                            {
                                paint_G=paintOneLineGreen;
                            }else {
                                paint_G=paintOneLineRed;
                            }

                            canvas_A.drawRect(float_G-float_TP,float_GF,float_G+float_TP,float_GH3, paint_G);

                            if(bool_JB)
                            {

                                canvas_A.drawLine(flaot_JW,flaot_JH2,float_G,float_GH2, paintOneLineOreg);
                                canvas_A.drawLine(flaot_JW,flaot_JH,float_G,float_GH, paintOneLineBlue);

                            }else {
                                canvas_A.drawRect(float_G-2,float_GH2-2,float_G+2,float_GH2+2, paintOneLineOreg);
                                canvas_A.drawRect(float_G-2,float_GH-2,float_G+2,float_GH+2, paintOneLineBlue);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            flaot_JH=float_GH;
                            flaot_JH2=float_GH2;
                            //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
                           if(false)
                               {
                               if(Math.abs(floats_Data[i][1])>float_KU)
                                   {
                                       float_KU+=0.00003F;
                                       if(Math.abs(floats_Data[i][1])>float_KU)
                                           {
                                               float_KU=Math.abs(floats_Data[i][1]);
                                           }
                                   }
                                   if(Math.abs(floats_Data[i][2])>float_KU)
                                   {
                                       float_KU+=0.00003F;
                                       if(Math.abs(floats_Data[i][2])>float_KU)
                                       {
                                           float_KU=Math.abs(floats_Data[i][2]);
                                       }
                                   }
                               }

//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText((new BigDecimal(float_KU).setScale(6,BigDecimal.ROUND_DOWN).toString()),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText((new BigDecimal(-float_KU).setScale(6,BigDecimal.ROUND_DOWN).toString()),float_PText-100,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);




                    }

                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawStock extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {
                if(floats_Data[0]!=null)
                {
                    float flaot_A=(((int)(floats_Data[0][1]*100))/100F);
                    //Log.w(this.toString(), "OJIFdrawOneF0: "+"="+flaot_A +"="+ (String.valueOf(flaot_A)+"="+floats_Data[0][1]));
                    canvas_A.drawText(String.valueOf(flaot_A),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                    float flaot_A2=(((int)(floats_Data[0][2]*100))/100F);
                    canvas_A.drawText(String.valueOf(flaot_A2),floats_WH[0]+80,floats_WH[1]+24,paintMOText);
                    if(floats_Data[0][1]>=80)
                        {
                            int_UI=1;
                        }else if(floats_Data[0][1]<=20) {
                        int_UI=-1;
                    }else {
                        int_UI=0;
                    }
                    if(floats_Data[1]!=null) {
                        if (floats_Data[0][1] > 80) {
                            if (floats_Data[1][1] > floats_Data[1][2] && floats_Data[0][1] < floats_Data[0][2]) {
                                int_UI = -1;
                            }
                        } else if (floats_Data[0][1] < 20) {
                            if (floats_Data[1][1] < floats_Data[1][2] && floats_Data[0][1] > floats_Data[0][2]) {
                                int_UI = 1;
                            }
                        }
                    }
                }
                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        float_P=(floats_WH[3]-(float_H*1))-20;
                        canvas_A.drawText("120",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*(50F/120F)))-20;
                        canvas_A.drawText("50",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*(80F/120F)))-20;
                        canvas_A.drawText("80",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*(20F/120F)))-20;
                        canvas_A.drawText("20",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        canvas_A.drawRect(floats_WH[0]+20,(floats_WH[3]-(float_H*(20F/120F)))-20,floats_WH[2]-50,(floats_WH[3]-(float_H*(80F/120F)))-20,paintOneColorPao);


                    }
                    //Log.w(this.toString(), "IKNJHUdrawF0: "+"="+floats_Pos.length +"="+float_W );
                    //canvas_A.drawLine(floats_WH[0]+20,float_H,floats_WH[0]+float_W+20,float_H,paintOneColorBlue);
                    //Log.w(this.toString(), "IKNJHUdrawF4: "+"="+floats_Data.length );
                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    float flaot_JH=0;
                    float flaot_JH2=0;
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            float float_GH=(floats_Data[i][1])/120F;
                            float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            float float_GH2=(floats_Data[i][2])/120F;
                            float_GH2=(floats_WH[3]-(float_H*float_GH2))-20;
                            if(bool_JB)
                            {
                                canvas_A.drawLine(flaot_JW,flaot_JH2,float_G,float_GH2, paintOneLineOreg);
                                canvas_A.drawLine(flaot_JW,flaot_JH,float_G,float_GH, paintOneLineBlue);

                            }else {
                                canvas_A.drawRect(float_G-2,float_GH2-2,float_G+2,float_GH2+2, paintOneLineOreg);
                                canvas_A.drawRect(float_G-2,float_GH-2,float_G+2,float_GH+2, paintOneLineBlue);
                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            flaot_JH=float_GH;
                            flaot_JH2=float_GH2;
                            //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
                            if(float_G>floats_WH[0])
                            {


                            }
//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawRSI extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos, String string_Name) {
            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {
                if(floats_Data[0]!=null)
                {
                    float flaot_A=(((int)(floats_Data[0][1]*100))/100F);
                    //Log.w(this.toString(), "OJIFdrawOneF0: "+"="+flaot_A +"="+ (String.valueOf(flaot_A)+"="+floats_Data[0][1]));
                    canvas_A.drawText(String.valueOf(flaot_A),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                    if(floats_Data[0][1]>=0)
                    {
                        int_UI=1;
                    }else {
                        int_UI=-1;
                    }
                }
                if(true)
                {


                    float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                    float float_H=(floats_WH[3]-floats_WH[1])-40;
                    float float_UH=1;
                    float float_DH=-1;
                    if(true)
                    {
                        float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        float float_PText=floats_WH[2]-40;
                        float float_PU=floats_WH[2]-50-5;
                        float float_PD=floats_WH[2]-50+5;

                        float_P=(floats_WH[3]-(float_H*0.5F))-20;
                        canvas_A.drawText("50",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.7F))-20;
                        canvas_A.drawText("70",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0.3F))-20;
                        canvas_A.drawText("30",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*1F))-20;
                        canvas_A.drawText("100",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        float_P=(floats_WH[3]-(float_H*0F))-20;
                        canvas_A.drawText("0",float_PText,float_P,paintMOText);
                        canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                        canvas_A.drawRect(floats_WH[0]+20,(floats_WH[3]-(float_H*0.3F))-20,floats_WH[2]-50,((floats_WH[3]-(float_H*0.7F))-20),paintOneColorPao);


                    }
                    //Log.w(this.toString(), "IKNJHUdrawF0: "+"="+floats_Pos.length +"="+float_W );
                    //canvas_A.drawLine(floats_WH[0]+20,float_H,floats_WH[0]+float_W+20,float_H,paintOneColorBlue);
                    //Log.w(this.toString(), "IKNJHUdrawF4: "+"="+floats_Data.length );
                    Boolean bool_JB=false;
                    float flaot_JW=0;
                    float flaot_JH=0;
                    for(int i=0;i<floats_Data.length-1;i++)
                    {
                        if(floats_Data[i]!=null)
                        {
                            if(floats_Pos[i]<0)
                            {
                                bool_JB =false;
                                continue;
                            }

                            float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                            float float_GH=(floats_Data[i][1])/100F;
                            float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                            if(bool_JB)
                            {
                                canvas_A.drawLine(flaot_JW,flaot_JH,float_G,float_GH, paintOneLineBlack);

                            }else {
                                canvas_A.drawRect(float_G-5,float_GH-5,float_G+5,float_GH+5, paintOneLineBlack);

                            }
                            bool_JB=true;
                            flaot_JW=float_G;
                            flaot_JH=float_GH;

                            //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
                            if(float_G>floats_WH[0])
                            {


                            }
//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                        }else {
                            bool_JB=false;
                        }



                    }
                }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, String string_Name) {
            super.drawOne(canvas_A, floats_WH, floats_Data, string_Name);
        }
    }
    class LineDrawRecommend extends LineDraw{
        @Override
        public void draw(Canvas canvas_A, float[] floats_WH, float[][] floats_Data, float[] floats_Pos,String string_Name) {
            //super.draw(canvas_A, floats_WH, floats_Data, floats_Pos,string_Name);

            //floatLine=1F;
//            float float_B=(floats_WH[2]-floats_WH[0])-(float_A*4);
//            int int_A=(((int)floats_WH[0])%2);
//            //Log.w(this.toString(), "KJFdrawOneD1: "+"="+int_A +"="+((int)float_B) +"="+float_B );

//            canvas_A.drawRoundRect(floats_WH[0],floats_WH[1],floats_WH[0]+float_A*2+10,floats_WH[3],float_A,float_A,paintOneColorBlue);
//            canvas_A.drawRoundRect(floats_WH[2]-(float_A*2+10),floats_WH[1],floats_WH[2],floats_WH[3],float_A,float_A,paintOneColorRed);

            int int_UI=0;
            if(floats_Data!=null&&floats_Data.length>0)
            {
                if(floats_Data[0]!=null)
                {
                    float flaot_A=(((int)(floats_Data[0][1]*1000))/1000F);
                    //Log.w(this.toString(), "OJIFdrawOneF0: "+"="+flaot_A +"="+ (String.valueOf(flaot_A)+"="+floats_Data[0][1]));
                    canvas_A.drawText(String.valueOf(flaot_A),floats_WH[0]+10,floats_WH[1]+24,paintMOText);
                if(floats_Data[0][1]>=0)
                    {
                        int_UI=1;
                    }else {
                    int_UI=-1;
                }
                }
                if(true)
                    {


                        float float_W=(floats_WH[2]-floats_WH[0])-90;//-60;
                        float float_H=(floats_WH[3]-floats_WH[1])-40;
                        float float_UH=1;
                        float float_DH=-1;
                        if(true)
                            {
                                float float_P=(floats_WH[3]-(float_H*0.5F))-20;
                                float float_PText=floats_WH[2]-40;
                                float float_PU=floats_WH[2]-50-5;
                                float float_PD=floats_WH[2]-50+5;
                                float_P=(floats_WH[3]-(float_H*0.5F))-20;
                                canvas_A.drawText("0",float_PText,float_P,paintMOText);
                                canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                                float_P=(floats_WH[3]-(float_H*1F))-20;
                                canvas_A.drawText("1",float_PText,float_P,paintMOText);
                                canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);

                                float_P=(floats_WH[3]-(float_H*0F))-20;
                                canvas_A.drawText("-1",float_PText,float_P,paintMOText);
                                canvas_A.drawLine(float_PU,float_P,float_PD,float_P, paintOneColorBlack);


                            }
                        //Log.w(this.toString(), "IKNJHUdrawF0: "+"="+floats_Pos.length +"="+float_W );
                        //canvas_A.drawLine(floats_WH[0]+20,float_H,floats_WH[0]+float_W+20,float_H,paintOneColorBlue);
                        //Log.w(this.toString(), "IKNJHUdrawF4: "+"="+floats_Data.length );
                        Boolean bool_JB=false;
                        float flaot_JW=0;
                        float flaot_JH=0;
                        for(int i=0;i<floats_Data.length-1;i++)
                        {
                            if(floats_Data[i]!=null)
                                {
                                    if(floats_Pos[i]<0)
                                        {
                                         bool_JB =false;
                                        continue;
                                        }

                                    float float_G=floats_WH[0]+(float_W*floats_Pos[i])+20;
                                    float float_GH=(floats_Data[i][1]+1)/2F;
                                    float_GH=(floats_WH[3]-(float_H*float_GH))-20;
                                    if(bool_JB)
                                        {
                                            canvas_A.drawLine(flaot_JW,flaot_JH,float_G,float_GH, paintOneLineBlack);

                                        }else {
                                        canvas_A.drawRect(float_G-5,float_GH-5,float_G+5,float_GH+5, paintOneLineBlack);

                                    }
                                    bool_JB=true;
                                    flaot_JW=float_G;
                                    flaot_JH=float_GH;

                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
                                     if(float_G>floats_WH[0])
                                       {


                                       }
//                                    float_GH=(-1+1)/2F;
//                                    //Log.w(this.toString(), "IKNJHUdrawF3: "+"="+i +"="+float_G +"="+float_GH +"="+floats_Data[i][1]+"="+((floats_WH[3]+5)-(float_H*float_GH))+"="+(floats_WH[3]+5));
//                                    canvas_A.drawRect(float_G-5,(floats_WH[3]-25)-(float_H*float_GH),float_G+5,(floats_WH[3]-15)-(float_H*float_GH),paintOneColorBlue);
                                }else {
                                bool_JB=false;
                            }



                        }
                    }
            }
            super.drawViewRect(canvas_A,int_UI,floats_WH,string_Name);
//            if(int_UI==1)
//            {
//                canvas_A.drawRoundRect(floats_WH[0],floats_WH[1],floats_WH[2],floats_WH[3],float_A,float_A,paintOneColorBlue);
//            }else if(int_UI==-1) {
//                canvas_A.drawRoundRect(floats_WH[0],floats_WH[1],floats_WH[2],floats_WH[3],float_A,float_A,paintOneColorRed);
//            }else
//            {
//                canvas_A.drawRoundRect(floats_WH[0],floats_WH[1],floats_WH[2],floats_WH[3],float_A,float_A,paintOneColorBlack);
//            }

        }

        @Override
        public void drawOne(Canvas canvas_A, float[] floats_WH, float[][] floats_Data,String string_Name) {
            //Log.w(this.toString(), "KIIGdrawOneG1: "+"="+(floats_WH!=null) +"="+(floats_Data!=null ));
            ////Log.w(this.toString(), "KIIGdrawOneG0: "+"="+floats_WH.length +"="+floats_Data.length );
            super.drawOne(canvas_A, floats_WH, floats_Data,string_Name);
        }
        void drawKOK(Canvas canvas_A,float[] float_W,float[] float_H,float float_P ,String string_A){
//            float float_A=5F;
//            //floatLine=1F;
//            float float_B=(float_W[1]-float_W[0])-(float_A*4);
//            canvas_A.drawRoundRect(float_W[0],float_H[0],float_W[0]+float_A*2+10,float_H[1],float_A,float_A,paintColorBlue);
//            canvas_A.drawRoundRect(float_W[1]-(float_A*2+10),float_H[0],float_W[1],float_H[1],float_A,float_A,paintColorRed);
//            canvas_A.drawRect(float_W[0]+float_A*2,float_H[0],float_W[0]+(float_A*2)+(float_B*float_P),float_H[1],paintColorBlue);
//            canvas_A.drawRect(float_W[0]+(float_A*2)+float_B*float_P,float_H[0],float_W[1]-(float_A*2),float_H[1],paintColorRed);
//            canvas_A.drawText(stringLineName,float_W[0]+((float_W[1]-float_W[0])/2),float_H[0]+((float_H[1]-float_H[0])*0.90F),paintTextName);
//            canvas_A.drawText(string_A,float_W[0]+((float_W[1]-float_W[0])/2),float_H[0]+((float_H[1]-float_H[0])*0.46F),paintText);
        }
    }
    // "Recommend.All","Recommend.Other","Recommend.MA","RSI","Stoch.K","Stoch.D","Stoch.RSI.K","Stoch.RSI.D","MACD.macd","MACD.signal",
    // "ADX","ADX+DI","ADX-DI","W.R","CCI20","ROC","BBPower","Rec.BBPower","AO","Mom"
    //"ATR","UO"
    //"EMA5|1""SMA5|1","EMA10|1","SMA10|1","EMA20|1","SMA20|1","EMA30|1","SMA30|1","EMA50|1","SMA50|1","EMA100|1","SMA100|1","EMA200|1","SMA200|1",
    // "Rec.Ichimoku|1","Ichimoku.BLine|1","Rec.VWMA|1","VWMA|1","Rec.HullMA9|1","HullMA9|1","Pivot.M.Classic.S3|1","Pivot.M.Classic.S2|1","Pivot.M.Classic.S1|1","Pivot.M.Classic.Middle|1","Pivot.M.Classic.R1|1","Pivot.M.Classic.R2|1","Pivot.M.Classic.R3|1","Pivot.M.Fibonacci.S3|1","Pivot.M.Fibonacci.S2|1","Pivot.M.Fibonacci.S1|1","Pivot.M.Fibonacci.Middle|1","Pivot.M.Fibonacci.R1|1","Pivot.M.Fibonacci.R2|1","Pivot.M.Fibonacci.R3|1","Pivot.M.Camarilla.S3|1","Pivot.M.Camarilla.S2|1","Pivot.M.Camarilla.S1|1","Pivot.M.Camarilla.Middle|1","Pivot.M.Camarilla.R1|1","Pivot.M.Camarilla.R2|1","Pivot.M.Camarilla.R3|1","Pivot.M.Woodie.S3|1","Pivot.M.Woodie.S2|1","Pivot.M.Woodie.S1|1","Pivot.M.Woodie.Middle|1","Pivot.M.Woodie.R1|1","Pivot.M.Woodie.R2|1","Pivot.M.Woodie.R3|1","Pivot.M.Demark.S1|1","Pivot.M.Demark.Middle|1","Pivot.M.Demark.R1|1"

}
