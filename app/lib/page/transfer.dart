import 'package:app/theme/constant.dart';
import 'package:app/theme/myappbar.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Transfer extends StatefulWidget {
  const Transfer({super.key});

  @override
  State<Transfer> createState() => _MyTransferState();
}

class _MyTransferState extends State<Transfer> {
  bool switchValue = false;
  Widget card = Row(
    children: [
      const SizedBox(
        width: 10,
      ),
      Image.asset(
        'images/card.png',
        height: 70,
      ),
      const SizedBox(
        width: 20,
      ),
      const Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          // 使用ListView.builder遍历cardList中的值
          Text(
            "cardItem['number'].toString()",
            style: MyTextStyle.mediumLarge,
          ),
          Text(
            "cardItem['type'].toString()",
            style: MyTextStyle.mediumLarge,
          ),
        ],
      ),
    ],
  );
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
            // Navigator.pop(context);
            Navigator.pushNamed(context, '/');
          },
        ),
        title: const Text(
          "转账",
          style: MyTextStyle.mediumLargeBlack,
        ),
        centerTitle: true,
      ),
      body: Align(
        alignment: Alignment.center,
        child: SizedBox(
          width: Constant.cardWidth,
          child: ListView(
            children: <Widget>[
              ListTile(
                title: const Text('付款账户', style: TextStyle(fontSize: 18.0)),
                subtitle: const Text('6216 **** **** 9367',
                    style: TextStyle(color: Colors.grey)),
                trailing: GestureDetector(
                  child: const Icon(Icons.arrow_forward_ios),
                  onTap: () {
                    // 点击时弹出BottomSheet
                    showModalBottomSheet(
                        context: context,
                        builder: (BuildContext context) {
                          return ListView(
                            children: [card, card, card],
                          );
                        });
                  },
                ),
              ),
              ListTile(
                title: const Text('可用余额', style: MyTextStyle.mediumLarge),
                subtitle: const Text('人民币 477.12',
                    style: TextStyle(color: Colors.red)),
                trailing: GestureDetector(
                  child: const Text(
                    '全部转出',
                    style: TextStyle(color: Colors.blue),
                  ),
                  onTap: () {},
                ),
              ),
              const ListTile(
                title: Text('币种', style: MyTextStyle.mediumLarge),
                subtitle: Text(
                  "人民币",
                ),
              ),
              const SizedBox(
                height: 10,
              ),
              Container(
                  width: Constant.cardWidth,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(8.0),
                  ),
                  padding: const EdgeInsets.symmetric(horizontal: 16.0),
                  child: const Row(
                    children: [
                      Text(
                        '收款人姓名',
                        style: MyTextStyle.mediumLarge,
                      ),
                      Expanded(
                        child: TextField(
                          decoration: InputDecoration(
                            hintText: '请输入',
                            border: InputBorder.none, // 去除边框
                            contentPadding: EdgeInsets.only(left: 8.0),
                          ),
                        ),
                      ),
                      Icon(
                        Icons.supervised_user_circle_rounded,
                        color: Colors.blue,
                      ),
                    ],
                  )),
              const SizedBox(
                height: 10,
              ),
              Container(
                  width: Constant.cardWidth,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(8.0),
                  ),
                  padding: const EdgeInsets.symmetric(horizontal: 16.0),
                  child: const Row(
                    children: [
                      Text(
                        '收款账号 ',
                        style: MyTextStyle.mediumLarge,
                      ),
                      Expanded(
                        child: TextField(
                          decoration: InputDecoration(
                            hintText: '请输入',
                            border: InputBorder.none, // 去除边框
                            contentPadding: EdgeInsets.only(left: 8.0),
                          ),
                        ),
                      ),
                      Icon(
                        Icons.camera,
                        color: Colors.blue,
                      ),
                    ],
                  )),
              const SizedBox(
                height: 10,
              ),
              Container(
                  width: Constant.cardWidth,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(8.0),
                  ),
                  padding: const EdgeInsets.symmetric(horizontal: 16.0),
                  child: const Row(
                    children: [
                      Text(
                        '附言',
                        style: MyTextStyle.mediumLarge,
                      ),
                      Expanded(
                        child: TextField(
                          decoration: InputDecoration(
                            hintText: '选填,最多二十字',
                            border: InputBorder.none, // 去除边框
                            contentPadding: EdgeInsets.only(left: 8.0),
                          ),
                        ),
                      ),
                    ],
                  )),
              ListTile(
                title: const Text('预约转账', style: MyTextStyle.mediumLarge),
                trailing: Switch(
                    value: switchValue,
                    onChanged: (bool value) {
                      setState(() {
                        switchValue = value;
                      });
                    }),
              ),

              Padding(
                padding: const EdgeInsets.all(20.0),
                child: ElevatedButton(
                  child: const Text('下一步', style: TextStyle(fontSize: 18)),
                  onPressed: () {},
                ),
              ),
              const Padding(
                padding: EdgeInsets.symmetric(horizontal: 16.0),
                child: Text(
                  '为了您的账户安全，携程将按实收款项人身份，不要将信息告诉陌生人等信息。',
                  style: TextStyle(color: Colors.grey),
                ),
              ),
              // ...其他列表项和文本...
            ],
          ),
        ),
      ),
    );
  }
}
