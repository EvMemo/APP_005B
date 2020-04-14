package com.example.app_005.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_005.Class.SkillLineNema;
import com.example.app_005.Class.SkillQuoName;
import com.example.app_005.Class.GridViewAdapter;
import com.example.app_005.Class.iint;
import com.example.app_005.Interface.OnGetLineTrendData;
import com.example.app_005.Interface.OnGetQuoData;
import com.example.app_005.Interface.OnMyRecyclerC;
import com.example.app_005.Interface.OnUpdateQuoData;
import com.example.app_005.MainApplication;
import com.example.app_005.R;
import com.example.app_005.Tool.FXDataConnect;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.MyFileIOTool;
import com.example.app_005.Tool.TimeOD;
import com.google.gson.Gson;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class QuosSystemCenter{
    Context context;
    //FXDataPool fxDataPool;

    //public CustomizeTrend customizeTrend;//走势图表控件
    public Spinner spinner;//模式列表布局
    //public ListView listView;//模式列表布局
    public  AlertDialog.Builder alert;//对话框
    ApplyDataLineAlert applyDataLineAlert;//申请数据线表格

    public RecyclerView recyclerView;//数据线列表布局
    //public MyFileIOTool fileIOTool;
   // public FXDataPool.FXQuo.FXQuoCacha quoCacha;
    public QuoModeData modeDataA;//模式列表数据
    MyRecyclerAdapter myRecyclerAdapter;
    //public int intModeNum;//模式列表选中的号码
    //public int intLineNum;//列表选中的号码
   // public static Button st_button;
    QuoOD quoOD;//数据线绘制数据
    CustomizeTrend customizeTrend;
    CustomizeTrend.CusTreDrawTool cusTreDrawTool;
    ColorDuoView colorDuoView;
    OpenLineView openLineView;
    //TouchTrendView touchTrendView;
//    HandlerThread handlerThreadData;
//    Handler handlerData;
//    Handler handlerUI;
//    boolean boolHandleData;
//    boolean boolHandleUI;
//    String sy_StrHandleData="sy_StrHandleData";
//    String sy_StrHandleUI="sy_StrHandleUI";
//    String sy_QuoCacha="sy_QuoCacha";
//    String sy_QuoOD="sy_QuoOD";
    FXDataConnect.QuoConnect quoConnect;
    FXDataConnect.FileIOConnect fileIOConnect;
    FXDataConnect.TimeConnect timeConnect;
    ImageButton imageButtonA, imageButtonB;
    //public QuoODSystemConnect odTcDataConnect;
    public QuosSystemCenter(){

    }
    public QuosSystemCenter(Context contextAA,CustomizeTrend customizeTrendAA,Spinner spinnerAA,RecyclerView recyclerViewAA,View view_A) {
        context = contextAA;
        MainApplication mainApplication_A=((MainApplication) contextAA.getApplicationContext());
        quoConnect = mainApplication_A.fxDataConnect.getQuoConnect();
        fileIOConnect = mainApplication_A.fxDataConnect.getFileConnect();
        timeConnect=mainApplication_A.fxDataConnect.getTimeConnect();
        spinner = spinnerAA;
        recyclerView = recyclerViewAA;
        modeDataA = new QuoModeData(fileIOConnect);
        quoOD = new QuoOD(mainApplication_A.fxDataConnect,view_A);

        customizeTrend =  view_A.findViewById(R.id.customizeTrend_0);
        cusTreDrawTool=customizeTrend.cusTreDrawTool;
        colorDuoView=view_A.findViewById(R.id.fre001_color_duo_001);
        openLineView=view_A.findViewById(R.id.fra001_opliv_001);
        colorDuoView.setOnGetQuoData(quoOD);
        colorDuoView.setOpenView(openLineView);

        imageButtonA = view_A.findViewById(R.id.fra001_button_001);
        imageButtonB = view_A.findViewById(R.id.fra001_button_002);
        initButton();
        //touchTrendView.initData(customizeTrend,colorDuoView);
        quoOD.setSkillQuoMode(modeDataA.quoModes[modeDataA.intMode].skillName);
        cusTreDrawTool.setQuoOD(quoOD);


        //fxDataPool=MyFXDataService.ST_fxDataPool;

       ;
        //odTcDataConnect=new QuoODSystemConnect(mainApplication_A.fxDataConnect,view_A);



       // quoOD.PuC_setQuoLineModePain(modeDataA);
       // quoOD.PuC_updateQuoDData();
        //---初始化控件
        //customizeTrend.PuC_anData(quoOD);
        PrC_initializeSpinner();
        PrC_initializeRecyclerView();
        //init_Handle();
        //###
        if (false) {
            // modeDataA=Prc_crQuoData();//初始化模式数据
            //quoCacha=MyFXDataService.ST_fxDataPool.fxQuo.new FXQuoCacha(MyFXDataService.ST_fxDataPool.fxQuo,3,5);
            //Prc_crQuoCachaData(modeDataA,intModeNum,quoOD);//初始化外汇临时数据

            //PrC_carReqCachaDataParam();

        }
        //cachaQuoData=new Q
        // PrC_crQuoODTime();//初始化时间

        Start();
    }
    //*********************************************************************************************


//    /**
//     * 初始化线程
//     */
//    void init_Handle(){
//        handlerThreadData=new HandlerThread("QuoODData");
//        handlerThreadData.start();
//        handlerData =new Handler(handlerThreadData.getLooper() ) {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                switch(msg.what){
//                    case 0://请求更新临时数据
//                        synchronized (sy_StrHandleData){
//                            if(boolHandleData)
//                            {
//                                boolHandleData=false;
//                            }else {
//                                return;
//                            }
//                            //PrC_carReqCachaDataParam();
//                        }
//                        break;
//                    case 1:
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
//        handlerUI =new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                switch(msg.what){
//                    case 0://请求绘制图表
//                        synchronized (sy_StrHandleUI){
//                            if(boolHandleUI)
//                                {
//                                    boolHandleUI=false;
//                                }else {
//                                return;
//                            }
//                        }
//                        customizeTrend.PuC_ODTrend();
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
//    }
    public void Start(){
        customizeTrend.StartDraw(1);
    }
    void initButton(){
        imageButtonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cusTreDrawTool.setMarkerClick();
            }
        });
    }
    //---------------------------------------------------------------------------------------------
//    /**
//     *初始化模式数据
//     */
//    private QuoModeData Prc_crQuoData(){
//        QuoModeData quoModeDataRt=new QuoModeData();
//
//        String string_A=context.getExternalFilesDir("QuoMode").getPath()+"/QuoModeData.json";
//        File file_A=new File(string_A);
//        if(file_A.exists())
//        {
//            Gson gson_A=new Gson();
//            String string_B=fileIOTool.getJSONFileData(file_A);
//            quoModeDataRt=gson_A.fromJson(string_B,QuoModeData.class);
//        }else {
//            SkillQuoName[] skillQuoNames =new SkillQuoName[]{SkillQuoName.EURUSD, SkillQuoName.USDJPY, SkillQuoName.GBPUSD, SkillQuoName.EURGBP, SkillQuoName.USDCHF, SkillQuoName.EURJPY, SkillQuoName.EURCHF, SkillQuoName.USDCAD, SkillQuoName.AUDUSD, SkillQuoName.GBPJPY};
//            String[] strings=new String[]{"EUR/USD","USD/JPY","GBP/USD","EUR/GBP","USD/CHF","EUR/JPY","EUR/CHF","USD/CAD","AUD/USD","GBP/JPY"};
//            quoModeDataRt=new QuoModeData();
//            quoModeDataRt.quoModes=new QuoModeData.QuoMode[10];
//            for(int i = 0; i< skillQuoNames.length; i++)
//            {
//                quoModeDataRt.quoModes[i]=quoModeDataRt.new QuoMode(skillQuoNames[i],strings[i],0);
//                //quoModeDataRt.quoModes[i].quoLineOPs=new QuoModeData.QuoLineMO[strings.length];
//                SkillQuoName skillQuoName_A = SkillQuoName.getSkill(i);
//                for(int e=0;e<strings.length;e++)
//                {
//                    //quoModeDataRt.quoModes[i].quoDataLinesA.add(quoModeDataRt.new QuoLine(new int[]{0,e},strings[e],null));
//                  //  quoModeDataRt.quoModes[i].quoLineOPs[e]=quoModeDataRt.new QuoLineMO(strings[e],0,new SkillQuoName[]{skillQuoName_A});
//                }
//
//            }
//            quoModeDataRt.quoLineMOS =new QuoModeData.QuoLineMO[0];
//            for(int i=0;i<0;i++)
//                {
//                    quoModeDataRt.quoLineMOS[i]=quoModeDataRt.new QuoLineMO("",0,null);
//                }
//        }
//        quoModeDataRt.intMode=0;
//        quoModeDataRt.intLine=0;
//        //intModeNum =0;
//        //intLineNum =0;
//        return quoModeDataRt;
//    }
//    /**
//     *初始化时间
//     */
//    private void PrC_crQuoOD(){
//
//
//
//
//    }
//    /**
//     *初始化外汇临时数据
//     */
//    private void Prc_crQuoCachaData(QuoModeData quoModeDataAA,int intModeAA,QuoOD quoODAA){
//        quoCacha.PuC_crQuoData(3,quoModeDataAA.quoModes[intModeAA].skillName,quoODAA.odTimeData.timeODNow);
//    }



    /**
     *初始化模式列表布局
     */
    private void PrC_initializeSpinner(){


        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<modeDataA.quoModes.length;i++)
            {
                Log.d("PrC_initializeSpinner", "PrC_initializeSpinner_0: "+i +"="+modeDataA.quoModes[i].name);
                list.add(modeDataA.quoModes[i].name);
            }
        //list.add("   +   ");
        SpinnerAdapter adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择列表项的操作
                if(false&&position==parent.getCount()-1)
                    {

                    }else {
                    modeDataA.intMode=position;
                   // PrC_setQuoODMainData(modeDataA.quoModes[modeDataA.intMode].skillName);
                    quoOD.setSkillQuoMode(modeDataA.quoModes[modeDataA.intMode].skillName);//
                    quoOD.reqUpdateQuoData();
                    cusTreDrawTool.updateDraw();
                    //customizeTrend.PuC_ODTrend();
//                    if(modeDataA.quoModes[position].intState==1)
//                        {
//                            PrC_setRecyclerViewData(position);
//                        }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //未选中时候的操作
            }
        });



    }
//    /**
//     *赋值图表货币走势线
//     */
//    private void PrC_setQuoODMainData(){
//    quoOD.painMainData=quoOD.new PaintData(modeDataA.quoModes[modeDataA.intState].skillName);
//    }
    /**
     *初始化数据线列表布局
     */
    private void PrC_initializeRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        // ArrayList<String> list=new ArrayList<>();
        ArrayList<RecyclerLineData> listdata=new ArrayList<>();

        for(int i = 0; i<modeDataA.quoLineMOS.length; i++)
        {
            listdata.add(new RecyclerLineData(modeDataA.quoLineMOS[0].name));
        }
        //listdata.add(new RecyclerLineData("AddNewQuoLine"));
        myRecyclerAdapter=new MyRecyclerAdapter(context,listdata);
        recyclerView.setAdapter(myRecyclerAdapter);
        //PrC_setQuoODLineData(modeDataA.quoLineMOS[0].name,modeDataA.quoLineMOS[modeDataA.intLine]);
        myRecyclerAdapter.Puc_setOnMyRecyclerC(new OnMyRecyclerC() {
            @Override
            public void OnMyRecyclerNum(int position) {
                if(modeDataA.quoLineMOS.length<position)
                    {
                        if((modeDataA.quoLineMOS.length-1)!=position)
                            {
                                modeDataA.intLine=position;
                                //quoOD.PuC_setQuoLineModePain(modeDataA);
                               //quoOD.reqUpdateQuoData(0);
                               // customizeTrend.PuC_ODTrend();
                            }else {

                            openAddQuoODLineWindow();
                        }



                    }

            }
        });


    }
