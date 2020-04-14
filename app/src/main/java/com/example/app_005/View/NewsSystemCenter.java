package com.example.app_005.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Class.SkillNewsName;
import com.example.app_005.Data.NewsOD;
import com.example.app_005.Interface.OnUpdateNewsData;
import com.example.app_005.Interface.OnUpdateTime;
import com.example.app_005.Internet.FortradeCollectTool;
import com.example.app_005.MainApplication;
import com.example.app_005.R;
import com.example.app_005.Tool.FXDataConnect;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.TimeOD;

import java.util.ArrayList;

public class NewsSystemCenter implements OnUpdateTime , OnUpdateNewsData {
   // public RecyclerView recyclerView;
   // MyNewsRecyclerAdapter myNewsRecyclerAdapter;
    //NewsOD.NewsBreakingPool newsBreakingPool;
    NewsRecycleView newsRecycleView;
    //NewsNewView newsNewView;
    NewsBreakingPool newsBreakingPool;
    ImageButton newsTextBut;
    NewsTextView newsTextView;
    RecordNewsTextSyster recordNewsTextSyster;
    //ImageButton imageButton;
    //ImageView imageView;
    Context context;
    NewsOD newsOD;
    public FXDataConnect.TimeConnect timeConnect;
    public TimeOD timeODNow;
    SkillNewsName[] skillNewsNames;
    int[][] intSkillMode;
    //FXDataPool.FXNews.FXNewsCacha fxNewsCacha;
    //NewsDataOD newsDataOD;
    //NewsDataRequestTool newsDataRequestTool;
    //记录原始窗口高度
    private int mWindowHeight = 0;
    private ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            Rect r = new Rect();
            //获取当前窗口实际的可见区域
            Activity activity_A=((Activity)context);
            activity_A.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
            int height = r.height();
            //]Log.w(this.toString(), "onGlobalLayout: E0"+"="+ mWindowHeight);
            if (mWindowHeight == 0) {
                //一般情况下，这是原始的窗口高度
                mWindowHeight = height;
            } else {
                if (mWindowHeight != height) {
                    //两次窗口高度相减，就是软键盘高度
                    int softKeyboardHeight = mWindowHeight - height;
                    //]Log.w(this.toString(),"SoftKeyboard height = " + softKeyboardHeight);
                }
            }
        }
    };
    public NewsSystemCenter(Context contextAA, NewsRecycleView newsRecycleView_A,  View view_A){
        //]Log.w(this.toString(), "onGlobalLayout: E0a"+"="+ mWindowHeight);
        this.context=contextAA;
        ((Activity)context).getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(mGlobalLayoutListener);
        newsRecycleView=newsRecycleView_A;
        //newsNewView=newsNewView_A;
       // imageButton=view_A.findViewById(R.id.fra002_img_but_01);
        //imageView=view_A.findViewById(R.id.fra002_imgView_01);
        newsTextView=view_A.findViewById(R.id.fra002_newsTextView_001);
        newsTextBut=view_A.findViewById(R.id.fra002_newsTextBut_001);
        recordNewsTextSyster=new RecordNewsTextSyster(view_A);
//        //imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(imageButton.isActivated())
//                    {
//                        imageButton.setActivated(false);
//                        imageView.setVisibility(View.INVISIBLE);
//                        //newsNewView.setVisibility(View.INVISIBLE);
//
//                    }else {
//                    imageButton.setActivated(true);
//                    imageView.setVisibility(View.VISIBLE);
//                    //newsNewView.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//        imageButton.setActivated(false);
//        imageView.setVisibility(View.GONE);
//        //newsNewView.setVisibility(View.GONE);
//        imageButton.setVisibility(View.GONE);
        context=contextAA;
        MainApplication mainApplication_A=((MainApplication)contextAA.getApplicationContext());
        timeConnect=mainApplication_A.fxDataConnect.getTimeConnect(this);
        timeODNow=timeConnect.getTimeNew();
        newsOD=new NewsOD(mainApplication_A.fxDataConnect,timeConnect.getTimeNew(),this);
        newsBreakingPool=new NewsBreakingPool(newsOD,mainApplication_A.fxDataConnect,timeODNow);
        newsBreakingPool.newsRecycleView=newsRecycleView;
        //newsBreakingPool.newsNewView=newsNewView;
        newsBreakingPool.newsTextView=newsTextView;
        newsBreakingPool.newsTextBut=newsTextBut;
        newsBreakingPool.initNewsTextBut();
        newsBreakingPool.newsTextView.setVisibility(View.INVISIBLE);
        newsBreakingPool.newsTextBut.setVisibility(View.INVISIBLE);
        newsBreakingPool.newsRecycleView.setNewsBreakingPool(newsBreakingPool);
       // newsBreakingPool.newsNewView.setNewsBreakingPool(newsBreakingPool);
        newsBreakingPool.reqRefreshNowNewsData();
        newsBreakingPool.reqAddNewNewData();



        PuC_initData();
       // PuC_initRecyclerView();
        //newsDataOD.skillNewsName=SkillNewsName.DaiFX即时新闻;
       // newsDataOD.PuC_asgData(0,MyFXTimingService.ST_titleTime.getTimeNow());
    }
    /**
     *
     */
    public void PuC_initData(){
        //fxNewsCacha=MyFXDataService.ST_fxDataPool.fxNews.new FXNewsCacha(MyFXDataService.ST_fxDataPool.fxNews,2);
        //newsDataOD=new NewsDataOD(recyclerView,MyFXTimingService.ST_titleTime.getTimeNow());
        //newsDataRequestTool=new NewsDataRequestTool();

        //skillNewsName=SkillNewsName.DailyFX;
        skillNewsNames=null;
        intSkillMode=null;

        //newsBreakingPool=newsOD.ccNewsData(timeODNow);
       // newsBreakingPool.ccRefreshNewsDataClass(null,null);
        //PuC_initRecyclerView(newsBreakingPool);
    }
    public void setSkillNewsNames(SkillNewsName[] skillNewsNames_A){
        skillNewsNames=skillNewsNames_A;
    }
    public void setintSkillMode(int[][] intSkillMode_A){
        intSkillMode=intSkillMode_A;
    }
    /**
     *刷新
     */
    public void refreshNewsDataMode(){
        //newsBreakingPool.ccRefreshNewsDataClass(skillNewsNames,intSkillMode);
        //newsRecycleView.
    }
    /**
     *刷新
     */
    public void refreshNewsDataTime(){
        //newsBreakingPool =newsOD.ccNewsData(timeODNow);
        //newsBreakingPool.ccRefreshNewsDataClass(skillNewsNames,intSkillMode);
        //myNewsRecyclerAdapter.setRefreshNewsData(newsBreakingPool);
    }
    public void addRecyclerUIData(){
        if(newsBreakingPool!=null)
            {
                newsBreakingPool.ccNewNewsDataClass();
//                newsNewView.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });
//                recyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        myNewsRecyclerAdapter.notifyItemRangeChanged(0, myNewsRecyclerAdapter.getItemCount());
//                    }
//                });

            }

    }
    void updateNewNewsData(){
        addRecyclerUIData();
    }
    void updateNewTimeNewsDay(){
        TimeOD timeOD_A=timeConnect.getTimeNew();
        timeODNow=timeOD_A;
        refreshNewsDataTime();
    }
    void nextDayNewsData(){
       // timeODNow= timeODNow.PuC_getCrTimeOD(-1);
        //NewsOD.NewsBreakingPool newsBreakingPool_A = newsOD.ccNewsData(timeODNow);
        //newsBreakingPool_A.ccRefreshNewsDataClass(skillNewsNames,intSkillMode);
        //myNewsRecyclerAdapter.addNextNewsData(newsBreakingPool);
    }


    /**
     *
     */
