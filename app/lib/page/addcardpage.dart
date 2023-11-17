import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';

class AddCardPage extends StatelessWidget {
  final TextEditingController _cardNumberController = TextEditingController();

  AddCardPage({super.key});

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
            Navigator.pushNamed(context, '/account');
          },
        ),
        title: const Text(
          "添加卡号",
          style: MyTextStyle.mediumLargeBlack,
        ),
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Container(
                width: Constant.cardWidth,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(8.0),
                  color: Colors.grey[200],
                ),
                padding: const EdgeInsets.symmetric(horizontal: 16.0),
                child: const Row(
                  children: [
                    Text(
                      '卡号',
                      style: MyTextStyle.mediumLarge,
                    ),
                    Expanded(
                      child: TextField(
                        decoration: InputDecoration(
                          hintText: '请输入',
                          border: InputBorder.none, // 去除边框
                          contentPadding: EdgeInsets.only(left: 8.0),
                        ),
                      ),
                    ),
                    Icon(
                      Icons.camera,
                      color: Colors.blue,
                    ),
                  ],
                )),
            const SizedBox(height: 20),
            ElevatedButton(
                onPressed: () {
                  // 在这里添加处理添加卡号的逻辑
                  String cardNumber = _cardNumberController.text;
                  print('添加卡号：$cardNumber');
                },
                child: SizedBox(
                  width: Constant.cardWidth,
                  child: const Text(
                    '确认',
                    style: MyTextStyle.mediumLarge,
                    textAlign: TextAlign.center,
                  ),
                )),
          ],
        ),
      ),
    );
  }
}
