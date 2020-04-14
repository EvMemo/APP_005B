package com.example.app_005.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.app_005.R;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<GridData> mList;
    private ArrayList<Button> buttons;
    public int intMode;
    private int selectorPosition;

    public GridViewAdapter(Context context, ArrayList<GridData> mList) {
        this.mContext = context;
        this.mList = mList;
        buttons=new ArrayList<>();
        intMode=0;

    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList != null ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return mList != null ? position : 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.my_grid_buttom, null);
        Button button_A = convertView.findViewById(R.id.button_grid);
        button_A.setText(mList.get(position).name);
        //buttons.add(button_A);
        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intMode==1) {
                    if (mList.get(position).aBoolean) {
                        mList.get(position).aBoolean=false;
                        v.setActivated(false);
                    } else {
                        mList.get(position).aBoolean=true;
                        v.setActivated(true);

                    }
                }else if(intMode==0) {
                    if(!mList.get(position).aBoolean)
                        {
                            for(int i=0;i<parent.getChildCount();i++)
                            {
                                parent.getChildAt(i).setActivated(false);

                            }
                            for(int i=0;i<mList.size();i++)
                            {
                                mList.get(i).aBoolean=false;
                            }
                            mList.get(position).aBoolean=true;
                            v.setActivated(true);
                        }

                }

            }
        });
        button_A.setActivated(mList.get(position).aBoolean);
        return convertView;
    }
    /**
     *
     */
    public ArrayList<SkillQuoName> PuC_calGridBoolData(){
        ArrayList<SkillQuoName> arrayList=new ArrayList<>();
        for(int i=0;i<mList.size();i++)
            {
                if(mList.get(i).aBoolean)
                    {
                        arrayList.add(mList.get(i).skillName);
                    }

            }
        return  arrayList;

    }
    public void Puc_setGridMode(int intAA){
        intMode=intAA;
        for(int i=0;i<mList.size();i++)
        {
            mList.get(i).aBoolean=false;
        }
        notifyDataSetChanged();
    }

    public static class GridData{
        public boolean aBoolean;
        public String name;
        public SkillQuoName skillName;
        public GridData(String stringAA){
            //string=stringAA;
            aBoolean=false;
        }
        public GridData(SkillQuoName skillName){
            this.skillName=skillName;
            name= skillName.getName();
            aBoolean=false;
        }
    }
}
