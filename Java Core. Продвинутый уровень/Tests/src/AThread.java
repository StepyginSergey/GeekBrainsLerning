/**
 * Created by stepygin on 21.06.2018.
 */
public class AThread {

    public static void main(String[] args) {
        ExtandsThread et = new ExtandsThread();
        et.start();


        new Thread(new ThreadImplementsRunnable()).start();

    }

}
