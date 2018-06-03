import java.io.*;

public class Task_1_Main {



    public static void main(String[] args) {
        FileInputStream in;
        createFile("task_1.txt", 50);
    }

    public static void createFile(String fileName,  int size){
        //DataOutputStream out;
        ByteArrayOutputStream out;

        byte[] array = new byte[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = (byte) i;
        }

        try {
            //out = new DataOutputStream(new FileOutputStream(fileName));
            out = new ByteArrayOutputStream(size);

            out.write(array);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
