import 'package:app/theme/colorplatte.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';

class MyIcon extends StatefulWidget {
  const MyIcon({Key? key, required this.text, required this.icon, this.onPressed}) : super(key: key);

  final String text;
  final Icon icon;
  final VoidCallback ?onPressed;

  @override
  State<MyIcon> createState() => _MyIconState();
}

class _MyIconState extends State<MyIcon> {
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: widget.onPressed,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          IconButton(onPressed: widget.onPressed, icon: widget.icon),
          const SizedBox(height: 2),
          Text(widget.text, style: MyTextStyle.small),
        ],
      ),
    );
  }
}
