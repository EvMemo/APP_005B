package com.example.app_005.Interface;

import com.example.app_005.Tool.TimeOD;

public interface OnUpdateTime {
    /**
     *最新全局时间更新事件
     */
    void onUpdateTime(TimeOD timeODAA);
    void onUpdateNewDay(TimeOD timeODAA);
}
