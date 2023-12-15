package com.cumt.bankapp.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LetterDigit {
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        boolean isSpecial = false;//定义一个boolean值，用来表示是否包含特殊字符

        for (int i = 0; i < str.length(); i++) {
            //判断是否含有特殊字符
            Pattern p = Pattern.compile("[~`#$%^&*!@.,()\\\\{}|:;?<>]");
            Matcher m = p.matcher(str);
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }else if (m.find()) {//判断是否包含特殊字符
                isSpecial = true;
            }
        }
        //密码的正则规则 8到12位  包含数字 字母 特殊字符!@.,()
        String regex = "^([a-zA-Z0-9~`#$%^&*!@.,()\\\\{}|:;?<>]){8,12}$";
        boolean isRight = isDigit && isLetter && isSpecial && str.matches(regex);
        return isRight;
    }

    //数字
    public static final String REG_NUMBER = ".*\\d+.*";
    //大写字母
    public static final String REG_UPPERCASE = ".*[A-Z]+.*";
    //小写字母
    public static final String REG_LOWERCASE = ".*[a-z]+.*";
    //特殊符号(~!@#$%^&*()_+|<>,.?/:;'[]{}\)
    public static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";

    public static boolean isLetterDigit1(String password) {
        //数字

        //密码为空及长度8-12位判断
        if (password == null || password.length() <=8 || password.length()>=12) return true;
        //密码满足数字大小写字母 特殊字符至少三种
        int i = 0;
        if (password.matches(REG_NUMBER)) i++;
        if (password.matches(REG_LOWERCASE))i++;
        if (password.matches(REG_UPPERCASE)) i++;
        if (password.matches(REG_SYMBOL)) i++;


        if (i  < 3 )  return false;

        return true;
    }

}
