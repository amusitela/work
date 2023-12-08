import 'package:app/utils/Mydio.dart';
import 'package:dio/dio.dart';

class LoginApi {
  static Future<Response> loginApi(Map<String, String> data) async {
    // 注意：这里假设你的登录接口路径为 '/login'
    return await ApiClient.dio.post('/login', data: data);
  }
}