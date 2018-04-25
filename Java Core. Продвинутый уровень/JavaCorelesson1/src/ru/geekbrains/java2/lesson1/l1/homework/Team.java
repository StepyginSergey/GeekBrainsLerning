package ru.geekbrains.java2.lesson1.l1.homework;

import ru.geekbrains.java2.lesson1.l1.homework.competitors.Competitor;

/**
 * Created by stepygin on 25.04.2018.
 */
public class Team {

    private Competitor[] competitors;

    public Team(Competitor[] c){
        this.competitors = c;
    }

    //информация обо всех участниках команды
    public void showResult(){
        for (Competitor с: competitors){
            с.showResult();
        }
    }

    public Competitor[] getCompetitors(){
        return competitors;
    }

    public void showCompetitorsCompletedCourse(){
        for (Competitor с: competitors){
            if(с.isOnDistance()){
                System.out.println(с.getName() + " прошел полосу препятствий!");
            }
        }
    }

}
