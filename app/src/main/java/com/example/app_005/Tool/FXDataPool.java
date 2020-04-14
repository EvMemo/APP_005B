package com.example.app_005.Tool;

import android.util.Log;

import com.example.app_005.Class.CalendarDataClass;
import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Class.SkillLineNema;
import com.example.app_005.Class.SkillNewsName;
import com.example.app_005.Class.SkillQuoName;
import com.example.app_005.Internet.Jin10CalendarCollectTool;
import com.example.app_005.Internet.XMCalendarCollertTool;
import com.example.app_005.Tool.WindowView.SnapCalendarDataTool;
import com.example.app_005.Tool.WindowView.SnapNewsDataTool;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.util.ArrayList;

/**
 * 外汇数据缓存池
 */
public class FXDataPool {
   // public FXQuo.FXQuoPool quoPool;
    public FXQuo fxQuo;
    public FXNews fxNews;
    public FXCalendar fxCalendar;
    public MyFileIOTool myFileIOTool;
    FXDataConnect.TimeConnect timeConnect;
    public FXDataPool(MyFileIOTool myFileIOToolAA,FXDataConnect.TimeConnect timeConnectAA,FXDataConnect fxDataConnect_A){
        timeConnect=timeConnectAA;
        fxQuo=new FXQuo(myFileIOToolAA);
        fxNews=new FXNews(myFileIOToolAA);
        fxCalendar=new FXCalendar(myFileIOToolAA);

    }

    public  class FXQuo {
        public FXQuoPool quoPool;//缓存数据
        public FXQuoFileTool fxQuoFileTool;
        public FXQuo.FXQuoCacha fxQuoCacha;
        public FXQuoRecord fxQuoRecord;

        public FXQuo(MyFileIOTool myFileIOToolAA) {
            myFileIOTool=myFileIOToolAA;
            fxQuoFileTool = new FXQuoFileTool(myFileIOToolAA);//初始化Quo工具
            fxQuoRecord = new FXQuoRecord();
            quoPool = new FXQuoPool();
            fxQuoCacha = new FXQuoCacha(this, 3, 5);
            if(false)
                {
                    TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
                    QuoDData[] quoDData_B=new QuoDData[10];
                    for(int i=0;i<quoDData_B.length;i++)
                    {
                        quoDData_B[i]=new QuoDData(SkillQuoName.getSkill(i),timeOD_A,0);

                        for(int h=0;h<24;h++)
                            {
                            for(int m=0;m<60;m++)
                                {
                                    quoDData_B[i].quoDayData.PuC_setQuoData(new float[]{100.001F,100.001F,100.001F,100.001F,100.001F},h,m);
                                }
                            }
                    }
                    Gson gson_A = new Gson();
                    File file_A;
                    String string_B;
                    long long_A=System.currentTimeMillis();
                    for(int i=0;i<quoDData_B.length;i++)
                        {
                            fxQuoFileTool.PuC_saveQuoDayDataToFile(quoDData_B[i].quoDayData);

                        }
                    long long_B=System.currentTimeMillis();
                }

        }
        /**
         *初始化MainData
         */
        void initMainData(TimeOD timeODAA) {
//            if (quoPool.mainDataA != null) {
//                quoPool.mainDataA = new QuoMainData(timeODAA);
//                String string_A;
//                File file_A;
//                String string_B;
//                Gson gson_A = new Gson();
//                for (int i = 0; i < quoPool.mainDataA.quoDayData.length; i++) {
//                    string_A = myFileIOTool.getJSONQuoFileName(quoPool.mainDataA.quoDayData[i].skillName, quoPool.mainDataA.quoDayData[i].timeOD);
//                    file_A = myFileIOTool.PuC_isJSONFile(string_A);
//                    if (file_A != null) {
//                        string_B = myFileIOTool.getJSONFileData(file_A);
//                        quoPool.mainDataA.quoDayData[i] = gson_A.fromJson(string_B, FXDataPool.FXQuo.QuoDayData.class);
//                        quoPool.mainDataA.quoDayData[i].intState = 1;
//                    } else {
//                        quoPool.mainDataA.arQuoDataData();
//                    }
//
//                }
//                quoPool.mainDataA.intLoad = 3;
//            }
//            MyFXDataService.TimeOD timeOD = timeODAA.PuC_getCrTimeOD(-1);
//            if (quoPool.mainDataB != null) {
//                quoPool.mainDataB = new QuoMainData(timeOD);
//                String string_A;
//                File file_A;
//                String string_B;
//                Gson gson_A = new Gson();
//                for (int i = 0; i < quoPool.mainDataB.quoDayData.length; i++) {
//                    string_A = myFileIOTool.getJSONQuoFileName(quoPool.mainDataB.quoDayData[i].skillName, quoPool.mainDataB.quoDayData[i].timeOD);
//                    file_A = myFileIOTool.PuC_isJSONFile(string_A);
//                    if (file_A != null) {
//                        string_B = myFileIOTool.getJSONFileData(file_A);
//                        quoPool.mainDataB.quoDayData[i] = gson_A.fromJson(string_B, FXDataPool.FXQuo.QuoDayData.class);
//                        quoPool.mainDataB.quoDayData[i].intState = 1;
//                    } else {
//                        quoPool.mainDataB.arQuoDataData();
//                    }
//
//                }
//                quoPool.mainDataB.intLoad = 3;
//            }
        }
        /**
         *更新MainData最新日期
         */
        public void PuC_updateMainQuoData(TimeOD timeODAA){
            synchronized ("MainData"){
                TimeOD timeOD_A=timeODAA.PuC_getCrTimeOD(-1);
                if (!quoPool.mainDataB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                    if(quoPool.mainDataB.intLoad==3)
                        {
                            quoPool.mainDataB.intLoad=2;
                            quoPool.mainDataB.PuC_saveMainData();
                        }
                    if (quoPool.mainDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                        quoPool.mainDataB=quoPool.mainDataA;
                    }else {
                        quoPool.mainDataB=new QuoMainData(timeOD_A);
                       // quoPool.mainDataB.arQuoDataData();
                    }
                    quoPool.mainDataB=null;
                }
                if (!quoPool.mainDataA.timeOD.PuC_isTimeODSameDay(timeODAA)) {
                    quoPool.mainDataA=new QuoMainData(timeOD_A);
                    quoPool.mainDataA.initMainData();
                }else {
                    quoPool.mainDataA=new QuoMainData(timeOD_A);
                    //quoPool.mainDataA.arQuoDataData();
                }
            }

        }
        public QuoDData PuC_getQuoDData(SkillQuoName skillQuoNameAA,TimeOD timeODAA) {
            QuoDData quoDayData_A=null;
            synchronized ("MainData"){
                if (quoPool.mainDataA.timeOD.PuC_isTimeODSameDay(timeODAA)) {
                    //]Log.w(this.toString(), "PuC_getQuoDDataG0" +"="+ timeODAA.intsA[2]);
                    quoDayData_A= quoPool.mainDataA.PuC_getQuoDayData(skillQuoNameAA);
                } else if (quoPool.mainDataB.timeOD.PuC_isTimeODSameDay(timeODAA)) {
                    //]Log.w(this.toString(), "PuC_getQuoDDataG1"  +"="+ timeODAA.intsA[2]);
                    quoDayData_A= quoPool.mainDataB.PuC_getQuoDayData(skillQuoNameAA);
                }
            }
            if(quoDayData_A!=null)return quoDayData_A;
            synchronized ("CachaData"){
                //]Log.w(this.toString(), "PuC_getQuoDDataG2"  +"="+ timeODAA.intsA[2]);
                quoDayData_A= fxQuoCacha.PuC_getQuoDayData(skillQuoNameAA,timeODAA);
            }
            if(quoDayData_A!=null)return quoDayData_A;
            synchronized ("FileData"){
                //]Log.w(this.toString(), "PuC_getQuoDDataG3"  +"="+ timeODAA.intsA[2]);
                quoDayData_A=new QuoDData(skillQuoNameAA,timeODAA,3);
                QuoDayData quoDayData_B=fxQuoFileTool.PuC_getQuoFileData(skillQuoNameAA,timeODAA);
                if(quoDayData_B!=null)
                    {
                        quoDayData_A.setQuoData(quoDayData_B);
                    }

                fxQuoCacha.PuC_addQuoChaData(quoDayData_A);
           }
//           if(quoDayData_A==null)
//               {
//                   //]Log.w(this.toString(), "PuC_getQuoDDataG4" );
//                   quoDayData_A=new QuoDData(skillQuoNameAA,timeODAA,0);
//               }
           return quoDayData_A;
        }
        public QuoDayLine PuC_getQuoDLine(SkillQuoName skillQuoNameAA,SkillQuoName skillQuoNameBB,TimeOD timeODAA){
            QuoDayLine quoDayLine_A=null;
//            synchronized ("MainData"){
//                if (quoPool.mainDataA.timeOD.PuC_isTimeODSameDay(timeODAA)) {
//                    quoDayLine_A= quoPool.mainDataA.PuC_getQuoDayLine(skillQuoNameAA);
//                } else if (quoPool.mainDataB.timeOD.PuC_isTimeODSameDay(timeODAA)) {
//                    quoDayLine_A= quoPool.mainDataB.PuC_getQuoDayLine(skillQuoNameAA);
//                }
//            }
//            if(quoDayLine_A!=null)return quoDayLine_A;
//            synchronized ("CachaData"){
//                quoDayLine_A= fxQuoCacha.PuC_getQuoDayLine(skillQuoNameAA,timeODAA);
//            }
//            if(quoDayLine_A!=null)return quoDayLine_A;
            synchronized ("FileData"){
                quoDayLine_A=fxQuoFileTool.PuC_getQuoLineFileData(skillQuoNameAA,skillQuoNameBB,timeODAA);
            }
            return quoDayLine_A;
        }
        public void addQuoData(FXCollectTool.FXQuoCollect.QuoCollectData[] quoDDatasAA, TimeOD timeODAA) {
            synchronized ("MainData") {
                if (quoPool.mainDataA.timeOD.PuC_isTimeODSameDay(timeODAA)) {
                    //]Log.w(this.toString(), "addQuoData01"+"="+ "MainDataA");
                    quoPool.mainDataA.PuC_addCollectData(quoDDatasAA, timeODAA);
                    //quoPool.mainDataA.PuC_saveMainData();
                    return;
                } else if (quoPool.mainDataB.timeOD.PuC_isTimeODSameDay(timeODAA)) {
                    //]Log.w(this.toString(), "addQuoData01"+"="+ "MainDataB");
                    quoPool.mainDataB.PuC_addCollectData(quoDDatasAA, timeODAA);
                    //quoPool.mainDataB.PuC_saveMainData();
                    return;
                }

            }
            Boolean[] booleans = new Boolean[quoDDatasAA.length];
            Boolean bool_A = false;
            synchronized ("CachaData") {
                for (int i = 0; i < quoDDatasAA.length; i++) {
                    booleans[i] = fxQuoCacha.PuC_addCollectQuoData(quoDDatasAA[i], SkillQuoName.getSkill(i), timeODAA);
                    if (!booleans[i]) bool_A = true;

                }
                if(bool_A)
                    {
                        //]Log.w(this.toString(), "addQuoData01"+"="+ "CachaData");
                    }
            }
            if (bool_A) {
                synchronized ("FileData") {
                    for (int i = 0; i < booleans.length; i++) {
                        if (!booleans[i]) {
                            fxQuoFileTool.PuC_saveOneQuoDataToFile(quoDDatasAA[i], SkillQuoName.getSkill(i), timeODAA);
                        }
                    }
                    if(bool_A)
                    {
                        //]Log.w(this.toString(), "addQuoData01"+"="+ "FileData");
                    }

                }
            }


        }
        public void addQuoLine(FXCollectTool.FXQuoCollect.QuoLineCollectData[] quoDDatasAA, TimeOD timeODAA){
            synchronized ("MainData") {
                if (quoPool.mainDataA.timeOD.PuC_isTimeODSameDay(timeODAA)) {
                    //]Log.w(this.toString(), "addQuoData01"+"="+ "MainDataA");
                    quoPool.mainDataA.PuC_addCollectLine(quoDDatasAA, timeODAA);
                    //quoPool.mainDataA.PuC_saveMainData();
                    return;
                } else if (quoPool.mainDataB.timeOD.PuC_isTimeODSameDay(timeODAA)) {
                    //]Log.w(this.toString(), "addQuoData01"+"="+ "MainDataB");
                    quoPool.mainDataB.PuC_addCollectLine(quoDDatasAA, timeODAA);
                    //quoPool.mainDataB.PuC_saveMainData();
                    return;
                }

            }
//            Boolean[] booleans = new Boolean[quoDDatasAA.length];
//            Boolean bool_A = false;
//            synchronized ("CachaData") {
//                for (int i = 0; i < quoDDatasAA.length; i++) {
//                    booleans[i] = fxQuoCacha.PuC_addCollectQuoData(quoDDatasAA[i], SkillQuoName.getSkill(i), timeODAA);
//                    if (!booleans[i]) bool_A = true;
//
//                }
//                if(bool_A)
//                {
//                    //]Log.w(this.toString(), "addQuoData01"+"="+ "CachaData");
//                }
//            }
            if (true) {
                synchronized ("FileData") {
                    for (int i = 0; i < quoDDatasAA.length; i++) {
                        fxQuoFileTool.PuC_saveOneQuoLineToFile(quoDDatasAA[i], quoDDatasAA[i].skillQuoName, timeODAA);
                    }

                }
            }
        }
        public void addQuoHistoryData(FXAlphaVantageTool.QuoHistoryData quoHistoryData_Data) {
            synchronized ("MainData") {
                //]Log.w(this.toString(), "addQuoHistoryData: "+"="+"OK" );
                quoPool.mainDataA.addCollectHistoryData(quoHistoryData_Data);
                quoPool.mainDataB.addCollectHistoryDataB(quoHistoryData_Data);

            }



        }

        public void sevaQuoData() {
            synchronized ("MainData") {
                if (quoPool.mainDataA != null) {
                    if (quoPool.mainDataA.intLoad == 3) {
                        quoPool.mainDataA.PuC_saveMainData();
                        // quoPool.mainDataA.quoDDataAr[i].sevaQuoDData(fxQuoFileTool);
                        quoPool.mainDataA.intLoad = 2;
                    }
                }
                if (quoPool.mainDataB != null) {
                    if (quoPool.mainDataB.intLoad == 3) {
                        quoPool.mainDataB.PuC_saveMainData();
                        // quoPool.mainDataA.quoDDataAr[i].sevaQuoDData(fxQuoFileTool);
                        quoPool.mainDataB.intLoad = 2;
                    }
                }
            }
        }

