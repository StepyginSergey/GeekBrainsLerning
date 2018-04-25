package ru.geekbrains.java2.lesson1.l1.lesson1;

public interface Flyable {
    void fly();

    default void defaultFly(){
        System.out.println("defFly");
    }
}
