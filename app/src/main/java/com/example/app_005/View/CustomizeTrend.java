package com.example.app_005.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.example.app_005.Interface.OnGetQuoData;
import com.example.app_005.Interface.OnUpdateTime;
import com.example.app_005.MainApplication;
import com.example.app_005.Tool.FXDataConnect;
import com.example.app_005.Tool.TimeOD;

import java.util.Calendar;

/**
 * 走势图表控件模式列表布局
 */
public class CustomizeTrend extends View
{
    private static final String TAG ="CustomizeTrend";
    private int width;//设置高
    private int height;//设置高
    private int widthLocation;//设置高
    private int heightLocation;//设置高

    //ODTimeData odTimeData;

//    /**
//     * 每个长度范围EachLength
//     */
//    float floatEachLength,
//    /**
//     * 长度范围
//     */
//    floatViewLength,
//    /**
//    * 移动位置
//    */
//    floatPositionNow,
//    /**
//     * 位置差距
//     */
//    floatPositionGap;
//
//    /**
//     *时间点
//     */
//    int[] od_001Data_intsA;



    private Paint mPaintLine;//定义一个绘制直线的画笔
    private Paint mPaintSecondLine;//定义一个绘制直线的画笔
    private Paint mPaintMarkerLine;//定义一个绘制直线的画笔
    private Paint mPaintInterCircle;//定义一个绘制圆的画笔
    private Paint mPaintOutSideCircle;//定义一个绘制圆的画笔
    private Paint mPaintText;//定义一个绘制文字的画笔
    private Paint mPaintTextB;//定义一个绘制文字的画笔

    private Calendar mCalendar;//创建一个时间类
    private static final int NEED_INVALIDATE=0X6666;

    private boolean aBoolean;
    //private ArrayList<Cla_001> cla_001ListA;
    private float[][] floatsB;//最大值最小值
    public QuosSystemCenter.QuoOD quoOD;//数据线绘制数据
    //public ODTimeData odTimeData;
    public OnGetQuoData onGetQuoData;
    //public int intStart;
    public boolean boolStartA,boolStartB;





    private class Cla_001{
        public Cla_001(){
            intA=0;
            floatsA=new float[1][2];
        }
        public Cla_001(int intAA,float[][] floatAA){
            intA=intAA;
            floatsA=floatAA;
        }
        public int intA;
        public float[][] floatsA;
    }

    private float[] floatsA;//=new float[2];
    private Scroller mScroller;
    private VelocityTracker velocityTracker;
    private int mMaxFlintVelocity, mMinFlintVelocity;
    private int mChildMeasuredWidth,mChildMeasuredHeight;
    private float lastX, lastY;
    Paint paintTextMaxMin,paintTextUIPo;
    Paint paintQuoColorU,paintQuoColorD,paintQuoColorB;
    Paint paintQuoColorUN,paintQuoColorDN,paintQuoColorTN,paintTextUIPoN,paintTextUIPoT,paintTextUIPoTI;
    Paint paintNnUI;
    String stringA="";
    int intA,intB;
    public boolean boolStart;
    public float intNum=10,floatUIJd,floatUIJd2;//时间点数量，走势柱大小，走势柱线大小
    public int intNumUIKk,intNumUITk,intNumUITkH;//直虚线限制数目,时间分钟限制数目,时间小时限制数目

   // public float floatUIPoN,floatUIPoT,floatUIPoM;//图表缩放
    int intNumSt;
    public CusTreDrawTool cusTreDrawTool;

    public CustomizeTrend(Context context) {

        super(context);
        //]Log.w(this.toString(), "0");
    }

    public CustomizeTrend(Context context,AttributeSet attrs) {
        super(context, attrs);
        //]Log.w(this.toString(), "1");
        init();

    }

