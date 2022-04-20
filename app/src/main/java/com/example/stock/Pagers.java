package com.example.stock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import static com.example.stock.R.drawable.bg_long_btn;
import static com.example.stock.R.drawable.bg_short_btn;


public class Pagers extends RelativeLayout {//繼承別的Layout亦可

    final int[] num_choose = {0};
    final int[] num_choose1 = {0};

    public Pagers(final Context context, int pageNumber) {//pageNumber是由ＭainActivity.java那邊傳入頁碼

        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.my_pagers, null);//連接頁面
        View view1 = inflater.inflate(R.layout.my_pagers1, null);
        View view2 = inflater.inflate(R.layout.my_pagers2, null);

//        final TextView textView = view.findViewById(R.id.textView);//取得頁面元件
//        final TextView textView1 = view1.findViewById(R.id.textView1);

        final Button mBtn5ma_break_up = view.findViewById(R.id.tec_break_5ma_up);//宣告所有按鈕
        final Button mBtn10ma_break_up = view.findViewById(R.id.tec_break_10ma_up);
        final Button mBtn20ma_break_up = view.findViewById(R.id.tec_break_20ma_up);
        final Button mBtnday_kd_break_up = view.findViewById(R.id.tec_break_day_kd_up);
        final Button mBtnday_MACD_break_up = view.findViewById(R.id.tec_break_day_MACD_up);
        final Button mBtn5ma_break_down = view.findViewById(R.id.tec_break_5ma_down);
        final Button mBtn10ma_break_down = view.findViewById(R.id.tec_break_10ma_down);
        final Button mBtn20ma_break_down = view.findViewById(R.id.tec_break_20ma_down);
        final Button mBtnday_kd_break_down = view.findViewById(R.id.tec_break_day_kd_down);
        final Button mBtnday_MACD_break_down = view.findViewById(R.id.tec_break_day_MACD_down);
        final Button mBtnforeign_investor_buyout_3days = view1.findViewById(R.id.chip_foreign_investor_buyout_3days);
        final Button mBtnforeign_investor_buyout_5days = view1.findViewById(R.id.chip_foreign_investor_buyout_5days);
        final Button mBtninvestment_trust_buyout_3days = view1.findViewById(R.id.chip_investment_trust_buyout_3days);
        final Button mBtninvestment_trust_buyout_5days = view1.findViewById(R.id.chip_investment_trust_buyout_5days);
        final Button mBtnforeign_investor_soldout_3days = view1.findViewById(R.id.chip_foreign_investor_soldout_3days);
        final Button mBtnforeign_investor_soldout_5days = view1.findViewById(R.id.chip_foreign_investor_soldout_5days);
        final Button mBtninvestment_trust_soldout_3days = view1.findViewById(R.id.chip_investment_trust_soldout_3days);
        final Button mBtninvestment_trust_soldout_5days = view1.findViewById(R.id.chip_investment_trust_soldout_5days);

        final int[] flag = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//設置tec checker
        final int[] flag1 = {0, 0, 0, 0, 0, 0, 0, 0};//設置chip checker

