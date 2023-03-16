package com.la;

import com.la.VISA.VISAController;
import com.la.port.PortController;
import gnu.io.SerialPort;

/**
 * @author LA
 * @createDate 2023-02-26-10:29
 */
public class AmplifierController {

    private PortController portController;
    private SerialPort serialPort;
    private VISAController visaController;

    public AmplifierController(String ip){
        //portController = new PortController();
        //serialPort = portController.openPort(com);
        visaController = new VISAController();
        visaController.open(ip);
    }

    public void initAmplifier() throws InterruptedException {

        int localMode = 1; // 远程模式
        int reference = 2;//参考源 2:外部参考,0:内部参考,1:内部扫描
        double phas = 0;//参考相位改变
        double freq = 1000;//参考源频率
        double sineV = 2;//输出正弦振幅
        int sourceInMode = 0;//输入信号模式 A

        //setLocalFounction(localMode);
        //setReference(reference);
        //setReferencePhas(phas);
        //setReferenceFreq(freq);
        //setSineAmplitude(sineV);
        //setSourceIn(sourceInMode);
        //setOutSignal(1,0);
    }

    //查询ID
    public String readID() throws InterruptedException {
        visaController.writeCmd(LCommand.OUTX);
        //portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(50);
        visaController.writeCmd(LCommand.QID);
        //portController.sendmessage(serialPort, LCommand.QID);
        Thread.sleep(50);
        String res = visaController.readResult();
        //String res = portController.readmessage(serialPort);
        Thread.sleep(50);
        return res;
    }

    //查询参考源
    public String QueryReference() throws Exception{
        visaController.writeCmd(LCommand.OUTX);
        //portController.sendmessage(serialPort,LCommand.OUTX);
        Thread.sleep(50);
        visaController.writeCmd(LCommand.QFMOD);
        //portController.sendmessage(serialPort, LCommand.QFMOD);
        Thread.sleep(50);
        //String res = portController.readmessage(serialPort);
        String res = visaController.readResult();
        Thread.sleep(50);
        return res;
    }

    //设置参考源(0:内部参考 1:内部扫描 2:外部参考)
    public void setReference(int i) throws InterruptedException {
        String sI = "" + i;
        String msg = LCommand.FMOD.replace("<i>", sI);
        visaController.writeCmd(msg);
        //portController.sendmessage(serialPort, msg);
        Thread.sleep(100);
    }

    //查询参考源斜率
    public String queryReferenceSlop() throws InterruptedException {
        visaController.writeCmd(LCommand.OUTX);
        //portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(50);
        visaController.writeCmd(LCommand.QRSLP);
        //portController.sendmessage(serialPort, LCommand.QRSLP);
        Thread.sleep(50);
        String res = visaController.readResult();
        //String res = portController.readmessage(serialPort);
        Thread.sleep(50);
        return res;
    }

    //设置参考源斜率
    public void setReferenceSlop() throws InterruptedException {
        String msg = LCommand.RSLP;
        //portController.sendmessage(serialPort, msg);
        visaController.writeCmd(msg);
        Thread.sleep(100);
    }

    //查询相位
    public String QueryReferencePhas() throws Exception{
        visaController.writeCmd(LCommand.OUTX);
        //portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(50);
        visaController.writeCmd(LCommand.QPHAS);
        //portController.sendmessage(serialPort, LCommand.QPHAS);
        Thread.sleep(50);
        String res = visaController.readResult();
        //String res = portController.readmessage(serialPort);
        Thread.sleep(50);
        return res;
    }

    //设置参考源相位
    public void setReferencePhas(double pash) throws InterruptedException {
        String sPash = "" + pash;
        String msg = LCommand.PHAS.replace("<x>", sPash);
        visaController.writeCmd(msg);
        //portController.sendmessage(serialPort, msg);
        Thread.sleep(100);
    }

    //查询参考源频率
    public String QueryReferenceFreq() throws Exception{
        visaController.writeCmd(LCommand.OUTX);
        //portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(50);
        visaController.writeCmd(LCommand.QFREQ);
        //portController.sendmessage(serialPort, LCommand.QFREQ);
        Thread.sleep(50);
        String res = visaController.readResult();
        //String res = portController.readmessage(serialPort);
        Thread.sleep(50);
        return res;
    }

    //设置参考源频率（仅在内部参考源时可用）
    public void setReferenceFreq(double freq) throws InterruptedException {
        String sFreq = "" + freq;
        String msg = LCommand.FREQ.replace("<f>", sFreq);
        visaController.writeCmd(msg);
        //portController.sendmessage(serialPort, msg);
        Thread.sleep(100);
    }

