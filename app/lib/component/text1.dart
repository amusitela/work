import 'package:flutter/material.dart';

class MyText1 extends StatelessWidget {
  const MyText1({super.key,required this.text});
  final String text;

  @override
  Widget build(BuildContext context) {
    return Container(
        padding:const EdgeInsets.all(10),
        child: Text(
          text,
          style: const TextStyle(
              fontSize: 33,
              fontWeight: FontWeight.bold, // 设置字体加粗
              letterSpacing: 0.33, // 设置字间距
              fontFamily: "Dosis",
              height: 1),
        ),
      );
  }
}