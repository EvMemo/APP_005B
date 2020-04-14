package com.example.app_005.Tool;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.example.app_005.Class.SkillNewsName;
import com.example.app_005.Class.SkillQuoName;
import com.example.app_005.Service.MyFXDataService;

import org.w3c.dom.Document;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static android.support.constraint.Constraints.TAG;

public class MyFileIOTool {

    Context contextA;
    //DocumentBuilderFactory factory;//创建DOM解析器工厂
   // DocumentBuilder documentBuilder;////创建DOM解析器

    public MyFileIOTool(Context contextAA){
        contextA=contextAA;
        String string_A=contextA.getExternalFilesDir("NewsDData").getPath();
        File file_NewsDData=new File(string_A);
        if(!file_NewsDData.exists())
            {
                file_NewsDData.mkdirs();
            }
        string_A=contextA.getExternalFilesDir("CalenderDData").getPath();
        file_NewsDData=new File(string_A);
        if(!file_NewsDData.exists())
        {
            file_NewsDData.mkdirs();
        }


    }
    /**
     *调查是否有对应JSON文件
     */
   public  File PuC_isJSONFile(String stringA){


        File file_A=new File(stringA);
        if(!file_A.exists())
        {
            //file_A.mkdirs();
            return null;
        }else {
            return file_A;
        }
    }

    /**
     * 获取Quo报价JSON文件名
     *
     * @param timeODAA the time odaa
     * @return the string
     */
    public String getJSONQuoFileName(SkillQuoName skillQuoNameAA, TimeOD timeODAA){
        String string_A=contextA.getExternalFilesDir("Quote").getPath();
        string_A+="/"+skillQuoNameAA.getName();

        return string_A+="/"+timeODAA.intsA[0]+"/"+(timeODAA.intsA[1]+1)+"/"+timeODAA.intsA[2]+".json";
    }
    /**
     * 获取Quo指标JSON文件名
     *
     * @param timeODAA the time odaa
     * @return the string
     */
    public String getJSONQuoLineFileName(SkillQuoName skillQuoNameAA, SkillQuoName skillQuoNameBB,TimeOD timeODAA){
        String string_A=contextA.getExternalFilesDir("QuoLine").getPath();
        string_A+="/"+skillQuoNameBB.getName();
        string_A+="/"+skillQuoNameAA.getName();
        return string_A+="/"+timeODAA.intsA[0]+"/"+(timeODAA.intsA[1]+1)+"/"+timeODAA.intsA[2]+".json";
    }
    /**
     * 获取News新闻JSON文件名
     *
     * @param timeODAA the time odaa
     * @return the string
     */
    public String getJSONNewsDDataFileName(TimeOD timeODAA){
        String string_A=contextA.getExternalFilesDir("NewsDData").getPath();

        return string_A + ("/" + timeODAA.intsA[0] + "/" + (timeODAA.intsA[1] + 1) + "/" + timeODAA.intsA[2] + ".json");
    }
    public String getJSONNewsDayFileName(SkillNewsName skillNewsNameAA, TimeOD timeODAA){
        String string_A=contextA.getExternalFilesDir("NewsDay").getPath();

        return string_A+="/"+timeODAA.intsA[0]+"/"+(timeODAA.intsA[1]+1)+"/"+timeODAA.intsA[2]+".json";
    }
    public String getCalenderDDataFileName(TimeOD timeODAA){
        String string_A=contextA.getExternalFilesDir("CalenderDData").getPath();

        return string_A + ("/" + timeODAA.intsA[0] + "/" + (timeODAA.intsA[1] + 1) + "/" + timeODAA.intsA[2] + ".json");
    }
    /**
     *获取Quo记录JSON文件名
     */
    public String PuC_getJSONQuoRecFileName(TimeOD timeODAA){
        String string_A=contextA.getExternalFilesDir("Record").getPath()+"/QuoRec";

        return string_A+="/"+timeODAA.intsA[0]+"/"+(timeODAA.intsA[1]+1)+"/"+timeODAA.intsA[2]+".json";
    }
    /**
     *保存数据到目标文件
     */
    public Boolean PuC_sevaJSONToFile(String stringAA, File fileAA){
        File file_A=new File(fileAA.getParent());
        if(!file_A.exists())
        {
            file_A.mkdirs();
        }

        try {
            FileWriter fileWriter_A=new FileWriter(fileAA,false);
            BufferedWriter bufferedWriter_A=new BufferedWriter(fileWriter_A);
            bufferedWriter_A.write(stringAA);
            //使用缓冲区中的方法，将数据刷新到目的地文件中去。
            bufferedWriter_A.flush();
            //关闭缓冲区,同时关闭了fw流对象
            bufferedWriter_A.close();
            fileWriter_A.close();

        } catch (Exception e) {
            Log.d(TAG, "PuC_sFXQTF_A0: "+e.toString());
            e.printStackTrace();
            return false;
        }
        return true;


    }

