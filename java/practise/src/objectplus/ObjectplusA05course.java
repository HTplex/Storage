package objectplus;
import java.util.Scanner;
public class ObjectplusA05course {
	public static void main(String[]args){
		
	}
}
class course{
	private String courseName;
	private String[] studentName;
	private int nofs;
	
	public course(String coursename){
		this.courseName=coursename;
		this.nofs=0;
	}
	public String getcoursename(){
		return this.courseName;
	}
	public void inputstudent(String s){
		this.studentName[this.nofs]=s;
		this.nofs++;
	}
}