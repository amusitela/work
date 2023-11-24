import 'package:app/theme/constant.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'package:syncfusion_flutter_charts/charts.dart';

class FundDetail extends StatefulWidget {
  const FundDetail({super.key, required this.fundId});
  final String fundId;

  @override
  State<FundDetail> createState() => _MyWidgetState();
}

class _MyWidgetState extends State<FundDetail> {
  String name = "富国天利";

  String value = "1.34%";
  String rate = "0.02%";
  List<String> list = ["计划和精神的", "债券型", "金牛基"];

  @override
  Widget build(BuildContext context) {
    Widget part1 = Container(
      width: MediaQuery.of(context).size.width,
      height: 150,
      color: const Color(0xffdf2d46),
      child: Row(
        children: [
          Expanded(
              child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              const Text(
                "净值",
                style: MyTextStyle.mediumWhite,
              ),
              const SizedBox(
                height: 5,
              ),
              Text(
                value,
                style: MyTextStyle.largeWhite,
              ),
            ],
          )),
          Expanded(
              child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              const Text(
                "日涨幅",
                style: MyTextStyle.mediumWhite,
              ),
              const SizedBox(
                height: 5,
              ),
              Text(
                rate,
                style: MyTextStyle.largeWhite,
              ),
              SizedBox(
                width: Constant.cardWidth / 5,
              )
            ],
          )),
          SizedBox(
            width: Constant.cardWidth / 5,
          )
        ],
      ),
    );
    Widget part2 = Container(
      width: MediaQuery.of(context).size.width,
      height: 60,
      padding: const EdgeInsets.only(top: 5, bottom: 5, left: 10),
      decoration: const BoxDecoration(
        color: Color(0xffdf2d46),
        border: Border(
          top: BorderSide(
            color: Colors.white, // 边框颜色
            width: 1.0, // 边框宽度
          ),
        ),
      ),
      child: SingleChildScrollView(
        scrollDirection: Axis.horizontal,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: List.generate(
            list.length,
            (index) => Container(
              margin: const EdgeInsets.all(3),
              padding: const EdgeInsets.all(3),
              decoration: BoxDecoration(
                border: Border.all(
                  color: Colors.white, // 边框颜色
                  width: 1.0, // 边框宽度
                ),
              ),
              child: Text(
                list[index],
                style: MyTextStyle.smallWhite,
              ),
            ),
          ),
        ),
      ),
    );
    Widget part3 = Center(
        child: Container(
            child: SfCartesianChart(
                // Initialize category axis
                primaryXAxis: CategoryAxis(),
                series: <LineSeries<SalesData, String>>[
          LineSeries<SalesData, String>(
              // Bind data source
              dataSource: <SalesData>[
                SalesData('Jan', 35),
                SalesData('Feb', 28),
                SalesData('Mar', 34),
                SalesData('Apr', 32),
                SalesData('May', 40)
              ],
              xValueMapper: (SalesData sales, _) => sales.year,
              yValueMapper: (SalesData sales, _) => sales.sales)
        ])));
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.white,
          leading: IconButton(
            icon: const Icon(Icons.arrow_back_ios),
            color: Colors.black,
            onPressed: () {
              // 在这里添加返回操作
              // Navigator.pop(context);
              // Navigator.pushNamed(context, '/');
              Navigator.pop(context);
            },
          ),
          title: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Text(
                name,
                style: MyTextStyle.mediumLargeBlack,
              ),
              Text(
                widget.fundId,
                style: MyTextStyle.mediumBlack,
              )
            ],
          ),
          centerTitle: true,
        ),
        body: ListView(
          children: [
            part1,
            part2,
            const SizedBox(
              height: 10,
            ),
            part3,
            const SizedBox(
              height: 20,
            ),
            SizedBox(width: Constant.cardWidth,
            child: ElevatedButton(
                onPressed: () {
                  // 在这里添加处理添加卡号的逻辑
                  // String cardNumber = _cardNumberController.text;
                  // print('添加卡号：$cardNumber');
                },
                
                child: Container(
                  width: Constant.cardWidth,
                  
                  child: const Text(
                    '暂时不支持买入',
                    style: MyTextStyle.mediumLarge,
                    textAlign: TextAlign.center,
                  ),
                )),)
            
          ],
        ));
  }
}

class SalesData {
  SalesData(this.year, this.sales);

  final String year;
  final double sales;
}
