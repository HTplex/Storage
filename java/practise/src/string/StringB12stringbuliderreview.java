package string;
import java.util.Scanner;
public class StringB12stringbuliderreview {
	public static void main(String[]args){
		char[] c={'d','e','g',' ','u'};
		StringBuilder s=new StringBuilder("welcome Thank YOU verymuch");
		s.append("everything");
		s.delete(2, 8);
		s.deleteCharAt(2);
		s.insert(2, "everything");
		s.insert(2,c,1,3);
		s.insert(2,c);
		s.replace(2,6,"hehe");
		s.reverse();
		s.setCharAt(5,'d');
		System.out.println(s);
	}
}
