import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class MyTextField extends StatefulWidget {
  const MyTextField({Key? key,required this.showText}) : super(key: key);
  final bool showText;
  @override
  _MyTextFieldState createState() => _MyTextFieldState();
}

class _MyTextFieldState extends State<MyTextField> {

  late TextEditingController _controller;

  @override
  void initState() {
    super.initState();
    _controller = TextEditingController();
  }

  void _showErrorDialog(String errorMessage) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Error'),
          content: Text(errorMessage),
          actions: <Widget>[
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text('OK'),
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: _controller,
      obscureText: !widget.showText,
      
      decoration: InputDecoration(
        border: const OutlineInputBorder(
          borderSide: BorderSide(color: Colors.grey),
        ),
        hintText: widget.showText?"请输入用户名":"请输入密码",
      ),
      inputFormatters: [
        FilteringTextInputFormatter.allow(
          RegExp(widget.showText ? r'[0-9]' : r'[0-9a-zA-Z!@#$%^&*(),.?":{}|<>]'),
        ),
        TextInputFormatter.withFunction((oldValue, newValue) {
          // 正则校验失败时弹出提示框
          if (oldValue.text != newValue.text) {
            _showErrorDialog('Invalid input. Please check your input.');
          }
          return newValue;
        }),
      ],
    );
  }
}
