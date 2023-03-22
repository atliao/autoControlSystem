import com.la.SwitchController;
import com.la.VISA.VISA;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-03-14-18:08
 */
public class SwitchControllerTest {

    SwitchController switchController;

    @Test
    public void testReadID() throws InterruptedException {
        switchController = new SwitchController("ASRL4::INSTR");
        String readID = switchController.readID();
        System.out.println("device id: " + readID);
        switchController.closeController();
    }

    @Test
    public void testQueryParameter(){
        switchController = new SwitchController("ASRL2::INSTR");
        String parameter = switchController.queryParameter();
        System.out.println(parameter);
        switchController.closeController();
    }

    @Test
    public void testQueryClose() throws InterruptedException {
        switchController = new SwitchController("ASRL4::INSTR");
        String queryClose = switchController.queryClose();
        System.out.println(queryClose);
        switchController.closeController();
    }

    @Test
    public void testCloseAndOpen() throws InterruptedException {
        switchController = new SwitchController("ASRL2::INSTR");
        //switchController.initSwitch();
        switchController.closeChannel(100);
        Thread.sleep(5000);

        switchController.openChannel(100);
        Thread.sleep(200);

        switchController.closeChannel(101);
        Thread.sleep(5000);

        switchController.openChannel(101);
        Thread.sleep(200);

        switchController.closeController();
    }
}
