import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:appinio_swiper/appinio_swiper.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class CreditCard extends StatefulWidget {
  const CreditCard({super.key});

  @override
  State<CreditCard> createState() => _MyCreditCardState();
}

class _MyCreditCardState extends State<CreditCard> {
final Map<String,dynamic> cardItem={
        "number":"1234567890",
        "money":"123",
        'type':"借记卡"
        };
  @override
  Widget build(BuildContext context) {
  int currentIndex = 0;
    return SizedBox(
        // height: MediaQuery.of(context).size.height * 0.75,
        height:300,
        
        // height: auto,
        child: AppinioSwiper(
          cardsCount: 10,
          loop: true,
          onSwiping: (AppinioSwiperDirection direction){
            print(direction.toString());
            
          },
          cardsBuilder: (BuildContext context,int index){
              return Padding(
              padding: const EdgeInsets.only(bottom: 10.0),
              
              child: Align(
                  child: Container(
                width: Constant.cardWidth,
                decoration: const BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.all(Radius.circular(8)),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.grey,
                      offset: Offset(0, 0), // 设置底部阴影
                      blurRadius: 2.0,
                    ),
                  ],
                ),
                child: Column(
                  children: [
                    const SizedBox(
                      height: 10,
                    ),
                    Row(
                      children: [
                        const SizedBox(
                          width: 10,
                        ),
                        Image.asset(
                          'images/card.png',
                          height: 50,
                        ),
                        const SizedBox(
                          width: 20,
                        ),
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            // 使用ListView.builder遍历cardList中的值
                            Text(
                              cardItem['number'].toString(),
                              style: MyTextStyle.mediumLarge,
                            ),
                            Text(
                              cardItem['type'].toString()+index.toString(),
                              style: MyTextStyle.mediumLarge,
                            ),
                          ],
                        ),
                      ],
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Padding(
                      padding: const EdgeInsets.all(10),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          const Text(
                            "账面余额",
                            style: MyTextStyle.medium,
                          ),
                          Text(
                            "人民币元: ${cardItem['money'].toString()}",
                            style: MyTextStyle.mediumLarge,
                          )
                        ],
                      ),
                    )
                  ],
                ),
              )));
          },
        ),
      );
  }
}