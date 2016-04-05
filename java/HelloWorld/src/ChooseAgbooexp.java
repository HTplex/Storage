
import java.util.Scanner;
public class ChooseAgbooexp {
public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	System.out.print("please input a number");
	int x=input.nextInt();
	System.out.print("the number "+x+" you just input is a "+((x%2==0)?"even":"odd")+" number");
}

}
