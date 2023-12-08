import 'dart:developer';

import 'package:app/page/addCardPage.dart';
import 'package:app/page/body/EditUserInfoPage.dart';
import 'package:app/page/body/creditcard.dart';
import 'package:app/page/body/monthly_budget_page.dart';
import 'package:app/page/forex_page.dart';
import 'package:app/page/funddetail.dart';
import 'package:app/page/guidance.dart';
import 'package:app/page/index.dart';
import 'package:app/page/login.dart';
import 'package:app/page/news.dart';
import 'package:app/page/newslist.dart';
import 'package:app/page/payment.dart';
import 'package:app/page/payment_csdn.dart';
import 'package:app/page/payresult.dart';
import 'package:app/page/register.dart';
import 'package:app/page/scanpage.dart';
import 'package:app/page/service.dart';
import 'package:app/page/account.dart';
import 'package:app/page/transfer.dart';
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
      case '/pay':
        return MaterialPageRoute(
          builder: (context) => const main_keyboard(),
        );
      case '/service':
        return MaterialPageRoute(
          builder: (context) => const ChatPage(),
        );
      case '/forex':
        return MaterialPageRoute(
          builder: (context) => ForexPage(),
        );
      case '/transfer':
        return MaterialPageRoute(
          builder: (context) => const Transfer(),
        );
      case '/pay/success':
        return MaterialPageRoute(
          builder: (context) => PaymentResultPage(
            success: true,
          ),
        );
      case '/article':
        return MaterialPageRoute(
          builder: (context) => NewsListPage(),
        );
      case '/article/detail':
        final String articleId = settings.arguments as String;
        return MaterialPageRoute(
            builder: (context) => NewsDetailPage(articleId: articleId));

      case '/account':
        return MaterialPageRoute(
            builder: (context) => TradeDetailPage(
                ));
      case '/pay/fail':
        return MaterialPageRoute(
          builder: (context) => PaymentResultPage(
            success: false,
          ),
        );
      case '/add/card':
        return MaterialPageRoute(
          builder: (context) => AddCardPage(),
        );
      case '/profile':
        return MaterialPageRoute(
          builder: (context) => const EditUserInfoPage(),
        );
      case '/bill':
        return MaterialPageRoute(
          builder: (context) => const MonthlyBudgetPage(),
        );
      case '/pay/user':
        // 从 settings 中获取参数
        final String userId = settings.arguments as String;
        print('------------------------------$userId-------------');
        return MaterialPageRoute(
            builder: (context) => PaymentPage(userId: userId));

      case '/fund/detail':
        // 从 settings 中获取参数
        final String fundId = settings.arguments as String;
        return MaterialPageRoute(
            builder: (context) => FundDetail(fundId: fundId));
      default:
        return MaterialPageRoute(
          builder: (context) => const IndexPage(),
        );
    }
  }
}
