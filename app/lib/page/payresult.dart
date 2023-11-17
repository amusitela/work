import 'package:flutter/material.dart';

class PaymentResultPage extends StatelessWidget {
  final bool success;

  PaymentResultPage({required this.success});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(
              success ? Icons.check_circle : Icons.error,
              size: 64.0,
              color: success ? Colors.green : Colors.red,
            ),
            SizedBox(height: 16.0),
            Text(
              success ? '支付成功' : '支付失败',
              style: TextStyle(fontSize: 18.0, fontWeight: FontWeight.bold),
            ),
          ],
        ),
      ),
    );
  }
}