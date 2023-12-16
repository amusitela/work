import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:app/utils/httpimage.dart';
import 'package:flutter/material.dart';

class NewsItem {
  final String title;
  final String read;
  final String praise;
  final String imageUrl;
  final String source;
  final String articleId;

  NewsItem(
      {required this.title,
      required this.praise,
      required this.imageUrl,
      required this.source,
      required this.read,
      required this.articleId});
}

class NewsListPage extends StatelessWidget {
  final List<NewsItem> newsItems = [
    NewsItem(
        title: "30个能量满满的句子，一起来充电！",
        source: "官方资讯",
        read: "122",
        praise: "166",
        articleId: "12",
        imageUrl:
            "https://pic.289685.xyz/picture/v2-746ea74fc135b29f67f2d4fbf3e1c133_1440w.jpg"),
    NewsItem(
        title: "30个能量满满的句子，一起来充电！",
        source: "官方资讯",
        read: "122",
        praise: "166",
        articleId: "22",
        imageUrl:
            "https://pic.289685.xyz/picture/4K LOL 英雄联盟 皎月女神 北极星神 黛安娜 电脑 桌面 壁纸.jpg"),
    NewsItem(
        title: "30个能量满满的句子，一起来充电！",
        source: "官方资讯",
        read: "122",
        praise: "166",
        articleId: "33",
        imageUrl:
            "https://pic.289685.xyz/picture/v2-746ea74fc135b29f67f2d4fbf3e1c133_1440w.jpg")
  ];

  NewsListPage({super.key});

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
        title: const Text(
          "新闻列表",
          style: MyTextStyle.mediumLargeBlack,
        ),
        centerTitle: true,
      ),
      body: ListView.builder(
        itemCount: newsItems.length,
        itemBuilder: (context, index) {
          return GestureDetector(
            onTap: () {
              Navigator.pushNamed(context, '/article/detail',
                  arguments: newsItems[index].articleId);
            },
            child: Container(
                margin: const EdgeInsets.only(
                    left: 10, right: 10, bottom: 10, top: 5),
                padding: const EdgeInsets.all(10),
                decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(8),
                    boxShadow: const [
                      BoxShadow(
                        color: Colors.grey,
                        offset: Offset(0, 0),
                        blurRadius: 3,
                      )
                    ]),
                child: Row(
                  children: [
                    Expanded(
                      child: Column(
                        children: [
                          Text(
                            newsItems[index].title,
                            maxLines: 2,
                            overflow: TextOverflow.ellipsis,
                            style: MyTextStyle.small,
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              Text(
                                newsItems[index].source,
                                style: MyTextStyle.mediumBlack,
                              ),
                              Row(
                                children: [
                                  const Icon(Icons.remove_red_eye_outlined),
                                  const SizedBox(
                                    width: 5,
                                  ),
                                  Text(
                                    newsItems[index].read,
                                    style: MyTextStyle.mediumBlack,
                                  ),
                                  const SizedBox(
                                    width: 5,
                                  ),
                                  const Icon(Icons.thumb_up_alt_outlined),
                                  const SizedBox(
                                    width: 5,
                                  ),
                                  Text(
                                    newsItems[index].praise,
                                    style: MyTextStyle.mediumBlack,
                                  )
                                ],
                              )
                            ],
                          ),
                        ],
                      ),
                    ),
                    const SizedBox(
                      width: 15,
                    ),
                    // Image.network(
                    //   newsItems[index].imageUrl,
                    //   headers: const {'Accept': 'application/json'},
                    //   height: 80,
                    //   width: 140,
                    //   fit: BoxFit.cover,
                    // )
                    // NetworkImageSSL(newsItems[index].imageUrl,)
                    // Image.asset('images/loading.gif', width: 140, height: 80)
                    FadeInImage(
                        placeholder: Image.asset('images/loading.gif',
                                width: 140, height: 80)
                            .image, // Use a GIF as a placeholder
                        // image: NetworkImageSSL(newsItems[index].imageUrl,
                        //     width: 140, height: 80),
                        image: Image.network(newsItems[index].imageUrl).image,
                        // image: Image.asset('images/loading.gif').image,
                        fit: BoxFit.fill,
                        width: 140,
                        height: 80)
                  ],
                )),
          );
        },
      ),
    );
  }
}
