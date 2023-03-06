import com.la.SignalVISAController;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-02-19-11:40
 */
public class SignalVISAControllerTest {

    SignalVISAController signalVISAController;

    @Test
    public void testReadId(){
        signalVISAController = new SignalVISAController("ASRL2::INSTR");
        String id = signalVISAController.readID();
        System.out.println("device id: " + id);
        signalVISAController.closeController();
    }

    @Test
    public void testSetFrequency(){
        signalVISAController = new SignalVISAController("ASRL2::INSTR");
        signalVISAController.setSignalFrequency(1,2.6);
        signalVISAController.closeController();
    }

    @Test
    public void testOutputOn(){
        signalVISAController = new SignalVISAController("ASRL2::INSTR");
        signalVISAController.OutputOn(1);
        signalVISAController.closeController();
    }

    @Test
    public void testVISAController() throws Exception{
        signalVISAController = new SignalVISAController("ASRL2::INSTR");
        String id = signalVISAController.readID();
        System.out.println("device id:" + id);
        signalVISAController.initSignalChannel(1);
        signalVISAController.setSignalFrequency(1,2.6);
        Thread.sleep(50);
        signalVISAController.setSignalAmplitude(1,2.4);
        Thread.sleep(100);
        signalVISAController.OutputOn(1);
        Thread.sleep(5000);
        signalVISAController.OutputOff(1);
        Thread.sleep(20);
        signalVISAController.closeController();
    }
}
