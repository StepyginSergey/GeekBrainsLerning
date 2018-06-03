import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileTest {
    public static void main (String[] args) {
        try {
            DataOutputStream out = new DataOutputStream( new
                    FileOutputStream( "file.txt" ));
            out.writeInt( 128 );
            out.writeLong( 128 );
            out.close();
            DataInputStream in = new DataInputStream( new FileInputStream( "file.txt" ));
            System.out.println(in.readInt());
            System.out.println(in.readLong());
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
