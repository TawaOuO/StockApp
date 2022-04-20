import yfinance as yf
from pandas_datareader import data as pdr
import time
import datetime
import pandas as pd
import numpy as np
from numpy import *
import csv
import requests
from bs4 import BeautifulSoup


#突破周線
def break_5ma(close_value):
	value = close_value.tail(1).to_numpy()
	previous_day_value = close_value.tail(2).to_numpy()
	previous_day_value = previous_day_value[0]
	five_day_value = close_value.tail(5).to_numpy()	
	five_day_average = np.average(five_day_value)
	previous_five_day_value = close_value.tail(6).to_numpy()
	new_previous_five_day_value = np.delete(previous_five_day_value, [5])
	previous_five_day_average = np.average(new_previous_five_day_value)

	if previous_day_value < previous_five_day_average and value >= five_day_average:
		return int(1)
	elif previous_day_value > previous_five_day_average and value <= five_day_average:
		return int(2)
	else:
		return int(0)

#突破雙周線
def break_10ma(close_value):
	value = close_value.tail(1).to_numpy()
	previous_day_value = close_value.tail(2).to_numpy()
	previous_day_value = previous_day_value[0]
	ten_day_value = close_value.tail(10).to_numpy()	
	ten_day_average = np.average(ten_day_value)
	previous_ten_day_value = close_value.tail(11).to_numpy()
	new_previous_ten_day_value = np.delete(previous_ten_day_value, [10])
	previous_ten_day_average = np.average(new_previous_ten_day_value)

	if previous_day_value < previous_ten_day_average and value >= ten_day_average:
		return int(1)
	elif previous_day_value > previous_ten_day_average and value <= ten_day_average:
		return int(2)
	else:
		return int(0)

#突破月線
def break_20ma(close_value):
	value = close_value.tail(1).to_numpy()
	previous_day_value = close_value.tail(2).to_numpy()
	previous_day_value = previous_day_value[0]
	twenty_day_value = close_value.tail(20).to_numpy()	
	twenty_day_average = np.average(twenty_day_value)
	previous_twenty_day_value = close_value.tail(21).to_numpy()
	new_previous_twenty_day_value = np.delete(previous_twenty_day_value, [20])
	previous_twenty_day_average = np.average(new_previous_twenty_day_value)

	if previous_day_value < previous_twenty_day_average and value >= twenty_day_average:
		return int(1)
	elif previous_day_value > previous_twenty_day_average and value <= twenty_day_average:
		return int(2)
	else:
		return int(0)

#日KD交叉
def kd_day_break(close_value, high_value, low_value):
	K_value = []
	D_value = []
	RSV_value = []
	previous_fourty_eight_day_value = close_value.tail(48).to_numpy()
	previous_fourty_eight_day_high_value = high_value.tail(48).to_numpy()
	previous_fourty_eight_day_low_value = low_value.tail(48).to_numpy()
	previous_nine_day_value = []
	K_value_previous = 40
	D_value_previous = 40
	
	try:
		for i in range(1,41):
			previous_nine_day_value = previous_fourty_eight_day_value[i-1:i+8]
			previous_nine_day_high_value = previous_fourty_eight_day_high_value[i-1:i+8]
			previous_nine_day_low_value = previous_fourty_eight_day_low_value[i-1:i+8]
			previous_nine_day_high = max(previous_nine_day_high_value)
			previous_nine_day_low = min(previous_nine_day_low_value)
			today_value = previous_nine_day_value[8]
			if (previous_nine_day_high - previous_nine_day_low) == 0:
				RSV = 0
			else:
				RSV = 100 * ((today_value - previous_nine_day_low)/(previous_nine_day_high - previous_nine_day_low))
			RSV_value.append(RSV)
			K_value_today = (K_value_previous * (2/3)) + (RSV * (1/3))
			K_value_previous = K_value_today
			D_value_today = (D_value_previous * (2/3)) + (K_value_today * (1/3))
			D_value_previous = D_value_today
			K_value.append(K_value_today)
			D_value.append(D_value_today)

		if K_value[-1] > D_value[-1] and K_value[-2] < D_value[-2] and K_value[-3] < D_value[-3] and K_value[-4] < D_value[-4] and K_value[-5] < D_value[-5] and K_value[-6] < D_value[-6]:
			return int(1)
		elif K_value[-1] < D_value[-1] and K_value[-2] > D_value[-2] and K_value[-3] > D_value[-3] and K_value[-4] > D_value[-4] and K_value[-5] > D_value[-5] and K_value[-6] > D_value[-6]:
			return int(2)
		else:
			return int(0) 

	except ValueError:
		return int(0)

