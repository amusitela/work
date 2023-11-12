import 'package:app/component/myButton.dart';
import 'package:app/component/picture.dart';
import 'package:app/component/text1.dart';
import 'package:app/component/text2.dart';
import 'package:app/component/textfeild.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _MyLoginPage();
}

class _MyLoginPage extends State<LoginPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
        padding: const EdgeInsets.only(left: 10),
        children: [
          const SizedBox(
            height: 20,
          ),
          const PictureWidget(imagePath: "images/1.jpg"),
          const SizedBox(
            height: 10,
          ),
          GestureDetector(
          onTap: (){
          Navigator.pushNamed(context, "/g");
          },
          child: const MyText1(text: "登录"),
          ),
          const MyText2(
              text:
                  "Sign In with your data  that you entered during your registration."),
          const SizedBox(
            height: 50,
          ),
          const MyText2(text: "用户名"),
          const MyTextField(showText: true),
          const MyText2(text: "密码"),
          const MyTextField(showText: false),
          const SizedBox(
            height: 20,
          ),
          Row(
            children: [
              // const SizedBox(
              //   width: 10,
              // ),
              const Text(
                "没有账号?",
                style: MyTextStyle.medium,
              ),
              const SizedBox(
                width: 4,
              ),
              GestureDetector(
                onTap: () {
                  Navigator.pushNamed(context, "/register");
                },
                child: const Text(
                  "去注册",
                  style: TextStyle(
                      color: Colors.blue,
                      fontSize: 15,
                      fontWeight: FontWeight.w500, // 设置字体加粗
                      letterSpacing: 0.15, // 设置字间距
                      fontFamily: "Dosis",
                      height: 1.5),
                  selectionColor: Colors.blue,
                ),
              )
            ],
          ),
          const SizedBox(height: 15,),
          Center(
            child: MyButton(
                buttonWidth: 319,
                buttonHeight: 56,
                onPressed: () {
                  Navigator.pushNamed(context, '/');
                }),
          )
        ],
      ),
    );
  }
}
