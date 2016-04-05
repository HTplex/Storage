package week2;
import java.util.Scanner;
public class DALinkListUser2 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
			DALinkList a=new DALinkList();
			String s=input.next();
			while(s!="end"){
				s=input.next();
				a.advPush(s);
			}
		input.close();
	}
}
