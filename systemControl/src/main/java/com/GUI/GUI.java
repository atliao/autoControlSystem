package com.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author LA
 * @createDate 2023-03-15-16:54
 */
public class GUI extends JFrame {

    private static final long serialVersionUID = 121414154325L;

    public GUI() {

        Container c = getContentPane(); //获得窗口内容
        c.setLayout(null);

        Font font1 = new Font("Times New Roman", Font.BOLD, 20);//前缀文字字体
        Font font2 = new Font("Times New Roman", Font.BOLD, 16);//下拉框字体
        Font font3 = new Font("Times New Roman", Font.BOLD, 26);//抬头文字字体
        Font font4 = new Font("calibri", Font.BOLD, 19);//二级文字字体格式
        Font font5 = new Font("calibri", Font.BOLD, 24);//监测文字字体


        //下面为,每个模块组件的定义

        //***************************************************Setting*******************************************************
        JLabel setting = new JLabel("AutoSystem");
        setting.setFont(font3);
        setting.setBounds(325, 80, 80, 30);
        c.add(setting);


        //***************************************************激光器开关按钮***************************************************
        //板块6
        JButton object6_1=new JButton("Emission");//激光开启按钮
        object6_1.setFont(font4);
        object6_1.setBounds(200, 390, 120, 35);
        c.add(object6_1);

        JButton object6_2=new JButton("Shutdown");//激光关闭按钮
        object6_2.setFont(font4);
        object6_2.setBounds(400, 390, 120, 35);
        c.add(object6_2);

        JPanel L_line2 = new LinePanel();//划分隔线(该类定义在代码最后)
        L_line2.setBounds(0, 450, 1000, 10);
        c.add(L_line2);

        //初始为可发射状态
        object6_1.setEnabled(true);
        object6_2.setEnabled(false);


        //***************************************************结果***************************************************

        JLabel object7_1 = new JLabel("Result");
        object7_1.setFont(font3);
        object7_1.setBounds(300, 470, 150, 30);
        c.add(object7_1);


        //***************************************************监控数据***************************************************
        JLabel object8_1 = new JLabel("500Hz : ");//激光器能量显示
        object8_1.setFont(font4);
        object8_1.setBounds(60, 580, 150, 25);
        c.add(object8_1);
        JTextField object8_2 =new JTextField("0",6);
        object8_2.setFont(font4);
        object8_2.setForeground(Color.BLUE); //监测数据蓝色显示
        object8_2.setBounds(200, 580, 60, 25);
        object8_2.setEditable(false); //监测数据不可选中
        c.add(object8_2);
        JLabel object8_3 = new JLabel("Pa/V");
        object8_3.setFont(font4);
        object8_3.setBounds(265, 580, 150, 25);
        c.add(object8_3);

        JLabel object8_4 = new JLabel("1000Hz : ");//电压显示
        object8_4.setFont(font4);
        object8_4.setBounds(60, 620, 150, 25);
        c.add(object8_4);
        JTextField object8_5=new JTextField("0",6);
        object8_5.setFont(font4);
        object8_5.setForeground(Color.BLUE); //监测数据蓝色显示
        object8_5.setBounds(200, 620, 60, 25);
        object8_5.setEditable(false); //监测数据不可选中
        c.add(object8_5);
        JLabel object8_6 = new JLabel("Pa/V");
        object8_6.setFont(font4);
        object8_6.setBounds(265, 620, 150, 25);
        c.add(object8_6);

        JLabel object8_7 = new JLabel("1500Hz : ");//重复率显示
        object8_7.setFont(font4);
        object8_7.setBounds(60, 660, 150, 25);
        c.add(object8_7);
        JTextField object8_8 =new JTextField("0",6);
        object8_8.setFont(font4);
        object8_8.setForeground(Color.BLUE);//监测数据蓝色显示
        object8_8.setBounds(200, 660, 60, 25);
        object8_8.setEditable(false); //监测数据不可选中
        c.add(object8_8);
        JLabel object8_9 = new JLabel("Pa/V");
        object8_9.setFont(font4);
        object8_9.setBounds(265, 660, 150, 25);
        c.add(object8_9);


        //***************************************************监听文本框***************************************************
        JTextArea jt = new JTextArea(4,15);//文本框
        jt.setEditable(false);
        jt.setForeground(Color.BLUE);
        jt.setFont(font4);
        JScrollPane scrollPane = new JScrollPane(jt);//滚动模块
        scrollPane.setBounds(365, 520, 360, 200);
        c.add(scrollPane);


        //***************************************************添加监听事件***************************************************

        //能量选择开关的监听------------------------------
        object6_1.addActionListener(new ActionListener() { //为能量开关，添加监听事件
            public void actionPerformed(ActionEvent e) {

                String str = new String((String)object6_1.getText());

                if(str.equals(new String("ON"))) { //能量设置开启，可以输入激光能量

                }
                else if(str.equals(new String("OFF"))) { //能量设置关闭，输入电压。(实验室只用能量输入，电压为激光器自动调节，暂时不启用该功能)

                }
            }
        });


        //***************************************************设置窗口参数***************************************************
        this.setTitle("Auto-Control");//标题
        this.setBounds(550,100,750,780);//大小和位置
        this.setResizable(false);//大小不可变
        this.setVisible(true);//可见
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭方式
    }

    //**************************************************测试函数***********************************************************
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        new GUI();
    }

    //**************************************************下面为模块组件的类定义***********************************************************
    class Mode extends AbstractListModel<String> implements ComboBoxModel<String> { //能量控制模式选项框
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        String selecteditem = "Continuous";
        String[] test = { "Continuous", "Burst"};

        public String getElementAt(int index) {
            return test[index];
        }

        public int getSize() {
            return test.length;
        }

        public void setSelectedItem(Object item) {
            selecteditem = (String) item;
        }

        public Object getSelectedItem() {
            return selecteditem;
        }

        public int getIndex() {
            for (int i = 0; i < test.length; i++) {
                if (test[i].equals(getSelectedItem()))
                    return i;
            }
            return 0;
        }
    }

    class LinePanel extends JPanel {//划线板块
        private static final long serialVersionUID = 1L;

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawLine(0, 5, getWidth(), 5);
        }
    }

}
