package array;
import java.util.Scanner;
public class ArrayB19parrelarray {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the total number of the students");
		int num=input.nextInt();
		String[] name=new String [num];
		int[] sco=new int [num];
		for(int i=0;i<name.length;i++){
			System.out.print("["+(i+1)+"]\nname:\t");
			name[i]=input.next();
			System.out.print("score:\t");
			sco[i]=input.nextInt();
		}
		for(int i=0;i<sco.length;i++){
			System.out.println(name[maxnum(sco)]);
			sco[maxnum(sco)]=0;
		}
	}
	public static int maxnum(int[]a){
		int m=0;
		int ma=0;
		for(int i=0;i<a.length;i++){
			if(ma<a[i]){
				ma=a[i];
				m=i;
			}
		}
		return m;
	}
}
