package week8;

public class LinHash {
	public static void main(String[]args){
	}
	class Node{
		Integer key;
		Integer info;
		Node(Integer key, Integer info){
			this.key=key;
			this.info=info;
		}
	}
	int N=201;
	Node[] st=new Node[N];
	public void insert(Integer key, Integer info){
		int h=hash(key);
		while(st[h]==null){
			if(st[h].key==key) break;
			h=(h+1)%N;
		}
		st[h]=new Node(key, info);
	}
	public Integer get(Integer key){
		int k=hash(key);
		while(st[k]!=null){
			if(st[k].key==key) return st[k].info;
			k=(k+1)%N;
		}
		return null;
	}
	public int hash(Integer key){
		return(key.hashCode()&0x7FFFFFFF%N);
	}
}
