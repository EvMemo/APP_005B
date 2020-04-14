package com.example.app_005.Tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app_005.Class.SkillQuoName;
import com.example.app_005.Interface.OnUpdateQuoData;
import com.example.app_005.Interface.OnUpdateTime;
import com.example.app_005.MainApplication;
import com.example.app_005.R;
import com.example.app_005.View.WinTrendView;

import java.util.ArrayList;

public class WindowSystemCenter implements OnUpdateQuoData, OnUpdateTime {
    Context context;
    FXDataConnect.QuoConnect quoConnect;
    View viewWindow;
    View[][] views;
    public WindowSystemCenter(Context contextAA,FXDataConnect.QuoConnect quoConnectAA){
        context=contextAA;
        quoConnect=quoConnectAA;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//第一个参数为xml文件中view的id，第二个参数为此view的父组件，可以为null，android会自动寻找它是否拥有父组件
        viewWindow = inflater.inflate(R.layout.one_window_view, null);
        views=new View[5][];
    }
    void initButtonSwitch(View viewAA){
//       ImageView imageView_A=viewAA.findViewById(R.id.image_one_win_00);
//       Button button_A=viewAA.findViewById(R.id.button_one_win_00);
//        views[0]=new View[]{imageView_A,button_A};
    }
    void initSelectedView(View viewAA){
//        View view_A=viewAA.findViewById(R.id.linear_one_win_01);
//        Button[] buttons_A=new Button[4];
//        buttons_A[0]=viewAA.findViewById(R.id.button_one_win_01);
//        buttons_A[1]=viewAA.findViewById(R.id.button_one_win_02);
//        buttons_A[2]=viewAA.findViewById(R.id.button_one_win_03);
//        buttons_A[3]=viewAA.findViewById(R.id.button_one_win_04);
//        views[1]=new View[]{view_A,buttons_A[0],buttons_A[1],buttons_A[2],buttons_A[3]};
    }
    void initTrendView(View viewAA){
//        View view_A=viewAA.findViewById(R.id.scroll_one_win_01);
//        views[2]=new View[]{view_A};
    }

    @Override
    public void onUpdateQuoUI(int intUI) {

    }

    @Override
    public void onUpdateTime(TimeOD timeODAA) {

    }

    @Override
    public void onUpdateNewDay(TimeOD timeODAA) {

    }

