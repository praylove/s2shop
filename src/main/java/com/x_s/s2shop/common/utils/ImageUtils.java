package com.x_s.s2shop.common.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

    public static boolean imageCheck(InputStream is){
        boolean flag = true;
        try {
            BufferedImage image = ImageIO.read(is);
            if (image == null) {
                flag = false;
            }
        } catch(IOException ex) {
            flag = false;
        }

        return flag;
    }

}
