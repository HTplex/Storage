package week2;
import java.util.Scanner;
public class DALinkLIstUser {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		DALinkList ll=new DALinkList();
		ll.push("this is my first node");
		ll.push("this is my second node");
		ll.push("didnt see that coming did u");
		System.out.println(ll.pop());
		ll.push("aha this again");
		while(!ll.isEmpty())
		System.out.println(ll.pop());
		//ll.push2("i dont give this a hope");
		//ll.pop();
		input.close();
	}
}
