package com.example.app_005.Tool.WindowView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.app_005.Tool.MyWindowView;

public class WindowLyButtonView extends View {

    private int width;//设置高
    private int height;//设置高
    Paint paintUI;
    MyWindowView myWindowView;
    public WindowLyButtonView(Context context) {
        super(context);
    }

    public WindowLyButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintUI=new Paint();
        paintUI.setColor(Color.BLUE);
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
    }
    public void setMyWindowView(MyWindowView myWindowView_A){
        myWindowView=myWindowView_A;
    }
    public void onTouch(MotionEvent event_A){
        if(myWindowView!=null)
        {
            myWindowView.ccWindowButTouch();
        }


    }
    float floatDownX;
    float floatDownY;
    float floatDownTime;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

                return true;
            case MotionEvent.ACTION_DOWN:
                floatDownX=event.getX();
                floatDownY=event.getY();
                floatDownTime=System.currentTimeMillis();
                return true;
            case MotionEvent.ACTION_UP:
                float float_X=event.getX();
                float float_Y=event.getY();
                onTouch(event);

                return true;
        }
        return super.onTouchEvent(event);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        //Log.w(this.toString(), "DOKDonTouchEventD1: "+"="+event.getAction() );
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