import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';

class TextAndField extends StatelessWidget {
  const TextAndField(
      {super.key,
      required this.icon,
      required this.text1,
      required this.text2});
  final Icon icon;
  final String text1;
  final String text2;

  @override
  Widget build(BuildContext context) {
    return Container(
        width: Constant.cardWidth,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(8.0),
          color: Colors.grey[200],
        ),
        padding: const EdgeInsets.symmetric(horizontal: 16.0),
        child: Row(
          children: [
            Text(
              text1,
              style: MyTextStyle.mediumLarge,
            ),
            Expanded(
              child: TextField(
                decoration: InputDecoration(
                  hintText: text2,
                  border: InputBorder.none, // 去除边框
                  contentPadding: const EdgeInsets.only(left: 8.0),
                ),
              ),
            ),
            icon,
          ],
        ));
  }
}
