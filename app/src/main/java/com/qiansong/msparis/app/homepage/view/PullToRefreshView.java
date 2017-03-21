package com.qiansong.msparis.app.homepage.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.qiansong.msparis.R;


/**
 * 下拉刷新
 * todo 现支持所有竖向滑动 RecyclerView为后期加入 ，如发现bug告诉我
 * todo 我们现在是使用XRecyclerView作为下拉刷新 只需要当做listview加入即可
 *                                                    --------叶晨
 */
public class PullToRefreshView extends LinearLayout{
	
	private static final int PULL_TO_REFRESH = 2;
	private static final int RELEASE_TO_REFRESH = 3;
	private static final int REFRESHING = 4;
	private static final int PULL_UP_STATE = 0;
	private static final int PULL_DOWN_STATE = 1;
	private static final int HEADER_OFFERT = 20;
	private boolean enablePullTorefresh = true;
	private boolean enablePullLoadMoreDataStatus = true;
	private int mLastMotionY;
	private boolean mLock;
	private boolean isFooter;
	private View mHeaderView;
	private View mFooterView;
	private LayoutParams params;
	private AdapterView<?> mAdapterView;
	private ScrollView mScrollView;
	private RecyclerView mRecyclerView;//-----------------------------------------------------

	private int mHeaderViewHeight;
	private int mFooterViewHeight;
	
	private ImageView mHeaderImageView;
	private ImageView mFooterImageView;
	private TextView mFooterTextView;
	private ProgressBar mFooterProgressBar;
	private LayoutInflater mInflater;
	private int mHeaderState;
	private int mFooterState;
	private int mPullState;
	private RotateAnimation mFlipAnimation;
	private RotateAnimation mReverseFlipAnimation;
	private OnFooterRefreshListener mOnFooterRefreshListener;
	private OnHeaderRefreshListener mOnHeaderRefreshListener;
	private float mCurrentDragPercent;
	private boolean isShow = false;
	private Drawable showDrawable;


	public PullToRefreshView(Context context) {
		super(context);
		init();
	}
	
