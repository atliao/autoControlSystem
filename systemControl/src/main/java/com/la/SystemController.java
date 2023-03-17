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
    private String sourceIP = "ASRL2::INSTR";
    private String switchIP = "ASRL4::INSTR";
    private String amplifierCOM = "ASRL6::INSTR";

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
        //switchController.initSwitch();
        //amplifierController.initAmplifier();
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
    public void setSource(int channel, int freq, double amp, double phase) throws InterruptedException {
        signalController.setSignalFrequency(channel, freq);
        signalController.setSignalAmplitude(channel, amp);
        signalController.setSignalPhase(channel, phase);
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
    public void setAdjustInit(int channel, int freq, double amp, double phase) throws InterruptedException {
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


    //平衡算法
    public double adjustMethod(int channel, double initAmp, double initPhase, int waitTime) throws Exception {

        double aAmp = 1; //步长
        double bAmp = 0.5; //步长因子

        double amp = initAmp;
        double aft = amp; //前锋

        double res = readVoltage(); //读取锁相电压
        double curRes;

        int flag = 1; //正反标志

        int i = 0; //记录次数

        while(res > 0.001){

            aft = aft + flag*aAmp;
            //调整振幅
            adjustAmplitude(channel, aft);
            //测试算法
            test(aft);
            //待稳定
            Thread.sleep(waitTime);
            //读取当前电压
            curRes = readVoltage();
            System.out.println("第" + (++i) + "次调整: " + aft);
            if(curRes < res){
                amp = aft;
                res = curRes;
            }else{
                flag = -flag;
                aft = amp;
                aAmp = aAmp*bAmp;
            }

        }
        return amp;
    }

    //测试平衡算法
    public void test(double amp){
        String msg = "sourceAmp: " + amp;
        amplifierController.test(msg);

    }
}
