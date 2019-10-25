#!/usr/bin/env python
# coding: utf-8

# In[1]:
if __name__ == '__main__' and __package__ is None: 


import sys
from urllib.request import urlopen
from bs4 import BeautifulSoup
def crawling (date, categorycode, itemcode):
#date="2019-09-05"
#categorycode="200"
#itemcode="212"

    url ="https://www.kamis.or.kr/customer/price/wholesale/item.do?action=priceinfo&regday="+date+"&itemcategorycode="+categorycode+"&itemcode="+itemcode+"&kindcode=&productrankcode=&convert_kg_yn=N"
    result = urlopen(url)
    html = result.read()

    bsObject = BeautifulSoup(html, "html.parser") 
    table=bsObject.find_all("table")
    tr=table[3].find_all("tr")
    ta=tr[1].find_all("td")
    print("평균 값:");print(ta[1].text)
    return ta[1].text


# In[2]:


crawling("2019-09-05","200","212")


# In[ ]:




