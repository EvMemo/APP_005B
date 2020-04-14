package com.example.app_005.Service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.app_005.R;

import static android.app.Notification.VISIBILITY_SECRET;
import static android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT;

/**
 * 外汇通知服务
 */
public class MyFXNotificationService extends Service {
    private MyBinder binder = new MyBinder();
    public NotificationUtils notificationUtils;
    public MyFXNotificationService() {
    }

    public class MyBinder extends Binder {
        public MyFXNotificationService getService(){
            return MyFXNotificationService.this;
        }
        public NotificationUtils PuC_getNotification(){
            return notificationUtils;

        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationUtils=new NotificationUtils();
        Log.d("202", "onCreate: ");
        NotificationTrend notificationTrend_A=new NotificationTrend();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("OLL", "onStartCommand() called with: intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
        Toast.makeText(getApplicationContext(),"OLOLO",Toast.LENGTH_SHORT);
        return super.onStartCommand(intent, flags, startId);
    }
    /**
     *
     */


    /**
     * 返回切换通知数据
     */
    public void PuC_ReturnSwitchoveNotificationData(){

    }

    /**
     * 移除通知数据
     */
    public void PuC_RemoveNotificationData(){

    }
    public class NotificationUtils  {

        public static final String CHANNEL_ID = "default";
        private static final String CHANNEL_NAME = "Default Channel";
        private static final String CHANNEL_DESCRIPTION = "this is default channel!";
        private NotificationManager mManager;

        public NotificationUtils() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel();
            }
        }

        @TargetApi(Build.VERSION_CODES.O)
        private void createNotificationChannel() {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            //是否绕过请勿打扰模式
            channel.canBypassDnd();
            //闪光灯
            channel.enableLights(true);
            //锁屏显示通知
            channel.setLockscreenVisibility(VISIBILITY_SECRET);
            //闪关灯的灯光颜色
            channel.setLightColor(Color.RED);
            //桌面launcher的消息角标
            channel.canShowBadge();
            //是否允许震动
            channel.enableVibration(true);
            //获取系统通知响铃声音的配置
            channel.getAudioAttributes();
            //获取通知取到组
            channel.getGroup();
            //设置可绕过  请勿打扰模式
            channel.setBypassDnd(true);
            //设置震动模式
            channel.setVibrationPattern(new long[]{100, 100, 200});
            //是否会有灯光
            channel.shouldShowLights();
            getManager().createNotificationChannel(channel);
        }

        private NotificationManager getManager() {
            if (mManager == null) {
                mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            }
            return mManager;
        }

        /**
         * 发送通知
         */
        public void sendNotification(String title, String content) {
            NotificationCompat.Builder builder = getNotification(title, content);
            getManager().notify(1, builder.build());
        }

        private NotificationCompat.Builder getNotification(String title, String content) {
            NotificationCompat.Builder builder = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
            } else {
                builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setPriority(PRIORITY_DEFAULT);
            }
            //标题
            builder.setContentTitle(title);
            //文本内容
            builder.setContentText(content);
            //小图标
            builder.setSmallIcon(R.mipmap.ic_launcher);
            //设置点击信息后自动清除通知
            builder.setAutoCancel(true);
            return builder;
        }

        /**
         * 发送通知
         */
        public void sendNotification(int notifyId, String title, String content) {
            NotificationCompat.Builder builder = getNotification(title, content);
            getManager().notify(notifyId, builder.build());
        }

        /**
         * 发送带有进度的通知
         */
        public void sendNotificationProgress(String title, String content, int progress, PendingIntent intent) {
            NotificationCompat.Builder builder = getNotificationProgress(title, content, progress, intent);
            getManager().notify(0, builder.build());
        }

