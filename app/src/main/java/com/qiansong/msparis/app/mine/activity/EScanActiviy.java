package com.qiansong.msparis.app.mine.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.zxing_code.decoding.CaptureActivityHandler;
import com.qiansong.msparis.app.commom.selfview.zxing_code.view.ViewfinderView;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.selfview.zxing_code.camera.CameraManager;
import com.qiansong.msparis.app.commom.selfview.zxing_code.decoding.InactivityTimer;

import java.io.IOException;
import java.util.Vector;



/**
 * 扫描快递单专用页面
 */
public abstract class EScanActiviy extends Activity implements SurfaceHolder.Callback {

    public static CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private static Vector<BarcodeFormat> decodeFormats;
    private static String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    private static SurfaceView surfaceView;

    private static EScanActiviy INSTANCE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scan);
        init_layout();//在这个方法里填充布局 要注意的是id要一一对应 不然会报空
        INSTANCE = this;
        CameraManager.init(INSTANCE);
        viewfinderView = (ViewfinderView) findViewById(R.id.capture_viewfinder);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(INSTANCE);

        init();
    }
    //填充布局 eg: R.layout.activity_scan
    public abstract void init_layout();
    //一开始进来执行
    public abstract void init();
    //扫描到结果后执行  cotent就是结果字符串
    public abstract void todo(String cotent);

    @Override
    protected void onResume() {
        super.onResume();
        surfaceView = (SurfaceView) findViewById(R.id.capture_preview);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        decodeFormats = null;
        characterSet = null;
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(INSTANCE);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }


    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        String resultString = result.getText();
        ContentUtil.makeLog("lzz","format:"+result.getBarcodeFormat().getName());
        getTicketInfo(resultString);
    }

    private static void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(INSTANCE, decodeFormats,
                    characterSet);
        }
    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

//            AssetFileDescriptor file = getResources().openRawResourceFd(
//                    R.raw.beep);
//            try {
//                mediaPlayer.setDataSource(file.getFileDescriptor(),
//                        file.getStartOffset(), file.getLength());
//                file.close();
//                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
//                mediaPlayer.prepare();
//            } catch (IOException e) {
//                mediaPlayer = null;
//            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        ContentUtil.makeLog("holder==null?", holder + "");
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    private static final long VIBRATE_DURATION = 100L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };


    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    private String serial;

    private void getTicketInfo(String serial) {
        this.serial = serial;
        ContentUtil.makeLog("yc","扫二维码结果是："+serial);

        todo(serial);

    }


}
