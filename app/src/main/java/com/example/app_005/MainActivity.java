package com.example.app_005;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.app_005.Class.CalendarDataClass;
import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Internet.XMCalendarCollertTool;
import com.example.app_005.Service.MyFXDataService;
import com.example.app_005.Service.MyFXNotificationService;
import com.example.app_005.Service.MyFXTimingService;
import com.example.app_005.Tool.MyGoogleTranslationTool;
import com.example.app_005.Tool.SoftHideKeyBoardUtil;
import com.example.app_005.View.IntegratedMessageCenterView;
import com.example.app_005.View.IntegratedNewsView;
import com.example.app_005.View.IntegratedTextView;
import com.example.app_005.View.MainTabView;
import com.example.app_005.ui.main.SectionsPagerAdapter;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private MyFXDataService myFXDataServiceA;//外汇数据服务
    public static int st_LayoutNum;
    MainTabView mainTabView;
    ViewPager viewPager;
    IntegratedMessageCenterView integratedMessageCenterView;
    IntegratedNewsView integratedNewsView;
    IntegratedTextView integratedTextView;
    ImageButton imageButton;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            MyFXDataService.MyBinder myBinder = (MyFXDataService.MyBinder)binder;
            myFXDataServiceA = myBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };//外汇数据服务连接器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //CuC_001();
        XMCalendarCollertTool xmCalendarCollertTool_A =new XMCalendarCollertTool();
        //InternetTool internetTool=new InternetTool(this);
        MyGoogleTranslationTool myGoogleTranslationTool=new MyGoogleTranslationTool();
        myGoogleTranslationTool.googleTranslate();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
       // SoftHideKeyBoardUtil.assistActivity(this);
        mainTabView=findViewById(R.id.main_mainTabView);
        mainTabView.setMainActivity(this);
        integratedMessageCenterView =findViewById(R.id.main_integratedMessageView);
        integratedMessageCenterView.setVisibility(View.INVISIBLE);
        integratedNewsView=findViewById(R.id.main_integratedNewsView);
        integratedNewsView.setVisibility(View.INVISIBLE);
        integratedNewsView.setMainActivity(this);
        integratedMessageCenterView.setIntegratedNewsView(integratedNewsView);
        integratedTextView=findViewById(R.id.main_integratedTextView);
        imageButton=findViewById(R.id.main_newsTextBut_001);
        integratedTextView.setVisibility(View.INVISIBLE);
        imageButton.setVisibility(View.INVISIBLE);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeIntegratedTextView();
            }
        });
        final MainApplication mainApplication_A=((MainApplication)getApplicationContext());

        mainApplication_A.fxDataConnect.getNewsConnect(integratedNewsView);
        mainApplication_A.fxDataConnect.getCalendarConnect(integratedNewsView);
        integratedNewsView.post(new Runnable() {
            @Override
            public void run() {


                integratedNewsView.startViewOK();
            }
        });
        //MyWindowView myWindowView=new MyWindowView(this);

       init_TabLayout();
        //startService(new Intent(MainActivity.this, M.class));//启动收集服务


//
        //FloatingActionButton fab = findViewById(R.id.fab);

        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(false)
            {
                Button btn = new Button(this);
                btn.setText("我是窗口");
                WindowManager wm =getWindowManager();// (WindowManager) getSystemService(WINDOW_SERVICE);
                WindowManager.LayoutParams layout = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,0,0, PixelFormat.TRANSLUCENT);

                layout.flags =  WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;//WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|


                layout.gravity = Gravity.CENTER;

                layout.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;

                layout.x = 500;
                layout.y = 500;
                Log.d("onResume000", "L0+"+(btn!=null));
                Log.d("onResume000", "L1+"+(wm!=null));
                Log.d("onResume000", "L2+"+(layout!=null));
                if (wm != null) {
                    wm.addView(btn,layout);
                }
            }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //InternetTool internetTool=new InternetTool(this);
    }
    void init(){

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("log", "MainAct onSaveInstanceState");
    }
    public void updateIntegratedNewNum(int intMode_A,int intNum_A){
        mainTabView.updateIntegratedNewNum(intMode_A,intNum_A);

    }
    public void openIntegratedView(int int_A){
        if(integratedMessageCenterView !=null)
            {
                integratedMessageCenterView.openView(int_A);
            }
    }
    public void closeIntegratedView(){
        if(integratedMessageCenterView !=null)
        {
            integratedMessageCenterView.closeView();
        }
    }
    public void openIntegratedNewsTextData(NewsDataClass newsDataClass_A){
        openIntegratedTextView();
        integratedTextView.setIntegratedNewsData(newsDataClass_A);
    }
    public void openIntegratedCalendarTextData(CalendarDataClass calenderData_A){
        openIntegratedTextView();
        integratedTextView.setIntegratedCalendarData(calenderData_A);
    }
    public void openIntegratedTextView(){
        integratedTextView.setVisibility(View.VISIBLE);
        imageButton.setVisibility(View.VISIBLE);
    }
    public void closeIntegratedTextView(){
        integratedTextView.setVisibility(View.INVISIBLE);
        imageButton.setVisibility(View.INVISIBLE);
    }

    /**
     * 启动服务
     */
    void init_Service(){
        startService(new Intent(MainActivity.this,MyFXTimingService.class));//启动定时服务
        bindService(new Intent(MainActivity.this,MyFXDataService.class),conn,BIND_AUTO_CREATE);//绑定数据服务
        startService(new Intent(MainActivity.this, MyFXNotificationService.class));//启动通知服务
    }
    void init_TabLayout(){
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(1);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                viewPager.setCurrentItem(0);
//            }
//        }).start();
//        TabLayout tabs = findViewById(R.id.tabs);
//        //tabs.setupWithViewPager(viewPager);
//        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//               // Toast.makeText(null, "选中的"+tab.getText(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//               // Toast.makeText(null, "未选中的"+tab.getText(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//               // Toast.makeText(null, "复选的"+tab.getText(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        tabs.getTabAt(0).setText("G");
//        tabs.getTabAt(1).setText("T");
//        tabs.getTabAt(2).setText("A");
    }
    public void setViewPagerNum(int int_A){
        if(viewPager!=null)
            {
                if(viewPager.getCurrentItem()!=(int_A))
                    {
                        viewPager.setCurrentItem(int_A);
                    }

            }
    }

    /**
     * 启动定时任务和服务
     */
    void PrC_startTiming(){
        Intent intent =new Intent(MainActivity.this,MyFXTimingService.class);
        //intent.setAction("short");
        PendingIntent sender=
                PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);

        AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), sender);
    }

    void CuC_001(){
        float[] floats_A=new float[]{1.11018F,1.11022F,1.11018F,1.11012F,1.11029F,1.11010F,1.11024F,1.11037F,1.11025F,1.11034F};//1730
        float[] floats_B=new float[5];
        for(int i=0;i<5;i++)
            {
                float float_2A=floats_A[i];
            for(int e=1;e<5;e++)
                {
                    float_2A+=floats_A[i+e];

                }
            float_2A/=5F;
                Log.d(this.toString(), "CuC_001: "+i +"="+float_2A );
                //1.110198
                //1.110182
                //1.110186
                //1.110224
                //1.110250
                //Z17H0PA6K9ICHNDN
                //OBD0SKAK68YG6B75
                //1.110198
                //1.110165
                //1.110190
                //1.110250
                //1.110280
            }

    }


}