package com.la.VISA;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.LongByReference;

/**
 * @author LA
 * @createDate 2023-02-22-11:13
 */
public interface VISA extends Library {

    VISA INSTANCE = (VISA) Native.loadLibrary("visa32", VISA.class);

    public static final long VI_ATTR_ASRL_BAUD = (0x3FFF0021);
    public static final long VI_ATTR_ASRL_DATA_BITS = (0x3FFF0022);
    public static final long VI_ATTR_ASRL_PARITY = (0x3FFF0023);
    public static final long VI_ATTR_ASRL_STOP_BITS = (0x3FFF0024);
    public static final long VI_ATTR_ASRL_FLOW_CNTRL = (0x3FFF0025);
    public static final long VI_NULL = 0;
    public static final long VI_SUCCESS = 0;

    public int viOpenDefaultRM(LongByReference session);

    public int viOpen(NativeLong viSession, String rsrcName,
                      NativeLong accessMode, NativeLong timeout,
                      LongByReference session);

    public int viClose(NativeLong vi);

    public int viScanf(NativeLong vi, String readFmt, Object... args);

    public int viPrintf(NativeLong vi, String writeFmt, Object... args);

    public int viRead(NativeLong vi, byte[] buf, NativeLong count, NativeLong retCount);

    public int viSetAttribute(NativeLong vi, long attribute, long attrValue);

    public int viGetAttribute(NativeLong vi, long attribute, Pointer attrState);

}

