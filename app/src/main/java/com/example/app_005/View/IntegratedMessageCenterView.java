package com.example.app_005.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class IntegratedMessageCenterView extends View {

    Paint paintUI;
    Paint paintUIB;
    Paint paintP;
    private int width;//设置高
    private int height;//设置高
    boolean boolUpInteg;
    IntegratedNewsView integratedNewsView;
    //View[] views;
    int intTabNum;
    public IntegratedMessageCenterView(Context context) {
        super(context);
    }

    public IntegratedMessageCenterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintUI=new Paint();
        paintUI.setColor(Color.parseColor("#FFEBEBEB"));//FF5E5E5E//FFEBEBEB


        paintUIB=new Paint();
        paintUIB.setColor(Color.BLACK);
        paintUIB.setAlpha(20);

        paintP=new Paint();
        paintP.setColor(Color.BLACK);//FF5E5E5E
        paintP.setAlpha(100);
        initValueAnimator();
        intTabNum=-1;
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
        canvas.drawRect(0,0,width,height,paintP);
        canvas.drawRoundRect(46,height-100-(604*floatAnimDayUI),width-46,height+100,30,30,paintUIB);
        canvas.drawRoundRect(50,height-100-(600*floatAnimDayUI),width-50,height+100,30,30,paintUI);
    }
    public void setIntegratedNewsView(IntegratedNewsView integratedNewsView_A){
        //views=new View[1];
        integratedNewsView=integratedNewsView_A;
       // views[0]=integratedNewsView_A;
    }
    public void updateFloatAnimDayUI(){
        integratedNewsView.setfloatAnimDayUI(floatAnimDayUI);
    }
    public void openView(int int_A){
        if(true)
            {
                if(intTabNum!=-1)
                    {
                        if(intTabNum!=int_A)
                            {
                                intTabNum=int_A;
                                if(true||intTabNum!=0)
                                    {

                                        //integratedNewsView.setVisibility(View.INVISIBLE);
                                        integratedNewsView.setVisibility(View.VISIBLE);
                                        integratedNewsView.setIntDrawM(intTabNum);
                                    }
                            }

                    }else{
                    intTabNum=int_A;
                    boolUpInteg=true;
                    setVisibility(View.VISIBLE);
                    if(true||intTabNum!=0)
                    {
                        integratedNewsView.setVisibility(View.VISIBLE);
                        integratedNewsView.setIntDrawM(intTabNum);
                    }
                    valueAnimator.cancel();
                    startValueAnimator();
            }
        }

    }
    public void closeView(){
        intTabNum=-1;
        boolUpInteg=false;
        setVisibility(View.INVISIBLE);
        integratedNewsView.setVisibility(View.INVISIBLE);
        integratedNewsView.closeIntegrated();
        valueAnimator.cancel();
        floatAnimDayUI=0;
    }
    ValueAnimator valueAnimator;
    float floatAnimDayUI;
    void  initValueAnimator(){

        valueAnimator = ValueAnimator.ofFloat(0F, 1);
        valueAnimator.setDuration(300);
    }
    private void startValueAnimator() {

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 动画更新过程中的动画值，可以根据动画值的变化来关联对象的属性，实现属性动画
                floatAnimDayUI = (float) animation.getAnimatedValue();
                updateFloatAnimDayUI();
                //Log.d("ValueAnimator", "动画值：" + floatAnimDayUI);
                invalidate();
            }
        });
        valueAnimator.start();
    }
    void  closeValueAnimator(){

        valueAnimator.cancel();
    }
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:

                return true;
            case MotionEvent.ACTION_MOVE:

                return true;
            case MotionEvent.ACTION_UP:
                if(event.getY()<(height-705))
                    {
                        closeView();
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
