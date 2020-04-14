package com.example.app_005.Interface;

import com.example.app_005.Class.SkillLineNema;
import com.example.app_005.View.CustomizeTrend;
import com.example.app_005.View.QuosSystemCenter;

public interface OnGetQuoData{
    public void On_getQuoData(CustomizeTrend.CusTreDrawTool cusTreDrawTool_A);
    public void On_setQuoBool(Boolean bool_A);
    Boolean On_getQuoBool();
    void On_GetLineData(boolean bool_D, SkillLineNema[] skillLineNemas_A);
}
