import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

import java.util.*;

/**
 * Created by stepygin on 15.05.2018.
 */
public class Test {
    static String label = "5";
    public static void main(String[] args) {

        //Collection<String> map3 = new HashMap<>()
        NavigableMap<String, Integer> map = new TreeMap<>();
        HashMap<String, Integer> map1 = new LinkedHashMap<>();

/*
        int[] asf = new int[10];
        asf[20] = 10;
        asf[5] = asf[2] / 0;
*/
/*
        Object object = new Integer(10);
        String str = (String)object;
        System.out.println(str);
*/
        try{

        }catch(Throwable e){
            throw new Error();
        }

        int[] time = {5}; //Чтобы внутри события был доступен, делаем в виде массива.
        //String label = "5";
        //label.setText("5");
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1000 * 5), //1000 мс * 60 сек = 1 мин
                        ae -> {
                            time[0]--;
                            Platform.runLater(() -> {
                                label = "" + time;
                                System.out.println(label);
                            } );
                        }
                )
        );

        timeline.setCycleCount(5); //Ограничим число повторений
        timeline.play(); //Запускаем

    }
}