//    /**
//     *赋值数据线列表数据
//     */
//    private void PrC_setRecyclerViewData(int intAA){
//
//        myRecyclerAdapter.mDatas.clear();
//        ArrayList<RecyclerLineData> lineData_A=new ArrayList<>();
//        for(int i=0;i<intAA+1;i++)
//        {
//            //Log.d("PrC_initializeSpinner", "PrC_initializeSpinner_0: "+i +"="+modeDataA.quoModes[i].name);
//            //list.add(modeDataA.quoModes[i].name);
//            lineData_A.add(new RecyclerLineData(modeDataA.quoModes[intAA].name));
//        }
//        //lineData_A.get(0).aBoolean=true;
//        myRecyclerAdapter.mDatas.addAll(lineData_A);
//        recyclerView.smoothScrollToPosition(0);
//        myRecyclerAdapter.notifyDataSetChanged();
//
//
//
//    }


    /**
     *初始化添加新模式窗口
     */
    private void PrC_crAlert(){
        View dialogView = LayoutInflater.from(context).inflate(R.layout.my_dialog_layout_002,null);
       // TextView dialogText = (TextView) dialogView.findViewById(R.id.dialog_text);
        //Button dialogBtnConfirm = (Button) dialogView.findViewById(R.id.dialog_btn_confirm);
       // Button dialogBtnCancel = (Button) dialogView.findViewById(R.id.dialog_btn_cancel);
        Spinner spinner_A= (Spinner) dialogView.findViewById(R.id.spinner_002);
        ArrayList<String> list=new ArrayList<>();
        String[] strings=new String[]{"EUR/USD","USD/JPY","GBP/USD","EUR/GBP","USD/CHF","EUR/JPY","EUR/CHF","USD/CAD","AUD/USD","GBP/JPY"};
        for(int i=0;i<strings.length;i++)
        {
            list.add(strings[i]);
        }
        SpinnerAdapter adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, list);
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, list);
        spinner_A.setAdapter(adapter);
        spinner_A.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择列表项的操作

               // PrC_setRecyclerViewData(modeDataA.quoModes[position],position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //未选中时候的操作
            }
        });

        alert= new AlertDialog.Builder(context);
        alert.setTitle("添加新模式");
        //设置icon
        alert.setIcon(R.mipmap.ic_launcher_round);
        //设置内容
        //alert.setMessage("对话框内容");
        alert.setView(dialogView);
        //设置按钮
       // dialogText.setText("我是自定义layout的弹窗！！");
        alert.setPositiveButton("确定"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"点击确定" ,Toast.LENGTH_SHORT).show();

                    }
                });
        alert.setNegativeButton("取消"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"点击取消" ,Toast.LENGTH_SHORT).show();

                    }
                });
        //创建并显示
        alert.create().show();



    }
    /**
     *初始化添加新数据线2窗口
     */
    private void PrC_crAlert_2(){


        applyDataLineAlert=new ApplyDataLineAlert();
        View dialogView = LayoutInflater.from(context).inflate(R.layout.my_dialog_layout_003,null);
        RadioGroup radioGroup_A=(RadioGroup) dialogView.findViewById(R.id.radioGroup_001);
        applyDataLineAlert.textViewA=(TextView) dialogView.findViewById(R.id.textView6);
        applyDataLineAlert.editTextA=(EditText) dialogView.findViewById(R.id.editText_005);
        applyDataLineAlert.radioButtonA=(RadioButton) dialogView.findViewById(R.id.radioButton);
        applyDataLineAlert.radioButtonA.toggle();
        applyDataLineAlert.radioButtonB=(RadioButton) dialogView.findViewById(R.id.radioButton2);
        applyDataLineAlert.intMode=0;
        applyDataLineAlert.editTextA.setVisibility(View.GONE);
        applyDataLineAlert.textViewA.setVisibility(View.GONE);
        radioGroup_A.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(this.toString(), "onCheckedChanged: "+checkedId);
                if(applyDataLineAlert.radioButtonA.getId()==checkedId)
                    {
                        applyDataLineAlert.intMode=0;
                        applyDataLineAlert.editTextA.setVisibility(View.GONE);
                        applyDataLineAlert.textViewA.setVisibility(View.GONE);
                        applyDataLineAlert.gridViewAdapter.Puc_setGridMode(0);

                    } else if(applyDataLineAlert.radioButtonB.getId()==checkedId)
                    {
                        applyDataLineAlert.intMode=1;
                        applyDataLineAlert.editTextA.setVisibility(View.VISIBLE);
                        applyDataLineAlert.textViewA.setVisibility(View.VISIBLE);
                        applyDataLineAlert.gridViewAdapter.Puc_setGridMode(1);
                }

            }
        });
        GridView gridView_A=(GridView) dialogView.findViewById(R.id.gridView_001);
        Spinner spinner_A= (Spinner) dialogView.findViewById(R.id.spinner_002);
        ArrayList<GridViewAdapter.GridData> list=new ArrayList<>();
       // String[] strings=new String[]{"EUR/USD","USD/JPY","GBP/USD","EUR/GBP","USD/CHF","EUR/JPY","EUR/CHF","USD/CAD","AUD/USD","GBP/JPY"};
        SkillQuoName[] skillNames_A= SkillQuoName.values();
        for(int i=10;i<skillNames_A.length;i++)
        {
            list.add(new GridViewAdapter.GridData(skillNames_A[i]));
        }
       GridViewAdapter gridViewAdapter_A=new GridViewAdapter(context,list);
        gridView_A.setAdapter(gridViewAdapter_A);
        applyDataLineAlert.gridViewAdapter=gridViewAdapter_A;

        alert= new AlertDialog.Builder(context);
        alert.setCancelable(false);
        alert.setTitle("添加新数据线");
        //设置icon
        alert.setIcon(R.mipmap.ic_launcher_round);
        //设置内容
        //alert.setMessage("对话框内容");
        alert.setView(dialogView);
        //设置按钮
        // dialogText.setText("我是自定义layout的弹窗！！");
        alert.setPositiveButton("确定"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                        ArrayList<SkillQuoName> skillQuoNames_A =applyDataLineAlert.gridViewAdapter.PuC_calGridBoolData();
                        applyDataLineAlert.boolA = true;
                        if (skillQuoNames_A.size() != 0) {
                            String string_A="";
                            if(applyDataLineAlert.intMode==0)
                                {
                                    string_A = skillQuoNames_A.get(0).getName();
                                }else {
                                string_A = applyDataLineAlert.editTextA.getText().toString();
                            }
                            modeDataA.addQuoLineMO(string_A,applyDataLineAlert.intMode,(SkillQuoName[])skillQuoNames_A.toArray());

                           // PrC_crAlertApply(skillQuoNames_A,string_A);
                            Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "添加失败,请选择数据线", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        alert.setNegativeButton("取消"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "点击取消", Toast.LENGTH_SHORT).show();
                        applyDataLineAlert = null;
                    }
                });
        //创建并显示
        alert.create().show();



    }

//    /**
//     *添加新数据线
//     */
//    private void PrC_crAlertApply(ArrayList<SkillQuoName> skillNamesAA, String nameAA){
//
//
//    }
//
//    /**
//     *存储模式数据
//     */
//    public void PuC_saveQuoModeNumber(){
//
//
//    }
//    /**
//     *添加模式数据
//     */
//    public void PuC_addQuoModeData(SkillQuoName[] skillNamesAA, String nameAA){
//
//
//    }


//    /**
//     * 请求更新临时数据
//     */
//    public void PuC_reqQuoCachaData(){
//        synchronized (sy_StrHandleData){
//            if(!boolHandleData)
//                {
//                    boolHandleData=true;
//                    handlerData.sendEmptyMessage(0);
//                }
//        }
//    }
//    /**
//     * 请求绘制图表
//     */
//    public void PuC_reqODTrend(){
//        synchronized (sy_StrHandleUI){
//            if(!boolHandleUI)
//            {
//                boolHandleUI=true;
//                handlerUI.sendEmptyMessage(0);
//            }
//        }
//    }
//    private void PrC_initQuoCachaData(QuoOD quoODAA){
//        TimeOD timeODNow_A;
//        int intLast_A;
//        SkillQuoName skillName_A;
//        SkillQuoName[] skillName_B;
//        //String name_A;
//        synchronized (sy_QuoOD){
//            timeODNow_A=quoOD.odTimeData.timeODNow;
//            intLast_A=quoOD.odTimeData.intLast;
//            skillName_A=quoOD.painMainData.skillName;
//            //name_A=quoOD.painLineName;
//            skillName_B=new SkillQuoName[quoOD.painLineData.length];
//            for(int i = 0; i<quoOD.painLineData.length; i++)
//            {
//                skillName_B[i]=quoOD.painLineData[i].skillName;
//            }
//        }
//        FXDataPool.FXQuo.QuoDData[] quoDData_A;
//        if(true)
//        {
////            quoDData_A =new FXDataPool.FXQuo.QuoDData[intLast_A+1];
////            quoDData_A[0]=quoCacha.fxQuo.new QuoDData(skillName_A,timeODNow_A);
////            for(int i = 1; i< quoDData_A.length; i++)
////            {
////                quoDData_A[i]=quoCacha.fxQuo.new QuoDData(skillName_A,timeODNow_A.PuC_getCrTimeOD(-1),skillName_B,5);
////            }
//        }
//    }
//    /**
//     *计算请求更新临时数据参数
//     */
//    private void  PrC_carReqCachaDataParam() {
//       // FXDataPool.FXQuo.QuoChaPool quoChaPool=quoCacha.fxQuo.new QuoChaPool();
//        MyFXDataService.TimeOD timeODNow_A;
//        int intLast_A;
//        SkillQuoName skillName_A;
//        SkillQuoName[] skillName_B;
//        //String name_A;
//        synchronized (sy_QuoOD){
//            timeODNow_A=quoOD.odTimeData.timeODNow;
//            intLast_A=quoOD.odTimeData.intLast;
//            skillName_A=quoOD.painMainData.skillName;
//            //name_A=quoOD.painLineName;
//            skillName_B=new SkillQuoName[quoOD.painLineData.length];
//            for(int i = 0; i<quoOD.painLineData.length; i++)
//                {
//                    skillName_B[i]=quoOD.painLineData[i].skillName;
//                }
//
//            //quoChaPool.quoMainDDataMain =quoChaPool.new QuoMainDData(quoOD.painMainData.skillName,quoOD.odTimeData.timeODNow,quoOD.odTimeData.intLast);
//
//
//        }
//        FXDataPool.FXQuo.QuoDData[] quoDData_A;
////        if(true)
////            {
////                quoDData_A =new FXDataPool.FXQuo.QuoDData[intLast_A+1];
////                quoDData_A[0]=quoCacha.fxQuo.new QuoDData(skillName_A,timeODNow_A);
////                for(int i = 1; i< quoDData_A.length; i++)
////                {
////                    quoDData_A[i]=quoCacha.fxQuo.new QuoDData(skillName_A,timeODNow_A.PuC_getCrTimeOD(-1),skillName_B,5);
////                }
////            }
////        if(true)
////            {
////                for(int i = 0; i< quoDData_A.length; i++)
////                {
////                    quoCacha.PuC_cacQuoDayData(quoDData_A[i]);
////                }
////            }
//        //FXDataPool.FXQuo.QuoLineDDay[] quoLineDDays_A;
//
//        //PuC_getFoDataSevricaQuoData(quoDData_A);
////        for(int i=0;i<quoDData_A.length;i++)
////            {
////            quoCacha.PuC_addQuoChaData(quoDData_A[i]);
////            }
//    }