        /**
         * 缓存数据
         */
        public class FXQuoPool {
            /**
             * 最新缓存数据
             */
            public QuoMainData mainDataA;
            /**
             * 前天缓存数据
             */
            public QuoMainData mainDataB;

            public FXQuoPool() {

                //Log.d("FXQuoPool", "FXQuoPool() called"+MyFXTimingService.ST_titleTime.getTimeNew());
                mainDataA=new QuoMainData(timeConnect.getTimeNew());
                mainDataB=new QuoMainData(timeConnect.getTimeNew().PuC_getCrTimeOD(-1));
                mainDataA.initMainData();
                mainDataB.initMainData();
            }

        }

        public class FXQuoFileTool {

            MyFileIOTool fileIOTool;
            Gson gson;
            String syStr_fileQuo = "syStr_fileQuo";
            String syStr_fileLine = "syStr_fileLine";
            String syStr_fileRec = "syStr_fileRec";

            public FXQuoFileTool(MyFileIOTool myFileIOToolAA) {
                fileIOTool=myFileIOToolAA;
                gson = new Gson();
                String string_A=myFileIOToolAA.contextA.getExternalFilesDir("Quote").getPath();
                File file_A = fileIOTool.PuC_isJSONFile(string_A);
                if(file_A==null)
                    {
                        file_A.mkdirs();
                    }
            }

            /**
             *
             */
            public QuoDayData PuC_getQuoFileData(SkillQuoName skillQuoNameAA, TimeOD timeODAA) {
                synchronized (syStr_fileQuo) {
                    String fileName = fileIOTool.getJSONQuoFileName(skillQuoNameAA, timeODAA);
                    File file_A = fileIOTool.PuC_isJSONFile(fileName);
                    if (file_A != null) {
                        String string_B = fileIOTool.getJSONFileData(file_A);
                        QuoDayData quoDData_A = gson.fromJson(string_B, FXDataPool.FXQuo.QuoDayData.class);
                        return quoDData_A;
                    } else {
                        return null;
                    }
                }

            }

            /**
             *
             */
            public QuoDayLine PuC_getQuoLineFileData(SkillQuoName skillQuoNameAA,SkillQuoName skillQuoNameBB, TimeOD timeODAA) {
                synchronized (syStr_fileLine) {
                    String fileName = fileIOTool.getJSONQuoLineFileName(skillQuoNameAA,skillQuoNameBB, timeODAA);
                    File file_A = fileIOTool.PuC_isJSONFile(fileName);
                    if (file_A != null) {
                        String string_B = fileIOTool.getJSONFileData(file_A);
                        QuoDayLine quoDData_A = gson.fromJson(string_B, FXDataPool.FXQuo.QuoDayLine.class);
                        return quoDData_A;
                    } else {
                        return null;
                    }
                }

            }

            /**
             *
             */
            public void PuC_saveQuoDayDataToFile(FXQuo.QuoDayData quoDayDataAA) {
                synchronized (syStr_fileQuo) {
                    String fileName = fileIOTool.getJSONQuoFileName(quoDayDataAA.skillName, quoDayDataAA.timeOD);
                    File file_A =new File(fileName);// fileIOTool.PuC_isJSONFile(fileName);
                    String string_C = gson.toJson(quoDayDataAA);
                    if (fileIOTool.PuC_sevaJSONToFile(string_C, file_A)) {
                        //]Log.w(this.toString(), "PuC_savQuoDayDataToFileOK: "+"="+quoDayDataAA.skillName.name());
                    } else {
                        Log.e(this.toString(), "PuC_savQuoDayDataToFileE: ");
                    }

                }
            }

            public void PuC_saveQuoDayLineToFile(FXQuo.QuoDayLine quoDayLineAA) {
                synchronized (syStr_fileLine) {
                    String fileName = fileIOTool.getJSONQuoFileName(quoDayLineAA.skillName, quoDayLineAA.timeOD);
                    File file_A = fileIOTool.PuC_isJSONFile(fileName);
                    String string_C = gson.toJson(quoDayLineAA);
                    if (fileIOTool.PuC_sevaJSONToFile(string_C, file_A)) {

                    } else {
                        Log.e(this.toString(), "PuC_savQuoDayDataToFile: ");
                    }

                }
            }

            /**
             *
             */
            public void PuC_saveOneQuoDataToFile(FXCollectTool.FXQuoCollect.QuoCollectData quoDDataAA, SkillQuoName skillQuoName, TimeOD timeODAA) {
                synchronized (syStr_fileQuo) {
                    QuoDayData quoDayData_A = PuC_getQuoFileData(skillQuoName, timeODAA);
                    quoDayData_A.PuC_setQuoData(quoDDataAA.floats, timeODAA.intsA[3], timeODAA.intsA[4]);
                    PuC_saveQuoDayDataToFile(quoDayData_A);

                }
            }
            public void PuC_saveOneQuoLineToFile(FXCollectTool.FXQuoCollect.QuoLineCollectData quoDDataAA, SkillQuoName skillQuoName, TimeOD timeODAA) {
                synchronized (syStr_fileQuo) {
                    QuoDayData quoDayData_A = PuC_getQuoFileData(skillQuoName, timeODAA);
                    quoDayData_A.setQuoLine(quoDDataAA.floatsLineData, timeODAA.intsA[3], timeODAA.intsA[4]);
                    PuC_saveQuoDayDataToFile(quoDayData_A);

                }
            }

            public FXQuoRecord.RecordDData PuC_getQuoRecData(TimeOD timeODAA) {
                synchronized (syStr_fileRec) {
                    String fileName = fileIOTool.PuC_getJSONQuoRecFileName(timeODAA);
                    File file_A = fileIOTool.PuC_isJSONFile(fileName);
                    if (file_A != null) {
                        String string_B = fileIOTool.getJSONFileData(file_A);
                        FXQuoRecord.RecordDData quoDData_A = gson.fromJson(string_B, FXQuoRecord.RecordDData.class);
                        return quoDData_A;
                    } else {
                        return null;
                    }
                }
            }

            public void PuC_saveQuoRecData(FXQuoRecord.RecordDData recordDDataAA) {
                synchronized (syStr_fileRec) {
                    String fileName = fileIOTool.PuC_getJSONQuoRecFileName(recordDDataAA.timeOD);
                    File file_A = fileIOTool.PuC_isJSONFile(fileName);
                    String string_C = gson.toJson(recordDDataAA);
                    if (fileIOTool.PuC_sevaJSONToFile(string_C, file_A)) {

                    } else {
                        Log.e(this.toString(), "PuC_savQuoDayDataToFile: ");
                    }
                }

            }
        }

        public class FXQuoRecord {
            public ArrayList<RecordDData> recordDDatas = new ArrayList<>();
            public FXQuoRecord(){

            }

            public RecordDData getRecDData(TimeOD timeODAA) {
                synchronized (this) {
                    for (int i = 0; i < recordDDatas.size(); i++) {
                        if (recordDDatas.get(i).timeOD.PuC_isTimeODSameDay(timeODAA)) {
                            recordDDatas.get(i).intCount++;
                            return recordDDatas.get(i);
                        }
                    }

                    return addRecDData(timeODAA);
                }
            }

            public RecordDData addRecDData(TimeOD timeODAA) {
                synchronized (this) {
                    RecordDData recordDData_A = fxQuoFileTool.PuC_getQuoRecData(timeODAA);
                    if (recordDData_A != null) {
                        recordDData_A.intCount = 1;
                        recordDData_A.intState = 2;
                    } else {
                        recordDData_A = new RecordDData(timeODAA);
                    }
                    recordDDatas.add(recordDData_A);
                    return recordDData_A;
                }
            }

            public void removeRecData(RecordDData recordDDataAA) {
                synchronized (this) {
                    for (int i = 0; i < recordDDatas.size(); i++) {
                        if (recordDDatas.get(i).equals(recordDDataAA)) {
                            recordDDatas.get(i).intCount--;
                            if (recordDDatas.get(i).intCount <= 0) {
                                if (recordDDatas.get(i).intState == 3) {
                                    saveRecData(recordDDatas.get(i));
                                }
                                recordDDatas.remove(i);
                            }

                        }
                    }
                }


            }

            public void saveRecData(RecordDData recordDDataAA) {
                recordDDataAA.intState = 2;
                fxQuoFileTool.PuC_saveQuoRecData(recordDDataAA);
            }

            public class RecordDData {
                public TimeOD timeOD;
                public int intState;//数据状态[0=初始化,1=没有数据,2=有数据,3=需要存储]
                public int intCount;//引用次数
                public ArrayList<RecordData> recordDatas;

                public RecordDData(TimeOD timeODAA) {
                    timeOD = timeODAA;
                    intState = 0;
                    intCount = 1;
                    recordDatas = new ArrayList<>();
                }

                public void addRecData(RecordT recordDataAA, String id) {
                    recordDatas.add(new RecordData(recordDataAA));
                }

            }

            public class RecordData<T extends RecordT> {
                public T data;

                public RecordData(T dataAA) {
                    data = dataAA;
                }

            }

            public class RecQuoTask extends RecordT {
                public RecQuoTask() {
                    intMode = 1;
                }
            }

            public class RecordT {
                public int intMode;
                public int id;
            }


        }

        /**
         * 外汇临时数据
         */
        public class FXQuoCacha {
            public FXQuo fxQuo;
            //public QuoChaPool quoChaPool;
            public int intDayMainMax;
            public int intDayLineMax;
            public QuoDData[] quoDData;

            public FXQuoCacha(FXQuo fxQuoAA, int intMainMaxAA, int intLineMaxAA) {
                this.fxQuo = fxQuoAA;
                intDayMainMax = intMainMaxAA;
                intDayLineMax = intLineMaxAA;
                quoDData = new QuoDData[intDayMainMax];

            }

            public boolean PuC_addCollectQuoData(FXCollectTool.FXQuoCollect.QuoCollectData quoDDatasAA,SkillQuoName skillQuoNameAA, TimeOD timeODAA) {

                for (int e = 0; e < quoDData.length; e++) {
                    if (quoDData[e] != null) {
                        if (quoDData[e].timeOD.PuC_isTimeODSameDay(timeODAA) && quoDData[e].skillName == skillQuoNameAA) {
                            quoDData[e].quoDayData.PuC_setQuoData(quoDDatasAA.floats, timeODAA.intsA[3], timeODAA.intsA[4]);
                            return true;
                        }else {
                            return false;
                        }
                    }
                }
                return false;

            }

            /**
             *
             */
            public void PuC_crQuoData(int intNumdorAA, SkillQuoName skillQuoNameAA, TimeOD timeODAA) {
                if (true) {
                    // quoChaPool=fxQuo.new QuoChaPool()
                }
//

            }

            public void PuC_crQuoData(int intTimeAA, int intMainAA, int intLineAA) {
                // intNumdor=intTimeAA;
                //quoChaPool=fxQuo.new QuoChaPool();
            }

            /**
             *
             */
            public QuoDData PuC_getQuoDayData(SkillQuoName skillQuoNameAA,TimeOD timeODAA) {
                for (int i = 0; i < quoDData.length; i++) {
                    if (quoDData[i] != null) {
                        if (skillQuoNameAA == quoDData[i].skillName &&timeODAA.PuC_isTimeODSameDay(quoDData[i].timeOD)) {

                            return quoDData[i];
                        }
                    }
                }
                return null;
            }
            public QuoDayLine PuC_getQuoDayLine(SkillQuoName skillQuoNameAA,TimeOD timeODAA) {
                for (int i = 0; i < quoDData.length; i++) {
                    if (quoDData[i] != null) {
                        if (skillQuoNameAA == quoDData[i].skillName &&timeODAA.PuC_isTimeODSameDay(quoDData[i].timeOD)) {

                            for(int e=0;e<quoDData[i].quoDayLines.length;e++)
                                {
                                    if (quoDData[i].quoDayLines[e] != null) {
                                        if (quoDData[i].quoDayLines[e].skillName == skillQuoNameAA) {
                                          return quoDData[i].quoDayLines[e];
                                        }
                                    }
                                }
                        }
                    }
                }
                return null;
            }
            /**
             * 获取报价数据
             */
            public void PuC_cacQuoDayData(QuoDData quoDDataAA) {
                synchronized (this) {
                    for (int e = 0; e < quoDData.length; e++) {
                        if (quoDData[e] != null) {
                            if (quoDDataAA.skillName == quoDData[e].skillName && quoDDataAA.timeOD.PuC_isTimeODSameDay(quoDData[e].timeOD)) {

                                quoDDataAA.quoDayData = quoDData[e].quoDayData;
                                for (int r = 0; r < quoDDataAA.quoDayLines.length; r++) {
                                    for (int t = 0; t < quoDData[e].quoDayLines.length; t++) {
                                        if (quoDData[e].quoDayLines[t] != null) {
                                            if (quoDData[e].quoDayLines[t].skillName == quoDDataAA.quoDayLines[r].skillName) {
                                                quoDDataAA.quoDayLines[r] = quoDData[e].quoDayLines[t];
                                            }
                                        }

                                    }
                                }
                            }


                        }


                    }
                }
            }

            /**
             * 赋值/fù zhí/报价数据
             */
            public void PuC_addQuoChaData(QuoDData quoDDataAA) {
                synchronized (this) {
                    QuoDData quoDData_B = quoDDataAA;
                    QuoDData quoDData_B2;
                    for (int i = 0; i < quoDData.length; i++) {
                        if (quoDData[i] != null) {
                            if (quoDDataAA.timeOD.PuC_isTimeODSameDay(quoDData[i].timeOD) && quoDDataAA.skillName == quoDData[i].skillName) {
                                quoDData[i] = null;
                            }
                        }
                        if (quoDData_B != null) {
                            if (quoDData[i] != null) {
                                quoDData_B2 = quoDData[i];
                                quoDData[i] = quoDData_B;
                                quoDData_B = quoDData_B2;
                            } else {
                                quoDData[i] = quoDData_B;
                                quoDData_B = null;
                            }
                        } else {
                            break;
                        }
                    }

                }
            }

