package lab;

public class NanoTime {
	public static void main(String[]args){
		while (true)
			if(System.currentTimeMillis()%1000==0)
				System.out.println(System.nanoTime());
		//conclusion: 1,000,000,000 nanoTime=1,000 ms=1s
	}
}
