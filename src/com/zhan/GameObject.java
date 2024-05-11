package com.zhan;

import java.awt.*;

public class GameObject {

    Image image;
    int height,width;

    int speed;
    double x,y;

    public GameObject (Image image,double x,double y,int speed){
        this.image = image;
        this.speed = speed;
        this.x =x;
        this.y =y;

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public GameObject(){}

    public void DrawPicture(Graphics g){
        g.drawImage(image,(int)x,(int)y,width,height,null);
    }

    public Rectangle getRect() {
        return new Rectangle((int)x,(int)y,width-2,height-2);
    }
}
