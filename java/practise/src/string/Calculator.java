package string;
import java.util.Scanner;
public class Calculator {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		args=new String[3];
		for(int i=0;i<3;i++){
			args[i]=input.next();
		}
		int a=Integer.parseInt(args[0]);
		int b=Integer.parseInt(args[2]);
		switch (args[1]){
		case "+": System.out.println(a+b);break;
		case "-": System.out.println(a-b);break;
		case "*": System.out.println(a*b);break;
		case "/": System.out.println(a/b);break;
		default : System.out.println("hehe");break;
		}
	}
}
