package objectplus;
import java.util.Scanner;
public class ObjectplusA06stack {
	public static void main(String[]args){
		
	}
}



class stackOfInteger{
	private int num=0;
	private static final int defultcapacity=16;
	private int[] numbers;
	
	public stackOfInteger(int capacity){
		this.numbers=new int[capacity];
	}
	public stackOfInteger(){
		this(defultcapacity);
	}
	public void push(int i){
		if(this.num>=numbers.length){
			int[] temp=new int[2*num];
			System.arraycopy(numbers,0,temp,0,num);
			numbers=temp;
		}
		numbers[num++]=i;//now the real about num++ and ++num;
	}
	public int pop(){
		return this.numbers[--num];
	}
	public boolean empty(){
		return num==0;
	}
	public int getsize(){
		return num;
	}
	public int peek(){
		return numbers[num-1];
	}
	
}
