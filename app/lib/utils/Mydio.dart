import 'package:dio/dio.dart';
class ApiClient {
  static String baseUrl="https://your-base-url.com";
  static final _dio=Dio(BaseOptions(
        baseUrl: baseUrl, // 替换为实际的baseUrl
        connectTimeout: Duration(milliseconds: 5000),
        receiveTimeout: Duration(milliseconds: 5000),
      ));

  static Dio get dio {
    if (_dio == null) {


      _dio.interceptors.add(InterceptorsWrapper(
        onRequest: (RequestOptions options, RequestInterceptorHandler handler) {
          // 在每个请求前都执行的逻辑
          // 添加token到请求头
          options.headers["Authorization"] = "Bearer YOUR_TOKEN_HERE"; // 替换为实际的token

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
    }

    return _dio;
  }
}
