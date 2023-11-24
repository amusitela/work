import 'package:app/component/myButton.dart';
import 'package:app/component/myicon.dart';
import 'package:app/theme/colorplatte.dart';
import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'monthly_budget_page.dart';
import 'EditUserInfoPage.dart';
class HomePage extends StatefulWidget {
   HomePage(
      {super.key,
        required this.imageUrl,
        required this.userName,
        required this.money});
   String imageUrl;
  String userName;
  String money;

  @override
  _HomePageState createState() => _HomePageState();
}
class _HomePageState extends State<HomePage> {
  String? _updatedImageUrl;
  @override
  void initState() {
    super.initState();
    // 初始化状态变量
    _updatedImageUrl = widget.imageUrl;
  }
  Future<void> _navigateAndEditUserInfo() async {
    // 使用 await 关键字等待 EditUserInfoPage 返回结果
    final result = await Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => EditUserInfoPage()),
    );

    // 检查返回的结果是否是 UserInfo 类型
    if (result is UserInfo) {
      // 如果是，更新 HomePage 的状态
      setState(() {
        // 更新显示的用户信息
        // 例如，如果您有一个用于显示用户名的变量，可以这样更新：
         widget.userName = result.nickname;
        // 并且类似地更新其他信息
      });
    }
  }

  Future<void> _pickImage() async {
    final ImagePicker _picker = ImagePicker();
    // 从相册中选择图片
    final XFile? image = await _picker.pickImage(source: ImageSource.gallery);
    if (image != null) {
      setState(() {
        // 更新imageUrl
        _updatedImageUrl = image.path;
      });
    }
  }
  @override
  Widget build(BuildContext context) {
    Widget avatar = Row(
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        const SizedBox(
          width: 20,
        ),
        GestureDetector(
          onTap: _pickImage,
          child: CircleAvatar(
            radius: 30,
            backgroundImage: AssetImage(_updatedImageUrl ?? widget.imageUrl), // 确保这里引用正确
          ),
        ),

        const SizedBox(
          width: 10,
        ),
        GestureDetector(
            onTap: _navigateAndEditUserInfo,
        child: Text(
          widget.userName,
          style: MyTextStyle.large,
          selectionColor: Colors.white,
        )
        )],
    );
    Widget buttonSection = Align(
      alignment: Alignment.center,
      child: Container(
          width: 350,
          padding: const EdgeInsets.all(20),
          decoration: const BoxDecoration(
            borderRadius: BorderRadius.all(
              Radius.circular(8),
            ),
            boxShadow: [
              BoxShadow(
                color: Colors.grey,
                offset: Offset(0, 0), // 设置底部阴影
                blurRadius: 5.0,
              )
            ],
            gradient: LinearGradient(
              begin: Alignment.topLeft,
              end: Alignment.bottomRight,
              colors: [Colors.white, ColorPlatte.bottomBarColor], // 渐变色数组
              stops: [0.0, 1.0], // 渐变色的起始和结束位置
            ),
          ),
          child: const Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              MyIcon(text: "我的账户", icon: Icon(Icons.account_balance_wallet)),
              MyIcon(text: "我的权益", icon: Icon(Icons.security)),
              MyIcon(text: "我的积分", icon: Icon(Icons.star)),
              MyIcon(text: "我的奖品", icon: Icon(Icons.card_giftcard)),
            ],
          )),
    );
    Widget incomeExpenditureModule = GestureDetector(
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => MonthlyBudgetPage()),
        );
      },
      child: Align(
        alignment: Alignment.center,
        child: Container(
          width: 350,
          padding: const EdgeInsets.all(20),
          decoration: BoxDecoration(
            borderRadius: BorderRadius.all(
              Radius.circular(8),
            ),
            color: Colors.white, // 你可以调整颜色以匹配你的设计
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Text(
                    '本月收支',
                    style: MyTextStyle.medium,
                  ),
                  SizedBox(
                    width: 5,
                  ),
                  Icon(Icons.arrow_forward_ios)
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text(
                    '收入',
                    style: MyTextStyle.medium,
                  ),
                  Text(
                    '¥ 4,000.00', // 替换为实际的收入数据
                    style: MyTextStyle.small,
                  ),
                  Text(
                    '支出',
                    style: MyTextStyle.medium,
                  ),
                  Text(
                    '¥ 103.02', // 替换为实际的支出数据
                    style: MyTextStyle.small,
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );

    Widget card = Align(
      alignment: Alignment.center,
      child: Container(
        width: 350,
        height: 100,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.all(
            Radius.circular(8),
          ),
          image: DecorationImage(
            image: const AssetImage('images/1.jpg'), // 替换为你的图片路径
            fit: BoxFit.cover,
            colorFilter: ColorFilter.mode(
              Colors.black.withOpacity(0.5), // 设置图片透明度
              BlendMode.dstATop, // 使用dstATop模式
            ),
          ),
        ),
        padding: const EdgeInsets.all(10),
        child: Column(children: [
          const Row(
            children: [

              Text(
                '资产',
                style: MyTextStyle.medium,
              ),
              Icon(Icons.arrow_forward_ios),
              SizedBox(
                width: 5,
              ),

            ],
          ),
          const SizedBox(
            height: 10,
          ),
          Text(
            widget.money,
            style: MyTextStyle.large,
          )
        ]),
      ),
    );

    return Scaffold(
      body: ListView(
        children: [
          avatar,
          const SizedBox(
            height: 30,
          ),

          buttonSection,
          const SizedBox(
            height: 20,
          ),
          const Row(
            children: [
              SizedBox(width: 20,)
              ,Text("我的资产",style: MyTextStyle.mediumLarge,),
            ],
          ),
          const SizedBox(height: 10,),
          card,
          const SizedBox(height: 20,), // 在卡片和新模块之间添加空间
          incomeExpenditureModule, // 在这里添加收支模块

        ],
      ),
    );
  }
}

