import 'package:app/component/myButton.dart';
import 'package:app/component/picture.dart';
import 'package:app/component/text1.dart';
import 'package:app/component/text2.dart';
import 'package:app/component/textfeild.dart';
import 'package:flutter/material.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({super.key});

  @override
  State<RegisterPage> createState() => _MyRegisterPage();
}

class _MyRegisterPage extends State<RegisterPage> {
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
          const MyText1(text: "注册"),
          const MyText2(
              text:
                  "Sign In with your data  that you entered during your registration."),
          const SizedBox(
            height: 50,
          ),
          const MyText2(text: "手机号"),
          const MyTextField(showText: true),
          const MyText2(text: "密码"),
          const MyTextField(showText: false),
          const MyText2(text: "验证码"),
          const MyTextField(showText: true),
          const SizedBox(
            height: 30,
          ),
          Center(
            child: MyButton(
                buttonWidth: 319,
                buttonHeight: 56,
                onPressed: () {
                  Navigator.pushNamed(context, '/login');
                }),
          )
        ],
      ),
    );
  }
}
