package string;
import java.util.Scanner;
public class StringA07reguarexp {
	public static void main(String[]args){
		String s=new String("this is java: a great language, have sentences such as: System.out.println(&^*Gggg*&^&%vgghhh$%)");
		Scanner input=new Scanner(System.in);
		int m=input.nextInt();
		System.out.println(s);
		m=input.nextInt();
		System.out.println(s.replaceAll("print.*","haha"));
		m=input.nextInt();
		System.out.println(s.replaceAll("[ .gs]","@"));
		m=input.nextInt();
		String[] tokens=s.split("[ :;,.&(]");
		for(String i:tokens)
			System.out.println(i);
	}
}
