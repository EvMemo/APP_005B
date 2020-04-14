package com.example.app_005.View;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 *  标题视图
 */
public class MyTitleView extends View {
    /**
     * 最新时间
     */
    private long newTime;
    /**
     * 集点时间
     */
    private long nowTime;
    public MyTitleView(Context context) {
        super(context);
    }

    public MyTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 修改最新时间
     */
    public void PuC_modificationNewTime(long longAA){

    }
    /**
     * 修改集点时间
     */
    public void PuC_modificationNowTime(long longAA){

    }

    /**
     * 获取最新时间
     *
     * @return the long
     */
    public long PuC_getNewTime(){
        return newTime;

    }

    /**
     *  获取集点时间
     *
     * @return the long
     */
    public long PuC_getNowTime(){
        return nowTime;
    }

    private void PrC_001(){

    }
}
