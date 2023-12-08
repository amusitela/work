import 'package:app/theme/textstyle.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:photo_view/photo_view.dart';
import 'dart:io';

class ChatMessage {
  final int identity;
  final String message;
  final String type; // 'file', 'image', or 'text'
  final String avatarUrl;

  ChatMessage(
      {required this.identity,
      required this.message,
      required this.type,
      required this.avatarUrl});
}

class ChatPage extends StatefulWidget {
  const ChatPage({super.key});

  @override
  _ChatPageState createState() => _ChatPageState();
}

class _ChatPageState extends State<ChatPage> {
  final ScrollController _scrollController = ScrollController();
  final TextEditingController _textController = TextEditingController();
  List<ChatMessage> messages = [
    ChatMessage(
        identity: 2,
        message: "Hello!",
        type: 'text',
        avatarUrl: "images/1.jpg"),
    ChatMessage(
        identity: 1,
        message: "Hi there!",
        type: 'text',
        avatarUrl: "images/card.png"),
  ];

  void _sendMessage(String text) {
    setState(() {
      messages.add(ChatMessage(
          identity: 2, message: text, type: 'text', avatarUrl: "images/1.jpg"));
      _textController.clear();
    });
    _scrollController.animateTo(
      _scrollController.position.maxScrollExtent,
      duration: const Duration(milliseconds: 300),
      curve: Curves.easeOut,
    );
  }

  void _sendImage() async {
    final picker = ImagePicker();
    final pickedFile = await picker.pickImage(source: ImageSource.gallery);

    if (pickedFile != null) {
      setState(() {
        messages.add(ChatMessage(
            identity: 2,
            message: pickedFile.path,
            type: 'image',
            avatarUrl: "images/1.jpg"));
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        leading: IconButton(
          icon: const Icon(Icons.arrow_back_ios),
          color: Colors.black,
          onPressed: () {
            Navigator.pop(context);
          },
        ),
        title: const Text(
          "在线客服",
          style: MyTextStyle.mediumLargeBlack,
        ),
        centerTitle: true,
      ),
      body: Column(
        children: [
          Expanded(
            child: ListView.builder(
              controller: _scrollController,
              itemCount: messages.length,
              itemBuilder: (context, index) {
                final message = messages[index];
                return Padding(
                    padding: const EdgeInsets.all(10),
                    child: Row(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      mainAxisAlignment: message.identity == 1
                          ? MainAxisAlignment.start
                          : MainAxisAlignment.end,
                      children: [
                        if (message.identity == 1) // Left aligned avatar
                          CircleAvatar(
                              backgroundImage: AssetImage(message.avatarUrl)),
                        if (message.identity == 1) const SizedBox(width: 10),
                        Flexible(
                          // Use Flexible to allow the text to wrap
                          child: message.type == 'text'
                              ? Container(
                                  padding: const EdgeInsets.all(10),
                                  decoration: const BoxDecoration(
                                      color: Colors.white,
                                      borderRadius:
                                          BorderRadius.all(Radius.circular(8)),
                                      boxShadow: [
                                        BoxShadow(
                                          color: Colors.grey,
                                          offset: Offset(0, 0), // 设置左侧阴影
                                          blurRadius: 3.0,
                                        )
                                      ]),
                                  child: Text(
                                    message.message,
                                    style: MyTextStyle.mediumBlack,
                                  ),
                                )
                              : GestureDetector(
                                  onTap: () => _previewImage(message.message),
                                  child: Image.file(File(message.message),
                                      width: 150, height: 150),
                                ),
                        ),
                        if (message.identity == 2) const SizedBox(width: 10),
                        if (message.identity == 2) // Right aligned avatar
                          CircleAvatar(
                              backgroundImage: AssetImage(message.avatarUrl)),
                      ],
                    ));
              },
            ),
          ),
          const Divider(height: 1.0),
          Row(
            children: [
              Expanded(
                  child: Container(
                margin: const EdgeInsets.all(10),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(8.0), // 设置圆角半径为8
                  border: Border.all(color: Colors.grey), // 边框颜色
                ),
                child: TextField(
                  controller: _textController,
                  decoration: const InputDecoration(
                    hintText: '请输入',
                    border: InputBorder.none, // 去除内部边框
                    contentPadding: EdgeInsets.only(left: 8.0),
                  ),
                ),
              )),
              GestureDetector(
                child: const Icon(Icons.send),
                onTap: () {
                  if (_textController.text.isNotEmpty) {
                    _sendMessage(_textController.text);
                  }
                },
              ),
              const SizedBox(
                width: 5,
              ),
              GestureDetector(
                onTap: _sendImage,
                child: const Icon(Icons.photo),
              ),
            ],
          ),
        ],
      ),
    );
  }

  void _previewImage(String imagePath) {
    Navigator.of(context).push(MaterialPageRoute(
      builder: (_) => Scaffold(
        appBar: AppBar(),
        body: Center(
          child: PhotoView(
            imageProvider: FileImage(File(imagePath)),
          ),
        ),
      ),
    ));
  }
}
