package ru.geekbrains.java.core.lesson.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 
 * �������� ����� Team, ������� ����� ��������� �������� �������, ������ �� 4-� ����������
(�.�. � ������������ ����� ����� ���� ���������� ���������), ����� ��� ������ ����������
� ������ �������, ��������� ���������, ����� ������ ���������� ��� ���� ������
�������.
 * 
 * */

public class Team {
	
	//�������� �������
	private String teamName;
	//������ ���������
	private String[] members;
	
	private List<String> membersFinishCourse = new ArrayList<String>();
	
	public Team (String teamName, String[] members){
		this.teamName = teamName;
		this.members = members; 
	}
	
	//����� ������ ���������� � ������ ������� ��������� ���������
	public void showAllMembersresult(){
		
		String membersString = "Members Finished: ";
		
		for(String s : membersFinishCourse){
			membersString += " | " + s;
		}
		
		System.out.println(membersString);
	}
	//����� ������ ���������� ������� ������ �������
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
