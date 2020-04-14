package com.example.app_005.Tool;

import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

public class TextStringTool {
   public static ArrayList<String> getTextAdvances(String string_A, Paint paint_A, float float_W) {
        ArrayList<String> strings_A = new ArrayList<>();
        int int_A = string_A.length();

        float float_A = paint_A.measureText(string_A);
        if (float_A > float_W) {
            int int_F2 = (int)(float_A / float_W)+1;
            int int_F = (int_A / int_F2);
            int int_B2 = 0;
            int int_B =  int_F;
            String string_B = null;
            while (true) {
                string_B = string_A.substring(int_B2, int_B);
                float_A = paint_A.measureText(string_B);

                if (float_A < float_W) {
                    int int_C = int_B;
                    while (true) {
                        if (float_A > (float_W - 10)) {
                            int_B = int_C;
                            break;
                        } else if (int_C >= int_A) {

                            if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                            {
                                for(int e=int_C-2;e>int_B2;e--)
                                {
                                    if(ccUnicodeBlock(string_A.charAt(e)))
                                    {
                                        int_C=e+1;
                                        break;
                                    }

                                }

                            }
                            string_B = string_A.substring(int_B2, int_C);
                            // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"1" +"="+ string_B);
                            strings_A.add(string_B);
                            return strings_A;

                        } else {
                            int_C++;
                            string_B = string_A.substring(int_B2, int_C);
                            float_A = paint_A.measureText(string_B);
                        }
                    }
                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
                    {
                        for(int e=int_B-2;e>int_B2;e--)
                        {
                            if(ccUnicodeBlock(string_A.charAt(e)))
                            {
                                int_B=e+1;
                                break;
                            }

                        }

                    }
                    string_B = string_A.substring(int_B2, int_B);
                    // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"0" +"="+ string_B);
                    strings_A.add(string_B);
                    //int_B2 = int_B;
                } else {
                    int int_C = int_B;
                    while (true) {
                        if (float_A < float_W) {
                            int_B = int_C;

                            break;
                        } else if (int_C >= int_B2) {
                            if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                            {
                                for(int e=int_C-2;e>int_B2;e--)
                                {
                                    if(ccUnicodeBlock(string_A.charAt(e)))
                                    {
                                        int_C=e+1;
                                        break;
                                    }

                                }

                            }
                            string_B = string_A.substring(int_B2, int_C);
                            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"0" +"="+ string_B);
                            strings_A.add(string_B);
                            return strings_A;

                        } else {
                            if (int_C <= int_B2+1)
                            {
                                if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                                {
                                    for(int e=int_C-2;e>int_B2;e--)
                                    {
                                        if(ccUnicodeBlock(string_A.charAt(e)))
                                        {
                                            int_C=e+1;
                                            break;
                                        }

                                    }

                                }
                                string_B = string_A.substring(int_B2, int_C);
                                // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"1" +"="+ string_B);
                                strings_A.add(string_B);
                                return strings_A;

                            }else {
                                int_C--;
                                string_B = string_A.substring(int_B2, int_C);
                                float_A = paint_A.measureText(string_B);
                            }

                        }
                    }
                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
                    {
                        for(int e=int_B-2;e>int_B2;e--)
                        {
                            if(ccUnicodeBlock(string_A.charAt(e)))
                            {
                                int_B=e+1;
                                break;
                            }

                        }

                    }
                    string_B = string_A.substring(int_B2, int_B);
                    // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"2" +"="+ string_B);
                    strings_A.add(string_B);
                    //int_B2 = int_B;
                }
                if(int_B>=int_A)
                {
                    break;
                }else {
                    int_B2=int_B;
                    int_B+=int_F;
                    if(int_B>=int_A)
                    {
                        int_B=int_A;

                        string_B = string_A.substring(int_B2, int_B);
                        // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"3" +"="+ string_B);
                        strings_A.add(string_B);
                        return strings_A;

                    }


                }
            }
            return strings_A;
        }else {
            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"9" +"="+ string_A);
            strings_A.add(string_A);
            return strings_A;
        }



    }
    public static String[] getTextAdvancesB(String string_A, Paint paint_A, float float_W) {
        ArrayList<String> strings_A = new ArrayList<>();
        int int_A = string_A.length();

        float float_A = paint_A.measureText(string_A);
        if (float_A > float_W) {
            int int_F2 = (int)(float_A / float_W)+1;
            int int_F = (int_A / int_F2);
            int int_B2 = 0;
            int int_B =  int_F;
            String string_B = null;
            while (true) {
                string_B = string_A.substring(int_B2, int_B);
                float_A = paint_A.measureText(string_B);

                if (float_A < float_W) {
                    int int_C = int_B;
                    while (true) {
                        if (float_A > (float_W - 10)) {
                            int_B = int_C;
                            break;
                        } else if (int_C >= int_A) {

                            if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                            {
                                for(int e=int_C-2;e>int_B2;e--)
                                {
                                    if(ccUnicodeBlock(string_A.charAt(e)))
                                    {
                                        int_C=e+1;
                                        break;
                                    }

                                }

                            }
                            string_B = string_A.substring(int_B2, int_C);
                            // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"1" +"="+ string_B);
                            strings_A.add(string_B);
                            return strings_A.toArray(new String[strings_A.size()]);

                        } else {
                            int_C++;
                            string_B = string_A.substring(int_B2, int_C);
                            float_A = paint_A.measureText(string_B);
                        }
                    }
                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
                    {
                        for(int e=int_B-2;e>int_B2;e--)
                        {
                            if(ccUnicodeBlock(string_A.charAt(e)))
                            {
                                int_B=e+1;
                                break;
                            }

                        }

                    }
                    string_B = string_A.substring(int_B2, int_B);
                    // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"0" +"="+ string_B);
                    strings_A.add(string_B);
                    //int_B2 = int_B;
                } else {
                    int int_C = int_B;
                    while (true) {
                        if (float_A < float_W) {
                            int_B = int_C;

                            break;
                        } else if (int_C >= int_B2) {
                            if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                            {
                                for(int e=int_C-2;e>int_B2;e--)
                                {
                                    if(ccUnicodeBlock(string_A.charAt(e)))
                                    {
                                        int_C=e+1;
                                        break;
                                    }

                                }

                            }
                            string_B = string_A.substring(int_B2, int_C);
                            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"0" +"="+ string_B);
                            strings_A.add(string_B);
                            return strings_A.toArray(new String[strings_A.size()]);

                        } else {
                            if (int_C <= int_B2+1)
                            {
                                if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                                {
                                    for(int e=int_C-2;e>int_B2;e--)
                                    {
                                        if(ccUnicodeBlock(string_A.charAt(e)))
                                        {
                                            int_C=e+1;
                                            break;
                                        }

                                    }

                                }
                                string_B = string_A.substring(int_B2, int_C);
                                // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"1" +"="+ string_B);
                                strings_A.add(string_B);
                                return strings_A.toArray(new String[strings_A.size()]);

                            }else {
                                int_C--;
                                string_B = string_A.substring(int_B2, int_C);
                                float_A = paint_A.measureText(string_B);
                            }

                        }
                    }
                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
                    {
                        for(int e=int_B-2;e>int_B2;e--)
                        {
                            if(ccUnicodeBlock(string_A.charAt(e)))
                            {
                                int_B=e+1;
                                break;
                            }

                        }

                    }
                    string_B = string_A.substring(int_B2, int_B);
                    // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"2" +"="+ string_B);
                    strings_A.add(string_B);
                    //int_B2 = int_B;
                }
                if(int_B>=int_A)
                {
                    break;
                }else {
                    int_B2=int_B;
                    int_B+=int_F;
                    if(int_B>=int_A)
                    {
                        int_B=int_A;

                        string_B = string_A.substring(int_B2, int_B);
                        // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"3" +"="+ string_B);
                        strings_A.add(string_B);
                        return strings_A.toArray(new String[strings_A.size()]);

                    }


                }
            }
            return strings_A.toArray(new String[strings_A.size()]);
        }else {
            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"9" +"="+ string_A);
            strings_A.add(string_A);
            return strings_A.toArray(new String[strings_A.size()]);
        }



    }
    public static String[] getTextAdvancesC(String string_A, Paint paint_A, float float_W) {
        ArrayList<String> strings_A = new ArrayList<>();
        int int_A = string_A.length();
        int int_T=(int)paint_A.getTextSize();
        //float_W-=int_T;
        //]Log.w("getTextAdvancesC", "getTextAdvancesC10: "+"="+ paint_A.getTextSize());

        float float_A = paint_A.measureText(string_A);
        if (float_A > float_W) {
            int int_F2 = (int)(float_A / float_W)+1;
            int int_F = (int_A / int_F2);
            int int_B2 = 0;
            int int_B =  int_F;
            String string_B = null;
            while (true) {
                string_B = string_A.substring(int_B2, int_B);
                float_A = paint_A.measureText(string_B);

                if (float_A < float_W) {
                    int int_C = int_B;
                    while (true) {
                        if (float_A > (float_W)) {
                            int_B = int_C-1;
                            break;
                        } else if (int_C >= int_A) {

                            if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                            {
                                for(int e=int_C-2;e>int_B2;e--)
                                {
                                    if(ccUnicodeBlock(string_A.charAt(e)))
                                    {
                                        int_C=e+1;
                                        break;
                                    }

                                }

                            }
                            string_B = string_A.substring(int_B2, int_C);
                            // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"1" +"="+ string_B);
                            strings_A.add(string_B);
                            //]Log.w("getTextAdvancesC", "getTextAdvancesC01: "+"="+string_B );
                            return strings_A.toArray(new String[strings_A.size()]);

                        } else {
                            int_C++;
                            string_B = string_A.substring(int_B2, int_C);
                            float_A = paint_A.measureText(string_B);
                           // //]Log.w("getTextAdvancesC", "getTextAdvancesC02: "+"="+string_B );
                        }
                    }
                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
                    {
                        for(int e=int_B-2;e>int_B2;e--)
                        {
                            if(ccUnicodeBlock(string_A.charAt(e)))
                            {
                                int_B=e+1;

                                break;
                            }

                        }

                    }
                    string_B = string_A.substring(int_B2, int_B);
                    float_A = paint_A.measureText(string_B);
                    // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"0" +"="+ string_B);
                    strings_A.add(string_B);
                    //]Log.w("getTextAdvancesC", "getTextAdvancesC03: "+"="+string_B +"="+float_A +"="+float_W );
                    //int_B2 = int_B;
                }
                else {
                    int int_C = int_B;
                    while (true) {
                        //]Log.w("getTextAdvancesC", "getTextAdvancesCS0: "+"="+float_A +"="+float_W +"="+ string_B);
                        if (float_A <= float_W) {
                            int_B = int_C;

                            break;
                        } else if (int_C >= int_B2) {
                            if(true||!ccUnicodeBlock(string_A.charAt(int_C-1)))
                            {
                                for(int e=int_C-2;e>int_B2;e--)
                                {
                                    if(ccUnicodeBlock(string_A.charAt(e)))
                                    {
                                        int_C=e+1;
                                        //]Log.w("getTextAdvancesC", "getTextAdvancesC04A: "+"="+int_C);
                                        break;
                                    }else {
                                        //]Log.w("getTextAdvancesC", "getTextAdvancesC04C: "+"="+string_A.charAt(e));
                                    }
                                    //]Log.w("getTextAdvancesC", "getTextAdvancesC04B: "+"="+int_C);
                                }


                            }
                            string_B = string_A.substring(int_B2, int_C);
                            float_A = paint_A.measureText(string_B);
                            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"0" +"="+ string_B);
                           // strings_A.add(string_B);
                           // //]Log.w("getTextAdvancesC", "getTextAdvancesC04: "+"="+string_B );
                            //return strings_A.toArray(new String[strings_A.size()]);
                           // int_B=int_C;
                           // break;

                        } else {
                            if (int_C <= int_B2+1)
                            {
                                if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                                {
                                    for(int e=int_C-2;e>int_B2;e--)
                                    {
                                        if(ccUnicodeBlock(string_A.charAt(e)))
                                        {
                                            int_C=e+1;
                                            break;
                                        }

                                    }

                                }
                                string_B = string_A.substring(int_B2, int_C);
                                // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"1" +"="+ string_B);
                                strings_A.add(string_B);
                                //]Log.w("getTextAdvancesC", "getTextAdvancesC05: "+"="+string_B );
                                return strings_A.toArray(new String[strings_A.size()]);

                            }else {
                                int_C--;
                                string_B = string_A.substring(int_B2, int_C);
                                float_A = paint_A.measureText(string_B);
                            }

                        }
                    }
                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
                    {
                        for(int e=int_B-2;e>int_B2;e--)
                        {
                            if(ccUnicodeBlock(string_A.charAt(e)))
                            {
                                int_B=e+1;
                                break;
                            }

                        }

                    }
                    string_B = string_A.substring(int_B2, int_B);

                    strings_A.add(string_B);
                    //]Log.w("getTextAdvancesC", "getTextAdvancesC04: "+"="+string_B );
                    //int_B2 = int_B;
                }
                if(int_B>=int_A)
                {
                    break;
                }else {
                    int_B2=int_B;
                    int_B+=int_F;
                    if(int_B>=int_A)
                    {
                        int_B=int_A;

                        string_B = string_A.substring(int_B2, int_B);
                        float_A = paint_A.measureText(string_B);
                        // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"3" +"="+ string_B);
                        strings_A.add(string_B);
                        //]Log.w("getTextAdvancesC", "getTextAdvancesC06: "+"="+string_B +"="+float_A +"="+float_W );
                        return strings_A.toArray(new String[strings_A.size()]);

                    }


                }
            }
            ////]Log.w("getTextAdvancesC", "getTextAdvancesC07: "+"="+string_B );
            return strings_A.toArray(new String[strings_A.size()]);
        }else {
            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"9" +"="+ string_A);
            strings_A.add(string_A);
            //]Log.w("getTextAdvancesC", "getTextAdvancesC08: "+"="+string_A );
            return strings_A.toArray(new String[strings_A.size()]);
        }



    }
    public static String getTitleAdvances(String string_A,Paint paint_A,float float_W) {
        //ArrayList<String> strings_A = new ArrayList<>();
        String string_rt=null;
        int int_A = string_A.length();
        float_W-=30;
        float float_A = paint_A.measureText(string_A);
        if (float_A > float_W) {
            int int_F2 = (int)(float_A / float_W)+1;
            int int_F = (int_A / int_F2);
            int int_B2 = 0;
            int int_B =  int_F;
            String string_B = null;
            while (true) {
                string_B = string_A.substring(int_B2, int_B);
                float_A = paint_A.measureText(string_B);

                if (float_A < float_W) {
                    int int_C = int_B;
                    while (true) {
                        if (float_A > (float_W)) {
                            int_B = int_C-1;
                            break;
                        } else if (int_C >= int_A) {

                            if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                            {
                                for(int e=int_C-2;e>int_B2;e--)
                                {
                                    if(ccUnicodeBlock(string_A.charAt(e)))
                                    {
                                        int_C=e+1;
                                        break;
                                    }

                                }

                            }
                            string_B = string_A.substring(int_B2, int_C);
                            // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"1" +"="+ string_B);
                            //strings_A.add(string_B);
                            return string_B+"...";

                        } else {
                            int_C++;
                            string_B = string_A.substring(int_B2, int_C);
                            float_A = paint_A.measureText(string_B);
                        }
                    }
                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
                    {
                        for(int e=int_B-2;e>int_B2;e--)
                        {
                            if(ccUnicodeBlock(string_A.charAt(e)))
                            {
                                int_B=e+1;
                                break;
                            }

                        }

                    }
                    string_B = string_A.substring(int_B2, int_B);
                    // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"0" +"="+ string_B);
                    //strings_A.add(string_B);
                    return string_B+"...";
                    //int_B2 = int_B;
                }
                else {
                    int int_C = int_B;
                    while (true) {
                        if (float_A < float_W) {
                            int_B = int_C;

                            break;
                        } else if (int_C >= int_B2) {
                            if(true||!ccUnicodeBlock(string_A.charAt(int_C-1)))
                            {
                                for(int e=int_C-2;e>int_B2;e--)
                                {
                                    if(ccUnicodeBlock(string_A.charAt(e)))
                                    {
                                        int_C=e+1;
                                        break;
                                    }

                                }

                            }
                            string_B = string_A.substring(int_B2, int_C);
                            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"0" +"="+ string_B);
//                            strings_A.add(string_B);
                            return string_B+"...";

                        } else {
                            if (int_C <= int_B2+1)
                            {
                                if(!ccUnicodeBlock(string_A.charAt(int_C-1)))
                                {
                                    for(int e=int_C-2;e>int_B2;e--)
                                    {
                                        if(ccUnicodeBlock(string_A.charAt(e)))
                                        {
                                            int_C=e+1;
                                            break;
                                        }

                                    }

                                }
                                string_B = string_A.substring(int_B2, int_C);
                                // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"1" +"="+ string_B);
//                                strings_A.add(string_B);
                                return string_B+"...";

                            }else {
                                int_C--;
                                string_B = string_A.substring(int_B2, int_C);
                                float_A = paint_A.measureText(string_B);
                            }

                        }
                    }
                    if(!ccUnicodeBlock(string_A.charAt(int_B-1)))
                    {
                        for(int e=int_B-2;e>int_B2;e--)
                        {
                            if(ccUnicodeBlock(string_A.charAt(e)))
                            {
                                int_B=e+1;
                                break;
                            }

                        }

                    }
                    string_B = string_A.substring(int_B2, int_B);
                    // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"2" +"="+ string_B);
//                    strings_A.add(string_B);
                    return string_B+"...";
                    //int_B2 = int_B;
                }
//                if(int_B>=int_A)
//                {
//                    return null;
//                }else {
//                    int_B2=int_B;
//                    int_B+=int_F;
//                    if(int_B>=int_A)
//                    {
//                        int_B=int_A;
//
//                        string_B = string_A.substring(int_B2, int_B);
//                        // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"3" +"="+ string_B);
////                        strings_A.add(string_B);
//                        return string_B;
//
//                    }
//
//
//                }

            }
            //
        }else {
            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"9" +"="+ string_A);
            // strings_A.add(string_A);
            return string_A;
        }



    }
