package com.GUI;

import com.la.Auto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @author LA
 * @createDate 2023-03-15-16:54
 */
public class GUIForAllFreq extends JFrame {

    private int[] frequency = {500,1000,1500,2000};
    private double amplitude = 1.0;
    private double phase = 0;

    public GUIForAllFreq() {

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

        //***************************************************开启按钮***************************************************
        JButton start = new JButton("Start");
        start.setFont(font4);
        start.setBounds(325, 90, 100,35);
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
        JLabel obj1_1 = new JLabel("500Hz : ");
        obj1_1.setFont(font4);
        obj1_1.setBounds(60, 270, 150, 25);
        c.add(obj1_1);
        JTextField obj1_2 =new JTextField("",6);
        obj1_2.setFont(font4);
        obj1_2.setForeground(Color.BLUE); //监测数据蓝色显示
        obj1_2.setBounds(170, 270, 80, 25);
        obj1_2.setEditable(false); //监测数据不可选中
        c.add(obj1_2);
        JLabel obj1_3 = new JLabel("V/uPa");
        obj1_3.setFont(font4);
        obj1_3.setBounds(265, 270, 150, 25);
        c.add(obj1_3);

        JLabel obj2_1 = new JLabel("1000Hz : ");
        obj2_1.setFont(font4);
        obj2_1.setBounds(60, 320, 150, 25);
        c.add(obj2_1);
        JTextField obj2_2=new JTextField("",6);
        obj2_2.setFont(font4);
        obj2_2.setForeground(Color.BLUE); //监测数据蓝色显示
        obj2_2.setBounds(170, 320, 80, 25);
        obj2_2.setEditable(false); //监测数据不可选中
        c.add(obj2_2);
        JLabel obj2_3 = new JLabel("V/uPa");
        obj2_3.setFont(font4);
        obj2_3.setBounds(265, 320, 150, 25);
        c.add(obj2_3);

        JLabel obj3_1 = new JLabel("1500Hz : ");
        obj3_1.setFont(font4);
        obj3_1.setBounds(60, 370, 150, 25);
        c.add(obj3_1);
        JTextField obj3_2 =new JTextField("",6);
        obj3_2.setFont(font4);
        obj3_2.setForeground(Color.BLUE);//监测数据蓝色显示
        obj3_2.setBounds(170, 370, 80, 25);
        obj3_2.setEditable(false); //监测数据不可选中
        c.add(obj3_2);
        JLabel obj3_3 = new JLabel("V/uPa");
        obj3_3.setFont(font4);
        obj3_3.setBounds(265, 370, 150, 25);
        c.add(obj3_3);

        JLabel obj4_1 = new JLabel("2000Hz : ");
        obj4_1.setFont(font4);
        obj4_1.setBounds(60, 420, 150, 25);
        c.add(obj4_1);
        JTextField obj4_2 =new JTextField("",6);
        obj4_2.setFont(font4);
        obj4_2.setForeground(Color.BLUE);//监测数据蓝色显示
        obj4_2.setBounds(170, 420, 80, 25);
        obj4_2.setEditable(false); //监测数据不可选中
        c.add(obj4_2);
        JLabel obj4_3 = new JLabel("V/uPa");
        obj4_3.setFont(font4);
        obj4_3.setBounds(265, 420, 150, 25);
        c.add(obj4_3);


        //***************************************************监听文本框***************************************************
        JTextArea jt = new JTextArea(4,15);//文本框
        jt.setEditable(false);
        jt.setForeground(Color.BLUE);
        jt.setFont(font4);
        JScrollPane scrollPane = new JScrollPane(jt);//滚动模块
        scrollPane.setBounds(355, 250, 380, 220);
        c.add(scrollPane);


        //***************************************************添加监听事件***************************************************

        start.addActionListener(new ActionListener() { //为开关，添加监听事件
            public void actionPerformed(ActionEvent e) {

                start.setEnabled(false);

                Thread A = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Auto auto = new Auto();
                        for(int freq : frequency){

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
                                double sensitivity = auto.AutoProcess(freq, amplitude, phase);
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
                                switch((int)freq){
                                    case 500:
                                        obj1_2.setText(res);
                                        break;
                                    case 1000:
                                        obj2_2.setText(res);
                                        break;
                                    case 1500:
                                        obj3_2.setText(res);
                                        break;
                                    case 2000:
                                        obj4_2.setText(res);
                                        break;
                                }

                                jt.append(" Completed ! \n");
                                jt.append(" Took " + resTime + " seconds\n\n");
                                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

                            } catch (Exception exception) {
                                exception.printStackTrace();
                                jt.append(" Something error...");
                            }
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
        new GUIForAllFreq();
    }

}