//    public void PuC_initRecyclerView(NewsOD.NewsBreakingPool newsBreakingPool_A){
//       //
//
//
////        myNewsRecyclerAdapter=new MyNewsRecyclerAdapter(context,newsBreakingPool_A);
////        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
////        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
////        recyclerView.setAdapter(myNewsRecyclerAdapter);
////        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
////            @Override
////            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
////
////                Toast.makeText(context, "单击",
////                        Toast.LENGTH_SHORT).show();
////            }
////
////            @Override
////            public void onItemLongClick(RecyclerView.ViewHolder viewHolder) {
////
////                MyNewsRecyclerAdapter.MyViewHolder myViewHolder=(MyNewsRecyclerAdapter.MyViewHolder)viewHolder;
////                if(myViewHolder.newsBreakingData.boolMoreText)
////                {
////                    Toast.makeText(context, "长按0",
////                            Toast.LENGTH_SHORT).show();
////                }else {
////                    Toast.makeText(context, "长按1",
////                            Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
//    }

    @Override
    public void onUpdateTime(TimeOD timeODAA) {

    }

    @Override
    public void onUpdateNewDay(TimeOD timeODAA) {
        updateNewTimeNewsDay();

    }

    @Override
    public void onUpdateNewsData(int int_Num) {
        //]Log.w(this.toString(), "onUpdateNewsDataD0: `"+"="+int_Num );
        switch(int_Num){
            case 0:
                //updateNewNewsData();
                break;
            default:
                break;
        }
    }

    @Override
    public void onUpdateNewsData(NewsDataClass[] newsDataClasses_A) {

    }

    //    public class MyNewsRecyclerAdapter extends RecyclerView.Adapter<MyNewsRecyclerAdapter.MyViewHolder> {