        mBtn5ma_break_up.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {//各按鈕事件
                if (flag[0] == 0) {
                    mBtn5ma_break_up.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag[0] = 1;
                    num_choose[0] += 1;
//                    String num_choose_flag00 = String.valueOf(num_choose[0]);
//                    textView.setText(num_choose_flag00);
//                    textView1.setText(num_choose_flag00);

                } else if (flag[0] == 1) {
                    mBtn5ma_break_up.setBackgroundResource(bg_long_btn);
                    flag[0] = 0;
                    num_choose[0] -= 1;
//                    String num_choose_flag00 = String.valueOf(num_choose[0]);
//                    textView.setText(String.valueOf(num_choose[0]));
//                    textView1.setText(String.valueOf(num_choose[0]));
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
                } else if (flag[1] == 1) {
                    mBtn10ma_break_up.setBackgroundResource(bg_long_btn);
                    flag[1] = 0;
                    num_choose[0] -= 1;
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
                } else if (flag[2] == 1) {
                    mBtn20ma_break_up.setBackgroundResource(bg_long_btn);
                    flag[2] = 0;
                    num_choose[0] -= 1;
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
                } else if (flag[3] == 1) {
                    mBtnday_kd_break_up.setBackgroundResource(bg_long_btn);
                    flag[3] = 0;
                    num_choose[0] -= 1;
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
                } else if (flag[4] == 1) {
                    mBtnday_MACD_break_up.setBackgroundResource(bg_long_btn);
                    flag[4] = 0;
                    num_choose[0] -= 1;
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
                } else if (flag[5] == 1) {
                    mBtn5ma_break_down.setBackgroundResource(bg_short_btn);
                    flag[5] = 0;
                    num_choose[0] -= 1;
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
                } else if (flag[6] == 1) {
                    mBtn10ma_break_down.setBackgroundResource(bg_short_btn);
                    flag[6] = 0;
                    num_choose[0] -= 1;
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
                } else if (flag[7] == 1) {
                    mBtn20ma_break_down.setBackgroundResource(bg_short_btn);
                    flag[7] = 0;
                    num_choose[0] -= 1;
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
                } else if (flag[8] == 1) {
                    mBtnday_kd_break_down.setBackgroundResource(bg_short_btn);
                    flag[8] = 0;
                    num_choose[0] -= 1;
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
                } else if (flag[9] == 1) {
                    mBtnday_MACD_break_down.setBackgroundResource(bg_short_btn);
                    flag[9] = 0;
                    num_choose[0] -= 1;
                }
            }
        });

        mBtnforeign_investor_buyout_3days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[0] == 0) {
                    mBtnforeign_investor_buyout_3days.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag1[0] = 1;
                    num_choose1[0] += 1;
                } else if (flag1[0] == 1) {
                    mBtnforeign_investor_buyout_3days.setBackgroundResource(bg_long_btn);
                    flag1[0] = 0;
                    num_choose1[0] -= 1;
                }
            }
        });

        mBtnforeign_investor_buyout_5days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[1] == 0) {
                    mBtnforeign_investor_buyout_5days.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag1[1] = 1;
                    num_choose1[0] += 1;
                } else if (flag1[1] == 1) {
                    mBtnforeign_investor_buyout_5days.setBackgroundResource(bg_long_btn);
                    flag1[1] = 0;
                    num_choose1[0] -= 1;
                }
            }
        });

        mBtninvestment_trust_buyout_3days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[2] == 0) {
                    mBtninvestment_trust_buyout_3days.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag1[2] = 1;
                    num_choose1[0] += 1;
                } else if (flag1[2] == 1) {
                    mBtninvestment_trust_buyout_3days.setBackgroundResource(bg_long_btn);
                    flag1[2] = 0;
                    num_choose1[0] -= 1;
                }
            }
        });

        mBtninvestment_trust_buyout_5days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[3] == 0) {
                    mBtninvestment_trust_buyout_5days.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag1[3] = 1;
                    num_choose1[0] += 1;
                } else if (flag1[3] == 1) {
                    mBtninvestment_trust_buyout_5days.setBackgroundResource(bg_long_btn);
                    flag1[3] = 0;
                    num_choose1[0] -= 1;
                }
            }
        });

        mBtnforeign_investor_soldout_3days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[4] == 0) {
                    mBtnforeign_investor_soldout_3days.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag1[4] = 1;
                    num_choose1[0] += 1;
                } else if (flag1[4] == 1) {
                    mBtnforeign_investor_soldout_3days.setBackgroundResource(bg_short_btn);
                    flag1[4] = 0;
                    num_choose1[0] -= 1;
                }
            }
        });

        mBtnforeign_investor_soldout_5days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[5] == 0) {
                    mBtnforeign_investor_soldout_5days.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag1[5] = 1;
                    num_choose1[0] += 1;
                } else if (flag1[5] == 1) {
                    mBtnforeign_investor_soldout_5days.setBackgroundResource(bg_short_btn);
                    flag1[5] = 0;
                    num_choose1[0] -= 1;
                }
            }
        });

        mBtninvestment_trust_soldout_3days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[6] == 0) {
                    mBtninvestment_trust_soldout_3days.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag1[6] = 1;
                    num_choose1[0] += 1;
                } else if (flag1[6] == 1) {
                    mBtninvestment_trust_soldout_3days.setBackgroundResource(bg_short_btn);
                    flag1[6] = 0;
                    num_choose1[0] -= 1;
                }
            }
        });

        mBtninvestment_trust_soldout_5days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[7] == 0) {
                    mBtninvestment_trust_soldout_5days.setBackgroundResource(R.drawable.bg_short_btn_click);
                    flag1[7] = 1;
                    num_choose1[0] += 1;
                } else if (flag1[7] == 1) {
                    mBtninvestment_trust_soldout_5days.setBackgroundResource(bg_short_btn);
                    flag1[7] = 0;
                    num_choose1[0] -= 1;
                }
            }
        });

        final Button mBtnGotoEnd = view.findViewById(R.id.pickend);
        final Button mBtnGotoEnd1 = view1.findViewById(R.id.pickend1);


        mBtnGotoEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(context, End.class);
                Bundle bundle = new Bundle();
                bundle.putInt("test", num_choose[0]);
                bundle.putInt("test1", num_choose1[0]);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        mBtnGotoEnd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1;
                intent1 = new Intent(context, End.class);
                context.startActivity(intent1);
            }
        });




        if (pageNumber == 1) {
            addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            //將元件放入ViewPager
        } else if (pageNumber == 2) {
            addView(view1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        } else if (pageNumber == 3) {
            addView(view2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }
}
