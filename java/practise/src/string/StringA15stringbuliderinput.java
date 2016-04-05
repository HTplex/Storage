package string;
import java.util.Scanner;
public class StringA15stringbuliderinput {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		StringBuilder a=new StringBuilder();
		a.append('a');
		int i=input.nextInt();
		System.out.println(a);
		char[] c={'e','h','y','o'};
		a.append(c,2,2);
		i=input.nextInt();
		System.out.println(a);
		a.append("another one added ");
		i=input.nextInt();
		System.out.println(a);
		a.append(25);
		i=input.nextInt();
		System.out.println(a);
		a.append(8==9);
		i=input.nextInt();
		System.out.println(a);
		a.append(Math.PI);
		i=input.nextInt();
		System.out.println(a);
		a.delete(3,8);
		i=input.nextInt();
		System.out.println(a);
		a.deleteCharAt(5);
		i=input.nextInt();
		System.out.println(a);
		a.insert(3,c,2,2);
		i=input.nextInt();
		System.out.println(a);
		a.insert(3,"you are a shit");
		i=input.nextInt();
		System.out.println(a);
		a.replace(4,9,"@@@@");
		i=input.nextInt();
		System.out.println(a);
		a.reverse();
		i=input.nextInt();
		System.out.println(a);
		a.setCharAt(0,'&');
		System.out.println(a);
		
	}
}
