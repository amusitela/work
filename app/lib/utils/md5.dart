import 'dart:convert';
import 'package:crypto/crypto.dart';
class MyMD5{
static String generateMd5(String input) {
  var content = Utf8Encoder().convert(input);
  var digest = md5.convert(content);
  return digest.toString();
}
}