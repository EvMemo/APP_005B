package com.example.app_005.Class;

public enum SkillLineNema {
   // "Recommend.All","Recommend.Other","Recommend.MA","RSI","Stoch.K","Stoch.D","Stoch.RSI.K","Stoch.RSI.D","MACD.macd","MACD.signal",
   // "ADX","ADX+DI","ADX-DI","W.R","CCI20","ROC","BBPower","Rec.BBPower","AO","Mom"
   //"ATR","UO"
   //"EMA5|1""SMA5|1","EMA10|1","SMA10|1","EMA20|1","SMA20|1","EMA30|1","SMA30|1","EMA50|1","SMA50|1","EMA100|1","SMA100|1","EMA200|1","SMA200|1",
   // "Rec.Ichimoku|1","Ichimoku.BLine|1","Rec.VWMA|1","VWMA|1","Rec.HullMA9|1","HullMA9|1","Pivot.M.Classic.S3|1","Pivot.M.Classic.S2|1","Pivot.M.Classic.S1|1","Pivot.M.Classic.Middle|1","Pivot.M.Classic.R1|1","Pivot.M.Classic.R2|1","Pivot.M.Classic.R3|1","Pivot.M.Fibonacci.S3|1","Pivot.M.Fibonacci.S2|1","Pivot.M.Fibonacci.S1|1","Pivot.M.Fibonacci.Middle|1","Pivot.M.Fibonacci.R1|1","Pivot.M.Fibonacci.R2|1","Pivot.M.Fibonacci.R3|1","Pivot.M.Camarilla.S3|1","Pivot.M.Camarilla.S2|1","Pivot.M.Camarilla.S1|1","Pivot.M.Camarilla.Middle|1","Pivot.M.Camarilla.R1|1","Pivot.M.Camarilla.R2|1","Pivot.M.Camarilla.R3|1","Pivot.M.Woodie.S3|1","Pivot.M.Woodie.S2|1","Pivot.M.Woodie.S1|1","Pivot.M.Woodie.Middle|1","Pivot.M.Woodie.R1|1","Pivot.M.Woodie.R2|1","Pivot.M.Woodie.R3|1","Pivot.M.Demark.S1|1","Pivot.M.Demark.Middle|1","Pivot.M.Demark.R1|1"
   Recommend_All(0),Recommend_Other(1),Recommend_MA(2),RSI(3),Stoch(4),
    Stoch_RSI(5),MACD(6),ADX(7),W_R(8),CCI20(9),ROC(10),BBPower(11),
    AO(12), Mom(13),ATR(14),UO(15),
    SMA(16),EMA(17),Ichimoku(18),VWMA(19),HullMA9(20),
    PivotM_Classic(21),PivotM_Fibonacci(22),PivotM_Camarilla(23),PivotM_Woodie(24),PivotM_Demark(25);
    private int date;
    public static String[] SkillName=null;
    SkillLineNema(int i) {
        date=i;
    }
    String getName(int index) {
        return SkillName[index];
    }
    public String getName(){
        return getName(date);
    }
    public int getIndex(){
        return date;
    }
    public static SkillLineNema getSkill(int intAA){
        for(SkillLineNema f: SkillLineNema.values())
        {
            if (f.getIndex()==intAA) {
                return f;
            }
        }
        return null;

    }
}
