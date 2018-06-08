/**
 * Created by stepygin on 07.06.2018.
 */
public class Task_1_Main {

    public static void main(String[] args) {

        Object obj = new Object();
        obj.equals(obj);
        obj.hashCode();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i++){
                    System.out.print("A");

                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i++){
                    System.out.print("B");

                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i++){
                    System.out.print("C");

                }
            }
        }).start();


    }
}
