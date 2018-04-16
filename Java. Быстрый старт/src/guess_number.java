import java.util.Scanner;

public class guess_number {

	private static Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		
		System.out.println("Ваша задача угадать число.");
		
		int range = 10;

		int number = (int) (Math.random() * range);
		
		playLevel(range, number);
		
		scanner.close();
	}
	
	private static void playLevel(int range, int number){
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
