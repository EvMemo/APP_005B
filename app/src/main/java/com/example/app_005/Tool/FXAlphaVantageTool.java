package com.example.app_005.Tool;

import android.util.JsonToken;
import android.util.Log;
import android.widget.TabHost;

import com.example.app_005.Class.SkillQuoName;

import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

public class FXAlphaVantageTool {
    String[] stringsID=new String[]{"EO2W3CNE7JU5RE4E","Z17H0PA6K9ICHNDN","9YG4I3GYRBAYHA08","9YG4I3GYRBAYHA08","OBD0SKAK68YG6B75"};
    String stringUrl="";
    FXDataConnect.QuoConnect quoConnect;
    public FXAlphaVantageTool(FXDataConnect.QuoConnect quoConnect_A){
        quoConnect=quoConnect_A;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   quoConnect.assignQuoHistoryData(getAlpVanHtHistoryData());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /**
     *历史数据
     */
    public QuoHistoryData getAlpVanHtHistoryData() throws InterruptedException {
        QuoHistoryData quoHistoryData_rt=new QuoHistoryData();
        FXDataPool.FXQuo.QuoDayData[] quoDayData_A=new FXDataPool.FXQuo.QuoDayData[11];
        String[][] stringsQuoName=new String[11][];
        stringsQuoName[0]=new String[]{"EUR","USD"};
        stringsQuoName[1]=new String[]{"USD","JPY"};
        stringsQuoName[2]=new String[]{"GBP","USD"};
        stringsQuoName[3]=new String[]{"EUR","GBP"};
        stringsQuoName[4]=new String[]{"USD","CHF"};
        stringsQuoName[5]=new String[]{"EUR","JPY"};
        stringsQuoName[6]=new String[]{"EUR","CHF"};
        stringsQuoName[7]=new String[]{"USD","CAD"};
        stringsQuoName[8]=new String[]{"AUD","USD"};
        stringsQuoName[9]=new String[]{"GBP","JPY"};
        stringsQuoName[10]=new String[]{"EUR","USD"};
        int int_A=0;
        long long_A=System.currentTimeMillis();
        float[][][] floats_Data=new float[11][][];
       for(int i=0;i<11;i++)
           {
               String string_A="https://www.alphavantage.co/query?function=FX_INTRADAY&from_symbol="+stringsQuoName[i][0]+"&to_symbol="+stringsQuoName[i][1]+"&interval=1min&outputsize=full&apikey="+stringsID[int_A];
               //]Log.w(this.toString(), "getAlpVanHtHistoryDataV2: "+"="+(string_A) );
               String string_Data=getHtml(string_A);
               ////]Log.w(this.toString(), "getAlpVanHtHistoryDataV1: "+"="+(string_Data!=null) );
               //]Log.w(this.toString(), "getAlpVanHtHistoryDataV0: "+"="+int_A +"="+ string_Data.length());
                if(string_Data.length()>500 )
                    {

                        quoHistoryData_rt.historyData[i]=ccAlpVanHtHistoryData(SkillQuoName.getSkill(i),string_Data);
                    }else {
                    int_A++;
                    i--;
                    Thread.sleep(60000);
                }
           }

        ////]Log.w(this.toString(), "getAlpVanHtHistoryDataV0: "+"="+ (System.currentTimeMillis()-long_A));
       return quoHistoryData_rt;

    }
    HistoryData ccAlpVanHtHistoryData(SkillQuoName skillQuoName,String string_Data){

        StringBuilder stringBuilder=new StringBuilder(string_Data);
        int int_A;
        int int_B;
        TimeOD timeOD_A=new TimeOD(System.currentTimeMillis());
        int_A=stringBuilder.indexOf("Refreshed\": \"");
       // //]Log.w(this.toString(), "ccAlpVanHtHistoryDataF0: "+"="+ timeOD_A.intsA[0]+"="+ timeOD_A.intsA[1]+"="+ timeOD_A.intsA[2]+"="+ timeOD_A.intsA[3]+"="+ timeOD_A.intsA[4]);
        int_A+=12;
        timeOD_A.intsA[0]=Integer.valueOf(stringBuilder.subSequence(int_A+1,int_A+5).toString());
        timeOD_A.intsA[1]=Integer.valueOf(stringBuilder.subSequence(int_A+6,int_A+8).toString())-1;
        timeOD_A.intsA[2]=Integer.valueOf(stringBuilder.subSequence(int_A+9,int_A+11).toString());
        timeOD_A.intsA[3]=Integer.valueOf(stringBuilder.subSequence(int_A+12,int_A+14).toString());
        timeOD_A.intsA[4]=Integer.valueOf(stringBuilder.subSequence(int_A+15,int_A+17).toString());
        Calendar calendar_A=Calendar.getInstance();
        calendar_A.setTimeInMillis(timeOD_A.PuC_getTimeLong());
        calendar_A.add(Calendar.HOUR_OF_DAY,8);
        timeOD_A=new TimeOD(calendar_A.getTimeInMillis());
       // //]Log.w(this.toString(), "ccAlpVanHtHistoryDataF1: "+"="+ timeOD_A.intsA[0]+"="+ timeOD_A.intsA[1]+"="+ timeOD_A.intsA[2]+"="+ timeOD_A.intsA[3]+"="+ timeOD_A.intsA[4]);
        int_A=stringBuilder.indexOf("min)\": {");
        int_A+=9;
        float[][] floats_rt=new float[1440][];
        boolean bool_A=true;
        int int_C=0;
        while (bool_A){
            //int_C++;
//            if(int_C==10)
//                {
//                break;
//                }
            int_A=stringBuilder.indexOf("\"",int_A);
            if(int_A==-1)
                {
                    break;
                }
            int_B=stringBuilder.indexOf("}",int_A);
            floats_rt[int_C]=(ccHistoryStringData(stringBuilder.substring(int_A,int_B)));
            int_A=int_B;
            int_C++;
        }
        ////]Log.w(this.toString(), "ccAlpVanHtHistoryDataD0: "+"="+floats_A.size());
        for(int i=0;i<10;i++)
            {
               // //]Log.w(this.toString(), "ccAlpVanHtHistoryDataF0: "+"="+floats_rt[i][0]+":"+floats_rt[i][1]+"="+floats_rt[i][2]);
            }

        return new HistoryData(skillQuoName,timeOD_A,floats_rt);

    }
    float[] ccHistoryStringData(String stringBuilder){
       // //]Log.w(this.toString(), "ccHistoryStringDataB0: "+"="+ stringBuilder);
        float[] floats_rt=new float[6];
        int int_A;
        int int_B;
        int_A=12;

        floats_rt[0]=Float.valueOf(stringBuilder.subSequence(int_A,int_A+2).toString());
        int_A+=3;

        floats_rt[1]=Float.valueOf(stringBuilder.subSequence(int_A,int_A+2).toString());
        int_A=stringBuilder.indexOf("en\": \"",int_A);
        int_B=stringBuilder.indexOf("\"",int_A+6);
        ////]Log.w(this.toString(), "ccHistoryStringDataJ0: "+"="+int_A +"="+int_B );
       // //]Log.w(this.toString(), "ccHistoryStringDataJ0: "+"="+stringBuilder.subSequence(int_A+6,int_B).toString());
        floats_rt[2]=Float.valueOf(stringBuilder.subSequence(int_A+6,int_B).toString());
        int_A=stringBuilder.indexOf("gh\": \"",int_A);
        int_B=stringBuilder.indexOf("\"",int_A+6);
        floats_rt[3]=Float.valueOf(stringBuilder.subSequence(int_A+6,int_B).toString());
        int_A=stringBuilder.indexOf("ow\": \"",int_A);
        int_B=stringBuilder.indexOf("\"",int_A+6);
        floats_rt[4]=Float.valueOf(stringBuilder.subSequence(int_A+6,int_B).toString());
        int_A=stringBuilder.indexOf("se\": \"",int_A);
        int_B=stringBuilder.indexOf("\"",int_A+6);
        floats_rt[5]=Float.valueOf(stringBuilder.subSequence(int_A+6,int_B).toString());
       // //]Log.w(this.toString(), "ccHistoryStringDataB1: "+"="+floats_rt[0]+"="+floats_rt[1]+"="+floats_rt[2]+"="+floats_rt[3]+"="+floats_rt[4]+"="+floats_rt[5]);
        return floats_rt;
    }
    class QuoHistoryData{

        HistoryData[] historyData;
        public QuoHistoryData(){
            historyData=new HistoryData[11];
        }

    }
    class HistoryData{
        public SkillQuoName skillQuoName;
        public TimeOD timeOD;
        public float[][] floatsData;
        public HistoryData(SkillQuoName skillQuoName_A,TimeOD timeOD_A,float[][] floats_Data){
            skillQuoName=skillQuoName_A;
            timeOD=timeOD_A;
            floatsData=floats_Data;
        }
    }
    /**
     * 获取静态网页源码
     *
     * @param path the path
     * @return the html
     * @throws Exception the exception
     */
    public String getHtml(String path){
        String html=null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取html数据
            byte[] data = readInputStream(inStream);//得到html的二进制数据
            html = new String(data, "UTF-8");
        } catch (Exception e) {
            //]Log.w(this.toString(),"AlpVangetHtml="+ e.toString());
            e.printStackTrace();
        }
        return html;
    }
    /**
     * 转换InputStream数据
     *
     * @param inStream the in stream
     * @return the byte [ ]
     * @throws Exception the exception
     */
    private byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
