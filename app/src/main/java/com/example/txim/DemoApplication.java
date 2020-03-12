package com.example.txim;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.example.txim.utils.PrivateConstants;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;

public class DemoApplication extends Application {
    public static final int SDKAPPID = 1400267156;
    private static DemoApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();

        // 配置 Config，请按需配置
        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new TIMSdkConfig(SDKAPPID));
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());
        TUIKit.init(this, SDKAPPID, configs);
        MultiDex.install(this);
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setAppVersion(TIMManager.getInstance().getVersion());
        CrashReport.initCrashReport(getApplicationContext(), PrivateConstants.BUGLY_APPID, true, strategy);
        instance=this;
    }
    public static DemoApplication instance() {
        return instance;
    }
}
