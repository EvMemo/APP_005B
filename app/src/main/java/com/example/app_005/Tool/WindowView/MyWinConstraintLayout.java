package com.example.app_005.Tool.WindowView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyWinConstraintLayout extends ConstraintLayout {
    public MyWinConstraintLayout(Context context) {
        super(context);
    }

    public MyWinConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        paintUI=new Paint();
        paintUI.setColor(Color.WHITE);

        paintUI2=new Paint();
        paintUI2.setColor(Color.BLUE);
    }

    private int width;//设置高
    private int height;//设置高
    Paint paintUI;
    Paint paintUI2;


    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(width, height);//设置宽和高
        //]Log.w(this.toString(), "MyWinCon.onMeasure: E0"+"="+width +"="+ height);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawRect(0,0,width,height,paintUI);
        //canvas.drawRect(30,30,width-200,height-30,paintUI2);
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //]Log.w(this.toString(), "MyWinCon.onTouchEvent: E0"+"="+event.getAction() );
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//
//                return true;
//            case MotionEvent.ACTION_DOWN:
//                return true;
//            case MotionEvent.ACTION_UP:
//                return true;
//        }
//        return super.onTouchEvent(event);
//    }
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        getParent().requestDisallowInterceptTouchEvent(true);
//        ////]Log.w(this.toString(), "DOKDonTouchEventD1: "+"="+event.getAction() );
//        switch (event.getAction()){
//            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_UP:
//                getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//        }
//
//        return super.dispatchTouchEvent(event);
//    }

}
/**
 * colllectQuoLineDataH1: ={ "data":[{"s":"FX_IDC:EURGBP","d":[0.37575758,-0.18181818,0.93333333,51.71797166,39.96034607,41.35560773,43.19063824,44.34018763,-0.00003844,-0.00002727,41.56348797,3.788861,10.70350882,-34.21052632,41.30397679,-0.00716709,-0.00017158,1,-0.00008006,-0.00011,0.836944,0.836985,0.8370395,0.83706,0.837083,0.8370692,0.83683155,null,0.83696709,0.83698806,0.83702294,0.83704096,0.83705171,0.83702087,0.83694952,null,0,0.83698,null,1,0.83705464,null,1,0.83689426,null,0.82621333,0.83220333,0.83482667,0.83819333,0.84081667,0.84418333,0.85017333,null,0.83220333,0.83449151,0.83590515,0.83819333,0.84048151,0.84189515,0.84418333,null,0.83580275,0.83635183,0.83690092,0.83819333,0.83799908,0.83854817,0.83909725,null,0.828415,0.8319925,0.834405,0.8379825,0.840395,0.8439725,0.846385,null,0.83651,0.839035,0.8425,null]},{"s":"FX_IDC:EURJPY","d":[-0.51212121,-0.09090909,-0.93333333,31.8406188,38.7780386,42.34698313,37.92142475,55.3845152,-0.01306775,-0.01000054,43.78124274,5.48596552,21.37601325,-59.72222222,-233.65282215,-0.03718424,-0.08883681,0,-0.02243235,-0.054,121.0114,121.0136,121.02885,121.03756667,121.04972,121.05521,121.115625,null,121.00481061,121.0144895,121.02556882,121.0328676,121.04292006,121.06524164,121.1011615,null,0,120.9915,null,-1,121.03209433,null,-1,121.00591481,null,118.07433333,119.41033333,120.09666667,120.74633333,121.43266667,122.08233333,123.41833333,null,119.41033333,119.92068533,120.23598133,120.74633333,121.25668533,121.57198133,122.08233333,null,120.4156,120.53806667,120.66053333,120.74633333,120.90546667,121.02793333,121.1504,null,118.781,119.4205,120.117,120.7565,121.453,122.0925,122.789,null,120.4215,120.90875,121.7575,null]},{"s":"FX_IDC:USDJPY","d":[-0.51212121,-0.09090909,-0.93333333,42.66276969,53.00914635,50.22541185,41.16854533,33.72593349,-0.00744143,-0.00586267,62.97576133,2.49493082,13.96668152,-50.51546392,-138.02738195,-0.01703089,-0.03936118,0,-0.02182059,-0.014,111.554,111.5577,111.5677,111.5716,111.5792,111.57015,111.59373,null,111.5540837,111.5584681,111.56481096,111.56876501,111.57264583,111.57878894,111.59782964,null,0,111.544,null,-1,111.57134635,null,-1,111.55080741,null,109.46433333,110.61233333,111.29266667,111.76033333,112.44066667,112.90833333,114.05633333,null,110.61233333,111.05086933,111.32179733,111.76033333,112.19886933,112.46979733,112.90833333,null,111.6573,111.76253333,111.86776667,111.76033333,112.07823333,112.18346667,112.2887,null,110.24,110.66,111.388,111.808,112.536,112.956,113.684,null,111.5265,111.87725,112.6745,null]},{"s":"FX_IDC:USDCHF","d":[-0.18181818,-0.36363636,0,48.10534435,52.21075269,45.00215054,20.56266305,9.20964358,-0.00004679,-0.00003848,45.0656351,3.71917758,24.6597554,-22.4,-21.49400986,0.00102225,-0.00090751,-1,-0.00018779,0.00004,0.978176,0.978186,0.9782705,0.97831767,0.9783104,0.9781985,0.9779938,null,0.97819,0.9782048,0.97824591,0.97826727,0.97826903,0.97819977,0.97814251,null,0,0.97791,null,-1,0.97825935,null,1,0.97816774,null,0.97526,0.97921,0.98147,0.98316,0.98542,0.98711,0.99106,null,0.97921,0.9807189,0.9816511,0.98316,0.9846689,0.9856011,0.98711,null,0.98264375,0.98300583,0.98336792,0.98316,0.98409208,0.98445417,0.98481625,null,0.977735,0.9793175,0.981685,0.9832675,0.985635,0.9872175,0.989585,null,0.982315,0.9835825,0.986265,null]},{"s":"FX_IDC:GBPUSD","d":[-0.06969697,-0.27272727,0.13333333,50.8711565,57.40740741,56.17283951,95.1556453,80.06029602,-0.00002214,-0.00003476,27.21012105,7.60707209,10.33604552,-44.44444444,22.22222223,0.01543805,-0.00043143,-1,-0.00007206,0.0002,1.29566,1.29564,1.295675,1.29569333,1.295726,1.295931,1.296693,null,1.29567793,1.29566537,1.29567499,1.29569719,1.29576486,1.29600435,1.29629729,null,0,1.29565,null,1,1.29565328,null,1,1.29569185,null,1.2723,1.2804,1.2842,1.2885,1.2923,1.2966,1.3047,null,1.2804,1.2834942,1.2854058,1.2885,1.2915942,1.2935058,1.2966,null,1.2857725,1.286515,1.2872575,1.2885,1.2887425,1.289485,1.2902275,null,1.27595,1.280325,1.28405,1.288425,1.29215,1.296525,1.30025,null,1.2823,1.28755,1.2904,null]},{"s":"FX_IDC:USDCAD","d":[0.445
 */
