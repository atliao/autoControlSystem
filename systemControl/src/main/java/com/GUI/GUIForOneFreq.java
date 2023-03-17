package com.GUI;

import com.la.Auto;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @author LA
 * @createDate 2023-03-17-13:36
 */
public class GUIForOneFreq extends JFrame {

    private double amplitude = 1.0;
    private double phase = 0;

    public GUIForOneFreq() {

        Container c = getContentPane(); //获得窗口内容
        c.setLayout(null);

        Font font1 = new Font("Times New Roman", Font.BOLD, 20);//前缀文字字体
        Font font2 = new Font("Times New Roman", Font.BOLD, 16);//下拉框字体
        Font font3 = new Font("Times New Roman", Font.BOLD, 28);//抬头文字字体
        Font font4 = new Font("calibri", Font.BOLD, 24);//二级文字字体格式
        Font font5 = new Font("calibri", Font.BOLD, 24);//监测文字字体


        //下面为,每个模块组件的定义

        //***************************************************Title*******************************************************
        JLabel title = new JLabel("Auto-Control");
        title.setFont(font3);
        title.setBounds(295, 30, 300, 30);
        c.add(title);
        //***************************************************设置频率***************************************************
        JLabel freq1 = new JLabel("frequency : ");
        freq1.setFont(font4);
        freq1.setBounds(120, 100, 150, 25);
        c.add(freq1);
        JTextField freq2 =new JTextField("0",6);
        freq2.setFont(font4);
        freq2.setBounds(250, 100, 80, 25);
        freq2.setEditable(true);
        c.add(freq2);
        JLabel freq3 = new JLabel("Hz");
        freq3.setFont(font4);
        freq3.setBounds(340, 100, 150, 25);
        c.add(freq3);
        //***************************************************开启按钮***************************************************
        JButton start = new JButton("Start");
        start.setFont(font4);
        start.setBounds(450, 90, 120,35);
        c.add(start);

        JPanel L_line = new LinePanel();//划分隔线
        L_line.setBounds(0, 150, 1000, 10);
        c.add(L_line);

        //初始为可按状态
        start.setEnabled(true);
        //***************************************************结果***************************************************

        JLabel label = new JLabel("Result");
        label.setFont(font3);
        label.setBounds(340, 170, 100, 30);
        c.add(label);

        //***************************************************测量数据***************************************************
        JLabel result1 = new JLabel("sensitivity :");
        result1.setFont(font4);
        result1.setBounds(215, 480, 150, 25);
        c.add(result1);
        JTextField result2 =new JTextField("",6);
        result2.setFont(font4);
        result2.setForeground(Color.BLUE); //监测数据蓝色显示
        result2.setBounds(340, 480, 80, 25);
        result2.setEditable(false); //监测数据不可选中
        c.add(result2);
        JLabel result3 = new JLabel("V/uPa");
        result3.setFont(font4);
        result3.setBounds(430, 480, 150, 25);
        c.add(result3);

        //***************************************************监听文本框***************************************************
        JTextArea jt = new JTextArea(4,15);//文本框
        jt.setEditable(false);
        jt.setForeground(Color.BLUE);
        jt.setFont(font4);
        JScrollPane scrollPane = new JScrollPane(jt);//滚动模块
        scrollPane.setBounds(195, 230, 380, 220);
        c.add(scrollPane);


        //***************************************************添加监听事件***************************************************

        start.addActionListener(new ActionListener() { //为开关，添加监听事件
            public void actionPerformed(ActionEvent e) {

                start.setEnabled(false);

                Thread A = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Auto auto = new Auto();
                        String freq = freq2.getText();
                        jt.append(" " + freq + " Hz measurement: starting...\n");
                        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());//定位到监控兰最后一行
                        try {
                            Thread.sleep(200);
                            jt.append(" waiting...\n");
                            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

                            //记录开始时间
                            Date date1 = new Date();
                            long startTime = date1.getTime();
                            //开始标定
                            double sensitivity = auto.AutoProcess(Integer.valueOf(freq), amplitude, phase);
                            //记录结束时间
                            Date date2 = new Date();
                            long endTime = date2.getTime();
                            //计算标定时间
                            long timeL = endTime - startTime;
                            double time = timeL/1000;
                            DecimalFormat dfTime = new DecimalFormat("0.0");
                            String resTime = dfTime.format(time);

                            //记录标定结果
                            DecimalFormat df = new DecimalFormat("0.000");
                            String res = df.format(sensitivity);
                            result2.setText(res);

                            jt.append(" Completed ! \n");
                            jt.append(" Took " + resTime + " seconds\n\n");
                            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

                        } catch (Exception exception) {
                            exception.printStackTrace();
                            jt.append(" Something error...");
                        }
                        start.setEnabled(true);
                    }
                });
                A.start();
            }
        });


        //***************************************************设置窗口参数***************************************************
        this.setTitle("Auto-Control");//标题
        this.setBounds(550,100,760,580);//大小和位置
        this.setResizable(false);//大小不可变
        this.setVisible(true);//可见
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭方式
    }

    //**************************************************测试函数***********************************************************
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        new GUIForOneFreq();
    }

}
