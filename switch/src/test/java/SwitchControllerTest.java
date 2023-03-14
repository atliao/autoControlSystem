import com.la.SwitchController;

/**
 * @author LA
 * @createDate 2023-03-14-18:08
 */
public class SwitchControllerTest {

    public static void main(String[] args) throws InterruptedException {
        SwitchController switchController = new SwitchController("ASRL2::INSTR");
        Thread.sleep(200);

        switchController.initSwitch();
        Thread.sleep(200);

        String id = switchController.readID();
        System.out.println(id);
        Thread.sleep(200);

        String closeChannel = switchController.readClose();
        System.out.println("closeChannel: " + closeChannel);
        Thread.sleep(200);

        switchController.closeChannel(100);
        Thread.sleep(200);

        switchController.openChannel(100);
        Thread.sleep(200);

        switchController.closeChannel(101);
        Thread.sleep(200);

        switchController.openChannel(101);
        Thread.sleep(200);
    }
}
