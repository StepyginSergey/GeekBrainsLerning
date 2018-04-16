import java.util.Scanner;

public class guess_number {

	private static Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		
		System.out.println("���� ������ ������� �����.");
		
		int range = 10;

		int number = (int) (Math.random() * range);
		
		playLevel(range, number);
		
		scanner.close();
	}
	
	private static void playLevel(int range, int number){
		while(true){
			System.out.println("�������� ����� �� 0 �� " + range);
			int input_number = scanner.nextInt();
			if(input_number == number){
				System.out.println("�� ������� �����!!!");
				break;
			} else if(input_number > number){
				System.out.println("���������� ����� ������!");
			}else if(input_number < number){
				System.out.println("���������� ����� ������!");
			}
		}
	}

}
