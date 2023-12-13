import requests
import json

url = "https://ebsnew.boc.cn/SAP/bocop/unlogin/ezdb/fund/query_selection"

headers = {
    "Host": "ebsnew.boc.cn",
    "Connection": "keep-alive",
    "Content-Length": "19",
    "Pragma": "no-cache",
    "Cache-Control": "no-cache",
    "bfw-ctrl": "json",
    "acton": "",
    "language": "zh_CN",
    "clentid": "554",
    "cifNumber": "",
    "Content-Type": "application/json",
    "Accept": "application/json",
    "userid": "",
    "User-Agent": "Mozilla/5.0 (Linux; Android 11; 21051182C Build/RKQ1.200826.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/94.0.4606.85 Mobile Safari/537.36/bocapp(8.3.0);lsta:0;;sessionId:fcffd042-8211-41cd-a0fb-d83af7571d89;cifNumber:;appSequence:22200;",
    "trantm": "144500",
    "uuid": "170244990038692479778",
    "transseqno": "170244990038692479778",
    "chnflg": "6",
    "trandt": "20231213",
    "version": "1.0.0",
    "Origin": "https://ebsnew.boc.cn",
    "X-Requested-With": "com.chinamworld.bocmbci",
    "Sec-Fetch-Site": "same-origin",
    "Sec-Fetch-Mode": "cors",
    "Sec-Fetch-Dest": "empty",
    "Referer": "https://ebsnew.boc.cn/bocphone/VuePhone/fundHome/index.html",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7",
}

payload = {
    "subChannel": "04"
}

response = requests.post(url, headers=headers, data=json.dumps(payload))

# Print the response content or handle it as needed
print(response.text)
