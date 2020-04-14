package com.example.app_005.Internet;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class InternetTool {
    Context context;
    public InternetTool(Context context_A){
        context=context_A;
        //CrC_WebView();
        new Thread(new Runnable() {
            @Override
            public void run() {
               String string_A=getHttpPostTradingDataA();
                //]Log.w(TAG, "InternetToolF0 "+"="+string_A.length()+"="+ string_A.substring(string_A.length()-300,string_A.length()-1));
               //sendGetRuquestWithOkHttp();
            }
        }).start();
    }
    String getHttpPostTradingDataA(){
        String string_rt=null;
        try {
            URL url = new URL("https://www.mgstage.com/search/search.php?search_word=&sort=popular&list_cnt=120&disp_type=thumb&search_range=latest");
            final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(2000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(5000);//设置指定时间内服务器没有返回数据的超时，5000ms
            httpURLConnection.setDoOutput(true);//设置允许输出
            httpURLConnection.setRequestMethod("GET");//设置请求的方式
            //httpURLConnection.setRequestProperty("Cookie","adc=1");

//            PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream());
//            out.print(string_Data);//写入输出流
//            out.flush();//立即刷新
//            out.close();

            int code = httpURLConnection.getResponseCode();
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                   // String total = IOUtils.toString(input);
                    ////]Log.w(TAG, "getHttpPostTradingDataAG0: "+"="+total );
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                    StringBuffer sb1 = new StringBuffer();
                    int ss;
                    while ((ss = reader.read()) != -1) {
                        sb1.append((char) ss);
                    }
                    string_rt=sb1.toString();
                    input.close();
                }

            }
        }catch (Exception e) {
            //]Log.w(TAG, "getHttpPostTradingDataF0: "+"="+e.toString() );
            e.printStackTrace();
        }

        return string_rt;
    }
    String getHttpPostTradingData(){
        String string_rt=null;
        try {
            URL url = new URL("https://www.kwongwah.com.my/20191203/%E5%8F%91%E5%B1%95%E7%A5%9E%E9%80%9F-%E8%A1%8C%E5%86%85%E6%83%8A%E8%AE%B6-%E5%8D%8E%E4%B8%BA%E6%96%B0%E6%89%8B%E6%9C%BA%E4%B8%8D%E5%90%AB%E7%BE%8E%E6%99%B6%E7%89%87/");
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(2000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(5000);//设置指定时间内服务器没有返回数据的超时，5000ms
             httpURLConnection.setDoOutput(true);//设置允许输出
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");//设置请求的方式
//            httpURLConnection.setRequestProperty("Host", "www.mgstage.com");
//            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0");
//            httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//            httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//            httpURLConnection.setRequestProperty("Accept-Encoding", " deflate");
//            httpURLConnection.setRequestProperty("Connection", "keep-alive");
//            httpURLConnection.setRequestProperty("Referer", "https://www.mgstage.com/search/search.php?search_word=&sort=popular&list_cnt=120&disp_type=thumb&search_range=latest&is_dvd_product=0");
//            httpURLConnection.setRequestProperty("Cookie", "uuid=aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; adc=1; _gid=GA1.2.1178591849.1575386319; PHPSESSID=i4v5gaabghtchatv8e261fle61; _gat_UA-58252858-1=1");
//            httpURLConnection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            //String string_Cookie="coc=1; uuid=aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; PHPSESSID=h1le6lp8d0bvnph0o5ekuau936; adc=1";

            //httpURLConnection.setRequestProperty("Cookie", string_Cookie);





//            CookieManager manager = new CookieManager();
//            CookieHandler.setDefault(manager);

//            PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream() );
//            String string_G="search_word=\n" +
//                    "sort=new\n" +
//                    "list_cnt=120\n" +
//                    "disp_type=thumb\n" +
//                    "search_range=latest\n" +
//                    "is_dvd_product=0";
//            out.print(string_G);//写入输出流
//            out.flush();//立即刷新
//            out.close();

            int code = httpURLConnection.getResponseCode();
            //]Log.w(TAG, "getHttpPostTradingDataDD0: "+"="+httpURLConnection.getRequestMethod() +"="+code );
            if(code == 200){
                if(true) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                    //readStream(input);
                    StringBuffer sb1 = new StringBuffer();
//                    if(true)
//                        {
//                            byte[] b= new byte[1024];
//                            int count = 0;
//                            if(input.available()>0 == false){
//                                continue;
//                            }else{
//                                Thread.sleep(200);
//                            }
//                            count = input.read(b);
//                        }
                    int ss=0;
                    char[] buffer = new char[1024];
                    while (ss != -1) {
                        ss = reader.read(buffer);
                        //]Log.w(TAG, "getHttpPostTradingDataR0: "+"="+ss +"="+sb1.length() );
                        sb1.append(buffer);

                    }
//                    Thread.sleep(500);
//                    while ((ss = reader.read()) != -1) {
//                        sb1.append((char) ss);
//                    }
//                    Thread.sleep(500);
//                    while ((ss = reader.read()) != -1) {
//                        sb1.append((char) ss);
//                    }
                    string_rt=sb1.toString();
                    input.close();
                }

            }
        }catch (Exception e) {
            //]Log.w(TAG, "getHttpPostTradingDataF0: "+"="+e.toString() );
            e.printStackTrace();
        }

        return string_rt;
    }
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        //]Log.w(TAG, "readStreamA: "+"="+outSteam.toString() );
        return outSteam.toByteArray();
    }
    private void sendGetRuquestWithOkHttp() {
        //创建okHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //创建request,首先要有一个url
        Request request=new Request.Builder().url("https://shecool.net/av-wiki/s-cute/")
//                .header("Host","www.mgstage.com")
//                .header("User-Agent"," Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0")
//                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
//                .header("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
////                .header("Accept-Encoding","gzip, deflate, br")
//                .header("Connection","keep-alive")
//                .header("Cookie","uuid=aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; adc=1; _gid=GA1.2.1178591849.1575386319; PHPSESSID=i4v5gaabghtchatv8e261fle61; _gat_UA-58252858-1=1")
//                .header("Upgrade-Insecure-Requests","1")
//                .header("Cache-Control","max-age=0")
                .build();
        //通过request的对象去构造得到一个Call对象，
        // 类似于将你的请求封装成了任务，既然是任务，就会有execute()和cancel()等方法。
        Call call=okHttpClient.newCall(request);
        //以异步的方式去执行请求,调用的是call.enqueue，将call加入调度队列，
        // 然后等待任务执行完成，我们在Callback中即可得到结果。
        //]Log.w(TAG, "DDOKDLonResponseS0: "+"="+"Start" );
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败的处理
                //]Log.w(TAG, "DDOKDLonResponseS0: "+"="+ false +"="+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功返回结果
                //如果希望返回的是字符串
                final String responseData=response.body().string();
                //如果希望返回的是二进制字节数组
                //byte[] responseBytes=response.body().bytes();
                //如果希望返回的是inputStream,有inputStream我们就可以通过IO的方式写文件.
                InputStream responseStream=response.body().byteStream();
                //注意，此时的线程不是ui线程，
                // 如果此时我们要用返回的数据进行ui更新，操作控件，就要使用相关方法
                String string_Data=responseData;
                //]Log.w(TAG, "DDOKDLonResponseS0: "+"="+ string_Data);

            }
        });
        //上面用到的enqueue是异步的方式，当然也可以同步，
        //同步--Call有一个execute()方法，你也可以直接调用call.execute()通过返回一个Response。
//    try {
//    Response response = call.execute();
//    if(response.isSuccessful()){
//    //同步方式下得到返回结果
//    String responseByExecute=response.body().string();
//        //]Log.w(TAG, "DDOKDLonResponseS0B: "+"="+ responseByExecute);
//    }
//     } catch (IOException e) {
//        //]Log.w(TAG, "DDOKDLonResponseS0B: "+"="+ false +"="+e.toString());
//          e.printStackTrace();
//      }
}
    private WebView webView;

    /**
     *获取动态网页
     */
    void CrC_WebView(){
        webView = new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }
        });
        webView.loadUrl("https://shecool.net/av-wiki/s-cute/");

    }
    /**
     ////     * 返回动态网页
     ////     */
    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html)
        {
            //]Log.w(TAG, "CrC_2: "+html);
            // 注意啦，此处就是执行了js以后 的网页源码
        }
    }
}