    public class WinTrendPool implements OnUpdateQuoData, OnUpdateTime {
        TimeOD timeOD;
        TimeOD timeODLast;
        FXDataConnect.QuoConnect quoConnect;
        WinTrendAdapter[] winTrendAdapters;
        WinTrendData[] winTrendDatas;
       LinearLayout linearLayout;
       Context context;
       Button buttonNest;
       int intNestMax,intNestNum;
        public WinTrendPool(Context context, TimeOD timeOD, LinearLayout linearLayout){
            this.quoConnect=((MainApplication)context.getApplicationContext()).fxDataConnect.getQuoConnect(this);
            this.timeOD=timeOD;
            this.linearLayout=linearLayout;
            this.context=context;
            initData();
            initWinTrendData();
            initWinTrendAdapter(this.linearLayout);

        }
        void initData(){

        }
        void initWinTrendData(){
//            winTrendDatas=new WinTrendData[10];
//            boolean bool_A=false;
//            int intLast_A=-1;
//            int[][] ints_A=new int[60][2];
//            ints_A[59][0]=timeOD.intsA[3];
//            ints_A[59][1]=timeOD.intsA[4];
//            for(int i=58;i>=0;i--)
//                {
//                    ints_A[i][1]=(ints_A[i+1][1]-1);
//                    if(ints_A[i][1]<0)
//                        {
//                            ints_A[i][1]=59;
//                            ints_A[i][0]=(ints_A[i+1][0]-1);
//                            if(ints_A[i][0]<0)
//                                {
//                                    ints_A[i][0]=23;
//                                    intLast_A=i;
//                                }
//                        }else {
//                        ints_A[i][0]=(ints_A[i+1][0]);
//                    }
//
//
//                }
            TimeOD timeOD_A=timeOD.PuC_getCrTimeOD(-1);
            for(int i=0;i<winTrendDatas.length;i++)
            {
                SkillQuoName skillQuoName_A=SkillQuoName.getSkill(i);
                FXDataPool.FXQuo.QuoDData quoDData_A=quoConnect.getFXQuoData(skillQuoName_A,timeOD);
                FXDataPool.FXQuo.QuoDData quoDData_B=quoConnect.getFXQuoData(skillQuoName_A,timeOD_A);
                winTrendDatas[i]=new WinTrendData(skillQuoName_A,timeOD);
                winTrendDatas[i].setData(quoDData_A,quoDData_B);

            }
        }
        void initWinTrendAdapter(LinearLayout linearLayoutAA){
//            LinearLayout linearLayout_A=linearLayoutAA.findViewById(R.id.linear_win_trend_02);
//            winTrendAdapters=new WinTrendAdapter[5];
//
//            for(int i=0;i<winTrendAdapters.length;i++)
//                {
//                    View view_A=View.inflate(context, R.layout.win_trend__one_view, null);
//                    WinTrendView winTrendView_A=view_A.findViewById(R.id.winTrendView_0);
//                    TextView[] textViews_A=new TextView[6];
//                    textViews_A[0]=view_A.findViewById(R.id.textView_one_win_01);
//                    textViews_A[1]=view_A.findViewById(R.id.textView_one_win_02);
//                    textViews_A[2]=view_A.findViewById(R.id.textView_one_win_03);
//                    textViews_A[3]=view_A.findViewById(R.id.textView_one_win_04);
//                    textViews_A[4]=view_A.findViewById(R.id.textView_one_win_05);
//                    textViews_A[5]=view_A.findViewById(R.id.textView_one_win_06);
//                    winTrendAdapters[i]=new WinTrendAdapter(winTrendView_A,textViews_A);
//                    linearLayout_A.addView(view_A);
//                }
        }
        void initWinSelecterView(LinearLayout linearLayoutAA){
//            intNestNum=0;
//            buttonNest=linearLayoutAA.findViewById(R.id.button_win_trend_nest_01);
//            buttonNest.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    intNestNum++;
//                    if(intNestMax>intNestNum)
//                        {
//                            intNestNum=0;
//                        }
//                }
//            });
        }
        public void updateViewData(TimeOD timeODAA){
            if(timeOD.PuC_isTimeODSameDay(timeODAA))
                {
                    for(int i=0;i<winTrendDatas.length;i++)
                        {
                            winTrendDatas[i]. setTime(timeODAA);
                        }

                }else {
                updateWinTrendData(timeODAA);
            }
            this.timeOD=timeODAA;
        }
        void updateWinTrendData(TimeOD timeODAA){
            TimeOD timeOD_A=timeODAA.PuC_getCrTimeOD(-1);
            for(int i=0;i<winTrendDatas.length;i++)
            {
                FXDataPool.FXQuo.QuoDData quoDData_A=quoConnect.getFXQuoData(winTrendDatas[i].skillQuoName,timeODAA);
                FXDataPool.FXQuo.QuoDData quoDData_B=quoConnect.getFXQuoData(winTrendDatas[i].skillQuoName,timeOD_A);
                winTrendDatas[i]. setData(timeODAA,quoDData_A,quoDData_B);
            }
        }
        public void StartView(){

        }
        @Override
        public void onUpdateQuoUI(int intUI) {

        }

        @Override
        public void onUpdateTime(TimeOD timeODAA) {

        }

        @Override
        public void onUpdateNewDay(TimeOD timeODAA) {

        }

