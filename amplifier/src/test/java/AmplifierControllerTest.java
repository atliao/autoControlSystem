import com.la.AmplifierController;
import org.junit.Test;

import javax.xml.transform.Source;
import java.sql.SQLOutput;

/**
 * @author LA
 * @createDate 2023-03-01-11:46
 */
public class AmplifierControllerTest {

    public static void main(String[] args) throws Exception {
        AmplifierController amplifierController = new AmplifierController("COM2");
        //查询ID
        String id = amplifierController.QueryID();
        System.out.println("设备id: " + id);
        //System.in.read();
        Thread.sleep(200);

        //查询参考源
        String refrence = amplifierController.QueryReference();
        System.out.println("参考源refrence: " + refrence);
        //System.in.read();
        Thread.sleep(200);

        //设置参考源
        amplifierController.setReference(0);
        //System.in.read();
        Thread.sleep(200);

        //查询相位
        String phas = amplifierController.QueryReferencePhas();
        System.out.println("参考源相位偏移: " + phas);
        //System.in.read();
        Thread.sleep(200);

        //设置参考源相位
        amplifierController.setReferencePhas(0);
        //System.in.read();
        Thread.sleep(200);

        //查询参考源频率
        String freq = amplifierController.QueryReferenceFreq();
        System.out.println("参考源频率: " + freq);
        //System.in.read();
        Thread.sleep(200);

        //设置参考源频率
        amplifierController.setReferenceFreq(1000);
        //System.in.read();
        Thread.sleep(200);

        //查询输出正弦信号振幅
        String sineAmplitude = amplifierController.QuerySineAmplitude();
        System.out.println("输出正弦信号振幅sineAmplitude: " + sineAmplitude);
        //System.in.read();
        Thread.sleep(200);

        //设置输出正弦信号振幅
        amplifierController.setSineAmplitude(3.0);
        //System.in.read();
        Thread.sleep(200);

        //查询输入源模式
        String sourceIn = amplifierController.QuerySourceIn();
        System.out.println("输入源模式sourceIn: " + sourceIn);
        //System.in.read();
        Thread.sleep(200);

        //设置输入源模式
        amplifierController.setSourceIn(0);
        //System.in.read();
        Thread.sleep(200);

        //查询输入信号的振幅（V）
        String sourceAmplitude = amplifierController.QuerySourceAmplitude();
        System.out.println("输入信号振幅sourceAmplitude: " + sourceAmplitude);
        //System.in.read();
        Thread.sleep(200);

        //查询输出情况
        String outSignal = amplifierController.QueryOutSignal(1);
        System.out.println("CH1输出情况outSignal: " + outSignal);
        //System.in.read();
        Thread.sleep(200);

        //设置输出情况
        amplifierController.setOutSignal(1, 1);
        //System.in.read();
        Thread.sleep(200);

        //查询控制功能
        String loclalFounction = amplifierController.QueryLoclalFounction();
        System.out.println("控制模式localFounction: " + loclalFounction);
        //System.in.read();
        Thread.sleep(200);

        //设置控制功能
        amplifierController.setLocalFounction(1);
        //System.in.read();
        Thread.sleep(200);

        //关闭接口
        amplifierController.closeController();
    }
}
