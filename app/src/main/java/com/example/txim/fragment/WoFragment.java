package com.example.txim.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.txim.DemoApplication;
import com.example.txim.R;
import com.example.txim.activity.LlActivity;
import com.example.txim.activity.MainActivity;
import com.example.txim.activity.Mmactivity;
import com.example.txim.utils.Constants;
import com.example.txim.utils.DemoLog;
import com.example.txim.utils.Icon;
import com.example.txim.utils.ToastUtils;
import com.example.txim.utils.UserBean;
import com.example.txim.utils.UserService;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.BaseFragment;
import com.tencent.qcloud.tim.uikit.component.SelectionActivity;
import com.tencent.qcloud.tim.uikit.component.dialog.TUIKitDialog;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;
import com.tencent.qcloud.tim.uikit.modules.chat.GroupChatManagerKit;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationManagerKit;
import com.tencent.qcloud.tim.uikit.utils.FileUtil;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class WoFragment extends Fragment {
    public WoFragment() {
    }
    private TextView tv_id,tv_name,tv_bb;
    private ImageView im_tx;
    private Button bt_out;
    private LinearLayout xg_mc,xg_mm,ll_lj;
    public static String packageName(Context context) {
        PackageManager manager = context.getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cpz_view=inflater.inflate(R.layout.profile_fragment,container,false);
        tv_id=cpz_view.findViewById(R.id.tv_id);
        tv_name=cpz_view.findViewById(R.id.tv_name);
        im_tx=cpz_view.findViewById(R.id.im_tx);
        tv_bb=cpz_view.findViewById(R.id.tv_bb);
        String bb = packageName(getContext());
        tv_bb.setText("小聊天"+bb+"@陈鹏仔");
        xg_mc=cpz_view.findViewById(R.id.xg_mc);
        xg_mm=cpz_view.findViewById(R.id.xg_mm);
        xg_mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cpz_intent=new Intent(getActivity(), Mmactivity.class);
                startActivity(cpz_intent);
            }
        });
        ll_lj=cpz_view.findViewById(R.id.ll_lj);
        ll_lj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ssd= TIMManager.getInstance().getLoginUser();
                new TUIKitDialog(getActivity())
                        .builder()
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setTitle("您的连接码："+ssd)
                        .setDialogWidth(0.75f)
                        .setPositiveButton("修改", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent cpz_intent=new Intent(getActivity(), LlActivity.class);
                                startActivity(cpz_intent);
                            }
                        })
                        .setNegativeButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        });


        Icon.Icon(im_tx,getContext());
        UserService cpz_service=new UserService(getContext());
        String ssd=TIMManager.getInstance().getLoginUser();
        UserBean bean=cpz_service.find2(ssd);
        tv_id.setText(bean.getName());
        tv_name.setText(String.format(getResources().getString(R.string.id), TIMManager.getInstance().getLoginUser()));
        TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
            @Override
            public void onError(int i, String s) {
            }
            @Override
            public void onSuccess(TIMUserProfile timUserProfile) {
                tv_name.setText(timUserProfile.getNickName());
            }
        });
        xg_mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle cpz_bundle = new Bundle();
                cpz_bundle.putString(TUIKitConstants.Selection.TITLE, getResources().getString(R.string.modify_nick_name));
                cpz_bundle.putString(TUIKitConstants.Selection.INIT_CONTENT, (String) tv_name.getText());
                cpz_bundle.putInt(TUIKitConstants.Selection.LIMIT, 20);
                SelectionActivity.startTextSelection(getContext(), cpz_bundle, new SelectionActivity.OnResultReturnListener() {
                    @Override
                    public void onReturn(Object text) {
                        tv_name.setText(text.toString());
                        updateProfile();
                    }
                });
            }
        });
        bt_out=cpz_view.findViewById(R.id.bt_out);
        bt_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TUIKitDialog(getActivity())
                        .builder()
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setTitle("您确定要退出登录么？")
                        .setDialogWidth(0.75f)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TIMManager.getInstance().logout(new TIMCallBack() {
                                    @Override
                                    public void onError(int code, String desc) {
                                        ToastUtil.toastLongMessage("logout fail: " + code + "=" + desc);
                                    }
                                    @Override
                                    public void onSuccess() {
                                        logout(DemoApplication.instance(), false);
                                        TUIKit.unInit();
                                        if (getActivity() != null) {
                                            getActivity().finish();
                                        }
                                    }
                                });
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        });
        return cpz_view;
    }
    public static void logout(Context context, boolean autoLogin) {
        SharedPreferences shareInfo = context.getSharedPreferences(Constants.USERINFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shareInfo.edit();
        editor.putBoolean(Constants.AUTO_LOGIN, autoLogin);
        editor.commit();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.LOGOUT, true);
        context.startActivity(intent);
    }
    private void updateProfile() {
        HashMap<String, Object> hashMap = new HashMap<>();
        // 昵称
        String nickName = (String) tv_name.getText();
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_NICK, nickName);
        TIMFriendshipManager.getInstance().modifySelfProfile(hashMap, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                DemoLog.e(TAG, "modifySelfProfile err code = " + i + ", desc = " + s);
                ToastUtil.toastShortMessage("Error code = " + i + ", desc = " + s);
            }

            @Override
            public void onSuccess() {
                DemoLog.i(TAG, "modifySelfProfile success");
            }
        });
    }
}
