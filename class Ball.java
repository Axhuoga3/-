package com.example;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    private int x, y; // 足球的位置
    private int diameter = 30; // 足球的直径
    private int xSpeed = 5, ySpeed = 5; // 足球的移动速度

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;

        // 边界检测，防止足球飞出窗口
        if (x <= 0 || x >= GamePanel.WIDTH - diameter) {
            xSpeed = -xSpeed;
        }
        if (y <= 0 || y >= GamePanel.HEIGHT - diameter) {
            ySpeed = -ySpeed;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }

    public void kick(int kickX, int kickY) {
        xSpeed = (x - kickX) / 10;
        ySpeed = (y - kickY) / 10;
    }
}
package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Ball ball;

    public GamePanel() {
        ball = new Ball(WIDTH / 2, HEIGHT / 2);

        // 添加鼠标点击事件监听器
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ball.kick(e.getX(), e.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.GREEN); // 设置背景颜色为绿色，模拟足球场

        ball.draw(g);
    }

    public void update() {
        ball.move();
        repaint();
    }
}
package com.example;

import javax.swing.*;

public class FootballGame extends JFrame {
    private GamePanel gamePanel;

    public FootballGame() {
        setTitle("Java 踢足球小游戏");
        setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel();
        add(gamePanel);

        setVisible(true);

        // 游戏循环
        new Thread(() -> {
            while (true) {
                gamePanel.update();
                try {
                    Thread.sleep(20); // 控制游戏更新速度
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new FootballGame();
    }
}
