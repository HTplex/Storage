package neuqoj;
import java.util.Scanner;
public class timestimes {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int su=1;
		for(int i=1;i<=n;i++){
			su*=i;
			su=ans(su);
		}
		System.out.println(su);
	}
	public static int ans(int i){
		while(i%10==0){
			i/=10;
		}
		int r=i%10;
		return r;
	}
}
