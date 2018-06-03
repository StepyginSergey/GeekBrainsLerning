import java.util.Arrays;
import java.util.TreeSet;

public class IntegerSorts {

    public static void main(String[] args) {
        int[] arr = {15, -30, 100, 30, 1};

        System.out.println(arr);

    }


    public static int[] sotr(int[] a){
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < a.length; i++) {
            set.add(a[i]);
        }

        Integer[] toArray = set.toArray(new Integer[set.size()]);

        return Arrays.stream(set.toArray(new Integer[set.size()])).mapToInt(Integer::intValue).toArray();
    }
}
