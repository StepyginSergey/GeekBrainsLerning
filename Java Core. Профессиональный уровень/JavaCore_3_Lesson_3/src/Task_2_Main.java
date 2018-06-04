import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by stepygin on 04.06.2018.
 */
public class Task_2_Main {

    public static void main(String[] args) {
        FileInputStream in1 = null, in2 = null, in3 = null, in4 = null, in5 = null;
        SequenceInputStream seq = null;
        FileOutputStream out = null;
        try {

            ArrayList<InputStream> al = new ArrayList<>();

            al.add(new FileInputStream("task_2_1.txt"));
            al.add(new FileInputStream("task_2_2.txt"));
            al.add(new FileInputStream("task_2_3.txt"));
            al.add(new FileInputStream("task_2_4.txt"));
            al.add(new FileInputStream("task_2_5.txt"));

            Enumeration<InputStream> e = Collections.enumeration(al);

            seq = new SequenceInputStream(e);

            out = new FileOutputStream("task_2_All.txt");
            int rb = seq.read();
            while (rb != -1) {
                out.write(rb);
                rb = seq.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { seq.close();  } catch (IOException e) { }
            try { out.close();  } catch (IOException e) { }
        }
    }


}
