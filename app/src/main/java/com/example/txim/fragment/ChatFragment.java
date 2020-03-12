package com.example.txim.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.txim.DemoApplication;
import com.example.txim.R;
import com.example.txim.activity.FriendActivity;
import com.example.txim.helpl.ChatLayoutHelper;
import com.example.txim.utils.Constants;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.base.BaseFragment;
import com.tencent.qcloud.tim.uikit.component.AudioPlayer;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;

public class ChatFragment extends BaseFragment {
    private ChatLayout chatLayout;
    private TitleBarLayout mTitleBar;
    private ChatInfo mChatInfo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mChatInfo = (ChatInfo) bundle.getSerializable(Constants.CHAT_INFO);
        if (mChatInfo == null) {
            return null;
        }
        View view=inflater.inflate(R.layout.chat_fragment,container,false);
        chatLayout = view.findViewById(R.id.chat_layout);
        chatLayout.initDefault();
        ChatLayoutHelper.customizeChatLayout(getActivity(), chatLayout);
        chatLayout.setChatInfo(mChatInfo);
        mTitleBar = chatLayout.getTitleBar();
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        init();
        return view;
    }
    private void init() {
        if (mChatInfo.getType() == TIMConversationType.C2C) {
            mTitleBar.setOnRightClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DemoApplication.instance(), FriendActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(TUIKitConstants.ProfileType.CONTENT, mChatInfo);
                    DemoApplication.instance().startActivity(intent);
                }
            });
            chatLayout.getMessageLayout().setOnItemClickListener(new MessageLayout.OnItemClickListener() {
                @Override
                public void onMessageLongClick(View view, int position, MessageInfo messageInfo) {
                    chatLayout.getMessageLayout().showItemPopMenu(position - 1, messageInfo, view);
                }

                @Override
                public void onUserIconClick(View view, int position, MessageInfo messageInfo) {
                    if (null == messageInfo || null == messageInfo.getTIMMessage()) {
                        return;
                    }
                    ChatInfo info = new ChatInfo();
                    info.setId(messageInfo.getTIMMessage().getSender());
                    /*Intent intent = new Intent(DemoApplication.instance(), FriendProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(TUIKitConstants.ProfileType.CONTENT, info);
                    DemoApplication.instance().startActivity(intent);*/
                }
            });
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        AudioPlayer.getInstance().stopPlayRecord();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        chatLayout.exitChat();
    }
}
