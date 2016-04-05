package string;
import java.util.Scanner;
public class StringC14cmdsum {
	public static void main(String[]args){
		int sum=0;
		for(int i=0;i<args.length;i++){
			int i1=Integer.parseInt(args[i]);
			sum+=i1;
		}
		System.out.println("sum: "+sum);
	}
}
