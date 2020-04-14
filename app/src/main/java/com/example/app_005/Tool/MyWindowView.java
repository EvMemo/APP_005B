package com.example.app_005.Tool;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.app_005.R;
import com.example.app_005.Tool.WindowView.MyWinConstraintLayout;
import com.example.app_005.Tool.WindowView.WindowButtonView;
import com.example.app_005.Tool.WindowView.WindowLyButtonView;
import com.example.app_005.Tool.WindowView.WindowNewsSysCet;
import com.example.app_005.Tool.WindowView.WindowRecycView;
import com.example.app_005.Tool.WindowView.WindowTabView;
import com.example.app_005.Tool.WindowView.WindowTextView;

import static android.content.Context.WINDOW_SERVICE;

public class MyWindowView {
    Context context;
    WindowManager.LayoutParams layoutParams;
    WindowManager windowManager;
    View view;
    WindowNewsSysCet windowNewsSysCet;
    WindowButtonView windowButtonView;
    WindowLyButtonView windowLyButtonView;
    WindowTabView windowTabView;
    WindowRecycView windowRecycView;
    WindowTextView windowTextView;
    MyWinConstraintLayout myWinConstraintLayout;
    WindowManager.LayoutParams layoutParamsB;
    public MyWindowView(Context contextAA){
        context=contextAA;

        windowManager = (WindowManager) contextAA.getApplicationContext().getSystemService(WINDOW_SERVICE);
        initWindowView();
       // initData();
    }
    Button buttonA0;
    ImageView imageViewA;
    Button[] buttonsA;
    View butViewA;
   // WindowButtonView windowButtonView;
    void initData(){
//        button.setText("OK");
//       // button.getLayoutParams().width=(100);
//        //button.setHeight(10);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                button.getLayoutParams().width=(100);
//            }
//        });
//        ConstraintLayout constraintLayout=new ConstraintLayout(context);
       // startWindow(initView());
    }
    void initWindowView(){

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0,
                PixelFormat.TRANSPARENT
        );
        layoutParams.flags=  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        //layoutParams.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        layoutParams.gravity =  Gravity.CENTER|Gravity.RIGHT;
        layoutParams.y=-200;
        View view_A= inflater.inflate(R.layout.one_winbut_view, null);
        windowManager.addView(view_A, layoutParams);

        LayoutInflater inflaterB = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutParamsB = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT
                ,
                0, 0,
                PixelFormat.TRANSPARENT
        );
        layoutParamsB.flags=  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParamsB.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParamsB.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        //layoutParams.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        layoutParamsB.gravity =  Gravity.CENTER|Gravity.RIGHT;
        View view_B= inflater.inflate(R.layout.one_window_view, null);
        myWinConstraintLayout=view_B.findViewById(R.id.win_consLay_001);
        myWinConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccWindowButTouch();
            }
        });
        windowLyButtonView=view_B.findViewById(R.id.win_winLyButtonView);
        windowLyButtonView.setMyWindowView(this);
        windowManager.addView(myWinConstraintLayout, layoutParamsB);
        windowButtonView=view_A.findViewById(R.id.winbut_but_001);
        windowButtonView.setMyWindowView(this);
        windowButtonView.setActivated(true);
        windowRecycView=view_B.findViewById(R.id.win_windowRecycView);
        windowTabView=view_B.findViewById(R.id.win_windowTabView);
        windowTabView.setMyWinDowView(this);
        ccWindowButTouch();
        //myWinConstraintLayout.setActivated(false);
        //layoutParams.x = 0;// 以屏幕左上角为原点，设置x、y初始值
        //layoutParams.y = 120;
    }
    public void ccWindowButTouch(){
        if(windowButtonView.isActivated())
            {
                windowButtonView.setActivated(false);
                myWinConstraintLayout.setVisibility(View.GONE);
            }else {

            windowButtonView.setActivated(true);
            myWinConstraintLayout.setVisibility(View.VISIBLE);
        }
    }
    public void cutTabView(int int_A){
        if(windowRecycView!=null)
            {

                windowRecycView.cutTabView(int_A);
            }
    }
