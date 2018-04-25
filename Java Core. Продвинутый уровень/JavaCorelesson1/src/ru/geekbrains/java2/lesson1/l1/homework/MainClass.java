package ru.geekbrains.java2.lesson1.l1.homework;

import ru.geekbrains.java2.lesson1.l1.homework.competitors.*;
import ru.geekbrains.java2.lesson1.l1.homework.obstacles.Cross;
import ru.geekbrains.java2.lesson1.l1.homework.obstacles.Obstacle;
import ru.geekbrains.java2.lesson1.l1.homework.obstacles.Wall;
import ru.geekbrains.java2.lesson1.l1.homework.obstacles.Water;

public class MainClass {
    public static void main(String[] args) {
        Competitor[] competitors = {new Cat("Барсик"), new Dog("Бобик"), new Human("Боб")};
        Obstacle[] course = {new Cross(400), new Wall(3), new Water(8)};

        for (Competitor с: competitors){
            for (Obstacle o: course){
                o.doIt(с);
                if (!с.isOnDistance()) break;
            }
        }

        System.out.println("=========");
        for (Competitor с: competitors){
            с.showResult();
        }
    }
}
