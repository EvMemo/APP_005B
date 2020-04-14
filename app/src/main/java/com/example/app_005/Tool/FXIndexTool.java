package com.example.app_005.Tool;

import android.util.Log;

import com.example.app_005.Interface.OnGetQuoData;
import com.example.app_005.Interface.OnUpdateQuoData;

/**
 * The type Fx index tool.
 */
public  class FXIndexTool implements OnUpdateQuoData {
    FXDataConnect.QuoConnect quoConnect;
    public FXIndexTool(FXDataConnect fxDataConnect){
        quoConnect=fxDataConnect.getQuoConnect(this);
    }
    public void initIndexData(){

    }
    /**
     *相对强度指数（RSI）由J.Welles Wilder开发，是一种动量指标，用于衡量价格变动的速度和变化。
     * RSI在0到100之间振荡。根据Wilder的说法，RSI在70以上时被视为超买，在30以下时被视为超卖。
     * 也可以通过寻找发散，故障摆动和中心线交叉来生成信号。RSI也可以用于识别总体趋势。
     */
    public void IndexQuoDData(FXDataPool.FXQuo.QuoDData quoDDataAA){

    }
    @Override
    public void onUpdateQuoUI(int intUI) {

    }
    public static float IndexRSI(float[] floats){
        float float_rt=0;
        float float_A=0;
        float float_B=0;
        float float_C=0;
        //0.0178571428571429
        //0.0385714285714286
        //0.0135714285714286
        for(int i=1;i<floats.length;i++)
        {


            float_C=floats[i]-floats[i-1];
            if(i==1)
            {
                // Log.w("FXIndexTool", "InedexRSIA2: "+"="+float_C +"="+floats[i] +"="+floats[i-1]);
            }
            if(float_C<0)
            {
                float_B-=float_C;
            }else {
                float_A+=float_C;
            }

        }


        float_A/=floats.length;
        float_B/=floats.length;
        // Log.w("FXIndexTool", "InedexRSIA1: "+"="+float_A +"="+float_B);
        float_rt=100-(100/(1+(float_A/float_B)));
        // Log.w("FXIndexTool", "InedexRSIV0: "+"="+float_rt);
        return float_rt;
    }