//    public void ccWindowLayoutTouch(){
//        if(windowButtonView.isActivated())
//        {
//            windowButtonView.setActivated(false);
//            myWinConstraintLayout.setVisibility(View.GONE);
//        }else {
//
//            windowButtonView.setActivated(true);
//            myWinConstraintLayout.setVisibility(View.VISIBLE);
//        }
//    }
    View initView(){
//第一个参数为xml文件中view的id，第二个参数为此view的父组件，可以为null，android会自动寻找它是否拥有父组件
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View view_A = inflater.inflate(R.layout.one_window_view, null);
         view=view_A;
          myWinConstraintLayout=view_A.findViewById(R.id.win_consLay_001);


        layoutParams.width=500;
        layoutParams.height=500;

       // myWinConstraintLayout.setPivotX(layoutParams.width/2);
       // myWinConstraintLayout.setPivotY(layoutParams.height/2);
      //  layoutParams.x = 0;// 以屏幕左上角为原点，设置x、y初始值
      //  layoutParams.y = 0;
        myWinConstraintLayout.setActivated(true);
        myWinConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //]Log.w(this.toString(), "myWinConstraintLayout:F0"+"="+v.isActivated() );
                if(v.isActivated())
                    {
                    v.setActivated(false);
                        WindowManager.LayoutParams floatBallParams = new WindowManager.LayoutParams();
                        floatBallParams.width = 100;
                        floatBallParams.height = 100;
                        floatBallParams.gravity = Gravity.TOP | Gravity.LEFT;
                        floatBallParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                        floatBallParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
                        floatBallParams.format = PixelFormat.RGBA_8888;
                       // layoutParams.width=200;
                        //layoutParams.height=500;

                        //myWinConstraintLayout.setPivotX(layoutParams.width/2);
                        //myWinConstraintLayout.setPivotY(layoutParams.height/2);
                        layoutParams.x = -100;// 以屏幕左上角为原点，设置x、y初始值
                       layoutParams.y = 0;
                        myWinConstraintLayout.setLayoutParams(layoutParams);
                        windowManager.addView(buttonA0,floatBallParams);
                        myWinConstraintLayout.invalidate();
                    }else {
//
//                    layoutParams.width=500;
//                    layoutParams.height=500;
//
//                   // myWinConstraintLayout.setPivotX(layoutParams.width/2);
//                   // myWinConstraintLayout.setPivotY(layoutParams.height/2);
//                    myWinConstraintLayout.setLayoutParams(layoutParams);
//                      layoutParams.x = 0;// 以屏幕左上角为原点，设置x、y初始值
//                      layoutParams.y = 0;
                    windowManager.removeViewImmediate(buttonA0);
                    v.setActivated(true);
                    myWinConstraintLayout.invalidate();
                }
            }
        });
//        windowButtonView=view_A.findViewById(R.id.win_winButtonView);
//
//        windowTabView=view_A.findViewById(R.id.win_windowTabView);
//        windowRecycView=view_A.findViewById(R.id.win_windowRecycView);
//        windowButtonView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //]Log.w(this.toString(), "myWinConstraintLayout:F0"+"="+v.isActivated() );
//                if(v.isActivated())
//                    {
//                    v.setActivated(false);
//                       windowTabView.setVisibility(View.GONE);
//                       windowRecycView.setVisibility(View.GONE);
//                        layoutParams.x = 0;// 以屏幕左上角为原点，设置x、y初始值
//                        layoutParams.y = 120;
//                        layoutParams.width=windowButtonView.getWidth();
//                        layoutParams.height=windowButtonView.getHeight();
//                        windowManager.updateViewLayout(view,layoutParams);
//                    }else {
//                    v.setActivated(true);
//                   // windowTabView.setVisibility(View.VISIBLE);
//                   // windowRecycView.setVisibility(View.VISIBLE);
//                    layoutParams.x = 0;// 以屏幕左上角为原点，设置x、y初始值
//                    //layoutParams.y = 20;
//                    layoutParams.width=windowButtonView.getWidth()+200;
//                    layoutParams.height=windowButtonView.getHeight();
//                    windowManager.updateViewLayout(view,layoutParams);
//
//                }
//            }
//        });
        windowNewsSysCet=new WindowNewsSysCet(view_A);
