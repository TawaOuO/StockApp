package com.example.stock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button mBtnMultiChoose = (Button) findViewById(R.id.multi_choose_button);
        Button mBtnTecChoose = (Button) findViewById(R.id.tec_choose_button);
        Button mBtnChipChoose = (Button) findViewById(R.id.chip_choose_button);
        Button mBtnBasicChoose = (Button) findViewById(R.id.basic_choose_button);
        mBtnMultiChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(
                        HomeActivity.this, PickingActivity.class);
                startActivity(intent);
            }
        });
        mBtnTecChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(
                        HomeActivity.this, TecPickingActivity.class);
                startActivity(intent);
            }
        });
        mBtnBasicChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(
                        HomeActivity.this, BasicPickingActivity.class);
                startActivity(intent);
            }
        });
        mBtnChipChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(
                        HomeActivity.this, ChipPickingActivity.class);
                startActivity(intent);
            }
        });

    }
}