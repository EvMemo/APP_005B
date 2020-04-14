package com.example.app_005.Interface;

import com.example.app_005.Tool.TimeOD;
import com.example.app_005.Tool.WindowView.SnapCalendarDataTool;

public interface OnUpdateSnapCalendarData {
    void onUpdateSnapCalendarData(SnapCalendarDataTool.SnapCalendarDataJ[] snapCalendarDataJS_A, TimeOD timeOD_A);
    void onPlanningSnapNewsTime(TimeOD[] timeODS_A);
}