#日MACD交叉
def MACD_break(close_value):
	short_EMA_win = 12
	long_EMA_win = 26
	MACD_win = 9
	OSC_value = []
	all_value = close_value.tail(long_EMA_win+MACD_win+120).to_numpy()
	pre_short_EMA_win_value = all_value[-short_EMA_win-121:-121]
	pre_long_EMA_win_value = all_value[-long_EMA_win-121:-121]
	pre_short_EMA = mean(pre_short_EMA_win_value)
	pre_long_EMA = mean(pre_long_EMA_win_value)
	pre_MACD = pre_short_EMA - pre_long_EMA

	try:
		for i in range(1,120):
			price_value = close_value.tail(120).to_numpy()
			price = price_value[i]
			short_EMA = pre_short_EMA * 1.0 * (short_EMA_win-1) / (short_EMA_win+1) + price * 2.0 / (short_EMA_win+1)
			long_EMA = pre_long_EMA * 1.0 * (long_EMA_win-1) / (long_EMA_win+1) + price * 2.0 / (long_EMA_win+1)
			DIF = short_EMA - long_EMA
			pre_short_EMA = short_EMA
			pre_long_EMA = long_EMA
			MACD = pre_MACD * 1.0 * (MACD_win-1) / (MACD_win+1) + DIF * 2.0 / (MACD_win+1)
			pre_MACD = MACD
			OSC = DIF - MACD
			OSC_value.append(OSC)
		
		if OSC_value[-1] > 0 and OSC_value[-2] < 0 and OSC_value[-3] < 0 and OSC_value[-4] < 0:
			return int(1)
		elif OSC_value[-1] < 0 and OSC_value[-2] > 0 and OSC_value[-3] > 0 and OSC_value[-4] > 0:
			return int(2)
		else:
			return int(0)

	except ValueError:
		return int(0)


#月均交易量
def monthvolume(volumed):
	month_volume = volumed.tail(20).to_numpy()
	month_volume_avg = np.average(month_volume)
	
	if month_volume_avg > 1000000:
		return int(1)
	else:
		return int(0) 

#法人買賣超
def overbuy(stock_num, who):
	foreign_investor_buyout = []
	investment_trust_buyout = []
	times = 0
	date_list = []

	try:
		r = requests.get('https://ebroker-dj.fbs.com.tw/z/zc/zcl/zcl.djhtm?a=' + stock_num + '&b=1')
		soup = BeautifulSoup(r.text, 'html.parser')
		sel = soup.select("td.t3n0")
		sel = str(sel)
		stats =str(soup)
		testdata = sel.find('<td class="t3n0" nowrap="">')
		testdata2 = sel[testdata:]
		testdata2 = testdata2.split('/')
		count = 0
		
		for i in testdata2:
			testdata3 =	filter(lambda ch: ch in '0123456789/', i)
			testdata4 = ''.join(list(testdata3))
			testdata2[count] = testdata4
			count += 1
		
		for i in range(0,5):
			cache = testdata2[i * 3 + 0] + '/' + testdata2[i * 3 + 1] + '/' + testdata2[i * 3 + 2]
			cache = cache[2:]
			date_list.append(cache)
			
		for i in date_list:
			endofdate = stats.find('<td class="t3n0" nowrap="">' + i + '</td>')
			foreign_investor_buyout_index = stats[endofdate + 68:endofdate + 85]
			investment_trust_buyout_index = stats[endofdate + 100:endofdate + 120]
			foreign_investor_buyout_index_1 = filter(lambda ch: ch in '0123456789-', foreign_investor_buyout_index)
			foreign_investor_buyout_index_2 = ''.join(list(foreign_investor_buyout_index_1))
			investment_trust_buyout_index_1 = filter(lambda ch: ch in '0123456789-', investment_trust_buyout_index)
			investment_trust_buyout_index_2 = ''.join(list(investment_trust_buyout_index_1))
			foreign_investor_buyout.append(int(foreign_investor_buyout_index_2))
			investment_trust_buyout.append(int(investment_trust_buyout_index_2))

		if who == 0:
			a = foreign_investor_buyout
		elif who == 1:
			a = investment_trust_buyout
		else:
			return 0

		
		if a[0] > 0 and a[1] > 0 and a[2] > 0 and a[3] > 0 and a[4] > 0:
			five_day_overbuy = 1
		elif a[0] < 0 and a[1] < 0 and a[2] < 0 and a[3] < 0 and a[4] < 0:
			five_day_overbuy = 2
		else:
			five_day_overbuy = 0

		if a[0] > 0 and a[1] > 0 and a[2] > 0:
			three_day_overbuy = 1
		elif a[0] < 0 and a[1] < 0 and a[2] < 0:
			three_day_overbuy = 2
		else:
			three_day_overbuy = 0

		if a[0] > 0 and a[1] <= 0 and a[2] <= 0 and a[3] <= 0:
			first_day_overbuy = 1
		elif a[0] < 0 and a[1] >= 0 and a[2] >= 0 and a[3] >= 0:
			first_day_overbuy = 2
		else:
			first_day_overbuy = 0

		return(first_day_overbuy, three_day_overbuy, five_day_overbuy)

	except IndexError:
		return(int(0), int(0), int(0))

	except ValueError:
		return(int(0), int(0), int(0))

