package com.example.quanlyquancf.Lop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.quanlyquancf.AdapterR.PageAdapter;
import com.example.quanlyquancf.Fragment.tabBan;
import com.example.quanlyquancf.Fragment.tabBanTrong;
import com.example.quanlyquancf.R;
import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_manhinhchinh);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLay1);
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        Setup(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void Setup(ViewPager viewPager)
    {
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        adapter.addFragment(new tabBan(),"Bàn hiện có");
        adapter.addFragment(new tabBanTrong(),"Bàn trống");
        viewPager.setAdapter(adapter);
    }
}
