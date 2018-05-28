/**
 * Created by stepygin on 28.05.2018.
 */
public class Task_3_Main {

    public static void main(String[] args) {
        //Указываем характеристики яблок
        Apple apple = new Apple();
        apple.setWeight(1.0f);

        //Определяем коробку для яблок
        Box<Apple> aBox = new Box(apple);
        aBox.showType();
        //Заполняем коробку яблоками
        for (int i = 0; i < 9; i++) {
            aBox.addFruit(apple);
        }
        System.out.println("Коробка с Яблоками " + aBox.getWeight());

        //Задаем характеристики Апельсинов
        Orange orange = new Orange();
        orange.setWeight(1.5f);

        //Определяем коробку для апельсинов и заполняем ее
        Box<Orange> oBox = new Box(orange);
        oBox.showType();
        for (int i = 0; i < 6; i++) {
            oBox.addFruit(orange);
        }
        System.out.println("Коробка с Апельсинами " + oBox.getWeight());

        System.out.println("Сравниваем вес коробок " + oBox.compare(aBox));


        Box<Orange> oBox2 = new Box(orange);
        oBox2.showType();
        for (int i = 0; i < 3; i++) {
            oBox2.addFruit(orange);
        }
        System.out.println("Коробка Вторая с Апельсинами " + oBox2.getWeight());

        oBox2.pepper(oBox);
        System.out.println("Вторая коробка с Апельсинами после пересыпания в нее первой " + oBox2.getWeight());

        System.out.println("Первая коробка с Апельсинами " + oBox.getWeight());

        System.out.println("Сравниваем вес второй коробки с апельсинами и коробки с яблоками " + oBox.compare(aBox));
    }
}
