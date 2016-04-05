package loop;
import java.util.Scanner;
public class Loopfreeplusplus {
	public static void main(String[]args){
	int i=1;
	while(i<10)
		if ((++i)%2==0)
			System.out.println("i "+i);
	int z=1;
	while(z<10)
		if ((z++)%2==0)
			System.out.println("z "+z);
	}
}
