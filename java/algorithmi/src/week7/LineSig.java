package week7;
import java.util.Scanner;
public class LineSig {

	public static void main(String[]args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		point[] p=new point[2*n];
		tree tr=new tree();
		
		for(int i=0;i<2*n;i++){
			double x1=in.nextDouble();
			double y1=in.nextDouble();
			double x2=in.nextDouble();
			double y2=in.nextDouble();
			
			if(x1==x2){
				p[i]=new point(x1,y1,y2);
				
				i++;
				p[i]=new point(x1,Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY);
			}
			else{
				p[i]=new point(x1,y1);
				//tr.root=tr.put(tr.root, y1, x1);
				i++;
				p[i]=new point(x1,y1,x2,y2);
			}
		}
		java.util.Arrays.sort(p);
		for(int i=0;i<p.length;i++){
			if(p[i].kind==p[i].PRE) tr.root=tr.put(tr.root, p[i].ty, p[i].tx);
			if(p[i].kind==p[i].SUB) tr.root=tr.delete(tr.root, p[i].py);
			if(p[i].kind==p[i].VER) tr.out(tr.root, p[i].py, p[i].ty,p[i].px);
		}
		in.close();
	}

	/*public static void main(String[]args){
		tree tr=new tree();
		for(double i=0;i<5;i++)
		tr.root=tr.put(tr.root, i, i);
		tr.out(tr.root, 2, 3);
	}*/
}
class tree{
	class Node{
		double y;
		double x;
		Node left;
		Node right;
		Node(double x, double y){
			this.x=x;
			this.y=y;
		}
		
	}
	Node root;
	public Node put(Node n, double x, double y){
		if(n==null) return new Node(x,y);
		if(x>n.x) n.right=put(n.right,x,y);
		else if(x<n.x) n.left=put(n.left,x,y);
		else n.y=y;
		return n;
	}
	public Node delete(Node n, double x){
		if(n==null) return null;
		if(n.x>x) n.left=delete(n.left, x);
		else if(n.x<x) n.right=delete(n.right, x);
		else {
			if(n.right==null) return n.left;
			if(n.left==null) return n.right;
			Node t=n;
			n=min(t.left);
			n.right=delmin(t.right);
			n.left=t.left;
		}
		return n;
	}
	public Node min(Node n){
		if (n.left==null) return n;
		else return min(n.left);
	}
	public Node delmin(Node n){
		if(n.left==null) return n.right;
		n.left=delmin(n.left);
		return n;
	}
	public Boolean isEmpty(){
		return root==null;
	}
	public void out(Node n, double x1, double x2,double i){
		if(n==null) return;
		if(n.x>=x1) out(n.left, x1, x2,i);
		
		if(n.x<=x2) out(n.right, x1, x2,i);
		if(n.x>=x1&&n.x<=x2) System.out.println(i+" "+n.x);
	}
}
class point implements Comparable<point> {
	final int PRE=0;
	final int SUB=1;
	final int VER=2;
	double tx;
	double ty;
	double px;
	double py;
	int kind;
	
	point(double x, double y){
			tx=x;
			ty=y;
			px=Double.NaN;
			py=Double.NaN;
			kind=PRE;
	}
	point(double x1, double y1, double x2, double y2){
		px=x1;
		py=y1;
		tx=x2;
		ty=y2;
		kind=SUB;
	}
	point(double x1, double y1, double y2){
		tx=x1;
		px=x1;
		ty=y1;
		py=y2;
		kind=VER;
	}
	public int compareTo(point that){
		if (this.tx-that.tx==0) return 0;
		return this.tx-that.tx>0?1:-1;
	}
	
}