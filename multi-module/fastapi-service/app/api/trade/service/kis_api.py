import sys
import os



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


class KisApi():
    

    BASE_DIR = os.path.dirname(os.path.abspath(__file__))
    load_dotenv(os.path.join(BASE_DIR, ".env"))

    def __init__(self):
        self.data = TradeModel()
        self.repo = TradeRepository()
        
        
        
    def send_message(self,msg):
        '''디스코드 메세지 전송'''
        now = datetime.datetime.now()
        message = {"content": f"[{now.strftime('%Y-%m-%d %H:%M:%S')}] {str(msg)}"}
        # requests.post(self.DISCORD_WEBHOOK_URL, data=message)
        print(message)
        return message


    def get_access_token(self,key,secret,url):
        """토큰 발급"""
        headers = {"content-type":"application/json"}
        
        body = {"grant_type":"client_credentials",
        "appkey":key, 
        "appsecret":secret}
        PATH = "oauth2/tokenP"
        URL = f"{url}/{PATH}"
        res = requests.post(URL, headers=headers, data=json.dumps(body))
        ACCESS_TOKEN = res.json()["access_token"]
        return ACCESS_TOKEN        
        
    def hashkey(self,datas,key,secret,url):
        """암호화"""
        PATH = "uapi/hashkey"
        URL = f"{url}/{PATH}"
        headers = {
        'content-Type' : 'application/json',
        'appKey' : key,
        'appSecret' : secret,
        }
        res = requests.post(URL, headers=headers, data=json.dumps(datas))
        hashkey = res.json()["HASH"]
        return hashkey


    def get_current_price(self,code,key,secret,url,token):
        """현재가 조회"""
        PATH = "uapi/domestic-stock/v1/quotations/inquire-price"
        URL = f"{url}/{PATH}"
        headers = {"Content-Type":"application/json", 
                "authorization": f"Bearer {token}",
                "appKey":key,
                "appSecret":secret,
                "tr_id":"FHKST01010100"}
        params = {
        "fid_cond_mrkt_div_code":"J",
        "fid_input_iscd":code,
        }
        res = requests.get(URL, headers=headers, params=params)
        return int(res.json()['output']['stck_prpr'])   # 주식 현재가 리턴 


    def get_target_price(self,code,key,secret,url,token,target_incr_price):
        """변동성 돌파 전략으로 매수 목표가 조회"""
        PATH = "uapi/domestic-stock/v1/quotations/inquire-daily-price"
        
        URL = f"{url}/{PATH}"
        headers = {"Content-Type":"application/json", 
            "authorization": f"Bearer {token}",
            "appKey":key,
            "appSecret":secret,
            "tr_id":"FHKST01010400"}
        params = {
        "fid_cond_mrkt_div_code":"J",
        "fid_input_iscd":code,
        "fid_org_adj_prc":"1",
        "fid_period_div_code":"D"
        }
        res = requests.get(URL, headers=headers, params=params)
        stck_oprc = int(res.json()['output'][0]['stck_oprc']) #오늘 시가
        stck_hgpr = int(res.json()['output'][1]['stck_hgpr']) #전일 고가
        stck_lwpr = int(res.json()['output'][1]['stck_lwpr']) #전일 저가
        target_price = stck_oprc + (stck_hgpr - stck_lwpr) * target_incr_price
        return target_price


    def get_stock_balance(self,key,secret,url,token,cano):
        """주식 잔고조회"""
        PATH = "uapi/domestic-stock/v1/trading/inquire-balance"
        URL = f"{url}/{PATH}"
        headers = {"Content-Type":"application/json", 
            "authorization":f"Bearer {token}",
            "appKey":key,
            "appSecret":secret,
            "tr_id":"VTTC8434R",
            "custtype":"P",
        }
        params = {
            "CANO": cano,
            "ACNT_PRDT_CD": "01", #ACNT_PRDT_CD
            "AFHR_FLPR_YN": "N",
            "OFL_YN": "",
            "INQR_DVSN": "02",
            "UNPR_DVSN": "01",
            "FUND_STTL_ICLD_YN": "N",
            "FNCG_AMT_AUTO_RDPT_YN": "N",
            "PRCS_DVSN": "01",
            "CTX_AREA_FK100": "",
            "CTX_AREA_NK100": ""
        }
        res = requests.get(URL, headers=headers, params=params)
        stock_list = res.json()['output1']
        evaluation = res.json()['output2']
        stock_dict = {}
        self.send_message(f"====주식 보유잔고====")
        for stock in stock_list:
            if int(stock['hldg_qty']) > 0:
                stock_dict[stock['pdno']] = stock['hldg_qty']
                time.sleep(0.1)
                self.send_message(f"{stock['prdt_name']}({stock['pdno']}): {stock['hldg_qty']}주")
                # time.sleep(0.1)
                # self.send_message(f"{stock['prdt_name']}({stock['pdno']}): 매입가 {stock['pchs_amt']}원")
                # time.sleep(0.1)
                # self.send_message(f"{stock['prdt_name']}({stock['pdno']}): 현재가 {stock['prpr']}원")
                time.sleep(0.1)
        self.send_message(f"주식 평가 금액: {evaluation[0]['scts_evlu_amt']}원")
        time.sleep(0.1)
        self.send_message(f"평가 손익 합계: {evaluation[0]['evlu_pfls_smtl_amt']}원")
        time.sleep(0.1)
        self.send_message(f"총 평가 금액: {evaluation[0]['tot_evlu_amt']}원")
        time.sleep(0.1)
        self.send_message(f"=================")
        return stock_dict
    
    def get_sell_target_price(self,key,secret,url,token,cano):
        """주식 잔고조회"""
        PATH = "uapi/domestic-stock/v1/trading/inquire-balance"
        URL = f"{url}/{PATH}"
        headers = {"Content-Type":"application/json", 
            "authorization":f"Bearer {token}",
            "appKey":key,
            "appSecret":secret,
            "tr_id":"VTTC8434R",
            "custtype":"P",
        }
        params = {
            "CANO": cano,
            "ACNT_PRDT_CD": "01", #ACNT_PRDT_CD
            "AFHR_FLPR_YN": "N",
            "OFL_YN": "",
            "INQR_DVSN": "02",
            "UNPR_DVSN": "01",
            "FUND_STTL_ICLD_YN": "N",
            "FNCG_AMT_AUTO_RDPT_YN": "N",
            "PRCS_DVSN": "01",
            "CTX_AREA_FK100": "",
            "CTX_AREA_NK100": ""
        }
        res = requests.get(URL, headers=headers, params=params)
        stock_list = res.json()['output1']
        stock_dict = {}
        self.send_message(f"====주식 보유잔고====")
        for stock in stock_list:
            if int(stock['hldg_qty']) > 0:
                stock_dict[stock['pdno']] = stock['pchs_avg_pric']
        
        return stock_dict


    def get_balance(self,key,secret,url,token,cano):
        """현금 잔고조회"""
        PATH = "uapi/domestic-stock/v1/trading/inquire-psbl-order"
        URL = f"{url}/{PATH}"
        headers = {"Content-Type":"application/json", 
            "authorization":f"Bearer {token}",
            "appKey":key,
            "appSecret":secret,
            "tr_id":"VTTC8908R",
            "custtype":"P"
        }
        params = {
            "CANO": cano,
            "ACNT_PRDT_CD": "01",
            "PDNO": "005930",
            "ORD_UNPR": "55000",
            "ORD_DVSN": "01",
            "CMA_EVLU_AMT_ICLD_YN": "Y",
            "OVRS_ICLD_YN": "Y"
        }
        res = requests.get(URL, headers=headers, params=params)
        cash = res.json()['output']['ord_psbl_cash']
        self.send_message(f"주문 가능 현금 잔고: {cash}원")
        return int(cash)
        

    def buy(self,key,secret,url,token,cano,code="005930", qty="1"):
        """주식 시장가 매수"""  
        PATH = "uapi/domestic-stock/v1/trading/order-cash"
        URL = f"{url}/{PATH}"
        data = {
            "CANO": cano,
            "ACNT_PRDT_CD": "01",
            "PDNO": code,
            "ORD_DVSN": "01",
            "ORD_QTY": str(int(qty)),
            "ORD_UNPR": "0",
        }
        headers = {"Content-Type":"application/json", 
            "authorization":f"Bearer {token}",
            "appKey":key,
            "appSecret":secret,
            "tr_id":"VTTC0802U",
            "custtype":"P",
            "hashkey" : self.hashkey(data,key,secret,url)
        }
        res = requests.post(URL, headers=headers, data=json.dumps(data))
        if res.json()['rt_cd'] == '0':
            self.send_message(f"[매수 성공]{str(res.json())}")
            return True
        else:
            self.send_message(f"[매수 실패]{str(res.json())}")
            return False


    def sell(self,key,secret,url,token,cano,code="005930", qty="1"):
        """주식 시장가 매도"""
        PATH = "uapi/domestic-stock/v1/trading/order-cash"
        URL = f"{url}/{PATH}"
        data = {
            "CANO": cano,
            "ACNT_PRDT_CD": "01",
            "PDNO": code,
            "ORD_DVSN": "01",
            "ORD_QTY": qty,
            "ORD_UNPR": "0",
        }
        headers = {"Content-Type":"application/json", 
            "authorization":f"Bearer {token}",
            "appKey":key,
            "appSecret":secret,
            "tr_id":"VTTC0801U",
            "custtype":"P",
            "hashkey" : self.hashkey(data,key,secret,url)
        }
        res = requests.post(URL, headers=headers, data=json.dumps(data))
        if res.json()['rt_cd'] == '0':
            self.send_message(f"[매도 성공]{str(res.json())}")
            return True
        else:
            self.send_message(f"[매도 실패]{str(res.json())}")
            return False
    
    def get_trade(self,key,secret,url,token,cano):
        """금일 주문체결 조회 """
        PATH = "uapi/domestic-stock/v1/trading/inquire-daily-ccld"
        URL = f"{url}/{PATH}"
        headers = {"Content-Type":"application/json", 
            "authorization":f"Bearer {token}",
            "appKey":key,
            "appSecret":secret,
            "tr_id":"VTTC8001R",
            "custtype":"P"
        }
        params = {
            "CANO": cano,
            "ACNT_PRDT_CD": "01",
            "INQR_STRT_DT": datetime.datetime.today().strftime("%Y%m%d"),
            "INQR_END_DT": datetime.datetime.today().strftime("%Y%m%d"),
            "SLL_BUY_DVSN_CD": "00",
            "INQR_DVSN": "01",
            "PDNO": "",
            "CCLD_DVSN": "00",
            "ORD_GNO_BRNO": "",
            "ODNO": "",
            "INQR_DVSN_3": "00",
            "INQR_DVSN_1": "",
            "CTX_AREA_FK100": "",
            "CTX_AREA_NK100": "",
        }
        res = requests.get(URL, headers=headers, params=params)
        return res.json()
    
    def get_trade2(self,nkkey,key,secret,url,token,cano):
        """금일 주문체결 조회 """
        PATH = "uapi/domestic-stock/v1/trading/inquire-daily-ccld"
        URL = f"{url}/{PATH}"
        headers = {"Content-Type":"application/json", 
            "authorization":f"Bearer {token}",
            "appKey":key,
            "appSecret":secret,
            "tr_id":"VTTC8001R",
            "custtype":"P"
        }
        params = {
            "CANO": cano,
            "ACNT_PRDT_CD": "01",
            "INQR_STRT_DT": "20240527",
            # "INQR_STRT_DT": datetime.datetime.today().strftime("%Y%m%d"),
            "INQR_END_DT": datetime.datetime.today().strftime("%Y%m%d"),
            "SLL_BUY_DVSN_CD": "00",
            "INQR_DVSN": "01",
            "PDNO": "",
            "CCLD_DVSN": "00",
            "ORD_GNO_BRNO": "",
            "ODNO": "",
            "INQR_DVSN_3": "00",
            "INQR_DVSN_1": "",
            "CTX_AREA_FK100": "",
            "CTX_AREA_NK100": nkkey,
        }
        res = requests.get(URL, headers=headers, params=params)
        return res.json()