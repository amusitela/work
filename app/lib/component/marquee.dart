import 'dart:async';
import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MarqueeWidget extends StatefulWidget {
  final List<String> textList;
  final int loopSeconds;

  const MarqueeWidget({
    Key? key,
    required this.textList,
    this.loopSeconds = 2,
  }) : super(key: key);

  @override
  _MarqueeWidgetState createState() => _MarqueeWidgetState();
}

class _MarqueeWidgetState extends State<MarqueeWidget> {
  late PageController _controller;
  int _currentPage = 0;
  late Timer _timer;

  @override
  void initState() {
    super.initState();
    _controller = PageController(viewportFraction: 1);

    // 在textList的末尾添加一个与开头相同的元素
    widget.textList.add(widget.textList.first);

    // Start the auto-scroll timer
    if (widget.textList.isNotEmpty) {
      _timer = Timer.periodic(Duration(seconds: widget.loopSeconds), (timer) {
        int nextPage = _currentPage + 1;
        if (nextPage >= widget.textList.length) {
          nextPage = 0;
        }
        _controller.animateToPage(
          nextPage,
          duration: const Duration(milliseconds: 300),
          curve: Curves.easeIn,
        );
        if (nextPage == 0) {
          _controller.jumpToPage(nextPage);
        }
        if (nextPage >= widget.textList.length) {
          nextPage = 0;
        }
        
      });
    }
  }

  @override
  void dispose() {
    _timer.cancel();
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 50, // This can be adjusted to fit the single item height
      
      child: PageView.builder(
        itemCount: widget.textList.length,
        controller: _controller,
        scrollDirection: Axis.vertical,
        itemBuilder: (context, index) {
          return Center(
            child: Container(
              width: Constant.cardWidth,
              constraints: BoxConstraints.expand(height: null),
              // height: 200,
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  const SizedBox(
                    width: 40,
                  ),
                  Expanded(
                    child: Container(
                    
                    // height: 200,
                      child: Text(
                        widget.textList[index],
                        style: MyTextStyle.medium,
                      ),
                    ),
                  ),
                  const Icon(Icons.arrow_forward_ios_sharp)
                ],
              ),
            ),
          );
        },
        onPageChanged: (value) {
          setState(() {
            _currentPage = value;
          });
        },
      ),
    );
  }
}