    public CustomizeTrend(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //]Log.w(this.toString(), "2");
    }
    void init(){
        boolStart=false;
        cusTreDrawTool=new CusTreDrawTool();
        init_Paint();



//        if(true)
//        {
//            cla_001ListA=new ArrayList<Cla_001>();
//            Random r = new Random();
//            float float_A=((r.nextFloat()*2)-1)*10F;
//            float[][] floats_A=new float[10][];
//
//            for(int i=0;i<floats_A.length;i++)
//            {
//                // float[][] floats0={{i}};
//
//                float float_B3=((r.nextFloat()*2)-1);
//                float float_B3a=((r.nextFloat()));
//                float float_B3b=float_B3a;
//                float float_B3c=float_B3a;
//                float float_B2=(float_B3*10F);
//                float float_B= (float_B2)+float_A;
//                //]Log.w(this.toString(),"OK6"+float_B2+"="+(float_B3)+"="+float_B);
//                float_B=(float_B<1)?1:float_B;
//                if(float_A-float_B>=0){
//                    float_B3b+=float_A;
//                    float_B3c-=float_B;
//                }else {
//                    float_B3b+=float_B;
//                    float_B3c-=float_A;
//                }
//                floats_A[i]=new float[]{i,float_A,float_B,float_B3b,float_B3c};
//                float_A=float_B;
//                ////]Log.w(this.toString(), "cla_001ListA="+cla_001ListA.get(i).floatsA[0][1]);
//            }
//            cla_001ListA.add(new Cla_001(0,floats_A));
//        }



    }
    /**
     * 初始化画笔
     */
    void init_Paint(){
        //初始化画直线的画笔
        aBoolean=true;

        paintTextUIPoN=new Paint();
        paintTextUIPoN.setAntiAlias(true);//消除锯齿
        paintTextUIPoN.setColor(Color.WHITE);//设置画笔颜色
        paintTextUIPoN.setTextAlign(Paint.Align.RIGHT);
        paintTextUIPoN.setTextSize(20);


        paintQuoColorUN=new Paint();
        paintQuoColorUN.setAntiAlias(true);//消除锯齿
        paintQuoColorUN.setColor(Color.RED);//设置画笔颜色
        paintQuoColorUN.setStyle(Paint.Style.FILL);//设置为空心
        paintQuoColorUN.setStrokeWidth(3);//设置宽度

        paintQuoColorDN=new Paint();
        paintQuoColorDN.setAntiAlias(true);//消除锯齿
        paintQuoColorDN.setColor(Color.BLUE);//设置画笔颜色
        paintQuoColorDN.setStyle(Paint.Style.FILL);//设置为空心
        paintQuoColorDN.setStrokeWidth(3);//设置宽度

        paintQuoColorTN=new Paint();
        paintQuoColorTN.setAntiAlias(true);//消除锯齿
        paintQuoColorTN.setColor(Color.BLACK);//设置画笔颜色
        paintQuoColorTN.setStyle(Paint.Style.FILL);//设置为空心
        paintQuoColorTN.setStrokeWidth(3);//设置宽度

        paintNnUI=new Paint();
        //paintNnUI.setAntiAlias(true);//消除锯齿
        paintNnUI.setColor(Color.GRAY);//设置画笔颜色
        //paintNnUI.setAlpha(220);
        paintNnUI.setStyle(Paint.Style.STROKE);
        paintNnUI.setStrokeWidth(1);//设置宽度
        PathEffect pathEffect_A=new DashPathEffect(new float[]{10,10},0);
        paintNnUI.setPathEffect(pathEffect_A);

        paintQuoColorB=new Paint();
        paintQuoColorB.setAntiAlias(true);//消除锯齿
        paintQuoColorB.setColor(Color.WHITE);//设置画笔颜色
        paintQuoColorB.setStyle(Paint.Style.FILL);//设置为空心
        //paintQuoColorB.setStrokeWidth(3);//设置宽度

        paintQuoColorU=new Paint();
        paintQuoColorU.setAntiAlias(true);//消除锯齿
        paintQuoColorU.setColor(Color.BLUE);//设置画笔颜色
        paintQuoColorU.setStyle(Paint.Style.STROKE);//设置为空心
        paintQuoColorU.setStrokeWidth(3);//设置宽度

        paintQuoColorD=new Paint();
        paintQuoColorD.setAntiAlias(true);//消除锯齿
        paintQuoColorD.setColor(Color.BLACK);//设置画笔颜色
        paintQuoColorD.setStyle(Paint.Style.FILL);//设置为空心
        paintQuoColorD.setStrokeWidth(3);//设置宽度

        paintTextMaxMin=new Paint();
        paintTextMaxMin.setAntiAlias(true);//消除锯齿
        paintTextMaxMin.setColor(Color.BLACK);//设置画笔颜色
        paintTextMaxMin.setTextAlign(Paint.Align.LEFT);
        paintTextMaxMin.setTextSize(30);

        paintTextUIPo=new Paint();
        paintTextUIPo.setAntiAlias(true);//消除锯齿
        paintTextUIPo.setColor(Color.BLACK);//设置画笔颜色
        paintTextUIPo.setTextAlign(Paint.Align.RIGHT);
        paintTextUIPo.setTextSize(20);

        paintTextUIPoT=new Paint();
        paintTextUIPoT.setAntiAlias(true);//消除锯齿
        paintTextUIPoT.setColor(Color.WHITE);//设置画笔颜色
        paintTextUIPoT.setTextAlign(Paint.Align.RIGHT);
        paintTextUIPoT.setTextSize(20);

        paintTextUIPoTI=new Paint();
        paintTextUIPoTI.setAntiAlias(true);//消除锯齿
        paintTextUIPoTI.setColor(Color.WHITE);//设置画笔颜色
        paintTextUIPoTI.setTextAlign(Paint.Align.CENTER);
        paintTextUIPoTI.setTextSize(30);
        //paintTextMaxMin.setMaskFilter(new BlurMaskFilter(20f, BlurMaskFilter.Blur.SOLID));


        mPaintLine = new Paint();
        mPaintLine.setAntiAlias(true);//消除锯齿
        mPaintLine.setColor(Color.GRAY);//设置画笔颜色
        mPaintLine.setStyle(Paint.Style.STROKE);//设置为空心
        mPaintLine.setStrokeWidth(10);//设置宽度
        // 初始化秒针的画笔
        mPaintSecondLine = new Paint();
        mPaintSecondLine.setAntiAlias(true);//消除锯齿
        mPaintSecondLine.setColor(Color.BLACK);//设置画笔颜色
        mPaintSecondLine.setStyle(Paint.Style.STROKE);//设置为空心
        mPaintSecondLine.setStrokeWidth(3);//设置宽度

        mPaintMarkerLine= new Paint();
        mPaintMarkerLine.setAntiAlias(true);//消除锯齿
        mPaintMarkerLine.setColor(Color.parseColor("#FF66CDC2"));//设置画笔颜色
        mPaintMarkerLine.setStyle(Paint.Style.STROKE);//设置为空心
        mPaintMarkerLine.setStrokeWidth(3);//设置宽度

        //初始化内圆的画笔
        mPaintInterCircle = new Paint();
        mPaintInterCircle.setAntiAlias(true);//消除锯齿
        mPaintInterCircle.setColor(Color.BLACK);
        mPaintInterCircle.setStyle(Paint.Style.STROKE);//设置为空心
        mPaintInterCircle.setStrokeWidth(5);
        //初始化外圆的画笔
        mPaintOutSideCircle = new Paint();
        mPaintOutSideCircle.setAntiAlias(true);//消除锯齿
        mPaintOutSideCircle.setColor(Color.BLACK);
        mPaintOutSideCircle.setStyle(Paint.Style.STROKE);//设置为空心
        mPaintOutSideCircle.setStrokeWidth(10);

        //绘制文字的画笔
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);//消除锯齿
        mPaintText.setColor(Color.BLACK);
        //mPaintText.setStyle(Paint.Style.STROKE);//设置为空心
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setTextSize(30);
        //mPaintText.setStrokeWidth(6);
        //绘制文字的画笔
        mPaintTextB = new Paint();
        mPaintTextB.setAntiAlias(true);//消除锯齿
        mPaintTextB.setColor(Color.GRAY);
        //mPaintTextB.setStyle(Paint.Style.STROKE);//设置为空心
        mPaintTextB.setTextAlign(Paint.Align.CENTER);
        mPaintTextB.setTextSize(50);
        //mPaintTextB.setStrokeWidth(6);
    }
    void init_ViewData(){
        //**初始化滑动
        floatsA=new float[]{0,0};
        mScroller = new Scroller(getContext(), null, true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mMaxFlintVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMinFlintVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMinFlintVelocity = 600;
        //**初始化走势长度

        //floatEachLength =(width-80)/intNum;
        ////]Log.w(TAG, "init_ViewData01: "+"="+floatEachLength);
        //floatViewLength =width-80;
        //floatPositionNow =0;
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //]Log.w(this.toString(), "KOKO+("+widthMeasureSpec+")+("+heightMeasureSpec+")");
        //]Log.w(TAG, "onMeasureF0: +("+widthMeasureSpec+")+("+heightMeasureSpec+")");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ////]Log.w(this.toString(), "onMeasure+("+widthMeasureSpec+")+("+heightMeasureSpec+")");

        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(width, height);//设置宽和高
        //boolMuStart=true;
        init_ViewData();
        //cusTreDrawTool.init_ViewData();


        cusTreDrawTool.init_TrendData(width,height,getContext());
        StartDraw(0);
        int[] location = new int[2] ;
        getLocationInWindow(location); //获取在当前窗口内的绝对坐标
        int[] location_B = new int[2] ;
        getLocationOnScreen(location_B);//获取在整个屏幕内的绝对坐标
        //]Log.w(TAG, "onMeasureG0: "+"="+location[0] +"="+location[1]+"="+location_B[0] +"="+location_B[1]);
        widthLocation=location_B[0];
        heightLocation=location_B[1];
        setLocationData();


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //]Log.w(TAG, "onMeasureG0C: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(boolStart&&cusTreDrawTool.paintData.fxDataF!=null)
            {
                cusTreDrawTool.StartDraw(canvas);
//                //canvas.clipRect(new Rect(0,0,100,100));
//                ////]Log.w(this.toString(), "KOKO+("+width+")+("+height+")");
//                oD_NnLine(canvas);//直虚线
//                OD_001(canvas);//L线
//
//                oD_QuoMaxMin(canvas);//走势|价格|价格线
//                PrC_oDTrendLine(canvas);//时间|时间点
//               // canvas.drawText(String.valueOf(modeRefers),width/2F,height/2F,paintTextUIPo);

            }



    }
    public void StartDraw(int int_A) {
        if(int_A==0)
            {
                boolStartA=true;
            }else {
            boolStartB=true;
        }
        if(boolStartA&&boolStartB)
            {
                boolStart=true;
                //]Log.w(TAG, "onTouchEventD2: " );
                PrC_setMovePosition(0);
            }



    }

    void PrC_setMovePosition(float floattAA)
    {
        //]Log.w(TAG, "PrC_setMovePositionF0: "+"="+ floattAA);
        cusTreDrawTool.SetMoveTrendData(floattAA);
        cusTreDrawTool.updateTimeDatsTime();
        cusTreDrawTool.calTimeDat();
        cusTreDrawTool.updateDraw();
        // floatPositionNow =floattAA;
        //calCharTime02(floattAA);
        //return //PrC_calChartTime();
    }
    void TrendIni(){
        invalidate();
    }

    public class TimeDat{
        public int intH,intM;
        public float floatPosition;
        public TimeDat(int intHAA,int intMAA){
            intH=intHAA;
            intM=intMAA;
        }


    }
    public class CusTreDrawTool implements OnUpdateTime {
        public Paint paintTextA;
        public Paint paintTextB;
        public Paint paintTextC;
        public Paint paintTextUIA;
        public Paint paintTextUIB;
        public Paint paintUIA;
        public Paint paintUIB;
        public Paint paintUIC;
        public Paint paintUID;
        public float floatTimeNum =10, trendUISize, trendUIBSize;//时间点数量，走势柱大小，走势柱线大小
        public int StraightLineLimit, intTimeMinuLimit, intTimeHourLimit;//直虚线限制数目,时间分钟限制数目,时间小时限制数目
//        public float intNum=10,floatUIJd,floatUIJd2;//时间点数量，走势柱大小，走势柱线大小
//        public int intNumUIKk,intNumUITk,intNumUITkH;//直虚线限制数目,时间分钟限制数目,时间小时限制数目

        //public float floatUIPoN,floatUIPoT,floatUIPoM;//图表缩放
        public TimeDat[] timeDats;
        public TimeOD timeODNew;
        public TimeOD timeODNow;//最新时间
        public int intLast;//最后时间
        public int intT;//最后时间位置
        public int intNum;//数量
        private int width;//设置高
        private int height;//设置高
        OnGetQuoData onGetQuoData;
        PaintData paintData;


        /**
         * 每个长度范围EachLength
         */
        float floatEachLength,
        /**
         * 长度范围
         */
        floatViewLength,
        /**
         * 移动位置
         */
        floatPositionNow,
        /**
         * 位置差距
         */
        floatPositionGap;

        /**
         *上次的距离
         */
        float floatCal02, /**
         * 移动距离
         */
        floatPo02, /**
         * 多出来的距离
         */
        floatMs02;
        boolean boolCal02,boolCal02B;
        float floatCal02_C;
        int intCal02,intCal02_B;
        int intCal02_C;

        //float[] floatsTrendPos;
        public CusTreDrawTool(){
        }
        public void updateDraw(){
            updateTrendQuoData();
        }
        public void setTrendDraw(float[][] floats_Data,float[] floats_NewData){
            paintData.fxDataF=floats_Data;
            paintData.fxDataNew=floats_NewData;
            if(floats_NewData!=null)
                {
                    paintData.fxNewD=floats_NewData[0];
                    paintData.fxNewU=floats_NewData[1];
                    paintData.setMaxMinData(floats_NewData[2],floats_NewData[3],floats_NewData[4]);
                }else {
                paintData.fxNewD=0;
                paintData.fxNewU=0;
            }

            paintData.ccPaintDataPos();
            invalidate();

        }
        public void setTrendLine(float[][][] floats_Line){

        }

        void init_TrendData(int int_W,int int_H,Context context_A){
            FXDataConnect.TimeConnect timeConnect_A=((MainApplication)context_A.getApplicationContext()).fxDataConnect.getTimeConnect(this);
            timeODNew=timeConnect_A.getTimeNew();
            timeODNow=timeODNew;
            paintData=new PaintData();
            width=int_W;
            height=int_H;
            floatEachLength =(width-80)/floatTimeNum;
            floatViewLength =width-80;
            floatPositionNow =0;
            setTrendLine(0,0);
        }

        public void setQuoOD(QuosSystemCenter.QuoOD quoOD_A){
            onGetQuoData=quoOD_A;
            quoOD=quoOD_A;
            quoOD_A.cusTreDrawTool=this;
        }

        public void StartDraw(Canvas canvas_A){
            drawLineChartL(canvas_A);//L线
            drawMaxMinPrice(canvas_A);//绘制最大价格
            drawChartPrice(canvas_A);//价格线|横虚线
            drawTime(canvas_A);//时间点
            drawDashed(canvas_A);//直虚线
            drawTrend(canvas_A);//走势
            drawtMarker(canvas_A);


        }
        public void setTrendLine(int int_A,int int_B){

            switch(int_A){
                case 0:
                    floatTimeNum =10;
                    StraightLineLimit =1;
                    trendUISize =30;
                    trendUIBSize =38;
                    switch(int_B){
                        case 5:
                            intTimeHourLimit =0;
                            intTimeMinuLimit =10;
                            break;
                        case 15:
                            intTimeHourLimit =0;
                            intTimeMinuLimit =30;
                            break;
                        case 30:
                            intTimeHourLimit =1;
                            intTimeMinuLimit =0;
                            break;
                        case 60:
                            intTimeHourLimit =2;
                            intTimeMinuLimit =0;
                            break;
                        case 1:
                        default:
                            intTimeHourLimit =0;
                            intTimeMinuLimit =2;
                            break;
                    }

                    break;
                case 1:
                    floatTimeNum =19;
                    StraightLineLimit =2;
                    trendUISize =16;
                    trendUIBSize =24;
                    switch(int_B){
                        case 5:
                            intTimeHourLimit =0;
                            intTimeMinuLimit =15;
                            break;
                        case 15:
                            intTimeHourLimit =1;
                            intTimeMinuLimit =0;
                            break;
                        case 30:
                            intTimeHourLimit =2;
                            intTimeMinuLimit =0;
                            break;
                        case 60:
                            intTimeHourLimit =4;
                            intTimeMinuLimit =0;
                            break;
                        case 1:
                        default:
                            intTimeHourLimit =0;
                            intTimeMinuLimit =3;
                            break;
                    }
                    break;
                case 2:
                    floatTimeNum =28;
                    StraightLineLimit =4;
                    trendUISize =10;
                    trendUIBSize =16;
                    switch(int_B){
                        case 5:
                            intTimeHourLimit =0;
                            intTimeMinuLimit =15;
                            break;
                        case 15:
                            intTimeHourLimit =1;
                            intTimeMinuLimit =0;
                            break;
                        case 30:
                            intTimeHourLimit =2;
                            intTimeMinuLimit =0;
                            break;
                        case 60:
                            floatTimeNum =23;
                            intTimeHourLimit =4;
                            intTimeMinuLimit =0;
                            break;
                        case 1:
                        default:

                            intTimeHourLimit =0;
                            intTimeMinuLimit =3;
                            break;
                    }
                    break;
                case 3:
                    floatTimeNum =46;
                    StraightLineLimit =6;
                    trendUISize =6;
                    trendUIBSize =8;
                    switch(int_B){
                        case 5:
                            intTimeHourLimit =0;
                            intTimeMinuLimit =30;
                            break;
                        case 15:
                            intTimeHourLimit =2;
                            intTimeMinuLimit =0;
                            break;
                        case 30:
                        case 60:
                            floatTimeNum =21;
                            intTimeHourLimit =3;
                            intTimeMinuLimit =0;
                            break;
                        case 1:
                        default:
                            intTimeHourLimit =0;
                            intTimeMinuLimit =5;
                            break;
                    }
                    break;
                case 4:
                    floatTimeNum =64;
                    StraightLineLimit =10;
                    trendUISize =4;
                    trendUIBSize =6;
                    switch(int_B){
                        case 5:
                            intTimeHourLimit =0;
                            intTimeMinuLimit =30;
                            break;
                        case 15:
                            intTimeHourLimit =2;
                            intTimeMinuLimit =0;
                            break;
                        case 30:
                        case 60:
                            floatTimeNum =23;
                            intTimeHourLimit =3;
                            intTimeMinuLimit =0;
                            break;
                        case 1:
                        default:
                            intTimeHourLimit =0;
                            intTimeMinuLimit =10;
                            break;
                    }
                    break;
                default:
                    intTimeMinuLimit =1;
                    break;
            }
            intNum=(int)floatTimeNum;
            floatEachLength =(width-80)/floatTimeNum;
            timeDats=new TimeDat[intNum];
            paintData.setDataNum(intNum);
        }




        /**
         *计算移动时间数据
         */
        public void SetMoveTrendData(float floatAA) {
            floatPositionNow = floatAA;

            float float_B = floatAA - floatPo02;//计算出上次和这次的移动距离
            floatPo02 = floatAA;//记录这次位置
            boolean bool_B2;
            if (floatCal02 > 0) {
                bool_B2 = true;
            } else {
                bool_B2 = false;
            }
            floatCal02 += float_B;//将这次计算移动距离和上次多出来的距离加在一起

            int int_A = ((int) (floatCal02 / floatEachLength));//计算这次移动距离是否超过时间距离
            if (floatCal02 > 0) {
                if (!bool_B2) {
                    int_A++;
                }
            } else {
                if (bool_B2) {
                    int_A++;
                }
                //bool_B2=false;
            }
            if (floatCal02 < 0) {

            }
            if (int_A != 0) {
                //]Log.w(TAG, "calCharTime04: " + "=" + floatCal02 + "=" + int_A);
            }


            if (floatAA <= 0)//计算多出来的距离
            {
                floatMs02 = floatEachLength + (floatAA % floatEachLength);

                //]Log.w(TAG, "calCharTime03: " + "=" + floatAA + "=" + floatMs02);
            } else {
                floatMs02 = (floatAA % floatEachLength);
                //]Log.w(TAG, "calCharTime03B: " + "=" + floatAA + "=" + floatMs02 + "=" + (floatAA % floatEachLength));
            }

            //]Log.w(TAG, "calCharTime05: " + "=" + floatMs02 + "=" + floatAA + "=" + floatCal02);

            //if(floatCal02==0)return;
            //]Log.w(TAG, "calCharTime06: " + "=" + floatCal02 + "=" + (floatCal02 - (floatEachLength * int_A)));
            floatCal02 -= (floatEachLength * int_A);
            TimeOD timeOD_F = new TimeOD(timeODNow.PuC_getTimeLong());
            timeOD_F.PuC_addMinute(-(int_A * quoOD.intTimeCycle));
            //quoOD.odTimeData.timeODNow.PuC_addMinute(-(int_A*quoOD.intTimeMode));

            floatMs02 = (floatEachLength + floatCal02);
            // timeODCal02.PuC_addMinute(-int_A);
            TimeOD timeODNews_A = timeODNew;
            intCal02 -= int_A;
            if (timeODNews_A.PuC_calTimeContrastMinu(timeOD_F) < 2)//[0==,1=<,2=>]
            {
                timeOD_F = new TimeOD(timeODNews_A);
                boolCal02B = true;
                floatMs02 = floatEachLength;
                // floatCal02=0;
                intCal02 = intCal02_B;
            } else {
                boolCal02B = false;
                floatCal02_C = 0;

            }
            timeODNow.intsA = timeOD_F.intsA;
            if (!timeOD_F.PuC_isTimeODSameDay(timeODNow)) {
                setQuoDataBool(false);

            }
        }
        void setQuoDataBool(Boolean bool_A){
            if(onGetQuoData!=null)
                {
                    onGetQuoData.On_setQuoBool(bool_A);
                }
        }
        //计算走势时间
        public void updateTimeDatsTime() {
            if (true) {
                timeDats[0] = new TimeDat(timeODNow.intsA[3], timeODNow.intsA[4]);
                int int_ModeNum=quoOD.intTimeCycle;
                int int_F = timeODNow.intsA[4] % int_ModeNum;
                int[] ints_A = new int[2];
                intLast = 0;
                intT = 0;
                ints_A[0] = timeDats[0].intH;
                ints_A[1] = timeDats[0].intM - int_F;//+2;
                for (int i = 0; i < timeDats.length; i++) {

                    if (ints_A[1] < 0) {
                        ints_A[1] = 60 + ints_A[1];
                        ints_A[0] -= 1;
                        if (ints_A[0] < 0) {
                            ints_A[0] = 23;
                            intLast++;
                            setQuoDataBool(false);
                            intT = i;//quoOD.odTimeData.timeDats.length-i;
                        }

                    }

                    timeDats[i] = new TimeDat(ints_A[0], ints_A[1]);
                    ints_A[1] -= int_ModeNum;

                }
            }
        }
        void updateTrendQuoData(){
            if(onGetQuoData!=null)
                {
                    onGetQuoData.On_getQuoData(this);
                }
        }
        /**
         * 计算刷新图表绘制时间点与坐标
         */
        public void calTimeDat() {
            //quoOD.setCustomizeData(floatViewLength,floatMs02,floatEachLength);
            //floatsTrendPos=new float[intNum];
            float float_position = floatViewLength - 150;
            float float_Each = floatMs02;//- (floatPositionGap >= 0 ? floatEachLength : 0);
            for(int i=0;i<timeDats.length;i++)
            {
                timeDats[i].floatPosition= ((float_position - (floatEachLength * i)) + float_Each);
            }
        }
        /**
         *计算缩放
         */
        void setDuoModeSt(int intAA){
            int int_NumSt=quoOD.intTimeSize;
            //]Log.w(TAG, "setDuoModeStG0: "+"="+int_NumSt );
            if(true)
                {
                    if(intAA>0)
                    {
                        if(int_NumSt==4)return;
                        int_NumSt+=intAA;
                        if(int_NumSt>=4)int_NumSt=4;

                    }else {
                        if(int_NumSt==0)return;
                        int_NumSt+=intAA;
                        if(int_NumSt<=0)int_NumSt=0;
                    }
                    //]Log.w(this.toString(), "setDuoModeSt01: "+"="+intNumSt +"="+int_NumSt+"="+ intAA );

                    quoOD.setTimeSize(int_NumSt);
                    setTrendLine(quoOD.intTimeSize,quoOD.intTimeCycle);
                    updateTimeDatsTime();
                    calTimeDat();
                    updateDraw();
                }



        }
//        public void ccPaintDataPos() {
//            float float_A = -1;//Max
//            float float_B = -1;//Min
//            for (int i = 0; i < paintData.fxDataF.length; i++) {
//                if(paintData.fxDataF[i]!=null)
//                {
//                    if (float_A != -1) {
//                        if(float_A<paintData.fxDataF[i][3])float_A=paintData.fxDataF[i][3];
//                    }else {
//                        float_A=paintData.fxDataF[i][3];
//                    }
//                    if (float_B != -1) {
//                        if(float_B>paintData.fxDataF[i][2])float_B=paintData.fxDataF[i][2];
//
//                    }else {
//                        float_B=paintData.fxDataF[i][2];
//                    }
//                }
//            }
//            //]Log.w(this.toString(), "ccPaintDataPos03: "+"="+float_A +"="+float_B );
//            float float_C=0;
//            if(float_A!=-1)
//            {
//                float_C=(float_A-float_B);
//            }else {
//                float_A=0.0001F;
//                float_B=0;
//                float_C=0.0001F;
//            }
//            ////]Log.w(this.toString(), "ccPaintDataPos04B: "+"="+(float_A)+"="+(float_B)+"="+float_C +"="+(0.0001F)+"="+(0.0001F/2F)+"="+ BigDecimal.valueOf((0.0001F)));
//            float float_C2=float_C/2F;
//            if(float_C>0.0005F)
//            {
//                float_C=float_C/2F;
//            }else {
//                float_C=0.0005F/2F;
//                // float float_D=(0.0001F-float_C)/2f;
//                // float_A+=float_D;
//                // float_B-=float_D;
//            }
//            //]Log.w(this.toString(), "ccPaintDataPosD0 "+"="+float_A +"="+float_B+"="+float_C2 +"="+(float_B+float_C));
//            //  float float_E2=(float_A-float_B)/2F;
//
//            float float_E=(float_B+float_C2);
//
//            //]Log.w(this.toString(), "ccPaintDataPos01: "+"="+(float_A-float_B)+"="+float_C);
//            //]Log.w(this.toString(), "ccPaintDataPos04: "+"="+(float_A)+"="+(float_B)+"="+float_C);
//            //]Log.w(this.toString(), "ccPaintDataPos02: "+"="+float_E+"="+float_C);
//            customizeTrend.setUIPo(float_C,float_E);
//
//        }
//        void ccTimePricePoint() {
//            floatTimePoint=null;
//            float floatU=floatUIPoM+floatUIPoN;
//            float floatD=floatUIPoM-floatUIPoN;
//
//            float floatPoAA=floatU-floatD;
//            float floatPo2AA=height-220;
//            int int_A=(int)(floatUIPoN*200000);
//            float float_A2=floatUIPoM-floatUIPoN;
//            int int_B=int_A/10;
//            int int_C=((int)(floatD*100000))%10;
//            float float_D=floatD;
//            int int_K=int_B/7;
//            if(int_K<=0)
//            {
//                int_K=1;
//            }
//            Path path = new Path();
//            if(int_C!=0)
//            {
//                float_D-=(int_C/100000F);
//                int_C=10-int_C;
//                float float_C=floatD+(int_C/100000F);
//            }
//            int int_Y=int_B/int_K;
//            floatTimePoint=new float[int_Y];
//            for(int i=int_K;i<=int_B;i+=int_K)
//            {
//                float float_B2=float_D+(0.0001F*i);
//                // //]Log.w(TAG, "oD_UILin02: "+"="+ int_A +"="+int_B+"="+float_B2 +"="+(floatD+float_B2)+"="+floatD +"="+((floatD+float_B2)-floatD));
//                // float_B2=floatD+float_B2;
//                float float_B3=floatPo2AA*((floatU-float_B2)/floatPoAA);
//                String string_C=String.valueOf(float_B2);
//                if(string_C.length()>7)
//                {
//                    string_C=string_C.substring(0,7);
//
//                }else if(string_C.length()<7)
//                {
//                    for(int r=string_C.length();r<7;r++)
//                    {
//                        string_C+="0";
//                    }
//                }
//
////                    float float_A= quoOD.odTimeData.timeDats[i].floatPosition;
//////                canvas.drawLine(float_A,100,float_A,height-120,paintNnUI);
////                    path.reset();
////                    path.moveTo(5, float_B3+100);
////                    path.lineTo(width-9, float_B3+100);
////                    canvas.drawPath(path, paintNnUI);
//                canvas.drawText(string_C,width-5,float_B3+100,paintTextUIPo);
//
//            }
//            canvas.save();
//            canvas.clipRect(80,90,width-80,height-110);
//            ////]Log.w(TAG, "oD_UILinF0: "+"="+int_B +"="+floatU+"="+ floatD +"="+float_D);
//            for(int i=int_K;i<=int_B;i+=int_K)
//            {
//                float float_B2=float_D+(0.0001F*i);
//
//                float float_B3=floatPo2AA*((floatU-float_B2)/floatPoAA);
//
//                path.reset();
//                path.moveTo(5, float_B3+100);
//                path.lineTo(width-9, float_B3+100);
//                canvas.drawPath(path, paintNnUI);
//
//            }
//            canvas.restore();
//            //]Log.w(TAG, "oD_UILin03 "+"="+int_C +"="+floatD +"="+(int_C%10));
//            if(int_B!=0)
//            {
//
//            }
//        }

        //绘制图表L线
        void drawLineChartL(Canvas canvas_A){
            canvas_A.drawRect(0,0,width,height,mPaintSecondLine);
            canvas_A.drawLine(80,height-80,width-80,height-80,mPaintSecondLine);
            canvas_A.drawLine(width-80,height-80,width-80,80,mPaintSecondLine);
        }
        //绘制最大价格
        void drawMaxMinPrice(Canvas canvas_A){
            if(true)
                {
                    canvas_A.drawText(paintData.strM[0],45,45,paintTextMaxMin);
                    canvas_A.drawText(paintData.strM[1],175,45,paintTextMaxMin);
                    canvas_A.drawText(paintData.strM[2],305,45,paintTextMaxMin);
                }

            String string_A=String.valueOf(paintData.flM);
            //]Log.w(TAG, "oD_QuoMaxMin01: "+"="+ string_A +"="+string_A.length());
            if(string_A.length()>7)
            {
                string_A=string_A.substring(0,7);

            }else if(string_A.length()<7)
            {
                for(int i=string_A.length();i<7;i++)
                {
                    string_A+="0";
                }
            }
            //canvas.drawText(string_A,width-5,height/2F,paintTextUIPo);
            float float_A=paintData.flPoM+paintData.flPoN;
            float float_A2=paintData.flPoM-paintData.flPoN;
            //]Log.w(TAG, "oD_QuoMaxMin02 "+"="+ float_A +"="+float_A2+"="+paintData.flPoN +"="+paintData.flPoM);
            String string_A_2=String.valueOf(float_A);
            //]Log.w(TAG, "oD_QuoMaxMin01: "+"="+ string_A +"="+string_A.length());
            if(string_A_2.length()>7)
            {
                string_A_2=string_A_2.substring(0,7);

            }else if(string_A_2.length()<7)
            {
                for(int i=string_A_2.length();i<7;i++)
                {
                    string_A_2+="0";
                }
            }
            canvas_A.drawText(string_A_2,width-5,100,paintTextUIPo);
            string_A_2=String.valueOf(float_A2);
            //]Log.w(TAG, "oD_QuoMaxMin01: "+"="+ string_A +"="+string_A.length());
            if(string_A_2.length()>7)
            {
                string_A_2=string_A_2.substring(0,7);

            }else if(string_A_2.length()<7)
            {
                for(int i=string_A_2.length();i<7;i++)
                {
                    string_A_2+="0";
                }
            }
            canvas_A.drawText(string_A_2,width-5,height-100,paintTextUIPo);
        }
        //绘制价格线|横虚线
        void drawChartPrice(Canvas canvas_A){
            float floatU=paintData.flPoM+paintData.flPoN;
            float floatD=paintData.flPoM-paintData.flPoN;

            float floatPoAA=floatU-floatD;
            float floatPo2AA=height-220;
            int int_A=(int)(paintData.flPoN*200000);
            int int_B=int_A/10;
            //]Log.w(this.toString(), "drawChartPrice: T0"+"="+floatD +"="+((int)(floatD*100000)) +"="+(((int)(floatD*100000))%10) );
            int int_C=((int)(floatD*100000))%10;
            float float_D=floatD;
            int int_K=int_B/7;
            if(int_K<=0)
            {
                int_K=1;
            }
            Path path = new Path();
            if(int_C!=0)
            {
                float_D-=(int_C/100000F);
                int_C=10-int_C;
                float float_C=floatD+(int_C/100000F);
            }
            int int_Y=int_B/int_K;
            float[] floats_Y=new float[int_Y];
            int int_Z=0;
            for(int i=int_K;i<=int_B;i+=int_K)
            {
                float float_B2=float_D+(0.0001F*i);
                float float_B3=floatPo2AA*((floatU-float_B2)/floatPoAA);
                String string_C=String.valueOf(float_B2);
                if(string_C.length()>7)
                {
                    string_C=string_C.substring(0,7);

                }else if(string_C.length()<7)
                {
                    for(int r=string_C.length();r<7;r++)
                    {
                        string_C+="0";
                    }
                }
                canvas_A.drawText(string_C,width-5,float_B3+100,paintTextUIPo);

            }
            canvas_A.save();
            canvas_A.clipRect(80,90,width-80,height-110);
            for(int i=int_K;i<=int_B;i+=int_K)
            {
                float float_B2=float_D+(0.0001F*i);

                float float_B3=floatPo2AA*((floatU-float_B2)/floatPoAA);

                path.reset();
                path.moveTo(5, float_B3+100);
                path.lineTo(width-9, float_B3+100);
                canvas_A.drawPath(path, paintNnUI);

            }
            canvas_A.restore();

        }
        //绘制时间|时间点
        void drawTime(Canvas canvas_A){
            //canvas.drawRect(75,height-90,width-75,height-40,mPaintInterCircle);
            canvas_A.save();
            canvas_A.clipRect(80,0,width-80,height-40);
            //Log.d(TAG, "PrC_oDTrendLine_0A: "+odTimeData.timeDats.length);
            for(int i = 0; i< timeDats.length; i++)
            {
                //Log.d(TAG, "PrC_oDTrendLine_0B: "+odTimeData.timeDats[i].floatPosition);
                float float_A= timeDats[i].floatPosition;

                String string_A= timeDats[i].intH+":"+(timeDats[i].intM<10?"0":"")+ timeDats[i].intM;

                if(intTimeHourLimit==0||timeDats[i].intH%(intTimeHourLimit)==0)
                {
                    if(intTimeMinuLimit==0)
                    {
                        if(timeDats[i].intM==0)
                        {
                            canvas_A.drawText(string_A, float_A, height-50, mPaintText);
                            canvas_A.drawLine(float_A,height-92,float_A,height-68,mPaintSecondLine);
                        }else {
                            canvas_A.drawLine(float_A,height-85,float_A,height-75,mPaintSecondLine);
                        }
                    }else {
                        if(timeDats[i].intM%(intTimeMinuLimit)==0)
                        {
                            canvas_A.drawText(string_A, float_A, height-50, mPaintText);
                            canvas_A.drawLine(float_A,height-92,float_A,height-68,mPaintSecondLine);
                        }else {
                            canvas_A.drawLine(float_A,height-85,float_A,height-75,mPaintSecondLine);
                        }
                    }

                }else {
                    canvas_A.drawLine(float_A,height-85,float_A,height-75,mPaintSecondLine);
                }

                // //]Log.w(this.toString(), "PrC_oDTrendLineF0: "+"="+(quoOD.odTimeData.timeDats[i].intM%intTimeMinuLimit==0)+"="+ intTimeMinuLimit +"="+quoOD.odTimeData.timeDats[i].intM);
                // canvas.drawRect(float_A-10,100,float_A+10,height-120,mPaintSecondLine);
            }
            canvas_A.restore();
//            TimeOD timeOD_N=timeODNow;
//            String string_TimeN=timeOD_N.intsA[0]+":"+timeOD_N.intsA[1]+":"+timeOD_N.intsA[2];
//            canvas_A.drawText(string_TimeN,height/2F,width/2F,mPaintText);

        }
        //直虚线
        void drawDashed(Canvas canvas_A){
            Path path = new Path();

            for(int i = 0; i< timeDats.length; i++)
            {
                if(timeDats[i].intM%StraightLineLimit!=0)
                {
                    continue;
                }
                float float_A= timeDats[i].floatPosition;
                path.reset();
                path.moveTo(float_A, 100);
                path.lineTo(float_A, height-120);
                canvas_A.drawPath(path, paintNnUI);
            }


        }
        //走势
        void drawTrend(Canvas canvas_A){
            canvas_A.save();
            canvas_A.clipRect(80,90,width-80,height-110);
            float floatU=paintData.flPoM+paintData.flPoN;
            float floatD=paintData.flPoM-paintData.flPoN;
            float float_Po=floatU-floatD;
            float float_Po2=height-220;
            float float_Lo=-1;
            for(int i = timeDats.length-1; i>=0; i--)
            {

                if(paintData.fxDataF[i]!=null)
                {
                    if(true)
                    {
                        float float_A= timeDats[i].floatPosition;
                        if(true)
                        {
                            float float_YU=float_Po2*((floatU-paintData.fxDataF[i][2])/float_Po);
                            float float_YD=float_Po2*((floatU-paintData.fxDataF[i][3])/float_Po);
                            canvas_A.drawLine(float_A,float_YU+100,float_A,float_YD+100,paintQuoColorD);
                        }
                        //Log.d(TAG, "PrC_oDTrendLine_0B: "+odTimeData.timeDats[i].floatPosition);

                        float float_A2= float_Po2*((floatU-paintData.fxDataF[i][0])/float_Po);
                        if(float_Lo==-1)
                        {
                            float_Lo=float_A2;
                        }else {

                        }
                        Paint paint_A;
                        if(float_Lo>=float_A2)
                        {
                            paint_A=paintQuoColorU;
                        }else {
                            paint_A=paintQuoColorD;
                        }
                        canvas_A.drawRect(float_A-trendUISize,float_Lo+100,float_A+trendUISize,float_A2+100,paintQuoColorB);
                        canvas_A.drawRect(float_A-trendUISize,float_Lo+100,float_A+trendUISize,float_A2+100,paint_A);
                        canvas_A.drawLine(float_A-trendUIBSize,float_Lo+100,float_A+trendUIBSize,float_Lo+100,paint_A);
                        float_Lo=float_A2;

                    }


                }
            }
            float float_Nw= float_Po2*((floatU-paintData.fxNewD)/float_Po);
            canvas_A.drawLine(0,float_Nw+100,width,float_Nw+100,mPaintSecondLine);
            float float_Nw2= float_Po2*((floatU-paintData.fxNewU)/float_Po);
            canvas_A.drawLine(0,float_Nw2+100,width,float_Nw2+100,mPaintSecondLine);
            String string_F=String.valueOf(paintData.fxNewD);
            if(string_F.length()>7)
            {
                string_F=string_F.substring(0,7);

            }else if(string_F.length()<7)
            {
                for(int i=string_F.length();i<7;i++)
                {
                    string_F+="0";
                }
            }

            canvas_A.restore();
            canvas_A.drawRect(width-80,float_Nw+88,width,float_Nw+112,paintQuoColorUN);
            canvas_A.drawText(string_F,width-5,float_Nw+100+7,paintTextUIPoN);
            string_F=String.valueOf(paintData.fxNewU);
            if(string_F.length()>7)
            {
                string_F=string_F.substring(0,7);

            }else if(string_F.length()<7)
            {
                for(int i=string_F.length();i<7;i++)
                {
                    string_F+="0";
                }
            }
            canvas_A.drawRect(width-80,float_Nw2+88,width,float_Nw2+112,paintQuoColorDN);
            canvas_A.drawText(string_F,width-5,float_Nw2+100+7,paintTextUIPoN);
        }
        void drawtMarker(Canvas canvas_A){
            if(booltMarker)
                {
                    if(true)
                    {
                        canvas_A.save();
                        canvas_A.clipRect(80,80,width-80,height-100);
                        canvas_A.drawLine(floatMarkerPoW-15,floatMarkerPoH,floatMarkerPoW+15,floatMarkerPoH,mPaintSecondLine);
                        canvas_A.drawLine(floatMarkerPoW,floatMarkerPoH-15,floatMarkerPoW,floatMarkerPoH+15,mPaintSecondLine);
                        canvas_A.drawLine(0,floatMarkerPoH,floatMarkerPoW-30,floatMarkerPoH,mPaintMarkerLine);
                        canvas_A.drawLine(floatMarkerPoW+30,floatMarkerPoH,width,floatMarkerPoH,mPaintMarkerLine);
                        canvas_A.drawLine(floatMarkerPoW,0,floatMarkerPoW,floatMarkerPoH-30,mPaintMarkerLine);
                        canvas_A.drawLine(floatMarkerPoW,floatMarkerPoH+30,floatMarkerPoW,height,mPaintMarkerLine);
                        canvas_A.restore();

                    }
                    canvas_A.drawRect(width-80,floatMarkerPoH-12,width,floatMarkerPoH+12,paintQuoColorTN);
                    canvas_A.drawText(strMarkerQuo,width-5,floatMarkerPoH+7,paintTextUIPoT);

                    canvas_A.drawRoundRect(floatMarkerPoW-50,height-18-57,floatMarkerPoW+50,height+18-57,5,5,paintQuoColorTN);
                    canvas_A.drawText(strMarkerTime,floatMarkerPoW,height-48,paintTextUIPoTI);
                }

        }
        void setTime(TimeOD time_A){
            timeODNew=time_A;
        }
        boolean booltMarker;
        float floatMarkerPoW;
        float floatMarkerPoH;

        /**
         *标记
         */
       public void setMarkerClick(){
           if(booltMarker)
               {
                   booltMarker=false;

               }else {
               booltMarker=true;
               floatMarkerPoW=width/2F;
               floatMarkerPoH=height/2F;
               ccMarkerData();
           }
           invalidate();
//



        }
        float floatMarkerNowW;
        float floatMarkerNowH;

        void setMarkerStart(float float_W,float float_H){
            floatMarkerNowW=float_W;
            floatMarkerNowH=float_H;
        }
        void setMarkerMove(float float_W,float float_H){
            floatMarkerPoW-=(floatMarkerNowW-float_W);
            floatMarkerPoH-=(floatMarkerNowH-float_H);
            if(floatMarkerPoW<0)
                {
                    floatMarkerPoW=0;
                }
            if(floatMarkerNowH<0)
            {
                floatMarkerNowH=0;
            }
            if(floatMarkerPoW>width)
            {
                floatMarkerPoW=width;
            }
            if(floatMarkerNowH>height)
            {
                floatMarkerNowH=height;
            }
            floatMarkerNowW=float_W;
            floatMarkerNowH=float_H;
            ccMarkerData();
            invalidate();
        }
        String strMarkerQuo;
        String strMarkerTime;
        void ccMarkerData(){
            float float_U=paintData.flPoM+paintData.flPoN;
            float float_N=paintData.flPoN*2F;
            float float_A=(floatMarkerPoH-100)/(height-200);
           //]Log.w(TAG, "setTouchTrePoG0: "+"="+float_A +"="+ floatMarkerPoW +"="+float_U +"="+(float_U-(float_N*float_A)));
            //]Log.w(TAG, "setTouchTrePoG1:" +"="+paintData.flPoM +"="+paintData.flPoN);
            float float_B=float_U-float_N*float_A;
            float float_TouchTreData=((int)(float_B*100000))/100000F;
            strMarkerQuo=String.valueOf(float_TouchTreData);
           if(strMarkerQuo.length()>7)
           {
               strMarkerQuo=strMarkerQuo.substring(0,7);

           }else if(strMarkerQuo.length()<7)
           {
               for(int i=strMarkerQuo.length();i<7;i++)
               {
                   strMarkerQuo+="0";
               }
           }
            ccMarkerTime();
        }
        void ccMarkerTime(){
            float float_position = floatViewLength - 150;
            float float_Each = floatMs02;//- (floatPositionGap >= 0 ? floatEachLength : 0);
            int int_N=cusTreDrawTool.intNum;
            float float_E=floatEachLength;
            TimeOD timeOD_A=cusTreDrawTool.timeODNow;
            int int_H=timeOD_A.intsA[3];
            int int_M=timeOD_A.intsA[4];
            //floatMarkerPoW=0;
            for(int i=0;i<timeDats.length;i++)
            {
                if((floatMarkerPoW+(floatEachLength/2F))>=timeDats[i].floatPosition)
                    {
                        //]Log.w(TAG, "ccMarkerTime: "+"="+timeDats[i].intH +"="+timeDats[i].intM);
                        strMarkerTime= timeDats[i].intH+":"+(timeDats[i].intM<10?"0":"")+ timeDats[i].intM;
                        break;

                    }
            }



        }


        @Override
        public void onUpdateTime(TimeOD timeODAA) {
            setTime(timeODAA);

        }

        @Override
        public void onUpdateNewDay(TimeOD timeODAA) {
            setTime(timeODAA);
            setQuoDataBool(false);
            quoOD.reqUpdateQuoData();

        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                ////]Log.w(this.toString(), "("+event.getX()+"="+width+")_A_("+event.getY()+"="+height+")");
                break;
            case MotionEvent.ACTION_DOWN:
                ////]Log.w(this.toString(), "dispatchTouchEvent:(ACTION_DOWN) ");
                //getParent().requestDisallowInterceptTouchEvent(true);
//                if (aBoolean){
//                    aBoolean=false;
//                    mPaintSecondLine.setColor(Color.GRAY);//设置画笔颜色
//                }else {
//                    aBoolean=true;
//                    mPaintSecondLine.setColor(Color.YELLOW);//设置画笔颜色
//                }
                break;
            case MotionEvent.ACTION_UP:
                ////]Log.w(this.toString(), "dispatchTouchEvent:(ACTION_UP) ");
                break;
        }

        return super.dispatchTouchEvent(event);
    }
    int modeRefers;
    int intModeNumL;
    float modeDistance;
    float firstModePosX,firstModePosY;
    //int intCCC;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //]Log.w(this.toString(), "onTouchEventMA0"+"="+event.getAction());
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                if (cusTreDrawTool.booltMarker) {
                    cusTreDrawTool.setMarkerMove(event.getX(), event.getY());
                } else {
                    modeRefers = event.getPointerCount();
                    //]Log.w(this.toString(), "onTouchGG9: " + "=" + modeRefers);
                    if (modeRefers == 2) {
                        float float_mod2 = modeDistance - spacing(event);
                        //]Log.w(this.toString(), "onTouchEventM01: " + "=" + float_mod2 + "=" + modeDistance + "=" + spacing(event));
                        //]Log.w(this.toString(), "onTouchEventM2: " + "=" + float_mod2 + "=" + (float_mod2 / 180F));
                        int int_Mlu = (int) (float_mod2 / 100);
                        if (intModeNumL != int_Mlu) {
                            int int_Cka = 0;
                            if (intModeNumL > int_Mlu) {
                                for (int i = intModeNumL; i > int_Mlu; i--) {
                                    int_Cka--;
                                }
                            } else {
                                for (int i = intModeNumL; i < int_Mlu; i++) {
                                    int_Cka++;
                                }
                            }
                            cusTreDrawTool.setDuoModeSt(int_Cka);
                            invalidate();
                            intModeNumL = int_Mlu;
                        }

                    } else {
                        ////]Log.w(this.toString(), "("+event.getX()+"="+width+")_B_("+event.getY()+"="+height+")");
                        float ev_x = event.getX();
                        //float ev_y = event.getY();
                        float ev_x2 = ev_x - lastX;
                        lastX = ev_x;
                        // lastY = ev_y-event.getY();
                        // lastX=floatsA[0]+lastX;
                        //lastY=floatsA[1]+lastX;
                        ev_x2 = floatsA[0] + ev_x2;
                        // if(ev_x2<0)ev_x2=0;
                        // if(ev_x2<0)ev_x2=0;
                        floatsA[0] = ev_x2;
                        //floatsA[1]=lastY;
                        //]Log.w(TAG, "onTouchEventD0: ");
                        PrC_setMovePosition(floatsA[0]);
                        invalidate();
                    }
                }
                return true;
            case MotionEvent.ACTION_DOWN:
                if(cusTreDrawTool.booltMarker)
                    {
                    cusTreDrawTool.setMarkerStart(event.getX(),event.getY());
                    }else {
                    intModeNumL=0;
                    modeRefers=1;
                    firstModePosX=event.getX();
                    firstModePosY=event.getY();
                    lastX = event.getX();
                    lastY = event.getY();
                    if(!mScroller.isFinished()){
                        mScroller.abortAnimation();
                    }
                }

                //floatsA[0]=lastX;
                //floatsA[1]=lastY;
                ////]Log.w(this.toString(), "onTouchEvent:(ACTION_DOWN) ");
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                modeRefers=0;
                intModeNumL=0;
                float up_x = event.getX();
                float up_y = event.getY();
                velocityTracker.computeCurrentVelocity(1000, mMaxFlintVelocity);
                int xVelocity = (int) velocityTracker.getXVelocity();
                int yVelocity = (int) velocityTracker.getYVelocity();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                boolean bool_A=(Math.abs(xVelocity) > mMinFlintVelocity|| Math.abs(yVelocity) > mMinFlintVelocity);
                //]Log.w(this.toString(), "(ACTION_UP)="+bool_A+"="+Math.abs(xVelocity)+"="+mMinFlintVelocity+"="+Math.abs(yVelocity)+"="+mMinFlintVelocity);
                if (bool_A)
                {
                    mScroller.fling((int)floatsA[0], (int)floatsA[1], xVelocity, yVelocity, -9999999, 99999, 0, cusTreDrawTool.height-50);
                    invalidate();
                }


                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                modeRefers+=1;
                if(true)
                {
                    modeDistance=spacing(event);
                }
                //]Log.w(TAG, "onTouchEventM03D: "+"="+ modeRefers);
                return true;
            case MotionEvent.ACTION_POINTER_UP:

                modeRefers -= 1;
                //]Log.w(TAG, "onTouchEventM03U: "+"="+ modeRefers);
                return true;

        }
        // getParent().requestDisallowInterceptTouchEvent(false);
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

            floatsA[0]=mScroller.getCurrX();
            //]Log.w(TAG, "onTouchEventD1: ");
            PrC_setMovePosition(floatsA[0]);
            stringA=String.valueOf(floatsA[0]);
            floatsA[1]=mScroller.getCurrY();
            ////]Log.w("OKL1", "computeScroll: "+mScroller.getStartX()+"="+ mScroller.getStartY());
            ////]Log.w("OKL0", "computeScroll: "+mScroller.getCurrX()+"="+ mScroller.getCurrY());
            ////]Log.w("OKL1", "computeScroll: "+mScroller.getFinalX()+"="+ mScroller.getFinalY());
            //scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
    TouchTrendView touchTrendView;
    public void initLocationData(TouchTrendView touchTrendView_A){
        touchTrendView=touchTrendView_A;
        touchTrendView.setCustomizeLocationData(new int[]{width,height,widthLocation,heightLocation});
    }
    public void setLocationData(){
        if(touchTrendView!=null)
            {
                //]Log.w(TAG, "setLocationDataD0: "+"="+ width+"="+height+"="+widthLocation+"="+heightLocation);
                touchTrendView.setCustomizeLocationData(new int[]{width,height,widthLocation,heightLocation});
            }
    }
    public class PaintData{
        //public int intState;
        //public SkillQuoName skillName;
       // public Paint paint;
        public float flMax;
        public float flMin;
        public float flSta;
        public float[] flM;
        public String[] strM;
        public float[][] fxDataF;
        public float[] fxDataNew;
        public float fxNewD,fxNewU;
        public float flUIPoMax,flUIPoMin;
        public float flPoN,flPoM;
        public int floNum;
        //public T ODTimeData;
        public PaintData(){
            //paint=PrC_crPaint();
           // floNum=intNumAA;
            //fxDataF=new float[floNum][];
            flM=new float[3];
            strM=new String[]{"","",""};
            fxNewD=-1;
            fxNewU=-1;
        }


        public void setDataNum(int int_Num){
            fxDataF=new float[int_Num][];
        }
        public void setMaxMinData(float floatPercent,float floatH,float floatL){
            //fxDataF=new float[floNum][];
            float float_A=((int)(floatPercent*100))/100F;
            strM=new String[]{String.valueOf(float_A)+"%",String.valueOf(floatH),String.valueOf(floatL)};
        }

        /**
         *
         */
//        private Paint PrC_crPaint(){
//            paint=new Paint();
//            switch(skillName.getIndex()){
//                case 0:case 1:case 2:case 3:case 4:case 5:case 6:case 7:case 8:case 9:
//                    paint.setAntiAlias(true);//消除锯齿
//                    paint.setColor(Color.GRAY);//设置画笔颜色
//                    paint.setStyle(Paint.Style.STROKE);//设置为空心
//                    paint.setStrokeWidth(10);//设置宽度
//                    break;
//                default:
//                    break;
//            }
//            return paint;
//        }
        public void ccPaintDataPos() {
            float float_A = -1;//Max
            float float_B = -1;//Min
            //]Log.w(TAG, "ccPaintDataPosF0: "+"="+ (fxDataF!=null));
            if(fxDataF!=null) {

                for (int i = 0; i < fxDataF.length; i++) {
                    if (fxDataF[i] != null) {
                        if (float_A != -1) {
                            if (float_A < fxDataF[i][3]) float_A = fxDataF[i][3];
                        } else {
                            float_A = fxDataF[i][3];
                        }
                        if (float_B != -1) {
                            if (float_B > fxDataF[i][2]) float_B = fxDataF[i][2];

                        } else {
                            float_B = fxDataF[i][2];
                        }
                    }
                }
                //]Log.w(this.toString(), "ccPaintDataPos03: " + "=" + float_A + "=" + float_B);
                float float_C = 0;
                if (float_A != -1) {
                    float_C = (float_A - float_B);
                } else {
                    float_A = 0.0001F;
                    float_B = 0;
                    float_C = 0.0001F;
                }
                ////]Log.w(this.toString(), "ccPaintDataPos04B: "+"="+(float_A)+"="+(float_B)+"="+float_C +"="+(0.0001F)+"="+(0.0001F/2F)+"="+ BigDecimal.valueOf((0.0001F)));
                float float_C2 = float_C / 2F;
                if (float_C > 0.0005F) {
                    float_C = float_C / 2F;
                } else {
                    float_C = 0.0005F / 2F;
                    // float float_D=(0.0001F-float_C)/2f;
                    // float_A+=float_D;
                    // float_B-=float_D;
                }
                //]Log.w(this.toString(), "ccPaintDataPosD0 " + "=" + float_A + "=" + float_B + "=" + float_C2 + "=" + (float_B + float_C));
                //  float float_E2=(float_A-float_B)/2F;

                float float_E = (float_B + float_C2);

                //]Log.w(this.toString(), "ccPaintDataPos01: " + "=" + (float_A - float_B) + "=" + float_C);
                //]Log.w(this.toString(), "ccPaintDataPos04: " + "=" + (float_A) + "=" + (float_B) + "=" + float_C);
                //]Log.w(this.toString(), "ccPaintDataPos02: " + "=" + float_E + "=" + float_C);
                flPoM = float_E;
                flPoN = float_C;
                //customizeTrend.setUIPo(float_C,float_E);
            }
        }
        public void setStrM(String[] strMAA){
            ////]Log.w(this.toString(), "setStrMC0: "+"="+ );
            for(int i=0;i<strMAA.length-1;i++)
            {
                if(strMAA[i]!=null)
                {
                    if(strMAA[i].length()>7)
                    {
                        strMAA[i]=strMAA[i].substring(0,7);

                    }else if(strMAA[i].length()<7)
                    {
                        for(int e=strMAA[i].length();e<7;e++)
                        {
                            strMAA[i]+="0";
                        }
                    }

                }
            }
            if(true)
            {
                //]Log.w(this.toString(), "setStrV0: "+"="+ strMAA[2]);
                int int_A=strMAA[2].indexOf(".");
                if(strMAA[2].length()>int_A+3)
                {
                    strMAA[2]=strMAA[2].substring(0,int_A+3);
                }
                //]Log.w(this.toString(), "setStrV1: "+"="+ strMAA[2]);
            }
            strM=strMAA;
        }
        public class PaintLine{
            public int intMode;
            public int intPage;
            public PaintLine(){

            }
            public void setLine(int int_Mode,int int_Page,float[][][] floats_Line){

            }
        }

    }





    //--------------------------------------------------------------------------------------------