    /**
     * 获取JSON文件数据
     *
     * @return the string
     */
    public String getJSONFileData(File fileAA){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fileReader_A=new FileReader(fileAA);
            BufferedReader bufferedReader = new BufferedReader(fileReader_A);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            //]Log.w(this.toString(), "getJSONFileData:E0 "+"="+e.toString() );
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    //String stringA="FXQuoFile";

//    /**
//     *创新报价文件
//     */
//    public boolean PuC_createFXQuoXMLFile(MyFXDataService.TimeOD timeODAA){
//        synchronized (stringA){
//            String string_a=contextA.getExternalFilesDir("Quote").getPath()+"/"+timeODAA.intsA[0]+"/"+timeODAA.intsA[1]+"/"+timeODAA.intsA[2]+".xml";
//            Log.d(TAG, "Clc_PrC_cCDF_0: "+string_a+"="+"/"+timeODAA.intsA[2]+".xml");
//            File file_A=new File(string_a);
//            Log.d(TAG, "Clc_PrC_cCDF_0.1: "+(file_A!=null));
//            Log.d(TAG, "Clc_PrC_cCDF_1: "+file_A.getPath() +"="+(file_A.exists()));
//            if(file_A.exists())
//            {
//                //return false;
//                file_A.delete();
//
//            }else {
//                File file_B=new File(file_A.getParent());
//                Log.d(TAG, "Clc_PrC_cCDF_1B1: "+file_B.getPath() +"="+(file_B.exists()));
//                if(!file_B.exists())
//                {
//
//                    file_B.mkdirs();
//                }
//            }
//            String string_B="Quote";
//
//            String string_C=timeODAA.intsA[0]+"-"+timeODAA.intsA[1]+"-"+timeODAA.intsA[2];
//            try {
//                FileOutputStream fos = new FileOutputStream(file_A);
//                XmlSerializer xs = Xml.newSerializer();
//                xs.setOutput(fos, "utf-8");
//                //生成xml头
//                xs.startDocument("utf-8", true);
//                //xs.setPrefix("class", "Good");
//                //添加xml根节点
//                xs.startTag(null, "XML");
//                xs.startTag(null, "Description");
//                xs.attribute(null,"Type",string_B);
//                xs.attribute(null,"Date",string_C);
//                xs.endTag(null, "Description");
//                xs.startTag(null, "dataMin");
//                xs.startTag(null, "H_0");
//                xs.endTag(null, "H_0");
//                xs.startTag(null, "H_1");
//                xs.endTag(null, "H_1");
//                xs.startTag(null, "H_2");
//                xs.endTag(null, "H_2");
//                xs.startTag(null, "H_3");
//                xs.endTag(null, "H_3");
//                xs.startTag(null, "H_4");
//                xs.endTag(null, "H_4");
//                xs.startTag(null, "H_5");
//                xs.endTag(null, "H_5");
//                xs.startTag(null, "H_6");
//                xs.endTag(null, "H_6");
//                xs.startTag(null, "H_7");
//                xs.endTag(null, "H_7");
//                xs.startTag(null, "H_8");
//                xs.endTag(null, "H_8");
//                xs.startTag(null, "H_9");
//                xs.endTag(null, "H_9");
//                xs.startTag(null, "H_10");
//                xs.endTag(null, "H_10");
//                xs.startTag(null, "H_11");
//                xs.endTag(null, "H_11");
//                xs.startTag(null, "H_12");
//                xs.endTag(null, "H_12");
//                xs.startTag(null, "H_13");
//                xs.endTag(null, "H_13");
//                xs.startTag(null, "H_14");
//                xs.endTag(null, "H_14");
//                xs.startTag(null, "H_15");
//                xs.endTag(null, "H_15");
//                xs.startTag(null, "H_16");
//                xs.endTag(null, "H_16");
//                xs.startTag(null, "H_17");
//                xs.endTag(null, "H_17");
//                xs.startTag(null, "H_18");
//                xs.endTag(null, "H_18");
//                xs.startTag(null, "H_19");
//                xs.endTag(null, "H_19");
//                xs.startTag(null, "H_20");
//                xs.endTag(null, "H_20");
//                xs.startTag(null, "H_21");
//                xs.endTag(null, "H_21");
//                xs.startTag(null, "H_22");
//                xs.endTag(null, "H_22");
//                xs.startTag(null, "H_23");
//                xs.endTag(null, "H_23");
//                xs.endTag(null, "dataMin");
//                xs.endTag(null, "XML");
//                xs.endDocument();
//            } catch (Exception e) {
//
//                return false;
//            }
//            return true;
//
//        }
//    }
//    public boolean PuC_createFXQuoXMLFile(MyFXDataService.TimeOD timeODAA, boolean booleanAA){
//        synchronized (stringA){
//            String string_a=contextA.getExternalFilesDir("Quote").getPath()+"/"+timeODAA.intsA[0]+"/"+timeODAA.intsA[1]+"/"+timeODAA.intsA[2]+".xml";
//            Log.d(TAG, "Clc_PrC_cCDF_0: "+string_a+"="+"/"+timeODAA.intsA[2]+".xml");
//            File file_A=new File(string_a);
//            Log.d(TAG, "Clc_PrC_cCDF_0.1: "+(file_A!=null));
//            Log.d(TAG, "Clc_PrC_cCDF_1: "+file_A.getPath() +"="+(file_A.exists()));
//            if(file_A.exists())
//            {
//                if(booleanAA)
//                    {
//                        file_A.delete();
//                    }else {
//                    return false;
//                }
//                //
//
//
//            }else {
//                File file_B=new File(file_A.getParent());
//                Log.d(TAG, "Clc_PrC_cCDF_1B1: "+file_B.getPath() +"="+(file_B.exists()));
//                if(!file_B.exists())
//                {
//
//                    file_B.mkdirs();
//                }
//            }
//            String string_B="Quote";
//
//            String string_C=timeODAA.intsA[0]+"-"+timeODAA.intsA[1]+"-"+timeODAA.intsA[2];
//            try {
//                FileOutputStream fos = new FileOutputStream(file_A);
//                XmlSerializer xs = Xml.newSerializer();
//                xs.setOutput(fos, "utf-8");
//                //生成xml头
//                xs.startDocument("utf-8", true);
//                //xs.setPrefix("class", "Good");
//                //添加xml根节点
//                xs.startTag(null, "XML");
//                xs.startTag(null, "Description");
//                xs.attribute(null,"Type",string_B);
//                xs.attribute(null,"Date",string_C);
//                xs.attribute(null,"DateType","0");
//                xs.endTag(null, "Description");
//                xs.startTag(null, "dataMin");
//                xs.startTag(null, "H_0");
//                xs.endTag(null, "H_0");
//                xs.startTag(null, "H_1");
//                xs.endTag(null, "H_1");
//                xs.startTag(null, "H_2");
//                xs.endTag(null, "H_2");
//                xs.startTag(null, "H_3");
//                xs.endTag(null, "H_3");
//                xs.startTag(null, "H_4");
//                xs.endTag(null, "H_4");
//                xs.startTag(null, "H_5");
//                xs.endTag(null, "H_5");
//                xs.startTag(null, "H_6");
//                xs.endTag(null, "H_6");
//                xs.startTag(null, "H_7");
//                xs.endTag(null, "H_7");
//                xs.startTag(null, "H_8");
//                xs.endTag(null, "H_8");
//                xs.startTag(null, "H_9");
//                xs.endTag(null, "H_9");
//                xs.startTag(null, "H_10");
//                xs.endTag(null, "H_10");
//                xs.startTag(null, "H_11");
//                xs.endTag(null, "H_11");
//                xs.startTag(null, "H_12");
//                xs.endTag(null, "H_12");
//                xs.startTag(null, "H_13");
//                xs.endTag(null, "H_13");
//                xs.startTag(null, "H_14");
//                xs.endTag(null, "H_14");
//                xs.startTag(null, "H_15");
//                xs.endTag(null, "H_15");
//                xs.startTag(null, "H_16");
//                xs.endTag(null, "H_16");
//                xs.startTag(null, "H_17");
//                xs.endTag(null, "H_17");
//                xs.startTag(null, "H_18");
//                xs.endTag(null, "H_18");
//                xs.startTag(null, "H_19");
//                xs.endTag(null, "H_19");
//                xs.startTag(null, "H_20");
//                xs.endTag(null, "H_20");
//                xs.startTag(null, "H_21");
//                xs.endTag(null, "H_21");
//                xs.startTag(null, "H_22");
//                xs.endTag(null, "H_22");
//                xs.startTag(null, "H_23");
//                xs.endTag(null, "H_23");
//                xs.endTag(null, "dataMin");
//                xs.endTag(null, "XML");
//                xs.endDocument();
//            } catch (Exception e) {
//
//                e.printStackTrace();
//                return false;
//            }
//            return true;
//
//        }
//    }
//    /**
//     *保存报价到目标文件
//     */
//    public Boolean PuC_sevaFXQuoXMLToFile(Document documentAA,File fileAA,MyFXDataService.TimeOD timeODAA){
//        synchronized (stringA){
//            //String string_A=contextA.getExternalFilesDir("Quote").getPath()+"/"+timeODAA.intsA[0]+"/"+timeODAA.intsA[1]+"/"+timeODAA.intsA[2]+".xml";
//            //File fileAA=new File(string_A);
//            if(!fileAA.exists())
//                {
//                    if(!PuC_createFXQuoXMLFile(timeODAA)){
//                        return false;
//                    }
//                }
//            try {
//                TransformerFactory tFactory = TransformerFactory.newInstance();// 将内存中的Dom保存到文件
//                Transformer transformer = tFactory.newTransformer();
//                // 设置输出的xml的格式，utf-8
//                transformer.setOutputProperty("encoding", "utf-8");
//                DOMSource source = new DOMSource(documentAA);
//                //xml的存放位置
//                StreamResult src = new StreamResult(fileAA);
//                transformer.transform(source, src);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//            return true;
//        }
//
//    }
//    /**
//     *获取对应DOM模式的文件XML
//     */
//    public Document PuC_getDOMModeFileXML(File file_AA){
//        Document document=null;
//
//        if(!file_AA.exists())
//            {
//                file_AA.delete();
//                Log.d(TAG, "PuC2131_0: "+"="+file_AA.getPath());
//            return null;
//            }
//        try {
//            Log.d(TAG, "PuC2131_1: "+"="+file_AA.getPath());
//            document = documentBuilder.parse(file_AA);
//        }catch (Exception e) {
//            Log.d(TAG, "PuC2131_2: "+"="+e.toString());
//            e.printStackTrace();
//            return null;
//        }
//        Log.d(TAG, "PuC2131_3: "+"="+(document!=null));
//        return document;
//
//    }
//    /**
//     *调查是否有对应XML文件
//     */
//    public File PuC_isXMLFile(int intAA,MyFXDataService.TimeOD timeODAA){
//
//        String string_A=null;
//        switch(intAA){
//            case 0:
//                break;
//            case 1:
//                string_A=contextA.getExternalFilesDir("Quote").getPath();
//                break;
//            default:
//                break;
//        }
//        string_A+="/"+timeODAA.intsA[0]+"/"+timeODAA.intsA[1]+"/"+timeODAA.intsA[2]+".xml";
//        File file_A=new File(string_A);
//        if(!file_A.exists())
//        {
//            return null;
//        }else {
//            return file_A;
//        }
//    }


}
