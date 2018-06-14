import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by stepygin on 14.06.2018.
 */
public class Task_2_Main {

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        try (FileWriter out = new FileWriter("out.txt")) {
            for (int j = 0; j < 3; j++) {
                int finalJ = j;
                Thread t = new Thread(() -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            out.write("Поток " + finalJ + " строка " + i + "\n");
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
                threads.add(t);
            }
            for (Thread t : threads) {
                t.join();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
