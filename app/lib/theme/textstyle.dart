import 'package:flutter/material.dart';

class MyTextStyle {
 static const TextStyle small=TextStyle(fontSize: 16,color: Colors.black,fontWeight: FontWeight.bold);
 static const TextStyle large=TextStyle(
              fontSize: 33,
              fontWeight: FontWeight.bold, // 设置字体加粗
              letterSpacing: 0.33, // 设置字间距
              fontFamily: "Dosis",
              height: 1);
  static const TextStyle medium=TextStyle(
            fontSize: 15,
            fontWeight: FontWeight.w500, // 设置字体加粗
            letterSpacing: 0.15, // 设置字间距
            fontFamily: "Dosis",
            height: 1.5);
  static const TextStyle mediumLarge=TextStyle(
              fontSize: 20,
              fontWeight: FontWeight.bold, // 设置字体加粗
              letterSpacing: 0.33, // 设置字间距
              fontFamily: "Dosis",
              height: 1);
  static const TextStyle mediumLargeBlack=TextStyle(
              fontSize: 20,
              fontWeight: FontWeight.bold, // 设置字体加粗
              letterSpacing: 0.33, // 设置字间距
              fontFamily: "Dosis",
              color: Colors.black,
              height: 1);
}