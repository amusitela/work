import 'package:app/component/keyboard.dart' as keyboard;
import 'package:app/component/password.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

/// 支付密码  +  自定义键盘

class main_keyboard extends StatefulWidget {
  static const String sName = "enter";

  const main_keyboard({super.key});

  @override
  State<StatefulWidget> createState() {
    return keyboardState();
  }
}

class keyboardState extends State<main_keyboard> {
  /// 用户输入的密码
  String pwdData = '';

  /*
    GlobalKey：整个应用程序中唯一的键
    ScaffoldState：Scaffold框架的状态
    解释：_scaffoldKey的值是Scaffold框架状态的唯一键
   */
  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();

  // VoidCallback：没有参数并且不返回数据的回调
  late VoidCallback? _showBottomSheetCallback;

  @override
  void initState() {
    _showBottomSheetCallback = _showBottomSheet;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      body: _buildContent(context),
    );
  }

  Widget _buildContent(BuildContext c) {
    return Container(
      width: double.maxFinite,
      height: 300.0,
      color: const Color(0xffffffff),
      child: Column(
        children: <Widget>[
          const Padding(
            padding: EdgeInsets.only(top: 50.0),
            child: Text(
              '请在此输入新支付密码',
              style: TextStyle(fontSize: 18.0, color: Color(0xff333333)),
            ),
          ),

          ///密码框
          Padding(
            padding: const EdgeInsets.only(top: 15.0),
            child: _buildPwd(pwdData),
          ),
        ],
      ),
    );
  }

  /// 密码键盘 确认按钮 事件
  void onAffirmButton() {
  // Fluttertoast.showToast(msg: "输入密码$pwdData", gravity: ToastGravity.CENTER);
  // debugPrint("111");
  if(pwdData=="123456"){
  Navigator.pushReplacementNamed(context, '/pay/success');
  }
  else Navigator.pushReplacementNamed(context, '/pay/fail');
  }

  /// 密码键盘的整体回调，根据不同的按钮事件来进行相应的逻辑实现
  void _onKeyDown(keyboard.KeyEvent data) {
// 如果点击了删除按钮，则将密码进行修改
    if (data.isDelete()) {
      if (pwdData.isNotEmpty) {
        pwdData = pwdData.substring(0, pwdData.length - 1);
        setState(() {});
      }
    }
// 点击了确定按钮时
    else if (data.isCommit()) {
      if (pwdData.length != 6) {
      //  Fluttertoast.showToast(msg: "密码不足6位，请重试", gravity: ToastGravity.CENTER);
      debugPrint("222");
        return;
      }
      onAffirmButton();
    }
//点击了数字按钮时  将密码进行完整的拼接
    else {
      if (pwdData.length < 6) {
        pwdData += data.key;
      }
      setState(() {});
    }
  }

  /// 底部弹出 自定义键盘  下滑消失
  void _showBottomSheet() {
    setState(() {
      // disable the button  // 禁用按钮
      _showBottomSheetCallback = null;
    });

    /*
      currentState：获取具有此全局键的树中的控件状态
      showBottomSheet：显示持久性的质感设计底部面板
      解释：联系上文，_scaffoldKey是Scaffold框架状态的唯一键，因此代码大意为，
           在Scaffold框架中显示持久性的质感设计底部面板
     */
    if (_scaffoldKey.currentState != null) {
  _scaffoldKey.currentState!
      .showBottomSheet<void>((BuildContext context) {
        // 将自定义的密码键盘作为其 child 这里将回调函数传入
        return keyboard.MyKeyboard(_onKeyDown);
      })
      .closed
      .whenComplete(() {
        if (mounted) {
          setState(() {
            // re-enable the button  // 重新启用按钮
            _showBottomSheetCallback = _showBottomSheet;
          });
        }
      });
}
  }

  /// 构建 密码输入框  定义了其宽度和高度
  Widget _buildPwd(var pwd) {
    return GestureDetector(
      child: SizedBox(
        width: 250.0,
        height: 40.0,
//      color: Colors.white,  自定义密码输入框的使用
        child: CustomJPasswordField(pwd),
      ),
// 用户点击输入框的时候，弹出自定义的键盘
      onTap: () {
        if (_showBottomSheetCallback != null) {
          _showBottomSheetCallback!();
        }
      },
    );
  }
}
