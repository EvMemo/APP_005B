package com.example.app_005.Tool;

import android.content.Context;
import android.util.Log;

import com.example.app_005.Class.CalendarDataClass;
import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Class.SkillQuoName;
import com.example.app_005.Interface.OnUpdateCalendarData;
import com.example.app_005.Interface.OnUpdateNewsData;
import com.example.app_005.Interface.OnUpdateQuoData;
import com.example.app_005.Interface.OnUpdateSnapCalendarData;
import com.example.app_005.Interface.OnUpdateSnapNewsData;
import com.example.app_005.Interface.OnUpdateTime;
import com.example.app_005.Internet.Jin10CalendarCollectTool;
import com.example.app_005.Tool.WindowView.SnapCalendarDataTool;
import com.example.app_005.Tool.WindowView.SnapNewsDataTool;

import java.util.ArrayList;

public class FXDataConnect {
    Context context;
    QuoConnect quoConnect;
    NewsConnect newsConnect;
    FileIOConnect fileIOConnect;
    TimeConnect timeConnect;
    CalendarConnect calendarConnect;
    ReadConnect readConnect;
    FXCollectTool collectTool;

    public FXDataConnect(Context context_A) {
        context=context_A;
//        quoConnect=new QuoConnect(fxQuoAA);
//        newsConnect=new NewsConnect(fxNewsAA);
//        fileIOConnect=new FileIOConnect(myFileIOToolAA);
    }
    public void setFXCollectTool(FXCollectTool fxCollectTool_A){collectTool=fxCollectTool_A;}
    public void setFXQuo(FXDataPool.FXQuo fxQuoAA){quoConnect=new QuoConnect(fxQuoAA);}
    public void setFXNews(FXDataPool.FXNews fxNewsAA, ReadModeTool.ReadModeNews readModeNews_A){newsConnect=new NewsConnect(fxNewsAA,readModeNews_A);}
    public void setFileIO(MyFileIOTool myFileIOToolAA){fileIOConnect=new FileIOConnect(myFileIOToolAA);}
    public void setTitleTime(TitleTime titleTimeAA){timeConnect=new TimeConnect(titleTimeAA); }
    public void setFXCalendar(FXDataPool.FXCalendar fxCalendar_A){calendarConnect=new CalendarConnect(fxCalendar_A); }
    public void setReadConnect(){readConnect=new ReadConnect();}
    public QuoConnect getQuoConnect(){  return quoConnect; }
    public QuoConnect getQuoConnect(OnUpdateQuoData onUpdateQuoData){ quoConnect.addOnUpdateUI(onUpdateQuoData);  return quoConnect; }
    public void removeQuoConnnect(OnUpdateQuoData onUpdateQuoData){  quoConnect.removeOnUpdateUI(onUpdateQuoData); }
    public NewsConnect getNewsConnect(){ return newsConnect;}
    public NewsConnect getNewsConnect(OnUpdateNewsData onUpdateNewsData_A){newsConnect.addOnUpdateUI(onUpdateNewsData_A); return newsConnect;}
    public void removeNewsConnnect(OnUpdateNewsData onUpdateNewsData_A){newsConnect.removeOnUpdateUII(onUpdateNewsData_A); }
    public FileIOConnect getFileConnect(){return fileIOConnect;}
    public void removeFileConnnect(){ }
    public TimeConnect getTimeConnect(){return timeConnect; }
    public TimeConnect getTimeConnect(OnUpdateTime onUpdateTime){timeConnect.addOnUpdateTime(onUpdateTime);return timeConnect; }
    public void removeTimeConnnect(){ }
    public CalendarConnect getCalendarConnect(){return calendarConnect; }
    public CalendarConnect getCalendarConnect(OnUpdateCalendarData onUpdateCalendarData_A){calendarConnect.addOnUpdateUI(onUpdateCalendarData_A);return calendarConnect; }
    public void removeCalendarConnnect(OnUpdateCalendarData onUpdateCalendarData_A){calendarConnect.removeOnUpdateUII(onUpdateCalendarData_A); }
    public ReadConnect getReadConnect(){return readConnect;}


    public class QuoConnect {
        FXDataPool.FXQuo fxQuo;
        public boolean boolUpdateTime;
        ArrayList<OnUpdateQuoData> onUpdateQuoData;

