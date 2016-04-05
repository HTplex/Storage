package mathod;
import java.util.Scanner;
public class MathodB06tri {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number you want");
		int i=input.nextInt();
		showtriangle(i);
	}
	public static void showtriangle(int a){
		int line=1;
		for (int i=1;i<=a;i++){
			for(int i2=line;i2<a;i2++){
				System.out.print("    ");
			}
			for (int i3=1;i3<=line;i3++){
				System.out.printf("%4d",line-i3+1);
				if (line==i3)
					System.out.println();
			}
			line++;
		}
	}
}
