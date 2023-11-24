import 'package:flutter/material.dart';



class InvestmentInfoPage3 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
    body: ListView(
      children: <Widget>[
        InvestmentInfoTile3(
          title: '中国混沌-原始天选',
          subtitle: '起购 0.01起购',
          percentage: '3.27%',
          tag: '年化回报',
          isPositive: true,
        ),
        InvestmentInfoTile3(
          title: '投资混沌基金A(006383)',
          subtitle: '起购 1.00元起购',
          percentage: '3.59%',
          tag: '年化回报',
          isPositive: true,
        ),
        // 其他的投资信息组件
      ],
    ),);
  }
}

class InvestmentInfoTile3 extends StatelessWidget {
  final String title;
  final String subtitle;
  final String percentage;
  final String tag;
  final bool isPositive;

  const InvestmentInfoTile3({
    Key? key,
    required this.title,
    required this.subtitle,
    required this.percentage,
    required this.tag,
    required this.isPositive,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(vertical: 10, horizontal: 16),
      decoration: BoxDecoration(
        border: Border(
          bottom: BorderSide(color: Colors.grey[300]!, width: 1),
        ),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(
            title,
            style: TextStyle(
              fontSize: 16,
              fontWeight: FontWeight.bold,
              color: Colors.black,
            ),
          ),
          SizedBox(height: 4),
          Text(
            subtitle,
            style: TextStyle(
              fontSize: 14,
              color: Colors.grey[600],
            ),
          ),
          SizedBox(height: 8),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Text(
                tag,
                style: TextStyle(
                  fontSize: 12,
                  color: Colors.red,
                ),
              ),
              Text(
                percentage,
                style: TextStyle(
                  fontSize: 20,
                  color: isPositive ? Colors.red : Colors.green,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
