package com.la;

import com.la.VISA.VISAController;
import com.la.port.PortController;
import gnu.io.SerialPort;

/**
 * @author LA
 * @createDate 2023-03-04-11:34
 */
public class SwitchController {

    public VISAController visaController;

    public SwitchController(String ip){
        visaController = new VISAController();
        visaController.open(ip);
    }

    public void initSwitch(){
        //visaController.setParameter();
    }

    public String readID(){
        visaController.writeCmd(SWCommond.QueryID);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String res = visaController.readResult();
        return res;
    }

    public String readClose(){
        visaController.writeCmd(SWCommond.QueryClose);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String res = visaController.readResult();
        return res;
    }

    public void closeChannel(int channel){
        String msg = SWCommond.Close.replace("snn", String.valueOf(channel));
        visaController.writeCmd(msg);
    }

    public void openChannel(int channel){
        String msg = SWCommond.Open.replace("snn", String.valueOf(channel));
        visaController.writeCmd(msg);
    }

    public void closeController(){
        visaController.close();
    }


}
