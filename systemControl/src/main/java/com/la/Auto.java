package com.la;

/**
 * @author LA
 * @createDate 2023-03-08-17:03
 */
public class Auto {

    public SystemController systemController;

    //源换能器 激励频率、振幅、相位
    public int sourceFreq; // KHz
    public double sourceAmp; // VPP
    public double sourcePhase; // °

    //补偿腔特征常数
    public double K = 2; // Pa/V

    public double AutoProcess(int freq, double amp, double phase) throws Exception {

        systemController = new SystemController();

        sourceFreq = freq;
        sourceAmp = amp;
        sourcePhase = phase;

        int waitTime;
        switch (freq){
            case 500:
                waitTime = 1000;
                break;
            case 1000:
                waitTime = 800;
                break;
            case 1500:
                waitTime = 500;
                break;
            case 2000:
                waitTime = 300;
                break;
            default:
                waitTime = 100;

        }

        //1.初始化系统
        systemController.initSystem();

        //2.驱动源换能器 (设置频率、振幅、相位)
        systemController.setSource(1, sourceFreq, sourceAmp, sourcePhase);
        systemController.start(1);
        //待稳定
        Thread.sleep(waitTime);

        //3.使位移传感器输出为 0

        double initAdjustAmp = 1.0;
        double initAdjustPhase = 0;

        //3.1 初始化补偿电压
        systemController.setAdjustInit(2, sourceFreq, initAdjustAmp, initAdjustPhase);
        systemController.start(2);

        //测试算法
        systemController.test(initAdjustAmp);

        systemController.closeChannel(100);
        //待稳定
        Thread.sleep(waitTime);


        //3.2 调节补偿电压
        double finalAmp;
        finalAmp = systemController.adjustMethod(2, initAdjustAmp, initAdjustPhase, waitTime);

        //4.读取补偿电压
        //double AdjustV = systemController.readAdjustVoltage(2);
        double AdjustV = finalAmp;

        //5.读取水听器输出电压
        systemController.openChannel(100);
        systemController.closeChannel(101);
        double SensorV = systemController.readVoltage();
        systemController.openChannel(101);

        //6.计算声压灵敏度
        double sensitivity = SensorV / (K * AdjustV);

        //7.关闭系统
        systemController.stop(1);
        systemController.stop(2);
        systemController.closeSystemControllers();


        return sensitivity;

    }
}
