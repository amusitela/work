import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ApiClient {
  static String baseUrl = "http://10.5.251.176:8080";
  static final _dio = Dio(BaseOptions(
    baseUrl: baseUrl, // 替换为实际的baseUrl
    connectTimeout: const Duration(milliseconds: 5000),
    receiveTimeout: const Duration(milliseconds: 5000),
  ));

  static Dio get dio {
    _dio.interceptors.add(InterceptorsWrapper(
      onRequest:
          (RequestOptions options, RequestInterceptorHandler handler) async {
        // 在每个请求前都执行的逻辑
        // 添加token到请求头
        options.headers["Authorization"] = await getToken(); // 替换为实际的token
        options.headers["Content-Type"] = "application/json";
        return handler.next(options);
      },
      onResponse: (Response response, ResponseInterceptorHandler handler) {
        if (response.statusCode == 401) {
          setJwtToNull();
          return;
        }
        // 在每个响应后都执行的逻辑
        return handler.next(response);
      },
      onError: (DioError e, ErrorInterceptorHandler handler) {
        // 在请求错误时执行的逻辑
        return handler.next(e);
      },
    ));

    return _dio;
  }
}

// 存储令牌

// 获取令牌
Future<String?> getToken() async {
  final prefs = await SharedPreferences.getInstance();
  return prefs.getString('jwt_token');
}

Future<void> setJwtToNull() async {
  SharedPreferences prefs = await SharedPreferences.getInstance();

  // Remove 'jwt' key from SharedPreferences
  await prefs.remove('jwt_token');
}
