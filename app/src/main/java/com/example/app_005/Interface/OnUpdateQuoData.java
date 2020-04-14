package com.example.app_005.Interface;

import com.example.app_005.Service.MyFXDataService;
import com.example.app_005.Tool.TimeOD;

public interface OnUpdateQuoData {
    /**
     * 数据库更新事件
     *[0=重新刷新数据,1=更新最新数据，2=重新刷新时间,4=更新日期]
     */
    void onUpdateQuoUI(int intUI);

}
