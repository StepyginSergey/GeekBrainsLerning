package ru.geekbrains.java.core.lesson3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        String[] array = {"феномен", "ферма", "Фермий", "фермер", "формула",
                "фонтан", "ферма", "фортуна", "фонтан", " фокус", "фермер",
                "фанера", "форватер", "феномен", "фермер"};

        System.out.println("Размер массива: " + array.length);

        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            String strTemp = array[i];

            if (map.containsKey(strTemp)) {
                int value = map.get(strTemp);
                value++;
                map.put(strTemp, value);
            } else {
                map.put(strTemp, 1);
                list.add(strTemp);
            }
        }

        System.out.println("Список уникальных слов: " + list.toString());
        System.out.println("Размер списка: " + list.size());
        System.out.println("Список слов и колличество повторений: " + map.toString());

        {
            //блок добавлен в качестве эксперимента
            TreeSet<String> treeSet = new TreeSet<String>();
            for (int i = 0; i < array.length; i++) {
                String strTemp = array[i];
                treeSet.add(strTemp);
            }

            System.out.println("Список уникальных значений из TreeSet + сортировка: " + treeSet.toString());
        }

        System.out.println("*****************************");
        System.out.println();
        System.out.println("Телефонный справочник:");
        {
            //Пример работы со справочником
            Phonebook book = new Phonebook();

            book.add("Петр", "987346723","2457356847");
            book.add("Дмитрий", "68431354");
            book.add("Александр", "358446351");
            book.add("Петр", "4684680");
            book.add("Петя", "2573579","89758364");

            book.get("Петр");
            book.get("Александр");
            book.get("Толя");
        }
    }
}
