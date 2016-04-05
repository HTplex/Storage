package object;
import java.util.Scanner;
public class ObjectA06random {
	public static void main(String[]args){
		Scanner input =new Scanner(System.in);
		java.util.Random a=new java.util.Random();
		java.util.Random b=new java.util.Random(100000000000L);
		int m=input.nextInt();
		if(m==-1)
			a=b;
		System.out.println("input:\n1:Int\n2:Int(a,b)\n3:Long\n4:double\n5:float\n6:boolean");
		int i=input.nextInt();
		for(int i1=0;i1<100;i1++){
		switch(i){
		case 1:
			System.out.print(a.nextInt()+"\t");break;
		case 2:int x=input.nextInt();
			int y=input.nextInt();
			for(int i2=0;i2<100;i2++){
			System.out.print(a.nextInt(y-x)+x+"\t");
			if (i2%8==0) System.out.println();
			}break;
		case 3:System.out.print(a.nextLong()+"\t");break;
		case 4:System.out.print(a.nextDouble()+"\t");break;
		case 5:System.out.print(a.nextFloat()+"\t");break;
		case 6:System.out.print(a.nextBoolean()+"\t");break;
		}
		if(i1%8==7)System.out.println();
		}
	}
}
