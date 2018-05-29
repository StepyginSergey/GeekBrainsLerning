package db;

import java.sql.*;

/**
 * Created by stepygin on 29.05.2018.
 */
public class DataBaseController {

    private Connection connection;
    PreparedStatement pStat;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:lesson2.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
            System.out.println("Отключение от БД");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(){
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS product ( id BIGINT PRIMARY KEY UNIQUE NOT NULL, prodid BIGINT UNIQUE NOT NULL, title TEXT, cost DECIMAL);";
            statement.execute(query);
            System.out.println("Таблица создана успешно!");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTable(){
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM product;";
            statement.execute(query);
            statement.close();
            System.out.println("Произведена очистка таблицы!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initBatch(){
        try {
            pStat = connection.prepareStatement("INSERT INTO product (id, prodid, title, cost) VALUES (?, ?, ?, ?);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillBatch(long id, long prodid, String title, float cost){
        try{
            pStat.setLong(1, id);
            pStat.setLong(2, prodid);
            pStat.setString(3, title);
            pStat.setFloat(4, cost);

            pStat.addBatch();
            //System.out.println("Запись добавлена  в пакет. #" + id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void loadBatch(){
        try {
            System.out.println("Загрузка пакета данных...");
            connection.setAutoCommit(false);
            pStat.executeBatch();
            System.out.println("Пакет данных загружен!");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void getPrice(String name){
        try {
            String query = "SELECT cost FROM product WHERE title = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery(query);

            if (rs.next()){
                System.out.println("Стоимость товара " + name + " равна " + rs.getFloat(0));
            }else {
                System.out.println("Такого товара нету!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
