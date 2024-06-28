import sys
import os

import numpy as np

from app.api.trade.service.kis_api import KisApi

sys.path.append(os.path.dirname(os.path.dirname(os.path.dirname((os.path.dirname(os.path.dirname(os.path.abspath(__file__))))))))


from app.api.trade.repository.tradeRepository import TradeRepository
from app.api.trade.service.trade_util import Reader
from dotenv import load_dotenv
import requests
import json
import datetime 
import time
import yaml
import pandas as pd 

from app.api.trade.model.model import TradeModel

# with open('C:\\Users\\qorhk\\JSggun\\JSggun\\config.yaml', encoding='UTF-8') as f:
#     _cfg = yaml.load(f, Loader=yaml.FullLoader)


class TradeService():
    

    BASE_DIR = os.path.dirname(os.path.abspath(__file__))
    load_dotenv(os.path.join(BASE_DIR, ".env"))

    def __init__(self):
        self.data = TradeModel()
        self.repo = TradeRepository()
        self.util = Reader()
        self.api = KisApi()
        self.data.sname = 'C:\\Users\\qorhk\\JSggun\\trade-AI\\app\\api\\trade\\save\\'
        # with open('config.yaml', encoding='UTF-8') as f:
            # cfg = yaml.load(f, Loader=yaml.FullLoader)
        # self.APP_KEY1 = cfg['APP_KEY_HO']
        # self.APP_SECRET1 = cfg['APP_SECRET_HO']
        # self.CANO1 = cfg['CANO_HO']
        # self.ACNT_PRDT_CD = '01'
        # self.DISCORD_WEBHOOK_URL = "https://hooks.slack.com/services/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"
        # self.URL_BASE = "https://openapivts.koreainvestment.com:29443"
        # ACCESS_TOKEN = ""
        self.drop_columns = ['orgn_odno','ord_unpr','cncl_yn',
                              'loan_dt','cncl_cfrm_qty','rmn_qty',
                              'rjct_qty','ccld_cndt_name','infm_tmd','ctac_tlno','prdt_type_cd',
                              'excg_dvsn_cd','inqr_ip_addr','cpbc_ordp_ord_rcit_dvsn_cd','cpbc_ordp_infm_mthd_dvsn_cd',
                              'cpbc_ordp_mtrl_dvsn_cd','ord_orgno','rsvn_ord_end_dt','ordr_empno']
        
    
        
    def start(self,key,secret,cano,url,accountId,buy_ratio,sell_ratio,symbol_list=[]):
        try:
            ACCESS_TOKEN = self.api.get_access_token(key,secret,url)
            bought_list = [] # 매수 완료된 종목 리스트
            total_cash = self.api.get_balance(key,secret,url,ACCESS_TOKEN,cano) # 보유 현금 조회
            stock_dict = self.api.get_stock_balance(key,secret,url,ACCESS_TOKEN,cano) # 보유 주식 조회
            for sym in stock_dict.keys():
                bought_list.append(sym)
                
            target_buy_count = 10 # 매수할 종목 수
            
            buy_percent = 0.1 # 종목당 매수 금액 비율
            buy_amount = total_cash * buy_percent  # 종목별 주문 금액 계산
            soldout = False
            
            
            self.api.send_message("===국내 주식 자동매매 프로그램을 시작합니다===")
            while True:
                t_now = datetime.datetime.now()
                t_9 = t_now.replace(hour=9, minute=0, second=0, microsecond=0)
                t_start = t_now.replace(hour=9, minute=5, second=0, microsecond=0)
                t_sell = t_now.replace(hour=15, minute=10, second=0, microsecond=0)
                t_exit = t_now.replace(hour=15, minute=20, second=0,microsecond=0)
                t_test = t_now.replace(hour=21, minute=27, second=0,microsecond=0)
                today = datetime.datetime.today().weekday()
            
             
                if today == 5 or today == 6:  # 토요일이나 일요일이면 자동 종료
                    self.api.send_message("주말이므로 프로그램을 종료합니다.")
                    break
                
                
                # if t_9 < t_now < t_start and soldout == False: # 잔여 수량 매도
                #     for sym, qty in stock_dict.items():
                #         self.api.sell(key,secret,url,ACCESS_TOKEN,cano,sym, qty)
                #     soldout == True
                #     bought_list = []
                #     stock_dict = self.api.get_stock_balance(key,secret,url,ACCESS_TOKEN,cano)
                    
                    
                if t_start < t_now < t_sell :  # AM 09:05 ~ PM 03:15 : 매수 
                    for sym in symbol_list:
                        if len(bought_list) < target_buy_count:
                            if sym in bought_list:
                                continue
                            buy_target_price = self.api.get_target_price(sym,key,secret,url,ACCESS_TOKEN,buy_ratio)
                            current_price = self.api.get_current_price(sym,key,secret,url,ACCESS_TOKEN)
                            if buy_target_price < current_price:
                                buy_qty = 0  # 매수할 수량 초기화
                                buy_qty = int(buy_amount // current_price)
                                if buy_qty > 0:
                                    self.api.send_message(f"{sym} 목표가 달성({buy_target_price} < {current_price}) 매수를 시도합니다.")
                                    result = self.api.buy(key,secret,url,ACCESS_TOKEN,cano,sym, buy_qty)
                                    if result:
                                        soldout = False
                                        bought_list.append(sym)
                                        self.api.get_stock_balance(key,secret,url,ACCESS_TOKEN,cano)
                                        df = pd.json_normalize(self.api.get_trade(key,secret,url,ACCESS_TOKEN,cano)['output1'])
                                        df.drop(self.drop_columns,axis=1,inplace=True)
                                        df['account_id'] = accountId
                                        df['trade_type'] = "ai"
                                        df.to_csv(f'{self.data.sname}{datetime.datetime.today().strftime("%Y%m%d")}계좌{accountId}거래내역.csv',index=False)
                            time.sleep(1)

                            # if len(bought_list) >0:
                                
                            #     sell_target_price_dic = self.api.get_sell_target_price(key,secret,url,ACCESS_TOKEN,cano)
                            #     for sym in bought_list:
                            #         sell_target_price = int(sell_target_price_dic[sym])
                            
                            
                                    # df = pd.json_normalize(self.api.get_trade(key,secret,url,ACCESS_TOKEN,cano)['output1'])
                                    # df.drop(self.drop_columns,axis=1,inplace=True)
                                    # df['account_id'] = accountId
                                    # df['trade_type'] = "ai"
                                    # sell_target_price =df.loc[(df['pdno'] == sym) & (df['sll_buy_dvsn_cd']=='02'),['avg_prvs']] 
                                    # df.sort_values(by='ord_tmd', ascending=False,inplace=True)
                                    # sell_target_price = sell_target_price.iloc[0]
                                    # if isinstance(sell_target_price, (pd.Series, np.ndarray)):
                                    #    sell_target_price = int(float(sell_target_price.item()) * sell_ratio)

                                #     current_price = self.api.get_current_price(sym,key,secret,url,ACCESS_TOKEN)
                                #     if sell_target_price < current_price:
                                #         sell_qty = df['tot_ccld_qty']
                                #         if sell_qty > 0:
                                #             self.api.send_message(f"{sym} 목표가 달성({sell_target_price} < {current_price}) 매도를 시도합니다.")
                                #             result = self.api.sell(key,secret,url,ACCESS_TOKEN,cano,sym,sell_qty)
                                #             if result:
                                #                 bought_list.remove(sym)
                                #                 self.api.get_stock_balance(key,secret,url,ACCESS_TOKEN,cano)
                                #                 df = pd.json_normalize(self.api.get_trade(key,secret,url,ACCESS_TOKEN,cano)['output1'])
                                #                 df.drop(self.drop_columns,axis=1,inplace=True)
                                #                 df['account_id'] = accountId
                                #                 df['trade_type'] = "ai"
                                #                 df.to_csv(f'{self.data.sname}{datetime.datetime.today().strftime("%Y%m%d")}계좌{accountId}거래내역.csv',index=False)
                                # time.sleep(1)
                        
                            
                    if (t_now.minute//15==0 ) and t_now.second <= 5: 
                        self.api.get_stock_balance(key,secret,url,ACCESS_TOKEN,cano)
                        
                        time.sleep(5)
                    
                if t_sell < t_now < t_exit:  # PM 03:15 ~ PM 03:20 : 일괄 매도
                    if soldout == False:
                        stock_dict = self.api.get_stock_balance(key,secret,url,ACCESS_TOKEN,cano)
                        for sym, qty in stock_dict.items():
                            self.api.sell(key,secret,url,ACCESS_TOKEN,cano,sym, qty)
                        soldout = True
                        bought_list = []
                        time.sleep(1)
                        
                        
                if t_exit < t_now:  # PM 03:20 ~ :프로그램 종료
                    self.api.send_message("프로그램을 종료합니다.")
                    df = pd.json_normalize(self.api.get_trade(key,secret,url,ACCESS_TOKEN,cano)['output1'])
                    df.drop(self.drop_columns,axis=1,inplace=True)
                    df['account_id'] = accountId
                    df['trade_type'] = "ai"
                    print(df)
                    df.to_csv(f'{self.data.sname}{datetime.datetime.today().strftime("%Y%m%d")}계좌{accountId}거래내역.csv',index=False)
                    print('csv 저장완료')
                    self.repo.save(df)
                    break

        except Exception as e:
            self.api.send_message(f"[오류 발생]{e}")
            time.sleep(1)
        

        