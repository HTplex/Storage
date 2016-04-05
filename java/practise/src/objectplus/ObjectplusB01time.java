package objectplus;
import java.util.Scanner;
public class ObjectplusB01time {
	public static void main(String[]args){
		time a=new time();
		System.out.println();
	}
}
class time{
	private int hour;
	private int minute;
	private int second;
	private static final int k=60;
	
	public time(){
		this(System.currentTimeMillis());
	}
	public time(long n){
		this.hour=(int)(n/k*k*1000)%24;
		this.minute=(int)(n/k*1000)%k;
		this.second=(int)(n/1000)%k;
	}
	public int gethour(){
		return this.hour;
	}
	public int getminute(){
		return this.minute;
	}
	public int getsecond(){
		return this.second;
	}
	public void setnewtime(long newtime){
		time t=new time(newtime);
		this.hour=t.hour;
		this.minute=t.minute;
		this.second=t.second;
	}
	
}
