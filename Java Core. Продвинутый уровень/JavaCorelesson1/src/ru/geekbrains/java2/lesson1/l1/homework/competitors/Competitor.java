package ru.geekbrains.java2.lesson1.l1.homework.competitors;

public interface Competitor {
    String getName();
    void run(int distance);
    void swim(int distance);
    void jump(int height);
    boolean isOnDistance();
    void showResult();
}
