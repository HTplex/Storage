package week1;

public class BCQuickUnionFixingUser {
	public static void main(String[]args){
		
		BCQuickUnionFixing a=new BCQuickUnionFixing(10);

		long time=0;
		for(int n=0;n<1000;n++){
			long i=System.nanoTime();
			a.union(0, 2);
			a.union(3, 5);
			a.union(5, 6);
			a.union(0, 7);
			a.union(6, 2);
			a.union(3, 2);
			System.out.println(a.connected(0, 8));
			System.out.println(a.connected(0, 6));
			System.out.println(a.connected(0, 2));
			System.out.println(a.connected(0, 0));
			time+=(System.nanoTime()-i);
		}
		System.out.println(time);
		
	}
}
