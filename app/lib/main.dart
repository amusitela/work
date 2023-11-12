import 'package:app/page/body/homepage.dart';
import 'package:app/page/guidance.dart';
import 'package:app/page/index.dart';
import 'package:app/page/login.dart';
import 'package:app/page/register.dart';
import 'package:app/page/scanpage.dart';
import 'package:app/route/router.dart';
import 'package:app/theme/mytheme.dart';
import 'package:flutter/material.dart';


void main() {
  runApp(MaterialApp(
    theme: AppTheme.getTheme(),
    debugShowCheckedModeBanner: false,
    onGenerateRoute: MyRouter.generateRoute,
    // home: const HomePage(imageUrl: "images/1.jpg", userName: 'userName', money: '100.0')
    home: const IndexPage()
  ));                                                                                                                                                                                                                                                                                                                       
}


