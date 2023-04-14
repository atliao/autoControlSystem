import java.text.DecimalFormat;

/**
 * @author LA
 * @createDate 2023-03-17-11:48
 */
public class TestFormat {

    public static void main(String[] args) {
        double t = 22.3546;
        DecimalFormat df = new DecimalFormat("0.000");
        String res = df.format(t);
        System.out.println(res);
    }
}
