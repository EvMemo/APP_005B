package com.example.app_005.Class;

import android.graphics.Paint;
import android.util.Log;

import com.example.app_005.Tool.TimeOD;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Map;

public class NewsDataClass {
    public SkillNewsName skillNewsName;
    public TimeOD timeOD;
    public int intMode;
    public int intID;
    public String stringTitle;
    public String stringText;
    public ArrayList<Nak> naks;
    public boolean boolMore;
    public boolean boolSee;
    public boolean boolCollert;

    public RecordNewsData recordNewsData;

//    public int intTextH;
//    public String[] stringsAdvancesText;
    public NewsDataClass(String stringTitle_A,String stringText_A,boolean boolMore_A,SkillNewsName skillNewsName_A,int intMode_A,TimeOD timeOD_A){
        skillNewsName=skillNewsName_A;
        timeOD=timeOD_A;
        intMode=intMode_A;
        stringTitle=stringTitle_A;
        stringText=stringText_A;
        boolMore=boolMore_A;
    }
    public NewsDataClass(String string_Title,String stringText_A,ArrayList<Nak> naks_A,SkillNewsName skillNewsName_A,int intMode_A,TimeOD timeOD_A){
        stringTitle=string_Title;
        stringText=stringText_A;
        naks=naks_A;
        boolMore=(naks_A!=null&&naks_A.size()>0);
        skillNewsName=skillNewsName_A;
        intMode=intMode_A;
        timeOD=timeOD_A;
    }
//    public void setJsonData(JSONObject jsonData_A){
//        jsonObject=jsonData_A;
//    }
//    ArrayList<String> getTextAdvances(String string_A, Paint paint_A, float float_W) {
//        ArrayList<String> strings_A = new ArrayList<>();
//        int int_A = string_A.length();
//
//        float float_A = paint_A.measureText(string_A);
//        if (float_A > float_W) {
//            int int_F2 = (int)(float_A / float_W)+1;
//            int int_F = (int_A / int_F2);
//            int int_B2 = 0;
//            int int_B =  int_F;
//            String string_B = null;
//            while (true) {
//                string_B = string_A.substring(int_B2, int_B);
//                float_A = paint_A.measureText(string_B);
//
//                if (float_A < float_W) {
//                    int int_C = int_B;
//                    while (true) {
//                        if (float_A > (float_W - 10)) {
//                            int_B = int_C;
//                            break;
//                        } else if (int_C >= int_A) {
//
//                            if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
//                            {
//                                for(int e=int_C-2;e>int_B2;e--)
//                                {
//                                    if(ccUnicodeBlock(string_A.charAt(e)))
//                                    {
//                                        int_C=e+1;
//                                        break;
//                                    }
//
//                                }
//
//                            }
//                            string_B = string_A.substring(int_B2, int_C);
//                            // Log.w(this.toString(), "getTextAdvancesR1: " +"="+"1" +"="+ string_B);
//                            strings_A.add(string_B);
//                            return strings_A;
//
//                        } else {
//                            int_C++;
//                            string_B = string_A.substring(int_B2, int_C);
//                            float_A = paint_A.measureText(string_B);
//                        }
//                    }
//                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
//                    {
//                        for(int e=int_B-2;e>int_B2;e--)
//                        {
//                            if(ccUnicodeBlock(string_A.charAt(e)))
//                            {
//                                int_B=e+1;
//                                break;
//                            }
//
//                        }
//
//                    }
//                    string_B = string_A.substring(int_B2, int_B);
//                    // Log.w(this.toString(), "getTextAdvancesR1: " +"="+"0" +"="+ string_B);
//                    strings_A.add(string_B);
//                    //int_B2 = int_B;
//                } else {
//                    int int_C = int_B;
//                    while (true) {
//                        if (float_A < float_W) {
//                            int_B = int_C;
//
//                            break;
//                        } else if (int_C >= int_B2) {
//                            if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
//                            {
//                                for(int e=int_C-2;e>int_B2;e--)
//                                {
//                                    if(ccUnicodeBlock(string_A.charAt(e)))
//                                    {
//                                        int_C=e+1;
//                                        break;
//                                    }
//
//                                }
//
//                            }
//                            string_B = string_A.substring(int_B2, int_C);
//                            // Log.w(this.toString(), "getTextAdvancesR0: " +"="+"0" +"="+ string_B);
//                            strings_A.add(string_B);
//                            return strings_A;
//
//                        } else {
//                            if (int_C <= int_B2+1)
//                            {
//                                if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
//                                {
//                                    for(int e=int_C-2;e>int_B2;e--)
//                                    {
//                                        if(ccUnicodeBlock(string_A.charAt(e)))
//                                        {
//                                            int_C=e+1;
//                                            break;
//                                        }
//
//                                    }
//
//                                }
//                                string_B = string_A.substring(int_B2, int_C);
//                                // Log.w(this.toString(), "getTextAdvancesR0: " +"="+"1" +"="+ string_B);
//                                strings_A.add(string_B);
//                                return strings_A;
//
//                            }else {
//                                int_C--;
//                                string_B = string_A.substring(int_B2, int_C);
//                                float_A = paint_A.measureText(string_B);
//                            }
//
//                        }
//                    }
//                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
//                    {
//                        for(int e=int_B-2;e>int_B2;e--)
//                        {
//                            if(ccUnicodeBlock(string_A.charAt(e)))
//                            {
//                                int_B=e+1;
//                                break;
//                            }
//
//                        }
//
//                    }
//                    string_B = string_A.substring(int_B2, int_B);
//                    // Log.w(this.toString(), "getTextAdvancesR0: " +"="+"2" +"="+ string_B);
//                    strings_A.add(string_B);
//                    //int_B2 = int_B;
//                }
//                if(int_B>=int_A)
//                {
//                    break;
//                }else {
//                    int_B2=int_B;
//                    int_B+=int_F;
//                    if(int_B>=int_A)
//                    {
//                        int_B=int_A;
//
//                        string_B = string_A.substring(int_B2, int_B);
//                        // Log.w(this.toString(), "getTextAdvancesR0: " +"="+"3" +"="+ string_B);
//                        strings_A.add(string_B);
//                        return strings_A;
//
//                    }
//
//
//                }
//            }
//            return strings_A;
//        }else {
//            // Log.w(this.toString(), "getTextAdvancesR0: " +"="+"9" +"="+ string_A);
//            strings_A.add(string_A);
//            return strings_A;
//        }
//
//
//
//    }
//    boolean ccUnicodeBlock(char char_A){
//
//        Character.UnicodeScript unicodeScript=Character.UnicodeScript.of(char_A);
//        // Log.w(this.toString(), "ccUnicodeBlockG1: "+"="+char_A +"="+ (unicodeScript==Character.UnicodeScript.HAN) +"="+(Character.isLetter(char_A))+"="+(Character.isDigit(char_A))+"="+(Character.isWhitespace(char_A)));
//        if(unicodeScript==Character.UnicodeScript.HAN)
//        {
//            return true;
//        }
//        if(Character.isLetter(char_A))
//        {
//            return false;
//        }
//        if(Character.isDigit(char_A))
//        {
//            return true;
//        }
//        if(Character.isWhitespace(char_A))
//        {
//            return true;
//
//        }
//
//        Log.w(this.toString(), "ccUnicodeBlockF0: "+"="+ char_A +"="+unicodeScript.toString());
//        return true;
//
//    }

}
