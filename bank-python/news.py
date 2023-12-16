import requests

url = "https://openapi.boc.cn/unlogin/query_article_detail"

headers = {
    "Host": "openapi.boc.cn",
    "Connection": "keep-alive",
    "Content-Length": "84",
    "Pragma": "no-cache",
    "Cache-Control": "no-cache",
    "timeout": "100000",
    "clentid": "548",
    "Content-Type": "application/json;charset=UTF-8",
    "Accept": "application/json, text/plain, */*",
    "clientid": "548",
    "userid": "",
    "User-Agent": "Mozilla/5.0 (Linux; Android 11; 21051182C Build/RKQ1.200826.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/94.0.4606.85 Mobile Safari/537.36/bocapp(8.3.0);lsta:0;;sessionId:840888a4-23a6-4eca-b9a4-31f0ce47aa68;cifNumber:;appSequence:22200;",
    "trantm": "133941",
    "uuid": "r0gl3s3eO7HtrJzev8tqyP3tlNZv1Toa",
    "trandt": "20231213",
    "Origin": "https://openapi.boc.cn",
    "X-Requested-With": "com.chinamworld.bocmbci",
    "Sec-Fetch-Site": "same-origin",
    "Sec-Fetch-Mode": "cors",
    "Sec-Fetch-Dest": "empty",
    "Referer": "https://openapi.boc.cn/inews/index.html",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7",
    "Cookie": "sajssdk_2015_cross_bocop-app-infonew_user=1; sensorsdata2015jssdkcrossbocop-app-info=%7B%22distinct_id%22%3A%22bocop-app-info%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiYm9jb3AtYXBwLWluZm8ifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%22bocop-app-info%22%7D",
}

data = {
    "channelId": "110000",
    "readerId": "",
    "articleId": "1108630",
    "needFullLifeCycle": False
}

response = requests.post(url, json=data, headers=headers)

print(response.status_code)
print(response.json())  # If the response is in JSON format
