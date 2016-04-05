package neuqoj;
import java.util.Scanner;
public class StringC03checkpassword {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String c=input.next();
		boolean b=true;
		if(c.length()!=8){
			System.out.println(!b);
		}
		int sum=0;
		for(int i=0;i<c.length();i++){
			Character a=c.charAt(i);
			if(a.isDigit(a))sum++;
			if(!a.isLetterOrDigit(a))
				b=false;
		}
		if(sum<=2){
			b=false;
		}
		System.out.println(b);
	}
}
