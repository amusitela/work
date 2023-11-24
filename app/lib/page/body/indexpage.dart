import 'package:app/component/marquee.dart';
import 'package:app/component/myicon.dart';
import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';

class FirstPage extends StatelessWidget {
  FirstPage({super.key});
  final List<String> loopList = [
    "如果 Text 小部件的文本内容在显示时没有完全可见，可以尝试以下几个方法来调整布局和文本的显示：123",
    "如果 Text 小部件的文本内容在显示时没有完全可见，可以尝试以下几个方法来调整布局和文本的显示：1234",
    "如果 Text 小部件的文本内容在显示时没有完全可见，可以尝试以下几个方法来调整布局和文本的显示：1235",
  ];

  Widget card = Center(
    child: Container(
        width: Constant.cardWidth,
        padding: EdgeInsets.all(20),
         decoration: BoxDecoration(
              border: Border.all(
                color: Color(0xfffeedde), // 边框颜色
                width: 5.0, // 边框宽度
              ),
              borderRadius: BorderRadius.all(Radius.circular(8.0))
            ),
        child: const Column(
          children: [
            Column(children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Expanded(
                      child: Text(
                    "3.12%",
                    style: TextStyle(
                        fontSize: 33,
                        fontWeight: FontWeight.bold, // 设置字体加粗
                        letterSpacing: 0.33, // 设置字间距
                        fontFamily: "Dosis",
                        color: Colors.red,
                        height: 1),
                  )),
                  Expanded(
                      child: Text(
                    "富国天利",
                    style: MyTextStyle.mediumLarge,
                    overflow: TextOverflow.ellipsis,
                    maxLines: 1,
                  ))
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Expanded(
                    child: Text("年增幅", style: MyTextStyle.medium),
                  ),
                  Expanded(
                      child: Text(
                    "说明咩咩咩咩咩咩咩咩咩咩咩咩",
                    style: MyTextStyle.medium,
                    overflow: TextOverflow.ellipsis,
                    maxLines: 1,
                  ))
                ],
              )
            ]),
            Column(children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Expanded(
                      child: Text(
                    "3.12%",
                    style: TextStyle(
                        fontSize: 33,
                        fontWeight: FontWeight.bold, // 设置字体加粗
                        letterSpacing: 0.33, // 设置字间距
                        fontFamily: "Dosis",
                        color: Colors.red,
                        height: 1),
                  )),
                  Expanded(
                      child: Text(
                    "富国天利",
                    style: MyTextStyle.mediumLarge,
                    overflow: TextOverflow.ellipsis,
                    maxLines: 1,
                  ))
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Expanded(
                    child: Text("年增幅", style: MyTextStyle.medium),
                  ),
                  Expanded(
                      child: Text(
                    "说明咩咩咩咩咩咩咩咩咩咩咩咩",
                    style: MyTextStyle.medium,
                    overflow: TextOverflow.ellipsis,
                    maxLines: 1,
                  ))
                ],
              )
            ]),
            Column(children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Expanded(
                      child: Text(
                    "3.12%",
                    style: TextStyle(
                        fontSize: 33,
                        fontWeight: FontWeight.bold, // 设置字体加粗
                        letterSpacing: 0.33, // 设置字间距
                        fontFamily: "Dosis",
                        color: Colors.red,
                        height: 1),
                  )),
                  Expanded(
                      child: Text(
                    "富国天利",
                    style: MyTextStyle.mediumLarge,
                    overflow: TextOverflow.ellipsis,
                    maxLines: 1,
                  ))
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Expanded(
                    child: Text("年增幅", style: MyTextStyle.medium),
                  ),
                  Expanded(
                      child: Text(
                    "说明咩咩咩咩咩咩咩咩咩咩咩咩",
                    style: MyTextStyle.medium,
                    overflow: TextOverflow.ellipsis,
                    maxLines: 1,
                  ))
                ],
              )
            ]),
          ],
        )),
  );

  @override
  Widget build(BuildContext context) {
    return ListView(
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
          height: 10,
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
    );
  }
}
