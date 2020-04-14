package com.example.app_005.Tool;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Interface.OnReadNews;
import com.example.app_005.R;

import java.util.ArrayList;

public class ReadModeTool {
    public ReadModeNews readModeNews;
    public ReadModeTool(){
        readModeNews=new ReadModeNews();
    }
    public class ReadModeNews{
        public ArrayList<ReadNewsData> readNewsData;
        public ArrayList<ReadNewsData> readNewsDataB;
        public boolean boolDataOK;
        public ArrayList<OnReadNews> onReadNews;

        Handler handlerUI;
        boolean boolHandleUI;
        public int intNewNum;
        public ReadModeNews(){
            readNewsData=new ArrayList<>();
            readNewsDataB=new ArrayList<>();
            onReadNews=new ArrayList<>();
            intNewNum=0;
            handlerUI = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0://更换临时数据
                            synchronized ("RMTool.boolHandleUI") {
                                if (boolHandleUI) {
                                    boolHandleUI = false;
                                } else {
                                    return;
                                }
                                setNewsDataClass();
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
        public void reqHandleUI(){
            synchronized ("RMTool.boolHandleUI") {
                if (!boolHandleUI) {
                    boolHandleUI = true;
                    handlerUI.sendEmptyMessage(0);
                }
            }

        }
        public void addNewsDataClass(NewsDataClass[] newsDataClasses_A){
            if(newsDataClasses_A!=null)
                {
                    ArrayList<ReadNewsData> readNewsData_A=new ArrayList<>();
                    for(int i=0;i<newsDataClasses_A.length;i++)
                        {
                            readNewsData_A.add(new ReadNewsData(newsDataClasses_A[i]));
                        }
                    synchronized ("RMTool.newsDataClassesB"){
                        readNewsDataB.addAll(readNewsData_A);

                    }
                    reqHandleUI();
                }



        }
        void setNewsDataClass(){
            synchronized ("RMTool.newsDataClassesB"){
                if(readNewsDataB!=null||readNewsDataB.size()!=0)
                    {
                    readNewsData.addAll(readNewsDataB);
                    ReadNewsData[] readNewsData_A=readNewsDataB.toArray(new ReadNewsData[readNewsDataB.size()]);
                    readNewsDataB=new ArrayList<>();
                    updateNewData(readNewsData_A);
                    }

            }
        }
        public void ccReadNewData(){
            for(int i=0;i<readNewsData.size();i++)
                {
                if(readNewsData.get(i).boolDrewNew=true)
                    {
                        readNewsData.get(i).boolDrewNew=false;
                    }
                }

        }

        public void addOnReadNews(OnReadNews onReadNews_A) {
            synchronized ("RMTool.onReadNews") {
                onReadNews.add(onReadNews_A);
                boolDataOK=true;

            }
        }
        public void removeOnReadNews(OnReadNews onReadNews_A){
            synchronized ("RMTool.onReadNews") {
                onReadNews.remove(onReadNews_A);
                if(onReadNews.size()==0)
                    {
                    boolDataOK=false;
                    }
            }
        }
        public void updateNewData(ReadNewsData[] readNewsData_A){
            synchronized ("RMTool.onReadNews") {
                //]Log.w(this.toString(), "updateRMNewData: R0"+"="+ readNewsData_A.length);
                for(int i=0;i<onReadNews.size();i++)
                    {
                        onReadNews.get(i).updateData(readNewsData_A);
                    }

            }
        }
        public class ReadNewsData{
            public NewsDataClass newsDataClass;
            public boolean boolNew;
            public boolean boolDrewNew;
            public ReadNewsData(NewsDataClass newsDataClass_A){
                intNewNum++;
                newsDataClass=newsDataClass_A;
                boolNew=true;
                boolDrewNew=false;
            }
            public int setBoolNewFalse(){
                intNewNum--;
                boolNew=false;
                boolDrewNew=true;
                return intNewNum;
            }
        }

    }
}
