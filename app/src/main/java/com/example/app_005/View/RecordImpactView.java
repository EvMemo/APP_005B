package com.example.app_005.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Class.RecordNewsData;
import com.example.app_005.Class.SkillFinacTool;

import java.util.ArrayList;

/**
 *鐵灰色
 * #625b57
 * 灰色
 * #808080
 * 岩灰
 * #708090
 * 濃藍
 * #006374
 * 孔雀藍
 * #00808c
 * 中度相关
 * 葉綠
 * #73b839
 * 暗鮭紅
 * #e9967a
 * 灰藍
 * #7ab8cc
 * 椰褐
 * #4d1f00
 * 紫丁香色
 * #b399ff
 * 较远相关
 * 月黃
 * #ffff4d
 * 鮮黃／黃色／黃色
 * #ffff00 / #ff0
 * 亮黃
 * #ffffe0
 * 白色
 * #ffffff / #fff
 * 象牙色
 * #fffff0
 */

public class RecordImpactView extends View {
    private int width;//设置高
    private int height;//设置高
    public int intTextH;
    Paint paintUI;
    Paint paintUIB;
    Paint paintUIC;
    Paint paintUID;
    Paint paintUIE;
    Paint paintUIH;
    Paint paintButA;
    Paint paintTextA;
    Paint paintTextB;
    Paint paintTextB2;
    Paint paintTextC;
    Paint paintTextD;
    RecordNewsTextSyster recordNewsTextSyster;
    NewsDataClass newsDataClass;
    RecoDrawDips recoDrawDips;
    boolean boolDips;
    boolean boolAdd;
    boolean boolEdit;
    boolean boolDraw;

    public RecordImpactView(Context context) {
        super(context);
    }

    public RecordImpactView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintUI = new Paint();
        paintUI.setColor(Color.parseColor("#20B2AA"));

        paintUIE = new Paint();
        paintUIE.setColor(Color.WHITE);

        paintButA = new Paint();
        paintButA.setColor(Color.parseColor("#F0FFFF"));
        paintTextA = new Paint();
        paintTextA.setAntiAlias(true);//消除锯齿
        paintTextA.setColor(Color.BLACK);
        paintTextA.setTextAlign(Paint.Align.LEFT);
        paintTextA.setTextSize(37);

        paintUIB = new Paint();
        paintUIB.setColor(Color.parseColor("#625b57"));

        paintUIC = new Paint();
        paintUIC.setColor(Color.parseColor("#1b57d0"));

        paintUID = new Paint();
        paintUID.setColor(Color.parseColor("#dce9eb"));

        paintTextB = new Paint();
        paintTextB.setAntiAlias(true);//消除锯齿
        paintTextB.setColor(Color.parseColor("#f1ebbd"));
        paintTextB.setTextAlign(Paint.Align.LEFT);
        paintTextB.setTextSize(35);

        paintTextB2 = new Paint();
        paintTextB2.setAntiAlias(true);//消除锯齿
        paintTextB2.setColor(Color.parseColor("#f1ebbd"));
        paintTextB2.setTextAlign(Paint.Align.CENTER);
        paintTextB2.setTextSize(35);

        paintTextC = new Paint();
        paintTextC.setAntiAlias(true);//消除锯齿
        paintTextC.setStyle(Paint.Style.FILL);
        paintTextC.setColor(Color.parseColor("#444f51"));
        paintTextC.setTextAlign(Paint.Align.LEFT);
        paintTextC.setTextSize(22);

        paintTextD = new Paint();
        paintTextD.setAntiAlias(true);//消除锯齿
        paintTextD.setColor(Color.BLACK);
        paintTextD.setTextAlign(Paint.Align.CENTER);
        paintTextD.setTextSize(34);


