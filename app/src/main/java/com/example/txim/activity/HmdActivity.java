package com.example.txim.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.txim.R;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;

public class HmdActivity extends AppCompatActivity {
    private TitleBarLayout mTitleBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hmd_activity);
        mTitleBar = findViewById(R.id.group_list_titlebar);
        mTitleBar.getRightGroup().setVisibility(View.GONE);
        mTitleBar.setTitle(getResources().getString(R.string.blacklist), TitleBarLayout.POSITION.MIDDLE);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
