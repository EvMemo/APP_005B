package com.example.app_005;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.app_005.Tool.SoftKeyViewUtil;
import com.example.app_005.View.NewsNewView;
import com.example.app_005.View.NewsRecycleView;
import com.example.app_005.View.NewsSystemCenter;
import com.example.app_005.ui.main.PageViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment_002.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment_002#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment_002 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private OnFragmentInteractionListener mListener;
    NewsSystemCenter newsSystemCenter;

    public BlankFragment_002() {
        // Required empty public constructor
    }

    public static BlankFragment_002 newInstance(String param1, String param2) {
        BlankFragment_002 fragment = new BlankFragment_002();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //新闻时间点

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewA=inflater.inflate(R.layout.fragment_blank_fragment_002, container, false);
       // SoftKeyViewUtil.assistView(viewA);
        PrC_initCrView(viewA);
        return viewA;
    }
    /**
     *
     */
    private void PrC_initCrView(View viewAA){
        //RecyclerView recyclerView_A=viewAA.findViewById(R.id.recyecler_003);
        //NewsNewView newsNewView_A=viewAA.findViewById(R.id.fra002_newsNewView);
        NewsRecycleView newsRecycleView_A=viewAA.findViewById(R.id.NewsRecycleView_001);
        newsSystemCenter=new NewsSystemCenter(getContext(),newsRecycleView_A,viewAA );


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
