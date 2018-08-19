package com.example.jin.customizeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.circleviewlibrary.UnrealView2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.uv)
    UnrealView2 mUv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mUv = findViewById(R.id.uv);
//        mUv.setCount(132);
    }
}
