package com.cumt.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T>
 */

@Data
public class MyResult<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据

    public static <T> MyResult<T> success() {
        MyResult<T> result = new MyResult<T>();
        result.code = 1;
        return result;
    }
    public static <T> MyResult<T> success(T object) {
        MyResult<T> result = new MyResult<T>();
        result.data = object;
        result.code = 200;
        return result;
    }
    public static <T> MyResult<T> success(T object,String msg) {
        MyResult<T> result = new MyResult<T>();
        result.data = object;
        result.code = 200;
        result.msg=msg;
        return result;
    }

    public static <T> MyResult<T> error(String msg) {
        MyResult result = new MyResult();
        result.msg = msg;
        result.code = 0;
        return result;
    }

    public static <T> MyResult<T> successMsg(String msg) {
        MyResult result = new MyResult();
        result.msg = msg;
        result.code = 200;
        return result;
    }


}