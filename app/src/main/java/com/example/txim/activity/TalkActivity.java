package com.example.txim.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.txim.R;
import com.example.txim.adapter.FragmentAdapter;
import com.example.txim.fragment.WoFragment;
import com.example.txim.fragment.HyFragment;
import com.example.txim.fragment.XxFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tencent.qcloud.tim.uikit.utils.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class TalkActivity extends AppCompatActivity {
    private BottomNavigationView navigation;
    private ViewPager cpz_vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        navigation=findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_xx);
        cpz_vp=findViewById(R.id.viewPager);
        cpz_vp=findViewById(R.id.viewPager);
        FileUtil.initPath();
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new XxFragment());
        fragments.add(new HyFragment());
        fragments.add(new WoFragment());
        cpz_vp.setAdapter(new FragmentAdapter(getSupportFragmentManager(),this,fragments));
        cpz_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                navigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        cpz_vp.setOffscreenPageLimit(3);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_xx:
                        cpz_vp.setCurrentItem(0);
                        return true;
                    case R.id.navigation_hy:
                        cpz_vp.setCurrentItem(1);
                        return true;
                    case R.id.navigation_dt:
                        cpz_vp.setCurrentItem(2);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
