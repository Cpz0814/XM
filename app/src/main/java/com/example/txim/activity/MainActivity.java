package com.example.txim.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.txim.WelcomeActivity;
import com.example.txim.utils.Constants;
import com.example.txim.utils.GenerateTestUserSig;
import com.example.txim.R;
import com.example.txim.utils.UserBean;
import com.example.txim.utils.UserService;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    EditText et_name,et_mm;
    Button bt_Login;
    TextView tv_zc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=findViewById(R.id.et_login);
        et_mm=findViewById(R.id.et_mm);
        bt_Login=findViewById(R.id.bt_login);
        tv_zc=findViewById(R.id.tv_zc);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cpz_name=et_name.getText().toString();
                String mm=et_mm.getText().toString();
                UserService service=new UserService(MainActivity.this);
                boolean cpz_flag=service.login(cpz_name,mm);
                if (cpz_flag){
                    UserBean cc_bean= service.find(cpz_name);
                    String us_name=cc_bean.getAutograph();
                    String us= GenerateTestUserSig.genTestUserSig(us_name);
                    login(us_name,us);
                }else {
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tv_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cpz_intent=new Intent(MainActivity.this,ZhuCeActivity.class);
                startActivity(cpz_intent);
            }
        });
    }
    private void login(String name,String us){
        TUIKit.login(name, us, new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                SharedPreferences shareInfo = getSharedPreferences(Constants.USERINFO, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shareInfo.edit();
                editor.putBoolean(Constants.AUTO_LOGIN, true);
                editor.commit();
                Intent intent = new Intent(MainActivity.this, TalkActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onError(String module, final int errCode, final String errMsg) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this, "登录失败==="+errCode+"==="+errMsg, Toast.LENGTH_SHORT).show();
                    }
                });
                Log.i(TAG, "imLogin errorCode = " + errCode + ", errorInfo = " + errMsg);
            }
        });
    }
}
