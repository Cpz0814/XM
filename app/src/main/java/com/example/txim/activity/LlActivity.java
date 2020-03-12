package com.example.txim.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.txim.R;
import com.example.txim.utils.DatabaseHelper;
import com.example.txim.utils.ToastUtils;
import com.example.txim.utils.UserBean;
import com.example.txim.utils.UserService;
import com.tencent.imsdk.TIMManager;
import com.tencent.qcloud.tim.uikit.component.dialog.TUIKitDialog;
//ia7llh7l
public class LlActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView tc;
    private EditText ed_lj;
    private Button bt_xg;
    private UserService service;
    private UserBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ll);
        ed_lj=findViewById(R.id.ed_lj);
        tc=findViewById(R.id.tcs);
        tc.setOnClickListener(this);
        bt_xg=findViewById(R.id.bt_xg);
        bt_xg.setOnClickListener(this);
        service=new UserService(LlActivity.this);
        String ssd= TIMManager.getInstance().getLoginUser();
        bean=service.find2(ssd);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.tcs){
            LlActivity.this.finish();
        }else if (view.getId()==R.id.bt_xg){
            if (!ed_lj.getText().toString().equals("")){
                new TUIKitDialog(LlActivity.this)
                        .builder()
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setTitle("修改后请重新登录！")
                        .setDialogWidth(0.75f)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String mm=ed_lj.getText().toString();
                                DatabaseHelper databaseHelper=new DatabaseHelper(LlActivity.this);
                                SQLiteDatabase db= databaseHelper.getWritableDatabase();
                                ContentValues values=new ContentValues();
                                values.put("autograph",mm);
                                db.update("user",values,"id=?",new String[]{String.valueOf(bean.getId())});
                                db.close();
                                LlActivity.this.finish();
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }else {
                ToastUtils.toastShow(this,"连接码不可为空",2);
            }
        }
    }
}
