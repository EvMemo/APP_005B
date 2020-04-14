package com.example.app_005.Tool.WindowView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.app_005.Tool.MyWindowView;

public class WindowTabView extends View {
    Paint paintUI;
    Paint paintUI2;

    private int width;//设置高
    private int height;//设置高
    public  int intNo;

    MyWindowView myWindowView;
    public WindowTabView(Context context) {
        super(context);
    }

    public WindowTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintUI=new Paint();
        paintUI.setColor(Color.WHITE);

        paintUI2=new Paint();
        paintUI2.setColor(Color.GRAY);
        intNo=0;
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
        canvas.drawRoundRect(0,0,width,height,20,20,paintUI);
        float flaot_A=0F;
        float flaot_B=10F;
        for(int i=0;i<3;i++)
            {

                canvas.drawRoundRect(flaot_B,10+flaot_A,width-flaot_B,10+flaot_A+100F,10,10,paintUI2);
                flaot_A+=110;
            }
        //canvas.drawRoundRect(10,0,width,height,20,20,paintUI);
        //canvas.drawRoundRect(0,0,width,height,20,20,paintUI);
    }
    public void setMyWinDowView(MyWindowView myWinDowView_A){
        myWindowView=myWinDowView_A;
        touchTab(0);
    }
    void touchTab(int int_A){
        if(myWindowView!=null)
            {
                myWindowView.cutTabView(int_A);
            }
    }
    void touchView(MotionEvent event) {
        float flaot_A=0F;
        float flaot_B=10F;
        float float_X=event.getX();
        float float_Y=event.getY();
        //]Log.w(this.toString(), "touchView: T0"+"="+float_X +"="+float_Y );
        if(float_X>10&&float_X<width-10)
            {
                for(int i=0;i<3;i++)
                {
                    if(float_Y>(10+flaot_A)&&float_Y<(10+flaot_A+100F))
                        {
                            touchTab(i);
                        break;
                        }

                    flaot_A+=110;

                }
            }


    }

    long longTouchTime;
    float floatTouchX,floatTouchY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //]Log.w(this.toString(), "WindowTabView.onTouchEvent: "+"="+event. getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_DOWN:
                floatTouchX=event.getX();
                floatTouchY=event.getY();
                longTouchTime=System.currentTimeMillis();
                return true;
            case MotionEvent.ACTION_UP:
                if(System.currentTimeMillis()<longTouchTime+500&&(spacing(floatTouchX,floatTouchY,event.getX(),event.getY())<10))
                {
                    touchView(event);

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
