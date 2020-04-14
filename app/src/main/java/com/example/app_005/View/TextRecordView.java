package com.example.app_005.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

import com.example.app_005.R;

public class TextRecordView extends AppCompatEditText {

    int width,height;
    private static final int DRAWABLE_LEFT = 0;
    private static final int DRAWABLE_TOP = 1;
    private static final int DRAWABLE_RIGHT = 2;
    private static final int DRAWABLE_BOTTOM = 3;
    private Drawable mClearDrawable;
    Context context;
    boolean boolD;
public TextRecordView(Context context) {
        super(context);
        init(context);
        }

public TextRecordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        }

public TextRecordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        Log.w(this.toString(), "TRVa0.onMeasure: E0a"+"="+width +"="+height);
        //setMeasuredDimension(width, height);//设置宽和高
        boolD=false;
        if(true||mClearDrawable!=null)
            {
                int width_A = mClearDrawable.getIntrinsicWidth();
                int height_A = mClearDrawable.getIntrinsicHeight();
                Log.w(this.toString(), "TRVa0.onMeasure: E0"+"="+width +"="+height +"="+ width_A +"="+height_A);
                if(height<width_A||height<height_A)
                    {
                        mClearDrawable=zoomDrawable(mClearDrawable,height*2,height*2);
                    }
            }
    }

    private void init(Context context) {
    this.context=context;
        mClearDrawable = ContextCompat.getDrawable(context,R.mipmap.ic_highlight_off_black_36dp);
        }
    private Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(null, newbmp);
    }
    private Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

@Override
protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIconVisible(hasFocus() && text.length() > 0);
        }

@Override
protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setClearIconVisible(focused && length() > 0);
        }

@Override
public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_UP:
        Drawable drawable = getCompoundDrawables()[DRAWABLE_RIGHT];
        if (drawable != null && event.getX() <= (getWidth() - getPaddingRight())
        && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {
        setText("");
        }
        break;
        }
        return super.onTouchEvent(event);
        }

private void setClearIconVisible(boolean visible) {
    if(!boolD)
        {
            boolD=true;
        }
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[DRAWABLE_LEFT], getCompoundDrawables()[DRAWABLE_TOP],
        visible ? mClearDrawable : null, getCompoundDrawables()[DRAWABLE_BOTTOM]);
        }
        }
//public class TextRecordView extends android.support.v7.widget.AppCompatEditText {
//    private int width;//设置高
//    private int height;//设置高影响RecordImpactView
//    Paint paintUI;
//    Paint mPaintLine;
//    public TextRecordView(Context context) {
//        super(context);
//    }
//
//    public TextRecordView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        paintUI=new Paint();
//        paintUI.setColor(Color.BLACK);
//
//        mPaintLine = new Paint();
//        mPaintLine.setAntiAlias(true);//消除锯齿
//        mPaintLine.setColor(Color.GRAY);//设置画笔颜色
//        mPaintLine.setStyle(Paint.Style.STROKE);//设置为空心
//        mPaintLine.setStrokeWidth(10);//设置宽度
//    }
//
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
//        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
//
//        //]Log.w(this.toString(), "TexRecView.onMeasure :E0" +"="+width +"="+height +"="+widthMeasureSpec +"="+heightMeasureSpec);
//
//
//        setMeasuredDimension(width, height);//设置宽和高
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawRect(0,0,10,10,paintUI);
//        canvas.drawRect(width-10,0,width,10,paintUI);
//        canvas.drawRect(0,height-10,10,height,paintUI);
//        canvas.drawRect(width-10,width,height-10,height,paintUI);
//        canvas.drawLine(0,0,width,height,mPaintLine);
//    }

//}
