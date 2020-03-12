package com.example.txim.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.txim.DemoApplication;
import com.example.txim.HY.NewFriend;
import com.example.txim.R;
import com.example.txim.activity.FriendActivity;
import com.example.txim.activity.HmdActivity;
import com.example.txim.activity.QlActivity;
import com.example.txim.adapter.Menu;
import com.example.txim.utils.ToastUtils;
import com.tencent.qcloud.tim.uikit.component.dialog.TUIKitDialog;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactItemBean;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactLayout;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactListView;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HyFragment extends Fragment {
    public HyFragment() {
    }
    private ContactLayout cpz_contactLayout;
    private Menu cpz_menu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cpz_view=inflater.inflate(R.layout.contact_fragment,container,false);
        cpz_contactLayout=cpz_view.findViewById(R.id.contact_layout);
        cpz_menu = new Menu(getActivity(), cpz_contactLayout.getTitleBar(), Menu.MENU_TYPE_CONTACT);
        cpz_contactLayout.getTitleBar().setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cpz_menu.isShowing()) {
                    cpz_menu.hide();
                } else {
                    cpz_menu.show();
                }
            }
        });
        cpz_contactLayout.getContactListView().setOnItemClickListener(new ContactListView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ContactItemBean contact) {
                if (position == 0) {
                    new TUIKitDialog(getActivity())
                            .builder()
                            .setCancelable(true)
                            .setCancelOutside(true)
                            .setTitle("添加好友会自动同意！")
                            .setDialogWidth(0.75f)
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            })
                            .show();
                } else if (position == 1) {
                    Intent cpz_intent = new Intent(DemoApplication.instance(), QlActivity.class);
                    cpz_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    DemoApplication.instance().startActivity(cpz_intent);
                } else if (position == 2) {
                    Intent cpz_intent = new Intent(DemoApplication.instance(), HmdActivity.class);
                    cpz_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    DemoApplication.instance().startActivity(cpz_intent);
                } else {
                    Intent cpz_intent = new Intent(DemoApplication.instance(), FriendActivity.class);
                    cpz_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    cpz_intent.putExtra(TUIKitConstants.ProfileType.CONTENT, contact);
                    DemoApplication.instance().startActivity(cpz_intent);
                }
            }
        });
        refreshData();
        return cpz_view;
    }
    private void refreshData() {
        // 通讯录面板的默认UI和交互初始化
        cpz_contactLayout.initDefault();
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        refreshData();
    }
}
