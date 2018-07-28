import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedTest {
    public static void main(String[] args) {
        PipedInputStream in = null;
        PipedOutputStream out = null;
        try{
            in = new PipedInputStream();
            out = new PipedOutputStream();
            out.connect(in);

            for (int i = 0; i < 100; i++) {
                out.write(i);
            }

            int x;
            while((x = in.read()) != -1){
                System.out.print(x + " ");
            }
            in.close();
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
