package objectplus;
import java.util.Scanner;
public class ObjectplusA01privite {
	public static void main(String[]args){
		student s=new student(0001,"HT");
		s.ingrade('A');
		System.out.println(s.getinfo());
	}
}
class student{
	private int id;
	private String name;
	private char grade;
	private java.util.Date lastchange;

	public student(int a,String b){
		id=a;
		name=b;
		lastchange=new java.util.Date();
	}
	public void ingrade(char c){
		grade=c;
		lastchange=new java.util.Date();
	}
	public String getinfo(){
		String s="";
		s=s.valueOf(id);
		StringBuilder b=new StringBuilder();
		b.append(s);
		b.append("\n");
		b.append(name);
		b.append("\n");
		b.append(grade);
		b.append("\n");
		b.append(lastchange);
		s=b.toString();
		return s;
	}
}
