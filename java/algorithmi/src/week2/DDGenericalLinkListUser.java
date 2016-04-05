package week2;

public class DDGenericalLinkListUser {
	public static void main(String[]args){
		DDGenericalLinkList<Integer> a=new DDGenericalLinkList<Integer>();
		a.push(1);
		a.push(2);
		System.out.println(a.pop());
		System.out.println(a.pop());

	}
}
