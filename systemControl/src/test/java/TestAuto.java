import com.la.Auto;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-03-16-11:48
 */
public class TestAuto {

    @Test
    public void test(){
        double sensitivity1 = 8 / (3.34375 * 2);
        double sensitivity2 = 8 / (3.34 * 2);
        double x = Math.abs((sensitivity1 - sensitivity2) / sensitivity2);
        System.out.println(x);
    }

    public static void main(String[] args) throws Exception {
        Auto auto = new Auto();
        double sensitivity = auto.AutoProcess(1000, 3, 0);
        System.out.println(sensitivity);
    }

}
