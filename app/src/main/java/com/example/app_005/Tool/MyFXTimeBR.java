package com.example.app_005.Tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 闹钟广播
 */
public class MyFXTimeBR extends BroadcastReceiver {
    Context context;
    IFXTimeBR ifxTimeBR;
    public MyFXTimeBR(Context contextAA,IFXTimeBR ifxTimeBRAA){
        context=contextAA;
        ifxTimeBR=ifxTimeBRAA;
        IntentFilter intentFilter_A= new IntentFilter();
        intentFilter_A.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this,intentFilter_A);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ifxTimeBR.PI_timeAddin();
    }
    /**
     *注册广播
     */
    public void PuC_signBroadcastReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        // 2. 设置接收广播的类型
        intentFilter.addAction("TimeOne");
        context.registerReceiver(this,intentFilter);

    }

    /**
     * 销毁广播
     */
    protected void Puc_onDestroy() {
        context.unregisterReceiver(this);
    }
    /**
     *闹钟接口
     */
    public interface IFXTimeBR{
        /**
         *定时闹钟
         */
        void PI_timeAddin();
    }

}