#月營收
def Monthly_Income(stock_num):
	try:
		r = requests.get('https://ebroker-dj.fbs.com.tw/z/zc/zch/zch.djhtm?a=' + stock_num)
		soup = BeautifulSoup(r.text, 'html.parser')
		stats = str(soup.select('table.t01'))
		pos = stats.find('oScrollMenu')
		stats = stats[pos:]
		pos = stats.find('td class="t3n0"')
		stats = stats[pos:]
		stats = stats.split('</td>')
		cache_list = []
		index_list = []

		#index_list[[年/月, 營收, 月增率, 去年同期, 年增率, 累計營收, 年增率(累計)]](單位:千元)
		for chap in stats:
			chap = chap.split('>')[-1]
			filt = filter(lambda s:s in '0123456789.,-/%', chap)
			cache_str = ''.join(list(filt))
			cache_list.append(cache_str)
			if len(cache_list) >= 7:
				index_list.append(cache_list)
				cache_list = []
			else:
				continue

		increase_count = 0
		decrease_count = 0
		for i in range(0, 3):
			year_increase = float(index_list[i][4].replace(',', '').replace('%', ''))
			if year_increase >= 20:
				increase_count += 1
			elif year_increase <= -20:
				decrease_count += 1
			else:
				continue

		if increase_count == 3:
			return int(1)
		elif decrease_count == 3:
			return int(2)
		else:
			return int(0)
	except Exception:
		return 0

#季營收,EPS
def Quarterly_Income_EPS(stock_num):
	try:
		r = requests.get('https://ebroker-dj.fbs.com.tw/z/zc/zcd_' + stock_num + '.djhtm')
		soup = BeautifulSoup(r.text, 'html.parser')
		stats = str(soup.select('table.t01'))
		pos = stats.find('td class="t3n0"')
		stats = stats[pos:]
		stats = stats.split('</td>')
		cache_list = []
		index_list = []

		#index_list[[季度, 加權平均股數, 營業收入, 稅前淨利, 稅後淨利, 每股營收, 稅前每股盈餘, 稅後每股盈餘]](單位:千股/百萬元)
		for chap in stats:
			chap = chap.split('>')[-1]
			filt = filter(lambda s:s in '0123456789.,-Q', chap)
			cache_str = ''.join(list(filt))
			cache_list.append(cache_str)
			if len(cache_list) >= 8:
				index_list.append(cache_list)
				cache_list = []
			else:
				continue

		#近四季有三季營收年增減>20%
		increase_count = 0
		decrease_count = 0
		for i in range(0, 4):
			quarter_income = float(index_list[i][2].replace(',', ''))
			last_year_quarter_income = float(index_list[i + 4][2].replace(',', ''))
			if quarter_income > last_year_quarter_income:
				increase_count += 1
			elif quarter_income < last_year_quarter_income:
				decrease_count += 1
			else:
				continue
		if increase_count >= 3:
			IncomeIncreaseFlag = int(1)
		elif decrease_count >= 3:
			IncomeIncreaseFlag = int(2)
		else:
			IncomeIncreaseFlag = int(0)

		#近四季有三季EPS年增減>10%
		increase_count = 0
		decrease_count = 0
		for i in range(0, 4):
			quarter_EPS = float(index_list[i][7].replace(',', ''))
			last_year_quarter_EPS = float(index_list[i + 4][7].replace(',', ''))
			if quarter_EPS > last_year_quarter_EPS:
				increase_count += 1
			elif quarter_EPS < last_year_quarter_EPS:
				decrease_count += 1
			else:
				continue
		if increase_count >= 3:
			EPS_IncreaseFlag = int(1)
		elif decrease_count >= 3:
			EPS_IncreaseFlag = int(2)
		else:
			EPS_IncreaseFlag = int(0)

		#季營收創五年新高(低)
		highest_income = 0
		lowest_income = 1000000000
		for index in index_list:
			quarter_income = float(index[2].replace(',', ''))
			if quarter_income >= highest_income:
				highest_income = quarter_income
			elif quarter_income <= lowest_income:
				lowest_income = quarter_income
			else:
				continue
		if highest_income == float(index_list[0][2].replace(',', '')):
			IncomeHighFlag = int(1)
		elif lowest_income == float(index_list[0][2].replace(',', '')):
			IncomeHighFlag = int(2)
		else:
			IncomeHighFlag = int(0)

		return [IncomeIncreaseFlag, EPS_IncreaseFlag, IncomeHighFlag]
	
	except Exception:
		return [int(0), int(0), int(0)]

