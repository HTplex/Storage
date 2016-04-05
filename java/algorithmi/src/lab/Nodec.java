package lab;

public class Nodec {
public static void main(String[]string){
	node a=new node();
	a.info=1;
	node b=a.next;
	b.info=3;
	a.next.next.info=7;
	for(int i=0;i<2;i++){
		System.out.println(a.info+" ");
		//a=a.next;
		//i don't know what happened
	}
	
	
}
}
