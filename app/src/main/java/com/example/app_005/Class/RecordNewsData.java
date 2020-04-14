package com.example.app_005.Class;

public class RecordNewsData {
    public RecordNewsImpactData[] recordNewsImpactData;
    public RecordNewsData(){

    }
    public void initCC(){
        recordNewsImpactData=new RecordNewsImpactData[9];
        recordNewsImpactData[0]=new RecordNewsImpactData(SkillFinacTool.getSkillFinacName(0));
        recordNewsImpactData[1]=new RecordNewsImpactData(SkillFinacTool.getSkillFinacName(0),1);
        recordNewsImpactData[2]=new RecordNewsImpactData(SkillFinacTool.getSkillFinacName(0),2,new SkillFinacTool.SkillFinacName[]{SkillFinacTool.getSkillFinacName(1),SkillFinacTool.getSkillFinacName(2)});
        recordNewsImpactData[3]=new RecordNewsImpactData(SkillFinacTool.getSkillFinacName(0));
        recordNewsImpactData[4]=new RecordNewsImpactData(SkillFinacTool.getSkillFinacName(0),1);
        recordNewsImpactData[5]=new RecordNewsImpactData(SkillFinacTool.getSkillFinacName(0),1,new SkillFinacTool.SkillFinacName[]{SkillFinacTool.getSkillFinacName(1),SkillFinacTool.getSkillFinacName(2)});
        recordNewsImpactData[6]=new RecordNewsImpactData(SkillFinacTool.getSkillFinacName(0));
        recordNewsImpactData[7]=new RecordNewsImpactData(SkillFinacTool.getSkillFinacName(0),1,new SkillFinacTool.SkillFinacName[]{SkillFinacTool.getSkillFinacName(1),SkillFinacTool.getSkillFinacName(2)});
        recordNewsImpactData[8]=new RecordNewsImpactData(SkillFinacTool.getSkillFinacName(0),1);
    }
    public class RecordNewsImpactData{
        public SkillFinacTool.SkillFinacName skillFinacName;
        public int intMode;
        public SkillFinacTool.SkillFinacName[] skillFinacNamesB;
        public RecordNewsImpactData(SkillFinacTool.SkillFinacName skillFinacName_A){
            skillFinacName=skillFinacName_A;
            intMode=0;
        }
        public RecordNewsImpactData(SkillFinacTool.SkillFinacName skillFinacName_A,int intMode_A){
            skillFinacName=skillFinacName_A;
            intMode=intMode_A;

        }
        public RecordNewsImpactData(SkillFinacTool.SkillFinacName skillFinacName_A,int intMode_A,SkillFinacTool.SkillFinacName[] skillFinacNamesB_A){
            skillFinacName=skillFinacName_A;
            intMode=intMode_A;
            skillFinacNamesB=skillFinacNamesB_A;

        }
        public void setDataMain(SkillFinacTool.SkillFinacName skillFinacName_A){
            skillFinacName=skillFinacName_A;

        }
        public void setIntMode(int intMode_A){
            intMode=intMode_A;

        }
        public void setDataB(SkillFinacTool.SkillFinacName[] skillFinacNamesB_A){
            skillFinacNamesB=skillFinacNamesB_A;

        }
        public String getModeString(){
            switch(intMode){
                case 0:
                    return "";
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
    }

}
