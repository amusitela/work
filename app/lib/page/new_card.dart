import 'package:app/theme/colorplatte.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:app/theme/constant.dart';

class NewCard extends StatefulWidget {
  const NewCard({super.key});

  @override
  State<NewCard> createState() => _MyNewCardState();
}

class _MyNewCardState extends State<NewCard> {
  final List<String> props = [
    "姓氏",
    "名",
    "拼音姓氏",
    "拼音名",
    "证件号",
  ];
  @override
  Widget build(BuildContext context) {
    Widget feild = Column(
      children: List.generate(
        props.length, // 你的列表项数量
        (index) {
          return Container(
            padding: const EdgeInsets.all(10),
            decoration: const BoxDecoration(
              color: Colors.white,
              border: Border(
                top: BorderSide(width: 0.2, color: Colors.grey),
                bottom: BorderSide(width: 0.2, color: Colors.grey),
              ),
            ),
            child: Row(
              // crossAxisAlignment: CrossAxisAlignment,
              children: [
                const Text(
                  "*",
                  style: MyTextStyle.mediumLargeRed,
                ),
                Text(
                  props[index],
                  style: MyTextStyle.mediumLarge,
                ),
                const Expanded(
                  child: TextField(
                    decoration: InputDecoration(
                      hintText: '请输入',
                      border: InputBorder.none, // 去除边框
                      contentPadding: EdgeInsets.only(left: 8.0),
                    ),
                    style: MyTextStyle.mediumBlack,
                  ),
                ),
              ],
            ),
          );
        },
      ),
    );

    Widget myButton = InkWell(
        onTap: () {
          // 在这里处理按钮点击事件
          print('Button Clicked!');
        },
        child: Container(
          // padding: EdgeInsets.all(10),
          margin: const EdgeInsets.all(20),
          height: 50,
          decoration: BoxDecoration(
            gradient: const LinearGradient(
              colors: [
                Color.fromARGB(255, 222, 225, 235),
                Color.fromARGB(255, 124, 130, 154)
              ], // 渐变色
            ),
            borderRadius: BorderRadius.circular(20),
          ),
          child: const Center(
            child: Text(
              '下一步',
              style: MyTextStyle.mediumLargeBlack,
            ),
          ),
        ));
    return Scaffold(
        backgroundColor: ColorPlatte.bgColor,
        appBar: AppBar(
          backgroundColor: Colors.white,
          leading: IconButton(
            icon: const Icon(Icons.arrow_back_ios),
            color: Colors.black,
            onPressed: () {
              // 在这里添加返回操作
              Navigator.pop(context);
              // Navigator.pushNamed(context, '/');
            },
          ),
          title: const Text(
            "申请信用卡",
            style: MyTextStyle.mediumLargeBlack,
          ),
          centerTitle: true,
        ),
        body: ListView(
          children: [
            const SizedBox(
              height: 30,
            ),
            const Text(
              "  欢迎申请",
              style: MyTextStyle.large,
            ),
            Text(
              "  ${Constant.name}银行信用卡",
              style: MyTextStyle.large,
            ),
            const SizedBox(
              height: 30,
            ),
            feild,
            myButton
          ],
        ));
  }
}