        paintUIH = new Paint();
        paintUIH.setColor(Color.parseColor("#f8fbfb"));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        //setViewH(500);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int int_A=10;
//        canvas.drawRoundRect(0,0,width,height,5,5,paintUI);
//        canvas.drawRoundRect(int_A,int_A+3,40+int_A,27+int_A+3,5,5,paintButA);
//        canvas.drawRoundRect(width-int_A-90-5,int_A+3,width-int_A-5,40+int_A+3,5,5,paintButA);
//        canvas.drawText("影响",60,38+3,paintTextA);
//        drawImpData(canvas,150,5+3,"USD",0,new String[]{"EUR","GBP","JPY"});
//        drawCrData(canvas,0,60);
        if (boolDraw) {
            if (recoDrawDips.boolDraw) {
                recoDrawDips.onDraw(canvas, 0, 0);
            }

        }
    }

    public void setNewsDataClass(NewsDataClass newsDataClass_A) {
        if (newsDataClass_A != null) {
            newsDataClass = newsDataClass_A;
            boolDraw = false;
            recoDrawDips = new RecoDrawDips();
            boolDraw = true;
            invalidate();
        }
    }
    void addSkillView() {
        if (!boolAdd) {
            boolAdd = true;
            recoDrawDips.openAddRecoFinView(true);
        }
    }

    void openTextRecordView(boolean bool_A) {
        if (recordNewsTextSyster != null) {
            recordNewsTextSyster.openTextRecordView();
        }

    }
    public void closeDrawView(){
        boolDraw=false;
        boolAdd=false;
    }

    void drawImpData(Canvas canvas_A, float float_W, float float_H, String string_A, int int_A, String[] strings_A) {
        float float_A = paintTextB.measureText(string_A) + 16;
        float flaot_B = 0;
        String string_IN = null;
        switch (1) {
            case 0:
                break;
            case 1:
                string_IN = "⬆⇧⬇≈";
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        if (string_IN != null) {
            flaot_B = paintTextB.measureText(string_IN) + 3;
        }
        float float_C = 0;
        String string_JI = null;
        if (strings_A == null) {


        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < strings_A.length; i++) {
                float_C += paintTextB.measureText((i == 0 ? "" : "•") + strings_A[i]) + 2;
                stringBuilder.append((i == 0 ? "" : "•") + strings_A[i]);
            }
            string_JI = stringBuilder.toString();
        }

        canvas_A.drawRoundRect(float_W, float_H, float_W + float_A + flaot_B + float_C, float_H + (paintTextB.getTextSize()) + 6, 5, 5, paintUIB);
        canvas_A.drawRoundRect(float_W + float_A + flaot_B - 8, float_H, float_W + float_A + flaot_B + float_C, float_H + (paintTextB.getTextSize()) + 6, 5, 5, paintUIC);
        canvas_A.drawText(string_A, float_W + 8, float_H + (paintTextB.getTextSize()) - 2, paintTextB);
        if (string_IN != null) {

            canvas_A.drawText(string_IN, float_W + float_A - 8 + 3, float_H + (paintTextB.getTextSize()) - 2, paintTextB);
        }
        if (string_JI != null) {

            canvas_A.drawText(string_JI, float_W + float_A - 8 + 3 + flaot_B + 2, float_H + (paintTextB.getTextSize()) - 2, paintTextB);
        }
        //Log.w(this.toString(), "drawImpData: E0"+"="+ float_W +"="+float_H +"="+float_A +"="+(paintTextB.getTextSize()/2F));


    }

    void drawCrData(Canvas canvas_A, float float_W, float float_H) {

        canvas_A.drawRoundRect(float_W + 10, float_H, width - 10, float_H + 200, 15, 15, paintUID);

        canvas_A.drawText("新建", float_W + 15, float_H + 40, paintTextC);
        float float_A = paintTextC.measureText("新建");
        String string_A = "USD";
        float float_B = paintTextB.measureText(string_A);
        canvas_A.drawRoundRect(float_W + 15 + float_A + 13, float_H + 8, float_W + 15 + float_A + 8 + 50 + float_B, float_H + paintTextB.getTextSize() + 20, 10, 10, paintUIB);
        canvas_A.drawText(string_A, float_W + 15 + float_A + 8 + 2 + 25, float_H + paintTextB.getTextSize() + 10, paintTextB);
        String string_IN = "⬆";
        canvas_A.drawRoundRect(float_W + 15 + float_A + 8 + 50 + float_B + 20, float_H + 8, float_W + 15 + float_A + 8 + 50 + float_B + 70, float_H + paintTextB.getTextSize() + 20, 10, 10, paintUIB);
        canvas_A.drawText(string_IN, float_W + 15 + float_A + 8 + 50 + float_B + 27, float_H + paintTextB.getTextSize() + 10, paintTextB);

        String string_JI = null;
        String[] string_JIs = new String[]{"EUR", "GBP", "JPY"};
        float float_C = 0;
        if (string_JIs == null) {


        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < string_JIs.length; i++) {
                String str_Ga = (i == 0 ? "" : "•") + string_JIs[i];
                float_C += paintTextB.measureText(str_Ga) + 2;
                stringBuilder.append(str_Ga);
            }
            string_JI = stringBuilder.toString();
        }
        if (string_JI != null) {
            canvas_A.drawRoundRect(float_W + 15 + float_A + 8 + 50 + float_B + 70 + 20, float_H + 8, float_W + 15 + float_A + 8 + 50 + float_B + 70 + 20 + float_C + 15, float_H + paintTextB.getTextSize() + 20, 10, 10, paintUIC);
            canvas_A.drawText(string_JI, float_W + 15 + float_A + 8 + 50 + float_B + 70 + 20 + 10, float_H + paintTextB.getTextSize() + 10, paintTextB);

            float_C += 35;
        }
        canvas_A.drawRoundRect(width - 130, float_H + 8, width - 30, float_H + paintTextB.getTextSize() + 20, 17, 17, paintUIE);
        canvas_A.drawRoundRect(width - 230 - 15, float_H + 8, width - 130 - 15, float_H + paintTextB.getTextSize() + 20, 17, 17, paintUIE);
        canvas_A.drawRoundRect(width - 230 - 15 - 100 - 15, float_H + 8, width - 130 - 15 - 100 - 15, float_H + paintTextB.getTextSize() + 20, 17, 17, paintUIE);
        //canvas_A.drawRoundRect(float_W + 15 + float_A + 8 + 50 + float_B+70+20+float_C, float_H + 8, float_W + 15 + float_A + 8 + 50 + float_B+70+20+50+float_C, float_H + paintTextB.getTextSize() + 20, 10, 10, paintUIC);
        //canvas_A.drawText("+",float_W + 15 + float_A + 8 + 50 + float_B+70+20+float_C+15,float_H + paintTextB.getTextSize() + 10,paintTextB);
        canvas_A.drawRoundRect(float_W + 20, float_H + 70, width - 20, float_H + 200 - 10, 13, 13, paintButA);

        canvas_A.drawRoundRect(width / 2F - 50, (float_H + 200 - 10) - 50, width / 2F + 50, (float_H + 200 - 10) - 10, 20, 20, paintUID);
        canvas_A.drawText("+", width / 2F - (paintTextA.getTextSize() / 4F), (float_H + 200 - 10) - 53 + paintTextA.getTextSize(), paintTextA);


