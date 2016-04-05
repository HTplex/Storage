package choose;
import java.util.Scanner;
public class ChooseBpRPC {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	int computer=(int)(3*Math.random());
	String a="lala";
	switch (computer){
	case 0: a="Scissor";break;
	case 1: a="rock";break;
	case 2: a="paper";break;
	default: a="haha";break;
	}
	System.out.println("scissor(0), rock(1), paper(2)"+a);
	int person=input.nextInt();
	switch (person){
	case 0: String b="Scissor";break;
	case 1: b="rock";break;
	case 2: b="paper";break;
	default: b="haha";break;
	}
	if ((person==1&&computer==0)||(person==2&&computer==1)||(person==0&&computer==2)) 
		System.out.println("The computer is "+computer+". You are "+person+". You win!");
	if ((person==0&&computer==1)||(person==1&&computer==2)||(person==2&&computer==0)) 
		System.out.println("The computer is "+computer+". You are "+person+". You lose!");
	if(person==computer)
		System.out.println("The computer is "+computer+". You are "+person+" too. It is a draw!");
	
	
	}
}
