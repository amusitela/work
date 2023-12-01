import 'package:dio/dio.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ApiClient {
  static String baseUrl = "http://192.168.31.34:8080";
  static final _dio = Dio(BaseOptions(
    baseUrl: baseUrl, // 替换为实际的baseUrl
    connectTimeout: Duration(milliseconds: 5000),
    receiveTimeout: Duration(milliseconds: 5000),
  ));

  static Dio get dio {
    _dio.interceptors.add(InterceptorsWrapper(
      onRequest: (RequestOptions options, RequestInterceptorHandler handler) async {
        // 在每个请求前都执行的逻辑
        // 添加token到请求头
        options.headers["Authorization"] = await getToken(); // 替换为实际的token
        options.headers["Content-Type"] = "application/json";
        return handler.next(options);
      },
      onResponse: (Response response, ResponseInterceptorHandler handler) {
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
