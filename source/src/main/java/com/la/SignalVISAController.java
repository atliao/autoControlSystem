package com.la;

import com.la.VISA.VISAController;

/**
 * @author LA
 * @createDate 2023-02-19-17:31
 */
public class SignalVISAController {

    private VISAController visaController;

    public SignalVISAController(String ip){
        visaController = new VISAController();
        visaController.open(ip);
    }

    //查询ID
    public String readID() throws InterruptedException {
        visaController.writeCmd("*IDN?");
        Thread.sleep(50);
        String res = visaController.readResult();
        return res;
    }

    //初始化信道
    public void initSignalChannel() throws Exception{
        //信道独立
        String msg1 = SCommand.channel_mode;
        visaController.writeCmd(msg1);
        Thread.sleep(360);

        //信号波形及反转：正弦，正常（不反转）
        String msg2;
        msg2 = SCommand.signal_polarity.replace("<channel>", "1");
        visaController.writeCmd(msg2);
        Thread.sleep(120);
        msg2 = SCommand.signal_polarity.replace("<channel>", "2");
        visaController.writeCmd(msg2);
        Thread.sleep(120);

        //信号极性：FS，双极性
        String msg3;
        msg3 = SCommand.signal_scale.replace("<channel>", "1");
        visaController.writeCmd(msg3);
        Thread.sleep(120);
        msg3 = SCommand.signal_scale.replace("<channel>", "2");
        visaController.writeCmd(msg3);
        Thread.sleep(120);

        //信号模式：连续
        String msg4;
        msg4 = SCommand.signal_continuous_mode.replace("<channel>", "1");
        visaController.writeCmd(msg4);
        Thread.sleep(100);
        msg4 = SCommand.signal_continuous_mode.replace("<channel>", "2");
        visaController.writeCmd(msg4);
        Thread.sleep(100);
    }

    //查看信道是否独立
    public String queryChannelMode() throws InterruptedException {
        String msg = SCommand.query_channel_mode;
        visaController.writeCmd(msg);
        Thread.sleep(50);
        String res = visaController.readResult();
        return res;
    }

    //查看信号波形
    public String querySignalPolarity(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.query_signal_polarity.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(50);
        String res = visaController.readResult();
        return res;
    }

    //查看信号极性
    public String querySignalScale(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.query_signal_scale.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(50);
        String res = visaController.readResult();
        return res;
    }

    //查看是否为连续模式
    public String queryContinuous(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.query_signal_continuous_mode.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(50);
        String res = visaController.readResult();
        return res;
    }

    //查询频率模式
    public String queryFrequencyMode(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.query_signal_frequncy_mode.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(50);
        String res = visaController.readResult();
        return res;
    }

    //设置频率模式
    public void setSignalFrequencyMode(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.signal_frequncy_mode.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(200);
    }

    //查询信号频率
    public String querySignalFrequency(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.query_signal_frequency.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(50);
        String res = visaController.readResult();
        return res;
    }

    //设置信号频率：Hz
    public void setSignalFrequency(int channel, int freq) throws InterruptedException {
        String chan = "" + channel;
        String frequency = "" + freq;
        String msg = SCommand.signal_frequency.replace("<channel>", chan);
        msg = msg.replace("<freq>", frequency);
        visaController.writeCmd(msg);
        Thread.sleep(60);
    }

    //查询振幅模式
    public String queryAmplitudeMode(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.query_signal_amplitude_mode.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(50);
        String res = visaController.readResult();
        return res;
    }

    //设置振幅模式
    public void setSignalAmplitudeMode(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.signal_amplitude_mode.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(250);
    }

    //查询信号振幅
    public String querySignalAmplitude(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.query_signal_amplitude.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(50);
        String res = visaController.readResult();
        return res;
    }

    //设置信号振幅：VPP
    public void setSignalAmplitude(int channel, double amp) throws InterruptedException {
        String chan = "" + channel;
        String amplitude = "" + amp;
        String msg = SCommand.signal_amplitude.replace("<channel>", chan);
        msg = msg.replace("<amp>", amplitude);
        visaController.writeCmd(msg);
        Thread.sleep(110);
    }

    //查询信号相位
    public String querySignalPhase(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.query_signal_phase.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(50);
        String res = visaController.readResult();
        return res;
    }

    //设置信号相位：°
    public void setSignalPhase(int channel, double phase) throws InterruptedException {
        String chan = "" + channel;
        String pha = "" + phase;
        String msg = SCommand.signal_phase.replace("<channel>", chan);
        msg = msg.replace("<phase>", pha);
        visaController.writeCmd(msg);
        Thread.sleep(50);

    }

    //信号输出开启
    public void OutputOn(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.channel_on.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(50);
    }

    //信号输出关闭
    public void OutputOff(int channel) throws InterruptedException {
        String chan = "" + channel;
        String msg = SCommand.channel_off.replace("<channel>", chan);
        visaController.writeCmd(msg);
        Thread.sleep(50);
    }

    //关闭设备接口
    public void closeController(){
        visaController.close();
    }
}