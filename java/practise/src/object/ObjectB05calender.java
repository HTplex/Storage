package object;
import java.util.Scanner;
public class ObjectB05calender {
	public static void main(String[]args){
		java.util.GregorianCalendar a=new java.util.GregorianCalendar();
		a.setTimeInMillis(1234567898765L);
		System.out.println("Y:"+a.YEAR+"\nM:"+a.MONTH+"\nD:"+a.DAY_OF_MONTH);
	}
}
