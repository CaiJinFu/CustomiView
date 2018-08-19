package com.example.circleviewlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author caijinfu
 * @date 2018/8/16
 * @describe
 */
public class UnrealView2 extends FrameLayout {

    private View mView;
    private LayoutInflater mInflater;
    private ImageView mIvInformation;
    private TextView mTvAllMsg;
    private Context mContext;
    private int mWidthMeasureSpec;

    public UnrealView2(@NonNull Context context) {
        super(context);
        init(context);
    }

    public UnrealView2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UnrealView2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        initView();
    }

    private void initView() {
        if (mView == null) {
            //初始化
            mInflater = LayoutInflater.from(getContext());
            //添加布局文件
            mView = mInflater.inflate(R.layout.main_toolbar_right_item, null);
            //绑定控件
            mIvInformation = mView.findViewById(R.id.iv_information);
            mTvAllMsg = mView.findViewById(R.id.tv_allMsg);
            //然后使用LayoutParams把控件添加到子view中
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
            addView(mView, lp);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidthMeasureSpec = widthMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        MarginLayoutParams params = (MarginLayoutParams) mTvAllMsg.getLayoutParams();
        int leftMargin;
        int mode = MeasureSpec.getMode(mWidthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            leftMargin = getMeasuredWidth() / 2 - 10;
        } else {
            leftMargin = getMeasuredWidth() / 2 > mIvInformation.getWidth() ? (getMeasuredWidth() / 2 + mIvInformation.getWidth() / 5) : mIvInformation.getWidth() - 20;
        }
        Log.i(TAG, "onLayout: " + leftMargin);
        Log.i(TAG, "MeasuredWidth: " + getMeasuredWidth());
        Log.i(TAG, "IvInformationWidth: " + mIvInformation.getWidth());
        Log.i(TAG, "父容器的一半宽度是否大于图片宽度: " + (getMeasuredWidth() / 2 > mIvInformation.getWidth()));
        params.leftMargin = leftMargin;
        mTvAllMsg.setLayoutParams(params);
    }

    private static final String TAG = "UnrealView";

}
