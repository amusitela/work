import 'package:app/page/payment_csdn.dart';
import 'package:app/page/body/homepage.dart';
import 'package:app/page/guidance.dart';
import 'package:app/page/index.dart';
import 'package:app/page/login.dart';
import 'package:app/page/payment.dart';
import 'package:app/page/register.dart';
import 'package:app/page/scanpage.dart';
import 'package:app/page/tradedetail.dart';
import 'package:app/route/router.dart';
// import 'package:app/test/test.dart';
import 'package:app/test/test2.dart';
import 'package:app/test/test3.dart';
import 'package:app/theme/mytheme.dart';
import 'package:flutter/material.dart';

// void main() {
//   runApp(MaterialApp(
//     theme: AppTheme.getTheme(),
//     debugShowCheckedModeBanner: false,
//     onGenerateRoute: MyRouter.generateRoute,
//     // home: const HomePage(imageUrl: "images/1.jpg", userName: 'userName', money: '100.0')
//     home: const IndexPage()
//   ));
// }
void main() {
  runApp(const MaterialApp(
      onGenerateRoute: MyRouter.generateRoute,
      home: IndexPage()
      ));
}


