package com.qiansong.msparis.app.commom.selfview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiansong.msparis.R;


/**
 * 标题栏
 *
 */
public class ETitleBar extends RelativeLayout {

    protected RelativeLayout leftLayout;
    protected ImageView leftImage;
    protected RelativeLayout rightLayout;
    protected ImageView rightImage;
    protected TextView titleView;
    protected RelativeLayout titleLayout;
    protected TextView leftTitle;
    protected TextView rightTitle;

    public ETitleBar(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public ETitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ETitleBar(Context context) {
        super(context);
        init(context, null);
    }
    
    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.widget_title_bar, this);
        leftLayout = (RelativeLayout) findViewById(R.id.left_layout);
        leftImage = (ImageView) findViewById(R.id.left_image);
        rightLayout = (RelativeLayout) findViewById(R.id.right_layout);
        rightImage = (ImageView) findViewById(R.id.right_image);
        titleView = (TextView) findViewById(R.id.title);
        titleLayout = (RelativeLayout) findViewById(R.id.root);

        leftTitle = (TextView) findViewById(R.id.left_title);
        rightTitle = (TextView) findViewById(R.id.right_title);
        
        parseStyle(context, attrs);
    }
    
    private void parseStyle(Context context, AttributeSet attrs){
        if(attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseTitleBar);
            String title = ta.getString(R.styleable.EaseTitleBar_titleBarTitle);
            titleView.setText(title);
            
            Drawable leftDrawable = ta.getDrawable(R.styleable.EaseTitleBar_titleBarLeftImage);
            if (null != leftDrawable) {
                leftImage.setImageDrawable(leftDrawable);
            }
            Drawable rightDrawable = ta.getDrawable(R.styleable.EaseTitleBar_titleBarRightImage);
            if (null != rightDrawable) {
                rightImage.setImageDrawable(rightDrawable);
            }
        
            Drawable background = ta.getDrawable(R.styleable.EaseTitleBar_titleBarBackground);
            if(null != background) {
                titleLayout.setBackgroundDrawable(background);
            }
            
            ta.recycle();
        }
    }
    
    public void setLeftImageResource(int resId) {
        leftTitle.setVisibility(View.GONE);
        leftImage.setVisibility(View.VISIBLE);
        leftImage.setImageResource(resId);
    }
    
    public void setRightImageResource(int resId) {
        rightTitle.setVisibility(View.GONE);
        rightImage.setVisibility(View.VISIBLE);
        rightImage.setImageResource(resId);
    }
    
    public void setLeftLayoutClickListener(OnClickListener listener){
        leftLayout.setOnClickListener(listener);
    }
    
    public void setRightLayoutClickListener(OnClickListener listener){
        rightLayout.setOnClickListener(listener);
    }

    public void setTitleClickListener(OnClickListener listener){
        titleView.setOnClickListener(listener);
    }
    
    public void setLeftLayoutVisibility(int visibility){
        leftLayout.setVisibility(visibility);
    }
    
    public void setRightLayoutVisibility(int visibility){
        rightLayout.setVisibility(visibility);
    }

    public void setLeftTitleVisibility(int visibility){
        leftTitle.setVisibility(visibility);
    }

    public void setRightTitleVisibility(int visibility){
        rightTitle.setVisibility(visibility);
    }
    
    public void setTitle(String title){
        titleView.setText(title);
    }

    public void setLeftTitle(String title){
        leftTitle.setVisibility(View.VISIBLE);
        leftImage.setVisibility(View.GONE);
        leftTitle.setText(title);
    }

    public void setRightTitle(String title){
        rightTitle.setVisibility(View.VISIBLE);
        rightImage.setVisibility(View.GONE);
        rightTitle.setText(title);
    }

    //设置右边是否触发点击事件
    public void setRightLayoutClickable(boolean flag){
        rightLayout.setClickable(flag);
    }
    public void setTitleColor(int color){
        titleView.setTextColor(color);
    }
    
    public void setBackgroundColor(int color){
        titleLayout.setBackgroundColor(color);
    }
    
    public RelativeLayout getLeftLayout(){
        return leftLayout;
    }
    
    public RelativeLayout getRightLayout(){
        return rightLayout;
    }
}