//    /**
//     *向数据服务获取对挡应的数据
//     */
//    public void PuC_getFoDataSevricaQuoData( FXDataPool.FXQuo.QuoDData[] quoDDataAA){
////        for(int i = 0; i< quoDDataAA.length; i++)
////            {
////                if(quoDDataAA[i].quoDayData.intState==0)
////                    {
////
////                        if(!fxDataPool.dataService.PuC_getQuoDDayData(quoDDataAA[i].quoDayData))
////                            {
////                                quoDDataAA[i].quoDayData.intState=2;
////                            }
////
////                    }
////                for(int e=0;e<quoDDataAA[i].quoDayLines.length;e++)
////                    {if(quoDDataAA[i].quoDayLines[e].intState==0)
////                    {
////
////                        if(!fxDataPool.dataService.PuC_getQuoDDayLine(quoDDataAA[i].quoDayLines[e]))
////                        {
////                            quoDDataAA[i].quoDayLines[e].intState=2;
////                        }
////
////                    }
////
////                    }
////
////            }
//
//    }
//    /**
//     *将传来的数据赋值给临时数据
//     */
//    private void PrC_assDataToQuoCachaData(FXDataPool.FXQuo.QuoChaPool quoChaPoolAA){
//        synchronized (){
//            quoCacha.quoChaPool=quoChaPoolAA;
//        }
//
//        FXDataPool.FXQuo.QuoChaPool quoChaPool=quoCacha.fxQuo.new QuoChaPool();
//        synchronized (sy_QuoOD){
//            quoChaPool.quoMainDDataMain =quoChaPool.new QuoMainDData(quoOD.painMainData.skillName,quoOD.odTimeData.timeODNow,quoOD.odTimeData.intLast);
//            if(quoOD.painLineData!=null)
//            {
//                for(int i=0;i<quoOD.painLineData.length;i++)
//                {
//
//                }
//            }
//        }
//
//
//        if(quoChaPoolAA.quoMainDDataMain.skillName==quoChaPool.quoMainDDataMain.skillName)
//        {
//            for(int i = 0; i<quoChaPool.quoMainDDataMain.timeODMain.length; i++)
//            {
//                for(int e = 0; e<quoChaPoolAA.quoMainDDataMain.timeODMain.length; e++)
//                {
//                    if(quoChaPoolAA.quoMainDDataMain.quoDayData[e]!=null)
//                    {
//                        if(quoChaPool.quoMainDDataMain.timeODMain[i].PuC_isTimeODSameDay(quoChaPoolAA.quoMainDDataMain.timeODMain[e]))
//                        {
//                            quoChaPool.quoMainDDataMain.quoDayData[i]=quoChaPoolAA.quoMainDDataMain.quoDayData[e];
//                        }
//                    }
//
//                }
//            }
//        }
//        boolean bool_A = false;
//        for (int i = 0; i < quoChaPool.quoMainDDataMain.quoDayData.length; i++) {
//            if (quoChaPool.quoMainDDataMain.quoDayData[i] == null) {
//                bool_A = true;
//            }
//        }
//        if (bool_A) {
//            PuC_getFoDataSevricaQuoData(quoChaPool);
//        }
//
//
//    }
//    public class QuoChaPool{
//       public int intA;
//       public QuoDData quoDDataMain;
//       public QuoDData[] quoDDataLine;
//       public class QuoDData{
//           public SkillQuoName skillName;
//           public MyFXDataService.TimeOD[] timeODMain;
//           public QuoTiDat[] quoTiDat;
//           public QuoDData(SkillQuoName fxSkillNameAA, MyFXDataService.TimeOD timeODAA,int intDay){
//               skillName=fxSkillNameAA;
//               timeODMain=new MyFXDataService.TimeOD[Math.abs(intDay)+1];
//               quoTiDat=new QuoTiDat[timeODMain.length];
//               timeODMain[0]=timeODAA;
//               for(int i=1;i<timeODMain.length;i++)
//                   {
//                       if (intDay > 0) {
//                           timeODMain[i] = new MyFXDataService.TimeOD(timeODMain[0].PuC_getCrTimeOD(i));
//                       } else {
//                           timeODMain[i] = new MyFXDataService.TimeOD(timeODMain[0].PuC_getCrTimeOD(-i));
//                       }
//                   }
//           }
//       }
//       public class QuoTiDat{
//           public float floatA;
//       }
//    }
//    /**
//     *
//     * 赋值QuoODMainData
//     */
//    private void PrC_setQuoODMainData(SkillQuoName skillNameMainAA){
//        synchronized (sy_QuoOD){
//
//        }
//
//    }
//    /**
//     *
//     * 赋值QuoODLineData
//     */
//    private void PrC_setQuoODLineData(int intPosition){
//        synchronized (sy_QuoOD) {
//
////            quoOD.painLineData = new QuoOD.PaintData[quoLineOPAA.skillQuoNames.length];
////            quoOD.painLineName=nameAA;
////            for (int i = 0; i < quoLineOPAA.skillQuoNames.length; i++) {
////                quoOD.painLineData[i] = quoOD.new PaintData(quoLineOPAA.skillQuoNames[i]);
////            }
//        }
//
//    }
    /**
     *打开添加指数窗口
     */
    void openAddQuoODLineWindow(){

    }


