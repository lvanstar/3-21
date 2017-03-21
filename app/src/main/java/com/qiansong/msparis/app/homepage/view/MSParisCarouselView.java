package com.qiansong.msparis.app.homepage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ContentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 叶晨 on 2017/2/10.
 * 轮播
 */

public class MSParisCarouselView extends ConvenientBanner {


    public MSParisCarouselView(Context context) {
        super(context);
    }

    public MSParisCarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MSParisCarouselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MSParisCarouselView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //轮播设置数据
    public  void setData( List<String> imgs) {
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        /**
         * 拼接缩略图规则显示
         */
        List<String>urls=new ArrayList<>();
        for (String url:imgs){
            urls.add(url+"!pdetail2x");
        }
        this.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, urls)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.hompage_select_0, R.mipmap.hompage_select_1})
                //设置指示器的方向
                .setPageIndicatorAlign(PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        convenientBanner.setManualPageable(false);//设置不能手动影响
    }

    public static class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
            Glide.with(context).load(data).fitCenter().placeholder(R.mipmap.placeholder_big).into(imageView);
       }
    }
}
