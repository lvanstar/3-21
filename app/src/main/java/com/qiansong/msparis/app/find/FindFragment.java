package com.qiansong.msparis.app.find;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.CustomViewPager;
import com.qiansong.msparis.app.find.fragment.DiscoverFragment;
import com.qiansong.msparis.app.commom.adapter.MyMainAdapter;
import com.qiansong.msparis.app.find.activity.SlideShowActivity;
import com.qiansong.msparis.app.find.fragment.FollowFragment;
import com.qiansong.msparis.app.find.util.MenuDialog;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/2/4.
 * <p>
 * 发现首页
 */

public class FindFragment extends Fragment {

    @InjectView(R.id.fragment_find_tv)
    TextView fragmentFindTv;
    @InjectView(R.id.fragment_find_rl)
    RelativeLayout fragmentFindRl;
    @InjectView(R.id.fragment_attion_tv)
    TextView fragmentAttionTv;
    @InjectView(R.id.fragment_attion_rl)
    RelativeLayout fragmentAttionRl;
    @InjectView(R.id.fragment_find_vp)
    CustomViewPager fragmentFindVp;
    @InjectView(R.id.fragment_find_show_iv)
    ImageView fragmentFindShowIv;
    private View view;

    private ArrayList<Fragment> list = new ArrayList<>();
    private MyMainAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_find, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setViews();
        setListeners();
    }

    /**
     * 设置欲加载的fragment
     */
    private void setViews() {

        list.add(new DiscoverFragment());
        list.add(new FollowFragment());
        adapter = new MyMainAdapter(getChildFragmentManager(), list);
        fragmentFindVp.setOffscreenPageLimit(list.size());                        //设置幕后item的缓存数目
        fragmentFindVp.setAdapter(adapter);
        setSelectedItem(0);
    }

    /**
     * 设置监听
     */
    private void setListeners() {

        // 发现
        fragmentFindRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedItem(0);
                fragmentFindTv.setTextColor(getResources().getColor(R.color.font19));
                fragmentAttionTv.setTextColor(getResources().getColor(R.color.font20));
            }
        });

        //关注
        fragmentAttionRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedItem(1);
                fragmentFindTv.setTextColor(getResources().getColor(R.color.font20));
                fragmentAttionTv.setTextColor(getResources().getColor(R.color.font19));
            }
        });

        //晒图
        fragmentFindShowIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MenuDialog(getActivity(), R.style.registDialog, R.layout.menu_save, "", new MenuDialog.ButtonClickListener() {
                    @Override
                    public void onButtonClick(int i) {
                        if (i==0){
                            Intent intent = new Intent(getActivity(),SlideShowActivity.class);
                            intent.putExtra(SlideShowActivity.CAMERA,SlideShowActivity.CAMERA);
                            startActivityForResult(intent,6);

                        } else if (i==1){

                            Intent intent = new Intent(getActivity(),SlideShowActivity.class);
                            intent.putExtra(SlideShowActivity.ALBUM,SlideShowActivity.ALBUM);
                            startActivityForResult(intent,6);
                        }
                    }
                }).show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * 选择页面
     *
     * @param pos
     */
    public void setSelectedItem(int pos) {
        fragmentFindVp.setCurrentItem(pos, false);
    }


}
