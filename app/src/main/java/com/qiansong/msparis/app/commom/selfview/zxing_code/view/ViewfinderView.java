/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qiansong.msparis.app.commom.selfview.zxing_code.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.zxing_code.camera.CameraManager;
import com.qiansong.msparis.app.commom.util.DisplayUtil;

import java.util.Collection;
import java.util.HashSet;



/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points.
 */
public final class ViewfinderView extends View {
    private static final String TAG = "log";
    /**
     * ˢ�½����ʱ��
     */
    private static final long ANIMATION_DELAY = 5L;
    private static final int OPAQUE = 0xFF;

    /**
     * �ĸ���ɫ�߽Ƕ�Ӧ�ĳ���
     */
    private int ScreenRate;

    /**
     * �ĸ���ɫ�߽Ƕ�Ӧ�Ŀ��
     */
    private static final int CORNER_WIDTH = 10;
    /**
     * ɨ����е��м��ߵĿ��
     */
    private static final int MIDDLE_LINE_WIDTH = 6;

    /**
     * ɨ����е��м��ߵ���ɨ������ҵļ�϶
     */
    private static final int MIDDLE_LINE_PADDING = 5;

    /**
     * �м�������ÿ��ˢ���ƶ��ľ���
     */
    private static final int SPEEN_DISTANCE = 5;

    /**
     * �ֻ����Ļ�ܶ�
     */
    private static float density;
    /**
     * �����С
     */
    private static final int TEXT_SIZE = 12;
    /**
     * �������ɨ�������ľ���
     */
    private static final int TEXT_PADDING_TOP = 10;

    /**
     * ���ʶ��������
     */
    private Paint paint;

    /**
     * �м们���ߵ����λ��
     */
    private int slideTop;

    /**
     * �м们���ߵ���׶�λ��
     */
    private int slideBottom;

    /**
     * ��ɨ��Ķ�ά��������������û��������ܣ���ʱ������
     */
    private Bitmap resultBitmap;
    private final int maskColor;//周围颜色
    private final int resultColor;//中间颜色
    private int laserLineResId;//扫描线图片资源

    private final int resultPointColor;
    private Collection<ResultPoint> possibleResultPoints;
    private Collection<ResultPoint> lastPossibleResultPoints;

    boolean isFirst;
    private int withs;

    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        density = context.getResources().getDisplayMetrics().density;
        //������ת����dp
        ScreenRate = (int) (30 * density);//四角长度

        paint = new Paint();
        Resources resources = getResources();
        maskColor = resources.getColor(R.color.mp_bantouming);
        resultColor = resources.getColor(R.color.mp_touming);

        laserLineResId=resources.getIdentifier("chat_sao","mipmap","com.qiansong.msparis");
        resultPointColor = resources.getColor(R.color.white);
        possibleResultPoints = new HashSet<ResultPoint>(5);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //�м��ɨ�����Ҫ�޸�ɨ���Ĵ�С��ȥCameraManager�����޸�
        Rect frame = CameraManager.get().getFramingRect();
        if (frame == null) {
            return;
        }

        //��ʼ���м��߻��������ϱߺ����±�
        if (!isFirst) {
            isFirst = true;
            slideTop = frame.top;
            slideBottom = frame.bottom;
        }

        //��ȡ��Ļ�Ŀ�͸�
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        withs = width;

        paint.setColor(resultBitmap != null ? resultColor : maskColor);

