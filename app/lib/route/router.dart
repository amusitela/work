import 'package:app/page/guidance.dart';
import 'package:app/page/index.dart';
import 'package:app/page/login.dart';
import 'package:app/page/register.dart';
import 'package:app/page/scanpage.dart';
import 'package:flutter/material.dart';

class MyRouter {
  static Route<dynamic> generateRoute(RouteSettings settings) {
    switch (settings.name) {
    case '/g':
        return MaterialPageRoute(
          builder: (context) => const Guidance(
            imagePath: "images/Business_Statistics.png",
            text1: "第一页",
            text2:
                "Your goals will help us to formulate the right recommendations for success",
            order: 1,
          ),
        );
      case '/g1':
        return PageRouteBuilder(
          transitionDuration: const Duration(milliseconds: 400), // 过渡动画的持续时间
          pageBuilder: (context, animation, secondaryAnimation) =>
              const Guidance(
            imagePath: "images/Business_Statistics.png",
            text1: "第二页",
            text2:
                "Your goals will help us to formulate the right recommendations for success",
            order: 2,
          ),
          transitionsBuilder: (context, animation, secondaryAnimation, child) {
            return SlideTransition(
              position: Tween<Offset>(
                begin: const Offset(1.0, 0.0),
                end: Offset.zero,
              ).animate(animation),
              child: child,
            );
          },
        );
      case '/g2':
        return PageRouteBuilder(
          transitionDuration: const Duration(milliseconds: 400), // 过渡动画的持续时间
          pageBuilder: (context, animation, secondaryAnimation) =>
              const Guidance(
            imagePath: "images/Business_Statistics.png",
            text1: "第三页",
            text2:
                "Your goals will help us to formulate the right recommendations for success",
            order: 3,
          ),
          transitionsBuilder: (context, animation, secondaryAnimation, child) {
            return SlideTransition(
              position: Tween<Offset>(
                begin: const Offset(1.0, 0.0),
                end: Offset.zero,
              ).animate(animation),
              child: child,
            );
          },
        );
      case '/qrcode':
        return MaterialPageRoute(
          builder: (context) => const ScanPage(),
        );
      case '/':
        return MaterialPageRoute(
          builder: (context) => const IndexPage(),
        );
        case '/login':
        return MaterialPageRoute(
          builder: (context) => const LoginPage(),
        );
        case '/register':
        return MaterialPageRoute(
          builder: (context) => const RegisterPage(),
        );
      default:
        return MaterialPageRoute(
          builder: (context) => const IndexPage(),
        );
    }
  }
}