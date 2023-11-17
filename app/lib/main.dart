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
  runApp(MaterialApp(
    // home: const HomePage(imageUrl: "images/1.jpg", userName: 'userName', money: '100.0')
    onGenerateRoute: MyRouter.generateRoute,
    home: IndexPage()
  ));
}

// void main() {
//   runApp(const MaterialApp(
//       // home: const HomePage(imageUrl: "images/1.jpg", userName: 'userName', money: '100.0')
//       onGenerateRoute: MyRouter.generateRoute,
//       home: TradeDetailPage(
//         money: "11111",
<<<<<<< HEAD
//         cardList: [{
//         "number":"1234567890",
//         "money":"123",
//         'type':"借记卡"
//         },
//         {
//         "number":"1234567890",
//         "money":"123",
//         'type':"借记卡"
//         },{
//         "number":"1234567890",
//         "money":"123",
//         'type':"借记卡"
//         }],
=======
>>>>>>> d38cb765bf60d884eab7ff9fbe6e482d5a9aed4a
//       )));
// }
