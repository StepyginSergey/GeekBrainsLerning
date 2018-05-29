import db.DataBaseController;

import java.util.Scanner;

/**
 * Created by stepygin on 29.05.2018.
 */
public class Main {

    public static void main(String[] args) {
        DataBaseController db = new DataBaseController();
        db.connect();
        db.createTable();

        db.clearTable();
        long time = System.currentTimeMillis();
        db.initBatch();
        for(int i = 0; i < 10_000; i++){
            db.fillBatch(i, "Product_" + i, (i+1)*10);
        }
        System.out.println("Время формирования пакета:" + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        db.loadBatch();

        System.out.println("Время загрузки пакета:" + (System.currentTimeMillis() - time));


        while (true){
            System.out.println("Введите команду:");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();

            if(str.startsWith("/exit")){
                break;
            }
            if(str.startsWith("/price")){
                // /price Product_13
                db.getPrice(str.split("\\s", 2)[1]);
            }
            if(str.startsWith("/changeprice")){
                //  /changeprice Product_13 500
                String[] data = str.split("\\s");
                db.changePrice(data[1], Float.valueOf(data[2]));
            }
            if(str.startsWith("/byprice")){
                //  /byprice 100 160
                String[] data = str.split("\\s");
                db.byPrice(Float.valueOf(data[1]), Float.valueOf(data[2]));
            }
        }

        db.disconnect();
    }
}
