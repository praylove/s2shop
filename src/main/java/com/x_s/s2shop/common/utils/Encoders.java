package com.x_s.s2shop.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Encoders {

    public static String bcrypt(String s){
        return new BCryptPasswordEncoder().encode(s);
    }

    public static String md5(String s, String salt){
        return md5(s + salt);
    }

    public static String md5(String s){
        String code = null;
        try {
            code = endcode("md5", s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String endcode(String algorithm, String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return Arrays.toString(MessageDigest.getInstance(algorithm).digest(s.getBytes("UTF-8")));
    }
}
