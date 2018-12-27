package com.x_s.s2shop.common.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCodeUtils {

    private static final char[] CODE_CHAR = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjklmnpqrstuvwxyz123456789".toCharArray();
    private static final int CHAR_LEN = CODE_CHAR.length;

    public static String code(){
        return code(4);
    }

    public static String code(int length){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i){
            int charIndex = random.nextInt(CHAR_LEN);
            sb.append(CODE_CHAR[charIndex]);
        }
        return sb.toString();
    }

    public static BufferedImage image(String code) {
        int width = 150, height = 30;
        return image(width, height, code);
    }

    public static BufferedImage image(int width, int height, String code) {
        //指定宽高和bufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //画笔画在image 上
        Graphics g = image.getGraphics();
        Color color = g.getColor();
        g.fillRect(0, 0, width, height);
        Random random = new Random();
        int charSize = width / code.length();
        for (int i = 0; i < code.length(); i++) {
            //设置字体
            g.setFont(getFont());
            //设置随机颜色
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawString(Character.toString(code.charAt(i)), charSize * i + 6, 25);
        }

        //将画笔颜色还原22
        g.setColor(color);
        g.dispose();

        return image;
    }

    private static Font getFont() {
        Random random = new Random();
        Font[] font = new Font[5];
        font[0] = new Font("Ravie", Font.PLAIN, 24);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, 24);
        font[2] = new Font("Forte", Font.PLAIN, 24);
        font[3] = new Font("Wide Latin", Font.PLAIN, 24);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, 24);
        return font[random.nextInt(5)];
    }
}
