package ru.geekbrains.java2.lesson1.l1.homework;

import ru.geekbrains.java2.lesson1.l1.homework.competitors.Competitor;
import ru.geekbrains.java2.lesson1.l1.homework.obstacles.Obstacle;

/**
 * Created by stepygin on 25.04.2018.
 */
public class Course {

    Obstacle[] course;

    public Course(Obstacle[] course){
        this.course = course;
    }

    public void doIt(Team team){
        for (Competitor с: team.getCompetitors()){
            for (Obstacle o: course){
                o.doIt(с);
                if (!с.isOnDistance()) break;
            }
        }
    }
}
