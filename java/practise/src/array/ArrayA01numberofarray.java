package array;
import java.util.Scanner;
public class ArrayA01numberofarray{
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	double[] firstarray=new double[10];
	int i=0;
	while (true){
		firstarray[i]=i;
		System.out.println("firstarray["+i+"]:\n"+firstarray[i]);
		i++;
	}
	}
}