//    /**
//     * 初始化数据
//     */
//    public void PuC_anData(QuosSystemCenter.QuoOD quoODAA){
//        this.quoOD=quoODAA;
//        intNum=quoODAA.odTimeData.intNum;
//        if(boolMuStart)
//            {
//                floatEachLength =(width-80)/intNum;
//                //]Log.w(TAG, "PuC_anData01 "+"="+floatEachLength +"="+((width-80)/10F) );
//            }
//
//
//        //this.odTimeData =new ODTimeData(timeODAA,intAA);
////        this.odTimeData.timeODCr=new MyFXDataService.TimeOD(timeODAA);
////        this.odTimeData.timeODNow=new MyFXDataService.TimeOD(this.odTimeData.timeODCr);
////        this.odTimeData.intTimeGrade=intAA;
//        // this.onGetQuoData=onGetQuoDataAA;
//        //PuC_calTimeDat();
////return this.odTimeData;
//        //Log.d(TAG, "PuC_anData: "+quoOD.odTimeData.timeODCr.intsA[3] +"="+quoOD.odTimeData.timeODCr.intsA[4]);
//        //Log.d(TAG, "PuC_anData_0: "+timeODAA.intsA[3] +"="+timeODAA.intsA[4]);
//        //Log.d(TAG, "PuC_anData_0: "+ MyFXTimingService.ST_TimeOD.intsA[3] +"="+ MyFXTimingService.ST_TimeOD.intsA[4]);
//
//
//
//    }

