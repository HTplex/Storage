package inhpoy;
import java.util.*;
public class InhpoyA08arraylist {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	ArrayList a=new ArrayList();
	for(int i=0;i<10;i++){
		a.add(i);
		System.out.println(a);
	}
	a.add(3,"@");
	System.out.println(a);
	a.remove(3);
	System.out.println(a);
	System.out.println(a.contains(1));
	System.out.println(a.contains("1"));
	a.add("1");
	System.out.println(a.contains("1"));
	System.out.println(a.contains(25));
	a.remove(10);
	System.out.println(a.size());
	a.add(1);
	System.out.println(a);
	a.remove(1);
	System.out.println(a);
	System.out.println(a.indexOf(1));
	System.out.println(a.indexOf('o'));
	System.out.println(a.lastIndexOf(1));
	System.out.println(a.get(4));
	System.out.println(a.isEmpty());
	a.clear();
	System.out.println(a.isEmpty());
	
	}
}