import matplotlib.pyplot as plt
from datetime import datetime
import matplotlib.dates as mdates

# 数据
#data = 280', 'items': [{'sdtUpdate': '2023-11-14', 'dwfeRateLJ': '0.0091', 'dyejiLjSYL': '-0.0156', 'ratioLJ': '0.3131', 'ratingSimilarAve': '0.0410'}, {'sdtUpdate': '2023-11-15', 'dwfeRateLJ': '0.0362', 'dyejiLjSYL': '-0.0305', 'ratioLJ': '0.8633', 'ratingSimilarAve': '0.0640'}, {'sdtUpdate': '2023-11-16', 'dwfeRateLJ': '0.0815', 'dyejiLjSYL': '0.0051', 'ratioLJ': '0.1442', 'ratingSimilarAve': '0.0876'}, {'sdtUpdate': '2023-11-17', 'dwfeRateLJ': '0.1177', 'dyejiLjSYL': '0.0258', 'ratioLJ': '0.2571', 'ratingSimilarAve': '0.1073'}, {'sdtUpdate': '2023-11-20', 'dwfeRateLJ': '0.1358', 'dyejiLjSYL': '0.0021', 'ratioLJ': '0.7151', 'ratingSimilarAve': '0.1208'}, {'sdtUpdate': '2023-11-21', 'dwfeRateLJ': '0.1449', 'dyejiLjSYL': '-0.0362', 'ratioLJ': '0.7024', 'ratingSimilarAve': '0.1163'}, {'sdtUpdate': '2023-11-22', 'dwfeRateLJ': '0.1268', 'dyejiLjSYL': '-0.0932', 'ratioLJ': '-0.0959', 'ratingSimilarAve': '0.0929'}, {'sdtUpdate': '2023-11-23', 'dwfeRateLJ': '0.0815', 'dyejiLjSYL': '-0.2077', 'ratioLJ': '0.5030', 'ratingSimilarAve': '0.0579'}, {'sdtUpdate': '2023-11-24', 'dwfeRateLJ': '0.0815', 'dyejiLjSYL': '-0.2083', 'ratioLJ': '-0.1825', 'ratingSimilarAve': '0.0579'}, {'sdtUpdate': '2023-11-27', 'dwfeRateLJ': '0.0634', 'dyejiLjSYL': '-0.2875', 'ratioLJ': '-0.4869', 'ratingSimilarAve': '0.0432'}, {'sdtUpdate': '2023-11-28', 'dwfeRateLJ': '0.0815', 'dyejiLjSYL': '-0.2444', 'ratioLJ': '-0.2619', 'ratingSimilarAve': '0.0561'}, {'sdtUpdate': '2023-11-29', 'dwfeRateLJ': '0.0905', 'dyejiLjSYL': '-0.2277', 'ratioLJ': '-0.8155', 'ratingSimilarAve': '0.0527'}, {'sdtUpdate': '2023-11-30', 'dwfeRateLJ': '0.1177', 'dyejiLjSYL': '-0.1679', 'ratioLJ': '-0.5534', 'ratingSimilarAve': '0.0813'}, {'sdtUpdate': '2023-12-01', 'dwfeRateLJ': '0.1358', 'dyejiLjSYL': '-0.1796', 'ratioLJ': '-0.4890', 'ratingSimilarAve': '0.0904'}, {'sdtUpdate': '2023-12-04', 'dwfeRateLJ': '0.1358', 'dyejiLjSYL': '-0.2099', 'ratioLJ': '-0.7753', 'ratingSimilarAve': '0.0871'}, {'sdtUpdate': '2023-12-05', 'dwfeRateLJ': '0.1268', 'dyejiLjSYL': '-0.2390', 'ratioLJ': '-2.4368', 'ratingSimilarAve': '0.0832'}, {'sdtUpdate': '2023-12-06', 'dwfeRateLJ': '0.1177', 'dyejiLjSYL': '-0.2637', 'ratioLJ': '-2.5471', 'ratingSimilarAve': '0.0705'}, {'sdtUpdate': '2023-12-07', 'dwfeRateLJ': '0.1177', 'dyejiLjSYL': '-0.2441', 'ratioLJ': '-2.6367', 'ratingSimilarAve': '0.0888'}, {'sdtUpdate': '2023-12-08', 'dwfeRateLJ': '0.1268', 'dyejiLjSYL': '-0.1967', 'ratioLJ': '-2.5266', 'ratingSimilarAve': '0.1021'}, {'sdtUpdate': '2023-12-11', 'dwfeRateLJ': '0.1725', 'dyejiLjSYL': '-0.1062', 'ratioLJ': '-1.8084', 'ratingSimilarAve': '0.1403'}, {'sdtUpdate': '2023-12-12', 'dwfeRateLJ': '0.1725', 'dyejiLjSYL': None, 'ratioLJ': '-1.4146', 'ratingSimilarAve': '0.1573'}]}
data={'serviceResponse': {'responseCode': '000000', 'responseMsg': '查询成功'}, 'strBakCode': '010761', 'items': [{'sdtUpdate': '2023-11-14', 'dwfeRateLJ': '0.1920', 'dyejiLjSYL': '0.0569', 'ratioLJ': '0.3131', 'ratingSimilarAve': '0.4730'}, {'sdtUpdate': '2023-11-15', 'dwfeRateLJ': '1.5669', 'dyejiLjSYL': '0.8172', 'ratioLJ': '0.8633', 'ratingSimilarAve': '1.1541'}, {'sdtUpdate': '2023-11-16', 'dwfeRateLJ': '0.5377', 'dyejiLjSYL': '0.0567', 'ratioLJ': '0.1442', 'ratingSimilarAve': '0.1081'}, {'sdtUpdate': '2023-11-17', 'dwfeRateLJ': '0.5684', 'dyejiLjSYL': '-0.1526', 'ratioLJ': '0.2571', 'ratingSimilarAve': '0.5520'}, {'sdtUpdate': '2023-11-20', 'dwfeRateLJ': '1.5593', 'dyejiLjSYL': '0.1053', 'ratioLJ': '0.7151', 'ratingSimilarAve': '1.0968'}, {'sdtUpdate': '2023-11-21', 'dwfeRateLJ': '1.2520', 'dyejiLjSYL': '0.1033', 'ratioLJ': '0.7024', 'ratingSimilarAve': '0.6296'}, {'sdtUpdate': '2023-11-22', 'dwfeRateLJ': '0.1229', 'dyejiLjSYL': '-0.6241', 'ratioLJ': '-0.0959', 'ratingSimilarAve': '-0.5109'}, {'sdtUpdate': '2023-11-23', 'dwfeRateLJ': '0.9755', 'dyejiLjSYL': '-0.1996', 'ratioLJ': '0.5030', 'ratingSimilarAve': '0.3982'}, {'sdtUpdate': '2023-11-24', 'dwfeRateLJ': '-0.3072', 'dyejiLjSYL': '-0.7993', 'ratioLJ': '-0.1825', 'ratingSimilarAve': '-0.6138'}, {'sdtUpdate': '2023-11-27', 'dwfeRateLJ': '-0.0154', 'dyejiLjSYL': '-1.3392', 'ratioLJ': '-0.4869', 'ratingSimilarAve': '-0.5143'}, {'sdtUpdate': '2023-11-28', 'dwfeRateLJ': '-0.0154', 'dyejiLjSYL': '-1.2823', 'ratioLJ': '-0.2619', 'ratingSimilarAve': '-0.1575'}, {'sdtUpdate': '2023-11-29', 'dwfeRateLJ': '-0.5684', 'dyejiLjSYL': '-2.1072', 'ratioLJ': '-0.8155', 'ratingSimilarAve': '-0.7034'}, {'sdtUpdate': '2023-11-30', 'dwfeRateLJ': '-0.6913', 'dyejiLjSYL': '-1.9036', 'ratioLJ': '-0.5534', 'ratingSimilarAve': '-0.7594'}, {'sdtUpdate': '2023-12-01', 'dwfeRateLJ': '-1.2213', 'dyejiLjSYL': '-2.2422', 'ratioLJ': '-0.4890', 'ratingSimilarAve': '-0.8676'}, {'sdtUpdate': '2023-12-04', 'dwfeRateLJ': '-1.8588', 'dyejiLjSYL': '-2.7998', 'ratioLJ': '-0.7753', 'ratingSimilarAve': '-1.5311'}, {'sdtUpdate': '2023-12-05', 'dwfeRateLJ': '-3.5947', 'dyejiLjSYL': '-4.2600', 'ratioLJ': '-2.4368', 'ratingSimilarAve': '-3.1445'}, {'sdtUpdate': '2023-12-06', 'dwfeRateLJ': '-3.2875', 'dyejiLjSYL': '-4.0503', 'ratioLJ': '-2.5471', 'ratingSimilarAve': '-2.8042'}, {'sdtUpdate': '2023-12-07', 'dwfeRateLJ': '-3.8252', 'dyejiLjSYL': '-4.2853', 'ratioLJ': '-2.6367', 'ratingSimilarAve': '-3.0608'}, {'sdtUpdate': '2023-12-08', 'dwfeRateLJ': '-4.2169', 'dyejiLjSYL': '-4.1464', 'ratioLJ': '-2.5266', 'ratingSimilarAve': '-2.8128'}, {'sdtUpdate': '2023-12-11', 'dwfeRateLJ': '-3.9174', 'dyejiLjSYL': '-3.7495', 'ratioLJ': '-1.8084', 'ratingSimilarAve': '-1.9550'}, {'sdtUpdate': '2023-12-12', 'dwfeRateLJ': '-3.7407', 'dyejiLjSYL': None, 'ratioLJ': '-1.4146', 'ratingSimilarAve': '-1.9016'}]}
# 提取数据
dates = [datetime.strptime(item['sdtUpdate'], '%Y-%m-%d') for item in data['items']]
dwfeRateLJ = [float(item['dwfeRateLJ']) for item in data['items']]
dyejiLjSYL = [float(item['dyejiLjSYL']) if item['dyejiLjSYL'] is not None else 0.0 for item in data['items']]
ratioLJ = [float(item['ratioLJ']) for item in data['items']]
ratingSimilarAve = [float(item['ratingSimilarAve']) for item in data['items']]

# 绘制图表
fig, axs = plt.subplots(4, 1, figsize=(10, 12), sharex=True)

# 累计收益率
axs[0].plot(dates, dwfeRateLJ, marker='o', label='累计收益率')
axs[0].set_ylabel('累计收益率')
axs[0].legend()

# 业绩累计收益率
axs[1].plot(dates, dyejiLjSYL, marker='o', label='业绩累计收益率')
axs[1].set_ylabel('业绩累计收益率')
axs[1].legend()

# 比率
axs[2].plot(dates, ratioLJ, marker='o', label='比率')
axs[2].set_ylabel('比率')
axs[2].legend()

# 评级平均值
axs[3].plot(dates, ratingSimilarAve, marker='o', label='评级平均值')
axs[3].set_ylabel('评级平均值')
axs[3].legend()

# 共享 X 轴标签
plt.xticks(rotation=45)
plt.gca().xaxis.set_major_locator(mdates.DayLocator(interval=2))
plt.gca().xaxis.set_major_formatter(mdates.DateFormatter('%Y-%m-%d'))

# 调整布局
plt.tight_layout()
plt.show()