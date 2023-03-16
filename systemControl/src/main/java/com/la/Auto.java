package com.la;

/**
 * @author LA
 * @createDate 2023-03-08-17:03
 */
public class Auto {

    public SystemController systemController;

    //源换能器 激励频率、振幅、相位
    public double sourceFreq; // KHz
    public double sourceAmp; // VPP
    public double sourcePhase; // °

    //补偿腔特征常数
    public double K = 2; // Pa/V

    public double AutoProcess(double freq, double amp, double phase) throws Exception {

        systemController = new SystemController();

        sourceFreq = freq;
        sourceAmp = amp;
        sourcePhase = phase;

        //1.初始化系统
        systemController.initSystem();

        //2.驱动源换能器 (设置频率、振幅、相位)
        systemController.setSource(1, sourceFreq, sourceAmp, sourcePhase);
        systemController.start(1);
        //待稳定
        Thread.sleep(1000);

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
        Thread.sleep(1000);


        //3.2 调节补偿电压
        double shiftV = systemController.readVoltage();
        double finalAmp = 0;
        if(shiftV > 0.01){
            finalAmp = systemController.adjustMethod(2, initAdjustAmp, initAdjustPhase);
        }

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
