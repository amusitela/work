import 'package:app/test/test2.dart';
import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';

class TradeDetailPage extends StatelessWidget {
  TradeDetailPage({super.key});
  final String money = "1111";
  final String earnMoney = "暂未更新";
  final List<Map<String, String>> cardList = Test.cardList;
  // final List<String> cardList;
  @override
  Widget build(BuildContext context) {
    Widget wealthCard = Align(
      alignment: Alignment.center,
      child: Container(
          decoration: const BoxDecoration(
            borderRadius: BorderRadius.all(Radius.circular(8)),
            color: Color(0xFFe7d4b6),
          ),
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
    Widget textWealth = Align(
      alignment: Alignment.center,
      child: SizedBox(
        width: Constant.cardWidth,
        child: const Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              "银行卡",
              style: MyTextStyle.mediumLarge,
            ),
            Icon(Icons.flip_camera_android_sharp)
          ],
        ),
      ),
    );

    Widget card = Column(
      children: List.generate(
        cardList.length,
        (index) {
          Map<String, String> cardItem = cardList[index];
          return Padding(
            padding: const EdgeInsets.only(bottom: 10.0),
            child: Align(
              child: Container(
                width: Constant.cardWidth,
                decoration: const BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.all(Radius.circular(8)),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.grey,
                      offset: Offset(0, 0),
                      blurRadius: 2.0,
                    ),
                  ],
                ),
                child: Column(
                  children: [
                    const SizedBox(
                      height: 10,
                    ),
                    Row(
                      children: [
                        const SizedBox(
                          width: 10,
                        ),
                        Image.asset(
                          'images/card.png',
                          height: 70,
                        ),
                        const SizedBox(
                          width: 20,
                        ),
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              cardItem['number'].toString(),
                              style: MyTextStyle.mediumLarge,
                            ),
                            Text(
                              cardItem['type'].toString(),
                              style: MyTextStyle.mediumLarge,
                            ),
                          ],
                        ),
                      ],
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Padding(
                      padding: const EdgeInsets.all(10),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          const Text(
                            "账面余额",
                            style: MyTextStyle.medium,
                          ),
                          Text(
                            "人民币元: ${cardItem['money'].toString()}",
                            style: MyTextStyle.mediumLarge,
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ),
          );
        },
      ),
    );
    Widget addCard = Align(
      alignment: Alignment.center,
      child: Container(
        width: Constant.cardWidth,
        padding: const EdgeInsets.only(bottom: 10, top: 10),
        decoration: const BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.all(Radius.circular(8)),
          boxShadow: [
            BoxShadow(
              color: Colors.grey,
              offset: Offset(0, 0), // 设置底部阴影
              blurRadius: 2.0,
            ),
          ],
        ),
        child: const Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Text(
                "+手动添加卡/账户",
                style: TextStyle(
                    color: Colors.blue,
                    fontSize: 15,
                    fontWeight: FontWeight.w500, // 设置字体加粗
                    letterSpacing: 0.15, // 设置字间距
                    fontFamily: "Dosis",
                    height: 1.5),
              ),
              Text(
                "添加你的账户、数字钱包",
                style: MyTextStyle.medium,
              )
            ]),
      ),
    );
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          color: Colors.black,
          onPressed: () {
            // 在这里添加返回操作
            Navigator.pop(context);
          },
        ),
        title: const Text(
          "账户总览",
          style: MyTextStyle.mediumLargeBlack,
        ),
        centerTitle: true,
      ),
      body: ListView(
        // physics: const NeverScrollableScrollPhysics(),
        children: [
          const SizedBox(
            height: 40,
          ),
          wealthCard,
          const SizedBox(
            height: 20,
          ),
          textWealth,
          const SizedBox(
            height: 20,
          ),
          card,
          GestureDetector(
            onTap: () {
              Navigator.pushNamed(context, '/add/card');
            },
            child: addCard,
          )
        ],
      ),
    );
  }
}
