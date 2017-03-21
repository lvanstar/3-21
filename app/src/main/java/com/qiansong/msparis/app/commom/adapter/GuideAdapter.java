package com.qiansong.msparis.app.commom.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.qiansong.msparis.app.commom.activity.MainActivity;
import com.qiansong.msparis.app.commom.bean.BannerBean;
import com.qiansong.msparis.app.commom.util.AndroidUtil;

import java.util.ArrayList;

/**
 * 引导页 图片适配器
 * 
 * @author jiawei.xue
 *
 * @date 2015-12-24
 */
public class GuideAdapter extends PagerAdapter {

	/** banner数据列表 */
	private ArrayList<BannerBean> bannerBeans = new ArrayList<BannerBean>();
	/** 图片view列表 */
	private ArrayList<ImageView> viewList = new ArrayList<ImageView>();
	/** 布局参数 */
	private FrameLayout.LayoutParams layoutParams;

	/** 上下文 */
	private Context context;

	public GuideAdapter(Activity context) {
		this.context = context;
		int screenWidth = AndroidUtil.getDisplayMetrics(context).widthPixels;
		layoutParams = new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}

	/**
	 * 
	 * @param bannerBeans
	 *            数据
	 */
	public void setBannerList(ArrayList<BannerBean> bannerBeans) {
		this.bannerBeans = bannerBeans;

		for (int i = 0; i < bannerBeans.size(); i++)
		{
			ImageView imageView = new ImageView(context);
			imageView.setScaleType(ScaleType.FIT_CENTER);
			imageView.setLayoutParams(layoutParams);
			viewList.add(imageView);
		}
	}

	@Override
	public int getCount() {
		return bannerBeans == null ? 0 : bannerBeans.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(viewList.get(position));
	}

	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final int pos = position;
		container.addView(viewList.get(position));

		final ImageView imageView = viewList.get(position);
		imageView.setImageResource(bannerBeans.get(position).img_srcxx);

		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (pos == 3) {
					context.startActivity(new Intent(context, MainActivity.class));
					((Activity) context).finish();

				}
			}
		});

		return imageView;
	}

}
