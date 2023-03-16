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
public class COM5Test {

    private Thread threadA;
    double vol = 100;

    public COM5Test() {
        PortController portController = new PortController();
        SerialPort serialport = portController.openPort("COM5");

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
                                portController.sendmessage(serialport,"锁相放大器: 15184335457\n");
                            }
                            if(str.equals("FMOD?\n")){
                                portController.sendmessage(serialport, "2\n");
                            }
                            if(str.equals("SLVL?\n")){
                                portController.sendmessage(serialport,"2.0\n");
                            }
                            if(str.equals("ISRC?\n")){
                                portController.sendmessage(serialport, "0\n");
                            }
                            if(str.startsWith("sourceAmp: ")){
                                String num = str.substring(10,str.length()-1);
                                double tmp = Double.valueOf(num);
                                vol = (tmp - 3.34) * (tmp - 3.34);
                            }
                            if(str.equals("OUTP? 3\n")){
                                String msg = String.valueOf(vol);
                                portController.sendmessage(serialport, msg + "\n");
                            }
                            if(str.equals("FOUT? 1\n")){
                                portController.sendmessage(serialport, "1\n");
                            }
                            if(str.equals("LOCL?\n")){
                                portController.sendmessage(serialport, "1\n");
                            }
                            if(str.equals("PHAS?\n")){
                                portController.sendmessage(serialport, "0.0\n");
                            }
                            if(str.equals("FREQ?\n")){
                                portController.sendmessage(serialport, "1000\n");
                            }
                            if(str.equals("RSLP?\n")){
                                portController.sendmessage(serialport, "0\n");
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
        new COM5Test();
    }
}