	public PullToRefreshView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {
		setOrientation(LinearLayout.VERTICAL);  
        // Load all of the animations we need in code rather than through XML  
        mFlipAnimation = new RotateAnimation(0, -180,  
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,  
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);  
        mFlipAnimation.setInterpolator(new LinearInterpolator());  
        mFlipAnimation.setDuration(150);
        mFlipAnimation.setFillAfter(true);
        mReverseFlipAnimation = new RotateAnimation(-180, 0,  
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,  
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);  
        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());  
        mReverseFlipAnimation.setDuration(150);  
        mReverseFlipAnimation.setFillAfter(true);  
        mHeaderState = 2;
        mInflater = LayoutInflater.from(getContext());
		addHeaderView();
	}



	/**
	 * 头部刷新视图
	 */
	private void addHeaderView() {
		mHeaderView = mInflater.inflate(R.layout.refresh_header, this, false);

		mHeaderImageView = (ImageView) mHeaderView.findViewById(R.id.pull_to_refresh_image);
		measureView(mHeaderView);
		mHeaderViewHeight = mHeaderView.getMeasuredHeight();
		params = new LayoutParams(LayoutParams.MATCH_PARENT, mHeaderViewHeight);
		params.topMargin = -(mHeaderViewHeight);
		addView(mHeaderView, params);
	}
	
	private void measureView(View child) {
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}
	
	/**
	 * 尾部刷新视图
	 */
	private void addFooterView() {
//		if (!isShowFoot)return;

		mFooterView = mInflater.inflate(R.layout.refresh_footer, this, false);
		mFooterImageView = (ImageView) mFooterView.findViewById(R.id.pull_to_load_image);
		mFooterTextView = (TextView) mFooterView.findViewById(R.id.pull_to_load_text);
		mFooterProgressBar = (ProgressBar) mFooterView.findViewById(R.id.pull_to_load_progress);

		measureView(mFooterView);
		mFooterViewHeight = mFooterView.getMeasuredHeight();
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mFooterViewHeight);
		addView(mFooterView, params);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		// footer view 鍦ㄦ娣诲姞淇濊瘉娣诲姞鍒發inearlayout涓殑鏈�悗
		addFooterView();
		initContentAdapterView();
	}

	private void initContentAdapterView() {
		int count = getChildCount();
		if (count < 3) {
			throw new IllegalArgumentException("this layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
		}
		View view = null;
		for (int i = 0; i < count - 1; ++i) {
			view = getChildAt(i);
			if (view instanceof AdapterView<?>) {
				mAdapterView = (AdapterView<?>) view;
			}
			if (view instanceof RecyclerView) {
				mRecyclerView = (RecyclerView) view;
			}
			if (view instanceof ScrollView) {
				// finish later
				mScrollView = (ScrollView) view;
				break;
			}
		}
		if (mAdapterView == null && mScrollView == null&&mRecyclerView==null) {
			throw new IllegalArgumentException("must contain a AdapterView or ScrollView in this layout!");
		}
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent e) {
		int y = (int) e.getRawY();
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			int deltaY = y - mLastMotionY;
//			if (deltaY<10)deltaY=0;//--------------------------------------------------------------------------
			if (isRefreshViewScroll(deltaY)) {
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			break;
		}
		return false;
	}
	
	private boolean isRefreshViewScroll(int deltaY) {
		if (mHeaderState == REFRESHING || mFooterState == REFRESHING) {
			return false;
		}
		if (mAdapterView != null) {
			if (deltaY > 0) {
				if (!enablePullTorefresh) {
					return false;
				}
				View child = mAdapterView.getChildAt(0);
				if (child == null) {
					return false;
				}
				if (mAdapterView.getFirstVisiblePosition() == 0 && child.getTop() == 0) {
					mPullState = PULL_DOWN_STATE;
					return true;
				}
				int top = child.getTop();
				int padding = mAdapterView.getPaddingTop();
				if (mAdapterView.getFirstVisiblePosition() == 0 && Math.abs(top - padding) <= 11) {
					mPullState = PULL_DOWN_STATE;
					return true;
				}

			} else if (deltaY < 0) {
				if (!enablePullLoadMoreDataStatus) {
					return false;
				}
				View lastChild = mAdapterView.getChildAt(mAdapterView.getChildCount() - 1);
				if (lastChild == null) {
					return false;
				}
				if (lastChild.getBottom() <= getHeight() && mAdapterView.getLastVisiblePosition() == mAdapterView.getCount() - 1) {
					mPullState = PULL_UP_STATE;
					return true;
				}
			}
		}
		//-----------------------------------------------------------
		if (mRecyclerView != null) {
			RecyclerView.LayoutManager lm =  mRecyclerView.getLayoutManager();
			int currPosition = 0;
			int lastPosition = 0;
			if (lm != null && lm instanceof  LinearLayoutManager) {
				currPosition = ((LinearLayoutManager)lm).findFirstVisibleItemPosition();
				lastPosition=((LinearLayoutManager)lm).findLastVisibleItemPosition();
			}else if (lm!=null&&lm instanceof GridLayoutManager){
				currPosition = ((GridLayoutManager)lm).findFirstVisibleItemPosition();
				lastPosition=((GridLayoutManager)lm).findLastVisibleItemPosition();
			}
			if (deltaY > 0) {
				if (!enablePullTorefresh) {
					return false;
				}
				View child = mRecyclerView.getChildAt(0);
				if (child == null) {
					return false;
				}

				if (currPosition== 0 && child.getTop() == 0) {
					mPullState = PULL_DOWN_STATE;
					return true;
				}
				int top = child.getTop();
				int padding = mRecyclerView.getPaddingTop();
				if (currPosition == 0 && Math.abs(top - padding) <= 11) {
					mPullState = PULL_DOWN_STATE;
					return true;
				}

			} else if (deltaY < 0) {
				if (!enablePullLoadMoreDataStatus) {
					return false;
				}
				View lastChild = mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1);
				if (lastChild == null) {
					return false;
				}
				if (lastChild.getBottom() <= getHeight() &&  lastPosition== mRecyclerView.getChildCount() - 1) {
					mPullState = PULL_UP_STATE;
					return true;
				}
			}
		}
		//-----------------------------------------------------------
		if (mScrollView != null) {
			View child = mScrollView.getChildAt(0);
			if (deltaY > 0 && mScrollView.getScrollY() == 0) {
				mPullState = PULL_DOWN_STATE;
				return true;
			} else if (deltaY < 0 && child.getMeasuredHeight() <= getHeight() + mScrollView.getScrollY()) {
				mPullState = PULL_UP_STATE;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mLock) {
			return true;
		}
		int y = (int) event.getRawY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			int deltaY = y - mLastMotionY;
			//---------------
			//----------------
			if (mPullState == PULL_DOWN_STATE) {
				headerPrepareToRefresh(deltaY);
			} else if (mPullState == PULL_UP_STATE && isFooter) {
				footerPrepareToRefresh(deltaY);
			}          
			mLastMotionY = y;
            changeDrawable();//处理头部下拉刷新动画
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			isShow = false;
			int topMargin = getHeaderTopMargin();
			if (mPullState == PULL_DOWN_STATE) {
				if (topMargin >= 0) {
					headerRefreshing();
				} else {
					setHeaderTopMargin(-mHeaderViewHeight);
				}
			} else if (mPullState == PULL_UP_STATE && isFooter) {
				if (Math.abs(topMargin) >= mHeaderViewHeight + mFooterViewHeight) {
					footerRefreshing();
				} else {
					setHeaderTopMargin(-mHeaderViewHeight);
				}
			}
			break;
		}
		return super.onTouchEvent(event);
	}
	
	private void headerPrepareToRefresh(int deltaY) {
		int newTopMargin = changingHeaderViewTopMargin(deltaY);
		if (newTopMargin >= 0 && mHeaderState != RELEASE_TO_REFRESH) {
			mHeaderState = RELEASE_TO_REFRESH;
		} else if (newTopMargin < 0 && newTopMargin > -mHeaderViewHeight && mHeaderState != PULL_TO_REFRESH) {
			mHeaderState = PULL_TO_REFRESH;
		}
	}
	
	private void footerPrepareToRefresh(int deltaY) {
		int newTopMargin = changingHeaderViewTopMargin(deltaY);
		if (Math.abs(newTopMargin) >= (mHeaderViewHeight + mFooterViewHeight) && mFooterState != RELEASE_TO_REFRESH) {
			mFooterTextView.setText(R.string.pull_to_refresh_footer_release_label);
			mHeaderImageView.setImageResource(R.drawable.ic_pulltorefresh_arrow);
			mFooterImageView.clearAnimation();
			mFooterImageView.startAnimation(mFlipAnimation);
			mFooterState = RELEASE_TO_REFRESH;
		} else if (Math.abs(newTopMargin) < (mHeaderViewHeight + mFooterViewHeight)) {
			mFooterImageView.clearAnimation();
			mFooterImageView.startAnimation(mFlipAnimation);
			mHeaderImageView.setImageResource(R.mipmap.yc_ic_pulltorefresh_arrow_up);
			mFooterTextView.setText(R.string.pull_to_refresh_footer_pull_label);
			mFooterState = PULL_TO_REFRESH;
		}
	}
	
	private int changingHeaderViewTopMargin(int deltaY) {
		float newTopMargin = params.topMargin + deltaY * 0.3f;
		if (deltaY > 0 && mPullState == PULL_UP_STATE && Math.abs(params.topMargin) <= mHeaderViewHeight) {
			return params.topMargin;
		}
		if (deltaY < 0 && mPullState == PULL_DOWN_STATE && Math.abs(params.topMargin) >= mHeaderViewHeight) {
			return params.topMargin;
		}
		params.topMargin = (int) newTopMargin;
		mHeaderView.setLayoutParams(params);
		invalidate();
		return params.topMargin;
	}
	
	public void headerRefreshing() {
		mHeaderState = REFRESHING;
		setHeaderTopMargin(0);
		mHeaderImageView.clearAnimation();
		AnimationDrawable drawable = (AnimationDrawable) getResources().getDrawable(R.drawable.meimei_showing);
		mHeaderImageView.setImageDrawable(drawable);
		drawable.start();
		if (mOnHeaderRefreshListener != null) {
			mOnHeaderRefreshListener.onHeaderRefresh(this);
		}
	}

	public void footerRefreshing() {
		mFooterState = REFRESHING;
		int top = mHeaderViewHeight + mFooterViewHeight;
		setHeaderTopMargin(-top);
		mFooterImageView.setVisibility(View.GONE);
		mFooterImageView.clearAnimation();
		mFooterImageView.setImageDrawable(null);
		mFooterProgressBar.setVisibility(View.VISIBLE);
		mFooterTextView.setText(R.string.pull_to_refresh_footer_refreshing_label);
		if (mOnFooterRefreshListener != null) {
			mOnFooterRefreshListener.onFooterRefresh(this);
		}
	}
	
	private void setHeaderTopMargin(int topMargin) {
		LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
		params.topMargin = topMargin;
		mHeaderView.setLayoutParams(params);
		invalidate();
	}
	
	public void onHeaderRefreshComplete() {
		setHeaderTopMargin(-mHeaderViewHeight);
		mHeaderImageView.setVisibility(View.VISIBLE);
		mHeaderImageView.setImageResource(R.drawable.ic_pulltorefresh_arrow);
		mHeaderState = PULL_TO_REFRESH;
	}
	
	public void onHeaderRefreshComplete(CharSequence lastUpdated) {
		onHeaderRefreshComplete();
	}
	
	public void onFooterRefreshComplete() {
		setHeaderTopMargin(-mHeaderViewHeight);
		mFooterImageView.setVisibility(View.VISIBLE);
		mFooterImageView.setImageResource(R.mipmap.yc_ic_pulltorefresh_arrow_up);
		mFooterTextView.setText(R.string.pull_to_refresh_footer_pull_label);
		mFooterProgressBar.setVisibility(View.GONE);
		mFooterState = PULL_TO_REFRESH;
	}
	
	public void onFooterRefreshComplete(int size) {
		if (size > 0) {
			mFooterView.setVisibility(View.VISIBLE);
		} else {
			mFooterView.setVisibility(View.GONE);
		}
		setHeaderTopMargin(-mHeaderViewHeight);
		mFooterImageView.setVisibility(View.VISIBLE);
		mFooterImageView.setImageResource(R.mipmap.yc_ic_pulltorefresh_arrow_up);
		mFooterTextView.setText(R.string.pull_to_refresh_footer_pull_label);
		mFooterProgressBar.setVisibility(View.GONE);
		mFooterState = PULL_TO_REFRESH;
	}
	
	private int getHeaderTopMargin() {
		LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
		return params.topMargin;
	}
	
	
	
	public void setOnHeaderRefreshListener(OnHeaderRefreshListener headerRefreshListener) {
		mOnHeaderRefreshListener = headerRefreshListener;
	}

	public void setOnFooterRefreshListener(OnFooterRefreshListener footerRefreshListener) {
		mOnFooterRefreshListener = footerRefreshListener;
	}

	public interface OnFooterRefreshListener {
		public void onFooterRefresh(PullToRefreshView view);
	}

	public interface OnHeaderRefreshListener {
		public void onHeaderRefresh(PullToRefreshView view);
	}

	public boolean isEnablePullTorefresh() {
		return enablePullTorefresh;
	}

	public void setEnablePullTorefresh(boolean enablePullTorefresh) {
		this.enablePullTorefresh = enablePullTorefresh;
	}

	public boolean isEnablePullLoadMoreDataStatus() {
		return enablePullLoadMoreDataStatus;
	}

	public void setEnablePullLoadMoreDataStatus(boolean enablePullLoadMoreDataStatus) {
		this.enablePullLoadMoreDataStatus = enablePullLoadMoreDataStatus;
	}
	
	public void setFooter(boolean footer){
		isFooter = footer;
	}

	//动画
	Handler handler=new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