        public QuoConnect(FXDataPool.FXQuo fxQuoAA) {
            fxQuo = fxQuoAA;
            boolUpdateTime = false;
            onUpdateQuoData = new ArrayList<>();
        }

        /**
         * 将收集到的报价数据赋值给数据池
         */
        public void assignQuoData(FXCollectTool.FXQuoCollect.QuoCollectData[] quoDDatasAA, TimeOD timeOD) {
            isUpdataTime();
            fxQuo.addQuoData(quoDDatasAA, timeOD);
            updateUI(0);
        }


        public void assignQuoLine(FXCollectTool.FXQuoCollect.QuoLineCollectData[] quoDDatasAA, TimeOD timeOD) {
            isUpdataTime();
            fxQuo.addQuoLine(quoDDatasAA, timeOD);
            updateUI(0);
        }
        public void assignQuoHistoryData(FXAlphaVantageTool.QuoHistoryData quoHistoryData_Data){
            isUpdataTime();
            fxQuo.addQuoHistoryData(quoHistoryData_Data);

            updateUI(0);
        }

        public FXDataPool.FXQuo.QuoDData getFXQuoData(SkillQuoName skillQuoNameAA, TimeOD timeOD) {
            isUpdataTime();
            return fxQuo.PuC_getQuoDData(skillQuoNameAA, timeOD);
        }

//        public float[][] getFXQuoFloatData(SkillQuoName skillQuoName, MyFXDataService.TimeOD timeOD, int floatTimeNum) {
//            isUpdataTime();
//
//
//            return floats_rt;
//        }

        public FXDataPool.FXQuo.QuoDayLine getFXQuoLine(SkillQuoName skillQuoNameAA, SkillQuoName skillQuoNameBB, TimeOD timeOD) {
            isUpdataTime();
            return fxQuo.PuC_getQuoDLine(skillQuoNameAA, skillQuoNameBB, timeOD);
        }
        public void saveFXQuoMainData(){
            fxQuo.sevaQuoData();
        }

        public void setBoolUpdateNewDateTime() {
            synchronized ("BoolUpdateTime") {
                boolUpdateTime = true;
            }
        }

        public boolean isUpdataTime() {
            synchronized ("BoolUpdateTime") {
                if (!boolUpdateTime) {
                    return false;
                } else {
                    updateFXQuoData();
                    return true;
                }
            }
        }

        void updateFXQuoData() {
            TimeOD timeOD_A = timeConnect.getTimeNew();
            fxQuo.PuC_updateMainQuoData(timeOD_A);
            updateUI(4);
        }

        void updateUI(int intUI) {
            synchronized ("OnUpdateQuoData"){
                for (int i = 0; i < onUpdateQuoData.size(); i++) {
                    if(onUpdateQuoData.get(i)!=null)
                        {
                            onUpdateQuoData.get(i).onUpdateQuoUI(intUI);
                        }else {
                        onUpdateQuoData.remove(i);
                        i--;
                    }

                }
            }

        }
        public void addOnUpdateUI(OnUpdateQuoData onUpdateUIAA){
            synchronized ("OnUpdateQuoData"){
                onUpdateQuoData.add(onUpdateUIAA);
            }
        }
        public void removeOnUpdateUI(OnUpdateQuoData onUpdateUIAA){
            synchronized ("OnUpdateQuoData"){
                onUpdateQuoData.remove(onUpdateUIAA);

            }
        }
    }
    public class NewsConnect {
        FXDataPool.FXNews fxNews;
        ArrayList<OnUpdateNewsData> onUpdateNewsData=new ArrayList<>();
        boolean boolUpdateTime;
        ReadModeTool.ReadModeNews readModeNews;

        public NewsConnect(FXDataPool.FXNews fxNewsAA,ReadModeTool.ReadModeNews readModeNews_A) {
            fxNews = fxNewsAA;
            readModeNews=readModeNews_A;
        }

        public FXDataPool.FXNews.NewsDData getNewsData( TimeOD timeOD_A){
            return fxNews.getNewsData(timeOD_A);

        }
        public void addNewsData(ArrayList<NewsDataClass> newsDataClasses,TimeOD timeOD_A){
            isUpdataTime();
            fxNews.addNewsDataClass(newsDataClasses,timeOD_A);
            fxNews.snapNewsDataTool.addSnapNewsData(newsDataClasses.toArray(new NewsDataClass[newsDataClasses.size()]),timeOD_A);
            saveNewsData();
            //updateUI(0);
            updateUI(newsDataClasses.toArray(new NewsDataClass[newsDataClasses.size()]));
            updateUI(0);

        }
        public void saveNewsData(){
            fxNews.saveAllNewsData();
        }

