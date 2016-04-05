package lab;

public class Goascii {
	public static void main(String[]args){
		for(int i=0;i<255;i++){
			System.out.print((char)(i));
			if(i%16==0)
				System.out.println();
		}
	}
}
