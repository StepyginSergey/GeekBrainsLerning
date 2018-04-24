package ru.geekbrains.java.core.lesson.first;

public class Main {

	public static void main(String[] args) {
		
		Course c = new Course (new String[] {"Бассейн","горка", "стена", "тарзанка"}); // Создаем полосу препятствий
		Team team = new Team ("komanda A", new String[] {"Vasya","Kolya", "Dasha", "Masha"});
		
		team.showAllMembers();
		
		c.doIt ( team ); // Просим команду пройти полосу
		team.showAllMembersresult(); // Показываем результаты
		
	}

}
