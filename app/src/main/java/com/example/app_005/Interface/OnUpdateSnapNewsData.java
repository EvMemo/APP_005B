package com.example.app_005.Interface;

import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.Tool.TimeOD;
import com.example.app_005.Tool.WindowView.SnapNewsDataTool;

public interface OnUpdateSnapNewsData {
     void onUpdateSnapNewsData(SnapNewsDataTool.SnapNewsDataJ[] snapNewsDataJS, TimeOD timeOD_A);
     void onPlanningSnapNewsTime(TimeOD[] timeODS_A);
}
