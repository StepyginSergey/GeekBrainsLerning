package ru.geekbrains.java2.lesson1.l1.homework.obstacles;

import ru.geekbrains.java2.lesson1.l1.homework.competitors.Animal;
import ru.geekbrains.java2.lesson1.l1.homework.competitors.Competitor;


public class Wall extends Obstacle{
private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.jump(height);
    }
}