    //查询正弦信号的振幅(V)
    public String QuerySineAmplitude() throws Exception{
        visaController.writeCmd(LCommand.OUTX);
        //portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(50);
        visaController.writeCmd(LCommand.QSLVL);
        //portController.sendmessage(serialPort, LCommand.QSLVL);
        Thread.sleep(50);
        String res = visaController.readResult();
        //String res = portController.readmessage(serialPort);
        Thread.sleep(50);
        return res;
    }

    //设置正弦信号的振幅(V)
    public void setSineAmplitude(double v) throws InterruptedException {
        String sV = "" + v;
        String msg = LCommand.SLVL.replace("<x>", sV);
        visaController.writeCmd(msg);
        //portController.sendmessage(serialPort, msg);
        Thread.sleep(100);
    }

    //查询输入源模式（0：A，1：A-B，2：I）
    public String QuerySourceIn() throws Exception{
        visaController.writeCmd(LCommand.OUTX);
        //portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(50);
        visaController.writeCmd(LCommand.QISRC);
        //portController.sendmessage(serialPort, LCommand.QISRC);
        Thread.sleep(50);
        String res = visaController.readResult();
        //String res = portController.readmessage(serialPort);
        Thread.sleep(50);
        return res;
    }

    //设置输入源的模式（0：A，1：A-B，2：I）
    public void setSourceIn(int i) throws InterruptedException {
        String sI = "" + i;
        String msg = LCommand.ISRC.replace("<i>", sI);
        visaController.writeCmd(msg);
        //portController.sendmessage(serialPort, msg);
        Thread.sleep(100);
    }

    //查询输入信号的电压(V)
    public String QuerySourceAmplitude() throws Exception{
        visaController.writeCmd(LCommand.OUTX);
        //portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(50);
        visaController.writeCmd(LCommand.QOUTP);
        //portController.sendmessage(serialPort, LCommand.QOUTP);
        Thread.sleep(50);
        String res = visaController.readResult();
        //String res = portController.readmessage(serialPort);
        Thread.sleep(50);
        return res;
    }

    //查询输出信号的情况(1:CH1 2:CH2)
    public String QueryOutSignal(int ch) throws Exception{
        String sCh = "" + ch;
        String msg = LCommand.QFOUT.replace("<ch>", sCh);
        visaController.writeCmd(LCommand.OUTX);
        //portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(50);
        visaController.writeCmd(msg);
        //portController.sendmessage(serialPort, msg);
        Thread.sleep(50);
        String res = visaController.readResult();
        //String res = portController.readmessage(serialPort);
        Thread.sleep(50);
        return res;
    }

    //设置输出信号的情况(1:CH1 2:CH2)(0:X(Y) 1:R 2:Θ)
    public void setOutSignal(int ch, int i) throws InterruptedException {
        String sCh = "" + ch;
        String sI = "" + i;
        String msg = LCommand.FOUT.replace("<ch>", sCh).replace("<i>", sI);
        visaController.writeCmd(msg);
        //portController.sendmessage(serialPort, msg);
        Thread.sleep(100);
    }

    //查询本地或远程功能(0:本地模式 1:远程模式 2:LOCAL LOCKOUT)
    public String QueryLoclalFounction() throws Exception{
        visaController.writeCmd(LCommand.OUTX);
        //portController.sendmessage(serialPort, LCommand.OUTX);
        Thread.sleep(50);
        visaController.writeCmd(LCommand.QLOCL);
        //portController.sendmessage(serialPort, LCommand.QLOCL);
        Thread.sleep(50);
        String res = visaController.readResult();
        //String res = portController.readmessage(serialPort);
        Thread.sleep(50);
        return res;
    }

    //设置本地或远程功能(0:本地模式 1:远程模式 2:LOCAL LOCKOUT)
    public void setLocalFounction(int i) throws InterruptedException {
        String sI = "" + i;
        String msg = LCommand.LOCL.replace("<i>", sI);
        visaController.writeCmd(msg);
        //portController.sendmessage(serialPort, msg);
        Thread.sleep(100);
    }

    //关闭接口
    public void closeController(){
        visaController.close();
        //portController.ClosePort(serialPort);
    }

    //测试平衡算法
    public void test(String msg){
        visaController.writeCmd(msg);
        //portController.sendmessage(serialPort, msg);
    }
}

