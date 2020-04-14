package com.example.app_005.Tool.WindowView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class WindowTextView extends View {

    private int width;//设置高
    private int height;//设置高
    Paint paintUI;
    public WindowTextView(Context context) {
        super(context);
    }

    public WindowTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintUI=new Paint();
        paintUI.setColor(Color.WHITE);
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

    public class WindowTextDrawClass{

        float floatTouchDownY;
        long longTouchTime;
        float floatTouchX,floatTouchY;

        float floatTouchLashH;
        float floatTouchNow;

        boolean boolDrawOK;
        boolean boolViewOK;

        public void Draw(Canvas canvas){

        }
        void ccViewH(){

        }
        public void setAnimFloat(float float_A){

        }
        void moveView(float float_A){

        }
        float repsMoveView(float float_Move) {
            return 0;
        }
        public void touchView(MotionEvent event){

        }

        public boolean onTouchEvent(MotionEvent event) {
            return false;
        }
        private float spacing(float float_X1,float float_Y1,float float_X2,float float_Y2) {
            float x = float_X1 - float_X2;
            float y = float_Y1 - float_Y2;
            return (float) Math.sqrt(x * x + y * y);
        }



    }
    public class WindowTextDrawNews extends WindowTextDrawClass{
        Paint paintUI;
        public WindowTextDrawNews(){
            paintUI=new Paint();
            paintUI.setColor(Color.WHITE);
        }
        @Override
        public void Draw(Canvas canvas){
            canvas.drawRoundRect(0,0,width,height,20,20,paintUI);


        }
        @Override
        void ccViewH(){

        }
        @Override
        public void setAnimFloat(float float_A){

        }
        @Override
        void moveView(float float_A){

        }
        @Override
        float repsMoveView(float float_Move) {
            return 0;
        }
        @Override
        public void touchView(MotionEvent event){

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return false;
        }
    }
}
