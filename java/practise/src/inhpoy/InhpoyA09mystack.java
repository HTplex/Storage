package inhpoy;
import java.util.Scanner;
public class InhpoyA09mystack {
	public static void main(String[]args){
/*		mystack a=new mystack();
		for(int i=0;i<10;i++){
			a.push(i);
		}
		while(!a.isempty()){
			a.pop();*/
	}
}
class mystack{
	java.util.ArrayList a;
	mystack(){
		a=new java.util.ArrayList();
	}
	public boolean isempty(){
		return a.isEmpty();
	}
	public int getsize(){
		return a.size();
	}
	public void push(Object a){
		this.a.add(a);
	}
	public Object pop(){
		Object c=this.a.get(this.a.size()-1);
		this.a.remove(this.a.size()-1);
		return c;
	}
	public Object peek(int i){
		return this.a.get(i);
	}
	public int search(Object a){
		return this.a.indexOf(a);
	}
}
