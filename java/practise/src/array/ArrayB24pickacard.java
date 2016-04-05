package array;
import java.util.Scanner;
public class ArrayB24pickacard {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String [] color={"Spades","Clubs","Diamonds","Hearts"};
		int [] colorc= new int [4];
		int c=52;
		int a;
		int m;
		int i=0;
		int [] numbers=new int [52];
		for(int i2=0;i2<52;i2++)
			numbers[i2]=i2;
		String [] number={"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
		
		while((colorc[0]==0)||(colorc[1]==0)||(colorc[2]==0)||(colorc[3]==0)){
			m=input.nextInt();
		a=(int)(Math.random()*c);
		a=numbers[a];
		System.out.println(number[(int)(a%13)]+" of "+color[(int)(a%4)]);
		colorc[(int)(a%4)]++;
		for(int w=a+1;w<52;w++)
			numbers[w-1]=numbers[w];
		i++;
		c--;
		}
		System.out.println("total : "+i);	
	}
}
