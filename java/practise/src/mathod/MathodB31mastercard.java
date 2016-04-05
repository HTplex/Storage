package mathod;
import java.util.Scanner;
public class MathodB31mastercard {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input you mastercardnumer");
		long cri=input.nextLong();
		int check=ch1(cri)+ch2(cri);
		System.out.println("is your "+checkcard(cri)+" a real card?"+(check%10==0));
		System.out.println(showcurrentnumber(cri,10)+"\n"+checkcard(cri)+"\n"+shownumber(cri)+"\n"+ch1(cri)+"\n"+ch2(cri));
	}
	public static int showcurrentnumber (long a,int b){
		return(((int)(a/(Math.pow(10, b-1))))%10);
	}
	public static String checkcard(long a){
		long i=0;
		for(i=1000000000000000000L;i>a;i/=10){}
		int num=(int)Math.log10(i);
		if(showcurrentnumber(a,num+1)==4){
			return "Visa";
		}
		else if(showcurrentnumber(a,num+1)==5){
			return "Master";
		}
		else if(showcurrentnumber(a,num+1)==6){
			return "Discover";
		}
		else if(showcurrentnumber(a,num+1)==3||showcurrentnumber(a,num)==7){
			return "America Express";
		}
		else return "unknown";
	}
	public static int shownumber(long a){
		long i=0;
		for(i=1000000000000000000L;i>a;i/=10){}
		int num=(int)Math.log10(i);
		return num+1;
	}
	public static int ch1(long a){
		int sum=0;
		for (int i=1;i<=shownumber(a);i++){
			int current=showcurrentnumber(a,i);
			sum+=((current*2)%10+(int)((current*2)/10));
		}
		return sum;
	}
	public static int ch2(long a){
		int i=0;
		int sum=0;
		for (i=shownumber(a);i>0;i-=2)
		sum+=showcurrentnumber(a,shownumber(a)+1-i);
		return sum;
	}
}
