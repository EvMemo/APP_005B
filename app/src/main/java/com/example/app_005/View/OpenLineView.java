package com.example.app_005.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.app_005.Class.SkillLineNema;

public class OpenLineView extends View {
    private int width;//设置高
    private int height;//设置高
    SkillLineNema skillLineNema;
    float[][] floatsData;
    Paint paintMaxText;
    Paint paintQioLine;
    Paint paintUILine;
    public OpenLineView(Context context) {
        super(context);
    }

    public OpenLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init_Paint();

    }
    void init_Paint(){
        paintMaxText = new Paint();
        paintMaxText.setAntiAlias(true);//消除锯齿
        paintMaxText.setColor(Color.BLACK);
        paintMaxText.setTextAlign(Paint.Align.CENTER);
        paintMaxText.setTextSize(24);

        paintQioLine=new Paint();
        paintQioLine.setColor(Color.BLACK);//设置画笔颜色
        paintQioLine.setStyle(Paint.Style.FILL);
        paintQioLine.setStrokeWidth(2);//


        paintUILine=new Paint();
        paintUILine.setAntiAlias(true);//消除锯齿
        paintUILine.setColor(Color.GRAY);//设置画笔颜色
        paintUILine.setStyle(Paint.Style.STROKE);
        paintUILine.setStrokeWidth(3);//设置宽度
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
        if(true)
            {
                canvas.drawRoundRect(4,5,width-4,height-5,5,5,paintUILine);

                canvas.drawText("0,00001",60,30,paintMaxText);
                canvas.drawText("0,00001",160,30,paintMaxText);
                canvas.drawLine(width-80,30,width-80,height-30,paintQioLine);

            }
    }
    boolean boolOpen;
    void setOpenViewBool(Boolean boolOpen_A){
        boolOpen=boolOpen_A;
        if(boolOpen)
            {
                this.setVisibility(View.VISIBLE);
            }else {
            this.setVisibility(View.INVISIBLE);
        }
    }
    void setData(SkillLineNema skillLineNema_A,float[][] floats_Data,float[] floats_Pos){

    }
    void ccLinePaint(SkillLineNema skillLineNema){
        switch(skillLineNema){
            case Recommend_All:
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                setVisibility(INVISIBLE);
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                return true;

        }
        return super.onTouchEvent(event);
    }
}
