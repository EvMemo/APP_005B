package com.example.app_005.Tool.WindowView;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.app_005.Class.SkillQuoName;
import com.example.app_005.Interface.OnUpdateQuoData;
import com.example.app_005.Interface.OnUpdateTime;
import com.example.app_005.Tool.FXDataConnect;
import com.example.app_005.Tool.FXDataPool;
import com.example.app_005.Tool.TimeOD;

import java.util.ArrayList;

public class WindowQuoColTool implements OnUpdateQuoData, OnUpdateTime {
    public FXDataConnect.QuoConnect quoConnect;
    FXDataConnect.TimeConnect timeConnect;
    public ArrayList<FXDataPool.FXQuo.QuoDData> quoDData;
    public TimeOD timeODNew;
    public TimeOD timeODEnd;
    public WindowQuoDrDraw[] windowQuoDrDraws;
    public boolean boolDraw;
    Handler handlerUI;
    boolean boolHandleMineUI;
    boolean boolHandleDayUI;
    WindowRecycView.WindowRecycDrawQuo windowRecycDrawQuo;
    public WindowQuoColTool(FXDataConnect fxDataConnect_A, WindowRecycView.WindowRecycDrawQuo windowRecycDrawQuo_A){
        windowRecycDrawQuo=windowRecycDrawQuo_A;
        quoConnect=fxDataConnect_A.getQuoConnect(this);
        timeConnect=fxDataConnect_A.getTimeConnect(this);
        timeODNew=timeConnect.getTimeNew();
        timeODEnd=timeODNew.PuC_getCrTimeOD(-1);
        timeODEnd.intsA[3]=0;
        timeODEnd.intsA[4]=0;
        windowQuoDrDraws=new WindowQuoDrDraw[11];
        for(int i=0;i<windowQuoDrDraws.length;i++)
            {
                windowQuoDrDraws[i]=new WindowQuoDrDraw(10,1,timeODNew,SkillQuoName.getSkill(i));
            }

        boolDraw=true;
        handlerUI = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0://更换临时数据
                        synchronized ("BoolDrawTimeMineUI") {
                            if (boolHandleMineUI) {
                                boolHandleMineUI = false;
                            } else {
                                return;
                            }


                            //changeCalendarData();
                        }
                        updateTimeNew();
                        windowRecycDrawQuo.reqDraw();
                        break;
                    case 4://更新UI
                        synchronized ("BoolDrawTimeDayUI") {
                            if (boolHandleDayUI) {
                                boolHandleDayUI = false;
                            }else {
                                return;
                            }
                        }
                        updateTimeNewDay();
                        windowRecycDrawQuo.reqDraw();
                        break;
                    case 2://更改日期
                        break;
                    default:
                        break;
                }
            }
        };

        reqUpdateQuoDData();
    }
    void reqUpdateDrawTimeMine(){
        synchronized ("BoolDrawTimeMineUI") {
            if (!boolHandleMineUI) {
                boolHandleMineUI = true;
                handlerUI.sendEmptyMessage(0);
            }
        }
    }
    void reqUpdateDrawTimeDay(){
        synchronized ("BoolDrawTimeDayUI") {
            if (!boolHandleDayUI) {
                boolHandleDayUI = true;
                handlerUI.sendEmptyMessage(4);
            }
        }
    }
    void reqUpdateQuoDData(){
        TimeOD timeOD_N=new TimeOD(timeODNew);
        TimeOD timeOD_E=new TimeOD(timeODEnd);
        for(int i=0;i<windowQuoDrDraws.length;i++)
            {
                FXDataPool.FXQuo.QuoDData[] quoDDataR_A=new FXDataPool.FXQuo.QuoDData[2];
                quoDDataR_A[0]=quoConnect.getFXQuoData(windowQuoDrDraws[i].skillQuoName,timeOD_N);
                quoDDataR_A[1]=quoConnect.getFXQuoData(windowQuoDrDraws[i].skillQuoName,timeOD_E);
                windowQuoDrDraws[i].setQuoDData(quoDDataR_A);

                windowQuoDrDraws[i].updateMinuDrawData();

            }

    }
    public void updateTimeNew(){
        timeODNew=timeConnect.getTimeNew();
        for(int i=0;i<windowQuoDrDraws.length;i++)
            {
                windowQuoDrDraws[i].updateMinuDrawData();
            }

    }
    public void updateTimeNewDay(){
        timeODNew=timeConnect.getTimeNew();
        timeODEnd=timeODNew.PuC_getCrTimeOD(-1);
        timeODEnd.intsA[3]=0;
        timeODEnd.intsA[4]=0;
        reqUpdateQuoDData();
    }

    @Override
    public void onUpdateQuoUI(int intUI) {
        if(intUI==0)
            {
                reqUpdateDrawTimeMine();
            }
        else if(intUI==4)
            {
                reqUpdateDrawTimeDay();
            }

    }

    @Override
    public void onUpdateTime(TimeOD timeODAA) {

    }

    @Override
    public void onUpdateNewDay(TimeOD timeODAA) {

    }

    public class WindowQuoDrData{
        public SkillQuoName skillQuoName;
        public FXDataPool.FXQuo.QuoDData quoDDataA,quoDDataB;
        public WindowQuoDrData(SkillQuoName skillQuoName_A){
            skillQuoName=skillQuoName_A;

        }
        public void setData(TimeOD timeOD, FXDataPool.FXQuo.QuoDData quoDData_A,FXDataPool.FXQuo.QuoDData quoDData_B){

        }

    }
    public class WindowQuoDrDraw {
        public int intNum;
        public int intTimeMode;
        public boolean boolDraw;
        public boolean boolQuoDData;
        public TimeOD timeODStart, timeODLast;
        public float floatNewU, floatNewD;
        public float floatNewUH, floatNewDH;
        public float floatMax, floatMin;
        public int intMoveN;
        public float floatDrawQ;
        public float floatTouchNow;
        public float floatDrawDaL;
        public FXDataPool.FXQuo.QuoData quoDataNew;
        public boolean boolQuoNew;
        public WindowQuoDrawD windowQuoDrawDNew;
        public ArrayList<WindowQuoDrawD> windowQuoDrawDS;
        public SkillQuoName skillQuoName;
        public FXDataPool.FXQuo.QuoDData[] quoDData;
        public WindowQuoDrawTime windowQuoDrawTime;
        public WindowQuoDrawMT windowQuoDrawMT;
       // public FXDataPool.FXQuo.QuoDData quoDDataNew;

        public WindowQuoDrDraw(int intNum_A, int intTimeMode,TimeOD timeOD_A,SkillQuoName skillQuoName_A) {
            boolDraw = false;
            intNum = intNum_A;
            intMoveN=1;
            //timeODLast.intsA[3]=0;
            //timeODLast.intsA[4]=0;
            quoDData=new FXDataPool.FXQuo.QuoDData[0];
            skillQuoName=skillQuoName_A;
            floatDrawDaL=15;
            intNum=(int)((1000-180)/floatDrawDaL)+3;
            timeODStart = new TimeOD(timeOD_A.PuC_getTimeLong());
            timeODLast=timeODStart.PuC_getCrTimeODMinute(-intNum);
            windowQuoDrawTime=new WindowQuoDrawTime(60,floatDrawDaL,timeODStart);
            windowQuoDrawMT =new WindowQuoDrawMT(300F);
            floatTouchNow=1;
            intMoveN=0;
        }
        /**
         *赋值QuoDData
         */
        public void setQuoDData(FXDataPool.FXQuo.QuoDData[] quoDData_A){
            quoDData=quoDData_A;
            boolDraw=false;
            initDrDrawData();

            ccQuoDrawDataMax();
        }
        /**
         *重新加载走势数据
         */
        void initDrDrawData() {
            if (!boolDraw) {
                boolDraw = true;
                windowQuoDrawDS = new ArrayList<>();
                int int_T = ccTimeMinuDiffer(timeODStart, timeODLast) + 1;
                //]Log.w(this.toString(), "initDrDrawData: R0"+"="+int_T +"="+timeODStart.getStringTime() +"="+timeODLast.getStringTime() );

                TimeOD timeOD_A = timeODLast;
                FXDataPool.FXQuo.QuoDData quoDData_A = null;
                for (int i = 0; i < quoDData.length; i++) {
                    if (quoDData[i] != null && quoDData[i].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                        quoDData_A = quoDData[i];
                        break;
                    }

                }
                int int_H = timeOD_A.intsA[3];
                int int_M = timeOD_A.intsA[4];
                for (int i = 0; i < int_T; i++) {
                    if (int_M > 59) {
                        int_M = 0;
                        int_H++;
                        if (int_H > 23) {
                            int_H = 0;
                            timeOD_A = timeOD_A.PuC_getCrTimeOD(1);
                            for (int e = 0; e < quoDData.length; e++) {
                                if (quoDData[e] != null && quoDData[e].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                    quoDData_A = quoDData[e];
                                    break;
                                }

                            }
                        }
                    }
                    if (quoDData_A != null) {
                        //]Log.w(this.toString(), this.toString() + ".initDrDrawData: E0" + "=" + int_H + "=" + int_M);

                        windowQuoDrawDS.add(new WindowQuoDrawD(quoDData_A.getQuoData(int_H, int_M),int_H, int_M));
                    } else {
                        windowQuoDrawDS.add( new WindowQuoDrawD(null));
                    }
                    int_M++;
                }

            } else {
                // int int_T = ccTimeMinuDiffer(timeODStart, timeODLast) + 1;

                TimeOD timeOD_A = timeODLast;
                FXDataPool.FXQuo.QuoDData quoDData_A = null;
                for (int i = 0; i < quoDData.length; i++) {
                    if (quoDData[i] != null && quoDData[i].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                        quoDData_A = quoDData[i];
                        break;
                    }

                }
                int int_H = timeOD_A.intsA[3];
                int int_M = timeOD_A.intsA[4];
                for (int i = 0; i < windowQuoDrawDS.size(); i++) {
                    int_M++;
                    if (int_M > 59) {
                        int_M = 0;
                        int_H++;
                        if (int_H > 23) {
                            timeOD_A = timeOD_A.PuC_getCrTimeOD(1);
                            for (int e = 0; e < quoDData.length; e++) {
                                if (quoDData[e] != null && quoDData[e].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                    quoDData_A = quoDData[e];
                                    break;
                                }

                            }
                        }
                    }
                    if (windowQuoDrawDS.get(i).quoData == null) {
                        if (quoDData_A != null) {
                            windowQuoDrawDS.get(i).setData(quoDData_A.getQuoData(int_H, int_M));

                        }
                    }

                }
            }
            getQuoDrawNewData();
            ccQuoDrawNewMax();
        }
        /**
         *获取最新一手走势数据
         */
        public void getQuoDrawNewData(){
            for(int i=0;i<quoDData.length;i++)
                {
                    if(quoDData[i]!=null&&quoDData[i].timeOD.PuC_isTimeODSameDay(timeODNew))
                        {

                            FXDataPool.FXQuo.QuoData quoData_A=quoDData[i].getQuoData(timeODNew.intsA[3],timeODNew.intsA[4]);
                            if(quoData_A!=null)
                                {
                                    quoDataNew=quoData_A;
                                    windowQuoDrawDNew=new WindowQuoDrawD(quoData_A,timeODNew.intsA[3],timeODNew.intsA[4]);

                                }


                        }

                }
        }
        /**
         *获取最新一手走势数据的UI值
         */
        void ccQuoDrawNewMax(){
            //]Log.w(this.toString(), "ccQuoDrawNewMax: E0"+"="+ ((windowQuoDrawDNew!=null&&windowQuoDrawDNew.boolData)));
            if(windowQuoDrawDNew!=null&&windowQuoDrawDNew.boolData)
                {
                    boolQuoNew=true;
                    float float_C=floatMax-floatMin;
                    floatNewD=windowQuoDrawDNew.quoData.floatsA[0];
                            floatNewU=windowQuoDrawDNew.quoData.floatsA[1];
                    floatNewDH= float_C/(floatNewD-floatMin);
                    floatNewUH= float_C/(floatNewU-floatMin);
                  //  //]Log.w(this.toString(), "ccQuoDrawNewMax: E1"+"="+floatMax +"="+ floatMin +"="+floatNewD +"="+floatNewU);
                  //  //]Log.w(this.toString(), "ccQuoDrawNewMax: E2"+"="+ (floatNewD-floatMax));
                    windowQuoDrawMT.setNowFloat(floatNewU,floatNewD);
                }else {
                boolQuoNew=false;
            }
        }
        /**
         *获取走势数据最大小值和UI值
         */
        void ccQuoDrawDataMax(){
            floatMax=0;
            floatMin=0;
            boolean bool_A=true;
            for(int i=0;i<windowQuoDrawDS.size();i++)
                {
                    if(windowQuoDrawDS.get(i).boolData)
                        {
                            if(bool_A)
                                {
                                    bool_A=false;
                                    floatMin=windowQuoDrawDS.get(i).quoData.floatsA[0];
                                    floatMax=windowQuoDrawDS.get(i).quoData.floatsA[1];
                                }else {
                                if(windowQuoDrawDS.get(i).quoData.floatsA[0]<floatMin)floatMin=windowQuoDrawDS.get(i).quoData.floatsA[0];
                                if(windowQuoDrawDS.get(i).quoData.floatsA[1]>floatMax)floatMax=windowQuoDrawDS.get(i).quoData.floatsA[1];

                            }

                        }

                }
             //]Log.w(this.toString(), "ccQuoDrawDataMax: E0"+"="+windowQuoDrawDS.size()+"="+floatMax +"="+floatMin);
            if(!bool_A)
                {


                    float float_C=floatMax-floatMin;
                    float float_G=(skillQuoName.getFloat()*5F);
                   // //]Log.w(this.toString(), "ccQuoDrawDataMax: E3"+"="+ floatMax +"="+floatMin +"="+float_C +"="+float_G);
                    if(float_G>float_C)
                        {
                            float float_G2= (float_G-float_C/2F);
                            floatMax+=float_G2;
                            floatMin-=float_G2;
                            float_C=float_G;
                        }
                    float float_C2;
                    for(int i=0;i<windowQuoDrawDS.size();i++)
                    {
                        if(windowQuoDrawDS.get(i).boolData)
                        {
                            float_C2=(windowQuoDrawDS.get(i).quoData.floatsA[0]-floatMin)/float_C;
                           // //]Log.w(this.toString(), "ccQuoDrawDataMax: E1"+"="+float_C2 +"="+ (windowQuoDrawDS.get(i).quoData.floatsA[0]-floatMin) +"="+float_C);
                            windowQuoDrawDS.get(i).floatUI=float_C2;

                        }

                    }
                }else {
                //]Log.w(this.toString(), "ccQuoDrawDataMax: !!!" );
            }

            ccQuoMaxTT();
        }
        void ccQuoMaxTT(){
            windowQuoDrawMT.setMaxMin(floatMax,floatMin);
            float float_A=floatMax-floatMin;
            //]Log.w(this.toString(), "ccQuoMaxTT: E0"+"="+float_A +"="+floatMax +"="+floatMin );
            if(float_A>1)
                {

                }else {
                float float_B=float_A;
                //float float_YA=skillQuoName.getFloat();//0.0001F;
               // float float_YB=float_YA;
                for(int i=1;i<99;i++)
                    {
                      //  float_YA*=10F;


                        float_A*=10F;
                        if(float_A>1)
                            {

                            float float_C=((int)float_A);
                                ////]Log.w(this.toString(), "ccQuoMaxTT: E1"+"=["+ i+"]="+float_A +"="+float_B +"="+(float_C) +"="+float_YA +"="+floatMax);
                                ////]Log.w(this.toString(), "ccQuoMaxTT: E2"+"=["+ i+"]="+floatMax +"="+getFloatTTMin(i) +"="+float_B +"="+skillQuoName.getName());

                                float float_PA=float_C;
                                for(int e=0;e<i;e++)
                                    {
                                        float_PA/=10F;
                                    }
                                float float_PB=0;
                                int int_IA=0;
                                if(float_C>5)
                                {

                                   // //]Log.w(this.toString(), "ccQuoMaxTT: R0"+"=["+ i+"]="+float_B +"="+float_C +"="+float_PA +"="+(1F+float_PA) +"="+ (1F+getFloatTTMin(i)));
                                    float float_N=floatMin;
                                    float_PB=getFloatTTMin(i);

                                }else {

                                   // //]Log.w(this.toString(), "ccQuoMaxTT: R0b"+"=["+ i+"]="+float_B  +"="+float_C+"="+float_PA +"="+(1F+float_PA)+"="+(1F+getFloatTTMin2(i)));
                                    float_PB=getFloatTTMin2(i);
                                    int_IA++;
                                }
                                float float_PC=floatMin;
                                for(int e=0;e<i-int_IA;e++)
                                    {
                                        float_PC*=10F;
                                    }
                               // //]Log.w(this.toString(), "ccQuoMaxTT: Y0"+"="+float_PC +"="+ (i-int_IA) +"="+((int)float_PC)/getFloatTen(i-int_IA));
                                float_PC=((int)float_PC)/getFloatTen(i-int_IA);
//                                for(int e=0;e<i-int_IA;e++)
//                                {
//                                    float_PC/=10F;
//                                }
                               // //]Log.w(this.toString(), "ccQuoMaxTT: R1"+"="+float_PC +"="+floatMin +"="+floatMax +"="+(1F+float_PB) +"="+float_PA +"="+int_IA );
                                ArrayList<Float> floats_A=new ArrayList<>();
                                float float_PD=float_PC;
                                int int_PD=((int)(float_PD*10000F));
                                int int_PB=((int)(float_PB*10000F));
                                int int_Mx=((int)(floatMax*10000F));
                                int int_Mi=((int)(floatMin*10000F));
                                for(int e=0;e<99;e++)
                                    {
                                        if(int_PD>int_Mi&&int_PD<int_Mx)
                                            {
                                               // //]Log.w(this.toString(), "ccQuoMaxTT: R3"+"="+int_PD +"="+ ((float)int_PD/10000F));
                                                floats_A.add(((float)int_PD/10000F));
                                            }
                                        if(int_PD>int_Mx)
                                            {
                                            break;
                                            }
                                        int_PD+=int_PB;


                                    }
                                if(floats_A!=null)
                                    {
                                    windowQuoDrawMT.setArrFloat(floats_A);
                                    }
                                ////]Log.w(this.toString(), "ccQuoMaxTT: R2"+"="+floats_A.size() +"="+floats_A.get(0) +"="+floats_A.get(1) +"="+floats_A.get(floats_A.size()-1) +"="+float_PB +"="+floatMin +"="+int_IA +"="+float_PD);
                            break;
                            }
                    }

            }
        }
        float getFloatTTMin(int int_A){
            switch(int_A){
                case 1:
                    return 0.1F;
                case 2:
                    return 0.01F;
                case 3:
                    return 0.001F;
                case 4:
                    return 0.0001F;
                case 5:
                    return 0.00001F;
                default:
                    return 0;
            }
        }
        float getFloatTTMin2(int int_A){
            switch(int_A){
                case 1:
                    return 0.05F;
                case 2:
                    return 0.005F;
                case 3:
                    return 0.0005F;
                case 4:
                    return 0.00005F;
                case 5:
                    return 0.000005F;
                default:
                    return 0;
            }
        }
        float getFloatTen(int int_A){
            switch(int_A){
                case 1:
                    return 10F;
                case 2:
                    return 100F;
                case 3:
                    return 1000F;
                case 4:
                    return 10000F;
                case 5:
                    return 100000F;
                default:
                    return 1;
            }
        }

        /**
         *加载最新分钟走势数据
         */
        void updateMinuDrawData(){
            if(true)
                {

                    TimeOD timeOD_A = timeODStart;
                    TimeOD timeOD_B = timeODNew;
                    //]Log.w(this.toString(), "updateMinuDrawData: Q1"+"="+(!timeOD_A.PuC_isTimeODSameMINUTE(timeOD_B)) +"="+(ccTimeMinuDiffer(timeOD_B,timeOD_A)) +"="+(intMoveN==1) );
                    if(!timeOD_A.PuC_isTimeODSameMINUTE(timeOD_B))
                    {
                        int int_T=ccTimeMinuDiffer(timeOD_B,timeOD_A);
                        if(intMoveN==1)
                        {
                            //]Log.w(this.toString(), "updateMinuDrawData: G0"+"="+int_T +"="+ timeOD_B.getStringTime());

                            updateDrDrawData(int_T,timeOD_B);
                        }
                        getQuoDrawNewData();
                    }

                    ccQuoDrawDataMax();
                    ccQuoDrawNewMax();

                }

        }
        /**
         *加载最新一天走势数据
         */
        public void updateNewDayDrawData(){

        }
        /**
         *缩小UI增加走势数量
         */
        public void addDrDrawData(int int_A) {
            //int_A=int_A-int_A-int_A;
            //]Log.w(this.toString(), "addDrDrawData: E0"+"="+int_A );
            if(intMoveN!=2)
                {

            intNum += int_A;
            int int_B = 0;
            int int_B2 = 0;
                    TimeOD timeOD_E;
                    TimeOD timeOD_E2;
            synchronized ("TimeNew") {

                timeOD_E = timeODLast.PuC_getCrTimeODMinute(-int_A);
                if (timeODEnd.PuC_calTimeContrastMinu(timeOD_E) == 2) {
                    timeOD_E = new TimeOD(timeODEnd.PuC_getTimeLong());
                    int_B = ccTimeMinuDiffer(timeOD_E, timeODLast);
                    int_B2 = int_A - int_B;
                    intMoveN=2;
//                    if (int_B2 != 0) {
//                        timeOD_E = timeODStart.PuC_getCrTimeODMinute(int_B2);
//                        if (timeODNew.PuC_calTimeContrastMinu(timeOD_E) == 1) {
//                            timeOD_E = new TimeOD(timeODNew.PuC_getTimeLong());
//                            int_B2 = ccTimeMinuDiffer(timeOD_E, timeODStart);
//                            int_B2=int_A-int_B;
//                        }
//                    }

                } else {
                    int_B = int_A;
                }
            }
            FXDataPool.FXQuo.QuoDData quoDData_A = null;
            TimeOD timeOD_A = timeODLast;
                    //]Log.w(this.toString(), "addDrDrawData: E1"+"="+ int_B +"="+int_B2);
            if (int_B != 0) {
                if(int_B<0)
                    {
                        for (int i = 0; i < quoDData.length; i++) {
                            if (quoDData[i] != null && quoDData[i].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                quoDData_A = quoDData[i];
                                break;
                            }

                        }
                        int int_H = timeOD_A.intsA[3];
                        int int_M = timeOD_A.intsA[4];
                        for (int i = 0; i < int_A; i++) {
                            int_M--;
                            if (int_M < 0) {
                                int_M = 59;
                                int_H--;
                                if (int_H < 0) {
                                    int_H=23;
                                    timeOD_A = timeOD_A.PuC_getCrTimeOD(-1);
                                    for (int e = 0; e < quoDData.length; e++) {
                                        if (quoDData[e] != null && quoDData[e].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                            quoDData_A = quoDData[e];

                                            break;
                                        }

                                    }
                                }

                            }
                            if (quoDData_A != null) {
                                //]Log.w(this.toString(), "addDrDrawData: Y0"+"="+ int_H+"="+int_M );

                                windowQuoDrawDS.add(0, new WindowQuoDrawD(quoDData_A.getQuoData(int_H, int_M),int_H, int_M));
                            } else {
                                windowQuoDrawDS.add(0, new WindowQuoDrawD(null));
                            }
                        }
                    }else {
                    timeODLast=timeOD_E;
                    for(int i=int_B;i<0;i++)
                        {
                            windowQuoDrawDS.remove(0);
                        }
                    if(false)
                        {
                            for (int i = 0; i < quoDData.length; i++) {
                                if (quoDData[i] != null && quoDData[i].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                    quoDData_A = quoDData[i];
                                    break;
                                }

                            }
                            int int_H = timeOD_A.intsA[3];
                            int int_M = timeOD_A.intsA[4];
                            for (int i = 0; i < int_A; i++) {
                                int_M--;
                                if (int_M < 0) {
                                    int_M = 59;
                                    int_H--;
                                    if (int_H < 0) {
                                        int_H=23;
                                        timeOD_A = timeOD_A.PuC_getCrTimeOD(-1);
                                        for (int e = 0; e < quoDData.length; e++) {
                                            if (quoDData[e] != null && quoDData[e].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                                quoDData_A = quoDData[e];

                                                break;
                                            }

                                        }
                                    }

                                }
                                if (quoDData_A != null) {

                                    windowQuoDrawDS.add(0, new WindowQuoDrawD(quoDData_A.getQuoData(int_H, int_M),int_H, int_M));
                                } else {
                                    windowQuoDrawDS.add(0, new WindowQuoDrawD(null));
                                }
                            }
                        }

                }

                timeODLast=timeOD_E;

            }
            timeOD_A = timeODStart;
            if(int_B2!=0)
                {
                    for (int i = 0; i < quoDData.length; i++) {
                        if (quoDData[i] != null && quoDData[i].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                            quoDData_A = quoDData[i];
                            break;
                        }

                    }
                    int int_H = timeOD_A.intsA[3];
                    int int_M = timeOD_A.intsA[4];
                    for (int i = 0; i < int_A; i++) {
                        int_M++;
                        if (int_M >59) {
                            int_M = 0;
                            int_H++;
                            if (int_H>23) {
                                int_H=0;
                                timeOD_A = timeOD_A.PuC_getCrTimeOD(1);
                                for (int e = 0; e < quoDData.length; e++) {
                                    if (quoDData[e] != null && quoDData[e].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                        quoDData_A = quoDData[e];

                                        break;
                                    }

                                }
                            }

                        }
                        if (quoDData_A != null) {

                            windowQuoDrawDS.add(new WindowQuoDrawD(quoDData_A.getQuoData(int_H, int_M),int_H, int_M));
                        } else {
                            windowQuoDrawDS.add( new WindowQuoDrawD(null));
                        }
                    }
                }
            ccQuoDrawDataMax();
            //]Log.w(this.toString(), "addDrDrawData: E0" + "=" + int_A + "=" + intNum + "=" + windowQuoDrawDS.size());

                }
        }

        /**
         *更新走势绘制数据
         */
        public void updateDrDrawData(int int_A,TimeOD timeOD_N) {
            //]Log.w(this.toString(), "updateDrDrawData: R0"+"="+ boolDraw +"="+int_A +"="+timeOD_N.getStringTime() +"="+timeODStart.getStringTime());

            if (!boolDraw) {
                initDrDrawData();
                windowQuoDrawTime=new WindowQuoDrawTime(60,floatDrawDaL,timeODStart);
                windowQuoDrawMT =new WindowQuoDrawMT(300F);
            }
            if(true) {
                windowQuoDrawTime.modeTiTime(int_A);
                if (int_A > 0) {
                    for (int i = 0; i < int_A; i++) {
                        windowQuoDrawDS.remove(0);
                    }
                    TimeOD timeOD_A = timeODStart;
                    FXDataPool.FXQuo.QuoDData quoDData_A = null;
                    for (int i = 0; i < quoDData.length; i++) {
                        if (quoDData[i] != null && quoDData[i].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                            quoDData_A = quoDData[i];
                            break;
                        }

                    }
                    int int_H = timeOD_A.intsA[3];
                    int int_M = timeOD_A.intsA[4];
                    for (int i = 0; i < int_A; i++) {
                        int_M++;
                        if (int_M > 59) {
                            int_M = 0;
                            int_H++;
                            if (int_H > 23) {
                                int_H=0;
                                timeOD_A = timeOD_A.PuC_getCrTimeOD(1);
                                for (int e = 0; e < quoDData.length; e++) {
                                    if (quoDData[e] != null && quoDData[e].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                        quoDData_A = quoDData[e];
                                        break;
                                    }

                                }
                            }
                        }
                        if(quoDData_A!=null)
                            {
                               //]Log.w(this.toString(), "updateDrDrawData: T1"+((quoDData_A.getQuoData(int_H, int_M))!=null) +"="+int_H +"="+int_M);
                                windowQuoDrawDS.add(new WindowQuoDrawD(quoDData_A.getQuoData(int_H, int_M),int_H, int_M));
                            }else {
                           // //]Log.w(this.toString(), "updateDrDrawData: T0"+"="+timeOD_A.getStringTime() +"="+int_H +"="+int_M );
                            windowQuoDrawDS.add(new WindowQuoDrawD(null));

                        }
                    }
                    timeODStart.PuC_addMinute(int_A);
                    timeODLast.PuC_addMinute(int_A);

                }
                else if(int_A<0) {
                    int_A=int_A-int_A-int_A;
                    if(windowQuoDrawDS.size()==0)
                        {
                            return;

                        }
                    for (int i = 0; i < int_A; i++) {
                        windowQuoDrawDS.remove(windowQuoDrawDS.size()-1);
                    }
                    TimeOD timeOD_A = timeODLast;

                    ////]Log.w(this.toString(), "updateDrDrawData: G0"+"="+ int_A +"="+timeOD_A.getStringTime() +"="+timeODLast.getStringTime()+"="+timeODStart.getStringTime());
                    FXDataPool.FXQuo.QuoDData quoDData_A = null;
                    for (int i = 0; i < quoDData.length; i++) {
                        if (quoDData[i] != null && quoDData[i].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                            quoDData_A = quoDData[i];
                            break;
                        }

                    }
                    int int_H = timeOD_A.intsA[3];
                    int int_M = timeOD_A.intsA[4];
                    for (int i = 0; i < int_A; i++) {
                        int_M--;
                        if (int_M <0) {
                            int_M = 59;
                            int_H--;
                            if (int_H <0) {
                                int_H=23;
                                timeOD_A = timeOD_A.PuC_getCrTimeOD(-1);
                                for (int e = 0; e < quoDData.length; e++) {
                                    if (quoDData[e] != null && quoDData[e].timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                                        quoDData_A = quoDData[e];

                                        break;
                                    }

                                }
                            }

                        }
                        if(quoDData_A!=null)
                            {
                                ////]Log.w(this.toString(), "updateDrDrawData: Y0"+"="+ int_H +"="+int_M);

                                windowQuoDrawDS.add(0,new WindowQuoDrawD(quoDData_A.getQuoData(int_H, int_M),int_H, int_M));
                            }else {
                            windowQuoDrawDS.add(0,new WindowQuoDrawD(null));
                        }
                    }

                    timeODStart.PuC_addMinute(-int_A);
                    timeODLast.PuC_addMinute(-int_A);
                }

            }

            //]Log.w(this.toString(), "updateDrDrawData: R1"+"="+ boolDraw +"="+int_A +"="+timeOD_N.getStringTime() +"="+timeODStart.getStringTime());
        }
        /**
         *平移走势时间
         */
        public void moveQuoDrDrawTime(float float_A) {
            if(float_A>0)
                {
                    if(intMoveN==1)
                        {
                            floatTouchNow =(floatTouchNow+(float_A/floatDrawDaL));
                            if(floatTouchNow>1)
                                {
                                    floatTouchNow=1;
                                }
                        return;
                        }

                }else {
                if(intMoveN==2)
                {
                    return;
                }
            }
            float float_V=(floatTouchNow+(float_A/floatDrawDaL));
            int int_A=(int)float_V;
            //]Log.w(this.toString(), "moveQuoDrDrawTime: Y0"+"="+float_A +"="+ float_V +"="+int_A);
            //]Log.w(this.toString(), "moveQuoDrDrawTime: Y3"+"="+float_A +"="+ intMoveN +"="+timeODStart.getStringTime());
            if(int_A!=0)
                {

                    floatTouchNow=float_V-int_A;
                }else {
                floatTouchNow=float_V;
            }
            //floatTouchNow +=float_A/floatDrawDaL;

//            //]Log.w(this.toString(), "WRecycV.moveView: E0" +"="+floatTouchNow+"="+float_A +"="+ floatDrawDaL);
//            if(int_A>1)
//            {
//                floatTouchNow-=((int)floatTouchNow);
//            }else if(int_A<-1)
//            {
//                floatTouchNow-=((int)floatTouchNow);
//            }
//           // int int_A=0;
            if (int_A != 0) {

                //]Log.w(this.toString(), "moveQuoDrDrawTime: R0"+"="+ float_A +"="+int_A +"="+floatTouchNow);
                int int_B = int_A;
                if (int_B > 0&&intMoveN!=1) {
                    TimeOD timeOD_A=null;
                    synchronized ("TimeNew") {

                        timeOD_A = timeODStart.PuC_getCrTimeODMinute(int_A);




                    }

                    //]Log.w(this.toString(), "moveQuoDrDrawTime: R5"+"="+(timeODNew.PuC_calTimeContrastMinu(timeOD_A) == 1) +"="+timeODNew.PuC_calTimeContrastMinu(timeOD_A)+"="+timeODNew.getStringDate() +"="+timeOD_A.getStringDate());
                    if (timeODNew.PuC_calTimeContrastMinu(timeOD_A) == 1) {
                        intMoveN=1;
                        //]Log.w(this.toString(), "moveQuoDrDrawTime: H0"+"="+timeODStart.getStringTime() +"="+timeOD_A.getStringTime() +"="+ timeODNew.getStringTime() );
                        //]Log.w(this.toString(), "moveQuoDrDrawTime: R8"+"="+timeODStart.getStringTime()  );
                        timeOD_A = new TimeOD(timeODNew.PuC_getTimeLong());
                        //]Log.w(this.toString(), "moveQuoDrDrawTime: R8B"+"="+timeODStart.getStringTime()  );
                        int_B = ccTimeMinuDiffer(timeOD_A, timeODStart);
                        //]Log.w(this.toString(), "moveQuoDrDrawTime: R7"+"="+int_B +"="+timeOD_A.getStringTime() +"="+timeODStart.getStringTime() );
                        if (int_B != 0) {


                            //]Log.w(this.toString(), "updateDrDrawData: G1"+"="+int_B +"="+ timeOD_A.getStringTime());
                            updateDrDrawData(int_B,timeOD_A);
                            ccQuoDrawDataMax();
                        }

                    }else {
                        //]Log.w(this.toString(), "updateDrDrawData: G1a"+"="+int_B +"="+ timeOD_A.getStringTime());
                        updateDrDrawData(int_B,timeOD_A);
                        ccQuoDrawDataMax();
                        intMoveN=0;
                    }
                } else if(int_B<0&&intMoveN!=2) {

                    TimeOD timeOD_A=null;
                    synchronized ("TimeNew") {

                        timeOD_A = timeODLast.PuC_getCrTimeODMinute(int_A);
                        //]Log.w(this.toString(), "moveQuoDrDrawTime: J0"+"="+timeODLast.getStringTime() +"="+timeOD_A.getStringTime() +"="+int_A );



                    }
                    //]Log.w(this.toString(), "moveQuoDrDrawTime: R1"+"="+(timeODEnd.PuC_calTimeContrastMinu(timeOD_A))+"="+timeODEnd.getStringTime()+"="+timeOD_A.getStringTime() );
                    if (timeODEnd.PuC_calTimeContrastMinu(timeOD_A) == 2) {
                        intMoveN=2;
                        timeOD_A = new TimeOD(timeODEnd.PuC_getTimeLong());
                        int_B = ccTimeMinuDiffer( timeODLast,timeOD_A);
                        //]Log.w(this.toString(), "moveQuoDrDrawTime: H0"+"="+ int_B +"="+timeOD_A.getStringTime() +"="+timeODLast.getStringTime());
                        if (int_B != 0) {

                            //]Log.w(this.toString(), "updateDrDrawData: G2"+"="+int_B +"="+ timeOD_A.getStringTime());
                            updateDrDrawData(int_B,timeOD_A);
                            ccQuoDrawDataMax();

                        }

                    }else {
                        //]Log.w(this.toString(), "updateDrDrawData: G2a"+"="+int_B +"="+ timeOD_A.getStringTime());
                        updateDrDrawData(int_B,timeOD_A);
                        ccQuoDrawDataMax();
                        intMoveN=0;
                    }

                }
                if(intMoveN!=0)
                    {
                        floatTouchNow=1;
                    }
            }
            //]Log.w(this.toString(), "moveQuoDrDrawTime: R4"+"="+intMoveN );
            //]Log.w(this.toString(), "moveQuoDrDrawTime: R0"+"//////////////////////////////////");
        }
        void zoomView(float float_A){
            floatDrawDaL+=float_A;
            if(floatDrawDaL<9)
            {
                floatDrawDaL=9;
            }
            if(floatDrawDaL>40)
            {
                floatDrawDaL=40;
            }
            windowQuoDrawTime.setFloatWui(floatDrawDaL);
            int int_A=(int)((1000-180)/floatDrawDaL)+3;
            int_A=int_A-intNum;
            //intNum=int_A;
            //]Log.w(this.toString(), "zoomView: R0"+"="+float_A +"="+int_A +"="+floatDrawDaL );
            if(int_A!=0)
                {
                addDrDrawData(int_A);
                }
          //  ccDrawDaNum(floatDrawDaL);
        }
//        public void ccDrawDaNum(float float_A){
//            intDrawDaNum=(int)((width-180)/floatDrawDaL)+3;
//            //]Log.w(this.toString(), "WRecycV.zoomView: E0"+"="+intDrawDaNum +"="+ floatDrawDaL);
//            invalidate();
//        }
    }
    /**
     *计算第一大过第二多少分钟
     */
    int ccTimeMinuDiffer(TimeOD timeOD_A,TimeOD timeOD_B){
        long long_A=timeOD_A.PuC_getTimeLong();
        long long_B=timeOD_B.PuC_getTimeLong();
        long between_days=(long_A-long_B)/(1000*60);
        //]Log.w(this.toString(), "ccTimeMinuDiffer: E0"+"="+ between_days +"="+((int)between_days));
        return (int)between_days;

    }
    /**
     *计算第一大过第二多少天
     */
    int ccTimeDayDiffer(TimeOD timeOD_A,TimeOD timeOD_B){
        long long_A=timeOD_A.PuC_getTimeLong();
        long long_B=timeOD_B.PuC_getTimeLong();
        long between_days=(long_A-long_B)/(1000*3600*24);
        //]Log.w(this.toString(), "ccTimeDayDiffer: E0"+"="+ between_days +"="+((int)between_days));
        return (int)between_days;

    }


    public class WindowQuoDrawD{
        public boolean boolData;
        public FXDataPool.FXQuo.QuoData quoData;
        public float floatUI;
        public int intH,intM;
        public WindowQuoDrawD(FXDataPool.FXQuo.QuoData quoData_A){
            if(quoData_A!=null)
                {
                    boolData=true;
                    quoData=quoData_A;

                }else {
                boolData=false;
            }
        }
        public WindowQuoDrawD(FXDataPool.FXQuo.QuoData quoData_A,int int_H,int int_M){
            //]Log.w(this.toString(), "WindowQuoDrawD: T0"+"="+ int_H +"="+int_M);
            intH=int_H;
            intM=int_M;
            if(quoData_A!=null)
            {
                boolData=true;
                quoData=quoData_A;

            }else {
                boolData=false;
            }
        }
        public void setData(FXDataPool.FXQuo.QuoData quoData_A){
            if(quoData_A!=null)
            {
                boolData=true;
                quoData=quoData_A;

            }else {
                boolData=false;
            }
        }

    }
    public class WindowQuoDrawTime{
        public float floatW;
        public float floatWui;
        public float floatTiA,floatTiB;
        public int intTiA,intTiB,intTiC;
        public TimeOD timeODN;

        public WindowQuoDrawTime(float float_A,float float_B,TimeOD timeOD_A){
            timeODN=new TimeOD(timeOD_A);
            floatW=float_A;
            floatWui=float_B;
            intTiA=0;
            ccTiTimeFloat();
        }
        void ccTiTimeFloat(){
            float float_A=(floatW*2F)/floatWui;
            intTiC=((int)float_A);
            intTiB=intTiA+((int)float_A);
           // //]Log.w(this.toString(), "ccTiTimeFloat: E0"+"="+float_A +"="+((int)float_A) +"="+floatTiB );
        }
        public void setFloatWui(float float_A){
            floatWui=float_A;
            ccTiTimeFloat();
            //]Log.w(this.toString(), "setFloatWui: T0"+"="+ intTiA +"="+intTiC);
            if(intTiA>intTiC)
                {

                    ccTiTime();
                }
        }
        public void modeTiTime(int int_A){
            if(int_A!=0)
                {
                    if(int_A>0)
                    {
                        intTiA+=int_A;
                        ccTiTime();
                        intTiB=intTiA+intTiC;

                    }else {
                        intTiA+=int_A;
                        ccTiTime2();
                        intTiB=intTiA+intTiC;
                    }
                }


        }
        void ccTiTime(){
            if(intTiA>intTiC)
            {

                timeODN.PuC_addMinute(intTiC);
                intTiA=intTiA-intTiC;
                ccTiTime();
            }
        }
        void ccTiTime2(){
            if(intTiA<0)
            {
                timeODN.PuC_addMinute(-intTiC);
                intTiA=intTiA+intTiC;
                ccTiTime2();
            }
        }
    }
    public class WindowQuoDrawMT {
        public ArrayList<Float> floatsUI;
        public ArrayList<String> stringsQ;
        public float floatUIu,floatUId;
        public float floatPUI;
        public float floatDataMax,floatDataMin,floatDataU,floatDataD;
        public String stringQU,stringQD;
        public int intN;
        boolean boolData;
        public WindowQuoDrawMT(float float_A){
            floatPUI=float_A;
            floatsUI=new ArrayList<>();
            stringsQ=new ArrayList<>();
        }
        public void setMaxMin(float float_Max,float float_Min){
            floatDataMax=float_Max;
            floatDataMin=float_Min;
            boolData=true;
        }
        public void setArrFloat(ArrayList<Float> floats_A){
            if (boolData) {
                float float_A = floatDataMax - floatDataMin;
                floatsUI=new ArrayList<>();
                stringsQ=new ArrayList<>();
                for(int i=0;i<floats_A.size();i++)
                    {
                    float float_B=floats_A.get(i)-floatDataMin;
                        float_B=float_B/float_A;
                        floatsUI.add(float_B);
                        stringsQ.add(String.valueOf(floats_A.get(i)));
                    }
            }
        }
        public void setNowFloat(float float_U,float float_D){
            floatDataU=float_U;
            floatDataD=float_D;
            if(boolData)
                {

                    float float_A=floatDataMax-floatDataMin;
                    floatUIu=(floatDataU-floatDataMin)/float_A;
                    floatUId=(floatDataD-floatDataMin)/float_A;
                    stringQU=String.valueOf(floatDataU);
                    stringQD=String.valueOf(floatDataD);
                    //]Log.w(this.toString(), "setNowFloat: T0"+"="+ stringQU +"="+stringQD +"="+floatDataU +"="+floatDataD);
                }
        }
    }
    public class WindowQuoDrawArrD{
        public boolean boolData;
        public FXDataPool.FXQuo.QuoData quoData;
        public WindowQuoDrawArrD(FXDataPool.FXQuo.QuoData quoData_A){
            quoData=quoData_A;
        }

    }
    public class WindowQuoDrawPool{

        public ArrayList<WindowQuoDrawArrD> windowQuoDrawArrDS;
        int intQuoDDNum;
        public ArrayList<FXDataPool.FXQuo.QuoDData> quoDDataAr;
        public ArrayList<TimeOD> timeODSQuoAr;
        public SkillQuoName skillQuoName;
        boolean boolQuoIsR;
        public int intDrStart, intDrLast;
        public int intWinNum;
        public TimeOD timeODNow;
        public TimeOD timeODStart,timeODLast;
        public TimeOD timeODStartB,timeODLastB;
        HandlerThread handlerThreadData;
        Handler handlerData;
        Handler handlerUI;
        boolean boolHandleData;
        boolean boolHandleUI;

        public WindowQuoDrawPool(TimeOD timeOD_A,int intWinNum_A){
            handlerThreadData = new HandlerThread("WinQuoDr");
            handlerThreadData.start();
            handlerData = new Handler(handlerThreadData.getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0://请求更新临时数据
                            synchronized ("WinQuoDrHandleData") {
                                if (boolHandleData) {
                                    boolHandleData = false;
                                } else {
                                    return;
                                }
                                //updateCalendarData();
                                getWinQuoData();
                            }
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
                            synchronized ("WinQuoDrHandleUI") {
                                if (boolHandleUI) {
                                    boolHandleUI = false;
                                } else {
                                    return;
                                }
                                //changeCalendarData();
                            }
                            break;
                        default:
                            break;
                    }
                }
            };
        }

        public void initWinQuoData(TimeOD timeOD_A){

        }
        void setSkillQuoName(SkillQuoName skillQuoName_A){
            synchronized ("skillQuoName"){
                skillQuoName=skillQuoName_A;
            }

        }
        SkillQuoName getSkillQuoName(){
            synchronized ("skillQuoName"){
                return skillQuoName;

            }
        }
        void reqWinQuoData(ArrayList<TimeOD> timeODS_A){
            if(timeODS_A!=null||timeODS_A.size()!=0)
                {
                    synchronized ("timeODSQuoAr"){
                        if(true)
                            {
                                timeODSQuoAr=timeODS_A;
                            }
//                        for(int i=0;i<timeODS_A.size();i++)
//                            {
//                                Boolean bool_A=true;
//                                for(int e=0;e<timeODSQuoAr.size();e++)
//                                {
//                                    if(timeODS_A.get(i).PuC_isTimeODSameDay(timeODSQuoAr.get(i)))
//                                        {
//                                            bool_A=false;
//                                            break;
//                                        }
//
//                                }
//                                if(bool_A)
//                                    {
//                                        //timeODSQuoAr.a
//                                    }else{
//
//                                }
//                            }



                    }
                }

        }
        void getWinQuoData(){
            ArrayList<TimeOD> timeODS_A=null;
            synchronized ("timeODSQuoAr"){
                if(true)
                {
                    timeODS_A=timeODSQuoAr;//=timeODS_A;
                    timeODSQuoAr=null;
                }
            }
            if(timeODS_A!=null)
                {
                    SkillQuoName skillQuoName_A=getSkillQuoName();
                    ArrayList<FXDataPool.FXQuo.QuoDData> quoDDataAr_A=null;
                    for(int i=0;i<timeODS_A.size();i++)
                        {
                            FXDataPool.FXQuo.QuoDData quoDData_A=null;
                            quoDData_A=getArrQuoDData(skillQuoName_A,timeODS_A.get(i));
                            if(quoDData_A!=null)
                                {
                                    quoDDataAr_A.add(quoDData_A);
                                }else {
                                quoDData_A=getQuoPoolData(skillQuoName_A,timeODS_A.get(i));
                                quoDDataAr_A.add(quoDData_A);
                            }

                        }
                    updateQuoDData(quoDDataAr_A);

                }
        }
        FXDataPool.FXQuo.QuoDData getQuoPoolData(SkillQuoName skillQuoName_A,TimeOD timeOD_A){

            return  quoConnect.getFXQuoData(skillQuoName_A,timeOD_A);

        }
        void updateQuoDData( ArrayList<FXDataPool.FXQuo.QuoDData> quoDData_A){
            synchronized ("WinDrQuoDDataAr"){
                quoDData=quoDData_A;

            }

        }

        void updateWinQuoDraDer(){
            TimeOD timeOD_A,timeOD_B;
            synchronized ("TimeODDr"){
                timeOD_A=timeODStartB;
                timeOD_B=timeODLastB;
            }
            SkillQuoName skillQuoName_A=getSkillQuoName();
            if(timeOD_A!=null&&timeOD_B!=null)
                {

                    int int_A=ccTimeMinuDiffer(timeOD_A,timeODStart);
                    if(true||int_A!=0)
                        {
                            if(int_A>0)
                                {
                                    int int_C=ccTimeDayDiffer(timeOD_A,timeODStart);
                                    if(int_A==0)
                                        {

                                            FXDataPool.FXQuo.QuoDData quoDData_A =getArrQuoDData(skillQuoName_A,timeOD_A);
                                            if(quoDData_A!=null)
                                                {
                                                    int int_R1=timeODStart.intsA[3];
                                                    int int_R2=timeODStart.intsA[4]++;
                                                    for(int i=0;i<int_C;i++)
                                                    {
                                                        if(int_R2>59)
                                                        {
                                                            int_R1++;
                                                            int_R2=0;
                                                            if(int_R1>23)
                                                            {
                                                                //!!!

                                                            }

                                                        }
                                                        windowQuoDrawArrDS.add(new WindowQuoDrawArrD(quoDData_A.quoDayData.hourData[int_R1].dataMin[int_R2]));
                                                    }

                                                }else {
                                                TimeOD timeOD_C=new TimeOD(timeODStart.PuC_getTimeLong());
                                                ArrayList<TimeOD> timeODS_C=new ArrayList<>();
                                                timeODS_C.add(timeOD_C);
                                                reqWinQuoData(timeODS_C);
                                            }

                                        }else {
                                        int int_C2=0;
                                        for(int i=0;i<=int_A;)
                                            {
                                                TimeOD timeOD_D=timeODStart.PuC_getCrTimeOD(i);
                                                FXDataPool.FXQuo.QuoDData quoDData_A =getArrQuoDData(skillQuoName_A,timeOD_D);
                                                if(quoDData_A!=null)
                                                    {

                                                        int int_R1=timeODStart.intsA[3];
                                                        int int_R2=timeODStart.intsA[4]++;
                                                        for(int e=int_C2;e<int_C;e++)
                                                        {
                                                            if(int_R2>59)
                                                            {
                                                                int_R1++;
                                                                int_R2=0;
                                                                if(int_R1>23)
                                                                {
                                                                    //!!!
                                                                    i++;
                                                                    break;

                                                                }

                                                            }
                                                            windowQuoDrawArrDS.add(new WindowQuoDrawArrD(quoDData_A.quoDayData.hourData[int_R1].dataMin[int_R2]));
                                                        }

                                                    }else {
                                                    ArrayList<TimeOD> timeODS_C=new ArrayList<>();
                                                    for(int r=i;r<=int_A;r++)
                                                        {
                                                            timeODS_C.add(timeODStart.PuC_getCrTimeOD(r));
                                                        }
                                                    reqWinQuoData(timeODS_C);

                                                }
                                            }

                                    }

                                }else {
                                for(int i=int_A;i>0;i++)
                                    {
                                        windowQuoDrawArrDS.remove(0);
                                        timeODStart=timeOD_A;
                                    }
                            }

                        }
                    if(boolQuoIsR)
                    {


                    }else {
                        int_A = ccTimeMinuDiffer(timeOD_B, timeODLast);
                        if (int_A != 0) {
                            if (int_A < 0) {
                                int int_C = ccTimeDayDiffer(timeOD_A, timeODStart);
                                if (int_A == 0) {

                                    FXDataPool.FXQuo.QuoDData quoDData_A = getArrQuoDData(skillQuoName_A, timeOD_A);
                                    if (quoDData_A != null) {
                                        int int_R1 = timeODStart.intsA[3];
                                        int int_R2 = timeODStart.intsA[4]++;
                                        for (int i = 0; i < int_C; i++) {
                                            if (int_R2 > 59) {
                                                int_R1++;
                                                int_R2 = 0;
                                                if (int_R1 > 23) {
                                                    //!!!

                                                }

                                            }
                                            windowQuoDrawArrDS.add(new WindowQuoDrawArrD(quoDData_A.quoDayData.hourData[int_R1].dataMin[int_R2]));
                                        }

                                    } else {
                                        TimeOD timeOD_C = new TimeOD(timeODStart.PuC_getTimeLong());
                                        ArrayList<TimeOD> timeODS_C = new ArrayList<>();
                                        timeODS_C.add(timeOD_C);
                                        reqWinQuoData(timeODS_C);
                                    }

                                } else {
                                    int int_C2 = 0;
                                    for (int i = 0; i <= int_A; ) {
                                        TimeOD timeOD_D = timeODStart.PuC_getCrTimeOD(i);
                                        FXDataPool.FXQuo.QuoDData quoDData_A = getArrQuoDData(skillQuoName_A, timeOD_D);
                                        if (quoDData_A != null) {

                                            int int_R1 = timeODStart.intsA[3];
                                            int int_R2 = timeODStart.intsA[4]++;
                                            for (int e = int_C2; e < int_C; e++) {
                                                if (int_R2 > 59) {
                                                    int_R1++;
                                                    int_R2 = 0;
                                                    if (int_R1 > 23) {
                                                        //!!!
                                                        i++;
                                                        break;

                                                    }

                                                }
                                                windowQuoDrawArrDS.add(new WindowQuoDrawArrD(quoDData_A.quoDayData.hourData[int_R1].dataMin[int_R2]));
                                            }

                                        } else {
                                            ArrayList<TimeOD> timeODS_C = new ArrayList<>();
                                            for (int r = i; r <= int_A; r++) {
                                                timeODS_C.add(timeODStart.PuC_getCrTimeOD(r));
                                            }
                                            reqWinQuoData(timeODS_C);

                                        }
                                    }

                                }

                            } else {
                                for (int i = int_A; i > 0; i++) {
                                    windowQuoDrawArrDS.remove(0);
                                    timeODStart = timeOD_A;
                                }
                            }
                        }
                    }

                }

        }
        FXDataPool.FXQuo.QuoDData getArrQuoDData(SkillQuoName skillQuoName_A,TimeOD timeOD_A){
            synchronized ("WinDrQuoDDataAr"){
                for(int i = 0; i< quoDDataAr.size(); i++)
                    {
                        if(skillQuoName_A==quoDDataAr.get(i).skillName&&timeOD_A.PuC_isTimeODSameDay(quoDDataAr.get(i).timeOD))
                            {
                                return quoDDataAr.get(i);

                            }

                    }


            }
            return null;

        }

        public void updateTimeNow(){

        }

    }

}
