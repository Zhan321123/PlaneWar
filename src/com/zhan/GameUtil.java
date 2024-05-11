package com.zhan;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GameUtil {

    public static Image getImage(String path){
        BufferedImage image = null;
        URL url = GameUtil.class.getClassLoader().getResource(path);
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


}
