package multidarray;
import java.util.Scanner;
public class FreeBin {
	public static void main(String[]args){
		int [] bin=new int[9];
		for(int i=0;i<255;i++){
			bin[0]++;
			for(int ii=0;ii<9;ii++){
				if(bin[ii]==2){
					bin[ii+1]++;
					bin[ii]=0;
				}
			}
			for(int q:bin)
				System.out.print(q);
			System.out.println();
		}
	}
}
