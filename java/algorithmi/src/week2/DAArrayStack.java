package week2;

public class DAArrayStack {
	public static void main(String[]args){
		
	}
	String[] data;
	int addr;
	public DAArrayStack(int i){
		this.data=new String[i];
		this.addr=0;
	}
	public DAArrayStack(){
		this.data=new String[10];
		this.addr=0;
	}
	public Boolean isEmpty(){
		return this.addr==0;
	}
	public String pop(){
		this.addr--;
		return this.data[this.addr];
	}
	public void push(String s){
		this.data[this.addr]=s;
		this.addr++;
	}
}
