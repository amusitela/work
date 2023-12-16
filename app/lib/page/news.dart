import 'dart:async';

import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';
import 'package:webview_flutter/webview_flutter.dart';
import 'package:webview_flutter_android/webview_flutter_android.dart';
import 'package:webview_flutter_wkwebview/webview_flutter_wkwebview.dart';

class NewsDetailPage extends StatefulWidget {
  const NewsDetailPage({super.key, required this.articleId});
  final String articleId;
  @override
  _NewsDetailPageState createState() => _NewsDetailPageState();
}

class _NewsDetailPageState extends State<NewsDetailPage> {
  String title = "30个能量满满的句子，一起来充电！";
  String read = "200";
  String source = "官方资讯";
  String praise = "155";
  String time = "2023-11-24 14:35";
  bool ifpraise = false;
  String htmlContent = '''
      <html>
      <head>
        <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
      </head>
      <style>
    /* 设置图片宽度为100%，高度自动调整以保持图片比例 */
    img {
      width: 100%;
      height: auto;
    }

    body::-webkit-scrollbar {
    display: none;
  }
  </style>
      <body>
        <p>Your HTML content here</p>
        <h1>示例标题</h1>
    <p>这是一个段落。</p>
     <img src="https://pic.289685.xyz/picture/v2-746ea74fc135b29f67f2d4fbf3e1c133_1440w.jpg" />
    <p>Your HTML content here</p>
        <h1>示例标题</h1>
    <p>这是一个段落。</p>
    <p>Your HTML content here</p>
        <h1>示例标题</h1>
    <p>这是一个段落。</p>
    <p>Your HTML content here</p>
        <h1>示例标题</h1>
    <p>这是一个段落。</p>
    <p>Your HTML content here</p>
        <h1>示例标题</h1>
    <p>这是一个段落。</p>
    <p>Your HTML content here</p>
        <h1>示例标题</h1>
    <p>这是一个段落。</p>
      </body>
      </html>
    ''';

  late WebViewController mycontroller;
  @override
  void initState() {
    // 2
    mycontroller = WebViewController()
      ..setJavaScriptMode(JavaScriptMode.unrestricted);
    // ..loadRequest(Uri.parse('https://juejin.cn'));
    mycontroller.loadHtmlString(htmlContent);
    super.initState();
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
            Navigator.pop(context);
          },
        ),
        title: Text(
          "新闻详情${widget.articleId}",
          style: MyTextStyle.mediumLargeBlack,
        ),
        centerTitle: true,
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Column(
            children: [
              Container(
                padding: const EdgeInsets.all(10),
                alignment: Alignment.centerLeft,
                child: Text(
                  source,
                  // style: MyTextStyle.mediumLargeBlack,
                ),
              ),
              const Divider(),
              Text(
                title,
                style: MyTextStyle.small,
                textAlign: TextAlign.left,
              ),
              const SizedBox(
                height: 10,
              ),
              Row(
                // mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  const SizedBox(
                    width: 10,
                  ),
                  Expanded(
                    child: Text(
                      time,
                      style: MyTextStyle.mediumBlack,
                    ),
                  ),
                  Row(
                    children: [
                      const Icon(
                        Icons.remove_red_eye_outlined,
                        color: Colors.grey,
                      ),
                      const SizedBox(
                        width: 5,
                      ),
                      Text(
                        read,
                        style: MyTextStyle.mediumBlack,
                      ),
                      const SizedBox(
                        width: 5,
                      ),
                      GestureDetector(
                        child: Icon(
                          Icons.thumb_up_alt_outlined,
                          color: ifpraise ? Colors.blue : Colors.grey,
                        ),
                        onTap: () {
                          setState(() {
                            ifpraise = !ifpraise;
                          });
                        },
                      ),
                      const SizedBox(
                        width: 5,
                      ),
                      Text(
                        praise,
                        style: MyTextStyle.mediumBlack,
                      )
                    ],
                  ),
                  const SizedBox(
                    width: 10,
                  ),
                ],
              ),
            ],
          ),
          const Divider(),
          Expanded(child: WebViewWidget(controller: mycontroller))
        ],
      ),
    );
  }
}
