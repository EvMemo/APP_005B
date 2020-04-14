package com.example.app_005.Tool.WindowView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Interface.OnUpdateNewsData;
import com.example.app_005.Interface.OnUpdateSnapNewsData;
import com.example.app_005.Interface.OnUpdateTime;
import com.example.app_005.Tool.FXDataConnect;
import com.example.app_005.Tool.TimeOD;

import java.util.ArrayList;

public class SnapNewsDataTool implements OnUpdateNewsData, OnUpdateTime {
    FXDataConnect.NewsConnect newsConnect;
    FXDataConnect.TimeConnect timeConnect;
    ArrayList<OnUpdateSnapNewsData> onUpdateSnapNewsData;
    SnapNewsDataDT snapNewsDataDTA,snapNewsDataDTB;
    ArrayList<SnapNewsDataPool> snapNewsDataPoolsCacha;
    Handler handlerUI;
    boolean boolHandleUI;
    boolean boolHandleNewDay;

    public SnapNewsDataTool(){
        onUpdateSnapNewsData=new ArrayList<>();
        snapNewsDataPoolsCacha =new ArrayList<>();
        handlerUI = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0://更换临时数据
                        synchronized ("SnapNewsDataToolNewDay") {
                            if (boolHandleNewDay) {
                                boolHandleNewDay = false;
                                updateNewSnapNewsDataDT();
                            } else {
                                //return;
                            }
                        }
                        synchronized ("SnapNewsDataToolUI") {
                            if (boolHandleUI) {
                                boolHandleUI = false;
                            } else {
                                return;
                            }
                        }
                        updateSnapNewsData();
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
    public void initConnect(FXDataConnect fxDataConnect_A){

        newsConnect=fxDataConnect_A.getNewsConnect(this);
        timeConnect=fxDataConnect_A.getTimeConnect(this);
        snapNewsDataDTA=new SnapNewsDataDT(timeConnect.getTimeNew());
        snapNewsDataDTB=new SnapNewsDataDT(timeConnect.getTimeNew().PuC_getCrTimeOD(-1));
    }
    public void addOnUpdateSnapNewsData(OnUpdateSnapNewsData onUpdateSnapNewsData_A){
        synchronized ( "onUpdateSnapCalendarData"){
            onUpdateSnapNewsData.add(onUpdateSnapNewsData_A);
        }

    }
    public void removeOnUpdateSnapNewsData(OnUpdateSnapNewsData onUpdateSnapNewsData_A){
        synchronized ( "onUpdateSnapCalendarData"){
            onUpdateSnapNewsData.remove(onUpdateSnapNewsData_A);
        }
    }
    void updateNewSnapNewsDataDT(){
        TimeOD timeOD_A=timeConnect.getTimeNew();
        TimeOD timeOD_B=timeConnect.getTimeNew().PuC_getCrTimeOD(-1);
        SnapNewsDataDT snapNewsDataDT_A=null;
        if(snapNewsDataDTA==null)
            {
                snapNewsDataDTA=new SnapNewsDataDT(timeOD_A);
            }else if((!snapNewsDataDTA.timeOD.PuC_isTimeODSameDay(timeOD_A)))
                {
                    if(snapNewsDataDTA.timeOD.PuC_isTimeODSameDay(timeOD_B))
                        {
                            snapNewsDataDT_A=snapNewsDataDTA;
                        }
                    snapNewsDataDTA=new SnapNewsDataDT(timeOD_A);

                }
        if(snapNewsDataDTB==null||(!snapNewsDataDTA.timeOD.PuC_isTimeODSameDay(timeOD_B)))
            {
                if(snapNewsDataDT_A!=null)
                    {
                        snapNewsDataDTB=snapNewsDataDT_A;
                    }else {
                    snapNewsDataDTB=new SnapNewsDataDT(timeOD_B);
                }

            }

    }
    public void addSnapNewsData(NewsDataClass[] newsDataClasses_A, TimeOD timeOD_A){
        synchronized ("SnapNewsData"){
            snapNewsDataPoolsCacha.add(new SnapNewsDataPool(newsDataClasses_A,timeOD_A));
        }
        reqChangeCalendarData();

    }
    void reqChangeCalendarData(){
        synchronized ("SnapNewsDataToolUI"){
            if (!boolHandleUI) {
                boolHandleUI = true;
                handlerUI.sendEmptyMessage(0);
            }
        }

    }
    void updateSnapNewsData(){
        ArrayList<SnapNewsDataPool> snapNewsDataPools_A =new ArrayList<>();
        synchronized ("SnapNewsData"){
            snapNewsDataPools_A = snapNewsDataPoolsCacha;
            snapNewsDataPoolsCacha =new ArrayList<>();
        }
        if(snapNewsDataPools_A !=null&& snapNewsDataPools_A.size()!=0)
            {
            for(int i = 0; i< snapNewsDataPools_A.size(); i++)
                {
                    if(snapNewsDataPools_A.get(i).timeOD.PuC_isTimeODSameDay(snapNewsDataDTA.timeOD))
                        {
                            snapNewsDataDTA.addNewsData(snapNewsDataPools_A.get(i).newsDataClasses,snapNewsDataPools_A.get(i).timeOD);

                        }else if(snapNewsDataPools_A.get(i).timeOD.PuC_isTimeODSameDay(snapNewsDataDTB.timeOD))
                            {

                                snapNewsDataDTB.addNewsData(snapNewsDataPools_A.get(i).newsDataClasses,snapNewsDataPools_A.get(i).timeOD);
                            }

                }
            }
    }
    public void sentNewSnapNewsData(SnapNewsDataJ[] snapNewsDataJS_A,TimeOD timeOD_A){
        synchronized ( "onUpdateSnapCalendarData"){
            for(int i=0;i<onUpdateSnapNewsData.size();i++)
                {
                    onUpdateSnapNewsData.get(i).onUpdateSnapNewsData(snapNewsDataJS_A,timeOD_A);

                }

        }
    }

    @Override
    public void onUpdateNewsData(int int_Num) {

    }

    @Override
    public void onUpdateNewsData(NewsDataClass[] newsDataClasses_A) {

    }

    @Override
    public void onUpdateTime(TimeOD timeODAA) {

    }

    @Override
    public void onUpdateNewDay(TimeOD timeODAA) {
        synchronized ("SnapNewsDataToolNewDay"){
            if (!boolHandleNewDay) {
                boolHandleNewDay = true;
                handlerUI.sendEmptyMessage(0);
            }
        }
    }
    public class SnapNewsDataPool {
        public TimeOD timeOD;
        public NewsDataClass[] newsDataClasses;
        public SnapNewsDataPool(NewsDataClass[] newsDataClasses_A, TimeOD timeOD_A){
            newsDataClasses=newsDataClasses_A;
            timeOD=timeOD_A;
        }
    }

    public class SnapNewsDataDT{
        public TimeOD timeOD;
        public ArrayList<SnapNewsDataJ> snapNewsDataJS;
        public SnapNewsDataDT(TimeOD timeOD_A){
            timeOD=timeOD_A;
            snapNewsDataJS=new ArrayList<>();
        }
        public void addNewsData(NewsDataClass[] newsDataClasses_A,TimeOD timeOD_A){
            ArrayList<SnapNewsDataJ> snapNewsDataJS_A=new ArrayList<>();
            for(int i=0;i<newsDataClasses_A.length;i++)
                {
                    snapNewsDataJS_A.add(new SnapNewsDataJ(newsDataClasses_A[i]));

                }
            snapNewsDataJS.addAll(snapNewsDataJS_A);
            sentNewNewsData(snapNewsDataJS_A.toArray(new SnapNewsDataJ[snapNewsDataJS_A.size()]),timeOD_A);
        }
        public void sentNewNewsData(SnapNewsDataJ[] snapNewsDataJS,TimeOD timeOD_A){
            sentNewSnapNewsData(snapNewsDataJS,timeOD_A);
        }
    }
    public class SnapNewsDataJ{
        NewsDataClass newsDataClass;
        public boolean boolNew;
        public boolean boolSeen;
        public SnapNewsDataJ(NewsDataClass newsDataClass_A){
            newsDataClass=newsDataClass_A;
            boolNew=true;
            boolSeen=false;
        }
    }
}
