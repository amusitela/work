import 'package:app/page/guidance.dart';
import 'package:flutter/material.dart';

class MyRouter {
  static Route<dynamic> generateRoute(RouteSettings settings) {
    switch (settings.name) {
      case '/g1':
        return PageRouteBuilder(
          transitionDuration: const Duration(milliseconds: 400), // 过渡动画的持续时间
          pageBuilder: (context, animation, secondaryAnimation) =>
              const Guidance(
            imagePath: "images/Business_Statistics.png",
            text1: "设置你的理标",
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
            text1: "设置你理财目标",
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
      default:
        return PageRouteBuilder(
          transitionDuration: const Duration(milliseconds: 400), // 过渡动画的持续时间
          pageBuilder: (context, animation, secondaryAnimation) =>
              const Guidance(
            imagePath: "images/Business_Statistics.png",
            text1: "设置你的理财目标",
            text2:
                "Your goals will help us to formulate the right recommendations for success",
            order: 1,
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
    }
  }
}
