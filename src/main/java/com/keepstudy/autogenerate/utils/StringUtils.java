package com.keepstudy.autogenerate.utils;

public class StringUtils{

    public static String javaName(String name) {
        String[] split = name.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        sb.append(split[0]);

        for (int i = 1; i < split.length; i++) {
            sb.append(toFirstUp(split[i]));
        }

        return sb.toString();
    }

    public static String toFirstUp(String str) {
        char[] chars = str.toCharArray();
        if (97 <=  chars[0] &&  chars[0] <= 122) {
            chars[0] ^= 32;
        }
        return new String(chars);
    }

}
