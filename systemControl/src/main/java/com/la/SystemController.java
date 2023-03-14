package com.la;

/**
 * @author LA
 * @createDate 2023-03-04-11:58
 */
public class SystemController {

    public SignalVISAController signalController;
    public SwitchController switchController;
    public AmplifierController amplifierController;

    //设备地址
    public static String sourceIP = "ASRL2::INSTR";
    public static String switchIP = "ASRL3::INSTR";
    public static String amplifierCOM = "COM5";

    public void initSystem() throws Exception {
        signalController = new SignalVISAController(sourceIP);
        switchController = new SwitchController(switchIP);
        amplifierController = new AmplifierController(amplifierCOM);

        signalController.initSignalChannel(1);
        signalController.initSignalChannel(2);
        switchController.initSwitch();
        amplifierController.initAmplifier();
    }

    public void readIDs(){
        String sourceID = signalController.readID();
        String switchID = switchController.readID();
        String amplifierID = amplifierController.QueryID();
        System.out.println(sourceID);
        System.out.println(switchID);
        System.out.println(amplifierID);
    }

    public void setSource(int channel, double freq, double amp, double phase){
        signalController.setSignalFrequency(channel, freq);
        signalController.setSignalAmplitude(channel, amp);
        signalController.setSignalPhase(channel, phase);
    }

    public void setAdjustInit(int channel, double freq, double amp, double phase){
        signalController.setSignalFrequency(channel, freq);
        signalController.setSignalAmplitude(channel, amp);
        signalController.setSignalPhase(channel, phase);
    }

    public void setAdjust(int channel, double amp, double phase) throws InterruptedException {
        signalController.setSignalAmplitude(channel, amp);
        Thread.sleep(100);
        signalController.setSignalPhase(channel, phase);
    }

    public void start(int channel){
        signalController.OutputOn(channel);
    }

    public void stop(int channel){
        signalController.OutputOff(channel);
    }

    int initChannel = 10;
    public double readShiftVoltage(int switchChannel) throws Exception {
        switchController.openChannel(initChannel);
        Thread.sleep(100);
        switchController.closeChannel(switchChannel);
        initChannel = switchChannel;
        String amplitude = amplifierController.QuerySourceAmplitude();
        return Double.valueOf(amplitude);
    }

    public double readAdjustVoltage(int switchChannel) throws Exception {
        switchController.openChannel(initChannel);
        Thread.sleep(100);
        switchController.closeChannel(switchChannel);
        initChannel = switchChannel;
        String amplitude = amplifierController.QuerySourceAmplitude();
        return Double.valueOf(amplitude);
    }
    public double readSensorVoltage(int switchChannel) throws Exception {
        switchController.openChannel(initChannel);
        Thread.sleep(100);
        switchController.closeChannel(switchChannel);
        initChannel = switchChannel;
        String amplitude = amplifierController.QuerySourceAmplitude();
        return Double.valueOf(amplitude);
    }

    public void closeSystemControllers(){
        signalController.closeController();
        switchController.closeController();
        amplifierController.closeController();
    }
}
