package com.x_s.s2shop.common.utils;

import java.util.Random;
import java.util.UUID;

public class Randoms {

    private static Random random = new Random();
    
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static int randomNumber(int n){
        double v = random.nextDouble();
        for (int i = 0; i < n; ++i) v *= 10;
        return (int)v;
    }
    
}
