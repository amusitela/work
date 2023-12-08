import 'package:app/component/myicon.dart';
import 'package:app/page/body/fund.dart';
import 'package:app/page/body/homepage.dart';
import 'package:app/page/body/indexpage.dart';
import 'package:app/page/body/creditcard.dart';
import 'package:app/theme/colorplatte.dart';
import 'package:app/theme/textstyle.dart';
import 'package:bubble_bottom_bar/bubble_bottom_bar.dart';
import 'package:flutter/material.dart';

class IndexPage extends StatefulWidget {
  const IndexPage({super.key});

  @override
  State<IndexPage> createState() => _MyIndexState();
}

class _MyIndexState extends State<IndexPage> {
  late int currentIndex;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    currentIndex = 0;
  }

  void changePage(int? index) {
    setState(() {
      currentIndex = index!;
    });
  }

  @override
  Widget build(BuildContext context) {
    // Widget card=Align(alignment: Alignment.center,
    
    // child: ,
    // )



    return Scaffold(
    
      
      body: IndexedStack(
        index: currentIndex,
        children:   [
        FirstPage(),
        CreditCard(),
        Fund(),
        HomePage(imageUrl: "images/1.jpg", userName: 'userName', money: '100.0')
        ],
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endDocked,
      bottomNavigationBar: BubbleBottomBar(
        opacity: .2,
        currentIndex: currentIndex,
        onTap: changePage,
        borderRadius: const BorderRadius.vertical(top: Radius.circular(16)),
        elevation: 8,
        // fabLocation: BubbleBottomBarFabLocation.end, //new
        hasNotch: true, //new
        hasInk: true, //new, gives a cute ink effect
        inkColor: Colors.black12, //optional, uses theme color if not specified
        items: const <BubbleBottomBarItem>[
          BubbleBottomBarItem(
              backgroundColor: ColorPlatte.bottomBarColor,
              icon: Icon(
                Icons.home,
                color: Colors.black,
              ),
              activeIcon: Icon(
                Icons.home,
                color: ColorPlatte.primaryColor,
              ),
              title: Text("首页",
                  style: TextStyle(color: ColorPlatte.primaryColor))),
          BubbleBottomBarItem(
              backgroundColor: ColorPlatte.bottomBarColor,
              icon: Icon(
                Icons.payment,
                color: Colors.black,
              ),
              activeIcon: Icon(
                Icons.payment,
                color: ColorPlatte.primaryColor,
              ),
              title: Text("信用卡",
                  style: TextStyle(color: ColorPlatte.primaryColor))),
          BubbleBottomBarItem(
              backgroundColor: ColorPlatte.bottomBarColor,
              icon: Icon(
                Icons.attach_money,
                color: Colors.black,
              ),
              activeIcon: Icon(
                Icons.attach_money,
                color: ColorPlatte.primaryColor,
              ),
              title: Text("财富",
                  style: TextStyle(color: ColorPlatte.primaryColor))),
          BubbleBottomBarItem(
              backgroundColor: ColorPlatte.bottomBarColor,
              icon: Icon(
                Icons.person,
                color: Colors.black,
              ),
              activeIcon: Icon(
                Icons.person,
                color: ColorPlatte.primaryColor,
              ),
              title: Text(
                "我的",
                style: TextStyle(color: ColorPlatte.primaryColor),
              ))
        ],
      ),
    );
  }
}
