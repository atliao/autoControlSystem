package com.la;

import com.la.port.PortController;
import gnu.io.SerialPort;

/**
 * @author LA
 * @createDate 2023-02-26-10:29
 */
public class AmplifierController {

    public PortController portController;
    public SerialPort serialPort;

    public AmplifierController(String com){
        portController = new PortController();
        serialPort = portController.openPort(com);
    }

    public void initAmplifier(){

        int localMode = 1; // 远程模式
        int reference = 2;//2:外部参考,0:内部参考,1:内部扫描
        double phas = 0;//相位改变
        double sineV = 2;//输出正弦振幅
        int sourceInMode = 0;//输入信号模式 A

        //setLocalFounction(localMode);
        setReference(reference);
        setReferencePhas(phas);
        //setSineAmplitude(sineV);
        setSourceIn(sourceInMode);
        //setOutSignal(1,0);
    }

    //查询ID
    public String QueryID(){
        String res = "";
        try {
            portController.sendmessage(serialPort, LCommand.OUTX);
            Thread.sleep(200);
            portController.sendmessage(serialPort, LCommand.QID);
            Thread.sleep(200);
            res = portController.readmessage(serialPort);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

    //查询参考源
    public String QueryReference() throws Exception{
        portController.sendmessage(serialPort,LCommand.OUTX);
        Thread.sleep(200);
        portController.sendmessage(serialPort, LCommand.QFMOD);
        Thread.sleep(200);
        String res = portController.readmessage(serialPort);
        return res;
    }

    //设置参考源(0:内部参考 1:内部扫描 2:外部参考)
    public void setReference(int i) {
        String sI = "" + i;
        String msg = LCommand.FMOD.replace("<i>", sI);
        portController.sendmessage(serialPort, msg);
    }

    //查询相位
    public String QueryReferencePhas() throws Exception{
        portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(200);
        portController.sendmessage(serialPort, LCommand.QPHAS);
        Thread.sleep(200);
        String res = portController.readmessage(serialPort);
        return res;
    }

    //设置参考源相位
    public void setReferencePhas(double pash){
        String sPash = "" + pash;
        String msg = LCommand.PHAS.replace("<x>", sPash);
        portController.sendmessage(serialPort, msg);
    }

    //查询参考源频率
    public String QueryReferenceFreq() throws Exception{
        portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(200);
        portController.sendmessage(serialPort, LCommand.QFREQ);
        Thread.sleep(200);
        String res = portController.readmessage(serialPort);
        return res;
    }
    //设置参考源频率（仅在内部参考源时可用）
    public void setReferenceFreq(double freq){
        String sFreq = "" + freq;
        String msg = LCommand.FREQ.replace("<f>", sFreq);
        portController.sendmessage(serialPort, msg);
    }

    //查询正弦信号的振幅(V)
    public String QuerySineAmplitude() throws Exception{
        portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(200);
        portController.sendmessage(serialPort, LCommand.QSLVL);
        Thread.sleep(200);
        String res = portController.readmessage(serialPort);
        return res;
    }

    //设置正弦信号的振幅(V)
    public void setSineAmplitude(double v){
        String sV = "" + v;
        String msg = LCommand.SLVL.replace("<x>", sV);
        portController.sendmessage(serialPort, msg);
    }

    //查询输入源模式（0：A，1：A-B，2：I）
    public String QuerySourceIn() throws Exception{
        portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(200);
        portController.sendmessage(serialPort, LCommand.QISRC);
        Thread.sleep(200);
        String res = portController.readmessage(serialPort);
        return res;
    }

    //设置输入源的模式（0：A，1：A-B，2：I）
    public void setSourceIn(int i){
        String sI = "" + i;
        String msg = LCommand.ISRC.replace("<i>", sI);
        portController.sendmessage(serialPort, msg);
    }

    //查询输入信号的电压(V)
    public String QuerySourceAmplitude() throws Exception{
        portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(200);
        portController.sendmessage(serialPort, LCommand.QOUTP);
        Thread.sleep(200);
        String res = portController.readmessage(serialPort);
        return res;
    }

    //查询输出信号的情况(1:CH1 2:CH2)
    public String QueryOutSignal(int ch) throws Exception{
        String sCh = "" + ch;
        String msg = LCommand.QFOUT.replace("<ch>", sCh);
        portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(200);
        portController.sendmessage(serialPort, msg);
        Thread.sleep(200);
        String res = portController.readmessage(serialPort);
        return res;
    }

    //设置输出信号的情况(1:CH1 2:CH2)(0:X(Y) 1:R 2:Θ)
    public void setOutSignal(int ch, int i){
        String sCh = "" + ch;
        String sI = "" + i;
        String msg = LCommand.FOUT.replace("<ch>", sCh).replace("<i>", sI);
        portController.sendmessage(serialPort, msg);
    }

    //查询本地或远程功能(0:本地模式 1:远程模式 2:LOCAL LOCKOUT)
    public String QueryLoclalFounction() throws Exception{
        portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(200);
        portController.sendmessage(serialPort, LCommand.QLOCL);
        Thread.sleep(200);
        String res = portController.readmessage(serialPort);
        return res;
    }

    //设置本地或远程功能(0:本地模式 1:远程模式 2:LOCAL LOCKOUT)
    public void setLocalFounction(int i){
        String sI = "" + i;
        String msg = LCommand.LOCL.replace("<i>", sI);
        portController.sendmessage(serialPort, msg);
    }

    //关闭接口
    public void closeController(){
        portController.ClosePort(serialPort);
    }
}

