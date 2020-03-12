package com.example.txim.view;

import android.view.View;

import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;

public class ConOnclik {
    public static void customizeConversation(final ConversationLayout layout) {
        // 从 ConversationLayout 获取会话列表
        ConversationListLayout listLayout = layout.getConversationList();
        listLayout.setItemTopTextSize(16); // 设置 item 中 top 文字大小
        listLayout.setItemBottomTextSize(12);// 设置 item 中 bottom 文字大小
        listLayout.setItemDateTextSize(10);// 设置 item 中 timeline 文字大小
        listLayout.enableItemRoundIcon(true);// 设置 item 头像是否显示圆角，默认是方形
        listLayout.disableItemUnreadDot(false);// 设置 item 是否不显示未读红点，默认显示
        // 长按弹出菜单
        listLayout.setOnItemLongClickListener(new ConversationListLayout.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(View view, int position, ConversationInfo conversationInfo) {
            }
        });
    }
}
