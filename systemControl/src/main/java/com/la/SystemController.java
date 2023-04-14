package com.la;


/**
 * @author LA
 * @createDate 2023-03-04-11:58
 */
public class SystemController {

    private SignalVISAController signalController;
    private SwitchController switchController;
    private AmplifierController amplifierController;

    //设备地址
    private String sourceIP = "ASRL10::INSTR";
    private String switchIP = "ASRL12::INSTR";
    private String amplifierCOM = "COM14";

    public SystemController() {
    }

    public SystemController(String sourceIP, String switchIP, String amplifierCOM) {
        this.sourceIP = sourceIP;
        this.switchIP = switchIP;
        this.amplifierCOM = amplifierCOM;
    }

    public void initSystem() throws Exception {
        signalController = new SignalVISAController(sourceIP);
        switchController = new SwitchController(switchIP);
        amplifierController = new AmplifierController(amplifierCOM);

        //signalController.initSignalChannel();
        switchController.initSwitch();
        amplifierController.initAmplifier();
    }

    //设备ID
    public void readIDs() throws InterruptedException {
        String sourceID = signalController.readID();
        String switchID = switchController.readID();
        String amplifierID = amplifierController.readID();
        System.out.println(sourceID);
        System.out.println(switchID);
        System.out.println(amplifierID);
    }

    //源换能器
    public void setSource(int channel, double freq, double amp, double phase) throws InterruptedException {
        signalController.setSignalFrequency(channel, freq);
        signalController.setSignalAmplitude(channel, amp);
        signalController.setSignalPhase(channel, phase);
    }

    //锁相
    public void setAmplifierFreq(double freq) throws InterruptedException {
        amplifierController.setReferenceFreq(freq);
    }

    //开启信号输出
    public void start(int channel) throws InterruptedException {

        signalController.OutputOn(channel);

    }

    //关闭信号输出
    public void stop(int channel) throws InterruptedException {

        signalController.OutputOff(channel);

    }

    //初始化补偿换能器
    public void setAdjustInit(int channel, double freq, double amp, double phase) throws InterruptedException {
        signalController.setSignalFrequency(channel, freq);
        signalController.setSignalAmplitude(channel, amp);
        signalController.setSignalPhase(channel, phase);
    }

    //调节补偿相位
    public void adjustPhase(int channel, double phase) throws InterruptedException {

        signalController.setSignalPhase(channel, phase);

    }

    //调节补偿电压
    public void adjustAmplitude(int channel, double amp) throws InterruptedException {

        signalController.setSignalAmplitude(channel, amp);

    }

    //读取补偿电压
    public double readAdjustVoltage(int signalChannel) throws Exception {
        String amplitude = signalController.querySignalAmplitude(signalChannel);
        return Double.valueOf(amplitude);
    }

    //读取锁相检测电压
    public double readVoltage() throws Exception {
        String amplitude = amplifierController.QuerySourceAmplitude();
        return Double.valueOf(amplitude);
    }

    //切换电子开关
    public void closeChannel(int chan) throws InterruptedException {

        switchController.closeChannel(chan);

    }

    public void openChannel(int chan) throws InterruptedException {

        switchController.openChannel(chan);

    }

    //关闭接口
    public void closeSystemControllers(){
        signalController.closeController();
        switchController.closeController();
        amplifierController.closeController();
    }

    public double waitStable() throws Exception {

        System.out.println("等待读数...");

        int waitTime = 300; //ms

        Thread.sleep(waitTime);
        double res1 = readVoltage();
        Thread.sleep(waitTime);
        double res2 = readVoltage();
        Thread.sleep(waitTime);
        double res3 = readVoltage();

        while(true){
            double ave = (res1 + res2 + res3)/3;
            if(ave < 0.01){
                return ave;
            }
            double high = ave*1.05;
            double low = ave*0.95;
            if((low <= res1 && res1 <= high)&&(low <= res2 && res2 <= high)&&(low <= res2 && res2 <= high)){
                return ave;
            }
            res1 = res2;
            res2 = res3;
            Thread.sleep(waitTime);
            res3 = readVoltage();
        }
    }


    //平衡算法
    public double adjustMethod(int channel, double initAmp, double initPhase) throws Exception {

        double aAmp = 0.1; //振幅步长
        double bAmp = 0.5; //振幅衰减因子

        double aPhas = 50; //相位步长
        double bPhas = 0.5; //相位衰减因子

        double amp = initAmp;//当前记录补偿电压
        double phas = initPhase;//当前记录补偿相位

        double aftV = amp; //前锋振幅
        double aftP = phas; //前锋相位

        double res = waitStable(); //读取锁相电压
        double curRes;

        int flagV = 1; //振幅正反调节标志
        int flagP = 1; //相位正反调节标志

        int iV = 0; //记录振幅调整次数
        int iP = 0; //记录相位调整次数

        double label = 0.001;

        while(res > label){

            int countV = 0;
            while(countV < 1 && res > label){
                aftV = aftV + flagV*aAmp;
                if(aftV < 0){
                    aftV = 0;
                }
                //调整振幅
                adjustAmplitude(channel, aftV);
                //测试算法
                testV(aftV);
                //Thread.sleep(50);
                //待稳定
                //waitStable();
                //读取当前电压
                //Thread.sleep(500);
                //curRes = readVoltage();
                curRes = waitStable();
                System.out.println("第" + (++iV) + "次振幅调整: " + aftV);
                if(curRes < res){
                    amp = aftV;
                    res = curRes;
                }else{
                    adjustAmplitude(channel, amp);
                    waitStable();
                    testV(amp);
                    flagV = -flagV;
                    aftV = amp;
                    aAmp = aAmp*bAmp;
                }
                countV++;
            }

            if(res < label){
                break;
            }

            int countP = 0;
            while(countP < 1 && res > label){
                aftP = aftP + flagP*aPhas;
                //调整相位
                adjustPhase(channel, aftP);
                //测试
                testP(aftP);
                //Thread.sleep(300);
                //待稳定
                //waitStable();
                //读取当前电压
                //curRes = readVoltage();
                curRes = waitStable();
                System.out.println("第" + (++iP) + "次相位调整: " + aftP);
                if(curRes < res){
                    phas = aftP;
                    res = curRes;
                }else{
                    adjustPhase(channel, phas);
                    waitStable();
                    testP(phas);
                    flagP = -flagP;
                    aftP = phas;
                    aPhas = aPhas*bPhas;
                }
                countP++;
            }

        }
        return amp;
    }

    //测试平衡算法
    public void testV(double amp){
        String msg = "sourceAmp: " + amp;
        amplifierController.testV(msg);

    }

    //测试平衡算法
    public void testP(double phas){
        String msg = "sourcePhas: " + phas;
        amplifierController.testP(msg);

    }


}
