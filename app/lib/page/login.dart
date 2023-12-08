import 'package:app/api/login.dart';
import 'package:app/component/myButton.dart';
import 'package:app/component/picture.dart';
import 'package:app/component/text1.dart';
import 'package:app/component/text2.dart';
import 'package:app/component/textfeild.dart';
import 'package:app/theme/textstyle.dart';
import 'package:app/utils/Mydio.dart';
import 'package:app/utils/md5.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'package:fluttertoast/fluttertoast.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _MyLoginPage();
}

class _MyLoginPage extends State<LoginPage> {
  late TextEditingController _accountController;
  late TextEditingController _passwordController;
  late String hint;
  bool isLoggingIn = false;
  @override
  void initState() {
    super.initState();
    _accountController = TextEditingController();
    _passwordController = TextEditingController();
  }

  void showLoginFailedToast() {
    // toas.showToast(
    //   msg: "登录失败，请检查用户名和密码",
    //   toastLength: Toast.LENGTH_SHORT,
    //   gravity: ToastGravity.BOTTOM,
    //   timeInSecForIosWeb: 1,
    //   backgroundColor: Colors.red,
    //   textColor: Colors.white,
    //   fontSize: 16.0,
    // );
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
          const PictureWidget(imagePath: "images/1.jpg"),
          const SizedBox(
            height: 10,
          ),
          GestureDetector(
            onTap: () {
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
          MyTextField(
            showText: true,
            controller: _accountController,
          ),
          const MyText2(text: "密码"),
          MyTextField(
            showText: false,
            controller: _passwordController,
          ),
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
          const SizedBox(
            height: 15,
          ),
          Center(
            child: MyButton(
                buttonWidth: 319,
                buttonHeight: 56,
                onPressed: () async {
                  print("点击");
                  if (isLoggingIn) {
                    return;
                  }
                  Map<String, String> data = {
                    'password': MyMD5.generateMd5(_passwordController.text),
                    'account': _accountController.text.toString()
                  };
                  try {
                    isLoggingIn = true;
                    print("点击1");
                    Response response = await LoginApi.loginApi(data);
                    debugPrint(response.toString());
                    hint = response.data['data'].toString();
                    debugPrint(hint);
                    if(hint!="null"){
                    Navigator.pushNamed(context, '/');
                    await saveToken(hint);
                    }
                    else{
                    ScaffoldMessenger.of(context)
                        .showSnackBar(SnackBar(content: Text("密码错误")));
                    }
                    

                    
                  } catch (e) {
                    debugPrint("Error: $e");

                    ScaffoldMessenger.of(context)
                        .showSnackBar(const SnackBar(content: Text("网络错误")));
                  } finally {
                    // 重置标志位，允许下一次登录
                    isLoggingIn = false;
                  }
                }),
          )
        ],
      ),
    );
  }
}

Future<void> saveToken(String token) async {
  final prefs = await SharedPreferences.getInstance();
  prefs.setString('jwt_token', token);
}
