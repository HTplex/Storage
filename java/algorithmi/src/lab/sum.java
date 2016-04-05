package lab;

public class sum {
	public static void main(String[]args){
		long sum=0;
		long t=System.currentTimeMillis();
		for(long i=0;i<Integer.MAX_VALUE-1;i++){
			sum+=1;
		}
		System.out.println(sum);
		System.out.println(System.currentTimeMillis()-t);
		
	}
}
