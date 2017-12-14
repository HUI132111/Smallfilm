package com.bwie.aizhonghui.smalltwo_yellow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.aizhonghui.smalltwo_yellow.R;

/**
 * Created by DANGEROUS_HUI on 2017/11/20.
 */

public class fragmentwd extends Fragment{

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.item_sy_wd,null);
        return view;
    }
}
