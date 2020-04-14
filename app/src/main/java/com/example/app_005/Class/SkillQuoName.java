package com.example.app_005.Class;

public enum SkillQuoName {
    //"\"FX_IDC:EURUSD\",\"FX_IDC:USDJPY\",\"FX_IDC:GBPUSD\",\"FX_IDC:EURGBP\",\"FX_IDC:EURJPY\"," +
    // "\"FX_IDC:GBPJPY\",\"FX_IDC:AUDUSD\",\"FX_IDC:USDCAD\",\"FX_IDC:USDCHF\",\"FX_IDC:NZDUSD\",\"FX_IDC:EURCHF\"";
    EURUSD(0),USDJPY(1),GBPUSD(2),EURGBP(3),EURJPY(4),GBPJPY(5),AUDUSD(6),USDCAD(7),USDCHF(8),NZDUSD(9),EURCHF(10);
//    ,STOCH(11),STOCHRSI(12),MACD(13),ADX(14),Williams(15),
//    CCI(16),ATR(17),HighsLows(18),UltimateOscillator(19),ROC(20),BullBearPower(21),
//    MA1(22), MA5(23),MA10(24),MA15(25),MA30(26),MA60(27),
//    FXMX(50);
    public static String[] SkillName=new String[]{"EURUSD","USDJPY","GBPUSD","EURGBP","EURJPY","GBPJPY","AUDUSD","USDCAD","USDCHF","NZDUSD","EURCHF"};
    public static float[] SkillFloat=new float[]{0.00001F,0.001F,0.00001F,0.00001F,0.001F,0.001F,0.00001F,0.00001F,0.00001F,0.00001F,0.00001F};
    private int date;
    SkillQuoName(int i) {
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
    public static SkillQuoName getSkill(int intAA){
        for(SkillQuoName f: SkillQuoName.values())
            {
                if (f.getIndex()==intAA) {
                    return f;
                }
            }
        return null;

    }
    public float getFloat(){
        return SkillFloat[date];
    }

}
