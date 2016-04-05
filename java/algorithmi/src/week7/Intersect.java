package week7;
import java.util.Scanner;
public class Intersect {
	public static void main(String[]args){ 
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		intree tr=new intree();
		for(int i=0;i<n;i++){
			double a=in.nextDouble();
			double b=in.nextDouble();
			tr.out(a, b);
			tr.put(a, b);
		}
		in.close();
	}
}
class intree {
	class Node{
		double index;
		double end;
		double max;
		Node left;
		Node right;
		Node(double index, double end){
			this.index=index;
			this.end=end;
			this.max=end;
		}
		
	}
	Node root;
	
	public Node put(Node n, double i, double end){
		if(n==null) return new Node(i, end);
		if(n.index>i) n.left=put(n.left,i,end);
		else if(n.index<i) n.right=put(n.right,i,end);
		else n.end=end;
		if(n.max<end) n.max=end;
		return n;
	}
	public void put(double i, double end){
		this.root=put(root, i, end);
	}
	public Node delete(Node n, double i){
		//no need to delete this time;
		return n;
	}
	public void out(Node n, double start, double end){
		if(n==null) return;
		if(n.max>=start) out(n.left, start, end);
		if(n.index<=end) out(n.right, start, end);
		if((n.index<=end&&n.end>=start)||(n.end>=start&&n.index<=end))
			System.out.println(max(n.index,start)+" "+min(n.end,end));
	}
	public void out(double start, double end){
		out(this.root, start, end);
	}
	public double max(double a, double b){
		return a>b?a:b;
	}
	public double min(double a, double b){
		return a<b?a:b;
	}
}