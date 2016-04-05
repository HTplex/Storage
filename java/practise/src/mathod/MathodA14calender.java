package mathod;
import java.util.Scanner;
public class MathodA14calender {
	public static void main(String[]args){
		System.out.print("please input the year:");
		Scanner input=new Scanner(System.in);
		int year=input.nextInt();
		System.out.print("please input the month you want(0)is whole year");
		int month=input.nextInt();
		if(month!=0){
		printtitle(year,month);
		printbody(year,month);
		}//month is normal
		else {
			for(int i=1;i<=12;i++){
				printtitle(year,i);
				printbody(year,i);
				System.out.println("\t");
			}//for month++
		}//month=0
	}//main
	
	public static void  printtitle(int year,int month){
		System.out.println("      \t"+showmonth(month)+"\t"+year);
		System.out.println("-----------------------------------");
		System.out.println("  Sun  Mon  Tue  Wed  Thu  Fri  Sat");
	}//title
	public static void printbody(int year,int month){
		int count=0;
		for (int i=startday(year,month);i>0;i--){
			System.out.print("     ");
			count++;
		}
		for (int day=1;day<=numofmonth(year,month);day++){
		System.out.printf("%5d",day);
		count++;
		if(count%7==0)
			System.out.println();
		}
	}//body
	
	public static String showmonth(int mon){
		String month="";
		switch (mon){
		case 1:month="January";break;
		case 2:month="February";break;
		case 3:month="March";break;
		case 4:month="April";break;
		case 5:month="May";break;
		case 6:month="June";break;
		case 7:month="July";break;
		case 8:month="August";break;
		case 9:month="September";break;
		case 10:month="Octuber";break;
		case 11:month="November";break;
		case 12:month="December";break;
		}
		return month;
	}//showmonth
	
	public static int numofmonth(int year,int month){
		int monnum=0;
		int feb=29;
		if((year%100==0&&year%400!=0)||year%4!=0)
			feb=28;
		switch(month){
		case 1:monnum=31;break;
		case 2:monnum=feb;break;
		case 3:monnum=31;break;
		case 4:monnum=30;break;
		case 5:monnum=31;break;
		case 6:monnum=30;break;
		case 7:monnum=31;break;
		case 8:monnum=31;break;
		case 9:monnum=30;break;
		case 10:monnum=31;break;
		case 11:monnum=30;break;
		case 12:monnum=31;break;
		}//switch
		return monnum;
	}//numofmonth
	
	public static int startday(int year,int month){
		if (month==1||month==2) {
			month+=12;
			year--;
		}//if month
		int start=((1+((26*(month+1))/10)+year%100+((year%100)/4)+(((int)(year/100))/4)+5*((int)(year/100)))-1)%7;
		return start;
	}//start day
}
