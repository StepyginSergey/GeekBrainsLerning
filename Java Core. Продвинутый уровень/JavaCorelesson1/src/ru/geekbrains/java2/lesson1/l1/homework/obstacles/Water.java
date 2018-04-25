package ru.geekbrains.java2.lesson1.l1.homework.obstacles;

import ru.geekbrains.java2.lesson1.l1.homework.competitors.Animal;
import ru.geekbrains.java2.lesson1.l1.homework.competitors.Competitor;

public class Water extends Obstacle {
    private int distance;

    public Water(int distance) {
        this.distance = distance;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(distance);
    }
}
