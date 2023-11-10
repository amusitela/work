import 'package:flutter/material.dart';

class MyText2 extends StatelessWidget {
  const MyText2({super.key, required this.text});
  final String text;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(10),
      child: Text(
        text,
        style: const TextStyle(
            fontSize: 15,
            fontWeight: FontWeight.w500, // 设置字体加粗
            letterSpacing: 0.15, // 设置字间距
            fontFamily: "Dosis",
            height: 1.5),
      ),
    );
  }
}
