package com.example.circleviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
    private String mCount;
    private Drawable mDrawable;

    public UnrealView2(@NonNull Context context) {
        this(context, null);
        initView(context);
    }

    public UnrealView2(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        initView(context);
    }

    public UnrealView2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UnrealView2);
        mCount = a.getString(R.styleable.UnrealView2_count2);
        mDrawable = a.getDrawable(R.styleable.UnrealView2_icon2);
        a.recycle();
    }

    public void setCount(int count) {
        mTvAllMsg.setText("" + count);
    }

    public void setIcon(int drawable) {
        mIvInformation.setBackground(ContextCompat.getDrawable(mContext, drawable));
    }

    private void initView(Context context) {
        mContext = context;
        if (mView == null) {
            //初始化
            mInflater = LayoutInflater.from(getContext());
            //添加布局文件
            mView = mInflater.inflate(R.layout.main_toolbar_right_item, this, true);
            /*//绑定控件
            mIvInformation = mView.findViewById(R.id.iv_information);
            mTvAllMsg = mView.findViewById(R.id.tv_allMsg);
            //然后使用LayoutParams把控件添加到子view中
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
            addView(mView, lp);*/
        }
    }

    /**
     * 此方法会在所有的控件都从xml文件中加载完成后调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //绑定控件
        mIvInformation = findViewById(R.id.iv_information);
        mTvAllMsg = findViewById(R.id.tv_allMsg);
        mTvAllMsg.setText(mCount);
        mIvInformation.setBackground(mDrawable);
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
