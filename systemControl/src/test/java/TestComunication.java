import com.la.SystemController;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-03-06-17:19
 */
public class TestComunication {

    public static void main(String[] args) {
        SystemController systemController = new SystemController();
        try {
            systemController.initSystem();
            systemController.readIDs();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
