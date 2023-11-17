import 'package:flutter/material.dart';

class PaymentPage extends StatefulWidget {
  PaymentPage({super.key,required this.userId});
  final String userId;
  @override
  _PaymentPageState createState() => _PaymentPageState();
}

class _PaymentPageState extends State<PaymentPage> {
  TextEditingController _amountController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();
  String _selectedPaymentMethod = '';
  bool _isPaymentSelected = false;

  void _showPaymentOptions() {
    showModalBottomSheet(
      context: context,
      builder: (BuildContext context) {
        return Container(
          padding: EdgeInsets.all(16.0),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              ListTile(
                title: Text('银行卡1'),
                onTap: () {
                  setState(() {
                    _selectedPaymentMethod = '银行卡1';
                    _isPaymentSelected = true;
                  });
                  Navigator.pop(context);
                },
              ),
              ListTile(
                title: Text('银行卡2'),
                onTap: () {
                  setState(() {
                    _selectedPaymentMethod = '银行卡2';
                    _isPaymentSelected = true;
                  });
                  Navigator.pop(context);
                },
              ),
            ],
          ),
        );
      },
    );
  }

  Future<void> _showPasswordDialog() async {
    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('输入支付密码'),
          content: TextField(
            controller: _passwordController,
            keyboardType: TextInputType.number,
            obscureText: true,
            maxLength: 6,
            decoration: InputDecoration(counterText: ''),
          ),
          actions: <Widget>[
            TextButton(
              onPressed: () {
                Navigator.pop(context);
              },
              child: Text('取消'),
            ),
            TextButton(
              onPressed: () {
                // 在这里添加密码校验逻辑
                if (_passwordController.text == '123456') {
                  print('支付成功');
                  Navigator.pop(context);
                } else {
                  print('密码错误，请重新输入');
                  // 可以在这里添加密码错误提示
                }
              },
              child: Text('确认'),
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('向用户${widget.userId}转账'),
      ),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            TextField(
              controller: _amountController,
              keyboardType: TextInputType.number,
              decoration: InputDecoration(labelText: '请输入金额'),
            ),
            SizedBox(height: 16.0),
            ElevatedButton(
              onPressed: () {
                if (!_isPaymentSelected) {
                  _showPaymentOptions();
                } else {
                  // _showPasswordDialog();
                  Navigator.pushNamed(context, '/pay');
                }
              },
              child: Text('支付'),
            ),
            SizedBox(height: 16.0),
            Text('选择支付方式: $_selectedPaymentMethod'),
          ],
        ),
      ),
    );
  }
}