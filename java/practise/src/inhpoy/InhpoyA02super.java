package inhpoy;
import java.util.Scanner;
public class InhpoyA02super {
	public static void main(String[]args){
		new speaklit();
	}
}
class speak{
	speak(){
	System.out.println("(x)");
	}
	speak(String s){
		System.out.println(s);
	}
}
class speaklit extends speak{
	//super();always here;
	speaklit(){
		this("(2)");
		System.out.println("(3)");
	}
	speaklit(String s){
		super("(1)");
		System.out.println(s);
	}
}