//        imageViewA=view_A.findViewById(R.id.image_one_win_00);
//        imageViewA.setVisibility(View.GONE);
//        buttonA0=view_A.findViewById(R.id.button_one_win_00);
//        buttonA0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(v.isActivated())
//                    {
//                        v.setAlpha(0.1F);
//                    v.setActivated(false);
//                        imageViewA.setVisibility(View.GONE);
//                        for(int i=0;i<buttonsA.length;i++)
//                        {
//                            buttonsA[i].setVisibility(View.GONE);
//                        }
//                    }else {
//                    v.setActivated(true);
//                    v.setAlpha(1);
//                    imageViewA.setVisibility(View.VISIBLE);
//                    for(int i=0;i<buttonsA.length;i++)
//                        {
//                            buttonsA[i].setVisibility(View.VISIBLE);
//                        }
//                }
//            }
//        });
//        buttonsA=new Button[4];
//        buttonsA[0]=view_A.findViewById(R.id.button_one_win_01);
//        buttonsA[1]=view_A.findViewById(R.id.button_one_win_02);
//        buttonsA[2]=view_A.findViewById(R.id.button_one_win_03);
//        buttonsA[3]=view_A.findViewById(R.id.button_one_win_04);
//
//        for(int i=0;i<buttonsA.length;i++)
//            {
//                buttonsA[i].setActivated(false);
//                buttonsA[i].setVisibility(View.GONE);
//                buttonsA[i].setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(!v.isActivated()){
//                            butViewA.setActivated(false);
//                            butViewA=v;
//                            butViewA.setActivated(true);
//                        }
//
//                    }
//                });
//            }
//        butViewA= buttonsA[0];
//        butViewA.setActivated(true);

        return view_A;
    }
    public void startWindow(View viewAA){
        view=viewAA;
    }
    public void updateWindow(View viewAA,WindowManager.LayoutParams layoutParamsAA){
        view=viewAA;
        windowManager.updateViewLayout(view,layoutParamsAA);
    }
    public void removeWindow(View viewAA){
        view=viewAA;
        windowManager.removeView(view);
    }
//    public class BlurBitmap {
//        /**
//         * 图片缩放比例
//         */
//        private static final float BITMAP_SCALE = 0.4f;
//        /**
//         * 最大模糊度(在0.0到25.0之间)
//         */
//        private static final float BLUR_RADIUS = 25f;
//
//        /**
//         * 模糊图片的具体方法
//         *
//         * @param context   上下文对象
//         * @param image     需要模糊的图片
//         * @return          模糊处理后的图片
//         */
//        public Bitmap blur(Context context, Bitmap image) {
//            // 计算图片缩小后的长宽
//            int width = Math.round(image.getWidth() * BITMAP_SCALE);
//            int height = Math.round(image.getHeight() * BITMAP_SCALE);
//
//            // 将缩小后的图片做为预渲染的图片。
//            Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
//            // 创建一张渲染后的输出图片。
//            Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
//
//            // 创建RenderScript内核对象
//            RenderScript rs = RenderScript.create(context);
//            // 创建一个模糊效果的RenderScript的工具对象
//            ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
//
//            // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间。
//            // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
//            Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
//            Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
//
//            // 设置渲染的模糊程度, 25f是最大模糊度
//            blurScript.setRadius(BLUR_RADIUS);
//            // 设置blurScript对象的输入内存
//            blurScript.setInput(tmpIn);
//            // 将输出数据保存到输出内存中
//            blurScript.forEach(tmpOut);
//
//            // 将数据填充到Allocation中
//            tmpOut.copyTo(outputBitmap);
//
//            return outputBitmap;
//        }
//    }
}
