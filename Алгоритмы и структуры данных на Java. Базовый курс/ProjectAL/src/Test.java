import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        Object  o = new Object();


        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());
        System.out.println(5871714 / 1000 / 60);

        System.out.println(TimeUnit.MILLISECONDS.toMinutes(5871714));
    }
}
