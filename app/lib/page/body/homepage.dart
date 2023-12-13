import 'package:app/component/myButton.dart';
import 'package:app/component/myicon.dart';
import 'package:app/theme/colorplatte.dart';
import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';
import '../monthly_budget_page.dart';

class HomePage extends StatelessWidget {
  const HomePage(
      {super.key,
      required this.imageUrl,
      required this.userName,
      required this.money});
  final String imageUrl;
  final String userName;
  final String money;
  @override
  Widget build(BuildContext context) {
    Widget avatar = GestureDetector(
      onTap: () {
        Navigator.pushNamed(context, '/profile');
      },
      child: Row(
        // crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          const SizedBox(
            width: 20,
          ),
          CircleAvatar(
            radius: 30,
            backgroundImage: AssetImage(imageUrl),
          ),
          const SizedBox(
            width: 10,
          ),
          Text(
            userName,
            style: MyTextStyle.large,
            selectionColor: Colors.white,
          ),
          const Expanded(child: Text(" ")),
          const Icon(Icons.arrow_forward_ios_rounded),
        ],
      ),
    );

    Widget buttonSection = Align(
      alignment: Alignment.center,
      child: Container(
          width: 350,
          padding: const EdgeInsets.all(20),
          decoration: const BoxDecoration(
            borderRadius: BorderRadius.all(
              Radius.circular(8),
            ),
            boxShadow: [
              BoxShadow(
                color: Colors.grey,
                offset: Offset(0, 0), // 设置底部阴影
                blurRadius: 5.0,
              )
            ],
            gradient: LinearGradient(
              begin: Alignment.topLeft,
              end: Alignment.bottomRight,
              colors: [Colors.white, ColorPlatte.cardRed], // 渐变色数组
              stops: [1.0, 1.0], // 渐变色的起始和结束位置
            ),
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              GestureDetector(
                onTap: () {
                  Navigator.pushNamed(context, "/account");
                },
                child: const MyIcon(
                    text: "我的账户", icon: Icon(Icons.account_balance_wallet)),
              ),
              GestureDetector(
                onTap: () {
                  Navigator.pushNamed(context, "/bill");
                },
                child: const MyIcon(
                    text: "我的账单", icon: Icon(Icons.monetization_on)),
              ),
              GestureDetector(
                onTap: () {
                  Navigator.pushNamed(context, "/service");
                },
                child: const MyIcon(text: "在线客服", icon: Icon(Icons.headset)),
              ),
              GestureDetector(
                onTap: () {
                  Navigator.pushNamed(context, "/article");
                },
                child: const MyIcon(text: "新闻资讯", icon: Icon(Icons.article)),
              ),
            ],
          )),
    );
    Widget incomeExpenditureModule = GestureDetector(
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => const MonthlyBudgetPage()),
        );
      },
      child: Align(
        alignment: Alignment.center,
        child: Container(
          width: Constant.cardWidth,
          padding: const EdgeInsets.only(top: 20, bottom: 20),
          decoration: const BoxDecoration(
            borderRadius: BorderRadius.all(
              Radius.circular(8),
            ),
            color: Colors.white, // 你可以调整颜色以匹配你的设计
          ),
          child: const Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Text(
                    '  本月收支',
                    style: MyTextStyle.medium,
                  ),
                  SizedBox(
                    width: 10,
                  ),
                  Icon(Icons.arrow_forward_ios)
                ],
              ),
              SizedBox(
                height: 20,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text(
                    '  收入:',
                    style: MyTextStyle.medium,
                  ),
                  Text(
                    '¥ 4,000.00', // 替换为实际的收入数据
                    style: MyTextStyle.small,
                  ),
                  Text(
                    '支出:',
                    style: MyTextStyle.medium,
                  ),
                  Text(
                    '¥ 103.02', // 替换为实际的支出数据
                    style: MyTextStyle.small,
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );

    Widget card = GestureDetector(
      onTap: () {
        // Navigator.pushNamed(context, "/total/money");
      },
      child: Align(
        alignment: Alignment.center,
        child: Container(
          width: 350,
          height: 100,
          decoration: const BoxDecoration(
            borderRadius: BorderRadius.all(
              Radius.circular(8),
            ),
            image: DecorationImage(
              image: AssetImage('images/logo.png'),
              // fit: BoxFit.cover,
              // colorFilter: ColorFilter.mode(
              //   Colors.black.withOpacity(0.5), // 设置图片透明度
              //   BlendMode.dstATop, // 使用dstATop模式
              // ),
            ),
          ),
          margin: const EdgeInsets.only(top: 20),
          child: Column(children: [
            const Row(
              children: [
                Text(
                  '资产  ￥',
                  style: MyTextStyle.mediumLargeBlack,
                ),
                SizedBox(
                  width: 5,
                ),
              ],
            ),
            const SizedBox(
              height: 10,
            ),
            Text(
              money,
              style: MyTextStyle.large,
            )
          ]),
        ),
      ),
    );

    return Scaffold(
      body: ListView(
        children: [
          avatar,
          const SizedBox(height: 30),
          buttonSection,
          const SizedBox(height: 20),
          Align(
            alignment: Alignment.center,
            child: SizedBox(
              width: Constant.cardWidth,
              child: const Text(
                "我的资产",
                style: MyTextStyle.mediumLarge,
              ),
            ),
          ),
          const SizedBox(height: 10),
          card,
          const SizedBox(height: 20),
          incomeExpenditureModule,
        ],
      ),
    );
  }
}
