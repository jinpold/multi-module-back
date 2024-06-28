
from dataclasses import dataclass
import pandas as pd


@dataclass
class TradeModel : 
    _context : str = ''
    _fname : str = '' # file name
    _dname : str = '' # data path
    _sname : str = '' # sava path
    _trade : pd.DataFrame = None
    _id : str = ''
    _label : str = ''
    
    @property
    def dname(self) -> str : return self._dname
    
    @dname.setter
    def dname(self, dname: str) : self._dname = dname
    
    @property
    def sname(self) -> str: return self._sname
    
    @sname.setter
    def sname(self, sname: str): self._sname = sname
    
    @property
    def fname(self) -> str: return self._fname
    
    @fname.setter
    def fname(self, fname: str): self._fname = fname

    @property
    def trade(self) -> str: return self._trade

    @trade.setter
    def trade(self, trade: pd.DataFrame): self._trade = trade

    @property
    def id(self) -> str: return self._id

    @id.setter
    def id(self, id: str): self._id = id

    @property
    def label(self) -> str: return self._label

    @label.setter
    def label(self,label: str): self._label = label
