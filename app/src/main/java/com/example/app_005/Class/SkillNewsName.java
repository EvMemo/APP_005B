package com.example.app_005.Class;

public enum SkillNewsName {
    DailyFX(0),FortradeAnalysis(1);
    public static String[] QuoName=new String[]{"DailyFX","FortradeAnalysis"};//,"EURCHF","USDCAD","AUDUSD","GBPJPY"};
    private int date;
    SkillNewsName(int i) {
        date=i;
    }
    // 普通方法  
    public static String getName(int index) {
        return QuoName[index];
    }
    public int getIndex(){
        return date;
    }
}