        public void setBoolUpdateNewDateTime() {
            synchronized ("BoolUpdateTime") {
                boolUpdateTime = true;
            }
        }

        public boolean isUpdataTime() {
            synchronized ("BoolUpdateTime") {
                if (!boolUpdateTime) {
                    return false;
                } else {
                    updateNewsDataDay();
                    return true;
                }
            }
        }

        void updateNewsDataDay() {
            TimeOD timeOD_A = timeConnect.getTimeNew();
            fxNews.updateNewDayNewsData();
            updateUI(4);
        }

        void updateUI(NewsDataClass[] newsDataClasses_A) {
            readModeNews.addNewsDataClass(newsDataClasses_A);
            synchronized ("OnUpdateNewsData"){

                for (int i = 0; i < onUpdateNewsData.size(); i++) {
                    if(onUpdateNewsData.get(i)!=null)
                    {
                        onUpdateNewsData.get(i).onUpdateNewsData(newsDataClasses_A);
                    }else {
                        onUpdateNewsData.remove(i);
                        i--;
                    }

                }
            }

        }
        void updateUI(int intUI) {
            synchronized ("OnUpdateNewsData"){
                for (int i = 0; i < onUpdateNewsData.size(); i++) {
                    if(onUpdateNewsData.get(i)!=null)
                    {
                        onUpdateNewsData.get(i).onUpdateNewsData(intUI);
                    }else {
                        onUpdateNewsData.remove(i);
                        i--;
                    }

                }
            }

        }
        public void addOnUpdateUI(OnUpdateNewsData onUpdateNewsData_A){
            synchronized ("OnUpdateNewsData"){
                onUpdateNewsData.add(onUpdateNewsData_A);
            }
        }
        public void removeOnUpdateUII(OnUpdateNewsData onUpdateNewsData_A){
            synchronized ("OnUpdateNewsData"){
                onUpdateNewsData.remove(onUpdateNewsData_A);

            }
        }
        public SnapNewsDataTool getSnapNewsDataTool(OnUpdateSnapNewsData onUpdateSnapNewsData){
            fxNews.snapNewsDataTool.addOnUpdateSnapNewsData(onUpdateSnapNewsData);
            return fxNews.snapNewsDataTool;
        }
        public void removeSnapNewsDataTool(OnUpdateSnapNewsData onUpdateSnapNewsData){
            fxNews.snapNewsDataTool.removeOnUpdateSnapNewsData(onUpdateSnapNewsData);
        }
    }
    public class TimeConnect{
        TitleTime titleTime;
        ArrayList<OnUpdateTime> onUpdateTimes;
        public TimeConnect(TitleTime titleTimeAA){
            titleTime=titleTimeAA;
            onUpdateTimes=new ArrayList<>();
        }
        public TimeOD getTimeNew(){
            return titleTime.getTimeNew();
        }
        public TimeOD getTimeNow(){
            return titleTime.getTimeNow();
        }
        public void setTimeNow(TimeOD timeNowAA){
            titleTime.setTimeNow(timeNowAA);
        }
//        public void updateTimeNew(){
//            titleTime.updateTimeNew();
//        }
        public void addOnUpdateTime(OnUpdateTime onUpdateTimeAA){
            synchronized ("OnUpdateTime"){
                //]Log.w(this.toString(), "addOnUpdateTime: "+"="+true );
                onUpdateTimes.add(onUpdateTimeAA);
            }
        }
        public void removeOnUpdateTime(OnUpdateTime onUpdateTimeAA){
            synchronized ("OnUpdateTime"){
                onUpdateTimes.remove(onUpdateTimeAA);

            }
        }
        /**
         *更新每一分钟
         */
        public void setNewMinuTime(){
           // titleTime.updateTimeNew();
           // updateTime();
        }
        /**
         *更新Time
         */
        public void setNewTime(){
            titleTime.updateTimeNew();
            updateTime();


           // timeConnect.updateTimeNew();
        }
        public void setNewsDay(){
            updateNewDay();
        }
        void updateNewDay() {
            synchronized ("OnUpdateTime"){
                TimeOD timeOD_A=titleTime.getTimeNew();
                //]Log.w(this.toString(), "updateNewDate: "+"="+onUpdateTimes.size() );
                for (int i = 0; i < onUpdateTimes.size(); i++) {
                    if(onUpdateTimes.get(i)!=null)
                    {
                        onUpdateTimes.get(i).onUpdateNewDay(timeOD_A);
                    }else {
                        onUpdateTimes.remove(i);
                        i--;
                    }

                }
            }

        }
        void updateTime() {
            synchronized ("OnUpdateTime"){
                TimeOD timeOD_A=titleTime.getTimeNew();
                //]Log.w(this.toString(), "updateTime: "+"="+onUpdateTimes.size() );
                for (int i = 0; i < onUpdateTimes.size(); i++) {
                    if(onUpdateTimes.get(i)!=null)
                    {
                        onUpdateTimes.get(i).onUpdateTime(timeOD_A);
                    }else {
                        onUpdateTimes.remove(i);
                        i--;
                    }

                }
            }

        }

    }
    public class CalendarConnect {
        FXDataPool.FXCalendar fxCalendar;
        ArrayList<OnUpdateCalendarData> onUpdateCalendarDataAL=new ArrayList<>();
        boolean boolUpdateTime;

