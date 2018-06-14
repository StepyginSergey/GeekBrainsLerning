import java.util.Arrays;
import java.util.List;

/**
 * Created by stepygin on 07.06.2018.
 */
public class Task_1_Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        new NamedThread("B", controller).start();
        new NamedThread("C", controller).start();
        new NamedThread("A", controller).start();
    }
}

class NamedThread extends Thread {
    private String name;
    private Controller controller;

    public NamedThread(String name, Controller controller) {
        setName(name);
        this.controller = controller;
    }

    @Override
    public void run() {
        synchronized (controller) {
            for (int i = 0; i < 5; i++) {
                try {
                    while (!controller.getCurrent().equals(getName())) {
                        controller.wait();
                    }
                    System.out.print(getName());
                    controller.move();
                    controller.notifyAll();
                } catch (Exception e) {
                }
            }
        }
    }
}

class Controller {
    int pos = 0;
    final String[] list = {"A","B","C"};

    public String getCurrent() {
        return list[pos];
    }

    public void move() {
        pos = (pos == list.length - 1) ? 0 : pos + 1;
    }
}
