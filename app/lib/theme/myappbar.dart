import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';

class MyAppBar extends StatelessWidget {
  const MyAppBar({super.key,required this.text});
  final String text;
  @override
  Widget build(BuildContext context) {
    return  AppBar(
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
        title:  Text(
          text,
          style: MyTextStyle.mediumLargeBlack,
        ),
        centerTitle: true,
      );
  }
}