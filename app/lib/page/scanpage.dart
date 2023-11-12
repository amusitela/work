
import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter_scankit/flutter_scankit.dart';

const boxSize = 200.0;

class ScanPage extends StatefulWidget {
  const ScanPage({Key? key}) : super(key: key);
  @override
  State<ScanPage> createState() => _CustomViewState();
}

class _CustomViewState extends State<ScanPage> {
  ScanKitController _controller = ScanKitController();

  @override
  void initState() {
    _controller.onResult.listen((result) {
     debugPrint('-------------------------');
      debugPrint(
              "scanning result:value=${result.originalValue} scanType=${result.scanType}");

      /// Note: Here the pop operation must be delayed.
      Future(() {
        Navigator.of(context).pop(result.originalValue);
      });
    });
    super.initState();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    var screenWidth = MediaQuery.of(context).size.width;
    var screenHeight = MediaQuery.of(context).size.height;
    var left = screenWidth / 2 - boxSize / 2;
    var top = screenHeight / 2 - boxSize / 2;
    var rect = Rect.fromLTWH(left, top, boxSize, boxSize);

    return Scaffold(
      body: SafeArea(
        child: Stack(
          children: [
            ScanKitWidget(
                    controller: _controller,
                    continuouslyScan: false,
                    boundingBox: rect),
            Align(
              alignment: Alignment.topCenter,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  IconButton(
                          onPressed: () {
                            Navigator.of(context).pop();
                          },
                          icon: Icon(
                            Icons.arrow_back,
                            color: Colors.white,
                            size: 28,
                          )),
                  IconButton(
                          onPressed: () {
                            _controller.switchLight();
                          },
                          icon: Icon(
                            Icons.lightbulb_outline_rounded,
                            color: Colors.white,
                            size: 28,
                          )),
                  IconButton(
                          onPressed: () {
                            _controller.pickPhoto();
                          },
                          icon: Icon(
                            Icons.picture_in_picture_rounded,
                            color: Colors.white,
                            size: 28,
                          ))
                ],
              ),
            ),
            Align(
              alignment: Alignment.center,
              child: Container(
                width: boxSize,
                height: boxSize,
                decoration: BoxDecoration(
                  border: Border(
                          left: BorderSide(color: Colors.black, width: 2),
                          right: BorderSide(color: Colors.black, width: 2),
                          top: BorderSide(color: Colors.black, width: 2),
                          bottom: BorderSide(color: Colors.black, width: 2)),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}