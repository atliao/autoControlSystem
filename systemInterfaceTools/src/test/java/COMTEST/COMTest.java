package COMTEST;

import com.la.port.PortController;
import gnu.io.SerialPort;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-04-14-12:04
 * @description
 */
public class COMTest {

    @Test
    public void test() throws InterruptedException {
        PortController portController = new PortController();
        SerialPort serialPort = portController.openPort("COM14");
        portController.sendmessage(serialPort, "OUTP? 3\n");
        Thread.sleep(100);
        String res = portController.readmessageR(serialPort);
        //String res = portController.readmessage(serialPort);
        System.out.println(res);
    }
}
