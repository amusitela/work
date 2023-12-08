import 'package:app/component/marquee.dart';
import 'package:app/component/myicon.dart';
import 'package:app/test/test2.dart';
import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class FirstPage extends StatefulWidget {
  const FirstPage({super.key});

  @override
  State<FirstPage> createState() => _MyFirstPageState();
}

class _MyFirstPageState extends State<FirstPage> {
  final List<Map<String, dynamic>> fundData = Test.fundData;

  late bool isLoggedIn = false;
  final List<Map<String, dynamic>> loopList = Test.artcleData;
  @override
  void initState() {
    // TODO: implement initState
    checkLoggedInStatus();
    print("首页");
  }

  Future<void> checkLoggedInStatus() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? jwt = prefs.getString(
        'jwt_token'); // Assuming 'jwt' is the key for your JWT in SharedPreferences

    setState(() {
      isLoggedIn = jwt != null;
    });
  }

  Future<void> setJwtToNull() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();

    // Remove 'jwt' key from SharedPreferences
    await prefs.remove('jwt_token');
  }

  @override
  Widget build(BuildContext context) {
    Widget card = Center(
      child: Container(
        width: Constant.cardWidth,
        padding: const EdgeInsets.all(20),
        decoration: BoxDecoration(
          border: Border.all(
            color: const Color(0xfffeedde),
            width: 5.0,
          ),
          borderRadius: const BorderRadius.all(Radius.circular(8.0)),
        ),
        child: Column(
          children: List.generate(
              3,
              (index) => GestureDetector(
                    onTap: () {
                      Navigator.pushNamed(context, "/fund/detail",
                          arguments: fundData[index]["fundId"]);
                    },
                    child: Column(
                      children: [
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            Expanded(
                              child: Text(
                                fundData[index]["rate"],
                                style: const TextStyle(
                                  fontSize: 33,
                                  fontWeight: FontWeight.bold,
                                  letterSpacing: 0.33,
                                  fontFamily: "Dosis",
                                  color: Colors.red,
                                  height: 1,
                                ),
                              ),
                            ),
                            Expanded(
                              child: Text(
                                fundData[index]["name"],
                                style: MyTextStyle.mediumLarge,
                                overflow: TextOverflow.ellipsis,
                                maxLines: 1,
                              ),
                            ),
                          ],
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            const Expanded(
                              child: Text("年增幅", style: MyTextStyle.medium),
                            ),
                            Expanded(
                              child: Text(
                                fundData[index]["description"],
                                style: MyTextStyle.medium,
                                overflow: TextOverflow.ellipsis,
                                maxLines: 1,
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  )),
        ),
      ),
    );

    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        title: const Text(
          '首页',
          style: MyTextStyle.small,
        ),
        automaticallyImplyLeading: false,
        backgroundColor: Colors.white,
        actions: [
          isLoggedIn
              ? IconButton(
                  icon: const Icon(Icons.exit_to_app, color: Colors.black),
                  onPressed: () {
                    setJwtToNull();
                    setState(() {
                      isLoggedIn = false;
                    });
                  },
                )
              : TextButton(
                  onPressed: () {
                    Navigator.pushNamed(context, '/login');
                  },
                  child: const Text(
                    '登录',
                    style: MyTextStyle.mediumBlack, // 根据需要自定义样式
                  ),
                ),
        ],
      ),
      body: ListView(
        // shrinkWrap: true,
        children: [
          GestureDetector(
            onTap: () {},
            child: Image.asset(
              'images/postcard.jpg', // 设置图片宽度与屏幕同宽
              fit: BoxFit.fitWidth, // BoxFit.fitWidth 会让图片宽度等于给定宽度，高度自适应
            ),
          ),
          const SizedBox(
            height: 10,
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              MyIcon(
                text: '扫一扫',
                icon: const Icon(Icons.qr_code),
                onPressed: () {
                  Navigator.pushNamed(context, '/qrcode');
                },
              ),
              MyIcon(
                text: '外汇',
                icon: const Icon(Icons.monetization_on_outlined),
                onPressed: () {
                  Navigator.pushNamed(context, '/forex');
                },
              ),
              MyIcon(
                text: '转账',
                icon: const Icon(Icons.currency_exchange),
                onPressed: () {
                  Navigator.pushNamed(context, '/transfer');
                },
              ),
              MyIcon(
                text: '账户',
                icon: const Icon(Icons.account_box),
                onPressed: () {
                  Navigator.pushNamed(context, "/account");
                },
              ),
              //  SizedBox(height: 10,),
              // const Marquee(),
            ],
          ),
          const SizedBox(
            height: 25,
          ),
          MarqueeWidget(
            textList: loopList,
            //循环的提示消息数量
          ),
          const SizedBox(
            height: 20,
          ),
          card
        ],
      ),
    );
  }
}
