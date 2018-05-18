import java.util.*;

/**
 * Created by stepygin on 15.05.2018.
 */
public class Test {
    public static void main(String[] args) {

        //Collection<String> map3 = new HashMap<>()
        NavigableMap<String, Integer> map = new TreeMap<>();
        HashMap<String, Integer> map1 = new LinkedHashMap<>();


        int[] asf = new int[10];
        asf[20] = 10;
        asf[5] = asf[2] / 0;

        try{
            return;
        }finally {
            System.out.println("Final");
        }


    }
}
