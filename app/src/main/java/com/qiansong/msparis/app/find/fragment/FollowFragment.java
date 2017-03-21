package com.qiansong.msparis.app.find.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;

import butterknife.ButterKnife;

/**
 * Created by lizhaozhao on 2017/2/8.
 *
 * 关注
 */

public class FollowFragment extends BaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_follow,null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
