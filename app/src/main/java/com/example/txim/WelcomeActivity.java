package com.example.txim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.txim.activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Timer cpz_timer=new Timer();
        TimerTask cpz_task=new TimerTask() {
            @Override
            public void run() {
                Intent cpz_intent=new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(cpz_intent);
                WelcomeActivity.this.finish();
            }
        };
        cpz_timer.schedule(cpz_task,3000);
    }
}
