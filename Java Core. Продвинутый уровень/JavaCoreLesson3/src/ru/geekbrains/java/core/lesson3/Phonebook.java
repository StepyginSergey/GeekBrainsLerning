package ru.geekbrains.java.core.lesson3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by stepygin on 04.05.2018.
 */
public class Phonebook {

    private ArrayList<Person> persons = new ArrayList<>();

    public void add(String name, String... phones){
        persons.add(new Person(name, new ArrayList<String>(Arrays.asList(phones))));
    }

    public void get(String name){
        ArrayList<String> list = new ArrayList<>();
        for (Person p : persons) {
            if(name.equals(p.getName())){
                list.add(p.getPhone());
            }
        }
        if(list.isEmpty()){
            System.out.println("Для авбонента " + name + " номеров телефона не найдено");
        }else{
            System.out.println("Для авбонента " + name + " найдены телефоны: " + list.toString());
        }

    }


    private class Person {
        private String name;
        private ArrayList<String> phones;

        public Person(String name, ArrayList<String> phones){
            this.name = name;
            this.phones = phones;
        }

        public String getName(){
            return name;
        }

        public String getPhone(){
            return phones.toString();
        }
    }
}