        public class WinTrendAdapter{
            WinTrendView winTrendView;
            TextView[] textViews;
            WinTrendData winTrendData;
            public WinTrendAdapter(WinTrendView winTrendView,TextView[] textViews){
                this.winTrendView=winTrendView;
                this.textViews=textViews;
            }
            public void setTrendData(WinTrendData winTrendData){
                this.winTrendData=winTrendData;
            }
            public void updateTrendView(){
                ArrayList<float[]> floats_A=winTrendData.floatsNumA;
            }
        }
        public class WinTrendData{
            public TimeOD timeOD;
            public SkillQuoName skillQuoName;
            public float flMax,flMin,flStr;
            public ArrayList<float[]> floats;
            //public float[][] floatsNum;
            public ArrayList<float[]>floatsNumA;
            public FXDataPool.FXQuo.QuoDData quoDDataA,quoDDataB;
            public WinTrendData(SkillQuoName skillQuoName, TimeOD timeOD){
                this.skillQuoName=skillQuoName;
                this.timeOD=timeOD;
                floatsNumA=new ArrayList<>();
            }
            public void setData(FXDataPool.FXQuo.QuoDData quoDDataAA, FXDataPool.FXQuo.QuoDData quoDDataBB){
                quoDDataA=quoDDataAA;
                quoDDataB=quoDDataBB;
                calculateData();
            }
            public void setData(TimeOD timeODAA, FXDataPool.FXQuo.QuoDData quoDDataAA, FXDataPool.FXQuo.QuoDData quoDDataBB){
                this.timeOD=timeODAA;
                setData(quoDDataAA,quoDDataBB);
                calculateData();
            }
            public void calNewDayData(TimeOD timeODAA, FXDataPool.FXQuo.QuoDData quoDDataAA, FXDataPool.FXQuo.QuoDData quoDDataBB){
                timeOD=timeODAA;
                quoDDataA=quoDDataAA;
                quoDDataB=quoDDataBB;
                initTrendData();
            }
            public boolean addMinu(int intMinuAA){
                int int_H=timeOD.intsA[3];
                int int_M=timeOD.intsA[4];
                int_M+=intMinuAA;
                while (int_M>59){
                    int_M-=59;
                    int_H++;
                }
                if(int_H>23)
                    {
                    return false;
                    }
                addTrendData(int_H,int_M);
                return true;

            }
            public boolean setTime(TimeOD timeODAA){

                int intH=timeOD.intsA[3];
                int intM=timeOD.intsA[4];
                int int_A=0;
                intH=timeOD.intsA[3]-intH;
                if(intH>0)
                    {
                        int_A+=(intH*60);
                    }
                intM=timeOD.intsA[4]-intM;
                int_A+=intM;
                if(int_A>=20)
                    {
                        initTrendData();
                    }else {
                    addMinu(int_A);
                }
                timeOD=timeODAA;
                return true;
            }
            void initTrendData() {
                boolean boolLast_A = false;
                int intH = timeOD.intsA[3];
                int intM = timeOD.intsA[4];
                int int_A = timeOD.intsA[4] % 5;
                floatsNumA=new ArrayList<>();
                int intH2 = timeOD.intsA[3];
                int intM2 = timeOD.intsA[4] - (59 - (4 - int_A));
                if (intM2 < 0) {
                    intM2 = 60 - intM2;
                    intH2--;
                    if (intH2 < 0) {
                        intH2 = 23;
                        boolLast_A = true;
                    }
                }
                float[] floats_B;
                float flMax_A=-1;
                float flMin_A=-1;
                float flStr_A=-1;
                float flEnd_A=-1;
                if (boolLast_A) {
                    FXDataPool.FXQuo.QuoDData quoDData_A=quoDDataB;
                    for (int i = 0; i < 11; i++) {
                        flMax_A=-1;
                        flMin_A=-1;
                        flStr_A=-1;
                        flEnd_A=-1;
                        for (int e = 0; e < 5; e++) {
                            if (intM2 >= 60) {
                                intM2 = 0;
                                intH2++;
                                if(intH2>23)
                                    {
                                        intH2=0;
                                        quoDData_A=quoDDataA;
                                    }
                            }
                            floats_B = quoDData_A.PuC_getData(intH2, intM2);
                            if(floats_B!=null)
                            {
                                if(flStr_A==-1){
                                    flMax_A=floats_B[0];
                                    flMin_A=floats_B[0];
                                    flStr_A=floats_B[0];
                                    flEnd_A=floats_B[0];
                                }else {
                                    if(flMax_A<floats_B[0])flMax_A=floats_B[0];
                                    if(flMin_A>floats_B[0])flMin_A=floats_B[0];
                                    flEnd_A=floats_B[0];
                                }
                            }

                            intM2++;
                        }
                        floatsNumA.add(new float[]{flMax_A,flMin_A,flStr_A,flEnd_A});

                    }
                    flMax_A=-1;
                    flMin_A=-1;
                    flStr_A=-1;
                    flEnd_A=-1;
                    for (int i = 0; i < 5 - (4 - int_A); i++) {
                        if (intM2 >= 60) {
                            intM2 = 0;
                            intH2++;
                            if(intH2>23)
                            {
                                intH2=0;
                                quoDData_A=quoDDataA;
                            }
                        }
                        floats_B = quoDData_A.PuC_getData(intH2, intM2);
                        if(floats_B!=null)
                        {
                            if(flStr_A==-1){
                                flMax_A=floats_B[0];
                                flMin_A=floats_B[0];
                                flStr_A=floats_B[0];
                                flEnd_A=floats_B[0];
                            }else {
                                if(flMax_A<floats_B[0])flMax_A=floats_B[0];
                                if(flMin_A>floats_B[0])flMin_A=floats_B[0];
                                flEnd_A=floats_B[0];
                            }
                        }

                        intM2++;
                    }
                    floatsNumA.add(new float[]{flMax_A,flMin_A,flStr_A,flEnd_A});


                } else {

                    for (int i = 0; i < 11; i++) {
                        flMax_A=-1;
                        flMin_A=-1;
                        flStr_A=-1;
                        flEnd_A=-1;
                        for (int e = 0; e < 5; e++) {
                            if (intM2 >= 60) {
                                intM2 = 0;
                                intH2++;
                            }
                            floats_B = quoDDataA.PuC_getData(intH2, intM2);
                            if(floats_B!=null)
                                {
                                    if(flStr_A==-1){
                                        flMax_A=floats_B[0];
                                        flMin_A=floats_B[0];
                                        flStr_A=floats_B[0];
                                        flEnd_A=floats_B[0];
                                    }else {
                                        if(flMax_A<floats_B[0])flMax_A=floats_B[0];
                                        if(flMin_A>floats_B[0])flMin_A=floats_B[0];
                                        flEnd_A=floats_B[0];
                                    }
                                }

                            intM2++;
                        }
                        floatsNumA.add(new float[]{flMax_A,flMin_A,flStr_A,flEnd_A});

                    }
                    flMax_A=-1;
                    flMin_A=-1;
                    flStr_A=-1;
                    flEnd_A=-1;
                    for (int i = 0; i < 5 - (4 - int_A); i++) {
                        if (intM2 >= 60) {
                            intM2 = 0;
                            intH2++;
                        }
                        floats_B = quoDDataA.PuC_getData(intH2, intM2);
                        if(floats_B!=null)
                        {
                            if(flStr_A==-1){
                                flMax_A=floats_B[0];
                                flMin_A=floats_B[0];
                                flStr_A=floats_B[0];
                                flEnd_A=floats_B[0];
                            }else {
                                if(flMax_A<floats_B[0])flMax_A=floats_B[0];
                                if(flMin_A>floats_B[0])flMin_A=floats_B[0];
                                flEnd_A=floats_B[0];
                            }
                        }

                        intM2++;
                    }
                    floatsNumA.add(new float[]{flMax_A,flMin_A,flStr_A,flEnd_A});


                }
            }
            void addTrendData(int intHAA,int intMAA){
                int int_H=timeOD.intsA[3];
                int int_M=timeOD.intsA[4];
                int int_A= timeOD.intsA[4] % 5;
                int int_A2=11;
                int int_E=int_M+1;
                int int_E2=intMAA;
                for(int i=int_H;i<=intHAA;i++)
                    {
                        for(int e=int_E;e<60;)
                        {
                            if(i==intHAA&&int_E==int_E2){
                                break;
                            }
                            e++;
                            int_A++;
                            if(int_A>4)
                                {
                                floatsNumA.remove(0);
                                floatsNumA.add(new float[]{-1,-1,-1,-1});
                                }
                            float[] float_C=quoDDataA.PuC_getData(i,e);
                            if(float_C!=null)
                                {
                                if(floatsNumA.get(11)[0]==-1)
                                    {
                                        floatsNumA.set(11,new float[]{float_C[0],float_C[0],float_C[0],float_C[0]});
                                    }else {
                                    if(floatsNumA.get(11)[0]<float_C[0])floatsNumA.get(11)[0]=float_C[0];
                                    if(floatsNumA.get(11)[1]>float_C[0])floatsNumA.get(11)[1]=float_C[0];
                                    floatsNumA.get(11)[3]=float_C[0];
                                }
                                }

                        }
                        int_E=0;
                    }

            }

            /**
             *计算
             */
            void calculateData(){



            }


        }
    }
}
