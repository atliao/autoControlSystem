package com.la;

/**
 * @author LA
 * @createDate 2023-03-06-17:03
 */
public class Auto {

    public SystemController systemController;

    //源换能器 激励频率、振幅、相位
    public static double sourceFreq; // KHz
    public static double sourceAmp; // VPP
    public static double sourcePhase; // °

    //补偿腔特征常数
    public static double K = 2; // Pa/V

    public double AutoProcess(double freq, double amp, double phase) throws Exception {

        sourceFreq = freq;
        sourceAmp = amp;
        sourcePhase = phase;

        //1.初始化系统
        systemController.initSystem();

        //2.开启源换能器 (设置频率、振幅、相位)
        systemController.setSource(1, sourceFreq, sourceAmp, sourcePhase);
        systemController.start(1);

        //3.使位移传感器输出为 0

        double adjustAmp = 1.0;
        double adjustPhase = 0;

        //3.1 设置补偿电压
        systemController.setAdjustInit(2, sourceFreq, adjustAmp, adjustPhase);
        systemController.start(2);
        double shiftV = systemController.readShiftVoltage(1);

        //3.2 调节补偿电压
        boolean flag = true;
        while(shiftV > 0.00002){
            //********************************平衡算法**********************************//
            double tmp = shiftV;
            if(flag == true){
                adjustAmp = adjustAmp + adjustAmp/2.0;
            }else {
                adjustAmp = adjustAmp - adjustAmp/2.0;
            }
            systemController.setAdjust(2, adjustAmp, adjustPhase);
            shiftV = systemController.readShiftVoltage(1);
            if(shiftV > tmp){
                flag = false;
            }else {
                flag = true;
            }
        }

        //4.读取补偿电压
        double AdjustV = systemController.readAdjustVoltage(2);

        //5.读取水听器输出电压
        double SensorV = systemController.readSensorVoltage(3);

        //6.计算声压灵敏度
        double sensitivity = SensorV / (K * AdjustV);

        //7.关闭系统
        systemController.stop(1);
        systemController.stop(2);
        systemController.closeSystemControllers();

        return sensitivity;

    }
}
