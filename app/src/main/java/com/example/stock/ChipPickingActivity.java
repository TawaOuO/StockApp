package com.example.stock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.example.stock.R.drawable.bg_long_btn;
import static com.example.stock.R.drawable.bg_short_btn;

public class ChipPickingActivity extends Activity {
    final int[] flag = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//設置tec checker
    final int[] flag1 = {0, 0, 0, 0, 0, 0, 0, 0};//設置chip checker
    final int[] flag2 = {0, 0, 0, 0, 0, 0, 0, 0, 0};//設置basic checker
    final int[] num_choose = {0};

    public String CheckNum(){
        //設置tecmap作為資料庫
        Map<String, String[]> tecmap = new HashMap();
        //resultmap中key為各條件,value為符合之股號
        Map<String, String[]> resultmap = new HashMap();
        //讀取當日資料
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(
                    (com.example.stock.ChipPickingActivity.this).getAssets().open("123.csv")
            ));
            String[] nextLine;
            reader.readNext();
            //每一列資料裝入tecmap,key為股號,value0~4分別為5ma,10ma,20ma,day_KD,day_MACD,
            //value5~7代表外資1d買賣超,3d買賣超,5d買賣超
            //value8~10代表投信1d買賣超,3d買賣超,5d買賣超
            //value11代表月均交易量
            //value12~13代表連三月營收年增減,近四季有三季營收年增減>20%
            //value14~15代表近四季有三季EPS年增減>10%,季營收創五年新高/低
            //value16代表殖利率
            //value17代表中文股名
            // 其中技術面值為0代表無突破,1代表往上突破,2代表往下突破
            // 籌碼面值為0代表無買賣超,1代表買超,2代表賣超
            // 基本面值為0代表無增減,1代表增加,2代表減少
            // 月均交易量值為1代表>1000張,0代表<=1000張
            // 殖利率值為1代表>5%,0代表無
            while ((nextLine = reader.readNext()) != null) {
                if (!nextLine[0].equals("")) {
                    tecmap.put(nextLine[0], new String[]{nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7], nextLine[8], nextLine[9], nextLine[10], nextLine[11], nextLine[12], nextLine[13], nextLine[14], nextLine[15], nextLine[16], nextLine[17], nextLine[18]});
                }
            }
        } catch (IOException e) {
            // reader在初始化時可能遭遇問題。記得使用try/catch處理例外情形。
            e.printStackTrace();
        }

        ArrayList<String> cache = new ArrayList();
        String[] cache1;

        //      依照tec_flag做篩選,結果丟進resultmap
        for (Map.Entry<String, String[]> entry : tecmap.entrySet()) {
            String numofstock;
            numofstock = entry.getKey();
            String[] searchlist;
            searchlist = entry.getValue();

            assert flag != null;
            if (flag[0] == 1) {
                if (searchlist[0].equals("1.0")) {
                    cache1 = resultmap.get("5ma_break_up");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("5ma_break_up", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("5ma_break_up", new String[]{numofstock});
                    }
                }
            }
            if (flag[1] == 1){
                if (searchlist[1].equals("1.0")){
                    cache1 = resultmap.get("10ma_break_up");
                    if (cache1 != null){
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("10ma_break_up", cache.toArray(new String[0]));
                        cache.clear();
                    }
                    else {
                        resultmap.put("10ma_break_up", new String[]{numofstock});
                    }
                }
            }
            if (flag[2] == 1){
                if (searchlist[2].equals("1.0")){
                    cache1 = resultmap.get("20ma_break_up");
                    if (cache1 != null){
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("20ma_break_up", cache.toArray(new String[0]));
                        cache.clear();
                    }
                    else {
                        resultmap.put("20ma_break_up", new String[]{numofstock});
                    }
                }
            }
            if (flag[3] == 1){
                if (searchlist[3].equals("1.0")){
                    cache1 = resultmap.get("day_KD_break_up");
                    if (cache1 != null){
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("day_KD_break_up", cache.toArray(new String[0]));
                        cache.clear();
                    }
                    else {
                        resultmap.put("day_KD_break_up", new String[]{numofstock});
                    }
                }
            }
            if (flag[4] == 1){
                if (searchlist[4].equals("1.0")){
                    cache1 = resultmap.get("day_MACD_break_up");
                    if (cache1 != null){
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("day_MACD_break_up", cache.toArray(new String[0]));
                        cache.clear();
                    }
                    else {
                        resultmap.put("day_MACD_break_up", new String[]{numofstock});
                    }
                }
            }
            if (flag[5] == 1){
                if (searchlist[0].equals("2.0")){
                    cache1 = resultmap.get("5ma_break_down");
                    if (cache1 != null){
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("5ma_break_down", cache.toArray(new String[0]));
                        cache.clear();
                    }
                    else {
                        resultmap.put("5ma_break_down", new String[]{numofstock});
                    }
                }
            }
            if (flag[6] == 1){
                if (searchlist[1].equals("2.0")){
                    cache1 = resultmap.get("10ma_break_down");
                    if (cache1 != null){
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("10ma_break_down", cache.toArray(new String[0]));
                        cache.clear();
                    }
                    else {
                        resultmap.put("10ma_break_down", new String[]{numofstock});
                    }
                }
            }
            if (flag[7] == 1){
                if (searchlist[2].equals("2.0")){
                    cache1 = resultmap.get("20ma_break_down");
                    if (cache1 != null){
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("20ma_break_down", cache.toArray(new String[0]));
                        cache.clear();
                    }
                    else {
                        resultmap.put("220ma_break_down", new String[]{numofstock});
                    }
                }
            }
            if (flag[8] == 1){
                if (searchlist[3].equals("2.0")){
                    cache1 = resultmap.get("day_KD_break_down");
                    if (cache1 != null){
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("day_KD_break_down", cache.toArray(new String[0]));
                        cache.clear();
                    }
                    else {
                        resultmap.put("day_KD_break_down", new String[]{numofstock});
                    }
                }
            }
            if (flag[9] == 1){
                if (searchlist[4].equals("2.0")){
                    cache1 = resultmap.get("day_MACD_break_down");
                    if (cache1 != null){
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("day_MACD_break_down", cache.toArray(new String[0]));
                        cache.clear();
                    }
                    else {
                        resultmap.put("day_MACD_break_down", new String[]{numofstock});
                    }
                }
            }
            if (flag[10] == 1){
                if (searchlist[11].equals("1.0")){
                    cache1 = resultmap.get("month_avg_volume");
                    if (cache1 != null){
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("month_avg_volume", cache.toArray(new String[0]));
                        cache.clear();
                    }
                    else {
                        resultmap.put("month_avg_volume", new String[]{numofstock});
                    }
                }
            }

            assert flag1 != null;
            if (flag1[0] == 1) {
                if (searchlist[6].equals("1.0")) {
                    cache1 = resultmap.get("foreign_investor_overbuy_3d");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("foreign_investor_overbuy_3d", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("foreign_investor_overbuy_3d", new String[]{numofstock});
                    }
                }
            }
            if (flag1[1] == 1) {
                if (searchlist[7].equals("1.0")) {
                    cache1 = resultmap.get("foreign_investor_overbuy_5d");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("foreign_investor_overbuy_5d", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("foreign_investor_overbuy_5d", new String[]{numofstock});
                    }
                }
            }
            if (flag1[2] == 1) {
                if (searchlist[9].equals("1.0")) {
                    cache1 = resultmap.get("investment_trust_overbuy_3d");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("investment_trust_overbuy_3d", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("investment_trust_overbuy_3d", new String[]{numofstock});
                    }
                }
            }
            if (flag1[3] == 1) {
                if (searchlist[10].equals("1.0")) {
                    cache1 = resultmap.get("investment_trust_overbuy_5d");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("investment_trust_overbuy_5d", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("investment_trust_overbuy_5d", new String[]{numofstock});
                    }
                }
            }
            if (flag1[4] == 1) {
                if (searchlist[6].equals("2.0")) {
                    cache1 = resultmap.get("foreign_investor_oversell_3d");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("foreign_investor_oversell_3d", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("foreign_investor_oversell_3d", new String[]{numofstock});
                    }
                }
            }
            if (flag1[5] == 1) {
                if (searchlist[7].equals("2.0")) {
                    cache1 = resultmap.get("foreign_investor_oversell_5d");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("foreign_investor_oversell_5d", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("foreign_investor_oversell_5d", new String[]{numofstock});
                    }
                }
            }
            if (flag1[6] == 1) {
                if (searchlist[9].equals("2.0")) {
                    cache1 = resultmap.get("investment_trust_oversell_3d");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("investment_trust_oversell_3d", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("investment_trust_oversell_3d", new String[]{numofstock});
                    }
                }
            }
            if (flag1[7] == 1) {
                if (searchlist[10].equals("2.0")) {
                    cache1 = resultmap.get("investment_trust_oversell_5d");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("investment_trust_oversell_5d", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("investment_trust_oversell_5d", new String[]{numofstock});
                    }
                }
            }

            assert flag2 != null;
            if (flag2[0] == 1) {
                if (searchlist[12].equals("1.0")) {
                    cache1 = resultmap.get("monthly_income_increase");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("monthly_income_increase", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("monthly_income_increase", new String[]{numofstock});
                    }
                }
            }
            if (flag2[1] == 1) {
                if (searchlist[13].equals("1.0")) {
                    cache1 = resultmap.get("quarterly_income_increase");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("quarterly_income_increase", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("quarterly_income_increase", new String[]{numofstock});
                    }
                }
            }
            if (flag2[2] == 1) {
                if (searchlist[14].equals("1.0")) {
                    cache1 = resultmap.get("quarterly_eps_increase");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("quarterly_eps_increase", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("quarterly_eps_increase", new String[]{numofstock});
                    }
                }
            }
            if (flag2[3] == 1) {
                if (searchlist[15].equals("1.0")) {
                    cache1 = resultmap.get("quarterly_income_newhigh");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("quarterly_income_newhigh", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("quarterly_income_newhigh", new String[]{numofstock});
                    }
                }
            }
            if (flag2[4] == 1) {
                if (searchlist[16].equals("1.0")) {
                    cache1 = resultmap.get("yield_percent");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("yield_percent", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("yield_percent", new String[]{numofstock});
                    }
                }
            }
            if (flag2[5] == 1) {
                if (searchlist[12].equals("2.0")) {
                    cache1 = resultmap.get("monthly_income_decrease");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("monthly_income_decrease", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("monthly_income_decrease", new String[]{numofstock});
                    }
                }
            }
            if (flag2[6] == 1) {
                if (searchlist[13].equals("2.0")) {
                    cache1 = resultmap.get("quarterly_income_decrease");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("quarterly_income_decrease", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("quarterly_income_decrease", new String[]{numofstock});
                    }
                }
            }
            if (flag2[7] == 1) {
                if (searchlist[14].equals("2.0")) {
                    cache1 = resultmap.get("quarterly_eps_decrease");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("quarterly_eps_decrease", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("quarterly_eps_decrease", new String[]{numofstock});
                    }
                }
            }
            if (flag2[8] == 1) {
                if (searchlist[15].equals("2.0")) {
                    cache1 = resultmap.get("quarterly_income_newlow");
                    if (cache1 != null) {
                        cache.addAll(Arrays.asList(cache1));
                        cache.add(numofstock);
                        resultmap.put("quarterly_income_newlow", cache.toArray(new String[0]));
                        cache.clear();
                    } else {
                        resultmap.put("quarterly_income_newlow", new String[]{numofstock});
                    }
                }
            }

        }


        Map<String, Integer> resultend = new HashMap();
        ArrayList<String> finalresult = new ArrayList();
        String chinesename;

        //個股符合幾個條件
        for (Map.Entry<String, String[]> entry : resultmap.entrySet()){
            String[] cache3 = entry.getValue();
            int cache4 = 0;
            for (String str: cache3){
                if (resultend.get(str) != null){
                    cache4 = resultend.get(str);
                    cache4 += 1;
                    resultend.put(str, cache4);
                }
                else {
                    resultend.put(str, 1);
                }
            }
        }

        //最後結果，符合挑件數=選擇條件數者即為符合所有條件股號，丟入finalresult
        for (Map.Entry<String, Integer> entry : resultend.entrySet()){
            String numofstock = entry.getKey();
            int times = entry.getValue();
            if (times == num_choose[0]){
                chinesename = tecmap.get(numofstock)[17];
                finalresult.add(numofstock + chinesename);
            }
        }

        return ("共有" + String.valueOf(finalresult.size()) + "隻股票符合條件");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking_chip);

        //宣告所有按鈕
        final TextView num_choose_text = findViewById(R.id.num_choose_text);
        final TextView stock_num_choose_text = findViewById(R.id.stock_num_choose_text);
        final Button mBtnforeign_investor_buyout_3days = findViewById(R.id.chip_foreign_investor_buyout_3days);
        final Button mBtnforeign_investor_buyout_5days = findViewById(R.id.chip_foreign_investor_buyout_5days);
        final Button mBtninvestment_trust_buyout_3days = findViewById(R.id.chip_investment_trust_buyout_3days);
        final Button mBtninvestment_trust_buyout_5days = findViewById(R.id.chip_investment_trust_buyout_5days);
        final Button mBtnforeign_investor_soldout_3days = findViewById(R.id.chip_foreign_investor_soldout_3days);
        final Button mBtnforeign_investor_soldout_5days = findViewById(R.id.chip_foreign_investor_soldout_5days);
        final Button mBtninvestment_trust_soldout_3days = findViewById(R.id.chip_investment_trust_soldout_3days);
        final Button mBtninvestment_trust_soldout_5days = findViewById(R.id.chip_investment_trust_soldout_5days);

        final int[] stock_num_choose = {0};
        final String[] stock_num_choose_text_change = new String[1];

        num_choose_text.setText("共選擇" + num_choose[0] + "個條件");

        mBtnforeign_investor_buyout_3days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1[0] == 0) {
                    mBtnforeign_investor_buyout_3days.setBackgroundResource(R.drawable.bg_long_btn_click);
                    flag1[0] = 1;
                    num_choose[0] += 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
                } else if (flag1[0] == 1) {
                    mBtnforeign_investor_buyout_3days.setBackgroundResource(bg_long_btn);
                    flag1[0] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
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
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
                } else if (flag1[1] == 1) {
                    mBtnforeign_investor_buyout_5days.setBackgroundResource(bg_long_btn);
                    flag1[1] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
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
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
                } else if (flag1[2] == 1) {
                    mBtninvestment_trust_buyout_3days.setBackgroundResource(bg_long_btn);
                    flag1[2] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
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
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
                } else if (flag1[3] == 1) {
                    mBtninvestment_trust_buyout_5days.setBackgroundResource(bg_long_btn);
                    flag1[3] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
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
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
                } else if (flag1[4] == 1) {
                    mBtnforeign_investor_soldout_3days.setBackgroundResource(bg_short_btn);
                    flag1[4] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
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
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
                } else if (flag1[5] == 1) {
                    mBtnforeign_investor_soldout_5days.setBackgroundResource(bg_short_btn);
                    flag1[5] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
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
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
                } else if (flag1[6] == 1) {
                    mBtninvestment_trust_soldout_3days.setBackgroundResource(bg_short_btn);
                    flag1[6] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
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
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
                } else if (flag1[7] == 1) {
                    mBtninvestment_trust_soldout_5days.setBackgroundResource(bg_short_btn);
                    flag1[7] = 0;
                    num_choose[0] -= 1;
                    num_choose_text.setText("共選擇" + num_choose[0] + "個條件");
                    stock_num_choose_text_change[0] = CheckNum();
                    stock_num_choose_text.setText(stock_num_choose_text_change[0]);
                }
            }
        });

        Button mBtnReturnHomePage = (Button) findViewById(R.id.return_home);
        mBtnReturnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(
                        ChipPickingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        final int[] choose_flag = {3};

        Button mBtnNext = (Button) findViewById(R.id.pickend_next);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(
                        ChipPickingActivity.this, End.class);
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