package com.example.app_005;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.app_005.Class.SkillFinacTool;
import com.example.app_005.Internet.DailyFXCollectTool;
import com.example.app_005.Internet.FortradeCollectTool;
import com.example.app_005.Internet.Jin10CalendarCollectTool;
import com.example.app_005.MainActivity;
import com.example.app_005.Service.MyFXDataService;
import com.example.app_005.Service.MyFXNotificationService;
import com.example.app_005.Service.MyFXTimingService;
import com.example.app_005.Tool.FXAlphaVantageTool;
import com.example.app_005.Tool.FXCollectTool;
import com.example.app_005.Tool.FXDataConnect;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.FXIndexTool;
import com.example.app_005.Tool.MyFileIOTool;
import com.example.app_005.Tool.MyWindowView;
import com.example.app_005.Tool.TitleTime;
import com.example.app_005.Tool.TradingCollectTool;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class MainApplication extends Application {
    public MyFXTimingService myFXTimingService;
    public MyFXDataService myFXDataService;
//    public MyFXCollectService myFXCollectService;
    public MyFXNotificationService myFXNotificationService;
    public MyFXNotificationService.NotificationUtils notificationUtils;
    public MyFileIOTool myFileIOTool;
    public FXDataPool fxDataPool;
    public FXDataConnect fxDataConnect;
    public FXCollectTool fxCollectTool;
    public SkillFinacTool skillFinacTool;
    TitleTime titleTime;
    MyWindowView myWindowView;

    public static final String CHANNEL_ID = "NotificationTrend";
    private static final String CHANNEL_NAME = "走势通知 Channel";
    private static final String CHANNEL_DESCRIPTION = "this is default channel!";
    NotificationManager notificationManager;
    NotificationChannel notificationChannel;
   /// MyFXNotificationService.NotificationUtils notificationUtils;
    /**
     *初始化 应用程序级别 的资源，如全局对象、环境配置变量、图片资源初始化、推送服务的注册等
     * 设置全局共享数据，如全局共享变量、方法等
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //]Log.w(TAG, "onCreate: okok");
        skillFinacTool=new SkillFinacTool(this);
        fxDataConnect=new FXDataConnect(this);
        titleTime=new TitleTime();
        fxDataConnect.setReadConnect();
        fxDataConnect.setTitleTime(titleTime);
        myFileIOTool=new MyFileIOTool(getApplicationContext());//初始化数据工具
        fxDataConnect.setFileIO(myFileIOTool);
         fxDataPool=new FXDataPool(myFileIOTool,fxDataConnect.getTimeConnect(),fxDataConnect);//初始化数据池
        fxDataConnect.setFXQuo(fxDataPool.fxQuo);
        fxDataConnect.setFXNews(fxDataPool.fxNews,fxDataConnect.getReadConnect().getReadModeTool().readModeNews);
        fxDataConnect.setFXCalendar(fxDataPool.fxCalendar);
        fxCollectTool=new FXCollectTool(this,fxDataConnect);
        fxDataConnect.setFXCollectTool(fxCollectTool);
        fxDataPool.fxNews.snapNewsDataTool.initConnect(fxDataConnect);
        startService(new Intent(this, MyFXTimingService.class));
        //FXAlphaVantageTool fxAlphaVantageTool=new FXAlphaVantageTool(fxDataConnect.getQuoConnect());
//        DailyFXCollectTool dailyFXCollectTool_A=new DailyFXCollectTool(this);
//        FortradeCollectTool fortradeCollectTool_A=new FortradeCollectTool(this);
//        TradingCollectTool tradingCollectTool=new TradingCollectTool();
        Jin10CalendarCollectTool jin10CalendarCollectTool_A=new Jin10CalendarCollectTool();
        myWindowView=new MyWindowView(this);


        //ccc_003();

        //initService();
        initData();

        if(false)
            {
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                //notificationUtils=new NotificationUtils();
                notificationChannel= new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.canShowBadge();
                notificationManager.createNotificationChannel(notificationChannel);
                NotificationCompat.Builder  builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
                builder.setOngoing(true);// 不能被用户x掉，会一直显示，如音乐播放等
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_trend_view);
                Bitmap screenshot;

                screenshot = Bitmap.createBitmap(150, 64, Bitmap.Config.ARGB_4444);
                Canvas canvas = new Canvas(screenshot);
                Paint paint_A=new Paint();
                paint_A.setAntiAlias(true);//消除锯齿
                paint_A.setColor(Color.BLACK);//设置画笔颜色
                paint_A.setStyle(Paint.Style.STROKE);//设置为空心
                paint_A.setStrokeWidth(3);//设置宽度
                canvas.drawLine(0,0,150,64,paint_A);
                canvas.drawLine(0,64,150,0,paint_A);
                remoteViews.setImageViewBitmap(R.id.image_notevi_02, screenshot);
                remoteViews.setImageViewBitmap(R.id.image_notevi_03, screenshot);
                // Intent intent = new Intent(getApplicationContext(),MyFXNotificationService.class);
                // PendingIntent homeIntent = PendingIntent.getService(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                // remoteViews.setOnClickPendingIntent(R.id.button_notevi_03, homeIntent);
                //标题
                builder.setContentTitle("Not");

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
                builder.setContent(remoteViews);
                //设置点击信息后的跳转（意图）
                // builder.setContentIntent(intent);
                // builder.setContent(remoteViews);
                Log.d("202", "NotificationTrend02 ");
                notificationManager.notify(0, builder.build());
                Log.d("202", "NotificationTrend03 ");
            }

  //startService(new Intent(this, MyFXNotificationService.class));//启动收集服务

    }
    void initService(){
        if(true)
            {
                startService(new Intent(this, MyFXTimingService.class));
//                bindService(new Intent(this, MyFXTimingService.class), new ServiceConnection() {
//                    @Override
//                    public void onServiceConnected(ComponentName name, IBinder service) {
//                        MyFXTimingService.MyBinder myBinder = (MyFXTimingService.MyBinder) service;
//                        myFXTimingService = myBinder.getService();
//                    }
//
//                    @Override
//                    public void onServiceDisconnected(ComponentName name) {
//
//                    }
//                }, BIND_AUTO_CREATE);//绑定数据服务
            }
        if(false) {
            bindService(new Intent(this, MyFXNotificationService.class), new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    MyFXNotificationService.MyBinder myBinder = (MyFXNotificationService.MyBinder) service;
                    myFXNotificationService = myBinder.getService();
                    notificationUtils = myBinder.PuC_getNotification();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            }, BIND_AUTO_CREATE);//绑定数据服务

            bindService(new Intent(this, MyFXDataService.class), new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    MyFXDataService.MyBinder myBinder = (MyFXDataService.MyBinder) service;
                    myFXDataService = myBinder.getService();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            }, BIND_AUTO_CREATE);//绑定数据服务
//            bindService(new Intent(this, MyFXCollectService.class), new ServiceConnection() {
//                @Override
//                public void onServiceConnected(ComponentName name, IBinder service) {
//                    MyFXCollectService.MyBinder myBinder = (MyFXCollectService.MyBinder) service;
//                    myFXCollectService = myBinder.getService();
//                }
//
//                @Override
//                public void onServiceDisconnected(ComponentName name) {
//
//                }
//            }, BIND_AUTO_CREATE);//绑定数据服务
        }
    }
    void initData(){

        //]Log.w(TAG, "onCreate: okok2");
        startService(new Intent(this, MyFXTimingService.class));
        //]Log.w(TAG, "onCreate: okok3");
        float[] floats_A=new float[]{44.34F,44.09F,44.15F,43.61F,44.33F,44.83F,45.10F,45.42F,45.84F,46.08F,45.89F,46.03F,45.61F,46.28F,46.28F,46.00F,46.03F,46.41F,46.22F,45.64F,46.21F,46.25F,45.71F,46.45F,45.78F,45.35F,44.03F,44.18F,44.22F,44.57F,43.42F,42.66F,43.13F};
        //]Log.w(TAG, "initDataD0: "+"="+ floats_A.length);
        float[] floats_C=null;

    }
    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for(Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }
    public static String dealResponseResult(InputStream inputStream) {
        String resultData = null;      //存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }

//    public void ccc_002(){




//        Thread t = new Thread(){
//            @Override
//            public void run() {
//                //提交的数据需要进行URL编码，字母和数字编码后都不变
//                String path = "http://192.168.252.1:8080/web/LoginServlet";
//                try {
//                    URL url = new URL(path);
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("POST");
//                    conn.setReadTimeout(5000);
//                    conn.setConnectTimeout(5000);
//
//
//                    //拼接处要提交的字符串
//                    @SuppressWarnings("deprecation")
//                    String data = "name="+URLEncoder.encode(name)+"&password="+pass;
//
//                    //为post添加两行属性
//                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                    conn.setRequestProperty("Content-Length", data.length()+"");
//
//                    //因为post是通过流往服务器提交数据的，所以我们需要设置一个输出流
//                    //设置打开输出流
//                    conn.setDoOutput(true);
//                    //拿到输出流
//                    OutputStream os = conn.getOutputStream();
//                    //使用输出流向服务器提交数据
//                    os.write(data.getBytes());
//
//                    if(conn.getResponseCode() == 200){
//                        InputStream is = conn.getInputStream();
//
//
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        };
//        t.start();
    void ccc_003(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<10;i++)
                    {
                URL url = new URL("https://scanner.tradingview.com/forex/scan");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                //]Log.w(this.toString(), "ccc_003: " );
                //设置连接超时,2000ms
                httpURLConnection.setConnectTimeout(2000);
//设置指定时间内服务器没有返回数据的超时，5000ms
                httpURLConnection.setReadTimeout(5000);
//设置允许输出
                httpURLConnection.setDoOutput(true);
//设置请求的方式
                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setRequestProperty("Host","scanner.tradingview.com");
//                    httpURLConnection.setRequestProperty("User-Agent","User-Agent");
//                    httpURLConnection.setRequestProperty("Accept","*/*");
//                    httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//                    httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
//                    httpURLConnection.setRequestProperty("Referer","https://www.tradingview.com/");
//                    httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//                    httpURLConnection.setRequestProperty("Origin","https://www.tradingview.com");
//                    httpURLConnection.setRequestProperty("Content-Length","1444");
//                    httpURLConnection.setRequestProperty("Connection","keep-alive");
//                    httpURLConnection.setRequestProperty("TE","Trailers");


                PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
                //out.print("{\"symbols\":{\"tickers\":[\"FX_IDC:EURUSD\"],\"query\":{\"types\":[\"forex\"]}},\"columns\":[\"Recommend.Other|1\",\"Recommend.All|1\",\"Recommend.MA|1\",\"RSI|1\",\"RSI[1]|1\",\"Stoch.K|1\",\"Stoch.D|1\",\"Stoch.K[1]|1\",\"Stoch.D[1]|1\",\"CCI20|1\",\"CCI20[1]|1\",\"ADX|1\",\"ADX+DI|1\",\"ADX-DI|1\",\"ADX+DI[1]|1\",\"ADX-DI[1]|1\",\"AO|1\",\"AO[1]|1\",\"Mom|1\",\"Mom[1]|1\",\"MACD.macd|1\",\"MACD.signal|1\",\"Rec.Stoch.RSI|1\",\"Stoch.RSI.K|1\",\"Rec.WR|1\",\"W.R|1\",\"Rec.BBPower|1\",\"BBPower|1\",\"Rec.UO|1\",\"UO|1\",\"EMA5|1\",\"close|1\",\"SMA5|1\",\"EMA10|1\",\"SMA10|1\",\"EMA20|1\",\"SMA20|1\",\"EMA30|1\",\"SMA30|1\",\"EMA50|1\",\"SMA50|1\",\"EMA100|1\",\"SMA100|1\",\"EMA200|1\",\"SMA200|1\",\"Rec.Ichimoku|1\",\"Ichimoku.BLine|1\",\"Rec.VWMA|1\",\"VWMA|1\",\"Rec.HullMA9|1\",\"HullMA9|1\",\"Pivot.M.Classic.S3|1\",\"Pivot.M.Classic.S2|1\",\"Pivot.M.Classic.S1|1\",\"Pivot.M.Classic.Middle|1\",\"Pivot.M.Classic.R1|1\",\"Pivot.M.Classic.R2|1\",\"Pivot.M.Classic.R3|1\",\"Pivot.M.Fibonacci.S3|1\",\"Pivot.M.Fibonacci.S2|1\",\"Pivot.M.Fibonacci.S1|1\",\"Pivot.M.Fibonacci.Middle|1\",\"Pivot.M.Fibonacci.R1|1\",\"Pivot.M.Fibonacci.R2|1\",\"Pivot.M.Fibonacci.R3|1\",\"Pivot.M.Camarilla.S3|1\",\"Pivot.M.Camarilla.S2|1\",\"Pivot.M.Camarilla.S1|1\",\"Pivot.M.Camarilla.Middle|1\",\"Pivot.M.Camarilla.R1|1\",\"Pivot.M.Camarilla.R2|1\",\"Pivot.M.Camarilla.R3|1\",\"Pivot.M.Woodie.S3|1\",\"Pivot.M.Woodie.S2|1\",\"Pivot.M.Woodie.S1|1\",\"Pivot.M.Woodie.Middle|1\",\"Pivot.M.Woodie.R1|1\",\"Pivot.M.Woodie.R2|1\",\"Pivot.M.Woodie.R3|1\",\"Pivot.M.Demark.S1|1\",\"Pivot.M.Demark.Middle|1\",\"Pivot.M.Demark.R1|1\"]}");//写入输出流
                out.print("{\"symbols\":{\"tickers\":[\"FX_IDC:EURUSD\"],\"query\":{\"types\":[\"forex\"]}},\"columns\":[\"EMA5|1\",\"ATR|1\",\"ADX|1\",\"ADX+DI|1\",\"ADX-DI|1\"]}");//写入输出流
                out.flush();//立即刷新

                out.close();
                //]Log.w(this.toString(), "ccc_001B5: " );

                        int code = httpURLConnection.getResponseCode();
                        //]Log.w(this.toString(), "ccc_001A0: " +"="+i+"="+code );
                        if(true||code == 200){
                            InputStream is = httpURLConnection.getInputStream();
                            if(true) {
                                InputStream input = httpURLConnection.getInputStream();
                                InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                                StringBuffer sb1 = new StringBuffer();
                                int ss;
                                while ((ss = reader.read()) != -1) {
                                    sb1.append((char) ss);
                                }
                                //]Log.w(TAG, "ccc_001A3: "+"="+sb1.toString());
                            }
                            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                            StringBuilder result = new StringBuilder();
                            String line;
                            while((line = reader.readLine()) != null) {
                                result.append(line);
                            }
                            //]Log.w(this.toString(), "ccc_001A1: "+"="+(is!=null)+"="+line +"="+result );
                            //连接服务器后，服务器做出响应返回的数据
                            String serverResponse = URLDecoder.decode((result.toString()), "utf-8");
                            //]Log.w(this.toString(), "ccc_001A2: "+"="+serverResponse );
                            is.close();

                            //对返回的数据serverResponse进行操作

                        }
                        Thread.sleep(3000);
                    }


                } catch (Exception e) {
                    //Log.e(this.toString(), e.toString());
                    e.printStackTrace();
                }
            }
        }).start();
    }

