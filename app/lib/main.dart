
import 'package:app/page/index.dart';

import 'package:app/route/router.dart';
// import 'package:app/test/test.dart';

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


