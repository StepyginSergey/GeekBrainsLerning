package ru.geekbrains.java.core.lesson.first;

/*
 * Добавить класс Course (полоса препятствий), в котором будут находиться: массив
препятствий, метод, который будет просить команду пройти всю полосу.
 * */

public class Course {

	private String[] course;
	
	public Course(String[] course){
		this.course = course;
	}
	
	
	public void showCourse(){
		String courseLine = "Course: ";
		
		for(int i = 0; i < course.length; i++){
			courseLine += " | " + course[i];
		}
		
		System.out.println(courseLine);
	}
	
	public void doIt(Team t){
		String[] members = t.getMembers();
		for(int i = 0; i < members.length; i++){
			t.setResult(members[i], (int)(Math.random() * 100));
		}
		
		
	}
}