        public CalendarConnect(FXDataPool.FXCalendar fxCalendar_A) {
            fxCalendar = fxCalendar_A;
        }

        public FXDataPool.FXCalendar.CalenderDData getDData( TimeOD timeOD_A){

            FXDataPool.FXCalendar.CalenderDData calenderDData_A=fxCalendar.getDData(timeOD_A);
            //]Log.w(this.toString(), "CalenderDDataGetDData:E0B"+"="+ timeOD_A.intsA[2]);
            if(calenderDData_A==null||calenderDData_A.calenderDayData.calenderDHData.size()==0)
                {
                    calenderDData_A=fxCalendar.getHttpData(timeOD_A);
                    //]Log.w(this.toString(), "CalenderDDataGetDData:E0C"+"="+calenderDData_A.calenderDayData.calenderDHData.size() +"="+calenderDData_A.calenderDayData.calenderEVData.size() );
                }
//            if(calenderDData_A==null)
//                {
//
//                }
//            if(calenderDData_A!=null)
//                {
//
//                }
            //]Log.w(this.toString(), "CalenderDDataGetDData:E0 "+"="+(calenderDData_A!=null) +"="+((calenderDData_A!=null)?calenderDData_A.calenderDayData.calenderDHData.size():"0") +"="+timeOD_A.intsA[0] +"-"+ timeOD_A.intsA[1] +"-"+ timeOD_A.intsA[2]);
            return calenderDData_A;

        }
//        public FXDataPool.FXCalendar.CalenderDData getHttpDData( TimeOD timeOD_A){
//            XMCalendarCollertTool.CalendarDH[] calendarDHS_A=collectTool.fxCalendarCollect.getCollectFXCalendarFirstDHData(timeOD_A.PuC_getTimeLong());
//            if(calendarDHS_A!=null)
//                {
//                    FXDataPool.FXCalendar.CalenderDData calenderDData_A=fxCalendar.new CalenderDData(timeOD_A);
//                    calenderDData_A.setData(calendarDHS_A);
//                    saveCalendarData(calenderDData_A);
//                    return calenderDData_A;
//
//                }else {
//                return null;
//            }
//
//
//        }
//        public void setData(XMCalendarCollertTool.CalendarDH[] calendarDHS_A, TimeOD timeOD_A){
//            isUpdataTime();
//            if(calendarDHS_A!=null)
//                {
//                    fxCalendar.setCalendarData(calendarDHS_A,timeOD_A);
//                    saveAllCalendarData();
//                    setOnUI(0);
//                }
//
//
//        }
        public void setData(Jin10CalendarCollectTool.CalendarJinDH[] calendarJinDHS_A,Jin10CalendarCollectTool.CalendarJinEV[] calendarJinEVS_A, TimeOD timeOD_A){
            isUpdataTime();
            boolean bool_A=false;
            if(calendarJinDHS_A!=null)
            {
                bool_A=true;
                CalendarDataClass[] calenderData_A= fxCalendar.setCalendarData(calendarJinDHS_A,timeOD_A);
                if(calenderData_A!=null)
                    {
                        //]Log.w(this.toString(), "updateOnUINum: E0"+"="+ calenderData_A.length);
                        updateOnUI(calenderData_A);
                    }
            }

            if(calendarJinEVS_A!=null)
            {
                bool_A=true;
                CalendarDataClass[] calenderData_B= fxCalendar.setCalendarEVData(calendarJinEVS_A,timeOD_A);
            }
            if(bool_A)
                {

                    saveAllCalendarData();
                    setOnUI(0);
                }


        }
//        public void updateData(XMCalendarCollertTool.CalendarCC[] calendarCCS_A, TimeOD timeOD_A){
//            isUpdataTime();
//            FXDataPool.FXCalendar.CalenderData[] calenderData_A =fxCalendar.updateCalendarData(calendarCCS_A,timeOD_A);
//            if(calenderData_A!=null)
//                {
//                    //saveAllCalendarData();
//                    updateOnUI(calenderData_A);
//                }
//
//
//        }
        public void saveAllCalendarData(){
            fxCalendar.saveAllCalensarData();
        }
        public void saveCalendarData(FXDataPool.FXCalendar.CalenderDData calenderDData_A){
            fxCalendar.saveFXCalensarData(calenderDData_A);
        }

