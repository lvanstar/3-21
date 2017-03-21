package com.qiansong.msparis.app.fulldress.util;

import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

/**
 * Created by yechen on 2017/2/9.
 */

public class AnimationUtil {

    // 从上面张开动画 动画
    public static Animation getScaleAnimation_out() {
        Animation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0f);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(100);
        return animation;
    }
    // 往上面收缩动画 动画
    public static Animation getScaleAnimation_in() {
        Animation animation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0f);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(100);
        return animation;
    }
}