//            0.02 < y < 0.9

			if (msg.what==0){
				float y= (float) msg.obj;
				if (y>0.02&&y<0.08){
					mHeaderImageView.setImageResource(R.mipmap.loading_1);
				}else if (y>=0.1&&y<0.18){
					mHeaderImageView.setImageResource(R.mipmap.loading_4);
				}else if (y>=0.18&&y<0.26){
					mHeaderImageView.setImageResource(R.mipmap.loading_6);
				}else if (y>=0.34&&y<0.43){
					mHeaderImageView.setImageResource(R.mipmap.loading_8);
				}else if (y>=0.43&&y<0.52){
					mHeaderImageView.setImageResource(R.mipmap.loading_12);
				}else if (y>=0.52&&y<0.60){
					mHeaderImageView.setImageResource(R.mipmap.loading_16);
				}else if (y>=0.60&&y<0.68){
					mHeaderImageView.setImageResource(R.mipmap.loading_18);
				}else if (y>=0.68&&y<0.76){
					mHeaderImageView.setImageResource(R.mipmap.loading_22);
				}else if (y>=0.76&&y<0.83){
					mHeaderImageView.setImageResource(R.mipmap.loading_26);
				}else if (y>=0.83&&y<=0.90){
					mHeaderImageView.setImageResource(R.mipmap.loading_34);
				}

			}
			return false;
		}
	});
	/**
	 * 头部刷新动画
	 */
	public void changeDrawable() {
		int margin = params.topMargin;
		if(margin < HEADER_OFFERT) {
			mCurrentDragPercent = ((float) mHeaderViewHeight + margin)/(float) mHeaderViewHeight;
			mCurrentDragPercent *= mCurrentDragPercent;
			
	    	if(mCurrentDragPercent > 0.02 && mCurrentDragPercent < 0.9) {//下拉过程的动画
	    		isShow = false;
	    		if(showDrawable == null)
	    			showDrawable = getResources().getDrawable(R.mipmap.loading_34);
				//------------------------------------------------------------------
				Message message=new Message();
				message.what=0;
				message.obj=mCurrentDragPercent;
				handler.sendMessage(message);
				//------------------------------------------------------------------
//	    		Drawable one = zoomDrawable(showDrawable, mCurrentDragPercent);
//	    		mHeaderImageView.setImageDrawable(one);
	    	} else if(mCurrentDragPercent >= 0.9 && !isShow) {//刷新过程的动画
	    		AnimationDrawable drawable = (AnimationDrawable) getResources().getDrawable(R.drawable.meimei_showing);
	    		mHeaderImageView.setImageDrawable(drawable);
	    		drawable.start();
	    		isShow = true;
	    	}
		}
    }
	/**
	 * 改变图片的尺寸
	 * @param drawable
	 * @param s
	 * @return
	 */
	private Drawable zoomDrawable(Drawable drawable, float s) {
 		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap oldbmp = drawableToBitmap(drawable);// drawable转换成bitmap
		Matrix matrix = new Matrix(); // 创建操作图片用的Matrix对象
		matrix.postScale(s, s); // 设置缩放比例
		if(width <= 0 || height <= 0) return null;
		Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true); // 建立新的bitmap，其内容是对原bitmap的缩放后的图
		return new BitmapDrawable(getResources(), newbmp); // 把bitmap转换成drawable并返回
	}
	
	private Bitmap drawableToBitmap(Drawable drawable) {// drawable 转换成bitmap

		BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
		return bitmapDrawable.getBitmap();
	}
}
