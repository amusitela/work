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
  late TextEditingController _phoneController;
  late TextEditingController _passwordController;
  late TextEditingController _yzmController;
  @override
  void initState() {
    super.initState();
    _phoneController = TextEditingController();
    _passwordController = TextEditingController();
    _yzmController = TextEditingController();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
        padding: const EdgeInsets.only(left: 10),
        children: [
          const SizedBox(
            height: 20,
          ),
          const Align(
            alignment: Alignment.centerLeft,
            child: PictureWidget(imagePath: "images/registry.png"),
          ),
          // Image.asset("images/registry.png"),
          const SizedBox(
            height: 10,
          ),
          const MyText1(text: "注册"),
          const MyText2(text: "向我们提供您的一些信息"),
          const SizedBox(
            height: 30,
          ),
          const MyText2(text: "手机号"),
          MyTextField(
            showText: true,
            controller: _phoneController,
          ),
          const MyText2(text: "密码"),
          MyTextField(
            showText: false,
            controller: _passwordController,
          ),
          const MyText2(text: "验证码"),
          MyTextField(
            showText: true,
            controller: _yzmController,
          ),
          const SizedBox(
            height: 15,
          ),
          GestureDetector(
            onTap: () {
              Navigator.pushNamed(context, "/login");
            },
            child: const Text(
              "去登录",
              style: TextStyle(
                  color: Colors.blue,
                  fontSize: 15,
                  fontWeight: FontWeight.w500, // 设置字体加粗
                  letterSpacing: 0.15, // 设置字间距
                  fontFamily: "Dosis",
                  height: 1.5),
              selectionColor: Colors.blue,
            ),
          ),
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
