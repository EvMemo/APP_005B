package com.example.app_005.Class;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public   class  SkillFinacTool {
    public static SkillFinacName[] St_FinacNames;
    public static Context context;
    public SkillFinacTool(Context context_A){
        context=context_A;
        //St_FinacNames=new SkillFinacName[0];
        initSkillFinacData();
    }
    synchronized void  initSkillFinacData(){
        SharedPreferences sharedPreferences_A= context.getSharedPreferences("SkillFinacTool_001_SP", 0);
        boolean bool_A = sharedPreferences_A.getBoolean("SkillFinacTool_001_isData", false);
        St_FinacNames=null;
        if(bool_A)
            {
                int int_A=sharedPreferences_A.getInt("SkillFinacTool_001_hasNumData", 0);
                if(int_A!=0)
                    {
                        St_FinacNames=new SkillFinacName[int_A];
                        for(int i=0;i<int_A;i++)
                            {
                                String string_A=sharedPreferences_A.getString("SkillFinacTool_001_SkillFinacName_"+i, "Null");
                                St_FinacNames[i]=new SkillFinacName(i,string_A);
                            }

                    }

            }
        if(bool_A||(St_FinacNames==null))
            {
                St_FinacNames=new SkillFinacName[8];
                St_FinacNames[0]=new SkillFinacName(0,"USD");
                St_FinacNames[1]=new SkillFinacName(1,"EUR");
                St_FinacNames[2]=new SkillFinacName(2,"GBP");
                St_FinacNames[3]=new SkillFinacName(3,"JPY");
                St_FinacNames[4]=new SkillFinacName(4,"AUD");
                St_FinacNames[5]=new SkillFinacName(5,"CAD");
                St_FinacNames[6]=new SkillFinacName(6,"CHF");
                St_FinacNames[7]=new SkillFinacName(7,"NZD");

                SharedPreferences.Editor edit_A = sharedPreferences_A.edit();
                edit_A.putInt("SkillFinacTool_001_hasNumData", St_FinacNames.length);
                for(int i=0;i<St_FinacNames.length;i++)
                    {
                        edit_A.putString("SkillFinacTool_001_SkillFinacName_"+i, St_FinacNames[i].stringName);
                    }
                edit_A.putBoolean("SkillFinacTool_001_isData",true);
                edit_A.commit();

            }
        //USD,EUR,GBP,JPY,AUD,CAD,CHF,NZD//EURUSD(0),USD(1),GBPUSD(2),EURGBP(3),EURJPY(4),GBPJPY(5),USD(6),USDCAD(7),USDCHF(8),NZDUSD(9),EURCHF(10);



    }
    public synchronized static SkillFinacName addSkillFinacName(String stringName_A){
        for(int i=0;i<St_FinacNames.length;i++)
        {
            if(St_FinacNames[i].equals(stringName_A))
            {
                return null;

            }

        }
        SkillFinacName skillFinacName_A=new SkillFinacName(St_FinacNames.length,stringName_A);
        SkillFinacName[] skillFinacNames_A=new SkillFinacName[St_FinacNames.length+1];
        for(int i=0;i<skillFinacNames_A.length-1;i++)
            {
                skillFinacNames_A[i]=St_FinacNames[i];
            }
        skillFinacNames_A[skillFinacNames_A.length-1]=skillFinacName_A;
        St_FinacNames=skillFinacNames_A;
        saveAddSkillFinacName(St_FinacNames.length,skillFinacName_A.stringName);
        return skillFinacName_A;

    }
    public synchronized static void saveAddSkillFinacName(int intNumData_A,String stringName_A){
        SharedPreferences sharedPreferences_A= context.getSharedPreferences("SkillFinacTool_001_SP", 0);
        SharedPreferences.Editor edit_A = sharedPreferences_A.edit();
        edit_A.putInt("SkillFinacTool_001_hasNumData", intNumData_A);
        edit_A.putString("SkillFinacTool_001_SkillFinacName_"+(intNumData_A-1),stringName_A);
        edit_A.putBoolean("SkillFinacTool_001_isData",true);
        edit_A.commit();
    }
    public synchronized static SkillFinacName getSkillFinacName(int intID_A){
        return ccSkillFinacName(intID_A);
    }
    public synchronized static SkillFinacName getSkillFinacName(String stringName_A){
        return ccSkillFinacName(stringName_A);
    }
    public synchronized static SkillFinacName getSkillFinacName(int intID_A,String stringName_A){
        return ccSkillFinacName(intID_A);
    }
    static synchronized SkillFinacName ccSkillFinacName(int intID_A){
        if(intID_A>=0&&St_FinacNames.length>intID_A)
            {
                return St_FinacNames[intID_A];

            }
        return null;
    }

    synchronized static SkillFinacName ccSkillFinacName(String stringName_A){
        SkillFinacName skillFinacName_A=null;
        for(int i=0;i<St_FinacNames.length;i++)
            {
                if(St_FinacNames[i].equals(stringName_A))
                    {
                        return St_FinacNames[i];

                    }

            }
        return skillFinacName_A;
    }

    public static class SkillFinacName{
        public int intID;
        public String stringName;
        public SkillFinacName(int intID_A,String stringName_A){
            intID=intID_A;
            stringName=stringName_A;
        }
        public boolean isSameName(SkillFinacName skillFinacName_A){
            return (intID==skillFinacName_A.intID);

        }
    }

}
