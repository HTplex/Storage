package week8;

public class HelloHash {
	public static void main(String[]args){
	}
	int n=50;
	class Node{
		Node next;
		Integer info;
		Integer key;
		Node(Integer key, Integer info){
			this.info=info;
			this.key=key;
			//this.next=null;
		}
	}
	private Node[] st=new Node[n];
	private int hash(Integer i){
		return (i.hashCode()&0x7fffffff%n);
	}
	public Integer get(int k){
		int i=hash(k);
		for(Node m=st[i];m!=null;m=m.next)
			if(m.key==k)
				return m.info;
			return null;
	}
	public void insert(Integer key, Integer info){
		Node n=this.st[hash(key)];
		while(n!=null){
			if(n.key==key) n.info=info;
			n=n.next;
		}
		n=new Node(key, info);
	}
}

