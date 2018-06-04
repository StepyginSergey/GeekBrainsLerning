import java.io.*;
import java.util.Scanner;

/**
 * Created by stepygin on 04.06.2018.
 */
public class Task_3_Main {
    public static void main(String[] args) {
/*        try {
            OutputStream out = new BufferedOutputStream( new FileOutputStream( "file.txt" ));
            for ( int i = 0 ; i < 1000000 ; i++)
                out.write(i);
            out.close();
           // InputStream in = new BufferedInputStream( new FileInputStream( "file.txt" ), 1800);
           // int x;
           // while ( (x = in.read()) != - 1 ) {
           //     System.out.println(x);
           // }
            //in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        try {
            byte[] buffer = new byte[1800];
            BufferedInputStream bufferedInput = new BufferedInputStream(new FileInputStream("file.txt"));
            int bytesRead = 0;
            while ((bytesRead = bufferedInput.read(buffer)) != -1) {
                String chunk = new String(buffer, 0, bytesRead);
                System.out.print(chunk);

                System.out.println();
                System.out.println();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Используйте Enter для выводв следующей страницы: ");
                String s = scanner.nextLine();
            }
            bufferedInput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