//    public static String getTitleAdvances(String string_A,Paint paint_A,float float_W) {
//        //ArrayList<String> strings_A = new ArrayList<>();
//        String string_rt=null;
//        int int_A = string_A.length();
//        float_W-=30;
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
//                        if (float_A > (float_W)) {
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
//                            // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"1" +"="+ string_B);
//                            //strings_A.add(string_B);
//                            return string_B+"...";
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
//                    // //]Log.w(this.toString(), "getTextAdvancesR1: " +"="+"0" +"="+ string_B);
//                    //strings_A.add(string_B);
//                    return string_B+"...";
//                    //int_B2 = int_B;
//                }
//                else {
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
//                            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"0" +"="+ string_B);
////                            strings_A.add(string_B);
//                            return string_B+"...";
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
//                                // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"1" +"="+ string_B);
////                                strings_A.add(string_B);
//                                return string_B+"...";
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
//                    // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"2" +"="+ string_B);
////                    strings_A.add(string_B);
//                    return string_B+"...";
//                    //int_B2 = int_B;
//                }
////                if(int_B>=int_A)
////                {
////                    return null;
////                }else {
////                    int_B2=int_B;
////                    int_B+=int_F;
////                    if(int_B>=int_A)
////                    {
////                        int_B=int_A;
////
////                        string_B = string_A.substring(int_B2, int_B);
////                        // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"3" +"="+ string_B);
//////                        strings_A.add(string_B);
////                        return string_B;
////
////                    }
////
////
////                }
//
//            }
//            //
//        }else {
//            // //]Log.w(this.toString(), "getTextAdvancesR0: " +"="+"9" +"="+ string_A);
//            // strings_A.add(string_A);
//            return string_A;
//        }
//
//
//
//    }
    public static boolean ccUnicodeBlock(char char_A){

        Character.UnicodeScript unicodeScript=Character.UnicodeScript.of(char_A);
        // //]Log.w(this.toString(), "ccUnicodeBlockG1: "+"="+char_A +"="+ (unicodeScript==Character.UnicodeScript.HAN) +"="+(Character.isLetter(char_A))+"="+(Character.isDigit(char_A))+"="+(Character.isWhitespace(char_A)));
        if(unicodeScript==Character.UnicodeScript.HAN)
        {
            return true;
        }
        if(Character.isLetter(char_A))
        {
            return false;
        }
        if(Character.isDigit(char_A))
        {
            return true;
        }
        if(Character.isWhitespace(char_A))
        {
            return true;

        }

        return true;

    }
}
