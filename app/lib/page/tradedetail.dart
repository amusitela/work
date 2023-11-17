import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';

class TradeDetailPage extends StatelessWidget {
  const TradeDetailPage(
      {super.key, required this.money, this.earnMoney = "暂未更新"});
  final String money;
  final String earnMoney;

  @override
  Widget build(BuildContext context) {
    Widget wealthCard = Align(
      alignment: Alignment.center,
      child: Container(
          color: const Color(0xFFe7d4b6),
          width: Constant.cardWidth,
          padding: const EdgeInsets.all(15),
          child: Column(
            children: [
              const Align(
              alignment: Alignment.topLeft,
              child: Text(
                "总资产(折算人民币元)",
                style: TextStyle(
                    color: Color(0xFF5f2a01),
                    fontSize: 15,
                    fontWeight: FontWeight.w500, // 设置字体加粗
                    letterSpacing: 0.15, // 设置字间距
                    fontFamily: "Dosis",
                    height: 1.5),
              ),
              ),
              const SizedBox(
                height: 10,
              ),
              Row(
                children: [
                  Text(
                    money,
                    style: const TextStyle(
                        color: Color(0xFF5f2a01),
                        fontSize: 20,
                        fontWeight: FontWeight.bold, // 设置字体加粗
                        letterSpacing: 0.33, // 设置字间距
                        fontFamily: "Dosis",
                        height: 1),
                  ),
                  const SizedBox(
                    width: 10,
                  ),
                  const Icon(
                    Icons.arrow_forward_ios,
                    color: Color(0xFF5f2a01),
                  )
                ],
              ),
              const SizedBox(
                height: 10,
              ),
              Row(
                children: [
                  Text(
                    "昨日收益  $earnMoney",
                    style: const TextStyle(
                        color: Color(0xFF5f2a01),
                        fontSize: 15,
                        fontWeight: FontWeight.w500, // 设置字体加粗
                        letterSpacing: 0.15, // 设置字间距
                        fontFamily: "Dosis",
                        height: 1.5),
                  ),
                  const SizedBox(
                    width: 10,
                  ),
                  const Icon(
                    Icons.arrow_forward_ios,
                    color: Color(0xFF5f2a01),
                  )
                ],
              )
            ],
          )),
    );
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          color: Colors.black,
          onPressed: () {
            // 在这里添加返回操作
            // Navigator.pop(context);
          },
        ),
        title: const Text(
          "账户总览",
          style: MyTextStyle.mediumLargeBlack,
        ),
        centerTitle: true,
      ),
      body: ListView(
        children: [
          wealthCard,
        ],
      ),
    );
  }
}
