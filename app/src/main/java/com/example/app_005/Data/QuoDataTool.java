package com.example.app_005.Data;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.example.app_005.Class.SkillQuoName;
import com.example.app_005.Service.MyFXDataService;
import com.example.app_005.Service.MyFXTimingService;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.HttpToop;
import com.example.app_005.Tool.MyFXTimeBR;
import com.example.app_005.Tool.MyFileIOTool;
import com.example.app_005.View.QuosSystemCenter;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * 报价数据工具
 */
public class QuoDataTool{
//    Context context;
//    MyFileIOTool fileIOTool;
//    Gson gson;
//    MyFXTimeBR fxTimeBR;
//    HandlerThread handlerThread_Col;
//    Handler handler_Col;
//    boolean handleColBool_0; String sy_ColBool="handleColBool_0";
//    HandlerThread handlerThread_Data;
//    Handler handler_Data;
//    FXFixedData fxFixedData;
//    public QuoDataTool(Context contextAA){
//        context=contextAA;
//        init();
//    }
//    public void init(){
//        fileIOTool=new MyFileIOTool();
//        gson=new Gson();
//        fxTimeBR=new MyFXTimeBR(context,this);
//
//        init_handlerCol();
//        init_handlerData();
//        fxFixedData=new FXFixedData(this, MyFXTimingService.ST_TimeOD);
//    }
//    public void init_handlerCol(){
//        handlerThread_Col =new HandlerThread("QuoHandler_Col");
//        handlerThread_Col.start();
//        handler_Col =new Handler(handlerThread_Col.getLooper() ) {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                switch(msg.what){
//                    case 0://请求收集报价数据//OK_20190718
//                        boolean bool_A=false;
//                        synchronized (sy_ColBool){
//                            bool_A=handleColBool_0;
//                            handleColBool_0=false;
//                            PuC_reqQuoCollectData();
//                        }
//                        if(bool_A)
//                            {
//
//                            }
//                        break;
//                    case 1:
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
//    }
//    public void init_handlerData(){
//        handlerThread_Data =new HandlerThread("QuoHandler_Data");
//        handlerThread_Data.start();
//        handler_Data =new Handler(handlerThread_Data.getLooper() ) {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                switch(msg.what){
//                    case 0://请求收集报价数据
//                        break;
//                    case 1:
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
//    }
//
//    /**
//     *请求收集报价数据//OK_20190718
//     */
//    public void PuC_reqQuoCollectData(){
//
//        CollData collData=PrC_collectQuoTrueFXData();
//       // PrC_saveCollectQuoData(collData);
//
//    }
//    /**
//     *请求获取报价文件数据//!!_20190715
//     */
//    public FXData PuC_reqGetQuoFileData(int[] intsAA, MyFXDataService.TimeOD timeODAA){
//        FXData fxData=null;
//        switch(intsAA[0]){
//            case 0:
//                fxData=PrC_getQuoCurrencyFileData(intsAA[1],timeODAA);
//
//                break;
//            default:
//                break;
//        }
//        return fxData;
//
//    }
//
//    /**
//     *请求保存报价数据到文件//!!_20190715
//     *
//     * @param fxData the fx data
//     */
//    public void Puc_reqSaveQuoData(FXData fxData){
//        switch(fxData.intMode){
//            case 0:
//                PrC_savaQuoDataToFile((FXQuoData)fxData.fxData);
//                break;
//            default:
//                break;
//        }
//    }
//    public FXDataPool.FXQuo.QuoDayData PuC_getQuoDDayData(SkillQuoName skillQuoNameAA, MyFXDataService.TimeOD timeODAA){
//        String string_A=(skillQuoNameAA.name()+ timeODAA.intsA[0] + "/" + (timeODAA.intsA[1] + 1) + "/" + timeODAA.intsA[2] + ".json");
//        return PrC_getQuoDDayCurrencyFileData(string_A);
//
//    }
//    public FXDataPool.FXQuo.QuoDayLine PuC_getQuoDDayLine(SkillQuoName skillQuoNameAA, MyFXDataService.TimeOD timeODAA){
//        String string_A=(skillQuoNameAA.name()+ timeODAA.intsA[0] + "/" + (timeODAA.intsA[1] + 1) + "/" + timeODAA.intsA[2] + ".json");
//        return PrC_getQuoDDayLineCurrencyFileData(string_A);
//
//    }
//
//
//    //----------------------------------------------------------------------------------------------
//    /**
//     *获取货币报价文件数据//OK_20190715
//     */
//    private FXDataPool.FXQuo.QuoDayData PrC_getQuoDDayCurrencyFileData(String stringFileAA){
//        String string_A=stringFileAA;
//        File file_A=fileIOTool.PuC_isQuoJSONFile(string_A);
//        FXDataPool.FXQuo.QuoDayData quoDayData_A =null;
//        if(file_A!=null)
//        {
//            String string_B=fileIOTool.getJSONFileData(file_A);
//            quoDayData_A =gson.fromJson(string_B, FXDataPool.FXQuo.QuoDayData.class);
//        }
//        return quoDayData_A;
//
//    }
//    private FXDataPool.FXQuo.QuoDayLine PrC_getQuoDDayLineCurrencyFileData(String stringFileAA){
//        String string_A=stringFileAA;
//        File file_A=fileIOTool.PuC_isQuoJSONFile(string_A);
//        FXDataPool.FXQuo.QuoDayLine quoDayLine_rt =null;
//        if(file_A!=null)
//        {
//            String string_B=fileIOTool.getJSONFileData(file_A);
//            quoDayLine_rt =gson.fromJson(string_B, FXDataPool.FXQuo.QuoDayLine.class);
//        }
//        return quoDayLine_rt;
//
//    }
//
//    /**
//     *保存报价数据到文件//OK_20190715
//     */
//    private void PrC_savaQuoDataToFile(FXQuoData fxQuoData){
//        String string_A=Puc_getQuoFileName(new int[]{0,fxQuoData.intQuo},fxQuoData.timeOD);
//        File file_A=new File(string_A);
//        String string_B=gson.toJson(fxQuoData);
//        fileIOTool.PuC_sevaJSONToFile(string_B,file_A);
//
//    }
////    /**
////     *保存收集报价数据到文件//OK_20190718
////     */
////    private void PrC_savaCollectQuoDataToFile(CollFXQuoTrueFXData fxDataAA) {
////        for (int i = 0; i < fxDataAA.stringssA.length; i++) {
////            String string_A = fileIOTool.PuC_getJSONQuoFileName(i, fxDataAA.timeOD);
////            File file_A = fileIOTool.PuC_isQuoJSONFile(string_A);
////            if (file_A != null) {
////                String string_B = fileIOTool.getJSONFileData(file_A);
////                FXQuoData fxQuoData_A = gson.fromJson(string_B, FXQuoData.class);
////                fxQuoData_A.PuC_setData(fxDataAA.stringssA[i]);
////                string_B = gson.toJson(fxQuoData_A);
////                fileIOTool.PuC_sevaJSONToFile(string_B, file_A);
////            } else {
////                FXQuoData fxQuoData_A = new FXQuoData(i, fxDataAA.timeOD);
////                fxQuoData_A.PuC_setData(fxDataAA.stringssA[i]);
////                String string_B = gson.toJson(fxQuoData_A);
////                fileIOTool.PuC_sevaJSONToFile(string_B, file_A);
////            }
////
////        }
////
////    }
//    /**
//     *
//     */
//    private void PrC_savaCollecToFile(int intAA, MyFXDataService.TimeOD timeODAA){
//
//    }
//    /**
//     * 获取报价文件名称
//     *
//     * @param intsAA   the ints aa
//     * @param timeODAA the time odaa
//     * @return the string
//     */
//    String Puc_getQuoFileName(int[] intsAA, MyFXDataService.TimeOD timeODAA){
//        String string_rt=null;
//        switch(intsAA[0]){
//            case 0:
//                string_rt=context.getExternalFilesDir("Quote").getPath();
//                switch(intsAA[1]+1){//"USD/JPY","GBP/USD","EUR/GBP","USD/CHF","EUR/JPY","EUR/CHF","USD/CAD","AUD/USD","GBP/JPY"
//                    case 0:
//                        break;
//                    case 1:
//                        string_rt+="/EURUSD";
//                        break;
//                    case 2:
//                        string_rt+="/USDJPY";
//                        break;
//                    case 3:
//                        string_rt+="/GBPUSD";
//                        break;
//                    case 4:
//                        string_rt+="/EURGBP";
//                        break;
//                    case 5:
//                        string_rt+="/USDCHF";
//                        break;
//                    case 6:
//                        string_rt+="/EURJPY";
//                        break;
//                    case 7:
//                        string_rt+="/EURCHF";
//                        break;
//                    case 8:
//                        string_rt+="/USDCAD";
//                        break;
//                    case 9:
//                        string_rt+="/AUDUSD";
//                        break;
//                    case 10:
//                        string_rt+="/GBPJPY";
//                        break;
//                    default:
//                        break;
//                }
//
//                break;
//            case 1:
//                break;
//            default:
//                break;
//        }
//        return string_rt + ("/" + timeODAA.intsA[0] + "/" + (timeODAA.intsA[1] + 1) + "/" + timeODAA.intsA[2] + ".json");
//
//    }
//
//    /**
//     *获取货币报价文件数据//OK_20190715
//     */
//    private FXData PrC_getQuoCurrencyFileData(int intAA, MyFXDataService.TimeOD timeODAA){
//        String string_A=Puc_getQuoFileName(new int[]{0,intAA},timeODAA);
//        File file_A=fileIOTool.PuC_isQuoJSONFile(string_A);
//        FXData fxData=new FXData<FXQuoData>(0);
//        if(file_A!=null)
//            {
//             String string_B=fileIOTool.getJSONFileData(file_A);
//                fxData.fxData=gson.fromJson(string_B,FXQuoData.class);
//            }else {
//            fxData.fxData=new FXQuoData();
//            ((FXQuoData) fxData.fxData).intQuo=intAA;
//        }
//        return fxData;
//
//    }
//    /**
//     *保存收集报价数据//OK_20190718
//     */
//    private void PrC_saveCollectQuoData(CollData collData) {
//        switch (collData.intMode) {
//            case 0:
//                CollFXQuoTrueFXData fxData_A= (CollFXQuoTrueFXData) collData.collData;
//                boolean bool_A=fxFixedData.PuC_addIsQuoData(fxData_A);
//                if(!bool_A)
//                    {
//                        PrC_savaCollectQuoDataToFile(fxData_A);
//                    }
//                break;
//            default:
//                break;
//        }
//    }
//    /**
//     *收集报价数据_truefx资源//OK_20190715
//     */
//    private CollData PrC_collectQuoTrueFXData() {
//        String string_A = "https://webrates.truefx.com/rates/connect.html?f=html";
//        try {
//            return CrC_FXString(HttpToop.getHtml(string_A));
//
//        } catch (Exception e) {
//            Log.e(this.toString(), e.toString());
//            e.printStackTrace();
//        }
//        return null;
//
//
//    }
//    /**
//     * 将网页转换成报价数据//OK_20190715
//     *
//     * @param stringAA the string aa
//     */
//    CollData CrC_FXString(String stringAA){
//        Calendar calendar_A=Calendar.getInstance();
//        calendar_A.set(Calendar.SECOND,0);
//        calendar_A.set(Calendar.MILLISECOND,0);
//        long long_A=calendar_A.getTimeInMillis();//-longA;
//
//        CollFXQuoTrueFXData fxQuotedData_A01;
//        String string_A=stringAA;
//        StringBuilder stringBuilder_A =new StringBuilder (stringAA);
//
//        //int int_A=stringBuilder_A.indexOf("<tr>")+4;
//        boolean boolean_A =true;
//        ArrayList<int[]> arrayList_A=new ArrayList<>();
//        int[] ints_A=new int[]{0,0};
//        while (boolean_A){
//            ints_A[0]=stringBuilder_A.indexOf("<tr>",ints_A[0]);
//            if(ints_A[0]==-1) {Log.d(TAG, "CrC_FXString_2");break;}
//
//            ints_A[1]=stringBuilder_A.indexOf("</tr>",ints_A[0]+4);
//            if(ints_A[1]==-1) {Log.d(TAG, "CrC_FXString_2");break;}
//            Log.d(TAG, "CrC_FXString_1: "+ints_A[0] +"="+ints_A[1]);
//            arrayList_A.add((new int[]{ints_A[0],ints_A[1]}));
//            ints_A[0]=ints_A[1]+5;
//            Log.d(TAG, "CrC_FXString_1A: "+arrayList_A.size());
//        }
//        Log.d(TAG, "CrC_FXString_0: "+arrayList_A.size());
//
//        if(arrayList_A.size()!=0)
//        {
//            fxQuotedData_A01=new CollFXQuoTrueFXData(long_A,arrayList_A.size());
//
//            ArrayList<String[]> arrayList_B=new ArrayList<>();
//            for(int i=0;i<arrayList_A.size();i++)
//            {
//                fxQuotedData_A01.stringssA[i]=(CrC_FXString_1(stringBuilder_A,arrayList_A.get(i)));
//            }
//            CollData collData=new CollData<CollFXQuoTrueFXData>(0);
//            collData.collData=fxQuotedData_A01;
//           return collData;
//
//        }else {
//            Log.e(TAG, "CrC_FXString: " );
//            return null;
//        }
//
//        //Log.d(TAG, "CrC_FXString_1: "+arrayList_A.get(0)[0] +"="+arrayList_A.get(0)[1]);
//
//    }
//    /**
//     * 转换成报价数据//OK_20190715
//     *
//     * @param stringBuilderAA the string builder aa
//     * @param intsAA          the ints aa
//     * @return the string [ ]
//     */
//    String[] CrC_FXString_1(StringBuilder stringBuilderAA,int[] intsAA ){
//        String[] strings_A=new String[4];//名,时,卖,买,小,大,开
//        String string_A=stringBuilderAA.substring(intsAA[0]+4,intsAA[1]);
//        //Log.d(TAG, "CrC_FXString_4: "+string_A);
//        int[] ints_B=new int[]{0,0};
//
//        for(int i=0;i<4;i++)
//        {
//            ints_B[0]=string_A.indexOf("<td>",ints_B[0]);
//            ints_B[1]=string_A.indexOf("</td>",ints_B[0]+4);
//            //Log.d(TAG, "CrC_FXString_5:"+string_A.substring(ints_B[0]+4,ints_B[1]) +"="+i);
//            switch(i){
//                case 0:
//                    strings_A[i]=string_A.substring(ints_B[0]+4,ints_B[1]);
//                    break;
//                case 2:
//                case 3:
//                    String string_B=string_A.substring(ints_B[0]+4,ints_B[1]);
//                    ints_B[0]=ints_B[1]+5;
//                    ints_B[0]=string_A.indexOf("<td>",ints_B[0]);
//                    ints_B[1]=string_A.indexOf("</td>",ints_B[0]+4);
//                    string_B+=string_A.substring(ints_B[0]+4,ints_B[1]);
//                    strings_A[i]=(string_B);
//                    break;
//                default:
//                    //int iny=1559940300020;
//                    strings_A[i]=string_A.substring(ints_B[0]+4,ints_B[1]);
//                    break;
//            }
//            ints_B[0]=ints_B[1]+5;
//
//        }
//
//       // Log.d(TAG, "CrC_FXString_6: "+strings_A[0] +"="+strings_A[1]+"="+strings_A[2]+"="+strings_A[3]+"="+strings_A[4]+"="+strings_A[5]+"="+strings_A[6]);
//        CollData collData=new CollData<String>();
//
//        return strings_A;
//
//    }
//    /**
//     *请求获取报价数据
//     */
//    @Override
//    public FXData PuC_reqGetQuoData(int[] intsAA, MyFXDataService.TimeOD timeODAA) {
//        FXData fxData=null;
//        switch(intsAA[0]){
//            case 0:
//                switch(intsAA[1]){
//                    case 0:
//                        break;
//                    default:
//                        break;
//                }
//                break;
//            default:
//                break;
//        }
//        return fxData;
//
//    }
//
//    /**
//     *定时闹钟=每分钟收集报价数据
//     */
//    @Override
//    public void PI_timeAddin() {
//        synchronized (sy_ColBool){
//            if(!handleColBool_0)
//                {
//                    handleColBool_0=true;
//                    Message message=new Message();
//                    handler_Col.sendEmptyMessage(0);
//                }
//        }
//    }
//
//    //-----Collect----------------------------------------------------------------------------------
//    public class CollData<T>{
//        int intMode;//[0=CollFXQuoTrueFXData]
//        T collData;
//        public CollData(){
//
//        }
//        public CollData(int intAA){
//            intMode=intAA;
//        }
//
//    }
//
//    /**
//     * The type Coll fx quo true fx data.
//     */
//    public class CollFXQuoTrueFXData {
//        public long longA;
//        public MyFXDataService.TimeOD timeOD;
//        /**
//         * 名,时,卖,买,小,大,开
//         */
//        public String[][] stringssA;
//        public CollFXQuoTrueFXData(long longAA, int intAA){
//            longA=longAA;
//            timeOD=new MyFXDataService.TimeOD(longAA);
//            stringssA=new String[intAA][];
//        }
//
//    }
//    //-----Data-------------------------------------------------------------------------------------
//    public class FXData<T>{
//        int intMode;//[0=FXQuoData]
//        T fxData;
//        public FXData(){
//
//        }
//        public FXData(int intAA){
//            intMode=intAA;
//        }
//    }
//    public class FXQuoData{
//        public int intQuo;//货币种类
//        public float flMax;
//        public float flMin;
//        public float flSta;//开
//        public MyFXDataService.TimeOD timeOD;
//        public FXQuoData(){
//
//        }
//        public FXQuoData(int intAA, MyFXDataService.TimeOD timeODAA){
//            intQuo=intAA;
//            timeOD=timeODAA;
//        }
//        public FXQuoH[] fxQuoH=new FXQuoH[24];
//        public class FXQuoH{
//            public FXQuoM[] fxQuoM=new FXQuoM[60];
//        }
//        public class FXQuoM{
//            public float[] floats;
//            public FXQuoM(){
//
//            }
//            public FXQuoM(float[] floatsAA){
//                floats=floatsAA;
//            }
//        }
//        public void PuC_setData(String[] stringsAA){
//
//        }
//    }
//
//    /**
//     * 报价固定数据
//     */
//    public class FXFixedData {
//        public FXQuoData[] fxQuoData0;
//        public FXQuoData[] fxQuoData1;
//        public MyFXDataService.TimeOD timeOD_0;
//        public MyFXDataService.TimeOD timeOD_1;
//
//        public FXFixedData(QuoDataTool quoDataToolAA, MyFXDataService.TimeOD timeODAA) {
//            quoDataTool = quoDataToolAA;
//            init_crData(timeODAA);
//        }
//
//        /**
//         * 初始化报价数据//OK_20190718
//         *
//         * @param timeODAA the time odaa
//         */
//        public void init_crData(MyFXDataService.TimeOD timeODAA) {
//            fxQuoData0 = new FXQuoData[10];
//            timeOD_0 = new MyFXDataService.TimeOD(timeODAA.intsA);
//            for (int i = 0; i < fxQuoData0.length; i++) {
//                fxQuoData0[i] = new FXQuoData(i, timeODAA);
//            }
//            fxQuoData1 = new FXQuoData[10];
//            timeOD_1 = timeOD_0.PuC_getCrTimeOD(-1);
//            for (int i = 0; i < fxQuoData1.length; i++) {
//                fxQuoData1[i] = new FXQuoData(i, timeODAA);
//            }
//
//
//        }
//
//        public boolean PuC_addIsQuoData(CollFXQuoTrueFXData trueFXDataAA) {
//            if (timeOD_0.PuC_isTimeODSameDay(trueFXDataAA.timeOD)) {
//                setQuoData(fxQuoData0,trueFXDataAA);
//                return true;
//            } else if (timeOD_1.PuC_isTimeODSameDay(trueFXDataAA.timeOD)) {
//                setQuoData(fxQuoData0,trueFXDataAA);
//                return true;
//            } else {
//                return false;
//            }
//        }
//
//        void setQuoData(FXQuoData[] fxQuoDataAA,CollFXQuoTrueFXData trueFXDataAA){
//            for(int i=0;i<trueFXDataAA.stringssA.length;i++)
//                {
//                    fxQuoDataAA[i].PuC_setData(trueFXDataAA.stringssA[i]);
//                }
//        }
//    }
//
//    /**
//     * 报价缓存数据
//     */
//    public class FXCachaQuoData implements MyFXTimeBR.IFXTimeBR {
//        public QuoDataTool quoDataTool;
//        public int floatTimeNum;//数量
//        public MyFXDataService myFXDataService;
//        public MyFXDataService.TimeOD[] timeODSReq;//请求拥有的缓存日期
//        public MyFXDataService.TimeOD[] timeODSNow;//现在拥有的缓存日期
//        public FXQuoData[] fxQuoDatasCacha;//现在拥有的缓存数据
//        public int[] intLineMode;
//        public IFXQuoData ifxQuoData;
//        MyFXTimeBR fxTimeBR;
//        public FXCachaQuoData(QuoDataTool quoDataToolAA, int intAA, MyFXDataService.TimeOD timeODAA){
//            if(intAA==0)return;
//            quoDataTool=quoDataToolAA;
//            ifxQuoData=quoDataTool;
//            floatTimeNum=intAA;
//            timeODSNow=new MyFXDataService.TimeOD[intAA];
//            myFXDataService=new MyFXDataService();
//            timeODSNow[0]=new MyFXDataService.TimeOD(timeODAA.PuC_getTimeLong());
//            for(int i=1;i<intAA;i++)
//                {
//                    timeODSNow[i]=timeODSNow[0].PuC_getCrTimeOD(-i);
//
//                }
//            fxTimeBR=new MyFXTimeBR(quoDataToolAA.context,this);
//        }
//        /**
//         *返回对应请求缓存数据
//         */
//        public void PuC_getCachaQuoData(QuosSystemCenter.QuoOD quoODAA){
//
//        }
//        /**
//         *更新缓存数据日期
//         */
//        public void PuC_updateCachaDate(){
//
//        }
//        /**
//         *赋值报价数据线类型
//         */
//        public void PuC_setLineMode(int[] intsAA){
//            intLineMode=intsAA;
//        }
//        /**
//         *计算是否更新现有缓存日期
//         */
//        public void PuC_calIsUpdateDataTime(int intAA,MyFXDataService.TimeOD timeODAA) {
//            if (intAA == 0) {
//                if (timeODSNow[0].PuC_calTimeContrast(timeODAA) == 1) {
//                    timeODSReq=new MyFXDataService.TimeOD[floatTimeNum];
//                    for(int i=1;i<intAA;i++)
//                    {
//                        timeODSReq[i]=timeODSReq[0].PuC_getCrTimeOD(-i);
//
//                    }
//                }
//
//            } else {
//                if (timeODSNow[0].PuC_calTimeContrast(timeODAA) == 2) {
//
//                }
//            }
//
//        }
//
//        /**
//         *请求举行更新报价数据
//         */
//        private void PrC_reqUpdateQuoData(){
//
//
//        }
//
//
//
//        /**
//         *定时任务接口
//         */
//        @Override
//        public void PI_timeAddin() {
//
//        }
//    }
//

}
