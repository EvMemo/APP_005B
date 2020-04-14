package com.example.app_005.Tool;

import android.view.View;

import com.example.app_005.Interface.OnGetLineTrendData;
import com.example.app_005.R;
import com.example.app_005.View.ColorDuoView;

public class LineTrendCenter {
    public LineData[] lineData;
    public int lineMode;
    public int LineNum;
    public int intPage;
    OnGetLineTrendData onGetLineTrendData;
    ColorDuoView colorDuoView;
    public  LineTrendCenter(OnGetLineTrendData onGetLineTrendData_A, View view_A)
    {
        colorDuoView=view_A.findViewById(R.id.fre001_color_duo_001);
        onGetLineTrendData=onGetLineTrendData_A;
        lineData=new LineData[26];
    }
    public void setIntMode(int int_A){
        switch(int_A){
            case 0://
                lineMode=0;
                break;
            case 1:
                lineMode=1;
                break;
            case 2:
                lineMode=2;
                break;
            default:
                break;
        }

    }
    public void setLinePage(int int_Page){
        switch(lineMode){
            case 0://
                lineMode=0;
                break;
            case 1:
                lineMode=1;
                break;
            case 2:
                lineMode=2;
                break;
            default:
                break;
        }
    }
    public void openLineTrend(int int_Num){

    }
    public void closeLineTrend(int int_Num){

    }
    public void setLineData(){

    }
    public void updateData(){
        onGetLineTrendData.OnGetLineTrendData();
    }
    public class LineData{
        public String stringName;
        public float[][] floatsData;
        public int intState;
        public ColorDuoView colorDuoView;
    }
}
