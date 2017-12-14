package com.bwie.aizhonghui.smalltwo_yellow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwie.aizhonghui.smalltwo_yellow.HomeActivity;
import com.bwie.aizhonghui.smalltwo_yellow.R;

/**
 * Created by DANGEROUS_HUI on 2017/11/20.
 */

public class fragmentsm extends Fragment implements View.OnClickListener{

    private View view;
    private ImageView ivsm;
    private ImageView ivv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.item_sy_sm,null);
        ivsm=view.findViewById(R.id.iv_fra_sm);
        ivv = view.findViewById(R.id.ivv);
        ivsm.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        ivsm.setVisibility(View.GONE);
        ivv.setVisibility(View.VISIBLE);

    }

//    @Override
//    public void fsm(int k) {
//        System.out.println("==dfs=="+k);
//       if(k==2){
//           ivsm.setVisibility(View.VISIBLE);
//           ivv.setVisibility(View.GONE);
//       }else {
//           ivsm.setVisibility(View.GONE);
//           ivv.setVisibility(View.VISIBLE);
//       }
//    }
}
