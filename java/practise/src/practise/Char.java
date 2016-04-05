package practise;
	import java.util.Scanner;
	public class Char {
		public static void main (String[]args){
			Scanner input= new Scanner(System.in);
			System.out.print("please input the unicode you want to thansform");
			int x=input.nextInt();
	char y=(char)x;
			System.out.println("the correspond byte of your unicode is   "+y);
	
		
}

	}