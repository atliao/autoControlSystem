package com.la.VISA;

import com.sun.jna.Memory;
import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

/**
 * @author LA
 * @createDate 2023-02-24-16:07
 */
public class VISAController {

    private static final IntByReference pValue = new IntByReference(0);
    LongByReference defaultSession;
    LongByReference vipSession;
    VISA visa = VISA.INSTANCE;

    public boolean open(String ip)
    {

        defaultSession = new LongByReference(0);
        int result = visa.viOpenDefaultRM(defaultSession);
        if (result != VISA.VI_SUCCESS) {
            return false;
        }

        vipSession = new LongByReference(0);

        NativeLong a = new NativeLong(defaultSession.getValue());
        NativeLong b = new NativeLong(0);
        result = visa.viOpen(a, ip, b, b, vipSession); //资源管理器逻辑标识符，打开地址，打开方式（0：正常多访问打开），返回打开的逻辑标识符
        if (result != VISA.VI_SUCCESS) {
            System.out.println(result);
            return false;
        }
        return true;
    }

    /**
     * 关闭设备.
     *
     * @return 成功返回true，失败返回false
     */
    public boolean close() {
        NativeLong a = new NativeLong(vipSession.getValue());
        int result = VISA.INSTANCE.viClose(a);
        if (result != VISA.VI_SUCCESS) {
            System.out.println(result);
            return false;
        }

        NativeLong b = new NativeLong(defaultSession.getValue());
        result = VISA.INSTANCE.viClose(b);
        if (result != VISA.VI_SUCCESS) {
            System.out.println(result);
            return false;
        }

        return true;
    }

    public void setParameter(int baud, int dataBits, int parity, int flowCntrl){
        NativeLong a = new NativeLong(vipSession.getValue());
        VISA.INSTANCE.viSetAttribute(a, VISA.VI_ATTR_ASRL_BAUD, baud);
        VISA.INSTANCE.viSetAttribute(a, VISA.VI_ATTR_ASRL_DATA_BITS, dataBits);
        //VISA.INSTANCE.viSetAttribute(a, VISA.VI_ATTR_ASRL_STOP_BITS, 10);
        VISA.INSTANCE.viSetAttribute(a, VISA.VI_ATTR_ASRL_PARITY, parity);
        VISA.INSTANCE.viSetAttribute(a, VISA.VI_ATTR_ASRL_FLOW_CNTRL, flowCntrl);
    }

    public int viGetAttribute(long attrName)  {
        NativeLong a = new NativeLong(vipSession.getValue());
        VISA.INSTANCE.viGetAttribute(a, attrName, pValue.getPointer());
        return pValue.getValue();
    }

    public boolean writeCmd(String cmdStr) {
        NativeLong a = new NativeLong(vipSession.getValue());
        int result = VISA.INSTANCE.viPrintf(a, "%s\n", cmdStr);
        if (result != VISA.VI_SUCCESS) {
            System.out.println(result);
            return false;
        }
        return true;
    }

    public boolean writeCmdR(String cmdStr) {
        NativeLong a = new NativeLong(vipSession.getValue());
        int result = VISA.INSTANCE.viPrintf(a, "%s", cmdStr);
        if (result != VISA.VI_SUCCESS) {
            System.out.println(result);
            return false;
        }
        return true;
    }

    public String readResult() {
        NativeLong a = new NativeLong(vipSession.getValue());
        Memory mem = new Memory(200);
        int result = VISA.INSTANCE.viScanf(a, "%t", mem);
        if (result != VISA.VI_SUCCESS) {
            System.out.println(result);
            return null;
        }
        return mem.getString(0);
    }

    public String readResultR() {
        NativeLong a = new NativeLong(vipSession.getValue());
        Memory mem = new Memory(100);
        int result = VISA.INSTANCE.viScanf(a, "%s", mem);
        if (result != VISA.VI_SUCCESS) {
            System.out.println(result);
            return null;
        }
        return mem.getString(0);
    }

    public String readMessage(){
        NativeLong vi = new NativeLong(vipSession.getValue());
        byte[] buffer = new byte[8];
        NativeLong count = new NativeLong(8);
        NativeLong resultCount = new NativeLong(0);
        VISA.INSTANCE.viRead(vi, buffer, count, resultCount);
        System.out.println("count:" + count);
        System.out.println("resultCount:" + resultCount);
        return new String(buffer);
    }
}