//        canvas_A.drawRoundRect(float_W+20+10,(float_H+70)+10,float_W+20+10+80,(float_H+70)+10+40,14,14,paintUID);
//        canvas_A.drawText("+",float_W+20+10+(35)+5,(float_H+70)+10+paintTextD.getTextSize()-3,paintTextD);
//
//
//        canvas_A.drawRoundRect(float_W+20+10,(float_H+70)+20+40,float_W+20+10+80,(float_H+70)+20+40+40,14,14,paintUID);
//        canvas_A.drawText("+",float_W+20+10+(35)+5,(float_H+70)+20+40+paintTextD.getTextSize()-3,paintTextD);

        String[] strings_V = new String[]{"无", "USD", "USD", "GBP", "JPY"};
        float float_Ka = 500;
        float float_Kb = 0;
        float float_KW = float_W + 20 + 10;
        float float_KH = (float_H + 70) + 10;
        float float_KHv = paintTextD.getTextSize() + 10;
        int int_Ka = 0;
        for (int i = 0; i < 1; i++) {
            for (int e = 0 + int_Ka; e < strings_V.length; e++) {
                String string_Ka = strings_V[e];
                float float_Kc = paintTextD.measureText(string_Ka) + 30;
                if (float_Kb + (float_Kc + 30) < float_Ka) {

                    canvas_A.drawRoundRect(float_KW + float_Kb, float_KH, float_KW + float_Kb + float_Kc + 30, float_KH + float_KHv, 14, 14, paintUID);
                    canvas_A.drawText(string_Ka, float_KW + float_Kb + (float_Kc / 2F), float_KH + float_KHv - 10, paintTextD);
                    float_Kb += float_Kc + 30 + 30;
                } else {
                    i--;
                    float_KH += float_KHv + 13;
                    float_Kb = 0;
                    int_Ka = e;
                    break;
                }
            }
        }

    }

    public void setRecordNewsTextSyster(RecordNewsTextSyster recordNewsTextSyster_A) {
        recordNewsTextSyster = recordNewsTextSyster_A;
    }

    public void setTextH(int int_A) {
        intTextH = int_A;

    }

    void setViewH(int float_H) {
        ConstraintLayout.LayoutParams layoutParams_A = (ConstraintLayout.LayoutParams) this.getLayoutParams();
        Log.w(this.toString(), "setViewWH: E0" + "=" + layoutParams_A.width + "=" + layoutParams_A.height + "=" + width + "=" + height);
        layoutParams_A.height = float_H;
        this.setLayoutParams(layoutParams_A);
    }



    void onTouch(float float_W, float float_H) {
        if (float_W > (width - 105) && float_W < (width - 15)) {
            if (float_H > 13 && float_H < 53) {
                //Log.w(this.toString(), "drawImpData: E1"+"="+float_W +"="+float_H );
                addSkillView();
                //setViewWH();
                //this.setVisibility(View.GONE);

            }

        }

    }

    float floatDownX;
    float floatDownY;
    float floatDownTime;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

                return true;
            case MotionEvent.ACTION_DOWN:
                floatDownX = event.getX();
                floatDownY = event.getY();
                floatDownTime = System.currentTimeMillis();
                return true;
            case MotionEvent.ACTION_UP:
                float float_X = event.getX();
                float float_Y = event.getY();
                onTouch(float_X, float_Y);

                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        //Log.w(this.toString(), "DOKDonTouchEventD1: "+"="+event.getAction() );
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    class RecoDrawDips {
        boolean boolDraw;
        boolean boolData;
        float floatViewH;
        float floatDataH;
        float floatDataH2;
        float floatDataH3;
        DrawData[] drawData;
        float[] floatViewCat;
        DrawBanji drawBanji;


        public RecoDrawDips() {
            boolDips = true;
            drawBanji=new DrawBanji();
            if (true)//||newsDataClass.recordNewsData!=null&&newsDataClass.recordNewsData.recordNewsImpactData.length!=0)
            {
                newsDataClass.recordNewsData = new RecordNewsData();
                newsDataClass.recordNewsData.initCC();
            }
            if (newsDataClass.recordNewsData.recordNewsImpactData != null && newsDataClass.recordNewsData.recordNewsImpactData.length != 0) {
                boolData = true;
                floatViewCat = new float[4];
                floatViewCat[0] = 150;
                floatViewCat[1] = 8;
                floatViewCat[2] = 1080 - 160;
                floatViewCat[3] = 8 + paintTextA.getTextSize();
                ccData(newsDataClass, boolDips);
                floatDataH = floatViewCat[3] + 10;
                setViewH((int) floatDataH);

            } else {

                boolData = false;
            }
            Log.w(this.toString(), "RecoDrawDips: R0" + "=" + drawData.length + "=" + newsDataClass.recordNewsData.recordNewsImpactData.length);
            boolDraw = true;

        }

        void ccData(NewsDataClass newsDataClass_A, boolean boolDips_A) {
            int int_A;
            float float_W = 150;
            float float_H = 0;
            ArrayList<DrawData> drawDatas_A = new ArrayList<>();
            Log.w(this.toString(), "ccData: H0" + "=" + newsDataClass_A.recordNewsData.recordNewsImpactData.length);
            for (int i = 0; i < newsDataClass_A.recordNewsData.recordNewsImpactData.length; i++) {
                String string_A = null;
                StringBuilder stringBuilder_A = new StringBuilder();
                RecordNewsData.RecordNewsImpactData recordNewsImpactData_A = newsDataClass_A.recordNewsData.recordNewsImpactData[i];
                float float_A = 0;
                stringBuilder_A.append(recordNewsImpactData_A.skillFinacName.stringName);
                DrawData drawData_A;
                Log.w(this.toString(), "ccData: H1" + "=" + boolDips_A + "=" + (recordNewsImpactData_A.skillFinacNamesB != null && recordNewsImpactData_A.skillFinacNamesB.length != 0) + "=" + ((floatViewCat[2] - floatViewCat[0])));
                if (false && boolDips_A) {

                    float_A = paintTextB2.measureText(stringBuilder_A.toString());
                    Log.w(this.toString(), "ccData: H3a" + "=" + (float_A + 20 + 10) + "=" + (floatViewCat[2] - floatViewCat[0]));
                    if ((float_A + 20 + 10) > (floatViewCat[2] - floatViewCat[0])) {
                        break;
                    } else {
                        float[] floats_G = new float[5];
                        floats_G[0] = floatViewCat[0] + 10;
                        floats_G[3] = floatViewCat[0] + (float_A + 20 + 10);
                        floats_G[1] = floatViewCat[1];
                        floats_G[3] = floatViewCat[3];
                        drawData_A = new DrawData(0, i, floats_G, stringBuilder_A.toString(), recordNewsImpactData_A);
                    }
                } else {

                    if (recordNewsImpactData_A.intMode != 0) {

                        stringBuilder_A.append(" " + recordNewsImpactData_A.getModeString());
                    }
                    float_A = paintTextB2.measureText(stringBuilder_A.toString());
                    if (recordNewsImpactData_A.skillFinacNamesB != null && recordNewsImpactData_A.skillFinacNamesB.length != 0) {
                        for (int e = 0; e < recordNewsImpactData_A.skillFinacNamesB.length; e++) {
                            stringBuilder_A.append((e == 0 ? " " : "•") + recordNewsImpactData_A.skillFinacNamesB[e].stringName);
                        }
                        float float_B = paintTextB2.measureText(stringBuilder_A.toString());

                        Log.w(this.toString(), "ccData: H3b" + "=" + (float_B + 30 + 10) + "=" + (floatViewCat[2] - floatViewCat[0]));
                        if ((float_B + 30 + 10) > (floatViewCat[2] - floatViewCat[0])) {
                            floatViewCat[1] += 8 + paintTextA.getTextSize() + 5;
                            floatViewCat[3] += 8 + paintTextA.getTextSize() + 5;
                            floatViewCat[0] = 10;
                            floatViewCat[2] = 1080 - 70;
                        }
                        float[] floats_G = new float[5];
                        floats_G[0] = floatViewCat[0] + 10;
                        floats_G[2] = floatViewCat[0] + (float_B + 30 + 10);
                        floats_G[1] = floatViewCat[1];
                        floats_G[3] = floatViewCat[3];
                        floats_G[4] = floatViewCat[0] + (float_A + 25);
                        drawData_A = new DrawData(1, i, floats_G, stringBuilder_A.toString(), recordNewsImpactData_A);
                        floatViewCat[0] = floats_G[2];
                    } else {
                        Log.w(this.toString(), "ccData: H3cb" + "=" + (float_A + 20 + 10) + "=" + (floatViewCat[2] - floatViewCat[0]));
                        if ((float_A + 20 + 10) > (floatViewCat[2] - floatViewCat[0])) {
                            floatViewCat[1] += 8 + paintTextA.getTextSize() + 5;
                            floatViewCat[3] += 8 + paintTextA.getTextSize() + 5;
                            floatViewCat[0] = 10;
                            floatViewCat[2] = 1080 - 70;
                        }
                        //DrawData drawData_A=new DrawData(1,newsDataClass_A.intID,)
                        float[] floats_G = new float[5];
                        floats_G[0] = floatViewCat[0] + 10;
                        floats_G[2] = floatViewCat[0] + (float_A + 20 + 10);
                        floats_G[1] = floatViewCat[1];
                        floats_G[3] = floatViewCat[3];
                        drawData_A = new DrawData(0, i, floats_G, stringBuilder_A.toString(), recordNewsImpactData_A);
                        floatViewCat[0] = floats_G[2];
                    }

                }
                drawDatas_A.add(drawData_A);
            }
            Log.w(this.toString(), "RecoDrawDips.ccData: E0" + "=" + drawDatas_A.size());
            drawData = drawDatas_A.toArray(new DrawData[drawDatas_A.size()]);
        }

        public void openAddRecoFinView(boolean bool_A) {
            if (bool_A) {
                floatDataH2=drawBanji.initData(null);
                setViewH((int) (floatDataH+floatDataH2+10));
            } else {

            }
            invalidate();

        }

        public void openEditRecoFinView(boolean bool_A,DrawData drawData_A) {
            if (bool_A) {
                floatDataH2=drawBanji.initData(drawData_A);
                setViewH((int) (floatDataH+floatDataH2+10));
            } else {

            }
            invalidate();
        }

        public float onDraw(Canvas canvas_A, float float_W, float float_H) {
            int int_A = 10;
            if (boolDips) {
                canvas_A.drawRoundRect(0, 0, width, height, 5, 5, paintUI);
                canvas_A.drawRoundRect(float_W, float_H, float_W + width, float_H + floatViewH, 5, 5, paintUI);
                canvas_A.drawRoundRect(float_W + int_A, float_H + int_A + 3, float_W + 40 + int_A, float_H + 27 + int_A + 3, 5, 5, paintButA);
                canvas_A.drawRoundRect(float_W + width - int_A - 90 - 5, float_H + int_A + 3, float_W + width - int_A - 5, float_H + 40 + int_A + 3, 5, 5, paintButA);
                canvas_A.drawText("影响", float_W + 60, float_H + 38 + 3, paintTextA);
                // canvas_A.drawLine(0,20,1080-70,20,paintUIC);
                //drawImpData(canvas,150,5+3,"USD",0,new String[]{"EUR","GBP","JPY"});
                for (int i = 0; i < drawData.length; i++) {
                    drawData[i].onDraw(canvas_A, float_W, float_H);

                }
                if(drawBanji.boolDraw)
                    {
                        Log.w(this.toString(), "RecoFin.onDraw: E0"+"="+floatDataH );
                        drawBanji.onDraw(canvas_A, float_W, float_H+floatDataH);

                    }
                //drawB.onDraw(canvas_A,float_W,float_H+floatDataH);
            } else {
                canvas_A.drawRoundRect(float_W, float_H, float_W + width, float_H + floatViewH, 5, 5, paintUI);

                canvas_A.drawRoundRect(float_W + int_A, float_H + int_A + 3, float_W + 40 + int_A, float_H + 27 + int_A + 3, 5, 5, paintButA);
                canvas_A.drawRoundRect(float_W + width - int_A - 90 - 5, float_H + int_A + 3, float_W + width - int_A - 5, float_H + 40 + int_A + 3, 5, 5, paintButA);
                canvas_A.drawText("影响", float_W + 60, float_H + 38 + 3, paintTextA);
            }
            return 0;

        }

        class DrawData {
            int intMode;
            int intID;
            float[] floatWH;
            String stringText;
            RecordNewsData.RecordNewsImpactData recordNewsImpactData;

            public DrawData(int intMode_A, int intID_A, float[] floatWH_A, String stringText_A, RecordNewsData.RecordNewsImpactData recordNewsImpactData_A) {
                recordNewsImpactData = recordNewsImpactData_A;
                intMode = intMode_A;
                intID = intID_A;
                floatWH = floatWH_A;
                stringText = stringText_A;
                Log.w(this.toString(), "DrawData: J0" + "=" + floatWH_A.length + "=" + (floatWH_A[0]) + "=" + floatWH_A[2]);
            }

            public void onDraw(Canvas canvas_A, float float_W, float float_H) {
                if (intMode == 0) {
                    canvas_A.drawRoundRect(float_W + floatWH[0], float_H + floatWH[1], float_W + floatWH[2], float_H + floatWH[3], 5, 5, paintUIB);
                    //canvas_A.drawRoundRect(float_W,float_H,float_W,float_H,5,5,paintUIC);
                    canvas_A.drawText(stringText, float_W + floatWH[0] + ((floatWH[2] - floatWH[0]) / 2F), float_H + floatWH[3] - 5, paintTextB2);

                } else if (intMode == 1) {
                    canvas_A.drawRoundRect(float_W + floatWH[0], float_H + floatWH[1], float_W + floatWH[2], float_H + floatWH[3], 5, 5, paintUIB);
                    canvas_A.drawRoundRect(float_W + floatWH[4], float_H + floatWH[1], float_W + floatWH[2], float_H + floatWH[3], 5, 5, paintUIC);
                    canvas_A.drawText(stringText, float_W + floatWH[0] + ((floatWH[2] - floatWH[0]) / 2F), float_H + floatWH[3] - 5, paintTextB2);
                } else {

                }
            }
        }

        class DrawBanji {
            public int intMode;
            public String stringMode;
            public String stringName;
            public SkillFinacTool.SkillFinacName skillFinacName;
            public int intModeEdit;
            public String stringModeEdit;
            public String stringNameB;
            public SkillFinacTool.SkillFinacName[] skillFinacNamesB;
            DrawData drawData;
            DrawBanjiSel drawBanjiSel;
            float floatDataH;
            float floatDataH2;
            float floatDataH3;
            public float[] floatsA, floatsB, floatsC;
            public boolean boolDraw;

            public DrawBanji() {
                boolDraw=false;
                drawBanjiSel=new DrawBanjiSel();
            }

//            public DrawBanji(DrawData drawData_A) {
//                intMode = 1;
//                drawData = drawData_A;
//                intModeEdit = drawData.recordNewsImpactData.intMode;
//                skillFinacName = drawData.recordNewsImpactData.skillFinacName;
//                stringName = skillFinacName.toString();
//                skillFinacNamesB = drawData.recordNewsImpactData.skillFinacNamesB;
//                if (skillFinacNamesB != null && skillFinacNamesB.length != 0) {
//                    StringBuilder stringBuilder_A = new StringBuilder();
//                    for (int i = 0; i < skillFinacNamesB.length; i++) {
//                        stringBuilder_A.append((i == 0 ? " " : "•") + skillFinacNamesB[i].toString());
//                    }
//                    stringNameB = stringBuilder_A.toString();
//
//                }
//                floatDataH=ccData();
//            }
            public float initData(DrawData drawData_A){
                if(drawData_A==null)
                    {
                        intMode = 0;
                        stringName = null;
                        intModeEdit = 0;
                        skillFinacNamesB = null;
                        drawData = null;
                        floatDataH=ccData();
                    }else {
                    intMode = 1;
                    drawData = drawData_A;
                    intModeEdit = drawData.recordNewsImpactData.intMode;
                    skillFinacName = drawData.recordNewsImpactData.skillFinacName;
                    stringName = skillFinacName.toString();
                    skillFinacNamesB = drawData.recordNewsImpactData.skillFinacNamesB;
                    if (skillFinacNamesB != null && skillFinacNamesB.length != 0) {
                        StringBuilder stringBuilder_A = new StringBuilder();
                        for (int i = 0; i < skillFinacNamesB.length; i++) {
                            stringBuilder_A.append((i == 0 ? " " : "•") + skillFinacNamesB[i].toString());
                        }
                        stringNameB = stringBuilder_A.toString();

                    }

                }
                drawBanjiSel.setDrawData(drawData);
                floatDataH=ccData()+10;
                floatDataH2=drawBanjiSel.ccData();
                if(true)
                    {
                        floatDataH3=50;

                    }
                boolDraw=true;
                return floatDataH+floatDataH2+floatDataH3;
            }
            public float ccData() {
                float float_Vh = 0;
                stringMode = (intMode == 0 ? "新建" : "BB");
                float floatStringMode_B = paintTextC.measureText(stringMode);
                if (stringName != null) {
                    float floatStringName_A = paintTextB.measureText(stringName);
                    floatsA = new float[4];
                    floatsA[0] = floatStringMode_B + 30;
                    floatsA[1] = 8;
                    floatsA[2] = 30 + floatStringName_A + floatStringMode_B + 30;
                    floatsA[3] = 16 + paintTextB.getTextSize();

                } else {
                    floatsA = new float[4];
                    floatsA[0] = 10 + floatStringMode_B + 30;
                    floatsA[1] = 8;
                    floatsA[2] = 20 + 100 + floatStringMode_B + 30;
                    floatsA[3] = 16 + paintTextB.getTextSize();
                }
                stringModeEdit = getModeString(intModeEdit);
                float floatModeEdit_A = paintTextB.measureText(stringModeEdit);
                floatsB = new float[4];
                floatsB[0] = floatsA[2] + 20;
                floatsB[1] = 8;
                floatsB[2] = floatsA[2] + 60 + floatModeEdit_A;
                floatsB[3] = 16 + paintTextB.getTextSize();
                if (stringNameB != null) {
                    //stringModeEdit=drawData.recordNewsImpactData.getModeString(intModeEdit);
                    float floatNameB_A = paintTextB.measureText(stringNameB);
                    floatsC = new float[4];
                    floatsC[0] = floatsB[2] + 20;
                    floatsC[1] = 8;
                    floatsC[2] = floatsB[2] + 30 + floatNameB_A;
                    floatsC[3] = 16 + paintTextB.getTextSize();
                } else {
                    floatsC = new float[4];
                    floatsC[0] = floatsB[2] + 20;
                    floatsC[1] = 8;
                    floatsC[2] = floatsB[2] + 40 + 100;
                    floatsC[3] = 16 + paintTextB.getTextSize();
                }

                return floatsC[3]+10;
            }


            public void setSkillMain(SkillFinacTool.SkillFinacName skillFinacName_A) {
                if (skillFinacName_A != null) {
                    skillFinacName = skillFinacName_A;
                    stringName = skillFinacName.toString();
                } else {
                    skillFinacName = null;
                }


            }

            public void setIntModeEdit(int intModeEdit_A) {
                switch (intModeEdit_A) {
                    case 0:
                        break;
                    default:
                        break;
                }
            }

            public void setSkillFinacNamesB(SkillFinacTool.SkillFinacName[] skillFinacNamesB_A) {
                if (skillFinacNamesB_A != null) {
                    skillFinacNamesB = skillFinacNamesB_A;
                    if (skillFinacNamesB != null && skillFinacNamesB.length != 0) {
                        StringBuilder stringBuilder_A = new StringBuilder();
                        for (int i = 0; i < skillFinacNamesB.length; i++) {
                            stringBuilder_A.append((i == 0 ? " " : "•") + skillFinacNamesB[i].toString());
                        }
                        stringNameB = stringBuilder_A.toString();

                    }
                } else {
                    skillFinacNamesB = null;
                }
            }

            public void onDraw(Canvas canvas_A, float float_W, float float_H) {
                canvas_A.drawRoundRect(float_W+10,float_H,width-10,float_H+floatDataH2+floatDataH-10,15,15,paintUID);
                canvas_A.drawText(stringMode, float_W + 20, float_H + paintTextC.getTextSize() + 16, paintTextC);
                canvas_A.drawRoundRect(float_W+floatsA[0], float_H+floatsA[1], float_W +floatsA[2], float_H+floatsA[3], 10, 10, paintUIB);
                canvas_A.drawRoundRect(float_W+floatsB[0], float_H+floatsB[1], float_W +floatsB[2], float_H+floatsB[3], 10, 10, paintUIB);
                canvas_A.drawRoundRect(float_W+floatsC[0], float_H+floatsC[1], float_W +floatsC[2], float_H+floatsC[3], 10, 10, paintUIC);
                canvas_A.drawText((stringName == null ? "" : stringName), float_W+floatsA[0] + 10, float_H+floatsA[3] - 8, paintTextB);
                canvas_A.drawText((stringModeEdit == null ? "" : stringModeEdit), float_W+floatsB[0] + 10, float_H+floatsB[3] - 8, paintTextB);
                canvas_A.drawText((stringNameB == null ? "" : stringNameB), float_W+floatsC[0] + 10, float_H+floatsC[3] - 8, paintTextB);
                if(drawBanjiSel.boolDraw)
                    {
                        drawBanjiSel.onDraw(canvas_A,float_W,float_H+floatDataH);

                    }


            }
            public  String getModeString(int int_A){
                switch(int_A){
                    case 0:
                        return " ";
                    case 1:
                        return "⬆";
                    case 2:
                        return "⬇";
                    case 3:
                        return "≈";
                    default:
                        break;
                }
                return "";
            }
            class DrawBanjiSel{
                public boolean boolDraw;
                public int intMode;
                public float floatDataView;
                public DrawBjSelData[] drawBjSelData;
                public DrawData drawData;
                float[] floatSelCat;
                public DrawBanjiSel(){
                    drawBjSelData=new DrawBjSelData[SkillFinacTool.St_FinacNames.length];
                    for(int i=0;i<SkillFinacTool.St_FinacNames.length;i++)
                        {
                            drawBjSelData[i]=new DrawBjSelData(SkillFinacTool.St_FinacNames[i]);
                            //Log.w(this.toString(), "DrawBanjiSel: F0"+"="+ );
                        }
                    Log.w(this.toString(), "DrawBanjiSel: I0"+"="+drawBjSelData.length );
                }
                public void setDrawData(DrawData drawData_A){
                    drawData=drawData_A;
                }
                public void setIntMode(int intMode_A){
                    intMode=intMode_A;
                }
                public void ccDataBoolL(){


                }
                public float ccData(){
                    floatSelCat=new float[4];
                    floatSelCat[0]=30;
                    floatSelCat[1]=20;
                    floatSelCat[2]=1080-70;
                    floatSelCat[3]=30+paintTextB.getTextSize();
                    float float_A=0;
                    //Log.w(this.toString(), "DrawBanjiSel.ccData: E0"+"="+intMode +"="+ drawBjSelData.length);
                    if(true||intMode==0)
                    {
                        for(int i=0;i<drawBjSelData.length;i++)
                        {
                            float_A=paintTextB.measureText(drawBjSelData[i].stringName);
                            if((float_A+20+30+40)>(floatSelCat[2]-floatSelCat[0]))
                            {
                                floatSelCat[0]=30;
                                floatSelCat[1]=floatSelCat[3]+15;
                                floatSelCat[2]=1080-70;
                                floatSelCat[3]+=25+paintTextB.getTextSize();
                            }

                            drawBjSelData[i].setFloatP(new float[]{floatSelCat[0]+20,floatSelCat[1],floatSelCat[0]+float_A+50+40,floatSelCat[3]});
                            //Log.w(this.toString(), "DrawBanjiSel.ccData: E1"+"="+floatSelCat[0]+10 +"="+floatSelCat[1] +"="+floatSelCat[0]+float_A+30 +"="+floatSelCat[3]);
                            floatSelCat[0]+=float_A+50+40;
                            // floatSelCat[1]=floatSelCat[3]+8;
                            // floatSelCat[2]=1080-20;
                            // floatSelCat[3]=8+paintTextB.getTextSize();
                        }


                    }else {

                    }
                    boolDraw=true;
                    floatDataView=floatSelCat[3]+10;
                    return floatDataView;

                }
                public void onDraw(Canvas canvas_A, float float_W, float float_H){
                    canvas_A.drawRoundRect(float_W+20,float_H+10,width-20,float_H+floatDataH2,15,15,paintUIH);
                    canvas_A.drawLine(30,50,1080-70,50,paintTextA);
                    for(int i=0;i<drawBjSelData.length;i++)
                        {
                            drawBjSelData[i].onDraw(canvas_A,float_W,float_H,paintUIC);
                        }

                }


                class DrawBjSelData{
                    public SkillFinacTool.SkillFinacName skillFinacName;
                    public String stringName;
                    public float[] floatP;
                    public boolean boolL;
                    public DrawBjSelData(SkillFinacTool.SkillFinacName skillFinacName_A){
                        skillFinacName=skillFinacName_A;
                        stringName=skillFinacName.stringName;
                       // Log.w(this.toString(), "DrawBjSelData: E0"+"="+ stringName);
                    }
                    public void onDraw(Canvas canvas_A,float float_W,float float_H,Paint paint_A){
                        canvas_A.drawRoundRect(float_W+floatP[0],float_H+floatP[1],float_W+floatP[2],float_H+floatP[3],10,10,paint_A);
                        canvas_A.drawText(stringName,float_W+floatP[0]+10, float_H+floatP[3]-10,paintTextB);
                    }
                    void setFloatP(float[] floatP_A){
                        floatP=floatP_A;
                    }
                    void setBoolL(boolean boolL_A){
                        boolL=boolL_A;
                    }

                }

            }

        }

    }

}