//    void ccc_001(){
//        //]Log.w(this.toString(), "ccc_001B0: " );
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //]Log.w(this.toString(), "ccc_001B3: " );
//                try {
//                    if(false)
//                        {
//                            Map<String,Object> result = new HashMap<>();
//                            URL url = null;//请求的URL地址
//                            HttpURLConnection conn = null;
//                            String requestHeader = null;//请求头
//                            byte[] requestBody = null;//请求体
//                            String responseHeader = null;//响应头
//                            byte[] responseBody = null;//响应体
//                            //用POST发送键值对数据
//                            url = new URL("http://192.168.31.200:8080/HttpServer/MyServlet");
//                            conn = (HttpURLConnection) url.openConnection();
//                            //通过setRequestMethod将conn设置成POST方法
//                            conn.setRequestMethod("POST");
//                            //调用conn.setDoOutput()方法以显式开启请求体
//                            conn.setDoOutput(true);
//                            //用setRequestProperty方法设置一个自定义的请求头:action，由于后端判断
//                            conn.setRequestProperty("action", NETWORK_POST_KEY_VALUE);
//                            //获取请求头
//                            requestHeader = getReqeustHeader(conn);
//                            //获取conn的输出流
//                            OutputStream os = conn.getOutputStream();
//                            //获取两个键值对name=孙群和age=27的字节数组，将该字节数组作为请求体
//                            requestBody = new String("name=孙群&age=27").getBytes("UTF-8");
//                            //将请求体写入到conn的输出流中
//                            os.write(requestBody);
//                            //记得调用输出流的flush方法
//                            os.flush();
//                            //关闭输出流
//                            os.close();
//                            //当调用getInputStream方法时才真正将请求体数据上传至服务器
//                            InputStream is = conn.getInputStream();
//                            //获得响应体的字节数组
//                            responseBody = getBytesByInputStream(is);
//                            //获得响应头
//                            responseHeader = getResponseHeader(conn);
//
//                        }
//                    if(false)
//                        {
//                            Map<String, String> params = new HashMap<String, String>();
//                            params.put("pairID", "1");
//                            params.put("period", "60");
//                            params.put("viewType", "normal");
//                            byte[] data =getRequestData(params, "utf-8").toString().getBytes();
//                            try {
//                                URL url = new URL("https://cn.investing.com/instruments/Service/GetTechincalData");
//                                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
//                                httpURLConnection.setConnectTimeout(3000); //设置连接超时时间
//                                httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
//                                httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
//                                httpURLConnection.setRequestMethod("POST"); //设置以Post方式提交数据
//                                httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
//                                //设置请求体的类型是文本类型
//                                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                                //设置请求体的长度
//                                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
//                                //获得输出流，向服务器写入数据
//                                OutputStream outputStream = httpURLConnection.getOutputStream();
//                                outputStream.write(data);
//
//                                int response = httpURLConnection.getResponseCode();            //获得服务器的响应码
//                                //]Log.w(TAG, "runMapA01 "+"="+response );
//                                if(true) {
//                                    InputStream inptStream = httpURLConnection.getInputStream();
//                                    BufferedReader reader = new BufferedReader(new InputStreamReader(inptStream));
//                                    StringBuilder result = new StringBuilder();
//                                    String line;
//                                    //]Log.w(TAG, "runMapA03: "+"="+ (inptStream!=null));
//                                    while((line = reader.readLine()) != null) {
//                                        //]Log.w(TAG, "runMapA04: "+"="+line );
//                                        result.append(line);
//                                    }
//                                    //]Log.w(TAG, "runMapA02: "+"="+ result);
//                                    String string_A= dealResponseResult(inptStream);  //处理服务器的响应结果
//                                    //]Log.w(TAG, "runMapA0 "+"="+ string_A);
//                                }
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    if(true)
//                        {
//                    URL url = new URL("https://cn.investing.com/instruments/Service/GetTechincalData");
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                    //]Log.w(this.toString(), "ccc_001B4: " );
//                    //设置连接超时,2000ms
//                    httpURLConnection.setConnectTimeout(2000);
////设置指定时间内服务器没有返回数据的超时，5000ms
//                    httpURLConnection.setReadTimeout(5000);
////设置允许输出
//                    httpURLConnection.setDoOutput(true);
////设置请求的方式
//                    httpURLConnection.setRequestMethod("POST");
//
//                    PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
//                    out.print("pairID=2&period=60&viewType=normal");//写入输出流
//                    out.flush();//立即刷新
//
//                    out.close();
//                    //]Log.w(this.toString(), "ccc_001B5: " );
//                    int code = httpURLConnection.getResponseCode();
//                    //]Log.w(this.toString(), "ccc_001A0: "+"="+code );
//                    if(true||code == 200){
//                        InputStream is = httpURLConnection.getInputStream();
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//                        StringBuilder result = new StringBuilder();
//                        String line;
//                        while((line = reader.readLine()) != null) {
//                            result.append(line);
//                        }
//                        //]Log.w(this.toString(), "ccc_001A1: "+"="+(is!=null)+"="+line +"="+result );
//                        //连接服务器后，服务器做出响应返回的数据
//                        String serverResponse = URLDecoder.decode((is.toString()), "utf-8");
//                        //]Log.w(this.toString(), "ccc_001A2: "+"="+serverResponse );
//                        is.close();
//
//                        //对返回的数据serverResponse进行操作
//
//                    }
//                        }
//                } catch (Exception e) {
////                    //Log.e(this.toString(), e.toString());
////                    e.printStackTrace();
////                }
//            }
//        }).start();
//        //]Log.w(this.toString(), "ccc_001B1: " );
//
//    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    /**
     *监听 应用程序 配置信息的改变，如屏幕旋转等
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    /**
     *通知 应用程序 当前内存使用情况（以内存级别进行识别）
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
    /**
     *注册和注销 ComponentCallbacks2回调接口
     */
    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        super.registerComponentCallbacks(callback);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        super.unregisterComponentCallbacks(callback);
    }
    /**
     *注册 / 注销对 应用程序内 所有Activity的生命周期监听
     */
    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
    }

    @Override
    public void registerOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        super.registerOnProvideAssistDataListener(callback);
    }

    @Override
    public void unregisterOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        super.unregisterOnProvideAssistDataListener(callback);
    }
}