        /**
         * 获取带有进度的Notification
         */
        private NotificationCompat.Builder getNotificationProgress(String title, String content,
                                                                   int progress, PendingIntent intent) {
            NotificationCompat.Builder builder = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
            } else {
                builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setPriority(PRIORITY_DEFAULT);
            }
            //标题
            builder.setContentTitle(title);
            //文本内容
            builder.setContentText(content);
            //小图标
            builder.setSmallIcon(R.mipmap.ic_launcher);
            //设置大图标，未设置时使用小图标代替，拉下通知栏显示的那个图标
            //设置大图片 BitmpFactory.decodeResource(Resource res,int id) 根据给定的资源Id解析成位图
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            if (progress > 0 && progress < 100) {
                //一种是有进度刻度的（false）,一种是循环流动的（true）
                //设置为false，表示刻度，设置为true，表示流动
                builder.setProgress(100, progress, false);
            } else {
                //0,0,false,可以将进度条隐藏
                builder.setProgress(0, 0, false);
                builder.setContentText("下载完成");
            }
            //设置点击信息后自动清除通知
            builder.setAutoCancel(true);
            //通知的时间
            builder.setWhen(System.currentTimeMillis());
            //设置点击信息后的跳转（意图）
            builder.setContentIntent(intent);
            return builder;
        }
    }
    //走势新闻任务
    public class NotificationTrend{
        public static final String CHANNEL_ID = "NotificationTrend";
        private static final String CHANNEL_NAME = "走势通知 Channel";
        private static final String CHANNEL_DESCRIPTION = "this is default channel!";
        NotificationManager notificationManager;
        NotificationChannel notificationChannel;
        NotificationUtils notificationUtils;
        Context context;
        public NotificationTrend(){
            Log.d("202", "NotificationTrend ");
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            //notificationUtils=new NotificationUtils();
            notificationChannel= new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.canShowBadge();
            notificationManager.createNotificationChannel(notificationChannel);
            NotificationCompat.Builder  builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
            builder.setOngoing(true);// 不能被用户x掉，会一直显示，如音乐播放等
            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_trend_view);
            //remoteViews.setImageViewResource(R.id.button_notevi_03, R.mipmap.baseline_more_vert_black_18dp);
            //remoteViews.setTextViewText(R.id.text_notevi_01, "OK");
            Intent intent = new Intent(getApplicationContext(),MyFXNotificationService.class);
            PendingIntent homeIntent = PendingIntent.getService(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //remoteViews.setOnClickPendingIntent(R.id.button_notevi_03, homeIntent);
            Bitmap screenshot;
            screenshot = Bitmap.createBitmap(64, 100, Bitmap.Config.ARGB_4444);
            Canvas canvas = new Canvas(screenshot);
            Paint paint_A=new Paint();
            paint_A.setAntiAlias(true);//消除锯齿
            paint_A.setColor(Color.BLACK);//设置画笔颜色
            paint_A.setStyle(Paint.Style.STROKE);//设置为空心
            paint_A.setStrokeWidth(3);//设置宽度
            canvas.drawCircle(34,34,5,paint_A);
            //标题
            builder.setContentTitle("Not");
            //文本内容
           // builder.setContentText("");
            //小图标
             builder.setSmallIcon(R.mipmap.ic_launcher);
            //设置大图标，未设置时使用小图标代替，拉下通知栏显示的那个图标
            //设置大图片 BitmpFactory.decodeResource(Resource res,int id) 根据给定的资源Id解析成位图
           // builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

            //设置点击信息后自动清除通知
            //builder.setAutoCancel(true);
            //通知的时间
            builder.setWhen(System.currentTimeMillis());
            //设置点击信息后的跳转（意图）
           // builder.setContentIntent(intent);
            builder.setContent(remoteViews);
            Log.d("202", "NotificationTrend02 ");
            notificationManager.notify(0, builder.build());
            Log.d("202", "NotificationTrend03 ");
        }
        void initNotChannel(){
            notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            //是否绕过请勿打扰模式
            notificationChannel.canBypassDnd();
            //闪光灯
            notificationChannel.enableLights(true);
            //锁屏显示通知
            notificationChannel.setLockscreenVisibility(VISIBILITY_SECRET);
            //闪关灯的灯光颜色
            notificationChannel.setLightColor(Color.RED);
            //桌面launcher的消息角标
            notificationChannel.canShowBadge();
            //是否允许震动
            notificationChannel.enableVibration(true);
            //获取系统通知响铃声音的配置
            notificationChannel.getAudioAttributes();
            //获取通知取到组
            notificationChannel.getGroup();
            //设置可绕过  请勿打扰模式
            notificationChannel.setBypassDnd(true);
            //设置震动模式
            notificationChannel.setVibrationPattern(new long[]{100, 100, 200});
            //是否会有灯光
            notificationChannel.shouldShowLights();
        }

    }
    public class NotificationNews{

    }
    public class NotificationMission{

    }
}