    /**
     * Index rsi simpe float.
     *
     * @param floatRX {先前的平均增益,先前的平均损失}
     * @param floatN  {昨天价格，今天价格}
     * @return the float
     */
    public float IndexRSISimpe(float[] floatRX,float[] floatN){
        float float_rt=0;
        float float_F=floatN[1]-floatN[0];
        float float_A=(floatRX[0]*13F);
        float float_A2=(floatRX[1]*13F);
        if(float_F<0)
            {
                float_A2-=float_F;
            }else {
            float_A+=float_F;
        }
        float_A/=14F;
        float_A2/=14F;
        float_rt=100-(100/(1+(float_A/float_A2)));
        return  float_rt;

    }
    /**
     *随机震荡指标由乔治·莱恩（George C. Lane）在1950年代后期开发，是一种动量指标，可显示一定时期内相对于高低范围的收盘价位。
     * 根据对莱恩的采访，随机振荡器“不跟随价格，不跟随数量或类似价格。它跟随价格的速度或动量。
     * 因此，随机振荡器的看涨和看跌背离可以用来预示反转。这是Lane确定的第一个也是最重要的信号。
     * 莱恩还使用此振荡器来确定牛市和熊市的设置，以预期将来会逆转。由于随机震荡指标是区间震荡指标，因此对于确定超买和超卖水平也很有用。
     */
    public float IndexSTOCH_K(int intNum,float[] floats){
        float[] floats_rt=new float[2];
        float float_N=floats[intNum-1];
        float float_U=float_N;
        float float_D=float_N;
        for(int i=0;i<floats.length;i++)
            {
                if(floats[i]>float_U)float_U=floats[i];
                if(floats[i]<float_D)float_D=floats[i];
            }
        return ((float_N-float_D)/(float_U-float_D))*100F;
    }
    /**
     *％D是％K 的3天简单移动平均线。此线与％K并排绘制，以用作信号线或触发线。
     */
    public void IndexSTOCH_D(){

    }
    /**
     *StochRSI由Tushar Chande和Stanley Kroll开发，是一种振荡器，可以在设定的时间内测量RSI相对于其高低范围的水平。
     * StochRSI将随机指标公式应用于RSI值，而不是价格值，使其成为指标的指标。结果是振荡器在0到1之间波动。
     */
    public void IndexStochRSI(){
        /**
         *StochRSI在设定的周期数内测量RSI相对于其高/低范围的值。
         * 用于计算StochRSI的周期数在公式中转移到RSI。
         * 例如，14天的StochRSI将使用14天RSI的当前值和14天RSI的14天高-低范围。
         */

    }
    public float IndexSMA(float[] floats){
        float float_A=0;
        for(int i=0;i<floats.length;i++)
            {
                float_A+=floats[i];
            }
        return (float_A/floats.length);

    }
    /**
     *指数平均数指标也叫EXPMA指标，它也是一种趋向类指标，
     * 其构造原理是仍然对价格收盘价进行算术平均，并根据计算结果来进行分析，用于判断价格未来走势的变动趋势。
     */
    public float IndexEMA(float floatAA,float floatBB,int intNum){
        if(true)
            {
                return (floatAA*(1/intNum))+(floatBB*((intNum-1)/intNum));

            }
        if(false)
            { float float_K=2/(intNum+1);//0.33333..0.666 ..0.667
                return (floatAA*float_K)+(floatBB*(1-float_K));

            }
        return 0;


    }
    /**
     *移动平均收敛/发散振荡器（MACD）由杰拉尔德·阿佩尔（Gerald Appel）在七十年代后期开发，是目前可用的最简单，最有效的动量指标之一。
     * MACD 通过从较短的移动平均线中减去较长的移动平均线，将两个趋势追踪指标移动平均线转换为动量振荡器。
     * 结果，MACD提供了两全其美的方法：趋势追随和动力。随着移动平均线收敛，交叉和发散，MACD在零线上方和下方波动。
     * 交易者可以寻找信号线交叉，中心线交叉和分歧以生成信号。由于MACD不受限制，因此对于识别超买和超卖水平不是特别有用。
     * {EMA12,EMA26,EMA9}
     */
    public void IndexMACD(float float12,float float26,float float9){
        float[] floats_rt=new float[3];
        floats_rt[0]=(float12-float26);//MACD线
        floats_rt[1]=float9;//信号线
        floats_rt[2]=floats_rt[0]-floats_rt[1];//MACD直方图

    }
    /**
     *平均方向指标（ADX），负方向指标（-DI）和正方向指标（+ DI）代表一组方向运动指标，这些指标构成由Welles Wilder开发的交易系统。
     * 尽管怀尔德在设计自己的定向运动系统时会考虑商品和每日价格，但这些指标也可以应用于股票。
     */
    public void IndexADX(float float12,float float26,float float9){


    }
    public float IndexATR(float[][] floats){//大小关
        float[] floats_A=new float[3];
        float float_mo=0;
        for(int i=0;i<floats.length-1;i++)
        {
            floats_A[0]=Math.abs(floats[i][0]-floats[i][1]);

            if(i!=0)
                {
                    floats_A[1]=Math.abs(floats[i][0]-floats[i-1][2]);
                    if(floats_A[1]>floats_A[0])floats_A[0]=floats_A[1];
                    floats_A[2]=Math.abs(floats[i][1]-floats[i-1][2]);
                    if(floats_A[2]>floats_A[0])floats_A[0]=floats_A[2];

                }
            float_mo+=floats_A[0];

        }
        return float_mo/floats.length;

    }

    public float IdxTR(float floatNHigh,float floatNLow,float floatLClose){
        float[] floats_A=new float[3];

        floats_A[0]=Math.abs(floatNHigh-floatNLow);
        float float_rt=floats_A[0];
        floats_A[1]=Math.abs(floatNHigh-floatLClose);
        if(floats_A[1]>float_rt)float_rt=floats_A[1];
        floats_A[2]=Math.abs(floatNLow-floatLClose);
        if(floats_A[2]>float_rt)float_rt=floats_A[2];

        return float_rt;

    }
    public float[] IdxDM(float floatNHigh,float floatNLow,float floatLHigh,float floatLLow){
        float[] flaots_rt;
        float float_A=floatLHigh-floatNHigh;
        if(float_A>0)
            {
                flaots_rt=new float[]{float_A,0};
                return  flaots_rt;
            }
        float float_B=floatLLow-floatNLow;
        if(float_B<0)
        {
            flaots_rt=new float[]{0,float_B};
            return  flaots_rt;
        }
        flaots_rt=new float[]{0,0};
        return  flaots_rt;
    }
    public float IdxDI14(float floatDM14){return (floatDM14/14F)*100F;}


}
