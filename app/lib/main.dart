import 'package:app/component/myBuilder.dart';
import 'package:app/login.dart';
import 'package:app/page/guidance.dart';
import 'package:app/route/router.dart';
import 'package:app/test.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
    theme: ThemeData(
        primaryColor: Colors.blue, // 设置主题颜色
        //textColor: Colors.red, // 设置强调颜色
      ),
    home: const Guidance(
        imagePath: "images/Business_Statistics.png",
        // imagePath: "images/1.jpg",
        text1: "设置你的理财目标",
        text2:
            "Your goals will help us to formulate the right recommendations for success",
        order: 1),
    onGenerateRoute: MyRouter.generateRoute,
  ));
}

class MyWidget extends StatefulWidget {
  const MyWidget({super.key});

  @override
  State<MyWidget> createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
