package com.example.app_005.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.app_005.MainActivity;
import com.example.app_005.R;

public class MainTabView extends View {
    Paint paintUI;
    Paint paintUI2;
    Paint mBitPaint;
    Paint mBitPaintB;
    Paint paintTextNew;
    Paint paintNewC;
    private int width;//设置高
    private int height;//设置高
    Bitmap[] bitmaps;
    MainActivity mainActivity;
    int intTabNum;
    float floatUpH;
    int[] intNewNum;
    public MainTabView(Context context) {
        super(context);
    }

    public MainTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintUI=new Paint();
        paintUI.setColor(Color.parseColor("#FC8D8D8D"));//FF5E5E5E

        paintUI2=new Paint();
        paintUI2.setColor(Color.WHITE);//FF5E5E5E

        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);

        paintTextNew=new Paint();
        paintTextNew.setColor(Color.BLACK);//FF5E5E5E
        paintTextNew.setAntiAlias(true);//消除锯齿
        paintTextNew.setTextAlign(Paint.Align.CENTER);
        paintTextNew.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        paintTextNew.setTextSize(22);

        paintNewC=new Paint();
        paintNewC.setColor(Color.RED);//FF5E5E5E
        paintNewC.setAntiAlias(true);//消除锯齿

        mBitPaintB = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaintB.setFilterBitmap(true);
        mBitPaintB.setDither(true);
        mBitPaintB.setAlpha(100);
        Resources res = getResources();
        bitmaps=new Bitmap[3];
        bitmaps[0]= BitmapFactory.decodeResource(res, R.mipmap.ic_assessment_white_36dp);
        bitmaps[1]= BitmapFactory.decodeResource(res, R.mipmap.ic_announcement_white_36dp);
        bitmaps[2]= BitmapFactory.decodeResource(res, R.mipmap.ic_date_range_white_36dp);
        intNewNum=new int[]{0,0,0};
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
        canvas.drawRoundRect(30-1,0,width-30+1,height,30,30,paintUI2);

        canvas.drawRect(30-1,height/2F,width-30+1,height,paintUI2);
      canvas.drawRoundRect(30,2,width-30,height,30,30,paintUI);
       // //]Log.w(this.toString(), "MainTabView: T0"+"="+width +"="+height );
       canvas.drawRect(30,height/2F,width-30,height,paintUI);
        for(int i=0;i<3;i++) {
            if (i == intTabNum) {

                canvas.drawBitmap(bitmaps[i], (width / 4F) * (i + 1) - (bitmaps[0].getWidth() / 2F), height / 2F - (bitmaps[i].getHeight() / 2F), mBitPaint);
            } else {
                canvas.drawBitmap(bitmaps[i], (width / 4F) * (i + 1) - (bitmaps[0].getWidth() / 2F), height / 2F - (bitmaps[i].getHeight() / 2F), mBitPaintB);
            }
            if (intNewNum[i]!=0) {

                canvas.drawCircle((width / 4F) * (i + 1) - (bitmaps[0].getWidth() / 2F)+5, height / 2F - (bitmaps[i].getHeight() / 2F) + 60, 17, paintNewC);
                if(intNewNum[i]>99)
                    {
                        canvas.drawText("--", (width / 4F) * (i + 1) - (bitmaps[0].getWidth() / 2F)+5, height / 2F - (bitmaps[i].getHeight() / 2F)+67, paintTextNew);

                    }else {
                    canvas.drawText(String.valueOf(intNewNum[i]), (width / 4F) * (i + 1) - (bitmaps[0].getWidth() / 2F)+5, height / 2F - (bitmaps[i].getHeight() / 2F)+67, paintTextNew);

                }

            }

        }
//        canvas.drawBitmap(bitmaps[0],(width/4F)*1-(bitmaps[0].getWidth()/2F),height/2F-(bitmaps[0].getHeight()/2F),mBitPaintB);
//        canvas.drawBitmap(bitmaps[1],(width/4F)*2-(bitmaps[0].getWidth()/2F),height/2F-(bitmaps[0].getHeight()/2F),mBitPaint);
//        canvas.drawBitmap(bitmaps[2],(width/4F)*3-(bitmaps[0].getWidth()/2F),height/2F-(bitmaps[0].getHeight()/2F),mBitPaintB);
    }
    public void setMainActivity(MainActivity mainActivity_A){
        mainActivity=mainActivity_A;
        intTabNum=0;
    }
    public void updateIntegratedNewNum(int intMode_A,int intNum_A){
        intNewNum[intMode_A]=intNum_A;
        invalidate();

    }
    public void openIntegratedView(int int_A){
        if(mainActivity!=null) {
            mainActivity.openIntegratedView(int_A);
        }
    }
    public void closeIntegratedView(){
        if(mainActivity!=null) {
            mainActivity.closeIntegratedView();
        }
    }
    void setTabNum(int int_A){
        if(mainActivity!=null) {
            closeIntegratedView();
            mainActivity.setViewPagerNum(int_A);
            intTabNum=int_A;
            invalidate();
        }
    }
    void touchView(float float_X,float float_Y){
        //]Log.w(this.toString(), "Zloat_XtouchView:TA "+"="+float_X +"="+float_Y);
        for(int i=1;i<=3;i++)
            {
                if(float_X>(((width/4F)*i)-30)&&float_X<(((width/4F)*i)+30))
                {
                    //]Log.w(this.toString(), "(float float_XtouchView:T0 "+"="+i);
                    setTabNum(i-1);
                }
            }
    }
    void touchUpView(float float_X,float float_Y){
        //]Log.w(this.toString(), "Zloat_XtouchView:TA "+"="+float_X +"="+float_Y);
        for(int i=1;i<=3;i++)
        {
            if(float_X>(((width/4F)*i)-30)&&float_X<(((width/4F)*i)+30))
            {
                //]Log.w(this.toString(), "touchUpView:T0 "+"="+i);
                openIntegratedView(i-1);
            }
        }
    }

    float floatTouchX,floatTouchY;
    long longTouchTime;
    boolean boolTouch;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                floatTouchX=event.getX();
                floatTouchY=event.getY();
                longTouchTime=System.currentTimeMillis();
                boolTouch=true;
                return true;
            case MotionEvent.ACTION_MOVE:
                float float_G=event.getY()-floatTouchY;
                if(boolTouch&&float_G<-100F)
                    {
                        boolTouch=false;
                        touchUpView(floatTouchX,floatTouchY);

                    }
                return true;
            case MotionEvent.ACTION_UP:
                if(boolTouch&&System.currentTimeMillis()<longTouchTime+500&&(spacing(floatTouchX,floatTouchY,event.getX(),event.getY())<10))
                {

                    touchView(event.getX(),event.getY());
                }
                return true;
            default:
                break;
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
