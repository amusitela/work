import 'package:flutter/material.dart';

class Mybuilder extends StatelessWidget {
  const Mybuilder({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
        children: <Widget>[
          Container(
            height: 200,
            color: Colors.blue,
            child: const Center(child: Text('Header')),
          ),
          SingleChildScrollView(
            scrollDirection: Axis.horizontal,
            child: Row(
              children: List.generate(10, (index) {
                return Container(
                  width: 150,
                  height: 200,
                  color: Colors.teal,
                  margin: const EdgeInsets.all(8),
                  child: Center(
                    child: Text('Grid Item $index'),
                  ),
                );
              }),
            ),
          ),
          GridView.builder(
            gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 2,
            ),
            itemCount: 6,
            itemBuilder: (context, index) {
              return Container(
                width: 10,
                height: 50,
                color: Colors.teal,
                margin: const EdgeInsets.all(8),
                child: Center(
                  child: Text('Grid Item $index'),
                ),
              );
            },
            shrinkWrap: true, // 设置为true
            physics:
                const NeverScrollableScrollPhysics(), // 设置为NeverScrollableScrollPhysics
          ),
          ListView.builder(
            itemCount: 20,
            itemBuilder: (context, index) {
              return ListTile(
                title: Text('List Item $index'),
              );
            },
            shrinkWrap: true,
            physics: const NeverScrollableScrollPhysics(),
          ),
        ],
      ),
    );
  }
}
