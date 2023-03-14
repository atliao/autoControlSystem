import com.la.SignalVISAController;
import org.junit.Test;

import java.io.IOException;

/**
 * @author LA
 * @createDate 2023-02-19-11:40
 */
public class SignalVISAControllerTest {

    static SignalVISAController signalVISAController;


    public void testReadId(){
        signalVISAController = new SignalVISAController("ASRL2::INSTR");
        String id = signalVISAController.readID();
        System.out.println("device id: " + id);
        signalVISAController.closeController();
    }


    public void testSetFrequency(){
        signalVISAController = new SignalVISAController("ASRL2::INSTR");
        signalVISAController.setSignalFrequency(1,2.6);
        signalVISAController.closeController();
    }


    public void testOutputOn(){
        signalVISAController = new SignalVISAController("ASRL2::INSTR");
        signalVISAController.OutputOn(1);
        signalVISAController.closeController();
    }


    public static void main(String[] args) throws Exception {

        signalVISAController = new SignalVISAController("ASRL2::INSTR");

        //查询设备ID
        String id = signalVISAController.readID();
        System.out.println("device id:" + id);
        //System.in.read();
        Thread.sleep(200);

        //初始化
        signalVISAController.initSignalChannel(1);
        //System.in.read();
        Thread.sleep(200);

        //查询是否为连续模式
        System.out.println("是否为连续模式: " + signalVISAController.queryContinuous(1));
        //System.in.read();
        Thread.sleep(200);

        //查询频率模式
        System.out.println("频率模式: " + signalVISAController.queryFrequencyMode(1));
        //System.in.read();
        Thread.sleep(200);

        //设置频率模式
        signalVISAController.setSignalFrequencyMode(1);
        //System.in.read();
        Thread.sleep(200);

        //查询频率
        System.out.println("信号频率: " + signalVISAController.querySignalFrequency(1));
        //System.in.read();
        Thread.sleep(200);

        //设置频率
        signalVISAController.setSignalFrequency(1,2.6);
        //System.in.read();
        Thread.sleep(200);

        //查询振幅模式
        System.out.println("振幅模式: " + signalVISAController.queryAmplitudeMode(1));
        //System.in.read();
        Thread.sleep(200);

        //设置振幅模式
        signalVISAController.setSignalAmplitudeMode(1);
        //System.in.read();
        Thread.sleep(200);

        //查询振幅
        System.out.println("信号振幅: " + signalVISAController.querySignalAmplitude(1));
        //System.in.read();
        Thread.sleep(200);

        //设置振幅
        signalVISAController.setSignalAmplitude(1,2.4);
        //System.in.read();
        Thread.sleep(200);

        //查询相位
        System.out.println("信号相位: " + signalVISAController.querySignalPhase(1));
        //System.in.read();
        Thread.sleep(200);

        //设置相位
        signalVISAController.setSignalPhase(1, 45);
        //System.in.read();
        Thread.sleep(200);

        //开启
        signalVISAController.OutputOn(1);
        //System.in.read();
        Thread.sleep(200);

        //关闭
        signalVISAController.OutputOff(1);
        //System.in.read();
        Thread.sleep(200);

        signalVISAController.closeController();
    }
}
