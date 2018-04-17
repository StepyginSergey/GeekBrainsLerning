import java.util.Scanner;

public class guess_number {

	private static Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		System.out.println("Ваша задача угадать число.");
		
		for(int i = 10; i <= 30; i+= 10){
			playLevel(i);
		}
		
		System.out.println("Вы выйграли!!!!");
		
		scanner.close();
		
		
	}
	
	private static void playLevel(int range){
		
		int number = (int) (Math.random() * range);
		
		while(true){
			System.out.println("Угодайте число от 0 до " + range);
			int input_number = scanner.nextInt();
			if(input_number == number){
				System.out.println("Вы угадали число!!!");
				break;
			} else if(input_number > number){
				System.out.println("Загаданное число меньше!");
			}else if(input_number < number){
				System.out.println("Загаданное число больше!");
			}
		}
	}

}
