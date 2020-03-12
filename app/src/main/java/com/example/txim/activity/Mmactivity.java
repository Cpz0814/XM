package com.example.txim.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.txim.DemoApplication;
import com.example.txim.R;
import com.example.txim.utils.DatabaseHelper;
import com.example.txim.utils.ToastUtils;
import com.example.txim.utils.UserBean;
import com.example.txim.utils.UserService;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.component.dialog.TUIKitDialog;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

public class Mmactivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView tc;
    private EditText ed_mm,ed_mms;
    private Button bt_xg;
    private UserService service;
    private UserBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mm_activity);
        tc=findViewById(R.id.tc);
        tc.setOnClickListener(this);
        bt_xg=findViewById(R.id.bt_xg);
        bt_xg.setOnClickListener(this);
        ed_mm=findViewById(R.id.ed_mm);
        ed_mms=findViewById(R.id.ed_mms);
        service=new UserService(Mmactivity.this);
        String ssd= TIMManager.getInstance().getLoginUser();
        bean=service.find2(ssd);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.tc){
            Mmactivity.this.finish();
        }else if (view.getId()==R.id.bt_xg){
            String mm=ed_mm.getText().toString();
            String mms=ed_mms.getText().toString();
            if (!mm.equals("")&&!mms.equals("")){
                if (mm.equals(bean.getPassword())){
                    new TUIKitDialog(Mmactivity.this)
                            .builder()
                            .setCancelable(true)
                            .setCancelOutside(true)
                            .setTitle("您确定修改？")
                            .setDialogWidth(0.75f)
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String mm=ed_mms.getText().toString();
                                    DatabaseHelper databaseHelper=new DatabaseHelper(Mmactivity.this);
                                    SQLiteDatabase db= databaseHelper.getWritableDatabase();
                                    ContentValues values=new ContentValues();
                                    values.put("password",mm);
                                    db.update("user",values,"id=?",new String[]{String.valueOf(bean.getId())});
                                    db.close();
                                    Mmactivity.this.finish();
                                }
                            })
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            })
                            .show();
                }else {
                    ToastUtils.toastShow(this,"旧密码输入错误",2);
                }
            }else {
                ToastUtils.toastShow(this,"不能为空",2);
            }
        }
    }
}
