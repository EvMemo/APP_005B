//package com.example.app_005.Service;
//
//import android.app.Service;
//import android.content.ComponentName;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.Binder;
//import android.os.IBinder;
//
//import com.example.app_005.MainApplication;
//import com.example.app_005.Tool.FXCollectTool;
//import com.example.app_005.Tool.FXDataConnect;
//import com.example.app_005.Tool.FXDataPool;
//
///**
// * 外汇收集服务任务中心FXMissionCentre
// */
//public class MyFXCollectService extends Service {
//    private MyBinder binder = new MyBinder();
//    private MyFXDataService myFXDataServiceA;//外汇数据服务
//    //FXDataConnect fxDataConnect;
//    private ServiceConnection conn = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder binder) {
//            MyFXDataService.MyBinder myBinder = (MyFXDataService.MyBinder)binder;
//            myFXDataServiceA = myBinder.getService();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//        }
//    };
//    public FXCollectTool fxCollectTool;
//    public FXCollectTool.FXQuoCollect fxQuoCollect;
//    public FXCollectTool.FXNewsCollect fxNewsCollect;
//    public FXDataConnect.QuoConnect quoConnect;
//    public FXDataConnect.NewsConnect newsConnect;
//    FXDataConnect.TimeConnect timeConnect;
//    public MyFXCollectService() {
//    }
//    public class MyBinder extends Binder {
//        public MyFXCollectService getService(){
//            return MyFXCollectService.this;
//        }
//
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//       return binder;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        init_Service();
//        init_Data();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        //CrC_WebView();
//        return super.onStartCommand(intent, flags, startId);
//    }
//    void init_Data(){
//        MainApplication mainApplication_A=((MainApplication) getApplicationContext().getApplicationContext());
//        quoConnect = mainApplication_A.fxDataConnect.getQuoConnect();
//        newsConnect = mainApplication_A.fxDataConnect.getNewsConnect();
//        timeConnect=mainApplication_A.fxDataConnect.getTimeConnect();
//        fxCollectTool=new FXCollectTool(this,mainApplication_A.fxDataConnect);
//        fxQuoCollect=fxCollectTool.new FXQuoCollect(getApplicationContext(),quoConnect);
//        fxNewsCollect=fxCollectTool.new FXNewsCollect(getApplicationContext(),newsConnect);
//    }
//    void init_Service(){
//        startService(new Intent(MyFXCollectService.this,MyFXTimingService.class));//绑定数据服务
//        bindService(new Intent(MyFXCollectService.this,MyFXDataService.class),conn,BIND_AUTO_CREATE);//绑定数据服务
////        calendarA=Calendar.getInstance();
////        Log.d(TAG, "init_Service_0: "+calendarA.getTimeInMillis() +"="+calendarA.get(Calendar.DATE) +"="+calendarA.get(Calendar.MONTH) );
////        calendarA.set(Calendar.DATE,1);
////        calendarA.set(Calendar.SECOND,0);
////        calendarA.set(Calendar.MILLISECOND,0);
////        Log.d(TAG, "init_Service_2: "+calendarA.getTimeInMillis() +"="+calendarA.get(Calendar.DATE) +"="+calendarA.get(Calendar.MONTH));
////        longA=calendarA.getTimeInMillis();
//
//    }
//    public void Start(){}
//
//
//    /**
//     *请求收集报价
//     */
//    public void PuC_reqCollectQuoData(){
//        //fxQuoCollect.PuC_reqCollertFXQuoData();
//    }
//    /**
//     *请求收集新闻/xīn wén/
//     */
//    public void PuC_reqCollectNewsData(){
//        fxNewsCollect.PuC_reqCollectDaiFXNewsData();
//    }
//
//
//    /**
//     * 将收集到的报价数据传给数据服务
//     */
//    public void PrC_giveFXQuoDataToDataService(FXDataPool.FXQuo.QuoData[] quoDatasAA, long longAA){
//        //quoConnect.assignQuoData();
////        if(myFXDataServiceA!=null)
////            {
////                myFXDataServiceA.PuC_asiCollectFXQuotedData(quoDatasAA,longAA);
////            }
//
//    }
//    /**
//     * 将收集到的新闻数据传给数据服务
//     */
//    public void PrC_giveDaiFXNewsDataToDataService(FXDataPool.FXQuo.QuoData[] quoDatasAA, long longAA){
//        if(myFXDataServiceA!=null)
//        {
//           // myFXDataServiceA.PuC_asiCollectFXQuotedData(quoDatasAA,longAA);
//        }
//
//    }
//
////    /**
////     * 开始收集报价数据
////     */
////    void PrC_CollectFXQuotedData(){
////        final String stringUrl="https://webrates.truefx.com/rates/connect.html?f=html";
////        //Log.d(this.toString(), "PC_001: "+stringUrl);
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                try {
////                    //Log.d(this.toString(), "PC_001_1: ");
////                    //CrC_FXJsoup();
////                    CrC_FXString(getHtml(stringUrl));
////                    //PrC_FXDataJsoup(stringUrl);
////
////                } catch (Exception e) {
////                    Log.e(this.toString(),e.toString());
////                    e.printStackTrace();
////                }
////            } }).start();
////
////    }
////    /**
////     *
////     */
////    private void PrC_CollectFXSkillData(){
////        final String stringUrl="https://cn.investing.com/technical/indicators";
////        WebView webView_A=new WebView(getApplicationContext());
////        webView_A.loadUrl(stringUrl);
////        WebSettings settings = webView_A.getSettings();
////        settings.setJavaScriptEnabled(true);
////       // String string_A=""
////       // final String js = "javascript:document.getElementById('name').value = '" + name + "';document.getElementById('number').value='" + number + "';";
////
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                try {
////                    //Log.d(this.toString(), "PC_001_1: ");
////                    //CrC_FXJsoup();
////                    CrC_FXString(getHtml(stringUrl));
////                    //PrC_FXDataJsoup(stringUrl);
////
////                } catch (Exception e) {
////                    Log.e(this.toString(),e.toString());
////                    e.printStackTrace();
////                }
////            } }).start();
////
////    }
//
////    /**
////     * 获取静态网页源码
////     *
////     * @param path the path
////     * @return the html
////     * @throws Exception the exception
////     */
////    public static String getHtml(String path) throws Exception {
////        URL url = new URL(path);
////        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
////        conn.setRequestMethod("GET");
////        conn.setConnectTimeout(5 * 1000);
////        InputStream inStream = conn.getInputStream();//通过输入流获取html数据
////        byte[] data = readInputStream(inStream);//得到html的二进制数据
////        String html = new String(data, "UTF-8");
////        return html;
////    }
//
////    /**
////     * 转换InputStream数据
////     *
////     * @param inStream the in stream
////     * @return the byte [ ]
////     * @throws Exception the exception
////     */
////    public static byte[] readInputStream(InputStream inStream) throws Exception{
////        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
////        byte[] buffer = new byte[1024];
////        int len = 0;
////        while( (len=inStream.read(buffer)) != -1 ){
////            outStream.write(buffer, 0, len);
////        }
////        inStream.close();
////        return outStream.toByteArray();
////    }
//
//
//
////    /**
////     * 将网页转换成报价数据
////     *
////     * @param stringAA the string aa
////     */
////    void CrC_FXString(String stringAA){
////        Calendar calendar_A=Calendar.getInstance();
////        calendar_A.set(Calendar.SECOND,0);
////        calendar_A.set(Calendar.MILLISECOND,0);
////        long long_A=calendar_A.getTimeInMillis();//-longA;
////
////        ClC_FXQuotedData01 fxQuotedData_A01;
////        String string_A=stringAA;
////        StringBuilder stringBuilder_A =new StringBuilder (stringAA);
////
////        //int int_A=stringBuilder_A.indexOf("<tr>")+4;
////        boolean boolean_A =true;
////        ArrayList<int[]> arrayList_A=new ArrayList<>();
////        int[] ints_A=new int[]{0,0};
////        while (boolean_A){
////            ints_A[0]=stringBuilder_A.indexOf("<tr>",ints_A[0]);
////            if(ints_A[0]==-1) {Log.d(TAG, "CrC_FXString_2");break;}
////
////            ints_A[1]=stringBuilder_A.indexOf("</tr>",ints_A[0]+4);
////            if(ints_A[1]==-1) {Log.d(TAG, "CrC_FXString_2");break;}
////            Log.d(TAG, "CrC_FXString_1: "+ints_A[0] +"="+ints_A[1]);
////            arrayList_A.add((new int[]{ints_A[0],ints_A[1]}));
////            ints_A[0]=ints_A[1]+5;
////            Log.d(TAG, "CrC_FXString_1A: "+arrayList_A.size());
////        }
////        Log.d(TAG, "CrC_FXString_0: "+arrayList_A.size());
////
////        if(arrayList_A.size()!=0)
////            {
////                fxQuotedData_A01=new ClC_FXQuotedData01(long_A,arrayList_A.size());
////
////                ArrayList<String[]> arrayList_B=new ArrayList<>();
////                for(int i=0;i<arrayList_A.size();i++)
////                    {
////                        fxQuotedData_A01.stringssA[i]=(CrC_FXString_1(stringBuilder_A,arrayList_A.get(i)));
////                    }
////                PrO_FXQuotedDataForDataService(fxQuotedData_A01,long_A);
////
////            }else {
////            Log.e(TAG, "CrC_FXString: " );
////        }
////
////        //Log.d(TAG, "CrC_FXString_1: "+arrayList_A.get(0)[0] +"="+arrayList_A.get(0)[1]);
////
////    }
////
////    /**
////     * 转换成报价数据
////     *
////     * @param stringBuilderAA the string builder aa
////     * @param intsAA          the ints aa
////     * @return the string [ ]
////     */
////    String[] CrC_FXString_1(StringBuilder stringBuilderAA,int[] intsAA ){
////        String[] strings_A=new String[4];//名,时,卖,买,小,大,开
////        String string_A=stringBuilderAA.substring(intsAA[0]+4,intsAA[1]);
////        Log.d(TAG, "CrC_FXString_4: "+string_A);
////        int[] ints_B=new int[]{0,0};
////
////        for(int i=0;i<4;i++)
////            {
////                ints_B[0]=string_A.indexOf("<td>",ints_B[0]);
////                ints_B[1]=string_A.indexOf("</td>",ints_B[0]+4);
////                Log.d(TAG, "CrC_FXString_5:"+string_A.substring(ints_B[0]+4,ints_B[1]) +"="+i);
////               switch(i){
////                   case 0:
////                       strings_A[i]=string_A.substring(ints_B[0]+4,ints_B[1]);
////                       break;
////                   case 2:
////                   case 3:
////                       String string_B=string_A.substring(ints_B[0]+4,ints_B[1]);
////                       ints_B[0]=ints_B[1]+5;
////                       ints_B[0]=string_A.indexOf("<td>",ints_B[0]);
////                       ints_B[1]=string_A.indexOf("</td>",ints_B[0]+4);
////                       string_B+=string_A.substring(ints_B[0]+4,ints_B[1]);
////                       strings_A[i]=(string_B);
////                       break;
////                   default:
////                       //int iny=1559940300020;
////                       strings_A[i]=string_A.substring(ints_B[0]+4,ints_B[1]);
////                       break;
////               }
////                ints_B[0]=ints_B[1]+5;
////
////                }
////
////        Log.d(TAG, "CrC_FXString_6: "+strings_A[0] +"="+strings_A[1]+"="+strings_A[2]+"="+strings_A[3]+"="+strings_A[4]+"="+strings_A[5]+"="+strings_A[6]);
////
////
////        return strings_A;
////
////    }
////
////    /**
////     * 调查对应货币
////     *
////     * @param stringAA the string aa
////     * @return the int
////     */
////    int CrC_FXString_2(String stringAA){
////
////        switch(stringAA){
////            case "EUR/USD":
////                return 0;
////            case "USD/JPY":
////                return 1;
////            case "GBP/USD":
////                return 2;
////            case "EUR/GBP":
////                return 3;
////            case "USD/CHF":
////                return 4;
////            case "EUR/JPY":
////                return 5;
////            case "EUR/CHF":
////                return 6;
////            case "USD/CAD":
////                return 7;
////            case "AUD/USD":
////                return 8;
////            case "GBP/JPY":
////                return 9;
////            default:
////                return -1;
////        }
////    }
////
////
////    /**
////     * 获取静态网页DOM XML
////     *
////     * @param stringAA the string aa
////     * @return the document
////     */
////    Document PrC_FXJsoup(String stringAA){
////        try {
////            //从一个URL加载一个Document对象。
////            Document doc = Jsoup.connect(stringAA).get();
////            //选择“美食天下”所在节点
////            //Elements elements = doc.select("tbody");
////            //elements=elements.last().children();
////            ////打印 <a>标签里面的title
////            //Log.d("mytag",elements.size()+elements.first().tagName());
////            //Log.d("mytag_2",element.tagName());
////            return doc;
////        }catch(Exception e) {
////            Log.d("mytag", e.toString());
////        }
////        return null;
////    }
////
////    /**
////     * 开始收集新闻数据
////     */
////    void Prc_collectNewsInDailyFX(){
////        int int_A=PrC_getDailyFXRecordLastNewsID();//获取DailyFX记录的最后新闻ID
////       //ArrayList<NewsData> nnewsData_A=PrC_getNewsInDailyFXHttps();
////    }
////
////    /**
////     * 根据最后新闻ID，收集新闻数据
////     *
////     * @param intAA the int aa
////     * @return the array list
////     */
////    ArrayList<NewsData> PrC_getNewsInDailyFXHttps(int intAA){
////        ArrayList<NewsData> nnewsData_A=new ArrayList();
////        String string_A="";
////        Document doc_A=PrC_FXJsoup(string_A);
////        if(PrC_CheckNewsInDailyFX(doc_A,intAA)){
////
////        }else {
////
////        }
////        return nnewsData_A;
////    }
////
////    /**
////     * 调查DailyFX最后记录是否还在参数网页内
////     *
////     * @param documentAA the document aa
////     * @param intAA      the int aa
////     * @return the boolean
////     */
////    boolean PrC_CheckNewsInDailyFX(Document documentAA,int intAA){
////        Document doc = documentAA;
////        Elements elements = doc.select(".jsdfx-feed-list.list-unstyled");
////        elements=elements.select("li.jsdfx-feed-item.dfx-real-time-news.jsdfx-feed-item-type-livenews");
////        String string_A=elements.last().attr("data-id");
////        string_A=string_A.substring(1);
////        int int_A=Integer.parseInt(string_A);
////        if(int_A<=intAA)
////            {
////                return true;
////
////            }else {
////            return false;
////        }
////    }
////
////    /**
////     * 将Xml转换成新闻数据
////     *
////     * @param elementsAA the elements aa
////     * @param intAA      the int aa
////     * @return the array list
////     */
////    ArrayList<NewsData> Prc_GrabDailyFXNewsData(Elements elementsAA,int intAA){
////        return null;
////    }
////
////    /**
////     * 获取DailyFX记录的最后新闻ID
////     */
////    int PrC_getDailyFXRecordLastNewsID(){
////        SharedPreferences pref = getSharedPreferences("DailyFXN",MODE_PRIVATE);
////        int int_A = pref.getInt("NewsLastID",-1);
////        return int_A;
////
////
////    }
////
////    /**
////     * 存储DailyFX记录的最后新闻ID
////     *
////     * @param intAA the int aa
////     */
////    void PrC_setDailyFXRecordLastNewsID(int intAA){
////        SharedPreferences.Editor editor = getSharedPreferences("DailyFXN",MODE_PRIVATE).edit();
////        editor.putInt("NewsLastID",intAA);
////        editor.commit();
////
////    }
////
////    /**
////     * 新闻数据
////     */
////    public class NewsData{
////         long longTimeA;//时间
////         int intCurrencyA;//ID
////         String stringNewsA;//新闻标题
////        String stringNewsB;//新闻内容
////        //NewsCla newsClaA;//内容数据
//////        public class NewsCla{
//////            int intLoad;//内容类型
//////        }
//////        public class NewsOne extends NewsCla{
//////            public String stringA;//新闻内容
//////
//////        }
//////        public class NewsDou extends NewsCla{
//////            public String stringA;//新闻内容
//////
//////
//////        }
////    }
////
////    private WebView webView;
////
////    /**
////     *获取动态网页
////     */
////    void CrC_WebView(){
////        webView = new WebView(getApplicationContext());
////        webView.getSettings().setJavaScriptEnabled(true);
////        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
////        webView.setWebViewClient(new WebViewClient(){
////            @Override
////            public void onPageFinished(WebView view, String url) {
////                webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
////            }
////        });
////        webView.loadUrl("https://www.investing.com/technical/technical-analysis");
////
////    }
////
////    /**
////     * 返回动态网页
////     */
////    class MyJavaScriptInterface
////    {
////        @JavascriptInterface
////        @SuppressWarnings("unused")
////        public void processHTML(String html)
////        {
////            Log.d(TAG, "CrC_2: "+html);
////            // 注意啦，此处就是执行了js以后 的网页源码
////        }
////    }
//}
////OBD0SKAK68YG6B75
////eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InBqbWVtbzAwMUBnbWFpbC5jb20iLCJpYXQiOjE1NjAwMDU4MzQsImV4cCI6MTU5MTU0MTgzNH0.RFxaArZdddBfZhgN8UTx9HqQd5Ge5SHkLwQyNjj6kN0