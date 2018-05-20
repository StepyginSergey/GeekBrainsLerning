import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    static int i = 1;
    public static void main(String[] args) {

        System.out.println("код перед таймера");

        startTimer();

        System.out.println("код после таймера");

    }


    private static void startTimer(){

        Timer timer = new Timer();

        timer.schedule(
                new TimerTask() {

                    @Override
                    public void run() {

                        System.out.println("ping " + i);
                        if(i == 5){
                            System.out.println("Exit");
                            try {
                                this.cancel();
                                timer.cancel();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }

                        }else {
                            System.out.println("Increment");
                            i++;
                        }

                    }
                }, 0, 2000);

    }
}
