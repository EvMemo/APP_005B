package com.example.app_005.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.app_005.Class.SkillLineNema;
import com.example.app_005.Interface.OnGetQuoData;
import com.example.app_005.Tool.LineDrawTool;
import com.example.app_005.Tool.LineTrendCenter;

public class ColorDuoView extends View {
    private int width;//设置高
    private int height;//设置高
    private int widthLocation;//设置高
    private int heightLocation;//设置高
    Paint paintText;
    Paint paintTextName;
    Paint paintColorBlue;
    Paint paintColorRed;
    String stringLineName;
    String stringLineData;
    float  floatLine;
    float floatWm;
    float floatHm;
    float floatW;
    float floatH;
    public LineTrendCenter lineTrendCenter;
    public LineDataPaint lineDataPaint;
    OnGetQuoData onGetQuoData;
    public ColorDuoView(Context context) {
        super(context);
    }

    public ColorDuoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    void init(){
        lineDataPaint=new LineDataPaint(26);
        lineDataPaint.ccLinePaint();
        stringLineName="EMA20";
        stringLineData="0.00001";
        floatLine=0.5F;

        paintText = new Paint();
        paintText.setAntiAlias(true);//消除锯齿
        paintText.setColor(Color.BLACK);
        //paintText.setStyle(Paint.Style.STROKE);//设置为空心
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setTextSize(30);

        paintColorBlue= new Paint();
        paintColorBlue.setAntiAlias(true);//消除锯齿
        paintColorBlue.setColor(Color.parseColor("#6DDDF6"));//设置画笔颜色
        paintColorBlue.setStyle(Paint.Style.FILL);//设置为空心
        paintColorBlue.setStrokeWidth(3);//

        paintColorRed= new Paint();
        paintColorRed.setAntiAlias(true);//消除锯齿
        paintColorRed.setColor(Color.parseColor("#FD476C"));//设置画笔颜色
        paintColorRed.setStyle(Paint.Style.FILL);//设置为空心
        paintColorRed.setStrokeWidth(3);//设置宽度

        //绘制文字的画笔
        paintText = new Paint();
        paintText.setAntiAlias(true);//消除锯齿
        paintText.setColor(Color.BLACK);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setTextSize(43);
        paintText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));

        paintTextName = new Paint();
        paintTextName.setAntiAlias(true);//消除锯齿
        paintTextName.setColor(Color.BLACK);
        paintTextName.setTextAlign(Paint.Align.CENTER);
        paintTextName.setTextSize(25);
        paintTextName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));






    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        floatW=(width-50)/4;
        floatH=(height-32)/3;
        setMeasuredDimension(width, height);//设置宽和高
        int[] location = new int[2] ;
        getLocationInWindow(location); //获取在当前窗口内的绝对坐标
        int[] location_B = new int[2] ;
        getLocationOnScreen(location_B);//获取在整个屏幕内的绝对坐标
        //]Log.w(this.toString(), "onMeasureG0B: "+"="+location[0] +"="+location[1]+"="+location_B[0] +"="+location_B[1]);
        widthLocation=location_B[0];
        heightLocation=location_B[1];
        setLocationData();
        lineDataPaint.ccLinePaint();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    public void setLineClass(LineTrendCenter lineClass_A){
        lineTrendCenter=lineClass_A;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // drawMinKOK(canvas);
        drawLine(canvas);
        if(lineTrendCenter!=null)
            {
                switch(lineTrendCenter.lineMode){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }

            }


        if(false)
            {
                float float_A=5F;
                floatLine=1F;
                float float_B=(width)-(float_A*4);
                canvas.drawRoundRect(0,0,float_A*2+10,height,float_A,float_A,paintColorBlue);
                canvas.drawRoundRect(width-(float_A*2+10),0,width,height,float_A,float_A,paintColorRed);
                canvas.drawRect(float_A*2,0,(float_A*2)+float_B*floatLine,height,paintColorBlue);
                canvas.drawRect((float_A*2)+float_B*floatLine,0,width-(float_A*2),height,paintColorRed);
                canvas.drawText(stringLineName,width/2,height*0.90F,paintTextName);
                canvas.drawText(stringLineData,width/2,height*0.46F,paintText);
            }

    }
    void drawLine(Canvas canvas_A){
        lineDataPaint.draw(canvas_A);
    }
    public void setOnGetQuoData(OnGetQuoData onGetQuoData_A){
        onGetQuoData=onGetQuoData_A;
    }
    public void startDraw(){
        if(onGetQuoData!=null)
            {
                onGetQuoData.On_GetLineData(lineDataPaint.boolMode,lineDataPaint.skillLineNemas);
            }

    }
    void drawMinKOK(Canvas canvas_A){
        int int_I=0;
        int int_E=0;
        switch(lineDataPaint.intMode){
            case 0:
                int_I=4;
                int_E=3;
                break;
            case 1:
                int_I=2;
                int_E=2;
                break;
            case 2:
                int_I=2;
                int_E=1;
                break;

            default:
                break;
        }
        float float_W=(width-50)/int_I;
        float float_H=(height-32)/int_E;
        int int_P=lineDataPaint.intPage;
        int int_N=lineDataPaint.intNum;
        for (int e = 0; e < int_E; e++) {
            for (int i = 0; i < int_I; i++) {

                if (int_N >= lineDataPaint.intNum) {
                    break;
                }
                //canvas.drawRect(10+(float_W*i)+(10*i),8+(float_H*e)+(8*e),10+(float_W*(i+1))+(10*i),8+(float_H*(e+1))+(8*e),paintColorBlue);
                float[] floats_W = new float[]{(10 + (float_W * i) + (10 * i)), (10 + (float_W * (i + 1)) + (10 * i))};
                float[] floats_H = new float[]{(8 + (float_H * e) + (8 * e)), (8 + (float_H * (e + 1)) + (8 * e))};
                drawKOK(canvas_A, floats_W, floats_H, 0.5F, String.valueOf(int_N));
                int_N++;

            }
        }
    }
    void drawKOK(Canvas canvas_A,float[] float_W,float[] float_H,float float_P){
        float float_A=5F;
        //floatLine=1F;
        float float_B=(float_W[1]-float_W[0])-(float_A*4);
        canvas_A.drawRoundRect(float_W[0],float_H[0],float_W[0]+float_A*2+10,float_H[1],float_A,float_A,paintColorBlue);
        canvas_A.drawRoundRect(float_W[1]-(float_A*2+10),float_H[0],float_W[1],float_H[1],float_A,float_A,paintColorRed);
        canvas_A.drawRect(float_W[0]+float_A*2,float_H[0],float_W[0]+(float_A*2)+(float_B*float_P),float_H[1],paintColorBlue);
        canvas_A.drawRect(float_W[0]+(float_A*2)+float_B*float_P,float_H[0],float_W[1]-(float_A*2),float_H[1],paintColorRed);
        canvas_A.drawText(stringLineName,float_W[0]+((float_W[1]-float_W[0])/2),float_H[0]+((float_H[1]-float_H[0])*0.90F),paintTextName);
        canvas_A.drawText(String.valueOf(intLineN),float_W[0]+((float_W[1]-float_W[0])/2),float_H[0]+((float_H[1]-float_H[0])*0.46F),paintText);
    }
    void drawKOK(Canvas canvas_A,float[] float_W,float[] float_H,float float_P ,String string_A){
        float float_A=5F;
        //floatLine=1F;
        float float_B=(float_W[1]-float_W[0])-(float_A*4);
        canvas_A.drawRoundRect(float_W[0],float_H[0],float_W[0]+float_A*2+10,float_H[1],float_A,float_A,paintColorBlue);
        canvas_A.drawRoundRect(float_W[1]-(float_A*2+10),float_H[0],float_W[1],float_H[1],float_A,float_A,paintColorRed);
        canvas_A.drawRect(float_W[0]+float_A*2,float_H[0],float_W[0]+(float_A*2)+(float_B*float_P),float_H[1],paintColorBlue);
        canvas_A.drawRect(float_W[0]+(float_A*2)+float_B*float_P,float_H[0],float_W[1]-(float_A*2),float_H[1],paintColorRed);
        canvas_A.drawText(stringLineName,float_W[0]+((float_W[1]-float_W[0])/2),float_H[0]+((float_H[1]-float_H[0])*0.90F),paintTextName);
        canvas_A.drawText(string_A,float_W[0]+((float_W[1]-float_W[0])/2),float_H[0]+((float_H[1]-float_H[0])*0.46F),paintText);
    }

    void updateLineData(SkillLineNema[] skillLineNemas_A, float[][][] floats_Data,float[] floats_Pos){
        lineDataPaint.setViewPos(floats_Pos);
        lineDataPaint.setData(skillLineNemas_A,floats_Data);
        invalidate();
    }

    OpenLineView openLineView;
    void setOpenView(OpenLineView openView_A){
        openLineView=openView_A;
    }
    boolean boolOpenView;
    void openLineView(int int_A){
        boolOpenView=true;
        openLineView.setVisibility(View.VISIBLE);
        intLineN=int_A;
    }
    void cloneLineView(){
        boolOpenView=false;
        openLineView.setVisibility(View.INVISIBLE);
    }
    int intLineN;
    void moveLineView(int int_A){
        int int_B=intLineN+int_A;
        if(int_B >= 0 && int_B <lineDataPaint.intNum)
            {
                intLineN+=int_A;
            }

    }
    //int intZoomMode;
    void zoomLineLayout(int int_A) {
        int int_B = (lineDataPaint.intMode + int_A);
        if(int_B<0)
            {
                int_B=0;
            }else if(int_B>2){
            int_B=2;
        }

        if(int_B!=lineDataPaint.intMode){
            lineDataPaint.setIntMode(int_B);
            int int_N = 0;
            switch (lineDataPaint.intMode) {
                case 0:
                    int_N = 12;

                    break;
                case 1:
                    int_N = 4;
                    break;
                case 2:
                    int_N = 2;
                    break;
                default:
                    break;
            }
            for (int i = 1; i <= ((lineDataPaint.intNum / int_N) + ((lineDataPaint.intNum % int_N) == 0 ? 0 : 1)); i++) {
                if(intLineN<(int_N*i))
                    {
                        intLineN=(int_N*(i-1));
                        //]Log.w(this.toString(), "zoomLineLayoutG2: "+"="+intLineN +"="+(int_N*i) );
                        lineDataPaint.setIntPage(i-1);
                        lineDataPaint.ccLinePaint();
                        break;
                    }

            }
            //]Log.w(this.toString(), "zoomLineLayoutG0: "+"="+ lineDataPaint.intMode +"="+int_A +"="+lineDataPaint.intPage +"="+((lineDataPaint.intNum / int_N) + ((lineDataPaint.intNum % int_N) == 0 ? 0 : 1)) +"="+intLineN);

        }
    }
    //int intMoveLayoutPage;
    void moveLineLayout(int int_A){
        int int_N = 0;
        switch (lineDataPaint.intMode) {
            case 0:
                int_N = 12;

                break;
            case 1:
                int_N = 4;
                break;
            case 2:
                int_N = 2;
                break;
            default:
                break;
        }
        int int_N2=(lineDataPaint.intNum / int_N)+((lineDataPaint.intNum % int_N) == 0 ? 0 : 1);
        int int_B=lineDataPaint.intPage+int_A;
        //]Log.w(this.toString(), "moveLineLayoutG1: "+"="+int_N2 +"="+ int_B +"="+lineDataPaint.intPage +"="+int_A);
        if(int_B>=0&&int_B<int_N2)
            {
                //lineDataPaint.intPage=int_B;
                lineDataPaint.setIntPage(int_B);

                intLineN=int_B*int_N;
                lineDataPaint.ccLinePaint();
                //]Log.w(this.toString(), "moveLineLayoutG0: "+"="+int_A +"="+  lineDataPaint.intMode +"="+int_N +"="+int_B);
            }


    }
    //int intLineNum=26;
    int intLinePage=3;
    float floatMoveZoom;
    int intDownNum;
    void startViewZoom(MotionEvent event){
        floatMoveZoom=spacing(event);
    }
    /**
     *缩放
     */
    void setViewZoom(MotionEvent event){
        float float_mod2 =  spacing(event)-floatMoveZoom;
        int int_Mlu = (int) (float_mod2 / 100);
       // //]Log.w(this.toString(), "setViewZoom: "+"="+int_Mlu +"="+float_mod2);
        if (intDownNum != int_Mlu) {
            int int_Cka = 0;
            if (intDownNum > int_Mlu) {
                for (int i = intDownNum; i > int_Mlu; i--) {
                    int_Cka--;
                }
            } else {
                for (int i = intDownNum; i < int_Mlu; i++) {
                    int_Cka++;
                }
            }
            zoomLineLayout(int_Cka);
            invalidate();
            intDownNum = int_Mlu;
        }

    }
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {


        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                ////]Log.w(this.toString(), "("+event.getX()+"="+width+")_A_("+event.getY()+"="+height+")");
                break;
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                ////]Log.w(this.toString(), "dispatchTouchEvent:(ACTION_UP) ");
                break;
        }

        return super.dispatchTouchEvent(event);
    }


    float floatMoveBH;
    void startMoveB(float int_H){
        floatMoveBH=int_H;
    }
    float flaotMoveDa;
    boolean ccMoveB(float float_H){
        //]Log.w(this.toString(), "ccMoveBF0: "+"="+float_H );
        float float_A=floatMoveBH-float_H;
        if(Math.abs(float_A)>=height/4F)
            {
                if(float_A>0)
                    {
                        moveLineLayout(1);
                    }else {
                    moveLineLayout(-1);
                }
                floatMoveBH=float_H;
                invalidate();
                return true;
            }else {
            return false;
        }
        //flaotMoveDa=float_A;

       // //]Log.w(this.toString(), "ccMoveB: "+"="+flaotMoveDa +"="+float_H);
    }
    void clickOpenView(float[] floats_L){
        //]Log.w(this.toString(), "clickOpenViewH0: "+"="+ (floats_L[0]-floats_L[2]) +"="+(floats_L[1]-floats_L[3]));
        //if((floats_L[0]-floats_L[2])>30F)return;
       // if((floats_L[1]-floats_L[3])>30F)return;
        int int_N = 0;
        int int_I=0;
        int int_E=0;
        switch (lineDataPaint.intMode) {
            case 0:
                int_N = 12;
                int_I=4;
                int_E=3;
                break;
            case 1:
                int_N = 4;
                int_I=2;
                int_E=2;
                break;
            case 2:
                int_N = 2;
                int_I=2;
                int_E=1;
                break;
            default:
                break;
        }
        int int_B=lineDataPaint.intPage;
        int int_N2=(lineDataPaint.intNum / int_N)+((lineDataPaint.intNum % int_N) == 0 ? 0 : 1);
        float float_W=(width-50)/int_I;
        float float_H=(height-32)/int_E;
        for(int e=0;e<int_E;e++)
            {
                float[] floats_H = new float[]{(8 + (float_H * e) + (8 * e)), (8 + (float_H * (e + 1)) + (8 * e))};
                if(floats_H[0]<=floats_L[3]&&floats_H[1]>=floats_L[3])
                    {
                        for(int i=0;i<int_I;i++) {
                            float[] floats_W = new float[]{(10 + (float_W * i) + (10 * i)), (10 + (float_W * (i + 1)) + (10 * i))};
                            if(floats_W[0]<=floats_L[2]&&floats_W[1]>=floats_L[2])
                            {
                                //]Log.w(this.toString(), "clickOpenViewJ0: " +"="+e +"="+i );
                                if(floats_H[0]<=floats_L[1]&&floats_H[1]>=floats_L[1])
                                {
                                    if(floats_W[0]<=floats_L[0]&&floats_W[1]>=floats_L[0])
                                    {

                                        int int_P=((e*int_I)+(i))+(lineDataPaint.intPage*int_N);
                                        //]Log.w(this.toString(), "clickOpenViewJ1: " +"="+int_P + "="+e +"="+i );
                                        openLineView(int_P);
                                    }
                                }

                            }
                    }


                }
            }



    }

    int intPointerNum;
    float floatOpenW;
    float floatOpenH;
    long longOpen;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_MOVE:
               // //]Log.w(this.toString(), "ColoDuoonTouchEvent: `"+"="+ intPointerNum);

                if(intPointerNum==2)
                    {
                        setViewZoom(event);

                    }else {
                    if(ccMoveB(event.getY())){
                        longOpen=-1;
                    }
                }

                return true;
            case MotionEvent.ACTION_DOWN:
                intPointerNum++;
                intDownNum=0;
                startMoveB(event.getY());
                floatOpenW=event.getX();
                floatOpenH=event.getY();
                longOpen=System.currentTimeMillis();
                //zoomLineLayout(1);
                //startViewZoom(event);
                return true;
            case MotionEvent.ACTION_UP:
                intPointerNum=0;
                if(longOpen!=-1)
                    {
                        //]Log.w(this.toString(), "KOHJIO0: " +"="+(System.currentTimeMillis()-longOpen));
                        if((System.currentTimeMillis()-longOpen)<300)
                            {
                                clickOpenView(new float[]{floatOpenW,floatOpenH,event.getX(),event.getY()});
                            }

                    }
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                longOpen=-1;
                startViewZoom(event);
                //]Log.w(this.toString(), "ColoDuoonTouchEvent: `"+"="+ intPointerNum);
                intPointerNum++;

                return true;
            case MotionEvent.ACTION_POINTER_UP:
                return true;

        }
        // getParent().requestDisallowInterceptTouchEvent(false);
        return super.onTouchEvent(event);
    }
    TouchTrendView touchTrendView;
    public void initLocationData(TouchTrendView touchTrendView_A){
        touchTrendView=touchTrendView_A;
        touchTrendView.setLineLocationData(new int[]{width,height,widthLocation,heightLocation});
    }
    public void setLocationData(){
        if(touchTrendView!=null)
        {
            touchTrendView.setLineLocationData(new int[]{width,height,widthLocation,heightLocation});
        }
    }
    class LineDataPaint{
        boolean boolMode;
        int intNum;
        int intMode;
        int intPage;
        SkillLineNema[] skillLineNemas;
        LineDrawTool lineDrawTool;
        LinePaint[] linePaints;
        float[] floatsPos;
        public LineDataPaint(int intNum_A){
            lineDrawTool=new LineDrawTool();
            intNum=intNum_A;
            intMode=1;
            intPage=0;
            //ccLinePaint();
        }
        public void draw(Canvas canvas_A){
            if(boolMode)
                {
                    for(int i=0;i<linePaints.length;i++)
                    {
                        linePaints[i].drawOne(canvas_A);
                    }
                }else {
                for(int i=0;i<linePaints.length;i++)
                {
                    linePaints[i].draw(canvas_A,floatsPos);
                }
            }

        }
        public void ccDrawCanv(){
            int int_I=0;
            int int_E=0;
            int int_N2=0;
            switch(intMode){
                case 0:
                    int_N2=12;
                    int_I=4;
                    int_E=3;
                    break;
                case 1:
                    int_N2=4;
                    int_I=2;
                    int_E=2;
                    break;
                case 2:
                    int_N2=2;
                    int_I=2;
                    int_E=1;
                    break;

                default:
                    break;
            }
            float float_W=(width-50)/int_I;
            float float_H=(height-32)/int_E;
            int int_P=intPage;
            int int_N=int_N2*intPage;
            int int_O=0;
            //]Log.w(this.toString(), "ccDrawCanvH0: "+"="+int_O +"="+linePaints.length +"="+ int_N +"="+lineDataPaint.intNum);
            for (int e = 0; e < int_E; e++) {
                for (int i = 0; i < int_I; i++) {

                    if (int_N >= lineDataPaint.intNum) {
                        break;
                    }
                    //canvas.drawRect(10+(float_W*i)+(10*i),8+(float_H*e)+(8*e),10+(float_W*(i+1))+(10*i),8+(float_H*(e+1))+(8*e),paintColorBlue);
                    float[] floats_W = new float[]{(10 + (float_W * i) + (10 * i)), (10 + (float_W * (i + 1)) + (10 * i))};
                    float[] floats_H = new float[]{(8 + (float_H * e) + (8 * e)), (8 + (float_H * (e + 1)) + (8 * e))};
                    linePaints[int_O].setFloatsWH(new float[]{floats_W[0],floats_H[0],floats_W[1],floats_H[1]});
                    int_O++;
                    int_N++;
                }
            }

        }
        public void setData(SkillLineNema[] skillLineNemas_A,float[][][] floats_Data){
            //]Log.w(this.toString(), "NKJIUsetDataJ0: "+"="+linePaints.length +"="+skillLineNemas_A.length +"="+floats_Data.length );
            for(int i=0;i<linePaints.length;i++)
                {
                    for(int e=0;e<skillLineNemas_A.length;e++)
                        {

                                if(linePaints[i].skillLineNema==skillLineNemas_A[e])
                                {
                                    linePaints[i].setData(floats_Data[e]);
                                }
                        }


                }
        }
        public void setViewPos(float[] floatsPos_A){
            floatsPos=floatsPos_A;
        }
        void setBoolMode(Boolean bool_Mode){
            boolMode=bool_Mode;

        }
        void setIntMode(int int_Mode){
            intMode=int_Mode;
        }
        void setIntPage(int int_Page){
            intPage=int_Page;
        }
        void ccLinePaint(){
            int int_N=0;
            int int_P=intPage;
            int int_E;
            switch (intMode) {
                case 0:
                    setBoolMode(true);
                    int_N = 12;
                    break;
                case 1:
                    setBoolMode(false);
                    int_N = 4;
                    break;
                case 2:
                    setBoolMode(false);
                    int_N = 2;
                    break;
                default:
                    break;
            }
            int int_B=int_N*(int_P);
            int int_B2=int_N*(int_P+1);
            if(int_B2>intNum)
                {
                    int_B2=intNum;
                }
            if(false)
                {
                    SkillLineNema[] skillLineNemas_A=new SkillLineNema[1];
                    linePaints=new LinePaint[1];
                    skillLineNemas_A[0]=SkillLineNema.getSkill(0);
                    linePaints[0]=new LinePaint(skillLineNemas_A[0]);
                    skillLineNemas=skillLineNemas_A;
                }else {
                SkillLineNema[] skillLineNemas_A=new SkillLineNema[int_B2-int_B];
                linePaints=new LinePaint[skillLineNemas_A.length];
                //]Log.w(this.toString(), "ccLinePaintG0: "+"="+skillLineNemas_A.length +"="+int_B +"="+int_B2 );
                for(int i=0;i<linePaints.length;i++)
                {
                    skillLineNemas_A[i]=SkillLineNema.getSkill(i+int_B);
                    linePaints[i]=new LinePaint(skillLineNemas_A[i]);
                }
                skillLineNemas=skillLineNemas_A;
            }
            ccDrawCanv();


        }

        class LinePaint{
            SkillLineNema skillLineNema;
            float[] floatsWH;
            float[][] floatsData;
            LineDrawTool.LineDraw lineDraw;
            public LinePaint(SkillLineNema skillLineNema_A){
                skillLineNema=skillLineNema_A;
                lineDraw=lineDrawTool.getLineDraw(skillLineNema_A);
            }
            public void setData(float[][] floatsData_A){
                floatsData=floatsData_A;
                String string_A=skillLineNema.toString()+"{"+floatsData_A.length+"}";
                for(int i=0;i<floatsData_A.length;i++)
                    {
                        if(floatsData_A[i]!=null)
                            {
                                string_A+="="+floatsData_A[i].length;
                            }else {
                            string_A+="=null";
                        }

                    }
                //]Log.w(this.toString(), "setDataGTF0: "+"="+ string_A);
            }

            public void setFloatsWH(float[] floatsWH) {
                this.floatsWH = floatsWH;
            }

            public void draw(Canvas canvas_A, float[] floats_Pos){
                lineDraw.draw(canvas_A,floatsWH,floatsData,floats_Pos,skillLineNema.name());

            }
            public void drawOne(Canvas canvas_A){
                //]Log.w(this.toString(), "OJIdrawOneO0: "+"="+(floatsWH!=null?floatsWH[0] +"="+floatsWH[1]:skillLineNema.name()) );
                lineDraw.drawOne(canvas_A,floatsWH,floatsData,skillLineNema.name());

            }
        }

    }




}
