package com.qiansong.msparis.app.commom.selfview.showimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.showimage.photoview.PhotoViewAttacher;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;


/**
 * 单张图片显示Fragment
 */
public class ImageDetailFragment extends Fragment {
	private String mImageUrl;
	private ImageView mImageView;
	private ProgressBar progressBar;
	private PhotoViewAttacher mAttacher;

	public static ImageDetailFragment newInstance(String imageUrl) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putString("url", imageUrl);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
		mImageView = (ImageView) v.findViewById(R.id.image);
		mAttacher = new PhotoViewAttacher(mImageView);

		mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

			@Override
			public void onPhotoTap(View arg0, float arg1, float arg2) {
				getActivity().finish();
			}
		});

		progressBar = (ProgressBar) v.findViewById(R.id.loading);

		mAttacher.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				new PopupWindows(getActivity().getApplicationContext(), mImageView);
				return false;
			}
		});
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		showImage();

	}
	int seeNum=8;
	private void showImage(){
//		Glide.with(getActivity()).load(mImageUrl).fitCenter().into(mImageView);
		seeNum--;
		ImageLoader.getInstance().displayImage(mImageUrl, mImageView, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				progressBar.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				String message = null;
				switch (failReason.getType()) {
					case IO_ERROR:
						message = "下载错误";
						Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
						break;
					case DECODING_ERROR:
						message = "图片无法显示";
						Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
						break;
					case NETWORK_DENIED:
						message = "网络有问题，无法下载";
						Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
						break;
					case OUT_OF_MEMORY:
//						message = "图片太大无法显示";
                       if (seeNum>0)showImage();
						break;
					case UNKNOWN:
						message = "未知的错误";
						Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
						break;
				}

				progressBar.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				progressBar.setVisibility(View.GONE);
				mAttacher.update();
			}
		});
	}


	public class PopupWindows extends PopupWindow
	{

		public PopupWindows(Context mContext, View parent)
		{

			View view = View.inflate(mContext, R.layout.item_popupwindow_save, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_ins));
			LinearLayout ll_popup = (LinearLayout) view
					.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.push_bottom_in_2));

			setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
			setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();

			Button bt1 = (Button) view
					.findViewById(R.id.item_popupwindows_save);

			Button bt2 = (Button) view
					.findViewById(R.id.item_popupwindows_cancel);
			bt1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v)
				{
					ExclusiveUtils.saveImage(getActivity(),mImageUrl);
					ContentUtil.makeToast(getActivity().getApplicationContext(),"保存成功");
					dismiss();
				}
			});
			bt2.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View v)
				{
					dismiss();
				}
			});

		}
	}



}
