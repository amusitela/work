import 'package:app/component/myButton.dart';
import 'package:app/component/myicon.dart';
import 'package:app/theme/colorplatte.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';

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
    Widget avatar = Row(
      crossAxisAlignment: CrossAxisAlignment.center,
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
        )
      ],
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
              colors: [Colors.white, ColorPlatte.bottomBarColor], // 渐变色数组
              stops: [0.0, 1.0], // 渐变色的起始和结束位置
            ),
          ),
          child: const Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              MyIcon(text: "我的账户", icon: Icon(Icons.ac_unit)),
              MyIcon(text: "我的账户", icon: Icon(Icons.ac_unit)),
              MyIcon(text: "我的账户", icon: Icon(Icons.ac_unit)),
              MyIcon(text: "我的账户", icon: Icon(Icons.ac_unit)),
            ],
          )),
    );
    Widget card = Align(
      alignment: Alignment.center,
      child: Container(
        width: 350,
        height: 100,
        decoration: BoxDecoration(
        borderRadius: BorderRadius.all(
              Radius.circular(8),
            ),
          image: DecorationImage(
            image: const AssetImage('images/1.jpg'), // 替换为你的图片路径
            fit: BoxFit.cover,
            colorFilter: ColorFilter.mode(
              Colors.black.withOpacity(0.5), // 设置图片透明度
              BlendMode.dstATop, // 使用dstATop模式
            ),
          ),
        ),
        padding: const EdgeInsets.all(10),
        child: Column(children: [
          const Row(
            children: [
              Text(
                '资产',
                style: MyTextStyle.medium,
              ),
              SizedBox(
                width: 5,
              ),
              Icon(Icons.arrow_forward_ios)
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
    );
    return Scaffold(
      body: ListView(
        children: [
          avatar,
          const SizedBox(
            height: 30,
          ),
          
          buttonSection,
          const SizedBox(
            height: 20,
          ),
          const Row(
          children: [
          SizedBox(width: 20,)
          ,Text("我的资产",style: MyTextStyle.mediumLarge,),
          ],
          ),
          const SizedBox(height: 10,),
          card
        ],
      ),
    );
  }
}
