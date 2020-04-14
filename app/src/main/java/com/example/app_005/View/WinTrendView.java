package com.example.app_005.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class WinTrendView extends View {
    private int width;//设置高
    private int height;//设置高
    Paint paintA;
    public float[][] floatsData;
    public WinTrendView(Context context) {
        super(context);
    }

    public WinTrendView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(width, height);//设置宽和高
        initData();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    void initData(){
        paintA=new Paint();
        paintA.setAntiAlias(true);//消除锯齿
        paintA.setColor(Color.BLACK);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        startDraw(canvas);
    }
    void startDraw(Canvas canvas){
        canvas.drawLine(30,height-30,width-30,height-30,paintA);
        canvas.drawLine(width-30,height-30,width-30,30,paintA);
    }
    public void reqDraw(){

    }
    public void setData(float[][] floatsDataAA){
        floatsData=floatsDataAA;
    }
//    public class TrendData{
//        public int intMax;
//        public float[] flPos;
//        public float[][] flData;
//        public TrendData(int intMaxAA){
//            intMax=intMaxAA;
//            flPos=new float[intMax];
//            flData=new float[intMaxAA][];
//        }
//    }


}
