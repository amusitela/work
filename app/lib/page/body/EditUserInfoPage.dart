import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';
// 其他必要的import

class EditUserInfoPage extends StatefulWidget {
  const EditUserInfoPage({super.key});

  @override
  _EditUserInfoPageState createState() => _EditUserInfoPageState();
}

class _EditUserInfoPageState extends State<EditUserInfoPage> {
  // 初始化表单的控制器
  final TextEditingController _nicknameController = TextEditingController();
  final TextEditingController _phoneNumberController = TextEditingController();
  final TextEditingController _birthDateController = TextEditingController();
  final TextEditingController _ethnicityController = TextEditingController();

  @override
  void dispose() {
    // 释放控制器资源
    _nicknameController.dispose();
    _phoneNumberController.dispose();
    _birthDateController.dispose();
    _ethnicityController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          color: Colors.black,
          onPressed: () {
            // 在这里添加返回操作
            // Navigator.pop(context);
            Navigator.pushNamed(context, '/account');
          },
        ),
        title: const Text(
          "修改信息",
          style: MyTextStyle.mediumLargeBlack,
        ),
        centerTitle: true,
      ),
      body: ListView(
        children: <Widget>[
          const Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Row(
                children: [
                  SizedBox(
                    width: 10,
                  ),
                  Text(
                    '头像',
                    style: MyTextStyle.mediumBlack,
                  ),
                ],
              ),
              Row(
                children: [
                  CircleAvatar(
                    radius: 40,
                    backgroundImage: AssetImage("images/1.jpg"),
                  ),
                  Icon(Icons.arrow_forward_ios_outlined),
                ],
              )
            ],
          ),
          ListTile(
            title: const Text('昵称'),
            subtitle: TextField(
              controller: _nicknameController,
              decoration: const InputDecoration(
                hintText: '请输入您的昵称',
              ),
            ),
          ),
          ListTile(
            title: const Text('手机号'),
            subtitle: TextField(
              controller: _phoneNumberController,
              decoration: const InputDecoration(
                hintText: '请输入您的手机号',
              ),
              keyboardType: TextInputType.phone,
            ),
          ),
          ListTile(
            title: const Text('出生日期'),
            subtitle: GestureDetector(
              behavior: HitTestBehavior.opaque,
              onTap: () async {
                // 弹出日期选择器
                final DateTime? pickedDate = await showDatePicker(
                  context: context,
                  initialDate: DateTime.now(),
                  firstDate: DateTime(1900),
                  lastDate: DateTime.now(),
                );
                if (pickedDate != null) {
                  setState(() {
                    _birthDateController.text =
                        pickedDate.toLocal().toString().split(' ')[0];
                  });
                }
              },
              child: AbsorbPointer(
                child: TextField(
                  controller: _birthDateController,
                  decoration: const InputDecoration(
                    hintText: '请选择日期',
                  ),
                ),
              ),
            ),
          ),
          ListTile(
            title: const Text('民族'),
            subtitle: TextField(
              controller: _ethnicityController,
              decoration: const InputDecoration(
                hintText: '请输入您的民族',
              ),
            ),
          ),
          ElevatedButton(
            onPressed: () {
              final userInfo = UserInfo(
                nickname: _nicknameController.text,
                phoneNumber: _phoneNumberController.text,
                birthDate: _birthDateController.text,
                ethnicity: _ethnicityController.text,
              );

              // 使用 Navigator.pop 回传 UserInfo 对象
              Navigator.pop(context, userInfo); // TODO: 在这里添加更新用户信息的逻辑
            },
            child: const Text('保存'),
          ),
        ],
      ),
    );
  }
}

class UserInfo {
  final String nickname;
  final String phoneNumber;
  final String birthDate;
  final String ethnicity;

  UserInfo({
    required this.nickname,
    required this.phoneNumber,
    required this.birthDate,
    required this.ethnicity,
  });
}
