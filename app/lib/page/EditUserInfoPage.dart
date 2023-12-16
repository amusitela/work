import 'dart:io';

import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';

import 'package:image_picker/image_picker.dart';

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

  File? _imageFile;

  Future _getImage() async {
    final imagePicker = ImagePicker();
    final pickedFile = await imagePicker.pickImage(source: ImageSource.gallery);

    if (pickedFile != null) {
      setState(() {
        _imageFile = File(pickedFile.path);
      });
    }
  }

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
            Navigator.pop(context);
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
          const SizedBox(
            height: 15,
          ),
          ListTile(
            leading: const Text(
              '头像',
              style: MyTextStyle.mediumLargeBlack,
            ),
            trailing: InkWell(
              onTap: _getImage,
              child: Row(
                mainAxisSize: MainAxisSize.min,
                children: [
                  CircleAvatar(
                    radius: 40,
                    backgroundImage: _imageFile != null
                        ? FileImage(_imageFile!) as ImageProvider
                        : const AssetImage('images/1.jpg'),
                  ),
                  const Icon(Icons.arrow_forward_ios_outlined),
                ],
              ),
            ),
          ),
          const SizedBox(
            height: 10,
          ),
          ListTile(
            title: const Text(
              '昵称',
              style: MyTextStyle.mediumLargeBlack,
            ),
            subtitle: TextField(
              controller: _nicknameController,
              decoration: const InputDecoration(
                hintText: '请输入您的昵称',
              ),
              style: MyTextStyle.mediumBlack,
            ),
          ),
          ListTile(
            title: const Text(
              '手机号',
              style: MyTextStyle.mediumLargeBlack,
            ),
            subtitle: TextField(
              controller: _phoneNumberController,
              decoration: const InputDecoration(
                hintText: '请输入您的手机号',
              ),
              keyboardType: TextInputType.phone,
              style: MyTextStyle.mediumBlack,
            ),
          ),
          ListTile(
            title: const Text(
              '出生日期',
              style: MyTextStyle.mediumLargeBlack,
            ),
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
                  style: MyTextStyle.mediumBlack,
                ),
              ),
            ),
          ),
          ListTile(
            title: const Text(
              '民族',
              style: MyTextStyle.mediumLargeBlack,
            ),
            subtitle: TextField(
              controller: _ethnicityController,
              decoration: const InputDecoration(
                hintText: '请输入您的民族',
              ),
              style: MyTextStyle.mediumBlack,
            ),
          ),
          const SizedBox(
            height: 20,
          ),
          Align(
            alignment: Alignment.center,
            child: Container(
              width: Constant.cardWidth,
              padding: const EdgeInsets.all(20),
              child: ElevatedButton(
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
            ),
          )
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