//    public void setIntNum(int intNumAA){
//
//
//            intNumSt=intNumAA;
//        switch(intNumAA){
//            case 0:
//                intNum=10;
//                intNumUIKk=1;
//                intNumUITk=2;
//                floatUIJd=30;
//                floatUIJd2=38;
//                break;
//            case 1:
//                intNum=19;
//                intNumUIKk=2;
//                intNumUITk=3;
//                floatUIJd=16;
//                floatUIJd2=24;
//                break;
//            case 2:
//                intNum=28;
//                intNumUIKk=4;
//                intNumUITk=4;
//                floatUIJd=10;
//                floatUIJd2=16;
//                break;
//            case 3:
//                intNum=46;
//                intNumUIKk=6;
//                intNumUITk=6;
//                floatUIJd=6;
//                floatUIJd2=8;
//                break;
//            case 4:
//                intNum=64;
//                intNumUIKk=10;
//                intNumUITk=10;
//                floatUIJd=4;
//                floatUIJd2=6;
//                break;
//            default:
//                intNum=12;
//                intNumUIKk=1;
//                intNumUITk=1;
//                floatUIJd=30;
//                floatUIJd2=42;
//                break;
//        }
//        setintNumUITk();
//        if(boolMuStart)
//        {
//            floatEachLength =(width-80)/intNum;
//            //]Log.w(TAG, "PuC_anData01 "+"="+floatEachLength +"="+((width-80)/10F) );
//        }
//        if(quoOD!=null)
//        {
//            //]Log.w(TAG, "setIntNumF0: "+"="+intNum );
//            quoOD.odTimeData.intNum=(int)intNum;
//            quoOD.odTimeData.setTimeDatsNum((int)intNum);
//            PuC_calTimeDat();
//
//            quoOD.setDuoModeSt(intNumSt);
//
//            PuC_ODTrend();
//
//            }
//
//
//
//    }
//    public void setintNumUITk(){
//        int int_A=0;
//        if(quoOD!=null)
//            {
//                int_A=quoOD.intTimeMode;
//            }
//        switch(intNumSt){
//            case 0:
//                switch(int_A){
//                    case 5:
//                        intNumUITkH=0;
//                        intNumUITk=10;
//                        break;
//                    case 15:
//                        intNumUITkH=0;
//                        intNumUITk=30;
//                        break;
//                    case 30:
//                        intNumUITkH=1;
//                        intNumUITk=0;
//                        break;
//                    case 60:
//                        intNumUITkH=2;
//                        intNumUITk=0;
//                        break;
//                    case 1:
//                    default:
//                        intNumUITkH=0;
//                        intNumUITk=2;
//                        break;
//                }
//
//                break;
//            case 1:
//                switch(int_A){
//                    case 5:
//                        intNumUITkH=0;
//                        intNumUITk=15;
//                        break;
//                    case 15:
//                        intNumUITkH=1;
//                        intNumUITk=0;
//                        break;
//                    case 30:
//                        intNumUITkH=2;
//                        intNumUITk=0;
//                        break;
//                    case 60:
//                        intNumUITkH=4;
//                        intNumUITk=0;
//                        break;
//                    case 1:
//                    default:
//                        intNumUITkH=0;
//                        intNumUITk=3;
//                        break;
//                }
//                break;
//            case 2:
//                switch(int_A){
//                    case 5:
//                        intNumUITkH=0;
//                        intNumUITk=15;
//                        break;
//                    case 15:
//                        intNumUITkH=1;
//                        intNumUITk=0;
//                        break;
//                    case 30:
//                        intNumUITkH=2;
//                        intNumUITk=0;
//                        break;
//                    case 60:
//                        intNum=23;
//                        intNumUITkH=4;
//                        intNumUITk=0;
//                        break;
//                    case 1:
//                    default:
//
//                        intNumUITkH=0;
//                        intNumUITk=3;
//                        break;
//                }
//                break;
//            case 3:
//                switch(int_A){
//                    case 5:
//                        intNumUITkH=0;
//                        intNumUITk=30;
//                        break;
//                    case 15:
//                        intNumUITkH=2;
//                        intNumUITk=0;
//                        break;
//                    case 30:
//                        intNum=21;
//                        intNumUITkH=3;
//                        intNumUITk=0;
//                        break;
//                    case 60:
//                        intNum=21;
//                        intNumUITkH=3;
//                        intNumUITk=0;
//                        break;
//                    case 1:
//                    default:
//                        intNumUITkH=0;
//                        intNumUITk=5;
//                        break;
//                }
//                break;
//            case 4:
//                switch(int_A){
//                    case 5:
//                        intNumUITkH=0;
//                        intNumUITk=30;
//                        break;
//                    case 15:
//                        intNumUITkH=2;
//                        intNumUITk=0;
//                        break;
//                    case 30:
//                        intNum=23;
//                        intNumUITkH=3;
//                        intNumUITk=0;
//                        break;
//                    case 60:
//                        intNum=23;
//                        intNumUITkH=3;
//                        intNumUITk=0;
//                        break;
//                    case 1:
//                    default:
//                        intNumUITkH=0;
//                        intNumUITk=10;
//                        break;
//                }
//                break;
//            default:
//                intNumUITk=1;
//                break;
//        }
//    }
//    /**
//     *  绘制走势方格
//     *
//     * @param canvas the canvas
//     */
//    void OD_001(Canvas canvas)
//    {
//        canvas.drawRect(0,0,width,height,mPaintSecondLine);
//        canvas.drawLine(80,height-80,width-80,height-80,mPaintSecondLine);
//        canvas.drawLine(width-80,height-80,width-80,80,mPaintSecondLine);
//    }
//    /**
//     * 绘制走势线
//     *
//     * @param canvas the canvas
//     */
//    void PrC_oDTrendLine(Canvas canvas){
//        if(quoOD.odTimeData !=null)
//        {
//            //canvas.drawRect(75,height-90,width-75,height-40,mPaintInterCircle);
//            canvas.save();
//            canvas.clipRect(80,0,width-80,height-40);
//            //Log.d(TAG, "PrC_oDTrendLine_0A: "+odTimeData.timeDats.length);
//            for(int i = 0; i< quoOD.odTimeData.timeDats.length; i++)
//            {
//                //Log.d(TAG, "PrC_oDTrendLine_0B: "+odTimeData.timeDats[i].floatPosition);
//                float float_A= quoOD.odTimeData.timeDats[i].floatPosition;
//
//                String string_A= quoOD.odTimeData.timeDats[i].intH+":"+(quoOD.odTimeData.timeDats[i].intM<10?"0":"")+ quoOD.odTimeData.timeDats[i].intM;
//
//                if(intNumUITkH==0||quoOD.odTimeData.timeDats[i].intH%(intNumUITkH)==0)
//                    {
//                        if(intNumUITk==0)
//                            {
//                            if(quoOD.odTimeData.timeDats[i].intM==0)
//                                {
//                                    canvas.drawText(string_A, float_A, height-50, mPaintText);
//                                    canvas.drawLine(float_A,height-92,float_A,height-68,mPaintSecondLine);
//                                }else {
//                                canvas.drawLine(float_A,height-85,float_A,height-75,mPaintSecondLine);
//                            }
//                            }else {
//                            if(quoOD.odTimeData.timeDats[i].intM%(intNumUITk)==0)
//                            {
//                                canvas.drawText(string_A, float_A, height-50, mPaintText);
//                                canvas.drawLine(float_A,height-92,float_A,height-68,mPaintSecondLine);
//                            }else {
//                                canvas.drawLine(float_A,height-85,float_A,height-75,mPaintSecondLine);
//                            }
//                        }
//
//                    }else {
//                    canvas.drawLine(float_A,height-85,float_A,height-75,mPaintSecondLine);
//                }
//
//               // //]Log.w(this.toString(), "PrC_oDTrendLineF0: "+"="+(quoOD.odTimeData.timeDats[i].intM%intTimeMinuLimit==0)+"="+ intTimeMinuLimit +"="+quoOD.odTimeData.timeDats[i].intM);
//               // canvas.drawRect(float_A-10,100,float_A+10,height-120,mPaintSecondLine);
//            }
//            canvas.restore();
//            TimeOD timeOD_N=quoOD.odTimeData.timeODNow;
//            String string_TimeN=timeOD_N.intsA[0]+":"+timeOD_N.intsA[1]+":"+timeOD_N.intsA[2];
//            canvas.drawText(string_TimeN,height/2F,width/2F,mPaintText);
//        }
//
//
//
//    }
//    void oD_NnLine(Canvas canvas){
//        canvas.save();
//        canvas.clipRect(80,90,width-80,height-110);
//        if(quoOD.odTimeData !=null)
//        {
//            Path path = new Path();
//
//            for(int i = 0; i< quoOD.odTimeData.timeDats.length; i++)
//            {
//                if(quoOD.odTimeData.timeDats[i].intM%intNumUIKk!=0)
//                    {
//                    continue;
//                    }
//                //Log.d(TAG, "PrC_oDTrendLine_0B: "+odTimeData.timeDats[i].floatPosition);
//                float float_A= quoOD.odTimeData.timeDats[i].floatPosition;
////                canvas.drawLine(float_A,100,float_A,height-120,paintNnUI);
//                path.reset();
//                path.moveTo(float_A, 100);
//                path.lineTo(float_A, height-120);
//                canvas.drawPath(path, paintNnUI);
//               // String string_A= quoOD.odTimeData.timeDats[i].intH+":"+(quoOD.odTimeData.timeDats[i].intM<10?"0":"")+ quoOD.odTimeData.timeDats[i].intM;
//                //canvas.drawText(string_A, float_A, height-50, mPaintText);
//                // canvas.drawRect(float_A-10,100,float_A+10,height-120,mPaintSecondLine);
//            }
//        }
//        canvas.restore();
//    }
//    void oD_QuoMaxMin(Canvas canvas){
//        if(quoOD.odTimeData !=null)
//        {
//            if(true)
//            {
//
//            }
//
//            canvas.drawText(quoOD.painMainData.strM[0],45,45,paintTextMaxMin);
//            canvas.drawText(quoOD.painMainData.strM[1],175,45,paintTextMaxMin);
//            canvas.drawText(quoOD.painMainData.strM[2],305,45,paintTextMaxMin);
//            String string_A=String.valueOf(floatUIPoM);
//            //]Log.w(TAG, "oD_QuoMaxMin01: "+"="+ string_A +"="+string_A.length());
//            if(string_A.length()>7)
//            {
//                string_A=string_A.substring(0,7);
//
//            }else if(string_A.length()<7)
//            {
//                for(int i=string_A.length();i<7;i++)
//                {
//                    string_A+="0";
//                }
//            }
//            //canvas.drawText(string_A,width-5,height/2F,paintTextUIPo);
//            float float_A=floatUIPoM+floatUIPoN;
//            float float_A2=floatUIPoM-floatUIPoN;
//            //]Log.w(TAG, "oD_QuoMaxMin02 "+"="+ float_A +"="+float_A2+"="+floatUIPoN +"="+floatUIPoM);
//            String string_A_2=String.valueOf(float_A);
//            //]Log.w(TAG, "oD_QuoMaxMin01: "+"="+ string_A +"="+string_A.length());
//            if(string_A_2.length()>7)
//            {
//                string_A_2=string_A_2.substring(0,7);
//
//            }else if(string_A_2.length()<7)
//            {
//                for(int i=string_A_2.length();i<7;i++)
//                {
//                    string_A_2+="0";
//                }
//            }
//            canvas.drawText(string_A_2,width-5,100,paintTextUIPo);
//            string_A_2=String.valueOf(float_A2);
//            //]Log.w(TAG, "oD_QuoMaxMin01: "+"="+ string_A +"="+string_A.length());
//            if(string_A_2.length()>7)
//            {
//                string_A_2=string_A_2.substring(0,7);
//
//            }else if(string_A_2.length()<7)
//            {
//                for(int i=string_A_2.length();i<7;i++)
//                {
//                    string_A_2+="0";
//                }
//            }
//             canvas.drawText(string_A_2,width-5,height-100,paintTextUIPo);
//            oD_UILin(canvas,float_A,float_A2);
//            oD_QuoDataPain(canvas,float_A,float_A2);
//
//            //canvas.drawText("00",width-5,height-100,paintTextUIPo);
//
//
////            //canvas.drawRect(75,height-90,width-75,height-40,mPaintInterCircle);
////            canvas.save();
////            canvas.clipRect(80,0,width-80,height-40);
////            //Log.d(TAG, "PrC_oDTrendLine_0A: "+odTimeData.timeDats.length);
////            for(int i = 0; i< quoOD.odTimeData.timeDats.length; i++)
////            {
////                //Log.d(TAG, "PrC_oDTrendLine_0B: "+odTimeData.timeDats[i].floatPosition);
////                float float_A= quoOD.odTimeData.timeDats[i].floatPosition;
////                canvas.drawLine(float_A,height-90,float_A,height-70,mPaintSecondLine);
////                String string_A= quoOD.odTimeData.timeDats[i].intH+":"+(quoOD.odTimeData.timeDats[i].intM<10?"0":"")+ quoOD.odTimeData.timeDats[i].intM;
////                canvas.drawText(string_A, float_A, height-50, mPaintText);
////                canvas.drawRect(float_A-10,500,float_A+10,height-120,mPaintSecondLine);
////            }
////            canvas.restore();
//        }
//    }
//    void oD_QuoDataPain(Canvas canvas,float floatU,float floatD){
//        canvas.save();
//        canvas.clipRect(80,90,width-80,height-110);
//        //Log.d(TAG, "PrC_oDTrendLine_0A: "+odTimeData.timeDats.length);
//        float float_Po=floatU-floatD;
//        float float_Po2=height-220;
//        float float_Lo=-1;
//        //]Log.w(TAG, "oD_QuoDataPainC0: "+"="+quoOD.odTimeData.timeDats.length +"="+quoOD.painMainData.fxDataF.length );
//        for(int i = quoOD.odTimeData.timeDats.length-1; i>=0; i--)
//        {
//
//            if(quoOD.painMainData.fxDataF[i]!=null)
//                {
//                    if(false)
//                        {
//                            float float_A= quoOD.odTimeData.timeDats[i].floatPosition;
//                            canvas.drawLine(float_A,height-90,float_A,height-70,mPaintSecondLine);
//                            String string_A= quoOD.odTimeData.timeDats[i].intH+":"+(quoOD.odTimeData.timeDats[i].intM<10?"0":"")+ quoOD.odTimeData.timeDats[i].intM;
//                            canvas.drawText(string_A, float_A, height-50, mPaintText);
//
//                            canvas.drawText(String.valueOf(quoOD.painMainData.fxDataF[i][0]), float_A, height-100, paintTextUIPo);
//                        }
//
//
//
//                    if(true)
//                        {
//                            float float_A= quoOD.odTimeData.timeDats[i].floatPosition;
//                            if(true)
//                                {
//                                float float_YU=float_Po2*((floatU-quoOD.painMainData.fxDataF[i][2])/float_Po);
//                                    float float_YD=float_Po2*((floatU-quoOD.painMainData.fxDataF[i][3])/float_Po);
//                                    canvas.drawLine(float_A,float_YU+100,float_A,float_YD+100,paintQuoColorD);
//                                }
//                            //Log.d(TAG, "PrC_oDTrendLine_0B: "+odTimeData.timeDats[i].floatPosition);
//
//                            float float_A2=(int)((float_Po/(floatU-quoOD.painMainData.fxDataF[i][0]))*100);
//                            float_A2= float_Po2*((floatU-quoOD.painMainData.fxDataF[i][0])/float_Po);
//                            if(float_Lo==-1)
//                                {
//                                    float_Lo=float_A2;
//                                    //mPaintSecondLine.setColor(Color.BLUE);
//                                }else {
//
//                            }
//                            Paint paint_A;
//                            if(float_Lo>=float_A2)
//                            {
//                                paint_A=paintQuoColorU;
//                                //  mPaintSecondLine.setColor(Color.RED);
//                            }else {
//                                paint_A=paintQuoColorD;
//                                //mPaintSecondLine.setColor(Color.BLUE);
//                            }
//                           // canvas.drawLine(float_A,height-90,float_A,height-70,mPaintSecondLine);
//
//                           // canvas.drawLine(float_A,float_Lo+100,float_A,float_A2+100,paint_A);
//                            canvas.drawRect(float_A-floatUIJd,float_Lo+100,float_A+floatUIJd,float_A2+100,paintQuoColorB);
//                            canvas.drawRect(float_A-floatUIJd,float_Lo+100,float_A+floatUIJd,float_A2+100,paint_A);
//                            canvas.drawLine(float_A-floatUIJd2,float_Lo+100,float_A+floatUIJd2,float_Lo+100,paint_A);
//                            String string_A= quoOD.odTimeData.timeDats[i].intH+":"+(quoOD.odTimeData.timeDats[i].intM<10?"0":"")+ quoOD.odTimeData.timeDats[i].intM;
//                            //canvas.drawText(string_A, float_A, height-50, mPaintText);
//                            //canvas.drawText(String.valueOf(quoOD.painMainData.fxDataF[i][0]), float_A, float_A2+100, paintTextUIPo);
//
//                            float_Lo=float_A2;
//                            if(i==0)
//                                {
//                                   // //]Log.w(TAG, "oD_QuoDataPainF0: "+"="+float_A2 +"="+((floatU-quoOD.painMainData.fxDataF[i][0])/float_Po)+"="+(floatU-quoOD.painMainData.fxDataF[i][0])+"="+float_Po );
//                                    //canvas.drawText(String.valueOf(BigDecimal.valueOf(float_A2)), float_A, (height-100), paintTextUIPo);
//                                }
//
//                            //canvas.drawRect(float_A-10,100+float_A2,float_A+10,height-120,mPaintSecondLine);
//                        }
//
//
//                }
//           // i=100;
//        }
//        float float_Nw= float_Po2*((floatU-quoOD.painMainData.fxNewD)/float_Po);
//        canvas.drawLine(0,float_Nw+100,width,float_Nw+100,mPaintSecondLine);
//        float float_Nw2= float_Po2*((floatU-quoOD.painMainData.fxNewU)/float_Po);
//        canvas.drawLine(0,float_Nw2+100,width,float_Nw2+100,mPaintSecondLine);
//        String string_F=String.valueOf(quoOD.painMainData.fxNewD);
//        if(string_F.length()>7)
//        {
//            string_F=string_F.substring(0,7);
//
//        }else if(string_F.length()<7)
//        {
//            for(int i=string_F.length();i<7;i++)
//            {
//                string_F+="0";
//            }
//        }
//
//        canvas.restore();
//        canvas.drawRect(width-80,float_Nw+88,width,float_Nw+112,paintQuoColorUN);
//        canvas.drawText(string_F,width-5,float_Nw+100+7,paintTextUIPoN);
//        string_F=String.valueOf(quoOD.painMainData.fxNewU);
//        if(string_F.length()>7)
//        {
//            string_F=string_F.substring(0,7);
//
//        }else if(string_F.length()<7)
//        {
//            for(int i=string_F.length();i<7;i++)
//            {
//                string_F+="0";
//            }
//        }
//        canvas.drawRect(width-80,float_Nw2+88,width,float_Nw2+112,paintQuoColorDN);
//        canvas.drawText(string_F,width-5,float_Nw2+100+7,paintTextUIPoN);
//    }
//    void oD_UILin(Canvas canvas,float floatU,float floatD){
//        float floatPoAA=floatU-floatD;
//        float floatPo2AA=height-220;
//        double dou_A=floatUIPoN;
//        dou_A=dou_A*200000;
//        float float_Dou1=floatUIPoN*200000;
//        int int_A=(int)(floatUIPoN*200000);
//        int int_DouA=(int)dou_A;
//        ////]Log.w(TAG, "oD_UILinDou01 "+"="+int_A +"="+int_DouA +"="+(dou_A-float_Dou1));
//       // //]Log.w(TAG, "oD_UILin01: "+"="+ int_A +"="+floatUIPoN +"="+floatU +"="+floatD);
//        float float_A2=floatUIPoM-floatUIPoN;
//        int int_B=int_A/10;
//        int int_C=((int)(floatD*100000))%10;
//        //]Log.w(this.toString(), "oD_UILinG0: "+"="+int_B );
//        float float_D=floatD;
//        int int_K=int_B/7;
//        if(int_K<=0)
//            {
//                int_K=1;
//            }
//        Path path = new Path();
//        if(int_C!=0)
//            {
//                float_D-=(int_C/100000F);
//                int_C=10-int_C;
//                float float_C=floatD+(int_C/100000F);
//                //]Log.w(TAG, "oD_UILin04"+"="+float_C +"="+int_B );
//            }
//        //]Log.w(this.toString(), "oD_UILinG1: "+"="+int_K  +"="+int_B);
//        for(int i=int_K;i<=int_B;i+=int_K)
//            {
//               float float_B2=float_D+(0.0001F*i);
//               // //]Log.w(TAG, "oD_UILin02: "+"="+ int_A +"="+int_B+"="+float_B2 +"="+(floatD+float_B2)+"="+floatD +"="+((floatD+float_B2)-floatD));
//               // float_B2=floatD+float_B2;
//                float float_B3=floatPo2AA*((floatU-float_B2)/floatPoAA);
//                String string_C=String.valueOf(float_B2);
//                if(string_C.length()>7)
//                {
//                    string_C=string_C.substring(0,7);
//
//                }else if(string_C.length()<7)
//                {
//                    for(int r=string_C.length();r<7;r++)
//                    {
//                        string_C+="0";
//                    }
//                }
//
////                    float float_A= quoOD.odTimeData.timeDats[i].floatPosition;
//////                canvas.drawLine(float_A,100,float_A,height-120,paintNnUI);
////                    path.reset();
////                    path.moveTo(5, float_B3+100);
////                    path.lineTo(width-9, float_B3+100);
////                    canvas.drawPath(path, paintNnUI);
//                canvas.drawText(string_C,width-5,float_B3+100,paintTextUIPo);
//
//            }
//        canvas.save();
//        canvas.clipRect(80,90,width-80,height-110);
//        ////]Log.w(TAG, "oD_UILinF0: "+"="+int_B +"="+floatU+"="+ floatD +"="+float_D);
//        for(int i=int_K;i<=int_B;i+=int_K)
//        {
//            float float_B2=float_D+(0.0001F*i);
//
//            float float_B3=floatPo2AA*((floatU-float_B2)/floatPoAA);
//
//            path.reset();
//            path.moveTo(5, float_B3+100);
//            path.lineTo(width-9, float_B3+100);
//            canvas.drawPath(path, paintNnUI);
//
//        }
//        canvas.restore();
//        //]Log.w(TAG, "oD_UILin03 "+"="+int_C +"="+floatD +"="+(int_C%10));
//        if(int_B!=0)
//            {
//
//            }
//
//
//
//    }
//
//
//
//
//    /**
//     * 举行绘制图表
//     */
//    public void PuC_ODTrend(){
//        quoOD.On_getQuoData();
//        //onGetQuoData.On_getQuoData();
//        invalidate();
//        //
//
//    }
//
//
////    public void setUIPo(float floatUIPoTAA,float floatUIPoMAA){
////        floatUIPoM=floatUIPoMAA;
////        floatUIPoT=floatUIPoTAA;
////        floatUIPoN=floatUIPoT;
////        if(false)
////            {
////
////        floatUIPoM=floatUIPoMAA;
////        floatUIPoT=floatUIPoTAA;
////        valueAnimator.cancel();
////        valueAnimator = ValueAnimator.ofFloat(floatUIPoN, floatUIPoT);
////        valueAnimator.setInterpolator(new LinearInterpolator());
////        //]Log.w(TAG, "setUIPo01: "+"="+floatUIPoN +"="+floatUIPoT );
////        valueAnimator.setDuration(500);
////        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
////            @Override
////            public void onAnimationUpdate(ValueAnimator animation) {
////
////                float currentValue = (Float) animation.getAnimatedValue();
////                // 获得改变后的值
////                floatUIPoN=currentValue;
////                //]Log.w(TAG, "setUIPo02: "+"="+floatUIPoN +"="+floatUIPoT );
////                invalidate();
////
////
////
////            }
////
////        });
////        valueAnimator.start();
////
////            }
////    }
//
//    /**
//     * 计算图表时间
//     */
////    private boolean PrC_calChartTime(){
////        boolean bool_rt=true;
////        float float_0A;//现在的集点离起点的长度
////        int int_A2;//集点离起点的多少分钟
////        Log.d(TAG, "CT001=MovePosition = [" + floatPositionNow + "]" +"="+floatEachLength);
////        float_0A=-floatPositionNow;//(floatPositionNow+(floatViewLength-70));//计算现在的集点离起点的长度
////        int_A2=(int)(float_0A/ floatEachLength);//计算出集点离起点的多少分钟
////
////        floatPositionGap=float_0A-(int_A2* floatEachLength);//计算多出来的距离
////        if(floatPositionGap>0) floatPositionGap=floatPositionGap-floatEachLength;
////        if(float_0A>0)int_A2++;
////
////        Log.d(this.toString(),"PrC_calChartTime_0A:="+int_A2 +"="+float_0A +"="+floatPositionGap);
////        if(quoOD.odTimeData.setTimeMove(int_A2))
////            {
////                //floatPositionGap=0;
////               // bool_rt=false;
////            }
////
////        if(false)
////            {
////                quoOD.odTimeData.timeODNow=new TimeOD(quoOD.odTimeData.timeODCr);
////                quoOD.odTimeData.timeODNow.PuC_addMinute(int_A2);//计算出移动后的时间
////            }
////
////        //Log.d(this.toString(),"PrC_calChartTime_0C:="+int_A2 +"="+odTimeData.timeODNow.intsA[3]+":"+odTimeData.timeODNow.intsA[4] +"="+odTimeData.timeODCr.intsA[3]+":"+odTimeData.timeODCr.intsA[4]);
////
////        return bool_rt;
////
////
////
////    }
////    /**
////     *上次的距离
////     */
////    float floatCal02, /**
////     * 移动距离
////     */
////    floatPo02, /**
////     * 多出来的距离
////     */
////    floatMs02;
////    boolean boolCal02,boolCal02B;
////    float floatCal02_C;
////    int intCal02,intCal02_B;
////    int intCal02_C;
//    public void setCharTimeNoze(){
//        floatMs02=floatEachLength;
//        // floatCal02=0;
//        intCal02=intCal02_B;
//    }
//    void calCharTime02(float floatAA){
//        float float_B=floatAA-floatPo02;//计算出上次和这次的移动距离
//        floatPo02=floatAA;//记录这次位置
//        boolean bool_B2;
//        if(floatCal02>0)
//            {
//                bool_B2=true;
//            }else {
//            bool_B2=false;
//        }
//        //]Log.w(TAG, "calCharTime02RT0: "+"="+(floatCal02+float_B)+"="+ floatCal02 +"="+ float_B +"="+floatAA );
//        floatCal02+=float_B;//将这次计算移动距离和上次多出来的距离加在一起
//
//        int int_A=((int)(floatCal02/floatEachLength));//计算这次移动距离是否超过时间距离
//        if(floatCal02>0)
//        {
//            if(!bool_B2)
//            {
//               int_A++;
//            }
//        }else {
//            if(bool_B2)
//            {
//                int_A++;
//            }
//            //bool_B2=false;
//        }
//        if(floatCal02<0)
//            {
//
//            }
//        if(int_A!=0)
//            {
//                //]Log.w(TAG, "calCharTime04: " +"="+floatCal02 +"="+int_A);
//            }
//
//
//        if(floatAA<=0)//计算多出来的距离
//        {
//            floatMs02=floatEachLength+(floatAA%floatEachLength);
//
//            //]Log.w(TAG, "calCharTime03: " +"="+floatAA +"="+floatMs02);
//        }else {
//            floatMs02=(floatAA%floatEachLength);
//            //]Log.w(TAG, "calCharTime03B: " +"="+floatAA +"="+floatMs02 +"="+(floatAA%floatEachLength));
//        }
//
//        //]Log.w(TAG, "calCharTime05: " +"="+floatMs02 +"="+floatAA +"="+floatCal02);
//
//        //if(floatCal02==0)return;
//        //]Log.w(TAG, "calCharTime06: " +"="+floatCal02 +"="+(floatCal02-(floatEachLength*int_A)));
//        floatCal02-=(floatEachLength*int_A);
//        TimeOD timeOD_F=new TimeOD(quoOD.odTimeData.timeODNow.PuC_getTimeLong());
//        timeOD_F.PuC_addMinute(-(int_A*quoOD.intTimeMode));
//        //quoOD.odTimeData.timeODNow.PuC_addMinute(-(int_A*quoOD.intTimeMode));
//
//        floatMs02=(floatEachLength+floatCal02);
//       // timeODCal02.PuC_addMinute(-int_A);
//        TimeOD timeODNews_A=quoOD.odTimeData.timeODNew;
//        intCal02-=int_A;
//        if(timeODNews_A.PuC_calTimeContrastMinu(timeOD_F)<2)//[0==,1=<,2=>]
//            {
//                timeOD_F=new TimeOD(timeODNews_A);
//                boolCal02B=true;
//                floatMs02=floatEachLength;
//               // floatCal02=0;
//                intCal02=intCal02_B;
//            }else {
//            boolCal02B=false;
//            floatCal02_C=0;
//
//        }
//        //]Log.w(TAG, "calCharTime02F0: "+"="+timeOD_F.intsA[2] +"="+ quoOD.odTimeData.timeODNow.intsA[2]);
//        quoOD.odTimeData.timeODNow.intsA=timeOD_F.intsA;
//        if(!timeOD_F.PuC_isTimeODSameDay(quoOD.odTimeData.timeODNow))
//        {
//            quoOD.odTimeData.setBoolTime(false);
//
//        }
//        //]Log.w(TAG, "calCharTime02RT1: "+"="+ floatCal02 +"="+floatMs02 +"="+(floatEachLength+floatCal02)+"="+floatEachLength);
//        PuC_calTimeDat();
//        PuC_getODData();
////        //]Log.w(TAG, "calCharTime08: " +"="+timeODCal02.intsA[4]+"="+intCal02 +"="+ intCal02_B);
////        //]Log.w(TAG, "calCharTime07: " +"="+boolCal02B +"="+timeODCal02.intsA[4]+"="+floatMs02 +"="+ floatCal02);
////        //]Log.w(TAG, "calCharTime01: " +"="+timeODCal02.intsA[3]+"="+timeODCal02.intsA[4] +"="+ int_A +"="+floatAA +"="+floatCal02);
//
//
//
//    }
//
////    /**
////     * 计算刷新图表绘制时间点与坐标
////     */
////    public void PuC_calTimeDat() {
////        quoOD.setCustomizeData(floatViewLength,floatMs02,floatEachLength);
////        if(false)
////            {
////                //]Log.w(TAG, "PuC_calTimeDatC0: "+"="+  quoOD.odTimeData.timeDats.length);
////
////                quoOD.odTimeData.timeDats[0] = quoOD.odTimeData.new TimeDat(quoOD.odTimeData.timeODNow.intsA[3],quoOD.odTimeData.timeODNow.intsA[4]);
////                int[] ints_A = new int[2];
////                quoOD.odTimeData.intLast = 0;
////                quoOD.odTimeData.intT=0;
////                float float_position = floatViewLength - 150;
////                float float_Each =floatMs02;//- (floatPositionGap >= 0 ? floatEachLength : 0);
////                ints_A[0] = quoOD.odTimeData.timeDats[0].intH;
////                ints_A[1] = quoOD.odTimeData.timeDats[0].intM;//+2;
////                Log.d(this.toString(), "PuC_calTimeDatG0: " + float_Each);
////                for (int i = 0; i < quoOD.odTimeData.timeDats.length; i++) {
////
////                    if (ints_A[1] < 0) {
////                        ints_A[1] = 59;
////                        ints_A[0] -= 1;
////                        if (ints_A[0] < 0) {
////                            ints_A[0] = 23;
////                            quoOD.odTimeData.intLast++;
////                            quoOD.odTimeData.setBoolTime(false);
////                            quoOD.odTimeData.intT=i;//quoOD.odTimeData.timeDats.length-i;
////                        }
////
////                    }
////
////                    quoOD.odTimeData.timeDats[i] = quoOD.odTimeData.new TimeDat(ints_A[0], ints_A[1]);
////                    // float_position = floatViewLength - 10;
////                    quoOD.odTimeData.timeDats[i].floatPosition = ((float_position - (floatEachLength * i)) + float_Each);
////                    //]Log.w(TAG, "PuC_calTimeDatC1: "+"="+ints_A[0] +"="+ints_A[1]+"="+  quoOD.odTimeData.timeDats[i].floatPosition);
////                    ints_A[1] -= 1;
////                    if(i==quoOD.odTimeData.timeDats.length-1)
////                        {
////                            //]Log.w(TAG, "PuCDat01: "+"="+ints_A[0] +"="+ints_A[1]+"="+ float_Each);
////                        }
////
////                }
////            }
////        if(false) {
////
////
////            quoOD.odTimeData.timeDats[0] = quoOD.odTimeData.new TimeDat(quoOD.odTimeData.timeODNow.intsA[3],quoOD.odTimeData.timeODNow.intsA[4]);
////            int[] ints_A = new int[2];
////            quoOD.odTimeData.intLast = 0;
////            quoOD.odTimeData.intT=0;
////            float float_position = floatViewLength - 50;
////            float float_Each = floatPositionGap;//- (floatPositionGap >= 0 ? floatEachLength : 0);
////            ints_A[0] = quoOD.odTimeData.timeDats[0].intH;
////            ints_A[1] = quoOD.odTimeData.timeDats[0].intM;
////            Log.d(this.toString(), "floatViewLength: " + floatViewLength);
////            for (int i = 0; i < quoOD.odTimeData.timeDats.length; i++) {
////
////                if (ints_A[1] < 0) {
////                    ints_A[1] = 59;
////                    ints_A[0] -= 1;
////                    if (ints_A[0] < 0) {
////                        ints_A[0] = 23;
////                        quoOD.odTimeData.intLast++;
////                        quoOD.odTimeData.intT=quoOD.odTimeData.timeDats.length-i;
////                    }
////
////                }
////
////                quoOD.odTimeData.timeDats[i] = quoOD.odTimeData.new TimeDat(ints_A[0], ints_A[1]);
////                // float_position = floatViewLength - 10;
////                quoOD.odTimeData.timeDats[i].floatPosition = ((float_position - (floatEachLength * i)) - float_Each);
////                ints_A[1] -= 1;
////            }
////            // Log.d(this.toString(), "PuC_calTimeDat_1A0: "+timeDats[0].intH +"="+timeDats[0].intM +"="+timeDats[0].floatPosition);
////            // Log.d(this.toString(), "PuC_calTimeDat_1A1: "+timeDats[3].intH +"="+timeDats[3].intM +"="+timeDats[3].floatPosition);
////            // Log.d(this.toString(), "PuC_calTimeDat_1A2: "+timeDats[9].intH +"="+timeDats[9].intM +"="+timeDats[9].floatPosition);
////
////        }
////    }
//    /**
//     *获得图表数据线数据
//     */
//    public void PuC_getODData(){
//
//        quoOD.On_getQuoData();
//
//    }



    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>






