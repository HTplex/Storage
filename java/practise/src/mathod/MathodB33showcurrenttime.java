package mathod;
import java.util.Scanner;
public class MathodB33showcurrenttime {
	/*public static void main(String[]args){
		System.out.println(showtotalday());//check
		System.out.println(showyear());//check
		System.out.println(daylast());//check
		System.out.println(mon());
		System.out.println(showmonth(mon()));
		System.out.println(day());
		showtime();
		}*/
	public static void main(String[]args){
		System.out.print("the current time is\n"+showyear()+"   "+showmonth(mon())+"   "+day()+"\n");
		showtime();
	}
	public static void showtime(){
		long a=System.currentTimeMillis();
		int second=(int)(a%(1000*60)/1000);
		int minute=(int)(a%(1000*60*60)/(60*1000));
		int hour=(int)(a%(1000*60*60*24)/(1000*60*60));
		System.out.print(hour+":"+minute+":"+second);
	}
	public static int showtotalday(){
		long a=System.currentTimeMillis()/(1000*60*60*24)+1;
		return (int)a;
	}
	public static int showyear(){
		int year=1970;
		int i=showtotalday();
		while(i>0){
			if((year%100==0&&year%400!=0)||(year%4!=0)){
				year++;
				i-=365;
			}//if
			else {
				year++;
			i-=366;
			}//else
		}
		return year-1;
	}
	public static int daylast(){
		int yea=showyear();
		int a=0;
		if((yea%100==0&&yea%400!=0)||(yea%4!=0))
			a=365;
		else a=366;
		int year=1970;
		int i=showtotalday();
		while(i>=0){
			if((year%100==0&&year%400!=0)||(year%4!=0)){
				year++;
				i-=365;
			}
			else {
				year++;
			i-=366;
			}
		}
		return i+=a;
	}
	public static int mon(){
		int i=daylast();
		int monnum=0;
		int mon=0;
		int feb=29;
		int year=showyear();
		if((year%100==0&&year%400!=0)||year%4!=0)
			feb=28;
		while (i>0){
		switch(mon){
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
		i-=monnum;
		mon++;
		}//while
		return mon-1;
	}
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
	public static int day(){
		int i=daylast();
		int monnum=0;
		int mon=0;
		int feb=29;
		int year=showyear();
		if((year%100==0&&year%400!=0)||year%4!=0)
			feb=28;
		while (i>0){
		switch(mon){
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
		i-=monnum;
		mon++;
		}
		return i+monnum;
	}
}
