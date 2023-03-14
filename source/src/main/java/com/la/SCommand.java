package com.la;

/**
 * @author LA
 * @createDate 2023-02-18-13:31
 */
public class SCommand {

    public static char nl = '\n';

    //两通道独立(345ms)
    public static String channel_mode = ":CHANne<channel>:MODE INDependent";

    //设置波形（正弦波）(100ms)
    public static String signal_polarity = ":OUTPut<channel>:POLarity SINusoid, NORMal";

    //设置极性（双极性）(100ms)
    public static String signal_scale = ":OUTPut<channel>:SCALe SINusoid, FS";

    //设置模式（连续）(90ms)
    public static String signal_continuous_mode = ":SOURce<channel>:CONTinuous:IMMediate";
    public static String query_signal_continuous_mode = ":SOURce<channel>:CONTinuous:STATe?";

    //设置频率的模式（连续or扫描）(180ms)
    public static String signal_frequncy_mode = ":SOURce<channel>:FREQuency:MODE CW";
    public static String query_signal_frequncy_mode = ":SOURce<channel>:FREQuency:MODE?";

    //设置连续模式的频率(45ms)
    public static String signal_frequency = ":SOURce<channel>:FREQuency:CW <freq> KHZ";
    public static String query_signal_frequency = ":SOURce<channel>:FREQuency:CW?";

    //设置振幅的模式（固定（连续）or扫描）(230ms)
    public static String signal_amplitude_mode = ":SOURce<channel>:VOLTage:LEVel:IMMediate:AMPLitude:MODE FIXed";
    public static String query_signal_amplitude_mode = ":SOURce<channel>:VOLTage:LEVel:IMMediate:AMPLitude:MODE?";

    //设置振幅(95ms)
    public static String signal_amplitude =":SOURce<channel>:VOLTager:LEVel:IMMediate:AMPLitude <amp>VPP";
    public static String query_signal_amplitude =":SOURce<channel>:VOLTager:LEVel:IMMediate:AMPLitude?";

    //设置相位(20ms) 单位 °
    public static String signal_phase = ":SOURce<channel>:PHASe <phase>DEG";
    public static String query_signal_phase = ":SOURce<channel>:PHASe?";

    //设置开关（开启和关闭信道）(15ms)
    public static String channel_on = ":OUTPut<channel>:STATe ON";
    public static String channel_off = ":OUTPut<channel>:STATe OFF";

}
