import 'package:app/component/picture.dart';
import 'package:app/component/text1.dart';
import 'package:app/component/text2.dart';
import 'package:app/component/textfeild.dart';
import 'package:flutter/material.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _MyLoginPage();
}

class _MyLoginPage extends State<LoginPage> {
  @override
  Widget build(BuildContext context) {

    return  ListView(
    padding: const EdgeInsets.only(left: 10),
    children: const [
    SizedBox(height: 20,),
    PictureWidget(imagePath: "images/1.jpg"),
    SizedBox(height: 10,),
    MyText1(text: "登录"),
    MyText2(text: "Sign In with your data  that you entered during your registration."),
    SizedBox(height: 60,),
    MyText2(text: "用户名"),
    MyTextField(showText: true),
    MyText2(text: "密码"),
    MyTextField(showText: false),


    ],); 
  }
}