//        //private List<NewsBreakingData> mDatas;
//        ArrayList<TimeOD> timeODS;
//        public NewsOD.NewsBreakingPool newsBreakingPool;
//        public NewsDataClass dataClass;
//        private Context mContext;
//        private LayoutInflater inflater;
//        View view;
//        QuosSystemCenter.RecyclerLineData lineData;
//        public MyNewsRecyclerAdapter(Context context, NewsOD.NewsBreakingPool newsBreakingPool_A){
//            //]Log.w(this.toString(), "MyNewsRecyclerAdapterF0: "+"="+ (newsBreakingPool_A!=null));
//            this. mContext=context;
//           // this. newsBreakingPools=new ArrayList<>(2);
//            newsBreakingPool=newsBreakingPool_A;
//            inflater=LayoutInflater. from(mContext);
//        }
//
//
//        @NonNull
//        @Override
//        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            View view = inflater.inflate(R.layout.news_text_001,viewGroup, false);
//            MyViewHolder holder= new MyViewHolder(view);
//            return holder;
//        }
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, final int position) {
//           NewsDataClass dataClass=newsBreakingPool.newsDataClassesNow.get(position);
//            holder.nameView.setText(dataClass.stringTitle);
////            FXDataPool.FXNews.NewsData newsData_A =PrC_caGetDataPosition(position);
////            if(newsData_A.content!=null)
////            {
////                holder.nameView.setText( newsData_A.name+"...");
////            }else {
////                holder.nameView.setText( newsData_A.name);
////            }
////
////            if(newsData_A.isSeen)
////            {
////                holder.nameView.setTextColor(Color.parseColor("#919191"));
////            }else {
////                holder.nameView.setTextColor(Color.parseColor("#333333"));
////
////            }
////
////            TimeOD timeOD_A=newsData_A.timeODNow;
////            String string_A=timeOD_A.intsA[0]+"年"+timeOD_A.intsA[1]+"月"+timeOD_A.intsA[2]+"日 "+timeOD_A.intsA[3]+":"+timeOD_A.intsA[4];
////            holder.timeView.setText(string_A);
////            holder.newsData=newsData_A;
//////            holder.collectView.setActivated( newsBreakingData_A.isCollect);
//////            holder.collectView.setOnClickListener(new View.OnClickListener() {
//////                @Override
//////                public void onClick(View v) {
//////                    mDatas.get(position).isSeen=true;
//////                    mDatas.get(position).isCollect=true;
//////                   notifyItemChanged(position);
//////
//////
//////
//////                }
//////            });
//        }
//
//        @Override
//        public int getItemCount() {
//            if(newsBreakingPool!=null&&newsBreakingPool.newsDataClassesNow!=null)
//                {
//                    //]Log.w(this.toString(), "DOJPDgetItemCountF0: "+"="+ newsBreakingPool.newsDataClassesNow.size());
//                    return newsBreakingPool.newsDataClassesNow.size();
//                }
//            //]Log.w(this.toString(), "DOJPDgetItemCountF0: "+"="+0 );
//            return 0;
//        }
//        public void setRefreshNewsData(NewsOD.NewsBreakingPool newsBreakingPool_A){
//            newsBreakingPool=newsBreakingPool_A;
//            notifyDataSetChanged();
//        }
//        public void addNextNewsData(NewsOD.NewsBreakingPool newsBreakingPool_A){
//            if(newsBreakingPool!=null)
//                {
//                    newsBreakingPool.newsDataClassesNow.addAll(newsBreakingPool_A.newsDataClassesNow);
//
//                }else {
//                newsBreakingPool =newsBreakingPool_A;
//            }
//            notifyDataSetChanged();
//
//        }
//
//
//
//
//        //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
//
//        class MyViewHolder extends RecyclerView.ViewHolder  {
//            TextView nameView;
//            TextView timeView;
//            ImageButton collectView;
//            public NewsOD.NewsBreakingData newsBreakingData;
//
//
//            public MyViewHolder(View view) {
//                super(view);
//                nameView=view.findViewById(R.id.news_textView_001);
//                timeView= view.findViewById(R.id.news_textView_002);
//                //collectView=(ImageButton) view.findViewById(R.id.imageButton_002);
//                //button.setOnClickListener(this);
//            }
//
//        }
//
//    }
//    public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
//        private GestureDetectorCompat mGestureDetector;
//        private RecyclerView recyclerView;
//
//        public OnRecyclerItemClickListener(RecyclerView recyclerView) {
//            this.recyclerView = recyclerView;
//            mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(),
//                    new ItemTouchHelperGestureListener());
//        }
//
//
//        public abstract void onItemClick(RecyclerView.ViewHolder viewHolder);
//
//        public abstract void onItemLongClick(RecyclerView.ViewHolder viewHolder);
//
//        @Override
//        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//            mGestureDetector.onTouchEvent(e);
//
//            return false;
//        }
//
//        @Override
//        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//            mGestureDetector.onTouchEvent(e);
//        }
//
//        @Override
//        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//        }
//
//
//        private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//
//                if (child != null) {
//                    RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(child);
//                    onItemClick(viewHolder);
//                }
//                return true;
//            }
//
//
//            @Override
//            public void onLongPress(MotionEvent e) {
//                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//                if (child != null) {
//                    RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(child);
//                    onItemLongClick(viewHolder);
//                }
//            }
//        }
//    }
    public class NewsBreakingPool implements OnUpdateTime , OnUpdateNewsData{
        public TimeOD timeOD;
        public SkillNewsName[] skillNewsName;
        public int[][] intsA;
        public FXDataPool.FXNews.NewsDData newsDData;
        public ArrayList<NewsDataClass> newsDataClassesNow;
        public ArrayList<NewsDataClass> newsDataClassesNew;
        NewsOD newsOD;
        NewsRecycleView newsRecycleView;
        //NewsNewView newsNewView;
        NewsTextView newsTextView;
    ImageButton newsTextBut;
        WebView webView;
        FXDataConnect.NewsConnect newsConnect;
        FXDataConnect.TimeConnect timeConnect;
        public int intIDNow;
        public int intIDNew;
    HandlerThread handlerThreadData;
    Handler handlerData;
    Handler handlerUI;
    boolean boolHandleData;
    boolean boolHandleUI;
        // public ArrayList<NewsDataClass> DataClasses;
        //public ArrayList<NewsBreakingData> newsBreakingData;
        public NewsBreakingPool(NewsOD newsOD_A,FXDataConnect fxDataConnect_A,TimeOD timeOD_A){
            newsConnect=fxDataConnect_A.getNewsConnect(this);
            timeConnect=fxDataConnect_A.getTimeConnect(this);
            newsOD=newsOD_A;
            //newsDData=newsDData_A;
            timeOD=timeOD_A;
            newsDataClassesNew=new ArrayList<>();
            newsDataClassesNow=new ArrayList<>();
            intIDNow=-1;
            intIDNew=-1;
            handlerThreadData = new HandlerThread("NewsODData");
            handlerThreadData.start();
            handlerData = new Handler(handlerThreadData.getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0://请求更新临时数据
                            synchronized ("NewsBreakingPoolData") {
                                if (boolHandleData) {
                                    boolHandleData = false;
                                } else {
                                    return;
                                }
                                //updateCalendarData();
                            }
                            break;
                        case 1:
                            break;
                        default:
                            break;
                    }
                }
            };
            handlerUI = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0://更换临时数据
                            synchronized ("NewsBreakingPoolUI") {
                                if (boolHandleUI) {
                                    boolHandleUI = false;
                                } else {
                                    return;
                                }
                                //changeCalendarData();
                            }
                            break;
                        case 1://更新UI
                            break;
                        case 2://更改日期
                            break;
                        default:
                            break;
                    }
                }
            };
        }

    public void reqHandlerData(int int_A){
        synchronized ("NewsBreakingPoolData") {
            if (!boolHandleData) {
                boolHandleData = true;
                handlerData.sendEmptyMessage(int_A);
            }
        }

    }
    public void reqHandlerUI(int int_A){
        synchronized ("NewsBreakingPoolUI") {
            if (!boolHandleUI) {
                boolHandleUI = true;
                handlerUI.sendEmptyMessage(int_A);
            }
        }

    }
    public void isRefreshNowNewsData(){
        if(!boolReqRefresh)
        {
            reqRefreshNowNewsData();
        }
    }
    public void isUpdateNextDayNowNewsData(){
        if(!boolUpdateNextDay)
        {
            reqUpdateNextDayNowNewsData();
        }
    }
        boolean boolReqRefresh;
        public void reqRefreshNowNewsData(){
            timeOD=timeConnect.getTimeNew();
            newsDData=newsOD.getNewsDData(timeOD);
            if(newsDData!=null) {
                if(newsDData.newsDayData.newsDataClasses.size()>0)
                    {

                boolReqRefresh=true;
                ArrayList<NewsDataClass> newsDataClasses_A = new ArrayList<>();
                if(true)
                    {
                        for (int i = newsDData.newsDayData.newsDataClasses.size()-1; i >=0; i--) {
                            newsDataClasses_A.add(newsDData.newsDayData.newsDataClasses.get(i));
                        }
                    }else {
                    for (int i = 0; i < 30; i++) {
                        String string_Text = "OKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOK";
                        string_Text += string_Text;
                        string_Text += string_Text;
                        string_Text += string_Text;
                        newsDataClasses_A.add(new NewsDataClass("No.00" + i, string_Text, true, SkillNewsName.FortradeAnalysis, 0, new TimeOD(System.currentTimeMillis())));
                    }


                }

                intIDNow=newsDataClasses_A.get(0).intID;
                intIDNew=intIDNow;
                newsRecycleView.setNewsData(newsDataClasses_A);
                    }else {
                    intIDNow=-1;
                    intIDNew=-1;
                    ArrayList<NewsDataClass> newsDataClasses_A = new ArrayList<>();
                    newsRecycleView.setNewsData(newsDataClasses_A );
                }
            }else {
                boolReqRefresh=false;
            }

        }
