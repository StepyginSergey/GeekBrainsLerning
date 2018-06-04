import java.io.*;

public class Task_1_Main {


    public static void main(String[] args) {
        createFile("task_1.txt", 50);
        readFile("task_1.txt");
    }

    public static void createFile(String fileName, int size) {

        ByteArrayOutputStream outTest = new ByteArrayOutputStream();

        for (int i = 0; i < size; i++) {
            outTest.write(i);
        }
        byte[] arr = outTest.toByteArray();

        try {
            DataOutputStream outFile = new DataOutputStream(new FileOutputStream(fileName));
            outFile.write(arr);
            outFile.close();
            System.out.println("Создан файл");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String fileName) {
        try {
            byte [] br = new byte [50];
            FileInputStream in = new FileInputStream(fileName);
            int x;
            while ((x = in.read()) != - 1 ) {
                System.out.print(x + " " );
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
