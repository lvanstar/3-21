package com.qiansong.msparis.app.homepage.view.sortlistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//字母检索的view
public class SideBar extends View {
	//private String[] b;
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	//
	public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	private static List<String> LIST=new ArrayList<>();
	//public static List<String> b= MyApplication.azList;
	private int choose = -1;//
	private Paint paint = new Paint();

	private TextView mTextDialog;

	public void setTextView(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}


	public SideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SideBar(Context context) {
		super(context);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		LIST.clear();
		//字母排序
		Collections.sort(MyApplication.azList);

		int height = getHeight();
		int width = getWidth();
		int singleHeight=0;
		if (MyApplication.azList.size()==0){
			for (int i=0;i<b.length;i++)LIST.add(b[i]);
		}else {
			for (int i=0;i<MyApplication.azList.size();i++)LIST.add(MyApplication.azList.get(i));
		}
//		singleHeight = height / LIST.size();
		singleHeight=height/(LIST.size()<20?20:LIST.size());
		for (int i = 0; i < LIST.size(); i++) {
//			paint.setColor(Color.rgb(33, 65, 98));
			paint.setColor(Color.parseColor("#FFFFFF"));
			// paint.setColor(Color.WHITE);
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
			paint.setTextSize(20);
			//
			if (i == choose) {
				paint.setColor(Color.WHITE);
				paint.setFakeBoldText(true);
			}
			//
			float xPos = width / 2 - paint.measureText(LIST.get(i)) / 2;
			float yPos = singleHeight * i + singleHeight;
			canvas.drawText(LIST.get(i).toLowerCase(), xPos, yPos, paint);
			paint.reset();//
		}
		setBackgroundResource(R.drawable.sidebar_background);
	}
	private void az(){

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY();//
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		final int c = (int) (y / getHeight() * LIST.size());//

		switch (action) {
		case MotionEvent.ACTION_UP:
//			setBackgroundDrawable(new ColorDrawable(0x00000000));
			choose = -1;//
			invalidate();
			if (mTextDialog != null) {
				mTextDialog.setVisibility(View.INVISIBLE);
			}
			break;

		default:
			setBackgroundResource(R.drawable.sidebar_background);
			if (oldChoose != c) {
				if (c >= 0 && c < LIST.size()) {
					if (listener != null) {
						listener.onTouchingLetterChanged(LIST.get(c));
					}
					if (mTextDialog != null) {
						mTextDialog.setText(LIST.get(c));
						mTextDialog.setVisibility(View.VISIBLE);
					}
					
					choose = c;
					invalidate();
				}
			}

			break;
		}
		return true;
	}

	/**
	 * ���⹫���ķ���
	 * 
	 * @param onTouchingLetterChangedListener
	 */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	/**
	 * @author coder
	 */
	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String s);
	}

}