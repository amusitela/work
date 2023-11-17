import 'package:app/component/myicon.dart';
import 'package:flutter/material.dart';

class FirstPage extends StatelessWidget {
  const FirstPage({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView( 
        children:  [
        SizedBox(height: 10,),
        Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
        MyIcon(text: '扫一扫', icon: Icon(Icons.qr_code),onPressed: (){Navigator.pushNamed(context, '/qrcode');},),
        MyIcon(text: '首页', icon: Icon(Icons.home),onPressed: (){debugPrint('1');},),
        MyIcon(text: '首页', icon: Icon(Icons.home),onPressed: (){debugPrint('1');},),
        MyIcon(text: '账户', icon: Icon(Icons.account_box),onPressed: (){Navigator.pushNamed(context, "/account");},),
        ],
        )
        
        ],
      );
  }
}