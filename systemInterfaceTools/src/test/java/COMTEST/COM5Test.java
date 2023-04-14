package COMTEST;

import com.la.port.PortController;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.TooManyListenersException;

/**
 * @author LA
 * @createDate 2023-03-16-10:45
 */
public class COM5Test {

    private Thread threadA;
    double res = 267*267 + 0.334*0.334;

    double v = 1;
    double p = 0;

    public COM5Test() {
        PortController portController = new PortController();
        SerialPort serialport = portController.openPort("COM15");

        threadA = new Thread(new Runnable() {
            public void run() { // 重写run()方法
                try {

                    portController.setListenerToSerialPort(serialport, event -> {
                        /*try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
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
                                double tmp = Double.parseDouble(num);
                                v = tmp;
                                res = (v - 0.334) * (v - 0.334) + (p - 267)*(p - 267);
                            }
                            if(str.startsWith("sourcePhas: ")){
                                String num = str.substring(11,str.length()-1);
                                double tmp = Double.parseDouble(num);
                                p = tmp;
                                res = (v - 0.334) * (v - 0.334) + (p - 267)*(p - 267);
                            }
                            if(str.equals("OUTX 0\n")){
                                res = 267*267 + 0.334*0.334;
                                v = 1;
                                p = 0;
                            }
                            if(str.equals("OUTP? 3\n")){
                                portController.sendmessage(serialport, String.valueOf(res) + "\r");
                                int i = 0;
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
                                portController.sendmessage(serialport, "1000\r");
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
