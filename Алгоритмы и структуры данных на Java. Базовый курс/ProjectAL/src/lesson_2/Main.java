package lesson_2;

import java.util.Scanner;

public class Main {

    private static long start;
    private static long finish;

    public static void main(String[] args) {
        MySortedArrayList<Integer> arraySort = new MySortedArrayList<>();
        MyArrayList<Integer> array = new MyArrayList();

        System.out.println("Введите:");
        System.out.println("1 - Создание Сортированного массива");
        System.out.println("2 - Создание Обычного массива");
        System.out.println("3 - Сортировка обычного массива методом выбора");
        System.out.println("4 - Сортировка обычного массива методом вставки");
        System.out.println("5 - Бинарный поиск в сортированном массиве");
        System.out.println("6 - Линейный поиск в массиве");
        System.out.println("0 - Выход");

        Scanner input = new Scanner(System.in);
        int operation = input.nextInt();
        while (operation != 0){
            int number;
            switch (operation){
                case 1:
                    arraySort = new MySortedArrayList<>();
                    System.out.println("Введите размер создаваемого сортированного массива");
                    number = input.nextInt();

                    start = System.currentTimeMillis();
                    for (int i = 0 ; i < number; i++){
                        arraySort.insert( i  * (int)(Math.random() * 10));
                    }
                    finish = System.currentTimeMillis();
                    time("Время на создание сортированного массива", start, finish);
                    break;

                case 2:
                    array = new MyArrayList<>();
                    System.out.println("Введите размер создаваемого массива");
                    number = input.nextInt();

                    start = System.currentTimeMillis();
                    for (int i = 0 ; i < number; i++){
                        array.insert( i  * (int)(Math.random() * 10));
                    }
                    finish = System.currentTimeMillis();
                    time("Время на создание обычного массива", start, finish);
                    break;

                case 3:
                    start = System.currentTimeMillis();
                    array.insertionSort();
                    finish = System.currentTimeMillis();
                    time("Время затраченное на сортировку методом Вставки", start, finish);
                    break;
                case 4:
                    start = System.currentTimeMillis();
                    array.selectionSort();
                    finish = System.currentTimeMillis();
                    time("Время затраченное на сортировку методом Выбора", start, finish);
                    break;

                case 5:
                    System.out.println("Введите число от 0 до " + arraySort.size() +  ", которое хотите найти:");
                    number = input.nextInt();

                    start = System.currentTimeMillis();
                    arraySort.find(number);
                    finish = System.currentTimeMillis();
                    time("Бинарный поиск. Время на поиск", start, finish);
                    break;

                case 6:
                    System.out.println("Введите число от 0 до " + arraySort.size() +  ", которое хотите найти:");
                    number = input.nextInt();

                    start = System.currentTimeMillis();
                    array.find(number);
                    finish = System.currentTimeMillis();
                    time("Линейный поиск. Время на поиск", start, finish);
                    break;

                default: break;

            }

            operation = input.nextInt();
        }

        System.out.println("Выход из приложения");
    }


    private static void time(String message, long start, long finish){
        System.out.println (message + ": " + (finish - start) + " ms");
    }
}