#殖利率
def Yield(stock_num):
	try:
		r = requests.get('https://ebroker-dj.fbs.com.tw/z/zc/zca/zca.djhtm?a=' + stock_num)
		soup = BeautifulSoup(r.text, 'html.parser')
		stats = str(soup.select('table.t01'))
		pos = stats.find('殖利率')
		stats = stats[pos:pos + 50]
		yield_percent = stats.split('</td>')[1]
		yield_percent = yield_percent.split('>')[-1]
		int_yield_percent = float(yield_percent.replace('%', ''))

		if int_yield_percent >= 5:
			return int(1)
		else:
			return int(0)
	except Exception:
		return int(0)


stock_list = pd.read_csv('stock_index_confirm.csv')[['name', 'number']]
timestart = (datetime.datetime.now() + datetime.timedelta(days = -240)).strftime('%Y-%m-%d')
end_day = (datetime.datetime.now()).strftime('%Y-%m-%d')
today = (datetime.datetime.now() + datetime.timedelta(days = 1)).strftime('%Y-%m-%d')
total_tec_list = pd.DataFrame()

for i in range(0, len(stock_list)):
	try :
		nameofstock = stock_list.name[i]
		index = stock_list.number[i]
		index = str(index).rstrip()
		info = yf.Ticker(index)
		pricedata = info.history(start = timestart, end = today)
		pricedata = pricedata.dropna(axis = 0, how = 'any')
		close_valued = pricedata['Close']
		high_valued = pricedata['High']
		low_valued = pricedata['Low']
		volumed = pricedata['Volume']
		stock_name = index.split('.')
		stock_name = stock_name[0]
		foreign_investor_overbuy_index = overbuy(stock_name, 0)
		investment_trust_overbuy_index = overbuy(stock_name, 1)
		IncomeIncrease_Q, EPS_Increase_Q, IncomeHigh_Q = Quarterly_Income_EPS(stock_name)
		print(stock_name)
		series_tec = pd.Series({'10.5ma':break_5ma(close_valued), '11.10ma':break_10ma(close_valued), '12.20ma':break_20ma(close_valued), '13.KD':kd_day_break(close_valued, high_valued, low_valued), '14.MACD':MACD_break(close_valued), '15.for_1d_buyout':foreign_investor_overbuy_index[0], '16.for_3d_buyout':foreign_investor_overbuy_index[1], '17.for_5d_buyout':foreign_investor_overbuy_index[2], '18.tru_1d_buyout':investment_trust_overbuy_index[0], '19.tru_3d_buyout':investment_trust_overbuy_index[1], '20.tru_5d_buyout':investment_trust_overbuy_index[2], '21.month_volume_avg':monthvolume(volumed), '22.month_income_year%':Monthly_Income(stock_name), '23.IncomeIncrease':IncomeIncrease_Q, '24.EPS_Increase':EPS_Increase_Q, '25.IncomeHigh':IncomeHigh_Q, '26.yield_percent':Yield(stock_name), '27.z_chinese': nameofstock}, name = stock_name)
		total_tec_list = total_tec_list.append(series_tec)

	except IndexError :
		pass

total_tec_list.to_csv('C:/app/' + str(today) + 'tec_result.csv', encoding = 'utf_8_sig')
print('資料整理完畢')