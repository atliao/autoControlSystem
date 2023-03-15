package com.la;

import com.la.VISA.VISA;
import com.la.VISA.VISAController;

/**
 * @author LA
 * @createDate 2023-03-04-11:34
 */
public class SwitchController {

    private VISAController visaController;

    public SwitchController(String ip){
        visaController = new VISAController();
        visaController.open(ip);
    }

    public String queryParameter(){
        int i1 = visaController.viGetAttribute(VISA.VI_ATTR_ASRL_BAUD);
        int i2 = visaController.viGetAttribute(VISA.VI_ATTR_ASRL_DATA_BITS);
        int i3 = visaController.viGetAttribute(VISA.VI_ATTR_ASRL_STOP_BITS);
        int i4 = visaController.viGetAttribute(VISA.VI_ATTR_ASRL_PARITY);
        int i5 = visaController.viGetAttribute(VISA.VI_ATTR_ASRL_FLOW_CNTRL);
        return "波特率: " + i1 + "\n" +
                "数据位: " + i2 + "\n" +
                "停止位: " + i3 + "\n" +
                "奇偶校验: " + i4 +  "\n" +
                "限流: " + i5;
    }

    public void initSwitch(){
        //visaController.setParameter();
    }

    public String readID() throws InterruptedException {
        visaController.writeCmd(SWCommond.QueryID);
        Thread.sleep(100);
        String res = visaController.readResult();
        return res;
    }

    public String queryClose() throws InterruptedException {
        visaController.writeCmd(SWCommond.QueryClose);
        Thread.sleep(100);
        String res = visaController.readResult();
        return res;
    }

    public void closeChannel(int channel) throws InterruptedException {
        String msg = SWCommond.Close.replace("snn", String.valueOf(channel));
        visaController.writeCmd(msg);
        Thread.sleep(100);
    }

    public void openChannel(int channel) throws InterruptedException {
        String msg = SWCommond.Open.replace("snn", String.valueOf(channel));
        visaController.writeCmd(msg);
        Thread.sleep(100);
    }

    public void closeController(){
        visaController.close();
    }


}
