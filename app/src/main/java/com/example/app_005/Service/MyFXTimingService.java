package com.example.app_005.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.app_005.MainApplication;
import com.example.app_005.Tool.FXCollectTool;
import com.example.app_005.Tool.FXDataConnect;

import java.util.Calendar;

/**
 * 外汇定时服务
 */
public class MyFXTimingService extends Service  {
    private MyBinder binder = new MyBinder();
   // public static Calendar ST_calendar;
   FXDataConnect.TimeConnect timeConnect;
   FXDataConnect.QuoConnect quoConnect;
   FXDataConnect.NewsConnect newsConnect;
   FXDataConnect.CalendarConnect calendarConnect;
   FXCollectTool fxCollectTool;
   // public static TimeOD ST_TimeOD;
    Intent intent;
    PendingIntent sender;
    AlarmManager am;
    MyAlarmManager myAlarmManager;
    public MyFXTimingService() {
    }
    public class MyBinder extends Binder {
        public MyFXTimingService getService(){
            return MyFXTimingService.this;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        //Log.d(this.toString(), "onCreate(okok4) called"+ST_titleTime);
        super.onCreate();

        FXDataConnect fxDataConnect_A=((MainApplication)getApplicationContext()).fxDataConnect;
        timeConnect=fxDataConnect_A.getTimeConnect();
        quoConnect=fxDataConnect_A.getQuoConnect();
        newsConnect=fxDataConnect_A.getNewsConnect();
        calendarConnect =fxDataConnect_A.getCalendarConnect();
        fxCollectTool=((MainApplication)getApplicationContext()).fxCollectTool;
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        init_crAlarmManager();

        initStart();

        if(false)
            {
                IntentFilter filter = new IntentFilter();
                filter.addAction(Intent.ACTION_TIME_TICK);
                filter.addAction(Intent.ACTION_TIME_CHANGED);
                registerReceiver(broadcastReceiver, filter);
            }

      //  ST_calendar=Calendar.getInstance();
       // ST_TimeOD=new MyFXDataService.TimeOD(ST_calendar.getTimeInMillis());
        //ST_titleTime=new TitleTime();

    }
    public void initStart(){
        initData();
        if(true)
        {
           // startAlar();
            startRM();
        }
    }
    void initData(){
        startCollectJin10CalendarDHData();
        initCollectCalendarTreeData();
    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.ACTION_TIME_TICK.equals(intent.getAction())){
                Calendar calendar=Calendar.getInstance();
                //]Log.w(this.toString(), "TimeJO00D22: "+"="+calendar.get(Calendar.MINUTE)+"="+calendar.get(Calendar.MILLISECOND) );
            }else if(intent.ACTION_TIME_CHANGED.equals(intent.getAction())){

            }
        }
    };

    /**
     *
     */
    private void init_crAlarmManager(){
        myAlarmManager=new MyAlarmManager(getApplicationContext());
        // myAlarmManager.startAlarm();;
       // am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        intent = new Intent(this,MyFXTimingService.class);
//       // intent.setNewsData(Uri.parse("myhttp://www.baidu.com"));
//        intent.setAction("TimeOne");
//        intent.setClass(this, this.getClass());
//        intent.putExtra("Mode",1);
//        //intent.putExtra(ID, id);
//        long atTimeInMillis = System.currentTimeMillis() + 1000;
//        //intent.putExtra(TIME, atTimeInMillis);
////          intent.putExtra(LABEL, label);
////          intent.putExtra(TIME, atTimeInMillis);
//        sender = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT    );
//        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, atTimeInMillis, sender);

    }
    void startAlar(){
        // am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(this,MyFXTimingService.class);
       // intent.setNewsData(Uri.parse("myhttp://www.baidu.com"));
        intent.setAction("TimeOne");
        intent.setClass(this, this.getClass());
        intent.putExtra("Mode",1);
        //intent.putExtra(ID, id);
        Calendar calendar=Calendar.getInstance();
        //calendar.add(Calendar.MINUTE,1);
        calendar.add(Calendar.SECOND,3);
        //calendar.set(Calendar.SECOND,5);
        calendar.set(Calendar.MILLISECOND,0);
        long long_B0=calendar.getTimeInMillis();
        long atTimeInMillis =long_B0;// System.currentTimeMillis() + 160000;
        //intent.putExtra(TIME, atTimeInMillis);
//          intent.putExtra(LABEL, label);
//          intent.putExtra(TIME, atTimeInMillis);
        sender = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT    );
        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, atTimeInMillis, sender);
    }
    void cancelAlar(){
        intent = new Intent(this,MyFXTimingService.class);
        // intent.setNewsData(Uri.parse("myhttp://www.baidu.com"));
        intent.setAction("TimeOne");
        intent.setClass(this, this.getClass());
        intent.putExtra("Mode",1);
        //intent.putExtra(ID, id);
        Calendar calendar=Calendar.getInstance();
        //calendar.add(Calendar.MINUTE,1);
        calendar.add(Calendar.MILLISECOND,15);
        calendar.set(Calendar.MILLISECOND,0);
        long long_B0=calendar.getTimeInMillis();
        long atTimeInMillis =long_B0;// System.currentTimeMillis() + 160000;
        //intent.putExtra(TIME, atTimeInMillis);
//          intent.putExtra(LABEL, label);
//          intent.putExtra(TIME, atTimeInMillis);
        sender = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT    );
        if (sender != null){
            //]Log.w("lily","TimeJO00CA +cancel alarm");
            am.cancel(sender);
        }else{
            //]Log.w("lily","TimeJO00CB +sender == null");
        }

    }
    /**
     *
     */
    private void PrC_desAlarmManager(){
        am.cancel(sender);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancelAlar();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //ST_calendar=Calendar.getInstance();
//        ST_titleTime.updateTimeNew();
        if(intent==null)
            {
            return super.onStartCommand(intent, flags, startId);
            }
        int int_A=intent.getIntExtra("Mode",0);
        Calendar calendar=Calendar.getInstance();
        ////]Log.w(this.toString(), "TimeJO00D: "+"="+calendar.get(Calendar.MINUTE)+"="+calendar.get(Calendar.MILLISECOND) );
        ////]Log.w(this.toString(), "TimeJO00C: "+"="+int_A +"="+(Calendar.getInstance().getTimeInMillis()) +"="+System.currentTimeMillis() +"="+new TimeOD(Calendar.getInstance().getTimeInMillis()).intsA[4]);
        switch(int_A){
            case 0:
                //]Log.w(this.toString(), "TimeTRA0-O: ");
               // startMsSecond();
                break;
            case 1:
                //startMsSecond();
                startAlar();
                //]Log.w(this.toString(), "TimeTRA0-A: "+"="+ (Calendar.getInstance().get(Calendar.MINUTE)) +"="+(Calendar.getInstance().get(Calendar.SECOND)));

              //  myAlarmManager.startAlarm();
               // myAlarmManager.cancelAlarm();
//                TimeOD timeOD_A=new TimeOD(Calendar.getInstance().getTimeInMillis());
//                //]Log.w(this.toString(), "TimeJO001: "+"="+timeOD_A.intsA[3]+"="+timeOD_A.intsA[4]);
//                long atTimeInMillis = System.currentTimeMillis() + 60000;
//                timeOD_A=new TimeOD(atTimeInMillis);
//                //]Log.w(this.toString(), "TimeJO001: "+"="+timeOD_A.intsA[3]+"="+timeOD_A.intsA[4]);
//
//                Calendar calendar=Calendar.getInstance();
//                calendar.add(Calendar.MINUTE,1);
//                long long_B0=calendar.getTimeInMillis();
//                calendar.set(Calendar.MILLISECOND,0);
//                long long_B1=calendar.getTimeInMillis();
//                //]Log.w(this.toString(), "TimeJO001: "+"="+(long_B1-long_B0)+"="+long_B0+"="+long_B1);
//                //intent.putExtra(TIME, atTimeInMillis);
////          intent.putExtra(LABEL, label);
////          intent.putExtra(TIME, atTimeInMillis);
//               // sender = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT    );
//                am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

                break;
            default:
                break;
        }
        return super.onStartCommand(intent, flags, startId);

    }
    void startMsSecond(){
        //]Log.w(this.toString(), "startMmission00: "+"="+Calendar.getInstance().get(Calendar.SECOND));
        startCollectQuoData();

        //fxCollectTool.reqCollectQuoData();
//        if(timeConnect.getTimeNew().intsA[4]%3==0)
//            {
//                fxCollectTool.reqCollectNewsData();
//            }

    }
    /**
     *更新每一分钟时间数据
     */
    void startUpdateMsMinute(){
        timeConnect.setNewMinuTime();

    }
    void startUpdateNewDay(){
        timeConnect.setNewsDay();
        quoConnect.setBoolUpdateNewDateTime();
        quoConnect.isUpdataTime();
        newsConnect.setBoolUpdateNewDateTime();
        newsConnect.isUpdataTime();
        calendarConnect.setBoolUpdateNewDateTime();
        calendarConnect.isUpdataTime();
    }
    /**
     *每十五分钟保存数据
     */
    void startSaveMainData(){
        quoConnect.saveFXQuoMainData();//
    }
    void startCollectQuoData(){
        fxCollectTool.reqCollectQuoData();
    }
    void startCollectQuoLine(){
        fxCollectTool.reqCollectQuoLine();
    }
    void startCollectNewsData(){
        fxCollectTool.reqCollectNewsData();
    }
    void startCollectCalendarXMDHData(){
        fxCollectTool.reqCollectCalendarDHData(System.currentTimeMillis());
    }
    void startCollectCalendarCCData(){
        fxCollectTool.reqCollectCalendarCCData();
    }
    void startCollectJin10CalendarDHData(){
        fxCollectTool.reqCollectCalendarJin10Data();
    }
    void initCollectCalendarTreeData(){
        fxCollectTool.reqCollectCalendarJin10TreeData();
    }
    long longRMSaveTime;
    long longRMLineTime;
    long longRMNewsTime;
    long longRMDateTime;
    long longRMCalendarTime;
    void startRM(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    longRMSaveTime =System.currentTimeMillis();
                    longRMLineTime= longRMSaveTime;
                    longRMNewsTime=longRMSaveTime;
                    longRMCalendarTime=longRMSaveTime;
                    Calendar calendar_A=Calendar.getInstance();
                    calendar_A.set(Calendar.SECOND,0);
                    calendar_A.set(Calendar.MILLISECOND,0);
                    calendar_A.set(Calendar.MINUTE,0);
                    calendar_A.add(Calendar.DAY_OF_MONTH,1);
                    longRMDateTime =calendar_A.getTimeInMillis();
                    while (true){
                        timeConnect.setNewTime();
                        if(System.currentTimeMillis()> longRMDateTime)//00Tread
                            {
                                calendar_A=Calendar.getInstance();
                                calendar_A.add(Calendar.DAY_OF_MONTH,1);
                                calendar_A.set(Calendar.MINUTE,0);
                                calendar_A.set(Calendar.SECOND,0);
                                calendar_A.set(Calendar.MILLISECOND,0);
                                longRMDateTime =calendar_A.getTimeInMillis();
                                startUpdateNewDay();
                            }
                        startMsSecond();//22Tread
                        if(System.currentTimeMillis()>(longRMLineTime))
                        {
                            //]Log.w(this.toString(), "startRM1: "+"="+ (Calendar.getInstance().get(Calendar.SECOND)) +"="+(Calendar.getInstance().get(Calendar.MINUTE)));
                            longRMLineTime =System.currentTimeMillis()+5000;
                            startCollectQuoLine();
                        }
                        if(System.currentTimeMillis()>(longRMNewsTime ))
                        {
                            //]Log.w(this.toString(), "startRMlongRMNewsTime: "+"="+ (Calendar.getInstance().get(Calendar.SECOND)) +"="+(Calendar.getInstance().get(Calendar.MINUTE)));
                            longRMNewsTime =System.currentTimeMillis()+(60000*1);
                            startCollectNewsData();
                        }
                        if(System.currentTimeMillis()>(longRMSaveTime ))
                        {
                            //]Log.w(this.toString(), "startRM1: "+"="+ (Calendar.getInstance().get(Calendar.SECOND)) +"="+(Calendar.getInstance().get(Calendar.MINUTE)));
                            if(false)
                                {
                                    longRMSaveTime =System.currentTimeMillis()+(60000*5);//;+(60000*15);
                                }else {
                                longRMSaveTime =System.currentTimeMillis()+(10000);//;+(60000*15);
                            }

                            //updateMsMinute();
                            startSaveMainData();
                        }
                        if(System.currentTimeMillis()>(longRMCalendarTime ))
                            {
                                //]Log.w(this.toString(), "longRMCalendarTime: "+"="+ (Calendar.getInstance().get(Calendar.SECOND)) +"="+(Calendar.getInstance().get(Calendar.MINUTE)));
                                longRMCalendarTime =System.currentTimeMillis()+(60050);//;+(60000*15);
                                //updateMsMinute();
                                startCollectJin10CalendarDHData();
                            }

                        ////]Log.w(this.toString(), "TimeTRA1-B: "+"="+ (Calendar.getInstance().get(Calendar.MINUTE)) +"="+(Calendar.getInstance().get(Calendar.SECOND)));

                        ////]Log.w(this.toString(), "startRM0: "+"="+ (Calendar.getInstance().get(Calendar.SECOND)) +"="+(Calendar.getInstance().get(Calendar.MINUTE)));


                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();
    }
    /**
     *定时广播
     */
    private void PrC_fixedTimeBroadcast(){

    }
//    /**
//     *标题
//     */
//    public class  TitleTime{
//         MyFXDataService.TimeOD timeODNew;
//         MyFXDataService.TimeOD timeODNow;
//         long longNew;
//         long longNow;
//         TitleTime(){
//            synchronized (this){
//                longNew=Calendar.getInstance().getTimeInMillis();
//                longNow=longNew;
//                timeODNew=new MyFXDataService.TimeOD(longNew);
//                timeODNow=new MyFXDataService.TimeOD(longNow);
//            }
//
//        }
//        public void updateTimeNew(){
//            synchronized (this){
//                longNew=Calendar.getInstance().getTimeInMillis();
//                timeODNew=new MyFXDataService.TimeOD(longNew);
//            }
//        }
//        public void setTimeNow(MyFXDataService.TimeOD timeODAA){
//            synchronized (this){
//                longNow=timeODAA.PuC_getTimeLong();
//                timeODNow=new MyFXDataService.TimeOD(longNow);
//            }
//        }
//        public void setTimeNow(long longTimeAA){
//            synchronized (this){
//                longNow=longTimeAA;
//                timeODNow=new MyFXDataService.TimeOD(longNow);
//            }
//        }
//        public MyFXDataService.TimeOD getTimeNew() {
//            synchronized (this) {
//                return timeODNew;
//            }
//        }
//        public MyFXDataService.TimeOD getTimeNow() {
//            synchronized (this) {
//                return timeODNow;
//            }
//        }
//        public long getlongNew() {
//            synchronized (this) {
//                return longNew;
//            }
//        }
//        public long getlongNow() {
//            synchronized (this) {
//                return longNow;
//            }
//        }
//    }
    public class MyAlarmManager{
        AlarmManager am;
        Context context;
        Intent intent;
        PendingIntent pendingIntent;
        public MyAlarmManager(Context contextAA){
            context=contextAA;
            am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent = new Intent(contextAA,MyFXTimingService.class);
           // intent.setNewsData("MyAlarmManager");
            intent.setAction("TimeOne");
            intent.setClass(contextAA, this.getClass());
            intent.putExtra("Mode",1);
            //intent.putExtra(ID, id);
            //intent.putExtra(TIME, atTimeInMillis);
//          intent.putExtra(LABEL, label);
//          intent.putExtra(TIME, atTimeInMillis);
            pendingIntent = PendingIntent.getService(contextAA, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT    );
        }
        public void startAlarm(){
            if(true)
                {
                    am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(context,MyFXTimingService.class);
       // intent.setNewsData(Uri.parse("myhttp://www.baidu.com"));
        intent.setAction("TimeOne");
        intent.setClass(context, this.getClass());
        intent.putExtra("Mode",1);
        //intent.putExtra(ID, id);
        long atTimeInMillis = System.currentTimeMillis() + 1000;
        //intent.putExtra(TIME, atTimeInMillis);
//          intent.putExtra(LABEL, label);
//          intent.putExtra(TIME, atTimeInMillis);
        sender = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT    );
        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, atTimeInMillis, sender);
                }else {
                Calendar calendar=Calendar.getInstance();
                calendar.add(Calendar.MILLISECOND,5);
                //calendar.set(Calendar.MILLISECOND,0);
                long long_B0=calendar.getTimeInMillis();

//            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
//            TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
//            timeOD_A.PuC_addMinute(1);
                ////]Log.w(this.toString(), "TimeJO00A: "+"="+timeOD_A.intsA[3]+"="+timeOD_A.intsA[4]);
                //]Log.w(this.toString(), "startAlarm0B1: "+"="+isAlarm());
                init_crAlarmManager(pendingIntent,System.currentTimeMillis() + 1000);
                //]Log.w(this.toString(), "startAlarm0B2: "+"="+isAlarm());
            }

        }
        public void cancelAlarm(){
            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE);
            if (sender != null){
                //]Log.w("lily","cancel alarm");
                am.cancel(pendingIntent);
            }else{
                //]Log.w("lily","sender == null");
            }
        }
        boolean isAlarm(){
            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE);
            return (sender != null);
        }
    public void init_crAlarmManager(PendingIntent pendingIntentAA,Long alarmTimeAA){
        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTimeAA, sender);

    }
}

}
