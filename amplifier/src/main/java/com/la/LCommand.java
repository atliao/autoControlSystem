package com.la;

/**
 * @author LA
 * @createDate 2023-02-26-09:48
 */
public class LCommand {

    public static char LF = 10;

    //每次编程前都要用OUTX i命令，任何询问命令前，指定响应的接口(0:RS232, 1:GPIB)
    public static String OUTX = "OUTX 0";

    //查询设备ID
    public static String QID = "*IDN?";

    //查询参考源 0:内部参考，1:内部扫描，2:外部参考
    public static String QFMOD = "FMOD?";
    //设置参考源 0:内部参考，1:内部扫描，2:外部参考
    public static String FMOD = "FMOD <i>";

    //查询外部引用的斜率
    public static String RSLP = "RSLP 0";
    public static String QRSLP = "RSLP?";

    //查询和设置参考源的相位改变量
    public static String QPHAS = "PHAS?";
    public static String PHAS = "PHAS <x>";

    //查询参考源频率(HZ)
    public static String QFREQ = "FREQ?";
    //设置内部振动器的频率，只有在内部参考源模式下可以使用(Hz)
    public static String FREQ = "FREQ <f>";

    //查询和设置正弦信号输出的振幅（VL的振幅，单位 VRMS）(范围：0.004-5.000)
    public static String QSLVL = "SLVL?";
    public static String SLVL = "SLVL <x>";

    //查询和设置 输入情况（0：A，1：A-B，2：I）
    public static String QISRC = "ISRC?";
    public static String ISRC = "ISRC <i>";

    //查询输入信号的电压（R）（Vsig的振幅）（在相位相同的情况下，也可以查询 X(1) ：VsigCosΘ, R(3):Vsig）
    //单位:V
    public static String QOUTP = "OUTP? 3";

    //查询和设置CH1或CH2的输出：可选择X(Y)，R等
    public static String QFOUT = "FOUT? <ch>";
    public static String FOUT = "FOUT <ch>, <i>";

    //查询和设置本地和远程功能(0:本地模式, 1:远程模式, 2:LOCAL LOCKOUT
    public static String QLOCL = "LOCL?";
    public static String LOCL = "LOCL <i>";
}
