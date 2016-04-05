package choose;
import java.util.Scanner;
public class ChooseBkmonth {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input the year");
		int year=input.nextInt();
		System.out.print("please input the month");
		int num=0;
		int month=input.nextInt();
		if (month==1||month==3||month==5||month==7||month==8||month==10||month==12)
			num=31;
		if(month==4||month==6||month==9||month==11)
			num=30;
		if (month==2){
			if (year%100==0){
				if (year%400==0)
					num=29;
				else num=28;
				}
			else {
				if (year%4==0){
				num=29;}
				else num=28;}			
		}
		
			System.out.println(year+"."+month+" has "+num+" days");
		}
}