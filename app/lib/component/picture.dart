import 'package:flutter/material.dart';

class PictureWidget extends StatelessWidget {
  const PictureWidget({super.key,required this.imagePath});
  final String imagePath;

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Image.asset(
      this.imagePath,
      filterQuality: FilterQuality.none,
    ));
  }
}