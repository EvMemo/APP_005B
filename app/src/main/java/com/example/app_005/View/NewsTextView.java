package com.example.app_005.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.example.app_005.Class.Nak;
import com.example.app_005.Class.NewsClassDW;
import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Class.SkillNewsName;
import com.example.app_005.Tool.InterTool;
import com.example.app_005.Tool.TextStringTool;
import com.example.app_005.Tool.TimeOD;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsTextView extends View {
    int width,height;
    NewsDataClass newsDataClass;
   // NewsClassDW newsClassDW;
    NewsClassDraw newsClassDraw;
    boolean boolDataOK;

    Paint paintUI;
    Paint paintP;
    Paint paintTitle;
    Paint paintTime;
    Paint paintText;
    Paint paintImgText;
    public NewsTextView(Context context) {
        super(context);
    }

    public NewsTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //newsClassDW=new NewsClassDW();

        paintUI=new Paint();
        paintUI.setAntiAlias(true);//消除锯齿
        paintUI.setColor(Color.parseColor("#FFDAD7D7"));//设置画笔颜色//#83DAD7D7
        paintUI.setStyle(Paint.Style.FILL);
        paintUI.setStrokeWidth(10);//设置宽度

        paintP=new Paint();
        paintP.setColor(Color.WHITE);//设置画笔颜色//#83DAD7D7
        paintP.setStyle(Paint.Style.FILL);
        paintP.setStrokeWidth(10);//设置宽度

//        paintTitle = new Paint();
//        paintTitle.setAntiAlias(true);//消除锯齿
//        paintTitle.setColor(Color.BLACK);
//        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
//        paintTitle.setTextAlign(Paint.Align.LEFT);
//        paintTitle.setTextSize(37);

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

        mScroller = new Scroller(getContext(), null, true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mMaxFlintVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMinFlintVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMinFlintVelocity = 600;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(width, height);//设置宽和高
        ArrayList<Nak> naks_A=new ArrayList<>();
        naks_A.add(new Nak(0,"11月下旬，来自民意测验机构YouGov的模型准确预测了2017年大选，他说英国首相鲍里斯·约翰逊（Boris Johnson）有望在12月12日的大选中赢得议会68个席位。"));
        naks_A.add(new Nak(7,"2019-12-14"));
        naks_A.add(new Nak(4,"https://www.fortrade.com/analysis/images/900/auto/microAnalysis/18102019/crude-oil-01.png"));
        naks_A.add(new Nak(4,"https://www.fortrade.com/analysis/images/1000/auto/weeklyAnalysis/11122019/ek257.png"));
        naks_A.add(new Nak(1,"11月下旬，来自民意测验机构YouGov的模型准确预测了2017年大选，他说英国首相鲍里斯·约翰逊（Boris Johnson）有望在12月12日的大选中赢得议会68个席位。根据YouGov模型，即多层次回归和后分层-或称``MRP''，他的保守党可能赢得650个席位中的359个席位，高\u200B\u200B于2017年大选的317个席位，这是该党自玛格丽特·撒切尔（Margaret Thatcher）1987年获胜以来的最好成绩。简而言之。根据该模型，工党有望获得211个席位，低于262个席位。SNP在第43位，自由民主党在第13位，英国退欧党无席位。同时，唐纳德·特朗普（Donald Trump）坚持要求英国在脱欧后的贸易谈判中与国民保健服务（NHS）无关，因为他试图推翻反对派关于医疗服务将要出售的说法。在12月初访问英国时，美国总统声称他对增加美国公司向NHS的市场准入没有兴趣，即使交了白银。这可能为保守党提供进一步的支持，保守党此前曾被认为可以在脱欧后的贸易谈判中向美国出售国民保健服务。市场认为，保守党的胜利可能会导致英国议会不久后通过英国脱欧协议，这将为英镑提供进一步支持。"));
        naks_A.add(new Nak(1,"11月下旬，来自民意测验机构YouGov的模型准确预测了2017年大选，他说英国首相鲍里斯·约翰逊（Boris Johnson）有望在12月12日的大选中赢得议会68个席位。根据YouGov模型，即多层次回归和后分层-或称``MRP''，他的保守党可能赢得650个席位中的359个席位，高\u200B\u200B于2017年大选的317个席位，这是该党自玛格丽特·撒切尔（Margaret Thatcher）1987年获胜以来的最好成绩。简而言之。根据该模型，工党有望获得211个席位，低于262个席位。SNP在第43位，自由民主党在第13位，英国退欧党无席位。同时，唐纳德·特朗普（Donald Trump）坚持要求英国在脱欧后的贸易谈判中与国民保健服务（NHS）无关，因为他试图推翻反对派关于医疗服务将要出售的说法。在12月初访问英国时，美国总统声称他对增加美国公司向NHS的市场准入没有兴趣，即使交了白银。这可能为保守党提供进一步的支持，保守党此前曾被认为可以在脱欧后的贸易谈判中向美国出售国民保健服务。市场认为，保守党的胜利可能会导致英国议会不久后通过英国脱欧协议，这将为英镑提供进一步支持。"));
        naks_A.add(new Nak(1,"11月下旬，来自民意测验机构YouGov的模型准确预测了2017年大选，他说英国首相鲍里斯·约翰逊（Boris Johnson）有望在12月12日的大选中赢得议会68个席位。根据YouGov模型，即多层次回归和后分层-或称``MRP''，他的保守党可能赢得650个席位中的359个席位，高\u200B\u200B于2017年大选的317个席位，这是该党自玛格丽特·撒切尔（Margaret Thatcher）1987年获胜以来的最好成绩。简而言之。根据该模型，工党有望获得211个席位，低于262个席位。SNP在第43位，自由民主党在第13位，英国退欧党无席位。同时，唐纳德·特朗普（Donald Trump）坚持要求英国在脱欧后的贸易谈判中与国民保健服务（NHS）无关，因为他试图推翻反对派关于医疗服务将要出售的说法。在12月初访问英国时，美国总统声称他对增加美国公司向NHS的市场准入没有兴趣，即使交了白银。这可能为保守党提供进一步的支持，保守党此前曾被认为可以在脱欧后的贸易谈判中向美国出售国民保健服务。市场认为，保守党的胜利可能会导致英国议会不久后通过英国脱欧协议，这将为英镑提供进一步支持。"));
        naks_A.add(new Nak(1,"11月下旬，来自民意测验机构YouGov的模型准确预测了2017年大选，他说英国首相鲍里斯·约翰逊（Boris Johnson）有望在12月12日的大选中赢得议会68个席位。根据YouGov模型，即多层次回归和后分层-或称``MRP''，他的保守党可能赢得650个席位中的359个席位，高\u200B\u200B于2017年大选的317个席位，这是该党自玛格丽特·撒切尔（Margaret Thatcher）1987年获胜以来的最好成绩。简而言之。根据该模型，工党有望获得211个席位，低于262个席位。SNP在第43位，自由民主党在第13位，英国退欧党无席位。同时，唐纳德·特朗普（Donald Trump）坚持要求英国在脱欧后的贸易谈判中与国民保健服务（NHS）无关，因为他试图推翻反对派关于医疗服务将要出售的说法。在12月初访问英国时，美国总统声称他对增加美国公司向NHS的市场准入没有兴趣，即使交了白银。这可能为保守党提供进一步的支持，保守党此前曾被认为可以在脱欧后的贸易谈判中向美国出售国民保健服务。市场认为，保守党的胜利可能会导致英国议会不久后通过英国脱欧协议，这将为英镑提供进一步支持。"));
        naks_A.add(new Nak(1,"11月下旬，来自民意测验机构YouGov的模型准确预测了2017年大选，他说英国首相鲍里斯·约翰逊（Boris Johnson）有望在12月12日的大选中赢得议会68个席位。根据YouGov模型，即多层次回归和后分层-或称``MRP''，他的保守党可能赢得650个席位中的359个席位，高\u200B\u200B于2017年大选的317个席位，这是该党自玛格丽特·撒切尔（Margaret Thatcher）1987年获胜以来的最好成绩。简而言之。根据该模型，工党有望获得211个席位，低于262个席位。SNP在第43位，自由民主党在第13位，英国退欧党无席位。同时，唐纳德·特朗普（Donald Trump）坚持要求英国在脱欧后的贸易谈判中与国民保健服务（NHS）无关，因为他试图推翻反对派关于医疗服务将要出售的说法。在12月初访问英国时，美国总统声称他对增加美国公司向NHS的市场准入没有兴趣，即使交了白银。这可能为保守党提供进一步的支持，保守党此前曾被认为可以在脱欧后的贸易谈判中向美国出售国民保健服务。市场认为，保守党的胜利可能会导致英国议会不久后通过英国脱欧协议，这将为英镑提供进一步支持。"));
        naks_A.add(new Nak(1,"11月下旬，来自民意测验机构YouGov的模型准确预测了2017年大选，他说英国首相鲍里斯·约翰逊（Boris Johnson）有望在12月12日的大选中赢得议会68个席位。根据YouGov模型，即多层次回归和后分层-或称``MRP''，他的保守党可能赢得650个席位中的359个席位，高\u200B\u200B于2017年大选的317个席位，这是该党自玛格丽特·撒切尔（Margaret Thatcher）1987年获胜以来的最好成绩。简而言之。根据该模型，工党有望获得211个席位，低于262个席位。SNP在第43位，自由民主党在第13位，英国退欧党无席位。同时，唐纳德·特朗普（Donald Trump）坚持要求英国在脱欧后的贸易谈判中与国民保健服务（NHS）无关，因为他试图推翻反对派关于医疗服务将要出售的说法。在12月初访问英国时，美国总统声称他对增加美国公司向NHS的市场准入没有兴趣，即使交了白银。这可能为保守党提供进一步的支持，保守党此前曾被认为可以在脱欧后的贸易谈判中向美国出售国民保健服务。市场认为，保守党的胜利可能会导致英国议会不久后通过英国脱欧协议，这将为英镑提供进一步支持。"));
        naks_A.add(new Nak(1,"11月下旬，来自民意测验机构YouGov的模型准确预测了2017年大选，他说英国首相鲍里斯·约翰逊（Boris Johnson）有望在12月12日的大选中赢得议会68个席位。根据YouGov模型，即多层次回归和后分层-或称``MRP''，他的保守党可能赢得650个席位中的359个席位，高\u200B\u200B于2017年大选的317个席位，这是该党自玛格丽特·撒切尔（Margaret Thatcher）1987年获胜以来的最好成绩。简而言之。根据该模型，工党有望获得211个席位，低于262个席位。SNP在第43位，自由民主党在第13位，英国退欧党无席位。同时，唐纳德·特朗普（Donald Trump）坚持要求英国在脱欧后的贸易谈判中与国民保健服务（NHS）无关，因为他试图推翻反对派关于医疗服务将要出售的说法。在12月初访问英国时，美国总统声称他对增加美国公司向NHS的市场准入没有兴趣，即使交了白银。这可能为保守党提供进一步的支持，保守党此前曾被认为可以在脱欧后的贸易谈判中向美国出售国民保健服务。市场认为，保守党的胜利可能会导致英国议会不久后通过英国脱欧协议，这将为英镑提供进一步支持。"));
        naks_A.add(new Nak(1,"11月下旬，来自民意测验机构YouGov的模型准确预测了2017年大选，他说英国首相鲍里斯·约翰逊（Boris Johnson）有望在12月12日的大选中赢得议会68个席位。根据YouGov模型，即多层次回归和后分层-或称``MRP''，他的保守党可能赢得650个席位中的359个席位，高\u200B\u200B于2017年大选的317个席位，这是该党自玛格丽特·撒切尔（Margaret Thatcher）1987年获胜以来的最好成绩。简而言之。根据该模型，工党有望获得211个席位，低于262个席位。SNP在第43位，自由民主党在第13位，英国退欧党无席位。同时，唐纳德·特朗普（Donald Trump）坚持要求英国在脱欧后的贸易谈判中与国民保健服务（NHS）无关，因为他试图推翻反对派关于医疗服务将要出售的说法。在12月初访问英国时，美国总统声称他对增加美国公司向NHS的市场准入没有兴趣，即使交了白银。这可能为保守党提供进一步的支持，保守党此前曾被认为可以在脱欧后的贸易谈判中向美国出售国民保健服务。市场认为，保守党的胜利可能会导致英国议会不久后通过英国脱欧协议，这将为英镑提供进一步支持。"));
        naks_A.add(new Nak(1,"11月下旬，来自民意测验机构YouGov的模型准确预测了2017年大选，他说英国首相鲍里斯·约翰逊（Boris Johnson）有望在12月12日的大选中赢得议会68个席位。根据YouGov模型，即多层次回归和后分层-或称``MRP''，他的保守党可能赢得650个席位中的359个席位，高\u200B\u200B于2017年大选的317个席位，这是该党自玛格丽特·撒切尔（Margaret Thatcher）1987年获胜以来的最好成绩。简而言之。根据该模型，工党有望获得211个席位，低于262个席位。SNP在第43位，自由民主党在第13位，英国退欧党无席位。同时，唐纳德·特朗普（Donald Trump）坚持要求英国在脱欧后的贸易谈判中与国民保健服务（NHS）无关，因为他试图推翻反对派关于医疗服务将要出售的说法。在12月初访问英国时，美国总统声称他对增加美国公司向NHS的市场准入没有兴趣，即使交了白银。这可能为保守党提供进一步的支持，保守党此前曾被认为可以在脱欧后的贸易谈判中向美国出售国民保健服务。市场认为，保守党的胜利可能会导致英国议会不久后通过英国脱欧协议，这将为英镑提供进一步支持。"));
        naks_A.add(new Nak(1,"来自民意测验机构YouGov的模型准确预测了2017年大选，他说英国首相鲍里斯·约翰逊（Boris Johnson）有望在12月12日的大选中赢得议会68个席位。根据YouGov模型，即多层次回归和后分层-或称``MRP''，他的保守党可能赢得650个席位中的359个席位，高\u200B\u200B于2017年大选的317个席位，这是该党自玛格丽特·撒切尔（Margaret Thatcher）1987年获胜以来的最好成绩。简而言之。根据该模型，工党有望获得211个席位，低于262个席位。SNP在第43位，自由民主党在第13位，英国退欧党无席位。同时，唐纳德·特朗普（Donald Trump）坚持要求英国在脱欧后的贸易谈判中与国民保健服务（NHS）无关，因为他试图推翻反对派关于医疗服务将要出售的说法。在12月初访问英国时，美国总统声称他对增加美国公司向NHS的市场准入没有兴趣，即使交了白银。这可能为保守党提供进一步的支持，保守党此前曾被认为可以在脱欧后的贸易谈判中向美国出售国民保健服务。市场认为，保守党的胜利可能会导致英国议会不久后通过英国脱欧协议，这将为英镑提供进一步支持。"));
        NewsDataClass newsDataClass_A=new NewsDataClass(naks_A.get(0).stringText,naks_A.get(1).stringText,naks_A,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis()));
        //setNewsDataClass(newsDataClass_A);
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
                newsClassDraw.StartDraw(canvas);
            }
    }
    public void setNewsDataClass(NewsDataClass newsDataClass_A){
        if(newsDataClass_A!=null)
            {
                newsDataClass=newsDataClass_A;
                newsClassDraw=new NewsClassDraw(newsDataClass,width,height);
                boolDataOK=true;
                invalidate();
            }

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
        NewsDraw[] newsDraws;

        public NewsClassDraw(NewsDataClass newsDataClass_A,int width_A,int height_A){
            newsDataClass=newsDataClass_A;
            width=width_A;
            height=height_A;
            initData();

        }
        void initData(){
            newsDraws=new NewsDraw[newsDataClass.naks.size()];
            intFloatArrayNum=0;
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
        void ccNewsDrawViewH(){
            float float_H=0;
            intFloatArrayLast=-1;
            for(int i=newsDraws.length-1;i>=0;i--)
            {

                float_H+=newsDraws[i].viewH;
                if(float_H>height-133)
                    {
                    intFloatArrayLast=i-1;
                    floatNewsLastH=(height-133)-float_H;
                        //]Log.w(this.toString(), "ccNewsDrawViewH: "+"="+i +"="+float_H +"="+(height-133) );
                    break;
                    }


            }
        }
        public void StartDraw(Canvas canvas){
            if(true)
                {
                    drawUI(canvas);
                    canvas.save();
                    canvas.clipRect(50,30,width-50,height-100);
                    //]Log.w(this.toString(), "KJUStartDrawS0: "+"="+newsDraws.length );
                    float float_H=0;
                    int int_A=intFloatArrayNum;
                    //float_H-=intViewH;
                for(int i=int_A;i<newsDraws.length;i++)
                    {
                        newsDraws[i].draw(canvas,0,float_H-intViewH);

                        if((float_H-intViewH)>(height-133))
                            {
                                //]Log.w(this.toString()   , "KJUStartDrawS1: "+"="+ i +"="+float_H +"="+ (height-133)+"="+intFloatArrayLast   );
                            break;
                            }
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
        class NewsDraw{
            public float viewH;
            public int intMode;
            public NewsDraw(String string_Data){

            }
            public void draw(Canvas canvas,float width_A,float height_A){

            }
        }
        class NewsDrawTitle extends NewsDraw{
            String[] stringsTitle;
            public NewsDrawTitle(String string_Data) {
                super(string_Data);
                stringsTitle=TextStringTool.getTextAdvancesC(string_Data,paintTitle,width-180);
                viewH=30+(40*stringsTitle.length);
            }
            @Override
            public void draw(Canvas canvas,float width_A,float height_A){
                for(int i=0;i<stringsTitle.length;i++)
                    {
                        canvas.drawText(stringsTitle[i],70,70+height_A+(i*40),paintTitle);
                    }


            }

        }
        class NewsDrawText extends NewsDraw{
            String[] stringsText;
            public NewsDrawText(String string_Data) {
                super(string_Data);
                stringsText=TextStringTool.getTextAdvancesC(string_Data,paintText,width-180);
                viewH=(37*stringsText.length)+30;
            }
            @Override
            public void draw(Canvas canvas,float width_A,float height_A){
                for(int i=0;i<stringsText.length;i++)
                {
                    canvas.drawText(stringsText[i],70,37+height_A+(i*37),paintText);
                }

            }
        }
        class NewsDrawTextTit extends NewsDraw{
            String[] stringsTextTit;
            public NewsDrawTextTit(String string_Data) {
                super(string_Data);
                stringsTextTit=TextStringTool.getTextAdvancesC(string_Data,paintTitle,width-180);
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
                canvas.drawText(stringTime,70,height_A+40,paintTime);

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
                    }else {
                    viewH=30;
                    getHttpImg();
                }
            }
            @Override
            public void draw(Canvas canvas, float width_A, float height_A){
                if(boolBitmapOK)
                    {
                        canvas.drawBitmap(bitmap,70,height_A,paintTitle);
                    }else {
                    canvas.drawRect(70,height_A+5,70+20,height_A+25,paintTitle);

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
                        float float_A=((float)bitmap.getWidth())/(width-180);
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
                stringImgText=TextStringTool.getTextAdvancesC(string_Data,paintImgText,width-180);
                viewH=(40*stringImgText.length)+30;
            }
            @Override
            public void draw(Canvas canvas,float width_A,float height_A){
                for(int i=0;i<stringImgText.length;i++)
                {
                    canvas.drawText(stringImgText[i],570,40+height_A+(i*40),paintImgText);
                }

            }
        }

    }

    int intFloatArrayNum,intFloatArrayLast;
    float floatNewsLastH;
    public void moveView(float float_Move) {
        if(boolDataOK&&intFloatArrayLast!=-1)
        {

            float float_A = intViewH - float_Move;
            float float_B = newsClassDraw.newsDraws[intFloatArrayNum].viewH;
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
                        float_B = newsClassDraw.newsDraws[intFloatArrayNum].viewH;
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
                    float_B = newsClassDraw.newsDraws[intFloatArrayNum].viewH;
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
            ////]Log.w(this.toString(), " DKODmoveViewS0: "+"="+((newsNewData.get(int_F)).floatViewH-(intViewH - float_Move)) +"="+float_A +"="+(intViewH - float_Move));

            intViewH = float_A;
            invalidate();
        }

    }
    public float repsMoveView(float float_Move) {
        float float_A=float_Move;
        float float_B=newsClassDraw.newsDraws[intFloatArrayNum].viewH;
        if(float_A<0)
        {
            if(intFloatArrayNum!=0)
            {
                intFloatArrayNum--;
                float_B=newsClassDraw.newsDraws[intFloatArrayNum].viewH;
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

}
