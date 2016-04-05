package week2;
import java.util.Scanner;
public class DCQueueUser {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		DCQueue a=new DCQueue();
		String s=input.next();
		while(!s.equals("stop")){
		s=input.next();
		a.enqueue(s);
		if(s.equals("pop"))
			System.out.println(a.dequeue());
		}
		input.close();
	}
}
