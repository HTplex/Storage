package week1;

public class CD3Sum {
	private int[] num;
	public CD3Sum(int N){
		num=new int[N];
	}
	
	public void inp(int a, int b){
		this.num[a]=b;
	}
	
	public void facinp(){
		for(int i=0;i<this.num.length;i++){
			this.num[i]=(int)(Math.pow(-1, i))*i;
		}
	}
	
	public void raninp(){
		for(int i=0;i<this.num.length;i++){
			this.num[i]=(int)(Math.pow(-1,i)*10*this.num.length*Math.random());
			java.util.Arrays.sort(this.num);
		}
	}
	
	public void trisumdull(){
		for(int i=0;i<this.num.length;i++){
			for(int i2=0;i2<this.num.length;i2++){
				for(int i3=0;i3<this.num.length;i3++){
					if(num[i]+num[i2]+num[i3]==0){
						System.out.println(num[i]+"\t"+num[i2]+"\t"+num[i3]);
					}
				}
			}
		}
	}
	
	public void trisumimp(){
		for(int i=0;i<this.num.length;i++){
			for(int i1=0;i1<this.num.length;i1++){
				int i2=java.util.Arrays.binarySearch(num, -num[i]-num[i1]);
				if(i2>=0)
				System.out.println(num[i]+"\t"+num[i1]+"\t"+num[i2]);
			}
		}
	}
	
	
	
	
	
	
	
	
	public static void main(String[]args){
		
	}
}