        public void setBoolUpdateNewDateTime() {
            synchronized ("BoolUpdateCalendarTime") {
                boolUpdateTime = true;
            }
        }

        public boolean isUpdataTime() {
            synchronized ("BoolUpdateCalendarTime") {
                if (!boolUpdateTime) {
                    return false;
                } else {
                    updateNewsDataDay();
                    return true;
                }
            }
        }

        void updateNewsDataDay() {
            TimeOD timeOD_A = timeConnect.getTimeNew();
            fxCalendar.updateNewDayCalendarData();
            setOnUI(4);
        }

        void setOnUI(int intUI) {
            synchronized ("OnUpdateCalendarData"){
                //]Log.w(this.toString(), "OnUpdateCalendarData E0 "+"="+onUpdateCalendarDataAL.size() );
                for (int i = 0; i < onUpdateCalendarDataAL.size(); i++) {
                    if(onUpdateCalendarDataAL.get(i)!=null)
                    {
                        onUpdateCalendarDataAL.get(i).onSetCalendarData(intUI);
                    }else {
                        onUpdateCalendarDataAL.remove(i);
                        i--;
                    }

                }
            }

        }
        void updateOnUI(CalendarDataClass[] calenderData_A) {
            synchronized ("OnUpdateCalendarData"){
                for (int i = 0; i < onUpdateCalendarDataAL.size(); i++) {
                    if(onUpdateCalendarDataAL.get(i)!=null)
                    {
                        onUpdateCalendarDataAL.get(i).onUpdateCalendarData(calenderData_A);
                    }else {
                        onUpdateCalendarDataAL.remove(i);
                        i--;
                    }

                }
            }

        }
        public void addOnUpdateUI(OnUpdateCalendarData onUpdateCalendarData_A){
            synchronized ("OnUpdateCalendarData"){
                onUpdateCalendarDataAL.add(onUpdateCalendarData_A);
            }
        }
        public void removeOnUpdateUII(OnUpdateCalendarData onUpdateCalendarData_A){
            synchronized ("OnUpdateCalendarData"){
                onUpdateCalendarDataAL.remove(onUpdateCalendarData_A);

            }
        }
        public SnapCalendarDataTool getSnapCalendarDataTool(OnUpdateSnapCalendarData onUpdateSnapCalendarData_A){
            fxCalendar.snapCalendarDataTool.addOnUpdateSnapCalendarData(onUpdateSnapCalendarData_A);
            return fxCalendar.snapCalendarDataTool;
        }
        public void removeSnapCalendarDataTool(OnUpdateSnapCalendarData onUpdateSnapCalendarData_A){
            fxCalendar.snapCalendarDataTool.removeOnUpdateSnapCalendarData(onUpdateSnapCalendarData_A);
        }
    }
    public class FileIOConnect{
        MyFileIOTool fileIOTool;
        public FileIOConnect(MyFileIOTool fileIOToolAA){
            this.fileIOTool=fileIOToolAA;
        }
        public MyFileIOTool getFileIOTool(){
            return fileIOTool;
        }

    }
    public class ReadConnect{
        ReadModeTool readModeTool;
        public ReadConnect(){
            readModeTool=new ReadModeTool();
        }
        public ReadModeTool getReadModeTool(){
            return readModeTool;
        }
    }


}
