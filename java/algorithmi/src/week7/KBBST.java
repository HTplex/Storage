package week7;
import java.util.*;
public class KBBST {
	private class Node{
		private Integer index;
		private String info;
		private Node left, right;
		private int count;
		private Boolean color;
		Node(String info, int index){
			this.index=index;
			this.info=info;
		}
		Node(String info, int index, Boolean color){
			this.index=index;
			this.info=info;
			this.color=color;
		}
		
	}
	private final boolean RED=true;
	private final boolean BLACK=true;
	private Node root;
	public int size(){
		return size(root);
	}
	public int size(Node n){
		if (n==null) return 0;
		else return n.count;
	}
	
	public void oput(String s,Integer i){
		Node temp=this.root;
		int c=i.compareTo(temp.index);
		while(temp!=null){
			if(c>0) temp=temp.right;
			else if(c<0)temp=temp.left;
			else temp.info=s;
		}
		temp=new Node(s,i);
		
		temp.left=null;
		temp.right=null;
	}
	
	public String get(Integer i){
		Node temp=root;
		int c=temp.index.compareTo(i);
		while(temp!=null){
			if(c>0) temp=temp.left;
			else if(c<0) temp=temp.right;
			else return temp.info;
		}
		return null;
	}

	public void put(String info, Integer loc){
		root=put(root, info, loc);
	}
	public Node put(Node N, String info, Integer loc){
		if(N==null) return new Node(info, loc);
		int c=N.index.compareTo(loc);
		if(c>0) N.left=put(N.left, info, loc);
		else if(c<0) N.right=put(N.right, info, loc);
		else N.info=info;
		N.count=1+size(N.left)+size(N.right); 
		return N;
	}
	public String min(){
		return min(root).info;
	}
	public Node min(Node n){
		if(n.left==null) return n;
		return min(n.left);
	}
	public String max(){
		Node temp=root;
		while(temp.right!=null)
			temp=temp.right;
		return temp.info;

	}
	public Node max(Node n){
		if(n.right==null) return n;
		return max(n.right);
	}
	public Node floor(Node n,Integer i){
		if(n==null) return null;
		int c=n.index.compareTo(i);
		if(c>0) return floor(n.left, i);
		else if(c==0) return n;
		else{
			Node t=floor(n.right,i);
			if (t==null) return n;
			else return t;
		}
	}
	public String floor(Integer i){
		Node t=floor(this.root, i);
		if(t==null) return null;
		else return t.info;
	}
	public Node ceiling(Node n, Integer i){
		if(n==null) return null;
		int c=n.index.compareTo(i);
		if(c<0) return ceiling(n.right, i);
		else if(c==0) return n;
		else{
			Node t=ceiling(n.left,i);
			if(t==null) return n;
			else return t;
		}
	}
	public String ceiling(Integer i){
		Node t=ceiling(root, i);
		if (t==null) return null;
		else return t.info;
	}
	
	public int rank(Node n,Integer i){
		if (n==null) return 0;
		int c=n.index.compareTo(i);
		if(c<0) return rank(n.left, i);
		else if(c>0) return 1+size(n.left)+rank(n.right,i);
		else return size(n.left);
	}
	public Iterable<Integer> Indexs(){
		Queue<Integer> q=new LinkedList<Integer>();
		inorder(root, q);
		return q;
	}
	public void inorder(Node n, Queue<Integer> q){
		if(n==null) return;
		inorder(n.left,q);
		q.offer(n.index);
		inorder(n.right,q);
		
	}
	public Node delmin(Node n){
		if(n.left==null) return n.right;
		n.left=delmin(n.left);
		n.count--;
		return n;
	}
	public void delmin(){
		root=delmin(root);
	}
	public void delmax(){
		root=delmax(root);
	}
	public Node delmax(Node n){
		if(n.right==null) return n.left;
		n.right=delmax(n.right);
		n.count=1+size(n.left)+size(n.right);
		return n;
	}
	
	public Node delete(Node n, Integer i){
		int c=n.index.compareTo(i);
		if(c>0) n.left=delete(n.left, i);
		else if(c<0) n.right=delete(n.right, i);
		else{
			if(n.right==null) return n.left;
			if(n.left==null) return n.right;
			Node t=n;
			n=min(t.right);
			n.right=delmin(t.right);
			n.left=t.left;
			
		}
		n.count=1+size(n.left)+size(n.right);
		return n;
	}
	public void delete(Integer i){
		root=delete(root, i);
	}
	public boolean isRed(Node n){
		if(n==null) return false;
		return n.color;
	}
	public Node rotateLeft(Node n){
		Node t=n.right;
		n.right=t.left;
		t.left=n;
		t.color=n.color;
		n.color=RED;
		return t;
	}
	public Node rotateRight(Node n){
		Node t=n.left;
		n.left=t.right;
		t.right=n;
		t.color=n.color;
		n.color=RED;
		return t;
	}
	public void flipColors(Node n){
		n.right.color=BLACK;
		n.left.color=BLACK;
		n.color=RED;
	}
	public Node advput(Node n, Integer l, String s){
		
		if(n==null) return new Node(s,l,RED);
		int c=n.index.compareTo(l);
		if(c>0) advput(n.left,l,s);
		else if(c<0) advput(n.right,l,s);
		else n.info=s;
		if(isRed(n.right)&&!isRed(n.left)) rotateLeft(n);
		if(isRed(n.left)&&isRed(n.left.left)) rotateRight(n);
		if(isRed(n.right)&&isRed(n.left)) flipColors(n);
		
		return n;
		
	}
	public void advput(Integer l, String s){
		root=advput(root, l, s);
	}
	public Boolean contains(Node n, Integer i){
		if (n==null) return false;
		int c=n.index.compareTo(i);
		if(c>0) return contains(n.left,i);
		else if(c<0) return contains(n.right,i);
		else return true;
	}
	public Boolean contains(Integer i){
		return contains(root, i);
	}
	public int rank(Integer i){
		return rank(root, i);
	}
	public int size(Integer hi, Integer lo){
		if(contains(lo)) return rank(hi)-rank(lo)+1;
		else return rank(hi)-rank(lo);
		//return 0;
	}
}
