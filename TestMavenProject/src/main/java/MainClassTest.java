/**
 * Created by stepygin on 06.07.2018.
 */
public class MainClassTest {

    public static void main(String[] args) {
        System.out.println("Start project");

        int vklad = 5000;
        int month = 0;

        while (vklad < 50000){
            month += 1;
            int bonus = vklad / 10000;
            vklad += bonus * 1000;
            vklad += 2000;
            System.out.println(
                    "summa vklada: " + vklad
                    + "--- number mounth:" + month
                    + " bonus - " + bonus);
        }

        System.out.println("Finish project!");
    }

}
