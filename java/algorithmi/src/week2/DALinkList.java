package week2;

import java.util.Iterator;

public class DALinkList implements Iterable<String> {
	public static void main(String[]args){
		 
	}
	public Iterator<String> iterator(){
		return new LLiterator();
	}
	private class LLiterator implements Iterator<String>{
		public boolean hasNext(){
			return first.next==null;
		}
		public String next(){
			return first.next.info;
		}
		public void remove(){
			//
		}
	}
	private class Node{
		String info;
		Node next;
	}
	
	private Node first=null;
	
	public void push(String s){
		Node OldFirst=this.first;
		this.first=new Node();
		first.info=s;
		first.next=OldFirst;
	}
	
	public String pop(){
		
		String s=this.first.info;
		this.first=this.first.next;
		return s;

	}
	public void push2(String s){
		this.first.next=this.first;//it is not working; null pointer
		this.first.info=s;
	}
	public boolean isEmpty(){
		return this.first==null;
	}
	public void advPush(String s){
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
