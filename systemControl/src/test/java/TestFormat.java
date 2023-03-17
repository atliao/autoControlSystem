import java.text.DecimalFormat;

/**
 * @author LA
 * @createDate 2023-03-17-11:48
 */
public class TestFormat {

    public static void main(String[] args) {
        double t = 2.3546;
        DecimalFormat df = new DecimalFormat("0.00");
        String res = df.format(t);
        System.out.println(res);
    }
}
