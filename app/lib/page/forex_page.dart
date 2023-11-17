import 'package:flutter/material.dart';
import 'package:flutter/services.dart'; // 用于更改状态栏颜色

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // 更改状态栏颜色为透明，并设置图标为深色
    SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle(
      statusBarColor: Colors.transparent,
      statusBarIconBrightness: Brightness.dark,
    ));

    return MaterialApp(
      title: '外汇汇率',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: ForexPage(),
    );
  }
}

class ForexPage extends StatelessWidget {
  final List<Map<String, dynamic>> currencyData = [
    {
      'country': '美国',
      'currency': 'USD',
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
        title: Text('外汇汇率'),
        centerTitle: true,
        elevation: 0, // 去除阴影
      ),
      body: ListView.separated(
        itemCount: currencyData.length,
        separatorBuilder: (context, index) => Divider(color: Colors.grey[300]),
        itemBuilder: (context, index) {
          var data = currencyData[index];
          return ListTile(
            leading: CircleAvatar(
              backgroundColor: Colors.white, // 根据需要设置旗帜背景颜色
              child: Text(data['flag'], style: TextStyle(fontSize: 24)),
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
                Text('实时汇率', style: TextStyle(color: Colors.grey)),
                Text(data['realRate'], style: TextStyle(fontWeight: FontWeight.bold)),
              ],
            ),
          );
        },
      ),
    );
  }
}
