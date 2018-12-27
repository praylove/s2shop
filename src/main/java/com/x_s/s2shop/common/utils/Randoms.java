package com.x_s.s2shop.common.utils;

import java.util.UUID;

public class Randoms {

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
