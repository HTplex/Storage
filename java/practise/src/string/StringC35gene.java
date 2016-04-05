package string;
import java.util.Scanner;
public class StringC35gene {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number of genes");
		int i=input.nextInt();
		String[] ss=new String[i];
		for(int n=0;n<i;n++){
			System.out.println("input gene");
			ss[n]=input.next();
		}
		System.out.println("input line");
		String bigs=input.next();
		boolean b=false;
		for(int count=0;count<i;count++){
			if(bigs.contains(ss[count])){
			b=true;	
			System.out.println(ss[count]);
			}
		}
		if(!b){
			System.out.println("no such gene found")
			;
		}
	}
}

