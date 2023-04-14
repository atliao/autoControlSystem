package COMTEST;

import com.la.port.PortController;
import gnu.io.SerialPort;

/**
 * @author LA
 * @createDate 2023-02-20-11:06
 */
public class COM2Test {
    public static void main(String[] args) throws InterruptedException {
        PortController portController = new PortController();
        //String s1 = "OUTX 0\n";
        String s2 = "OUTP? 3\n";
        SerialPort port = portController.openPort("COM4");
        //portController.sendmessage(port, s1);
        Thread.sleep(100);
        portController.sendmessage(port, s2);
        Thread.sleep(100);
        String readmessage = portController.readmessage(port);
        System.out.println("read:" + readmessage);
        portController.ClosePort(port);
    }
}