//        public void reqAddNowNewsData(){
//           // timeOD=timeConnect.getTimeNew();
//            newsDData=newsOD.getNewsDData(timeOD);
//            if(newsDData!=null) {
//                if(newsDData.newsDayData.DataClasses.size()>0)
//                {
//
//                    boolReqRefresh=true;
//                    ArrayList<NewsDataClass> newsDataClasses_A = new ArrayList<>();
//                    if(true)
//                    {
//                        for (int i = newsDData.newsDayData.DataClasses.size()-1; i >=0; i--) {
//                            newsDataClasses_A.add(newsDData.newsDayData.DataClasses.get(i));
//                        }
//                    }else {
//                        for (int i = 0; i < 30; i++) {
//                            String string_Text = "OKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOK";
//                            string_Text += string_Text;
//                            string_Text += string_Text;
//                            string_Text += string_Text;
//                            newsDataClasses_A.add(new NewsDataClass("No.00" + i, string_Text, true, SkillNewsName.FortradeAnalysis, 0, new TimeOD(System.currentTimeMillis())));
//                        }
//
//
//                    }
//
//                    intIDNow=newsDataClasses_A.get(0).intID;
//                    intIDNew=intIDNow;
//                    newsRecycleView.setNewsData(newsDataClasses_A);
//                }else {
//                    intIDNow=-1;
//                    intIDNew=-1;
//                    ArrayList<NewsDataClass> newsDataClasses_A = new ArrayList<>();
//                    newsRecycleView.setNewsData(newsDataClasses_A );
//                }
//            }else {
//                boolReqRefresh=false;
//            }
//
//        }
        boolean boolUpdateNextDay;
        public void reqUpdateNextDayNowNewsData(){
            TimeOD timeOD_A=timeOD.PuC_getCrTimeOD(-1);
            FXDataPool.FXNews.NewsDData newsDData_A=newsOD.getNewsDData(timeOD_A);
            if(newsDData_A!=null)
                {
                    timeOD=timeOD_A;
                    boolReqRefresh=true;
                    ArrayList<NewsDataClass> newsDataClasses_A = new ArrayList<>();
                    if(true)
                        {
                            for (int i = newsDData.newsDayData.newsDataClasses.size()-1; i >=0; i--) {

                                newsDataClasses_A.add(newsDData.newsDayData.newsDataClasses.get(i));
                            }

                        }else {
                        for (int i = 30; i < 40; i++) {
                            String string_Text = "OKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOK";
                            string_Text += string_Text;
                            string_Text += string_Text;
                            string_Text += string_Text;
                            newsDataClasses_A.add(new NewsDataClass("No.00" + i, string_Text, true, SkillNewsName.FortradeAnalysis, 0, new TimeOD(System.currentTimeMillis())));
                    }

                    }
                    newsRecycleView.addNextDayData(newsDataClasses_A);
                }else {
                boolReqRefresh=false;
            }
        }

        public void reqAddToNewsData(){
//            //]Log.w(this.toString(), "reqAddToNewsData: " +"="+newsNewView.newsNewData.size()+"="+newsRecycleView.newsRecycDataNow.size() +"="+newsRecycleView.intRecycDataNow+"="+newsRecycleView.intRecycDataLastNum);
//            if(newsNewView.newsNewData!=null&&newsNewView.boolDataOK)
//                {
//                    if(newsNewView.newsNewData.size()>0)
//                        {
//                            NewsDataClass newsDataClass_A=newsNewView.newsNewData.get(newsNewView.newsNewData.size()-1).dataClass;
//                            newsRecycleView.addToNewsData(newsDataClass_A);
//                            newsNewView.removeNewsData(newsNewView.newsNewData.size()-1);
//
//
//                        }
//
//                }
           // //]Log.w(this.toString(), "reqAddToNewsDataB: " +"="+newsNewView.newsNewData.size()+"="+newsRecycleView.newsRecycDataNow.size() +"="+newsRecycleView.intRecycDataNow+"="+newsRecycleView.intRecycDataLastNum);

        }
        public void reqAddNewNewData(){
            if(intIDNow==-1)
                {
                    reqRefreshNowNewsData();
                }else {
                if(newsDData!=null)
                {

                    if(newsDData.newsDayData.newsDataClasses.size()>0)
                    {
                        //]Log.w(this.toString(), "reqAddNewNewData: T0"+"="+ newsDData.newsDayData.newsDataClasses.size() +"="+newsDData.newsDayData.newsDataClasses.get(newsDData.newsDayData.newsDataClasses.size()-1).intID +"="+intIDNew);
                        if(newsDData.newsDayData.newsDataClasses.get(newsDData.newsDayData.newsDataClasses.size()-1).intID>intIDNew)
                        {
                            ArrayList<NewsDataClass> newsDataClasses_A=new ArrayList<>();

                            for(int i=newsDData.newsDayData.newsDataClasses.size()-1;i>=0;i--)
                            {
                                if(newsDData.newsDayData.newsDataClasses.get(i).intID>intIDNew)
                                {
                                    //]Log.w(this.toString(), "reqUpdateNextDayNowNewsDataS0: "+"="+(newsDData.newsDayData.newsDataClasses.get(i).skillNewsName==SkillNewsName.FortradeAnalysis&&newsDData.newsDayData.newsDataClasses.get(i).intMode==0) +"="+(newsDData.newsDayData.newsDataClasses.get(i).skillNewsName)+"="+(newsDData.newsDayData.newsDataClasses.get(i).intMode));
                                    if(newsDData.newsDayData.newsDataClasses.get(i).skillNewsName==SkillNewsName.FortradeAnalysis&&newsDData.newsDayData.newsDataClasses.get(i).intMode==0)
                                    {
                                        // newsBreakingPool.newsTextView.setNewsDataClass(newsDData.newsDayData.DataClasses.get(i));
                                       // break;
                                    }
                                    newsDataClasses_A.add(newsDData.newsDayData.newsDataClasses.get(i));
                                }else {
                                    break;
                                }

                            }
                            //]Log.w(this.toString(), "reqAddNewNewData: T9"+"="+ newsDataClasses_A.size());
                            newsRecycleView.addNewsData(newsDataClasses_A);


                        }
                    }


                }
            }
//
//            for (int i = 0; i < 10; i++) {
//                String string_Text = "OKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOK";
//                string_Text += string_Text;
//                string_Text += string_Text;
//                string_Text += string_Text;
//                newsDataClasses_A.add(new NewsDataClass("No.-" + i, string_Text, true, SkillNewsName.FortradeAnalysis, 0, new TimeOD(System.currentTimeMillis())));
//
//            }
//            newsNewView.addNewNewsData(newsDataClasses_A);



        }
        public void reqGetNewDayNewsDData(){
            reqAddNewNewData();
            timeOD=timeConnect.getTimeNew();
            newsDData=newsOD.getNewsDData(timeOD);
            //reqNewDayNewsData();
        }
        public void reqNewDayNewsData(){
            if(newsDData!=null)
                {
                    ArrayList<NewsDataClass> newsDataClasses_A=new ArrayList<>();
                    if(newsDData.newsDayData.newsDataClasses.size()>0)
                        {
                            for(int i=newsDData.newsDayData.newsDataClasses.size()-1;i>=0;i--)
                            {
                                newsDataClasses_A.add(newsDData.newsDayData.newsDataClasses.get(i));
                            }
                            newsRecycleView.addNextDayData(newsDataClasses_A);//addNewNewsData(newsDataClasses_A);
                            int int_ID=newsDataClasses_A.get(0).intID;
                            intIDNew=int_ID;
                            intIDNow=int_ID;
                        }else {
                        intIDNew=-1;
                        intIDNow=-1;
                    }

                }

        }
        public boolean ccRefreshNewsDataClass(SkillNewsName[] skillNewsNames_A, int[][] ints_A){
            skillNewsName=skillNewsNames_A;
            intsA=ints_A;
            if(newsDData!=null&&newsDData.newsDayData!=null)
            {
                ArrayList<NewsDataClass> newsDataClasses_A=new ArrayList<>();
                for(int i=0;i<newsDData.newsDayData.newsDataClasses.size();i++)
                {
                    if(skillNewsNames_A!=null)
                    {

                        for(int e=0;e<skillNewsNames_A.length;e++)
                        {
                            if(newsDData.newsDayData.newsDataClasses.get(i).skillNewsName==skillNewsNames_A[e])
                            {

                                if(ints_A!=null&&ints_A[e]!=null)
                                {
                                    for(int r=0;r<ints_A[e].length;r++)
                                    {
                                        if(newsDData.newsDayData.newsDataClasses.get(i).intMode==ints_A[e][r])
                                        {
                                            newsDataClasses_A.add(newsDData.newsDayData.newsDataClasses.get(i));
                                            break;
                                        }

                                    }
                                    break;
                                }else {
                                    newsDataClasses_A.add(newsDData.newsDayData.newsDataClasses.get(i));
                                    break;
                                }

                            }

                        }
                    }else {
                        newsDataClasses_A.add(newsDData.newsDayData.newsDataClasses.get(i));
                    }



                }
                newsDataClassesNow=newsDataClasses_A;
                newsDataClassesNew=new ArrayList<>();
                if(newsDataClassesNow!=null&&newsDataClassesNow.size()!=0)
                {
                    intIDNow=newsDataClassesNow.get(newsDataClassesNow.size()-1).intID;
                    intIDNew=intIDNow;
                    return true;
                }else {
                    return false;
                }
            }
            return false;

        }
        public boolean ccNewNewsDataClass(){
            //]Log.w(this.toString(), "ccNewNewsDataClassF0: "+"="+intIDNew );
            if(intIDNew!=-1)
            {
                if(newsDData!=null&&newsDData.newsDayData!=null)
                {
                    ArrayList<NewsDataClass> newsDataClasses_A=new ArrayList<>();
                    //]Log.w(this.toString(), "ccNewNewsDataClassF1: "+"="+newsDData.newsDayData.newsDataClasses.size() );
                    for(int i=newsDData.newsDayData.newsDataClasses.size()-1;i>=0;i--)
                    {
                        if(newsDData.newsDayData.newsDataClasses.get(i).intID>intIDNew)
                        {
                            if(skillNewsName!=null)
                            {

                                for(int e=0;e<skillNewsName.length;e++)
                                {
                                    if(newsDData.newsDayData.newsDataClasses.get(i).skillNewsName==skillNewsName[e])
                                    {

                                        if(intsA!=null&&intsA[e]!=null)
                                        {
                                            for(int r=0;r<intsA[e].length;r++)
                                            {
                                                if(newsDData.newsDayData.newsDataClasses.get(i).intMode==intsA[e][r])
                                                {
                                                    newsDataClasses_A.add(newsDData.newsDayData.newsDataClasses.get(i));
                                                    break;
                                                }

                                            }
                                            break;
                                        }else {
                                            newsDataClasses_A.add(newsDData.newsDayData.newsDataClasses.get(i));
                                            break;
                                        }

                                    }

                                }
                            }else {
                                newsDataClasses_A.add(newsDData.newsDayData.newsDataClasses.get(i));
                            }
                        }else {
                            break;
                        }




                    }
                    if(newsDataClasses_A!=null&&newsDataClasses_A.size()!=0)
                    {
                        //]Log.w(this.toString(), "ccNewNewsDataClassF2: "+"="+newsDataClasses_A.size() );
                        newsDataClassesNow.addAll(0,newsDataClasses_A);
                        intIDNew=newsDataClassesNow.get(newsDataClassesNow.size()-1).intID;
                        return true;
                    }else {
                        return false;
                    }
                }

            }
            return false;
        }
        public void initNewsTextBut(){
            newsTextBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closeNewsTextView();
                }
            });
        }
        public void openNewsTextView(NewsDataClass newsDataClass_A){

            newsTextView.setVisibility(View.VISIBLE);
            newsTextBut.setVisibility(View.VISIBLE);
            newsTextView.setNewsDataClass(newsDataClass_A);
            recordNewsTextSyster.openRecNewsData(newsDataClass_A);
        }
    public void openNewsNewTextView(NewsDataClass newsDataClass_A){

        //imageButton.setActivated(false);
        //imageView.setVisibility(View.INVISIBLE);
        //newsNewView.setVisibility(View.INVISIBLE);

        newsTextView.setVisibility(View.VISIBLE);
        newsTextBut.setVisibility(View.VISIBLE);
        newsTextView.setNewsDataClass(newsDataClass_A);
    }
        public void closeNewsTextView(){
            newsTextView.setVisibility(View.INVISIBLE);
            newsTextBut.setVisibility(View.INVISIBLE);
            recordNewsTextSyster.closeRecNewsData();
        }
//        void openRecycNewsDataClass(NewsDataClass newsDataClass_A){
//            switch(newsDataClass_A.skillNewsName){
//                case FortradeAnalysis:
//                    switch(newsDataClass_A.intMode){
//                        case 0:
//                        case 1:
//                            break;
//                        case 2:
//                            break;
//
//                        default:
//                            break;
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//        void openRecycNewsTextView(NewsDataClass newsDataClass_A){
//            if(true)
//                {
//
//                }
//
//        }
//    void closeRecycNewsTextView(){
//
//    }
//        void OpenRecycNewsWeb(NewsDataClass newsDataClass_A){
//
//        }
//    void closeRecycNewsWebView(){
//
//    }

        @Override
        public void onUpdateNewsData(int int_Num) {
            newsRecycleView.post(new Runnable() {
                @Override
                public void run() {
                    reqAddNewNewData();
                }
            });

        }

    @Override
    public void onUpdateNewsData(NewsDataClass[] newsDataClasses_A) {

    }

    @Override
        public void onUpdateTime(TimeOD timeODAA) {

        }

        @Override
        public void onUpdateNewDay(TimeOD timeODAA) {
            reqNewDayNewsData();
        }
    }

}