        //����ɨ����������Ӱ���֣����ĸ����֣�ɨ�������浽��Ļ���棬ɨ�������浽��Ļ����
        //ɨ��������浽��Ļ��ߣ�ɨ�����ұߵ���Ļ�ұ�
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
                paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);


        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            paint.setAlpha(OPAQUE);
            canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
        } else {


            paint.setColor(Color.parseColor("#898b84"));
            canvas.drawRect(frame.left, frame.top, frame.right + 1,
                    frame.top + 2, paint);
            canvas.drawRect(frame.left, frame.top + 2, frame.left + 2,
                    frame.bottom - 1, paint);
            canvas.drawRect(frame.right - 1, frame.top, frame.right + 1,
                    frame.bottom - 1, paint);
            canvas.drawRect(frame.left, frame.bottom - 1, frame.right + 1,
                    frame.bottom + 1, paint);

            //��ɨ�����ϵĽǣ��ܹ�8������
            paint.setColor(Color.parseColor("#fbfefe"));
            canvas.drawRect(frame.left, frame.top, frame.left + ScreenRate,
                    frame.top + CORNER_WIDTH, paint);
            canvas.drawRect(frame.left, frame.top, frame.left + CORNER_WIDTH, frame.top
                    + ScreenRate, paint);
            canvas.drawRect(frame.right - ScreenRate, frame.top, frame.right,
                    frame.top + CORNER_WIDTH, paint);
            canvas.drawRect(frame.right - CORNER_WIDTH, frame.top, frame.right, frame.top
                    + ScreenRate, paint);
            canvas.drawRect(frame.left, frame.bottom - CORNER_WIDTH, frame.left
                    + ScreenRate, frame.bottom, paint);
            canvas.drawRect(frame.left, frame.bottom - ScreenRate,
                    frame.left + CORNER_WIDTH, frame.bottom, paint);
            canvas.drawRect(frame.right - ScreenRate, frame.bottom - CORNER_WIDTH,
                    frame.right, frame.bottom, paint);
            canvas.drawRect(frame.right - CORNER_WIDTH, frame.bottom - ScreenRate,
                    frame.right, frame.bottom, paint);

            paint.setColor(Color.parseColor("#fbfefe"));
            //�����м����,ÿ��ˢ�½��棬�м���������ƶ�SPEEN_DISTANCE
            slideTop += SPEEN_DISTANCE;
            if (slideTop >= frame.bottom) {
                slideTop = frame.top;
            }
//            canvas.drawRect(frame.left + MIDDLE_LINE_PADDING, slideTop - MIDDLE_LINE_WIDTH / 2, frame.right - MIDDLE_LINE_PADDING, slideTop + MIDDLE_LINE_WIDTH / 2, paint);

           Bitmap laserLineBitmap = BitmapFactory.decodeResource(getResources(), laserLineResId);
            int h = laserLineBitmap.getHeight();//取原图高
            //如果没有设置线条高度，则用图片原始高度
//            if (laserLineHeight == Scanner.dp2px(getContext(), DEFAULT_LASER_LINE_HEIGHT)) {
//                laserLineHeight = laserLineBitmap.getHeight() / 2;
//            }
            Rect laserRect = new Rect(frame.left, slideTop, frame.right, slideTop + h);
            canvas.drawBitmap(laserLineBitmap, null, laserRect, paint);

            //��ɨ����������
            paint.setColor(Color.parseColor("#f3f7f9"));
            paint.setTextSize(TEXT_SIZE * density);
//            paint.setAlpha(0x40);
            paint.setTypeface(Typeface.create("System", Typeface.BOLD));
            float w = paint.measureText(getResources().getString(R.string.scan_text));
            canvas.drawText(getResources().getString(R.string.scan_text), ((withs - w) / 2), (float) (frame.bottom + (float) TEXT_PADDING_TOP * density) + DisplayUtil.dip2px(getContext(), 10), paint);


            Collection<ResultPoint> currentPossible = possibleResultPoints;
            Collection<ResultPoint> currentLast = lastPossibleResultPoints;
            if (currentPossible.isEmpty()) {
                lastPossibleResultPoints = null;
            } else {
                possibleResultPoints = new HashSet<ResultPoint>(5);
                lastPossibleResultPoints = currentPossible;
                paint.setAlpha(OPAQUE);
                paint.setColor(resultPointColor);
                for (ResultPoint point : currentPossible) {
                    canvas.drawCircle(frame.left + point.getX(), frame.top
                            + point.getY()*2, 6.0f, paint);
                }
            }
            if (currentLast != null) {
                paint.setAlpha(OPAQUE / 2);
                paint.setColor(resultPointColor);
                for (ResultPoint point : currentLast) {
                    canvas.drawCircle(frame.left + point.getX(), frame.top
                            + point.getY(), 3.0f, paint);
                }
            }


            //ֻˢ��ɨ�������ݣ�����ط���ˢ��
            postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
                    frame.right, frame.bottom);

        }
    }

    public void drawViewfinder() {
        resultBitmap = null;
        invalidate();
    }

    /**
     * Draw a bitmap with the result points highlighted instead of the live
     * scanning display.
     *
     * @param barcode An image of the decoded barcode.
     */
    public void drawResultBitmap(Bitmap barcode) {
        resultBitmap = barcode;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint point) {
        possibleResultPoints.add(point);
    }



}
