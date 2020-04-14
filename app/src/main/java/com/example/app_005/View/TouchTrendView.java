package com.example.app_005.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TouchTrendView extends View {
    public boolean boolFlag;
    int[] viewA;
    int[] viewB;
    int[] viewC;
    CustomizeTrend customizeTrend;
    ColorDuoView colorDuoView;
    float floatPoiW;
    float floatPoiH;
    public TouchTrendView(Context context) {
        super(context);
    }

    public TouchTrendView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int int_width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int int_height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(int_width, int_height);//设置宽和高
        int[] location_B = new int[2] ;
        getLocationOnScreen(location_B);//获取在整个屏幕内的绝对坐标
        setLocationData(new int[]{int_width,int_height,location_B[0],location_B[1]});//设置宽和高
        initPoiData();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path_A = new Path();
        //]Log.w(this.toString(), "TouchonDrawD0: "+"="+floatPoiW +"="+floatPoiH);
        //path_A.addRect(0+floatPoiW, 0+floatPoiH, 50+floatPoiW, 50+floatPoiH, Path.Direction.CW);
        if(true)
            {
                canvas.drawLine(floatPoiW-15,floatPoiH,floatPoiW+15,floatPoiH,paint);
                canvas.drawLine(floatPoiW,floatPoiH-15,floatPoiW,floatPoiH+15,paint);
            }
        if(floatPoiH<1044)
            {
                if(viewB!=null)
                    {
                        canvas.save();
                        //]Log.w(this.toString(), "TouchTrendViewonDrawG0: "+"="+(viewB[0]-80) +"="+(viewB[1]-100));
                        canvas.clipRect(80,80,viewB[0]-80,viewB[1]-100);
                        canvas.drawLine(floatPoiW+30,floatPoiH,viewA[0],floatPoiH,paint);
                        canvas.drawLine(0,floatPoiH,floatPoiW-30,floatPoiH,paint);
                        canvas.drawLine(floatPoiW,0,floatPoiW,floatPoiH-30,paint);
                        canvas.drawLine(floatPoiW,viewA[1],floatPoiW,floatPoiH+30,paint);
                        canvas.restore();

                    }


            }


       // path_A.offset(floatPoiW,floatPoiH);  //
        //canvas.drawPath(path_A,paint);
        //canvas.drawRect(floatPoiW -100, floatPoiH -100, floatPoiW +100, floatPoiH +100,paint);
    }
    Path path = new Path();
    Paint paint;

    void initPoiData(){
        floatPoiW =(viewA[0]/2F);
        floatPoiH =(viewA[1]/2F);
        path = new Path();
        path.addRect(0, 0, 400, 400, Path.Direction.CW);
        paint=new Paint();
        paint.setAntiAlias(true);//消除锯齿
        paint.setColor(Color.BLACK);//设置画笔颜色
        //paintNnUI.setAlpha(220);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2.5F);//设置宽度
        path.offset(100,0);  //
        path.offset(100,0);  //将path向右平移
        path.offset(0,0);  //将path向右平移
    }

    public void initData(CustomizeTrend customizeTrend_A, ColorDuoView colorDuoView_A){
        customizeTrend=customizeTrend_A;

        colorDuoView=colorDuoView_A;
        customizeTrend.initLocationData(this);
        colorDuoView.initLocationData(this);
    }
    void setTouchMove(float int_W,float int_H){


        floatPoiW -=(floatDownW-int_W);
        floatPoiH -=(floatDownH-int_H);
        floatDownW=int_W;
        floatDownH=int_H;

        if(floatPoiW <0)
            {
                floatPoiW =0;
            }
        if(floatPoiH <0)
        {
            floatPoiH =0;
        }
        if(floatPoiW >viewA[0])
        {
            floatPoiW =viewA[0];
        }
        if(floatPoiH >viewA[1])
        {
            floatPoiH =viewA[1];
        }
        //]Log.w(this.toString(), "setTouchMoveF0: "+"="+ int_W+"="+ int_H+"="+ floatPoiW+"="+ floatPoiH);
        if(floatPoiH<1044)
        {
            if(viewB!=null)
            {
               // customizeTrend.cusTreDrawTool.setTouchTrePo(floatPoiH);


            }


        }
        invalidate();


    }
    public void setLocationData(int[] int_Po){
        viewA=int_Po;
    }
    public void setCustomizeLocationData(int[] int_Po){
        viewB=int_Po;
    }
    public void setLineLocationData(int[] int_Po){
        viewC=int_Po;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {


        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                //Log.i(this.toString(), "("+event.getX()+"="+width+")_A_("+event.getY()+"="+height+")");
                break;
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                //Log.i(this.toString(), "dispatchTouchEvent:(ACTION_UP) ");
                break;
        }

        return super.dispatchTouchEvent(event);
    }
    float floatDownW;
    float floatDownH;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_MOVE:
                setTouchMove(event.getX(),event.getY());

                return true;
            case MotionEvent.ACTION_DOWN:
                floatDownW=event.getX();
                floatDownH=event.getY();

                return true;
            case MotionEvent.ACTION_UP:
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                return true;

        }
        // getParent().requestDisallowInterceptTouchEvent(false);
        return super.onTouchEvent(event);
    }
}
