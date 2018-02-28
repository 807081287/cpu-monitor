package com.gsww.utils;

/**
 * MD5Util
 * com.gsww.utils
 *
 * @author Xander
 * 使用key加密
 * @Date 2018-02-27 下午2:31
 * The word 'impossible' is not in my dictionary.
 */
public class MD5Util {
    private static String a = "1";

    public MD5Util() {
    }

    public static String md5encode(String var0) {
        try {
            MD5 var1 = new MD5();
            return var1.encrypt(var0, a);
        } catch (Exception var2) {
            return var0;
        }
    }

    public static String md5decode(String var0) {
        try {
            MD5 var1 = new MD5();
            return var1.decrypt(var0, a);
        } catch (Exception var2) {
            return var0;
        }
    }

}
