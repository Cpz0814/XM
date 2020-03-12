package com.example.txim.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;

public class Icon {
    public static ImageView Icon(ImageView ss, Context context){
        TIMUserProfile profile = TIMFriendshipManager.getInstance().querySelfProfile();
        if (profile != null) {
            if (!TextUtils.isEmpty(profile.getFaceUrl())) {
                GlideEngine.loadImage(ss, Uri.parse(profile.getFaceUrl()));
            }
        } else {
            SharedPreferences shareInfo = context.getSharedPreferences(Constants.USERINFO, Context.MODE_PRIVATE);
            String url = shareInfo.getString(Constants.ICON_URL, "");
            if (!TextUtils.isEmpty(url)) {
                GlideEngine.loadImage(ss, Uri.parse(url));
            }
        }
        return ss;
    }
}
