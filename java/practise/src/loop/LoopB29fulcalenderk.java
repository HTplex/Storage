package loop;
import java.util.Scanner;
public class LoopB29fulcalenderk {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number of the year");
		int year=input.nextInt();
		int in=((1+((26*14)/10)+(year-1)%100+(((year-1)%100)/4)+(((int)((year-1)/100))/4)+5*((int)((year-1)/100))))%7-1;
		String month="";
		int monnum=1;
		int feb=29;
		if(year%4!=0||(year%100==0&&year%400!=0))
			feb=28;
 		for (int i=1;i<=12;i++){
			switch (i){
			case 1:month="January";monnum=31;break;
			case 2:month="February";monnum=feb;break;
			case 3:month="March";monnum=31;break;
			case 4:month="April";monnum=30;break;
			case 5:month="May";monnum=31;break;
			case 6:month="June";monnum=30;break;
			case 7:month="July";monnum=31;break;
			case 8:month="August";monnum=31;break;
			case 9:month="September";monnum=30;break;
			case 10:month="Octuber";monnum=31;break;
			case 11:month="November";monnum=30;break;
			case 12:month="December";monnum=31;break;
			}//switchmonth
			System.out.println("            "+month+" "+year);
			System.out.println("--------------------------------------------------------");
			System.out.println("     Sun     Mon     Tue     Wed     Thu     Fri     Sat");
			int count=0;
				for (int ii=in;ii>0;ii--){
					System.out.print("        ");
					count++;
			}//blankfor
				for(int iii=1;iii<=monnum;iii++){
					System.out.printf("%8d",iii);
					count++;
					if (count%7==0)
						System.out.println();
				}//fordate
				
				System.out.println("\n");
				in=(in+monnum)%7;
		}//formonth
 		
	}
}
