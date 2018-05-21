/**
 * Created by stepygin on 21.05.2018.
 */
public class ThreadTest {
    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("выполнение потока");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        try {
            t.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.start();


    }

    class MyException extends Exception{
        private String message;
        public MyException(String msg){
            this.message = msg;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

}
