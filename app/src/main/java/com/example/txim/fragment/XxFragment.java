package com.example.txim.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.txim.DemoApplication;
import com.example.txim.R;
import com.example.txim.activity.ChatActivity;
import com.example.txim.adapter.Menu;
import com.example.txim.utils.Constants;
import com.example.txim.view.ConOnclik;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;

public class XxFragment extends Fragment {
    public XxFragment() {
    }
    private ConversationLayout cpz_conversationLayout;
    private Menu cpz_menu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.conversation_fragment,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        cpz_conversationLayout=view.findViewById(R.id.conversation_layout);
        cpz_menu = new Menu(getActivity(),cpz_conversationLayout.getTitleBar(), Menu.MENU_TYPE_CONVERSATION);
        cpz_conversationLayout.initDefault();
        cpz_conversationLayout.getConversationList().setOnItemClickListener(new ConversationListLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ConversationInfo messageInfo) {
                startChatActivity(messageInfo);
            }
        });
        TitleBarLayout titleBarLayout = cpz_conversationLayout.findViewById(R.id.conversation_title);
        titleBarLayout.setTitle("信息列表", TitleBarLayout.POSITION.MIDDLE);
        titleBarLayout.getLeftGroup().setVisibility(View.GONE);
        titleBarLayout.setRightIcon(R.drawable.conversation_more);
        titleBarLayout.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cpz_menu.isShowing()) {
                    cpz_menu.hide();
                } else {
                    cpz_menu.show();
                }
            }
        });
        ConOnclik.customizeConversation(cpz_conversationLayout);
    }
    private void startChatActivity(ConversationInfo conversationInfo) {
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(conversationInfo.isGroup() ? TIMConversationType.Group : TIMConversationType.C2C);
        chatInfo.setId(conversationInfo.getId());
        chatInfo.setChatName(conversationInfo.getTitle());
        Intent intent = new Intent(DemoApplication.instance(), ChatActivity.class);
        intent.putExtra(Constants.CHAT_INFO, chatInfo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        DemoApplication.instance().startActivity(intent);
    }
}
