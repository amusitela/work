import 'package:app/theme/colorplatte.dart';
import 'package:flutter/material.dart';

class MyButton extends StatefulWidget {
  //const MyButton({super.key,required this.buttonColor=ColorPlatte.barColor,required this.buttonHeight,required this.buttonWidth,required this.fontSize});
  const MyButton(
      {Key? key,
      this.buttonColor = ColorPlatte.barColor,
      this.fontSize = 16,
      this.buttonWidth = 112,
      this.buttonHeight = 40,
      this.text = "text",
      this.textColor=ColorPlatte.buttonTextColor,
      required this.onPressed})
      : super(key: key);
  final String text;
  final Color buttonColor;
  final Color textColor;
  final double fontSize;
  final double buttonWidth;
  final double buttonHeight;
  final VoidCallback onPressed;
  @override
  State<MyButton> createState() => _MyButtonState();
}

class _MyButtonState extends State<MyButton> {
  @override
  Widget build(BuildContext context) {
    return Container(
        width: widget.buttonWidth,
        height: widget.buttonHeight,
        decoration: BoxDecoration(
            color: widget.buttonColor,
            borderRadius: const BorderRadius.all(Radius.circular(12))),
        child: TextButton(
          onPressed: widget.onPressed,
          child: Align(
            alignment: Alignment.center, // 文本在容器中心对齐
            child: Text(
              widget.text,
              style: TextStyle(
                  color: const Color(0xFFFFFFFF), fontSize: widget.fontSize),
              textAlign: TextAlign.center,
            ),
          ),
        ));
  }
}
