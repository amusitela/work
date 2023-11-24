import 'package:app/theme/textstyle.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Fund extends StatelessWidget {
  const Fund({super.key});
  final fundId = "10086";
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        // appBar: AppBar(
        //     backgroundColor: Colors.white,
        //     leading: IconButton(
        //       icon: const Icon(Icons.arrow_back_ios),
        //       color: Colors.black,
        //       onPressed: () {

        //         Navigator.pushNamed(context, '/');
        //       },
        //     ),
        //     title: const Text(
        //       "基金",
        //       style: MyTextStyle.mediumLargeBlack,
        //     ),
        //     centerTitle: true,
        //   ),
        body: ListView.builder(
            itemCount: 10,
            itemBuilder: (context, index) {
              return GestureDetector(
                child: const Padding(
                  padding: EdgeInsets.all(10),
                  child: Column(children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Expanded(
                            child: Text(
                          "3.12%",
                          style: TextStyle(
                              fontSize: 33,
                              fontWeight: FontWeight.bold, // 设置字体加粗
                              letterSpacing: 0.33, // 设置字间距
                              fontFamily: "Dosis",
                              color: Colors.red,
                              height: 1),
                        )),
                        Expanded(
                            child: Text(
                          "富国天利",
                          style: MyTextStyle.mediumLarge,
                          overflow: TextOverflow.ellipsis,
                          maxLines: 1,
                        ))
                      ],
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Expanded(
                          child: Text("年增幅", style: MyTextStyle.medium),
                        ),
                        Expanded(
                            child: Text(
                          "说明咩咩咩咩咩咩咩咩咩咩咩咩",
                          style: MyTextStyle.medium,
                          overflow: TextOverflow.ellipsis,
                          maxLines: 1,
                        ))
                      ],
                    )
                  ]),
                ),
                onTap: () {
                  Navigator.pushNamed(context, '/fund/detail',
                      arguments: fundId);
                },
              );
            }));
  }
}
