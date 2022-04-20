package com.example.stock;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.stock.R.drawable.bg_long_btn;
import static com.example.stock.R.drawable.bg_short_btn;

public class StairsPickingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking);

        //宣告所有按鈕
        final TextView num_choose_text = findViewById(R.id.num_choose_text);
        final Button mBtn5ma_break_up = findViewById(R.id.tec_break_5ma_up);
        final Button mBtn10ma_break_up = findViewById(R.id.tec_break_10ma_up);
        final Button mBtn20ma_break_up = findViewById(R.id.tec_break_20ma_up);
        final Button mBtnday_kd_break_up = findViewById(R.id.tec_break_day_kd_up);
        final Button mBtnday_MACD_break_up = findViewById(R.id.tec_break_day_MACD_up);
        final Button mBtn5ma_break_down = findViewById(R.id.tec_break_5ma_down);
        final Button mBtn10ma_break_down = findViewById(R.id.tec_break_10ma_down);
        final Button mBtn20ma_break_down = findViewById(R.id.tec_break_20ma_down);
        final Button mBtnday_kd_break_down = findViewById(R.id.tec_break_day_kd_down);
        final Button mBtnday_MACD_break_down = findViewById(R.id.tec_break_day_MACD_down);
        final Button mBtnforeign_investor_buyout_3days = findViewById(R.id.chip_foreign_investor_buyout_3days);
        final Button mBtnforeign_investor_buyout_5days = findViewById(R.id.chip_foreign_investor_buyout_5days);
        final Button mBtninvestment_trust_buyout_3days = findViewById(R.id.chip_investment_trust_buyout_3days);
        final Button mBtninvestment_trust_buyout_5days = findViewById(R.id.chip_investment_trust_buyout_5days);
        final Button mBtnforeign_investor_soldout_3days = findViewById(R.id.chip_foreign_investor_soldout_3days);
        final Button mBtnforeign_investor_soldout_5days = findViewById(R.id.chip_foreign_investor_soldout_5days);
        final Button mBtninvestment_trust_soldout_3days = findViewById(R.id.chip_investment_trust_soldout_3days);
        final Button mBtninvestment_trust_soldout_5days = findViewById(R.id.chip_investment_trust_soldout_5days);
        final Button mBtnstock_volume = findViewById(R.id.stock_volume);
        final Button mBtnmonthly_income_increase = findViewById(R.id.basic_monthly_income_increase);
        final Button mBtnmonthly_income_decrease = findViewById(R.id.basic_monthly_income_decrease);
        final Button mBtnquarterly_income_increase = findViewById(R.id.basic_quarterly_income_increase);
        final Button mBtnquarterly_income_decrease = findViewById(R.id.basic_quarterly_income_decrease);
        final Button mBtnquarterly_eps_increase = findViewById(R.id.basic_quarterly_eps_increase);
        final Button mBtnquarterly_eps_decrease = findViewById(R.id.basic_quarterly_eps_decrease);
        final Button mBtnquarterly_income_newhigh = findViewById(R.id.basic_quarterly_income_newhigh);
        final Button mBtnquarterly_income_newlow = findViewById(R.id.basic_quarterly_income_newlow);
        final Button mBtnyield_percent = findViewById(R.id.basic_yield_percent);

        final int[] flag = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//設置tec checker
        final int[] flag1 = {0, 0, 0, 0, 0, 0, 0, 0};//設置chip checker
        final int[] flag2 = {0, 0, 0, 0, 0, 0, 0, 0, 0};//設置basic checker
        final int[] num_choose = {0};

        num_choose_text.setText("共選擇" + num_choose[0] + "個條件");

        mBtn5ma_break_up.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {//各按鈕事件
                if (flag[0] == 0) {
                    mBtn5ma_break_up.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag[0] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag[0] == 1) {
                    mBtn5ma_break_up.setBackgroundResource(bg_long_btn);
                    flag[0] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtn10ma_break_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[1] == 0) {
                    mBtn10ma_break_up.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag[1] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag[1] == 1) {
                    mBtn10ma_break_up.setBackgroundResource(bg_long_btn);
                    flag[1] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtn20ma_break_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[2] == 0) {
                    mBtn20ma_break_up.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag[2] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag[2] == 1) {
                    mBtn20ma_break_up.setBackgroundResource(bg_long_btn);
                    flag[2] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnday_kd_break_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[3] == 0) {
                    mBtnday_kd_break_up.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag[3] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag[3] == 1) {
                    mBtnday_kd_break_up.setBackgroundResource(bg_long_btn);
                    flag[3] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnday_MACD_break_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[4] == 0) {
                    mBtnday_MACD_break_up.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag[4] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag[4] == 1) {
                    mBtnday_MACD_break_up.setBackgroundResource(bg_long_btn);
                    flag[4] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnstock_volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[10] == 0) {
                    mBtnstock_volume.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag[10] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag[10] == 1) {
                    mBtnstock_volume.setBackgroundResource(bg_long_btn);
                    flag[10] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtn5ma_break_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[5] == 0) {
                    mBtn5ma_break_down.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag[5] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag[5] == 1) {
                    mBtn5ma_break_down.setBackgroundResource(bg_short_btn);
                    flag[5] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtn10ma_break_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[6] == 0) {
                    mBtn10ma_break_down.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag[6] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag[6] == 1) {
                    mBtn10ma_break_down.setBackgroundResource(bg_short_btn);
                    flag[6] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtn20ma_break_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[7] == 0) {
                    mBtn20ma_break_down.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag[7] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag[7] == 1) {
                    mBtn20ma_break_down.setBackgroundResource(bg_short_btn);
                    flag[7] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnday_kd_break_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[8] == 0) {
                    mBtnday_kd_break_down.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag[8] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag[8] == 1) {
                    mBtnday_kd_break_down.setBackgroundResource(bg_short_btn);
                    flag[8] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnday_MACD_break_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[9] == 0) {
                    mBtnday_MACD_break_down.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag[9] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag[9] == 1) {
                    mBtnday_MACD_break_down.setBackgroundResource(bg_short_btn);
                    flag[9] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnforeign_investor_buyout_3days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[0] == 0) {
                    mBtnforeign_investor_buyout_3days.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag1[0] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag1[0] == 1) {
                    mBtnforeign_investor_buyout_3days.setBackgroundResource(bg_long_btn);
                    flag1[0] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnforeign_investor_buyout_5days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[1] == 0) {
                    mBtnforeign_investor_buyout_5days.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag1[1] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag1[1] == 1) {
                    mBtnforeign_investor_buyout_5days.setBackgroundResource(bg_long_btn);
                    flag1[1] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtninvestment_trust_buyout_3days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[2] == 0) {
                    mBtninvestment_trust_buyout_3days.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag1[2] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag1[2] == 1) {
                    mBtninvestment_trust_buyout_3days.setBackgroundResource(bg_long_btn);
                    flag1[2] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtninvestment_trust_buyout_5days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[3] == 0) {
                    mBtninvestment_trust_buyout_5days.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag1[3] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag1[3] == 1) {
                    mBtninvestment_trust_buyout_5days.setBackgroundResource(bg_long_btn);
                    flag1[3] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnforeign_investor_soldout_3days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[4] == 0) {
                    mBtnforeign_investor_soldout_3days.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag1[4] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag1[4] == 1) {
                    mBtnforeign_investor_soldout_3days.setBackgroundResource(bg_short_btn);
                    flag1[4] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnforeign_investor_soldout_5days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[5] == 0) {
                    mBtnforeign_investor_soldout_5days.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag1[5] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag1[5] == 1) {
                    mBtnforeign_investor_soldout_5days.setBackgroundResource(bg_short_btn);
                    flag1[5] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtninvestment_trust_soldout_3days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[6] == 0) {
                    mBtninvestment_trust_soldout_3days.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag1[6] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag1[6] == 1) {
                    mBtninvestment_trust_soldout_3days.setBackgroundResource(bg_short_btn);
                    flag1[6] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtninvestment_trust_soldout_5days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[7] == 0) {
                    mBtninvestment_trust_soldout_5days.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag1[7] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag1[7] == 1) {
                    mBtninvestment_trust_soldout_5days.setBackgroundResource(bg_short_btn);
                    flag1[7] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnmonthly_income_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2[0] == 0) {
                    mBtnmonthly_income_increase.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag2[0] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag2[0] == 1) {
                    mBtnmonthly_income_increase.setBackgroundResource(bg_long_btn);
                    flag2[0] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnquarterly_income_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2[1] == 0) {
                    mBtnquarterly_income_increase.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag2[1] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag2[1] == 1) {
                    mBtnquarterly_income_increase.setBackgroundResource(bg_long_btn);
                    flag2[1] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnquarterly_eps_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2[2] == 0) {
                    mBtnquarterly_eps_increase.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag2[2] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag2[2] == 1) {
                    mBtnquarterly_eps_increase.setBackgroundResource(bg_long_btn);
                    flag2[2] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnquarterly_income_newhigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2[3] == 0) {
                    mBtnquarterly_income_newhigh.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag2[3] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag2[3] == 1) {
                    mBtnquarterly_income_newhigh.setBackgroundResource(bg_long_btn);
                    flag2[3] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnyield_percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2[4] == 0) {
                    mBtnyield_percent.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag2[4] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag2[4] == 1) {
                    mBtnyield_percent.setBackgroundResource(bg_long_btn);
                    flag2[4] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnmonthly_income_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2[5] == 0) {
                    mBtnmonthly_income_decrease.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag2[5] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag2[5] == 1) {
                    mBtnmonthly_income_decrease.setBackgroundResource(bg_short_btn);
                    flag2[5] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnquarterly_income_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2[6] == 0) {
                    mBtnquarterly_income_decrease.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag2[6] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag2[6] == 1) {
                    mBtnquarterly_income_decrease.setBackgroundResource(bg_short_btn);
                    flag2[6] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnquarterly_eps_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2[7] == 0) {
                    mBtnquarterly_eps_decrease.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag2[7] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag2[7] == 1) {
                    mBtnquarterly_eps_decrease.setBackgroundResource(bg_short_btn);
                    flag2[7] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        mBtnquarterly_income_newlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2[8] == 0) {
                    mBtnquarterly_income_newlow.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag2[8] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                } else if (flag2[8] == 1) {
                    mBtnquarterly_income_newlow.setBackgroundResource(bg_short_btn);
                    flag2[8] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                }
            }
        });

        final int[] choose_flag = {0};

        Button mBtnReturnHomePage = (Button) findViewById(R.id.return_home);
        mBtnReturnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(
                        StairsPickingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        Button mBtnNext = (Button) findViewById(R.id.pickend_next);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(
                        StairsPickingActivity.this, End.class);
                Bundle bundle = new Bundle();
                bundle.putInt("choose_number", num_choose[0]);
                bundle.putIntArray("flag0", flag);
                bundle.putIntArray("flag1", flag1);
                bundle.putIntArray("flag2", flag2);
                bundle.putIntArray("choose_flag", choose_flag);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}