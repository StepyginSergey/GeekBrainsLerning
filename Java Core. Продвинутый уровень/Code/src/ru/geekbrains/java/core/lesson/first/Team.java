package ru.geekbrains.java.core.lesson.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 
 * Добавить класс Team, который будет содержать название команды, массив из 4-х участников
(т.е. в конструкторе можно сразу всех участников указывать), метод для вывода информации
о членах команды, прошедших дистанцию, метод вывода информации обо всех членах
команды.
 * 
 * */

public class Team {
	
	//название команды
	private String teamName;
	//список учатников
	private String[] members;
	
	private List<String> membersFinishCourse = new ArrayList<String>();
	
	public Team (String teamName, String[] members){
		this.teamName = teamName;
		this.members = members; 
	}
	
	//метод вывода информации о членах команды прошедших дистанцию
	public void showAllMembersresult(){
		
		String membersString = "Members Finished: ";
		
		for(String s : membersFinishCourse){
			membersString += " | " + s;
		}
		
		System.out.println(membersString);
	}
	//метод вывода информации обовсех членах команды
	public void showAllMembers(){
		
		String membersString = "Members: ";
		
		for(int i = 0; i < members.length; i++){
			membersString += " | " + members[i];
		}
		
		System.out.println(membersString);
	}
	
	public int size(){
		return members.length;
	}
	
	public String[] getMembers(){
		return members;
	}
	
	public void setResult(String member, int time){
		membersFinishCourse.add(member + " with time: " + String.valueOf(time));	
	}
	
}
