package choose;
import java.util.Scanner;
public class ChooseBbeven {
	public static void main(String[]args){
		System.out.print("please input a integer");
		Scanner input= new Scanner(System.in);
		int x=input.nextInt();
		System.out.println("The number "+x+"is a even number?"+(x%2==0));
		
	}
}
