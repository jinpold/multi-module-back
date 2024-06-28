

import threading
from fastapi import FastAPI, Request
from pydantic import BaseModel
import uvicorn
from starlette.middleware.cors import CORSMiddleware
import yaml
from app.api.trade.repository.tradeRepository import TradeRepository
from app.api.trade.service.trade_service import TradeService

app = FastAPI()
    
origins = [
    "*"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
service = TradeService()

stocks = ["005930","000660","000990","033640","093370",
                  "066570","010120","053610","118990","217820","035510"]

with open('config.yaml', encoding='UTF-8') as f:
    cfg = yaml.load(f, Loader=yaml.FullLoader)

url = cfg['URL_BASE']


@app.get("/")
async def tradeJin():
        key_JIN = cfg['APP_KEY_JIN']
        secret_JIN = cfg['APP_SECRET_JIN']
        cano_JIN = cfg['CANO_JIN']
        
        key_SOO = cfg['APP_KEY_SOO']
        secret_SOO = cfg['APP_SECRET_SOO']
        cano_SOO = cfg['CANO_SOO']
        
        key_HO = cfg['APP_KEY_HO']
        secret_HO = cfg['APP_SECRET_HO']
        cano_HO = cfg['CANO_HO']
        
        key_JU = cfg['APP_KEY_JU']
        secret_JU = cfg['APP_SECRET_JU']
        cano_JU = cfg['CANO_JU']
        
        key_HOJU = cfg['APP_KEY_HOJU']
        secret_HOJU = cfg['APP_SECRET_HOJU']
        cano_HOJU = cfg['CANO_HOJU']
        
        key_HOHYUN = cfg['APP_KEY_HOHYUN']
        secret_HOHYUN = cfg['APP_SECRET_HOHYUN']
        cano_HOHYUN = cfg['CANO_HOHYUN']
        
        key_GEONWOO = cfg['APP_KEY_GEONWOO']
        secret_GEONWOO = cfg['APP_SECRET_GEONWOO']
        cano_GEONWOO = cfg['CANO_GEONWOO']
        
        key_TAEHO = cfg['APP_KEY_TAEHO']
        secret_TAEHO = cfg['APP_SECRET_TAEHO']
        cano_TAEHO = cfg['CANO_TAEHO']
        
        key_DONGGYU = cfg['APP_KEY_DONGGYU']
        secret_DONGGYU = cfg['APP_SECRET_DONGGYU']
        cano_DONGGYU = cfg['CANO_DONGGYU']
        
        # 앱키, 시크릿, 계좌, url, account_id, buy_ratio, sell_ratio, 종목리스트 
        thread_1 = threading.Thread(target = service.start, args=(key_HO,secret_HO,cano_HO,url,1,0.1,1.01,stocks))
        thread_2 = threading.Thread(target = service.start, args=(key_JIN,secret_JIN,cano_JIN,url,2,0.1,1.02,stocks))
        thread_3 = threading.Thread(target = service.start, args=(key_SOO,secret_SOO,cano_SOO,url,3,0.05,1.03,stocks))
        thread_4 = threading.Thread(target = service.start, args=(key_JU,secret_JU,cano_JU,url,4,0.01,1.04,stocks))
        thread_5 = threading.Thread(target = service.start, args=(key_HOJU,secret_HOJU,cano_HOJU,url,5,0.3,1.05,stocks))
        thread_6 = threading.Thread(target = service.start, args=(key_HOHYUN,secret_HOHYUN,cano_HOHYUN,url,6,0.2,1.06,stocks))
        thread_7 = threading.Thread(target = service.start, args=(key_GEONWOO,secret_GEONWOO,cano_GEONWOO,url,7,0.15,1.07,stocks))
        thread_8 = threading.Thread(target = service.start, args=(key_TAEHO,secret_TAEHO,cano_TAEHO,url,8,0.25,1.08,stocks))
        thread_9 = threading.Thread(target = service.start, args=(key_DONGGYU,secret_DONGGYU,cano_DONGGYU,url,9,0.35,1.09,stocks))
        
        thread_1.start()
        thread_2.start()
        thread_3.start()
        thread_4.start()
        thread_5.start()
        thread_6.start()
        thread_7.start()
        thread_8.start()
        thread_9.start()
        
        '''
        삼성전자 : 005930
        sk하이닉스 : 000660
        DB하이텍 : 000990
        네패스 : 033640
        후성 : 093370
        LG전자 : 066570
        LS일렉트릭 : 010120
        SK시그넷 : 260870  //모의투자 매매불가 종목
        모트렉스 : 118990
        원익피앤이 : 217820
        신세계I&C : 035510
        프로텍 : 053610
        '''
    
    
    
if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8000)
    