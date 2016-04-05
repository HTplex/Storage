package loop;
import java.util.Scanner;
public class LoopB44dartlike {
	public static void main(String[]args){
		  int n1=0,n2=0,n3=0,n4=0,n=0;
		  for (int i=1;i<=1000000;i++){
			  double x=Math.random()-0.5;
			  double y=Math.random()-0.5;
		  if (x<0){
			  System.out.print("1");
			  n1++;
			  }
			  else if (y<0){
				  System.out.print("4");
				  n4++;
			  }
			  else if (x+y<0.5){
				  System.out.print("3");
				  n3++;
			  }
				  else {
					  System.out.print("2");
					  n2++;
				  }
		  n++;
		  if (n%1000==0)
			  System.out.println();
		  }
		  System.out.println("\n\nn1="+n1+" n2="+n2+" n3="+n3+" n4="+n4);
	}
}
