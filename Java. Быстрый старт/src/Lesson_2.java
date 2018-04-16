import java.util.Scanner;

public class Lesson_2 {

	public static void main(String[] args) {
		/*
		System.out.println("Введите число:");
		//чтение из консоли
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		System.out.println("a = " + a);
		*/
		
		
		System.out.println("Введите операцию:");
		System.out.println("1 - Сложение");
		System.out.println("2 - Вычитание");
		System.out.println("3 - Умножение");
		System.out.println("4 - Деление");
		Scanner scanner = new Scanner(System.in);
		int operation = scanner.nextInt();
		
		System.out.println("Введите первое число:");
		int a = scanner.nextInt();
		
		System.out.println("Введите второе число:");
		int b = scanner.nextInt();
		
		int result;
		
		if(operation == 1){
			result = a + b;
		} else if(operation == 2){
			result = a - b;
		} else if(operation == 3){
			result = a * b;
		}else {
			if(b == 0){
				result = a;
			}else {
				result = a / b;
			}
		}
		
		
		System.out.println("Результат: " + result);
	}

}
