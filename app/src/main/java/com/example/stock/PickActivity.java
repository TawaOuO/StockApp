package com.example.stock;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PickActivity extends Activity{

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        ArrayList<View> mPages = new ArrayList<>();

        for (int i=0;i<3;i++) {//新增3個分頁
            Pagers pager = new Pagers(this, (i + 1));
            mPages.add(pager);
        }

        ViewPager viewPager = findViewById(R.id.mViewPager);
        TabLayout tab = findViewById(R.id.tab);


        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(mPages);
        tab.setupWithViewPager(viewPager);//將TabLayout綁定給ViewPager
        viewPager.setAdapter(myPagerAdapter);//綁定適配器
        viewPager.setCurrentItem(0);//指定跳到某頁，一定得設置在setAdapter後面


//        Button mBtnGotoEnd = findViewById(R.id.pickend);
//
//        mBtnGotoEnd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent;
//                intent = new Intent(PickActivity.this, End.class);
//                startActivity(intent);
//            }
//        });

    }
}