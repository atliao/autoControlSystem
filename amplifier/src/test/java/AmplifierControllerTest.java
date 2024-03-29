import com.la.AmplifierController;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-03-01-11:46
 */
public class AmplifierControllerTest {

    AmplifierController amplifierController;

    @Test
    public void test(){
        String a = "aa\r";
        String num = "0.0199891\r";
        num = num.substring(0, num.length()-1);
        System.out.println(num + "mV");
    }
    @Test
    public void testReadID() throws Exception {
        amplifierController = new AmplifierController("COM14");
        amplifierController.initAmplifier();
        //String res = amplifierController.readID();
        amplifierController.setReferencePhas(100);
        amplifierController.setReferenceFreq(30);
        //Thread.sleep(1000);
        //String res = amplifierController.QueryReferenceFreq();
        String res = amplifierController.QuerySourceAmplitude();
        System.out.println(res);
        amplifierController.closeController();
    }


    @Test
    public void testQuery() throws Exception {
        amplifierController = new AmplifierController("COM4");

        String reference = amplifierController.QueryReference();
        System.out.println("参考源: " + reference);

        String referenceFreq = amplifierController.QueryReferenceFreq();
        System.out.println("参考频率: " + referenceFreq);

        String referencePhas = amplifierController.QueryReferencePhas();
        System.out.println("参考相位: " + referencePhas);

        String referenceSlop = amplifierController.queryReferenceSlop();
        System.out.println("参考源斜率: " + referenceSlop);

        String sineAmplitude = amplifierController.QuerySineAmplitude();
        System.out.println("参考正弦输出振幅: " + sineAmplitude);

        String sourceIn = amplifierController.QuerySourceIn();
        System.out.println("信号输入源模式: " + sourceIn);

        amplifierController.closeController();

    }

    @Test
    public void testSetting() throws InterruptedException {
        amplifierController = new AmplifierController("ASRL6::INSTR");

        //设置参考源(2:外部参考源)
        amplifierController.setReference(2);

        //设置参考频率（仅内部振荡）
        amplifierController.setReferenceFreq(1000);

        //设置参考相位
        amplifierController.setReferencePhas(0);

        //设置正弦输出振幅
        amplifierController.setSineAmplitude(2.0);

        //设置输入模式 A:0
        amplifierController.setSourceIn(0);

        amplifierController.closeController();
    }

    @Test
    public void testReadSourceAmplitude() throws Exception {
        amplifierController = new AmplifierController("COM4");
        amplifierController.initAmplifier();
        amplifierController.setReferenceFreq(1000);
        Thread.sleep(5000);
        String sourceAmplitude = amplifierController.QuerySourceAmplitude();
        Double res = Double.valueOf(sourceAmplitude);
        System.out.println("测量振幅：" + res + " mV");
        amplifierController.closeController();
    }
}
