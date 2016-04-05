package array;
import java.util.Scanner;
public class ArrayA05porker {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int[] num=new int[54];
		int i;
		for(i=0;i<num.length;i++)
			num[i]=i;
		String [] color={"Spades","Clubs","Diamonds","Hearts"};
		String [] number={"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
		int temp;
		for(i=0;i<num.length;i++){
			int b=(int)(54*Math.random());
			temp=num [i];
			num[i]=num[b];
			num[b]=temp;
		}
		for(int a=0;a<54;a++){
			if (num[a]==52)System.out.println("Card number "+(a+1)+":\tjoker");
			else if(num[a]==53)System.out.println("Card number "+(a+1)+":\tJOKER");
			else
			System.out.println("Card number "+(a+1)+":\t"+number[num[a]%13]+" of "+color[(int)(num[a]/13)]);
		}

	}
}
