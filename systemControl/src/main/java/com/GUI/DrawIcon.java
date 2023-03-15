package com.GUI;

import javax.swing.*;
import java.awt.*;

public class DrawIcon implements Icon{

    public int width;
    public int height;
    public Color c;

    public int getIconHeight() {
        return this.height;
    }

    public int getIconWidth() {
        return this.width;
    }

    public DrawIcon(int width, int height, Color c) {
        this.height = height;
        this.width = width;
        this.c = c;
    }

    public void paintIcon(Component arg0 , Graphics arg1, int x, int y) {
        arg1.setColor(c);
        arg1.fillOval(x, y, width, height);
    }

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        DrawIcon icon = new DrawIcon(8,8,Color.BLUE);

        JLabel j = new JLabel("测试",icon,SwingConstants.CENTER);
        JFrame jf = new JFrame();
        Container c = jf.getContentPane();
        c.add(j);
        jf.setVisible(true);
        jf.setSize(200,150);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