//    @Override
//    public void On_getQuoData() {
//        synchronized (sy_QuoOD) {
//            if (!quoOD.odTimeData.getBoolTime()) {
//                quoOD.PuC_updateQuoDData();
//            } else {
//
//            }
//            float[][] floats=new float[quoOD.odTimeData.floatTimeNum][];
//                    int int_B=quoOD.odTimeData.timeODNow.intsA[3];
//                    int int_B2=quoOD.odTimeData.timeODNow.intsA[4];
//                    TimeOD timeOD_A=quoOD.odTimeData.timeODNow;
//                    for(int i=0;i<0;i++)
//                    {
//                        if(int_B2<0){
//                            int_B2=59;
//                            int_B--;
//                            if(int_B<0)
//                            {
//                                int_B=23;
//                                timeOD_A=timeOD_A.PuC_getCrTimeOD(-1);
//                            }
//                        }
//                        int_B2--;
//
//                    }
//            if (false) {
////                int intBo = 0;
////                boolean boolBo=true;
////                if (odTimeData.intLast != 0) {
////
////                    if(int_B==0)
////                    {
////                        int int_B3=int_B2+1;
////
////
////                        for(int i=int_B2;i<=0;i--)
////                        {
////
////                        }
////                        if(true)
////                        {
////                            SkillQuoName quoName_A=null;
////                            MyFXDataService.TimeOD timeODNow=null;
////                            //quoConnect.getFXQuoData();
////                        }
////                        for(int i = 0; i<quoCacha.quoDDataAr.length; i++)
////                        {
////                            if(quoOD.painMainData.skillName==quoCacha.quoDDataAr[i].skillName)
////                            {
////                                if(odTimeData.timeODNow.PuC_isTimeODSameDay(quoCacha.quoDDataAr[i].timeODNow))
////                                {
////                                    boolBo=false;
////                                    if(quoCacha.quoDDataAr[i].quoDayData.intState==1)
////                                    {
////                                        float[][] floats_A=quoCacha.quoDDataAr[i].PuC_getDatas(odTimeData.timeODNow,int_B3);
////                                        for(int e=0;e<floats_A.length;i++)
////                                        {
////                                            floats[e]=floats_A[e];
////                                        }
////                                        quoOD.painMainData.fxDataF=floats_A;
////                                    }else if(quoCacha.quoDDataAr[i].quoDayData.intState==0){intBo=1;}
////
////                                }else if(timeOD_A.PuC_isTimeODSameDay(quoCacha.quoDDataAr[i].timeODNow))
////                                {
////                                    boolBo=false;
////                                    if(quoCacha.quoDDataAr[i].quoDayData.intState==1)
////                                    {
////                                        float[][] floats_A=quoCacha.quoDDataAr[i].PuC_getDatas(23,59,odTimeData.floatTimeNum-int_B3);
////                                        for(int e=int_B3;e<floats.length;i++)
////                                        {
////                                            floats[e]=floats_A[e];
////                                        }
////                                        quoOD.painMainData.fxDataF=floats_A;
////                                    }else if(quoCacha.quoDDataAr[i].quoDayData.intState==0){intBo=1;}
////
////                                }
////                            }
////                        }
////                        quoOD.painMainData.fxDataF=floats;
////                    }
////                    else {
////                        //???;
////                    }
////
////                } else {
////
////                    if(true)
////                    {
////                        quoOD.painMainData.fxDataF=quoConnect.getFXQuoFloatData(quoOD.painMainData.skillName,odTimeData.timeODNow,odTimeData.floatTimeNum);
////                        for(int i = 0; i<quoCacha.quoDDataAr.length; i++)
////                        {
////                            if(quoOD.painMainData.skillName==quoCacha.quoDDataAr[i].skillName)
////                            {
////                                if(odTimeData.timeODNow.PuC_isTimeODSameDay(quoCacha.quoDDataAr[i].timeODNow))
////                                {
////                                    boolBo=false;
////                                    if(quoCacha.quoDDataAr[i].quoDayData.intState==1)
////                                    {
////                                        float[][] floats_A=quoCacha.quoDDataAr[i].PuC_getDatas(odTimeData.timeODNow,odTimeData.floatTimeNum);
////                                        quoOD.painMainData.fxDataF=floats_A;
////                                    }else if(quoCacha.quoDDataAr[i].quoDayData.intState==0){intBo=1;}
////
////                                }
////                            }
////                        }
////                    }
////                }
////                if(boolBo||intBo==1)
////                {
////                    PrC_carReqCachaDataParam();
////                }
////            }
//            }
//        }
//
//    }


    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
        private List<RecyclerLineData> mDatas;
        private Context mContext;
        private LayoutInflater inflater;
        private OnMyRecyclerC onMyRecyclerC;
        View view;
        RecyclerLineData lineData;
        public MyRecyclerAdapter(Context context, List<RecyclerLineData> datas ){
            this. mContext=context;
            this. mDatas=datas;

            inflater=LayoutInflater. from(mContext);
        }
        public void Puc_setOnMyRecyclerC(OnMyRecyclerC onMyRecyclerCAA){
            this.onMyRecyclerC=onMyRecyclerCAA;
        }
        /**
         *
         */
        public void PuC_addData(RecyclerLineData recyclerLineDataAA){
            for(int i=0;i<mDatas.size();i++)
            {
                mDatas.get(i).aBoolean=false;
            }
            recyclerLineDataAA.aBoolean=true;
            mDatas.add(recyclerLineDataAA);
            notifyDataSetChanged();

        }
        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        //填充onCreateViewHolder方法返回的holder中的控件
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.button.setText( mDatas.get(position).name);
            holder.button.setActivated(mDatas.get(position).aBoolean);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(position==(mDatas.size()-1))
                        {
                            onMyRecyclerC.OnMyRecyclerNum(position);
                        }else {
                        if(view!=null)
                        {
                            view.setActivated(false);

                        }
                        if(lineData!=null)
                        {
                            lineData.aBoolean=false;
                        }
                        lineData=mDatas.get(position);
                        lineData.aBoolean=true;
                        v.setActivated(true);
                        view=v;
                        onMyRecyclerC.OnMyRecyclerNum(position);

                    }



                }
            });
        }

        //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view_B=LayoutInflater.from(context).inflate(R.layout.text_001,parent,false);
            View view = inflater.inflate(R.layout.button_001,parent, false);
            MyViewHolder holder= new MyViewHolder(view);
            return holder;
        }
        class MyViewHolder extends RecyclerView.ViewHolder  {
            Button button;

            public MyViewHolder(View view) {
                super(view);
                button=(Button) view.findViewById(R.id.button_002);
                //button.setOnClickListener(this);
            }

        }
    }

    /**
     * 申请添加数据线表格
     */
    class ApplyDataLineAlert{
        public boolean boolA;
        public String name;
        public int intMode;
        public iint[] iintLine;
        public GridViewAdapter gridViewAdapter;
        public RadioButton radioButtonA,radioButtonB;
        public TextView textViewA;
        public  EditText editTextA;


    }

    /**
     * 申请生成模式表格
     */
    class ApplyDataModeAlert{
        public boolean boolA;
        public String name;
        public int intMode;
    }
    public class RecyclerLineData {
        public String name;
        //public int[] intLine;
        public boolean aBoolean;
        public RecyclerLineData(String nameAA){
            name=nameAA;
            //intLine=intsAA;
            aBoolean=false;
        }

    }

    /**
     * 模式列表数据
     */
    public class QuoModeData{
        public int intMode;
        public int intLine;
        public QuoMode[] quoModes;
        public QuoLineMO[] quoLineMOS;
        public int intState;//数据状态[0=初始化,1=有数据,2=需要存储]
        public QuoModeData(FXDataConnect.FileIOConnect fileIOConnectAA){
            intState=0;
            MyFileIOTool fileIOTool_A=fileIOConnectAA.getFileIOTool();
            String string_A=context.getExternalFilesDir("QuoMode").getPath()+"/QuoModeData.json";
            File file_A=new File(string_A);
            if(file_A.exists())
            {
                Gson gson_A=new Gson();
                String string_B=fileIOTool_A.getJSONFileData(file_A);
                QuoModeData quoModeData_A=gson_A.fromJson(string_B,QuoModeData.class);
            }else {
                SkillQuoName[] skillQuoNames =new SkillQuoName[]{SkillQuoName.EURUSD, SkillQuoName.USDJPY, SkillQuoName.GBPUSD, SkillQuoName.EURGBP, SkillQuoName.USDCHF, SkillQuoName.EURJPY, SkillQuoName.EURCHF, SkillQuoName.USDCAD, SkillQuoName.AUDUSD, SkillQuoName.GBPJPY};
                String[] strings=new String[]{"EUR/USD","USD/JPY","GBP/USD","EUR/GBP","USD/CHF","EUR/JPY","EUR/CHF","USD/CAD","AUD/USD","GBP/JPY"};
                quoModes=new QuoModeData.QuoMode[10];
                for(int i = 0; i< skillQuoNames.length; i++)
                {
                    quoModes[i]=new QuoMode(skillQuoNames[i],strings[i],0);
                    SkillQuoName skillQuoName_A = SkillQuoName.getSkill(i);


                }
                quoLineMOS =new QuoLineMO[1];
                for(int i = 0; i< quoLineMOS.length; i++)
                    {

                    }
                quoLineMOS[0]=new QuoLineMO("Add",99,null);
            }
            intMode=0;
            intLine=-1;
            intState=1;
        }
        public boolean addQuoMode(QuoMode quoModeAA){
            intState=2;
            return false;
        }
        public boolean addQuoLineMO(String nameAA, int intModeAA, SkillQuoName[] skillQuoNamesAA){
            QuoLineMO quoLineMO_A=new QuoLineMO(nameAA,intModeAA,skillQuoNamesAA);
            intState=2;
            return false;
        }
        public void saveQuoModeData(MyFileIOTool fileIOToolAA){
            if(intState==2)
                {
                    String string_A=context.getExternalFilesDir("QuoMode").getPath()+"/QuoModeData.json";
                    File file_A=new File(string_A);
                    intState=1;
                }
        }

        public class QuoMode{


            //public FXQuoName quoName;
            public SkillQuoName skillName;
            public String name;
            public int intMode;
            //public ArrayList<QuoLine> quoDataLinesA;
            //public QuoLineMO[] quoLineOPs;
            public QuoMode(SkillQuoName skillNameAA, String stringAA, int intModeAA){
                skillName =skillNameAA;
                name=stringAA;
                intMode=intModeAA;
            }
        }
        /**
         * 数据线类型
         */
        public class QuoLineMO {
            public String name;//名称
            public int intMode;//类型[0=单线,1=组合线]
            //public iint[] iintLine;//数据线
            public SkillQuoName[] skillQuoNames;
            public QuoLineMO(String name, int intMode, SkillQuoName[] skillQuoNames){
                this.name=name;
                this.intMode=intMode;
                this.skillQuoNames = skillQuoNames;
            }
        }
        
    }

    /**
     * 数据线绘制数据
     */
    public class QuoOD implements OnGetQuoData, OnUpdateQuoData,  OnGetLineTrendData {
        //public ODTimeData odTimeData;//绘制数据时间
//        public PaintData painMainData;//绘制数据类型
//        public PaintData[] painLineData;//绘制数据类型
        public String painLineName;
        public FXDataPool.FXQuo.QuoDData quoDDataNew, quoDDataNew2;
        public FXDataPool.FXQuo.QuoDData quoDDataA, quoDDataB;
        public FXDataPool.FXQuo.QuoDData quoDDataA2, quoDDataB2;
        FXDataConnect.QuoConnect quoConnect;
       // FXDataConnect.TimeConnect timeConnect;
        //public boolean boolQuoD;
        HandlerThread handlerThreadData;
        Handler handlerData;
        Handler handlerUI;
        boolean boolHandleData;
        boolean boolHandleUI;
        //CustomizeTrend customizeTrend;

        Button buttonA;
        View viewLinerLoA;
        Button[] buttonsTimeA;
        int buttonUITag;
        //int intTimeMode;

        public int intTimeCycle;//时间周期
        public int intTimeSize;//时间大小
        //LineTrendCenter lineTrendCenter;

        boolean quoDDataBool;
//        public TimeOD timeODNew;
//        public TimeOD timeODNow;//最新时间
//        public int intLast;//最后时间
//        public int intT;//最后时间位置

        public SkillQuoName skillQuoName;
        CustomizeTrend.CusTreDrawTool cusTreDrawTool;
       // QuoODSystemConnect quoODSystemConnect;

        public QuoOD(FXDataConnect fxDataConnectAA, View view_A) {
            //quoODSystemConnect=quoODSystemConnect_A;
            //timeConnect = fxDataConnectAA.getTimeConnect(this);
            quoConnect = fxDataConnectAA.getQuoConnect(this);
            //lineTrendCenter = new LineTrendCenter(this, view_A);
            //customizeTrend = view_A.findViewById(R.id.customizeTrend_0);

            buttonA = view_A.findViewById(R.id.fra001_button_003);
            viewLinerLoA = view_A.findViewById(R.id.fra001_line_001);
            buttonsTimeA = new Button[5];
            buttonsTimeA[0] = view_A.findViewById(R.id.fra001_time_01);
            buttonsTimeA[1] = view_A.findViewById(R.id.fra001_time_02);
            buttonsTimeA[2] = view_A.findViewById(R.id.fra001_time_03);
            buttonsTimeA[3] = view_A.findViewById(R.id.fra001_time_04);
            buttonsTimeA[4] = view_A.findViewById(R.id.fra001_time_05);

            setTimeCycle(1);
            init_ButtonUI();
            //intTimeMode = 1;
            //odTimeData = new ODTimeData(timeConnect.getTimeNew(), 4, 1);
            // customizeTrend.PuC_anData(this);
            //customizeTrend.setIntNum(4);

            handlerThreadData = new HandlerThread("QuoODData");
            handlerThreadData.start();
            handlerData = new Handler(handlerThreadData.getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0://请求更新临时数据
                            synchronized ("QuoODBoolData") {
                                if (boolHandleData) {
                                    boolHandleData = false;
                                } else {
                                    return;
                                }
                                getUpdateQuoDData();
                                getUpdateNewQuoDData();
                                reqChangeQuoData();
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
                            synchronized ("QuoODBoolUI") {
                                if (boolHandleUI) {
                                    boolHandleUI = false;
                                } else {
                                    return;
                                }
                                changeQuoData();
                            }
                            break;
                        case 1://更新UI
                            cusTreDrawTool.updateDraw();
                            break;
                        case 2://更改日期
                            break;
                        default:
                            break;
                    }
                }
            };
        }
        /**
         * 初始化时间周期按键
         */
        void init_ButtonUI() {
            buttonA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewLinerLoA.getVisibility() == View.VISIBLE) {
                        viewLinerLoA.setVisibility(View.INVISIBLE);
                    } else {
                        viewLinerLoA.setVisibility(View.VISIBLE);
                    }

                }
            });
            for (int i = 0; i < buttonsTimeA.length; i++) {
                buttonsTimeA[i].setOnClickListener(onClickListener);
            }

        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////]Log.w(this.toString(), "buttonUITagA0: "+"="+v.getTag() );
                /// //]Log.w(this.toString(), "buttonUITagA0: "+"="+((String) v.getTag()) );
                ////]Log.w(this.toString(), "buttonUITagA0: "+"="+((int)v.getTag()) );
                int int_A = Integer.valueOf(v.getTag().toString());
                if (buttonUITag != int_A) {
                    buttonUITag = int_A;
                    setQuoTimeMode(int_A);

                }
                viewLinerLoA.setVisibility(View.INVISIBLE);
            }

        };

        /**
         * 赋值时间周期
         */
        public void setQuoTimeMode(int intAA) {
            if(true)
                {
                    //intTimeMode = intAA;

                    setTimeCycle(intAA);
                    buttonA.setText(String.valueOf(intTimeCycle));
                    cusTreDrawTool.setTrendLine(intTimeSize,intTimeCycle);
                    cusTreDrawTool.updateTimeDatsTime();
                    cusTreDrawTool.calTimeDat();
                    cusTreDrawTool.updateDraw();
                    //quoODSystemConnect.setTrendPlanData();
                   // quoODSystemConnect.updateTrendData();
                    //quoODSystemConnect.StartDrawCusTrend();
                }

        }
        //修改时间周期
        public void setTimeCycle(int int_A) {
            intTimeCycle=int_A;

        }


        //修改时间大小
        public void setTimeSize(int int_A) {
            intTimeSize=int_A;

        }

