import com.la.SystemController;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-04-11-17:22
 * @description
 */
public class TestSystem {

    SystemController systemController;

    //设备地址
    private String sourceIP = "USB0::0x0D4A::0x000E::9267032::INSTR";
    private String switchIP = "ASRL3::INSTR";
    private String amplifierCOM = "COM5";

    @Test
    public void test() throws Exception {

        systemController = new SystemController(sourceIP, switchIP, amplifierCOM);
        System.out.println("初始化系统...");
        systemController.initSystem();
        int chan = 2;
        double freq = 100; //Hz
        double amp = 0.04; //mV
        double phas = 0; //DEG
        systemController.setSource(chan, freq, amp, phas);
        systemController.closeChannel(204);
        systemController.closeChannel(203);
        System.out.println("开启信号源通道2: 频率 " + freq + " Hz, 振幅 " + amp + " Vrms, 相位 " + phas + " DEG");
        systemController.start(chan);
        systemController.setAmplifierFreq(freq);
        Thread.sleep(500);
        System.out.println("等待稳定...");
        Thread.sleep(4500);
        double res = systemController.readVoltage();
        System.out.println("锁相测量振幅: " + res + " mV");
        freq = 5; //Hz
        amp = 0.05; //mV
        phas = 90; //DEG
        Thread.sleep(500);
        System.out.println("更改信号源通道2: 频率 " + freq + " Hz, 振幅 " + amp + " Vrms, 相位 " + phas + " DEG");
        systemController.setSource(chan, freq, amp, phas);
        systemController.setAmplifierFreq(freq);
        Thread.sleep(500);
        System.out.println("等待稳定...");
        Thread.sleep(4500);
        res = systemController.readVoltage();
        System.out.println("锁相测量振幅: " + res + " mV");
        Thread.sleep(500);
        systemController.openChannel(203);
        systemController.openChannel(204);
        systemController.stop(chan);
        systemController.closeSystemControllers();
        System.out.println("关闭系统");



    }
}
