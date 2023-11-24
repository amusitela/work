import 'package:flutter/material.dart';




class MyHomePage2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('仿UI'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Card(
          elevation: 4.0,
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              children: <Widget>[
                InfoRow(title: '美元(USD)', value: '715.00', isValueUp: false),
                InfoRow(title: '澳元(AU)', value: '455.99', isValueUp: true),
                InfoRow(title: '上证指数', value: '3067.93', isValueUp: false),
                Divider(),
                InfoRow(title: '中美混沌-原始天选', value: '0.01起购', isValueUp: false, valueFontSize: 14.0),
                SizedBox(height: 10),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Text('百分比', style: TextStyle(color: Colors.red, fontSize: 20.0)),
                    Text('3.27%', style: TextStyle(color: Colors.red, fontSize: 24.0, fontWeight: FontWeight.bold)),
                  ],
                ),
                // 其他的UI组件
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class InfoRow extends StatelessWidget {
  final String title;
  final String value;
  final bool isValueUp;
  final double valueFontSize;

  const InfoRow({
    Key? key,
    required this.title,
    required this.value,
    this.isValueUp = false,
    this.valueFontSize = 18.0,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: <Widget>[
        Text(title, style: TextStyle(color: Colors.grey[600], fontSize: 16.0)),
        Text(
          value,
          style: TextStyle(
            color: isValueUp ? Colors.green : Colors.red,
            fontSize: valueFontSize,
            fontWeight: FontWeight.bold,
          ),
        ),
      ],
    );
  }
}
