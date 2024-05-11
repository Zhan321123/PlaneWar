package com.zhan;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import static com.zhan.Parameter.*;

public class GameFrame extends Frame{


    //导入图片
    Image plane = GameUtil.getImage("images\\plane.png");
    Image sky = GameUtil.getImage("images\\sky.jpg");

    //绘制窗口
    public void FrameLaunch(){
        this.setTitle("Plane War");//设置标题
        setVisible(true);//设置窗口是否可见
        setSize(500,500);//设置窗口大小
        setLocation(550,200);//窗口位置

        //增加窗口关闭
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //对炮弹组赋予对象
        for (int i=0;i<bs.length;i++){
            bs[i] = new Bomb();
        }

        new PaintThread().start();//启动重画窗口的线程
        this.addKeyListener(new KeyMonitor());//启动键盘监听

    }

    Plane p1 = new Plane(plane,200,350,6);//创建飞机对象
    Bomb[] bs = new Bomb[BombNum];//创建炮弹组
    Explode explode;//创建爆炸对象
    Date start = new Date();//游戏开始时间
    long time = 0;//游戏运行时间
    boolean hit;//撞击与否
    //画出图像
    @Override
    public void paint(Graphics g) {

        g.drawImage(sky,0,0,500,500,null);//画背景


        p1.DrawPicture(g);//画出飞机

        //画出炮弹组
        for (int i=0;i<bs.length;i++){
            bs[i].DrawPicture(g);

            hit = bs[i].getRect().intersects(p1.getRect());//矩形相交检测，检测是否撞击

            //如果撞击，播放爆炸效果
            if (hit) {
                p1.live = false;
                if (explode == null) {
                    explode = new Explode(p1.x, p1.y);
                }
                explode.DrawPicture(g);
            }
        }

        paintString(g);
    }

    //图中的字
    public void paintString(Graphics g){

        Font f = g.getFont();
        Color c = g.getColor();

        g.setColor(Color.green);//设置画笔颜色为绿色

        //计算运行时间
        if (p1.live){
            time = (System.currentTimeMillis()-start.getTime())/1000;
            g.drawString("已坚持："+time+" 秒",100,100);
        }else {
            g.drawString("已坚持："+time,100,100);
        }

        if (!p1.live){

            g.setFont(new Font("微软雅黑",Font.BOLD,70));//设置字体
            //结束提示
            if (time<10) {
                g.drawString("菜，才坚持"+time+"秒", 20, 250);
            }else {
                g.drawString("牛,坚持了"+time+"秒",20,250);
            }
        }

        //归还画笔
        g.setFont(f);
        g.setColor(c);
    }


    GameFrame(){
        FrameLaunch();
    }

    public static void main(String[] args) {
        new GameFrame();

    }

    //实现键盘的监听
    class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            p1.Pressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            p1.Released(e);
        }
    }

    //增加线程不断重画
    class PaintThread extends Thread{
        @Override
        public void run() {
            while(true){
                repaint();

                try {
                    Thread.sleep(20);//系统休息时间1s/20ms=50帧
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //双缓冲解决屏幕闪烁问题
    private Image offScreenImage = null;
    public void update(Graphics g){
        if (offScreenImage == null){
            offScreenImage = this.createImage(500,500);
        }
        Graphics gOff =offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }



}
