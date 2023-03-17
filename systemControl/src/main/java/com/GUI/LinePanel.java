package com.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author LA
 * @createDate 2023-03-17-11:10
 */
public class LinePanel extends JPanel {//划线板块
    private static final long serialVersionUID = 1L;
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(0, 5, getWidth(), 5);
    }
}

