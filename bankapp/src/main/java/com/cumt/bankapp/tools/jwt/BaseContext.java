package com.cumt.bankapp.tools.jwt;

public class BaseContext {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void setCurrentId(String id) {
        threadLocal.set(id);
    }

    public static String getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
