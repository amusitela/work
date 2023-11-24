import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart'; // 用于更改状态栏颜色

class ForexPage extends StatelessWidget {
  ForexPage({super.key});
  final List<Map<String, dynamic>> currencyData = [
    {
      'country': '美国',
      'currency': 'USD',
      'buyRate': '726.57',
      'sellRate': '723.69',
      'realRate': '723.53',
      'flag': '🇺🇸',
    },
    {
      'country': '日本',
      'currency': 'YEN',
      'buyRate': '726.57',
      'sellRate': '723.69',
      'realRate': '723.53',
      'flag': '🇺🇸',
    },
    // 更多货币数据...
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          color: Colors.black,
          onPressed: () {
            // 在这里添加返回操作
            // Navigator.pop(context);
            Navigator.pushNamed(context, '/');
          },
        ),
        title: const Text(
          "外汇",
          style: MyTextStyle.mediumLargeBlack,
        ),
        centerTitle: true,
      ),
      body: ListView.separated(
        itemCount: currencyData.length,
        separatorBuilder: (context, index) => Divider(color: Colors.grey[300]),
        itemBuilder: (context, index) {
          var data = currencyData[index];
          return ListTile(
            leading: CircleAvatar(
              backgroundColor: Colors.white, // 根据需要设置旗帜背景颜色
              child: Text(data['flag'], style: const TextStyle(fontSize: 24)),
            ),
            title: Text('${data['country']}(${data['currency']})'),
            subtitle: Row(
              children: <Widget>[
                Expanded(child: Text('买入价 ${data['buyRate']}')),
                Expanded(child: Text('卖出价 ${data['sellRate']}')),
              ],
            ),
            trailing: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.end,
              children: <Widget>[
                const Text('实时汇率', style: TextStyle(color: Colors.grey)),
                Text(data['realRate'],
                    style: const TextStyle(fontWeight: FontWeight.bold)),
              ],
            ),
          );
        },
      ),
    );
  }
}
