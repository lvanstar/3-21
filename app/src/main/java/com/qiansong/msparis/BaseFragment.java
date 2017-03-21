package com.qiansong.msparis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.util.SweetAlertDialog;

/**
 * Created by Administrator on 2017/3/14.
 */

public class BaseFragment extends Fragment{
    public SweetAlertDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new SweetAlertDialog(getActivity());
        dialog.setTitleText("加载中...");
    }

}
