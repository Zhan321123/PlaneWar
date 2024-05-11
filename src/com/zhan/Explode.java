package com.zhan;

import java.awt.*;

public class Explode {

    double x,y;//爆炸位置
    static Image[] images = new Image[28];//定义爆炸图片组

    static {
        for (int i=0;i<28;i++){
            images[i] = GameUtil.getImage("images\\explode\\图层 "+(i+1)+".png");

        }
    }

    //
    int count ;
    public void DrawPicture (Graphics g){
        if (count<28){
            g.drawImage(images[count],(int)x,(int)y,null);
            count++;
        }
    }

    public Explode(){}

    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
