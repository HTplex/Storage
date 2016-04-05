package loop;
import java.util.Scanner;
public class LoopB34RPC2 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("welcom to the RPC game ");
		int i=0;
		while (i<2){
			int comp=(int)(3*Math.random()+1);
			System.out.print("please input");
			int man=input.nextInt();
			if((man==3&&comp==1)||(man==1&&comp==2)||(man==2&&comp==3)){
				i++;
			System.out.println("you win");
			}
				else if ((man==1&&comp==3)||(man==3&&comp==2)||(man==2&&comp==1)){
					i=0;
					System.out.println("you lose");
				}
				else System.out.println("tied");
			}
		System.out.print("you finally win");
	}
}
