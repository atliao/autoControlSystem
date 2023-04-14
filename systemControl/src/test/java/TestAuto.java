import com.la.Auto;
import com.la.SystemController;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-03-16-11:48
 */
public class TestAuto {

    @Test
    public void test(){
        double sensitivity1 = 8 / (3.34375 * 2);
        double sensitivity2 = 8 / (3.34 * 2);
        double x = ((333.984375-334)*(333.984375-334) + (3.3408203125-3.34)*(3.3408203125-3.34)) / (2*3.3408203125);
        System.out.println(x);
    }

    public static void main(String[] args) throws Exception {
        //设备地址
        String sourceIP = "ASRL10::INSTR";
        String switchIP = "ASRL12::INSTR";
        String amplifierCOM = "COM14";
        Auto auto = new Auto(sourceIP, switchIP, amplifierCOM);
        double sensitivity = auto.AutoProcess(500, 0.1, 0);
        System.out.println(sensitivity);
    }

    @Test
    public void testWaitStable() throws Exception {
        String sourceIP = "USB0::0x0D4A::0x000E::9267032::INSTR";
        String switchIP = "ASRL3::INSTR";
        String amplifierCOM = "COM5";
        SystemController systemController = new SystemController(sourceIP,switchIP,amplifierCOM);
        systemController.initSystem();
        double res = systemController.waitStable();
        System.out.println(res);
    }

}
