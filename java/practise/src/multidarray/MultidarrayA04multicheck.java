package multidarray;
import java.util.Scanner;
public class MultidarrayA04multicheck {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("number of students");
		int s=input.nextInt();
		System.out.print("number of tests");
		int t=input.nextInt();
		int [][] score=new int [s][t];
		for(int i=0;i<score.length;i++){
			System.out.println("please input the answers of student "+(i+1)+"\nA:1 B:2 C:3 D:4");
			for(int i2=0;i2<score[i].length;i2++){
			score[i][i2]=input.nextInt();
		}
	}
		int[] key=new int [t];
		System.out.println("please input the key \nA:1 b:2 C:3 D:4");
		for(int a=0;a<t;a++){
			key[a]=input.nextInt();
		}
		int [] sss=new int[s];
		for(int i3=0;i3<s;i3++){
			for(int i4=0;i4<t;i4++){
				if(score[i3][i4]==key[i4]){
					sss[i3]+=1;
				}
			}
		}
		for(int q=0;q<s;q++){
			System.out.println("student "+(q+1)+": "+(int)(100.0*((double)sss[q]/(double)t)));
		}
	}
}
