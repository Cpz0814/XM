package com.example.txim.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.txim.R;
import com.example.txim.SplashActivity;
import com.example.txim.fragment.ChatFragment;
import com.example.txim.utils.Constants;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;

public class ChatActivity extends AppCompatActivity {
    private ChatFragment mChatFragment;
    private ChatInfo mChatInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            startSplashActivity();
        } else {
            mChatInfo = (ChatInfo) bundle.getSerializable(Constants.CHAT_INFO);
            if (mChatInfo == null) {
                startSplashActivity();
                return;
            }
            mChatFragment = new ChatFragment();
            mChatFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.empty_view, mChatFragment).commitAllowingStateLoss();
        }
    }
    private void startSplashActivity() {
        Intent intent = new Intent(ChatActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();
    }
}
