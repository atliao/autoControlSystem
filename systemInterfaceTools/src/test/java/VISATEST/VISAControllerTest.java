package VISATEST;

import com.la.VISA.VISA;
import com.la.VISA.VISAController;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-02-24-15:09
 */
public class VISAControllerTest {

    @Test
    public void testOpen(){
        VISAController vController = new VISAController();
        boolean open = vController.open("ASRL3::INSTR");
        System.out.println(open);
        boolean close = vController.close();
        System.out.println(close);
    }

    @Test
    public void testParameter() throws InterruptedException {
        VISAController vController = new VISAController();
        boolean open = vController.open("ASRL2::INSTR");
        System.out.println(open);
        vController.setParameter();
        int i1 = vController.viGetAttribute(VISA.VI_ATTR_ASRL_BAUD);
        int i2 = vController.viGetAttribute(VISA.VI_ATTR_ASRL_DATA_BITS);
        int i3 = vController.viGetAttribute(VISA.VI_ATTR_ASRL_STOP_BITS);
        int i4 = vController.viGetAttribute(VISA.VI_ATTR_ASRL_PARITY);
        int i5 = vController.viGetAttribute(VISA.VI_ATTR_ASRL_FLOW_CNTRL);
        System.out.println("波特率: " + i1 + "\n" +
                            "数据位: " + i2 + "\n" +
                            "停止位: " + i3 + "\n" +
                            "奇偶校验: " + i4 +  "\n" +
                            "限流: " + i5);
        boolean close = vController.close();
        System.out.println(close);
    }

    @Test
    public void testSendAndRead(){
        VISAController vController = new VISAController();
        boolean open = vController.open("ASRL2::INSTR");
        System.out.println(open);
        vController.writeCmd("*IDN?");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s = vController.readResult();
        System.out.println(s);
        boolean close = vController.close();
        System.out.println(close);
    }
}