//    Calendar calender_A_Pco_001;
//    /**
//     * 走势数据表
//     */
//    public Cla_002 Cla002_A;
//    /**
//     * 走势数据表
//     */
//    class Cla_002
//    {
//        public  int int_A;
//        public ArrayList<Cla_002A> ClaaA;
//        public long long_A,long_B;
//
//
//    }
//    /**
//     * 走势数据
//     */
//    class Cla_002A{
//        public float float_A;
//        public String string_A;
//
//    }
//    /**
//     *绘制图表背景
//     */
//    private void PrC_oDChartBackdrop(Canvas canvas){
//        canvas.drawRect(0,0,width,height,mPaintSecondLine);
//        canvas.drawLine(80,height-80,width-80,height-80,mPaintSecondLine);
//        canvas.drawLine(width-80,height-80,width-80,80,mPaintSecondLine);
//
//    }
//    float[] floats_A;
//    /**
//     *绘制图表时间
//     */
//    private void PrC_000(Canvas canvas){
//
//
//    }
//
//    /**
//     *初始化走势图数据
//     *
//     * @param longAA the long aa
//     */
//    public void PCo_001(long longAA){
//        calender_A_Pco_001 = Calendar.getInstance();
//        calender_A_Pco_001.setTimeInMillis(longAA);
//        int minute = calender_A_Pco_001.get(Calendar.MINUTE);
//        int second = calender_A_Pco_001.get(Calendar.SECOND);
//        Cla002_A=new Cla_002();
//
//
//    }
//    Cla_002  PCo_001A(long longAA){
//        Cla_002 cla_002A=new Cla_002();
//        float float_0A= floatViewLength - floatPositionNow;//计算现在的集点离起点的长度
//        int int_A2=(int)(float_0A/ floatEachLength);//计算出集点离起点的多少分钟
//        float float_0A2=float_0A-(int_A2* floatEachLength);
//        int[] ints_A=OD_001C2((int_A2));
//        String stringB="";
//        ints_A[1]+=1;
//        cla_002A.ClaaA= new ArrayList<>();
//        for(int i=1;i<10;i++)
//        {
//            Cla_002A cla_002A1=new Cla_002A();
//            ints_A[1]-=1;
//            if( ints_A[1]<0)
//            {
//                int int_A=(Math.abs(ints_A[1]/60))+1;
//                ints_A[1]+=(int_A*60);
//                ints_A[0]-=int_A;
//                if(ints_A[0]<0)
//                {
//                    ints_A[0]=(24+ints_A[0]);
//                }
//            }
//            stringB=(ints_A[0]+":"+((ints_A[1]>=10)?ints_A[1]:"0"+ints_A[1]));
//            float float_0B2=(floatViewLength -(floatEachLength *i))-float_0A2+(float_0A2>=0? floatEachLength :0)-10;
//            cla_002A1.string_A=stringB;
//            cla_002A1.float_A=float_0B2;
//            cla_002A.ClaaA.add(cla_002A1);
//        }
//        cla_002A.int_A=cla_002A.ClaaA.size();
//        return cla_002A;
//    }
//
//    public void PCo_002(){
//
//    }
//
//
//
//
//
//
//    /**
//     * Od 001 data.
//     */
//    void OD_001Data(){
////        floatEachLength =(width-80)/10F;
////        floatViewLength=width-80;
//        Calendar c = Calendar.getInstance();
//        od_001Data_intsA=new int[2];
//        // format1 = new SimpleDateFormat("HH:mm");
//        od_001Data_intsA[0] = c.get(Calendar.HOUR_OF_DAY);
//        od_001Data_intsA[1] = c.get(Calendar.MINUTE);
//    }
//
//
//
//    /**
//     * 根据计算出时间点
//     *
//     * @param intAA the int aa
//     * @return the int [ ]
//     */
//     int[] OD_001C2(int intAA){
//        int[] ints_A=od_001Data_intsA.clone();
//        ints_A[1]+=intAA;
//        if( ints_A[1]>=60)
//        {
//            int int_A=ints_A[1]/60;
//            ints_A[1]-=(60*int_A);
//            ints_A[0]+=int_A;
//            if(ints_A[0]>=24)
//            {
//                ints_A[0]-=(ints_A[0]/24)*24;
//            }
//        }else if( ints_A[1]<0)
//        {
//            int int_A=(Math.abs(ints_A[1]/60))+1;
//            ints_A[1]+=(int_A*60);
//            ints_A[0]-=int_A;
//            if(ints_A[0]<0)
//            {
//                ints_A[0]=(24+ints_A[0]);
//            }
//        }
//        return ints_A;
//    }
//
//
//    /**
//     * 绘制走势线
//     *
//     * @param canvas the canvas
//     */
//    void OD_001A(Canvas canvas){
//        float float_0A;//现在的集点离起点的长度
//        float float_0A2;//多出来的距离
//        int int_A2;//集点离起点的多少分钟
//
//       int[] ints_A=new int[2];//
//       float_0A= floatViewLength - floatPositionNow;//计算现在的集点离起点的长度
//        Log.d("dATA","dATA_2:="+"="+float_0A +"="+ floatViewLength +"="+ floatPositionNow);
//       int_A2=(int)(float_0A/ floatEachLength);//计算出集点离起点的多少分钟
//       float_0A2=float_0A-(int_A2* floatEachLength);//计算多出来的距离
//        //Log.d("dATA","dATA_3.1:="+"="+floatViewLength +"="+ floatPositionNow +"="+int_A2);
//        //Log.d("dATA","dATA_3:="+"="+float_0A +"="+ floatViewLength +"="+floatPositionNow);
//        Log.d("dATA","dATA_3.2:="+float_0A2);
//        ints_A=OD_001C2((int_A2));
//       // Log.d("dATA", "OD_001A: "+float_0A+"="+int_A2+"="+ints_A[0]+":"+ints_A[1]);
//        String stringB="";
//        ints_A[1]+=1;
//        for(int i=1;i<10;i++)
//        {
//            ints_A[1]-=1;
//            if( ints_A[1]<0)
//            {
//                int int_A=(Math.abs(ints_A[1]/60))+1;
//                ints_A[1]+=(int_A*60);
//                ints_A[0]-=int_A;
//                if(ints_A[0]<0)
//                {
//                    ints_A[0]=(24+ints_A[0]);
//                }
//            }
//            stringB=(ints_A[0]+":"+((ints_A[1]>=10)?ints_A[1]:"0"+ints_A[1]));
//            float float_0B2=(floatViewLength -(floatEachLength *i))-float_0A2+(float_0A2>=0? floatEachLength :0)-10;
//            canvas.drawLine(float_0B2,height-90,float_0B2,height-70,mPaintSecondLine);
//            canvas.drawText(stringB, float_0B2, height-50, mPaintText);
//        }
//
//        if(cla_001ListA.size()!=0)
//            {
//                if(cla_001ListA.get(0).floatsA.length!=0)
//                    {
//                        boolean bool_A=true;
//                        float float_A=0;
//                        float float_B=0;
//                        for(int i=0;i<cla_001ListA.get(0).floatsA.length;i++)
//                        {
//                            if(cla_001ListA.get(0).floatsA[0].length>=3)
//                            {
//                                if(bool_A)
//                                {
//                                    bool_A=false;
//                                    float_A=cla_001ListA.get(0).floatsA[i][3];
//                                    float_B=cla_001ListA.get(0).floatsA[i][3];
//                                }
//                                if(float_A>cla_001ListA.get(0).floatsA[i][3])
//                                {
//                                    float_A=cla_001ListA.get(0).floatsA[i][3];
//
//                                }
//                                if(float_B<cla_001ListA.get(0).floatsA[i][3])
//                                {
//                                    float_B=cla_001ListA.get(0).floatsA[i][3];
//                                }
//                                if(float_A>cla_001ListA.get(0).floatsA[i][4])
//                                {
//                                    float_A=cla_001ListA.get(0).floatsA[i][4];
//
//                                }
//                                if(float_B<cla_001ListA.get(0).floatsA[i][4])
//                                {
//                                    float_B=cla_001ListA.get(0).floatsA[i][4];
//                                }
//                            }
//
//                        }
//                        float[] floats_A=new float[4];
//                        float floar_E=height-220;
//                        float floar_E2= floatEachLength *0.35F;
//                        float floar_E3= floatEachLength *0.2F;
//                        float floar_F=float_B-float_A;
//                        float floar_D=(floar_E/floar_F);
//                        ////]Log.w(this.toString(), "OK7=("+float_B+")+("+float_A+")+("+floar_F);
//                        for(int i=0;i<cla_001ListA.get(0).floatsA.length;i++)
//                        {
//                            if (cla_001ListA.get(0).floatsA[0].length>=3)
//                            {
//                                floats_A=cla_001ListA.get(0).floatsA[i];
//                                for(int e=0;e<floats_A.length;e++)
//                                    {
//                                        floats_A[e]=floar_D*(floats_A[e]-float_A);
//                                    }
//
//                               // //]Log.w(this.toString(), "OK7=("+float_B+")+("+float_A+")+("+floar_F);
//                                canvas.drawLine(100+(floatEachLength *i),(height-100)-floats_A[0],100+(floatEachLength *i),(height-100)-floats_A[2],mPaintSecondLine);
//                                canvas.drawLine(100+(floatEachLength *i),(height-100)-floats_A[0],100+(floatEachLength *i),(height-100)-floats_A[3],mPaintSecondLine);
//
//                                canvas.drawLine(100+(floatEachLength *i)-floar_E3,(height-100)-floats_A[0],100+(floatEachLength *i)-floar_E3,(height-100)-floats_A[1],mPaintSecondLine);
//                                canvas.drawLine(100+(floatEachLength *i)+floar_E3,(height-100)-floats_A[0],100+(floatEachLength *i)+floar_E3,(height-100)-floats_A[1],mPaintSecondLine);
//                                canvas.drawLine(100+(floatEachLength *i)-floar_E2,(height-100)-floats_A[0],100+(floatEachLength *i)+floar_E2,(height-100)-floats_A[0],mPaintSecondLine);
//                                canvas.drawLine(100+(floatEachLength *i)-floar_E3,(height-100)-floats_A[1],100+(floatEachLength *i)+floar_E3,(height-100)-floats_A[1],mPaintSecondLine);
//
//                            }
//                        }
//
//                }
//            }
//
//    }
//
//
//    void OD_002(Canvas canvas)
//    {
//        canvas.drawRect(floatsA[0],100,floatsA[0]+50,150,mPaintSecondLine);
//        canvas.drawText(stringA,width/2,height/2,mPaintTextB);
//    }





}
