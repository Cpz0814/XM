package com.example.txim.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.txim.R;
import com.example.txim.utils.UserBean;
import com.example.txim.utils.UserService;

import org.apache.commons.lang.RandomStringUtils;

public class ZhuCeActivity extends AppCompatActivity {
    private Button bt_zc;
    private EditText et_name,et_pawd,et_pawds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        initView();
        bt_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et_name.getText().toString();
                String pswd=et_pawd.getText().toString();
                String pswds=et_pawds.getText().toString();
                UserService userService=new UserService(ZhuCeActivity.this);
                if (!name.equals("")&&!pswd.equals("")){
                    if (!pswds.equals("")&&pswds.equals(pswd)){
                        UserBean bean=new UserBean(name,pswd,name,"暂无名称");
                        userService.register(bean);
                        Intent cpz_intent=new Intent(ZhuCeActivity.this,MainActivity.class);
                        cpz_intent.putExtra("name",name);
                        cpz_intent.putExtra("pswd",pswd);
                        startActivity(cpz_intent);
                        Toast.makeText(ZhuCeActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        ZhuCeActivity.this.finish();
                    }else {
                        Toast.makeText(ZhuCeActivity.this, "密码输入不一致", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ZhuCeActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void initView() {
        bt_zc=findViewById(R.id.bt_zc);
        et_name=findViewById(R.id.et_name);
        et_pawd=findViewById(R.id.et_pawd);
        et_pawds=findViewById(R.id.et_pawds);
    }
}