//        /**
//         *赋值报价数量
//         */
//        void setTimeDatsNum(int int_Num){
//            intNum=int_Num;
//            timeDats=new TimeDat[int_Num];
//
//        }
        /**
         * 请求更新临时数据
         */
        public void reqUpdateQuoData() {
            if (!boolHandleData) {
                boolHandleData = true;
                handlerData.sendEmptyMessage(0);
            }
        }

        /**
         * 更换临时数据
         */
        public void reqChangeQuoData() {
            if (!boolHandleUI) {
                boolHandleUI = true;
                handlerUI.sendEmptyMessage(0);
            }
        }
        /**
         * 更新获取QuoDData数据,存在就只切换位置
         */
        private void  getUpdateQuoDData() {
            if (!getQuoDDataBool()) {
                //---根据最新日期更新QuoDData数据,计算之前的数据根据更新日期是否可以重用
//                if(cusTreDrawTool.timeODNow==null)
//                    {
//                        cusTreDrawTool.timeODNow=timeConnect.getTimeNew();
//                    }
                TimeOD timeOD = cusTreDrawTool.timeODNow;
                Log.w(this.toString(), "getUpdateQuoDData: E0"+"="+(timeOD!=null) +"="+(timeConnect!=null) );
                Log.w(this.toString(), "getUpdateQuoDData: E1"+"="+(timeConnect.getTimeNew()!=null) );
                TimeOD timeOD_B = timeOD.PuC_getCrTimeOD(-1);
                SkillQuoName skillQuoName_A = skillQuoName;
                quoDDataA2 = quoConnect.getFXQuoData(skillQuoName, timeOD);
                quoDDataB2 = quoConnect.getFXQuoData(skillQuoName, timeOD_B);
               // getUpdateNewQuoDData();
            }
        }
        /**
         * 改新最新最大值数据
         */
        void getUpdateNewQuoDData() {
            TimeOD timeOD_A = cusTreDrawTool.timeODNow;
            if (quoDDataNew != null) {
                if (quoDDataNew.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                    if (quoDDataNew.skillName == skillQuoName) {
                        // quoDDataNew2=quoDDataNew;
                        quoDDataNew2 = null;

                        return;
                    }
                }
            }
            quoDDataNew2 = quoConnect.getFXQuoData(skillQuoName, timeOD_A);
        }

        /**
         * 更新切换临时数据
         */
        void changeQuoData() {
            quoDDataA = quoDDataA2;
            quoDDataB = quoDDataB2;
            //]Log.w(this.toString(), "updateUIDataJ0 " + "=" + (quoDDataA != null) + "=" + (quoDDataB != null) + "=" + (quoDDataA2 != null) + "=" + (quoDDataB2 != null));
            if (quoDDataNew2 != null) {
                quoDDataNew = quoDDataNew2;

            } else {
                //]Log.w(this.toString(), "updateUIData01: " + "=" + (quoDDataNew2 != null));
            }
            setQuoDDataBool(true);//cacQuoDataTime());
            cusTreDrawTool.updateDraw();
            //quoODSystemConnect.StartDrawCusTrend();
        }

        /**
         * 赋值货币
         */
        public void setSkillQuoMode(SkillQuoName skillNameAA) {
            skillQuoName=skillNameAA;
            setQuoDDataBool(false);

//            if(false)
//            {
//                odTimeData.setBoolTime(false);
//                painMainData = new PaintData(skillNameAA, odTimeData.intNum);
//                painMainData.fxNewD = -1;
//                painMainData.fxNewU = -1;
//
//            }

        }
        /**
         *修正QuoDData数据确定
         */
        public void setQuoDDataBool(Boolean bool_A){
            synchronized ("quoDDataBool"){
                quoDDataBool=bool_A;
            }
        }
        /**
         *QuoDData数据是否确定
         */
        public Boolean getQuoDDataBool(){
            synchronized ("quoDDataBool"){
                return quoDDataBool;
            }
        }
        public float[][] getQuoTrendData(TimeOD timeOD_AA,int int_LL,int int_NN,int int_TT,int int_Cyc){
            float[][] floats_rt=null;
            TimeOD timeOD_A=timeOD_AA;
            int intLast = int_LL;
            int intFMax = int_NN;
            //]Log.w(this.toString(), "getQuoTrendDataF0: "+"="+(getQuoDDataBool()) +"="+ (quoDDataA!=null)+"="+ (quoDDataB!=null));
            if(getQuoDDataBool()) {
                if (intLast == 0) {
                    floats_rt = quoDDataA.PuC_getDatas(timeOD_A, int_Cyc, intFMax);
                    //painMainData.setStrM(quoDDataA.getMaxMinData());
                } else {
                    int intT = int_TT;
                    floats_rt = new float[intFMax][];
                    float[][] floats_B = quoDDataA.PuC_getDatas(timeOD_A, int_Cyc, intT);
                    for (int i = 0; i < intT; i++) {
                        floats_rt[i] = floats_B[i];
                    }

                    floats_B = quoDDataB.PuC_getDatasT(intFMax - intT, int_Cyc);
                    for (int i = 0; i < (floats_B.length); i++) {
                        floats_rt[intT + i] = floats_B[i];
                    }

                }
            } else {
                SkillQuoName skillQuoName_A = skillQuoName;
                if (intLast == 0) {
                    if (quoDDataA != null && quoDDataA.intState >= 2 && (quoDDataA.skillName == skillQuoName_A) && quoDDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                        floats_rt = quoDDataA.PuC_getDatas(timeOD_A, int_Cyc, intFMax);
                        //painMainData.setStrM(quoDDataA.getMaxMinData());
                    } else if (quoDDataB != null && quoDDataB.intState >= 2 && (quoDDataB.skillName == skillQuoName_A) && quoDDataB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                        floats_rt = quoDDataB.PuC_getDatas(timeOD_A, int_Cyc, intFMax);
                    }
                } else {
                    TimeOD timeOD_B = timeOD_A.PuC_getCrTimeOD(-1);
                    timeOD_B.intsA[3] = 23;
                    timeOD_B.intsA[4] = 59;
                    int intT = int_TT;
                    int intS = intFMax - intT;
                    float[][] floats_B = new float[intFMax][];
                    float[][] floats_B2 = null;
                    if (true) {
                        if (quoDDataA != null && quoDDataA.intState >= 2 && (quoDDataA.skillName == skillQuoName_A) && quoDDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                            floats_B2 = quoDDataA.PuC_getDatas(timeOD_A, int_Cyc, intT);
                        } else if (quoDDataB != null && quoDDataB.intState >= 2 && (quoDDataB.skillName == skillQuoName_A) && quoDDataB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                            floats_B2 = quoDDataB.PuC_getDatas(timeOD_A, int_Cyc, intT);
                        }

                    }
                    if (floats_B2 != null) {
                        for (int i = 0; i < intT; i++) {
                            floats_B[i] = floats_B2[i];
                        }
                    }
                    floats_B2 = null;

                    if (true) {

                        if (quoDDataA != null && quoDDataA.intState >= 2 && (quoDDataA.skillName == skillQuoName_A) && quoDDataA.timeOD.PuC_isTimeODSameDay(timeOD_B)) {
                            floats_B2 = quoDDataA.PuC_getDatasT(intS, int_Cyc);
                        } else if (quoDDataB != null && quoDDataB.intState >= 2 && (quoDDataB.skillName == skillQuoName_A) && quoDDataB.timeOD.PuC_isTimeODSameDay(timeOD_B)) {
                            floats_B2 = quoDDataB.PuC_getDatasT(intS, int_Cyc);
                        }

                    }
                    if (floats_B2 != null) {
                        for (int i = intT; i < floats_B2.length; i++) {

                            floats_B[intT + i] = floats_B2[i];
                        }
                    }

                    floats_rt = floats_B;
                }
            }
            return floats_rt;
        }

        /**
         * Get line data float [ ] [ ].
         *
         * @param skillQuoName   the skill quo name
         * @param timeOD_AA      the time od aa
         * @param int_LL         是否超过时间
         * @param int_NN         数量
         * @param int_TT         超过时间的位置
         * @param int_MM         指标显示模式
         * @param int_Mom         时间周期
         * @return the float [ ] [ ]
         */
        float[][][] getLineData(SkillQuoName skillQuoName, TimeOD timeOD_AA, int int_LL, int int_NN, int int_TT,int int_Mom,int int_MM) {
            //float[][] floats_Data = new float[skillLineNemas.length][];
            float[][][] floats_A=new float[int_NN][][];
            TimeOD timeOD_A = timeOD_AA;
            int int_L = int_LL;
            //]Log.w(this.toString(), "getLineDataR0: "+"="+int_LL +"="+int_NN +"="+ int_TT +"="+int_Mom +"="+int_MM);
            if (getQuoDDataBool()) {
                switch (int_MM) {
                    case 0://单数据
                        floats_A = quoDDataA.getLineData(timeOD_A.intsA[3], timeOD_A.intsA[4],1,1);
                        break;
                    case 1://多数据
                        if (int_L == 0) {
                            floats_A = quoDDataA.getLineData(timeOD_A.intsA[3], timeOD_A.intsA[4],int_NN,int_Mom);
                        } else {
                            float[][][] floats_C;
                            floats_C=quoDDataA.getLineData(timeOD_A.intsA[3], timeOD_A.intsA[4],int_NN,int_Mom);
                            if(floats_C!=null)
                                {
                                for(int i=0;i<floats_C.length;i++)
                                    {
                                        floats_A[i]=floats_C[i];
                                    }
                                }
                            floats_C=quoDDataB.getLineDataB(int_NN-int_TT,int_Mom);
                            if(floats_C!=null)
                            {
                                for(int i=0;i<floats_C.length;i++)
                                {
                                    floats_A[i+(int_TT+1)]=floats_C[i];
                                }
                            }

                        }
                        break;
                    default:
                        break;
                }


            } else {
                SkillQuoName skillQuoName_A = skillQuoName;
                switch (int_MM) {
                    case 0://单数据
                        if (quoDDataA != null && quoDDataA.intState >= 2 && (quoDDataA.skillName == skillQuoName_A) && quoDDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                            floats_A = quoDDataA.getLineData(timeOD_A.intsA[3], timeOD_A.intsA[4],1,1);
                            //painMainData.setStrM(quoDDataA.getMaxMinData());
                        } else if (quoDDataB != null && quoDDataB.intState >= 2 && (quoDDataB.skillName == skillQuoName_A) && quoDDataB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                            floats_A = quoDDataB.getLineData(timeOD_A.intsA[3], timeOD_A.intsA[4],1,1);
                        }

                        break;
                    case 1://多数据
                        if (int_L == 0) {
                            if (quoDDataA != null && quoDDataA.intState >= 2 && (quoDDataA.skillName == skillQuoName_A) && quoDDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                floats_A = quoDDataA.getLineData(timeOD_A.intsA[3], timeOD_A.intsA[4],int_NN,int_Mom);
                                //painMainData.setStrM(quoDDataA.getMaxMinData());
                            } else if (quoDDataB != null && quoDDataB.intState >= 2 && (quoDDataB.skillName == skillQuoName_A) && quoDDataB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                floats_A = quoDDataB.getLineData(timeOD_A.intsA[3], timeOD_A.intsA[4],int_NN,int_Mom);
                            }
                        } else {

                            float[][][] floats_C=null;
                            if (quoDDataA != null && quoDDataA.intState >= 2 && (quoDDataA.skillName == skillQuoName_A) && quoDDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                floats_C=quoDDataA.getLineData(timeOD_A.intsA[3], timeOD_A.intsA[4],int_TT,int_Mom);
                            } else if (quoDDataB != null && quoDDataB.intState >= 2 && (quoDDataB.skillName == skillQuoName_A) && quoDDataB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                floats_C=quoDDataB.getLineData(timeOD_A.intsA[3], timeOD_A.intsA[4],int_TT,int_Mom);
                            }
                            if(floats_C!=null)
                            {
                                for(int i=0;i<floats_C.length;i++)
                                {
                                    floats_A[i]=floats_C[i];
                                }
                            }
                            TimeOD timeOD_B = timeOD_A.PuC_getCrTimeOD(-1);

                            if (quoDDataA != null && quoDDataA.intState >= 2 && (quoDDataA.skillName == skillQuoName_A) && quoDDataA.timeOD.PuC_isTimeODSameDay(timeOD_B)) {
                                floats_C=quoDDataA.getLineDataB(int_NN-int_TT,int_Mom);
                            } else if (quoDDataB != null && quoDDataB.intState >= 2 && (quoDDataB.skillName == skillQuoName_A) && quoDDataB.timeOD.PuC_isTimeODSameDay(timeOD_B)) {
                                floats_C=quoDDataB.getLineDataB(int_NN-int_TT,int_Mom);
                            }
                            if(floats_C!=null)
                            {
                                for(int i=0;i<floats_C.length;i++)
                                {
                                    floats_A[i+(int_TT+1)]=floats_C[i];
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }



            }
            //]Log.w(this.toString(), "getLineDataG0: "+"="+ floats_A.length +"="+int_MM +"="+int_NN);
            return floats_A;
        }
        float[] getQuoViewPos(){
            float float_position = cusTreDrawTool.floatViewLength - 150;
            float float_Each = cusTreDrawTool.floatMs02;//- (floatPositionGap >= 0 ? floatEachLength : 0);
            float float_Length=cusTreDrawTool.floatEachLength;
            float[] flaot_rt=new float[cusTreDrawTool.timeDats.length];
            for(int i=0;i<cusTreDrawTool.timeDats.length;i++)
            {
                flaot_rt[i]= ((float_position-((float_Length * i)+(float_Length-float_Each)))/float_position);
                //]Log.w(this.toString(), "getQuoViewPosH0: "+"="+i +"="+flaot_rt[i] +"="+ ((float_Length * i)-float_Each) +"="+float_position +"="+float_Length +"="+float_Each);

            }
            return flaot_rt;
        }
        /**
         * 赋值最新值数据
         */
        float[] getNewOnePaint(TimeOD timeOD_A) {
            float[]  floats_Data=new float[5];
            float[] floats_A=null;
            if (getQuoDDataBool()) {

                floats_A= quoDDataNew.PuC_getData(timeOD_A.intsA[3], timeOD_A.intsA[4]);
                if(floats_A!=null)
                    {
                        floats_Data[0]=floats_A[0];
                        floats_Data[1]=floats_A[1];
                    }
                floats_A=quoDDataNew.PuC_getMaxData();
                if(floats_A!=null)
                {
                    floats_Data[2]=floats_A[0];
                    floats_Data[3]=floats_A[1];
                    floats_Data[4]=floats_A[2];
                }

            } else {
                if (quoDDataNew != null) {
                    if (quoDDataNew.skillName == skillQuoName) {
                        if (quoDDataNew.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                            //floats_Data= quoDDataNew.PuC_getData(timeOD_A.intsA[3], timeOD_A.intsA[4]);
                            floats_A= quoDDataNew.PuC_getData(timeOD_A.intsA[3], timeOD_A.intsA[4]);
                            if(floats_A!=null)
                            {
                                floats_Data[0]=floats_A[0];
                                floats_Data[1]=floats_A[1];
                            }
                            floats_A=quoDDataNew.PuC_getMaxData();
                            if(floats_A!=null)
                            {
                                floats_Data[2]=floats_A[0];
                                floats_Data[3]=floats_A[1];
                                floats_Data[4]=floats_A[2];
                            }
                        }
                    }
                }

            }
            return floats_Data;


        }
        float[] getViewPos(){
            float[] floats_Data=new float[cusTreDrawTool.timeDats.length];
            float float_A=customizeTrend.getWidth();
            for(int i=0;i<floats_Data.length;i++)
                {
                    floats_Data[i]=(float_A/cusTreDrawTool.timeDats[i].floatPosition);
                }
            return floats_Data;
        }

        /**
         * 更新获取Quoline数据,存在就只切换位置
         */
        public void updateQuoLine() {

        }
        /**
         *整理
         *
         */
        float[][][] trimLinedData(float[][][] floats_Data,SkillLineNema[] skillLineNemas_A) {
            float[][][] floats_tr = new float[skillLineNemas_A.length][floats_Data.length][];
            for (int i = 0; i < floats_Data.length; i++) {
                if (floats_Data[i] != null) {

                    for (int e = 0; e < floats_Data[i].length; e++) {
                        if (floats_Data[i][e] != null) {

                            SkillLineNema skillLineNema_B = SkillLineNema.getSkill((int) floats_Data[i][e][0]);
                            for (int r = 0; r < skillLineNemas_A.length; r++) {
                                if (skillLineNemas_A[r] == skillLineNema_B) {
                                    floats_tr[r][i] = floats_Data[i][e];
                                }

                            }


                        }
                    }
                }
            }
            if(floats_tr!=null)
            {
                String string_B="";
                for(int f=0;f<floats_tr.length;f++)
                {
                    string_B+="{("+f+")";
                    if(floats_tr[f][0]!=null)
                    {
                        string_B+="="+floats_tr[f][0].length;
                        for(int w=1;w<floats_tr[f][0].length;w++)
                        {

                            if(true)
                            {
                                string_B+="="+(floats_tr[f][0][w]);
                            }

                        }
                    }else {
                        string_B+="=null";
                    }
                    string_B+="}";

                }
                //]Log.w(this.toString(), "trimLinedDataJ0: "+"="+skillLineNemas_A.length +"="+floats_Data.length +"="+string_B );

            }
            return floats_tr;
        }
        /**
         * 图表请求数据
         */
        @Override
        public void On_getQuoData(CustomizeTrend.CusTreDrawTool cusTreDrawTool_A) {
            float[][] floats_Data=getQuoTrendData(cusTreDrawTool_A.timeODNow,cusTreDrawTool_A.intLast,cusTreDrawTool_A.intNum,cusTreDrawTool_A.intT,intTimeCycle);
            float[] float_NewData=getNewOnePaint(cusTreDrawTool_A.timeODNew);
           // float[][][] floats_Line=getLineData(skillQuoName,cusTreDrawTool_A.timeODNow,cusTreDrawTool_A.intLast,cusTreDrawTool_A.intNum,cusTreDrawTool_A.intT,intTimeCycle,1);

            cusTreDrawTool_A.setTrendDraw(floats_Data,float_NewData);
            float[] floats_Pos=getViewPos();
            colorDuoView.lineDataPaint.setViewPos(floats_Pos);
            colorDuoView.startDraw();
            if(!getQuoDDataBool())
                {
                reqUpdateQuoData();
                }
//
////            TimeOD timeODNow = odTimeData.getTimeData();
////            TimeOD timeOD_B = timeODNow.PuC_getCrTimeOD(-1);
////            SkillQuoName skillQuoName_A = painMainData.skillName;
////            SkillQuoName[] skillQuoName_B = new SkillQuoName[painLineData.length];
////            //]Log.w(this.toString(), "On_getQuoDataK0: "+"="+(!odTimeData.getBoolTime())+"="+ timeODNow.intsA[2]+"="+quoDDataA.timeODNow.intsA[2]+"="+timeOD_B.intsA[2]+"="+quoDDataB.timeODNow.intsA[2] );
//            //]Log.w(this.toString(), "On_getQuoDataD0: " + "=" + odTimeData.getBoolTime());
//            if (!odTimeData.getBoolTime()) {
//                //]Log.w(this.toString(), "On_getQuoData: " + "=" + odTimeData.getBoolTime());
//                PuC_updatePaintData(false);
//                getNewOnePaint(false);
//                PuC_updateQuoDData();
//
//            } else {
//                PuC_updatePaintData(true);
//                getNewOnePaint(true);
//            }
        }

        @Override
        public void On_setQuoBool(Boolean bool_A) {
            setQuoDDataBool(bool_A);
        }

        @Override
        public Boolean On_getQuoBool() {
            return getQuoDDataBool();
        }

        @Override
        public void On_GetLineData(boolean bool_D, SkillLineNema[] skillLineNemas_A) {
            int int_A=bool_D?0:1;
            //]Log.w(this.toString(), "On_GetLineDataH0: "+"="+cusTreDrawTool.intLast+"="+cusTreDrawTool.intNum+"="+cusTreDrawTool.intT+"="+intTimeCycle+"="+int_A );
            float[][][] floats_Data=getLineData(skillQuoName,cusTreDrawTool.timeODNow,cusTreDrawTool.intLast,cusTreDrawTool.intNum,cusTreDrawTool.intT,intTimeCycle,int_A);
            //floats_Data=getLineData(skillQuoName,cusTreDrawTool.timeODNow,cusTreDrawTool.intLast,cusTreDrawTool.intNum,cusTreDrawTool.intT,intTimeCycle,0);
            //]Log.w(this.toString(), "On_GetLineDataK0: "+((floats_Data==null)?-1:floats_Data.length+"="+((floats_Data[0]==null)?-1:floats_Data[0].length+"="+((floats_Data[0][0]==null)?-1:floats_Data[0][0].length))));
            floats_Data=trimLinedData(floats_Data,skillLineNemas_A);
            //]Log.w(this.toString(), "On_GetLineDataK1: "+((floats_Data==null)?-1:floats_Data.length) +"="+floats_Data[0].length);
            float[] floats_Pos=getQuoViewPos();
            colorDuoView.updateLineData(skillLineNemas_A,floats_Data,floats_Pos);
        }


        //------------------------------------------------------------------------------------------





//        /**
//         * 外部请求举行更新临时数据
//         */
//        public void reqUpdateQuoData(int intWhatAA) {
//            synchronized ("QuoODBool") {
//                if (!boolHandleData) {
//                    boolHandleData = true;
//                    handlerData.sendEmptyMessage(intWhatAA);
//                }
//            }
//        }









//        boolean cacQuoDataTime(){
//            int intLast = odTimeData.intLast;//是否时间在中间
//            TimeOD timeODNow = odTimeData.getTimeData();
//            TimeOD timeOD_B = timeODNow.PuC_getCrTimeOD(-1);
//            SkillQuoName skillQuoName_A = painMainData.skillName;
//            SkillQuoName[] skillQuoName_B = new SkillQuoName[painLineData.length];
//            if(!(quoDDataA.skillName == skillQuoName_A && quoDDataA.timeODNow.PuC_isTimeODSameDay(timeODNow)))
//                {
//                    return false;
//
//                }
//            if(!(quoDDataB.skillName == skillQuoName_A && quoDDataB.timeODNow.PuC_isTimeODSameDay(timeOD_B)))
//            {
//                return false;
//
//            }
//            return true;
//        }

        // public OnGetQuoData onGetQuoData;



//        /**
//         * 赋值指数组合
//         */
//        public void PuC_setQuoLineModePain(QuoModeData quoModeDataAA) {
//            if (quoModeDataAA.intLine != -1) {
//                odTimeData.setBoolTime(false);
//                SkillQuoName[] skillQuoNames_A = quoModeDataAA.quoLineMOS[modeDataA.intLine].skillQuoNames;
//                painLineName = quoModeDataAA.quoLineMOS[modeDataA.intLine].name;
//                painLineData = new PaintData[skillQuoNames_A.length];
//                for (int i = 0; i < painLineData.length; i++) {
//                    painLineData[i] = new PaintData(skillQuoNames_A[i], odTimeData.intNum);
//                }
//
//            } else {
//                odTimeData.setBoolTime(false);
//                painLineData = new PaintData[0];
//                painMainData.fxNewD = -1;
//                painMainData.fxNewU = -1;
//            }
//        }



//        float floatViewLength;
//        float floatMs02;
//        float floatEachLength;
//
//        /**
//         * 赋值图表移动数据
//         */
//        public void setCustomizeData(float floatViewLength_A, float floatMs02_A, float floatEachLength_A) {
//            floatViewLength = floatViewLength_A;
//            floatMs02 = floatMs02_A;
//            floatEachLength = floatEachLength_A;
//            customizeTrend.setCharTimeNoze();
//            PuC_calTimeDat();
//        }
//
//        /**
//         * 计算图表时间数据
//         */
//        public void PuC_calTimeDat() {
//            if (true) {
//                quoOD.odTimeData.timeDats[0] = quoOD.odTimeData.new TimeDat(quoOD.odTimeData.timeODNow.intsA[3], quoOD.odTimeData.timeODNow.intsA[4]);
//                int int_F = quoOD.odTimeData.timeODNow.intsA[4] % intTimeMode;
//                int[] ints_A = new int[2];
//                quoOD.odTimeData.intLast = 0;
//                quoOD.odTimeData.intT = 0;
//                float float_position = floatViewLength - 150;
//                float float_Each = floatMs02;//- (floatPositionGap >= 0 ? floatEachLength : 0);
//                ints_A[0] = quoOD.odTimeData.timeDats[0].intH;
//                ints_A[1] = quoOD.odTimeData.timeDats[0].intM - int_F;//+2;
//                //]Log.w(this.toString(), "PuC_calTimeDatG0: " + float_Each);
//                //]Log.w(this.toString(), "PuC_calTimeDatF0A" + "=" + ints_A[0] + "=" + ints_A[1] + "=" + quoOD.odTimeData.timeDats.length);
//                for (int i = 0; i < quoOD.odTimeData.timeDats.length; i++) {
//
//                    if (ints_A[1] < 0) {
//                        ints_A[1] = 60 + ints_A[1];
//                        ints_A[0] -= 1;
//                        if (ints_A[0] < 0) {
//                            ints_A[0] = 23;
//                            quoOD.odTimeData.intLast++;
//                            quoOD.odTimeData.setBoolTime(false);
//                            quoOD.odTimeData.intT = i;//quoOD.odTimeData.timeDats.length-i;
//                        }
//
//                    }
//
//                    quoOD.odTimeData.timeDats[i] = quoOD.odTimeData.new TimeDat(ints_A[0], ints_A[1]);
//                    // float_position = floatViewLength - 10;
//                    quoOD.odTimeData.timeDats[i].floatPosition = ((float_position - (floatEachLength * i)) + float_Each);
//                    ints_A[1] -= intTimeMode;
//
//                }
//                //]Log.w(this.toString(), "PuC_calTimeDatF0: " + "=" + ints_A[0] + "=" + ints_A[1] + "=" + quoOD.odTimeData.intLast + "=" + quoOD.odTimeData.intT + "=" + intTimeMode);
//            }
//        }

//        /**
//         * 更新获取QuoDData数据,存在就只切换位置
//         */
//        private void PuC_updateQuoDData() {
//            if (!odTimeData.getBoolTime()) {
//                //---根据最新日期更新QuoDData数据,计算之前的数据根据更新日期是否可以重用
//                int intLast = odTimeData.intLast;//是否时间在中间
//                TimeOD timeODNow = odTimeData.getTimeData();
//                TimeOD timeOD_B = timeODNow.PuC_getCrTimeOD(-1);
//                SkillQuoName skillQuoName_A = painMainData.skillName;
//                SkillQuoName[] skillQuoName_B = new SkillQuoName[painLineData.length];
//                quoDDataA2 = quoConnect.getFXQuoData(painMainData.skillName, timeODNow);
//                quoDDataB2 = quoConnect.getFXQuoData(painMainData.skillName, timeOD_B);
//                if (false) {
//
//                    quoDDataA2 = quoDDataA;
//                    quoDDataB2 = quoDDataB;
//
//                    for (int i = 0; i < painLineData.length; i++) {
//                        skillQuoName_B[i] = painLineData[i].skillName;
//                    }
//                    boolean bool_A = true;//是否从文件获取数据
//
//                    FXDataPool.FXQuo.QuoDData quoDData_A = quoDDataA2;
//                    //>计算是否可重用数据
//                    if ((quoDData_A != null) && quoDData_A.skillName == skillQuoName_A && quoDData_A.timeODNow.PuC_isTimeODSameDay(timeODNow)) {
//                        //]Log.w(this.toString(), "dateUIDataE0: " + "=" + timeODNow.intsA[2] + "=" + quoDData_A.timeODNow.intsA[2]);
//                        quoDDataA2 = quoDData_A;
//                        quoDData_A = null;
//                        bool_A = false;
//                    }
//                    //>计算是否可重用数据
//                    if ((bool_A && quoDDataB2 != null) && (quoDDataB2.skillName == skillQuoName_A && quoDDataB2.timeODNow.PuC_isTimeODSameDay(timeODNow))) {
//                        quoDDataA2 = quoDDataB2;
//                        quoDDataB2 = null;
//                        bool_A = false;
//                        //]Log.w(this.toString(), "dateUIDataE1: " + "=" + timeODNow.intsA[2]);
//                    }
//                    //>从文件获取数据
//                    if (bool_A) {
//
//                        quoDDataA2 = quoConnect.getFXQuoData(painMainData.skillName, timeODNow);
//                        //]Log.w(this.toString(), "dateUIDataE2: " + "=" + timeODNow.intsA[2]);
//                    }
//                    //>调查更新QuoLine
//                    for (int i = 0; i < skillQuoName_B.length; i++) {
//                        Boolean bool_C = true;//是否从文件获取QuoLine数据
//                        for (int e = 0; e < quoDDataA2.quoDayLines.length; e++) {
//                            //>是否有QuoLine数据
//                            if (quoDDataA2.quoDayLines[e].skillName == skillQuoName_B[i]) {
//                                bool_C = false;
//                                break;
//                            }
//                        }
//                        //>从文件获取QuoLine数据
//                        if (bool_C) {
//                            FXDataPool.FXQuo.QuoDayLine quoDayLine_A = quoConnect.getFXQuoLine(skillQuoName_A, skillQuoName_B[i], timeODNow);
//                            quoDDataA2.addQuoLine(quoDayLine_A);
//                        }
//
//                    }
//                    //>是否时间在中间,更新第二数据
//                    if (true || intLast > 0) {
//                        boolean bool_B = true;
//                        //>计算是否可重用数据
//                        if ((quoDDataB2 != null) && (quoDDataB2.skillName == skillQuoName_A && quoDDataB2.timeODNow.PuC_isTimeODSameDay(timeOD_B))) {
//                            bool_B = false;
//                        }
//                        //>计算是否可重用数据
//                        if (bool_B && (quoDData_A != null) && (quoDData_A.skillName == skillQuoName_A && quoDData_A.timeODNow.PuC_isTimeODSameDay(timeOD_B))) {
//                            quoDDataB2 = quoDData_A;
//                            quoDData_A = null;
//                            bool_B = false;
//                        }
//                        //>从文件获取数据
//                        if (bool_B) {
//
//                            quoDDataB2 = quoConnect.getFXQuoData(painMainData.skillName, timeOD_B);
//                            //]Log.w(this.toString(), "PuC_updateQuoDDataV0: " + "=" + (quoDDataB2 != null));
//                            if (quoDDataB2 != null) {
//                                //]Log.w(this.toString(), "PuC_updateQuoDDataV1: " + "=" + (quoDDataB2.quoDayData != null));
//                            }
//                        }
//                        //>更新QuoLine
//
//                        for (int i = 0; i < skillQuoName_B.length; i++) {
//                            Boolean bool_C = true;
//                            for (int e = 0; e < quoDDataB2.quoDayLines.length; e++) {
//                                if (quoDDataB2.quoDayLines[e].skillName == skillQuoName_B[i]) {
//                                    bool_C = false;
//                                    break;
//                                }
//                            }
//                            if (bool_C) {
//                                FXDataPool.FXQuo.QuoDayLine quoDayLine_A = quoConnect.getFXQuoLine(skillQuoName_A, skillQuoName_B[i], timeODNow);
//                                quoDDataB2.addQuoLine(quoDayLine_A);
//                            }
//
//                        }
//                    }
//                }
//                getUpdateNewQuoDData();
//            }
//            //]Log.w(this.toString(), "PuC_updateQuoDDataJ0 " + "=" + (quoDDataA != null) + "=" + (quoDDataB != null) + "=" + (quoDDataA2 != null) + "=" + (quoDDataB2 != null));
//
//            reqUpdateQuoData();
//        }





//        /**
//         * 更新图表价格数据(True=不用重新调查)
//         */
//        void PuC_updatePaintData(boolean boolAA) {
//            int intLast = odTimeData.intLast;
//            int intFMax = odTimeData.intNum;
//            TimeOD timeOD_A = odTimeData.getTimeData();
//            painMainData.initFloData();
//            //]Log.w(this.toString(), "PuC_updatePaintData01: " + "=" + boolAA + "=" + intLast + "=" + customizeTrend.intNum + "=" + intTimeMode + "=" + intFMax);
//            if (boolAA) {
//
//                if (intLast == 0) {
//                    painMainData.fxDataF = quoDDataA.PuC_getDatas(timeOD_A, intTimeMode, intFMax);
//                    painMainData.setStrM(quoDDataA.getMaxMinData());
//                } else {
//                    int intT = odTimeData.intT;
//                    painMainData.fxDataF = new float[intFMax][];
//                    float[][] floats_B = quoDDataA.PuC_getDatas(timeOD_A, intTimeMode, intT);
//                    painMainData.setStrM(quoDDataA.getMaxMinData());
//                    //]Log.w(this.toString(), "PuC_updatePaintDataC01: " + "=" + intT + "=" + floats_B.length);
//                    //]Log.w(this.toString(), "PuC_updatePaintDataC02: " + "=" + timeOD_A.intsA[3] + "=" + timeOD_A.intsA[4]);
//                    for (int i = 0; i < intT; i++) {
//                        painMainData.fxDataF[i] = floats_B[i];
//                    }
//                    //]Log.w(this.toString(), "PuC_updatePaintDataJ0: " + "=" + intFMax + "=" + intT + "=" + intTimeMode);
//                    //]Log.w(this.toString(), "PuC_updatePaintDataJq: " + "=" + (quoDDataA != null) + "=" + (quoDDataB != null));
//                    floats_B = quoDDataB.PuC_getDatasT(intFMax - intT, intTimeMode);
//                    //]Log.w(this.toString(), "PuC_updatePaintDataC11: " + "=" + intT + "=" + floats_B.length);
//                    for (int i = 0; i < (floats_B.length); i++) {
//                        painMainData.fxDataF[intT + i] = floats_B[i];
//                    }
//
//                }
//            } else {
//                SkillQuoName skillQuoName_A = painMainData.skillName;
//                if (intLast == 0) {
//                    if (quoDDataA != null && quoDDataA.intState >= 2 && (quoDDataA.skillName == skillQuoName_A) && quoDDataA.timeODNow.PuC_isTimeODSameDay(timeOD_A)) {
//                        painMainData.fxDataF = quoDDataA.PuC_getDatas(timeOD_A, intTimeMode, intFMax);
//                        painMainData.setStrM(quoDDataA.getMaxMinData());
//                    } else if (quoDDataB != null && quoDDataB.intState >= 2 && (quoDDataB.skillName == skillQuoName_A) && quoDDataB.timeODNow.PuC_isTimeODSameDay(timeOD_A)) {
//                        painMainData.fxDataF = quoDDataB.PuC_getDatas(timeOD_A, intTimeMode, intFMax);
//                        painMainData.setStrM(quoDDataB.getMaxMinData());
//                    }
//                } else {
//                    TimeOD timeOD_B = timeOD_A.PuC_getCrTimeOD(-1);
//                    timeOD_B.intsA[3] = 23;
//                    timeOD_B.intsA[4] = 59;
//                    int intT = odTimeData.intT;
//                    int intS = intFMax - intT;
//                    float[][] floats_B = new float[intFMax][];
//                    float[][] floats_B2 = null;
//                    if (true) {
//                        if (quoDDataA != null && quoDDataA.intState >= 2 && (quoDDataA.skillName == skillQuoName_A) && quoDDataA.timeODNow.PuC_isTimeODSameDay(timeOD_A)) {
//                            floats_B2 = quoDDataA.PuC_getDatas(timeOD_A, intTimeMode, intT);
//                            painMainData.setStrM(quoDDataA.getMaxMinData());
//                        } else if (quoDDataB != null && quoDDataB.intState >= 2 && (quoDDataB.skillName == skillQuoName_A) && quoDDataB.timeODNow.PuC_isTimeODSameDay(timeOD_A)) {
//                            floats_B2 = quoDDataB.PuC_getDatas(timeOD_A, intTimeMode, intT);
//                            painMainData.setStrM(painMainData.strM = quoDDataB.getMaxMinData());
//                        }
//
//                    }
//                    if (floats_B2 != null) {
//                        for (int i = 0; i < intT; i++) {
//                            floats_B[i] = floats_B2[i];
//                        }
//                    }
//                    floats_B2 = null;
//
//                    if (true) {
//
//                        if (quoDDataA != null && quoDDataA.intState >= 2 && (quoDDataA.skillName == skillQuoName_A) && quoDDataA.timeODNow.PuC_isTimeODSameDay(timeOD_A)) {
//                            floats_B2 = quoDDataA.PuC_getDatasT(intS, intTimeMode);
//                        } else if (quoDDataB != null && quoDDataB.intState >= 2 && (quoDDataB.skillName == skillQuoName_A) && quoDDataB.timeODNow.PuC_isTimeODSameDay(timeOD_A)) {
//                            floats_B2 = quoDDataB.PuC_getDatasT(intS, intTimeMode);
//                        }
//
//                    }
//                    if (floats_B2 != null) {
//                        for (int i = intT; i < floats_B2.length; i++) {
//
//                            floats_B[intT + i] = floats_B2[i];
//                        }
//                    }
//
//                    painMainData.fxDataF = floats_B;
//                }
//            }
//            String string_C2 = ">";
//            for (int i = 0; i < painMainData.fxDataF.length; i++) {
//
//                if (painMainData.fxDataF[i] != null) {
//                    string_C2 += "=" + painMainData.fxDataF[i][0];
//                } else {
//                    string_C2 += "=null";
//                }
//            }
//            painMainData.ccPaintDataPos();
//
//            //]Log.w(this.toString(), "PuC_updatePaintData02: " + "=" + string_C2);
//        }




//        /**
//         * 当时间周期变动是更新数据
//         */
//        public void setDuoModeSt(int intAA) {
//
//            PuC_updatePaintData(true);
//            getNewOnePaint(true);
//        }
//
//        public void CloseData() {
//            quoConnect.removeOnUpdateUI(this);
//        }



        @Override
        public void OnGetLineTrendData() {
            float[][] floats_Data = new float[1][];
            SkillLineNema skillLineNema = SkillLineNema.getSkill(0);
            if (!getQuoDDataBool()) {
//                //]Log.w(this.toString(), "On_getQuoData: " + "=" + odTimeData.getBoolTime());
//                PuC_updatePaintData(false);
//                getNewOnePaint(false);
//                PuC_updateQuoDData();

            } else {
//                PuC_updatePaintData(true);
//                getNewOnePaint(true);
            }

        }

        @Override
        public void onUpdateQuoUI(int intUI) {
            switch (intUI) {
                case 0:

                    handlerUI.sendEmptyMessage(1);
                    break;
                case 4:
                    handlerUI.sendEmptyMessage(2);
                    break;
                case 2:
                    break;
                default:
                    break;
            }

        }

//        @Override
//        public void onUpdateTime(TimeOD timeODAA) {
//            //]Log.w(this.toString(), "onUpdateTime: " + "=" + timeODAA.intsA[3] + "=" + timeODAA.intsA[4]);
//            //customizeTrend.setUIPo(timeODAA.intsA[4]);
//            //odTimeData.setTimeNew(timeODAA);
//
//        }
//
//        @Override
//        public void onUpdateNewDay(TimeOD timeODAA) {
//
//            //odTimeData.setTimeNew(timeODAA);
//        }

    }
//        public class QuoODSystemConnect implements OnUpdateTime,OnGetLineTrendData,OnGetQuoData {
//            public QuoOD quoOD;
//            //public ODTimeData odTimeData;
//            public CustomizeTrend customizeTrend;
//            CustomizeTrend.CusTreDrawTool cusTreDrawTool;
//            LineTrendCenter lineTrendCenter;
//            FXDataConnect.TimeConnect timeConnect;
//
//            public QuoODSystemConnect(FXDataConnect fxDataConnectAA, View view_A) {
//                timeConnect=fxDataConnectAA.getTimeConnect(this);
//                customizeTrend=view_A.findViewById(R.id.customizeTrend_0);
//                customizeTrend.cusTreDrawTool.setQuoOD(this);
//                //cusTreDrawTool=customizeTrend.new CusTreDrawTool(this);
//                quoOD=new QuoOD(this,fxDataConnectAA,view_A);
//                lineTrendCenter=new LineTrendCenter(this,view_A);
//
//                quoOD.setQuoTimeMode(0);
//                //odTimeData=new ODTimeData(timeConnect.getTimeNew());
//
//            }
//
//            //初始化
//            public void init() {
//                //quoOD.timeODNew=timeConnect.getTimeNew();
//                //quoOD.timeODNow=timeConnect.getTimeNew();
//                //setTimeCycle(0);
//                //setTimeSize(0);
//                //int int_Num=customizeTrend.cusTreDrawTool.setTrendLine(quoOD.intTimeCycle,quoOD.intTimeSize);
//                //setTimeCycle(int_Num);
//                //quoOD.
//
//            }
//
//            //赋值走势数量
//            public void setTimeDatNum(int int_A) {
//                cusTreDrawTool.setTrendLine(quoOD.intTimeCycle,quoOD.intTimeSize);
//
//            }
//
//
//            //修改货币
//            public void setQuoSkillMode(SkillQuoName skillMode_A){
//                quoOD.setSkillQuoMode(skillMode_A);
//            }
//
//            //修改走势绘制规划
//            void setTrendPlanData() {
//                //int int_Num=customizeTrend.cusTreDrawTool.setTrendLine(quoOD.intTimeCycle,quoOD.intTimeSize);
//                //quoOD.setTimeDatsNum(int_Num);
//                setOdTrendTime();
//
//            }
//
//
//
//            void setQuoDDataBool(boolean bool_A){
//                quoOD.setQuoDDataBool(bool_A);
//            }
//
//            //赋值走势数据
//            public void updateTrendData() {
//                float[][] floats_Data=quoOD.getQuoTrendData();
//                float[] floats_Pos=ccPaintDataPos(floats_Data);
//                float[] floats_New=quoOD.getNewOnePaint();
//
//            }
//            public float[] ccPaintDataPos(float[][] floats_Data) {
//                float float_A = -1;//Max
//                float float_B = -1;//Min
//                for (int i = 0; i < floats_Data.length; i++) {
//                    if(floats_Data[i]!=null)
//                    {
//                        if (float_A != -1) {
//                            if(float_A<floats_Data[i][3])float_A=floats_Data[i][3];
//                        }else {
//                            float_A=floats_Data[i][3];
//                        }
//                        if (float_B != -1) {
//                            if(float_B>floats_Data[i][2])float_B=floats_Data[i][2];
//
//                        }else {
//                            float_B=floats_Data[i][2];
//                        }
//                    }
//                }
//                float float_C=0;
//                if(float_A!=-1)
//                {
//                    float_C=(float_A-float_B);
//                }else {
//                    float_A=0.0001F;
//                    float_B=0;
//                    float_C=0.0001F;
//                }
//                ////]Log.w(this.toString(), "ccPaintDataPos04B: "+"="+(float_A)+"="+(float_B)+"="+float_C +"="+(0.0001F)+"="+(0.0001F/2F)+"="+ BigDecimal.valueOf((0.0001F)));
//                float float_C2=float_C/2F;
//                if(float_C>0.0005F)
//                {
//                    float_C=float_C/2F;
//                }else {
//                    float_C=0.0005F/2F;
//                    // float float_D=(0.0001F-float_C)/2f;
//                    // float_A+=float_D;
//                    // float_B-=float_D;
//                }
//                //]Log.w(this.toString(), "ccPaintDataPosD0 "+"="+float_A +"="+float_B+"="+float_C2 +"="+(float_B+float_C));
//                //  float float_E2=(float_A-float_B)/2F;
//
//                float float_E=(float_B+float_C2);
//
//                //]Log.w(this.toString(), "ccPaintDataPos01: "+"="+(float_A-float_B)+"="+float_C);
//                //]Log.w(this.toString(), "ccPaintDataPos04: "+"="+(float_A)+"="+(float_B)+"="+float_C);
//                //]Log.w(this.toString(), "ccPaintDataPos02: "+"="+float_E+"="+float_C);
//                return new float[]{float_C,float_E};
//
//            }
//            public void StartDrawCusTrend(){
//                customizeTrend.PuC_ODTrend();
//            }
//
//
//            //请求更新Quo数据
//            void reqUpdateQuoData() {
//                quoOD.reqUpdateQuoData();
//
//            }
//
//            //获取更新Quo数据
//            public void getQuoDataUI() {
//
//
//            }
//            /**
//             *更新Quo数据
//             */
//            public void updateQuoDData(){
//
//            }
//            public void drawCusTrend(){
//
//            }
//
//
//            @Override
//            public void onUpdateTime(TimeOD timeODAA) {
//
//            }
//
//            @Override
//            public void onUpdateNewDay(TimeOD timeODAA) {
//
//            }
//
//            @Override
//            public void OnGetLineTrendData() {
//
//            }
//
//            @Override
//            public void On_getQuoData() {
//
//            }
//
//            @Override
//            public void On_setQuoBool(Boolean bool_A) {
//
//            }
//
//            @Override
//            public Boolean On_getQuoBool() {
//                return null;
//            }
//        }


//        public class QuoFX{
//            Paint paint;
//        }



//
//    public class ODTimeData {
//        //Calendar calendar;
//        //long longTimeCr;
//        //public TimeOD timeODCr;//初始化时间
//        public TimeOD timeODNew;
//        public TimeOD timeODNow;//最新时间
//        public int intLast;//最后时间
//        public int intT;//最后时间
//        public int intTimeGrade;//时间等级
//        public int intNum;
//        public TimeDat[] timeDats;//图表时间位置
//        public ArrayList<ODPaintData> paintData;
//        public boolean boolTimeData;//QuoDData数据是否完整
//        public int intTimeCycle;//时间周期
//        public int intTimeSize;//时间大小
//
//        public ODTimeData(TimeOD timeODAA,int intNumAA,int intGradeAA) {
//           // calendar=new GregorianCalendar();
//            setIntNum(intNumAA);
//            timeDats = new TimeDat[intNum];
//            intTimeGrade = intGradeAA;
//           // timeODCr=new TimeOD(timeODAA);
//            timeODNow=new TimeOD(timeODAA);
//            timeODNew=timeConnect.getTimeNew();
//            //]Log.w(this.toString(), "ODTimeData: "+"="+timeODNew.intsA[3]+"="+timeODNew.intsA[4] );
//            //longTimeCr=timeODCr.PuC_getTimeLong();
//        }
//        //
////        public ODTimeData(MyFXDataService.TimeOD timeODCrAA, MyFXDataService.TimeOD timeODNowAA, int intGradeAA) {
////            timeODCr = timeODCrAA;
////            timeODNow = timeODNowAA;
////            intTimeGrade = intGradeAA;
////            floatTimeNum=10;
////            timeDats = new TimeDat[floatTimeNum];
////        }
////        public boolean setTimeMove(int intMinu){
////            synchronized (this){
////                boolean bool_rt=false;
////                Calendar calendar=new GregorianCalendar();
////                calendar.setTimeInMillis(longTimeCr);
////                calendar.add(Calendar.MINUTE,intMinu);
////                TimeOD timeOD_B=new TimeOD(calendar.getTimeInMillis());
////                timeODNow=timeOD_B;
////                TimeOD timeODNew_C=timeConnect.getTimeNew();
////                //if(timeODNew_C.PuC_calTimeContrastMinu(timeODNow)==1){timeODNow=timeODNew_C ;bool_rt=true;}
////                if(!timeODNow.PuC_isTimeODSameDay(timeOD_B))
////                    {
////                        setBoolTime(false);
////                    }
////
////
////                //]Log.w(this.toString(), "setTimeMove(POPO)= [" + intMinu + "]"+"="+timeODNow.intsA[3] +"="+timeODNow.intsA[4]);
////                return bool_rt;
////            }
////        }
//        public void setIntNum(int intNumAA){
//            switch(intNumAA){
//                case 0:
//                    intNum=10;
//                    break;
//                case 1:
//                    intNum=19;
//                    break;
//                case 2:
//                    intNum=28;
//                    break;
//                case 3:
//                    intNum=46;
//                    break;
//                case 4:
//                    intNum=64;
//                    break;
//                default:
//                    intNum=10;
//                    break;
//            }
//            timeDats = new TimeDat[intNum];
//
//        }
//        public void setTimeDatsNum(int intNumAA){
//
//            timeDats = new TimeDat[intNumAA];
//
//        }
//        /**
//         *赋值QuoDData数据是否完整
//         */
//        public void setBoolTime(boolean boolAA){
//            synchronized (this){
//                boolTimeData=boolAA;
//            }
//        }
//        /**
//         *QuoDData数据是否完整[F=需要更新QuoDData数据]
//         */
//        public boolean getBoolTime(){
//            synchronized (this){
//                return boolTimeData;
//            }
//        }
//        /**
//         *获得当前移动时间
//         */
//        public TimeOD getTimeData(){
//
//            synchronized (this){
//                return timeODNow;
//            }
//        }
//        public void setTimeNew(TimeOD timeODAA){
//            timeODNew=timeODAA;
//        }
//        /**
//         *
//         */
//        public void PuC_getReqQuoOD(){
//
//        }
//
//
//
//
//    }
//
//    public class ODPaintData{
//        public SkillQuoName skillName;
//        public float[] floData;
//        public Paint paint;
//
//    }


}
