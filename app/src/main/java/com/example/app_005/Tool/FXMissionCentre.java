package com.example.app_005.Tool;

import com.example.app_005.Class.SkillQuoName;
import com.example.app_005.Service.MyFXDataService;

import java.util.ArrayList;

public class FXMissionCentre {
    public FXMissionQuo fxMissionQuo;
    public FXMissionCentre(){
        fxMissionQuo=new FXMissionQuo();
    }
    public class FXMissionQuo{
        ArrayList<MissionTable> missionTables;
        public FXMissionQuo(){
            missionTables=new ArrayList<>();
        }
        public void initData(){

        }
        public void researchQuoPriceMission(SkillQuoName skillQuoNameAA, float floatAA, TimeOD timeODAA) {
            QuoPriceTrace quoPriceTrace_A ;
            for (int i = 0; i < missionTables.size(); i++) {
                if (missionTables.get(i).intType == 0) {
                    quoPriceTrace_A = ((QuoPriceTrace) missionTables.get(i));
                    if (quoPriceTrace_A.researchMission(floatAA)) {
                        finishQuoPriceMission(i,0,true);
                        i--;
                    } else if (quoPriceTrace_A.researchTimeEnd(timeODAA)) {
                        finishQuoPriceMission(i,0,false);
                        i--;
                    }
                }
            }
        }

        public void researchQuoPriceMission(SkillQuoName skillQuoNameAA, float[] floatsAA, TimeOD timeODAA){
            QuoIndexTrace quoIndexTrace_A;
            for (int i = 0; i < missionTables.size(); i++) {
                if (missionTables.get(i).intType == 0) {
                    quoIndexTrace_A = ((QuoIndexTrace) missionTables.get(i));
                    if (quoIndexTrace_A.researchMission(floatsAA)) {
                        finishQuoPriceMission(i,1,true);
                        i--;
                    } else if (quoIndexTrace_A.researchTimeEnd(timeODAA)) {
                        finishQuoPriceMission(i,1,false);
                        i--;
                    }
                }
            }
        }
        /**
         *结束
         */
        public void finishQuoPriceMission(int intPositionAA,int intTypeAA,boolean boolAA){
            switch(intTypeAA){
                case 0:
                    QuoPriceTrace quoPriceTrace_A=(QuoPriceTrace)missionTables.get(intPositionAA);
                    missionTables.remove(intPositionAA);
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }
        /**
         *追踪价格任务
         */
        public class QuoPriceTrace extends MissionTable{
            public SkillQuoName skillQuoName;
            public TimeOD timeODStart,timeODEnd;
            public int intMission;//[0=价格大过目标,1=价格小目标]
            public float floatTarget;//目标价格
            public QuoPriceTrace(SkillQuoName skillQuoNameAA,float floatTargetAA, int intMissionAA, TimeOD timeODAA, TimeOD timeODBB){
                intType=1;
                skillQuoName=skillQuoNameAA;
                timeODStart=timeODAA;
                timeODEnd=timeODBB;
                floatTarget=floatTargetAA;
                intMission=intMissionAA;
                intFinish=0;
            }
            public boolean researchMission(float floatTargetAA){
                if(intMission==0)
                    {
                    if(floatTargetAA>floatTarget){
                        intFinish=1;
                        return true;
                    }
                    }else if(intMission==1){
                    if(floatTargetAA<floatTarget){
                        intFinish=1;
                        return true;
                    }
                }
                return false;
            }
            public boolean researchTimeEnd(TimeOD timeODAA) {
                if (timeODEnd != null) {
                    if (timeODAA.PuC_calTimeContrast(timeODAA) == 1) {
                        intFinish=2;
                        return true;
                    }
                }
                return false;

            }
        }
        public class QuoIndexTrace extends MissionTable{
            public SkillQuoName skillQuoName;
            public TimeOD timeODStart,timeODEnd;
            public int[] intMission;//[0=价格大过目标,1=价格小目标]
            public float[] floatTargets;//目标价格
            public QuoIndexTrace(SkillQuoName skillQuoNameAA,float[] floatTargetsAA, int[] intMissionAA, TimeOD timeODAA, TimeOD timeODBB){
                intType=1;
                skillQuoName=skillQuoNameAA;
                timeODStart=timeODAA;
                timeODEnd=timeODBB;
                floatTargets=floatTargetsAA;
                intMission=intMissionAA;
                intFinish=0;
            }
            public boolean researchMission(float[] floatTargetsAA){
                boolean bool_A=false;
                for(int i=0;i<intMission.length;i++)
                    {
                        if(intMission[i]==0)
                        {
                            if(!(floatTargetsAA[i]>floatTargets[i])){
                                return false;
                            }
                        }else if(intMission[i]==1){
                            if(!(floatTargetsAA[i]<floatTargets[i])){
                                return false;
                            }
                        }
                    }
                intFinish=1;
                return true;
            }
            public boolean researchTimeEnd(TimeOD timeODAA) {
                if (timeODEnd != null) {
                    if (timeODAA.PuC_calTimeContrast(timeODAA) == 1) {
                        intFinish=2;
                        return true;
                    }
                }
                return false;

            }
        }
    }
    public class MissionTable{
        public int intFinish;//任务完成了没[0=举行着,1=任务完成,2=任务失败]
        public int intType;//任务类型
    }
}
