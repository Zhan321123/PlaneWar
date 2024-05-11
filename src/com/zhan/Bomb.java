package com.zhan;

import java.awt.*;

import static com.zhan.Parameter.*;

public class Bomb extends GameObject{
    double degree;

    public Bomb (){
        x = 100;
        y = 150;
        speed = BombSpeed;
        width = 8;
        height = 8;
        degree = Math.random()*Math.PI*2;

    }

    @Override
    public void DrawPicture(Graphics g) {

        Color c = g.getColor();

        g.setColor(new Color(34, 189, 199));
        g.fillOval((int) x,(int)y,8,8);

        x += Math.cos(degree)*speed;
        y += Math.sin(degree)*speed;

        g.setColor(c);

        if(x < 0 || x > 490){
            degree = Math.PI-degree;
        }
        if (y<40||y>492){
            degree = -degree;
        }

    }
}