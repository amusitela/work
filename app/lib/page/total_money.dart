import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TotalMoney extends StatefulWidget {
  const TotalMoney({super.key});

  @override
  State<TotalMoney> createState() => _MyTotalMoneyState();
}

class _MyTotalMoneyState extends State<TotalMoney>{
    

  @override
  void initState() {
    super.initState();

  }

  @override
  void dispose() {

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: CustomScrollView(
          slivers: <Widget>[
            SliverAppBar(
              expandedHeight: 200.0,
              floating: false,
              pinned: true,
              flexibleSpace: FlexibleSpaceBar(
                centerTitle: true,
                title: Text('余额宝', style: TextStyle(color: Colors.white)),
                background: Image.network(
                  'https://via.placeholder.com/350x200',
                  fit: BoxFit.cover,
                ),
              ),
            ),
            SliverList(
              delegate: SliverChildBuilderDelegate(
                (BuildContext context, int index) {
                  return Container(
                    color: Colors.white,
                    child: ListTile(
                      title: Text('Item #\$index'),
                    ),
                  );
                },
                childCount: 100,
              ),
            ),
          ],
        ),
      );
  }
}


