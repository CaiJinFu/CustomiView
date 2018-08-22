package com.example.jin.customizeview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.circleviewlibrary.UnrealView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.uv)
    UnrealView mUv;
    @BindView(R.id.btn)
    AppCompatButton mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mUv = findViewById(R.id.uv);
//        mUv.setCount(132);
        getScreenDensity_ByWindowManager();
        final View view = findViewById(R.id.btn);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightPixels = dm.heightPixels;
        int widthPixels = dm.widthPixels;
        float density = dm.density;
        float heightDP = heightPixels / density;
        float widthDP = widthPixels / density;
        float smallestWidthDP;
        if (widthDP < heightDP) {
            smallestWidthDP = widthDP;
        } else {
            smallestWidthDP = heightDP;
        }
        Log.d(TAG, "最小宽度: " + smallestWidthDP + "dp");

        view.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "按钮设置宽度为170，在这个屏幕的Width: " + px2dip(MainActivity.this, view.getWidth()) + "dp");
                Log.d(TAG, "按钮设置宽度为50，在这个屏幕的Height: " + px2dip(MainActivity.this, view.getHeight()) + "dp");
            }
        });
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        getScreenDensity_ByWindowManager();
        Log.d(TAG, "按钮Width: " + px2dip(this, mBtn.getWidth()));
        Log.d(TAG, "按钮Height: " + px2dip(this, mBtn.getHeight()));
    }

    //获得手机的宽度和高度像素单位为px
// 通过WindowManager获取
    public void getScreenDensity_ByWindowManager() {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;
        Log.d(TAG, "Screen Ratio: [" + width + "x" + height + "],density=" + density + ",densityDpi=" + densityDpi);
        Log.d(TAG, "Screen mDisplayMetrics: " + mDisplayMetrics);
    }

    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private static final String TAG = "MainActivity";

}
