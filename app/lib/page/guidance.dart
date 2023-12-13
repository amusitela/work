import 'package:app/theme/colorplatte.dart';
import 'package:flutter/material.dart';

class Guidance extends StatefulWidget {
  final String imagePath;
  final String text1;
  final String text2;
  final int order;
  const Guidance(
      {super.key,
      required this.imagePath,
      required this.text1,
      required this.text2,
      required this.order});

  @override
  State<Guidance> createState() => _MyGuidance();
}

class _MyGuidance extends State<Guidance> {
  @override
  Widget build(BuildContext context) {
    Widget picture = Center(
        child: Image.asset(
      widget.imagePath,
      filterQuality: FilterQuality.none,
    ));
    Widget textSection1 = Center(
      child: SizedBox(
        width: 200,
        height: 100,
        child: Text(
          widget.text1,
          style: const TextStyle(
              fontSize: 33,
              fontWeight: FontWeight.bold, // 设置字体加粗
              letterSpacing: 0.33, // 设置字间距
              fontFamily: "Dosis",
              height: 1),
          textAlign: TextAlign.center,
        ),
      ),
    );
    Widget textSection2 = Center(
        child: SizedBox(
      width: 270,
      height: 45,
      child: Text(
        widget.text2,
        style: const TextStyle(
            fontSize: 15,
            fontWeight: FontWeight.w500, // 设置字体加粗
            letterSpacing: 0.15, // 设置字间距
            fontFamily: "Dosis",
            height: 1.5),
        textAlign: TextAlign.center,
      ),
    ));
    Widget recList = Center(
        child: Row(mainAxisAlignment: MainAxisAlignment.center, children: [
      Container(
        margin: const EdgeInsets.all(10),
        width: 20,
        height: 4,
        decoration: BoxDecoration(
          color: (widget.order == 1)
              ? ColorPlatte.barColor
              : ColorPlatte.defaultColor,
          borderRadius: const BorderRadius.all(
            Radius.circular(2),
          ),
        ),
      ),
      Container(
        width: 20,
        height: 4,
        decoration: BoxDecoration(
          color: (widget.order == 2)
              ? ColorPlatte.barColor
              : ColorPlatte.defaultColor,
          borderRadius: const BorderRadius.all(
            Radius.circular(2),
          ),
        ),
      ),
      Container(
        width: 20,
        height: 4,
        margin: const EdgeInsets.all(10),
        decoration: BoxDecoration(
          color: (widget.order == 3)
              ? ColorPlatte.barColor
              : ColorPlatte.defaultColor,
          borderRadius: const BorderRadius.all(
            Radius.circular(2),
          ),
        ),
      ),
    ]));

    Widget buttonSection = Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          TextButton(
              onPressed: () {
                print("跳过");
                Navigator.pushNamed(context, '/login');
              },
              child: const Text(
                "跳过",
                style: TextStyle(color: Color(0xFF3D1010), fontSize: 14),
                textAlign: TextAlign.center,
              )),
          TextButton(
            onPressed: () {
              print("继续");
              if (widget.order == 1) {
                Navigator.pushNamed(context, '/g1');
              }
              if (widget.order == 2) {
                Navigator.pushNamed(context, '/g2');
              }
              if (widget.order == 3) {
                Navigator.pushNamed(context, '/login');
              }
            },
            child: Container(
              width: 112,
              height: 44,
              decoration: const BoxDecoration(
                  color: ColorPlatte.barColor,
                  borderRadius: BorderRadius.all(Radius.circular(12))),
              child: const Align(
                alignment: Alignment.center, // 文本在容器中心对齐
                child: Text(
                  "继续",
                  style: TextStyle(color: Color(0xFFFFFFFF), fontSize: 14),
                  textAlign: TextAlign.center,
                ),
              ),
            ),
          ),
        ]);
    return Scaffold(
      body: Stack(
        children: [
          Positioned(
            top: 0,
            child: SizedBox(
              width: MediaQuery.of(context).size.width, // 使用MediaQuery获取屏幕宽度
              child: Column(
                children: [
                  const SizedBox(height: 230),
                  picture,
                  const SizedBox(
                    height: 75,
                  ),
                  textSection1,
                  const SizedBox(
                    height: 26,
                  ),
                  textSection2,
                  const SizedBox(
                    height: 10,
                  ),
                  Hero(
                    tag: 'tag',
                    child: recList,
                  )
                  // Align(
                  //   alignment: Alignment.bottomLeft,
                  //   child: buttonSection,
                  // ),
                ],
              ),
            ),
          ),
          Positioned(
            bottom: 20,
            child: SizedBox(
              width: MediaQuery.of(context).size.width, // 使用MediaQuery获取屏幕宽度
              child: buttonSection,
            ),
          )
        ],
      ),
    );
  }
}
