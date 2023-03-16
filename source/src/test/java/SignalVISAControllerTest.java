import com.la.SignalVISAController;
import org.junit.Test;

import java.io.IOException;

/**
 * @author LA
 * @createDate 2023-02-19-11:40
 */
public class SignalVISAControllerTest {

    static SignalVISAController signalVISAController;


    @Test
    public void testReadId() throws InterruptedException {
        signalVISAController = new SignalVISAController("ASRL2::INSTR");
        String id = signalVISAController.readID();
        System.out.println("device id: " + id);
        signalVISAController.closeController();
    }

    @Test
    public void testQuery() throws InterruptedException {
        signalVISAController = new SignalVISAController("ASRL2::INSTR");

        String channelMode = signalVISAController.queryChannelMode();
        System.out.println("信道模式: " + channelMode);

        int channel = 1;

        String signalPolarity = signalVISAController.querySignalPolarity(channel);
        System.out.println("信号波形: " + signalPolarity);

        String signalScale = signalVISAController.querySignalScale(channel);
        System.out.println("信号极性: " + signalScale);

        String continuous = signalVISAController.queryContinuous(channel);
        System.out.println("信号连续: " + continuous);

        String frequencyMode = signalVISAController.queryFrequencyMode(channel);
        System.out.println("频率模式: " + frequencyMode);

        String signalFrequency = signalVISAController.querySignalFrequency(channel);
        System.out.println("信号频率: " + signalFrequency);

        String amplitudeMode = signalVISAController.queryAmplitudeMode(channel);
        System.out.println("振幅模式: " + amplitudeMode);

        String signalAmplitude = signalVISAController.querySignalAmplitude(channel);
        System.out.println("信号振幅: " + signalAmplitude);

        String signalPhase = signalVISAController.querySignalPhase(channel);
        System.out.println("信号相位: " + signalPhase);

        signalVISAController.closeController();
    }

    @Test
    public void testOutputOnAndOff() throws InterruptedException {
        signalVISAController = new SignalVISAController("ASRL2::INSTR");
        signalVISAController.OutputOn(1);
        Thread.sleep(5000);
        signalVISAController.OutputOff(1);
        signalVISAController.closeController();
    }

    @Test
    public void testSetting() throws Exception {
        signalVISAController = new SignalVISAController("ASRL2::INSTR");
        //初始化
        //signalVISAController.initSignalChannel(1);

        //设置频率模式
        signalVISAController.setSignalFrequencyMode(1);

        //设置频率
        signalVISAController.setSignalFrequency(1,1000);

        //设置振幅模式
        signalVISAController.setSignalAmplitudeMode(1);

        //开启
        signalVISAController.OutputOn(1);

        Thread.sleep(5000);

        //设置振幅
        signalVISAController.setSignalAmplitude(1,3.4);

        //设置相位
        signalVISAController.setSignalPhase(1,0);

        Thread.sleep(5000);

        //关闭
        signalVISAController.OutputOff(1);

        signalVISAController.closeController();
    }

}
