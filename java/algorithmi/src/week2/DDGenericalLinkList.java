package week2;


public class DDGenericalLinkList<E> {
	public static void main(String[]args){
		
	}
	
	private class Node{
		E info;
		Node next;
	}
	
	private Node first=null;
	
	public void push(E s){
		Node OldFirst=this.first;
		this.first=new Node();
		first.info=s;
		first.next=OldFirst;
	}
	
	public E pop(){
		
		E s=this.first.info;
		this.first=this.first.next;
		return s;

	}
	public void push2(E s){
		this.first.next=this.first;//it is not working; null pointer
		this.first.info=s;
	}
	public boolean isEmpty(){
		return this.first==null;
	}
	public void advPush(E s){
		if (!s.equals("-")){
			this.push(s);
			System.out.println("pushed");
		}
		else {
			if(!this.isEmpty())
			System.out.println(this.pop());
			else System.out.println("isEmpty");
		}
	}
}