            public void PuC_addQuoChaLine(QuoDayLine quoDayLineAA, SkillQuoName skillQuoNameMainAA) {
                synchronized (this) {

                    for (int i = 0; i < quoDData.length; i++) {
                        if (quoDData[i] != null) {
                            if (quoDayLineAA.timeOD.PuC_isTimeODSameDay(quoDData[i].timeOD) && skillQuoNameMainAA == quoDData[i].skillName) {
                                QuoDayLine quoDData_B = quoDayLineAA;
                                QuoDayLine quoDData_B2;
                                for (int e = 0; e < quoDData[i].quoDayLines.length; e++) {
                                    if (quoDData_B != null) {
                                        if (quoDData[i].quoDayLines[e] != null) {
                                            quoDData_B2 = quoDData[i].quoDayLines[e];
                                            quoDData[i].quoDayLines[e] = quoDData_B;
                                            quoDData_B = quoDData_B2;
                                        } else {
                                            quoDData[i].quoDayLines[e] = quoDData_B;
                                            quoDData_B = null;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                                break;
                            }
                        }

                    }

                }
            }

//            /**
//             * 调查/diào chá/
//             */
//            public QuoDayData PuC_isQuoDataTime(SkillQuoName skillQuoName, MyFXDataService.TimeOD timeODAA) {
//                for (int i = 0; i < quoDDataA.length; i++) {
//                    if (quoDDataA[i] != null) {
//                        if (quoDDataA[i].skillName == skillQuoName) {
//                            if (quoDDataA[i].timeOD.PuC_isTimeODSameDay(timeODAA)) {
//                                return quoDDataA[i].quoDayData;
//                            }
//                        }
//                    }
//                }
//                return null;
//
//            }

        }

        public class QuoMainData {
            public TimeOD timeOD;
            public QuoDData[] quoDData;
            //public QuoDayData[] quoDayData;
            //public QuoDayLine[] quoDayLines;

            /**
             * 记录存储态度0=初始化,1=没有数据,2=有数据存储成功,3=需要存储}
             */
            public int intLoad = 0;//

            public QuoMainData(TimeOD timeODAA) {
                timeOD = timeODAA;
                intLoad = 0;

            }
            public void initMainData(){
                quoDData=new QuoDData[11];
                File file_A;
                String string_B;
                Gson gson_A = new Gson();
                for(int i=0;i<quoDData.length;i++)
                    {
                        quoDData[i]=new QuoDData(SkillQuoName.getSkill(i),timeOD,0);
                        //Log.d(this.toString(), "initMainData(OKL0.1) called"+SkillQuoName.getSkill(i));
                       // Log.d(this.toString(), "initMainData(OKL1) called"+quoDDataAr[i].skillName);
                        String string_A = myFileIOTool.getJSONQuoFileName(quoDData[i].skillName, timeOD);
                        file_A = myFileIOTool.PuC_isJSONFile(string_A);
                        ////]Log.w(this.toString(), "initMainDataI01: "+"="+ (file_A != null)+"="+quoDDataAr[i].skillName.name());
                        if (file_A != null) {
                            string_B = myFileIOTool.getJSONFileData(file_A);
                            try {
                                quoDData[i].quoDayData = gson_A.fromJson(string_B, FXDataPool.FXQuo.QuoDayData.class);
                            } catch (JsonSyntaxException e) {
                                Log.e(this.toString(), "JsonSyntaxException: "+"="+e.toString() );

                            }

                            if(quoDData[i].quoDayData==null)
                                {
                                    quoDData[i].quoDayData = new QuoDayData(SkillQuoName.getSkill(i), timeOD);
                                }
                            quoDData[i].intState=2;
                            quoDData[i].quoDayData.intState = 2;

                        } else {
                            quoDData[i].quoDayData = new QuoDayData(SkillQuoName.getSkill(i), timeOD);
                            quoDData[i].intState=1;
                            quoDData[i].quoDayData.intState = 1;
                        }
                        if(false)
                            {
                                SkillQuoName[] skillQuoNames_B=new SkillQuoName[0];
                                for(int e=0;e<skillQuoNames_B.length;e++)
                                {
                                    string_A = myFileIOTool.getJSONQuoFileName(skillQuoNames_B[e], timeOD);
                                    file_A = myFileIOTool.PuC_isJSONFile(string_A);
                                    if (file_A != null) {
                                        string_B = myFileIOTool.getJSONFileData(file_A);
                                        quoDData[i].quoDayLines[e] = gson_A.fromJson(string_B, FXDataPool.FXQuo.QuoDayLine.class);
                                        quoDData[i].quoDayLines[e].intState = 2;

                                    } else {
                                        quoDData[i].quoDayLines[e] = new QuoDayLine(SkillQuoName.getSkill(i), timeOD);
                                        quoDData[i].quoDayLines[e].intState = 1;
                                    }
                                }
                            }


                    }



            }

//            public void arQuoDataData() {
//                String string_A;
//                File file_A;
//                String string_B;
//                Gson gson_A = new Gson();
//                for(int i=0;i<quoDDataA.length;i++)
//                    {
//                        string_A = myFileIOTool.getJSONQuoFileName(SkillQuoName.getSkill(i), timeOD);
//                        file_A = myFileIOTool.PuC_isJSONFile(string_A);
//                        if (file_A != null) {
//                            string_B = myFileIOTool.getJSONFileData(file_A);
//                            quoDayData[i] = gson_A.fromJson(string_B, FXDataPool.FXQuo.QuoDayData.class);
//                            quoDayData[i].intState = 2;
//                        } else {
//                            quoDayData[i] = new QuoDayData(SkillQuoName.getSkill(i), timeOD);
//                            quoDayData[i].intState = 1;
//                        }
//                    }
//                if(false)
//                    {
//                        quoDayData = new QuoDayData[10];
//                        quoDayLines=new QuoDayLine[0];
//
//                        for (int i = 0; i < quoDayData.length; i++) {
//                            string_A = myFileIOTool.getJSONQuoFileName(SkillQuoName.getSkill(i), timeOD);
//                            file_A = myFileIOTool.PuC_isJSONFile(string_A);
//                            if (file_A != null) {
//                                string_B = myFileIOTool.getJSONFileData(file_A);
//                                quoDayData[i] = gson_A.fromJson(string_B, FXDataPool.FXQuo.QuoDayData.class);
//                                quoDayData[i].intState = 2;
//                            } else {
//                                quoDayData[i] = new QuoDayData(SkillQuoName.getSkill(i), timeOD);
//                                quoDayData[i].intState = 1;
//                            }
//
//                        }
//                        if(false) {
//                            for (int i = 0; i < quoDayLines.length; i++) {
//
//                                SkillQuoName skillQuoName_A = null;
//                                string_A = myFileIOTool.getJSONQuoFileName(skillQuoName_A, timeOD);
//                                file_A = myFileIOTool.PuC_isJSONFile(string_A);
//                                if (file_A != null) {
//                                    string_B = myFileIOTool.getJSONFileData(file_A);
//                                    quoDayLines[i] = gson_A.fromJson(string_B, FXDataPool.FXQuo.QuoDayLine.class);
//                                    quoDayLines[i].intState = 2;
//                                } else {
//                                    quoDayLines[i] = new QuoDayLine(skillQuoName_A, timeOD);
//                                    quoDayLines[i].intState = 1;
//                                }
//                            }
//
//
//                        }
//                    }
//
//            }

            public void PuC_addCollectData(FXCollectTool.FXQuoCollect.QuoCollectData[] quoDDatasAA, TimeOD timeODAA) {
                for (int i = 0; i < quoDData.length; i++) {
                    quoDData[i].quoDayData.PuC_setQuoData(quoDDatasAA[i].floats, timeODAA.intsA[3], timeODAA.intsA[4]);
                }
                intLoad=3;

            }
            /**
             *
             */
            public void PuC_addCollectLine(FXCollectTool.FXQuoCollect.QuoLineCollectData[] quoDDatasAA, TimeOD timeODAA) {
                for(int i=0;i<quoDDatasAA.length;i++)
                    {
                        quoDData[quoDDatasAA[i].skillQuoName.getIndex()].quoDayData.setQuoLine(quoDDatasAA[i].floatsLineData, timeODAA.intsA[3], timeODAA.intsA[4]);
                    }
                intLoad=3;
            }
            public void PuC_addCollectLine2(FXCollectTool.FXQuoCollect.QuoLineCollectData[] quoDDatasAA, TimeOD timeODAA) {
                for(int i=0;i<quoDDatasAA.length;i++)
                {
                    quoDData[quoDDatasAA[i].skillQuoName.getIndex()].quoDayData.setQuoLine(quoDDatasAA[i].floatsLineData, timeODAA.intsA[3], timeODAA.intsA[4]);
                }
                intLoad=3;
            }
            public void addCollectHistoryData(FXAlphaVantageTool.QuoHistoryData quoHistoryData_Data){
                for (int i = 0; i < quoHistoryData_Data.historyData.length; i++) {
                    if(quoHistoryData_Data.historyData[i].timeOD.PuC_isTimeODSameDay(timeOD))
                        {
                            int int_A=quoHistoryData_Data.historyData[i].timeOD.intsA[3];
                            int int_B=quoHistoryData_Data.historyData[i].timeOD.intsA[4];
                            int int_C=0;
                            //]Log.w(this.toString(), "addCollectHistoryDataG0: "+"="+int_A +"="+int_B +"="+quoHistoryData_Data.historyData[i].floatsData[0][0] +"="+quoHistoryData_Data.historyData[i].floatsData[0][1]);
                            for(int e=int_A;e>=0;e--)
                                {
                                    for(int r=int_B;r>=0;r--)
                                        {
                                            if(true)
                                                {
                                                    quoDData[i].quoDayData.PuC_setQuoHistoryData(quoHistoryData_Data.historyData[i].floatsData[int_C],e,r);
                                                }
                                            int_C++;
                                        }
                                    int_B=59;

                                }

                        }
                    //quoDDataAr[i].quoDayData.PuC_setQuoData(quoDDatasAA[i].floats, timeODAA.intsA[3], timeODAA.intsA[4]);
                }
                intLoad=3;
            }
            public void addCollectHistoryDataB(FXAlphaVantageTool.QuoHistoryData quoHistoryData_Data){
                for (int i = 0; i < quoHistoryData_Data.historyData.length; i++) {
                    if((timeOD).PuC_isTimeODSameDay((quoHistoryData_Data.historyData[i].timeOD.PuC_getCrTimeOD(-1))))
                    {
                        int int_A=quoHistoryData_Data.historyData[i].timeOD.intsA[3];
                        int int_B=quoHistoryData_Data.historyData[i].timeOD.intsA[4];
                        int int_C=(int_A*60)+int_B+1;
                        //]Log.w(this.toString(), "addCollectHistoryDataBG0: "+"="+int_A +"="+int_B +"="+int_C +"="+quoHistoryData_Data.historyData[i].floatsData[int_C][0] +"="+quoHistoryData_Data.historyData[i].floatsData[int_C][1]);
                        for(int e=23;e>=0;e--)
                        {
                            int_C++;
                            if(int_C>=1440)break;
                            for(int r=59;r>=0;r--)
                            {
                                if(true)
                                {
                                    quoDData[i].quoDayData.PuC_setQuoHistoryData(quoHistoryData_Data.historyData[i].floatsData[int_C],e,r);
                                    if(false)
                                        {
                                            //]Log.w(this.toString(), "addCollectHistoryDataBB0: "+"="+int_C +"="+e +"="+r +"="+quoHistoryData_Data.historyData[i].floatsData[int_C][0]+"="+quoHistoryData_Data.historyData[i].floatsData[int_C][1] );
                                        }
                                }
                            }

                        }

                    }else if(quoHistoryData_Data.historyData[i].timeOD.PuC_isTimeODSameDay(timeOD)) {
                        int int_A=quoHistoryData_Data.historyData[i].timeOD.intsA[3];
                        int int_B=quoHistoryData_Data.historyData[i].timeOD.intsA[4];
                        int int_C=0;
                        for(int e=0;e>=int_A;e--)
                        {
                            for(int r=int_B;r>=0;r--)
                            {
                                if(true)
                                {
                                    quoDData[i].quoDayData.PuC_setQuoHistoryData(quoHistoryData_Data.historyData[i].floatsData[int_C],e,r);
                                }
                                int_C++;
                            }
                            int_B=59;

                        }
                    }
                    //quoDDataAr[i].quoDayData.PuC_setQuoData(quoDDatasAA[i].floats, timeODAA.intsA[3], timeODAA.intsA[4]);
                }
                intLoad=3;
            }
            public QuoDData PuC_getQuoDayData(SkillQuoName skillQuoNameAA){
                for(int i=0;i<quoDData.length;i++)
                    {
                    if(quoDData[i].skillName==skillQuoNameAA)
                        {
                        return quoDData[i];
                        }
                    }
                return null;
            }
            public QuoDayLine PuC_getQuoDayLine(SkillQuoName skillQuoNameAA,SkillQuoName skillQuoNameBB){
                for(int i=0;i<quoDData.length;i++)
                {
                    if(quoDData[i].skillName==skillQuoNameAA)
                    {
                        for(int e=0;e<quoDData[i].quoDayLines.length;e++)
                            {
                            if(quoDData[i].quoDayLines[e].skillName==skillQuoNameBB)
                                {
                                    return quoDData[i].quoDayLines[e];
                                }
                            }

                    }
                }
                return null;
            }
           /**
            *
            */
           public void PuC_saveMainData(){
           for(int i=0;i<quoDData.length;i++)
               {
               fxQuoFileTool.PuC_saveQuoDayDataToFile(quoDData[i].quoDayData);
                   for(int e=0;e<quoDData[i].quoDayLines.length;e++)
                   {
                       fxQuoFileTool.PuC_saveQuoDayLineToFile(quoDData[i].quoDayLines[e]);
                   }
               }

           }


        }

        public class QuoDData {
            public TimeOD timeOD;
            public SkillQuoName skillName;//货币种类
            public int intState;//数据状态[-1=弃置数据,0=初始化,1=没有数据,2=有数据,3=需要存储]
            public QuoDayData quoDayData;
            public int intLineMax;
            public QuoDayLine[] quoDayLines;
            public QuoDData(SkillQuoName skillNameAA, TimeOD timeODAA, int intLineMaxAA) {
                timeOD = timeODAA;
                skillName = skillNameAA;
                intState=0;
                quoDayData = new QuoDayData(skillNameAA, timeODAA);
                intLineMax = intLineMaxAA;
                quoDayLines = new QuoDayLine[intLineMaxAA];
            }
            public void setQuoData(QuoDayData quoDataAA){
                quoDayData=quoDataAA;
                intState=2;
            }
            public void addQuoLine(QuoDayLine quoDayLineAA) {
                QuoDayLine quoDayLine_B = quoDayLineAA;
                QuoDayLine quoDayLine_B2 = null;
                for (int i = 0; i < quoDayLines.length; i++) {
                    if (quoDayLines[i] != null) {
                        if (quoDayLineAA.skillName == quoDayLines[i].skillName) {
                            quoDayLines[i] = null;
                        }
                    }
                    if (quoDayLine_B != null) {
                        if (quoDayLines[i] != null) {
                            quoDayLine_B2 = quoDayLines[i];
                            quoDayLines[i] = quoDayLine_B;
                            quoDayLine_B = quoDayLine_B2;
                        } else {
                            quoDayLines[i] = quoDayLine_B;
                            quoDayLine_B = null;
                        }
                    }

                }

            }
            public String[] getMaxMinData(){
               // 小,大,开
                //]Log.w(this.toString(), "getMaxMinDataV0: "+"="+quoDayData.flMin+"="+quoDayData.flMax+"="+quoDayData.flSta );
                return new String[]{String.valueOf(quoDayData.flMin),String.valueOf(quoDayData.flMax),String.valueOf(quoDayData.flSta)};
            }
            public float[] PuC_getMaxData() {
                if(quoDayData==null)
                {
                    return null;
                }
                if(quoDayData.flSta==-1)
                    {
                        return null;
                    }
                return new float[]{quoDayData.flSta,quoDayData.flMax,quoDayData.flMin};
            }
            public QuoData getQuoData(int int_H,int int_M){
//                if(quoDayData==null)
//                {
//                    //]Log.w(this.toString(), "PuC_getDataEA0: "+"="+ timeOD.intsA[3] +":"+timeOD.intsA[4]);
//                    //]Log.w(this.toString(), "PuC_getDataEA1: "+"="+ intState);
//                }
//                if(quoDayData.hourData==null)
//                {
//
//                    //]Log.w(this.toString(), "PuC_getDataEA0: "+"="+ timeOD.intsA[3] +":"+timeOD.intsA[4]);
//                    //]Log.w(this.toString(), "PuC_getDataEA1: "+"="+ intState);
//                    //]Log.w(this.toString(), "PuC_getDataE0: "+"="+ quoDayData.timeOD.intsA[3] +":"+quoDayData.timeOD.intsA[4]);
//                    //]Log.w(this.toString(), "PuC_getDataE1: "+"="+ quoDayData.skillName +":"+quoDayData.timeOD);
//
//                }


                if (quoDayData.hourData[int_H] == null) return null;
                if (quoDayData.hourData[int_H].dataMin[int_M] == null) return null;
                return quoDayData.hourData[int_H].dataMin[int_M];
            }

            /**
             *
             */
            public float[] PuC_getData(int intH, int intM) {
                if(quoDayData==null)
                    {
                        //]Log.w(this.toString(), "PuC_getDataEA0: "+"="+ timeOD.intsA[3] +":"+timeOD.intsA[4]);
                        //]Log.w(this.toString(), "PuC_getDataEA1: "+"="+ intState);
                    }
                if(quoDayData.hourData==null)
                    {

                        //]Log.w(this.toString(), "PuC_getDataEA0: "+"="+ timeOD.intsA[3] +":"+timeOD.intsA[4]);
                        //]Log.w(this.toString(), "PuC_getDataEA1: "+"="+ intState);
                        //]Log.w(this.toString(), "PuC_getDataE0: "+"="+ quoDayData.timeOD.intsA[3] +":"+quoDayData.timeOD.intsA[4]);
                        //]Log.w(this.toString(), "PuC_getDataE1: "+"="+ quoDayData.skillName +":"+quoDayData.timeOD);

                    }


                if (quoDayData.hourData[intH] == null) return null;
                if (quoDayData.hourData[intH].dataMin[intM] == null) return null;
                return quoDayData.hourData[intH].dataMin[intM].floatsA;
            }

            public float[] PuC_getData(int intH, int intM, int intNum) {
                intM -= intNum;
                if (intM < 0) {
                    intM = 59;
                    intH--;
                    if (intH <= 0) {
                        return null;
                    }
                }
                if (quoDayData.hourData[intH] == null) return null;
                if (quoDayData.hourData[intH].dataMin[intM] == null) return null;
                return quoDayData.hourData[intH].dataMin[intM].floatsA;
            }

            public float[][] PuC_getDatas(TimeOD timeODAA, int intAA) {
                float[][] floats_rt = new float[intAA][];
                synchronized (this) {
                    int int_H = timeODAA.intsA[3];
                    int int_M = timeODAA.intsA[4];
                    String string_log="";
                    for (int i = 0; i < intAA; i++) {
                        floats_rt[i] = PuC_getData(int_H, int_M);
                        //string_log+="="+int_H+":"+int_M +"["+floats_rt[i][0]+"]";
                        int_M--;
                        if (int_M < 0) {
                            int_M = 59;
                            int_H--;
                            if (int_H <= 0) {
                                break;
                            }
                        }
                    }
                    //]Log.w(this.toString(), "PuC_getDatas0: "+"="+ string_log);
                }
                return floats_rt;
            }
            public float[][] PuC_getDatas(TimeOD timeODAA, int int_Time,int intNum) {
                float[][] floats_rt = new float[intNum][];
                synchronized (this) {
                    int int_H = timeODAA.intsA[3];
                    int int_M = timeODAA.intsA[4];
                    int int_TT=(int_M%int_Time)+1;

                    //]Log.w(this.toString(), "PuC_getDatasF0: "+"="+int_TT +"="+int_M +"="+int_Time );
                    int int_TA=int_TT;
                    String string_log="";
                    float[] floats_D;
                    float[] floats_D2;
                    for (int i = 0; i < intNum; i++) {
                        floats_D=null;

                        for(int e=0;e<int_TA;e++)
                            {
                                floats_D2=PuC_getData(int_H, int_M);
                                if(floats_D2!=null)
                                    {
                                        if(floats_D!=null)
                                        {
                                            floats_D[0]=floats_D2[0];
                                            floats_D[1]=floats_D2[1];
                                            if(floats_D[2]>floats_D2[2])floats_D[2]=floats_D2[2];
                                            if(floats_D[3]<floats_D2[3])floats_D[3]=floats_D2[3];

                                        }else {
                                            floats_D=PuC_getData(int_H, int_M);
                                        }
                                    }
                                int_M--;
                                if (int_M < 0) {
                                    int_M = 59;
                                    int_H--;
                                    if (int_H <= 0) {
                                        break;
                                    }
                                }

                            }
                        int_TA=int_Time;
                        floats_rt[i] = floats_D;
                        //string_log+="="+int_H+":"+int_M +"["+floats_rt[i][0]+"]";

                    }
                    //]Log.w(this.toString(), "PuC_getDatas0: "+"="+ floats_rt[0]);
                }
                return floats_rt;
            }

            public float[][] PuC_getDatasT(int intAA,int int_Time) {
                float[][] floats_rt = new float[intAA][];
                synchronized (this) {
                    int int_H = 23;
                    int int_M = 59;
                    int int_TT=(int_M%int_Time)+1;
                    //]Log.w(this.toString(), "PuC_getDatasTF0: "+"="+int_TT +"="+int_M +"="+int_Time );
                    int int_TA=int_TT;
                    float[] floats_D;
                    float[] floats_D2;
                    for (int i = 0; i < intAA; i++) {
                        floats_D=null;
                        for(int e=0;e<int_TA;e++)
                        {
                            floats_D2=PuC_getData(int_H, int_M);
                            if(floats_D2!=null)
                            {
                                if(floats_D!=null)
                                {
                                    floats_D[0]=floats_D2[0];
                                    floats_D[1]=floats_D2[1];
                                    if(floats_D[2]>floats_D2[2])floats_D[2]=floats_D2[2];
                                    if(floats_D[3]<floats_D2[3])floats_D[3]=floats_D2[3];

                                }else {
                                    floats_D=PuC_getData(int_H, int_M);
                                }
                            }
                            int_M--;
                            if (int_M < 0) {
                                int_M = 59;
                                int_H--;
                                if (int_H <= 0) {
                                    break;
                                }
                            }

                        }
                        int_TA=int_Time;
                        floats_rt[i] = PuC_getData(int_H, int_M);

                    }
                }
                return floats_rt;
            }

            public float[][] PuC_getDatas(int intHAA, int intMAA, int intNumAA) {
                float[][] floats_rt = new float[intNumAA][];
                synchronized (this) {
                    int int_H = intHAA;
                    int int_M = intMAA;
                    for (int i = 0; i < intNumAA; i++) {
                        floats_rt[i] = PuC_getData(int_H, int_M);
                        int_M--;
                        if (int_M < 0) {
                            int_M = 59;
                            int_H--;
                            if (int_H <= 0) {
                                break;
                            }
                        }
                    }
                }
                return floats_rt;
            }
            public float[][] getLineData(int int_H,int int_M){
                if(quoDayData==null)
                {
                    //]Log.w(this.toString(), "PuC_getDataEA0: "+"="+ timeOD.intsA[3] +":"+timeOD.intsA[4]);
                    //]Log.w(this.toString(), "PuC_getDataEA1: "+"="+ intState);
                }
                if(quoDayData.hourData==null)
                {

                    //]Log.w(this.toString(), "PuC_getDataEA0: "+"="+ timeOD.intsA[3] +":"+timeOD.intsA[4]);
                    //]Log.w(this.toString(), "PuC_getDataEA1: "+"="+ intState);
                    //]Log.w(this.toString(), "PuC_getDataE0: "+"="+ quoDayData.timeOD.intsA[3] +":"+quoDayData.timeOD.intsA[4]);
                    //]Log.w(this.toString(), "PuC_getDataE1: "+"="+ quoDayData.skillName +":"+quoDayData.timeOD);

                }


                if (quoDayData.hourData[int_H] == null) return null;
                if (quoDayData.hourData[int_H].dataLine[int_M] == null) return null;
                //]Log.w(this.toString(), "getLineDataH0: " +"="+ quoDayData.hourData[int_H].dataLine[int_M].floatsLine.length);
                return quoDayData.hourData[int_H].dataLine[int_M].floatsLine;
            }
//            public float[][][] getLineData(int int_H,int int_M,int int_N){
//                return getLineData(int_H, int_M, int_N,1);
//
//            }
            public float[][][] getLineData(int int_H,int int_M,int int_N,int int_Mon){
                float[][][] floats_Data=new float[int_N][][];
                int int_A=int_H;
                int int_B=int_M;
                int int_C=(int_M%int_Mon)+1;
                //]Log.w(this.toString(), "FOGgetLineDataD2: "+"="+ int_N +"="+int_H +"="+int_M +"="+int_C);
                for(int i=0;i<int_N;i++)
                {
                    if(int_B<0)
                    {
                        int_B=59;
                        int_A--;
                        if(int_A<0)
                        {
                            //]Log.w(this.toString(), "FOGgetLineDataD1: "+"="+ int_N +"="+int_H +"="+int_M +"="+int_A +"="+int_B);
                            return null;
                        }
                    }
                    floats_Data[i]=getLineData(int_A,int_B);
                    if(floats_Data[i]!=null)
                        {
                            //]Log.w(this.toString(), "FOGgetLineDataD0: "+"="+floats_Data[i].length );
                        }


                    int_B-=int_C;
                    int_C=int_Mon;
                }
                //]Log.w(this.toString(), "getLineDataPI0: "+"="+int_N +"="+ floats_Data.length);
                return floats_Data;
            }
            public float[][][] getLineDataB(int int_N,int int_Mon){
                float[][][] floats_Data=new float[int_N][][];
                int int_A=23;
                int int_B=59;
                int int_C=(60%int_Mon);
                for(int i=0;i<int_N;i++)
                {
                    if(int_B<0)
                    {
                        int_B=59;
                        int_A--;
                        if(int_A<0)
                        {
                            return null;
                        }
                    }
                    floats_Data[i]=getLineData(int_A,int_B);


                    int_B-=int_C;
                    int_C=int_Mon;
                }
                return floats_Data;
            }
            public void sevaQuoDData(FXQuoFileTool fxQuoFileToolAA){
                fxQuoFileToolAA.PuC_saveQuoDayDataToFile(quoDayData);
            }


        }

        public class QuoDayData {
            public TimeOD timeOD;
            public SkillQuoName skillName;//货币种类
            public int intState;//数据状态[0=初始化,1=没有数据,2=有数据]
            public float flMax;
            public float flMin;
            public float flSta;//开
            public QuoHourData[] hourData = new QuoHourData[24];
            public FXQuoRecord.RecordDData recordDData;

            public QuoDayData(SkillQuoName skillNameAA, TimeOD timeODAA) {
                skillName = skillNameAA;
                timeOD = timeODAA;
                hourData = new QuoHourData[24];
                intState = 0;
                flSta = -1;
            }

            /**
             *
             */
            public void PuC_setQuoData(float[] floatsAA, int intH, int intM) {
                //["bid","ask","high","low","close","change","change_abs"]
                //[出价，买价，高,低,关闭，百分巴仙,百分数]
                //卖,买,大,小,开,%,%123
                if (hourData[intH] == null) hourData[intH] = new QuoHourData();
                if( hourData[intH].dataMin[intM]!=null)
                    {
                        hourData[intH].dataMin[intM].updateData(floatsAA);
                    }else {
                    hourData[intH].dataMin[intM] = new QuoData(floatsAA);
                }

                if (flSta != -1) {
                    if(flMin>floatsAA[3])flMin=floatsAA[3];
                    if(flMax<floatsAA[2])flMax=floatsAA[2];
                    flSta=floatsAA[5];

                } else {

                    flMin=floatsAA[3];
                    flMax=floatsAA[2];
                    flSta=floatsAA[5];

                }
               // //]Log.w(this.toString(), "PuC_setQuoDataV0: "+"="+flMin +"="+flMax +"="+ flSta);
                intState=2;
            }
            public void PuC_setQuoHistoryData(float[] floatsAA, int intH, int intM) {
                //卖,买,大,小,开,%,%123
                if (hourData[intH] == null) hourData[intH] = new QuoHourData();
                if( hourData[intH].dataMin[intM]!=null)
                {
                    return;
                }else {
                    hourData[intH].dataMin[intM] = new QuoData(floatsAA[2],floatsAA[4],floatsAA[3]);
                }


               // //]Log.w(this.toString(), "PuC_setQuoHistoryData: "+"="+flMin +"="+flMax +"="+ flSta);
                intState=2;
            }
            public void setQuoLine(float[][] floats_Data, int intH, int intM){
                if (hourData[intH] == null) hourData[intH] = new QuoHourData();
                if( hourData[intH].dataLine[intM]!=null)
                {
                    hourData[intH].dataLine[intM].updateData(floats_Data);
                }else {
                    hourData[intH].dataLine[intM] = new QuoLine(floats_Data);
                }

//                if (flSta != -1) {
//                    if(flMin>floatsAA[3])flMin=floatsAA[3];
//                    if(flMax<floatsAA[2])flMax=floatsAA[2];
//                    flSta=floatsAA[5];
//
//                } else {
//
//                    flMin=floatsAA[3];
//                    flMax=floatsAA[2];
//                    flSta=floatsAA[5];
//
//                }
                // //]Log.w(this.toString(), "PuC_setQuoDataV0: "+"="+flMin +"="+flMax +"="+ flSta);
                intState=2;
            }


            public void initRecData() {
                recordDData = fxQuoRecord.getRecDData(timeOD);

            }

            public void closeRecData() {
                fxQuoRecord.removeRecData(recordDData);
            }

        }

        public class QuoHourData {
            public QuoData[] dataMin = new QuoData[60];
            public QuoLine[] dataLine=new QuoLine[60];

            public QuoHourData() {


            }
        }

        public class QuoData {
            public float[] floatsA=new float[4];//卖,买,小,大

            public QuoData() {


            }
            //卖,买,小,大,开
            public QuoData(float[] floatsAA) {
                floatsA=new float[4];
                floatsA[0]=floatsAA[0];
                floatsA[1]=floatsAA[1];
                floatsA[2]=floatsAA[0];
                floatsA[3]=floatsAA[0];
//                floatsA[4]=floatsAA[0];
                //floatsA = floatsAA;
            }
            public QuoData(float floats_A,float floats_B,float floats_C) {
                floatsA=new float[4];
                floatsA[0]=floats_A;
                floatsA[1]=floats_A;
                floatsA[2]=floats_A;
                floatsA[3]=floats_A;
//                floatsA[4]=floatsAA[0];
                //floatsA = floatsAA;
            }
            public void updateData(float[] floatsAA){
                floatsA[0]=floatsAA[0];
                floatsA[1]=floatsAA[1];
                if(floatsA[2]>floatsAA[0])floatsA[2]=floatsAA[0];
                if(floatsA[3]<floatsAA[0])floatsA[3]=floatsAA[0];
            }
        }
        public class QuoLine {
            public float[][] floatsLine;

            public QuoLine(float[][] floatsAA) {
                floatsLine=floatsAA;
//                floatsA[4]=floatsAA[0];
                //floatsA = floatsAA;
            }
            public void updateData(float[][] floatsAA){
                floatsLine=floatsAA;

            }
        }
        public class QuoDayLine {
            public SkillQuoName skillName;
            public TimeOD timeOD;
            public int intState;//数据状态[0=初始化,1=没有数据,2=有数据]
            public float flMax;
            public float flMin;
            public float flSta;//开
            public QuoHourData[] hourData = new QuoHourData[24];

            public QuoDayLine(SkillQuoName skillNameAA, TimeOD timeODAA) {
                this.skillName = skillNameAA;
                this.timeOD = timeODAA;
                intState = 0;

            }
            public void PuC_setQuoData(float[] floatsAA, int intH, int intM) {
                //卖,买,小,大,开
                if (hourData[intH] == null) hourData[intH] = new QuoHourData();
                hourData[intH].dataMin[intM] = new QuoData(floatsAA);
                if (flSta != -1) {
                    if(flMin>floatsAA[2])flMin=floatsAA[2];
                    if(flMax<floatsAA[3])flMax=floatsAA[3];

                } else {
                    flMin=floatsAA[2];
                    flMax=floatsAA[3];
                    flSta=floatsAA[4];
                }
                intState=2;

            }
            public float[][] getLineData( int int_H, int int_M){
                if(hourData[int_H] == null)return null;
                if(hourData[int_H].dataLine[int_M] == null)return null;
                return hourData[int_H].dataLine[int_M].floatsLine;
            }
            public float[] LineData(SkillLineNema skillName){
                float[] floats_rt=null;
                switch(skillName){
                    case RSI:
                        float floatRSI=0;
                        float floatRS=0;
                        floats_rt=new float[]{floatRSI,floatRS};
                        break;
                    case Stoch:
                        float floatK=1;
                        float floatD=1;
                        floats_rt=new float[]{floatK,floatD};
                        break;
                    case Stoch_RSI:
                        float floatSTOCHRSI=0;
                        floats_rt=new float[]{floatSTOCHRSI};
                        break;
                    case MACD:
                        float floatMACD12=0;
                        float floatMACD26=0;
                        float floatMACD9=0;
                        floats_rt=new float[]{floatMACD12,floatMACD26,floatMACD9};
                        break;
                    case ADX:
                        float floatADX=0;
                        float floatDI_P=0;
                        float floatDI_N=0;
                        float floatTR=0;
                        float floatDX_P=0;
                        float floatDX_N=0;
                        floats_rt=new float[]{floatADX,floatDI_P,floatDI_N,floatTR,floatDX_P,floatDX_N};
                        break;
                    case W_R:
                        float floatWilliamsR=2;
                        floats_rt=new float[]{floatWilliamsR};
                        break;
                    case CCI20:
                        float floatCCI=3;
                        floats_rt=new float[]{floatCCI};
                        break;
                    case ATR:
                        float floatATR=2;
                        float floatATR_TR=2;
                        floats_rt=new float[]{floatATR,floatATR_TR};
                        break;
                    default:
                        break;
                }
                return  floats_rt;

            }

        }

        public class QuoLineDDay {
            public SkillQuoName skillName;
            public String name;
            public TimeOD timeOD;
            public QuoDData[] quoDayLineData;

            public QuoLineDDay(SkillQuoName skillMainNameAA, String nameAA, TimeOD timeODAA, QuoDData[] quoDDataAA) {
                skillName = skillMainNameAA;
                name = nameAA;
                timeOD = timeODAA;
                quoDayLineData = quoDDataAA;
            }
        }

        public class QuoChaPool {
            public FXQuo fxQuo;
            public int intDayMainMax;
            public int intDayLineMax;
            public QuoDData[] quoDData;

            // public QuoMainDData quoMainDDataMain;
            //public QuoMainDData[] quoMainDDataLine;
            // public QuoLineDData quoLineDData;
            public QuoChaPool(int intMainAA, int intLineAA) {
                intDayMainMax = intMainAA;
                intDayLineMax = intLineAA;
                quoDData = new QuoDData[intDayMainMax];

            }




        }

        public class FXData<T> {
            public int[] intsA;//号
            public TimeOD timeODA;
            public T dataT;

            public FXData() {

            }

            public FXData(int[] intsAA, T dataTAA) {
                intsA = intsAA;
                dataT = dataTAA;
            }

        }

        public int[] getFXDataNumber(String stringAA) {
            int[] ints_A = null;//[0=EURUSD,1=USDJPY,2=GBPUSD,3=EURGBP,4=USDCHF,5=EURJPY,6=EURCHF,7=USDCAD,8=AUDUSD,9=GBPJPY]
            switch (stringAA) {
                case "EURUSD":
                    ints_A = new int[]{0, 0};
                    break;
                case "USDJPY":
                    ints_A = new int[]{0, 1};
                    break;
                case "GBPUSD":
                    ints_A = new int[]{0, 2};
                    break;
                case "EURGBP":
                    ints_A = new int[]{0, 3};
                    break;
                case "USDCHF":
                    ints_A = new int[]{0, 4};
                    break;
                case "EURJPY":
                    ints_A = new int[]{0, 5};
                    break;
                case "EURCHF":
                    ints_A = new int[]{0, 6};
                    break;
                case "USDCAD":
                    ints_A = new int[]{0, 7};
                    break;
                case "AUDUSD":
                    ints_A = new int[]{0, 8};
                    break;
                case "GBPJPY":
                    ints_A = new int[]{0, 9};
                    break;
                default:
                    break;
            }
            return ints_A;
        }

        public FXData getFXDataClass(int[] intsAA) {
            FXData fxData = null;
            switch (intsAA[0]) {
                case 0:
                    fxData = new FXData<QuoDData>();
                    break;
                default:
                    break;
            }
            return fxData;
        }
    }
    public class FXNews{
        public FXNewsPool fxNewsPool;
        public FXNewsFileTool fxNewsFileTool;
        public SnapNewsDataTool snapNewsDataTool;
        public FXNews(MyFileIOTool myFileIOToolAA){
            fxNewsFileTool=new FXNewsFileTool(myFileIOToolAA);
            snapNewsDataTool=new SnapNewsDataTool();
            fxNewsPool=new FXNewsPool();
//            ArrayList<NewsDataClass> newsDataClasses_A=new ArrayList<>();
//            newsDataClasses_A.add(new NewsDataClass("A001","B001",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A002","B002",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A003","B003",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A001","B001",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A002","B002",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A003","B003",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A001","B001",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A002","B002",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A003","B003",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A001","B001",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A002","B002",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A003","B003",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A001","B001",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A002","B002",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A003","B003",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A001","B001",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A002","B002",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//            newsDataClasses_A.add(new NewsDataClass("A003","B003",false,SkillNewsName.FortradeAnalysis,0,new TimeOD(System.currentTimeMillis())));
//
//            addNewsDataClass(newsDataClasses_A,new TimeOD(System.currentTimeMillis()));

        }
        public NewsDData getNewsData(TimeOD timeOD_A){
            NewsDData newsDataClass=null;
            synchronized ("NewsMainData"){
                if (fxNewsPool.newsMainDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                    newsDataClass= fxNewsPool.newsMainDataA.newsDData;
                } else if (fxNewsPool.newsMainDataB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                    newsDataClass= fxNewsPool.newsMainDataB.newsDData;
                }
            }
            if(newsDataClass!=null)return newsDataClass;
            synchronized ("FileData"){
                newsDataClass=fxNewsFileTool.PuC_getNewsDDataFileData(timeOD_A);
//                quoDayData_A=new QuoDData(skillQuoNameAA,timeODAA,3);
//                QuoDayData quoDayData_B=fxQuoFileTool.PuC_getQuoFileData(skillQuoNameAA,timeODAA);
                if(newsDataClass!=null)
                {
                    newsDataClass=new NewsDData(timeOD_A);
                }

            }
            return newsDataClass;

        }
        public void addNewsDataClass(ArrayList<NewsDataClass> newsDataClasses,TimeOD timeOD_A){
            //]Log.w(this.toString(), "addNewsDataClassF0: "+"="+timeOD_A.intsA[2]);
            //]Log.w(this.toString(), "addNewsDataClassF1: "+"="+fxNewsPool.newsMainDataA.timeOD.intsA[2]);
            //]Log.w(this.toString(), "addNewsDataClassF2: "+"="+fxNewsPool.newsMainDataB.timeOD.intsA[2]);
            synchronized ("NewsMainData"){
                if (fxNewsPool.newsMainDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                    fxNewsPool.newsMainDataA.newsDData.PuC_addData(newsDataClasses);
                   return;
                } else if (fxNewsPool.newsMainDataB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                    fxNewsPool.newsMainDataB.newsDData.PuC_addData(newsDataClasses);
                    return;
                }
            }
            synchronized ("FileData"){
                NewsDData newsDData_A=fxNewsFileTool.PuC_getNewsDDataFileData(timeOD_A);
                newsDData_A.PuC_addData(newsDataClasses);
                fxNewsFileTool.PuC_saveNewsDDataToFile(newsDData_A);
                return;
//

            }
        }
        public void updateNewDayNewsData(){
            synchronized ("NewsMainData"){
                TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
                TimeOD timeOD_B=timeOD_A.PuC_getCrTimeOD(-1);
                NewsMainData newsMainData_A=fxNewsPool.newsMainDataA;
                if (!fxNewsPool.newsMainDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                    fxNewsPool.newsMainDataA=new NewsMainData(timeOD_A);
                    fxNewsPool.newsMainDataA.initNewsMainData();
                }

                if (!fxNewsPool.newsMainDataB.timeOD.PuC_isTimeODSameDay(timeOD_B)) {
                    saveNewsData(fxNewsPool.newsMainDataB.newsDData);
                    if(newsMainData_A!=null&&(newsMainData_A.timeOD.PuC_isTimeODSameDay(timeOD_B)))
                        {
                            fxNewsPool.newsMainDataB=newsMainData_A;

                        }else {
                        fxNewsPool.newsMainDataB=new NewsMainData(timeOD_B);
                        fxNewsPool.newsMainDataB.initNewsMainData();
                    }
                }
            }
        }
        public void saveNewsData(NewsDData newsDData_A){
            //fxNewsFileTool.PuC_saveNewsDDataToFile(newsDData_A);
        }
        public void saveAllNewsData(){
            synchronized ("NewsMainData"){
                //]Log.w(this.toString(), "saveAllNewsDataW0: "+"="+(fxNewsPool.newsMainDataA!=null) );
               if(fxNewsPool.newsMainDataA!=null)
                   {

                       saveNewsData(fxNewsPool.newsMainDataA.newsDData);

                   }
                if(fxNewsPool.newsMainDataB!=null)
                {

                    saveNewsData(fxNewsPool.newsMainDataB.newsDData);

                }
            }
        }
        public class FXNewsPool{
            public NewsMainData newsMainDataA;
            public NewsMainData newsMainDataB;
            public FXNewsPool(){
                newsMainDataA=new NewsMainData(timeConnect.getTimeNew());
                newsMainDataB=new NewsMainData(timeConnect.getTimeNew().PuC_getCrTimeOD(-1));
                newsMainDataA.initNewsMainData();
                newsMainDataB.initNewsMainData();
            }



//            /**
//             *
//             */
//            public NewsDData PuC_isGetNewsData(SkillNewsName skillNewsNameAA, TimeOD timeODAA){
//                if(newsMainDataA!=null)
//                    {
//                        if(newsMainDataA.timeOD.PuC_isTimeODSameDay(timeODAA))
//                            {
//                                if(skillNewsNameAA==SkillNewsName.DaiFX即时新闻)
//                                    {
//                                        if(newsMainDataA.newsDData.intState>=2)
//                                            {
//                                            return newsMainDataA.newsDData.newsDData;
//                                            }
//                                    }
//
//                            }
//                    }
//                return null;
//
//            }
        }
        public class FXNewsFileTool{

            MyFileIOTool fileIOTool;
            Gson gson;
            String syStr_file="syStr_NewsFile";
            String syStr_fileRec="syStr_NewsFileRec";
            public FXNewsFileTool(MyFileIOTool myFileIOToolAA){
                fileIOTool=myFileIOToolAA;
                gson=new Gson();
            }
            /**
             *
             */
            public NewsDData PuC_getNewsDDataFileData(TimeOD timeODAA){
                synchronized (syStr_file){
                    String fileName=fileIOTool.getJSONNewsDDataFileName(timeODAA);
                    File file_A=fileIOTool.PuC_isJSONFile(fileName);
                    //]Log.w(this.toString(), "PuC_getNewsDDataFileDataW0: "+"="+(file_A!=null) );
                    if(file_A!=null) {
                        String string_B = fileIOTool.getJSONFileData(file_A);
                        NewsDData newsDData_A=gson.fromJson(string_B, NewsDData.class);
                        //]Log.w(this.toString(), "PuC_getNewsDDataFileDataW2: "+"="+(newsDData_A!=null) +"="+string_B );
                        if(newsDData_A.newsDayData!=null&&newsDData_A.newsDayData.newsDataClasses!=null)
                            {
                                //]Log.w(this.toString(), "PuC_getNewsDDataFileDataW1: "+"="+ newsDData_A.newsDayData.newsDataClasses.size());
                            }

                        return newsDData_A;
                    }else {
                        return null;
                    }
                }

            }
            /**
             *
             */
            public void PuC_saveNewsDDataToFile(NewsDData newsDData_A){
                synchronized (syStr_file){
                    String fileName=fileIOTool.getJSONNewsDDataFileName(newsDData_A.timeOD);
                    //]Log.w(this.toString(), "PuC_saveNewsDDataToFileW0: "+"="+newsDData_A.newsDayData.newsDataClasses.size() );
                    File file_A=new File(fileName);
                    String string_C=gson.toJson(newsDData_A);
//                    //]Log.w(this.toString(), "PuC_saveNewsDDataToFileR0: "+"="+ (file_A!=null));
//                    //]Log.w(this.toString(), "PuC_saveNewsDDataToFileR0: "+"="+ file_A.exists());
                    if(fileIOTool.PuC_sevaJSONToFile(string_C,file_A))
                    {

                    }else {
                        Log.e(this.toString(), "PuC_savQuoDayDataToFile: " );
                    }

                }

            }
            public FXNewsRecord.RecordDData PuC_getNewsRecData(TimeOD timeODAA){
                synchronized (syStr_fileRec){
                    String fileName=fileIOTool.PuC_getJSONQuoRecFileName(timeODAA);
                    File file_A=fileIOTool.PuC_isJSONFile(fileName);
                    if(file_A!=null) {
                        String string_B = fileIOTool.getJSONFileData(file_A);
                        FXNewsRecord.RecordDData quoDData_A=gson.fromJson(string_B, FXNewsRecord.RecordDData.class);
                        return quoDData_A;
                    }else {
                        return null;
                    }
                }
            }
            public void PuC_saveNewsRecData(FXNewsRecord.RecordDData recordDDataAA){
                synchronized (syStr_fileRec) {
                    String fileName=fileIOTool.PuC_getJSONQuoRecFileName(recordDDataAA.timeOD);
                    File file_A=fileIOTool.PuC_isJSONFile(fileName);
                    String string_C=gson.toJson(recordDDataAA);
                    if(fileIOTool.PuC_sevaJSONToFile(string_C,file_A))
                    {

                    }else {
                        Log.e(this.toString(), "PuC_savQuoDayDataToFile: " );
                    }
                }

            }
        }
        public class FXNewsRecord{
            public ArrayList<RecordDData> recordDDatas=new ArrayList<>();
            public RecordDData getRecDData(TimeOD timeODAA){
                synchronized (this) {
                    for (int i = 0; i < recordDDatas.size(); i++) {
                        if (recordDDatas.get(i).timeOD.PuC_isTimeODSameDay(timeODAA)) {
                            recordDDatas.get(i).intCount++;
                            return recordDDatas.get(i);
                        }
                    }

                    return addRecDData(timeODAA);
                }
            }
            public RecordDData addRecDData(TimeOD timeODAA){
                synchronized (this) {
                    RecordDData recordDData_A = fxNewsFileTool.PuC_getNewsRecData(timeODAA);
                    if (recordDData_A != null) {
                        recordDData_A.intCount = 1;
                        recordDData_A.intState = 2;
                    } else {
                        recordDData_A = new RecordDData(timeODAA);
                    }
                    recordDDatas.add(recordDData_A);
                    return recordDData_A;
                }
            }
            public void removeRecData(RecordDData recordDDataAA){
                synchronized (this){
                    for(int i=0;i<recordDDatas.size();i++)
                    {
                        if(recordDDatas.get(i).equals(recordDDataAA))
                        {
                            recordDDatas.get(i).intCount--;
                            if(recordDDatas.get(i).intCount<=0)
                            {
                                if(recordDDatas.get(i).intState==3)
                                {
                                    saveRecData(recordDDatas.get(i));
                                }
                                recordDDatas.remove(i);
                            }

                        }
                    }
                }


            }
            public void saveRecData(RecordDData recordDDataAA){
                recordDDataAA.intState=2;
                fxNewsFileTool.PuC_saveNewsRecData(recordDDataAA);
            }
            public class RecordDData{
                public TimeOD timeOD;
                public int intState;//数据状态[0=初始化,1=没有数据,2=有数据,3=需要存储]
                public int intCount;//引用次数
                public ArrayList<RecordData> recordDatas;
                public RecordDData(TimeOD timeODAA){
                    timeOD=timeODAA;
                    intState=0;
                    intCount=1;
                    recordDatas=new ArrayList<>();
                }
                public void addRecData(RecordT recordDataAA,String id){
                    recordDatas.add(new RecordData(recordDataAA));
                }

            }
            public class RecordData<T extends RecordT>{
                public T data;
                public RecordData(T dataAA){
                    data=dataAA;
                }

            }

            public class RecNewsTask extends RecordT{
                public RecNewsTask(){
                    intMode=1;
                }
            }
            public class RecordT{
                public int intMode;
                public int id;
            }


        }
        public class NewsMainData{
            public TimeOD timeOD;
            public int intState;//
            public NewsDData newsDData;
            public NewsMainData(TimeOD timeODAA){
                timeOD=timeODAA;
                newsDData =new NewsDData(timeOD);
            }
            public void initNewsMainData(){
                newsDData=fxNewsFileTool.PuC_getNewsDDataFileData(timeOD);
                if(newsDData==null)
                    {
                        newsDData=new NewsDData(timeOD);
                        intState=0;
                    }else {
                    intState=1;
                }


            }
            public NewsDData getNewsData(SkillNewsName skillNewsName_A){
                switch(skillNewsName_A){
                    case DailyFX:
                        return newsDData;
                    default:
                        break;
                }
                return null;
            }
        }
        public class NewsDData {
            public TimeOD timeOD;
            public SkillNewsName skillNewsName;
            public int intState;//数据状态[0=初始化,1=没有数据,2=有数据,3=等待存储]
            public NewsDayData newsDayData;
            public int intID;
            public NewsDData(TimeOD timeODAA){
                timeOD=timeODAA;
                newsDayData=new NewsDayData();
                intState=0;
                intID=0;
            }
            public void PuC_setData(NewsDayData newsDayData_A,TimeOD timeOD_A){
                timeOD=timeOD_A;

                if(newsDayData_A!=null){
                    newsDayData=newsDayData_A;
                    intState=2;
                }else {
                    intState=1;
                }


            }
            public void PuC_addData(NewsDataClass newsDataClass_A){
                newsDataClass_A.intID=intID+1;
                newsDayData.newsDataClasses.add(newsDataClass_A);
                intState=3;
            }
            public void PuC_addData(ArrayList<NewsDataClass> newsDataClass_A){
                for(int i=0;i<newsDataClass_A.size();i++)
                    {
                        newsDataClass_A.get(i).intID=intID++;
                    }
                newsDayData.newsDataClasses.addAll(newsDataClass_A);
                intState=3;
            }
        }
//        public class FXNewsCacha{
//            public FXNews fxNews;
//            public int intMaxDay;
//            public NewsDData[] newsDDatas;
//            public FXNewsCacha(FXNews fxNewsAA,int intMaXAA){
//                fxNews=fxNewsAA;
//                intMaxDay=intMaXAA;
//                newsDDatas=new NewsDData[intMaxDay];
//            }
//            /**
//             *
//             */
//            public void PuC_addNewsDData(NewsDData newsDDataAA){
//                for(int i=0;i<newsDDatas.length;i++)
//                    {
//                    if(newsDDatas[i]!=null)
//                        {
//                            if(newsDDatas[i].skillNewsName==newsDDataAA.skillNewsName)
//                                {
//                                if(newsDDatas[i].timeOD.PuC_isTimeODSameDay(newsDDataAA.timeOD))
//                                    {
//                                        if(newsDDatas[i].intState>=2)
//                                        {
//                                            newsDDatas[i]=null;
//                                        }
//                                    }
//                                }
//
//                        }
//                    }
//                NewsDData newsDData_B=newsDDataAA;
//                NewsDData newsDData_B2=null;
//                for(int i=0;i<newsDDatas.length;i++)
//                    {
//                        if(newsDData_B!=null)
//                            {
//                                if(newsDDatas[i]!=null)
//                                {
//                                    newsDData_B2=newsDDatas[i];
//                                    newsDDatas[i]=newsDData_B;
//                                    newsDData_B=newsDData_B2;
//                                }else {
//                                    newsDDatas[i]=newsDData_B;
//                                    newsDData_B=null;
//                                }
//                            }else {
//                            break;
//                        }
//
//                    }
//
//            }
//            /**
//             *
//             */
//            public NewsDData PuC_isGetNewsData(SkillNewsName skillNewsNameAA, TimeOD timeODAA){
//                for(int i=0;i<newsDDatas.length;i++)
//                    {
//                        if(newsDDatas[i]!=null)
//                            {
//                            if(newsDDatas[i].intState>=2)
//                                {
//                                if(newsDDatas[i].skillNewsName==skillNewsNameAA)
//                                    {
//                                    if(newsDDatas[i].timeOD.PuC_isTimeODSameDay(timeODAA))
//                                        {
//                                        return newsDDatas[i];
//                                        }
//                                    }
//                                }
//                            }
//
//                    }
//                return null;
//
//            }
//
//        }

//        public class NewsDData{
//            public TimeOD timeOD;
//            public SkillNewsName skillNewsName;
//            public int intState;//数据状态[0=初始化,1=没有数据,2=没有数据,3=等待存储]
//
//            public NewsDData(SkillNewsName skillNewsNameAA, TimeOD timeODAA){
//                skillNewsName=skillNewsNameAA;
//                timeOD=timeODAA;
//                intState=0;
//            }
//
//        }
        public class NewsDayData{
            public ArrayList<NewsDataClass> newsDataClasses=new ArrayList<>();
        }
//        public class NewsTodData{
//            public ArrayList<NewsData> newsData;
//            public NewsTodData(){
//                newsData=new ArrayList<>();
//            }
//        }

    }
    public class FXCalendar{
        FXCalenderPool fxCalenderPool;
        FXCalenderFileTool fxCalenderFileTool;
        Jin10CalendarCollectTool jin10CalendarCollectTool;
        public SnapCalendarDataTool snapCalendarDataTool;
        public FXCalendar(MyFileIOTool myFileIOToolAA) {
            fxCalenderFileTool = new FXCalenderFileTool(myFileIOToolAA);
            fxCalenderPool = new FXCalenderPool();
            jin10CalendarCollectTool=new Jin10CalendarCollectTool();
            snapCalendarDataTool=new SnapCalendarDataTool();
        }
//        public boolean setCalendarData(XMCalendarCollertTool.CalendarDH[] calendarDHS_A, TimeOD timeOD_A){
//            boolean bool_A=true;
//            synchronized ("CalenderMainData"){
//                if(fxCalenderPool.calenderMainDataA.timeOD.PuC_isTimeODSameDay(timeOD_A))
//                    {
//                        fxCalenderPool.calenderMainDataA.calenderDData.setData(calendarDHS_A);
//                        bool_A=false;
//                    }else if(fxCalenderPool.calenderMainDataB.timeOD.PuC_isTimeODSameDay(timeOD_A))
//                        {
//                            fxCalenderPool.calenderMainDataB.calenderDData.setData(calendarDHS_A);
//                            bool_A=false;
//                        } else if(fxCalenderPool.calenderMainDataC.timeOD.PuC_isTimeODSameDay(timeOD_A))
//                {
//                    fxCalenderPool.calenderMainDataC.calenderDData.setData(calendarDHS_A);
//                    bool_A=false;
//                }
//            }
//            if(bool_A)
//                {
//                    synchronized ("CalendarFileData"){
//                       CalenderDData calenderDData_A=fxCalenderFileTool.getCalenderDDataFileData(timeOD_A);
//                       if(calenderDData_A!=null)
//                           {
//                               calenderDData_A.setData(calendarDHS_A);
//                           }else {
//                           calenderDData_A=new CalenderDData(timeOD_A);
//                           calenderDData_A.setData(calendarDHS_A);
//                       }
//                       saveFXCalensarData(calenderDData_A);
//                    }
//                }
//            return false;
//        }
        //Jin10CalendarCollectTool.CalendarJinDH[] calendarJinDHS_A
        public CalendarDataClass[] setCalendarData(Jin10CalendarCollectTool.CalendarJinDH[] calendarDHS_A, TimeOD timeOD_A){
            boolean bool_A=true;
            CalendarDataClass[] calenderData_A=null;
            synchronized ("CalenderMainData"){
                if(fxCalenderPool.calenderMainDataA.timeOD.PuC_isTimeODSameDay(timeOD_A))
                {
                    calenderData_A=fxCalenderPool.calenderMainDataA.calenderDData.setData(calendarDHS_A);
                    bool_A=false;
                }else if(fxCalenderPool.calenderMainDataB.timeOD.PuC_isTimeODSameDay(timeOD_A))
                {
                    calenderData_A=fxCalenderPool.calenderMainDataB.calenderDData.setData(calendarDHS_A);
                    bool_A=false;
                }else if(fxCalenderPool.calenderMainDataC.timeOD.PuC_isTimeODSameDay(timeOD_A))
                {
                    calenderData_A=fxCalenderPool.calenderMainDataC.calenderDData.setData(calendarDHS_A);
                    bool_A=false;
                }
            }
            if(bool_A)
            {
                synchronized ("CalendarFileData"){
                    CalenderDData calenderDData_A=fxCalenderFileTool.getCalenderDDataFileData(timeOD_A);
                    if(calenderDData_A!=null)
                    {
                        calenderData_A=calenderDData_A.setData(calendarDHS_A);
                    }else {
                        calenderDData_A=new CalenderDData(timeOD_A);
                        calenderData_A=calenderDData_A.setData(calendarDHS_A);
                    }
                    saveFXCalensarData(calenderDData_A);
                }
            }
            return calenderData_A;
        }
        public CalendarDataClass[] setCalendarEVData(Jin10CalendarCollectTool.CalendarJinEV[] calendarDHS_A, TimeOD timeOD_A){
            boolean bool_A=true;
            CalendarDataClass[] calenderData_A=null;
            synchronized ("CalenderMainData"){
                if(fxCalenderPool.calenderMainDataA.timeOD.PuC_isTimeODSameDay(timeOD_A))
                {
                    calenderData_A=fxCalenderPool.calenderMainDataA.calenderDData.setData(calendarDHS_A);
                    bool_A=false;
                }else if(fxCalenderPool.calenderMainDataB.timeOD.PuC_isTimeODSameDay(timeOD_A))
                {
                    calenderData_A=fxCalenderPool.calenderMainDataB.calenderDData.setData(calendarDHS_A);
                    bool_A=false;
                }
            }
            if(bool_A)
            {
                synchronized ("CalendarFileData"){
                    CalenderDData calenderDData_A=fxCalenderFileTool.getCalenderDDataFileData(timeOD_A);
                    if(calenderDData_A!=null)
                    {
                        calenderData_A=calenderDData_A.setData(calendarDHS_A);
                    }else {
                        calenderDData_A=new CalenderDData(timeOD_A);
                        calenderData_A=calenderDData_A.setData(calendarDHS_A);
                    }
                    saveFXCalensarData(calenderDData_A);
                }
            }
            return calenderData_A;
        }
//        public CalenderData[] updateCalendarData(XMCalendarCollertTool.CalendarCC[] calendarCCS_A, TimeOD timeOD_A){
//            synchronized ("CalenderMainData"){
//                if(fxCalenderPool.calenderMainDataA.timeOD.PuC_isTimeODSameDay(timeOD_A))
//                {
//                    return  fxCalenderPool.calenderMainDataA.calenderDData.isUpdateData(calendarCCS_A);
//                }else if(fxCalenderPool.calenderMainDataB.timeOD.PuC_isTimeODSameDay(timeOD_A))
//                {
//                    return  fxCalenderPool.calenderMainDataB.calenderDData.isUpdateData(calendarCCS_A);
//                }
//            }
//            return null;
//        }
        public CalenderDData getDData(TimeOD timeOD_A){
            CalenderDData calenderDData=null;
            synchronized ("CalendarMainData"){
                if (fxCalenderPool.calenderMainDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                    calenderDData= fxCalenderPool.calenderMainDataA.calenderDData;

                } else if (fxCalenderPool.calenderMainDataB.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                    calenderDData= fxCalenderPool.calenderMainDataB.calenderDData;
                }
            }
            if(calenderDData!=null){
                if(calenderDData.calenderDayData.calenderDHData.size()==0)
                    {
                        calenderDData=null;
                    }else {
                    return calenderDData;
                }

            }
            synchronized ("CalendarFileData"){

                if(true)
                    {
                        return null;
                    }else {
                    calenderDData=fxCalenderFileTool.getCalenderDDataFileData(timeOD_A);
//                quoDayData_A=new QuoDData(skillQuoNameAA,timeODAA,3);
//                QuoDayData quoDayData_B=fxQuoFileTool.PuC_getQuoFileData(skillQuoNameAA,timeODAA);
                    if(calenderDData!=null)
                    {
                        if(calenderDData.calenderDayData.calenderDHData.size()==0)
                        {
                            calenderDData=null;
                        }
                    }
                }


            }
//            if(calenderDData!=null)return calenderDData;
//            if(true)
//                {
//
//                }

            return calenderDData;

        }
        public CalenderDData getHttpData(TimeOD timeOD_A){
            CalenderDData calenderDData_A=null;
            try {
                Jin10CalendarCollectTool.CalendarJinDH[] calendarJinDHS_A=jin10CalendarCollectTool.getEconomicCalendar(timeOD_A);
                Jin10CalendarCollectTool.CalendarJinEV[] calendarJinEVS_A=jin10CalendarCollectTool.getEconomicCalendarEV(timeOD_A);
                //]Log.w(this.toString(), "GG.getHttpData: TR0"+"="+calendarJinDHS_A.length +"="+calendarJinEVS_A.length );
                calenderDData_A=new CalenderDData(timeOD_A);
                calenderDData_A.setData(calendarJinDHS_A);
                calenderDData_A.setData(calendarJinEVS_A);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return calenderDData_A;
        }
        public void saveAllCalensarData(){
            if(fxCalenderPool.calenderMainDataA!=null&&fxCalenderPool.calenderMainDataA.intState==3)
                {
                fxCalenderFileTool.saveCalenderDDataToFile(fxCalenderPool.calenderMainDataA.calenderDData);
                    fxCalenderPool.calenderMainDataA.intState=2;
                }
            if(fxCalenderPool.calenderMainDataB!=null&&fxCalenderPool.calenderMainDataB.intState==3)
            {
                fxCalenderFileTool.saveCalenderDDataToFile(fxCalenderPool.calenderMainDataB.calenderDData);
                fxCalenderPool.calenderMainDataB.intState=2;
            }
        }
        public void saveFXCalensarData(CalenderDData calenderDData_A){
            fxCalenderFileTool.saveCalenderDDataToFile(calenderDData_A);
        }
        public void updateNewDayCalendarData(){
            synchronized ("NewsMainData"){
                TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
                TimeOD timeOD_B=timeOD_A.PuC_getCrTimeOD(-1);
                CalenderMainData calenderMainData_A=fxCalenderPool.calenderMainDataA;
                if (!fxCalenderPool.calenderMainDataA.timeOD.PuC_isTimeODSameDay(timeOD_A)) {
                    fxCalenderPool.calenderMainDataA=new CalenderMainData(timeOD_A);
                    fxCalenderPool.calenderMainDataA.initNewsMainData();
                }

                if (!fxCalenderPool.calenderMainDataB.timeOD.PuC_isTimeODSameDay(timeOD_B)) {
                    saveFXCalensarData(fxCalenderPool.calenderMainDataB.calenderDData);
                    if(calenderMainData_A!=null&&(calenderMainData_A.timeOD.PuC_isTimeODSameDay(timeOD_B)))
                    {
                        fxCalenderPool.calenderMainDataB=calenderMainData_A;

                    }else {
                        fxCalenderPool.calenderMainDataB=new CalenderMainData(timeOD_B);
                        fxCalenderPool.calenderMainDataB.initNewsMainData();
                    }
                }
            }
        }
        public class FXCalenderPool{
            public CalenderMainData calenderMainDataA;
            public CalenderMainData calenderMainDataB;
            public CalenderMainData calenderMainDataC;
            public FXCalenderPool(){
                calenderMainDataA=new CalenderMainData(timeConnect.getTimeNew());
                calenderMainDataB=new CalenderMainData(timeConnect.getTimeNew().PuC_getCrTimeOD(-1));
                calenderMainDataC=new CalenderMainData(timeConnect.getTimeNew().PuC_getCrTimeOD(1));
                calenderMainDataA.initNewsMainData();
                calenderMainDataB.initNewsMainData();
                calenderMainDataC.initNewsMainData();
            }

        }
        class CalenderMainData{
            public TimeOD timeOD;
            public int intState;//数据状态[0=初始化,1=没有数据,2=有数据,3=需要存储]
            public CalenderDData calenderDData;
            public CalenderMainData(TimeOD timeODAA){
                timeOD=timeODAA;
                calenderDData =new CalenderDData(timeOD);
            }
            public void initNewsMainData(){
                calenderDData=fxCalenderFileTool.getCalenderDDataFileData(timeOD);
                if(calenderDData==null)
                {
                    calenderDData=new CalenderDData(timeOD);
                    intState=0;
                }else {
                    intState=1;
                }


            }
        }
        public class CalenderDData{
             public TimeOD timeOD;
//            public SkillNewsName skillNewsName;
            public int intState;//数据状态[0=初始化,1=没有数据,2=有数据,3=等待存储]
            int intCalendarN;
            public CalenderDayData calenderDayData;
//            public int intID;
            public CalenderDData(TimeOD timeOD_A){
                timeOD=timeOD_A;
                calenderDayData=new CalenderDayData();
                intState=0;
                intCalendarN=0;
            }

//            public boolean setData(XMCalendarCollertTool.CalendarDH[] calendarDHS_A) {
//                boolean bool_rt=false;
//                boolean bool_N=true;
//
//                if(calenderDayData!=null)
//                    {
//                        //]Log.w(this.toString(), "XMCalendarCollertTool.Data: E0 :DH"+"="+calendarDHS_A.length +"="+calenderDayData.calenderDHData.size() +"="+timeOD.getStringDay() );
//                        if(calenderDayData.calenderDHData.size()==calendarDHS_A.length)
//                            {
//                                for(int i = intCalendarN; i<calenderDayData.calenderDHData.size(); i++) {
//                                    if (calenderDayData.calenderDHData.get(i).boolMode) {
//                                        if (!calendarDHS_A[i].stringData0.equals("")) {
//                                            if (calenderDayData.calenderDHData.get(i).stringActual.equals("")) {
//
//                                                    calenderDayData.calenderDHData.get(i).stringActual = calendarDHS_A[i].stringData0;
//                                                    calenderDayData.calenderDHData.get(i).intColorFont0 = calendarDHS_A[i].intColorFont0;
//                                                    if (calenderDayData.calenderDHData.get(i).stringPrevious.equals("")) {
//                                                        calenderDayData.calenderDHData.get(i).stringPrevious = calendarDHS_A[i].stringData2;
//                                                        //calenderDayData.calenderDHData.get(i).boolFont0=calendarDHS_A[i].boolFont0;
//                                                    }
//                                                    bool_rt = true;
//
//
//                                            }
//                                        } else {
//                                            if (bool_N) {
//                                                intCalendarN = i;
//                                                bool_N = false;
//                                            }
//
//                                        }
//                                    }
//
//
//                                }
//
//                            }else {
//                            if(calenderDayData.calenderDHData.size()==0)
//                                {
//                                    for(int i=0;i<calendarDHS_A.length;i++)
//                                        {
//                                            //]Log.w(this.toString(), "XM.setData: TG0a" );
//                                            calenderDayData.calenderDHData.add(new CalenderData(calendarDHS_A[i]));
//                                            if(calendarDHS_A[i].boolMode)
//                                                {
//                                                    if(calendarDHS_A[i].stringData0==null)
//                                                        {
//                                                            if (bool_N) {
//                                                                intCalendarN = i;
//                                                                bool_N = false;
//                                                            }
//                                                        }
//
//                                                }
//                                        }
//
//                                }else {
//                                calenderDayData.calenderDHData =new ArrayList<>();
//                                for(int i=0;i<calendarDHS_A.length;i++)
//                                {
//
//                                    //]Log.w(this.toString(), "XM.setData: TG0a" );
//                                    calenderDayData.calenderDHData.add(new CalendarDataClass(calendarDHS_A[i]));
//                                    if(calendarDHS_A[i].boolMode)
//                                    {
//                                        if(calendarDHS_A[i].stringData0==null)
//                                        {
//                                            if (bool_N) {
//                                                intCalendarN = i;
//                                                bool_N = false;
//                                            }
//                                        }
//
//                                    }
//                                }
//                            }
//
//
//                        }
//                        //]Log.w(this.toString(), "XMCalendarCollertTool.Data: E1 :DH"+"="+bool_rt );
//
//                    }
//                return bool_rt;
//            }
            public CalendarDataClass[] setData(Jin10CalendarCollectTool.CalendarJinDH[] calendarDHS_A) {
                boolean bool_rt=false;
                boolean bool_N=true;
                ArrayList<CalendarDataClass> calenderData_A=new ArrayList<>();

                if(calenderDayData!=null)
                {
                    if(calenderDayData.calenderDHData ==null)
                        {
                            calenderDayData.calenderDHData =new ArrayList<>();
                        }
                    //calenderDayData.calenderDHData=new ArrayList<>();
                    //]Log.w(this.toString(), "JinCalendarCollertTool.Data: E0 :DH"+"="+calendarDHS_A.length +"="+calenderDayData.calenderDHData.size() +"="+timeOD.getStringDay() );
                    int int_A=0;
                    int int_B=0;
                    String string_A="";
                    if(calenderDayData.calenderDHData.size()==calendarDHS_A.length)
                    {
                        for(int i = intCalendarN; i<calenderDayData.calenderDHData.size(); i++) {
                            if(calenderData_A.size()<5)
                                {
                                    calenderData_A.add(calenderDayData.calenderDHData.get(i));
                                }
                            if (true||calenderDayData.calenderDHData.get(i).hasResult) {
                                if (calendarDHS_A[i].stringActual!=null) {
                                    if (calenderDayData.calenderDHData.get(i).stringActual ==null) {
                                        int_A++;
                                        calenderDayData.calenderDHData.get(i).stringActual = calendarDHS_A[i].stringActual;
                                        //calenderDayData.calenderDHData.get(i).intColorFont0 = calendarDHS_A[i].intColorFont0;
                                        if (calenderDayData.calenderDHData.get(i).stringPrevious ==null) {
                                            calenderDayData.calenderDHData.get(i).stringPrevious = calendarDHS_A[i].stringPrevious;
                                            //calenderDayData.calenderDHData.get(i).boolFont0=calendarDHS_A[i].boolFont0;
                                        }

                                        calenderData_A.add(calenderDayData.calenderDHData.get(i));
                                        bool_rt = true;


                                    }
                                } else {
                                    if (bool_N) {
                                        intCalendarN = i;
                                        int_B=i;
                                        string_A=calenderDayData.calenderDHData.get(i).stringTime;
                                        bool_N = false;
                                    }

                                }
                            }


                        }

                    }else {
                        if(calenderDayData.calenderDHData.size()==0)
                        {
                            for(int i=0;i<calendarDHS_A.length;i++)
                            {
                                calenderDayData.calenderDHData.add(new CalendarDataClass(calendarDHS_A[i]));
                                if(true||calendarDHS_A[i].boolMode)
                                {
                                    if(calendarDHS_A[i].stringActual==null)
                                    {
                                        if (bool_N) {
                                            intCalendarN = i;
                                            bool_N = false;
                                        }
                                    }

                                }
                            }

                        }else {
                            calenderDayData.calenderDHData =new ArrayList<>();
                            for(int i=0;i<calendarDHS_A.length;i++)
                            {
                                calenderDayData.calenderDHData.add(new CalendarDataClass(calendarDHS_A[i]));
                                if(true||calendarDHS_A[i].boolMode)
                                {
                                    if(calendarDHS_A[i].stringActual==null)
                                    {
                                        if (bool_N) {
                                            intCalendarN = i;
                                            int_B=i;
                                            string_A=calenderDayData.calenderDHData.get(i).stringTime;
                                            bool_N = false;
                                        }
                                    }

                                }
                            }
                        }


                    }
                    //]Log.w(this.toString(), "JinCalendarCollertTool.Data: E1 :DH"+"="+int_A +"="+int_B +"="+string_A);
                    //]Log.w(this.toString(), "XMCalendarCollertTool.Data: E1 :DH"+"="+bool_rt );

                }
                //]Log.w(this.toString(), "JinCalendarCollertToolG$: R0"+"="+ calenderData_A.size());
                if(calenderData_A.size()==0)
                    {
                    return null;
                    }else {
                    return calenderData_A.toArray(new CalendarDataClass[calenderData_A.size()]);
                }
              //  return bool_rt;
            }
            public CalendarDataClass[] setData(Jin10CalendarCollectTool.CalendarJinEV[] calendarEVS_A) {
                boolean bool_rt=false;
                boolean bool_N=true;
                ArrayList<CalendarDataClass> calenderData_A=new ArrayList<>();

                if(calenderDayData!=null)
                {
                    //calenderDayData.calenderDHData=new ArrayList<>();
                    //]Log.w(this.toString(), "JinCalendarCollertTool.Data: E0 :EV"+"="+calendarEVS_A.length +"="+calenderDayData.calenderEVData.size() );
                    int int_A=0;
                    int int_B=0;
                    String string_A="";
                    if(calenderDayData.calenderEVData ==null||calenderDayData.calenderEVData.size()==0)
                        {
                            calenderDayData.calenderEVData =new ArrayList<>();
                            for(int i=0;i<calendarEVS_A.length;i++)
                                {
                                    calenderDayData.calenderEVData.add(new CalendarDataClass(calendarEVS_A[i]));
                                }
                        }else {
                        if(calenderDayData.calenderEVData.size()==calendarEVS_A.length)
                            {

                            }else {

                        }
                    }


                }
//                //]Log.w(this.toString(), "JinCalendarCollertToolG$: R0"+"="+ calenderData_A.size());
//                if(calenderData_A.size()==0)
//                {
//                    return null;
//                }else {
//                    return calenderData_A.toArray(new CalenderData[calenderData_A.size()]);
//                }
                return null;
                //  return bool_rt;
            }
            public CalendarDataClass[] isUpdateData(XMCalendarCollertTool.CalendarCC[] calendarCCS_A){
                boolean bool_rt=false;
                boolean bool_N=true;
                ArrayList<CalendarDataClass> calenderData_A=new ArrayList<>();
                if(calenderDayData!=null)
                {
                    //]Log.w(this.toString(), "XMCalendarCollertTool.Data: E0 :CC"+"="+calendarCCS_A.length +"="+calenderDayData.calenderDHData.size() +"="+new TimeOD(System.currentTimeMillis()).intsA[4] );
                    if(calenderDayData.calenderDHData.size()==calendarCCS_A.length)
                    {

                        for(int i = intCalendarN; i<calenderDayData.calenderDHData.size(); i++)
                        {
                            if(calendarCCS_A[i]!=null)
                                {
                                    if (!calendarCCS_A[i].stringData0.equals("")) {
                                        if (calenderDayData.calenderDHData.get(i).stringActual.equals("")) {

                                                    calenderData_A.add(calenderDayData.calenderDHData.get(i));
                                                    calenderDayData.calenderDHData.get(i).stringActual = calendarCCS_A[i].stringData0;
                                                    //calenderDayData.calenderDHData.get(i).intColorFont0 = calendarCCS_A[i].intColorFont0;
                                                    if (calenderDayData.calenderDHData.get(i).stringPrevious.equals("")) {
                                                        calenderDayData.calenderDHData.get(i).stringPrevious = calendarCCS_A[i].stringData2;
                                                        //calenderDayData.calenderDHData.get(i).boolFont0=calendarDHS_A[i].boolFont0;
                                                    }
                                                    bool_rt = true;


                                        }
                                    } else {
                                        if (bool_N) {
                                            intCalendarN = i;
                                            bool_N = false;
                                        }

                                    }
                                }



                        }

                    }else {

                        //]Log.w(this.toString(), "XMCalendarCollertTool.Data: E2 :CC"+"="+calendarCCS_A.length +"="+calenderDayData.calenderDHData.size());


                    }

                }
                //]Log.w(this.toString(), "XMCalendarCollertTool.Data: E1 :CC"+"="+calenderData_A.size());
                if(calenderData_A.size()!=0)
                    {
                    return calenderData_A.toArray(new CalendarDataClass[calenderData_A.size()]);
                    }else {
                    return null;
                }
            }
        }
        public class CalenderDayData{
            //public ArrayList<CalenderData> calenderDHData;
            //public ArrayList<CalenderData> calenderEVData;
            public ArrayList<CalendarDataClass> calenderDHData;
            public ArrayList<CalendarDataClass> calenderEVData;
            public CalenderDayData(){
                calenderDHData =new ArrayList<>();
                calenderEVData =new ArrayList<>();
            }

        }
        public class CalenderData{
            public int intMode;
            public boolean boolPending;
            public boolean boolMode;
            public long longTime;
            public String stringTimePeriod;
            public String stringTime;
            public String stringID;
            public int intID;
            public String stringAttr;
            public String stringFlag;
            public String stringName;
            public int intStar;
            public String stringActual;
            public String stringForecast;
            public String stringPrevious;
            public int intColorFont0;
            public int intColorFont1;
            public boolean boolRevised;
            CalendarDataClass calendarDataClass;
            public CalenderData(long longTime, String stringTime, String stringID, String stringAttr, String stringFlag, String stringName, int intStar, String stringActual, String stringForecast, String stringPrevious, int intColorFont0, int intColorFont1, boolean boolRevised){

                this.longTime=longTime;
                this.stringTime=stringTime;
                this.stringID=stringID;
                this.stringAttr=stringAttr;
                this.stringFlag=stringFlag;
                this. stringName=stringName;
                this.intStar = intStar;
                this.stringActual = stringActual;
                this.stringForecast = stringForecast;
                this.stringPrevious = stringPrevious;
                this. intColorFont0=intColorFont0;
                this. intColorFont1=intColorFont1;
                this. boolRevised=boolRevised;

                //]Log.w(this.toString(), "CalenderData: JJ0d"+"="+ stringActual);
            }
            public CalenderData(XMCalendarCollertTool.CalendarDH calendarDH){
                this.boolMode=calendarDH.boolMode;
                this.longTime=calendarDH.longTime;
                this.stringTime=calendarDH.stringTime;
                this.stringID=calendarDH.stringID;
                this.stringAttr=calendarDH.stringAttr;
                this.stringFlag=calendarDH.stringFlag;
                this. stringName=calendarDH.stringName;
                this.intStar =calendarDH.intImportance;
                this.stringActual =calendarDH.stringData0;
                this.stringForecast =calendarDH.stringData1;
                this.stringPrevious =calendarDH.stringData2;
                this. intColorFont0=calendarDH.intColorFont0;
                this. intColorFont1=calendarDH.intColorFont1;
                this. boolRevised=calendarDH.boolRevised;

                //]Log.w(this.toString(), "CalenderData: JJ0c"+"="+ stringActual);
            }
            public CalenderData(Jin10CalendarCollectTool.CalendarJinDH calendarDH){
                calendarDataClass=new CalendarDataClass(calendarDH);
                intMode=1;
                this.boolMode=calendarDH.boolMode;
                this.longTime=calendarDH.longTime;
                this.stringTimePeriod=calendarDH.stringTimePeriod;
                this.stringTime=calendarDH.stringTime;
                this.intID=calendarDH.intID;
                //this.stringAttr=calendarDH.stringAttr;
                this.stringFlag=calendarDH.stringFlag;
                this. stringName=calendarDH.stringName;
                this.intStar =calendarDH.intStar;
                this.stringActual =calendarDH.stringActual;
                this.stringForecast =calendarDH.stringForecast;
                this.stringPrevious =calendarDH.stringPrevious;
                //]Log.w(this.toString(), "CalenderData: GR0"+"="+stringActual +"="+stringForecast +"="+stringPrevious);

                //]Log.w(this.toString(), "CalenderData: JJ0a"+"="+ stringActual);
                //this. intColorFont0=calendarDH.intColorFont0;
               // this. intColorFont1=calendarDH.intColorFont1;
               //this. boolRevised=calendarDH.boolRevised;
                calendarDataClass=new CalendarDataClass(calendarDH);
            }
            public CalenderData(Jin10CalendarCollectTool.CalendarJinEV calendarEV_A){
                calendarDataClass=new CalendarDataClass(calendarEV_A);
                intMode=2;

                this.boolPending=calendarEV_A.boolPending;
                this.intID=calendarEV_A.intID;
                this.stringFlag=calendarEV_A.stringFlag;
                this.stringActual =calendarEV_A.stringCity;
                //]Log.w(this.toString(), "CalenderData: JJ0b"+"="+ stringActual);
                this.stringTime=calendarEV_A.stringTime;
                this. stringName=calendarEV_A.stringEvent;
                this.intStar =calendarEV_A.intStar;
                //this. intColorFont0=calendarDH.intColorFont0;
                // this. intColorFont1=calendarDH.intColorFont1;
                //this. boolRevised=calendarDH.boolRevised;
            }
        }
        class FXCalenderFileTool{
            MyFileIOTool fileIOTool;
            Gson gson;
            String syStr_file="syStr_CalenderFile";
            String syStr_fileRec="syStr_CalenderFileRec";
            public FXCalenderFileTool(MyFileIOTool myFileIOToolAA){
                fileIOTool=myFileIOToolAA;
                gson=new Gson();
            }
            /**
             *
             */
            public CalenderDData getCalenderDDataFileData(TimeOD timeODAA){
                synchronized (syStr_file){
                    String fileName=fileIOTool.getCalenderDDataFileName(timeODAA);
                    File file_A=fileIOTool.PuC_isJSONFile(fileName);
                    //]Log.w(this.toString(), "getCalenderDDataFileName: "+"="+(file_A!=null) );
                    if(file_A!=null) {
                        String string_B = fileIOTool.getJSONFileData(file_A);
                        CalenderDData calenderDData_A=gson.fromJson(string_B, CalenderDData.class);
                        //]Log.w(this.toString(), "PuC_getNewsDDataFileDataW2: "+"="+(calenderDData_A!=null) +"="+string_B );
                        if(calenderDData_A.calenderDayData!=null&&calenderDData_A.calenderDayData.calenderDHData !=null)
                        {
                           // //]Log.w(this.toString(), "PuC_getNewsDDataFileDataW1: "+"="+ calenderDData_A.newsDayData.calendarDataClasses.size());
                        }

                        return calenderDData_A;
                    }else {
                        return null;
                    }
                }

            }
            /**
             *
             */
            public void saveCalenderDDataToFile(CalenderDData calenderDData_A){
                synchronized (syStr_file){
                    String fileName=fileIOTool.getCalenderDDataFileName(calenderDData_A.timeOD);
                    //]Log.w(this.toString(), "PuC_saveNewsDDataToFileW0: "+"="+calenderDData_A.calenderDayData.calenderDHData.size() );
                    File file_A=new File(fileName);
                    String string_C=gson.toJson(calenderDData_A);
//                    //]Log.w(this.toString(), "PuC_saveNewsDDataToFileR0: "+"="+ (file_A!=null));
//                    //]Log.w(this.toString(), "PuC_saveNewsDDataToFileR0: "+"="+ file_A.exists());
                    if(fileIOTool.PuC_sevaJSONToFile(string_C,file_A))
                    {

                    }else {
                        Log.e(this.toString(), "PuC_savQuoDayDataToFile: " );
                    }

                }

            }
//            public FXNewsRecord.RecordDData PuC_getNewsRecData(TimeOD timeODAA){
//                synchronized (syStr_fileRec){
//                    String fileName=fileIOTool.PuC_getJSONQuoRecFileName(timeODAA);
//                    File file_A=fileIOTool.PuC_isJSONFile(fileName);
//                    if(file_A!=null) {
//                        String string_B = fileIOTool.getJSONFileData(file_A);
//                        FXNewsRecord.RecordDData quoDData_A=gson.fromJson(string_B, FXNewsRecord.RecordDData.class);
//                        return quoDData_A;
//                    }else {
//                        return null;
//                    }
//                }
//            }
//            public void PuC_saveNewsRecData(FXNewsRecord.RecordDData recordDDataAA){
//                synchronized (syStr_fileRec) {
//                    String fileName=fileIOTool.PuC_getJSONQuoRecFileName(recordDDataAA.timeOD);
//                    File file_A=fileIOTool.PuC_isJSONFile(fileName);
//                    String string_C=gson.toJson(recordDDataAA);
//                    if(fileIOTool.PuC_sevaJSONToFile(string_C,file_A))
//                    {
//
//                    }else {
//                        Log.e(this.toString(), "PuC_savQuoDayDataToFile: " );
//                    }
//                }
//
//            }
        }
        class FXCalenderRecord{

        }
    }
}
