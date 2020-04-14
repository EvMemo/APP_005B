package com.example.app_005.Tool;

import com.example.app_005.Service.MyFXDataService;

import java.util.ArrayList;

/**
 * 请求数据类
 */
public class RequestDataTool {
    public  class QuoRequest{
       // public ArrayList<FXDataPool.FXData> fxDataListA;//请求数据集合
        public FXDataPool dataPool;
        public QuoRequest(){

          //  fxDataListA=new ArrayList<>();
            //dataPool=new FXDataPool();
        }

        /**
         * 添加请求数据
         *
         * @param intsAA   the ints aa
         * @param timeODAA the time odaa
         */
        public void add(int[] intsAA, TimeOD timeODAA){
           // FXDataPool.FXData fxData_A=dataPool.getFXDataClass(intsAA);
           // fxDataListA.add(fxData_A);
        }

        /**
         * 添加请求数据
         *
         * @param string   the string
         * @param timeODAA the time odaa
         */
        public void add(String string, TimeOD timeODAA){
          //  add(dataPool.getFXDataNumber(string),timeODAA);

        }



    }
}
