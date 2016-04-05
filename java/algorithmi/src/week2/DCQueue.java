package week2;

public class DCQueue {
	class Node{
		String info;
		Node next;
	}
	
	private Node first;
	private Node last;
	
	public void enqueue(String s){
		
		Node oldlast=new Node();
		oldlast=this.last;
		this.last=new Node();
		this.last.info=s;
		if(isEmpty())
			first=last;					//nullpointerexcepion:if the node don't have information
		else{							//yet then it can't have next
			oldlast.next=this.last;
		}
		
	}
	
	private boolean isEmpty(){
		return first==null;
	}
	
	public String dequeue(){
		String s="";
		if(first.info!=null){
		Node oldfirst=first;
		s=oldfirst.info;
		this.first=new Node();
		first=oldfirst.next;
		oldfirst=null;
		if(isEmpty())
			last=null;
		}
		return s;
	}
	
	public static void main(String[]args){
		
	}
	
	
}
