package COMTEST;

import com.la.port.PortController;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;

import java.util.Calendar;
import java.util.TooManyListenersException;

/**
 * @author LA
 * @createDate 2023-03-16-10:45
 */
public class COM1Test {

    private Thread threadA;

    public COM1Test() {
        PortController portController = new PortController();
        SerialPort serialport = portController.openPort("COM1");

        threadA = new Thread(new Runnable() {
            public void run() { // 重写run()方法
                try {

                    portController.setListenerToSerialPort(serialport, event -> {
                        if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                            Calendar Cld = Calendar.getInstance();
                            int YY = Cld.get(Calendar.YEAR);
                            int MM = Cld.get(Calendar.MONTH) + 1;
                            int DD = Cld.get(Calendar.DATE);
                            int HH = Cld.get(Calendar.HOUR_OF_DAY);
                            int mm = Cld.get(Calendar.MINUTE);
                            int SS = Cld.get(Calendar.SECOND);
                            int MI = Cld.get(Calendar.MILLISECOND);
                            String curTime = YY + "-" + MM + "-" + DD + "," + HH + ":" + mm + ":" + SS + ":" + MI;

                            String str = portController.readmessage(serialport);
                            System.out.println(curTime + "-> " + str);
                            if(str.equals("*IDN?\n")){
                                portController.sendmessage(serialport,"信号源: 15184335457\n");
                            }
                            if(str.equals(":CHANnel:MODE?\n")){
                                portController.sendmessage(serialport,"IDN\n");
                            }
                            if(str.equals(":OUTPut1:POLarity?\n")||str.equals(":OUTPut2:POLarity?\n")){
                                portController.sendmessage(serialport,"NORM\n");
                            }
                            if(str.equals(":OUTPut1:SCALe?\n")||str.equals(":OUTPut1:SCALe?\n")){
                                portController.sendmessage(serialport,"FS\n");
                            }
                            if(str.equals(":SOURce1:CONTinuous:STATe?\n") || str.equals(":SOURce2:CONTinuous:STATe?\n")){
                                portController.sendmessage(serialport, "1\n");
                            }
                            if(str.equals(":SOURce1:FREQuency:CW?\n") || str.equals(":SOURce2:FREQuency:CW?\n")){
                                portController.sendmessage(serialport, "1000 Hz\n");
                            }
                            if(str.equals(":SOURce1:VOLTager:LEVel:IMMediate:AMPLitude?\n") || str.equals(":SOURce2:VOLTager:LEVel:IMMediate:AMPLitude?\n")){
                                portController.sendmessage(serialport, "2.4 VPP\n");
                            }
                            if(str.equals(":SOURce1:PHASe?\n") || str.equals(":SOURce2:PHASe?\n")){
                                portController.sendmessage(serialport, "0 DEG\n");
                            }
                            if(str.equals(":SOURce1:FREQuency:MODE?\n") || str.equals(":SOURce2:FREQuency:MODE?\n")){
                                portController.sendmessage(serialport, "CW\n");
                            }
                            if(str.equals(":SOURce1:VOLTage:LEVel:IMMediate:AMPLitude:MODE?\n") || str.equals(":SOURce2:VOLTage:LEVel:IMMediate:AMPLitude:MODE?\n")){
                                portController.sendmessage(serialport, "FIX\n");
                            }
                        }
                    });
                }catch (TooManyListenersException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
    }

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        new COM1Test();
    }
}
