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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class End extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end);
        TextView choose_result = findViewById(R.id.end_text);

        //設置tecmap作為資料庫
        Map<String, String[]> tecmap = new HashMap();

        //bundleend接收選擇條件,容器tecflag,chipflag,basicflag
        //tec_flag中,值分別代表0:5ma_up,1:10ma_up,2:20ma_up,3:day_KD_up,4:day_MACD_up
        //                    5:5ma_down,6:10ma_down,7:20ma_down,8:day_KD_down,9:day_MACD_down
        //chip_flag中,值分別代表0:foreign_investor_buyout_3days,1:foreign_investor_buyout_5days
        //                     2:investment_trust_buyout_3days,3:investment_trust_buyout_5days
        //                     4:stock_volume
        //                     5:foreign_investor_soldout_3days,6:foreign_investor_soldout_5days
        //                     7:investment_trust_soldout_3days,8:investment_trust_soldout_5days
        //basic_flag中,值分別代表0:monthly_income_increase,1:quarterly_income_increase
        //                      2:quarterly_eps_increase,3:quarterly_income_newhigh
        //                      4:yield_percent
        //                      5.monthly_income_decrease,6.quarterly_income_decrease
        //                      7.quarterly_eps_decrease,8.quarterly_income_newlow
        Bundle bundleend = this.getIntent().getExtras();
        int[] tec_flag = new int[0];
        int[] chip_flag = new int[0];
        int[] basic_flag = new int[0];
        int[] how_choose_flag = new int[0];
        int choosenum = 0;
        if (bundleend != null) {
            tec_flag = bundleend.getIntArray("flag0");
            chip_flag = bundleend.getIntArray("flag1");
            basic_flag = bundleend.getIntArray("flag2");
            choosenum = bundleend.getInt("choose_number");
            how_choose_flag = bundleend.getIntArray("choose_flag");
        }

        //resultmap中key為各條件,value為符合之股號
        Map<String, String[]> resultmap = new HashMap();


        //讀取當日資料
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(
                    (End.this).getAssets().open("123.csv")
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

            assert tec_flag != null;
            if (tec_flag[0] == 1) {
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
            if (tec_flag[1] == 1){
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
            if (tec_flag[2] == 1){
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
            if (tec_flag[3] == 1){
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
            if (tec_flag[4] == 1){
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
            if (tec_flag[5] == 1){
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
            if (tec_flag[6] == 1){
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
            if (tec_flag[7] == 1){
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
            if (tec_flag[8] == 1){
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
            if (tec_flag[9] == 1){
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
            if (tec_flag[10] == 1){
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

            assert chip_flag != null;
            if (chip_flag[0] == 1) {
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
            if (chip_flag[1] == 1) {
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
            if (chip_flag[2] == 1) {
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
            if (chip_flag[3] == 1) {
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
            if (chip_flag[4] == 1) {
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
            if (chip_flag[5] == 1) {
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
            if (chip_flag[6] == 1) {
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
            if (chip_flag[7] == 1) {
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

            assert basic_flag != null;
            if (basic_flag[0] == 1) {
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
            if (basic_flag[1] == 1) {
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
            if (basic_flag[2] == 1) {
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
            if (basic_flag[3] == 1) {
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
            if (basic_flag[4] == 1) {
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
            if (basic_flag[5] == 1) {
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
            if (basic_flag[6] == 1) {
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
            if (basic_flag[7] == 1) {
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
            if (basic_flag[8] == 1) {
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
            if (times == choosenum){
                chinesename = tecmap.get(numofstock)[17];
                finalresult.add(numofstock + chinesename);
            }
        }

        Collections.sort(finalresult);

        TextView result_stock1 = findViewById(R.id.stock_num_1);
        TextView result_stock2 = findViewById(R.id.stock_num_2);
        TextView result_stock3 = findViewById(R.id.stock_num_3);
        TextView result_stock4 = findViewById(R.id.stock_num_4);
        TextView result_stock5 = findViewById(R.id.stock_num_5);
        TextView result_stock6 = findViewById(R.id.stock_num_6);
        TextView result_stock7 = findViewById(R.id.stock_num_7);
        TextView result_stock8 = findViewById(R.id.stock_num_8);
        TextView result_stock9 = findViewById(R.id.stock_num_9);
        TextView result_stock10 = findViewById(R.id.stock_num_10);
        TextView result_stock11 = findViewById(R.id.stock_num_11);
        TextView result_stock12 = findViewById(R.id.stock_num_12);
        TextView result_stock13 = findViewById(R.id.stock_num_13);
        TextView result_stock14 = findViewById(R.id.stock_num_14);
        TextView result_stock15 = findViewById(R.id.stock_num_15);
        TextView result_stock16 = findViewById(R.id.stock_num_16);
        TextView result_stock17 = findViewById(R.id.stock_num_17);
        TextView result_stock18 = findViewById(R.id.stock_num_18);
        TextView result_stock19 = findViewById(R.id.stock_num_19);
        TextView result_stock20 = findViewById(R.id.stock_num_20);
        TextView result_stock21 = findViewById(R.id.stock_num_21);
        TextView result_stock22 = findViewById(R.id.stock_num_22);
        TextView result_stock23 = findViewById(R.id.stock_num_23);
        TextView result_stock24 = findViewById(R.id.stock_num_24);
        TextView result_stock25 = findViewById(R.id.stock_num_25);
        TextView result_stock26 = findViewById(R.id.stock_num_26);
        TextView result_stock27 = findViewById(R.id.stock_num_27);
        TextView result_stock28 = findViewById(R.id.stock_num_28);
        TextView result_stock29 = findViewById(R.id.stock_num_29);
        TextView result_stock30 = findViewById(R.id.stock_num_30);
        TextView result_stock31 = findViewById(R.id.stock_num_31);
        TextView result_stock32 = findViewById(R.id.stock_num_32);
        TextView result_stock33 = findViewById(R.id.stock_num_33);
        TextView result_stock34 = findViewById(R.id.stock_num_34);
        TextView result_stock35 = findViewById(R.id.stock_num_35);
        TextView result_stock36 = findViewById(R.id.stock_num_36);
        TextView result_stock37 = findViewById(R.id.stock_num_37);
        TextView result_stock38 = findViewById(R.id.stock_num_38);
        TextView result_stock39 = findViewById(R.id.stock_num_39);
        TextView result_stock40 = findViewById(R.id.stock_num_40);
        TextView result_stock41 = findViewById(R.id.stock_num_41);
        TextView result_stock42 = findViewById(R.id.stock_num_42);
        TextView result_stock43 = findViewById(R.id.stock_num_43);
        TextView result_stock44 = findViewById(R.id.stock_num_44);
        TextView result_stock45 = findViewById(R.id.stock_num_45);
        TextView result_stock46 = findViewById(R.id.stock_num_46);
        TextView result_stock47 = findViewById(R.id.stock_num_47);
        TextView result_stock48 = findViewById(R.id.stock_num_48);
        TextView result_stock49 = findViewById(R.id.stock_num_49);
        TextView result_stock50 = findViewById(R.id.stock_num_50);
        TextView result_stock51 = findViewById(R.id.stock_num_51);
        TextView result_stock52 = findViewById(R.id.stock_num_52);
        TextView result_stock53 = findViewById(R.id.stock_num_53);
        TextView result_stock54 = findViewById(R.id.stock_num_54);
        TextView result_stock55 = findViewById(R.id.stock_num_55);
        TextView result_stock56 = findViewById(R.id.stock_num_56);
        TextView result_stock57 = findViewById(R.id.stock_num_57);
        TextView result_stock58 = findViewById(R.id.stock_num_58);
        TextView result_stock59 = findViewById(R.id.stock_num_59);
        TextView result_stock60 = findViewById(R.id.stock_num_60);
        TextView result_stock61 = findViewById(R.id.stock_num_61);
        TextView result_stock62 = findViewById(R.id.stock_num_62);
        TextView result_stock63 = findViewById(R.id.stock_num_63);
        TextView result_stock64 = findViewById(R.id.stock_num_64);
        TextView result_stock65 = findViewById(R.id.stock_num_65);
        TextView result_stock66 = findViewById(R.id.stock_num_66);
        TextView result_stock67 = findViewById(R.id.stock_num_67);
        TextView result_stock68 = findViewById(R.id.stock_num_68);
        TextView result_stock69 = findViewById(R.id.stock_num_69);
        TextView result_stock70 = findViewById(R.id.stock_num_70);
        TextView result_stock71 = findViewById(R.id.stock_num_71);
        TextView result_stock72 = findViewById(R.id.stock_num_72);
        TextView result_stock73 = findViewById(R.id.stock_num_73);
        TextView result_stock74 = findViewById(R.id.stock_num_74);
        TextView result_stock75 = findViewById(R.id.stock_num_75);
        TextView result_stock76 = findViewById(R.id.stock_num_76);
        TextView result_stock77 = findViewById(R.id.stock_num_77);
        TextView result_stock78 = findViewById(R.id.stock_num_78);
        TextView result_stock79 = findViewById(R.id.stock_num_79);
        TextView result_stock80 = findViewById(R.id.stock_num_80);
        TextView result_stock81 = findViewById(R.id.stock_num_81);
        TextView result_stock82 = findViewById(R.id.stock_num_82);
        TextView result_stock83 = findViewById(R.id.stock_num_83);
        TextView result_stock84 = findViewById(R.id.stock_num_84);
        TextView result_stock85 = findViewById(R.id.stock_num_85);
        TextView result_stock86 = findViewById(R.id.stock_num_86);
        TextView result_stock87 = findViewById(R.id.stock_num_87);
        TextView result_stock88 = findViewById(R.id.stock_num_88);
        TextView result_stock89 = findViewById(R.id.stock_num_89);
        TextView result_stock90 = findViewById(R.id.stock_num_90);
        TextView result_stock91 = findViewById(R.id.stock_num_91);
        TextView result_stock92 = findViewById(R.id.stock_num_92);
        TextView result_stock93 = findViewById(R.id.stock_num_93);
        TextView result_stock94 = findViewById(R.id.stock_num_94);
        TextView result_stock95 = findViewById(R.id.stock_num_95);
        TextView result_stock96 = findViewById(R.id.stock_num_96);
        TextView result_stock97 = findViewById(R.id.stock_num_97);
        TextView result_stock98 = findViewById(R.id.stock_num_98);
        TextView result_stock99 = findViewById(R.id.stock_num_99);
        TextView result_stock100 = findViewById(R.id.stock_num_100);

        result_stock1.setVisibility(View.INVISIBLE);
        result_stock2.setVisibility(View.INVISIBLE);
        result_stock3.setVisibility(View.INVISIBLE);
        result_stock4.setVisibility(View.INVISIBLE);
        result_stock5.setVisibility(View.INVISIBLE);
        result_stock6.setVisibility(View.INVISIBLE);
        result_stock7.setVisibility(View.INVISIBLE);
        result_stock8.setVisibility(View.INVISIBLE);
        result_stock9.setVisibility(View.INVISIBLE);
        result_stock10.setVisibility(View.INVISIBLE);
        result_stock11.setVisibility(View.INVISIBLE);
        result_stock12.setVisibility(View.INVISIBLE);
        result_stock13.setVisibility(View.GONE);
        result_stock14.setVisibility(View.GONE);
        result_stock15.setVisibility(View.GONE);
        result_stock16.setVisibility(View.GONE);
        result_stock17.setVisibility(View.GONE);
        result_stock18.setVisibility(View.GONE);
        result_stock19.setVisibility(View.GONE);
        result_stock20.setVisibility(View.GONE);
        result_stock21.setVisibility(View.GONE);
        result_stock22.setVisibility(View.GONE);
        result_stock23.setVisibility(View.GONE);
        result_stock24.setVisibility(View.GONE);
        result_stock25.setVisibility(View.GONE);
        result_stock26.setVisibility(View.GONE);
        result_stock27.setVisibility(View.GONE);
        result_stock28.setVisibility(View.GONE);
        result_stock29.setVisibility(View.GONE);
        result_stock30.setVisibility(View.GONE);
        result_stock31.setVisibility(View.GONE);
        result_stock32.setVisibility(View.GONE);
        result_stock33.setVisibility(View.GONE);
        result_stock34.setVisibility(View.GONE);
        result_stock35.setVisibility(View.GONE);
        result_stock36.setVisibility(View.GONE);
        result_stock37.setVisibility(View.GONE);
        result_stock38.setVisibility(View.GONE);
        result_stock39.setVisibility(View.GONE);
        result_stock40.setVisibility(View.GONE);
        result_stock41.setVisibility(View.GONE);
        result_stock42.setVisibility(View.GONE);
        result_stock43.setVisibility(View.GONE);
        result_stock44.setVisibility(View.GONE);
        result_stock45.setVisibility(View.GONE);
        result_stock46.setVisibility(View.GONE);
        result_stock47.setVisibility(View.GONE);
        result_stock48.setVisibility(View.GONE);
        result_stock49.setVisibility(View.GONE);
        result_stock50.setVisibility(View.GONE);
        result_stock51.setVisibility(View.GONE);
        result_stock52.setVisibility(View.GONE);
        result_stock53.setVisibility(View.GONE);
        result_stock54.setVisibility(View.GONE);
        result_stock55.setVisibility(View.GONE);
        result_stock56.setVisibility(View.GONE);
        result_stock57.setVisibility(View.GONE);
        result_stock58.setVisibility(View.GONE);
        result_stock59.setVisibility(View.GONE);
        result_stock60.setVisibility(View.GONE);
        result_stock61.setVisibility(View.GONE);
        result_stock62.setVisibility(View.GONE);
        result_stock63.setVisibility(View.GONE);
        result_stock64.setVisibility(View.GONE);
        result_stock65.setVisibility(View.GONE);
        result_stock66.setVisibility(View.GONE);
        result_stock67.setVisibility(View.GONE);
        result_stock68.setVisibility(View.GONE);
        result_stock69.setVisibility(View.GONE);
        result_stock70.setVisibility(View.GONE);
        result_stock71.setVisibility(View.GONE);
        result_stock72.setVisibility(View.GONE);
        result_stock73.setVisibility(View.GONE);
        result_stock74.setVisibility(View.GONE);
        result_stock75.setVisibility(View.GONE);
        result_stock76.setVisibility(View.GONE);
        result_stock77.setVisibility(View.GONE);
        result_stock78.setVisibility(View.GONE);
        result_stock79.setVisibility(View.GONE);
        result_stock80.setVisibility(View.GONE);
        result_stock81.setVisibility(View.GONE);
        result_stock82.setVisibility(View.GONE);
        result_stock83.setVisibility(View.GONE);
        result_stock84.setVisibility(View.GONE);
        result_stock85.setVisibility(View.GONE);
        result_stock86.setVisibility(View.GONE);
        result_stock87.setVisibility(View.GONE);
        result_stock88.setVisibility(View.GONE);
        result_stock89.setVisibility(View.GONE);
        result_stock90.setVisibility(View.GONE);
        result_stock91.setVisibility(View.GONE);
        result_stock92.setVisibility(View.GONE);
        result_stock93.setVisibility(View.GONE);
        result_stock94.setVisibility(View.GONE);
        result_stock95.setVisibility(View.GONE);
        result_stock96.setVisibility(View.GONE);
        result_stock97.setVisibility(View.GONE);
        result_stock98.setVisibility(View.GONE);
        result_stock99.setVisibility(View.GONE);
        result_stock100.setVisibility(View.GONE);


        if (finalresult.size() > 0){
                if (finalresult.get(0) != null){
                    result_stock1.setText(finalresult.get(0));
                    result_stock1.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 1){
                if  (finalresult.get(1) != null){
                    result_stock2.setText(finalresult.get(1));
                    result_stock2.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 2){
                if  (finalresult.get(2) != null){
                    result_stock3.setText(finalresult.get(2));
                    result_stock3.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 3){
                if  (finalresult.get(3) != null){
                    result_stock4.setText(finalresult.get(3));
                    result_stock4.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 4){
                if  (finalresult.get(4) != null){
                    result_stock5.setText(finalresult.get(4));
                    result_stock5.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 5){
                if  (finalresult.get(5) != null){
                    result_stock6.setText(finalresult.get(5));
                    result_stock6.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 6){
                if  (finalresult.get(6) != null){
                    result_stock7.setText(finalresult.get(6));
                    result_stock7.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 7){
                if  (finalresult.get(7) != null){
                    result_stock8.setText(finalresult.get(7));
                    result_stock8.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 8){
                if  (finalresult.get(8) != null){
                    result_stock9.setText(finalresult.get(8));
                    result_stock9.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 9){
                if  (finalresult.get(9) != null){
                    result_stock10.setText(finalresult.get(9));
                    result_stock10.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 10){
                if  (finalresult.get(10) != null){
                    result_stock11.setText(finalresult.get(10));
                    result_stock11.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 11){
                if  (finalresult.get(11) != null){
                    result_stock12.setText(finalresult.get(11));
                    result_stock12.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 12){
                if  (finalresult.get(12) != null){
                    result_stock13.setText(finalresult.get(12));
                    result_stock13.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 13){
                if  (finalresult.get(13) != null){
                    result_stock14.setText(finalresult.get(13));
                    result_stock14.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 14){
                if  (finalresult.get(14) != null){
                    result_stock15.setText(finalresult.get(14));
                    result_stock15.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 15){
                if  (finalresult.get(15) != null){
                    result_stock16.setText(finalresult.get(15));
                    result_stock16.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 16){
                if  (finalresult.get(16) != null){
                    result_stock17.setText(finalresult.get(16));
                    result_stock17.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 17){
                if  (finalresult.get(17) != null){
                    result_stock18.setText(finalresult.get(17));
                    result_stock18.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 18){
                if  (finalresult.get(18) != null){
                    result_stock19.setText(finalresult.get(18));
                    result_stock19.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 19){
                if  (finalresult.get(19) != null){
                    result_stock20.setText(finalresult.get(19));
                    result_stock20.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 20){
                if  (finalresult.get(20) != null){
                    result_stock21.setText(finalresult.get(20));
                    result_stock21.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 21){
                if  (finalresult.get(21) != null){
                    result_stock22.setText(finalresult.get(21));
                    result_stock22.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 22){
                if  (finalresult.get(22) != null){
                    result_stock23.setText(finalresult.get(22));
                    result_stock23.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 23){
                if  (finalresult.get(23) != null){
                    result_stock24.setText(finalresult.get(23));
                    result_stock24.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 24){
                if  (finalresult.get(24) != null){
                    result_stock25.setText(finalresult.get(24));
                    result_stock25.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 25){
                if  (finalresult.get(25) != null){
                    result_stock26.setText(finalresult.get(25));
                    result_stock26.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 26){
                if  (finalresult.get(26) != null){
                    result_stock27.setText(finalresult.get(26));
                    result_stock27.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 27){
                if  (finalresult.get(27) != null){
                    result_stock28.setText(finalresult.get(27));
                    result_stock28.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 28){
                if  (finalresult.get(28) != null){
                    result_stock29.setText(finalresult.get(28));
                    result_stock29.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 29){
                if  (finalresult.get(29) != null){
                    result_stock30.setText(finalresult.get(29));
                    result_stock30.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 30){
                if  (finalresult.get(30) != null){
                    result_stock31.setText(finalresult.get(30));
                    result_stock31.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 31){
                if  (finalresult.get(31) != null){
                    result_stock32.setText(finalresult.get(31));
                    result_stock32.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 32){
                if  (finalresult.get(32) != null){
                    result_stock33.setText(finalresult.get(32));
                    result_stock33.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 33){
                if  (finalresult.get(33) != null){
                    result_stock34.setText(finalresult.get(33));
                    result_stock34.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 34){
                if  (finalresult.get(34) != null){
                    result_stock35.setText(finalresult.get(34));
                    result_stock35.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 35){
                if  (finalresult.get(35) != null){
                    result_stock36.setText(finalresult.get(35));
                    result_stock36.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 36){
                if  (finalresult.get(36) != null){
                    result_stock37.setText(finalresult.get(36));
                    result_stock37.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 37){
                if  (finalresult.get(37) != null){
                    result_stock38.setText(finalresult.get(37));
                    result_stock38.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 38){
                if  (finalresult.get(38) != null){
                    result_stock39.setText(finalresult.get(38));
                    result_stock39.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 39){
                if  (finalresult.get(39) != null){
                    result_stock40.setText(finalresult.get(39));
                    result_stock40.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 40){
                if  (finalresult.get(40) != null){
                    result_stock41.setText(finalresult.get(40));
                    result_stock41.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 41){
                if  (finalresult.get(41) != null){
                    result_stock42.setText(finalresult.get(41));
                    result_stock42.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 42){
                if  (finalresult.get(42) != null){
                    result_stock43.setText(finalresult.get(42));
                    result_stock43.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 43){
                if  (finalresult.get(43) != null){
                    result_stock44.setText(finalresult.get(43));
                    result_stock44.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 44){
                if  (finalresult.get(44) != null){
                    result_stock45.setText(finalresult.get(44));
                    result_stock45.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 45){
                if  (finalresult.get(45) != null){
                    result_stock46.setText(finalresult.get(45));
                    result_stock46.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 46){
                if  (finalresult.get(46) != null){
                    result_stock47.setText(finalresult.get(46));
                    result_stock47.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 47){
                if  (finalresult.get(47) != null){
                    result_stock48.setText(finalresult.get(47));
                    result_stock48.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 48){
                if  (finalresult.get(48) != null){
                    result_stock49.setText(finalresult.get(48));
                    result_stock49.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 49){
                if  (finalresult.get(49) != null){
                    result_stock50.setText(finalresult.get(49));
                    result_stock50.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 50){
                if  (finalresult.get(50) != null){
                    result_stock51.setText(finalresult.get(50));
                    result_stock51.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 51){
                if  (finalresult.get(51) != null){
                    result_stock52.setText(finalresult.get(51));
                    result_stock52.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 52){
                if  (finalresult.get(52) != null){
                    result_stock53.setText(finalresult.get(52));
                    result_stock53.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 53){
                if  (finalresult.get(53) != null){
                    result_stock54.setText(finalresult.get(53));
                    result_stock54.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 54){
                if  (finalresult.get(54) != null){
                    result_stock55.setText(finalresult.get(54));
                    result_stock55.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 55){
                if  (finalresult.get(55) != null){
                    result_stock56.setText(finalresult.get(55));
                    result_stock56.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 56){
                if  (finalresult.get(56) != null){
                    result_stock57.setText(finalresult.get(56));
                    result_stock57.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 57){
                if  (finalresult.get(57) != null){
                    result_stock58.setText(finalresult.get(57));
                    result_stock58.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 58){
                if  (finalresult.get(58) != null){
                    result_stock59.setText(finalresult.get(58));
                    result_stock59.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 59){
                if  (finalresult.get(59) != null){
                    result_stock60.setText(finalresult.get(59));
                    result_stock60.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 60){
                if  (finalresult.get(60) != null){
                    result_stock61.setText(finalresult.get(60));
                    result_stock61.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 61){
                if  (finalresult.get(61) != null){
                    result_stock62.setText(finalresult.get(61));
                    result_stock62.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 62){
                if  (finalresult.get(62) != null){
                    result_stock63.setText(finalresult.get(62));
                    result_stock63.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 63){
                if  (finalresult.get(63) != null){
                    result_stock64.setText(finalresult.get(63));
                    result_stock64.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 64){
                if  (finalresult.get(64) != null){
                    result_stock65.setText(finalresult.get(64));
                    result_stock65.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 65){
                if  (finalresult.get(65) != null){
                    result_stock66.setText(finalresult.get(65));
                    result_stock66.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 66){
                if  (finalresult.get(66) != null){
                    result_stock67.setText(finalresult.get(66));
                    result_stock67.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 67){
                if  (finalresult.get(67) != null){
                    result_stock68.setText(finalresult.get(67));
                    result_stock68.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 68){
                if  (finalresult.get(68) != null){
                    result_stock69.setText(finalresult.get(68));
                    result_stock69.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 69){
                if  (finalresult.get(69) != null){
                    result_stock70.setText(finalresult.get(69));
                    result_stock70.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 70){
                if  (finalresult.get(70) != null){
                    result_stock71.setText(finalresult.get(70));
                    result_stock71.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 71){
                if  (finalresult.get(71) != null){
                    result_stock72.setText(finalresult.get(71));
                    result_stock72.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 72){
                if  (finalresult.get(72) != null){
                    result_stock73.setText(finalresult.get(72));
                    result_stock73.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 73){
                if  (finalresult.get(73) != null){
                    result_stock74.setText(finalresult.get(73));
                    result_stock74.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 74){
                if  (finalresult.get(74) != null){
                    result_stock75.setText(finalresult.get(74));
                    result_stock75.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 75){
                if  (finalresult.get(75) != null){
                    result_stock76.setText(finalresult.get(75));
                    result_stock76.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 76){
                if  (finalresult.get(76) != null){
                    result_stock77.setText(finalresult.get(76));
                    result_stock77.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 77){
                if  (finalresult.get(77) != null){
                    result_stock78.setText(finalresult.get(77));
                    result_stock78.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 78){
                if  (finalresult.get(78) != null){
                    result_stock79.setText(finalresult.get(78));
                    result_stock79.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 79){
                if  (finalresult.get(79) != null){
                    result_stock80.setText(finalresult.get(79));
                    result_stock80.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 80){
                if  (finalresult.get(80) != null){
                    result_stock81.setText(finalresult.get(80));
                    result_stock81.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 81){
                if  (finalresult.get(81) != null){
                    result_stock82.setText(finalresult.get(81));
                    result_stock82.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 82){
                if  (finalresult.get(82) != null){
                    result_stock83.setText(finalresult.get(82));
                    result_stock83.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 83){
                if  (finalresult.get(83) != null){
                    result_stock84.setText(finalresult.get(83));
                    result_stock84.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 84){
                if  (finalresult.get(84) != null){
                    result_stock85.setText(finalresult.get(84));
                    result_stock85.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 85){
                if  (finalresult.get(85) != null){
                    result_stock86.setText(finalresult.get(85));
                    result_stock86.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 86){
                if  (finalresult.get(86) != null){
                    result_stock87.setText(finalresult.get(86));
                    result_stock87.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 87){
                if  (finalresult.get(87) != null){
                    result_stock88.setText(finalresult.get(87));
                    result_stock88.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 88){
                if  (finalresult.get(88) != null){
                    result_stock89.setText(finalresult.get(88));
                    result_stock89.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 89){
                if  (finalresult.get(89) != null){
                    result_stock90.setText(finalresult.get(89));
                    result_stock90.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 90){
                if  (finalresult.get(90) != null){
                    result_stock91.setText(finalresult.get(90));
                    result_stock91.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 91){
                if  (finalresult.get(91) != null){
                    result_stock92.setText(finalresult.get(91));
                    result_stock92.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 92){
                if  (finalresult.get(92) != null){
                    result_stock93.setText(finalresult.get(92));
                    result_stock93.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 93){
                if  (finalresult.get(93) != null){
                    result_stock94.setText(finalresult.get(93));
                    result_stock94.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 94){
                if  (finalresult.get(94) != null){
                    result_stock95.setText(finalresult.get(94));
                    result_stock95.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 95){
                if  (finalresult.get(95) != null){
                    result_stock96.setText(finalresult.get(95));
                    result_stock96.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 96){
                if  (finalresult.get(96) != null){
                    result_stock97.setText(finalresult.get(96));
                    result_stock97.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 97){
                if  (finalresult.get(97) != null){
                    result_stock98.setText(finalresult.get(97));
                    result_stock98.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 98){
                if  (finalresult.get(98) != null){
                    result_stock99.setText(finalresult.get(98));
                    result_stock99.setVisibility(View.VISIBLE);
                }
            }
            if (finalresult.size() > 99){
                if  (finalresult.get(99) != null){
                    result_stock100.setText(finalresult.get(99));
                    result_stock100.setVisibility(View.VISIBLE);
                }
            }

        choose_result.setText("共有" + String.valueOf(finalresult.size()) + "隻股票符合條件");
//        choose_result.setText(test[9]);

        Button mBtnChooseAgain = (Button) findViewById(R.id.choose_again_btn);
        final int[] finalHow_choose_flag = how_choose_flag;
        mBtnChooseAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (finalHow_choose_flag[0] == 0) {
                    intent = new Intent(
                            End.this, PickingActivity.class);
                    startActivity(intent);
                }
                else if (finalHow_choose_flag[0] == 1){
                    intent = new Intent(
                            End.this, BasicPickingActivity.class);
                    startActivity(intent);
                }
                else if (finalHow_choose_flag[0] == 2){
                    intent = new Intent(
                            End.this, TecPickingActivity.class);
                    startActivity(intent);
                }
                else if (finalHow_choose_flag[0] == 3){
                    intent = new Intent(
                            End.this, ChipPickingActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}

