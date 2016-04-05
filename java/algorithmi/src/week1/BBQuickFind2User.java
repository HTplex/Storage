package week1;

public class BBQuickFind2User {
	public static void main(String[]args){
		
		BBQuickFind2 a=new BBQuickFind2(10);
		a.union(5, 4);
		a.union(7, 5);
		a.union(0, 9);
		a.union(3, 6);
		a.union(1, 5);
		a.union(8, 4);
		System.out.println(a.connected(0, 8));
		System.out.println(a.connected(0, 6));
		for(int m:a.id)
			System.out.print(m+" ");
	}
	
}
	
	
	
