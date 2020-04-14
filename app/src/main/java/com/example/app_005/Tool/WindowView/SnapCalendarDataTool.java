package com.example.app_005.Tool.WindowView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.app_005.Class.CalendarDataClass;
import com.example.app_005.Interface.OnUpdateCalendarData;
import com.example.app_005.Interface.OnUpdateSnapCalendarData;
import com.example.app_005.Interface.OnUpdateTime;
import com.example.app_005.Tool.FXDataConnect;
import com.example.app_005.Tool.TimeOD;

import java.util.ArrayList;

public class SnapCalendarDataTool implements OnUpdateTime,OnUpdateCalendarData {
    FXDataConnect.CalendarConnect calendarConnect;

    FXDataConnect.TimeConnect timeConnect;
    ArrayList<OnUpdateSnapCalendarData> onUpdateSnapCalendarData;
    SnapNewsDataDT snapNewsDataDTA, snapNewsDataDTB;
    ArrayList<SnapCalendarDataPool> snapCalendarDataPools;
    Handler handlerUI;
    boolean boolHandleUI;
    boolean boolHandleNewDay;

    public SnapCalendarDataTool() {
        onUpdateSnapCalendarData = new ArrayList<>();
        snapCalendarDataPools = new ArrayList<>();
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
                        updateSnapCalendarData();
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

    public void initConnect(FXDataConnect fxDataConnect_A) {

        calendarConnect = fxDataConnect_A.getCalendarConnect(this);
        timeConnect = fxDataConnect_A.getTimeConnect(this);
        snapNewsDataDTA = new SnapNewsDataDT(timeConnect.getTimeNew());
        snapNewsDataDTB = new SnapNewsDataDT(timeConnect.getTimeNew().PuC_getCrTimeOD(-1));
    }

    public void addOnUpdateSnapCalendarData(OnUpdateSnapCalendarData onUpdateSnapCalendarData_A) {
        synchronized ("onUpdateSnapCalendarData") {
            onUpdateSnapCalendarData.add(onUpdateSnapCalendarData_A);
        }

    }

    public void removeOnUpdateSnapCalendarData(OnUpdateSnapCalendarData onUpdateSnapCalendarData_A) {
        synchronized ("onUpdateSnapCalendarData") {
            onUpdateSnapCalendarData.remove(onUpdateSnapCalendarData_A);
        }
    }

    void updateNewSnapNewsDataDT() {
        TimeOD timeOD_A = timeConnect.getTimeNew();
        TimeOD timeOD_B = timeConnect.getTimeNew().PuC_getCrTimeOD(-1);
        SnapNewsDataDT snapNewsDataDT_A = null;
        if (snapNewsDataDTA == null) {
            snapNewsDataDTA = new SnapNewsDataDT(timeOD_A);
        } else if ((!snapNewsDataDTA.timeOD.PuC_isTimeODSameDay(timeOD_A))) {
            if (snapNewsDataDTA.timeOD.PuC_isTimeODSameDay(timeOD_B)) {
                snapNewsDataDT_A = snapNewsDataDTA;
            }
            snapNewsDataDTA = new SnapNewsDataDT(timeOD_A);

        }
        if (snapNewsDataDTB == null || (!snapNewsDataDTA.timeOD.PuC_isTimeODSameDay(timeOD_B))) {
            if (snapNewsDataDT_A != null) {
                snapNewsDataDTB = snapNewsDataDT_A;
            } else {
                snapNewsDataDTB = new SnapNewsDataDT(timeOD_B);
            }

        }

    }

    public void addSnapCalendarData(CalendarDataClass[] calendarDataClasses_A, TimeOD timeOD_A) {
        synchronized ("SnapNewsData") {
            snapCalendarDataPools.add(new SnapCalendarDataPool(calendarDataClasses_A, timeOD_A));
        }
        reqChangeCalendarData();

    }

    void reqChangeCalendarData() {
        synchronized ("SnapNewsDataToolUI") {
            if (!boolHandleUI) {
                boolHandleUI = true;
                handlerUI.sendEmptyMessage(0);
            }
        }

    }

    void updateSnapCalendarData() {
        ArrayList<SnapCalendarDataPool> snapCalendarDataPools_A = new ArrayList<>();
        synchronized ("SnapNewsData") {
            snapCalendarDataPools_A = snapCalendarDataPools;
            snapCalendarDataPools = new ArrayList<>();
        }
        if (snapCalendarDataPools_A != null && snapCalendarDataPools_A.size() != 0) {
            for (int i = 0; i < snapCalendarDataPools_A.size(); i++) {
                if (snapCalendarDataPools_A.get(i).timeOD.PuC_isTimeODSameDay(snapNewsDataDTA.timeOD)) {
                    snapNewsDataDTA.addNewsData(snapCalendarDataPools_A.get(i).calendarDataClasses, snapCalendarDataPools_A.get(i).timeOD);

                } else if (snapCalendarDataPools_A.get(i).timeOD.PuC_isTimeODSameDay(snapNewsDataDTB.timeOD)) {

                    snapNewsDataDTB.addNewsData(snapCalendarDataPools_A.get(i).calendarDataClasses, snapCalendarDataPools_A.get(i).timeOD);
                }

            }
        }
    }

    public void sentNewSnapNewsData(SnapCalendarDataJ[] snapCalendarDataJS_A, TimeOD timeOD_A) {
        synchronized ("onUpdateSnapCalendarData") {
            for (int i = 0; i < onUpdateSnapCalendarData.size(); i++) {
                onUpdateSnapCalendarData.get(i).onUpdateSnapCalendarData(snapCalendarDataJS_A, timeOD_A);

            }

        }
    }


    @Override
    public void onUpdateTime(TimeOD timeODAA) {

    }

    @Override
    public void onUpdateNewDay(TimeOD timeODAA) {
        synchronized ("SnapNewsDataToolNewDay") {
            if (!boolHandleNewDay) {
                boolHandleNewDay = true;
                handlerUI.sendEmptyMessage(0);
            }
        }
    }

    @Override
    public void onSetCalendarData(int int_Num) {

    }

    @Override
    public void onUpdateCalendarData(CalendarDataClass[] calenderData_A) {

    }

    public class SnapCalendarDataPool {
        public TimeOD timeOD;
        public CalendarDataClass[] calendarDataClasses;

        public SnapCalendarDataPool(CalendarDataClass[] calendarDataClasses_A, TimeOD timeOD_A) {
            calendarDataClasses = calendarDataClasses_A;
            timeOD = timeOD_A;
        }
    }

    public class SnapNewsDataDT {
        public TimeOD timeOD;
        public ArrayList<SnapCalendarDataJ> snapNewsDataJS;

        public SnapNewsDataDT(TimeOD timeOD_A) {
            timeOD = timeOD_A;
            snapNewsDataJS = new ArrayList<>();
        }

        public void addNewsData(CalendarDataClass[] calendarDataClasses_A, TimeOD timeOD_A) {
            ArrayList<SnapCalendarDataJ> snapNewsDataJS_A = new ArrayList<>();
            for (int i = 0; i < calendarDataClasses_A.length; i++) {
                snapNewsDataJS_A.add(new SnapCalendarDataJ(calendarDataClasses_A[i]));

            }
            snapNewsDataJS.addAll(snapNewsDataJS_A);
            sentNewNewsData(snapNewsDataJS_A.toArray(new SnapCalendarDataJ[snapNewsDataJS_A.size()]), timeOD_A);
        }

        public void sentNewNewsData(SnapCalendarDataJ[] snapNewsDataJS, TimeOD timeOD_A) {
            sentNewSnapNewsData(snapNewsDataJS, timeOD_A);
        }
    }

    public class SnapCalendarDataJ {
        CalendarDataClass calendarDataClass;
        public boolean boolNew;
        public boolean boolSeen;

        public SnapCalendarDataJ(CalendarDataClass calendarDataClass_A) {
            calendarDataClass = calendarDataClass_A;
            boolNew = true;
            boolSeen = false;
        }
    }
}