package com.example.app_005.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.widget.Scroller;

import java.util.logging.Handler;

public class MyFXTabs extends View {
    private int width;//设置高
    private int height;//设置高
    boolean bool_A;
    int intMode;
    private Scroller mScroller;
    private VelocityTracker velocityTracker;
    private int mMaxFlintVelocity, mMinFlintVelocity;

    public MyFXTabs(Context context) {
        super(context);
    }

    public MyFXTabs(Context context, AttributeSet attrs) {
        super(context, attrs);
        intMode=0;
        mScroller = new Scroller(getContext(), null, true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mMaxFlintVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMinFlintVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        //]Log.w("MyFXTabs", "onMeasure: ="+width +"="+height );

        setMeasuredDimension(width, height);//设置宽和高
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint_A=new Paint();
        RectF rectF_A=new RectF();
        paint_A.setColor(Color.argb(200,0,0,0));
        paint_A.setAntiAlias(true);//消除
        //canvas.drawRoundRect(0,(height/2F)-18,(width),(height/2F)+18,24,24,paint_A);
        //canvas.drawRoundRect(0,(0)+50,(width),36+50,24,24,paint_A);
        // canvas.drawRoundRect(0,(height)-36-50,(width),height-50,24,24,paint_A);

        Paint paint_B=new Paint();
        paint_B.setColor(Color.WHITE);
        paint_B.setAntiAlias(true);//消除锯齿
        // canvas.drawCircle(width/2F,height/2F,30,paint_A);
        paint_B.setTextAlign(Paint.Align.CENTER);
        paint_B.setTextSize(30);

        //canvas.drawText("12",width/2F,(height/2F)+10,paint_B);
        //canvas.drawText("0",width/2F,(50+18)+10,paint_B);
        //canvas.drawText("24",width/2F,((height-50)-18)+10,paint_B);
        canvas.drawRect(0,0,width,height,paint_A);
        //canvas.drawCircle(width/2F,height/2F,5,paint_B);

        //canvas.drawCircle(width/2F,30,5,paint_B);
        // canvas.drawCircle(width/2F,height-30,5,paint_B);
        if(bool_A)
            {
                canvas.drawCircle(float_TouchX_A,float_TouchY_A,20,paint_B);
            }
        if(true)
            {
                float float_B=width/2F;
                float float_B2= height /2F;
            switch(intMode){
                case 0:
                    canvas.drawRoundRect(0,float_B2-200,20,float_B2+200,0,20,paint_B);
                    break;
                case 1:
                    canvas.drawRoundRect(0,float_B2-200,width-20,float_B2+200,0,20,paint_B);
                    break;
                case 2:
                    canvas.drawRoundRect(0,float_B2-200+100,width-20,float_B2+200+100,0,20,paint_B);
                    break;
                case 3:
                    canvas.drawRoundRect(0,float_B2-200-100,width-20,float_B2+200-100,0,20,paint_B);
                    break;
                default:
                    break;
            }
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


    float float_TouchX_A,float_TouchY_A;
    float float_Touch_B=60;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //]Log.w("MyFXTabs", "onTouchEvent: ="+event.getX() +"="+event.getY() );
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_MOVE:
                if(!bool_A)
                    {
                        if((event.getX()-float_TouchX_A)>46)
                        {
                            //]Log.w("MyFXTabs", "onTouchEvent(((: ="+(event.getX()-float_TouchX_A) +"=A" );
                            PrC_setModeTouch(1);
                        }else if((event.getY()-float_TouchY_A)>(100)) {
                            PrC_setModeTouch(2);
                            //]Log.w("MyFXTabs", "onTouchEvent(((: ="+(event.getY()-float_TouchY_A) +"=B" );
                        }else if((float_TouchY_A-event.getY())>(100)) {
                            PrC_setModeTouch(3);
                            //]Log.w("MyFXTabs", "onTouchEvent(((: ="+(event.getY()-float_TouchY_A) +"=C" );
                        }else if((float_TouchX_A-event.getX())>(float_Touch_B/3F)){
                            PrC_setModeTouch(0);
                            //]Log.w("MyFXTabs", "onTouchEvent(((: ="+(float_TouchX_A-event.getX()) +"=D" );
                        }
                    }

                return true;
            case MotionEvent.ACTION_DOWN:
                bool_A=true;
                float_TouchX_A=event.getX();
                float_TouchY_A=event.getY();
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }

                return true;
            case MotionEvent.ACTION_UP:
                bool_A=false;
                return true;
            default:
                break;
        }
        return super.onTouchEvent(event);

    }
    /**
     *
     */
    private void PrC_C001(){
        Animation animation_A=new AlphaAnimation(1,0);
        animation_A.setDuration(3000);
        OvershootInterpolator interpolator_A=new OvershootInterpolator();
        animation_A.setInterpolator(interpolator_A);
        animation_A.start();
    }
    /**
     *
     */
    private void PrC_setModeTouch(int intAA){
        intMode=intAA;
        velocityTracker.computeCurrentVelocity(1000, mMaxFlintVelocity);
     //   mScroller.fling((int)floatsA[0], (int)floatsA[1], xVelocity, yVelocity, -9999999, 99999, 0, height-50);
        bool_A=true;
        invalidate();

    }
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {

//            floatsA[0]=mScroller.getCurrX();
//            PrC_setMovePosition(floatsA[0]);
//            stringA=String.valueOf(floatsA[0]);
//            floatsA[1]=mScroller.getCurrY();
            ////]Log.w("OKL1", "computeScroll: "+mScroller.getStartX()+"="+ mScroller.getStartY());
            ////]Log.w("OKL0", "computeScroll: "+mScroller.getCurrX()+"="+ mScroller.getCurrY());
            ////]Log.w("OKL1", "computeScroll: "+mScroller.getFinalX()+"="+ mScroller.getFinalY());
            //scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
