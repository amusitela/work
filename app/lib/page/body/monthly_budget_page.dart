import 'package:flutter/material.dart';

class MonthlyBudgetPage extends StatelessWidget {
  const MonthlyBudgetPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('本月收支'),
        backgroundColor: Colors.blue,
      ),
      body: ListView(
        children: [
          // 示例交易记录
          buildTransactionItem(
            context,
            merchant: '超市购物',
            amount: '-¥20.90',
            cardType: '信用卡',
            cardLastDigits: '3960',
          ),
          // 可以继续添加更多的交易记录
        ],
      ),
    );
  }

  Widget buildTransactionItem(
      BuildContext context, {
        required String merchant,
        required String amount,
        required String cardType,
        required String cardLastDigits,
      }) {
    return ListTile(
      leading: Icon(Icons.store, color: Colors.blue), // 可以根据商户类型更改图标
      title: Text(merchant, style: TextStyle(fontSize: 16.0)),
      subtitle: Text('$cardType 尾号$cardLastDigits', style: TextStyle(color: Colors.grey)),
      trailing: Text(amount, style: TextStyle(color: amount.startsWith('-') ? Colors.red : Colors.green)),
    );
  }
}
