package com.example.app_005.View;

import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.app_005.Class.NewsDataClass;
import com.example.app_005.R;

public class RecordNewsTextSyster {
    RecordImpactView recordImpactView;
    TextRecordView textRecordView;
    public RecordNewsTextSyster(View view_A){
        recordImpactView=view_A.findViewById(R.id.recordImpactView_001);
        textRecordView=view_A.findViewById(R.id.textRecordView_1);
        textRecordView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                float mLayerWidth = (float) textRecordView.getWidth();
                float mTextWidth = (float) textRecordView.getWidth();
               // Log.w(this.toString(), "RecordNewsTextSyster.onGlobalLayout: E0"+"="+ mLayerWidth +"="+mTextWidth);
                if (mLayerWidth != 0 && mTextWidth != 0) {
                   // startAnimation(mLayerWidth,mTextWidth);
                }
            }
        });
        textRecordView.setActivated(false);
        textRecordView.setVisibility(View.GONE);
        recordImpactView.setVisibility(View.GONE);
        recordImpactView.setRecordNewsTextSyster(this);
    }
    public void openTextRecordView(){
        if(textRecordView.isActivated())
            {
                textRecordView.setActivated(false);
                textRecordView.setVisibility(View.GONE);
            }else {
            textRecordView.setActivated(true);
            textRecordView.setVisibility(View.VISIBLE);
        }
    }
    public void openRecNewsData(NewsDataClass newsData_A){
        recordImpactView.setVisibility(View.VISIBLE);
        recordImpactView.setNewsDataClass(newsData_A);

    }
    public void closeRecNewsData(){
        recordImpactView.setVisibility(View.GONE);
        //recordImpactView.setNewsDataClass(newsData_A);

    }
}
