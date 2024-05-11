package com.zhan;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{

    boolean l,r,u,d;
    boolean live = true;


    @Override
    public void DrawPicture(Graphics g){
        if (live) {
            super.DrawPicture(g);
            //设置运动状况
            if (x > 10 && l) {
                x -= speed;
            }
            if (x < 470 && r) {
                x += speed;
            }

            if (y > 40 && u) {
                y -= speed;
            }
            if (y < 470 && d) {
                y += speed;
            }
        }
    }

    public void Pressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                r = true;
                break;
            case KeyEvent.VK_LEFT:
                l = true;
                break;
            case KeyEvent.VK_UP:
                u = true;
                break;
            case KeyEvent.VK_DOWN:
                d = true;
        }
    }

    public void Released(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                r = false;
                break;
            case KeyEvent.VK_LEFT:
                l = false;
                break;
            case KeyEvent.VK_UP:
                u = false;
                break;
            case KeyEvent.VK_DOWN:
                d = false;
        }
    }


    public Plane(Image image, double x, double y, int speed) {
        super(image, x, y, speed);

    }

}
