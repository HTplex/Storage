package week2;
import java.util.Scanner;
public class DBResizeableArrayUser {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		DBResizeableArray a=new DBResizeableArray();
		String s=input.next();
		while (!s.equals("stop")){
			a.push(s);
			s=input.next();
		}
		for(int i=0;i<15;i++)
		a.pop();
		input.close();
	}
}
